package RTT.corba;


/**
* RTT/corba/CServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Service.idl
* Wednesday, July 11, 2012 5:27:00 PM CEST
*/


/**
	 * An Orocos Service which hosts operations, attributes and properties.
	 * @ingroup CompIDL
	 */
public interface CServiceOperations  extends RTT.corba.COperationInterfaceOperations, RTT.corba.CConfigurationInterfaceOperations, RTT.corba.CDataFlowInterfaceOperations
{

  /**
  	     * Return the name of this service.
  	     */
  String getName ();

  /**
  	     * Return the description of this service.
  	     */
  String getServiceDescription ();

  /**
  	     * Get a list of all the child services this service offers.
  	     */
  String[] getProviderNames ();

  /**
  	     * Get a child service this service provides.
  	     */
  RTT.corba.CService getService (String name);

  /**
  	     * Has this service a child service with given name ?
  	     */
  boolean hasService (String name);
} // interface CServiceOperations
