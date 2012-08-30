/**
 * TramitQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.TramitWS;

public interface TramitQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.procediment.ProcedimentDTO obtenirProcediment(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarTaxes(long in0, es.caib.rolsac.api.v2.taxa.TaxaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFormularis(long in0, es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in1) throws java.rmi.RemoteException;
    public int getNumDocumentsInformatius(long in0) throws java.rmi.RemoteException;
    public int getNumFormularis(long in0) throws java.rmi.RemoteException;
    public int getNumTaxes(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO obtenirOrganCompetent(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistatDocumentsInformatius(long in0, es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in1) throws java.rmi.RemoteException;
}
