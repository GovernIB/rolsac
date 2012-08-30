/**
 * FitxaQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.FitxaWS;

public interface FitxaQueryServiceEJBRemote extends java.rmi.Remote {
    public int getNumUnitatsAdministratives(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarUnitatsAdministratives(long in0, es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFetsVitals(long in0, es.caib.rolsac.api.v2.fetVital.FetVitalCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarDocuments(long in0, es.caib.rolsac.api.v2.document.DocumentCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarEnllacos(long in0, es.caib.rolsac.api.v2.enllac.EnllacCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarSeccions(long in0, es.caib.rolsac.api.v2.seccio.SeccioCriteria in1) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirIcona(long in0) throws java.rmi.RemoteException;
    public int getNumFetsVitals(long in0) throws java.rmi.RemoteException;
    public int getNumSeccions(long in0) throws java.rmi.RemoteException;
    public int getNumDocuments(long in0) throws java.rmi.RemoteException;
    public int getNumEnllacos(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirImatge(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirBaner(long in0) throws java.rmi.RemoteException;
}
