package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.StatelessDelegate;
import org.ibit.rol.sac.persistence.intf.AdministracionRemotaFacade;
import org.ibit.rol.sac.persistence.intf.AdministracionRemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.AdministracionRemotaFacadeUtil;
import org.ibit.rol.sac.model.*;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

/**
 * Business delegate para manipular Publico Objetivo.
 */
public class AdministracionRemotaDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarAdministracionRemota(AdministracionRemota administracionRemota) throws DelegateException {
        try {
            return getFacade().grabarAdministracionRemota(administracionRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<AdministracionRemota> listarAdministracionRemota() throws DelegateException {
        try {
            return getFacade().listarAdministracionRemota();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public AdministracionRemota obtenerAdministracionRemota(Long id) throws DelegateException {
        try {
            return getFacade().obtenerAdministracionRemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarAdministracionRemota(Long id) throws DelegateException {
        try {
            getFacade().borrarAdministracionRemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Set<FichaRemota> listarFichasRemotas(Long id) throws DelegateException {
        try {
            return getFacade().listarFichasRemotas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Set<ProcedimientoRemoto> listarProcedimientosRemotos(Long id) throws DelegateException {
        try {
            return getFacade().listarProcedimientosRemotos(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Set<EdificioRemoto> listarEdificiosRemotos(Long id) throws DelegateException {
        try {
            return getFacade().listarEdificiosRemotos(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Set<TramiteRemoto> listarTramitesRemotos(Long id) throws DelegateException {
        try {
            return getFacade().listarTramitesRemotos(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public Set<UnidadAdministrativaRemota> listarUARemotas(Long id) throws DelegateException {
        try {
            return getFacade().listarUARemotas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public Set<NormativaExternaRemota> listarNormativasExternasRemotas(Long id) throws DelegateException {
        try {
            return getFacade().listarNormativasExternasRemotas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public boolean isEmpty(Long id) throws DelegateException {
    	try {
            return getFacade().isEmpty(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public boolean isEmpty(String idRemoto) throws DelegateException {
    	try {
    		return getFacade().isEmpty(idRemoto);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /**
	 * Obtiene el logop de una {@link AdministracionRemota}
	 */
    public Archivo obtenerLogop(Long id) throws DelegateException {
    	try {
    		return getFacade().obtenerLogop(id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /**
	 * Obtiene el logog de una {@link AdministracionRemota}
	 */
    public Archivo obtenerLogog(Long id) throws DelegateException {
    	try {
    		return getFacade().obtenerLogog(id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /**
     * Borra un Logop de una {@link AdministracionRemota} determinado.
     */
    public void borrarLogop(Long id) throws DelegateException {
    	try {
            getFacade().borrarLogop(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Borra un Logog de una {@link AdministracionRemota} determinado.
     */
    public void borrarLogog(Long id) throws DelegateException {
    	try {
            getFacade().borrarLogog(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private AdministracionRemotaFacade getFacade() throws RemoteException {
        return (AdministracionRemotaFacade) facadeHandle.getEJBObject();
    }

    protected AdministracionRemotaDelegate() throws DelegateException {
        try {
            AdministracionRemotaFacadeHome home = AdministracionRemotaFacadeUtil.getHome();
            AdministracionRemotaFacade remote = home.create();
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
