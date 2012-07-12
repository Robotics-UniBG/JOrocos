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
 * File: CorbaOrocosService.java
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

import it.unibg.robotics.jorocos.core.AbstractOrocosService;
import it.unibg.robotics.jorocos.core.OrocosOperation;
import it.unibg.robotics.jorocos.core.OrocosProperty;
import it.unibg.robotics.jorocos.exceptions.OrocosPropertyNotExistException;
import it.unibg.robotics.jorocos.exceptions.OrocosServiceNotExistException;
import it.unibg.robotics.jorocos.interfaces.CallbackListener;

import org.omg.CORBA.Any;

import RTT.corba.CAnyArgumentsHolder;
import RTT.corba.CCallInterrupted;
import RTT.corba.CNoSuchNameException;
import RTT.corba.CSendHandle;
import RTT.corba.CSendStatus;
import RTT.corba.CService;
import RTT.corba.CWrongNumbArgException;
import RTT.corba.CWrongTypeArgException;

/**
 * The class CorbaOrocosService is a specialization of AbstractOrocosService
 * defined for the CORBA transport.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class CorbaOrocosService extends AbstractOrocosService{

	/**
	 * Instantiates a new Corba Orocos service.
	 *
	 * @param service a pointer to the service that has to be modeled
	 * @param introspect if true the constructor will introspect the service and store
	 * all the information about its services, operation and properties.
	 * This information can be retrieved by using the methods {@link #getServices()},
	 * {@link #getOperations()} and {@link #getProperties()}.
	 */
	public CorbaOrocosService(CService service, boolean introspect) {
		super(service, introspect);
	}

	/**
	 * @see AbstractOrocosService#callOperationSynchronously(String, Object...)
	 */
	@Override
	public Object callOperationSynchronously(String operationName, Object... arguments){

		OrocosOperation operation = getOperation(operationName);
		Any[] anyArguments = castOperationArguments(operation, arguments);
		CAnyArgumentsHolder holder = new CAnyArgumentsHolder(anyArguments);

		try {

			Any result = getCService().callOperation(operationName, holder);
			if(operation.getReturnType().equals("void")){
				return null;
			}
			return AnyObjectCast.anyToObject(result);

		} catch (CNoSuchNameException e) {
			e.printStackTrace();
		} catch (CWrongNumbArgException e) {
			logger.error("The number of the arguments passed to the operation is wrong.");
			e.printStackTrace();
		} catch (CWrongTypeArgException e) {
			logger.error("At least one of the arguments passed to the operation belong to a wrong type.");
			e.printStackTrace();
		} catch (CCallInterrupted e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * @see AbstractOrocosService#callOperationAsynchronously(String, CallbackListener, int, Object...)
	 */
	@Override
	public void callOperationAsynchronously(final String operationName, final CallbackListener listener, 
			final int periodMs, final Object... arguments) {

		final String serviceName = getName();
		Thread thread = new Thread() {

			public void run() {
				try {

					OrocosOperation operation = getOperation(operationName);
					Any[] anyArguments = castOperationArguments(operation, arguments);
					CAnyArgumentsHolder holder = new CAnyArgumentsHolder(anyArguments);
					CSendHandle handler = getCService().sendOperation(operationName, anyArguments);
					CSendStatus sendStatus = handler.collectIfDone(holder);
					while(sendStatus == CSendStatus.CSendNotReady){
						sendStatus = handler.collectIfDone(holder);
//						System.out.println("SendStatus: " + sendStatus.value());
						Thread.sleep(periodMs);
					}
					if(sendStatus == CSendStatus.CSendSuccess){
						Object returnValue = AnyObjectCast.anyToObject(handler.ret());
						listener.callback(serviceName, operationName, returnValue);
					}else if(sendStatus == CSendStatus.CSendFailure){
						listener.callback(serviceName, operationName, null);
						logger.error("The asynchornous call to the operation '" + operationName + "' fails.");
					}

				} catch (CNoSuchNameException e) {
					e.printStackTrace();
				} catch (CWrongNumbArgException e) {
					logger.error("The number of the arguments passed to the operation is wrong.");
					e.printStackTrace();
				} catch (CWrongTypeArgException e) {
					logger.error("At least one of the arguments passed to the operation belong to a wrong type.");
					e.printStackTrace();
				} catch (CCallInterrupted e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}

	/**
	 * @see AbstractOrocosService#introspectService(String, boolean)
	 */
	@Override
	protected AbstractOrocosService introspectService(String name, boolean introspect) throws OrocosServiceNotExistException{

		CService service = getCService().getService(name);
		if(service == null){
			logger.error("The required Orocos service (name: " + name + ") does not exist.");
			throw new OrocosServiceNotExistException(name);
		}
		AbstractOrocosService orocosService = new CorbaOrocosService(service,introspect);

		return orocosService;

	}

	/**
	 * Cast the arguments of an operation from a list of {@link java.lang.Object} to 
	 * an array of {@link org.omg.CORBA.Any}
	 * @param operation the name of the operation
	 * @param arguments the list of the operation arguments that have to be casted
	 * @return the array of casted operation arguments
	 */
	private Any[] castOperationArguments(OrocosOperation operation, Object... arguments){
		Any[] anyArguments = new Any[arguments.length];
		for (int i = 0; i < arguments.length; i++) {
			anyArguments[i] = AnyObjectCast.objectToAny(arguments[i],operation.getArguments().get(i).getDataType().toLowerCase());
		}
		return anyArguments;
	}

	/**
	 * @see AbstractOrocosService#getPropertyValue(String)
	 */
	@Override
	public Object getPropertyValue(String propertyName) throws OrocosPropertyNotExistException {
		OrocosProperty property = getProperty(propertyName); // Check if the property exists
		return AnyObjectCast.anyToObject(getCService().getProperty(property.getName()));
	}

	/**
	 * @see AbstractOrocosService#setPropertyValue(String, Object)
	 */
	@Override
	public void setPropertyValue(String propertyName, Object value) throws OrocosPropertyNotExistException {
		
		getCService().setProperty(propertyName, AnyObjectCast.objectToAny(value, getProperty(propertyName).getDataType()));
		
	}

}
