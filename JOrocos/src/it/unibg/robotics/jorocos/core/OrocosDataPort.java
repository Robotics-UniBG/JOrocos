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
 * File: OrocosDataPort.java
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
 * The Class OrocosDataPort models a data port of an Orocos task context.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class OrocosDataPort{
	
	/**
	 * The Enum PortType defines the available port types.
	 */
	public enum PortType{

		/** The input port type. */
		INPUT_PORT ("Input port"), 
		/** The output port type. */
		OUTPUT_PORT ("Output port");
		
		/** The string that reports the port type */
		private String type;
		
		/**
		 * Instantiates a new PortType
		 * @param type
		 */
		PortType(String type){
			this.type = type;
		}
		
		/**
		 * Returns the string corresponding to the port type.
		 */
		public String toString(){
			return type;
		}
	}

	/**
	 * The Enum OutputPortStatus defines the available status
	 * of a data published on an output port for a certain {@link AbstractOrocosConnection}.
	 */
	public enum OutputPortStatus{

		/** 
		 * The new data state. 
		 * In this case the data on the channel represent by the connection to the output port.
		 * has not been read yet.
		 * */
		NEW_DATA, 
		/** 
		 * The old data state. 
		 * In this case the data on the channel represent by the connection to the output port.
		 * has already been read.
		 * */
		OLD_DATA, 
		/** 
		 * The no data state. 
		 * In this case there is no data on the channel represent by the connection to the output port.
		 * */
		NO_DATA
	}
	
	/** The name of the data port. */
	private String name;
	
	/** The type of the data port. */
	private PortType type;
	
	/** The data type of the data port. */
	private String dataType;
	
	/** The component containg the data port. */
	private AbstractOrocosComponent component;
	
	/**
	 * Instantiates a new orocos data port.
	 *
	 * @param name the name of the data port
	 * @param type the type of the data port
	 * @param dataType the data type of the data port
	 */
	public OrocosDataPort(String name, PortType type, String dataType) {
		super();
		this.name = name;
		this.type = type;
		this.dataType = dataType;
	}

	/**
	 * Returns the name of the data port.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the type of the data port.
	 *
	 * @return the type
	 */
	public PortType getPortType() {
		return type;
	}

	/**
	 * Returns the data type of the data port.
	 *
	 * @return the data type
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * Returns the component containing the data port.
	 *
	 * @return the component
	 */
	public AbstractOrocosComponent getComponent() {
		return component;
	}

	/**
	 * Sets the component containing the port.
	 *
	 * @param component the new component
	 */
	public void setComponent(AbstractOrocosComponent component) {
		this.component = component;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Port name: " + name);
		sb.append(" - Port type: " + type);
		sb.append(" - Port data type : " + dataType);
		return sb.toString();
	}
	
	
}
