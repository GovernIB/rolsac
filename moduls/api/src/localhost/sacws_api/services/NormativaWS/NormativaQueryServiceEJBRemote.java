/**
 * NormativaQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.NormativaWS;

public interface NormativaQueryServiceEJBRemote extends java.rmi.Remote {
    public java.lang.Object[] llistarProcediments(long in0, es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in1) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO obtenirUnitatAdministrativa(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.butlleti.ButlletiDTO obtenirButlleti(long in0) throws java.rmi.RemoteException;
    public int getNumProcediments(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarAfectades(long in0) throws java.rmi.RemoteException;
    public int getNumAfectades(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarAfectants(long in0) throws java.rmi.RemoteException;
    public int getNumAfectants(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirArxiuNormativa(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.afectacio.AfectacioDTO[] llistarAfectacionsAfectants(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.afectacio.AfectacioDTO[] llistarAfectacionsAfectades(long in0) throws java.rmi.RemoteException;
}
