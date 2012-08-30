/**
 * FamiliaQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.FamiliaWS;

public interface FamiliaQueryServiceEJBRemote extends java.rmi.Remote {
    public java.lang.Object[] llistarProcedimentsLocals(long in0, es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in1) throws java.rmi.RemoteException;
    public int getNumProcedimentsLocals(long in0) throws java.rmi.RemoteException;
    public int getNumIcones(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarIcones(long in0, es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria in1) throws java.rmi.RemoteException;
}
