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
 * File: CorbaOrocosComponent.java
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
package it.unibg.robotics.jorocos.corba;

import it.unibg.robotics.jorocos.core.AbstractOrocosComponent;
import it.unibg.robotics.jorocos.core.AbstractOrocosConnection;
import it.unibg.robotics.jorocos.core.OrocosDataPort;
import it.unibg.robotics.jorocos.core.AbstractOrocosConnection.LockPolicy;
import it.unibg.robotics.jorocos.core.OrocosDataPort.PortType;
import it.unibg.robotics.jorocos.exceptions.WrongPortTypeException;

import java.util.HashMap;
import java.util.Observer;

import org.apache.log4j.Logger;

import RTT.corba.CNoCorbaTransport;
import RTT.corba.CNoSuchPortException;
import RTT.corba.CTaskContext;

/**
 * The Class CorbaOrocosComponent is a specialization of AbstractOrocosComponent
 * defined for the CORBA transport.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class CorbaOrocosComponent extends AbstractOrocosComponent{

	private Logger logger = Logger.getLogger(CorbaOrocosConnection.class);
	

	/**
	 * Instantiates a new Corba Orocos component.
	 *
	 * @param taskContext a pointer to the task context
	 * @param introspect if true the constructor will introspect the component and store
	 * all the information about its services, ports and properties.
	 */
	public CorbaOrocosComponent(CTaskContext taskContext, boolean introspect) {
		super(taskContext, introspect);
	}

	/**
	 * @see AbstractOrocosComponent#introspectOwnService(boolean)
	 */
	@Override
	public void introspectOwnService(boolean introspect){
		
		ownService = new CorbaOrocosService(getCTaskContext().getProvider("this"), introspect);

	}
	
	/**
	 * @see AbstractOrocosComponent#createDataConnectionToInputPort(String, LockPolicy, Object)
	 */
	@Override
	public boolean createDataConnectionToInputPort(final String portName, LockPolicy lockPolicy, Object client) throws WrongPortTypeException{
		OrocosDataPort port = getPort(portName);
		if(port.getPortType() == PortType.OUTPUT_PORT){
			throw new WrongPortTypeException(port.getPortType());
		}
		if(inputPortConnectionsMap.containsKey(portName) && 
				inputPortConnectionsMap.get(portName).containsKey(client)){
			return true;
		}else{
			try {
				AbstractOrocosConnection connection = CorbaOrocosConnection.createDataConnection(port, lockPolicy);
				// Store the connection into the hashmap
				HashMap<Object, AbstractOrocosConnection> map;
				// if a connection to the same port already exist extract the map,
				// hotherwise create a new map
				if(inputPortConnectionsMap.containsKey(portName)){
					map = inputPortConnectionsMap.get(portName);
					inputPortConnectionsMap.remove(portName);
				}else{
					map = new HashMap<Object, AbstractOrocosConnection>();
				}
				// add to the map the couple client/connection
				map.put(client, connection);
				// store the map in the input port connections map
				inputPortConnectionsMap.put(portName, map);
				return true;
			} catch (CNoCorbaTransport e) {
				logger.error("Error on the Corba Transport. See the exception stack trace.");
				e.printStackTrace();
				return false;
			} catch (CNoSuchPortException e) {
				logger.error("The port you required does not exist");
				e.printStackTrace();
				return false;
			} 
		}
	}

	/**
	 * @see AbstractOrocosComponent#createBufferConnectionToInputPort(String, LockPolicy, Object, int)
	 */
	@Override
	public boolean createBufferConnectionToInputPort(String portName, LockPolicy lockPolicy, Object client, int bufferSize) throws WrongPortTypeException{
		OrocosDataPort port = getPort(portName);
		if(port.getPortType() == PortType.OUTPUT_PORT){
			throw new WrongPortTypeException(port.getPortType());
		}
		if(inputPortConnectionsMap.containsKey(portName) && 
				inputPortConnectionsMap.get(portName).containsKey(client)){
			return true;
		}else{
			try {
				AbstractOrocosConnection connection = CorbaOrocosConnection.createBufferConnection(port, lockPolicy, bufferSize);
				HashMap<Object, AbstractOrocosConnection> map;
				if(inputPortConnectionsMap.containsKey(portName)){
					map = inputPortConnectionsMap.get(portName);
					inputPortConnectionsMap.remove(portName);
				}else{
					map = new HashMap<Object, AbstractOrocosConnection>();
				}
				map.put(client, connection);
				inputPortConnectionsMap.put(portName, map);
				return true;
			} catch (CNoCorbaTransport e) {
				logger.error("Error on the Corba Transport. See the exception stack trace.");
				e.printStackTrace();
				return false;
			} catch (CNoSuchPortException e) {
				logger.error("The port you required does not exist");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/**
	 * @see AbstractOrocosComponent#subscribeToDataOutputPort(String, LockPolicy, Observer, int)
	 */
	@Override
	public boolean subscribeToDataOutputPort(String portName, LockPolicy lockPolicy, Observer observer, int periodMs) throws WrongPortTypeException{
		final OrocosDataPort port = getPort(portName);
		if(port.getPortType() == PortType.INPUT_PORT){
			throw new WrongPortTypeException(port.getPortType());
		}
		if(outputPortConnectionsMap.containsKey(portName) && 
				outputPortConnectionsMap.get(portName).containsKey(observer)){
			return true;
		}else{
			try {
				AbstractOrocosConnection connection = CorbaOrocosConnection.createDataConnection(port, lockPolicy);
				HashMap<Observer, AbstractOrocosConnection> map;
				if(outputPortConnectionsMap.containsKey(portName)){
					map = outputPortConnectionsMap.get(portName);
					outputPortConnectionsMap.remove(portName);
				}else{
					map = new HashMap<Observer, AbstractOrocosConnection>();
				}
				map.put(observer, connection);
				outputPortConnectionsMap.put(portName, map);
				connection.addObserver(observer);
				connection.initPublishSubscriber(periodMs);
				return true;
			} catch (CNoCorbaTransport e) {
				logger.error("Error on the Corba Transport. See the exception stack trace.");
				e.printStackTrace();
				return false;
			} catch (CNoSuchPortException e) {
				logger.error("The port you required does not exist");
				e.printStackTrace();
				return false;
			} 
		}
	}

	
	/**
	 * @see AbstractOrocosComponent#subscribeToBufferOutputPort(String, LockPolicy, Observer, int, int)
	 */
	@Override
	public boolean subscribeToBufferOutputPort(String portName, LockPolicy lockPolicy, Observer observer, int periodMs, int bufferSize) throws WrongPortTypeException{
		OrocosDataPort port = getPort(portName);
		if(port.getPortType() == PortType.INPUT_PORT){
			throw new WrongPortTypeException(port.getPortType());
		}
		if(outputPortConnectionsMap.containsKey(portName) && 
				outputPortConnectionsMap.get(portName).containsKey(observer)){
			return true;
		}else{
			try {
				AbstractOrocosConnection connection = CorbaOrocosConnection.createBufferConnection(port, lockPolicy, bufferSize);
				HashMap<Observer, AbstractOrocosConnection> map;
				if(outputPortConnectionsMap.containsKey(portName)){
					map = outputPortConnectionsMap.get(portName);
					outputPortConnectionsMap.remove(portName);
				}else{
					map = new HashMap<Observer, AbstractOrocosConnection>();
				}
				map.put(observer, connection);
				outputPortConnectionsMap.put(portName, map);
				connection.addObserver(observer);
				connection.initPublishSubscriber(periodMs);
				return true;
			} catch (CNoCorbaTransport e) {
				logger.error("Error on the Corba Transport. See the exception stack trace.");
				e.printStackTrace();
				return false;
			} catch (CNoSuchPortException e) {
				logger.error("The port you required does not exist");
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/**
	 * @see AbstractOrocosComponent#unsubscribeFromOutputPort(String, Observer)
	 */
	@Override
	public boolean unsubscribeFromOutputPort(String portName, Observer observer){
		if(outputPortConnectionsMap.containsKey(portName) &&
				outputPortConnectionsMap.get(portName).containsKey(observer)){
			((CorbaOrocosConnection)outputPortConnectionsMap.get(portName)
					.get(observer)).deleteObserver(observer);
			outputPortConnectionsMap.get(portName).remove(observer);
			return true;
		}
		return true;
	}

	
	

}
