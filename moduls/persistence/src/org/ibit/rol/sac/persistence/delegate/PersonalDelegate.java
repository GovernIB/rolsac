package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
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

	public Long grabarPersonal(final Personal personal, final Long idUA) throws DelegateException {
		try {
			return getFacade().grabarPersonal(personal, idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public Personal obtenerPersonal(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerPersonal(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda buscadorListarPersonal(final Map parametros, final int pagina, final int resultados,
			final boolean uaFilles, final boolean uaMeves, final boolean soloIds) throws DelegateException {
		try {
			return getFacade().buscadorListarPersonal(parametros, pagina, resultados, uaFilles, uaMeves, soloIds);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * @deprecated Se usa desde el back antiguo.
	 */
	@Deprecated
	public Set listarPersonalUA(final Long unidadAdmin_id) throws DelegateException {
		try {
			return getFacade().listarPersonalUA(unidadAdmin_id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public void borrarPersonal(final Long id) throws DelegateException {
		try {
			getFacade().borrarPersonal(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda buscadorListarPersonal(final Personal personal, final Long idUA, final boolean uaHijas,
			final boolean uaPropias, final PaginacionCriteria paginacion) throws DelegateException {
		try {
			return getFacade().buscadorListarPersonal(personal, idUA, uaHijas, uaPropias, paginacion);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda consultaPersonal(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaPersonal(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private PersonalFacade getFacade() throws RemoteException {
		return (PersonalFacade) facadeHandle.getEJBObject();
	}

	protected PersonalDelegate() throws DelegateException {
		try {
			final PersonalFacadeHome home = PersonalFacadeUtil.getHome();
			final PersonalFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (final NamingException e) {
			throw new DelegateException(e);
		} catch (final CreateException e) {
			throw new DelegateException(e);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
