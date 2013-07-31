/**
 * ExcepcioDocumentacioQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.caib.rolsac.api.v2.excepcioDocumentacio.ws;

public interface ExcepcioDocumentacioQueryServiceEJBRemote extends java.rmi.Remote {
    public int getNumDocumentsTramit(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarDocumentsTramits(long in0, es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in1) throws java.rmi.RemoteException;
    public int getNumCatalegsDocuments(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarCatalegsDocuments(long in0, es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria in1) throws java.rmi.RemoteException;
}
