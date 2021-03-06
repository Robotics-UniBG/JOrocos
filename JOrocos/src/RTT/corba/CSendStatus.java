package RTT.corba;


/**
* RTT/corba/CSendStatus.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

public class CSendStatus implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 3;
  private static RTT.corba.CSendStatus[] __array = new RTT.corba.CSendStatus [__size];

  public static final int _CSendFailure = 0;
  public static final RTT.corba.CSendStatus CSendFailure = new RTT.corba.CSendStatus(_CSendFailure);
  public static final int _CSendNotReady = 1;
  public static final RTT.corba.CSendStatus CSendNotReady = new RTT.corba.CSendStatus(_CSendNotReady);
  public static final int _CSendSuccess = 2;
  public static final RTT.corba.CSendStatus CSendSuccess = new RTT.corba.CSendStatus(_CSendSuccess);

  public int value ()
  {
    return __value;
  }

  public static RTT.corba.CSendStatus from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected CSendStatus (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class CSendStatus
