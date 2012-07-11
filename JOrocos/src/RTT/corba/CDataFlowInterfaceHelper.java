package RTT.corba;


/**
* RTT/corba/CDataFlowInterfaceHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/


/**
     * An interface to access the dataflow
     * of a CControlTask object. Data ports are exported as
     * assignable expressions (Set/Get). Buffer ports are
     * exported as buffer channels.
     * @ingroup CompIDL
     */
abstract public class CDataFlowInterfaceHelper
{
  private static String  _id = "IDL:RTT/corba/CDataFlowInterface:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CDataFlowInterface that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CDataFlowInterface extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTT.corba.CDataFlowInterfaceHelper.id (), "CDataFlowInterface");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CDataFlowInterface read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CDataFlowInterfaceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CDataFlowInterface value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTT.corba.CDataFlowInterface narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CDataFlowInterface)
      return (RTT.corba.CDataFlowInterface)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CDataFlowInterfaceStub stub = new RTT.corba._CDataFlowInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static RTT.corba.CDataFlowInterface unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CDataFlowInterface)
      return (RTT.corba.CDataFlowInterface)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CDataFlowInterfaceStub stub = new RTT.corba._CDataFlowInterfaceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
