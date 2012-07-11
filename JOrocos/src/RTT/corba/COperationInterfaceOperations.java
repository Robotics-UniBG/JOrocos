package RTT.corba;


/**
* RTT/corba/COperationInterfaceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/


/**
     * Exposes the operations this service offers.
     * @ingroup CompIDL
     */
public interface COperationInterfaceOperations 
{

  /**
         * Get a list of all operations.
         */
  String[] getOperations ();

  /**
         * Get a list of all arguments of a given operation.
         */
  RTT.corba.CArgumentDescription[] getArguments (String operation) throws RTT.corba.CNoSuchNameException;

  /**
         * Returns the number of arguments required in callOperation() and sendOperation() for
         * a given operation.
         */
  short getArity (String operation) throws RTT.corba.CNoSuchNameException;

  /**
         * Returns the number of arguments required in CSendHandle::collect() and CSendHandle::collectIfDone() for
         * a given operation.
         */
  short getCollectArity (String operation) throws RTT.corba.CNoSuchNameException;

  /**
         * Get a result type of a given operation.
         */
  String getResultType (String operation) throws RTT.corba.CNoSuchNameException;

  /**
         * Returns the argument or return type name of call as known to the Orocos type system.
         */
  String getArgumentType (String operation, short nbr) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongArgumentException;

  /**
         * Returns the argument type  of collect as known to the Orocos type system.
         */
  String getCollectType (String operation, short nbr) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongArgumentException;

  /**
         * Get a description of a given operation.
         */
  String getDescription (String operation) throws RTT.corba.CNoSuchNameException;

  /**
         * Checks if a given operation accepts the list of arguments.
         * If no exception is thrown, the arguments and operation name were valid.
         */
  void checkOperation (String operation, org.omg.CORBA.Any[] args) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongNumbArgException, RTT.corba.CWrongTypeArgException;

  /**
         * Call an operation with a list of arguments.
         * This method will block until the operation completes and returns its result.
         */
  org.omg.CORBA.Any callOperation (String operation, RTT.corba.CAnyArgumentsHolder args) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongNumbArgException, RTT.corba.CWrongTypeArgException, RTT.corba.CCallInterrupted;

  /**
         * Send an operation with a list of arguments.
         * This method will return immediately and return a CSendHandle.
         */
  RTT.corba.CSendHandle sendOperation (String operation, org.omg.CORBA.Any[] args) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongNumbArgException, RTT.corba.CWrongTypeArgException, RTT.corba.CCallInterrupted;
} // interface COperationInterfaceOperations