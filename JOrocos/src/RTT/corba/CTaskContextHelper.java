package RTT.corba;


/**
* RTT/corba/CTaskContextHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TaskContext.idl
* Wednesday, July 11, 2012 5:27:10 PM CEST
*/


/**
	 * A CTaskContext is the main entry point of a distributed
	 * component and maps to a RTT::TaskContext.
	 * @ingroup CompIDL
	 */
abstract public class CTaskContextHelper
{
  private static String  _id = "IDL:RTT/corba/CTaskContext:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CTaskContext that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CTaskContext extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTT.corba.CTaskContextHelper.id (), "CTaskContext");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CTaskContext read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CTaskContextStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CTaskContext value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTT.corba.CTaskContext narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CTaskContext)
      return (RTT.corba.CTaskContext)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CTaskContextStub stub = new RTT.corba._CTaskContextStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static RTT.corba.CTaskContext unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTT.corba.CTaskContext)
      return (RTT.corba.CTaskContext)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTT.corba._CTaskContextStub stub = new RTT.corba._CTaskContextStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
