package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.LopdLegitimacion;
import org.ibit.rol.sac.persistence.intf.LopdLegitimacionFacade;
import org.ibit.rol.sac.persistence.intf.LopdLegitimacionFacadeHome;
import org.ibit.rol.sac.persistence.util.LopdLegitimacionFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate pera consultar sia pendiente..
 */
public class LopdLegitimacionDelegate implements StatelessDelegate {
	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;

	public ResultadoBusqueda getLopdLegitimacion(final int pagina, final int cuantos, final String orden,
			final String ordenAsc) throws DelegateException {
		try {
			return getFacade().getLopdLegitimacion(pagina, cuantos, orden, ordenAsc);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public void grabarLopdLegitimacion(final LopdLegitimacion lopdLeg) throws DelegateException {
		try {
			getFacade().grabarLopdLegitimacion(lopdLeg);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public LopdLegitimacion obtenerLopdLegitimacion(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerLopdLegitimacion(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public void borrarLopdLegitimacion(final Long id) throws DelegateException {
		try {
			getFacade().borrarLopdLegitimacion(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private LopdLegitimacionFacade getFacade() throws RemoteException {
		return (LopdLegitimacionFacade) facadeHandle.getEJBObject();
	}

	protected LopdLegitimacionDelegate() throws DelegateException {
		try {
			final LopdLegitimacionFacadeHome home = LopdLegitimacionFacadeUtil.getHome();
			final LopdLegitimacionFacade remote = home.create();
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
