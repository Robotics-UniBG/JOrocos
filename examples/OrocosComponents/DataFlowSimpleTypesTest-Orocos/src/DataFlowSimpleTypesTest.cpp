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
 * File: DataFlowSimpleTypesTest.cpp
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

#include "DataFlowSimpleTypesTest/DataFlowSimpleTypesTest.hpp"



namespace JOrocos{


DataFlowSimpleTypesTest::DataFlowSimpleTypesTest(string const& name)
: TaskContext(name),
  boolInputPort("boolInputPort"),
  charInputPort("charInputPort"),
  ucharInputPort("ucharInputPort"),
  doubleInputPort("doubleInputPort"),
  floatInputPort("floatInputPort"),
  intInputPort("intInputPort"),
  uintInputPort("uintInputPort"),
  longInputPort("longInputPort"),
  ulongInputPort("ulongInputPort"),
  stringInputPort("stringInputPort"),
  boolOutputPort("boolOutputPort"),
  charOutputPort("charOutputPort"),
  ucharOutputPort("ucharOutputPort"),
  doubleOutputPort("doubleOutputPort"),
  floatOutputPort("floatOutputPort"),
  intOutputPort("intOutputPort"),
  uintOutputPort("uintOutputPort"),
  longOutputPort("longOutputPort"),
  ulongOutputPort("ulongOutputPort"),
  stringOutputPort("stringOutputPort")
{

	this->addPort(boolInputPort).doc("The input port");
	this->addPort(charInputPort).doc("The input port");
	this->addPort(ucharInputPort).doc("The input port");
	this->addPort(doubleInputPort).doc("The input port");
	this->addPort(floatInputPort).doc("The input port");
	this->addPort(intInputPort).doc("The input port");
	this->addPort(uintInputPort).doc("The input port");
	this->addPort(longInputPort).doc("The input port");
	this->addPort(ulongInputPort).doc("The input port");
	this->addPort(stringInputPort).doc("The input port");

	this->addPort(stringOutputPort).doc("The output port");
	this->addPort(boolOutputPort).doc("The output port");
	this->addPort(charOutputPort).doc("The output port");
	this->addPort(ucharOutputPort).doc("The output port");
	this->addPort(doubleOutputPort).doc("The output port");
	this->addPort(floatOutputPort).doc("The output port");
	this->addPort(intOutputPort).doc("The output port");
	this->addPort(uintOutputPort).doc("The output port");
	this->addPort(longOutputPort).doc("The output port");
	this->addPort(ulongOutputPort).doc("The output port");


}

bool DataFlowSimpleTypesTest::startHook(){

	return true;

}

bool DataFlowSimpleTypesTest::configureHook(){

	return true;

}

void DataFlowSimpleTypesTest::stopHook(){

}

void DataFlowSimpleTypesTest::cleanupHook(){

}

void DataFlowSimpleTypesTest::updateHook() {


	bool input;

	if(boolInputPort.read(input) == NewData){



		boolOutputPort.write(!input);

	}

	char charInput;

	if(charInputPort.read(charInput) == NewData){

		charOutputPort.write(charInput);

	}

	unsigned char ucharInput;

	if(ucharInputPort.read(ucharInput) == NewData){

		ucharOutputPort.write(ucharInput);

	}

	double doubleInput;

	if(doubleInputPort.read(doubleInput) == NewData){

		doubleOutputPort.write(doubleInput);

	}

	float floatInput;

	if(floatInputPort.read(floatInput) == NewData){

		floatOutputPort.write(floatInput);

	}

	int intInput;

	if(intInputPort.read(intInput) == NewData){

		intOutputPort.write(intInput);

	}

	unsigned int uintInput;

	if(uintInputPort.read(uintInput) == NewData){

		uintOutputPort.write(uintInput);

	}

	long longInput;

	if(longInputPort.read(longInput) == NewData){

		longOutputPort.write(longInput);

	}

	unsigned long ulongInput;

	if(ulongInputPort.read(ulongInput) == NewData){

		ulongOutputPort.write(ulongInput);

	}

	string stringInput;

	if(stringInputPort.read(stringInput) == NewData){

		stringOutputPort.write(stringInput);

	}


}
}

ORO_CREATE_COMPONENT( JOrocos::DataFlowSimpleTypesTest );

