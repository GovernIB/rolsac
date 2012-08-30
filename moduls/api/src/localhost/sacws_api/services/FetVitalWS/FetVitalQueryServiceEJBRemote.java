/**
 * FetVitalQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.FetVitalWS;

public interface FetVitalQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO getIcona(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFitxes(long in0, es.caib.rolsac.api.v2.fitxa.FitxaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarProcedimentsLocals(long in0, es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFetsVitalsAgrupacionsFV(long in0, es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria in1) throws java.rmi.RemoteException;
    public int getNumFitxes(long in0) throws java.rmi.RemoteException;
    public int getNumProcedimentsLocals(long in0) throws java.rmi.RemoteException;
    public int getNumFetsVitalsAgrupacionsFV(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO getFotografia(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO getIconaGran(long in0) throws java.rmi.RemoteException;
}
