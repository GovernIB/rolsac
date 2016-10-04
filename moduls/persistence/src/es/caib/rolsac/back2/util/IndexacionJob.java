package es.caib.rolsac.back2.util;

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

import java.util.ArrayList;
import java.util.List;


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
        
    	log.debug("Ejecutando indexacion con tipo indexacion:" + tipoIndexacion);
    	
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
		
    	//PASO 2. EJECUTAR LA INDEXACION.
    	List<String> elementosIndexacion = new ArrayList<String>();
    	if ("todo".equals(tipoIndexacion)) {
    		elementosIndexacion.add("ficha");
    		elementosIndexacion.add("procedimiento");
    		elementosIndexacion.add("normativa");
    		elementosIndexacion.add("tramite");
    		elementosIndexacion.add("ua");
    		elementosIndexacion.add("pendientes");
    	} else {
    		elementosIndexacion.add(tipoIndexacion);
    	}
    	
    	
    	for (String elementoIndexacion : elementosIndexacion) {
    		log.debug("Iniciando indexacion " + elementoIndexacion);
    		try {
    			 switch(elementoIndexacion) {
    	        	case "ficha":
    	        		solrProceso.indexarTodoFicha(solrPendienteJob);
    	        		break;
    	        	case "procedimiento":
    	        		solrProceso.indexarTodoProcedimiento(solrPendienteJob);
    	        		break;
    	        	case "normativa":
    	        		solrProceso.indexarTodoNormativa(solrPendienteJob);
    	        		break;
    	        	case "tramite":
    	        		solrProceso.indexarTodoTramite(solrPendienteJob);
    	        		break;
    	        	case "ua":
    	        		solrProceso.indexarTodoUA(solrPendienteJob);
    	        		break;
    	        	case "pendientes":
    	        		solrProceso.indexarPendientes();
    	        		break;
    	        	default:
    	        		log.error("Tipo indexacion no controlado: " + elementoIndexacion); 
    	        		break;
    	        }
    		} catch (DelegateException e) {
    			log.error("Error indexacion " + elementoIndexacion, e); 
    		}     		    	
    		log.debug("Finalizado indexacion " + elementoIndexacion);    		
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
    	
    	
    	log.debug("Fin ejecucion indexacion con tipo indexacion:" + tipoIndexacion);
    }
       
   
}

