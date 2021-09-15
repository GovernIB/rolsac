package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.MensajeEmail;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ServicioMensaje;

public class MensajeDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -437746444276565342L;

	MensajeDelegateI impl;

	public MensajeDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final MensajeDelegateI impl) {
		this.impl = impl;
	}

	public void marcarMensajeLeidoProc(final Long idMensaje, final String usuario) throws DelegateException {
		impl.marcarMensajeLeidoProc(idMensaje, usuario);
	}

	public void marcarMensajeLeidoServ(final Long idMensaje, final String usuario) throws DelegateException {
		impl.marcarMensajeLeidoServ(idMensaje, usuario);
	}

	public void enviarMensajeProc(final String texto, final Long idEntidad, final String usuario, final boolean gestor,
			final MensajeEmail mensajeEmail) throws DelegateException {
		impl.enviarMensajeProc(texto, idEntidad, usuario, gestor, mensajeEmail);
	}

	public void enviarMensajeServ(final String texto, final Long idEntidad, final String usuario, final boolean gestor,
			final MensajeEmail mensajeEmail) throws DelegateException {
		impl.enviarMensajeServ(texto, idEntidad, usuario, gestor, mensajeEmail);
	}

	public List<ProcedimientoMensaje> getMensajesProcedimiento(final Long idProcedimiento) throws DelegateException {
		return impl.getMensajesProcedimiento(idProcedimiento);
	}

	public List<ServicioMensaje> getMensajesServicio(final Long idServicio) throws DelegateException {
		return impl.getMensajesServicio(idServicio);
	}

	public void enviarEmailsPendientes() throws DelegateException {
		impl.enviarEmailsPendientes();
	}

	public void limpiarEmails() throws DelegateException {
		impl.limpiarEmails();
	}

	public String obtenerUltimoGestorProc(final Long idEntidad) throws DelegateException {
		return impl.obtenerUltimoGestorProc(idEntidad);
	}

	public String obtenerUltimoGestorServ(final Long idEntidad) throws DelegateException {
		return impl.obtenerUltimoGestorServ(idEntidad);
	}
}
