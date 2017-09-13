package org.ibit.rol.sac.persistence.delegate;


import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.intf.NormativaRemotaFacade;
import org.ibit.rol.sac.persistence.intf.NormativaRemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.NormativaRemotaFacadeUtil;
import org.ibit.rol.sac.persistence.util.RemotoUtils;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

import net.sf.hibernate.Session;
import net.sf.hibernate.HibernateException;


/**
 * Business delegate para manipular normativas remotas.
 */
public class NormativaRemotaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;

	public Long grabarNormativaRemota(final NormativaRemota normativaRemota) throws DelegateException {
		try {
			return getFacade().grabarNormativaRemota(normativaRemota);
		} catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
    public NormativaRemota obtenerNormativaRemota(Long idExterno,Long idUaRemota) throws DelegateException {
        try {
            return getFacade().obtenerNormativaRemota(idExterno, idUaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public NormativaRemota obtenerNormativaRemota(final String idRemoto, final Long idExtNorm) throws DelegateException {
         try {
            return getFacade().obtenerNormativaRemota(idRemoto, idExtNorm);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Normativa> obtenerNormativasProcedimiento(Long idProcedimiento) throws DelegateException {
		try {
            return getFacade().obtenerNormativasProcedimiento(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
    public void anyadirProcedimiento(Long proc_id, Long norm_id) throws DelegateException {
    	try {
    		getFacade().anyadirProcedimiento(proc_id, norm_id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public void  borrarNormativaRemota(Long id) throws DelegateException {
    	try {
    		getFacade().borrarNormativaRemota(id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public void borrarNormativaRemota(final String idRemoto, final Long idExt)  throws DelegateException {
    	try {
    		getFacade().borrarNormativaRemota(idRemoto, idExt);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public void eliminarProcNormativa(String idRemoto, Long idNorm, Long idProc) throws DelegateException {
    	try {
    		getFacade().eliminarProcNormativa(idRemoto,idNorm, idProc);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private NormativaRemotaFacade getFacade() throws RemoteException {
        return (NormativaRemotaFacade) facadeHandle.getEJBObject();
    }
    
    protected NormativaRemotaDelegate() throws DelegateException {
        try {
            NormativaRemotaFacadeHome home = NormativaRemotaFacadeUtil.getHome();
            NormativaRemotaFacade remote = home.create();
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
