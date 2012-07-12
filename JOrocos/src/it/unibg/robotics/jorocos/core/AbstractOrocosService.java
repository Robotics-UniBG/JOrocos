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
 * File: AbstractOrocosService.java
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

import it.unibg.robotics.jorocos.exceptions.OrocosPropertyNotExistException;
import it.unibg.robotics.jorocos.exceptions.OrocosServiceNotExistException;
import it.unibg.robotics.jorocos.interfaces.CallbackListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.log4j.Logger;

import RTT.corba.CArgumentDescription;
import RTT.corba.CNoSuchNameException;
import RTT.corba.CService;
import RTT.corba.CConfigurationInterfacePackage.CProperty;

/**
 * The Class AbstractOrocosService defines a proxy to an Orocos service and offers 
 * the functionality for introspecting and invoking its operations and introspecting, 
 * reading and writing its properties.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public abstract class AbstractOrocosService {

	/**  The name of the service. */
	private String name;
	
	/** The description of the service. */
	private String description;

	/** The list of the operations provided by this service. */
	private ArrayList<OrocosOperation> operations;

	/** A pointer to the service modeled by this class. */
	private CService cService;

	/** The list of services provided by this service. */
	private ArrayList<AbstractOrocosService> services;

	/** The logger. */
	protected Logger logger;

	/** The list of properties contained in this service. */
	private ArrayList<OrocosProperty> properties;
	/** The list of the names of the properties contained in this service. */
	private ArrayList<String> propertiesNames;

	/**
	 * Instantiates a new abstract Orocos service.
	 *
	 * @param service a pointer to the service that has to be modeled
	 * @param introspect if true the constructor will introspect the service and store
	 * all the information about its services, operation and properties.
	 * This information can be retrieved by using the methods {@link #getServices()},
	 * {@link #getOperations()} and {@link #getProperties()}.
	 */
	public AbstractOrocosService(CService service, boolean introspect) {
		super();
		this.cService = service;
		name = service.getName();
		description = service.getServiceDescription();
		operations = new ArrayList<OrocosOperation>();
		properties = new ArrayList<OrocosProperty>();
		propertiesNames = new ArrayList<String>();

		services = new ArrayList<AbstractOrocosService>();
		logger = Logger.getLogger(AbstractOrocosService.class);

		if(introspect){
			introspectOperations();
			introspectServices();
			introspectProperties();
		}

	}

	/**
	 * Returns the service name.
	 *
	 * @return the service name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the service description.
	 *
	 * @return the service description
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Introspect all the operations provided by this service and store them.
	 * The operations can be retrieved by using the method {@link #getOperations()}
	 */
	public void introspectOperations() {

		String[] operationsNames = cService.getOperations();
		logger.info("   Introspection of the service '" + name +"' operations: ");
		for (int i = 0; i < operationsNames.length; i++) {
			String operationName = operationsNames[i];
			logger.info("     Opearation-" + (i+1) + ": " + operationName);

			this.operations.add(introspectOperation(operationName));		
		}

	}

	/**
	 * Returns the required operation provided by this service.
	 *
	 * @param operationName the name of the requested operation
	 * @return the operation
	 */
	public OrocosOperation getOperation(String operationName){
		if(operations.size()>0){
			OrocosOperation operation;
			for (Iterator<OrocosOperation> iterator = operations.iterator(); iterator.hasNext();) {
				operation = iterator.next();
				if(operation.getName().equals(operationName)){
					return operation;
				}
			}
		}
		return introspectOperation(operationName);
	}

	/**
	 * Returns all the operations provided by this service.
	 * This method should be called after the method {@link #introspectOperations()}
	 *
	 * @return the all operations
	 */
	public ArrayList<OrocosOperation> getOperations() {
		return operations;
	}

	/**
	 * Returns the names of all the operations provided by this service.
	 * This method should be called after the method {@link #introspectOperations()}
	 *
	 * @return the name of all the operations
	 */
	public ArrayList<String> getOperationsNames(){
		ArrayList<String> result = new ArrayList<String>();
		Collections.addAll(result, cService.getOperations());
		return result;
	}

	/**
	 * Call the requested operation of this service in a synchronously way.
	 * 
	 * @param operationName the name of the operation
	 * @param arguments the arguments of the operation
	 * @return the result of the operation execution
	 */
	abstract public Object callOperationSynchronously(String operationName, Object... arguments);

	/**
	 * Call the requested operation of this service in a asynchronously way.
	 * 
	 * @param operationName operationName the name of the operation
	 * @param listener the client that calls the operation, usually should be "this". 
	 * The client must be an instance of a class that implement the interface {@link CallbackListener}.
	 * When the execution of the operation will be completed the client will be notified by means of its method
	 * {@link CallbackListener#callback(String, String, Object)}.
	 * @param periodMs defines the frequency with which the availability
	 * of the operation result will be checked (it is expressed as period in milliseconds).
	 * @param arguments the arguments of the operation
	 */
	abstract public void callOperationAsynchronously(String operationName, CallbackListener listener, int periodMs, Object... arguments);

	/**
	 * Introspect a specific operation provided by this service.
	 *
	 * @param operationName the operation name
	 * @return the operation 
	 */
	private OrocosOperation introspectOperation(String operationName){

		try {
			String description = cService.getDescription(operationName);
			String resultType = cService.getResultType(operationName);
			CArgumentDescription[] argsDescriptor;
			ArrayList<OrocosArgument> arguments = new ArrayList<OrocosArgument>();

			argsDescriptor = cService.getArguments(operationName);
			for (int j = 0; j < argsDescriptor.length; j++) {
				CArgumentDescription arg = argsDescriptor[j];
				logger.info("       Attribute-" + (j+1) + ": " + arg.name + " (type: " + arg.type + ", description: " + arg.description + ")");
				arguments.add(new OrocosArgument(arg.name, arg.type, arg.description));
			}
			return new OrocosOperation(operationName, description, resultType, arguments);
		} catch (CNoSuchNameException e) {
			logger.error("The required Orocos operation '" + operationName + "' does not exist.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Introspect all the services provided by this service and store them.
	 * The services can be retrieved by using the method {@link #getServices()}.
	 */
	public void introspectServices() {

		String[] servicesNames = cService.getProviderNames();
		for (int i = 0; i < servicesNames.length; i++) {
			try {
				services.add(introspectService(servicesNames[i], true));
			} catch (OrocosServiceNotExistException e) {
				// should not happen
				e.printStackTrace();
			}
		}

	}

	/**
	 * Returns the requested service provided by this service.
	 *
	 * @param serviceName the service name
	 * @param introspect if true, in the case the request service has not been yet introspected, 
	 * the method will introspect the service and store all the information about its services, 
	 * operation and properties.
	 * @return the service
	 * @throws OrocosServiceNotExistException the Orocos service not exist exception
	 */
	public AbstractOrocosService getService(String serviceName, boolean introspect) throws OrocosServiceNotExistException {
		if(services.size()>0){
			AbstractOrocosService service;
			for (Iterator<AbstractOrocosService> iterator = services.iterator(); iterator.hasNext();) {
				service = iterator.next();
				if(service.getName().equals(serviceName)){
					return service;
				}			
			}
		}
		return introspectService(serviceName, introspect);
	}

	/**
	 * Returns the all services provided by this service.
	 * This method should be called after the method {@link #introspectServices()}.
	 *
	 * @return the all services
	 */
	public ArrayList<AbstractOrocosService> getServices() {
		return services;
	}

	/**
	 * Returns the names of all the services provided by this service.
	 * This method should be called after the method {@link #introspectServices()}.
	 *
	 * @return the name of all the services
	 */
	public ArrayList<String> getServicesNames(){
		ArrayList<String> result = new ArrayList<String>();
		Collections.addAll(result, cService.getProviderNames());
		return result;
	}

	/**
	 * Introspects a specific service provided by this service.
	 *
	 * @param name the service name
	 * @param introspect if true the method will introspect the service and store all the information 
	 * about its services, operation and properties.
	 * @return the service
	 * @throws OrocosServiceNotExistException the Orocos service not exist exception
	 */
	abstract protected AbstractOrocosService introspectService(String name, boolean introspect) throws OrocosServiceNotExistException;

	/**
	 * Returns a pointer to the service modeled by this class.
	 *
	 * @return a pointer to the service modeled by this class
	 */
	public CService getCService() {
		return cService;
	}

	/**
	 * Introspect all the properties contained in this service and store them.
	 * The services can be retrieved by using the method {@link #getProperties()}.
	 */
	public void introspectProperties(){

		properties = new ArrayList<OrocosProperty>();
		propertiesNames = new ArrayList<String>();
		CProperty[] cProperties = cService.getPropertyList();
		logger.info("   Introspection of the service '" + name +"' properties: ");

		for (int i = 0; i < cProperties.length; i++) {
			String name = cProperties[i].name;
			String description = cProperties[i].description;
			String dataType = cService.getPropertyType(name);
			properties.add(new OrocosProperty(name, description, dataType));
			propertiesNames.add(name);
			logger.info("     Property-" + (i+1) + ": name = " + name + ", description = " + description + ", dataType = " + dataType);
			
		}
	}

	/**
	 * Returns the requested property contained in this service.
	 * @param propertyName the name of the requested property
	 * @return the property
	 * @throws OrocosPropertyNotExistException
	 */
	public OrocosProperty getProperty(String propertyName) throws OrocosPropertyNotExistException{
		OrocosProperty property = getPropertyFromArrayList(propertyName);
		if(property == null)
			introspectProperties();
		property = getPropertyFromArrayList(propertyName);
		if(property == null)
			throw new OrocosPropertyNotExistException(propertyName);
		return property;
	}

	/**
	 * Returns the requested property by looking into the set of propertied stored
	 * during the last execution of the method {@link #introspectProperties()}.
	 * @param propertyName the name of the requested property
	 * @return the property if it exist, null otherwise
	 */
	private OrocosProperty getPropertyFromArrayList(String propertyName){
		OrocosProperty property = null;
		if(propertiesNames.contains(propertyName)){
			for (Iterator<OrocosProperty> iterator = properties.iterator(); iterator.hasNext();) {
				property = (OrocosProperty) iterator.next();
				if(property.getName().equals(propertyName))
					return property;
			}
		}
		return property;
	}

	/**
	 * Returns the all properties contained in this service.
	 * This method should be called after the method {@link #introspectProperties()}.
	 *
	 * @return the all properties
	 */
	public ArrayList<OrocosProperty> getProperties(){
		return properties;
	}

	/**
	 * Returns the names of all the properties contained in this service.
	 * This method should be called after the method {@link #introspectProperties()}.
	 *
	 * @return the name of all the properties
	 */
	public ArrayList<String> getPropertiesNames(){
		return propertiesNames;
	}

	/**
	 * Returns the value of the requested property.
	 * 
	 * @param propertyName the name of the property
	 * @return the value of the requested property
	 * @throws OrocosPropertyNotExistException
	 */
	abstract public Object getPropertyValue(String propertyName) throws OrocosPropertyNotExistException;
	
	/**
	 * Sets the value of the requested property.
	 * @param propertyName the name of the property
	 * @param value the new value for the property
	 * @throws OrocosPropertyNotExistException
	 */
	abstract public void setPropertyValue(String propertyName, Object value) throws OrocosPropertyNotExistException;


}
