package it.unibg.robotic.examples.helloWorldDataFlow;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

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
		
		HelloWorldDataFlowProxy proxy = new HelloWorldDataFlowProxy(IP, "2809");

		proxy.writeOnInputPort("World");
	}

}
