/**
 * PublicObjectiuQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.PublicObjectiuWS;

public interface PublicObjectiuQueryServiceEJBRemote extends java.rmi.Remote {
    public int getNumAgrupacions(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarAgrupacions(long in0, es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria in1) throws java.rmi.RemoteException;
}
