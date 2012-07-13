/********************************************************************************
 *
 * HelloWorldDataFlow
 *
 * Copyright (c) 2012
 * All rights reserved.
 *
 * Luca Gherardi
 * University of Bergamo
 * Dept. of Information Technology and Mathematics
 *
 * -------------------------------------------------------------------------------
 *
 * File: HelloWorldDataFlow.cpp
 * Created: July 13, 2012
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

#include "HelloWorldDataFlow/HelloWorldDataFlow.hpp"



namespace JOrocos{


HelloWorldDataFlow::HelloWorldDataFlow(string const& name)
: TaskContext(name),
  inputPort("inputPort"),
  outputPort("outputPort")
{

	this->addPort(inputPort).doc("The input port");
	this->addPort(outputPort).doc("The output port");

}

bool HelloWorldDataFlow::startHook(){

	return true;

}

bool HelloWorldDataFlow::configureHook(){

	return true;

}

void HelloWorldDataFlow::stopHook(){

}

void HelloWorldDataFlow::cleanupHook(){

}

void HelloWorldDataFlow::updateHook() {

	string input;

	if(inputPort.read(input) == NewData){

		string output = "Hello " + input;

		cout << output << endl;

		outputPort.write(output);

	}

}
}

ORO_CREATE_COMPONENT( JOrocos::HelloWorldDataFlow );

