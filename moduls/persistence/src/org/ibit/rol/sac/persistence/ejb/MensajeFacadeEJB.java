package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.MensajeEmail;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ServicioMensaje;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.util.EmailUtils;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Session;

/**
 * SessionBean para operar con mensajes proc / serv.
 *
 * @ejb.bean name="sac/persistence/MensajeFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.MensajeFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="NotSupported"
 */
public abstract class MensajeFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}

	/**
	 * Marcar mensaje leido proc
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void marcarMensajeLeidoProc(final Long idMensaje, final String usuario) throws DelegateException {
		log.debug("Marcar mensaje leido proc ");

		Session session = null;
		try {
			session = getSession();

			final ProcedimientoMensaje mensaje = (ProcedimientoMensaje) session.load(ProcedimientoMensaje.class,
					idMensaje);
			Hibernate.initialize(mensaje);
			mensaje.setFechaLectura(new Date());
			mensaje.setUsuarioLectura(usuario);
			mensaje.setLeido(true);
			session.update(mensaje);
			session.flush();

		} catch (final Exception he) {
			throw new EJBException(he);

		} finally {

			if (session != null) {
				close(session);
			}
		}
	}

	/**
	 * Marcar mensaje leido proc
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void marcarMensajeLeidoServ(final Long idMensaje, final String usuario) throws DelegateException {
		log.debug("Marcar mensaje leido serv ");

		Session session = null;
		try {
			session = getSession();

			final ServicioMensaje mensaje = (ServicioMensaje) session.load(ServicioMensaje.class, idMensaje);
			Hibernate.initialize(mensaje);
			mensaje.setFechaLectura(new Date());
			mensaje.setUsuarioLectura(usuario);
			mensaje.setLeido(true);
			session.update(mensaje);
			session.flush();
		} catch (final Exception he) {
			throw new EJBException(he);

		} finally {

			if (session != null) {
				close(session);
			}
		}
	}

	/**
	 * Marcar mensaje leido proc
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void enviarMensajeProc(final String texto, final Long idEntidad, final String usuario, final boolean gestor,
			final MensajeEmail mensajeEmail) throws DelegateException {
		log.debug("Enviar mensaje proc ");

		Session session = null;
		try {
			session = getSession();

			final ProcedimientoMensaje mensaje = new ProcedimientoMensaje();
			mensaje.setFechaCreacion(new Date());
			mensaje.setGestor(gestor);
			mensaje.setIdProcedimiento(idEntidad);
			mensaje.setLeido(false);
			mensaje.setTexto(texto);
			mensaje.setUsuario(usuario);

			session.save(mensaje);
			session.flush();

			if (mensajeEmail != null) {
				session.save(mensajeEmail);
				session.flush();
			}

		} catch (final Exception he) {
			throw new EJBException(he);

		} finally {

			if (session != null) {
				close(session);
			}
		}
	}

	/**
	 * Marcar mensaje leido proc
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void enviarMensajeServ(final String texto, final Long idEntidad, final String usuario, final boolean gestor,
			final MensajeEmail mensajeEmail) throws DelegateException {
		log.debug("Enviar mensaje serv ");

		Session session = null;
		try {
			session = getSession();

			final ServicioMensaje mensaje = new ServicioMensaje();
			mensaje.setFechaCreacion(new Date());
			mensaje.setGestor(gestor);
			mensaje.setIdServicio(idEntidad);
			mensaje.setLeido(false);
			mensaje.setTexto(texto);
			mensaje.setUsuario(usuario);

			session.save(mensaje);
			session.flush();

			if (mensajeEmail != null) {
				session.save(mensajeEmail);
				session.flush();
			}

		} catch (final Exception he) {
			throw new EJBException(he);

		} finally {

			if (session != null) {
				close(session);
			}
		}
	}

	/**
	 * Lista mensajes proc
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<ProcedimientoMensaje> getMensajesProcedimiento(final Long idProcedimiento) throws DelegateException {
		final Session session = getSession();
		try {
			final StringBuffer hql = new StringBuffer();

			hql.append(" select mensaje from ProcedimientoMensaje mensaje");
			hql.append(" where mensaje.idProcedimiento = " + idProcedimiento);
			hql.append(" order by mensaje.fechaCreacion desc");
			return session.createQuery(hql.toString()).list();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Lista mensajes serv
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public List<ServicioMensaje> getMensajesServicio(final Long idServicio) throws DelegateException {
		final Session session = getSession();
		try {
			final StringBuffer hql = new StringBuffer();

			hql.append(" select mensaje from ServicioMensaje mensaje");
			hql.append(" where mensaje.idServicio = " + idServicio);
			hql.append(" order by mensaje.fechaCreacion desc");
			return session.createQuery(hql.toString()).list();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Envia emails pendientes
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void enviarEmailsPendientes() throws DelegateException {
		final Session session = getSession();
		try {
			final StringBuffer hql = new StringBuffer();

			hql.append(" select  mensaje.id from MensajeEmail mensaje");
			hql.append(" where mensaje.enviado = false ");
			hql.append(" order by mensaje.fechaCreacion desc");
			final List<Long> idMensajes = session.createQuery(hql.toString()).list();

			if (idMensajes != null) {
				EmailUtils emailUtils = null;
				emailUtils = new EmailUtils("java:/es.caib.rolsac.mail");

				for (final Long idMensaje : idMensajes) {
					final MensajeEmail mensaje = (MensajeEmail) session.load(MensajeEmail.class, idMensaje);
					try {
						emailUtils.postMail(mensaje.getTitulo(), mensaje.getContenido(), null, mensaje.getTo());
						mensaje.setError("");
						mensaje.setEnviado(true);
						mensaje.setFechaEnviado(new Date());
						session.update(mensaje);
						session.flush();

					} catch (final Exception e) {
						log.error(e);
						String error = e.getLocalizedMessage();
						if (error != null && error.length() > 500) {
							error = error.substring(0, 499);
						}
						mensaje.setError(error);
						session.update(mensaje);
						session.flush();
					}
				}
			}

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Limpia emails antiguos
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void limpiarEmails() throws DelegateException {
		final Session session = getSession();
		try {

			final String sql = "from MensajeEmail as mens where mens.fechaCreacion <= SYSDATE -7";

			session.delete(sql);
			session.flush();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene el ultimo gestor que ha escrito un mensaje.
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public String obtenerUltimoGestorProc(final Long idEntidad) throws DelegateException {
		log.debug("Marcar mensaje leido proc ");

		Session session = null;
		try {
			session = getSession();

			final StringBuffer hql = new StringBuffer();

			hql.append(" select max(mensaje.id) from ProcedimientoMensaje mensaje");
			hql.append(" where mensaje.idProcedimiento = " + idEntidad);
			hql.append("  and mensaje.gestor = 0 ");
			final Long idMensaje = (Long) session.createQuery(hql.toString()).uniqueResult();
			String gestor = null;
			if (idMensaje != null) {
				final ProcedimientoMensaje mensaje = (ProcedimientoMensaje) session.load(ProcedimientoMensaje.class,
						idMensaje);
				gestor = mensaje.getUsuario();
			}
			return gestor;

		} catch (final Exception he) {
			throw new EJBException(he);

		} finally {

			if (session != null) {
				close(session);
			}
		}
	}

	/**
	 * Obtiene el ultimo gestor que ha escrito un mensaje.
	 *
	 * @throws DelegateException
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public String obtenerUltimoGestorServ(final Long idEntidad) throws DelegateException {
		log.debug("Marcar mensaje leido serv ");

		Session session = null;
		try {
			session = getSession();

			final StringBuffer hql = new StringBuffer();

			hql.append(" select max(mensaje.id) from ServicioMensaje mensaje");
			hql.append(" where mensaje.idServicio = " + idEntidad);
			hql.append("  and mensaje.gestor = 0 ");
			final Long idMensaje = (Long) session.createQuery(hql.toString()).uniqueResult();
			String gestor = null;
			if (idMensaje != null) {
				final ServicioMensaje mensaje = (ServicioMensaje) session.load(ServicioMensaje.class, idMensaje);
				gestor = mensaje.getUsuario();
			}
			return gestor;
		} catch (final Exception he) {
			throw new EJBException(he);

		} finally {

			if (session != null) {
				close(session);
			}
		}
	}
}
