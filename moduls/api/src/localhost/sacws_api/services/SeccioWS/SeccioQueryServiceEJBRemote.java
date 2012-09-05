/**
 * SeccioQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.SeccioWS;

public interface SeccioQueryServiceEJBRemote extends java.rmi.Remote {
    public int getNumUnitatsAdministratives(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarUnitatsAdministratives(long in0, es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in1) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.seccio.SeccioDTO obtenirPare(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarFitxes(long in0, es.caib.rolsac.api.v2.fitxa.FitxaCriteria in1) throws java.rmi.RemoteException;
    public int getNumFitxes(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.seccio.SeccioDTO[] llistarFilles(long in0, es.caib.rolsac.api.v2.seccio.SeccioCriteria in1) throws java.rmi.RemoteException;
    public int getNumFilles(long in0) throws java.rmi.RemoteException;
    public int getNumPares(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.seccio.SeccioDTO[] llistarPares(long in0) throws java.rmi.RemoteException;
}
