package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.MensajeEmail;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ServicioMensaje;

/**
 * Interfaz Mensaje Delegate.
 *
 * @author slromero
 *
 */
public interface MensajeDelegateI {

	public void marcarMensajeLeidoProc(final Long idMensaje, final String usuario) throws DelegateException;

	public void marcarMensajeLeidoServ(final Long idMensaje, final String usuario) throws DelegateException;

	public void enviarMensajeProc(final String texto, final Long idProc, final String usuario, boolean gestor,
			MensajeEmail mensajeEmail) throws DelegateException;

	public void enviarMensajeServ(final String texto, final Long idServ, final String usuario, boolean gestor,
			MensajeEmail mensajeEmail) throws DelegateException;

	public List<ProcedimientoMensaje> getMensajesProcedimiento(Long idProcedimiento) throws DelegateException;

	public List<ServicioMensaje> getMensajesServicio(Long idServicio) throws DelegateException;

	public void enviarEmailsPendientes() throws DelegateException;

	public void limpiarEmails() throws DelegateException;

	public String obtenerUltimoGestorProc(Long idEntidad) throws DelegateException;

	public String obtenerUltimoGestorServ(Long idEntidad) throws DelegateException;
}
