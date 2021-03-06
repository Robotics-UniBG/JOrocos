package RTT.corba;


/**
* RTT/corba/CConnPolicy.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/

public final class CConnPolicy implements org.omg.CORBA.portable.IDLEntity
{
  public RTT.corba.CConnectionModel type = null;
  public boolean init = false;
  public RTT.corba.CLockPolicy lock_policy = null;
  public boolean pull = false;
  public int size = (int)0;
  public int transport = (int)0;
  public int data_size = (int)0;
  public String name_id = null;

  public CConnPolicy ()
  {
  } // ctor

  public CConnPolicy (RTT.corba.CConnectionModel _type, boolean _init, RTT.corba.CLockPolicy _lock_policy, boolean _pull, int _size, int _transport, int _data_size, String _name_id)
  {
    type = _type;
    init = _init;
    lock_policy = _lock_policy;
    pull = _pull;
    size = _size;
    transport = _transport;
    data_size = _data_size;
    name_id = _name_id;
  } // ctor

} // class CConnPolicy
