package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.intf.IconoMateriaFacade;
import org.ibit.rol.sac.persistence.intf.IconoMateriaFacadeHome;
import org.ibit.rol.sac.persistence.util.IconoMateriaFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

/**
 * Business delegate para manipular Iconos familias.
 */
public class IconoMateriaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public Long grabarIconoMateria(IconoMateria icono, Long materia_id, Long perfil_id) throws DelegateException {
        try {
            return getFacade().grabarIconoMateria(icono, materia_id, perfil_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public IconoMateria obtenerIconoMateria(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIconoMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Archivo obtenerIcono(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIcono(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarIconosMateria(Collection<Long> iconosABorrar) throws DelegateException {
        try {
            getFacade().borrarIconosMateria(iconosABorrar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private IconoMateriaFacade getFacade() throws RemoteException {
        return (IconoMateriaFacade) facadeHandle.getEJBObject();
    }
    
    protected IconoMateriaDelegate() throws DelegateException {
        try {
            IconoMateriaFacadeHome home = IconoMateriaFacadeUtil.getHome();
            IconoMateriaFacade remote = home.create();
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
