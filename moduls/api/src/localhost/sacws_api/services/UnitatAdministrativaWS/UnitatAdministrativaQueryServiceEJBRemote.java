/**
 * UnitatAdministrativaQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.UnitatAdministrativaWS;

public interface UnitatAdministrativaQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO obtenirPare(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO obtenirEspaiTerritorial(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarProcediments(long in0, es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarMateries(long in0, es.caib.rolsac.api.v2.materia.MateriaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFitxes(long in0, es.caib.rolsac.api.v2.fitxa.FitxaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarNormatives(long in0, es.caib.rolsac.api.v2.normativa.NormativaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarPersonal(long in0, es.caib.rolsac.api.v2.personal.PersonalCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarUsuaris(long in0, es.caib.rolsac.api.v2.usuari.UsuariCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarEdificis(long in0, es.caib.rolsac.api.v2.edifici.EdificiCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarSeccions(long in0, es.caib.rolsac.api.v2.seccio.SeccioCriteria in1) throws java.rmi.RemoteException;
    public int getNumFitxes(long in0) throws java.rmi.RemoteException;
    public int getNumNormatives(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tractament.TractamentDTO obtenirTractament(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO[] llistarFilles(long in0, es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarTramits(long in0, es.caib.rolsac.api.v2.tramit.TramitCriteria in1) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirFotop(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirFotog(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirLogoh(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirLogov(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirLogos(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirLogot(long in0) throws java.rmi.RemoteException;
    public int getNumFilles(long in0) throws java.rmi.RemoteException;
    public int getNumEdificis(long in0) throws java.rmi.RemoteException;
    public int getNumPersonal(long in0) throws java.rmi.RemoteException;
    public int getNumProcediments(long in0) throws java.rmi.RemoteException;
    public int getNumTramits(long in0) throws java.rmi.RemoteException;
    public int getNumUsuaris(long in0) throws java.rmi.RemoteException;
    public int getNumSeccions(long in0) throws java.rmi.RemoteException;
    public int getNumMateries(long in0) throws java.rmi.RemoteException;
}
