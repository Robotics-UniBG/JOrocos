package RTT.corba;

/**
* RTT/corba/CFlowStatusHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

public final class CFlowStatusHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CFlowStatus value = null;

  public CFlowStatusHolder ()
  {
  }

  public CFlowStatusHolder (RTT.corba.CFlowStatus initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CFlowStatusHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CFlowStatusHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CFlowStatusHelper.type ();
  }

}
