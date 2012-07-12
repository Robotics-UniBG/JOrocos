/********************************************************************************
 *
 * JOrocos Library
 *
 * Copyright (c) 2011
 * All rights reserved.
 *
 * Luca Gherardi
 * University of Bergamo
 * Dept. of Information Technology and Mathematics
 *
 * -------------------------------------------------------------------------------
 *
 * File: OrocosOperation.java
 * Created: Jul 27, 2011
 *
 * Author: <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * 
 * Supervised by: <A HREF="mailto:brugali@unibg.it">Davide Brugali</A>
 * 
 * In cooperation with: <A HREF="mailto:herman.bruyninckx@mech.kuleuven.be">Herman Bruyninckx</A>
 *
 * -------------------------------------------------------------------------------
 *
 * This software is published under a dual-license: GNU Lesser General Public
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
package it.unibg.robotics.jorocos.core;

import java.util.ArrayList;

/**
 * The Class OrocosOperation models an operation provided by an Orocos
 * task context.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class OrocosOperation{

	/** The name of the operation. */
	private String name;
	
	/** The description of the operation. */
	private String description;
	
	/** The return type of the operation. */
	private String returnType;
	
	/** The list containing the arguments of the operation. */
	private ArrayList<OrocosArgument> arguments;
	
	
	/**
	 * Instantiates a new orocos operation.
	 *
	 * @param name the name of the operation
	 * @param description the description of the operation
	 * @param resultType the result type of the operation
	 * @param arguments the arguments of the operation
	 */
	public OrocosOperation(String name, String description,
			String resultType, ArrayList<OrocosArgument> arguments) {
		super();
		this.name = name;
		this.description = description;
		this.returnType = resultType;
		this.arguments = arguments;
	}

	/**
	 * Returns the name of the operation.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of the operation
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the return type of the operation.
	 *
	 * @return the return type
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * Returns the number of the operation arguments .
	 *
	 * @return the arguments count
	 */
	public int getArgumentsCount() {
		return arguments.size();
	}

	/**
	 * Returns the arguments of the operation.
	 *
	 * @return the arguments
	 */
	public ArrayList<OrocosArgument> getArguments() {
		return arguments;
	}

}
