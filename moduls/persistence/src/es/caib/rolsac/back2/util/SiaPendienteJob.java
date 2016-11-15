package es.caib.rolsac.back2.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SiaDelegate;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;


/***
 * JOB SIA. 
 *
 */
public class SiaPendienteJob implements Job  {

	/** El log. **/
	protected static Log log = LogFactory.getLog(SiaPendienteJob.class);

	/***
	 * Metodo para ejcutar el job.
	 */
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
    	SchedulerContext schedulerContext;
    	SiaJob siaJob = null;
    	
    	try {
			schedulerContext = context.getScheduler().getContext();
		} catch (SchedulerException e) {
			log.debug("Error extrayendo el contexto en el job.", e);
			return;
		}
    	final String tipoEnvio = (String) schedulerContext.get("tipoEnvio");
        
    	log.debug("Ejecutando indexacion con tipo indexacion:" + tipoEnvio);
    	
    	SiaDelegate siaDelegate = DelegateUtil.getSiaDelegate();
    	SiaPendienteProcesoDelegate siaProcesoDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
    	
    	try {
    		siaJob=siaProcesoDelegate.crearSiaJob();
    		//EJECUTAR EL ENVIO A SIA.
	    	
	    	if ("todo".equals(tipoEnvio)) {
					siaDelegate.enviarTodos();
	    	} else {
	    		
	    		siaDelegate.enviarPendientes();
	    	}
	    	
	    	siaProcesoDelegate.cerrarSiaJob(siaJob);
	    	
    	} catch (DelegateException e) {
    		log.error("Error ejecucion envio con tipo envio:" + tipoEnvio);
    	}
    	
    	log.debug("Fin ejecucion indexacion con tipo indexacion:" + tipoEnvio);
    }
       
    
}

