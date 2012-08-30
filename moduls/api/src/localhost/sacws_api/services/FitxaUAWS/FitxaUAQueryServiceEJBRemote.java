/**
 * FitxaUAQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.FitxaUAWS;

public interface FitxaUAQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO obtenirUnitatAdministrativa(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fitxa.FitxaDTO obtenirFitxa(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.seccio.SeccioDTO obtenirSeccio(long in0) throws java.rmi.RemoteException;
}
