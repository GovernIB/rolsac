package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

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

	public void enviarMensajeProc(final String texto, final Long idProc, final String usuario, boolean gestor)
			throws DelegateException;

	public void enviarMensajeServ(final String texto, final Long idServ, final String usuario, boolean gestor)
			throws DelegateException;

	public List<ProcedimientoMensaje> getMensajesProcedimiento(Long idProcedimiento) throws DelegateException;

	public List<ServicioMensaje> getMensajesServicio(Long idServicio) throws DelegateException;
}
