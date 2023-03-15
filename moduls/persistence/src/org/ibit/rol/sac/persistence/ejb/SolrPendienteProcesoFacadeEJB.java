package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.DocumentoServicioDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.ServicioDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteJobDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.exception.ExcepcionElasticApi;
import es.caib.solr.api.exception.ExcepcionSolrApi;
import es.caib.solr.api.exception.ExcepcionSolrElasticApi;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * SessionBean para consultar idiomas.
 *
 * @ejb.bean name="sac/persistence/SolrPendienteProcesoFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.SolrPendienteProcesoFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="NotSupported"
 */
public abstract class SolrPendienteProcesoFacadeEJB extends HibernateEJB {

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
	 * Indexa todas las fichas
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param solrPendienteJob
	 * @throws DelegateException
	 * @throws ExcepcionSolrApi
	 */
	public void indexarTodoFicha(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO FICHA");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_FICHA);
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_FICHA_DOCUMENTO);
		} catch (final Exception e) {
			log.error("Error en indexarTodoFicha cuando se desindexa", e);
			return;
		}

		final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
		final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();

		List<Long> listFicha = null;
		try {
			listFicha = fichaDelegate.buscarIdsFichas();
		} catch (final Exception e) {
			log.error("Error en indexarTodoFicha cuando se busca las fichas ids", e);
			return;
		}

		final int totalFichas = listFicha.size();
		log.debug("Numero de fichas a revisar para indexar: " + totalFichas);

		int iFicha = 0;
		for (final Long idFicha : listFicha) {
			iFicha++;

			solrPendienteJob.setTotalFicha(Float.valueOf(iFicha * 100 / totalFichas));
			solrPendienteJob.setTotalFichaDoc(Float.valueOf(iFicha * 100 / totalFichas));
			try {
				solrDelegate.indexarPendiente(solrIndexer, fichaDelegate, idFicha, EnumCategoria.ROLSAC_FICHA,
						solrPendienteJob);

				// Obtiene Documento ficha
				final List<Long> idDocumentos = docuDelegate.obtenerDocumentosFichaSolr(idFicha);
				for (final Long idDocumento : idDocumentos) {
					try {
						solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento,
								EnumCategoria.ROLSAC_FICHA_DOCUMENTO, solrPendienteJob);
					} catch (final Exception e) {
						log.error("Se ha producido un error en documento ficha con id " + idDocumento, e);
					}
				}
			} catch (final Exception e) {
				log.error("Se ha producido un error en la ficha con id " + idFicha, e);
			}
		}

		log.debug("Se han terminado de revisar las fichas para indexar.");

		solrPendienteJob.setTotalFicha(100f);
		solrPendienteJob.setTotalFichaDoc(100f);
		solrPendienteJob.setFechaFicha(new Date());

		try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (final Exception e) {
			log.error("Error en indexarTodoFicha cuando se actualiza el job", e);
		}

		try {
			solrIndexer.commit();
		} catch (final ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo fichas", e);
		} catch (final ExcepcionSolrElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo fichas", e);
		} catch (final ExcepcionElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo fichas", e);
		}
	}

	/**
	 * Indexa todas los procedimientos.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoProcedimiento(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO PROCEDIMIENTO");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_PROCEDIMIENTO);
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO);
		} catch (final Exception e) {
			log.error("Error en indexarTodoProcedimiento cuando se desindexa", e);
			return;
		}

		// Obtiene los procedimientos
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
		List<Long> listProc = null;
		try {
			listProc = procDelegate.buscarIdsProcedimientos();
		} catch (final DelegateException e) {
			log.error("Error en indexarTodoProcedimiento cuando se busca id procedimiento", e);
			return;
		}

		// Recorremos la lista
		final int totalProc = listProc.size();

		log.debug("Numero de procedimientos a revisar para indexar: " + totalProc);

		int iDoc = 0;
		for (final Long idProc : listProc) {
			iDoc++;
			solrPendienteJob.setTotalProcedimiento(Float.valueOf(iDoc * 100 / totalProc));
			solrPendienteJob.setTotalProcedimientoDoc(Float.valueOf(iDoc * 100 / totalProc));
			try {
				solrDelegate.indexarPendiente(solrIndexer, procDelegate, idProc, EnumCategoria.ROLSAC_PROCEDIMIENTO,
						solrPendienteJob);

				final List<Long> idDocumentos = docuDelegate.obtenerDocumentosProcedimientoSolr(idProc);
				for (final Long idDocumento : idDocumentos) {
					try {
						solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento,
								EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO, solrPendienteJob);
					} catch (final Exception e) {
						log.error("Se ha producido un error en procedimiento " + idProc + " para documento  con id "
								+ idDocumento, e);
					}
				}
			} catch (final Exception e) {
				log.error("Se ha producido un error en el procedimiento con id " + idProc, e);
			}

		}

		log.debug("Se han terminado de revisar los procedimientos para indexar.");

		solrPendienteJob.setTotalProcedimiento(100f);
		solrPendienteJob.setTotalProcedimientoDoc(100f);
		solrPendienteJob.setFechaProcedimiento(new Date());
		try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (final Exception e) {
			log.error("Error en indexarTodoProcedimiento cuando se actualiza el job", e);
		}

		try {
			solrIndexer.commit();
		} catch (final ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo procedimiento", e);
		} catch (final ExcepcionSolrElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo procedimiento", e);
		} catch (final ExcepcionElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo procedimiento", e);
		}
	}

	/**
	 * Indexa todas los servicios.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoServicio(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO SERVICIO");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_SERVICIO);
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_SERVICIO_DOCUMENTO);
		} catch (final Exception e) {
			log.error("Error en indexarTodoServicio cuando se desindexa", e);
			return;
		}

		// Obtiene los servicios
		final ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
		final DocumentoServicioDelegate docuDelegate = DelegateUtil.getDocumentoServicioDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
		List<Long> listServ = null;
		try {
			listServ = servicioDelegate.buscarIdsServicios();
		} catch (final DelegateException e) {
			log.error("Error en indexarTodoServicio cuando se busca id servicio", e);
			return;
		}

		// Recorremos la lista
		final int totalServicios = listServ.size();

		log.debug("Numero de servicios a revisar para indexar: " + totalServicios);

		int iDoc = 0;
		for (final Long idServicio : listServ) {
			iDoc++;
			solrPendienteJob.setTotalServicio(Float.valueOf(iDoc * 100 / totalServicios));
			solrPendienteJob.setTotalServicioDoc(Float.valueOf(iDoc * 100 / totalServicios));
			try {
				solrDelegate.indexarPendiente(solrIndexer, servicioDelegate, idServicio, EnumCategoria.ROLSAC_SERVICIO,
						solrPendienteJob);

				final List<Long> idDocumentos = docuDelegate.obtenerDocumentosServiciosSolr(idServicio);
				for (final Long idDocumento : idDocumentos) {
					try {
						solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento,
								EnumCategoria.ROLSAC_SERVICIO_DOCUMENTO, solrPendienteJob);
					} catch (final Exception e) {
						log.error("Se ha producido un error en servicio " + idServicio + " para documento  con id "
								+ idDocumento, e);
					}
				}
			} catch (final Exception e) {
				log.error("Se ha producido un error en el servicio con id " + idServicio, e);
			}

		}

		log.debug("Se han terminado de revisar los servicios para indexar.");

		solrPendienteJob.setTotalServicio(100f);
		solrPendienteJob.setTotalServicioDoc(100f);
		solrPendienteJob.setFechaServicio(new Date());
		try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (final Exception e) {
			log.error("Error en indexarTodoServicio cuando se actualiza el job", e);
		}

		try {
			solrIndexer.commit();
		} catch (final ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo servicio", e);
		} catch (final ExcepcionSolrElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo servicio", e);
		} catch (final ExcepcionElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo servicio", e);
		}
	}

	/**
	 * Indexa todas las normativas.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoNormativa(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO NORMATIVA");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_NORMATIVA);
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO);
		} catch (final Exception e) {
			log.error("Error en indexarTodoNormativa cuando se desindexa", e);
			return;
		}

		final NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
		// Obtiene las normativas
		List<Long> listNorm = null;
		try {
			listNorm = normDelegate.buscarIdsNormativas();
		} catch (final DelegateException e) {
			log.error("Error en indexarTodoNormativa cuando se busca las ids de normativa", e);
			return;
		}

		// Recorremos la lista
		final int totalNorm = listNorm.size();
		int iNor = 0;
		for (final Long idNorm : listNorm) {
			iNor++;
			solrPendienteJob.setTotalNormativa(Float.valueOf(iNor * 100 / totalNorm));
			solrPendienteJob.setTotalNormativaDoc(Float.valueOf(iNor * 100 / totalNorm));

			Normativa normativa;
			try {
				solrDelegate.indexarPendiente(solrIndexer, normDelegate, idNorm, EnumCategoria.ROLSAC_NORMATIVA,
						solrPendienteJob);

				// Obtiene documentos normativa
				normativa = normDelegate.obtenerNormativa(idNorm);
				final Iterator<Entry<String, Traduccion>> itTradNorm = normativa.getTraduccionMap().entrySet()
						.iterator();
				while (itTradNorm.hasNext()) {
					final Map.Entry mapTrad = itTradNorm.next();
					final TraduccionNormativa tradNorm = (TraduccionNormativa) mapTrad.getValue();

					final Archivo arc = tradNorm != null && tradNorm.getArchivo() != null ? tradNorm.getArchivo()
							: null;
					if (arc != null) {
						try {
							solrDelegate.indexarPendiente(solrIndexer, normDelegate, arc.getId(),
									EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO, solrPendienteJob);
						} catch (final Exception e) {
							log.error("Se ha producido un error en normativa con id " + idNorm + " para archivo con id "
									+ arc.getId(), e);
						}
					}
				}
			} catch (final Exception e) {
				log.error("Se ha producido un error en la normativa con id " + idNorm, e);
			}
		}

		log.debug("Se han terminado de revisar las normativas para indexar.");

		solrPendienteJob.setTotalNormativa(100f);
		solrPendienteJob.setFechaNormativa(new Date());
		try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (final Exception e) {
			log.error("Error en indexarTodoNormativa cuando se actualiza el job", e);
		}

		try {
			solrIndexer.commit();
		} catch (final ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo normativa", e);
		} catch (final ExcepcionSolrElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo normativa", e);
		} catch (final ExcepcionElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo normativa", e);
		}
	}

	/***
	 * Indexa todas los trámites. .
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param solrPendienteJob
	 * @throws DelegateException
	 * @throws ExcepcionSolrApi
	 */
	public void indexarTodoTramite(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO TRAMITE");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_TRAMITE);
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO);
		} catch (final Exception e) {
			log.error("Error en indexarTodoTramite cuando se desindexa", e);
			return;
		}

		final TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
		final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();

		// Obtiene los trámites
		List<Long> listTram = null;
		try {
			listTram = tramDelegate.buscarIdsTramites();
		} catch (final DelegateException e) {
			log.error("Error en indexarTodoTramite cuando se busca los ids de tramites", e);
			return;
		}

		// Recorremos la lista
		final int totalTram = listTram.size();

		log.debug("Numero de tramites a revisar para indexar: " + totalTram);

		int iTram = 0;
		for (final Long idTramite : listTram) {
			iTram++;
			solrPendienteJob.setTotalTramite(Float.valueOf(iTram * 100 / totalTram));
			try {
				solrDelegate.indexarPendiente(solrIndexer, tramDelegate, idTramite, EnumCategoria.ROLSAC_TRAMITE,
						solrPendienteJob);
				final List<Long> idDocumentos = docuDelegate.obtenerDocumentosTramiteSolr(idTramite);
				for (final Long idDocumento : idDocumentos) {
					try {
						solrDelegate.indexarPendiente(solrIndexer, tramDelegate, idDocumento,
								EnumCategoria.ROLSAC_TRAMITE_DOCUMENTO, solrPendienteJob);
					} catch (final Exception e) {
						log.error("Se ha producido un error en tramite con id " + iTram + " para documento con id "
								+ idDocumento, e);
					}
				}
			} catch (final Exception e) {
				log.error("Se ha producido un error en el trámite con id " + idTramite, e);
			}
		}

		log.debug("Se han terminado de revisar los tramites para indexar.");

		solrPendienteJob.setTotalTramite(100f);
		solrPendienteJob.setFechaTramite(new Date());
		try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (final Exception e) {
			log.error("Error en indexarTodoTramite cuando se actualiza el job", e);
		}
		try {
			solrIndexer.commit();
		} catch (final ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo tramite", e);
		} catch (final ExcepcionSolrElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo tramite", e);
		} catch (final ExcepcionElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo tramite", e);
		}
	}

	/**
	 * Indexa todas los UA.
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoUA(final SolrPendienteJob solrPendienteJob) {
		log.debug("INDEXAR TODO UA");
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarCategoria(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA);
		} catch (final Exception e) {
			log.error("Error en indexarTodoUA cuando se desindexa", e);
			return;
		}

		final UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
		// Obtiene las UA´s
		List<Long> listUas = null;

		try {
			listUas = uaDelegate.buscarIdsUas();
		} catch (final DelegateException e) {
			log.error("Error en indexarTodoUA cuando se buscan las ids", e);
			return;
		}

		// Recorremos la lista
		final int totalUA = listUas.size();
		log.debug("Numero de UAs a revisar para indexar: " + totalUA);
		int iUA = 0;
		for (final Long idUa : listUas) {
			iUA++;
			solrPendienteJob.setTotalUnidadAdministrativa(Float.valueOf(iUA * 100 / totalUA));
			try {
				solrDelegate.indexarPendiente(solrIndexer, uaDelegate, idUa, EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA,
						solrPendienteJob);
			} catch (final Exception e) {
				log.error("Se ha producido un error en la unidad administrativa con id " + idUa, e);
			}
		}

		log.debug("Se han terminado de revisar las UAs para indexar.");

		solrPendienteJob.setTotalUnidadAdministrativa(100f);
		solrPendienteJob.setFechaUnidadAdministrativa(new Date());
		try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (final Exception e) {
			log.error("Error en indexarTodoUA cuando se actualiza el job", e);
		}
		try {
			solrIndexer.commit();
		} catch (final ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo ua", e);
		} catch (final ExcepcionSolrElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo ua", e);
		} catch (final ExcepcionElasticApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo ua", e);
		}
	}

	/**
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 *
	 * @return Booleano indicando si se indexan todos los procesos pendientes .
	 * @throws DelegateException
	 */
	public Boolean indexarPendientes() throws DelegateException {
		// org.apache.commons.logging.Log.class
		Session session = null;
		SolrIndexer solrIndexer = null;
		try {

			solrIndexer = obtenerParamIndexer();
			final SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
			final List<SolrPendiente> solrPendientes = solrPendiente.getPendientes();
			session = getSession();
			final SolrPendienteJobDelegate solrPendienteUnitario = DelegateUtil.getSolrPendienteJobDelegate();
			int i = 0;
			for (final SolrPendiente solrpendiente : solrPendientes) {
				if (i % 20 == 0) {
					session.flush();
					try {
						if (solrIndexer != null) {
							solrIndexer.commit();
						}
					} catch (final ExcepcionSolrApi e) {
						log.error("Ha dado un error intentando comitear en pendientes con la iteracion i " + i, e);
					} catch (final ExcepcionSolrElasticApi e) {
						log.error("Ha dado un error intentando comitear en pendientes con la iteracion i " + i, e);
					} catch (final ExcepcionElasticApi e) {
						log.error("Ha dado un error intentando comitear en pendientes con la iteracion i " + i, e);
					}
				}
				i++;
				final SolrPendienteResultado solrPendienteResultado = solrPendienteUnitario
						.indexarPendiente(solrIndexer, solrpendiente);
				solrPendienteUnitario.resolverPendiente(solrpendiente, solrPendienteResultado);
			}
			session.flush();
			return true;
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			if (session != null) {
				close(session);
			}
			try {
				if (solrIndexer != null) {
					solrIndexer.commit();
				}
			} catch (final ExcepcionSolrApi e) {
				log.error("Ha dado un error intentando comitear en pendientes", e);
			} catch (final ExcepcionSolrElasticApi e) {
				log.error("Ha dado un error intentando comitear en pendientes", e);
			} catch (final ExcepcionElasticApi e) {
				log.error("Ha dado un error intentando comitear en pendientes", e);
			}
		}
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
		final boolean solrActivo = iactivo != null && "S".equals(iactivo.toUpperCase());

		final String urlElastic = System.getProperty("es.caib.rolsac.elastic.url");
		final String userElastic = System.getProperty("es.caib.rolsac.elastic.user");
		final String passElastic = System.getProperty("es.caib.rolsac.elastic.pass");
		final String iactivoElastic = System.getProperty("es.caib.rolsac.elastic.activo");
		final boolean elasticActivo = iactivoElastic != null && "S".equals(iactivoElastic.toUpperCase());

		final String elasticCategoriasActivas = System.getProperty("es.caib.rolsac.elastic.categoriasActivas");
		final List<EnumCategoria> categorias = new ArrayList<>();
		if (elasticCategoriasActivas != null && !elasticCategoriasActivas.isEmpty()) {
			for (final String categoria : elasticCategoriasActivas.split(",")) {
				final EnumCategoria enumCategoria = EnumCategoria.fromString(categoria.trim());
				if (enumCategoria != null) {
					categorias.add(enumCategoria);
				}
			}
		}

		final SolrIndexer solrIndexer = SolrFactory.getIndexer(url, index, EnumAplicacionId.ROLSAC, user, pass,
				urlElastic, userElastic, passElastic, solrActivo, elasticActivo, categorias);
		return solrIndexer;
	}
}
