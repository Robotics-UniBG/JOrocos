/********************************************************************************
 *
 * TrajectoryAdapter
 *
 * Copyright (c) 2012
 * All rights reserved.
 *
 * Luca Gherardi and Alexey Zakharov
 * University of Bergamo
 * Dept. of Information Technology and Mathematics
 *
 * -------------------------------------------------------------------------------
 *
 * File: TrajectoryAdapter.cpp
 * Created: Feb 09, 2012
 *
 * Author: <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 *
 * Supervised by: <A HREF="mailto:brugali@unibg.it">Davide Brugali</A>
 *
 * -------------------------------------------------------------------------------
 *
 * This sofware is published under a dual-license: GNU Lesser General Public
 * License LGPL 2.1 and BSD license. The dual-license implies that users of this
 * code may choose which terms they prefer.
 *
 * -------------------------------------------------------------------------------
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  - Neither the name of the University of Bergamo nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License LGPL as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version or the BSD license.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License LGPL and the BSD license for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License LGPL and BSD license along with this program.
 *
 *******************************************************************************/

#include "TrajectoryAdapter/TrajectoryAdapter.hpp"

#include <rtt/os/main.h>

#include <rtt/Logger.hpp>
#include <rtt/Property.hpp>
#include <rtt/Attribute.hpp>
#include <rtt/Service.hpp>
#include <rtt/Component.hpp>

#include <math.h>
#include <sys/time.h>

#include <tf/tf.h>
#include <angles/angles.h>

namespace Navigation{


TrajectoryAdapter::TrajectoryAdapter(string const& name)
: TaskContext(name),
  trajectoryOutPort("trajectoryOutPort"),
  motionStatusOutPort("motionStatusOutPort"),
  trajectoryInPort("trajectoryInPort"),
  laserScanInPort("laserScanInPort"),
  odometryInPort("odometryInPort"),
  motionStatusInPort("motionStatusInPort")
{

	this->addPort(trajectoryOutPort).doc("Adapted Trajectory output port");
	this->addPort(motionStatusOutPort).doc("Motion Status output port");

	this->addPort(trajectoryInPort).doc("Trajectory input port");
	this->addPort(laserScanInPort).doc("Laser scan input port");
	this->addPort(odometryInPort).doc("Odometry input port");
	this->addEventPort(motionStatusInPort).doc("Motion Status input port");

	this->addAttribute("inputTrajectory",targetTrajectory);
	this->addAttribute("adaptedTrajectory",adaptedTrajectory);

	this->addProperty("goalDistanceThreeshold",goalDistanceThreeshold)
							.doc("min difference between current pose and target pose "
									"in order to consider the goal position reached");
	this->addProperty("goalOrientationThreeshold",goalOrientationThreeshold)
									.doc("min difference between current theta and target theta "
											"in order to consider the goal orientation reached");

	marshalling = this->getProvider<Marshalling>("marshalling");


}

bool TrajectoryAdapter::startHook(){

	//fillPositionsQueue();
	return true;

}

bool TrajectoryAdapter::configureHook(){

	//marshalling = this->getProvider<Marshalling>("marshalling");
	return true;

}

void TrajectoryAdapter::stopHook(){



}

void TrajectoryAdapter::cleanupHook(){
	//marshalling->writeProperties("properties/TrajectoryAdapter.cpf");
}

void TrajectoryAdapter::updateHook() {

	sensor_msgs::LaserScan laserScanInput;
	nav_msgs::Odometry odometryInput;
	brics_rn_msgs::Trajectory trajectoryInput;
	std_msgs::String eventInput;
	std_msgs::String eventOutput;


//	if(odometryInPort.read(odometryInput) == NewData){
//
//		lastOdometry = odometryInput;
//
//		float goalDistance = fabs(getDistance(lastOdometry.pose.pose.position, targePose.pose.position));
//		float goalDeltaThera = fabs(getDeltaTheta(lastOdometry.pose.pose.orientation, targePose.pose.orientation));
//
//		if(goalDistance < goalDistanceThreeshold && goalDeltaThera < goalOrientationThreeshold){
//			computeNewTarget();
//		}
//
//	}
//
//	if(laserScanInPort.read(laserScanInput) == NewData){
//
//		float goalDistance = fabs(getDistance(lastOdometry.pose.pose.position, targePose.pose.position));
//		for(vector<float>::iterator it = laserScanInput.ranges.begin(); it < laserScanInput.ranges.end(); it++){
//			// Here we should check also the direction of the measure because in this case we are blocking the
//			// robot also if the obstacle is on his left or right sides
//			if((*it) < goalDistance){
//				computeNewTarget();
//				break;
//			}
//		}
//
//	}

	if(trajectoryInPort.read(trajectoryInput) == NewData){
		targetTrajectory = trajectoryInput;

		adaptedTrajectory = trajectoryInput;
		trajectoryOutPort.write(adaptedTrajectory);

		eventOutput.data = mbn_common::MOTION_STARTED_EVENT;
		motionStatusOutPort.write(eventOutput);
	}

	if(motionStatusInPort.read(eventInput) == NewData){

		if(eventInput.data.compare(MOTION_DONE_EVENT) == 0){

			eventOutput.data = mbn_common::POSE_GOAL_REACHED_EVENT;
			motionStatusOutPort.write(eventOutput);

		}

	}




}


bool TrajectoryAdapter::computeNewTarget(){

	geometry_msgs::PoseStamped computedTarget;

	// Compute the new target, store it in targetPose and write on the output port

	//	if(start){
	//			if(positions.size() > 0){
	//				geometry_msgs::Pose2D currentPose = positions.at(0);
	//				positions.pop_front();
	//				nextTargetPoseOutPort.write(currentPose);
	//			}
	//			if(velocityChanged){
	//				nextTargetVelOutPort.write(currentVelocity);
	//				velocityChanged = false;
	//			}
	//		}


//	targePose = computedTarget;
//	nextTargetPoseOutPort.write(targePose);
	return true;

}

float TrajectoryAdapter::getDistance(geometry_msgs::Point pointA, geometry_msgs::Point pointB){

	return sqrt( pow((pointA.x - pointB.x),2.0) + pow((pointA.y - pointB.y),2.0) + pow((pointA.z - pointB.z),2.0));

}

float TrajectoryAdapter::getDeltaTheta(geometry_msgs::Quaternion quatA, geometry_msgs::Quaternion quatB){

	return angles::normalize_angle(tf::getYaw(quatA)) - angles::normalize_angle(tf::getYaw(quatB));

}

//void TrajectoryAdapter::fillPositionsQueue(){
//
////	positionsQueue.clear();
////	for(vector<geometry_msgs::Pose2D>::iterator it = currentPath.begin(); it!=currentPath.end(); ++it){
////		positionsQueue.push_back(*it);
////	}
//
//}

}

ORO_CREATE_COMPONENT( Navigation::TrajectoryAdapter );

