package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.StatelessDelegate;
import org.ibit.rol.sac.persistence.intf.PublicoObjetivoFacade;
import org.ibit.rol.sac.persistence.intf.PublicoObjetivoFacadeHome;
import org.ibit.rol.sac.persistence.util.PublicoObjetivoFacadeUtil;
import org.ibit.rol.sac.model.PublicoObjetivo;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

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

    public PublicoObjetivo obtenerPublicoObjetivoPorTitulo(String titulo) throws DelegateException {
        try {
            return getFacade().obtenerPublicoObjetivoPorTitulo(titulo);
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
