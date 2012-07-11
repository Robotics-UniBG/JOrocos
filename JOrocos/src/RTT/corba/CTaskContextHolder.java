package RTT.corba;

/**
* RTT/corba/CTaskContextHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TaskContext.idl
* Wednesday, July 11, 2012 5:27:10 PM CEST
*/


/**
	 * A CTaskContext is the main entry point of a distributed
	 * component and maps to a RTT::TaskContext.
	 * @ingroup CompIDL
	 */
public final class CTaskContextHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CTaskContext value = null;

  public CTaskContextHolder ()
  {
  }

  public CTaskContextHolder (RTT.corba.CTaskContext initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CTaskContextHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CTaskContextHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CTaskContextHelper.type ();
  }

}