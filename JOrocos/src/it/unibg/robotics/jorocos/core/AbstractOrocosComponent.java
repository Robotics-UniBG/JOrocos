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
 * File: AbstractOrocosComponent.java
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

import it.unibg.robotics.jorocos.core.AbstractOrocosConnection.LockPolicy;
import it.unibg.robotics.jorocos.core.OrocosDataPort.PortType;
import it.unibg.robotics.jorocos.exceptions.ConnectionToPortNotExistException;
import it.unibg.robotics.jorocos.exceptions.WrongPortTypeException;
import it.unibg.robotics.jorocos.exceptions.WrongUseOfConnectionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observer;

import org.apache.log4j.Logger;

import RTT.corba.CDataFlowInterface;
import RTT.corba.CNoSuchPortException;
import RTT.corba.CPortType;
import RTT.corba.CTaskContext;
import RTT.corba.CTaskState;

/**
 * The Class AbstractOrocosComponent is a proxy to an Orocos task context and allows clients 
 * to introspect its data ports and its own service. The class offers the operations for creating
 * connections to Orocos ports and writing and reading data on these ports.
 * 
 * This class allows both the operations of reading and writing on the component data port. 
 * In order to be executed these operations require a connection between the java client and the Orocos port. 
 * Two types of connections are available: data and buffer. 
 * On a data connection the reader has access only to the last written value whereas on a buffer connection 
 * a predefined number of values can be stored.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public abstract class AbstractOrocosComponent{

	/** 
	 * The possible states of the component FSM. 
	 * See the Orocos documentation for more information about the 
	 * meaning of the states.
	 */
	public enum ComponentState{
		
		INIT,
		PRE_OPERATIONAL,
		FATAL_ERROR,
		EXCEPTION,
		STOPPED,
		RUNNING,
		RUNTIME_ERROR
		
	}
	
	/** The input ports list. */
	private ArrayList<OrocosDataPort> inputPorts;

	/** The input ports names list. */
	private ArrayList<String> inputPortsNames;

	/** The output ports list. */
	private ArrayList<OrocosDataPort> outputPorts;

	/** The output ports names list. */
	private ArrayList<String> outputPortsNames;

	/** 
	 * The connections map for the input ports. 
	 * The key string is the port name,
	 * the value is another map which has an observer 
	 * (the client which created the connection)
	 * as key and the connection as value. 
	 * 
	 * We store the connections in order to use them when we a certaint client want to write on 
	 * a certain input port
	 * */
	protected HashMap<String, HashMap<Object, AbstractOrocosConnection>> inputPortConnectionsMap;
	
	/** 
	 * The connections map for the output ports. 
	 * The key string is the port name,
	 * the value is another map which has an observer 
	 * (the client which created the connection)
	 * as key and the connection as value. 
	 * */
	protected HashMap<String, HashMap<Observer, AbstractOrocosConnection>> outputPortConnectionsMap;

	//	private ArrayList<OrocosService> services;

	/** The logger. */
	private Logger logger;

	/** A pointer to the task context modeled by this class. */
	private CTaskContext cTaskContext;
	
	/** The service which encapsulate the services of this task context. */
	protected AbstractOrocosService ownService;

	/** The component name. */
	private String name;
	
	/** The component description. */
	private String description;

	/** A pointer to the data flow interface of the task context which is modeled by this class. */
	private CDataFlowInterface cDataFlowInterface;

	/**
	 * Instantiates a new abstract Orocos component.
	 *
	 * @param taskContext a pointer to the task context that has to be modeled
	 * @param introspect if true the constructor will introspect the component and store
	 * all the information about its services, ports and properties.
	 * This information can be retrieved by using the methods {@link #getOwnService()} 
	 * (which provided information about services, operations and properties) 
	 * and {@link #getPorts()}.
	 */
	public AbstractOrocosComponent(CTaskContext taskContext, boolean introspect){

		this.cTaskContext = taskContext;
		this.name = taskContext.getName();
		this.description = taskContext.getDescription();
		
		inputPorts = new ArrayList<OrocosDataPort>();
		inputPortsNames = new ArrayList<String>();
		outputPorts = new ArrayList<OrocosDataPort>();
		outputPortsNames = new ArrayList<String>();

		inputPortConnectionsMap = new HashMap<String, HashMap<Object,AbstractOrocosConnection>>();
		outputPortConnectionsMap = new HashMap<String, HashMap<Observer,AbstractOrocosConnection>>();

		cDataFlowInterface = taskContext.ports();

		logger = Logger.getLogger(AbstractOrocosComponent.class);
		if(introspect){

			introspectOwnService(true);
			introspectPorts();

		}

	}

	/**
	 * Returns the component name.
	 *
	 * @return the component name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the component description.
	 *
	 * @return the component description
	 */
	public String getDescritpion(){
		return description;
	}
	
	/**
	 * Returns the current state of the component FSM.
	 *
	 * @return the current state of the component FSM
	 */
	public ComponentState getState(){
		CTaskState state = cTaskContext.getTaskState();
		if(state.equals(CTaskState.CInit))
			return ComponentState.INIT;
		else if(state.equals(CTaskState.CPreOperational))
			return ComponentState.PRE_OPERATIONAL;
		else if(state.equals(CTaskState.CFatalError))
			return ComponentState.FATAL_ERROR;
		else if(state.equals(CTaskState.CException))
			return ComponentState.EXCEPTION;
		else if(state.equals(CTaskState.CStopped))
			return ComponentState.STOPPED;
		else if(state.equals(CTaskState.CRunning))
			return ComponentState.RUNNING;
		else if(state.equals(CTaskState.CRunTimeError))
			return ComponentState.RUNTIME_ERROR;
		else
			return null;
	}
	
	/**
	 * Calls the configure operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the operation completes without problems.
	 */
	public boolean configure(){
		return cTaskContext.configure();
	}
	
	/**
	 * Calls the isConfigured operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the component is in the Stopped, Active or Running states.
	 */
	public boolean isConfigured(){
		return cTaskContext.isConfigured();
	}
	
	/**
	 * Calls the start operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the operation completes without problems.
	 */
	public boolean start(){
		return cTaskContext.start();
	}
	
	/**
	 * Calls the isRunning operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the component is in the Running or RunTimeError states.
	 */
	public boolean isRunning(){
		return cTaskContext.isRunning();
	}
	
	/**
	 * Calls the stop operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the operation completes without problems.
	 */
	public boolean stop(){
		return cTaskContext.stop();
	}
	
	/**
	 * Calls the resetException operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the operation completes without problems.
	 */
	public boolean resetException(){
		return cTaskContext.resetException();
	}
	
	/**
	 * Calls the cleanup operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the operation completes without problems.
	 */
	public boolean cleanup(){
		return cTaskContext.cleanup();
	}
	
	/**
	 * Calls the activate operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the operation completes without problems.
	 */
	public boolean activate(){
		return cTaskContext.activate();
	}
	
	/**
	 * Calls the isActive operation of the task context modeled by this class.
	 * For more information take a look at the documentation of the CTaskContext class.
	 *
	 * @return true if the component is processing requests.
	 */
	public boolean isActive(){
		return cTaskContext.isActive();
	}

	/**
	 * Introspect the ports of this component.
	 * It looks for the name and the types of all the ports and store them.
	 * They can be then retrieved by calling the methods {@link #getPorts()}, {@link #getInputPorts()} and
	 * {@link #getOutputPorts()}.
	 */
	public void introspectPorts() {

		inputPorts = new ArrayList<OrocosDataPort>();
		inputPortsNames = new ArrayList<String>();

		outputPorts = new ArrayList<OrocosDataPort>();
		outputPortsNames = new ArrayList<String>();

		String[] portsNames = cDataFlowInterface.getPorts();
		
		logger.info("    Introspection of the component ports: ");
		for (int i = 0; i < portsNames.length; i++) {
			OrocosDataPort port = introspectPort(portsNames[i]);
			logger.info("      Port-" + (i+1)+ ": " + port.getName() + " (type: " + port.getPortType() + ", data type: " + port.getDataType() + ")");
		}

	}

	/**
	 * Returns the port with the specified name.
	 * If the ports have not been yet introspected it executes the introspection
	 * by looking for the required port.
	 * 
	 * @param portName the name of the port that has to be returned
	 * @return the required port
	 */
	public OrocosDataPort getPort(String portName) {
		OrocosDataPort port;
		if(inputPortsNames.contains(portName)){
			for (Iterator<OrocosDataPort> iterator = inputPorts.iterator(); iterator.hasNext();) {
				port = iterator.next();
				if(port.getName().equals(portName)){
					return port;
				}
			}
		}
		if(outputPortsNames.contains(portName)){
			for (Iterator<OrocosDataPort> iterator = outputPorts.iterator(); iterator.hasNext();) {
				port = iterator.next();
				if(port.getName().equals(portName)){
					return port;
				}
			}
		}
		return introspectPort(portName);
	}

	/**
	 * Returns all the component ports which have been previously instrospected.
	 * This method should be called after the method {@link #introspectPorts()}.
	 * 
	 * @return the list of the component ports
	 */
	public ArrayList<OrocosDataPort> getPorts(){
		ArrayList<OrocosDataPort> ports = new ArrayList<OrocosDataPort>();
		ports.addAll(inputPorts);
		ports.addAll(outputPorts);
		return ports;
	}

	/**
	 * Returns all the names of the component ports which have been previously instrospected.
	 * This method should be called after the method {@link #introspectPorts()}.
	 * 
	 * @return the list of the names of the component ports
	 */
	public ArrayList<String> getPortsNames(){
		ArrayList<String> portsNames = new ArrayList<String>();
		portsNames.addAll(inputPortsNames);
		portsNames.addAll(outputPortsNames);
		return portsNames;
	}

	/**
	 * Returns all the component input ports which have been previously instrospected.
	 * This method should be called after the method {@link #introspectPorts()}.
	 * 
	 * @return the list of the component input ports
	 */
	public ArrayList<OrocosDataPort> getInputPorts(){
		return inputPorts;
	}

	/**
	 * Returns all the names of the component input ports which have been previously instrospected.
	 * This method should be called after the method {@link #introspectPorts()}.
	 * 
	 * @return the list of the names of the component input ports
	 */
	public ArrayList<String> getInputPortsNames(){
		return inputPortsNames;
	}

	/**
	 * Returns all the component output ports which have been previously instrospected.
	 * This method should be called after the method {@link #introspectPorts()}.
	 * 
	 * @return the list of the component output ports
	 */
	public ArrayList<OrocosDataPort> getOutputPorts(){
		return outputPorts;
	}

	/**
	 * Returns all the names of the component output ports which have been previously instrospected.
	 * This method should be called after the method {@link #introspectPorts()}.
	 * 
	 * @return the list of the names of the component output ports
	 */
	public ArrayList<String> getOutputPortsNames(){
		return outputPortsNames;
	}

	/**
	 * Creates a data connection to the requested input port of this component
	 * 
	 * @param portName the name of the port for which the connection has to be created
	 * @param lockPolicy the lock policy of the connection, see the class AbstactOrocosConnection for more info
	 * @param client the client that has to be connected to the port, usually should be "this"
	 * @return true if the connection has been successfully created
	 * @throws WrongPortTypeException
	 */
	abstract public boolean createDataConnectionToInputPort(String portName, LockPolicy lockPolicy, Object client) throws WrongPortTypeException;

	/**
	 * Creates a buffer connection to the requested input port of this component
	 * 
	 * @param portName the name of the port for which the connection has to be created
	 * @param lockPolicy the lock policy of the connection, see the class AbstactOrocosConnection for more info
	 * @param client the client that has to be connected to the port, usually should be "this"
	 * @param bufferSize the buffer dimension
	 * @return if the connection has been successfully created
	 * @throws WrongPortTypeException
	 */
	abstract public boolean createBufferConnectionToInputPort(String portName, LockPolicy lockPolicy, Object client, int bufferSize) throws WrongPortTypeException;

	/**
	 * Creates a data connection to the requested output port of this component and subscribe to it
	 * in order to be notified when a new data will be available
	 * 
	 * @param portName the name of the port for which the connection has to be created
	 * @param lockPolicy the lock policy of the connection, see the class AbstactOrocosConnection for more info
	 * @param observer the client that has to be connected to the port, usually should be "this". 
	 * The client must be an instance of a class that implement the interface {@link java.util.Observer}.
	 * When a new data will be available on the output port the observer will be notified accordingly to the 
	 * pattern observer/observable
	 * @param periodMs defines the frequency with which the availability
	 * of new data on the port will be checked (it is expressed as period in milliseconds).
	 * @return true if the connection has been successfully created
	 * @throws WrongPortTypeException
	 */
	abstract public boolean subscribeToDataOutputPort(String portName, LockPolicy lockPolicy, Observer observer, int periodMs) throws WrongPortTypeException;

	/**
	 * Creates a buffer connection to the requested output port of this component and subscribe to it
	 * in order to be notified when a new data will be available
	 * 
	 * @param portName the name of the port for which the connection has to be created
	 * @param lockPolicy the lock policy of the connection, see the class AbstactOrocosConnection for more info
	 * @param observer the client that has to be connected to the port, usually should be "this". 
	 * The client must be an instance of a class that implement the interface {@link java.util.Observer}.
	 * When a new data will be available on the output port the observer will be notified accordingly to the 
	 * pattern observer/observable
	 * @param periodMs defines the frequency with which the availability
	 * of new data on the port will be checked (it is expressed as period in milliseconds).
	 * @param bufferSize the buffer dimension
	 * @return true if the connection has been successfully created
	 * @throws WrongPortTypeException
	 */
	abstract public boolean subscribeToBufferOutputPort(String portName, LockPolicy lockPolicy, Observer observer, int periodMs, int bufferSize) throws WrongPortTypeException;

	
	/**
	 * Unsubscribes the observer from the specified output port
	 * 
	 * @param portName the name of the port for which the connection has to be removed
	 * @param observer the client that was connected for which the connection was created
	 * @return true if the connection has been successfully removed
	 */
	abstract public boolean unsubscribeFromOutputPort(String portName, Observer observer);

	/**
	 * Writes a data on the required input port.
	 * A connection to the port has to be created before calling this method.
	 * 
	 * @param portName the name of the port on which we want to write the data
	 * @param value the value we want to write. It has to be of the same type of the port
	 * @param client the client that want to write on the port, usually should be "this"
	 * @return true if the data has been written correctly
	 * @throws ConnectionToPortNotExistException
	 * @throws WrongPortTypeException
	 */
	public boolean writeOnPort(String portName, Object value, Object client) throws ConnectionToPortNotExistException, WrongPortTypeException{
		if(! inputPortConnectionsMap.containsKey(portName) || 
				! inputPortConnectionsMap.get(portName).containsKey(client)){
			logger.error("The required Orocos connection to '" + portName + "' does not exist. You have to create it before writing ot reading");
			throw new ConnectionToPortNotExistException(portName);
		}
		if(getPort(portName).getPortType() == PortType.OUTPUT_PORT){
			logger.error("You are trying to write on an output port. It's not possible.");
			throw new WrongPortTypeException(getPort(portName).getPortType());
		}
		try {
			return inputPortConnectionsMap.get(portName).get(client).writeData(value);
		} catch (WrongUseOfConnectionException e) {
			throw new RuntimeException("This should not be possible",e);
		}
	}

	/**
	 * Introspects the own service of the components and stores its
	 * services, operations and properties.
	 * The own service can be retrieved by using the method {@link #getOwnService()}
	 * 
	 * @param introspect, if true the services encapsulated in the own service will be
	 * recursively introspected
	 */
	abstract public void introspectOwnService(boolean introspect);

	/**
	 * Returns the own service of this component.
	 * You need to call this method if you want to call an operation of 
	 * the task context.
	 * @return
	 */
	public AbstractOrocosService getOwnService(){
		if(ownService == null){
			introspectOwnService(false);
		}
		return ownService;
	}

	/**
	 * Returns a pointer the the data flow interface modeled by this component.
	 * @return a pointer the the data flow interface modeled by this component.
	 */
	public CDataFlowInterface getCDataFlowInterface(){
		return cDataFlowInterface;
	}

	/**
	 * Returns a pointer the the data task context modeled by this component.
	 * @return a pointer the the task context modeled by this component.
	 */
	public CTaskContext getCTaskContext(){
		return cTaskContext;
	}

	/**
	 * Introspect a specific component port.
	 *
	 * @param portName the name of the port that has to be introspected 
	 * @return the component port with the specified name
	 */
	private OrocosDataPort introspectPort(String portName){
		PortType type = null;
		String dataType = "";
		try {
			if(cDataFlowInterface.getPortType(portName) == CPortType.CInput){
				type = PortType.INPUT_PORT;
			}else{
				type = PortType.OUTPUT_PORT;
			}
			dataType = cDataFlowInterface.getDataType(portName);
		} catch (CNoSuchPortException e) {
			logger.error("The port you are asking does not exist.");
			e.printStackTrace();
			return null;
		}

		OrocosDataPort port = new OrocosDataPort(portName, type, dataType);
		port.setComponent(this);
		if(port.getPortType() == PortType.INPUT_PORT){
			inputPorts.add(port);
			inputPortsNames.add(portName);
		}else{
			outputPorts.add(port);
			outputPortsNames.add(portName);
		}
		return port;
	}


}
