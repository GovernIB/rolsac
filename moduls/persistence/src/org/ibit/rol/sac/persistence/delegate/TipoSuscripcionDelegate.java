package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.util.TipoSuscripcionFacadeUtil;
import org.ibit.rol.sac.persistence.intf.TipoSuscripcionFacade;
import org.ibit.rol.sac.persistence.intf.TipoSuscripcionFacadeHome;
import org.ibit.rol.sac.model.Usuario;

/**
 * @deprecated Clase que únicamente se usa desde el back antiguo.
 * Business delegate para manipular Microsite.
 */
public class TipoSuscripcionDelegate implements StatelessDelegate {

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	private static final long serialVersionUID = 1L;

	/** @deprecated No se utiliza */
	public Long grabarTipoSuscripcion(TipoSuscripcion site) throws DelegateException {
		try {
			return getFacade().grabarTipoSuscripcion(site);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated Se utiliza únicamente desde el back antiguo */
	public TipoSuscripcion obtenerTipoSuscripcion(Long id) throws DelegateException {
		try {
			return getFacade().obtenerTipoSuscripcion(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated Se utiliza únicamente desde el back antiguo */
	public List listarTiposSuscripcion() throws DelegateException {
		try {
			return getFacade().listarTiposSuscripcion();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public List listarTiposSuscripcionFiltro(Usuario usu, Map param) throws DelegateException {
		try {
			return getFacade().listarTiposSuscripcionFiltro(usu, param);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public void borrarTipoSuscripcion(Long id) throws DelegateException {
		try {
			getFacade().borrarTipoSuscripcion(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public Hashtable getParametros() throws DelegateException {
		try {
			return getFacade().getParametros();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public void parametrosCons() throws DelegateException {
		try {
			getFacade().parametrosCons();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public int getPagina() throws DelegateException {
		try {
			return getFacade().getPagina();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public void setPagina(int pagina) throws DelegateException {
		try {
			getFacade().setPagina(pagina);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public void setOrderby(String orderby) throws DelegateException {
		try {
			getFacade().setOrderby(orderby);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public String getValorBD(String valor) throws DelegateException {
		try {
			return getFacade().getValorBD(valor);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/** @deprecated No se utiliza */
	public void setFiltro(String valor) throws DelegateException {
		try {
			getFacade().setFiltro(valor);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}    

	/** @deprecated No se utiliza */
	public String getUsuarioEJB() throws DelegateException {
		try {
			return getFacade().getUsuarioEJB();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}   


	/* ========================================================= */
			/* ======================== REFERENCIA AL FACADE  ========== */
			/* ========================================================= */

			private Handle facadeHandle;

	private TipoSuscripcionFacade getFacade() throws RemoteException {
		return (TipoSuscripcionFacade) facadeHandle.getEJBObject();
	}

	protected TipoSuscripcionDelegate() throws DelegateException {
		try {
			TipoSuscripcionFacadeHome home = TipoSuscripcionFacadeUtil.getHome();
			TipoSuscripcionFacade remote = home.create();
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



