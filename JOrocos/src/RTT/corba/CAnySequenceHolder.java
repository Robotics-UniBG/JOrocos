package RTT.corba;


/**
* RTT/corba/CAnySequenceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OrocosTypes.idl
* Wednesday, July 11, 2012 5:26:55 PM CEST
*/


// Classical Orocos type
public final class CAnySequenceHolder implements org.omg.CORBA.portable.Streamable
{
  public org.omg.CORBA.Any value[] = null;

  public CAnySequenceHolder ()
  {
  }

  public CAnySequenceHolder (org.omg.CORBA.Any[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.CAnySequenceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.CAnySequenceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.CAnySequenceHelper.type ();
  }

}
