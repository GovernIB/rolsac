package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.persistence.intf.UnidadNormativaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadNormativaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadNormativaFacadeUtil;

/**
 * Business delegate para manipular Unidades Normativa
 */
public class UnidadNormativaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	private static final long serialVersionUID = 1L;

	public Long grabarUnidadNormativa(UnidadNormativa unidadNormativa, Long unidad_id, Long Normativa_id) throws DelegateException {
        try {
            return getFacade().grabarUnidadNormativa(unidadNormativa, unidad_id, Normativa_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public void grabarUnidadesNormativa(List<UnidadNormativa> unidadesNormativaNuevas, List<Long> unidadesNormativaABorrar) throws DelegateException {
		try {
            getFacade().grabarUnidadesNormativa(unidadesNormativaNuevas, unidadesNormativaABorrar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    public void borrarUnidadNormativa(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private UnidadNormativaFacade getFacade() throws RemoteException {
        return (UnidadNormativaFacade) facadeHandle.getEJBObject();
    }
    
    protected UnidadNormativaDelegate() throws DelegateException {
        try {
            UnidadNormativaFacadeHome home = UnidadNormativaFacadeUtil.getHome();
            UnidadNormativaFacade remote = home.create();
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
