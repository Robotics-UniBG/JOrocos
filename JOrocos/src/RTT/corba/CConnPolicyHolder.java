package RTT.corba;

/**
* RTT/corba/CConnPolicyHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

public final class CConnPolicyHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CConnPolicy value = null;

  public CConnPolicyHolder ()
  {
  }

  public CConnPolicyHolder (RTT.corba.CConnPolicy initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CConnPolicyHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CConnPolicyHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CConnPolicyHelper.type ();
  }

}