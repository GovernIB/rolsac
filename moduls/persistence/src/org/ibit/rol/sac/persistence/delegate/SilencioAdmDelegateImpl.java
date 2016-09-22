package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.persistence.intf.SilencioAdmFacade;
import org.ibit.rol.sac.persistence.intf.SilencioAdmFacadeHome;
import org.ibit.rol.sac.persistence.util.SilencioAdmFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular silencio adm.
 */
public class SilencioAdmDelegateImpl implements StatelessDelegate, SilencioAdmDelegateI
{
	private static final long serialVersionUID = -5255731439556042563L;
	
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#grabarSilencioAdm(org.ibit.rol.sac.model.SilencioAdm)
	 */
    public Long grabarSilencioAdm(SilencioAdm silencio, boolean editar) throws DelegateException {
        try {
            return getFacade().grabarSilencioAdm(silencio, editar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ResultadoBusqueda listarSilencioAdm(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarSilencioAdm(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#listarSilencioAdm()
	 */
    public List listarSilencioAdm() throws DelegateException {
        try {
            return getFacade().listarSilencioAdm();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#obtenerSilencioAdm(java.lang.String)
	 */
    public SilencioAdm obtenerSilencioAdm(Long codigo) throws DelegateException {
        try {
            return getFacade().obtenerSilencioAdm(codigo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#borrarSilencioAdm(java.lang.String)
	 */
    public void borrarSilencioAdm(Long codigo) throws DelegateException {
        try {
            getFacade().borrarSilencioAdm(codigo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private SilencioAdmFacade getFacade() throws RemoteException {
        return (SilencioAdmFacade) facadeHandle.getEJBObject();
    }
    SilencioAdmDelegateImpl() throws DelegateException {
        try {
            SilencioAdmFacadeHome home = SilencioAdmFacadeUtil.getHome();
            SilencioAdmFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	@Override
	public int cuantosProcedimientosConSilencio(Long id)
			throws DelegateException {
		 try {
            return getFacade().cuantosProcedimientosConSilencio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
}
