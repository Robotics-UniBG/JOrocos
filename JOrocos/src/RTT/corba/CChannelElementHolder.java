package RTT.corba;

/**
* RTT/corba/CChannelElementHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/


/**
     * Represents the basic channel element interface
     * for reading, writing and disconnecting a channel.
     * Use this interface in case you want to communicate
     * with an Orocos component's port from a non-Orocos application.
     */
public final class CChannelElementHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.CChannelElement value = null;

  public CChannelElementHolder ()
  {
  }

  public CChannelElementHolder (RTT.corba.CChannelElement initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CChannelElementHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CChannelElementHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CChannelElementHelper.type ();
  }

}
