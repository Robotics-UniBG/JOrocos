package RTT.corba;


/**
* RTT/corba/CChannelElementOperations.java .
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
public interface CChannelElementOperations 
{

  /**
           * Reads from this Channel Element.
           */
  RTT.corba.CFlowStatus read (org.omg.CORBA.AnyHolder sample, boolean copy_old_data);

  /**
           * Writes into this Channel Element.
           * @return false if the channel became invalid
           */
  boolean write (org.omg.CORBA.Any sample);

  /**
           * Disconnect and dispose this object.
           * You may no longer use this object after calling this method.
           */
  void disconnect ();
} // interface CChannelElementOperations
