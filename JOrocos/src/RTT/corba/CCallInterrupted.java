package RTT.corba;


/**
* RTT/corba/CCallInterrupted.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

public final class CCallInterrupted extends org.omg.CORBA.UserException
{

  public CCallInterrupted ()
  {
    super(CCallInterruptedHelper.id());
  } // ctor


  public CCallInterrupted (String $reason)
  {
    super(CCallInterruptedHelper.id() + "  " + $reason);
  } // ctor

} // class CCallInterrupted
