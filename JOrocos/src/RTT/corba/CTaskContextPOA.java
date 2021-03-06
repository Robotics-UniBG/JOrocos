package RTT.corba;


/**
* RTT/corba/CTaskContextPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TaskContext.idl
* Wednesday, July 11, 2012 5:27:10 PM CEST
*/


/**
	 * A CTaskContext is the main entry point of a distributed
	 * component and maps to a RTT::TaskContext.
	 * @ingroup CompIDL
	 */
public abstract class CTaskContextPOA extends org.omg.PortableServer.Servant
 implements RTT.corba.CTaskContextOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getName", new java.lang.Integer (0));
    _methods.put ("getDescription", new java.lang.Integer (1));
    _methods.put ("getTaskState", new java.lang.Integer (2));
    _methods.put ("configure", new java.lang.Integer (3));
    _methods.put ("start", new java.lang.Integer (4));
    _methods.put ("activate", new java.lang.Integer (5));
    _methods.put ("stop", new java.lang.Integer (6));
    _methods.put ("resetException", new java.lang.Integer (7));
    _methods.put ("cleanup", new java.lang.Integer (8));
    _methods.put ("isActive", new java.lang.Integer (9));
    _methods.put ("isRunning", new java.lang.Integer (10));
    _methods.put ("isConfigured", new java.lang.Integer (11));
    _methods.put ("inFatalError", new java.lang.Integer (12));
    _methods.put ("inRunTimeError", new java.lang.Integer (13));
    _methods.put ("ports", new java.lang.Integer (14));
    _methods.put ("getProvider", new java.lang.Integer (15));
    _methods.put ("getRequester", new java.lang.Integer (16));
    _methods.put ("getPeerList", new java.lang.Integer (17));
    _methods.put ("getPeer", new java.lang.Integer (18));
    _methods.put ("hasPeer", new java.lang.Integer (19));
    _methods.put ("addPeer", new java.lang.Integer (20));
    _methods.put ("removePeer", new java.lang.Integer (21));
    _methods.put ("connectPeers", new java.lang.Integer (22));
    _methods.put ("disconnectPeers", new java.lang.Integer (23));
    _methods.put ("connectPorts", new java.lang.Integer (24));
    _methods.put ("connectServices", new java.lang.Integer (25));
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
  	     * Return the instance name of this component.
  	     */
       case 0:  // RTT/corba/CTaskContext/getName
       {
         String $result = null;
         $result = this.getName ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  /**
  	     * Return the description of this component.
  	     */
       case 1:  // RTT/corba/CTaskContext/getDescription
       {
         String $result = null;
         $result = this.getDescription ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  /**
  	     * Returns the current state of the component.
  	     */
       case 2:  // RTT/corba/CTaskContext/getTaskState
       {
         RTT.corba.CTaskState $result = null;
         $result = this.getTaskState ();
         out = $rh.createReply();
         RTT.corba.CTaskStateHelper.write (out, $result);
         break;
       }


  /**
  	     * Configure this component.
  	     * @see RTT::base::TaskCore::configure()
  	     */
       case 3:  // RTT/corba/CTaskContext/configure
       {
         boolean $result = false;
         $result = this.configure ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Start this component.
  	     * @see RTT::base::TaskCore::start()
  	     */
       case 4:  // RTT/corba/CTaskContext/start
       {
         boolean $result = false;
         $result = this.start ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Activate this component.
  	     * @see RTT::base::TaskCore::activate()
  	     */
       case 5:  // RTT/corba/CTaskContext/activate
       {
         boolean $result = false;
         $result = this.activate ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Stop this component.
  	     * @see RTT::base::TaskCore::stop()
  	     */
       case 6:  // RTT/corba/CTaskContext/stop
       {
         boolean $result = false;
         $result = this.stop ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Asks the component to transition from an Exception state to the
  	     * Stopped state
  
  	     * @see RTT::base::TaskCore::exception() and
  	     * RTT::base::TaskCore::recover()
  	     */
       case 7:  // RTT/corba/CTaskContext/resetException
       {
         boolean $result = false;
         $result = this.resetException ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Cleanup this component.
  	     * @see RTT::base::TaskCore::cleanup()
  	     */
       case 8:  // RTT/corba/CTaskContext/cleanup
       {
         boolean $result = false;
         $result = this.cleanup ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Is this component's ExecutionEngine active ?
  	     * @see RTT::base::TaskCore::isActive()
  	     */
       case 9:  // RTT/corba/CTaskContext/isActive
       {
         boolean $result = false;
         $result = this.isActive ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Is this component running ?
  	     * @see RTT::base::TaskCore::isRunning()
  	     */
       case 10:  // RTT/corba/CTaskContext/isRunning
       {
         boolean $result = false;
         $result = this.isRunning ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Is this component configured ?
  	     * @see RTT::base::TaskCore::isConfigured()
  	     */
       case 11:  // RTT/corba/CTaskContext/isConfigured
       {
         boolean $result = false;
         $result = this.isConfigured ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Is this component in a Fatal error state ?
  	     * @see RTT::base::TaskCore::inFatalError()
  	     */
       case 12:  // RTT/corba/CTaskContext/inFatalError
       {
         boolean $result = false;
         $result = this.inFatalError ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Is this component in a RunTime error state ?
  	     * @see RTT::base::TaskCore::inRunTimeError()
  	     */
       case 13:  // RTT/corba/CTaskContext/inRunTimeError
       {
         boolean $result = false;
         $result = this.inRunTimeError ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Access to the Data Flow ports.
  	     * @see RTT::base::TaskCore::ports()
  	     */
       case 14:  // RTT/corba/CTaskContext/ports
       {
         RTT.corba.CDataFlowInterface $result = null;
         $result = this.ports ();
         out = $rh.createReply();
         RTT.corba.CDataFlowInterfaceHelper.write (out, $result);
         break;
       }


  /**
  	     * Get a service.
               *
               * Use 'this' as the name to get the task context's own service
               * provider
               *
  	     * @see RTT::TaskContext::provides()
  	     */
       case 15:  // RTT/corba/CTaskContext/getProvider
       {
         String service_name = in.read_string ();
         RTT.corba.CService $result = null;
         $result = this.getProvider (service_name);
         out = $rh.createReply();
         RTT.corba.CServiceHelper.write (out, $result);
         break;
       }


  /**
  	     * Get a required service.
  	     * @see RTT::TaskContext::requires()
  	     */
       case 16:  // RTT/corba/CTaskContext/getRequester
       {
         String service_name = in.read_string ();
         RTT.corba.CServiceRequester $result = null;
         $result = this.getRequester (service_name);
         out = $rh.createReply();
         RTT.corba.CServiceRequesterHelper.write (out, $result);
         break;
       }


  /**
  	     * Get a list of all the peers this task is connected to.
  	     * @see RTT::TaskContext::getPeerList()
  	     */
       case 17:  // RTT/corba/CTaskContext/getPeerList
       {
         String $result[] = null;
         $result = this.getPeerList ();
         out = $rh.createReply();
         RTT.corba.CTaskContextPackage.CPeerNamesHelper.write (out, $result);
         break;
       }


  /**
  	     * Get a peer this task is connected to.
  	     * @see RTT::TaskContext::getPeer()
  	     */
       case 18:  // RTT/corba/CTaskContext/getPeer
       {
         String name = in.read_string ();
         RTT.corba.CTaskContext $result = null;
         $result = this.getPeer (name);
         out = $rh.createReply();
         RTT.corba.CTaskContextHelper.write (out, $result);
         break;
       }


  /**
  	     * Has this task a peer with given name ?
  	     * @see RTT::TaskContext::hasPeer()
  	     */
       case 19:  // RTT/corba/CTaskContext/hasPeer
       {
         String name = in.read_string ();
         boolean $result = false;
         $result = this.hasPeer (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Add a one-way peer connection.
  	     * @see RTT::TaskContext::addPeer()
  	     */
       case 20:  // RTT/corba/CTaskContext/addPeer
       {
         RTT.corba.CTaskContext p = RTT.corba.CTaskContextHelper.read (in);
         String peer_alias = in.read_string ();
         boolean $result = false;
         $result = this.addPeer (p, peer_alias);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Remove a one-way peer connection.
  	     * @see RTT::TaskContext::removePeer()
  	     */
       case 21:  // RTT/corba/CTaskContext/removePeer
       {
         String name = in.read_string ();
         boolean $result = false;
         $result = this.removePeer (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Create a two-way peer connection.
  	     * @see RTT::TaskContext::connectPeers()
  	     */
       case 22:  // RTT/corba/CTaskContext/connectPeers
       {
         RTT.corba.CTaskContext p = RTT.corba.CTaskContextHelper.read (in);
         boolean $result = false;
         $result = this.connectPeers (p);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Destroy a two-way peer connection.
  	     * @see RTT::TaskContext::disconnectPeers()
  	     */
       case 23:  // RTT/corba/CTaskContext/disconnectPeers
       {
         String name = in.read_string ();
         boolean $result = false;
         $result = this.disconnectPeers (name);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Connect all compatible and equally named
  	     * data ports with another CTaskContext's
  	     * data ports.
  	     * @see RTT::TaskContext::connectPorts()
  	     */
       case 24:  // RTT/corba/CTaskContext/connectPorts
       {
         RTT.corba.CTaskContext p = RTT.corba.CTaskContextHelper.read (in);
         boolean $result = false;
         $result = this.connectPorts (p);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /**
  	     * Connect all compatible and equally named
  	     * services with another CTaskContext's
  	     * services.
  	     * @see RTT::TaskContext::connectServices()
  	     */
       case 25:  // RTT/corba/CTaskContext/connectServices
       {
         RTT.corba.CTaskContext p = RTT.corba.CTaskContextHelper.read (in);
         boolean $result = false;
         $result = this.connectServices (p);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:RTT/corba/CTaskContext:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CTaskContext _this() 
  {
    return CTaskContextHelper.narrow(
    super._this_object());
  }

  public CTaskContext _this(org.omg.CORBA.ORB orb) 
  {
    return CTaskContextHelper.narrow(
    super._this_object(orb));
  }


} // class CTaskContextPOA
