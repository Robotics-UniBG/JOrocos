package orogen.JOrocos.Corba;


/**
* orogen/JOrocos/Corba/vector__JOrocos_Pose_Helper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../OrocosComponents/DataFlowComplexTypesTestTypesPlugin/transports/corba/DataFlowComplexTypesTestTypesPluginTypes.idl
* Friday, July 13, 2012 12:54:15 PM CEST
*/

abstract public class vector__JOrocos_Pose_Helper
{
  private static String  _id = "IDL:orogen/JOrocos/Corba/vector__JOrocos_Pose_:1.0";

  public static void insert (org.omg.CORBA.Any a, orogen.JOrocos.Corba.Pose[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static orogen.JOrocos.Corba.Pose[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = orogen.JOrocos.Corba.PoseHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (orogen.JOrocos.Corba.vector__JOrocos_Pose_Helper.id (), "vector__JOrocos_Pose_", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static orogen.JOrocos.Corba.Pose[] read (org.omg.CORBA.portable.InputStream istream)
  {
    orogen.JOrocos.Corba.Pose value[] = null;
    int _len0 = istream.read_long ();
    value = new orogen.JOrocos.Corba.Pose[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = orogen.JOrocos.Corba.PoseHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, orogen.JOrocos.Corba.Pose[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      orogen.JOrocos.Corba.PoseHelper.write (ostream, value[_i0]);
  }

}
