package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.persistence.intf.PerfilFacade;
import org.ibit.rol.sac.persistence.intf.PerfilFacadeHome;
import org.ibit.rol.sac.persistence.util.PerfilFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular mascaras.
 */
public class PerfilDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarPerfil(PerfilCiudadano perfil) throws DelegateException {
        try {
            return getFacade().grabarPerfil(perfil);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda listarPerfiles(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarPerfiles(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	} 
    }
    
    public List listarPerfiles() throws DelegateException {
        try {
            return getFacade().listarPerfiles();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public PerfilCiudadano obtenerPerfil(Long id) throws DelegateException {
        try {
            return getFacade().obtenerPerfil(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public PerfilCiudadano obtenerPerfil(String codigo) throws DelegateException {
        try {
            return getFacade().obtenerPerfil(codigo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarPerfil(Long id) throws DelegateException {
        try {
            getFacade().borrarPerfil(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private PerfilFacade getFacade() throws RemoteException {
        return (PerfilFacade) facadeHandle.getEJBObject();
    }

    protected PerfilDelegate() throws DelegateException {
        try {
            PerfilFacadeHome home = PerfilFacadeUtil.getHome();
            PerfilFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

}
