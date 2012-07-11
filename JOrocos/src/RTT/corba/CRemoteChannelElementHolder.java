package RTT.corba;

/**
* RTT/corba/CRemoteChannelElementHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/


/**
     * The advanced channel element interface, used by Orocos
     * to relay over-CORBA data flow connections.
     */
public final class CRemoteChannelElementHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CRemoteChannelElement value = null;

  public CRemoteChannelElementHolder ()
  {
  }

  public CRemoteChannelElementHolder (RTT.corba.CRemoteChannelElement initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CRemoteChannelElementHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CRemoteChannelElementHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CRemoteChannelElementHelper.type ();
  }

}
