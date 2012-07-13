package orogen.JOrocos.Corba;


/**
* orogen/JOrocos/Corba/PathHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../OrocosComponents/DataFlowComplexTypesTestTypesPlugin/transports/corba/DataFlowComplexTypesTestTypesPluginTypes.idl
* Friday, July 13, 2012 12:54:15 PM CEST
*/

abstract public class PathHelper
{
  private static String  _id = "IDL:orogen/JOrocos/Corba/Path:1.0";

  public static void insert (org.omg.CORBA.Any a, orogen.JOrocos.Corba.Path that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static orogen.JOrocos.Corba.Path extract (org.omg.CORBA.Any a)
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
            "stamp",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "frameId",
            _tcOf_members0,
            null);
          _tcOf_members0 = orogen.JOrocos.Corba.PoseHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (orogen.JOrocos.Corba.vector__JOrocos_Pose_Helper.id (), "vector__JOrocos_Pose_", _tcOf_members0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "poses",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (orogen.JOrocos.Corba.PathHelper.id (), "Path", _members0);
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

  public static orogen.JOrocos.Corba.Path read (org.omg.CORBA.portable.InputStream istream)
  {
    orogen.JOrocos.Corba.Path value = new orogen.JOrocos.Corba.Path ();
    value.stamp = istream.read_double ();
    value.frameId = istream.read_string ();
    value.poses = orogen.JOrocos.Corba.vector__JOrocos_Pose_Helper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, orogen.JOrocos.Corba.Path value)
  {
    ostream.write_double (value.stamp);
    ostream.write_string (value.frameId);
    orogen.JOrocos.Corba.vector__JOrocos_Pose_Helper.write (ostream, value.poses);
  }

}
