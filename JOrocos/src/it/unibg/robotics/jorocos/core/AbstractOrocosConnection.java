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
 * File: AbstractOrocosConnection.java
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
import it.unibg.robotics.jorocos.exceptions.WrongUseOfConnectionException;

import java.util.Observable;

import org.omg.CORBA.TypeCodePackage.BadKind;

import RTT.corba.CChannelElement;
import RTT.corba.CConnPolicy;

/**
 * The Class AbstractOrocosConnection provides the channel that allows 
 * the operations of writing data on the output ports and reading data 
 * from the input ports.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public abstract class AbstractOrocosConnection extends Observable{


	/**
	 * The Enum ConnectionType defines the available connections.
	 */
	public enum ConnectionType{

		/** 
		 * The DATA connection.
		 * On a data connection, the reader will have only access to the last written value. 
		 */
		DATA, 

		/** 
		 * The BUFFER connection.
		 * On a buffered connection, a size number of elements can be stored until the reader reads them.  
		 */
		BUFFER
	}

	/**
	 * The Enum LockPolocy defines the available lock policies.
	 */
	public enum LockPolicy{

		/** 
		 * The USYNC connection policy defines how locking is done in the connection. 
		 * UNSYNC means there's no synchronization at all (not thread safe).
		 */
		USYNC, 

		/** 
		 * The LOCKED connection policy defines how locking is done in the connection.
		 * LOCKED uses mutexes. 
		 */
		LOCKED, 

		/** 
		 * The LOCK FREE connection policy defines how locking is done in the connection.
		 * LOCK_FREE uses a lock free method. 
		 */
		LOCK_FREE
	}

	/** 
	 * One of the port of this connection. The other port is not explicitly defined and is 
	 * represented by the client that creates this connection 
	 */
	protected OrocosDataPort port;

	/** 
	 * The connection type. 
	 */
	protected ConnectionType connectionType;

	/** The connection lock policy. */
	protected CConnPolicy connectionPolicy;

	/** 
	 * A pointer to the channel which is modeled by this connection. 
	 * */
	protected CChannelElement channelElement;

	/**
	 * Returns the Orocos port, see the variable OrocosDataPort.
	 *
	 * @return the the Orocos port which is part of the connection
	 */
	public OrocosDataPort getPort(){
		return port;
	}

	/**
	 * Returns the connection type.
	 *
	 * @return the connection type
	 */
	public ConnectionType getConnectionType(){
		return connectionType;
	}

	/**
	 * Tries to read a data from the data port.
	 * This method works only if the Orocos data port is an input port.
	 *
	 * @return the result of the reading operation
	 * @throws WrongUseOfConnectionException the wrong use of connection exception
	 */
	abstract public PortReadingResult readData() throws WrongUseOfConnectionException;

	/**
	 * Tries to write a data on the data port.
	 * This method works only if the Orocos data port is an output port.
	 *
	 * @param value the data that has to be written
	 * @return true, if successful. False otherwise, e.g. if value is an instance of a different type
	 * with respect to the data port type.
	 * @throws WrongUseOfConnectionException the wrong use of connection exception
	 * @throws BadKind 
	 */
	abstract public boolean writeData(Object value) throws WrongUseOfConnectionException;

	/**
	 * Start the publish subscriber thread which periodically tries to read from an output port
	 * and notifies the client when new data are available.
	 *
	 * @param periodMs defines the frequency with which the availability
	 * of new data on the port will be checked (it is expressed as period in milliseconds).
	 */
	public void initPublishSubscriber(final int periodMs){

		Thread thread = new Thread() {
			public void run() {
				PortReadingResult or;
				// check if the observer is still there
				// we have no more than 1 observer
				while(countObservers()>0){
					try {
						or = readData();
						while(or.getStatus() == OutputPortStatus.NO_DATA  
								|| or.getStatus() == OutputPortStatus.OLD_DATA){
							Thread.sleep(periodMs);
							or = readData();
						}
						// if a new data is availbale notify the observer
						setChanged();					
						notifyObservers(new OrocosPortEvent(port.getName(), or.getValue()));
						
					} catch(InterruptedException e){
						e.printStackTrace();
					} catch (WrongUseOfConnectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

}
