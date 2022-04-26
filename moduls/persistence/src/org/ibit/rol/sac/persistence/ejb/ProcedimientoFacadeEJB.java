package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Historico;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MensajeEmail;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Procedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.util.POUtils;
import org.ibit.rol.sac.persistence.util.RolsacPropertiesUtil;
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

/**
 * SessionBean para mantener y consultar Procedimientos
 *
 * TODO (enricjaen@dgtic) 03.03.2011 Aquesta clase te mes de 1000 linias de codi
 * i 47 procediments. Te masses responsabilitats, que haurien de dividir-se. Per
 * exemple: - insertar, borrar procediment - buscar procediment - ordenar
 * procediments - actualitzar a altres administracions
 *
 *
 * @ejb.bean name="sac/persistence/ProcedimientoFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.ProcedimientoFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public abstract class ProcedimientoFacadeEJB extends HibernateEJB implements ProcedimientoDelegateI {

	private static final long serialVersionUID = -7754045272386270651L;

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
	 * @deprecated No se usa. Autoriza la creacion de un procedimiento
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Deprecated
	public boolean autorizaCrearProcedimiento(final Integer validacionProcedimiento) throws SecurityException {
		return !(validacionProcedimiento.equals(Validacion.PUBLICA) && !userIsSuper());
	}

	/**
	 * @deprecated No se usa. Autoriza la modificacion de un procedimiento
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Deprecated
	public boolean autorizaModificarProcedimiento(final Long idProcedimiento) throws SecurityException {
		return (getAccesoManager().tieneAccesoProcedimiento(idProcedimiento));
	}

	/**
	 * Actualiza docs de un Procedimiento.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 *
	 * @param idProcedimiento
	 *            El id procedimiento.
	 * @param traducciones
	 *            Las traducciones para actualizar
	 * @param archivosABorrar
	 *            ids de archivos a borrar
	 *
	 * @throws DelegateException
	 */
	@Override
	public void grabarArchivos(final Long idProcedimiento, final Map<String, TraduccionProcedimientoLocal> traducciones,
			final List<Long> archivosAborrar) {
		Session session = null;
		try {
			session = getSession();
			final ProcedimientoLocal procedimientoBD = (ProcedimientoLocal) session.load(ProcedimientoLocal.class,
					idProcedimiento);

			for (final String idioma : traducciones.keySet()) {
				if (procedimientoBD.getTraduccion(idioma) != null) {
					Hibernate.initialize(((TraduccionProcedimientoLocal) procedimientoBD.getTraduccion(idioma))
							.getLopdInfoAdicional());
				}

				final TraduccionProcedimientoLocal trad = traducciones.get(idioma);
				if ((procedimientoBD.getTraduccion(idioma) != null
						&& ((TraduccionProcedimientoLocal) procedimientoBD.getTraduccion(idioma))
								.getLopdInfoAdicional() == null)
						|| trad.getLopdInfoAdicional() == null) {
					((TraduccionProcedimientoLocal) procedimientoBD.getTraduccion(idioma))
							.setLopdInfoAdicional(trad.getLopdInfoAdicional());
				} else {
					if ((procedimientoBD.getTraduccion(idioma)) == null) {
						procedimientoBD.setTraduccion(idioma, new TraduccionProcedimientoLocal());
					}
					final Archivo archiv = ((TraduccionProcedimientoLocal) procedimientoBD.getTraduccion(idioma))
							.getLopdInfoAdicional();
					if (archivosAborrar.contains(archiv.getId())) {
						archivosAborrar.remove(archiv.getId());
					}
					archiv.setDatos(trad.getLopdInfoAdicional().getDatos());
					archiv.setMime(trad.getLopdInfoAdicional().getMime());
					archiv.setNombre(trad.getLopdInfoAdicional().getNombre());
					archiv.setPeso(trad.getLopdInfoAdicional().getPeso());
					archiv.setDatos(trad.getLopdInfoAdicional().getDatos());
				}

			}

			session.update(procedimientoBD);
			session.flush();

			if (archivosAborrar != null) {
				for (final Long idArchivo : archivosAborrar) {
					final Archivo archivo = (Archivo) session.load(Archivo.class, idArchivo);
					session.delete(archivo);
					session.flush();
				}
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
	 * Obtiene la info adicional de un proc.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de un proc.
	 *
	 * @return Devuelve <code>Archivo</code> que contiene la info adicional.
	 */
	@Override
	public Archivo obtenerProcInfoAdicional(final Long id, final String idioma) {

		final Session session = getSession();

		try {

			final Archivo archivo = (Archivo) session.load(Archivo.class, id);
			Hibernate.initialize(archivo);
			return archivo;
		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Crea o actualiza un Procedimiento.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 *
	 * @param procedimiento
	 *            Indica el procedimiento a guardar.
	 * @param idUA
	 *            Identificador de la unidad administrativa a la que es asiganda el
	 *            nuevo procedimiento.
	 * @param procedimientoMensaje
	 * @param mensajeEmail
	 *
	 * @return Devuelve el identificador del procedimiento guardado.
	 * @throws DelegateException
	 */
	@Override
	public Long grabarProcedimiento(final ProcedimientoLocal procedimiento, final Long idUA,
			final ProcedimientoMensaje procedimientoMensaje, final MensajeEmail mensajeEmail) throws DelegateException {

		final Session session = getSession();

		try {
			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}

			Date fechaActualizacionBD = new Date();
			/// CHECKS
			if (procedimiento.getId() == null) {

				if (procedimiento.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
					throw new SecurityException("No puede crear un procedimiento público");
				}

			} else {

				if (!getAccesoManager().tieneAccesoProcedimiento(procedimiento.getId())) {
					throw new SecurityException("No tiene acceso al procedimiento");
				}

				final ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(procedimiento.getId());
				procedimientoBD.mergeNormativas(procedimiento);
				session.update(procedimientoBD);
				session.flush();
				session.evict(procedimientoBD); // TODO Faltaría hacer un merge correcto
				fechaActualizacionBD = procedimientoBD.getFechaActualizacion();

			}

			/*
			 * Se alimenta la fecha de actualización de forma automática si no se ha
			 * introducido dato
			 */
			if (procedimiento.getFechaActualizacion() == null
					|| DateUtils.fechasIguales(fechaActualizacionBD, procedimiento.getFechaActualizacion())) {
				procedimiento.setFechaActualizacion(new Date());
			}

			final UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			procedimiento.setUnidadAdministrativa(unidad);

			// #399 Check valores SIA
			checkValoresSIA(procedimiento);

			if (procedimiento.getId() == null) {

				session.save(procedimiento);
				addOperacion(session, procedimiento, Auditoria.INSERTAR);

			} else {

				session.update(procedimiento);
				addOperacion(session, procedimiento, Auditoria.MODIFICAR);

			}

			if (procedimiento.isComun() && procedimiento.getId() != null) {
				actualizarTramiteComun(session, procedimiento.getId(), procedimiento.getOrganResolutori());

			}

			// Generamos el mensaje procedimiento y revisamos los mensajes pendientes
			if (procedimientoMensaje != null) {
				procedimientoMensaje.setIdProcedimiento(procedimiento.getId());
				session.save(procedimientoMensaje);
				session.flush();
			}

			if (mensajeEmail != null) {
				session.save(mensajeEmail);
				session.flush();
			}

			Hibernate.initialize(procedimiento.getTramites());
			Hibernate.initialize(procedimiento.getMaterias());
			Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());

			Actualizador.actualizar(procedimiento);

			session.flush();

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(),
					SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, procedimiento);

			return procedimiento.getId();

		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	private void actualizarTramiteComun(final Session session, final Long idProcedimiento,
			final UnidadAdministrativa organResolutori) throws HibernateException {
		final Query query = session.createQuery("from Tramite t where t.procedimiento.id = :id");
		query.setLong("id", idProcedimiento);

		final List<Tramite> tramites = query.list();
		if (tramites != null) {
			for (final Tramite tramit : tramites) {
				tramit.setOrganCompetent(organResolutori);
				session.update(tramit);
			}
		}
	}

	/**
	 * Check si tiene la info adicional .
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Override
	public boolean checkInfoAdicional(final Long id) throws DelegateException {
		final Session session = getSession();
		boolean resultado = false;
		try {
			final ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
			if (procedimiento.getTraduccion("ca") != null) {
				Hibernate.initialize(
						((TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca")).getLopdInfoAdicional());
			}
			final TraduccionProcedimientoLocal trad = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca");
			resultado = trad != null && trad.getLopdInfoAdicional() != null
					&& trad.getLopdInfoAdicional().getId() != null;
		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);
		}
		return resultado;
	}

	/**
	 * Check si alguna normativa esta derogada.
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Override
	public boolean isNormativaDerogada(final Long id) throws DelegateException {
		final Session session = getSession();
		boolean resultado = false;
		try {
			final ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(id);
			for (final Normativa normativa : procedimientoBD.getNormativas()) {
				if (!normativa.isVigente()) {
					resultado = true;
					break;
				}
			}
		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);
		}
		return resultado;
	}

	/**
	 * Check si alguna normativa esta como no valida..
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Override
	public boolean tieneNormativas(final Long id) throws DelegateException {
		final Session session = getSession();
		boolean resultado = true;
		try {
			final ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(id);
			resultado = procedimientoBD.getNormativas() != null && !procedimientoBD.getNormativas().isEmpty();
		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);
		}
		return resultado;
	}

	/**
	 * Check si alguna normativa esta como no valida..
	 *
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	@Override
	public boolean isNormativaValidas(final Long id) throws DelegateException {
		final Session session = getSession();
		boolean resultado = true;
		try {
			final ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(id);
			for (final Normativa normativa : procedimientoBD.getNormativas()) {
				if (!normativa.isDatosValidos()) {
					resultado = false;
					break;
				}
			}
		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);
		}
		return resultado;
	}

	/**
	 * Checkear valores de SIA.
	 *
	 * @param procedimiento
	 * @throws Exception
	 */
	private void checkValoresSIA(final ProcedimientoLocal procedimiento) throws Exception {

		final boolean isNuloCodSia = StringUtils.isEmpty(procedimiento.getCodigoSIA());
		final boolean isNuloEstSia = StringUtils.isEmpty(procedimiento.getEstadoSIA());
		final boolean isNuloFecSia = (procedimiento.getFechaSIA() == null);

		// Verifica que todos estan rellenados o todos estan nulos
		final boolean correcto = (isNuloCodSia && isNuloEstSia && isNuloFecSia)
				|| (!isNuloCodSia && !isNuloEstSia && !isNuloFecSia);

		if (!correcto) {
			final String messageError = "Verificacion valores SIA: alguno de los atributos no son nulos y otros si. Procedimiento: "
					+ procedimiento.getId() + " - Código SIA: " + procedimiento.getCodigoSIA() + " - Estado SIA: "
					+ procedimiento.getEstadoSIA() + " - Fecha SIA: " + procedimiento.getFechaSIA();
			log.error("Error: " + messageError);
			throw new Exception(messageError);
		}

	}

	// TODO amartin: ¿intentar unificar los dos métodos de guardado en uno?
	/**
	 * Crea o actualiza un Procedimiento.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 *
	 * @param procedimiento
	 *            Indica el procedimiento a guardar.
	 * @param idUA
	 *            Identificador de la unidad administrativa a la que es asiganda el
	 *            nuevo procedimiento.
	 * @param listaTramitesParaBorrar
	 * @param listaIdsTramitesParaActualizar
	 * @param procedimientoMensaje
	 * @param mensajeEmail
	 *
	 * @return Devuelve el identificador del procedimiento guardado.
	 */
	@Override
	public Long grabarProcedimientoConTramites(final ProcedimientoLocal procedimiento, final Long idUA,
			final List listaTramitesParaBorrar, final List listaIdsTramitesParaActualizar,
			final ProcedimientoMensaje procedimientoMensaje, final MensajeEmail mensajeEmail) {

		final Session session = getSession();

		try {

			Date FechaActualizacionBD = new Date();

			if (procedimiento.getId() == null) {

				if (procedimiento.getValidacion().equals(Validacion.PUBLICA) && !userIsSuper()) {
					throw new SecurityException("No puede crear un procedimiento público");
				}

			} else {

				if (!getAccesoManager().tieneAccesoProcedimiento(procedimiento.getId())) {
					throw new SecurityException("No tiene acceso al procedimiento");
				}

				final ProcedimientoLocal procedimientoBD = obtenerProcedimientoNewBack(procedimiento.getId());
				FechaActualizacionBD = procedimientoBD.getFechaActualizacion();

			}

			if (!getAccesoManager().tieneAccesoUnidad(idUA, false)) {
				throw new SecurityException("No tiene acceso a la unidad");
			}

			/*
			 * Se alimenta la fecha de actualización de forma automática si no se ha
			 * introducido dato
			 */
			if (procedimiento.getFechaActualizacion() == null
					|| DateUtils.fechasIguales(FechaActualizacionBD, procedimiento.getFechaActualizacion())) {
				procedimiento.setFechaActualizacion(new Date());
			}

			final UnidadAdministrativa unidad = (UnidadAdministrativa) session.load(UnidadAdministrativa.class, idUA);
			procedimiento.setUnidadAdministrativa(unidad);

			final TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();

			if (listaTramitesParaBorrar != null && listaIdsTramitesParaActualizar != null) {

				// Eliminar los que no han sido quitados de la lista
				for (final Tramite tramite : (List<Tramite>) listaTramitesParaBorrar) {

					// Si se va a borrar un trámite de iniciación con el estado del procedimiento
					// "público",
					// lo impedimos y generamos una excepción.
					if (Procedimiento.ESTADO_PUBLICACION_PUBLICA.equals(procedimiento.getValidacion())
							&& tramite.getFase() == Tramite.INICIACION)
						throw new EJBException(
								"No se puede borrar el trámite de iniciación cuando el estado del procedimiento es público");

					DelegateUtil.getProcedimientoDelegate().eliminarTramite(tramite.getId(), procedimiento.getId());
					tramiteDelegate.borrarTramite(tramite.getId(), procedimiento.getId());

				}

				// Actualizar orden y añadir los trámites
				if (!listaIdsTramitesParaActualizar.isEmpty()) {
					actualizarOrdenTramites(new ArrayList<Long>(listaIdsTramitesParaActualizar));
				}

			}

			// #399 Check valores SIA
			checkValoresSIA(procedimiento);

			if (procedimiento.getId() == null) {

				session.save(procedimiento);
				addOperacion(session, procedimiento, Auditoria.INSERTAR);

			} else {

				session.update(procedimiento);
				addOperacion(session, procedimiento, Auditoria.MODIFICAR);

			}

			session.flush();
			if (procedimiento.isComun()) {
				actualizarTramiteComun(session, procedimiento.getId(), procedimiento.getOrganResolutori());
			}

			// Generamos el mensaje procedimiento
			if (procedimientoMensaje != null) {
				procedimientoMensaje.setIdProcedimiento(procedimiento.getId());
				session.save(procedimientoMensaje);
				session.flush();
			}

			// Generamos email directo si hace falta.
			if (mensajeEmail != null) {
				session.save(mensajeEmail);
				session.flush();
			}

			Hibernate.initialize(procedimiento.getTramites());
			Hibernate.initialize(procedimiento.getMaterias());
			Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());

			Actualizador.actualizar(procedimiento);

			session.flush();

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(),
					SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, procedimiento);

			return procedimiento.getId();

		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Lista todos los procedimientos.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @return Devuelve <code>List</code> de todos los procedimientos
	 */
	@Override
	public List listarProcedimientos() {

		// agarcia: antes era @ejb.permission
		// role-name="${role.system},${role.admin},${role.super},${role.oper}". Pero
		// este metodo debe ser unchecked para permitir accesos via WS
		final Session session = getSession();
		try {
			final Criteria criteri = session.createCriteria(ProcedimientoLocal.class);

			// criteri.setFetchMode("traducciones", FetchMode.EAGER);
			criteri.setCacheable(true);
			final List procedimientosValidos = new ArrayList<ProcedimientoLocal>();
			final List procedimientos = criteri.list();
			for (int i = 0; i < procedimientos.size(); i++) {
				final ProcedimientoLocal procedimiento = (ProcedimientoLocal) procedimientos.get(i);

				if (!procedimiento.getTraduccionMap().isEmpty()) {
					procedimientosValidos.add(procedimiento);
					Hibernate.initialize(procedimiento.getMaterias());
				}
			}

			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(procedimientosValidos, new ProcedimientoLocal());

			return procedimientosValidos;

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * @deprecated Se usa desde API v1 Lista todos los procedimientos.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public List listarProcedimientosPublicos() {

		// agarcia: antes era @ejb.permission
		// role-name="${role.system},${role.admin},${role.super},${role.oper}"
		final Session session = this.getSession();
		try {
			final Criteria criteri = session.createCriteria(ProcedimientoLocal.class);
			// criteri.setFetchMode("traducciones", FetchMode.EAGER);
			criteri.setCacheable(true);
			final List procsvalidos = new ArrayList<ProcedimientoLocal>();
			final List procs = criteri.list();
			for (int i = 0; i < procs.size(); i++) {
				final ProcedimientoLocal pl = (ProcedimientoLocal) procs.get(i);
				if (!pl.getTraduccionMap().isEmpty() && this.publico(pl)) {
					procsvalidos.add(pl);
					Hibernate.initialize(pl.getMaterias());// agarcia
				}
			}

			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(procsvalidos, new ProcedimientoLocal());

			return procsvalidos;

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			this.close(session);
		}
	}

	/**
	 * Obtiene el nombre de procedimiento Local.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idProc
	 *            Identificador del procedimiento.
	 * @param catalan
	 *            Indica si es catalan.
	 * @return Devuelve el nombre del <code>ProcedimientoLocal</code> solicitado.
	 */
	@Override
	public String obtenerNombreProcedimiento(final Long idProc, final boolean catalan) {

		final Session session = getSession();
		String nombre = null;
		ProcedimientoLocal procedimiento = null;
		try {
			procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, idProc);

			TraduccionProcedimientoLocal trad = null;
			if (catalan) {
				trad = ((TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca"));
			} else {
				trad = ((TraduccionProcedimientoLocal) procedimiento.getTraduccion("es"));
				if (trad == null || trad.getNombre() == null || trad.getNombre().isEmpty()) {
					trad = ((TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca"));
				}
			}
			nombre = trad.getNombre();
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
		return nombre;
	}

	/**
	 * Obtiene un procedimiento Local.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id
	 *            Identificador del procedimiento.
	 * @return Devuelve <code>ProcedimientoLocal</code> solicitado.
	 */
	@Override
	public ProcedimientoLocal obtenerProcedimiento(final Long id) {

		final Session session = getSession();
		ProcedimientoLocal procedimiento = null;
		try {
			procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
			if (visible(procedimiento)) {

				Hibernate.initialize(procedimiento.getDocumentos());
				for (final Documento d : procedimiento.getDocumentos()) {
					if (d == null) {
						continue; // por algun motivo, en ocasiones los documentos en la coleccion son nulos
					}

					final Map<String, Traduccion> mapaTraduccions = d.getTraduccionMap();
					final Set<String> idiomes = mapaTraduccions.keySet();
					for (final Iterator<String> i = idiomes.iterator(); i.hasNext();) {
						final TraduccionDocumento trad = (TraduccionDocumento) d.getTraduccion(i.next());
						if (trad != null) {
							Hibernate.initialize(trad.getArchivo());
						}
					}
				}

				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());

				Hibernate.initialize(procedimiento.getNormativas());
				for (final Normativa n : procedimiento.getNormativas()) {
					final Map<String, Traduccion> mapaTraduccions = n.getTraduccionMap();
					final Set<String> idiomes = mapaTraduccions.keySet();
					for (final Iterator<String> i = idiomes.iterator(); i.hasNext();) {
						final TraduccionNormativa trad = (TraduccionNormativa) n.getTraduccion(i.next());
						if (trad != null) {
							Hibernate.initialize(trad.getArchivo());
						}
					}
				}

				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa().getHijos());
				Hibernate.initialize(procedimiento.getOrganResolutori());
				if (procedimiento.getOrganResolutori() != null) {
					Hibernate.initialize(procedimiento.getOrganResolutori().getHijos());
				}

				Hibernate.initialize(procedimiento.getUnidadAdministrativa().getUnidadesNormativas());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa().getEdificios());

				if (procedimiento.getUnidadAdministrativa().getUnidadesNormativas() != null) {
					for (final UnidadNormativa n : procedimiento.getUnidadAdministrativa().getUnidadesNormativas()) {
						final Map<String, Traduccion> mapaTraduccions = n.getNormativa().getTraduccionMap();
						final Set<String> idiomes = mapaTraduccions.keySet();
						for (final Iterator<String> i = idiomes.iterator(); i.hasNext();) {
							final TraduccionNormativa trad = (TraduccionNormativa) n.getNormativa()
									.getTraduccion(i.next());
							if (trad != null) {
								Hibernate.initialize(trad.getArchivo());
							}
						}
					}
				}

				Hibernate.initialize(procedimiento.getTramites());
				for (final Tramite t : procedimiento.getTramites()) {
					if (t == null)
						continue;

					Hibernate.initialize(t.getFormularios());
					Hibernate.initialize(t.getDocsInformatius());
					Hibernate.initialize(t.getTaxes());
					Hibernate.initialize(t.getOrganCompetent());
					for (final DocumentTramit dt : t.getDocsInformatius()) {
						Hibernate.initialize(dt.getArchivo());
						final Map<String, Traduccion> mapaTraduccions = dt.getTraduccionMap();
						final Set<String> idiomes = mapaTraduccions.keySet();
						for (final Iterator<String> i = idiomes.iterator(); i.hasNext();) {
							final TraduccionDocumento trad = (TraduccionDocumento) dt.getTraduccion(i.next());
							if (trad != null)
								Hibernate.initialize(trad.getArchivo());
						}
					}

					for (final DocumentTramit df : t.getFormularios()) {
						Hibernate.initialize(df.getArchivo());
						final Map<String, Traduccion> mapaTraduccions = df.getTraduccionMap();
						final Set<String> idiomes = mapaTraduccions.keySet();
						for (final Iterator<String> i = idiomes.iterator(); i.hasNext();) {
							final TraduccionDocumento trad = (TraduccionDocumento) df.getTraduccion(i.next());
							if (trad != null)
								Hibernate.initialize(trad.getArchivo());
						}
					}
				}

				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getIniciacion());
				Hibernate.initialize(procedimiento.getFamilia());

			} else {
				throw new SecurityException("El procedimiento no es visible");
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

		// Esto no está funcionando bien...
		// ----------------------------------------------------------------------
		// ArrayList listaOrdenada = new ArrayList(procedimiento.getDocumentos());
		// Comparator comp = new DocsProcedimientoComparator();
		// Collections.sort(listaOrdenada, comp);
		// ----------------------------------------------------------------------

		// Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
		final List procs = new ArrayList(0);
		for (final Documento documento : procedimiento.getDocumentos()) {
			if (documento != null) {
				procs.add(documento);
			}
		}
		Collections.sort(procs, new Documento());
		procedimiento.setDocumentos(procs);

		// Ordenamos las materias por el campo id
		final List mats = new ArrayList(procedimiento.getMaterias());
		Collections.sort(mats, new Materia());
		procedimiento.setMaterias(new HashSet<Materia>(mats));

		// Ordenamos las normativas por el campo id
		final List norms = new ArrayList(procedimiento.getNormativas());
		Collections.sort(norms, new Normativa());
		procedimiento.setNormativas(new HashSet<Normativa>(norms));

		/*
		 * TODO: error de compilacion tras el merge con 177 //Ordenamos los normativas
		 * por el campo id List tramites = new ArrayList(procedimiento.getTramites());
		 * Collections.sort(tramites, new Tramite()); procedimiento.setTramites(new
		 * HashSet<Tramite>(tramites));
		 */

		// Ordenamos los Hechos vitales procedimientos por el campo orden (si nulo,
		// ordena por el campo id)
		final List hechosVitales = new ArrayList(procedimiento.getHechosVitalesProcedimientos());
		Collections.sort(hechosVitales);
		procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>(hechosVitales));

		// log.debug("##################################################################################################");
		// log.debug("ObtenerProcedimiento: " + id.intValue());
		// log.debug("Id Unidad Administrativa: " +
		// procedimiento.getUnidadAdministrativa().getId().intValue());
		// log.debug("Tramites: " + procedimiento.getTramites().size());
		// log.debug("Documentos: " + procedimiento.getDocumentos().size());
		// log.debug("Id Familia: " + procedimiento.getFamilia().getId().intValue());
		// log.debug("Ventana: " + procedimiento.getVentana());
		// log.debug("Materias: " + procedimiento.getMaterias().size());
		// log.debug("Normativas: " + procedimiento.getNormativas().size());
		// log.debug("Id Iniciacion: " + procedimiento.getIniciacion().getId());
		// log.debug("Responsable: " + procedimiento.getResponsable());
		// log.debug("Indicador: " + procedimiento.getIndicador());
		// log.debug("Info: " + procedimiento.getInfo());
		// log.debug("Signatura: " + procedimiento.getSignatura());
		// log.debug("Url: " + procedimiento.getUrl());
		// log.debug("Hechos Vitales: " +
		// procedimiento.getHechosVitalesProcedimientos().size());
		// log.debug("##################################################################################################");

		return procedimiento;
	}

	/**
	 * Obtiene un procedimiento Local.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public ProcedimientoLocal obtenerProcedimientoNewBack(final Long id) {

		final Session session = getSession();
		ProcedimientoLocal procedimiento = null;
		try {
			procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);

			if (visible(procedimiento)) {
				Hibernate.initialize(procedimiento.getDocumentos());
				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa().getHijos());
				Hibernate.initialize(procedimiento.getOrganResolutori());
				Hibernate.initialize(procedimiento.getServicioResponsable());

				if (procedimiento.getOrganResolutori() != null) {
					Hibernate.initialize(procedimiento.getOrganResolutori().getHijos());
				}

				if (procedimiento.getServicioResponsable() != null) {
					Hibernate.initialize(procedimiento.getServicioResponsable().getHijos());
				}

				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getIniciacion());
				Hibernate.initialize(procedimiento.getFamilia());

				if (procedimiento.getTraducciones() != null) {
					for (final String idioma : procedimiento.getTraducciones().keySet()) {
						if (((TraduccionProcedimientoLocal) procedimiento.getTraduccion(idioma)) != null
								&& ((TraduccionProcedimientoLocal) procedimiento.getTraduccion(idioma))
										.getLopdInfoAdicional() != null) {
							Hibernate.initialize(((TraduccionProcedimientoLocal) procedimiento.getTraduccion(idioma))
									.getLopdInfoAdicional());
						}
					}
				}
			} else {
				throw new SecurityException("El procedimiento no es visible");
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

		// Ordenamos los documentos por el campo orden (si nulo, ordena por el campo id)
		final List procs = new ArrayList(0);
		for (final Documento documento : procedimiento.getDocumentos()) {
			if (documento != null) {
				procs.add(documento);
			}
		}
		Collections.sort(procs, new Documento());
		procedimiento.setDocumentos(procs);

		// Ordenamos las materias por el campo id
		final List mats = new ArrayList(procedimiento.getMaterias());
		Collections.sort(mats, new Materia());
		procedimiento.setMaterias(new HashSet<Materia>(mats));

		// Ordenamos las normativas por el campo id
		final List norms = new ArrayList(procedimiento.getNormativas());
		Collections.sort(norms, new Normativa());
		procedimiento.setNormativas(new HashSet<Normativa>(norms));

		// Ordenamos los Hechos vitales procedimientos por el campo orden (si nulo,
		// ordena por el campo id)
		final List hechosVitales = new ArrayList(procedimiento.getHechosVitalesProcedimientos());
		Collections.sort(hechosVitales);
		procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>(hechosVitales));

		// Eliminamos los nulls del ArrayList de la lista de trámites
		final List<Tramite> listaTramites = new ArrayList<Tramite>(0);
		for (final Tramite tramite : procedimiento.getTramites()) {
			if (tramite != null) {
				listaTramites.add(tramite);
			}
		}
		procedimiento.setTramites(listaTramites);

		return procedimiento;
	}

	/**
	 * Comprueba si un procedimiento tiene como estado de publicación
	 * org.ibit.rol.sac.model.Procedimiento.ESTADO_PUBLICACION_PUBLICA.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public boolean isProcedimientoConEstadoPublicacionPublica(final Long idProcedimiento) {

		final Session session = getSession();
		ProcedimientoLocal procedimiento = null;

		try {

			procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, idProcedimiento);

			return Procedimiento.ESTADO_PUBLICACION_PUBLICA.equals(procedimiento.getValidacion());

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Valida si existe un tramite de inicio distinto a tramiteId para el
	 * procedimiento Local procId.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param idProcedimiento
	 *            Identificador del procedimiento.
	 * @param idTramite
	 *            Identificador del trámite.
	 * @return Devuelve <code>true</code> si existe mas de un trámite con el mismo
	 *         identificador asociado a un determinado procedimiento.
	 */
	@Override
	public boolean existeOtroTramiteInicioProcedimiento(final Long idProcedimiento, final Long idTramite) {

		final Session session = getSession();
		try {
			final StringBuilder consulta = new StringBuilder(
					" select count(t.id) from Tramite t where t.procedimiento.id = :idProcedimiento and t.fase = :fase ");

			if (idTramite != null) {
				consulta.append(" and t.id != :tramiteId");
			}

			final Query query = session.createQuery(consulta.toString());
			query.setLong("fase", 1); // 1 = fase de inicio o inicializacion.
			query.setLong("idProcedimiento", idProcedimiento);

			if (idTramite != null) {
				query.setLong("tramiteId", idTramite);
			}

			final Integer numTramites = (Integer) query.uniqueResult();

			return numTramites > 0;

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * @deprecated Se usa desde el back antiguo Busca todas los Procedimientos que
	 *             cumplen los criterios de busqueda
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public List buscarProcedimientos(final Map parametros, final Map traduccion) {

		final Session session = getSession();
		try {
			if (!userIsOper()) {
				parametros.put("validacion", Validacion.PUBLICA);
			}

			final List params = new ArrayList();
			final String sQuery = populateQuery(parametros, traduccion, params);

			// Eliminado "left join fetch" por problemas en el cache de traducciones.
			final Query query = session.createQuery("select procedimiento from ProcedimientoLocal as procedimiento "
					+ ", procedimiento.traducciones as trad " + sQuery);
			for (int i = 0; i < params.size(); i++) {
				final String o = (String) params.get(i);
				query.setString(i, o);
			}

			final List<ProcedimientoLocal> procedimientos = query.list();

			if (!userIsOper()) {
				// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
				// id)
				Collections.sort(procedimientos, new ProcedimientoLocal());
				return procedimientos;
			} else {
				final List procedimientosAcceso = new ArrayList();
				final Usuario usuario = getUsuario(session);
				for (int i = 0; i < procedimientos.size(); i++) {
					final ProcedimientoLocal procedimiento = procedimientos.get(i);
					if (tieneAcceso(usuario, procedimiento)) {
						procedimientosAcceso.add(procedimiento);
					}
				}
				// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
				// id)
				Collections.sort(procedimientosAcceso, new ProcedimientoLocal());
				return procedimientosAcceso;
			}
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * @deprecated No se usa Busca todas los Procedimientos que cumplen los
	 *             criterios de busqueda del nuevo back (rolsacback).
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public ResultadoBusqueda buscadorProcedimientos(final Map parametros, final Map traduccion,
			final UnidadAdministrativa ua, final boolean uaFilles, final boolean uaMeves, final Long materia,
			final Long fetVital, final Long publicObjectiu, final String pagina, final String resultats,
			final int visible, final String en_plazo, final String telematico) {

		final Session session = getSession();
		try {
			if (!userIsOper()) {
				parametros.put("validacion", Validacion.PUBLICA);
			}

			final List params = new ArrayList();
			String i18nQuery = "";

			if (traduccion.get("idioma") != null) {
				i18nQuery = populateQuery(parametros, traduccion, params);

			} else {
				final String paramsQuery = populateQuery(parametros, new HashMap(), params);
				if (paramsQuery.length() == 0) {
					i18nQuery += " where ";
				} else {
					i18nQuery += paramsQuery + " and ";
				}
				i18nQuery += "(" + i18nPopulateQuery(traduccion, params) + ")";
			}

			final Long idUA = (ua != null) ? ua.getId() : null;
			String uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, uaFilles, uaMeves);

			if (!StringUtils.isEmpty(uaQuery)) {
				uaQuery = " and procedimiento.unidadAdministrativa.id in (" + uaQuery + ")";
			}

			String from = "from ProcedimientoLocal as procedimiento, ";
			String where = "";

			if (telematico != null && (telematico.equals("1") || telematico.equals("0"))) {

				where += "and procedimiento.id in ( select tra.procedimiento from Tramite as tra where tra.telematico = "
						+ ApiRestUtils.intToBool(telematico) + " )";
			}

			if (en_plazo != null && !en_plazo.equals("")) {
				if (en_plazo.equals("1")) {
					where += "and procedimiento.id in ";
				} else if (en_plazo.equals("0")) {
					where += "and procedimiento.id not in ";
				}

				where += "( select tra.procedimiento from Tramite as tra where tra.fase = 1 ";
				where += "and ( " + DateUtils.stringFechaAhoraBBDD()
						+ " < tra.dataTancament or tra.dataTancament is null) ";
				where += "and ( " + DateUtils.stringFechaAhoraBBDD() + " > tra.dataInici or tra.dataInici is null) ) ";
			}

			if (materia != null) {
				where += " and procedimiento.id in ( select procsLocales.id "
						+ "from Materia as mat, mat.procedimientosLocales as procsLocales " + "where mat.id = "
						+ materia + ")";
			}

			if (fetVital != null) {
				from += "procedimiento.hechosVitalesProcedimientos as hechosVit, ";
				where += " and hechosVit.hechoVital.id = " + fetVital;
			}

			if (publicObjectiu != null) {
				from += "procedimiento.publicosObjetivo as pubsObj, ";
				where += " and pubsObj.id = " + publicObjectiu;
			}

			if (visible == 1) {
				where += " and ( " + DateUtils.stringFechaAhoraBBDD()
						+ " < procedimiento.fechaCaducidad or procedimiento.fechaCaducidad is null) ";
				where += " and ( " + DateUtils.stringFechaAhoraBBDD()
						+ " > procedimiento.fechaPublicacion or procedimiento.fechaPublicacion is null) ";
			} else if (visible == 2) {
				where += " and ( " + DateUtils.stringFechaAhoraBBDD() + " > procedimiento.fechaCaducidad or "
						+ DateUtils.stringFechaAhoraBBDD()
						+ " < procedimiento.fechaPublicacion or procedimiento.validacion = 2 or procedimiento.validacion = 3) ";
			}

			if (userIsOper()) {
				// Filtrar por el acceso del usuario

				// tieneAccesoValidable
				if (!userIsSuper()) {
					where += " and procedimiento.validacion = " + Validacion.INTERNA;
				}

				// tieneAcceso
				if (!userIsSystem()) {

					if (StringUtils.isEmpty(uaQuery)) { // Se está buscando en todas las unidades orgánicas
						uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(null, true, true);
						if (!StringUtils.isEmpty(uaQuery)) {
							uaQuery = " and procedimiento.unidadAdministrativa.id in (" + uaQuery + ")";
						} else {
							// Esto significa que el usuario no tiene ninguna unidad administrativa
							// configurada, y
							// no es system. Por lo que en realidad no hace falta ejecutar nada, sino más
							// bien devolver
							// un resultado vacío

							// uaQuery = " and procedimiento.unidadAdministrativa.id in (-1)";
							final ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
							resultadoBusqueda.setTotalResultados(0);
							resultadoBusqueda.setListaResultados(new ArrayList<ProcedimientoLocal>());

							return resultadoBusqueda;
						}

					}
				}

			}

			where += " and index(tradFam) = 'ca' ";
			final String select = "select new ProcedimientoLocal(procedimiento.id, trad.nombre, procedimiento.validacion, "
					+ "procedimiento.fechaActualizacion, procedimiento.fechaCaducidad, procedimiento.fechaPublicacion, "
					+ "tradFam.nombre, index(trad), procedimiento.unidadAdministrativa ) ";

			final String selectCount = "select count(*) ";

			final String restoQuery = " procedimiento.traducciones as trad, procedimiento.familia as fam, fam.traducciones as tradFam "
					+ i18nQuery + uaQuery + where;
			final String orderBy = " order by procedimiento." + parametros.get("ordreCamp") + " "
					+ parametros.get("ordreTipus");

			final String queryStr = select + from + restoQuery + orderBy;
			final String queryCountStr = selectCount + from + restoQuery;

			final Query query = session.createQuery(queryStr);
			final Query queryCount = session.createQuery(queryCountStr);

			for (int i = 0; i < params.size(); i++) {
				final String o = (String) params.get(i);
				query.setString(i, o);
				queryCount.setString(i, o);
			}

			final int resultadosMax = new Integer(resultats).intValue();
			final int primerResultado = new Integer(pagina).intValue() * resultadosMax;

			final ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

			resultadoBusqueda.setTotalResultados((Integer) queryCount.uniqueResult());

			if (resultadosMax != RESULTATS_CERCA_TOTS) {
				query.setFirstResult(primerResultado);
				query.setMaxResults(resultadosMax);
			}

			resultadoBusqueda.setListaResultados(query.list());

			return resultadoBusqueda;

		} catch (final DelegateException de) {
			throw new EJBException(de);
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Busca todas los Procedimientos que cumplen los criterios de busqueda del
	 * nuevo back (rolsacback).
	 *
	 * @param bc
	 *            Indica los criterios para la consulta.
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public ResultadoBusqueda buscadorProcedimientos(final BuscadorProcedimientoCriteria bc) {

		final Session session = getSession();
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		try {
			final PaginacionCriteria paginacion = bc.getPaginacion();

			final StringBuilder from = new StringBuilder(
					" from  ProcedimientoLocal as procedimiento,  procedimiento.traducciones as trad");
			if (bc.getIdTramitePlantilla() != null) {
				from.append(", procedimiento.tramites as tram ");
			}
			final StringBuilder where = new StringBuilder("where index(trad) = :idioma ");
			StringBuilder consulta;
			if (bc.getSoloId()) {
				if ("familia".equals(paginacion.getPropiedadDeOrdenacion())) {
					consulta = new StringBuilder("select distinct procedimiento.id, tradFam.nombre from where");
				} else {
					consulta = new StringBuilder("select distinct procedimiento.id, procedimiento."
							+ paginacion.getPropiedadDeOrdenacion() + " from where");
				}

				if (bc.getProcedimiento().getFamilia().getId() == null
						|| bc.getProcedimiento().getFamilia().getId() != -1) {
					where.append("and index(tradFam) = :idioma ");
					from.append(", procedimiento.familia as fam, fam.traducciones as tradFam");
				}
			} else {
				consulta = new StringBuilder(
						"select new ProcedimientoLocal(procedimiento.id, trad.nombre, procedimiento.validacion, procedimiento.fechaActualizacion, procedimiento.comun,"
								+ " trad.lopdFinalidad, trad.lopdDestinatario, trad.lopdDerechos, leg, infoAdicional, ");

				if (bc.getProcedimiento().getFamilia().getId() == null
						|| bc.getProcedimiento().getFamilia().getId() != -1) {
					consulta.append(
							"procedimiento.fechaCaducidad, procedimiento.fechaPublicacion, tradFam.nombre, index(trad), procedimiento.unidadAdministrativa ) ");
					where.append("and index(tradFam) = :idioma ");
					from.append(
							", procedimiento.familia as fam, fam.traducciones as tradFam left outer join procedimiento.lopdLegitimacion leg left outer join trad.lopdInfoAdicional infoAdicional ");
				} else { // Para poder buscar proc sin familia
					// TODO en el constructor s esta pasando familia con el nombre del proc
					consulta.append(
							"procedimiento.fechaCaducidad, procedimiento.fechaPublicacion,trad.nombre , index(trad), procedimiento.unidadAdministrativa ) ");

				}

				consulta.append("from where");
			}
			where.append("and procedimiento.unidadAdministrativa.id in (:UA) ");

			if (bc.getProcedimiento().getId() != null)
				where.append(" and procedimiento.id = :id ");

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getNombreProcedimiento()))
				where.append(" and upper(trad.nombre) like upper(:nombreProcedimiento) ");

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getIndicador()))
				where.append(" and procedimiento.indicador = :indicador ");

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getVentanillaUnica()))
				where.append(" and procedimiento.ventanillaUnica = :ventanillaUnica ");

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getTramite())) {
				// where.append(" and procedimiento.tramites.id IN (:tramite) ");
				where.append(
						" and procedimiento.id IN ( select tra.procedimiento from Tramite as tra where tra.id = :tramite ) ");
			}

			if (bc.getIdPlataforma() != null) {
				where.append(
						" and procedimiento.id IN ( select tra.procedimiento from Tramite as tra where tra.plataforma.id = :idPlataforma ) ");
			}

			if (bc.getIdTramitePlantilla() != null) {
				where.append(" and tram.tramitePlantilla.id = :idTramitePlantilla ");
			}

			if (bc.getPdtValidar() != null) {
				where.append(" and procedimiento.pendienteValidar = :pdtValidar ");
			}
			if (bc.getMensajePorLeer() != null) {

				// Las opciones en catalegProcedimientos:
				// <option value="0"><spring:message code='txt.mensajesPdtSin'/></option>
				// <option value="1"><spring:message code='txt.mensajesPdt'/></option>
				// <option value="2"><spring:message code='txt.mensajesPdtSupervisor'/></option>
				// <option value="3"><spring:message code='txt.mensajesPdtGestor'/></option>
				if (bc.getMensajePorLeer() == 0) {
					where.append(
							" and procedimiento.id  not in ( select procMensa.idProcedimiento from ProcedimientoMensaje procMensa where procMensa.leido = 0) ");
				} else if (bc.getMensajePorLeer() == 1) {
					where.append(
							" and procedimiento.id in ( select procMensa.idProcedimiento from ProcedimientoMensaje procMensa where procMensa.leido = 0) ");
				} else if (bc.getMensajePorLeer() == 2) {
					where.append(
							" and procedimiento.id  in ( select procMensa.idProcedimiento from ProcedimientoMensaje procMensa where procMensa.leido = 0 and procMensa.gestor = 1) ");
				} else if (bc.getMensajePorLeer() == 3) {
					where.append(
							" and procedimiento.id  in ( select procMensa.idProcedimiento from ProcedimientoMensaje procMensa where procMensa.leido = 0 and procMensa.gestor = 0) ");
				}
			}
			if (bc.getEstado() != null) {
				where.append(" and procedimiento.validacion = :estado ");
			}

			if (bc.getComun() != null) {
				where.append(" and procedimiento.comun = " + ApiRestUtils.intToBool(bc.getComun()) + " ");
			}

			if (bc.getProcedimiento().getFamilia() != null && bc.getProcedimiento().getFamilia().getId() != null) {
				if (bc.getProcedimiento().getFamilia().getId() == -1) {
					where.append(" and procedimiento.familia is null ");

				} else {

					where.append(" and procedimiento.familia.id = :familia ");
				}
			}

			if (bc.getProcedimiento().getIniciacion() != null
					&& bc.getProcedimiento().getIniciacion().getId() != null) {
				if (bc.getProcedimiento().getIniciacion().getId() == -1) {
					where.append(" and procedimiento.iniciacion is null ");
				} else {
					where.append(" and procedimiento.iniciacion.id = :iniciacion ");
				}
			}

			if (bc.getIdPublicoObjetivo() != null) {
				if (bc.getIdPublicoObjetivo() == -1) {// Seleccionado cap
					where.append(" and size(procedimiento.publicosObjetivo)=0 ");
				} else {
					where.append(" and pubsObj.id = :idPublicoObjetivo ");
					from.append(", procedimiento.publicosObjetivo as pubsObj");
				}

			}

			if (bc.getIdMateria() != null) {
				if (bc.getIdMateria() == -1) {
					where.append(
							" and procedimiento.id  not in ( select procsLocales.id from Materia as mat, mat.procedimientosLocales as procsLocales where mat.id is not null ) ");

				} else {
					where.append(
							" and procedimiento.id in ( select procsLocales.id from Materia as mat, mat.procedimientosLocales as procsLocales where mat.id = :idMateria ) ");
				}
			}

			if (bc.getProcedimiento().getSilencio() != null && bc.getProcedimiento().getSilencio().getId() != null) {
				if (bc.getProcedimiento().getSilencio().getId() == -1) {
					where.append(" and procedimiento.silencio is null ");
				} else {
					where.append(" and procedimiento.silencio.id = :idSilencio ");
				}
			}

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getCodigoSIA())) {

				where.append(" and procedimiento.codigoSIA like upper(:codSIA) ");
			}

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getEstadoSIA())) {
				if (bc.getProcedimiento().getEstadoSIA().equals("-1")) {
					where.append(" and procedimiento.estadoSIA is null ");
				} else if (bc.getProcedimiento().getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)) {
					where.append(" and procedimiento.estadoSIA not is null and procedimiento.estadoSIA <> :estadoSIA ");
				} else {
					where.append(" and procedimiento.estadoSIA = :estadoSIA ");
				}
			}

			if (bc.getIdHechoVital() != null) {

				where.append(" and hechosVit.hechoVital.id = :idHechoVital ");
				from.append(", procedimiento.hechosVitalesProcedimientos as hechosVit");

			}

			if (bc.getTelematico() != null) {
				final String telematico = bc.getTelematico() ? "1" : "0";
				where.append(
						"and procedimiento.id in ( select tra.procedimiento from Tramite as tra where tra.telematico = "
								+ ApiRestUtils.intToBool(telematico) + " )");
			}

			if (bc.getEnPlazo() != null) {

				if (bc.getEnPlazo())
					where.append(" and procedimiento.id in ");

				else if (!bc.getEnPlazo())
					where.append(" and procedimiento.id not in ");

				where.append(" ( select tra.procedimiento from Tramite as tra where tra.procedimiento is not null ");
				where.append("and ( " + DateUtils.stringFechaAhoraBBDD()
						+ " < tra.dataTancament or tra.dataTancament is null) ");
				where.append(
						"and ( " + DateUtils.stringFechaAhoraBBDD() + " > tra.dataInici or tra.dataInici is null) ) ");

			}

			if (bc.getVisibilidad() == Validacion.PUBLICA) {
				where.append(" and ( " + DateUtils.stringFechaAhoraBBDD()
						+ " < procedimiento.fechaCaducidad or procedimiento.fechaCaducidad is null) ");
				where.append(" and ( " + DateUtils.stringFechaAhoraBBDD()
						+ " > procedimiento.fechaPublicacion or procedimiento.fechaPublicacion is null) ");
				where.append(" and (procedimiento.validacion <> " + Validacion.INTERNA
						+ " and procedimiento.validacion <> " + Validacion.RESERVA + ") "); // #355 devolvia no visibles
			} else if (bc.getVisibilidad() == Validacion.INTERNA) {
				where.append(" and ( " + DateUtils.stringFechaAhoraBBDD() + " > procedimiento.fechaCaducidad or "
						+ DateUtils.stringFechaAhoraBBDD()
						+ " < procedimiento.fechaPublicacion or procedimiento.validacion = " + Validacion.INTERNA
						+ " or procedimiento.validacion = " + Validacion.RESERVA + ") ");
			}

			Integer validacion = null;
			if (userIsOper() && !userIsSuper()) { // Filtrar por el acceso del usuario, tieneAccesoValidable

				where.append(" and procedimiento.validacion = :validacion");
				validacion = Validacion.INTERNA;

			} else if (!userIsOper()) {

				where.append(" and procedimiento.validacion = :validacion");
				validacion = Validacion.PUBLICA;

			}

			if (paginacion.getPropiedadDeOrdenacion().equals("familia")) {
				where.append("order by tradFam.nombre");
			} else {
				where.append("order by procedimiento.").append(paginacion.getPropiedadDeOrdenacion());
			}
			where.append(" ").append(paginacion.getCriterioOrdenacion());
			// Se incluye la siguiente linea para que siempre ordene luego por el id.
			if (!"id".equals(paginacion.getPropiedadDeOrdenacion())) {
				where.append(" , procedimiento.id ").append(" ASC");
			}
			final String idUAs = DelegateUtil.getUADelegate()
					.obtenerCadenaFiltroUA(bc.getUnidadAdministrativa().getId(), bc.getUaHijas(), bc.getUaPropias());
			final String queryString = consulta.toString().replace("from", from.toString()).replace("where",
					where.toString());

			final Query query = session.createQuery(queryString.toString().replace(":UA", idUAs));
			query.setParameter("idioma", bc.getLocale());

			if (bc.getProcedimiento().getId() != null)
				query.setParameter("id", bc.getProcedimiento().getId());

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getNombreProcedimiento()))
				query.setParameter("nombreProcedimiento", "%" + bc.getProcedimiento().getNombreProcedimiento() + "%");

			if (bc.getIdHechoVital() != null)
				query.setParameter("idHechoVital", bc.getIdHechoVital());

			if (bc.getIdMateria() != null && bc.getIdMateria() != -1)
				query.setParameter("idMateria", bc.getIdMateria());

			if (bc.getIdPublicoObjetivo() != null && bc.getIdPublicoObjetivo() != -1) {
				query.setParameter("idPublicoObjetivo", bc.getIdPublicoObjetivo());
			}

			if (bc.getIdPlataforma() != null) {
				query.setParameter("idPlataforma", bc.getIdPlataforma());
			}

			if (bc.getIdTramitePlantilla() != null) {
				query.setParameter("idTramitePlantilla", bc.getIdTramitePlantilla());
			}

			if (bc.getPdtValidar() != null) {
				query.setParameter("pdtValidar", bc.getPdtValidar());
			}
			if (bc.getEstado() != null) {
				query.setParameter("estado", bc.getEstado());
			}

			if (bc.getProcedimiento().getIniciacion().getId() != null
					&& bc.getProcedimiento().getIniciacion().getId() != -1)
				query.setParameter("iniciacion", bc.getProcedimiento().getIniciacion());

			if (bc.getProcedimiento().getFamilia().getId() != null && bc.getProcedimiento().getFamilia().getId() != -1)
				query.setParameter("familia", bc.getProcedimiento().getFamilia().getId());

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getTramite()))
				query.setParameter("tramite", bc.getProcedimiento().getTramite());

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getIndicador()))
				query.setParameter("indicador", Integer.parseInt(bc.getProcedimiento().getIndicador()));

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getVentanillaUnica()))
				query.setParameter("ventanillaUnica", Integer.parseInt(bc.getProcedimiento().getVentanillaUnica()));

			if (validacion != null)
				query.setParameter("validacion", validacion);

			if (bc.getProcedimiento().getSilencio().getId() != null
					&& bc.getProcedimiento().getSilencio().getId() != -1) {

				query.setParameter("idSilencio", bc.getProcedimiento().getSilencio().getId());
			}

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getCodigoSIA())) {

				query.setParameter("codSIA", "%" + bc.getProcedimiento().getCodigoSIA() + "%");
			}

			if (StringUtils.isNotEmpty(bc.getProcedimiento().getEstadoSIA())
					&& !bc.getProcedimiento().getEstadoSIA().equals("-1")) {
				// No sea baja(reactivación,modificacion,alta)
				if (bc.getProcedimiento().getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)) {
					query.setParameter("estadoSIA", SiaUtils.ESTADO_BAJA);
				} else {
					query.setParameter("estadoSIA", bc.getProcedimiento().getEstadoSIA());
				}
			}

			resultadoBusqueda.setTotalResultados(query.list().size());

			resultadoBusqueda = PaginatedHibernateEJB.obtenerListadoPaginado(query, paginacion);

			if (resultadoBusqueda.getListaResultados() != null) {

				try {
					final StringBuilder idProced = new StringBuilder();
					if (resultadoBusqueda.getListaResultados() != null
							&& resultadoBusqueda.getListaResultados().size() > 0) {
						for (final Object proc : resultadoBusqueda.getListaResultados()) {
							final Long idProc = ((ProcedimientoLocal) proc).getId();
							idProced.append(idProc);
							idProced.append(" , ");
						}

						String idProcedimientos = idProced.toString();
						idProcedimientos = idProcedimientos.substring(0, idProcedimientos.length() - 2);

						final String hql = "select proMensa.idProcedimiento, proMensa.gestor from ProcedimientoMensaje proMensa where proMensa.leido = false and proMensa.idProcedimiento in ("
								+ idProcedimientos + ")";
						final Query queryMensas = session.createQuery(hql);
						// queryMensas.setParameterList("idProcs", idProcs);
						final List<Object[]> resultados = queryMensas.list();
						if (resultados != null) {
							for (final Object[] resultado : resultados) {
								for (final Object proc : resultadoBusqueda.getListaResultados()) {
									final String idProc = ((ProcedimientoLocal) proc).getId().toString();
									if (resultado[0].toString().equals(idProc)) {
										final boolean gestor = Boolean.valueOf(resultado[1].toString());
										if (gestor) {
											((ProcedimientoLocal) proc).setMensajesNoLeidosGestor(true);
										} else {
											((ProcedimientoLocal) proc).setMensajesNoLeidosSupervisor(true);
										}
										break;
									}
								}
							}
						}
					}
				} catch (final Exception ex) {
					log.error("Error calculando el total de mensajes", ex);
				}
			}

		} catch (final HibernateException e) {

			throw new EJBException(e);

		} catch (final DelegateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

		return resultadoBusqueda;

	}

	/**
	 * Construye el query de búsqueda multiidioma en todos los campos
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
	 * @deprecated Se usa desde la API v1 Obtiene una lista de procedimientos de la
	 *             misma Materia
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public List buscarProcedimientosMateria(final Long id) {
		final Session session = getSession();
		try {
			final List result = new ArrayList();
			final Materia materia = (Materia) session.load(Materia.class, id);
			Hibernate.initialize(materia.getProcedimientosLocales());
			for (final Iterator iter = materia.getProcedimientosLocales().iterator(); iter.hasNext();) {
				final ProcedimientoLocal procedimiento = (ProcedimientoLocal) iter.next();
				if (publico(procedimiento)) {
					result.add(procedimiento);
				}
			}
			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(result, new ProcedimientoLocal());
			return result;
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Asigna un tramite existente al procedimiento.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 *
	 * @param idTramite
	 *            Identificador del trámite.
	 *
	 * @param idProcedimiento
	 *            Identificador del procedimiento.
	 * @throws DelegateException
	 */
	@Override
	public void anyadirTramite(final Long idTramite, final Long idProcedimiento) throws DelegateException {

		log.debug("[anyadirTramite] tramiteId=" + idTramite + " procId=" + idProcedimiento);
		final Session session = getSession();

		try {

			if (!getAccesoManager().tieneAccesoProcedimiento(idProcedimiento))
				throw new SecurityException("No tiene acceso al procedimiento");

			final ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class,
					idProcedimiento);
			final Tramite tramite = (Tramite) session.load(Tramite.class, idTramite);

			log.debug("tramite load=" + tramite);
			log.debug("proc load=" + procedimiento);

			procedimiento.addTramite(tramite);
			session.flush();

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, procedimiento.getId(),
					SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);

		} catch (final HibernateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

	}

	/**
	 * Elimina un tramite del procedimiento.
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 *
	 * @param idTramite
	 *            Identificador del trámite.
	 *
	 * @param idProcedimiento
	 *            Identificador del procedimiento.
	 * @throws DelegateException
	 */
	@Override
	public void eliminarTramite(final Long idTramite, final Long idProcedimiento) throws DelegateException {

		final Session session = getSession();

		try {

			if (!getAccesoManager().tieneAccesoProcedimiento(idProcedimiento))
				throw new SecurityException("No tiene acceso al procedimiento");

			final ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class,
					idProcedimiento);
			final Tramite tramite = (Tramite) session.load(Tramite.class, idTramite);

			if (isProcedimientoConEstadoPublicacionPublica(procedimiento.getId())) {
				// Comprobamos si el trámite es de iniciación. Si lo es, impedimos su borrado.
				if (tramite.getFase() == Tramite.INICIACION)
					throw new IllegalStateException(
							"No se puede borrar el trámite de iniciación cuando el estado del procedimiento es público");
			}

			procedimiento.removeTramite(tramite);
			session.flush();

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, procedimiento.getId(), false);

		} catch (final HibernateException e) {

			throw new EJBException(e);

		} finally {

			close(session);

		}

	}

	/**
	 * Borra un procedimiento.(PORMAD)
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 *
	 * @param id
	 *            Identificador del procedimiento.
	 * @throws DelegateException
	 */
	@Override
	public void borrarProcedimiento(final Long id) throws DelegateException {

		String idSia;
		final Session session = getSession();
		try {

			if (!getAccesoManager().tieneAccesoProcedimiento(id))
				throw new SecurityException("No tiene acceso al procedimiento");

			// Borramos los mensajes que puedan tener
			session.delete("from ProcedimientoMensaje as procMen where procMen.idProcedimiento = ?", id,
					Hibernate.LONG);

			final ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
			idSia = procedimiento.getCodigoSIA();
			procedimiento.getNormativas().clear();

			// Borram els documents directament amb query per evitar el problema del ordres.
			// S'ha llevat el cascade=delete de l'hbm.
			session.delete("from Documento as doc where doc.procedimiento.id = ?", id, Hibernate.LONG);

			addOperacion(session, procedimiento, Auditoria.BORRAR);
			final Historico historico = getHistorico(session, procedimiento);
			((HistoricoProcedimiento) historico).setProcedimiento(null);
			procedimiento.getUnidadAdministrativa().removeProcedimientoLocal(procedimiento);

			if (procedimiento instanceof ProcedimientoRemoto) {

				final AdministracionRemota admin = ((ProcedimientoRemoto) procedimiento).getAdministracionRemota();
				if (admin != null)
					admin.removeProcedimientoRemoto((ProcedimientoRemoto) procedimiento);

			} else {

				Actualizador.borrar(new ProcedimientoLocal(id));

			}

			// Borrar comentarios
			session.delete("from ComentarioProcedimiento as cp where cp.procedimiento.id = ?", id, Hibernate.LONG);
			session.delete(procedimiento);
			session.flush();

			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, id, true);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, id,
					SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO, idSia, procedimiento);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * @deprecated Se usa desde el back antiguo Obtiene los procedimientos de una
	 *             unidad administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public List listarProcedimientosUA(final Long id) {
		final Session session = getSession();
		try {
			final UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session
					.load(UnidadAdministrativa.class, id);
			Hibernate.initialize(unidadAdministrativa.getProcedimientos());

			final List result = new ArrayList();
			for (final Iterator iter = unidadAdministrativa.getProcedimientos().iterator(); iter.hasNext();) {
				final ProcedimientoLocal procedimiento = (ProcedimientoLocal) iter.next();
				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
				if (publico(procedimiento)) {
					result.add(procedimiento);
				}
			}
			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(result, new ProcedimientoLocal());

			return result;
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * @deprecated Se usa desde el back antiguo Obtiene los procedimientos de una
	 *             unidad administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public List listarProcedimientosUO(final Long id, final Integer conse) {
		final Session session = getSession();

		try {
			String hql = " from ProcedimientoLocal pro";
			hql += " where (pro.unidadAdministrativa=:uaid";
			hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid) ";
			hql += " or pro.unidadAdministrativa in (SELECT ua.id from UnidadAdministrativa ua where ua.padre in (SELECT ua.id from UnidadAdministrativa ua where ua.padre= :uaid)) ";
			hql += " )";
			// TODO: comprobar este cambio, estaba en ROLSAC_STAMARGA
			// hql+=" and (pro.fechaCaducidad IS NULL OR pro.fechaCaducidad > :now ) and
			// pro.validacion = 1";
			if (conse == 1) {
				hql += " order by pro.orden  asc,pro.fechaActualizacion desc";
			}
			if (conse == 2) {
				hql += " order by pro.orden2 asc,pro.fechaActualizacion desc";
			}
			if (conse == 3) {
				hql += " order by pro.orden3 asc,pro.fechaActualizacion desc";
			}
			final Query query = session.createQuery(hql);
			query.setLong("uaid", id);
			// query.setDate("now", new Date());
			final List procs = new ArrayList(query.list());
			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(procs, new ProcedimientoLocal());
			for (final Object proc : procs) {
				// Inicializamos las materias del procedimiento
				Hibernate.initialize(((ProcedimientoLocal) proc).getMaterias());
			}

			return procs;

		} catch (final Exception he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * @deprecated Se usa desde API v1 Obtiene los procedimientos de una unidad
	 *             administrativa
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public List listarProcedimientosPublicosUA(final Long id) {
		final Session session = getSession();
		try {
			final UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session
					.load(UnidadAdministrativa.class, id);
			return procedimientosPublicosRecursivosUA(unidadAdministrativa);
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	private List procedimientosPublicosRecursivosUA(final UnidadAdministrativa ua) throws HibernateException {

		final List result = new ArrayList();
		Hibernate.initialize(ua.getProcedimientos());

		final Iterator iterator = ua.getProcedimientos().iterator();
		while (iterator.hasNext()) {

			final ProcedimientoLocal proc = (ProcedimientoLocal) iterator.next();
			if (publico(proc))
				result.add(proc);

		}

		for (int i = 0; i < ua.getHijos().size(); i++) {

			final UnidadAdministrativa uaHijo = ua.getHijos().get(i);
			result.addAll(procedimientosPublicosRecursivosUA(uaHijo));

		}

		// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
		// id)
		Collections.sort(result, new ProcedimientoLocal());

		return result;

	}

	/**
	 * Obtiene los ids de los procedimientos publicos de una unidad administrativa
	 * (PORMAD recuperacion de datos inicial)
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param idUA
	 *            Identificador de la unidad administrativa.
	 *
	 * @param codEstandarMateria
	 *            Código estándar materia.
	 *
	 * @param codEstandarHechoVital
	 *            Código estándar hecho vital.
	 *
	 * @return Devuelve <code>List<Long></code> de los identificadores de los
	 *         procedimientos públicos de la unidad administrativa especificada.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(final Long idUA, final String[] codEstandarMateria,
			final String[] codEstandarHechoVital) {

		final Session session = getSession();
		try {

			final UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session
					.load(UnidadAdministrativa.class, idUA);
			Hibernate.initialize(unidadAdministrativa.getProcedimientos());

			final Set<ProcedimientoLocal> procs = unidadAdministrativa.getProcedimientos();
			final List<Long> procsFinales = new ArrayList<Long>();
			for (final ProcedimientoLocal proc : procs) {

				if (publico(proc)) {

					// Variable que indica si el procedimiento tiene alguna relacion
					boolean relacionada = false;

					// comprobamos materias
					final Query queryMat = session.createQuery(
							"select mat.codigoEstandar from ProcedimientoLocal p, p.materias as mat where p.id = :id");
					queryMat.setParameter("id", proc.getId(), Hibernate.LONG);

					final List<String> codigosMaterias = queryMat.list();

					// si el procedimiento está relacionado con alguna materia la marcamos
					for (final String codigoMat : codEstandarMateria) {

						if (relacionada = codigosMaterias.contains(codigoMat))
							break;

					}

					// Si no tiene niguna relación con ninguna materia miramos si tiene ralación con
					// algún hecho vital
					if (!relacionada) {

						final Query queryHechos = session.createQuery(
								"select hpv.hechoVital.codigoEstandar from ProcedimientoLocal p, p.hechosVitalesProcedimientos as hpv where p.id = :id");
						queryHechos.setParameter("id", proc.getId(), Hibernate.LONG);

						final List<String> codigosHechos = queryHechos.list();

						// si la ficha está relacionada con el hecho vital la marcamos
						for (final String codigoHev : codEstandarHechoVital) {

							if (relacionada = codigosHechos.contains(codigoHev))
								break;

						}

					}

					if (relacionada) {
						/*
						 * Hibernate.initialize(proc.getMaterias());
						 * Hibernate.initialize(proc.getHechosVitalesProcedimientos());
						 * Hibernate.initialize(proc.getDocumentos());
						 */
						procsFinales.add(proc.getId());

					}

				}

			}

			return procsFinales;

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Obtiene los procedimientos públicos de un Hecho Vital
	 *
	 * @ejb.interface-method
	 *
	 * @ejb.permission unchecked="true"
	 *
	 * @param id
	 *            Identificador de un hecho vital
	 *
	 * @return Devuelve <code>List</code> de los procedimientos con un determinado
	 *         hecho vital asignado.
	 */
	@Override
	public List listarProcedimientosPublicosHechoVital(final Long id) {

		final Session session = getSession();
		try {

			final HechoVital hechoVital = (HechoVital) session.load(HechoVital.class, id);
			final List result = new ArrayList();
			for (int i = 0; i < hechoVital.getHechosVitalesProcedimientos().size(); i++) {

				final HechoVitalProcedimiento hechoVitalProcedimiento = hechoVital.getHechosVitalesProcedimientos()
						.get(i);
				final ProcedimientoLocal proc = hechoVitalProcedimiento.getProcedimiento();
				if (publico(proc))
					result.add(proc);

			}

			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(result, new ProcedimientoLocal());

			return result;

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * @deprecated No se usa Consulta toda la informacion de un procedimiento
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	public ProcedimientoLocal consultarProcedimiento(final Long id) {
		final Session session = getSession();
		try {
			final ProcedimientoLocal procedimiento = (ProcedimientoLocal) session.load(ProcedimientoLocal.class, id);
			if (userIsOper() || publico(procedimiento)) {
				Hibernate.initialize(procedimiento.getTramites());
				final List<Tramite> tramites = procedimiento.getTramites();
				for (final Iterator iter = tramites.iterator(); iter.hasNext();) {
					final Tramite tramite = (Tramite) iter.next();
					if (tramite == null)
						continue;
					Hibernate.initialize(tramite.getFormularios());
					Hibernate.initialize(tramite.getDocsInformatius());
					Hibernate.initialize(tramite.getTaxes());
				}
				Hibernate.initialize(procedimiento.getDocumentos());
				Hibernate.initialize(procedimiento.getNormativas());
				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());

				return procedimiento;
			} else {
				throw new SecurityException("Procedimiento no público.");
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Construye el query de búsqueda segun los parametros
	 */
	private String populateQuery(final Map parametros, final Map traduccion, final List params) {
		String aux = "";

		for (final Iterator iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
			final String key = (String) iter1.next();
			final Object value = parametros.get(key);
			if (!key.startsWith("ordre") && value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (aux.length() > 0)
							aux = aux + " and ";
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( procedimiento." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " upper( procedimiento." + key + " ) like ? ";
							params.add("%" + sValue + "%");
						}
					}
				} else if (value instanceof Date) {
					if (aux.length() > 0)
						aux = aux + " and ";
					aux = aux + "procedimiento." + key + " = '" + value + "'";
				} else {
					if (aux.length() > 0)
						aux = aux + " and ";
					aux = aux + "procedimiento." + key + " = " + value;
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

		if (aux.length() > 0) {
			aux = "where " + aux;
		}
		return aux;
	}

	/**
	 * Valida si un procedimiento es público
	 *
	 * @param proc
	 *            Indica el procedimiento local a validar.
	 *
	 * @return Devuelve <code>true</code> si la fecha de caducidad es posterior a la
	 *         actual o es nula, si la fecha de publicación es anterior a la actual
	 *         o nula y si el campo validación contiene el valor Público.
	 **/
	protected boolean publico(final ProcedimientoLocal proc) {

		final Date now = new Date();
		final boolean noCaducado = (proc.getFechaCaducidad() == null || proc.getFechaCaducidad().after(now));
		final boolean publicado = (proc.getFechaPublicacion() == null || proc.getFechaPublicacion().before(now));

		return visible(proc) && noCaducado && publicado;
	}

	private List<Documento> obtenerListaDocumentos(final ProcedimientoLocal proc) throws DelegateException {

		int listaSize = 0;
		if (proc.getDocumentos() != null) {
			listaSize = proc.getDocumentos().size();
		}
		final List<Documento> listaDocumentos = new ArrayList<Documento>(listaSize);
		if (proc.getDocumentos() != null) {
			final DocumentoDelegate documentoDelegate = DelegateUtil.getDocumentoDelegate();
			for (final Documento documento : proc.getDocumentos()) {
				if (documento == null)
					continue;
				listaDocumentos.add(documentoDelegate.obtenerDocumento(documento.getId()));
			}
		}

		return listaDocumentos;
	}

	/**
	 * Actualiza el orden de los tramites
	 *
	 * FIXME enric@dgtic: este metodo lo pondria en procedimientoFacadeEJB
	 *
	 * @param mapaIdOrdenTramite
	 *            <String,String[]> eg. key= orden_doc396279 value={"1"}
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */

	@Override
	public void actualizarOrdenTramites(final ArrayList<Long> tramitesId) throws DelegateException {

		final Session session = getSession();
		try {
			for (final Long idTramite : tramitesId) {
				if (!getAccesoManager().tieneAccesoTramite(idTramite)) {
					// Es necesario comprobar para cada trámite??
					throw new SecurityException("No tiene acceso al tramite");
				}
			}

			long orden = 0L;
			for (final Long idTramite : tramitesId) {
				final Tramite tramite = (Tramite) session.load(Tramite.class, idTramite);
				tramite.setOrden(orden);
				orden++;
				session.save(tramite);
			}
			session.flush();

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/** Clase que se utiliza para comparar de trámites. */
	class TramiteComparator implements Comparator {

		@Override
		public int compare(final Object o1, final Object o2) {
			final Long x1 = new Long(((Tramite) o1).getOrden());
			final Long x2 = new Long(((Tramite) o2).getOrden());

			return x1.compareTo(x2);
		}

	}

	/**
	 * Buscamos el numero de procedimientos activos des de la fecha actual
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId
	 *            Listado de identificadores de unidades administrativas.
	 * @param fechaCaducidad
	 *            Filtro que indica el rango temporal en el que se encuentra activo
	 *            un procedimiento.
	 * @return numero de Procedimientos activos
	 */
	@Override
	public int buscarProcedimientosActivos(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad) {

		Integer resultado = 0;
		final Session session = getSession();
		try {
			Query query = null;
			if (listaUnidadAdministrativaId.size() > 0) {

				query = session.createQuery(
						" select count(*) from ProcedimientoLocal as prc " + " where prc.validacion = :validacion "
								+ " and (prc.fechaCaducidad >= :fecha or prc.fechaCaducidad is null) "
								+ " and (prc.fechaPublicacion <= :fecha or prc.fechaPublicacion is null) "
								+ " and prc.unidadAdministrativa.id in (:lId) ");

				query.setInteger("validacion", Validacion.PUBLICA);
				query.setDate("fecha", fechaCaducidad);
				query.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);

				resultado = (Integer) query.uniqueResult();
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

		return resultado;
	}

	/**
	 * Buscamos el numero de procedimientos activos des de la fecha actual.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param listaUnidadAdministrativaId
	 *            Listado de identificadores de unidades administrativas.
	 * @param fechaCaducidad
	 *            Filtro que indica el rango temporal en el que se encuentra activo
	 *            un procedimiento.
	 * @return Devuelve el número de Procedimientos caducados
	 */
	@Override
	public int buscarProcedimientosCaducados(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad) {

		Integer resultado = 0;
		final Session session = getSession();
		try {
			Query query = null;
			if (listaUnidadAdministrativaId.size() > 0) {

				query = session.createQuery(" select count(*) from ProcedimientoLocal as prc " + " where ( "
						+ " 	( prc.validacion != :validacion ) "
						+ " 	or ( prc.validacion = :validacion and prc.fechaCaducidad < :fecha ) "
						+ " 	or ( prc.validacion = :validacion and prc.fechaCaducidad is null and prc.fechaPublicacion > :fecha ) "
						+ " 	or ( prc.validacion = :validacion and prc.fechaCaducidad >= :fecha and prc.fechaPublicacion > :fecha ) "
						+ " ) and prc.unidadAdministrativa.id in (:lId) ");

				query.setInteger("validacion", Validacion.PUBLICA);
				query.setDate("fecha", fechaCaducidad);
				query.setParameterList("lId", listaUnidadAdministrativaId, Hibernate.LONG);

				resultado = (Integer) query.uniqueResult();
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

		return resultado;
	}

	/**
	 * @deprecated Se usa desde SincronizacionServicio.java Obtiene los
	 *             procedimientos publicos de una unidad administrativa (PORMAD
	 *             recuperacion de datos inicial)
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) {
		final Session session = getSession();
		try {
			final UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) session
					.load(UnidadAdministrativa.class, idUA);
			Hibernate.initialize(unidadAdministrativa.getProcedimientos());

			final Set<ProcedimientoLocal> procs = unidadAdministrativa.getProcedimientos();
			final List<ProcedimientoLocal> procsFinales = new ArrayList<ProcedimientoLocal>();
			for (final ProcedimientoLocal proc : procs) {
				if (publico(proc)) {
					// Variable que indica si el procedimiento tiene alguna relacion
					boolean relacionada = false;

					// comprobamos materias
					final Query queryMat = session.createQuery(
							"select mat.codigoEstandar from ProcedimientoLocal p, p.materias as mat where p.id =:id");
					queryMat.setParameter("id", proc.getId(), Hibernate.LONG);

					final List<String> codigosMaterias = queryMat.list();

					// si el procedimiento esta relacionada con alguna materia la marcamos
					for (final String codigoMat : codEstMat) {
						if (relacionada = codigosMaterias.contains(codigoMat)) {
							break;
						}
					}

					// Si no tiene niguna relacion con ninguna materia miramos si teiene ralacion
					// con algun HV
					if (!relacionada) {
						final Query queryHechos = session.createQuery(
								"select hpv.hechoVital.codigoEstandar from ProcedimientoLocal p, p.hechosVitalesProcedimientos as hpv where p.id =:id");
						queryHechos.setParameter("id", proc.getId(), Hibernate.LONG);

						final List<String> codigosHechos = queryHechos.list();

						// si la ficha esta relacionada con el hechovital la marcamos
						for (final String codigoHev : codEstHV) {
							if (relacionada = codigosHechos.contains(codigoHev)) {
								break;
							}
						}
					}

					if (relacionada) {
						Hibernate.initialize(proc.getMaterias());
						Hibernate.initialize(proc.getHechosVitalesProcedimientos());
						Hibernate.initialize(proc.getDocumentos());
						procsFinales.add(proc);
					}
				}
			}
			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(procsFinales, new ProcedimientoLocal());
			return procsFinales;
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 *
	 * @param solrPendiente
	 * @param solrIndexer
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		return indexarSolr(solrIndexer, solrPendiente.getIdElemento(),
				EnumCategoria.fromString(solrPendiente.getTipo()));
	}

	/**
	 * Obtiene un procedimiento Local.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id
	 *            Identificador del procedimiento.
	 * @return Devuelve <code>ProcedimientoLocal</code> solicitado.
	 */
	@Override
	public ProcedimientoLocal obtenerProcedimientoParaSolr(final Long id, final Session iSession) {

		final Session session;
		if (iSession == null) {
			session = getSession();
		} else {
			session = iSession;
		}
		ProcedimientoLocal procedimiento = null;
		try {
			procedimiento = (ProcedimientoLocal) session.get(ProcedimientoLocal.class, id);
			if (procedimiento != null) {

				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				if (procedimiento.getUnidadAdministrativa() != null) {
					Hibernate.initialize(procedimiento.getUnidadAdministrativa().getHijos());
					Hibernate.initialize(procedimiento.getUnidadAdministrativa().getUnidadesNormativas());
				}
				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getFamilia());
				Hibernate.initialize(procedimiento.getServicioResponsable());
				Hibernate.initialize(procedimiento.getDocumentos());
			}

		} catch (final HibernateException he) {
			log.error("Error obteniendo el procedimiento con id " + id, he);
		} finally {
			if (iSession == null) {
				close(session);
			}
		}

		return procedimiento;
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 *
	 * @param solrPendiente
	 * @param solrIndexer
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento,
			final EnumCategoria categoria) {
		log.debug("FichafacadeEJB.indexarSolr. idElemento:" + idElemento + " categoria:" + categoria);

		try {
			// Paso 0. Obtenemos la ficha y comprobamos si se puede indexar.
			final ProcedimientoLocal procedimiento = obtenerProcedimientoParaSolr(idElemento, null);
			if (procedimiento == null) {
				return new SolrPendienteResultado(false, "Error obteniendo la id del procedimiento");
			}

			final boolean isIndexable = IndexacionUtil.isIndexable(procedimiento);
			if (!isIndexable) {
				return new SolrPendienteResultado(true, "No se puede indexar");
			}

			// Preparamos la información básica: id elemento, aplicacionID = ROLSAC y la
			// categoria de tipo ficha.
			final IndexData indexData = new IndexData();
			indexData.setCategoria(categoria);
			indexData.setAplicacionId(EnumAplicacionId.ROLSAC);
			indexData.setElementoId(idElemento.toString());
			indexData.setCategoriaRaiz(EnumCategoria.ROLSAC_PROCEDIMIENTO);
			indexData.setElementoIdRaiz(idElemento.toString());

			// Iteramos las traducciones
			final Map<String, Traduccion> traducciones = procedimiento.getTraduccionMap();
			final MultilangLiteral titulo = new MultilangLiteral();
			final MultilangLiteral descripcion = new MultilangLiteral();
			final MultilangLiteral urls = new MultilangLiteral();
			final MultilangLiteral searchText = new MultilangLiteral();
			final MultilangLiteral searchTextOptional = new MultilangLiteral();
			final List<EnumIdiomas> idiomas = new ArrayList<EnumIdiomas>();

			final String nomUnidadAministrativa;
			if (procedimiento.getUnidadAdministrativa() == null) {
				nomUnidadAministrativa = "";
			} else {
				nomUnidadAministrativa = procedimiento.getUnidadAdministrativa().getNombre();
			}

			final boolean esProcSerInterno = POUtils.contienePOInterno(procedimiento.getPublicosObjetivo());

			// Recorremos las traducciones
			for (final String keyIdioma : traducciones.keySet()) {
				final EnumIdiomas enumIdioma = EnumIdiomas.fromString(keyIdioma);
				final TraduccionProcedimiento traduccion = (TraduccionProcedimiento) traducciones.get(keyIdioma);

				if (traduccion != null && enumIdioma != null) {

					if ((traduccion.getNombre() == null || traduccion.getNombre().isEmpty())) {
						continue;
					}

					// Anyadimos idioma al enumerado.
					idiomas.add(enumIdioma);

					// Seteamos los primeros campos multiidiomas: Titulo, Descripción y el search
					// text.
					titulo.addIdioma(enumIdioma, traduccion.getNombre());
					descripcion.addIdioma(enumIdioma, traduccion.getResumen());
					searchText.addIdioma(enumIdioma, traduccion.getNombre() + " " + traduccion.getResumen());

					final StringBuffer textoOptional = new StringBuffer();

					// materia
					for (final Materia materia : procedimiento.getMaterias()) {
						final TraduccionMateria traduccionMateria = (TraduccionMateria) materia
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

					// Servicio Responsable
					if (procedimiento.getServicioResponsable() != null) {
						final TraduccionUA unidadAdm = (TraduccionUA) procedimiento.getServicioResponsable()
								.getTraduccion(keyIdioma);
						if (unidadAdm != null) {
							textoOptional.append(" ");
							textoOptional.append(unidadAdm.getNombre());
						}
					}

					// Publico objetivo, para extraer el nombre del publico objetivo
					String nombrePubObjetivo = "persones";
					String idPublicoObjetivo = "200";
					for (final PublicoObjetivo publicoObjectivo : procedimiento.getPublicosObjetivo()) {
						final TraduccionPublicoObjetivo traduccionPO = (TraduccionPublicoObjetivo) publicoObjectivo
								.getTraduccion(keyIdioma);
						if (traduccionPO != null) {
							nombrePubObjetivo = traduccionPO.getTitulo().toLowerCase();
							idPublicoObjetivo = publicoObjectivo.getId().toString();
							break; // Con encontrar uno nos basta
						}
					}

					// UO
					if (procedimiento.getUnidadAdministrativa() != null
							&& procedimiento.getUnidadAdministrativa().getTraduccion(keyIdioma) != null) {
						final TraduccionUA unidadAdm = (TraduccionUA) procedimiento.getUnidadAdministrativa()
								.getTraduccion(keyIdioma);
						if (unidadAdm != null) {
							textoOptional.append(" ");
							textoOptional.append(unidadAdm.getNombre());
						}
					}

					// Nombre familia
					textoOptional.append(" ");
					textoOptional.append(procedimiento.getNombreFamilia());

					// Normativa asociadas
					for (final Normativa normativa : procedimiento.getNormativas()) {
						final TraduccionNormativa traduccionNormativa = (TraduccionNormativa) normativa
								.getTraduccion(keyIdioma);
						if (traduccionNormativa != null) {
							textoOptional.append(traduccionNormativa.getTitulo());
							textoOptional.append(" ");
						}
					}

					searchTextOptional.addIdioma(enumIdioma, traduccion.getResultat() + " "
							+ traduccion.getObservaciones() + " " + textoOptional.toString());

					if (esProcSerInterno) {
						// Si es interno usamos la url especifica para los procedimientos internos

						final String url = RolsacPropertiesUtil.getPropiedadPOInternoUrlProc()
								.replace("{idioma}", keyIdioma).replace("{idPublicoObjetivo}", idPublicoObjetivo)
								.replace("{nombrePubObjetivo}", nombrePubObjetivo)
								.replace("{idProcedimiento}", procedimiento.getId().toString());
						urls.addIdioma(enumIdioma, url);

					} else {
						// Si no es interno
						urls.addIdioma(enumIdioma, "/seucaib/" + keyIdioma + "/" + idPublicoObjetivo + "/"
								+ nombrePubObjetivo + "/tramites/tramite/" + procedimiento.getId());
					}
				}
			}

			// Seteamos datos multidioma.
			indexData.setTitulo(titulo);
			indexData.setDescripcion(descripcion);
			indexData.setUrl(urls);
			indexData.setSearchText(searchText);
			indexData.setSearchTextOptional(searchTextOptional);
			indexData.setIdiomas(idiomas);

			// Datos IDs materias.
			final List<String> materiasId = new ArrayList<String>();
			for (final Materia materia : procedimiento.getMaterias()) {
				materiasId.add(materia.getId().toString());
			}
			indexData.setMateriaId(materiasId);

			// Datos IDs publico Objetivos.
			final List<String> publicoObjetivoId = new ArrayList<String>();
			for (final PublicoObjetivo publicoObjectivo : procedimiento.getPublicosObjetivo()) {
				publicoObjetivoId.add(publicoObjectivo.getId().toString());
			}
			indexData.setPublicoId(publicoObjetivoId);

			// Datos IDs de familia.
			if (procedimiento.getFamilia() != null) {
				indexData.setFamiliaId(procedimiento.getFamilia().getId().toString());
			}

			// Fechas
			indexData.setFechaActualizacion(procedimiento.getFechaActualizacion());
			indexData.setFechaPublicacion(procedimiento.getFechaPublicacion());
			indexData.setFechaCaducidad(procedimiento.getFechaCaducidad());

			if (esProcSerInterno) {
				indexData.setInterno(true);
			} else {
				indexData.setInterno(false);
			}

			// UA
			final PathUO pathUO = IndexacionUtil.calcularPathUO(procedimiento.getUnidadAdministrativa());
			if (pathUO == null) {
				return new SolrPendienteResultado(true, "No se puede indexar: no cuelga de UA visible");
			}
			indexData.getUos().add(pathUO);

			final boolean telematico = IndexacionUtil.isTelematicoProcedimiento(procedimiento);
			indexData.setTelematico(telematico);

			final Tramite tramite = IndexacionUtil.getTramiteInicio(procedimiento);
			if (tramite != null) {
				indexData.setFechaPlazoIni(tramite.getDataInici());
				indexData.setFechaPlazoFin(tramite.getDataTancament());
			}

			solrIndexer.indexarContenido(indexData);
			return new SolrPendienteResultado(true);
		} catch (final Exception exception) {
			log.error("Error en procedimientoFacade intentando indexar. idElemento:" + idElemento + " categoria:"
					+ categoria, exception);
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}

	/**
	 * Metodo para indexar un solrPendiente.
	 *
	 * @param solrPendiente
	 * @param solrIndexer
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) {
		try {
			solrIndexer.desindexar(solrPendiente.getIdElemento().toString(), EnumCategoria.ROLSAC_PROCEDIMIENTO);
			return new SolrPendienteResultado(true);
		} catch (final Exception exception) {
			log.error("Error en procedimientoFacade intentando desindexar.", exception);
			return new SolrPendienteResultado(false, ExceptionUtils.getStackTrace(exception));
		}
	}

	/**
	 * Devuelve una lista con los ids de los procedimientos
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public List<Long> buscarIdsProcedimientos() {
		final Session session = getSession();

		try {

			final StringBuilder consulta = new StringBuilder("select proc.id from ProcedimientoLocal as proc ");

			final Query query = session.createQuery(consulta.toString());
			query.setCacheable(true);

			return query.list();

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}

	/**
	 * Devuelve una lista con los procedimientos que tienen una normativa
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public List<ProcedimientoLocal> listarProcedimientosNormativa(final Long id) {
		final Session session = getSession();
		try {
			final List result = new ArrayList();
			final Normativa normativa = (Normativa) session.load(Normativa.class, id);
			Hibernate.initialize(normativa.getProcedimientos());
			for (final Iterator iter = normativa.getProcedimientos().iterator(); iter.hasNext();) {
				final ProcedimientoLocal procedimiento = (ProcedimientoLocal) iter.next();
				Hibernate.initialize(procedimiento.getTramites());
				Hibernate.initialize(procedimiento.getMaterias());
				Hibernate.initialize(procedimiento.getUnidadAdministrativa());
				Hibernate.initialize(procedimiento.getHechosVitalesProcedimientos());
				Hibernate.initialize(procedimiento.getPublicosObjetivo());
				Hibernate.initialize(procedimiento.getNormativas());
				if (publico(procedimiento)) {
					result.add(procedimiento);
				}
			}
			// Ordenamos los procedimientos por el campo orden (si nulo, ordena por el campo
			// id)
			Collections.sort(result, new ProcedimientoLocal());
			return result;
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Actualiza el procedimiento pero no sus relaciones
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void actualizarProcedimiento(final ProcedimientoLocal proc) {
		final Session session = getSession();
		try {

			// #399 Check valores SIA
			checkValoresSIA(proc);

			session.update(proc);
			session.flush();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Obtiene los procedimientos que se han alterado por el tiempo su estado SIA.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public List<Long> getProcedimientosEstadoSIAAlterado() {

		final Session session = getSession();
		try {
			final StringBuffer hql = new StringBuffer();

			hql.append(" select pro.id from ProcedimientoLocal pro");
			hql.append(" where ");
			// Procedimientos caducados son con estado SIA de alta y fechaCaducidad pasada y
			// que son publicas.
			hql.append(" (   pro.estadoSIA is not null ");
			hql.append(" AND pro.estadoSIA like 'A'");
			hql.append(" AND pro.validacion = 1");
			hql.append(" AND pro.fechaCaducidad is not null ");
			hql.append(" AND pro.fechaCaducidad < " + DateUtils.stringFechaAhoraBBDD() + " ) ");

			hql.append(" OR ");
			// Procedimientos activos sin estado o de baja y cuya fecha de caducidad es
			// futura y que son publicas.
			hql.append(" (   (pro.estadoSIA is null     OR   (pro.estadoSIA is NOT NULL AND pro.estadoSIA like 'B')) ");
			hql.append(" AND pro.validacion = 1");
			hql.append(" AND pro.fechaPublicacion IS NOT NULL ");
			hql.append(" AND pro.fechaPublicacion <= " + DateUtils.stringFechaAhoraBBDD() + " ");
			hql.append("  ) ");

			return session.createQuery(hql.toString()).list();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Obtiene los procedimientos según el organo resolutorio.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public List<Long> listarProcedimientosOrganoResolutori(final Long idOrganResolutori) {
		final Session session = getSession();
		try {
			final StringBuffer hql = new StringBuffer();

			hql.append(" select pro.id from ProcedimientoLocal pro");
			hql.append(" where pro.organResolutori.id = " + idOrganResolutori);

			return session.createQuery(hql.toString()).list();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	/**
	 * Reordena los documentos del procedimiento.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void reordenarDocumentos(final Long idProcedimiento, final List<Long> idDocumentos,
			final ProcedimientoMensaje procedimientoMensaje) {
		final Session session = getSession(); // session.beginTransaction()
		try {
			// Paso 1. Obtenemos el procedimiento.
			ProcedimientoLocal procedimiento = obtenerProcedimientoParaSolr(idProcedimiento, session);

			// Paso 2. Obtener los procedimientos que han sido borrados.
			long maxOrden = 0;
			final List<Documento> docsParaBorrar = new ArrayList<Documento>();
			if (procedimiento.getDocumentos() != null) {
				for (final Documento doc : procedimiento.getDocumentos()) {
					if (doc != null && doc.getId() != null) {
						if (doc.getOrden() >= maxOrden) {
							maxOrden = doc.getOrden() + 1;
						}

						if (!idDocumentos.contains(doc.getId())) {
							docsParaBorrar.add(doc); // Lo añadimos en la lista.
						}

						// Paso 2.1. Comprobamos que tiene permisos para editar los documentos.
						if (!getAccesoManager().tieneAccesoDocumento(doc.getId())) {
							throw new SecurityException("No tiene acceso al documento");
						}
					}
				}
			}

			// Paso 3. Los borramos tanto en BBDD como en la relacion.
			for (final Documento docParaBorrar : docsParaBorrar) {
				session.delete(docParaBorrar); // Borramos el objeto
				procedimiento.getDocumentos().remove(docParaBorrar); // Borramos la relacion
			}

			// Paso 4. Reordenar.
			for (int i = 0; i < idDocumentos.size(); i++) {
				final int orden = i; // Empezará en el 0 el orden.
				final Documento documento = (Documento) session.get(Documento.class, idDocumentos.get(i));
				documento.setOrden(orden + maxOrden);
				session.update(documento);

				/*
				 * for(int j = 0 ; j < procedimiento.getDocumentos().size(); j++) { Documento
				 * doc = procedimiento.getDocumentos().get(j); if (doc != null && doc.getId() !=
				 * null && doc.getId().compareTo(idDocumentos.get(i)) == 0) {
				 * //doc.setOrden(orden); procedimiento.getDocumentos().set(j, documento); } }
				 */
			}
			session.flush();

			// Paso 4.2 Reordenar.
			for (int i = 0; i < idDocumentos.size(); i++) {
				final int orden = i; // Empezará en el 0 el orden.
				final Documento documento = (Documento) session.get(Documento.class, idDocumentos.get(i));
				documento.setOrden(orden);
				session.update(documento);
			}
			session.flush();

			// Paso 5.Llamamos al addOperacion
			addOperacion(session, procedimiento, Auditoria.MODIFICAR);

			// Paso 6. Obtenemos de nuevo el procedimiento (por si se cachea) y actualizamos
			// la fecha de actualización.
			procedimiento = obtenerProcedimientoParaSolr(idProcedimiento, session);
			procedimiento.setFechaActualizacion(new Date());
			session.update(procedimiento);

			// Paso 6.5 Si procedimientoMensaje relleno, lo guardamos
			if (procedimientoMensaje != null) {
				session.save(procedimientoMensaje);
			}

			// Paso 7. Llamamos al actualizador.
			Actualizador.actualizar(procedimiento);

			// Paso 8. Actualizamos en solr y sia.
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_PROCEDIMIENTO, idProcedimiento, false);
			SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, idProcedimiento,
					SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, procedimiento);

			// Paso 9. Flush.
			session.flush();

		} catch (final Exception he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Consulta los procedimientos en funcion del filtro generico
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */

	@Override
	public ResultadoBusqueda consultaProcedimientos(final FiltroGenerico filtro) {

		final Session session = getSession();
		final Integer pageSize = filtro.getPageSize();
		final Integer pageNumber = filtro.getPage();
		final Long id = filtro.getId();
		final String lang = filtro.getLang();
		final Map<String, String> parametros = new HashMap<String, String>();

		final String activo = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_ACTIVO);
		final String codigoUA = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_UA);
		final String codigoUADIR3 = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_UA_DIR3);
		final String descendientes = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_UA_DESCENDIENTES);
		final String vigente = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_VIGENTE);
		final String telematico = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_TELEMATICO);
		final String codigoAHV = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_AGRUPACION_HECHO_VITAL);
		final String codigoAM = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_AGRUPACION_MATERIA);
		final String codigoSIA = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_CODIGO_SIA);
		final String estadoSIA = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_ESTADO_SIA);
		final String estadoUA = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_ESTADO_UA);
		final String codigoFamilia = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_FAMILIA);
		final String fechaActualizacionSia = filtro
				.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_FECHA_ACTUALIZACION_SIA);
		final String fechaPublicacionDesde = filtro
				.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_FECHA_PUBLICACION_DESDE);
		final String fechaPublicacionHasta = filtro
				.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_FECHA_PUBLICACION_HASTA);
		final String codigoMateria = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_MATERIA);
		final String codigoPublicoObjetivo = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_PUBLICO);
		final String textos = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_TEXTOS);
		final String titulo = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_TITULO);
		final String tramiteTelematico = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_TRAMITE_TELEMATICO);
		final String comun = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_COMUN);
		final String versionTramiteTelematico = filtro
				.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_VERSION_TRAMITE_TELEMATICO);
		final String plataforma = filtro.getValor(FiltroGenerico.FILTRO_PROCEDIMIENTO_PLATAFORMA);

		final StringBuilder select = new StringBuilder("SELECT p ");
		final StringBuilder selectCount = new StringBuilder("SELECT count(p) ");
		final StringBuilder from = new StringBuilder(" FROM ProcedimientoLocal as p, p.traducciones as trad ");
		final StringBuilder where = new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang", lang);
		final StringBuilder order = new StringBuilder(filtro.getOrdenSQL("p"));

		try {

			if (id != null && id > 0) {
				where.append(" AND p.id = :id");
				parametros.put("id", id.toString());
			}

			if (!StringUtils.isEmpty(activo)) {
				if (activo.equals("1")) {
					// está activo
					where.append(filtroProcedimientosActivos("p"));
				} else if (activo.equals("0")) {
					// esta caducado
					where.append(filtroProcedimientosCaducados("p"));
				}
			}

			// Buscamos por id de la ua (y sus descendientes si procede)
			final Long idUA = (codigoUA != null) ? Long.parseLong(codigoUA) : null;
			String uaQuery = null;
			try {
				uaQuery = DelegateUtil.getUADelegate().obtenerCadenaFiltroUA(idUA, "1".equals(descendientes), false);
			} catch (final DelegateException e) {
				e.printStackTrace();
			}

			if (!StringUtils.isEmpty(uaQuery)) {
				where.append(" AND p.unidadAdministrativa.id in (" + uaQuery + ")");
			}

			if (!StringUtils.isEmpty(plataforma)) {
				where.append(
						" AND (p.plataforma.id = :plataforma OR  p.tramitePlantilla in (select plant from TramitePlantilla plant where plant.plataforma.identificador like :plataforma) )");
				parametros.put("plataforma", plataforma);
			}

			// Buscamos por codigo dir3 de la ua y sus descendientes
			String uaQueryDir3 = null;
			try {
				uaQueryDir3 = DelegateUtil.getUADelegate().obtenerCadenaFiltroUAPorDir3(codigoUADIR3,
						"1".equals(descendientes), false);
			} catch (final DelegateException e) {
				e.printStackTrace();
			}

			if (!StringUtils.isEmpty(uaQueryDir3)) {
				// se ha añadido un codigodir3 que concuerda con una UA
				where.append(" AND p.unidadAdministrativa.id in (" + uaQueryDir3 + ")");
			} else if (!StringUtils.isEmpty(codigoUADIR3)) {
				// Se ha añadido un codigo dir3 que no se corresponde con ninguna ua por lo que
				// no tendra descendientes
				// Se añade para forzar que no retorne resultados
				where.append(" AND p.unidadAdministrativa.codigoDIR3 = :codigoUADIR3");
				parametros.put("codigoUADIR3", codigoUADIR3);
			}

			if (!StringUtils.isEmpty(estadoUA)) {
				where.append(" AND p.unidadAdministrativa.validacion = :estadoUA");
				parametros.put("estadoUA", estadoUA);
			}

			if ((!StringUtils.isEmpty(vigente) && vigente.equals("1"))
					|| (!StringUtils.isEmpty(telematico) && (telematico.equals("1") || telematico.equals("0")))) {
				final StringBuilder wereSubselect = new StringBuilder();

				if ((!StringUtils.isEmpty(telematico) && (telematico.equals("1") || telematico.equals("0")))) {
					wereSubselect.append("WHERE (t.telematico = " + ApiRestUtils.intToBool(telematico) + " ) ");
				}

				if ((!StringUtils.isEmpty(vigente) && vigente.equals("1"))) {
					if (wereSubselect.length() <= 0) {
						wereSubselect.append("WHERE ");
					} else {
						wereSubselect.append("AND ");
					}

					wereSubselect.append(
							"t.fase = 1 AND (t.dataInici > current_date OR t.dataInici IS NULL) AND (t.dataTancament < current_date OR t.dataTancament IS NULL) ");

				}

				where.append(" AND p.id in ( SELECT t.procedimiento FROM Tramite AS t ");
				where.append(wereSubselect.toString());
				where.append(" ) ");

			}

			if (!StringUtils.isEmpty(codigoAHV)) {
				from.append(" , p.hechosVitalesProcedimientos as hv ");
				where.append(
						" AND hv.hechoVital.id in ( SELECT hechovital.id FROM  HechoVitalAgrupacionHV as ahv, ahv.hechoVital as hechovital, ahv.agrupacion as agr WHERE agr.id = :codigoAHV ) ");
				parametros.put("codigoAHV", codigoAHV);
			}

			if (!StringUtils.isEmpty(codigoAM)) {
				where.append(
						" AND p.materias in (SELECT am.materia FROM MateriaAgrupacionM AS am WHERE am.agrupacion.id = :codigoAM ) ");
				parametros.put("codigoAM", codigoAM);
			}

			if (!StringUtils.isEmpty(codigoSIA)) {
				where.append(" AND p.codigoSIA = :codigoSIA");
				parametros.put("codigoSIA", codigoSIA);
			}

			if (!StringUtils.isEmpty(estadoSIA)) {
				where.append(" AND p.estadoSIA = :estadoSIA");
				parametros.put("estadoSIA", estadoSIA);
			}

			if (!StringUtils.isEmpty(codigoFamilia)) {
				where.append(" AND p.familia.id = :codigoFamilia");
				parametros.put("codigoFamilia", codigoFamilia);
			}

			if (!StringUtils.isEmpty(fechaActualizacionSia)) {
				where.append(" AND p.fechaSIA = :fechaActualizacionSia");
				parametros.put("fechaActualizacionSia", fechaActualizacionSia);
			}

			if (!StringUtils.isEmpty(fechaPublicacionDesde)) {
				where.append(" AND p.fechaPublicacion >= :fechaPublicacionDesde");
				parametros.put("fechaPublicacionDesde", fechaPublicacionDesde);
			}

			if (!StringUtils.isEmpty(fechaPublicacionHasta)) {
				where.append(" AND p.fechaPublicacion <= :fechaPublicacionHasta");
				parametros.put("fechaPublicacionHasta", fechaPublicacionHasta);
			}

			if (!StringUtils.isEmpty(codigoMateria)) {
				from.append(" , p.materias as m ");
				where.append(" AND m.id = :codigoMateria");
				parametros.put("codigoMateria", codigoMateria);
			}

			if (!StringUtils.isEmpty(codigoPublicoObjetivo)) {
				from.append(" , p.publicosObjetivo as po ");
				where.append(" AND po.id = :codigoPublicoObjetivo");
				parametros.put("codigoPublicoObjetivo", codigoPublicoObjetivo);
			}

			if (!StringUtils.isEmpty(tramiteTelematico) || !StringUtils.isEmpty(versionTramiteTelematico)) {
				from.append(" , p.tramites as tram ");

				if (!StringUtils.isEmpty(tramiteTelematico)) {
					where.append(
							" AND ( tram.idTraTel = :tramiteTelematico OR tram.tramitePlantilla in (select plant from TramitePlantilla plant where plant.identificador = :tramiteTelematico) ) ");
					parametros.put("tramiteTelematico", tramiteTelematico);
				}

				if (!StringUtils.isEmpty(versionTramiteTelematico)) {
					where.append(
							" AND (tram.versio = :versionTramiteTelematico OR tram.tramitePlantilla in (select plant from TramitePlantilla plant where plant.version = :versionTramiteTelematico) )");
					parametros.put("versionTramiteTelematico", versionTramiteTelematico);
				}
			}

			if (!StringUtils.isEmpty(comun)) {
				where.append(" AND p.comun = :comun");
				parametros.put("comun", comun);
			}

			if (!StringUtils.isEmpty(textos)) {
				final String[] camposBuscablesPorTexto = { "p.dirElectronica", "p.responsable", "p.tramite", "p.url",
						"p.signatura", "trad.destinatarios", "trad.lugar", "trad.nombre", "trad.notificacion",
						"trad.observaciones", "trad.plazos", "trad.recursos", "trad.requisitos", "trad.resolucion",
						"trad.resultat", "trad.resumen" };

				where.append(" AND ( ");
				parametros.put("textos", "%" + textos + "%");

				boolean primero = true;

				for (final String campo : camposBuscablesPorTexto) {
					if (primero) {
						primero = false;
					} else {
						where.append(" OR ");
					}

					where.append(campo);
					where.append(" LIKE :textos ");

				}

				where.append(" ) ");
			}

			if (!StringUtils.isEmpty(titulo)) {
				final String[] camposBuscablesPorTexto = { "trad.nombre" };

				// Si hubiera que buscar en case-insensitive se podria hacer algo tipo:
				// titulo.tolowercase
				// y en la query (aunque no es muy eficiente):
				// where.append("LOWER("+campo+")) like('%pres%')

				where.append(" AND ( ");
				parametros.put("titulo", "%" + titulo + "%");

				boolean primero = true;

				for (final String campo : camposBuscablesPorTexto) {
					if (primero) {
						primero = false;
					} else {
						where.append(" OR ");
					}

					where.append(campo);
					where.append(" LIKE :titulo ");

				}

				where.append(" ) ");
			}

			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(),
					selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	private String filtroProcedimientosActivos(final String alias) {
		/*
		 * AND ( p.validacion = 1 " +
		 * " AND (p.fechaCaducidad >= :ahora or p.fechaCaducidad is null) " + " AND
		 * (p.fechaPublicacion <= :ahora or p.fechaPublicacion is null) )
		 */

		final StringBuilder res = new StringBuilder(" AND ( ");
		res.append(alias);
		res.append(".validacion = ");
		res.append(Validacion.PUBLICA);
		res.append(" AND ( ");
		res.append(campo(alias, "fechaCaducidad"));
		res.append(" >= ");
		res.append(formatoFecha(DateUtils.getNow()));
		res.append(" OR ");
		res.append(campo(alias, "fechaCaducidad"));
		res.append(" is null ) AND ( ");
		res.append(campo(alias, "fechaPublicacion"));
		res.append(" <= ");
		res.append(formatoFecha(DateUtils.getNow()));
		res.append(" OR ");
		res.append(campo(alias, "fechaPublicacion"));
		res.append(" is null ))");

		return res.toString();

	}

	private String filtroProcedimientosCaducados(final String alias) {
		/*
		 * ( p.validacion != 1 ) " +
		 * " 	or ( p.validacion = 1 and p.fechaCaducidad < :manana ) " +
		 * " 	or ( p.validacion = 1 and p.fechaCaducidad is null and p.fechaPublicacion > :manana ) "
		 * + " or ( p.validacion = 1 and p.fechaCaducidad >= :manana and
		 * p.fechaPublicacion > :manana )
		 */

		final Date fecha = DateUtils.getNextDay();

		final StringBuilder res = new StringBuilder(" AND ( ");
		res.append(campo(alias, "validacion"));
		res.append(" != ");
		res.append(Validacion.PUBLICA);
		res.append(" OR ( ");// --1
		res.append(campo(alias, "validacion"));
		res.append(" = ");
		res.append(Validacion.PUBLICA);
		res.append(" AND ");
		res.append(campo(alias, "fechaCaducidad"));
		res.append(" < ");
		res.append(formatoFecha((fecha)));
		res.append(" ) OR ( "); // --2
		res.append(campo(alias, "validacion"));
		res.append(" = ");
		res.append(Validacion.PUBLICA);
		res.append(" AND ( ");
		res.append(campo(alias, "fechaCaducidad"));
		res.append(" is null OR ");
		res.append(campo(alias, "fechaCaducidad"));
		res.append(" >= ");
		res.append(formatoFecha((fecha)));
		res.append(") AND ");
		res.append(campo(alias, "fechaPublicacion"));
		res.append(" > ");
		res.append(formatoFecha((fecha)));
		res.append(" ))");

		return res.toString();

	}

	private StringBuilder campo(final String alias, final String campo) {
		final StringBuilder res = new StringBuilder(alias);
		res.append(".");
		res.append(campo);
		return res;
	}

	private String formatoFecha(final Date d) {
		return "'" + DateUtils.formatearddMMyyyy(d) + "'";
	}

}
