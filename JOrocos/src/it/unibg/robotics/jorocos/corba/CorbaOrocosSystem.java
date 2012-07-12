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
 * File: CorbaOrocosSystem.java
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
import it.unibg.robotics.jorocos.core.AbstractOrocosSystem;
import it.unibg.robotics.jorocos.exceptions.SystemIpAndPortAlreadyDefinedException;
import it.unibg.robotics.jorocos.exceptions.SystemIpAndPortNotDefinedException;
import it.unibg.robotics.jorocos.exceptions.SystemNotConnectedException;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.BindingHolder;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import RTT.corba.CTaskContext;
import RTT.corba.CTaskContextHelper;

/**
 * The Class CorbaOrocosSystem is a specialization of AbstractOrocosSystem
 * defined for the CORBA transport.
 * 
 * This class implements the singleton pattern.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class CorbaOrocosSystem extends AbstractOrocosSystem{

	/** The unique instance of the Corba Orocos System. */
	private static CorbaOrocosSystem INSTANCE = new CorbaOrocosSystem();

	/** The IP address of the Orocos System. */
	private static String OROCOS_IP = null;

	/** The port number of the Orocos System. */
	private static String OROCOS_PORT = null;
	
	/** The pointer to the CORBA ORB. */
	private ORB orb;

	/** The name service context. */
	private NamingContextExt nameServiceContext = null;

	/** The orocos context. */
	private NamingContextExt orocosContext = null;
	
	/** The logger. */
	private final Logger logger;

	/**
	 * Instantiates a new Orocos System.
	 */
	private CorbaOrocosSystem(){
		logger = Logger.getLogger(CorbaOrocosSystem.class);
		BasicConfigurator.configure();
	}

	/**
	 * Returns the single instance of OrocosSystem.
	 * This method should be called only the first time that you need to retrieve an instance of the Orocos System.
	 * From the second time just call the method without parameters.
	 * {@link #getInstance()}
	 *
	 * @param systemIP the Orocos system IP address
	 * @param systemPort the Orocos system port
	 * @return a single instance of the Orocos system
	 */
	public static CorbaOrocosSystem getInstance(String systemIP, String systemPort)
			throws SystemIpAndPortAlreadyDefinedException {
		
		if(OROCOS_IP != null){
			throw new SystemIpAndPortAlreadyDefinedException();
		}
		OROCOS_IP = systemIP;
		OROCOS_PORT = systemPort;

		return INSTANCE;
		
	}

	/**
	 * Returns the single instance of OrocosSystem.
	 * You can use this method only if you have already called the version with the parameters.
	 * {@link #getInstance(String, String)}
	 * 
	 * @return a single instance of the Orocos system
	 */
	public static CorbaOrocosSystem getInstance()
			throws SystemIpAndPortNotDefinedException {
		
		if(OROCOS_IP == null){
			throw new SystemIpAndPortNotDefinedException();
		}else{
			return INSTANCE;
		}
		
	}

	/**
	 * @see AbstractOrocosSystem#connect()
	 */
	@Override
	public boolean connect() {
		
		Properties properties = new Properties();
		properties.put("ORBInitRef.NameService","corbaname::" + OROCOS_IP + ":" + OROCOS_PORT +"/NameService");
		properties.put("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
		properties.put("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");
		properties.put("jacorb.log.default.verbosity", "1");
		
		// create and initialize the ORB
		orb = ORB.init(new String[0], properties);
		logger.info("Connected to the ORB");

		try {
			nameServiceContext = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

			orocosContext = NamingContextExtHelper.narrow(nameServiceContext.resolve_str("TaskContexts"));
			logger.info("Connected to the Name Service");

			return true;

		}catch (InvalidName e) {
			logger.error("Name Service cannot be found");
			return false;
		}catch (NotFound e) {
			logger.error("TaskContext service not found. Maybe the Orocos System is not running");
			return false;
		} catch (CannotProceed e) {
			logger.error("JacORB error, control the class OrocoSystem, method getComponents");
			return false;
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			logger.error("JacORB error, control the class OrocoSystem, method getComponents");
			return false;
		}
		
	}

	/**
	 * @see AbstractOrocosSystem#getComponent(String, boolean)
	 */
	@Override
	public AbstractOrocosComponent getComponent(String componentName, boolean introspect)
			throws SystemNotConnectedException {
		CTaskContext taskContext;
		try {
			if(orocosContext == null){
				throw new SystemNotConnectedException(); 
			}

			org.omg.CORBA.Object o = orocosContext.resolve_str(componentName);//resolve(blistHolder.value[i].binding_name);
			taskContext = CTaskContextHelper.narrow(o);
			if(taskContext != null){
				logger.info("The component " + componentName + " has been found");
				return new CorbaOrocosComponent(taskContext, introspect);
			}
			return null;


		}catch (NotFound e) {
			logger.error("The component " + componentName + " has not been found");
			e.printStackTrace();
			return null;
		} catch (CannotProceed e) {
			logger.error("JacORB error, control the class OrocoSystem, method getComponents");
			e.printStackTrace();
			return null;
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			logger.error("JacORB error, control the class OrocoSystem, method getComponents");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns the CORBA ORB.
	 * @return the CORBA ORB
	 */
	public ORB getORBReference(){
		return orb;
	}

	/**
	 * @see AbstractOrocosSystem#introspectAllComponents()
	 */
	@Override
	public void introspectAllComponents()
			throws SystemNotConnectedException {
		componentsId = new ArrayList<String>();
		components = new ArrayList<AbstractOrocosComponent>();

		logger.info("Instropection of the system components:");
		BindingListHolder blistHolder = new BindingListHolder();
		BindingIteratorHolder biteratorHolder = new BindingIteratorHolder();
		BindingHolder bHolder = new BindingHolder();

		if(orocosContext == null){
			throw new SystemNotConnectedException(); 
		}
		orocosContext.list(0, blistHolder, biteratorHolder);

		if(biteratorHolder.value != null){

			int i = 1;
			while(biteratorHolder.value.next_one(bHolder)){
				String id = bHolder.value.binding_name[0].id;
				componentsId.add(id);
				logger.info("  Component-" + i + ": " + id);

				org.omg.CORBA.Object o;
				try {
					o = orocosContext.resolve(bHolder.value.binding_name);
					CTaskContext taskContext = CTaskContextHelper.narrow(o);
					AbstractOrocosComponent component = new CorbaOrocosComponent(taskContext,true);
					components.add(component);
				} catch (NotFound e) {
					logger.error("TaskContext service not found. Maybe the Orocos System is not running");
					e.printStackTrace();
				} catch (CannotProceed e) {
					logger.error("JacORB error, control the class OrocoSystem, method getComponents");
					e.printStackTrace();
				} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
					logger.error("JacORB error, control the class OrocoSystem, method getComponents");
					e.printStackTrace();
				}


				i++;
			}
		}
	}
	
//	/**
//	 * Return a new instance of an Any Object.
//	 * @return a new instance of an Any Object
//	 * @throws IpAndPortNotDefinedException
//	 */
//	public static Any newAnyInstance() throws IpAndPortNotDefinedException{
//		return getInstance().orb.create_any();
//	}


}
