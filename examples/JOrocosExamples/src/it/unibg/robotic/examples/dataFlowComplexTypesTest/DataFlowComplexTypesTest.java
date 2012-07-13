package it.unibg.robotic.examples.dataFlowComplexTypesTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import orogen.JOrocos.Corba.Path;
import orogen.JOrocos.Corba.Pose;
import orogen.JOrocos.Corba.Quaternion;
import orogen.JOrocos.Corba.Vector3;

public class DataFlowComplexTypesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String IP = "";
		try {
			IP = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DataFlowComplexTypesTestProxy proxy = new DataFlowComplexTypesTestProxy(IP, "2809");

		Path path = new Path();
		
		path.stamp = System.currentTimeMillis();
		path.frameId = "/map";
		Pose poses[] = new Pose[2];
		poses[0] = new Pose(new Vector3(1, 0, 0), new Quaternion(0,0,0,1));
		poses[1] = new Pose(new Vector3(1, 0.5, 0), new Quaternion(0,0,0,1));
		path.poses = poses;
		
		proxy.writeOnInputPort("pathInputPort",path);
	}

}
