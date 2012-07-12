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
 * File: CorbaOrocosConnection.java
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

import it.unibg.robotics.jorocos.core.AbstractOrocosConnection;
import it.unibg.robotics.jorocos.core.OrocosDataPort;
import it.unibg.robotics.jorocos.core.OrocosDataPort.OutputPortStatus;
import it.unibg.robotics.jorocos.core.OrocosDataPort.PortType;
import it.unibg.robotics.jorocos.core.PortReadingResult;
import it.unibg.robotics.jorocos.exceptions.WrongUseOfConnectionException;

import org.apache.log4j.Logger;
import org.omg.CORBA.AnyHolder;

import RTT.corba.CConnPolicy;
import RTT.corba.CConnPolicyHolder;
import RTT.corba.CConnectionModel;
import RTT.corba.CFlowStatus;
import RTT.corba.CNoCorbaTransport;
import RTT.corba.CNoSuchPortException;

/**
 * The Class CorbaOrocosConnection is a specialization of AbstractOrocosConnection
 * defined for the CORBA transport.
 * 
 * @author <A HREF="mailto:luca.gherardi@unibg.it">Luca Gherardi</A>
 * @version 1.0
 * @since August 2011
 */
public class CorbaOrocosConnection extends AbstractOrocosConnection {

	/** The logger. */
	private Logger logger;

	/**
	 * Instantiates a new Orocos connection impl.
	 *
	 * @param port the port
	 * @param connectionType the connection type. {@link #connectionType}
	 * @param init the init option. If true, one should initialize the connection's value with the last
	 * value written on the writer port. This is only possible if the writer
	 * port has the keepsLastWrittenValue() flag set (i.e. if it remembers what was the last written value).
	 * @param lockPolicy the lock policy. {@link #lockPolicy}
	 * @param pull the pull option. If true, then the sink will have to pull data. Otherwise, it is pushed
	 * from the source. In both cases, the reader side is notified
	 * that new data is available by base::ChannelElementBase::signal()
	 * @param bufferSize the buffer size
	 * @param transport the transport option. Can be used to force a certain kind of transports. The number is a RTT
	 * transport id. When the transport type is zero, local in-process communication
	 * is used, unless one of the ports is remote. If the transport type deviates
	 * from the default remote transport of one of the ports, an out-of-band transport
	 * is setup using that type
	 * @param dataSize the data size. Some protocols require a hint on big the data will be, especially if the data is dynamically
	 * sized (like std::vector<double>). If you leave this empty (recommended), the protocol
	 * will try to guess it. The unit of data size is protocol dependent.
	 * @param connectionName the connection name. The connection name.
	 * Can be used to coordinate out of band transport such that they can find each other by
	 * name. In practice, the name contains a port number or file descriptor to be opened.
	 * You only need to provide a name_id if you're using out-of-band transports without
	 * supervisor, for example, when using MQueues without Corba.
	 * @throws CNoCorbaTransport the c no corba transport
	 * @throws CNoSuchPortException the c no such port exception
	 */
	private CorbaOrocosConnection(OrocosDataPort port, ConnectionType connectionType,
			boolean init, LockPolicy lockPolicy, boolean pull, int bufferSize,
			int transport, int dataSize, String connectionName) throws CNoCorbaTransport, CNoSuchPortException {


		logger = Logger.getLogger(CorbaOrocosConnection.class);

		this.port = port;
		this.connectionType = connectionType;

		CConnectionModel type;
		if(connectionType == ConnectionType.DATA){
			type = RTT.corba.CConnectionModel.CData;
		}else{
			type = RTT.corba.CConnectionModel.CBuffer;
		}

		RTT.corba.CLockPolicy policy;
		if(lockPolicy == LockPolicy.USYNC){
			policy = RTT.corba.CLockPolicy.CUnsync;
		}else if(lockPolicy == LockPolicy.LOCK_FREE){
			policy = RTT.corba.CLockPolicy.CLockFree;
		}else{
			policy = RTT.corba.CLockPolicy.CLocked;
		}

		connectionPolicy = new CConnPolicy(type, init, policy, pull, bufferSize, transport, dataSize, connectionName);
		CConnPolicyHolder policyHolder = new CConnPolicyHolder(connectionPolicy);

			if(port.getPortType() == PortType.INPUT_PORT){
				channelElement =  port.getComponent().getCDataFlowInterface().buildChannelOutput(port.getName(), policyHolder);
				if(port.getComponent().getCDataFlowInterface().channelReady(port.getName(), channelElement)){
					logger.info("Createad a connection to the port " + port.getName());
				}else{
					logger.warn("Connection to the port " + port.getName() + " failed");
				}
			}else{
				channelElement =  port.getComponent().getCDataFlowInterface().buildChannelInput(port.getName(), policyHolder);
				logger.info("Createad a connection to the port " + port.getName());
			}

		

	}


	/**
	 * Creates a data connection to the specified data port.
	 *
	 * @param port the port
	 * @param lockPolicy the lock policy
	 * @return the created connection
	 * @throws CNoCorbaTransport the c no corba transport
	 * @throws CNoSuchPortException the c no such port exception
	 */
	public static AbstractOrocosConnection createDataConnection(OrocosDataPort port, LockPolicy lockPolicy) throws CNoCorbaTransport, CNoSuchPortException{
		return new CorbaOrocosConnection(port, ConnectionType.DATA, false, lockPolicy, false, 0, 0, 0, "");
	}
	
	/**
	 * Creates a buffer connection to the specified data port.
	 *
	 * @param port the port
	 * @param lockPolicy the lock policy
	 * @param bufferSize the buffer size
	 * @return the created connection
	 * @throws CNoCorbaTransport the c no corba transport
	 * @throws CNoSuchPortException the c no such port exception
	 */
	public static AbstractOrocosConnection createBufferConnection(OrocosDataPort port, LockPolicy lockPolicy, int bufferSize ) throws CNoCorbaTransport, CNoSuchPortException{
		return new CorbaOrocosConnection(port, ConnectionType.BUFFER, false, lockPolicy, false, bufferSize, 0, 0, "");
	}
	
	/**
	 * @see AbstractOrocosConnection#readData()
	 */
	@Override
	public PortReadingResult readData() throws WrongUseOfConnectionException{
		if(port.getPortType() == PortType.INPUT_PORT){
			logger.error("You are trying to read from an Orocos input port ");
			throw new WrongUseOfConnectionException(port.getPortType());
		}
		AnyHolder holder = new AnyHolder();
		CFlowStatus flowStatus = channelElement.read(holder, false);
		if(flowStatus == CFlowStatus.CNewData){
			return new PortReadingResult(OutputPortStatus.NEW_DATA, AnyObjectCast.anyToObject(holder.value));
			//return new ReadOperationResult(OutputPortStatus.NEW_DATA, holder.value);
		}else if(flowStatus == CFlowStatus.COldData){
			return new PortReadingResult(OutputPortStatus.OLD_DATA, AnyObjectCast.anyToObject(holder.value));
			//return new ReadOperationResult(OutputPortStatus.OLD_DATA, holder.value);
		}else{
			return new PortReadingResult(OutputPortStatus.NO_DATA, null);
		}

	}

	/**
	 * @see AbstractOrocosConnection#writeData(Object)
	 */
	@Override
	public boolean writeData(Object value) throws WrongUseOfConnectionException {
		if(port.getPortType() == PortType.OUTPUT_PORT){
			logger.error("You are trying to write on an Orocos output port ");
			throw new WrongUseOfConnectionException(port.getPortType());
		}
		
		String portDataType = port.getDataType().toLowerCase();
		String valueDataType = value.getClass().getSimpleName();
		
		boolean generalTypesCondition = portDataType.equals(value.getClass().getSimpleName().toLowerCase()) ||
				portDataType.endsWith(value.getClass().getSimpleName().toLowerCase()); // for complex data type (e.g. /std/string)
		boolean booleanCondition = portDataType.equals("bool") && value.getClass().isAssignableFrom(Boolean.class);
		boolean charCondition = portDataType.equals("char") && value.getClass().isAssignableFrom(Character.class);
		boolean intCondition = (portDataType.equals("int") || portDataType.equals("uint")) && 
				value.getClass().isAssignableFrom(Integer.class);
		boolean arrayCondition = portDataType.equals("array") && (valueDataType.equals("boolean[]") || valueDataType.equals("char[]")
				|| valueDataType.equals("double[]") || valueDataType.equals("float[]") || 
				valueDataType.equals("int[]") || valueDataType.equals("java.lang.String[]"));
		
		if(! ( generalTypesCondition || booleanCondition || charCondition || intCondition || arrayCondition)){

			//System.out.println(value.getClass().getSimpleName().toLowerCase());
			//System.out.println(port.getDataType().toLowerCase());
			logger.error("You are trying to write a wrong data type (port type: " + port.getDataType() + 
					" - object type: " + value.getClass().getSimpleName() + ")");
			return false;
		}

		return channelElement.write(AnyObjectCast.objectToAny(value,port.getDataType().toLowerCase()));
	}

	
}
