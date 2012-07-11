package RTT.corba;


/**
* RTT/corba/CWrongArgumentExceptionHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

abstract public class CWrongArgumentExceptionHelper
{
  private static String  _id = "IDL:RTT/corba/CWrongArgumentException:1.0";

  public static void insert (org.omg.CORBA.Any a, RTT.corba.CWrongArgumentException that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTT.corba.CWrongArgumentException extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "which_arg",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[1] = new org.omg.CORBA.StructMember (
            "max_arg",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (RTT.corba.CWrongArgumentExceptionHelper.id (), "CWrongArgumentException", _members0);
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

  public static RTT.corba.CWrongArgumentException read (org.omg.CORBA.portable.InputStream istream)
  {
    RTT.corba.CWrongArgumentException value = new RTT.corba.CWrongArgumentException ();
    // read and discard the repository ID
    istream.read_string ();
    value.which_arg = istream.read_long ();
    value.max_arg = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTT.corba.CWrongArgumentException value)
  {
    // write the repository ID
    ostream.write_string (id ());
    ostream.write_long (value.which_arg);
    ostream.write_long (value.max_arg);
  }

}