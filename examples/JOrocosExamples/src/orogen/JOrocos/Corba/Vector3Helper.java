package orogen.JOrocos.Corba;


/**
* orogen/JOrocos/Corba/Vector3Helper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../OrocosComponents/DataFlowComplexTypesTestTypesPlugin/transports/corba/DataFlowComplexTypesTestTypesPluginTypes.idl
* Friday, July 13, 2012 12:54:15 PM CEST
*/

abstract public class Vector3Helper
{
  private static String  _id = "IDL:orogen/JOrocos/Corba/Vector3:1.0";

  public static void insert (org.omg.CORBA.Any a, orogen.JOrocos.Corba.Vector3 that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static orogen.JOrocos.Corba.Vector3 extract (org.omg.CORBA.Any a)
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
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[0] = new org.omg.CORBA.StructMember (
            "x",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[1] = new org.omg.CORBA.StructMember (
            "y",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[2] = new org.omg.CORBA.StructMember (
            "z",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (orogen.JOrocos.Corba.Vector3Helper.id (), "Vector3", _members0);
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

  public static orogen.JOrocos.Corba.Vector3 read (org.omg.CORBA.portable.InputStream istream)
  {
    orogen.JOrocos.Corba.Vector3 value = new orogen.JOrocos.Corba.Vector3 ();
    value.x = istream.read_double ();
    value.y = istream.read_double ();
    value.z = istream.read_double ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, orogen.JOrocos.Corba.Vector3 value)
  {
    ostream.write_double (value.x);
    ostream.write_double (value.y);
    ostream.write_double (value.z);
  }

}
