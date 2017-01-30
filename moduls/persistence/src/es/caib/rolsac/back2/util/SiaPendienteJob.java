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
        
    	log.debug("Ejecutando enviar SIA con tipo envio:" + tipoEnvio);
    	
    	SiaDelegate siaDelegate = DelegateUtil.getSiaDelegate();
    	SiaPendienteProcesoDelegate siaProcesoDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
    	
    	try {
    		
    		//EJECUTAR EL ENVIO A SIA.
	    	if ("todo".equals(tipoEnvio)) {
	    		siaJob=siaProcesoDelegate.crearSiaJob("TOT");
	    		siaDelegate.enviarTodos(siaJob);
	    	} else if ("info".equals(tipoEnvio)){
	    		siaJob=siaProcesoDelegate.crearSiaJob("INF");
	    		siaDelegate.info(siaJob);
	    	} else if ("tiempo".equals(tipoEnvio)) {
	    		siaJob=siaProcesoDelegate.crearSiaJob("TMP");
	    		siaDelegate.revisarProcedimientosPorTiempo(siaJob);
	    	} else {
	    		siaJob=siaProcesoDelegate.crearSiaJob("PDT");
	    		siaDelegate.enviarPendientes(siaJob);
	    	}
	    	
	    	siaProcesoDelegate.cerrarSiaJob(siaJob.getId());
	    	
    	} catch (DelegateException e) {
    		log.error("Error ejecucion envio con tipo envio:" + tipoEnvio);
    	}
    	
    	log.debug("Fin ejecucion indexacion con tipo indexacion:" + tipoEnvio);
    }
       
    
}

