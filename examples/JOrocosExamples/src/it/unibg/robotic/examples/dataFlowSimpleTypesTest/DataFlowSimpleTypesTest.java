package it.unibg.robotic.examples.dataFlowSimpleTypesTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataFlowSimpleTypesTest {

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

		DataFlowSimpleTypesTestProxy proxy = new DataFlowSimpleTypesTestProxy(IP, "2809");

		proxy.writeOnInputPort("boolInputPort",false);
		proxy.writeOnInputPort("charInputPort",'a');
		proxy.writeOnInputPort("doubleInputPort",1.5d);
		proxy.writeOnInputPort("floatInputPort",2.5f);
		proxy.writeOnInputPort("intInputPort",-3);
		proxy.writeOnInputPort("uintInputPort",4);
		proxy.writeOnInputPort("stringInputPort","Hello World");
		
	}

}
