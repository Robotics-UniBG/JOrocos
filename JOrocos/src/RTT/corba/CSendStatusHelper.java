package RTT.corba;


/**
* RTT/corba/CSendStatusHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

abstract public class CSendStatusHelper
{
  private static String  _id = "IDL:RTT/corba/CSendStatus:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CSendStatus that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CSendStatus extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_enum_tc (RTT.corba.CSendStatusHelper.id (), "CSendStatus", new String[] { "CSendFailure", "CSendNotReady", "CSendSuccess"} );
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CSendStatus read (org.omg.CORBA.portable.InputStream istream)
  {
    return RTT.corba.CSendStatus.from_int (istream.read_long ());
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CSendStatus value)
  {
    ostream.write_long (value.value ());
  }

}
