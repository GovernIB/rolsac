/**
 * EnllacQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.EnllacWS;

public interface EnllacQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.procediment.ProcedimentDTO obtenirProcediment(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fitxa.FitxaDTO obtenirFitxa(long in0) throws java.rmi.RemoteException;
}
