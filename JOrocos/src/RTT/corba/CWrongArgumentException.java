package RTT.corba;


/**
* RTT/corba/CWrongArgumentException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

public final class CWrongArgumentException extends org.omg.CORBA.UserException
{
  public int which_arg = (int)0;
  public int max_arg = (int)0;

  public CWrongArgumentException ()
  {
    super(CWrongArgumentExceptionHelper.id());
  } // ctor

  public CWrongArgumentException (int _which_arg, int _max_arg)
  {
    super(CWrongArgumentExceptionHelper.id());
    which_arg = _which_arg;
    max_arg = _max_arg;
  } // ctor


  public CWrongArgumentException (String $reason, int _which_arg, int _max_arg)
  {
    super(CWrongArgumentExceptionHelper.id() + "  " + $reason);
    which_arg = _which_arg;
    max_arg = _max_arg;
  } // ctor

} // class CWrongArgumentException