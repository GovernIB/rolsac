/**
 * ButlletiQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.ButlletiWS;

public interface ButlletiQueryServiceEJBRemote extends java.rmi.Remote {
    public java.lang.Object[] llistarNormatives(long in0, es.caib.rolsac.api.v2.normativa.NormativaCriteria in1) throws java.rmi.RemoteException;
    public int getNumNormatives(long in0, long in1) throws java.rmi.RemoteException;
}