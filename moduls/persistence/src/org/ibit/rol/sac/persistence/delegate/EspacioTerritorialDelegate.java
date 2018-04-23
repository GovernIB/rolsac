package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.EspacioTerritorialFacade;
import org.ibit.rol.sac.persistence.intf.EspacioTerritorialFacadeHome;
import org.ibit.rol.sac.persistence.util.EspacioTerritorialFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 03-jul-2007
 * Time: 11:57:05
 * To change this template use File | Settings | File Templates.
 */
public class EspacioTerritorialDelegate  implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public void borrarEspacioTerritorial(Long id) throws DelegateException {
		try {
            getFacade().borrarEspacioTerritorial(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public List<EspacioTerritorial> listarAntecesoresEspacioTerritorial(Long id) throws DelegateException{
		try {
            return getFacade().listarAntecesoresEspacioTerritorial(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public List<EspacioTerritorial> listarEspacioTerritorialesRaiz() throws DelegateException {
		try {
            return getFacade().listarEspacioTerritorialesRaiz();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public Collection<EspacioTerritorial> listarHijosEspacioTerritorial(Long id) throws DelegateException {
		try {
            return getFacade().listarHijosEspacioTerritorial(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
    public EspacioTerritorial obtenerEspacioTerritorial(Long id) throws DelegateException {
        try {
            return getFacade().obtenerEspacioTerritorial(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ResultadoBusqueda listarEspaciosTerritoriales(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarEspaciosTerritoriales(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    @SuppressWarnings("unchecked")
    public List<EspacioTerritorial> listarEspaciosTerritoriales() throws DelegateException {
        try {
            return getFacade().listarEspaciosTerritoriales();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Archivo obtenerMapaEspacio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerMapaEspacio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Archivo obtenerLogoEspacio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerLogoEspacio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void grabarEspacioTerritorial(EspacioTerritorial espai, Long idPadre) throws DelegateException {
    	try {
    		if (espai.getId() != null) {
    			getFacade().actualizarEspacioTerritorial(espai, idPadre);
    		} else {
    			getFacade().crearEspacioTerritorial(espai, idPadre);
    		}
    		
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private EspacioTerritorialFacade getFacade() throws RemoteException {
        return (EspacioTerritorialFacade) facadeHandle.getEJBObject();
    }
    
    protected EspacioTerritorialDelegate() throws DelegateException {
        try {
            EspacioTerritorialFacadeHome home = EspacioTerritorialFacadeUtil.getHome();
            EspacioTerritorialFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public ResultadoBusqueda consultaEspaciosTerritoriales(FiltroGenerico filtro) throws DelegateException{
        try {
            return getFacade().consultaEspaciosTerritoriales(filtro);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
}
