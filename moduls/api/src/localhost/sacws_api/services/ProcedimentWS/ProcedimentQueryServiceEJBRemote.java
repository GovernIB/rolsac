/**
 * ProcedimentQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.ProcedimentWS;

public interface ProcedimentQueryServiceEJBRemote extends java.rmi.Remote {
    public java.lang.Object[] llistarMateries(long in0, es.caib.rolsac.api.v2.materia.MateriaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFetsVitals(long in0, es.caib.rolsac.api.v2.fetVital.FetVitalCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarNormatives(long in0, es.caib.rolsac.api.v2.normativa.NormativaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarDocuments(long in0, es.caib.rolsac.api.v2.document.DocumentCriteria in1) throws java.rmi.RemoteException;
    public int getNumNormatives(long in0, long in1) throws java.rmi.RemoteException;
    public int getNumFetsVitals(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarTramits(long in0, es.caib.rolsac.api.v2.tramit.TramitCriteria in1) throws java.rmi.RemoteException;
    public int getNumTramits(long in0) throws java.rmi.RemoteException;
    public int getNumMateries(long in0) throws java.rmi.RemoteException;
    public int getNumDocuments(long in0) throws java.rmi.RemoteException;
}