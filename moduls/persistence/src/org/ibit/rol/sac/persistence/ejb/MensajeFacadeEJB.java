package org.ibit.rol.sac.persistence.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ServicioMensaje;
import org.ibit.rol.sac.persistence.delegate.DelegateException;

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
	public void enviarMensajeProc(final String texto, final Long idEntidad, final String usuario, final boolean gestor)
			throws DelegateException {
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
	public void enviarMensajeServ(final String texto, final Long idEntidad, final String usuario, final boolean gestor)
			throws DelegateException {
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
			hql.append(" order by mensaje.fechaCreacion asc");
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
			hql.append(" order by mensaje.fechaCreacion asc");
			return session.createQuery(hql.toString()).list();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

}
