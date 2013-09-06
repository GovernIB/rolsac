package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.intf.FormularioFacade;
import org.ibit.rol.sac.persistence.intf.FormularioFacadeHome;
import org.ibit.rol.sac.persistence.util.FormularioFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * @deprecated	Ésta clase se usa únicamente desde el back antiguo
 * Business delegate para manipular Formularios.
 */
public class FormularioDelegate implements StatelessDelegate {
	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */


	/** @deprecated Usado desde el back antiguo */
	public Long grabarFormulario(Formulario formulario) throws DelegateException {
		try {
			return getFacade().grabarFormulario(formulario);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo*/
	public Formulario obtenerFormulario(Long id) throws DelegateException {
		try {
			return getFacade().obtenerFormulario(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo*/
	public Archivo obtenerArchivoFormulario(Long id) throws DelegateException {
		try {
			return getFacade().obtenerArchivoFormulario(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo*/
	public Archivo obtenerManualFormulario(Long id) throws DelegateException {
		try {
			return getFacade().obtenerManualFormulario(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}


	/** @deprecated Usado desde el back antiguo*/
	public void borrarFormulario(Long id) throws DelegateException {
		try {
			getFacade().borrarFormulario(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private FormularioFacade getFacade() throws RemoteException {
		return (FormularioFacade) facadeHandle.getEJBObject();
	}

	protected FormularioDelegate() throws DelegateException {
		try {
			FormularioFacadeHome home = FormularioFacadeUtil.getHome();
			FormularioFacade remote = home.create();
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
