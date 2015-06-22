package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.persistence.intf.PerfilGestorFacade;
import org.ibit.rol.sac.persistence.intf.PerfilGestorFacadeHome;
import org.ibit.rol.sac.persistence.util.PerfilGestorFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular mascaras.
 */
public class PerfilGestorDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarPerfilGestor(PerfilGestor perfilGestor) throws DelegateException {
        try {
            return getFacade().grabarPerfilGestor(perfilGestor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ResultadoBusqueda listarPerfilesGestor(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarPerfilesGestor(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	} 
    }

    public List listarPerfilesGestor() throws DelegateException {
        try {
            return getFacade().listarPerfilesGestor();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public PerfilGestor obtenerPerfilGestor(Long id) throws DelegateException {
        try {
            return getFacade().obtenerPerfilGestor(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public PerfilGestor obtenerPerfilGestor(String codigo) throws DelegateException {
        try {
            return getFacade().obtenerPerfilGestor(codigo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarPerfilGestor(Long id) throws DelegateException {
        try {
            getFacade().borrarPerfilGestor(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public void anyadirSeccionPerfilGestor(Long idSeccio, Long idPerfilGestor) throws DelegateException {
        try {
            getFacade().anyadirSeccionPerfilGestor(idSeccio,idPerfilGestor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
		
	}
	
	public void eliminarSeccionPerfilGestor(Long idSeccio, Long idPerfilGestor) throws DelegateException {
        try {
            getFacade().eliminarSeccionPerfilGestor(idSeccio,idPerfilGestor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
		
	}

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private PerfilGestorFacade getFacade() throws RemoteException {
        return (PerfilGestorFacade) facadeHandle.getEJBObject();
    }

    protected PerfilGestorDelegate() throws DelegateException {
        try {
            PerfilGestorFacadeHome home = PerfilGestorFacadeUtil.getHome();
            PerfilGestorFacade remote = home.create();
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
