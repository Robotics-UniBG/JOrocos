package RTT.corba;


/**
* RTT/corba/CRemoteChannelElementHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/


/**
     * The advanced channel element interface, used by Orocos
     * to relay over-CORBA data flow connections.
     */
abstract public class CRemoteChannelElementHelper
{
  private static String  _id = "IDL:RTT/corba/CRemoteChannelElement:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CRemoteChannelElement that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CRemoteChannelElement extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTT.corba.CRemoteChannelElementHelper.id (), "CRemoteChannelElement");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CRemoteChannelElement read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CRemoteChannelElementStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CRemoteChannelElement value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTT.corba.CRemoteChannelElement narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CRemoteChannelElement)
      return (RTT.corba.CRemoteChannelElement)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CRemoteChannelElementStub stub = new RTT.corba._CRemoteChannelElementStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static RTT.corba.CRemoteChannelElement unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CRemoteChannelElement)
      return (RTT.corba.CRemoteChannelElement)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CRemoteChannelElementStub stub = new RTT.corba._CRemoteChannelElementStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
