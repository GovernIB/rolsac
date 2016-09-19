package org.ibit.rol.sac.persistence.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteProcesoDelegate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;


/***
 * la indexación. 
 * @author slromero
 *
 */
public class IndexacionJob implements Job  {

	/** El log. **/
	protected static Log log = LogFactory.getLog(IndexacionJob.class);

	/***
	 * Metodo para ejcutar el job.
	 */
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
    	SchedulerContext schedulerContext;
    	SolrPendienteJob solrPendienteJob = null;
    	
    	try {
			schedulerContext = context.getScheduler().getContext();
		} catch (SchedulerException e) {
			log.debug("Error extrayendo el contexto en el job.", e);
			return;
		}
    	final String tipoIndexacion = (String) schedulerContext.get("tipoindexacion");
        
    	SolrPendienteDelegate solrDelegate = DelegateUtil.getSolrPendienteDelegate();
    	SolrPendienteProcesoDelegate solrProceso = DelegateUtil.getSolrPendienteProcesoDelegate();
    	
    	//Entrar solo si es no pendientes
    	if (!"pendientes".equals(tipoIndexacion)) {
	    	///PASO 1. GUARDAR EL JOB.
	    	try {
				 solrPendienteJob = solrDelegate.crearSorlPendienteJob();
			} catch (DelegateException e) {
				log.debug("Error creando el job en bbdd.", e);
				return;
			}
    	}
		
    	log.error("Ejecutando el indexacion job con tipoIndexacion:" + tipoIndexacion);
    	
    	//PASO 2. EJECUTAR LA INDEXACION.
        switch(tipoIndexacion) {
        	case "todo":
        		try { solrProceso.indexarTodoFicha(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ficha", e); } log.debug("Finalizado indexacion ficha");
        		try { solrProceso.indexarTodoProcedimiento(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo procedimiento", e); } log.debug("Finalizado indexacion procedimiento");
        		try { solrProceso.indexarTodoNormativa(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo normativa", e); } log.debug("Finalizado indexacion normativa");
        		try { solrProceso.indexarTodoTramite(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo tramite", e); } log.debug("Finalizado indexacion tramite");
        		try { solrProceso.indexarTodoUA(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ua", e); } log.debug("Finalizado indexacion ua");
        		break;
        	case "ficha":
        		try { solrProceso.indexarTodoFicha(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ficha", e); } log.debug("Finalizado indexacion ficha");
        		break;
        	case "procedimiento":
        		try { solrProceso.indexarTodoProcedimiento(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo procedimiento", e); } log.debug("Finalizado indexacion procedimiento");
        		break;
        	case "normativa":
        		try { solrProceso.indexarTodoNormativa(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo normativa", e); } log.debug("Finalizado indexacion normativa");
        		break;
        	case "tramite":
        		try { solrProceso.indexarTodoTramite(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo tramite", e); } log.debug("Finalizado indexacion tramite");
        		break;
        	case "ua":
        		try { solrProceso.indexarTodoUA(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ua", e); } log.debug("Finalizado indexacion ua");
        		break;
        	case "pendientes":
        		try { solrProceso.indexarPendientes(); } catch (DelegateException e) { log.error("Error indexando pendientes", e); } log.debug("Finalizado indexacion pendientes");
        		break;
        	default:
        		log.debug("NO HAY TIPO DE INDEXACION!!!"); 
        		break;
        }
     
    	//Entrar solo si es no pendientes
    	if (!"pendientes".equals(tipoIndexacion)) {
	        //PASO 3. CERRAR JOB.
	        try {
				solrDelegate.cerrarSorlPendienteJob(solrPendienteJob);
			} catch (DelegateException e) {
				log.error("Error cerrando el job", e);
			}
    	}
    }
    
    /**
     * Job que se ejecuta todos los dias para indexar lo pendiente por la noche.
     */
    //@Scheduled(cron = "* 10 15 * *")
    //@Scheduled(cron = "#{systemProperties['es.caib.rolsac.solr.jobpendientes']") 
    /*public void jobIndexacionNocturna() {
    	SolrPendienteDelegate solrDelegate = DelegateUtil.getSolrPendienteDelegate();
    	try { 
    		solrDelegate.indexarPendientes();
    	} catch (DelegateException e) { log.error("Error indexando pendientes", e); }

		log.error("jobIndexacionNocturna log.");	
    }
    
    @Scheduled(cron = "0 0/5 * *")
    public void jobIndexacionNocturna2() {
    	SolrPendienteDelegate solrDelegate = DelegateUtil.getSolrPendienteDelegate();
    	try { 
    		solrDelegate.indexarPendientes();
    	} catch (DelegateException e) { log.error("Error indexando pendientes", e); }
    	log.error("jobIndexacionNocturna log 2.");
    }*/

   
}

