package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.persistence.intf.SilencioAdmFacade;
import org.ibit.rol.sac.persistence.intf.SilencioAdmFacadeHome;
import org.ibit.rol.sac.persistence.util.SilencioAdmFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular silencio adm.
 */
public class SilencioAdmDelegateImpl implements StatelessDelegate, SilencioAdmDelegateI {
	private static final long serialVersionUID = -5255731439556042563L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#grabarSilencioAdm(
	 * org.ibit.rol.sac.model.SilencioAdm)
	 */
	@Override
	public Long grabarSilencioAdm(final SilencioAdm silencio, final boolean editar) throws DelegateException {
		try {
			return getFacade().grabarSilencioAdm(silencio, editar);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda listarSilencioAdm(final int pagina, final int resultats, final String idioma)
			throws DelegateException {
		try {
			return getFacade().listarSilencioAdm(pagina, resultats, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#listarSilencioAdm(
	 * )
	 */
	@Override
	public List listarSilencioAdm() throws DelegateException {
		try {
			return getFacade().listarSilencioAdm();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#obtenerSilencioAdm
	 * (java.lang.String)
	 */
	@Override
	public SilencioAdm obtenerSilencioAdm(final Long codigo) throws DelegateException {
		try {
			return getFacade().obtenerSilencioAdm(codigo);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegateI#borrarSilencioAdm(
	 * java.lang.String)
	 */
	@Override
	public void borrarSilencioAdm(final Long codigo) throws DelegateException {
		try {
			getFacade().borrarSilencioAdm(codigo);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private SilencioAdmFacade getFacade() throws RemoteException {
		return (SilencioAdmFacade) facadeHandle.getEJBObject();
	}

	SilencioAdmDelegateImpl() throws DelegateException {
		try {
			final SilencioAdmFacadeHome home = SilencioAdmFacadeUtil.getHome();
			final SilencioAdmFacade remote = home.create();
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
	public int cuantosProcedimientosConSilencio(final Long id) throws DelegateException {
		try {
			return getFacade().cuantosProcedimientosConSilencio(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
