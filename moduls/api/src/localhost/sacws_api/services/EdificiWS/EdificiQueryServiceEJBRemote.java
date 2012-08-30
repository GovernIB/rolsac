/**
 * EdificiQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.EdificiWS;

public interface EdificiQueryServiceEJBRemote extends java.rmi.Remote {
    public int getNumUnitatsAdministratives(long in0) throws java.rmi.RemoteException;
    public java.lang.Object[] llistarUnitatsAdministratives(long in0, es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in1) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirFotoPequenya(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirFotoGrande(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirPlano(long in0) throws java.rmi.RemoteException;
}
