package org.ibit.rol.sac.persistence.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Criterion;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

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
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteJobDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.job.IndexacionJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.exception.ExcepcionSolrApi;
import es.caib.solr.api.model.types.EnumAplicacionId;
import es.caib.solr.api.model.types.EnumCategoria;


/**
 * SessionBean para consultar idiomas.
 *
 * @ejb.bean
 *  name="sac/persistence/SolrPendienteFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SolrPendienteFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SolrPendienteFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}
	

	 /**
     * Obtiene los parámetros de configuración del indexer
     * @return SolrIndexer
     * 
     */
	private SolrIndexer obtenerParamIndexer() {
		String url = System.getProperty("es.caib.rolsac.solr.url");
		String index = System.getProperty("es.caib.rolsac.solr.index");
		String user = System.getProperty("es.caib.rolsac.solr.user");
		String pass = System.getProperty("es.caib.rolsac.solr.pass");
		SolrIndexer solrIndexer = SolrFactory.getIndexer(url, index, EnumAplicacionId.ROLSAC, user, pass);
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

        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(SolrPendiente.class);
            criteri.add(Expression.eq("resultado", 0));
            
            List<SolrPendiente> solrPendientes = castList(SolrPendiente.class, criteri.list());

            return solrPendientes;

        } catch (HibernateException he) {
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
    public List<SolrPendienteJob> getListJobs(int cuantos) {

        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(SolrPendienteJob.class);
            criteri.addOrder(Order.desc("fechaIni"));
            criteri.setMaxResults(cuantos);
            
            return castList(SolrPendienteJob.class, criteri.list());

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    
    
    /**
     *  Crea el job.
     * @param tipoIndexacion
     * @throws SchedulerException 
     *  
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
     */

    public void crearJob(final String tipoIndexacion) throws Exception  {
    	
    	//Se ha simplificado, se verán los últimos jobs ejecutados y, si alguno de ellos está sin fecha fin
    	//  se da por hecho que se está ejecutando.
    	List<SolrPendienteJob> jobs = getListJobs(5);
    	for(SolrPendienteJob job : jobs) {
    		if (job.getFechaFin() == null) {
    			throw new Exception("Se está ejecutando un job, intentelo más tarde");
    		}
    	}
    	
    	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
    	scheduler.start(); 
    	JobDetail jobDetail = new JobDetail("IndexacionJob", Scheduler.DEFAULT_GROUP, IndexacionJob.class);
    	Trigger trigger = TriggerUtils.makeImmediateTrigger(0, 0); 
    	scheduler.getContext().put("tipoindexacion", tipoIndexacion);
        trigger.setName("FireOnceNowTrigger");  
    	scheduler.scheduleJob(jobDetail, trigger);
    }
    
    /**
     * Crear una solr pendiente job. 
     * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .     *  
   	 */
	public SolrPendienteJob crearSorlPendienteJob() {
		try
    	{
			final Session session = getSession();
			final SolrPendienteJob solrpendienteJob = new SolrPendienteJob();
	    	solrpendienteJob.setFechaIni(new Date());
	    	session.save(solrpendienteJob); 
			session.flush();
			session.close();
			return solrpendienteJob;
    	 } catch(Exception exception) {
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
    public void cerrarSorlPendienteJob(SolrPendienteJob solrpendienteJob)  {
    	try
    	{
    		Session session = getSession();
	    	solrpendienteJob.setFechaFin(new Date());
	    	session.update(solrpendienteJob); 
			session.flush();
			session.close();
	    } catch(Exception exception) {
			throw new EJBException(exception);
		}
    }
    
	
	/**
	 * Indexa todas las fichas 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @param solrPendienteJob
	 * @throws DelegateException
	 * @throws ExcepcionSolrApi
	 */
	public void indexarTodoFicha(final SolrPendienteJob solrPendienteJob)  {
		
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_FICHA);
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_FICHA_DOCUMENTO);
		} catch (Exception e) {
			log.debug("Error en indexarTodoFicha cuando se desindexa", e);
			return;
		}
		
		final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
		final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
		
		List<Long> listFicha = null;
		try {
			listFicha = fichaDelegate.buscarIdsFichas();
		} catch (Exception e) {
			log.debug("Error en indexarTodoFicha cuando se busca las fichas ids", e);
			return ;
		}
		
    	final int totalFichas = listFicha.size();
    	
		int iFicha = 0;
    	for (Long idFicha : listFicha) {
    		iFicha++;
    		solrPendienteJob.setTotalFicha( Float.valueOf( iFicha * 100/ totalFichas ));
    		solrPendienteJob.setTotalFichaDoc(Float.valueOf( iFicha * 100/ totalFichas ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, fichaDelegate, idFicha, EnumCategoria.ROLSAC_FICHA, solrPendienteJob);
        		
    			//Obtiene Documento ficha
        		List<Long> idDocumentos = docuDelegate.obtenerDocumentosFichaSolr(idFicha);
        		for (Long idDocumento : idDocumentos) {
        			try{
        				solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento, EnumCategoria.ROLSAC_FICHA_DOCUMENTO, solrPendienteJob);	        				
        			} catch (Exception e) {
        				e.printStackTrace();
    					log.error("Se ha producido un error en documento ficha con id " + idDocumento);
    				}
				}
			} catch (Exception e) {
				log.error("Se ha producido un error en la ficha con id " + idFicha);
			}
		}
    	
    	solrPendienteJob.setTotalFicha(100f);
    	solrPendienteJob.setTotalFichaDoc(100f);
    	solrPendienteJob.setFechaFicha(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoFicha cuando se actualiza el job", e);
		}
    	
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo fichas", e);				
		}
	}
	
	
	/**
	* Indexa todas los procedimientos. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoProcedimiento(final SolrPendienteJob solrPendienteJob)  {
		
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_PROCEDIMIENTO);
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO);
		} catch (Exception e) {
			log.debug("Error en indexarTodoProcedimiento cuando se desindexa", e);
			return;
		}
		
		//Obtiene los procedimientos
    	final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
    	final DocumentoDelegate docuDelegate = DelegateUtil.getDocumentoDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	List<Long> listProc = null;
		try {
			listProc = procDelegate.buscarIdsProcedimientos();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoProcedimiento cuando se busca id procedimiento", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalDoc = listProc.size();
    	int iDoc = 0;
    	for (Long idProc : listProc) {
    		iDoc++;
			solrPendienteJob.setTotalProcedimiento( Float.valueOf( iDoc * 100 / totalDoc ));
    		solrPendienteJob.setTotalProcedimientoDoc(Float.valueOf( iDoc * 100 / totalDoc ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, procDelegate, idProc, EnumCategoria.ROLSAC_PROCEDIMIENTO, solrPendienteJob);
        		
    			List<Long> idDocumentos = docuDelegate.obtenerDocumentosProcedimientoSolr(idProc);
        		for (Long idDocumento : idDocumentos) {
        			try{
        				solrDelegate.indexarPendiente(solrIndexer, docuDelegate, idDocumento, EnumCategoria.ROLSAC_PROCEDIMIENTO_DOCUMENTO, solrPendienteJob);	        				
	        		} catch (Exception e) {
    					log.error("Se ha producido un error en documento procedimiento con id " + idDocumento);
    				}
				}
			} catch (Exception e) {
				log.error("Se ha producido un error en el procedimiento con id " + idProc);
			}
    		
		}

    	solrPendienteJob.setTotalProcedimiento(100f);
    	solrPendienteJob.setTotalProcedimientoDoc(100f);
    	solrPendienteJob.setFechaProcedimiento(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoProcedimiento cuando se actualiza el job", e);
		}
    	
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo procedimiento", e);				
		}
	}
	
	
	/**
	 * Indexa todas las normativas. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public void indexarTodoNormativa(final SolrPendienteJob solrPendienteJob) {
		
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_NORMATIVA);
		} catch (Exception e) {
			log.debug("Error en indexarTodoNormativa cuando se desindexa", e);
			return;
		}
		
		final NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	//Obtiene las normativas
    	List<Long> listNorm = null;
		try {
			listNorm = normDelegate.buscarIdsNormativas();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoNormativa cuando se busca las ids de normativa", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalNorm = listNorm.size();
    	int iNor = 0;
    	for (Long idNorm : listNorm) {
    		iNor++;
    		solrPendienteJob.setTotalNormativa( Float.valueOf( iNor * 100/ totalNorm ));
    		solrPendienteJob.setTotalNormativaDoc(Float.valueOf( iNor * 100/ totalNorm ));
    		
    		Normativa normativa;
			try {
				solrDelegate.indexarPendiente(solrIndexer, normDelegate, idNorm, EnumCategoria.ROLSAC_NORMATIVA, solrPendienteJob);
        		
				//Obtiene documentos normativa
				normativa=normDelegate.obtenerNormativa(idNorm);
        		Iterator<Entry<String, Traduccion>> itTradNorm = normativa.getTraduccionMap().entrySet().iterator();
        		while (itTradNorm.hasNext()) {
        			Map.Entry mapTrad = (Map.Entry)itTradNorm.next();
        			TraduccionNormativa tradNorm=(TraduccionNormativa) mapTrad.getValue();
        			
        			Archivo arc = tradNorm != null && tradNorm.getArchivo() != null ? tradNorm.getArchivo() : null;
            		if (arc != null){
            			try{	
            				solrDelegate.indexarPendiente(solrIndexer, normDelegate, arc.getId(), EnumCategoria.ROLSAC_NORMATIVA_DOCUMENTO, solrPendienteJob);
            			} catch (Exception e) {
        					log.error("Se ha producido un error en documento normativa con id " + arc.getId());
        				}
            		}
        		}
			} catch (Exception e) {
				log.error("Se ha producido un error en la normativa con id " + idNorm);
			} 	
    	}
    	
    	solrPendienteJob.setTotalNormativa(100f);
    	solrPendienteJob.setFechaNormativa(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoNormativa cuando se actualiza el job", e);
		}
    	
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo normativa", e);				
		}
	}
	
	/***
	  * Indexa todas los trámites. . 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param solrPendienteJob
	 * @throws DelegateException
	 * @throws ExcepcionSolrApi
	 */
	public  void indexarTodoTramite(final SolrPendienteJob solrPendienteJob)  {
		SolrIndexer solrIndexer  = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_TRAMITE);
		} catch (Exception e) {
			log.debug("Error en indexarTodoTramite cuando se desindexa", e);
			return;
		}
		
		final TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
		final SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	//Obtiene los trámites
    	List<Long> listTram = null; 
		try {
			listTram = tramDelegate.buscarIdsTramites();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoTramite cuando se busca los ids de tramites", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalTram = listTram.size();
    	int iTram = 0;
    	for (Long idTramite : listTram) {
    		iTram++;
    		solrPendienteJob.setTotalTramite( Float.valueOf( iTram * 100/ totalTram ));
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, tramDelegate, idTramite, EnumCategoria.ROLSAC_TRAMITE, solrPendienteJob);
			} catch (Exception e) {
				log.error("Se ha producido un error en el trámite con id " + idTramite);
			}
		}
    	
    	solrPendienteJob.setTotalTramite(100f);
    	solrPendienteJob.setFechaTramite(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoTramite cuando se actualiza el job", e);
		}
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
			log.error("Ha dado un error intentando comitear en indexar todo tramite", e);				
		}
	}
	
	/**
	 * Indexa todas los UA.
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi 
	 * @throws DelegateException 
	 */
	public void indexarTodoUA(final SolrPendienteJob solrPendienteJob)  {
		SolrIndexer solrIndexer = null;
		try {
			solrIndexer = obtenerParamIndexer();
			solrIndexer.desindexarAplicacion(EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA);
		} catch (Exception e) {
			log.debug("Error en indexarTodoUA cuando se desindexa", e);
			return;
		}
		
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
    	SolrPendienteJobDelegate solrDelegate = DelegateUtil.getSolrPendienteJobDelegate();
    	//Obtiene las UA´s
    	List<Long> listUas = null;
    	
		try { 
			listUas = uaDelegate.buscarIdsUas();
		} catch (DelegateException e) {
			log.debug("Error en indexarTodoUA cuando se buscan las ids", e);
			return;
		}
		
		//Recorremos la lista
    	final int totalUA = listUas.size();
    	int iUA = 0;
    	for (Long idUa : listUas) {
    		iUA++;
    		solrPendienteJob.setTotalUnidadAdministrativa( Float.valueOf( iUA * 100 / totalUA ));
    		
    		try {
    			solrDelegate.indexarPendiente(solrIndexer, uaDelegate, idUa, EnumCategoria.ROLSAC_UNIDAD_ADMINISTRATIVA, solrPendienteJob);    					
			} catch (Exception e) {
				log.error("Se ha producido un error en la unidad administrativa con id " + idUa);
			}
		}
    	
    	solrPendienteJob.setTotalUnidadAdministrativa(100f);
    	solrPendienteJob.setFechaUnidadAdministrativa(new Date());
    	try {
			solrDelegate.actualizarJob(solrPendienteJob);
		} catch (Exception e) {
			log.debug("Error en indexarTodoUA cuando se actualiza el job", e);
		}
    	try {
			solrIndexer.commit();
		} catch (ExcepcionSolrApi e) {
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
    	 //org.apache.commons.logging.Log.class
    	Session session = null;
    	SolrIndexer solrIndexer = null;
        try {
        	
        	solrIndexer = obtenerParamIndexer();
        	
        	List<SolrPendiente> solrPendientes = getPendientes();
        	session = getSession();
        	SolrPendienteJobDelegate solrPendienteUnitario = DelegateUtil.getSolrPendienteJobDelegate();
			int i = 0;
        	for(SolrPendiente solrpendiente : solrPendientes) {
        			if (i % 20 == 0) {
        				session.flush();
        				try {
        	            	if (solrIndexer != null) {
        	            		solrIndexer.commit();
        	            	}
        				} catch (ExcepcionSolrApi e) {
        					log.error("Ha dado un error intentando comitear en pendientes con la iteracion i " + i, e);				
        				}
        			} 
        			i++;        			
        			final SolrPendienteResultado solrPendienteResultado = solrPendienteUnitario.indexarPendiente(solrIndexer, solrpendiente);  
        			solrPendienteUnitario.resolverPendiente(solrpendiente, solrPendienteResultado);
        	}        	
        	session.flush();
        	return true;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            if (session != null) {close(session);}
            try {
            	if (solrIndexer != null) {
            		solrIndexer.commit();
            	}
			} catch (ExcepcionSolrApi e) {
				log.error("Ha dado un error intentando comitear en pendientes", e);				
			}
        }
    }
  
    
    /**
     * Método que se encarga de realizar las acciones segun si ha sido correcto o no.
     * @param solrpendiente
     * @param session
     * @param solrPendienteResultado
     * @throws HibernateException
     */
    private void resolverPendiente(final SolrPendiente solrpendiente,
    		final Session session, final SolrPendienteResultado solrPendienteResultado) throws HibernateException {
    	if (solrPendienteResultado != null) {
			if (solrPendienteResultado.isCorrecto()) {
				solrpendiente.setResultado(1);
				session.update(solrpendiente);
			} else {
				final Calendar fechaCalendar  = Calendar.getInstance();
				fechaCalendar.setTime(solrpendiente.getFechaCreacion());
				final Calendar hoyCalendar  = Calendar.getInstance();
				hoyCalendar.setTime(new Date());
				
				final int dias = hoyCalendar.get(Calendar.DATE) - fechaCalendar.get(Calendar.DATE);
				//Si hace 10 dias o + que se crea se marca como erronea porque no se ha podido endexar
				if ( dias >= 10){
					solrpendiente.setResultado(-1);
					solrpendiente.setMensajeError(solrPendienteResultado.getMensaje());
					session.update(solrpendiente);
				}else{
					log.error("No se ha podido realizar la operación (dias ejecutandose:"+dias+")con el registro : "+solrpendiente.getId());
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
        	SolrIndexer solrIndexer = obtenerParamIndexer();
        	solrIndexer.desindexarCaducados();
        	
        } catch (ExcepcionSolrApi e) {
        	throw new EJBException(e);
		}
		return true;
    }
    
    /**
     * Comprueba si hay pendientes por ejecutar con la misma acción y elemento.
     * @param tipo
     * @param idElemento
     * @param accion
     * @return
     */
    private Boolean hayPendientes(String tipo, Long idElemento, Long accion) {
    	 final Session session = getSession();
         try 
         {
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
         } catch (HibernateException he) {
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
    public Boolean grabarSolrPendiente(String tipo, Long idElemento, Long accion) {

        Session session = getSession();
        try {
        	Boolean resultado;
        	//Si hay pendientes, no insertar
        	if (hayPendientes(tipo, idElemento, accion)){
        		resultado = true;
        	} else {
	            final SolrPendiente solrPendiente = new SolrPendiente();
	            solrPendiente.setTipo(tipo);
	            solrPendiente.setIdElemento(idElemento);
	            solrPendiente.setAccion(accion.intValue());
	            solrPendiente.setFechaCreacion(new java.util.Date()); //import java.util.Date;
	            solrPendiente.setResultado(0);
	            session.save(solrPendiente);
	            session.flush();
	            resultado = true;
        	}
            return resultado;
        } catch (HibernateException he) {
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
    public ResultadoBusqueda getPendientes(int pagina, int resultados) {
    	
    	List<SolrPendiente> solrPendTotal = getPendientes();
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	
    	int indiceDesde = pagina * resultados;
    	int indiceHasta = indiceDesde + resultados;
    	int tamanyoLista = solrPendTotal.size();
    	
    	if (indiceHasta > tamanyoLista) {
    		indiceHasta = tamanyoLista;
    	}
    		
    	resultadoBusqueda.setTotalResultados(solrPendTotal.size());
    	
    	if ( resultados < RESULTATS_CERCA_TOTS ) {
    		solrPendTotal = solrPendTotal.subList( indiceDesde, indiceHasta );    		
    	}
    	
    	resultadoBusqueda.setListaResultados(solrPendTotal);
    	
    	return resultadoBusqueda;

    	
    }

    
}
