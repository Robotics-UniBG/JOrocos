package it.unibg.robotic.examples.dataFlowSimpleTypesTest;

import it.unibg.robotics.jorocos.corba.CorbaOrocosSystem;
import it.unibg.robotics.jorocos.core.AbstractOrocosComponent;
import it.unibg.robotics.jorocos.core.AbstractOrocosConnection.LockPolicy;
import it.unibg.robotics.jorocos.core.AbstractOrocosSystem;
import it.unibg.robotics.jorocos.core.OrocosPortEvent;
import it.unibg.robotics.jorocos.exceptions.ConnectionToPortNotExistException;
import it.unibg.robotics.jorocos.exceptions.SystemIpAndPortAlreadyDefinedException;
import it.unibg.robotics.jorocos.exceptions.SystemNotConnectedException;
import it.unibg.robotics.jorocos.exceptions.WrongPortTypeException;

import java.util.Observable;
import java.util.Observer;

public class DataFlowSimpleTypesTestProxy implements Observer {

	AbstractOrocosSystem system;
	AbstractOrocosComponent dataFlowSimpleTypeTestComponent;
	
	public DataFlowSimpleTypesTestProxy(String systemIP, String systemPort) {
		
		try {
			
			// create a proxy to the Orocos system and connect to it
			system = CorbaOrocosSystem.getInstance(systemIP, systemPort);
			system.connect();
			
			// create a proxy to the Hello World component
			dataFlowSimpleTypeTestComponent = system.getComponent("DataFlowSimpleTypesTest", false);
			
			// create a connection to the input ports
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("boolInputPort", 
					LockPolicy.LOCK_FREE, this);
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("charInputPort", 
					LockPolicy.LOCK_FREE, this);
			// This has been commented because the CORBA transport doesn't know the type,
			// hence it crashes the Orocos system
//			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("ucharInputPort", 
//					LockPolicy.LOCK_FREE, this);
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("doubleInputPort", 
					LockPolicy.LOCK_FREE, this);
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("floatInputPort", 
					LockPolicy.LOCK_FREE, this);
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("intInputPort", 
					LockPolicy.LOCK_FREE, this);
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("uintInputPort", 
					LockPolicy.LOCK_FREE, this);
			// These have been commented because the CORBA transport doesn't know the types,
			// hence it crashes the Orocos system
//			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("longInputPort", 
//					LockPolicy.LOCK_FREE, this);
//			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("ulongInputPort", 
//					LockPolicy.LOCK_FREE, this);
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("stringInputPort", 
					LockPolicy.LOCK_FREE, this);
			
			
			// subsribe to the output ports with a period of 100ms
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("boolOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("charOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			// This has been commented because the CORBA transport doesn't know the type,
			// hence it crashes the Orocos system
//			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("ucharOutputPort",
//					LockPolicy.LOCK_FREE, this, 100);
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("doubleOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("floatOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("intOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("uintOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			// These have been commented because the CORBA transport doesn't know the types,
			// hence it crashes the Orocos system
//			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("longOutputPort",
//					LockPolicy.LOCK_FREE, this, 100);
//			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("ulongOutputPort",
//					LockPolicy.LOCK_FREE, this, 100);
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("stringOutputPort",
					LockPolicy.LOCK_FREE, this, 100);
			
		} catch (SystemIpAndPortAlreadyDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemNotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPortTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Observable observable, Object event) {
		if(event instanceof OrocosPortEvent){
			System.out.println("Port: " + ((OrocosPortEvent)event).getPortName() +
					" - Value: " + ((OrocosPortEvent)event).getValue());
		}
		
	}
	
	public void writeOnInputPort(String name, Object value){
		
		try {
			dataFlowSimpleTypeTestComponent.writeOnPort(name, value, this);
		} catch (ConnectionToPortNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPortTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
