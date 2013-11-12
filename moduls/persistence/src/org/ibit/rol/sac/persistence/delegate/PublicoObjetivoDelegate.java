package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.persistence.intf.PublicoObjetivoFacade;
import org.ibit.rol.sac.persistence.intf.PublicoObjetivoFacadeHome;
import org.ibit.rol.sac.persistence.util.PublicoObjetivoFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Publico Objetivo.
 */
public class PublicoObjetivoDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarPublicoObjetivo(PublicoObjetivo hechov) throws DelegateException {
        try {
            return getFacade().grabarPublicoObjetivo(hechov);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda listarPublicoObjetivo(int pagina, int resultats) throws DelegateException {
    	try {
    		return getFacade().listarPublicoObjetivo(pagina, resultats);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    @SuppressWarnings("unchecked")
	public List<PublicoObjetivo> listarPublicoObjetivo() throws DelegateException {
        try {
            return getFacade().listarPublicoObjetivo();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public PublicoObjetivo obtenerPublicoObjetivo(Long id) throws DelegateException {
        try {
            return getFacade().obtenerPublicoObjetivo(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarPublicoObjetivo(Long id) throws DelegateException {
        try {
            getFacade().borrarPublicoObjetivo(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void subirOrden(Long id) throws DelegateException {
    	try {
            getFacade().subirOrden(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reordenar(Long id, Integer nuevoOrden, Integer ordenAnterior) throws DelegateException {
    	try {
    		getFacade().reordenar(id, nuevoOrden, ordenAnterior);
    	} catch (RemoteException e ) {
    		throw new DelegateException(e);
    	}
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private PublicoObjetivoFacade getFacade() throws RemoteException {
        return (PublicoObjetivoFacade) facadeHandle.getEJBObject();
    }

    protected PublicoObjetivoDelegate() throws DelegateException {
        try {
            PublicoObjetivoFacadeHome home = PublicoObjetivoFacadeUtil.getHome();
            PublicoObjetivoFacade remote = home.create();
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
