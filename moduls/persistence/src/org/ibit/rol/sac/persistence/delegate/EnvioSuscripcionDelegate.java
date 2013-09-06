package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.persistence.intf.EnvioSuscripcionFacade;
import org.ibit.rol.sac.persistence.intf.EnvioSuscripcionFacadeHome;
import org.ibit.rol.sac.persistence.util.EnvioSuscripcionFacadeUtil;


import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * @deprecated Usado sólo desde el back antiguo
 * Business delegate para manipular materias.
 */
public class EnvioSuscripcionDelegate implements StatelessDelegate {

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/** @deprecated No se usa. */
	public void init() throws DelegateException {
		try {
			getFacade().init();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}	


	/** @deprecated Usado desde el back antiguo */
	public void init(Long id) throws DelegateException {
		try {
			getFacade().init(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public List listarTrabajos() throws DelegateException {
		try {
			return getFacade().listarTrabajos();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public Long grabarEnvio(EnvioSuscripcion envio)  throws DelegateException {
		try {
			return getFacade().grabarEnvio(envio);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public void actualizaComoEnviado(Long id)  throws DelegateException {
		try {
			getFacade().actualizaComoEnviado(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public void anyadirAllGrupos(List grupos, Long id) throws DelegateException {
		try {
			getFacade().anyadirAllGrupos(grupos, id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}

	}


	/** @deprecated Usado desde el back antiguo */
	public void eliminarGrupos(String[] grupos, Long id) throws DelegateException {
		try {
			getFacade().eliminarGrupos(grupos, id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}

	}


	/** @deprecated Usado desde el back antiguo */
	public boolean checkTipo(Long idTipo, Long id) throws DelegateException {
		try {
			return getFacade().checkTipo(idTipo,id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public List listarEnvios() throws DelegateException {
		try {
			return getFacade().listarEnvios();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public List listarPendientes() throws DelegateException {
		try {
			return getFacade().listarPendientes();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public EnvioSuscripcion obtenerEnvio(Long id) throws DelegateException {
		try {
			return getFacade().obtenerEnvio(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public void borrarEnvio(Long id) throws DelegateException {
		try {
			getFacade().borrarEnvio(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public Hashtable getParametros() throws DelegateException {
		try {
			return getFacade().getParametros();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public void parametrosCons() throws DelegateException {
		try {
			getFacade().parametrosCons();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public int getPagina() throws DelegateException {
		try {
			return getFacade().getPagina();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public void setPagina(int pagina) throws DelegateException {
		try {
			getFacade().setPagina(pagina);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public void setOrderby(String orderby) throws DelegateException {
		try {
			getFacade().setOrderby(orderby);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public void setOrderby2(String orderby) throws DelegateException {
		try {
			getFacade().setOrderby2(orderby);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}   


	/** @deprecated No se usa */
	public String getValorBD(String valor) throws DelegateException {
		try {
			return getFacade().getValorBD(valor);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo */
	public void setFiltro(String valor) throws DelegateException {
		try {
			getFacade().setFiltro(valor);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public String getWhere() throws DelegateException {
		try {
			return getFacade().getWhere();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public void setWhere(String valor) throws DelegateException {
		try {
			getFacade().setWhere(valor);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}   


	/** @deprecated No se usa */
	public int getTampagina() throws DelegateException {
		try {
			return getFacade().getTampagina();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated No se usa */
	public void setTampagina(int tampagina) throws DelegateException {
		try {
			getFacade().setTampagina(tampagina);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	} 




	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private EnvioSuscripcionFacade getFacade() throws RemoteException {
		return (EnvioSuscripcionFacade) facadeHandle.getEJBObject();
	}

	protected EnvioSuscripcionDelegate() throws DelegateException {
		try {
			EnvioSuscripcionFacadeHome home = EnvioSuscripcionFacadeUtil.getHome();
			EnvioSuscripcionFacade remote = home.create();
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
