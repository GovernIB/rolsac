package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.persistence.intf.TipoNormativaFacade;
import org.ibit.rol.sac.persistence.intf.TipoNormativaFacadeHome;
import org.ibit.rol.sac.persistence.util.TipoNormativaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular tipos normativas.
 */
public class TipoNormativaDelegate implements StatelessDelegate {
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	private static final long serialVersionUID = 986051651952144005L;

	public Long grabarTipoNormativa(Tipo tiponorm) throws DelegateException {
        try {
            return getFacade().grabarTipoNormativa(tiponorm);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda listarTiposNormativas(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarTiposNormativas(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public List listarTiposNormativas() throws DelegateException {
        try {
            return getFacade().listarTiposNormativas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Tipo obtenerTipoNormativa(Long id) throws DelegateException {
        try {
            return getFacade().obtenerTipoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarTipoNormativa(Long id) throws DelegateException {
        try {
            getFacade().borrarTipoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private TipoNormativaFacade getFacade() throws RemoteException {
        return (TipoNormativaFacade) facadeHandle.getEJBObject();
    }

    protected TipoNormativaDelegate() throws DelegateException {
        try {
            TipoNormativaFacadeHome home = TipoNormativaFacadeUtil.getHome();
            TipoNormativaFacade remote = home.create();
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
