package RTT.corba;

/**
* RTT/corba/CNoSuchPortExceptionHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

public final class CNoSuchPortExceptionHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CNoSuchPortException value = null;

  public CNoSuchPortExceptionHolder ()
  {
  }

  public CNoSuchPortExceptionHolder (RTT.corba.CNoSuchPortException initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CNoSuchPortExceptionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CNoSuchPortExceptionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CNoSuchPortExceptionHelper.type ();
  }

}
