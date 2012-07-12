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
 * File: PortReadingResult.java
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

import it.unibg.robotics.jorocos.core.OrocosDataPort.OutputPortStatus;

/**
 * The Class ReadOperationResult models the result of a reading operation from
 * an Orocos input port. ReadOperationResult contains information about the 
 * status of the data on the output port (new, old, no_data - see {@link OutputPortStatus})
 * and the value of the data (if present).
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class PortReadingResult {

	/** The port status of the data on the output port (see {@link OutputPortStatus}). */
	private OutputPortStatus status;
	
	/** The value of the data on the output port (if present). */
	private Object value;
	
	/**
	 * Instantiates a new read operation result.
	 *
	 * @param outputPortStatus the status of the data on the output port (see {@link OutputPortStatus})
	 * @param value the value of the data
	 */
	public PortReadingResult(OutputPortStatus outputPortStatus, Object value) {
		super();
		this.status = outputPortStatus;
		this.value = value;
	}

	/**
	 * Returns the the status of the data on the output port.
	 *
	 * @return the the status of the data on the output port
	 */
	public OutputPortStatus getStatus() {
		return status;
	}

	/**
	 * Returns the value of the data on the output port.
	 *
	 * @return the value of the data on the output port
	 */
	public Object getValue() {
		return value;
	}
	
	
	
}
