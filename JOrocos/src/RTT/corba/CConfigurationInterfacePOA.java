package RTT.corba;


/**
* RTT/corba/CConfigurationInterfacePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ConfigurationInterface.idl
* Wednesday, July 11, 2012 5:26:13 PM CEST
*/


/**
	 * Exposes both attributes and properties
	 * of a service.
	 * @ingroup CompIDL
	 */
public abstract class CConfigurationInterfacePOA extends org.omg.PortableServer.Servant
 implements RTT.corba.CConfigurationInterfaceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getAttributeList", new java.lang.Integer (0));
    _methods.put ("getPropertyList", new java.lang.Integer (1));
    _methods.put ("getAttribute", new java.lang.Integer (2));
    _methods.put ("setAttribute", new java.lang.Integer (3));
    _methods.put ("hasAttribute", new java.lang.Integer (4));
    _methods.put ("hasProperty", new java.lang.Integer (5));
    _methods.put ("getProperty", new java.lang.Integer (6));
    _methods.put ("setProperty", new java.lang.Integer (7));
    _methods.put ("getPropertyType", new java.lang.Integer (8));
    _methods.put ("getAttributeType", new java.lang.Integer (9));
    _methods.put ("getPropertyTypeName", new java.lang.Integer (10));
    _methods.put ("getAttributeTypeName", new java.lang.Integer (11));
    _methods.put ("attributeToString", new java.lang.Integer (12));
    _methods.put ("propertyToString", new java.lang.Integer (13));
    _methods.put ("attributeFromString", new java.lang.Integer (14));
    _methods.put ("propertyFromString", new java.lang.Integer (15));
    _methods.put ("isAttributeAssignable", new java.lang.Integer (16));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // RTT/corba/CConfigurationInterface/getAttributeList
       {
         String $result[] = null;
         $result = this.getAttributeList ();
         out = $rh.createReply();
         RTT.corba.CConfigurationInterfacePackage.CAttributeNamesHelper.write (out, $result);
         break;
       }

       case 1:  // RTT/corba/CConfigurationInterface/getPropertyList
       {
         RTT.corba.CConfigurationInterfacePackage.CProperty $result[] = null;
         $result = this.getPropertyList ();
         out = $rh.createReply();
         RTT.corba.CConfigurationInterfacePackage.CPropertyNamesHelper.write (out, $result);
         break;
       }

       case 2:  // RTT/corba/CConfigurationInterface/getAttribute
       {
         String name = in.read_string ();
         org.omg.CORBA.Any $result = null;
         $result = this.getAttribute (name);
         out = $rh.createReply();
         out.write_any ($result);
         break;
       }

       case 3:  // RTT/corba/CConfigurationInterface/setAttribute
       {
         String name = in.read_string ();
         org.omg.CORBA.Any value = in.read_any ();
         boolean $result = false;
         $result = this.setAttribute (name, value);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 4:  // RTT/corba/CConfigurationInterface/hasAttribute
       {
         String name = in.read_string ();
         boolean $result = false;
         $result = this.hasAttribute (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 5:  // RTT/corba/CConfigurationInterface/hasProperty
       {
         String name = in.read_string ();
         boolean $result = false;
         $result = this.hasProperty (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Get a property by name. You can scope the name for
  	     * entering sub-properties. For example 'controlparameters.gains.k'
  	     * to retrieve nested property with name 'k'.
  	     */
       case 6:  // RTT/corba/CConfigurationInterface/getProperty
       {
         String name = in.read_string ();
         org.omg.CORBA.Any $result = null;
         $result = this.getProperty (name);
         out = $rh.createReply();
         out.write_any ($result);
         break;
       }


  /**
  	     * Set a property by name. You can scope the name for
  	     * entering sub-properties. For example 'controlparameters.gains.k'
  	     * to write nested property with name 'k'.
  	     */
       case 7:  // RTT/corba/CConfigurationInterface/setProperty
       {
         String name = in.read_string ();
         org.omg.CORBA.Any value = in.read_any ();
         boolean $result = false;
         $result = this.setProperty (name, value);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Return the type of the attribute or property.
  	     */
       case 8:  // RTT/corba/CConfigurationInterface/getPropertyType
       {
         String name = in.read_string ();
         String $result = null;
         $result = this.getPropertyType (name);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 9:  // RTT/corba/CConfigurationInterface/getAttributeType
       {
         String name = in.read_string ();
         String $result = null;
         $result = this.getAttributeType (name);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  /**
  	     * Return the type name of the expression as it is known
  	     * to the Orocos Type System.
  	     */
       case 10:  // RTT/corba/CConfigurationInterface/getPropertyTypeName
       {
         String name = in.read_string ();
         String $result = null;
         $result = this.getPropertyTypeName (name);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 11:  // RTT/corba/CConfigurationInterface/getAttributeTypeName
       {
         String name = in.read_string ();
         String $result = null;
         $result = this.getAttributeTypeName (name);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  /**
  	     * Return the result of an attribute or property as a text string.
  	     */
       case 12:  // RTT/corba/CConfigurationInterface/attributeToString
       {
         String name = in.read_string ();
         String $result = null;
         $result = this.attributeToString (name);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 13:  // RTT/corba/CConfigurationInterface/propertyToString
       {
         String name = in.read_string ();
         String $result = null;
         $result = this.propertyToString (name);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  /**
  	     * Assign a given property or attribute a stringified value.
  	     * @return true if the assignment could be done.
  	     */
       case 14:  // RTT/corba/CConfigurationInterface/attributeFromString
       {
         String name = in.read_string ();
         String value = in.read_string ();
         boolean $result = false;
         $result = this.attributeFromString (name, value);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 15:  // RTT/corba/CConfigurationInterface/propertyFromString
       {
         String name = in.read_string ();
         String value = in.read_string ();
         boolean $result = false;
         $result = this.propertyFromString (name, value);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Returns true if the attribute can be changed.
  	     */
       case 16:  // RTT/corba/CConfigurationInterface/isAttributeAssignable
       {
         String name = in.read_string ();
         boolean $result = false;
         $result = this.isAttributeAssignable (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RTT/corba/CConfigurationInterface:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CConfigurationInterface _this() 
  {
    return CConfigurationInterfaceHelper.narrow(
    super._this_object());
  }

  public CConfigurationInterface _this(org.omg.CORBA.ORB orb) 
  {
    return CConfigurationInterfaceHelper.narrow(
    super._this_object(orb));
  }


} // class CConfigurationInterfacePOA