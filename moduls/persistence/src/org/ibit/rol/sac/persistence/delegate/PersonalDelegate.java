package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.persistence.intf.PersonalFacade;
import org.ibit.rol.sac.persistence.intf.PersonalFacadeHome;
import org.ibit.rol.sac.persistence.util.PersonalFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Personal.
 */
public class PersonalDelegate implements StatelessDelegate {

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	public Long grabarPersonal(Personal personal, Long idUA) throws DelegateException {
		try {
			return getFacade().grabarPersonal(personal, idUA);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public Personal obtenerPersonal(Long id) throws DelegateException {
		try {
			return getFacade().obtenerPersonal(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda buscadorListarPersonal(Map parametros, int pagina, int resultados, boolean uaFilles, boolean uaMeves) throws DelegateException {
		try {
			return getFacade().buscadorListarPersonal(parametros, pagina, resultados, uaFilles, uaMeves);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * @deprecated	Se usa desde el back antiguo.
	 * */
	public Set listarPersonalUA(Long unidadAdmin_id) throws DelegateException {
		try {
			return getFacade().listarPersonalUA(unidadAdmin_id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public void borrarPersonal(Long id) throws DelegateException {
		try {
			getFacade().borrarPersonal(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda buscadorListarPersonal(Personal personal, Long idUA, boolean uaHijas, boolean uaPropias, PaginacionCriteria paginacion) throws DelegateException {
		try {
			return getFacade().buscadorListarPersonal(personal, idUA, uaHijas, uaPropias, paginacion);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private PersonalFacade getFacade() throws RemoteException {
		return (PersonalFacade) facadeHandle.getEJBObject();
	}

	protected PersonalDelegate() throws DelegateException {
		try {
			PersonalFacadeHome home = PersonalFacadeUtil.getHome();
			PersonalFacade remote = home.create();
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
