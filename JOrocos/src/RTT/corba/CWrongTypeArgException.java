package RTT.corba;


/**
* RTT/corba/CWrongTypeArgException.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

public final class CWrongTypeArgException extends org.omg.CORBA.UserException
{
  public int whicharg = (int)0;
  public String expected = null;
  public String received = null;

  public CWrongTypeArgException ()
  {
    super(CWrongTypeArgExceptionHelper.id());
  } // ctor

  public CWrongTypeArgException (int _whicharg, String _expected, String _received)
  {
    super(CWrongTypeArgExceptionHelper.id());
    whicharg = _whicharg;
    expected = _expected;
    received = _received;
  } // ctor


  public CWrongTypeArgException (String $reason, int _whicharg, String _expected, String _received)
  {
    super(CWrongTypeArgExceptionHelper.id() + "  " + $reason);
    whicharg = _whicharg;
    expected = _expected;
    received = _received;
  } // ctor

} // class CWrongTypeArgException
