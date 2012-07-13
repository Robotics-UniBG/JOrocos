package it.unibg.robotic.examples.dataFlowComplexTypesTest;

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

import orogen.JOrocos.Corba.Path;

public class DataFlowComplexTypesTestProxy implements Observer {

	AbstractOrocosSystem system;
	AbstractOrocosComponent dataFlowSimpleTypeTestComponent;
	
	public DataFlowComplexTypesTestProxy(String systemIP, String systemPort) {
		
		try {
			
			// create a proxy to the Orocos system and connect to it
			system = CorbaOrocosSystem.getInstance(systemIP, systemPort);
			system.connect();
			
			// create a proxy to the Hello World component
			dataFlowSimpleTypeTestComponent = system.getComponent("DataFlowComplexTypesTest", false);
			
			// create a connection to the input ports
			dataFlowSimpleTypeTestComponent.createDataConnectionToInputPort("pathInputPort", 
					LockPolicy.LOCK_FREE, this);
			
			// subsribe to the output ports with a period of 100ms
			dataFlowSimpleTypeTestComponent.subscribeToDataOutputPort("pathOutputPort",
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
			if(((OrocosPortEvent)event).getValue() instanceof Path){
				Path path = (Path)((OrocosPortEvent)event).getValue();
				System.out.println("Stamp: " + path.stamp);
				System.out.println("Frame Id: " + path.frameId);
				System.out.println("Num Waypoints: " + path.poses.length);
				for(int i = 0; i < path.poses.length; i++){
					System.out.println("Pose-" + i + ": " + 
							"t_x:" + path.poses[0].position.x +
							", t_y:" + path.poses[0].position.y +
							", t_z:" + path.poses[0].position.z +
							", o_x:" + path.poses[0].orientation.x +
							", o_y:" + path.poses[0].orientation.y +
							", o_z:" + path.poses[0].orientation.z +
							", o_w:" + path.poses[0].orientation.w);
				}
			}
		}
		
	}
	
	public void writeOnInputPort(String name, Path value){
		
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
