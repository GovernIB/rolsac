/**
 * IconaFamiliaQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.IconaFamiliaWS;

public interface IconaFamiliaQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.familia.FamiliaDTO obtenirFamilia(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.perfil.PerfilDTO obtenirPerfil(long in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.arxiu.ArxiuDTO obtenirIcona(long in0) throws java.rmi.RemoteException;
}
