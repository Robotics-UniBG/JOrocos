package orogen.JOrocos.Corba;


/**
* orogen/JOrocos/Corba/Pose.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../OrocosComponents/DataFlowComplexTypesTestTypesPlugin/transports/corba/DataFlowComplexTypesTestTypesPluginTypes.idl
* Friday, July 13, 2012 12:54:15 PM CEST
*/

public final class Pose implements org.omg.CORBA.portable.IDLEntity
{
  public orogen.JOrocos.Corba.Vector3 position = null;
  public orogen.JOrocos.Corba.Quaternion orientation = null;

  public Pose ()
  {
  } // ctor

  public Pose (orogen.JOrocos.Corba.Vector3 _position, orogen.JOrocos.Corba.Quaternion _orientation)
  {
    position = _position;
    orientation = _orientation;
  } // ctor

} // class Pose