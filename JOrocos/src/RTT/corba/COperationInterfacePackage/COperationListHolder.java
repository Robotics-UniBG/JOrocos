package RTT.corba.COperationInterfacePackage;


/**
* RTT/corba/COperationInterfacePackage/COperationListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/

public final class COperationListHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public COperationListHolder ()
  {
  }

  public COperationListHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTT.corba.COperationInterfacePackage.COperationListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTT.corba.COperationInterfacePackage.COperationListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTT.corba.COperationInterfacePackage.COperationListHelper.type ();
  }

}