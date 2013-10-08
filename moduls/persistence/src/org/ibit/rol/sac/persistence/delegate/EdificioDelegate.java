package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.persistence.intf.EdificioFacade;
import org.ibit.rol.sac.persistence.intf.EdificioFacadeHome;
import org.ibit.rol.sac.persistence.util.EdificioFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular edificios.
 */
public class EdificioDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarEdificio(Edificio edificio) throws DelegateException {
        try {
            return getFacade().grabarEdificio(edificio);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Edificio obtenerEdificio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerEdificio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /**
     * @deprecated	No se utiliza
     * */
    @Deprecated
    public ResultadoBusqueda listarEdificios(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarEdificios(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    
    /**
     * @deprecated	Se utiliza desde el back antiguo
     * */
    @Deprecated
    public List listarEdificios() throws DelegateException {
        try {
            return getFacade().listarEdificios();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /**
     * @deprecated	Se utiliza desde el back antiguo
     * */
    @Deprecated
    public List buscarEdificios(Map parametros, Map traduccion) throws DelegateException {
        try {
            return getFacade().buscarEdificios(parametros, traduccion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ResultadoBusqueda buscarEdificios(Map parametros, Map traduccion, Long idUA, boolean uaFilles, boolean uaMeves, String pagina, String resultats) throws DelegateException {
        try {
            return getFacade().buscarEdificios(parametros, traduccion, idUA, uaFilles, uaMeves, pagina, resultats);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Archivo obtenerFotoPequenyaEdificio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFotoPequenyaEdificio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerFotoGrandeEdificio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFotoGrandeEdificio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerPlanoEdificio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerPlanoEdificio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarEdificio(Long id) throws DelegateException {
        try {
            getFacade().borrarEdificio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Set listarEdificiosUnidad(Long id) throws DelegateException {
        try {
            return getFacade().listarEdificiosUnidad(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void anyadirUnidad(Long unidad_id, Long edi_id) throws DelegateException {
        try {
            getFacade().anyadirUnidad(unidad_id, edi_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void eliminarUnidad(Long unidad_id, Long edi_id) throws DelegateException {
        try {
            getFacade().eliminarUnidad(unidad_id, edi_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    /**
     * @deprecated	Se utiliza desde el back antiguo
     * */
    @Deprecated
    public List buscarEdificiosConMultiidioma(Map parametros, Map traduccion) throws DelegateException {
        try {
            return getFacade().buscarEdificiosConMultiidioma(parametros, traduccion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
  
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private EdificioFacade getFacade() throws RemoteException {
        return (EdificioFacade) facadeHandle.getEJBObject();
    }

    protected EdificioDelegate() throws DelegateException {
        try {
            EdificioFacadeHome home = EdificioFacadeUtil.getHome();
            EdificioFacade remote = home.create();
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
