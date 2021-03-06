package RTT.corba;


/**
* RTT/corba/CSendHandle.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/


/**
     * A handler object that allows us to collect the results
     * of a send. Due to memory restrictions, a server may choose
     * to cleanup a CSendHandle before or during collect() was called.
     * This is equivalent to having a SendError.
     */
public interface CSendHandle extends CSendHandleOperations, org.omg.CORBA.Object, org.omg.CORBA.portable.IDLEntity 
{
} // interface CSendHandle
