package org.ibit.rol.sac.persistence.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.exception.ExcepcionElasticApi;
import es.caib.solr.api.exception.ExcepcionSolrApi;
import es.caib.solr.api.exception.ExcepcionSolrElasticApi;
import es.caib.solr.api.model.types.EnumAplicacionId;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

/**
 * SessionBean para consultar idiomas.
 *
 * @ejb.bean name="sac/persistence/SolrPendienteFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.SolrPendienteFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SolrPendienteFacadeEJB extends HibernateEJB {

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
	 * Obtiene los parámetros de configuración del indexer
	 *
	 * @return SolrIndexer
	 *
	 */
	private SolrIndexer obtenerParamIndexer() {
		final String url = System.getProperty("es.caib.rolsac.solr.url");
		final String index = System.getProperty("es.caib.rolsac.solr.index");
		final String user = System.getProperty("es.caib.rolsac.solr.user");
		final String pass = System.getProperty("es.caib.rolsac.solr.pass");
		final String iactivo = System.getProperty("es.caib.rolsac.solr.activo");
		final boolean solrActivo = iactivo != null && !"S".equals(iactivo.toUpperCase());

		final String urlElastic = System.getProperty("es.caib.rolsac.elastic.url");
		final String userElastic = System.getProperty("es.caib.rolsac.elastic.user");
		final String passElastic = System.getProperty("es.caib.rolsac.elastic.pass");
		final String iactivoElastic = System.getProperty("es.caib.rolsac.solr.activo");
		final boolean elasticActivo = iactivoElastic != null && !"S".equals(iactivo.toUpperCase());

		final SolrIndexer solrIndexer = SolrFactory.getIndexer(url, index, EnumAplicacionId.ROLSAC, user, pass,
				urlElastic, userElastic, passElastic, solrActivo, elasticActivo);
		return solrIndexer;
	}

	/**
	 * Lista todos los SolrPendientes
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Devuelve un listado de todos los SolrPendientes.
	 */
	public List<SolrPendiente> getPendientes() {

		final Session session = getSession();
		try {
			final Criteria criteri = session.createCriteria(SolrPendiente.class);
			criteri.add(Expression.eq("resultado", 0));
			criteri.addOrder(Order.asc("id"));
			final List<SolrPendiente> solrPendientes = castList(SolrPendiente.class, criteri.list());

			return solrPendientes;

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista todos los SolrPendientesJob según cuantos.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Devuelve un listado de todos los SolrPendientes.
	 */
	public List<SolrPendienteJob> getListJobs(final int cuantos) {

		final Session session = getSession();
		try {
			final Criteria criteri = session.createCriteria(SolrPendienteJob.class);
			criteri.addOrder(Order.desc("fechaIni"));
			criteri.setMaxResults(cuantos);

			return castList(SolrPendienteJob.class, criteri.list());

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Revisa si se está ejecutando algún job.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Booleano True si hay algo activo y false si no lo está.
	 */
	public boolean checkJobsActivos() {
		boolean retorno = false;
		// Se ha simplificado, se verán los últimos jobs ejecutados y, si alguno de
		// ellos está sin fecha fin
		// se da por hecho que se está ejecutando.
		final List<SolrPendienteJob> jobs = getListJobs(5);
		for (final SolrPendienteJob job : jobs) {
			if (job.getFechaFin() == null) {
				retorno = true;
			}
		}
		return retorno;
	}

	/**
	 * Crear una solr pendiente job.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Booleano indicando si se indexan todos los procesos pendientes . *
	 */
	public SolrPendienteJob crearSorlPendienteJob() {
		try {
			final Session session = getSession();
			final SolrPendienteJob solrpendienteJob = new SolrPendienteJob();
			solrpendienteJob.setFechaIni(new Date());
			session.save(solrpendienteJob);
			session.flush();
			session.close();
			return solrpendienteJob;
		} catch (final Exception exception) {
			throw new EJBException(exception);
		}
	}

	/**
	 * Cerrando el pendiente job.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 */
	public void cerrarSorlPendienteJob(final SolrPendienteJob solrpendienteJob) {
		try {
			final Session session = getSession();
			solrpendienteJob.setFechaFin(new Date());
			session.update(solrpendienteJob);
			session.flush();
			session.close();
		} catch (final Exception exception) {
			throw new EJBException(exception);
		}
	}

	/**
	 * Cerrando todos los jobs.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 */
	public Boolean cerrarJobs() {
		Session session = null;
		try {
			session = getSession();
			final Query query = getSession()
					.createQuery("from SolrPendienteJob solrJob where solrJob.fechaFin is null");
			final List<SolrPendienteJob> jobs = query.list();
			for (final SolrPendienteJob job : jobs) {
				job.setFechaFin(new Date());
				session.update(job);
			}
			session.flush();
			return true;
		} catch (final Exception exception) {
			throw new EJBException(exception);
		} finally {
			if (session != null) {
				close(session);
			}
		}
	}

	/**
	 * Método que se encarga de realizar las acciones segun si ha sido correcto o
	 * no.
	 *
	 * @param solrpendiente
	 * @param session
	 * @param solrPendienteResultado
	 * @throws HibernateException
	 */
	private void resolverPendiente(final SolrPendiente solrpendiente, final Session session,
			final SolrPendienteResultado solrPendienteResultado) throws HibernateException {
		if (solrPendienteResultado != null) {
			if (solrPendienteResultado.isCorrecto()) {
				solrpendiente.setResultado(1);
				session.update(solrpendiente);
			} else {
				final Calendar fechaCalendar = Calendar.getInstance();
				fechaCalendar.setTime(solrpendiente.getFechaCreacion());
				final Calendar hoyCalendar = Calendar.getInstance();
				hoyCalendar.setTime(new Date());

				final int dias = hoyCalendar.get(Calendar.DATE) - fechaCalendar.get(Calendar.DATE);
				// Si hace 10 dias o + que se crea se marca como erronea porque no se ha podido
				// endexar
				final String sdias = System.getProperty("es.caib.rolsac.solr.dias");
				int diasMaximos = 10;
				if (sdias != null) {
					diasMaximos = Integer.parseInt(sdias);
				}
				if (dias >= diasMaximos) {
					solrpendiente.setResultado(-1);
					solrpendiente.setMensajeError(solrPendienteResultado.getMensaje());
					session.update(solrpendiente);
				} else {
					log.error("No se ha podido realizar la operación (dias ejecutandose:" + dias + ")con el registro : "
							+ solrpendiente.getId());
				}
			}
		}
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Booleano indicando si se han borrado todas las caducadas .
	 */
	public Boolean borrarCaducadas() {

		try {
			final SolrIndexer solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCaducados();

		} catch (final ExcepcionSolrApi e) {
			throw new EJBException(e);
		} catch (final ExcepcionElasticApi e) {
			throw new EJBException(e);
		} catch (final ExcepcionSolrElasticApi e) {
			throw new EJBException(e);
		}
		return true;
	}

	/**
	 * Comprueba si hay pendientes por ejecutar con la misma acción y elemento.
	 *
	 * @param tipo
	 * @param idElemento
	 * @param accion
	 * @return
	 */
	private Boolean hayPendientes(final String tipo, final Long idElemento, final Long accion) {
		final Session session = getSession();
		try {
			final Criteria criteri = session.createCriteria(SolrPendiente.class);
			criteri.add(Expression.eq("resultado", 0));
			criteri.add(Expression.eq("tipo", tipo));
			criteri.add(Expression.eq("idElemento", idElemento));
			criteri.add(Expression.eq("accion", accion.intValue()));
			final int cuantos = criteri.list().size();
			if (cuantos == 0) {
				return false;
			} else {
				return true;
			}
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Booleano indicando si se han grabado todos los solar pendientes .
	 */
	public Boolean grabarSolrPendiente(final String tipo, final Long idElemento, final Long accion) {

		final Session session = getSession();
		try {
			Boolean resultado;
			// Si hay pendientes, no insertar
			if (hayPendientes(tipo, idElemento, accion)) {
				resultado = true;
			} else {
				// Si es un desindexar, habría que ver que borrar todos los indexar pendientes
				if (accion == 2l) {
					borrarReindexarPendientes(tipo, idElemento);
				}

				final SolrPendiente solrPendiente = new SolrPendiente();
				solrPendiente.setTipo(tipo);
				solrPendiente.setIdElemento(idElemento);
				solrPendiente.setAccion(accion.intValue());
				solrPendiente.setFechaCreacion(new java.util.Date()); // import java.util.Date;
				solrPendiente.setResultado(0);
				session.save(solrPendiente);
				session.flush();
				resultado = true;
			}
			return resultado;
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	private void borrarReindexarPendientes(final String tipo, final Long idElemento) {
		final Session session = getSession();
		try {
			final Criteria criteri = session.createCriteria(SolrPendiente.class);
			criteri.add(Expression.eq("resultado", 0));
			criteri.add(Expression.eq("idElemento", idElemento));
			criteri.add(Expression.eq("tipo", tipo));
			criteri.add(Expression.eq("accion", 1));
			final List<SolrPendiente> solrPendientes = castList(SolrPendiente.class, criteri.list());
			for (final SolrPendiente solrPendiente : solrPendientes) {
				session.delete(solrPendiente);
				session.flush();
			}

		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Obtiene los pendientes para la paginación
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param pagina
	 * @param resultados
	 * @return
	 */
	public ResultadoBusqueda getPendientes(final int pagina, final int resultados) {

		List<SolrPendiente> solrPendTotal = getPendientes();
		final ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		final int indiceDesde = pagina * resultados;
		int indiceHasta = indiceDesde + resultados;
		final int tamanyoLista = solrPendTotal.size();

		if (indiceHasta > tamanyoLista) {
			indiceHasta = tamanyoLista;
		}

		resultadoBusqueda.setTotalResultados(solrPendTotal.size());

		if (resultados < RESULTATS_CERCA_TOTS) {
			solrPendTotal = solrPendTotal.subList(indiceDesde, indiceHasta);
		}

		resultadoBusqueda.setListaResultados(solrPendTotal);

		return resultadoBusqueda;

	}

}
