package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ServicioMensaje;
import org.ibit.rol.sac.persistence.intf.MensajeFacade;
import org.ibit.rol.sac.persistence.intf.MensajeFacadeHome;
import org.ibit.rol.sac.persistence.util.MensajeFacadeUtil;

/**
 * Business delegate pera enviar Mensaje.
 */
public class MensajeDelegateImpl extends MensajeDelegate implements StatelessDelegate, MensajeDelegateI {

	/** Serial Version UID. **/
	private static final long serialVersionUID = 1L;

	private Handle facadeHandle;

	private MensajeFacade getFacade() throws RemoteException {
		return (MensajeFacade) facadeHandle.getEJBObject();
	}

	protected MensajeDelegateImpl() throws DelegateException {
		try {
			final MensajeFacadeHome home = MensajeFacadeUtil.getHome();
			final MensajeFacade remote = home.create();
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
	public void marcarMensajeLeidoProc(final Long idMensaje, final String usuario) throws DelegateException {
		try {
			getFacade().marcarMensajeLeidoProc(idMensaje, usuario);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void marcarMensajeLeidoServ(final Long idMensaje, final String usuario) throws DelegateException {
		try {
			getFacade().marcarMensajeLeidoServ(idMensaje, usuario);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void enviarMensajeProc(final String texto, final Long idEntidad, final String usuario, final boolean gestor)
			throws DelegateException {
		try {
			getFacade().enviarMensajeProc(texto, idEntidad, usuario, gestor);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void enviarMensajeServ(final String texto, final Long idEntidad, final String usuario, final boolean gestor)
			throws DelegateException {
		try {
			getFacade().enviarMensajeServ(texto, idEntidad, usuario, gestor);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<ProcedimientoMensaje> getMensajesProcedimiento(final Long idProcedimiento) throws DelegateException {
		try {
			return getFacade().getMensajesProcedimiento(idProcedimiento);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<ServicioMensaje> getMensajesServicio(final Long idServicio) throws DelegateException {
		try {
			return getFacade().getMensajesServicio(idServicio);
		} catch (final Exception e) {
			throw new DelegateException(e);
		}
	}

}
