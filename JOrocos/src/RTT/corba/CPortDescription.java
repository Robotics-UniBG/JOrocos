package RTT.corba;


/**
* RTT/corba/CPortDescription.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

public final class CPortDescription implements org.omg.CORBA.portable.IDLEntity
{
  public RTT.corba.CPortType type = null;
  public String name = null;
  public String type_name = null;

  public CPortDescription ()
  {
  } // ctor

  public CPortDescription (RTT.corba.CPortType _type, String _name, String _type_name)
  {
    type = _type;
    name = _name;
    type_name = _type_name;
  } // ctor

} // class CPortDescription
