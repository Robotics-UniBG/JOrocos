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
 * File: AbstractOrocosSystem.java
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

import it.unibg.robotics.jorocos.exceptions.SystemNotConnectedException;

import java.util.ArrayList;

/**
 * The Class OrocosSystem represents a proxy of a running Orocos system.
 * This class offers the functionality that allows a developer to connect 
 * his software to an Orocos system, introspect its components and retrieve 
 * references to them.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public abstract class AbstractOrocosSystem{

	/** The list of the identifiers of all the available task contexts of the Orocos system */
	protected ArrayList<String> componentsId = null;
	
	/** The list of all the available task contexts of the Orocos system */
	protected ArrayList<AbstractOrocosComponent> components = null;
	
	/**
	 * Create a connection to the running Orocos System.
	 * 
	 * @return true if the connections has been created successfully 
	 */
	abstract public boolean connect();

	/**
	 * Returns a proxy of the requested Orocos component.
	 * 
	 * @param componentName the name of the component
	 * @param introspect if true the method will introspect the component and store
	 * all the information about its services, ports and properties.
	 * This information can be retrieved by using the methods 
	 * {@link AbstractOrocosComponent#getOwnService()} 
	 * (which provided information about services, operations and properties) 
	 * and {@link AbstractOrocosComponent##getPorts()} provided by the returned object.
	 * @return the proxy of Orocos component
	 * @throws SystemNotConnectedException
	 */
	abstract public AbstractOrocosComponent getComponent(String componentName, boolean introspect)
			throws SystemNotConnectedException;
	
	/**
	 * Returns a list containing the the names of all the Orocos components running on the system/
	 * @return a list of all the names of the system components
	 * @throws SystemNotConnectedException
	 */
	public ArrayList<String> getComponentsNames() throws SystemNotConnectedException {
		return componentsId;
	}

	/**
	 * Returns a list containing all the Orocos components running on the system.
	 * @return a list containing all the components
	 * @throws SystemNotConnectedException
	 */
	public ArrayList<AbstractOrocosComponent> getComponents()
			throws SystemNotConnectedException {
		return components;
	}

	/**
	 * Introspects all the components of the system. 
	 * This method retrieves the list of the components ids and create proxies to this component 
	 * by using the constructor {@link AbstractOrocosComponent#AbstractOrocosComponent(RTT.corba.CTaskContext, boolean)}
	 * and enabling the introspection.
	 * @throws SystemNotConnectedException
	 */
	abstract public void introspectAllComponents() throws SystemNotConnectedException;

}
