package RTT.corba;

/**
* RTT/corba/CSendHandleHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/


/**
     * A handler object that allows us to collect the results
     * of a send. Due to memory restrictions, a server may choose
     * to cleanup a CSendHandle before or during collect() was called.
     * This is equivalent to having a SendError.
     */
public final class CSendHandleHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CSendHandle value = null;

  public CSendHandleHolder ()
  {
  }

  public CSendHandleHolder (RTT.corba.CSendHandle initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CSendHandleHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CSendHandleHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CSendHandleHelper.type ();
  }

}
