package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaResumen;
import org.ibit.rol.sac.model.FichaResumenUA;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;
import org.ibit.rol.sac.persistence.util.Cadenas;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.IndexData;
import es.caib.solr.api.model.MultilangLiteral;
import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import es.caib.solr.api.model.types.EnumIdiomas;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

/**
 * SessionBean para mantener y consultar Unidades Administrativas.
 *
 * @ejb.bean name="sac/persistence/UnidadAdministrativaFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.UnidadAdministrativaFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
public abstract class UnidadAdministrativaFacadeEJB extends HibernateEJB implements UnidadAdministrativaDelegateI {

	private static final long serialVersionUID = 6954366130820517158L;

	/** Nom pel govern de les illes a la taula d'unitats orgàniques */
	public static final String NOM_GOVERN_ILLES = "Govern de les Illes Balears";

	/** ID comodín de unidad administrativa */
	public static final String EMPTY_ID = "-1";

	/**
	 * Obtiene referencia al ejb de control de Acceso.
	 *
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}

	/**
	 * Crea una Unidad Administrativa raíz.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system}"
	 *
	 * @param unidad
	 *            Indica la unidad administrativa a crear.
	 *
	 * @return Devuelve el identificador de la unidad administrativa.
	 * @throws DelegateException
	 */
	@Override
	public Long crearUnidadAdministrativaRaiz(final UnidadAdministrativa unidad) throws DelegateException {

		final Session session = getSession();

		try {

			final int orden = numUnidadesRaiz(session);
			unidad.setOrden(orden);
			session.save(unidad);
			addOperacion(session, unidad, Auditoria.INSERTAR);
			session.flush();
			Actualizador.actualizar(unidad);

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, unidad.getId(), false);
			// SIA
			final List<Long> listIdProcedimientos = DelegateUtil.getProcedimientoDelegate()
					.listarProcedimientosOrganoResolutori(unidad.getId());
			for (final Long idProcedimiento : listIdProcedimientos) {
				try {
					SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO,
							idProcedimiento, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);
				} catch (final Exception ex) {
					log.error("Error mirando si es indexable SIA. UA:" + unidad.getId() + " proc:" + idProcedimiento,
							ex);
				}
			}

			return unidad.getId();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Crea una Unidad Administrativa
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 *
	 * @param unidad
	 *            Indica la unidad administrativa a crear.
	 *
	 * @param idPadre
	 *            Identificador de la unidad adminsitrativa padre.
	 *
	 * @return Devuelve el identificador de la unidad administrativa padre.
	 * @throws DelegateException
	 */
	@Override
	public Long crearUnidadAdministrativa(final UnidadAdministrativa unidad, final Long idPadre)
			throws DelegateException {

		final Session session = getSession();

		try {

			if (!getAccesoManager().tieneAccesoUnidad(idPadre, true)) {

				throw new SecurityException("No tiene acceso a la unidad");

			}

			final UnidadAdministrativa padre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idPadre);
			padre.addHijo(unidad);
			session.save(unidad);
			addOperacion(session, unidad, Auditoria.INSERTAR);
			session.flush();
			Actualizador.actualizar(unidad);

			// SOLR Indexar unidad administrativa
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, unidad.getId(), false);
			// SIA
			final List<Long> listIdProcedimientos = DelegateUtil.getProcedimientoDelegate()
					.listarProcedimientosOrganoResolutori(unidad.getId());
			for (final Long idProcedimiento : listIdProcedimientos) {
				try {
					SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO,
							idProcedimiento, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);

				} catch (final Exception ex) {
					log.error("Error mirando si es indexable SIA. UA:" + unidad.getId() + " proc:" + idProcedimiento,
							ex);
				}
			}
			return unidad.getId();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Actualiza una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param unidad
	 *            Indica la unidad administrativa a actualizar.
	 * @param idPadre
	 *            Identificador de la unidad administrativa.
	 */
	@Override
	public void actualizarUnidadAdministrativa(final UnidadAdministrativa unidad, final Long idPadre) {

		final Session session = getSession();
		final Long idPadreAntigua = (unidad.getPadre() == null) ? null : unidad.getPadre().getId();
		final boolean newIsNull = (idPadre == null);
		final boolean oldIsNull = (idPadreAntigua == null);

		if (newIsNull && !userIsSystem())
			throw new SecurityException("Solo el usuario de sistema puede crear raices.");

		final boolean change = (newIsNull ? !oldIsNull : !idPadre.equals(idPadreAntigua));
		if (change && !oldIsNull && !newIsNull) {

			if (!getAccesoManager().tieneAccesoMoverOrganigrama(idPadreAntigua, idPadre))
				throw new SecurityException("No tiene acceso al nodo superior anterior o al actual.");

		}

		if (!getAccesoManager().tieneAccesoUnidad(unidad.getId(), true))
			throw new SecurityException("No tiene acceso a la unidad");

		try {

			session.update(unidad);
			if (change) {

				if (!oldIsNull) {

					final UnidadAdministrativa oldPadre = (UnidadAdministrativa) session
							.load(UnidadAdministrativa.class, idPadreAntigua);
					oldPadre.removeHijo(unidad);

				}

				if (!newIsNull) {

					final UnidadAdministrativa newPadre = (UnidadAdministrativa) session
							.load(UnidadAdministrativa.class, idPadre);
					newPadre.addHijo(unidad);

				}

				if (newIsNull || oldIsNull) {

					session.flush();
					final Query query = session.getNamedQuery("unidades.root");
					final List<UnidadAdministrativa> lista = castList(UnidadAdministrativa.class, query.list());

					for (int i = 0; i < lista.size(); i++) {

						final UnidadAdministrativa uni = lista.get(i);
						uni.setOrden(i);

					}

				}
			}

			addOperacion(session, unidad, Auditoria.MODIFICAR);
			session.flush();
			Actualizador.actualizar(unidad);

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, unidad.getId(), false);
			marcarIndexacionPendienteElementosRelacionadosUA(unidad.getId());
			// SIA
			final List<Long> listIdProcedimientos = DelegateUtil.getProcedimientoDelegate()
					.listarProcedimientosOrganoResolutori(unidad.getId());
			for (final Long idProcedimiento : listIdProcedimientos) {
				try {
					SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO,
							idProcedimiento, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);

				} catch (final Exception ex) {
					log.error("Error mirando si es indexable SIA. UA:" + unidad.getId() + " proc:" + idProcedimiento,
							ex);
				}
			}
		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Actualiza los edificios asociados a una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param unidad
	 *            Indica la unidad administrativa a actualizar.
	 * @param idsNuevosEdificios
	 *            Identificadores de los nuevos edificios asociados.
	 */
	@Override
	public void actualizarEdificiosUnidadAdministrativa(final UnidadAdministrativa unidad,
			final List<Long> idsNuevosEdificios) {

		try {

			final EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();

			// Obtener los edificios actuales de la UA.
			final Set<Edificio> edificiosActuales = edificioDelegate.listarEdificiosUnidad(unidad.getId());

			// Borrar los edificios actuales.
			for (final Edificio edificio : edificiosActuales)
				edificioDelegate.eliminarUnidad(unidad.getId(), edificio.getId());

			// Insertar los nuevos.
			for (final Long id : idsNuevosEdificios) {

				if (id != null) {
					edificioDelegate.anyadirUnidad(unidad.getId(), id);
				}

			}

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, unidad.getId(), false);
			// SIA
			final List<Long> listIdProcedimientos = DelegateUtil.getProcedimientoDelegate()
					.listarProcedimientosOrganoResolutori(unidad.getId());
			for (final Long idProcedimiento : listIdProcedimientos) {
				try {
					SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO,
							idProcedimiento, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);
				} catch (final Exception ex) {
					log.error("Error mirando si es indexable SIA. UA:" + unidad.getId() + " proc:" + idProcedimiento,
							ex);
				}
			}
		} catch (final DelegateException e) {

			throw new EJBException(e);

		}

	}

	/**
	 * Actualiza los usuarios asociados a una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param unidad
	 *            Indica la unidad administrativa a actualizar.
	 * @param idsNuevosUsuarios
	 *            Identificadores de los nuevos usuarios asociados.
	 */
	@Override
	public void actualizarUsuariosUnidadAdministrativa(final UnidadAdministrativa unidad,
			final List<Long> idsNuevosUsuarios) {

		try {

			// Obtener los usuarios actuales de la UA y borrarlos.
			final UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();

			if (unidad.getUsuarios() != null) {
				for (final Usuario usuario : (Set<Usuario>) unidad.getUsuarios())
					usuarioDelegate.desasignarUnidad(usuario.getId(), unidad.getId());
			}

			// Asociar los actuales.
			for (final Long id : idsNuevosUsuarios) {

				if (id != null) {
					usuarioDelegate.asignarUnidad(id, unidad.getId());
				}

			}

		} catch (final DelegateException e) {

			throw new EJBException(e);

		}

	}

	/**
	 * Lista los hijos de una unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>List<UnidadAdministrativa></code> de los hijos de la
	 *         unidad administrativa solicitada.
	 */
	@Override
	public List<UnidadAdministrativa> listarHijosUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
			Hibernate.initialize(ua.getHijos());
			final List<UnidadAdministrativa> result = new ArrayList<UnidadAdministrativa>();

			for (int i = 0; i < ua.getHijos().size(); i++) {

				final UnidadAdministrativa uaHijo = ua.getHijos().get(i);
				if (uaHijo != null && visible(uaHijo))
					result.add(uaHijo);

			}

			return result;

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Lista las unidades Administrativas raiz de un usuario.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Override
	public List<UnidadAdministrativa> listarUnidadesAdministrativasRaiz() {

		final Session session = getSession();

		try {

			final Usuario usu = getUsuario(session);

			if (userIsSystem()) {

				return castList(UnidadAdministrativa.class,
						session.createCriteria(UnidadAdministrativa.class).add(Expression.isNull("padre")).list());

			} else {

				// List uas = new ArrayList(session.filter(usu.getUnidadesAdministrativas(),
				// "where this.padre is null"));
				// Les arrel no tenen perque tenir el pare null ja que un usuari pot tenir
				// assignat un node qualsevol.
				final List<UnidadAdministrativa> uas = new ArrayList<UnidadAdministrativa>(
						castSet(UnidadAdministrativa.class, usu.getUnidadesAdministrativas()));

				// Eliminamos unidades duplicadas, por haber ya un antecesor.
				final Set<UnidadAdministrativa> duplicadas = new HashSet<UnidadAdministrativa>();

				for (int i = 0; i < uas.size(); i++) {

					final UnidadAdministrativa unidad = uas.get(i);
					UnidadAdministrativa padre = unidad.getPadre();
					boolean duplicada = false;

					while (!duplicada && padre != null) {

						if (uas.contains(padre)) {

							duplicada = true;
							duplicadas.add(unidad);

						}

						padre = padre.getPadre();

					}

				}

				uas.removeAll(duplicadas);

				return uas;

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Lista las unidades Administrativas raiz de un usuario que estan publicadas o
	 * no.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 *
	 * @param publicadas
	 *            Indica si las unidades administrativas están publicadas.
	 *
	 * @return Devuelve <code>List<UnidadAdministrativa></code> de todas las
	 *         unidades administrativas publicadas.
	 */
	public List<UnidadAdministrativa> listarUnidadesAdministrativasRaiz(final boolean publicadas) {

		final Session session = getSession();

		try {

			final Usuario usu = getUsuario(session);

			final Criteria criteria = session.createCriteria(UnidadAdministrativa.class);
			criteria.add(Expression.isNull("padre"));
			criteria.add(publicadas ? Expression.isNotNull("businessKey") : Expression.isNull("businessKey"));

			if (!userIsSystem()) {

				criteria.createAlias("usuarios", "usu");
				criteria.add(Expression.eq("usu.username", usu.getUsername()));

			}

			return castList(UnidadAdministrativa.class, criteria.list());

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Lista las unidades Administrativas raiz que estan o no publicadas.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Override
	public List<UnidadAdministrativa> listarTodasUnidadesAdministrativasRaiz() {

		final Session session = getSession();

		try {

			return castList(UnidadAdministrativa.class,
					session.createCriteria(UnidadAdministrativa.class).add(Expression.isNull("padre")).list());

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Lista los padres de unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad adminsitrativa solicitada.
	 *
	 * @return Devuelve <code>List<UnidadAdministrativa></code> de la unidad
	 *         administrativa solicitada.
	 */
	@Override
	public List<UnidadAdministrativa> listarPadresUnidadAdministrativa(final Long id) {

		final Session session = getSession();
		final List<UnidadAdministrativa> padres = new Vector<UnidadAdministrativa>();

		try {

			final UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session
					.load(UnidadAdministrativa.class, id);
			padres.add(unidadAdministrativa);

			UnidadAdministrativa padre = unidadAdministrativa.getPadre();
			while (padre != null) {

				padres.add(padre);
				padre = padre.getPadre();

			}

			Collections.reverse(padres);

			return padres;

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene una Unidad Administrativa
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>UnidadAdministrativa</code> solicitada.
	 */
	@Override
	public UnidadAdministrativa obtenerUnidadAdministrativa(final Long id) {

		final Session session = getSession();

		try {

			return (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene informacion para el front de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa solicitada.
	 *
	 * @return Devuelve <code>UnidadAdministrativa</code> solicitada.
	 */
	@Override
	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getFotop());
				Hibernate.initialize(ua.getFotog());
				Hibernate.initialize(ua.getLogoh());
				Hibernate.initialize(ua.getLogov());
				Hibernate.initialize(ua.getLogos());
				Hibernate.initialize(ua.getLogot());
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getEspacioTerrit());
				Hibernate.initialize(ua.getEdificios());
				Hibernate.initialize(ua.getUnidadesMaterias());
				Hibernate.initialize(ua.getPadre());
				Hibernate.initialize(ua.getHijos());
				Hibernate.initialize(ua.getProcedimientos());
				Hibernate.initialize(ua.getPersonal());
				Hibernate.initialize(ua.getUnidadesNormativas());
				Hibernate.initialize(ua.getUsuarios());

				return ua;

			} else {

				throw new SecurityException("La unidad administrativa no es publica con id " + ua.getId());

			}

		} catch (final HibernateException he) {

			throw new EJBException("No se ha encontrado la unitat administrativa solicitada", he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene informacion para el front de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa solicitada.
	 *
	 * @return Devuelve <code>UnidadAdministrativa</code> solicitada.
	 */
	@Override
	public UnidadAdministrativa consultarUnidadAdministrativa(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getFotop());
				Hibernate.initialize(ua.getFotog());
				Hibernate.initialize(ua.getLogoh());
				Hibernate.initialize(ua.getLogov());
				Hibernate.initialize(ua.getLogos());
				Hibernate.initialize(ua.getLogot());
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getEspacioTerrit());
				Hibernate.initialize(ua.getEdificios());
				Hibernate.initialize(ua.getUnidadesMaterias());
				Hibernate.initialize(ua.getPadre());
				Hibernate.initialize(ua.getHijos());
				Hibernate.initialize(ua.getFichasUA());
				Hibernate.initialize(ua.getTodasfichas());
				Hibernate.initialize(ua.getProcedimientos());
				Hibernate.initialize(ua.getPersonal());
				Hibernate.initialize(ua.getUnidadesNormativas());

				if (userIsSuper()) {

					Hibernate.initialize(ua.getUsuarios());

				}

				return ua;

			} else {

				throw new SecurityException("La unidad administrativa no se publica con id " + ua.getId());

			}

		} catch (final HibernateException he) {

			throw new EJBException("No se ha encontrado la unidad administrativa solicitada", he);

		} finally {

			close(session);

		}

	}

	/**
	 * @deprecated Se usa en el back antiguo Obtiene informacion para el front de
	 *             una Unidad Administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public UnidadAdministrativa consultarUnidadAdministrativaPMA(final Long id) {
		final Session session = getSession();

		try {
			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {
				Hibernate.initialize(ua.getFotop());
				Hibernate.initialize(ua.getFotog());
				Hibernate.initialize(ua.getLogoh());
				Hibernate.initialize(ua.getLogov());
				Hibernate.initialize(ua.getLogos());
				Hibernate.initialize(ua.getLogot());
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getEspacioTerrit());
				Hibernate.initialize(ua.getEdificios());
				Hibernate.initialize(ua.getUnidadesMaterias());
				Hibernate.initialize(ua.getPadre());
				Hibernate.initialize(ua.getHijos());
				Hibernate.initialize(ua.getFichasUA());
				Hibernate.initialize(ua.getTodasfichas());
				Hibernate.initialize(ua.getProcedimientos());
				Hibernate.initialize(ua.getPersonal());
				Hibernate.initialize(ua.getUnidadesNormativas());

				if (userIsAdmin()) {
					Hibernate.initialize(ua.getUsuarios());
				}
				return ua;
			} else {
				log.error("L'unidad administrativa no es visible;");
				return null;
			}

		} catch (final HibernateException he) {
			throw new EJBException("No s' ha trobat la unitat administrativa sol�licitada", he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene las ids de las UAs hijas.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 */
	@Override
	public List<Long> obtenerHijosUnidadAdministrativa(final Long idUA) {

		final Session session = getSession();

		try {

			final Query query = session
					.createQuery("select ua.id from UnidadAdministrativa as ua where ua.padre.id = " + idUA);
			return query.list();
		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene una Unidad Administrativa por el codigo Estandar(PORMAD).
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param codEstandar
	 *            Indica el código estándar de una unidad administrativa.
	 *
	 * @return <code>UnidadAdministrativa</code> solicitada.
	 */
	@Override
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(final String codEstandar) {

		final Session session = getSession();

		try {

			final Query query = session
					.createQuery("from UnidadAdministrativa as ua where ua.codigoEstandar = :codEst");

			query.setParameter("codEst", codEstandar);
			query.setMaxResults(1);
			query.setCacheable(true);

			final List<UnidadAdministrativa> result = castList(UnidadAdministrativa.class, query.list());

			if (result.isEmpty()) {

				return null;

			}

			final UnidadAdministrativa ua = result.get(0);

			if (visible(ua)) {

				Hibernate.initialize(ua.getFotop());
				Hibernate.initialize(ua.getHijos());
				Hibernate.initialize(ua.getFotog());
				Hibernate.initialize(ua.getLogoh());
				Hibernate.initialize(ua.getLogov());
				Hibernate.initialize(ua.getLogos());
				Hibernate.initialize(ua.getLogot());
				Hibernate.initialize(ua.getTratamiento());
				Hibernate.initialize(ua.getUnidadesMaterias());
				Hibernate.initialize(ua.getEdificios());

				return ua;

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene una Unidad Administrativa por el codigo DIR3.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param codEstandar
	 *            Indica el código estándar de una unidad administrativa.
	 *
	 * @return <code>UnidadAdministrativa</code> solicitada.
	 *
	 */
	@Override
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodDir3(final String codDir3, final boolean inicializar) {
		UnidadAdministrativa res = null;

		if (!StringUtils.isEmpty(codDir3)) {
			final Session session = getSession();
			try {
				final Query query = session
						.createQuery("from UnidadAdministrativa as ua where ua.codigoDIR3 = :codDir3");

				query.setParameter("codDir3", codDir3);
				query.setMaxResults(1);
				query.setCacheable(true);

				final List<UnidadAdministrativa> result = castList(UnidadAdministrativa.class, query.list());

				if (!result.isEmpty()) {

					final UnidadAdministrativa ua = result.get(0);
					if (inicializar) {
						Hibernate.initialize(ua.getFotop());
						Hibernate.initialize(ua.getHijos());
						Hibernate.initialize(ua.getFotog());
						Hibernate.initialize(ua.getLogoh());
						Hibernate.initialize(ua.getLogov());
						Hibernate.initialize(ua.getLogos());
						Hibernate.initialize(ua.getLogot());
						Hibernate.initialize(ua.getTratamiento());
						Hibernate.initialize(ua.getUnidadesMaterias());
						Hibernate.initialize(ua.getEdificios());
					}
					res = ua;
				}
			} catch (final HibernateException he) {
				throw new EJBException(he);
			} finally {
				close(session);
			}
		}
		return res;
	}

	/**
	 * Obtiene la foto pequeña de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de una unidad administrativa.
	 *
	 * @return Devuelve <code>Archivo</code> que contiene la foto pequeña de la
	 *         unidad administrativa solicitada.
	 */
	@Override
	public Archivo obtenerFotoPequenyaUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getFotop());

				return ua.getFotop();

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene la foto grande de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>Archivo</code> que contiene la foto grande de la
	 *         unidad administrativa solcitada.
	 */
	@Override
	public Archivo obtenerFotoGrandeUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getFotog());

				return ua.getFotog();

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo horitzontal de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>Archivo</code> con el logo horizontal de la unidad
	 *         administrativa.
	 */
	@Override
	public Archivo obtenerLogoHorizontalUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getLogoh());

				return ua.getLogoh();

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");
			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo vertical de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>Archivo</code> con el logo vertical de la unidad
	 *         administrativa solicitada.
	 */
	@Override
	public Archivo obtenerLogoVerticalUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getLogov());
				return ua.getLogov();

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo saludo de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>Archivo</code> con el logo saludo de la unidad
	 *         administrativa solicitada.
	 */
	@Override
	public Archivo obtenerLogoSaludoUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getLogos());

				return ua.getLogos();

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	// CODI NOU PELS LOGOS
	/**
	 * Obtiene la logo saludo vertical de una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>Archivo</code> que contiene el logo saludo verticval
	 *         de la unidad administrativa solicitada.
	 */
	@Override
	public Archivo obtenerLogoSaludoVerticalUA(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (visible(ua)) {

				Hibernate.initialize(ua.getLogot());

				return ua.getLogot();

			} else {

				throw new SecurityException("El usuario no tiene el rol operador");

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Autorización para eliminar una Unidad Administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 *
	 * @param idUa
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>true</code> si tiene acceso.
	 */
	@Override
	public boolean autorizarEliminarUA(final Long idUa) {

		return (getAccesoManager().tieneAccesoUnidad(idUa, true));

	}

	/**
	 * Valida si el usuario tiene privilegios para crear una unidad administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @return Devuelve <code>true</code> si tiene permisos para crear una unidad
	 *         adminsitrativa.
	 */
	@Override
	public Boolean autorizarCrearUA() {

		Boolean autorizacion;

		try {

			autorizacion = getAccesoManager().tieneAccesoCrearUnidad();

		} catch (final SecurityException se) {

			autorizacion = Boolean.FALSE;

		}

		return autorizacion;

	}

	/**
	 * Elimina una unidad administrativa. Se podra eliminar la UA, si no tiene
	 * elementos relacionados (Procedimientos,Normativas,etc.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.organigrama}"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa.
	 * @throws DelegateException
	 */
	@Override
	public void eliminarUaSinRelaciones(final Long idUA) throws DelegateException {

		Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			final Boolean isRaiz = ua.isRaiz();

			if (!isRaiz) {

				// Si no és arrel, el pot borrar només si té accés a l'antecesor.
				if (!autorizarEliminarUA(ua.getPadre().getId()))
					throw new SecurityException("No tiene acceso al antecesor de la unidad.");

			} else {

				// Si és arrel, només si és ADMIN o SYSTEM i té accés a l'unitat.
				if (!userIsAdmin() || !autorizarEliminarUA(ua.getId()))
					throw new SecurityException("No tiene acceso a la unidad.");

			}

			addOperacion(session, ua, Auditoria.BORRAR);
			getHistorico(session, ua);

			session = deleteCodUaHistorico(session, ua);

			ua.getEdificios().clear();
			ua.getUsuarios().clear();

			if (!isRaiz) {

				ua.getPadre().removeHijo(ua);

			} else {

				final Usuario usuario = getUsuario(session);
				usuario.getUnidadesAdministrativas().remove(ua);

			}

			final Long id = ua.getId();
			if (ua instanceof UnidadAdministrativaRemota) {

				final AdministracionRemota admin = ((UnidadAdministrativaRemota) ua).getAdministracionRemota();
				if (admin != null)
					admin.removeUnidadAdministrativaRemota((UnidadAdministrativaRemota) ua);

			} else {

				Actualizador.borrar(new UnidadAdministrativa(id));

			}

			// SOLR Desindexar unidad administrativa
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, idUA, true);
			marcarIndexacionPendienteElementosRelacionadosUA(idUA);
			marcarSiaPendienteElementosRelacionadosUA(idUA);

			session.delete(ua);
			session.flush();

			session.flush();

			if (isRaiz) {

				final Query query = session.getNamedQuery("unidades.root");
				final List<?> lista = query.list();
				for (int i = 0; i < lista.size(); i++) {
					final UnidadAdministrativa uni = (UnidadAdministrativa) lista.get(i);
					uni.setOrden(i);
				}

				session.flush();

			}

		} catch (final HibernateException he) {

			throw new EJBException("El sistema está actualizando los datos, espere unos minutos.");

		} finally {

			close(session);

		}

	}

	/**
	 * Borra una unidad administrativa.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin}"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa solicitada.
	 */
	public void borrarUnidadAdministrativa(final Long id) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (ua.isRaiz()) {

				throw new SecurityException("No puede eliminar una unidad raiz.");

			} else {

				if (!getAccesoManager().tieneAccesoUnidad(ua.getPadre().getId(), true)) {

					throw new SecurityException("No tiene acceso al padre de la unidad");

				}

			}

			final Long idPadre = ua.getPadre().getId();
			session.update(ua);

			final UnidadAdministrativa uaPadre = (UnidadAdministrativa) session.load(UnidadAdministrativa.class,
					idPadre);
			uaPadre.darDeBajaUA(ua);
			ua.darDeBajaHijosUA(ua);

			addOperacion(session, ua, Auditoria.BORRAR);
			session.flush();

			if (ua instanceof UnidadAdministrativaRemota) {

				final AdministracionRemota admin = ((UnidadAdministrativaRemota) ua).getAdministracionRemota();
				if (admin != null)
					admin.removeUnidadAdministrativaRemota((UnidadAdministrativaRemota) ua);

			} else {

				Actualizador.borrar(ua);

			}

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, id, true);
			marcarSiaPendienteElementosRelacionadosUA(id);

		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Comprueba si se puede borrar la UA viendo si está asociado a algún
	 * procedimiento.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve el mensaje de error asociado si está asociado a algún
	 *         procedimiento, sino devuelve nulo.
	 */
	@Override
	public String checkProcedimientosUA(final Long id) {
		final Session session = getSession();
		String mensajeError = null;
		try {

			final Long cuantosProcUA = Long.valueOf(session.createQuery(
					"select count(*) from ProcedimientoLocal procedimiento WHERE procedimiento.unidadAdministrativa.id = "
							+ id)
					.uniqueResult().toString());
			if (cuantosProcUA > 0) {
				mensajeError = "unitatadm.esborrat.incorrecte.procediments";
			}

			if (mensajeError == null) {
				final Long cuantosProcResol = Long.valueOf(session.createQuery(
						"select count(*) from ProcedimientoLocal procedimiento WHERE procedimiento.organResolutori.id = "
								+ id)
						.uniqueResult().toString());
				if (cuantosProcResol > 0) {
					mensajeError = "unitatadm.esborrat.incorrecte.procediments.organ";
				}
			}

			if (mensajeError == null) {
				final Long cuantosProcResp = Long.valueOf(session.createQuery(
						"select count(*) from ProcedimientoLocal procedimiento WHERE procedimiento.servicioResponsable.id = "
								+ id)
						.uniqueResult().toString());
				if (cuantosProcResp > 0) {
					mensajeError = "unitatadm.esborrat.incorrecte.procediments.servei";
				}
			}
		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
		return mensajeError;
	}

	/**
	 * Carga los identificadores de una unidad y de sus hijos de manera recursiva.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @return Devuelve <code>List<Long></code> con los identificadores de los hijos
	 *         de la unidad administrativa solicitada.
	 */
	@Override
	public List<Long> cargarArbolUnidadId(final Long id) {

		final Session sessio = getSession();

		try {

			final List<Long> ids = new ArrayList<Long>();
			ids.add(id);
			cargarHijosUnidadId(id, ids, sessio);

			return ids;

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(sessio);

		}

	}

	/**
	 * Devuelve todas las {@link Seccion} relacionadas con una
	 * {@link UnidadAdministrativa}
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idUA
	 *            Identificador de la unidad admistrativa solicitada.
	 * @return Devuelve <code>List<Seccion></code> de la unidad administrativa
	 *         solicitada.
	 */
	@Override
	public List<Seccion> listarSeccionesUA(final Long idUA) {

		List<Seccion> resultado = null;

		if (idUA != null) {

			final Session session = getSession();

			try {

				List<Seccion> filtroSecciones = new ArrayList<Seccion>();
				resultado = new ArrayList<Seccion>();

				/*
				 * TODO: optimizar quitando el uso del método: tieneAccesoSeccion() incluyendo
				 * la comprobación directamente en esta consulta. o utilizando el método
				 * tieneAcceso de HibernateEJB
				 */
				final StringBuilder consulta = new StringBuilder(
						" SELECT DISTINCT seccion  FROM FichaUA AS fua, Seccion AS seccion ");
				consulta.append(" WHERE fua.seccion.id = seccion.id AND fua.unidadAdministrativa.id = :idUA ");

				final Query query = session.createQuery(consulta.toString());
				query.setParameter("idUA", idUA);

				// Filtrado por usuario
				filtroSecciones = query.list();
				for (final Seccion sec : filtroSecciones) {

					if (getAccesoManager().tieneAccesoSeccion(sec.getId()))
						resultado.add(sec);

				}

				// Inicializamos hijos para no tener error de lazy al comprobar si el List
				// contiene
				// o no elementos, intentando saber si tiene hijos para crear correctamente el
				// DTO
				// asociado al elemento Seccion (SeccionDTO.fills).
				final Iterator<Seccion> itSeccion = resultado.iterator();
				while (itSeccion.hasNext()) {

					final Seccion seccion = itSeccion.next();
					Hibernate.initialize(seccion.getHijos());

				}

			} catch (final HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		} else {

			resultado = Collections.emptyList();

		}

		return resultado;

	}

	/**
	 * Devuelve el número de {@link Ficha} relacionadas con una
	 * {@link UnidadAdministrativa} y una {@link Seccion}.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa.
	 *
	 * @param idSeccion
	 *            Identificador de la sección.
	 *
	 * @return Devuelve el número de fichas relacionadas a una unidad administrativa
	 *         y a una sección.
	 */
	@Override
	public int cuentaFichasSeccionUA(final Long idUA, final Long idSeccion) {

		int resultado = 0;

		if (idUA != null && idSeccion != null) {

			final Session session = getSession();

			try {

				final StringBuilder consulta = new StringBuilder(" select count(fua.id) ");
				consulta.append(" from FichaResumenUA as fua, FichaResumen as ficha ");
				consulta.append(" where fua.idUa = :idUA ");
				consulta.append(" and fua.ficha.id = ficha.id ");
				consulta.append(" and fua.idSeccio = :idSeccion ");
				consulta.append(" and ficha.validacion = :validacion ");
				consulta.append(" and (ficha.fechaCaducidad > " + DateUtils.stringFechaAhoraBBDD()
						+ " or ficha.fechaCaducidad is null) ");
				consulta.append(" and (ficha.fechaPublicacion <= " + DateUtils.stringFechaAhoraBBDD()
						+ " or ficha.fechaPublicacion is null) ");

				final Query query = session.createQuery(consulta.toString());

				query.setParameter("idUA", idUA);
				query.setParameter("idSeccion", idSeccion);
				query.setParameter("validacion", Validacion.PUBLICA);

				resultado = (Integer) query.uniqueResult();

			} catch (final HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		}

		return resultado;

	}

	/* ================================================ */
	/* == MÉTODOS PRIVADOS ========================== */
	/* ================================================ */

	private int numUnidadesRaiz(final Session session) throws HibernateException {

		final Query query = session.getNamedQuery("unidades.root.count");

		return ((Integer) query.list().get(0)).intValue();

	}

	/**
	 * Construye la consulta de búsqueda segun los parámetros
	 */
	private String populateQuery(final Map parametros, final Map traduccion, final List params) {
		String aux = "";

		for (final Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
			final String key = (String) iter1.next();
			final Object value = parametros.get(key);
			if (value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (aux.length() > 0)
							aux = aux + " and ";
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( unidad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( unidad." + key + " ) like ? ";
							params.add("%" + sValue + "%");
						}
					}
				} else if (value instanceof Date) {
					if (aux.length() > 0)
						aux = aux + " and ";
					aux = aux + "unidad." + key + " = '" + value + "'";
				} else {
					if (aux.length() > 0)
						aux = aux + " and ";
					aux = aux + "unidad." + key + " = " + value;
				}
			}
		}

		// Tratamiento de traducciones
		if (!traduccion.isEmpty()) {
			if (aux.length() > 0)
				aux = aux + " and ";
			aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
			traduccion.remove("idioma");
		}
		for (final Iterator iter2 = traduccion.keySet().iterator(); iter2.hasNext();) {
			final String key = (String) iter2.next();
			final Object value = traduccion.get(key);
			if (value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " and upper( trad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " and upper( trad." + key + " ) like ? ";
							params.add("%" + sValue + "%");
						}
					}
				} else {
					aux = aux + " and trad." + key + " = ? ";
					params.add(value);
				}
			}
		}

		return aux;
	}

	/**
	 * Construye la consulta de búsqueda multiidioma en todos los campos
	 */
	private String i18nPopulateQuery(final Map traducciones, final List params) {
		String aux = "";

		for (final Iterator iterTraducciones = traducciones.keySet().iterator(); iterTraducciones.hasNext();) {

			final String key = (String) iterTraducciones.next();
			final Object value = traducciones.get(key);

			if (value != null) {
				if (aux.length() > 0)
					aux = aux + " or ";
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( trad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( trad." + key + " ) like ? ";
							params.add("%" + sValue + "%");
						}
					}
				} else {
					aux = aux + " trad." + key + " = ? ";
					params.add(value);
				}
			}
		}

		return aux;
	}

	/**
	 * Carga todos los ids de las unidades hijas de una unidad de manera recursiva.
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @param listaIdUAhijas
	 *            Lista con los identificadores de los hijos de una determinada
	 *            unidad administrativa.
	 *
	 * @param session
	 *            Sesión de hibernate.
	 *
	 * @throws HibernateException
	 */
	private void cargarHijosUnidadId(final Long id, final List listaIdUAhijas, final Session session)
			throws HibernateException {

		final UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
		final List hijos = unidad.getHijos();

		for (int i = 0; i < hijos.size(); i++) {

			final UnidadAdministrativa uni = (UnidadAdministrativa) hijos.get(i);
			if (uni != null) {

				listaIdUAhijas.add(uni.getId());
				cargarHijosUnidadId(uni.getId(), listaIdUAhijas, session);

			}

		}

	}

	/**
	 * Devuelve un {@link List} de {@link FichaUA}, relacionadas con una
	 * {@link UnidadAdministrativa} y una {@link Seccion}.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa solicitada.
	 *
	 * @param idSeccion
	 *            Identificador de la sección solicitada.
	 *
	 * @return Devuelve <code>List<FichaDTO></code> relacionadas con una unidad
	 *         administrativa y una sección.
	 */
	@Override
	public List<FichaDTO> listarFichasSeccionUA(final Long idUA, final Long idSeccion, final String idioma,
			final PaginacionCriteria paginacion) {

		final Vector<FichaDTO> listaFichasConOrden = new Vector<FichaDTO>();

		if (idUA != null && idSeccion != null) {

			final Session session = getSession();

			try {

				final Query query = session.getNamedQuery("fichasBySeccionInUA");
				query.setParameter("idUA", idUA);
				query.setParameter("idSeccion", idSeccion);
				query.setParameter("idioma", idioma);
				query.setParameter("validacion", Validacion.PUBLICA);
				query.setParameter("sysdate", DateUtils.getNow());

				/*
				 * 16/12/2013: Se genera un orden en lugar de recuperar el orden real debido a
				 * que se ha cambiado el mecanismo que establece el orden de las fichas. Los
				 * datos antiguos generan valores que no coinciden con los generados por el
				 * nuevo mecanismo.
				 */
				final List<FichaDTO> listaFichas = query.list();

				long ordenBase = 0L;

				for (final FichaDTO ficha : listaFichas) {

					ficha.setOrdre(ordenBase + 1L);
					listaFichasConOrden.add(ficha);
					ordenBase++;

				}

			} catch (final HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		}

		return listaFichasConOrden;

	}

	/**
	 * Devuelve un {@link List} de {@link FichaUA}, relacionadas con una
	 * {@link UnidadAdministrativa} y una {@link Seccion}.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa solicitada.
	 *
	 * @param idSeccion
	 *            Identificador de la sección solicitada.
	 * @return Devuelve <code>List<FichaDTO></code> relacionadas con una unidad
	 *         administrativa y una sección.
	 *
	 */
	@Override
	public List<FichaDTO> listarFichasSeccionUASinPaginacion(final Long idUA, final Long idSeccion,
			final String idioma) {

		List<FichaDTO> listaFichas = new ArrayList<FichaDTO>();

		if (idUA != null && idSeccion != null) {

			final Session session = getSession();

			try {

				final Query query = session.getNamedQuery("fichasBySeccionInUA");
				query.setParameter("idUA", idUA);
				query.setParameter("idSeccion", idSeccion);
				query.setParameter("idioma", idioma);
				query.setParameter("validacion", Validacion.PUBLICA);
				query.setParameter("sysdate", DateUtils.getNow());

				/*
				 * 16/12/2013: Se genera un orden en lugar de recuperar el orden real debido a
				 * que se ha cambiado el mecanismo que establece el orden de las fichas. Los
				 * datos antiguos generan valores que no coinciden con los generados por el
				 * nuevo mecanismo.
				 */
				listaFichas = query.list();

			} catch (final HibernateException he) {

				throw new EJBException(he);

			} finally {

				close(session);

			}

		}

		return listaFichas;

	}

	/**
	 * Compone la miga de pan de la unidad administrativa para el rolsacback
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa.
	 *
	 * @param idioma
	 *            Indica el idioma en que se realiza la búsqueda.
	 *
	 * @param url
	 *            Indica la url de la miga de pan.
	 *
	 * @param uaIdPlaceholder
	 *            ??
	 *
	 * @return Devuelve una cadena de texto con la miga de pan.
	 */
	@Override
	public StringBuffer getUaMollaBack2(final Long idUA, final String idioma, final String url,
			final String uaIdPlaceholder) {

		StringBuffer migaPan = new StringBuffer(" ");

		try {

			UnidadAdministrativa uniadm = obtenerUnidadAdministrativa(idUA);
			boolean tieneAcceso = getAccesoManager().tieneAccesoUnidad(uniadm.getId(), false);

			while (uniadm != null && tieneAcceso) {

				final StringBuffer uaSbuf = new StringBuffer(((TraduccionUA) uniadm.getTraduccion(idioma)).getNombre());
				Cadenas.initAllTab(uaSbuf); // primera letra en mayusculas

				final String uaTexto = Cadenas.initTab(uaSbuf.toString()); // articulos a minusculas
				final String uaURL = url.replaceFirst(uaIdPlaceholder, uniadm.getId().toString());

				final StringBuffer migaPanUA = new StringBuffer("<li class=\"ua");

				if (idUA.equals(uniadm.getId()))
					migaPanUA.append(" seleccionat");

				// Obtenemos todos los padres de la jerarquía.
				final List padres = listarPadresUnidadAdministrativa(uniadm.getId());
				String padreUAId = "";

				if (padres.size() > 1) {
					// El penúltimo elemento es el padre directo de la UA
					final UnidadAdministrativa padre = (UnidadAdministrativa) padres.get(padres.size() - 2);
					padreUAId = Long.toString(padre.getId());
				}

				migaPanUA.append("\" data-clave_ua_padre=\"");
				migaPanUA.append(padreUAId);
				migaPanUA.append("\"><div><a class=\"ua\" href=\"");
				migaPanUA.append(uaURL);
				migaPanUA.append("\">");
				migaPanUA.append(uaTexto);
				migaPanUA.append("</a><a class=\"desplegar\" href=\"javascript:void(0);\"></a></div></li>");

				migaPan.insert(0, migaPanUA);

				uniadm = uniadm.getPadre();
				if (uniadm != null)
					tieneAcceso = getAccesoManager().tieneAccesoUnidad(uniadm.getId(), false);

			}

		} catch (final EJBException e) {

			migaPan = new StringBuffer("&nbsp;");

		}

		return migaPan;

	}

	/**
	 * Devuelve las unidades administrativas que coinciden con los criterios de
	 * búsqueda
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public ResultadoBusqueda buscadorUnidadesAdministrativas(final Map<String, Object> parametros,
			final Map<String, String> traduccion, final Long id, final String idioma, final boolean uaFilles,
			final boolean uaMeves, final Long materia, final String pagina, final String resultats) {

		// TODO 26/08/2013: Refactorizar.
		final Session session = getSession();
		List<UnidadAdministrativa> listaUnidadesAdministrativas = new ArrayList<UnidadAdministrativa>();

		try {

			if (!userIsOper())
				parametros.put("validacion", Validacion.PUBLICA);

			final List params = new ArrayList();
			String i18nQuery = "";

			if (traduccion.get("idioma") != null) {

				i18nQuery = populateQuery(parametros, traduccion, params);

			} else {

				final String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if (paramsQuery.length() == 0) {

					i18nQuery += "";

				} else {

					i18nQuery += paramsQuery + " and ";

				}

				i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ")";

			}

			final String select = "select new UnidadAdministrativa( unidad.id, unidad.codigoEstandar, trad.nombre, unidad.orden, index(trad) ) ";
			final String from = "from UnidadAdministrativa as unidad, unidad.traducciones trad ";
			String where = "where " + i18nQuery + " and unidad.padre = " + id + " ";
			final String orderBy = "order by unidad.orden";

			if (userIsSystem()) {

				if (id == null) {

					where = "where " + i18nQuery + (uaFilles ? "" : " and unidad.padre is null ");

				} else if (uaMeves && uaFilles) {

					where = "where " + i18nQuery;

				} else if (uaFilles) {

					where = " where " + i18nQuery + " and unidad.padre in ("
							+ cargarArbolUnidadId(id).toString().replaceAll("\\[|\\]", "") + ")";

				}

			} else {

				String cadenaFiltro = obtenerCadenaFiltroUA(id, uaFilles, uaMeves);
				if (StringUtils.isEmpty(cadenaFiltro))
					cadenaFiltro = EMPTY_ID;

				where = where.replaceFirst("and unidad.padre = " + id, " ");
				where += "and (unidad.id in(" + cadenaFiltro + ") " + (id != null ? "or unidad.padre = " + id : "")
						+ ") " + (id != null ? "and unidad.id != " + id + " " : "");
			}

			if (materia != null) {
				where += " and unidad.id in (select uam.unidad.id " + "from UnidadMateria as uam "
						+ "where uam.materia.id = " + materia + ") ";
			}

			final Query query = session.createQuery(select + from + where + orderBy);

			// Asignar parámetros
			for (int i = 0; i < params.size(); i++) {

				final String o = (String) params.get(i);
				query.setString(i, o);

			}

			final int resultadosMax = new Integer(resultats).intValue();
			final int primerResultado = new Integer(pagina).intValue() * resultadosMax;

			listaUnidadesAdministrativas = castList(UnidadAdministrativa.class, query.list());

			if (resultadosMax != RESULTATS_CERCA_TOTS) {

				query.setFirstResult(primerResultado);
				query.setMaxResults(resultadosMax);

			}

			final ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
			resultadoBusqueda.setListaResultados(listaUnidadesAdministrativas);
			resultadoBusqueda.setTotalResultados(listaUnidadesAdministrativas.size());

			return resultadoBusqueda;

		} catch (final DelegateException ex) {

			log.warn("[obtenerCadenaFiltroUA: " + id + "] No se ha podido llamar al método. " + ex.getMessage());

			throw new EJBException(ex);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Asigna a una unidad administratival un nuevo orden y reordena los elementos
	 * afectados.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 *
	 * @param id
	 *            Identificador de la unidad administrativa.
	 *
	 * @param ordenNuevo
	 *            Indica el nuevo orden para la unidad administrativa.
	 *
	 * @param ordenAnterior
	 *            Indica el orden anterior de la unidad administrativa.
	 *
	 * @param idPadre
	 *            Identificador de la unidad administrativa padre.
	 * @throws DelegateException
	 */
	@Override
	public void reordenar(final Long id, final Integer ordenNuevo, final Integer ordenAnterior, final Long idPadre)
			throws DelegateException {

		final Session session = getSession();
		List<UnidadAdministrativa> listaUAs = new ArrayList<UnidadAdministrativa>();

		try {

			final StringBuilder consulta = new StringBuilder("select ua from UnidadAdministrativa as ua ");

			if (idPadre != null) {

				consulta.append(" where ua.padre = :idPadre ");

			} else {

				consulta.append(" where ua.padre is null ");

			}

			consulta.append(" order by ua.orden asc ");

			final Query query = session.createQuery(consulta.toString());

			if (idPadre != null)
				query.setParameter("idPadre", idPadre);

			listaUAs = castList(UnidadAdministrativa.class, query.list());

			// Modificar sólo los elementos entre la posición del elemento que cambia
			// de orden y su nueva posición
			final int ordenMayor = ordenNuevo > ordenAnterior ? ordenNuevo : ordenAnterior;
			final int ordenMenor = ordenMayor == ordenNuevo ? ordenAnterior : ordenNuevo;

			// Si el nuevo orden es mayor que el anterior, desplazar los elementos
			// intermedios hacia arriba (-1), en caso contrario, hacia abajo (+1)
			final int incremento = ordenNuevo > ordenAnterior ? -1 : 1;

			for (final UnidadAdministrativa unidadAdministrativa : listaUAs) {

				final long orden = unidadAdministrativa.getOrden();

				if (orden >= ordenMenor && orden <= ordenMayor) {

					if (id.equals(unidadAdministrativa.getId())) {

						unidadAdministrativa.setOrden(ordenNuevo);

					} else {

						unidadAdministrativa.setOrden(orden + incremento);

					}

				}

				// Revisamos si tiene edificios y en caso contrario lo limpiamos
				if (unidadAdministrativa.getEdificios().size() == 0)
					unidadAdministrativa.setEdificios(null);

				// Revisamos si tiene materias y en caso contrario lo limpiamos
				if (unidadAdministrativa.getUnidadesMaterias().size() == 0)
					unidadAdministrativa.setUnidadesMaterias(null);

				// Actualizar todo para asegurar que no hay duplicados ni huecos
				session.saveOrUpdate(unidadAdministrativa);

				// SOLR Indexar unidad administrativa
				final SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
				solrPendiente.grabarSolrPendiente(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA.toString(),
						unidadAdministrativa.getId(), 1l);
				session.flush();
			}

			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Método que devuelve una cadena csv de ids de unidades administrativas según
	 * los parámetros pasados.
	 *
	 * @param idUA
	 *            Identificador de la unidada administrativa.
	 * @param uaHijas
	 *            Indica si la unidad administrativa tiene unidades hijas.
	 * @param uaPropias
	 * @return
	 * @throws HibernateException
	 * @throws DelegateException
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public String obtenerCadenaFiltroUA(final Long idUA, final boolean uaHijas, final boolean uaPropias)
			throws DelegateException {

		final Set<Long> uas = new HashSet<Long>();
		final Set<Long> uasIds = new HashSet<Long>();

		if (idUA != null)
			uas.add(idUA);

		if (uaPropias)
			uas.addAll(getIdsUnidadesAdministrativasUsuario(ctx.getCallerPrincipal().getName()));

		if (uaHijas) {

			final UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

			for (final Long uaActual : uas) {

				uasIds.add(uaActual);
				final List<Long> idsDescendientes = castList(Long.class, uaDelegate.cargarArbolUnidadId(uaActual));
				uasIds.addAll(idsDescendientes);

			}

		} else {

			for (final Long uaActual : uas)
				uasIds.add(uaActual);

		}

		final StringBuilder consultaUA = new StringBuilder();
		String separador = "";

		for (final Long uaId : uasIds) {

			consultaUA.append(separador).append(uaId);
			separador = ", ";

		}

		return consultaUA.toString();

	}

	/**
	 * Método que devuelve una cadena csv de ids de unidades administrativas según
	 * los parámetros pasados. si no encuentra codigo dir3 retorna cadena vacia
	 *
	 * @param codDir3UA
	 *            Código DIR3 de la unidada administrativa.
	 * @param uaHijas
	 *            Indica si la unidad administrativa tiene unidades hijas.
	 * @param uaPropias
	 * @return
	 * @throws HibernateException
	 * @throws DelegateException
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public String obtenerCadenaFiltroUAPorDir3(final String codDir3UA, final boolean uaHijas, final boolean uaPropias)
			throws DelegateException {
		String res = "";
		if (!StringUtils.isEmpty(codDir3UA)) {
			final UnidadAdministrativa ua = obtenerUnidadAdministrativaPorCodDir3(codDir3UA, false);
			final Long idUa = ua != null ? ua.getId() : null;
			res = obtenerCadenaFiltroUA(idUa, uaHijas, uaPropias);
		}
		return res;
	}

	/**
	 * Se encarga de actualizar las fichas de una sección relacionada con una UA.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa.
	 *
	 * @param idSeccion
	 *            Identificador de la sección.
	 *
	 * @param fichas
	 *            Lista que contiene los identificadores de las fichas.
	 * @throws DelegateException
	 *
	 * @throws EJBException
	 */
	@Override
	public void actualizaFichasSeccionUA(final Long idUA, final Long idSeccion, final List<FichaDTO> fichas)
			throws DelegateException {

		final Session session = getSession();

		try {

			final Query query = session.getNamedQuery("fichasUABySeccionInUA");
			query.setParameter("idUA", idUA);
			query.setParameter("idSeccion", idSeccion);
			query.setParameter("idioma", DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
			query.setParameter("validacion", Validacion.PUBLICA);
			query.setParameter("sysdate", DateUtils.getNow());

			// Borramos las fichas UA
			final List<FichaResumenUA> listaFichasUA = query.list();
			for (final FichaResumenUA fua : listaFichasUA) {

				final FichaResumen ficha = (FichaResumen) session.get(FichaResumen.class, fua.getFicha().getId());
				ficha.removeFichaUA(fua);
				session.flush();
				session.delete(fua);

				IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, fua.getFicha().getId(), false);

			}

			session.flush();

			// Creamos las fichas UA
			for (Integer i = 0; i < fichas.size(); i++) {

				final FichaDTO ficha = fichas.get(i);
				final FichaResumen fichaAUX = (FichaResumen) session.load(FichaResumen.class, ficha.getId());

				final FichaResumenUA fichaUA = new FichaResumenUA();
				fichaUA.setIdUa(idUA);
				fichaUA.setIdSeccio(idSeccion);
				fichaUA.setFicha(fichaAUX);
				fichaUA.setOrden(i + 1);

				session.save(fichaUA);

				IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, ficha.getId(), false);

			}

			session.flush();

		} catch (final HibernateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene una lista de identificadores de las unidades admnistrativas que
	 * pertenecen a un usuario.
	 *
	 * @param nombreUsuario
	 *            Cadena de texto que indica el nombre del usuario.
	 * @return Devuelve <code>List<Long></code> de identificadores de unidades
	 *         administrativas.
	 */
	private List<Long> getIdsUnidadesAdministrativasUsuario(final String nombreUsuario) {

		final Session session = getSession();

		try {

			final StringBuilder consulta = new StringBuilder("select ua.id ");
			consulta.append(" from UnidadAdministrativa as ua, ua.usuarios as uaUsu ");
			consulta.append(" where uaUsu.username = :nombreUsuario ");

			final Query query = session.createQuery(consulta.toString());
			query.setParameter("nombreUsuario", nombreUsuario);

			return query.list();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar el archivo foto grande
	 *
	 * @param idUA
	 * @throws HibernateException
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarFotoGrande(final Long idUA) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			session.delete(ua.getFotog());
			ua.setFotog(null);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar el archivo foto pequeña
	 *
	 * @param idUA
	 * @throws HibernateException
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarFotoPetita(final Long idUA) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			session.delete(ua.getFotop());
			ua.setFotop(null);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar el archivo logo salutación vertical
	 *
	 * @param idUA
	 * @throws HibernateException
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarLogoTipos(final Long idUA) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			session.delete(ua.getLogot());
			ua.setLogot(null);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar el archivo logo vertical
	 *
	 * @param idUA
	 * @throws HibernateException
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarLogoVertical(final Long idUA) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			session.delete(ua.getLogov());
			ua.setLogov(null);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar el archivo logo horitzontal
	 *
	 * @param idUA
	 * @throws HibernateException
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarLogoHorizontal(final Long idUA) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			session.delete(ua.getLogoh());
			ua.setLogoh(null);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar el archivo logo salutación horitzontal
	 *
	 * @param idUA
	 * @throws HibernateException
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarLogoSalutacio(final Long idUA) {

		final Session session = getSession();

		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			session.delete(ua.getLogos());
			ua.setLogos(null);
			session.flush();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Se encarga de eliminar las relaciones de fichasUA de una sección relacionada
	 * con una UA.
	 *
	 * @param idUA
	 * @param idSeccion
	 * @throws EJBException
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void eliminarSeccionUA(final Long idUA, final Long idSeccion) {

		final Session session = getSession();

		try {

			final StringBuilder consulta = new StringBuilder(" SELECT fichaUA ");
			consulta.append(" FROM FichaResumenUA AS fichaUA ");
			consulta.append(" WHERE fichaUA.idUa = :idUA ");
			consulta.append(" AND fichaUA.idSeccio = :idSeccion ");

			final Query query = session.createQuery(consulta.toString());
			query.setParameter("idUA", idUA);
			query.setParameter("idSeccion", idSeccion);

			final List<FichaResumenUA> listaFichasUA = query.list();
			if (!listaFichasUA.isEmpty()) {

				for (final FichaResumenUA fuaResumen : listaFichasUA) {

					fuaResumen.getFicha().removeFichaUA(fuaResumen);
					session.delete(fuaResumen);

					IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, fuaResumen.getFicha().getId(),
							false);

				}

				session.flush();

			}

		} catch (final Exception e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

	}

	/**
	 * Comprueba si es indexable un tramite.
	 *
	 * @return
	 */
	private boolean isIndexable(final UnidadAdministrativa unidadAdministrativa) {
		boolean indexable = true;
		if (unidadAdministrativa.getValidacion() != 1) {
			indexable = false;
		}

		return indexable;
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 *
	 * @param solrPendiente
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		return indexarSolr(solrIndexer, solrPendiente.getIdElemento(),
				EnumCategoria.fromString(solrPendiente.getTipo()));
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 *
	 * @param solrPe.ndiente
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento,
			final EnumCategoria categoria) {
		log.debug("UnidadAdministrativaFacadeEJB.indexarSolr. idElemento:" + idElemento + " categoria:" + categoria);

		try {
			// Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final UnidadAdministrativa unidadAdministrativa = obtenerUnidadAdministrativaIndexar(idElemento);
			if (unidadAdministrativa == null) {
				log.error("No se encuentra unidad administrativa con id: " + idElemento);
				return new SolrPendienteResultado(false, "No se encuentra unidad administrativa con id: " + idElemento);
			}

			final boolean isIndexable = this.isIndexable(unidadAdministrativa);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}

			// Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la
			// categoria de tipo normativa.
			final IndexData indexData = new IndexData();
			indexData.setCategoria(categoria);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());
			final PathUO pathUO = IndexacionUtil.calcularPathUO(unidadAdministrativa);
			if (pathUO == null) {
				return new SolrPendienteResultado(true, "No se puede indexar: no cuelga de UA visible");
			}
			indexData.getUos().add(pathUO);

			// Iteramos las traducciones
			final Map<String, Traduccion> traducciones = unidadAdministrativa.getTraduccionMap();
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral searchText = new MultilangLiteral();
			final MultilangLiteral searchTextOptional = new MultilangLiteral();
			final List<EnumIdiomas> idiomas = new ArrayList<EnumIdiomas>();

			// Recorremos las traducciones
			for (final String keyIdioma : unidadAdministrativa.getTraduccionMap().keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionUA traduccion = (TraduccionUA) traducciones.get(keyIdioma);

				if (traduccion != null && enumIdioma != null) {

					// Lo que hace es saltarse el idioma que no tiene nombre.
					if ((traduccion.getNombre() == null || traduccion.getNombre().isEmpty())) {
						continue;
					}

					// Anyadimos idioma al enumerado.
					idiomas.add(enumIdioma);

					// Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search
					// text.
					titulo.addIdioma(enumIdioma, traduccion.getNombre());
					descripcion.addIdioma(enumIdioma, solrIndexer.htmlToText(traduccion.getPresentacion()));
					searchText.addIdioma(enumIdioma,
							traduccion.getNombre() + " " + unidadAdministrativa.getResponsable());

					final StringBuffer textoOptional = new StringBuffer();

					// materia
					for (final UnidadMateria materia : unidadAdministrativa.getUnidadesMaterias()) {
						final TraduccionMateria traduccionMateria = (TraduccionMateria) materia.getMateria()
								.getTraduccion(keyIdioma);
						if (traduccionMateria != null) {
							textoOptional.append(" ");
							textoOptional.append(traduccionMateria.getNombre());
							textoOptional.append(" ");
							textoOptional.append(traduccionMateria.getDescripcion());
							textoOptional.append(" ");
							textoOptional.append(traduccionMateria.getPalabrasclave());
						}
					}

					// edificios
					for (final Edificio edificio : unidadAdministrativa.getEdificios()) {
						final TraduccionEdificio traduccionEdificio = (TraduccionEdificio) edificio
								.getTraduccion(keyIdioma);
						if (traduccionEdificio != null) {
							textoOptional.append(" ");
							textoOptional.append(traduccionEdificio.getDescripcion());
						}
					}

					// Unidades administrativas.
					textoOptional.append(" ");
					textoOptional.append(IndexacionUtil.calcularPathTextUO(unidadAdministrativa, keyIdioma));

					searchTextOptional.addIdioma(enumIdioma, textoOptional.toString());
					urls.addIdioma(enumIdioma,
							"/govern/organigrama/area.do?lang=" + keyIdioma + "&coduo=" + unidadAdministrativa.getId());
				}
			}

			indexData.setTitulo(titulo);
			indexData.setDescripcion(descripcion);
			indexData.setUrl(urls);
			indexData.setSearchText(searchText);
			indexData.setSearchTextOptional(searchTextOptional);
			indexData.setIdiomas(idiomas);

			// Fechas
			indexData.setFechaActualizacion(unidadAdministrativa.getFechaUltimaActualizacion());

			solrIndexer.indexarContenido(indexData);
			return new SolrPendienteResultado(true);
		} catch (final Exception exception) {
			log.error("Error en unidadadministrativa intentando indexar. idElemento:" + idElemento + " categoria:"
					+ categoria, exception);
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}

	/**
	 * Obtener UA para solr y tener las relaciones.
	 *
	 * @param idUA
	 * @return
	 */
	private UnidadAdministrativa obtenerUnidadAdministrativaIndexar(final Long idUA) {
		final Session session = getSession();
		UnidadAdministrativa ua = null;
		try {
			ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			Hibernate.initialize(ua.getEdificios());
			Hibernate.initialize(ua.getUnidadesMaterias());
			session.clear();
		} catch (final HibernateException he) {
			log.error("Error cargano unidad administrativa con id " + idUA, he);
		} finally {
			close(session);
		}
		return ua;
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 *
	 * @param solrPendiente
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		try {
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(),
					EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA);
			return new SolrPendienteResultado(true);
		} catch (final Exception exception) {
			log.error("Error en unidadadministrativa intentando desindexar.", exception);
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}

	/**
	 * Busca los ids de las unidades administrativas
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public List<Long> buscarIdsUas() {
		final Session session = getSession();
		try {

			final StringBuilder consulta = new StringBuilder("select u.id from UnidadAdministrativa as u ");

			final Query query = session.createQuery(consulta.toString());
			query.setCacheable(true);

			return query.list();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}
	}

	private void marcarSiaPendienteElementosRelacionadosUA(final Long idUA) {

		// Primero las fichas que se relacionan con el hechovital.
		final Session session = getSession();

		// Luego los procedimientos
		StringBuilder consulta = new StringBuilder(
				"select proc.id from ProcedimientoLocal proc left join proc.unidadAdministrativa uad where uad.id = "
						+ idUA);
		try {
			final Query query = session.createQuery(consulta.toString());
			query.setCacheable(true);
			final List<Long> idProcedimientos = castList(Long.class, query.list());
			for (final Long idProcedimiento : idProcedimientos) {
				SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, idProcedimiento,
						SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);
			}
		} catch (final Exception he) {
			throw new EJBException(he);
		}

		// Luego los servicios
		consulta = new StringBuilder(
				"select serv.id from Servicio serv left join serv.servicioResponsable uad where uad.id = " + idUA);
		try {
			final Query query = session.createQuery(consulta.toString());
			query.setCacheable(true);
			final List<Long> idServicios = castList(Long.class, query.list());
			for (final Long idServicio : idServicios) {
				SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_SERVICIO, idServicio,
						SiaUtils.SIAPENDIENTE_SERVICIO_EXISTE, null, null);
			}
		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	private void marcarIndexacionPendienteElementosRelacionadosUA(final Long idUA) {

		// TODO Evitamos ejecutar esta parte para no sobrecargar. Ante cambios en UAs
		// conviene ejecutar indexacion completa.
		if (true)
			return;

		// Obtemos descendientes
		final List<Long> arbolUA = cargarArbolUnidadId(idUA);

		// Primero las fichas que se relacionan con el hechovital.
		final Session session = getSession();
		// La acción es indexar (porque habrá que actualizar la información)
		final Long accion = 1l;

		try {
			for (final Long idUnidadAdministrativa : arbolUA) {

				// Primero busca las fichas relacionadas.
				StringBuilder consulta = new StringBuilder(
						"select ficha.id from Ficha ficha left join ficha.fichasua fua left join fua.unidadAdministrativa uad  where uad.id = "
								+ idUnidadAdministrativa);
				Query query = session.createQuery(consulta.toString());
				query.setCacheable(true);
				final List<Long> idFichas = castList(Long.class, query.list());
				for (final Long idFicha : idFichas) {
					IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_FICHA, idFicha, false);
				}

				// Luego los procedimientos
				consulta = new StringBuilder(
						"select proc.id from ProcedimientoLocal proc left join proc.unidadAdministrativa uad where uad.id = "
								+ idUnidadAdministrativa);
				query = session.createQuery(consulta.toString());
				query.setCacheable(true);
				final List<Long> idProcedimientos = castList(Long.class, query.list());
				for (final Long idProcedimiento : idProcedimientos) {
					IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, idProcedimiento,
							false);
				}

				// Luego las normativas
				consulta = new StringBuilder(
						"select nor.id from Normativa nor left join nor.unidadesnormativas.unidadAdministrativa uad where uad.id = "
								+ idUnidadAdministrativa);
				query = session.createQuery(consulta.toString());
				query.setCacheable(true);
				final List<Long> idNormativas = castList(Long.class, query.list());
				for (final Long idNormativa : idNormativas) {
					IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_NORMATIVA, idNormativa, false);
				}
			}
		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Consulta las unidades administrativas en funcion del filtro generico
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public ResultadoBusqueda consultaUnidadesAdministrativas(final FiltroGenerico filtro) {

		final Session session = getSession();
		final Integer pageSize = filtro.getPageSize();
		final Integer pageNumber = filtro.getPage();
		final Long id = filtro.getId();
		final String lang = filtro.getLang();
		final Map<String, String> parametros = new HashMap<String, String>();

		final String codigoUAPadre = filtro.getValor(FiltroGenerico.FILTRO_UA_CODIGO_UA_PADRE);
		final String validacion = filtro.getValor(FiltroGenerico.FILTRO_UA_VALIDACION);
		final String codigoNormativa = filtro.getValor(FiltroGenerico.FILTRO_UA_CODIGO_NORMATIVA);
		final String codigoSeccion = filtro.getValor(FiltroGenerico.FILTRO_UA_CODIGO_SECCION);

		final StringBuilder select = new StringBuilder("SELECT ua ");
		final StringBuilder selectCount = new StringBuilder("SELECT count(ua) ");
		final StringBuilder from = new StringBuilder(" FROM UnidadAdministrativa as ua, ua.traducciones as trad ");
		final StringBuilder where = new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang", lang);
		final StringBuilder order = new StringBuilder(filtro.getOrdenSQL("ua"));

		try {

			if (!StringUtils.isEmpty(codigoUAPadre)) {
				if (codigoUAPadre.equals("-1")) {
					where.append(" AND ua.padre is null");
				} else {
					where.append(" AND ua.padre = :padre");
					parametros.put("padre", codigoUAPadre);
				}
			}

			if (id != null && id > 0) {
				where.append(" AND ua.id = :id");
				parametros.put("id", id.toString());
			}

			if (!StringUtils.isEmpty(validacion)) {
				where.append(" AND ua.validacion = :validacion");
				parametros.put("validacion", validacion);
			}

			if (!StringUtils.isEmpty(codigoNormativa)) {
				// ua de una normativa en concreto
				where.append(
						" AND ua.id in ( SELECT uad.id FROM Normativa nor LEFT JOIN nor.unidadesnormativas noruas join noruas.unidadAdministrativa uad where nor.id = :normativa ) ");
				parametros.put("normativa", codigoNormativa);
			}

			// Buscamos las ua que estén relacionadas con una seccion (la seccion se
			// relaciona con ua a través de la ficha)
			if (!StringUtils.isEmpty(codigoSeccion)) {
				where.append(
						" AND ua.id in  (SELECT fua.unidadAdministrativa.id FROM  FichaUA AS fua WHERE fua.seccion.id = :seccion )  ");
				parametros.put("seccion", codigoSeccion);
			}

			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(),
					selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Consulta el código de dir3 de la UA, si tiene codigo dir3 retorna ese código,
	 * si no busca el cod dir 3 en el padre. -- retorna vacio si no encuentra codigo
	 * dir 3 y no tiene padre, sino retona el cod dir 3 actual o el de su antecesor
	 * mas cercano.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public String consultaCodigoDir3(final Long id) {

		String resultado = "";
		final Session session = getSession();
		try {

			final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);

			if (!StringUtils.isEmpty(ua.getCodigoDIR3())) {
				resultado = ua.getCodigoDIR3();
			} else {
				UnidadAdministrativa padre = ua.getPadre();

				while (padre != null && StringUtils.isEmpty(padre.getCodigoDIR3()) && !padre.isRaiz()
						&& padre != null) {
					padre = padre.getPadre();
				}

				if (padre != null && !StringUtils.isEmpty(padre.getCodigoDIR3())) {
					resultado = padre.getCodigoDIR3();
				}
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		return resultado;
	}

	/**
	 * Obtiene el padre UA que tenga DIR3.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public UnidadAdministrativa obtenerPadreDir3(final Long id) {

		final UnidadAdministrativa resultado = null;
		final Session session = getSession();
		try {
			if (id != null) {
				final UnidadAdministrativa ua = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, id);
				obtenerPadreDir3(ua, new ArrayList<Long>());
			}
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		return resultado;
	}

	private UnidadAdministrativa obtenerPadreDir3(final UnidadAdministrativa servicioResponsable,
			final List<Long> idAntecesores) {
		if (servicioResponsable == null) {
			return null;
		} else {
			if (idAntecesores.contains(servicioResponsable.getId())) {
				log.error("Se ha producido un ciclo en getPadreDir3 con el id:" + servicioResponsable.getId());
				return null;
			} else {
				if (servicioResponsable.getCodigoDIR3() != null && !servicioResponsable.getCodigoDIR3().isEmpty()) {
					return servicioResponsable;
				} else {
					idAntecesores.add(servicioResponsable.getId());
					return obtenerPadreDir3(servicioResponsable.getPadre(), idAntecesores);
				}
			}
		}

	}
}
