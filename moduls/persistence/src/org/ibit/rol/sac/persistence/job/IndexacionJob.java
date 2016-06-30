package org.ibit.rol.sac.persistence.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
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
	 * Método para ejcutar el job.
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
		
    	
    	//PASO 2. EJECUTAR LA INDEXACIÓN.
        switch(tipoIndexacion) {
        	case "todo":
        		try { solrDelegate.indexarTodoFicha(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ficha", e); }
        		try { solrDelegate.indexarTodoProcedimiento(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo procedimiento", e); }
        		try { solrDelegate.indexarTodoNormativa(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo normativa", e); }
        		try { solrDelegate.indexarTodoTramite(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo tramite", e); }
        		try { solrDelegate.indexarTodoUA(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ua", e); }
        		break;
        	case "ficha":
        		try { solrDelegate.indexarTodoFicha(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ficha", e); }
        		break;
        	case "procedimiento":
        		try { solrDelegate.indexarTodoProcedimiento(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo procedimiento", e); }
        		break;
        	case "normativa":
        		try { solrDelegate.indexarTodoNormativa(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo normativa", e); }
        		break;
        	case "tramite":
        		try { solrDelegate.indexarTodoTramite(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo tramite", e); }
        		break;
        	case "ua":
        		try { solrDelegate.indexarTodoUA(solrPendienteJob); } catch (DelegateException e) { log.error("Error indexando todo ua", e); }
        		break;
        	case "pendientes":
        		try { solrDelegate.indexarPendientes(); } catch (DelegateException e) { log.error("Error indexando pendientes", e); }
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

