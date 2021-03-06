package RTT.corba;


/**
* RTT/corba/CServiceRequesterHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ServiceRequester.idl
* Wednesday, July 11, 2012 5:27:05 PM CEST
*/


/**
	 * An Orocos Required Service.
	 * Exposes that this component requires certain services.
	 * @ingroup CompIDL
	 */
abstract public class CServiceRequesterHelper
{
  private static String  _id = "IDL:RTT/corba/CServiceRequester:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CServiceRequester that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CServiceRequester extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTT.corba.CServiceRequesterHelper.id (), "CServiceRequester");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CServiceRequester read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CServiceRequesterStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CServiceRequester value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTT.corba.CServiceRequester narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CServiceRequester)
      return (RTT.corba.CServiceRequester)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CServiceRequesterStub stub = new RTT.corba._CServiceRequesterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static RTT.corba.CServiceRequester unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CServiceRequester)
      return (RTT.corba.CServiceRequester)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CServiceRequesterStub stub = new RTT.corba._CServiceRequesterStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
