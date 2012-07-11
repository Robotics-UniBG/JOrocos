package RTT.corba;


/**
* RTT/corba/CPortDescriptionHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

abstract public class CPortDescriptionHelper
{
  private static String  _id = "IDL:RTT/corba/CPortDescription:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CPortDescription that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CPortDescription extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = RTT.corba.CPortTypeHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "type",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "type_name",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTT.corba.CPortDescriptionHelper.id (), "CPortDescription", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CPortDescription read (org.omg.CORBA.portable.InputStream istream)
  {
    RTT.corba.CPortDescription value = new RTT.corba.CPortDescription ();
    value.type = RTT.corba.CPortTypeHelper.read (istream);
    value.name = istream.read_string ();
    value.type_name = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CPortDescription value)
  {
    RTT.corba.CPortTypeHelper.write (ostream, value.type);
    ostream.write_string (value.name);
    ostream.write_string (value.type_name);
  }

}