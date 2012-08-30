/**
 * PerfilQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.PerfilWS;

public interface PerfilQueryServiceEJBRemote extends java.rmi.Remote {
    public java.lang.Object[] llistarIconesFamilia(long in0, es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria in1) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarIconesMateria(long in0, es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria in1) throws java.rmi.RemoteException;
    public int getNumIconesFamilia(long in0) throws java.rmi.RemoteException;
    public int getNumIconesMateria(long in0) throws java.rmi.RemoteException;
}
