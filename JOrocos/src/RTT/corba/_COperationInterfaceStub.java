package RTT.corba;


/**
* RTT/corba/_COperationInterfaceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from OperationInterface.idl
* Wednesday, July 11, 2012 5:26:50 PM CEST
*/


/**
     * Exposes the operations this service offers.
     * @ingroup CompIDL
     */
public class _COperationInterfaceStub extends org.omg.CORBA.portable.ObjectImpl implements RTT.corba.COperationInterface
{


  /**
         * Get a list of all operations.
         */
  public String[] getOperations ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getOperations", true);
                $in = _invoke ($out);
                String $result[] = RTT.corba.COperationInterfacePackage.COperationListHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getOperations (        );
            } finally {
                _releaseReply ($in);
            }
  } // getOperations


  /**
         * Get a list of all arguments of a given operation.
         */
  public RTT.corba.CArgumentDescription[] getArguments (String operation) throws RTT.corba.CNoSuchNameException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getArguments", true);
                $out.write_string (operation);
                $in = _invoke ($out);
                RTT.corba.CArgumentDescription $result[] = RTT.corba.CDescriptionsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getArguments (operation        );
            } finally {
                _releaseReply ($in);
            }
  } // getArguments


  /**
         * Returns the number of arguments required in callOperation() and sendOperation() for
         * a given operation.
         */
  public short getArity (String operation) throws RTT.corba.CNoSuchNameException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getArity", true);
                $out.write_string (operation);
                $in = _invoke ($out);
                short $result = $in.read_ushort ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getArity (operation        );
            } finally {
                _releaseReply ($in);
            }
  } // getArity


  /**
         * Returns the number of arguments required in CSendHandle::collect() and CSendHandle::collectIfDone() for
         * a given operation.
         */
  public short getCollectArity (String operation) throws RTT.corba.CNoSuchNameException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCollectArity", true);
                $out.write_string (operation);
                $in = _invoke ($out);
                short $result = $in.read_ushort ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCollectArity (operation        );
            } finally {
                _releaseReply ($in);
            }
  } // getCollectArity


  /**
         * Get a result type of a given operation.
         */
  public String getResultType (String operation) throws RTT.corba.CNoSuchNameException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getResultType", true);
                $out.write_string (operation);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getResultType (operation        );
            } finally {
                _releaseReply ($in);
            }
  } // getResultType


  /**
         * Returns the argument or return type name of call as known to the Orocos type system.
         */
  public String getArgumentType (String operation, short nbr) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongArgumentException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getArgumentType", true);
                $out.write_string (operation);
                $out.write_ushort (nbr);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongArgumentException:1.0"))
                    throw RTT.corba.CWrongArgumentExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getArgumentType (operation, nbr        );
            } finally {
                _releaseReply ($in);
            }
  } // getArgumentType


  /**
         * Returns the argument type  of collect as known to the Orocos type system.
         */
  public String getCollectType (String operation, short nbr) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongArgumentException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getCollectType", true);
                $out.write_string (operation);
                $out.write_ushort (nbr);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongArgumentException:1.0"))
                    throw RTT.corba.CWrongArgumentExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getCollectType (operation, nbr        );
            } finally {
                _releaseReply ($in);
            }
  } // getCollectType


  /**
         * Get a description of a given operation.
         */
  public String getDescription (String operation) throws RTT.corba.CNoSuchNameException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getDescription", true);
                $out.write_string (operation);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getDescription (operation        );
            } finally {
                _releaseReply ($in);
            }
  } // getDescription


  /**
         * Checks if a given operation accepts the list of arguments.
         * If no exception is thrown, the arguments and operation name were valid.
         */
  public void checkOperation (String operation, org.omg.CORBA.Any[] args) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongNumbArgException, RTT.corba.CWrongTypeArgException
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("checkOperation", true);
                $out.write_string (operation);
                RTT.corba.CAnyArgumentsHelper.write ($out, args);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongNumbArgException:1.0"))
                    throw RTT.corba.CWrongNumbArgExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongTypeArgException:1.0"))
                    throw RTT.corba.CWrongTypeArgExceptionHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                checkOperation (operation, args        );
            } finally {
                _releaseReply ($in);
            }
  } // checkOperation


  /**
         * Call an operation with a list of arguments.
         * This method will block until the operation completes and returns its result.
         */
  public org.omg.CORBA.Any callOperation (String operation, RTT.corba.CAnyArgumentsHolder args) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongNumbArgException, RTT.corba.CWrongTypeArgException, RTT.corba.CCallInterrupted
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("callOperation", true);
                $out.write_string (operation);
                RTT.corba.CAnyArgumentsHelper.write ($out, args.value);
                $in = _invoke ($out);
                org.omg.CORBA.Any $result = $in.read_any ();
                args.value = RTT.corba.CAnyArgumentsHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongNumbArgException:1.0"))
                    throw RTT.corba.CWrongNumbArgExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongTypeArgException:1.0"))
                    throw RTT.corba.CWrongTypeArgExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CCallInterrupted:1.0"))
                    throw RTT.corba.CCallInterruptedHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return callOperation (operation, args        );
            } finally {
                _releaseReply ($in);
            }
  } // callOperation


  /**
         * Send an operation with a list of arguments.
         * This method will return immediately and return a CSendHandle.
         */
  public RTT.corba.CSendHandle sendOperation (String operation, org.omg.CORBA.Any[] args) throws RTT.corba.CNoSuchNameException, RTT.corba.CWrongNumbArgException, RTT.corba.CWrongTypeArgException, RTT.corba.CCallInterrupted
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("sendOperation", true);
                $out.write_string (operation);
                RTT.corba.CAnyArgumentsHelper.write ($out, args);
                $in = _invoke ($out);
                RTT.corba.CSendHandle $result = RTT.corba.CSendHandleHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:RTT/corba/CNoSuchNameException:1.0"))
                    throw RTT.corba.CNoSuchNameExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongNumbArgException:1.0"))
                    throw RTT.corba.CWrongNumbArgExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CWrongTypeArgException:1.0"))
                    throw RTT.corba.CWrongTypeArgExceptionHelper.read ($in);
                else if (_id.equals ("IDL:RTT/corba/CCallInterrupted:1.0"))
                    throw RTT.corba.CCallInterruptedHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return sendOperation (operation, args        );
            } finally {
                _releaseReply ($in);
            }
  } // sendOperation

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RTT/corba/COperationInterface:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _COperationInterfaceStub
