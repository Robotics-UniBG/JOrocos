package RTT.corba;

/**
* RTT/corba/CServiceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Service.idl
* Wednesday, July 11, 2012 5:27:00 PM CEST
*/


/**
	 * An Orocos Service which hosts operations, attributes and properties.
	 * @ingroup CompIDL
	 */
public final class CServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CService value = null;

  public CServiceHolder ()
  {
  }

  public CServiceHolder (RTT.corba.CService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CServiceHelper.type ();
  }

}
