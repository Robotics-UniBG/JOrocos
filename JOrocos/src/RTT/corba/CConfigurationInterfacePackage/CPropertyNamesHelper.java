package RTT.corba.CConfigurationInterfacePackage;


/**
* RTT/corba/CConfigurationInterfacePackage/CPropertyNamesHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ConfigurationInterface.idl
* Wednesday, July 11, 2012 5:26:13 PM CEST
*/

abstract public class CPropertyNamesHelper
{
  private static String  _id = "IDL:RTT/corba/CConfigurationInterface/CPropertyNames:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CConfigurationInterfacePackage.CProperty[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CConfigurationInterfacePackage.CProperty[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = RTT.corba.CConfigurationInterfacePackage.CPropertyHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (RTT.corba.CConfigurationInterfacePackage.CPropertyNamesHelper.id (), "CPropertyNames", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTT.corba.CConfigurationInterfacePackage.CProperty[] read (org.omg.CORBA.portable.InputStream istream)
  {
    RTT.corba.CConfigurationInterfacePackage.CProperty value[] = null;
    int _len0 = istream.read_long ();
    value = new RTT.corba.CConfigurationInterfacePackage.CProperty[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = RTT.corba.CConfigurationInterfacePackage.CPropertyHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CConfigurationInterfacePackage.CProperty[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      RTT.corba.CConfigurationInterfacePackage.CPropertyHelper.write (ostream, value[_i0]);
  }

}
