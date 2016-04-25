package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.intf.UsuarioFacade;
import org.ibit.rol.sac.persistence.intf.UsuarioFacadeHome;
import org.ibit.rol.sac.persistence.util.UsuarioFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Business delegate para manipular usurios.
 */
public class UsuarioDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public void grabarUsuario(Usuario usuario) throws DelegateException {
        try {
            getFacade().grabarUsuario(usuario);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List buscarUsuarios(Map parametros) throws DelegateException {
        try {
            return getFacade().buscarUsuarios(parametros);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Usuario obtenerUsuario(Long id) throws DelegateException {
        try {
            return getFacade().obtenerUsuario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Usuario obtenerUsuariobyUsername(String username) throws DelegateException {
        try {
            return getFacade().obtenerUsuariobyUsername(username);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
  
    public void asignarUnidad(Long usuario_id, Long ua_id)
            throws DelegateException {
        try {
            getFacade().asignarUnidad(usuario_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void desasignarUnidad(Long usuario_id, Long ua_id)
            throws DelegateException {
        try {
            getFacade().desasignarUnidad(usuario_id, ua_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarUsuario(Long id) throws DelegateException {
        try {
            getFacade().borrarUsuario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Usuario obtenerUsuariobyUsernamePMA(String username) throws DelegateException {
        try {
            return getFacade().obtenerUsuariobyUsernamePMA(username);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }   
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private UsuarioFacade getFacade() throws RemoteException {
        return (UsuarioFacade) facadeHandle.getEJBObject();
    }

    protected UsuarioDelegate() throws DelegateException {
        try {
            UsuarioFacadeHome home = UsuarioFacadeUtil.getHome();
            UsuarioFacade remote = home.create();
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
