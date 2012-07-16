/********************************************************************************
 *
 * DataFlowSimpleTypesTest
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
 * File: DataFlowSimpleTypesTest.hpp
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

#ifndef HELLO_WORLD_DATA_FLOW_HPP
#define HELLO_WORLD_DATA_FLOW_HPP

#include <rtt/os/main.h>

#include <rtt/Port.hpp>
#include <rtt/TaskContext.hpp>
#include <rtt/Property.hpp>
#include <rtt/Logger.hpp>
#include <rtt/Property.hpp>
#include <rtt/Attribute.hpp>
#include <rtt/Component.hpp>


using namespace std;
using namespace RTT;

namespace JOrocos{

class DataFlowSimpleTypesTest : public TaskContext{

public:

	DataFlowSimpleTypesTest(string const& name);

private:


	InputPort<bool> boolInputPort;
	InputPort<char> charInputPort;
	InputPort<unsigned char> ucharInputPort;
	InputPort<double> doubleInputPort;
	InputPort<float> floatInputPort;
	InputPort<int> intInputPort;
	InputPort<unsigned int> uintInputPort;
	InputPort<long> longInputPort;
	InputPort<unsigned long> ulongInputPort;
	InputPort<string> stringInputPort;

	OutputPort<bool> boolOutputPort;
	OutputPort<char> charOutputPort;
	OutputPort<unsigned char> ucharOutputPort;
	OutputPort<double> doubleOutputPort;
	OutputPort<float> floatOutputPort;
	OutputPort<int> intOutputPort;
	OutputPort<unsigned int> uintOutputPort;
	OutputPort<long> longOutputPort;
	OutputPort<unsigned long> ulongOutputPort;
	OutputPort<string> stringOutputPort;

//	InputPort<vector<bool> > vboolInputPort;
//	InputPort<vector<char> > vcharInputPort;
//	InputPort<vector<unsigned char> > vucharInputPort;
//	InputPort<vector<double> > vdoubleInputPort;
//	InputPort<vector<float> > vfloatInputPort;
//	InputPort<vector<int> > vintInputPort;
//	InputPort<vector<unsigned int> > vuintInputPort;
//	InputPort<vector<long> > vlongInputPort;
//	InputPort<vector<unsigned long> > vulongInputPort;
//	InputPort<vector<string> > vstringInputPort;
//
//	OutputPort<vector<bool> > vboolOutputPort;
//	OutputPort<vector<char> > vcharOutputPort;
//	OutputPort<vector<unsigned char> > vucharOutputPort;
//	OutputPort<vector<double> > vdoubleOutputPort;
//	OutputPort<vector<float> > vfloatOutputPort;
//	OutputPort<vector<int> > vintOutputPort;
//	OutputPort<vector<unsigned int> > vuintOutputPort;
//	OutputPort<vector<long> > vlongOutputPort;
//	OutputPort<vector<unsigned long> > vulongOutputPort;
//	OutputPort<vector<string> > vstringOutputPort;



	bool startHook();
	bool configureHook();
	void updateHook();
	void stopHook();
	void cleanupHook();

};

}

#endif
