package RTT.corba;


/**
* RTT/corba/CNoSuchNameException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

public final class CNoSuchNameException extends org.omg.CORBA.UserException
{
  public String name = null;

  public CNoSuchNameException ()
  {
    super(CNoSuchNameExceptionHelper.id());
  } // ctor

  public CNoSuchNameException (String _name)
  {
    super(CNoSuchNameExceptionHelper.id());
    name = _name;
  } // ctor


  public CNoSuchNameException (String $reason, String _name)
  {
    super(CNoSuchNameExceptionHelper.id() + "  " + $reason);
    name = _name;
  } // ctor

} // class CNoSuchNameException
