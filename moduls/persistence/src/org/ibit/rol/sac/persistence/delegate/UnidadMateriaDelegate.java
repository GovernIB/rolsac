package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.persistence.intf.UnidadMateriaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadMateriaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadMateriaFacadeUtil;

/**
 * Business delegate para manipular Unidades Materia
 */
public class UnidadMateriaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	private static final long serialVersionUID = 1L;

	public Long grabarUnidadMateria(UnidadMateria unidadMateria, Long unidad_id, Long materia_id) throws DelegateException {
        try {
            return getFacade().grabarUnidadMateria(unidadMateria, unidad_id, materia_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public void grabarUnidadesMateria(List<UnidadMateria> unidadesMateriaNuevas, List<Long> unidadesMateriaABorrar) throws DelegateException {
		try {
            getFacade().grabarUnidadesMateria(unidadesMateriaNuevas, unidadesMateriaABorrar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    public void borrarUnidadMateria(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private UnidadMateriaFacade getFacade() throws RemoteException {
        return (UnidadMateriaFacade) facadeHandle.getEJBObject();
    }
    
    protected UnidadMateriaDelegate() throws DelegateException {
        try {
            UnidadMateriaFacadeHome home = UnidadMateriaFacadeUtil.getHome();
            UnidadMateriaFacade remote = home.create();
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
