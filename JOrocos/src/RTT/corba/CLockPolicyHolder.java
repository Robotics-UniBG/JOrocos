package RTT.corba;

/**
* RTT/corba/CLockPolicyHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

public final class CLockPolicyHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CLockPolicy value = null;

  public CLockPolicyHolder ()
  {
  }

  public CLockPolicyHolder (RTT.corba.CLockPolicy initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CLockPolicyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CLockPolicyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CLockPolicyHelper.type ();
  }

}
