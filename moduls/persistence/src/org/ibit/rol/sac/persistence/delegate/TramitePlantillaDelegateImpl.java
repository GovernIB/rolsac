package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.TramitePlantilla;
import org.ibit.rol.sac.persistence.intf.TramitePlantillaFacade;
import org.ibit.rol.sac.persistence.intf.TramitePlantillaFacadeHome;
import org.ibit.rol.sac.persistence.util.TramitePlantillaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular silencio adm.
 */
public class TramitePlantillaDelegateImpl implements StatelessDelegate, TramitePlantillaDelegateI {
	private static final long serialVersionUID = -5255731439556042563L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegateI#
	 * grabarTramitePlantilla( org.ibit.rol.sac.model.TramitePlantilla)
	 */
	@Override
	public Long grabarTramitePlantilla(final TramitePlantilla tramitePlantilla, final boolean editar)
			throws DelegateException {
		try {
			return getFacade().grabarTramitePlantilla(tramitePlantilla, editar);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegateI#
	 * listarTramitePlantilla()
	 */
	@Override
	public ResultadoBusqueda listarTramitePlantilla(final int pagina, final int resultats, final String idioma)
			throws DelegateException {
		try {
			return getFacade().listarTramitePlantilla(pagina, resultats, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegateI#
	 * listarTramitePlantilla( )
	 */
	@Override
	public List<TramitePlantilla> listarTramitePlantilla() throws DelegateException {
		try {
			return getFacade().listarTramitePlantilla();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegateI#
	 * obtenerTramitePlantilla (java.lang.String)
	 */
	@Override
	public TramitePlantilla obtenerTramitePlantilla(final Long codigo) throws DelegateException {
		try {
			return getFacade().obtenerTramitePlantilla(codigo);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegateI#
	 * borrarTramitePlantilla( java.lang.String)
	 */
	@Override
	public void borrarTramitePlantilla(final Long codigo) throws DelegateException {
		try {
			getFacade().borrarTramitePlantilla(codigo);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private TramitePlantillaFacade getFacade() throws RemoteException {
		return (TramitePlantillaFacade) facadeHandle.getEJBObject();
	}

	TramitePlantillaDelegateImpl() throws DelegateException {
		try {
			final TramitePlantillaFacadeHome home = TramitePlantillaFacadeUtil.getHome();
			final TramitePlantillaFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (final NamingException e) {
			throw new DelegateException(e);
		} catch (final CreateException e) {
			throw new DelegateException(e);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public int cuantosProcedimientosConTramitePlantilla(final Long id) throws DelegateException {
		try {
			return getFacade().cuantosProcedimientosConTramitePlantilla(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
