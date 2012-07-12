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
 * File: OrocosArgument.java
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

/**
 * The Class OrocosArgument models an argument of an Orocos operation.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class OrocosArgument {

	/** The name of the argument. */
	String name;
	
	/** The type of the argument. */
	String dataType;
	
	/** The description of the argument. */
	String description;
	
	/**
	 * Instantiates a new Orocos argument.
	 *
	 * @param name the name of the argument
	 * @param type the type of the argument
	 * @param description the description of the argument
	 */
	public OrocosArgument(String name, String type, String description) {
		super();
		this.name = name;
		this.dataType = type;
		this.description = description;
	}

	/**
	 * Returns the argument name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the argument type.
	 *
	 * @return the type
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * Returns the argument description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
}
