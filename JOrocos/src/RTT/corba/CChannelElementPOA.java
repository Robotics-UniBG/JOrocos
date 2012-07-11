package RTT.corba;


/**
* RTT/corba/CChannelElementPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DataFlow.idl
* Wednesday, July 11, 2012 5:26:42 PM CEST
*/


/**
     * Represents the basic channel element interface
     * for reading, writing and disconnecting a channel.
     * Use this interface in case you want to communicate
     * with an Orocos component's port from a non-Orocos application.
     */
public abstract class CChannelElementPOA extends org.omg.PortableServer.Servant
 implements RTT.corba.CChannelElementOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("read", new java.lang.Integer (0));
    _methods.put ("write", new java.lang.Integer (1));
    _methods.put ("disconnect", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {

  /**
           * Reads from this Channel Element.
           */
       case 0:  // RTT/corba/CChannelElement/read
       {
         org.omg.CORBA.AnyHolder sample = new org.omg.CORBA.AnyHolder ();
         boolean copy_old_data = in.read_boolean ();
         RTT.corba.CFlowStatus $result = null;
         $result = this.read (sample, copy_old_data);
         out = $rh.createReply();
         RTT.corba.CFlowStatusHelper.write (out, $result);
         out.write_any (sample.value);
         break;
       }


  /**
           * Writes into this Channel Element.
           * @return false if the channel became invalid
           */
       case 1:  // RTT/corba/CChannelElement/write
       {
         org.omg.CORBA.Any sample = in.read_any ();
         boolean $result = false;
         $result = this.write (sample);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
           * Disconnect and dispose this object.
           * You may no longer use this object after calling this method.
           */
       case 2:  // RTT/corba/CChannelElement/disconnect
       {
         this.disconnect ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RTT/corba/CChannelElement:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CChannelElement _this() 
  {
    return CChannelElementHelper.narrow(
    super._this_object());
  }

  public CChannelElement _this(org.omg.CORBA.ORB orb) 
  {
    return CChannelElementHelper.narrow(
    super._this_object(orb));
  }


} // class CChannelElementPOA
