package RTT.corba;


/**
* RTT/corba/PairSeqHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OrocosTypes.idl
* Wednesday, July 11, 2012 5:26:55 PM CEST
*/


// use to convert std::map
public final class PairSeqHolder implements org.omg.CORBA.portable.Streamable
{
  public RTT.corba.Pair value[] = null;

  public PairSeqHolder ()
  {
  }

  public PairSeqHolder (RTT.corba.Pair[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.PairSeqHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.PairSeqHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.PairSeqHelper.type ();
  }

}
