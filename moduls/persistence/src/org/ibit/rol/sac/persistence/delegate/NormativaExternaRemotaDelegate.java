package org.ibit.rol.sac.persistence.delegate;


import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.intf.NormativaExternaRemotaFacade;
import org.ibit.rol.sac.persistence.intf.NormativaExternaRemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.NormativaExternaRemotaFacadeUtil;
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
public class NormativaExternaRemotaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public Long grabarNormativaExternaRemota(final NormativaExternaRemota normativaRemota) throws DelegateException {
		try {
			return getFacade().grabarNormativaExternaRemota(normativaRemota);
		} catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
    public NormativaExternaRemota obtenerNormativaExternaRemota(Long idExterno,Long idUaRemota) throws DelegateException {
        try {
            return getFacade().obtenerNormativaExternaRemota(idExterno, idUaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public NormativaExternaRemota obtenerNormativaExternaRemota(final String idRemoto, final Long idExtNorm) throws DelegateException {
         try {
            return getFacade().obtenerNormativaExternaRemota(idRemoto, idExtNorm);
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
    
    private NormativaExternaRemotaFacade getFacade() throws RemoteException {
        return (NormativaExternaRemotaFacade) facadeHandle.getEJBObject();
    }
    
    protected NormativaExternaRemotaDelegate() throws DelegateException {
        try {
            NormativaExternaRemotaFacadeHome home = NormativaExternaRemotaFacadeUtil.getHome();
            NormativaExternaRemotaFacade remote = home.create();
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
