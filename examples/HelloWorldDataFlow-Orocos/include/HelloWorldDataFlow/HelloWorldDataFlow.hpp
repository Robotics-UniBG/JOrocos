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
 * File: TrajectoryAdapter.hpp
 * Created: Feb 17, 2012
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

#ifndef TRAJECTORY_ADAPTER_HPP
#define TRAJECOTRY_ADAPTER_HPP

#include <rtt/Port.hpp>
#include <rtt/TaskContext.hpp>
#include <rtt/Property.hpp>

#include <rtt/marsh/Marshalling.hpp>

#include <geometry_msgs/typekit/Types.hpp>
#include <brics_rn_msgs/typekit/Types.hpp>
#include <sensor_msgs/typekit/Types.hpp>
#include <nav_msgs/typekit/Types.hpp>
#include <std_msgs/typekit/Types.hpp>

#include <vector>
#include <deque>

#include "mbn_common/Events.hpp"

using namespace std;
using namespace RTT;

namespace Navigation{

const string MOTION_DONE_EVENT = "MOTION_DONE";

class TrajectoryAdapter : public TaskContext{

public:

	TrajectoryAdapter(string const& name);

private:


	OutputPort<brics_rn_msgs::Trajectory> trajectoryOutPort;
	OutputPort<std_msgs::String> motionStatusOutPort;

	InputPort<brics_rn_msgs::Trajectory> trajectoryInPort;
	InputPort<sensor_msgs::LaserScan> laserScanInPort;
	InputPort<nav_msgs::Odometry> odometryInPort;
	InputPort<std_msgs::String> motionStatusInPort;


	brics_rn_msgs::Trajectory targetTrajectory;
	brics_rn_msgs::Trajectory adaptedTrajectory;

	int currentWaypointIndex;

	nav_msgs::Odometry lastOdometry;

	float goalDistanceThreeshold;
	float goalOrientationThreeshold;

	//bool start;

	bool velocityChanged;

	boost::shared_ptr<Marshalling> marshalling;

	bool startHook();
	bool configureHook();
	void updateHook();
	void stopHook();
	void cleanupHook();

	bool computeNewTarget();
	float getDistance(geometry_msgs::Point pointA, geometry_msgs::Point pointB);
	float getDeltaTheta(geometry_msgs::Quaternion quatA, geometry_msgs::Quaternion quatB);
//	void fillPositionsQueue();

};

}

#endif
