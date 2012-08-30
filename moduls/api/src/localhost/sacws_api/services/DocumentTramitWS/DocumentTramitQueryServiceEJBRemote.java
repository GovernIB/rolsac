/**
 * DocumentTramitQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.DocumentTramitWS;

public interface DocumentTramitQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.tramit.TramitDTO obtenirTramit(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirArxiu(long in0) throws java.rmi.RemoteException;
}
