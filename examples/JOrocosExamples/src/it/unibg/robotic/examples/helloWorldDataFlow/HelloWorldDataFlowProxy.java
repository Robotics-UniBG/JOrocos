package it.unibg.robotic.examples.helloWorldDataFlow;

import java.util.Observable;
import java.util.Observer;

import it.unibg.robotics.jorocos.corba.CorbaOrocosSystem;
import it.unibg.robotics.jorocos.core.AbstractOrocosComponent;
import it.unibg.robotics.jorocos.core.OrocosDataPort;
import it.unibg.robotics.jorocos.core.OrocosPortEvent;
import it.unibg.robotics.jorocos.core.AbstractOrocosConnection.LockPolicy;
import it.unibg.robotics.jorocos.core.AbstractOrocosSystem;
import it.unibg.robotics.jorocos.exceptions.ConnectionToPortNotExistException;
import it.unibg.robotics.jorocos.exceptions.SystemIpAndPortAlreadyDefinedException;
import it.unibg.robotics.jorocos.exceptions.SystemNotConnectedException;
import it.unibg.robotics.jorocos.exceptions.WrongPortTypeException;

public class HelloWorldDataFlowProxy implements Observer {

	AbstractOrocosSystem system;
	AbstractOrocosComponent helloWorldDataFlowComponent;
	
	public HelloWorldDataFlowProxy(String systemIP, String systemPort) {
		
		try {
			
			// create a proxy to the Orocos system and connect to it
			system = CorbaOrocosSystem.getInstance(systemIP, systemPort);
			system.connect();
			
			// create a proxy to the Hello World component
			helloWorldDataFlowComponent = system.getComponent("HelloWorldDataFlow", false);
			
			// create a connection to the input port
			helloWorldDataFlowComponent.createDataConnectionToInputPort("inputPort", 
					LockPolicy.LOCK_FREE, this);
			
			// subsribe to the output port with a period of 100ms
			helloWorldDataFlowComponent.subscribeToDataOutputPort("outputPort",
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
			System.out.println(((OrocosPortEvent)event).getValue());
		}
		
	}
	
	public void writeOnInputPort(String value){
		
		try {
			helloWorldDataFlowComponent.writeOnPort("inputPort", value, this);
		} catch (ConnectionToPortNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongPortTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
