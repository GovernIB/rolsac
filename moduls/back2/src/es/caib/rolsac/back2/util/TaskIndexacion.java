package es.caib.rolsac.back2.util;
		
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Componente que se arranca para el job a cierta hora del día.
 * @author slromero
 *
 */
@Component
public class TaskIndexacion {

	/** El log. **/
	protected static Log log = LogFactory.getLog(TaskIndexacion.class);

	@Scheduled(cron ="0 0 2 * * *")
	//@Scheduled(cron ="#{systemProperties['es.caib.rolsac.solr.jobpendientes']}")
	//@Scheduled(cron ="#{es.caib.rolsac.solr.jobpendientes}")
	public void ejecutarJobInternal() {
		
		log.error("Job noctura que se ejecuta para la Indexacion pendiente.");	
		SolrPendienteDelegate solrDelegate = DelegateUtil.getSolrPendienteDelegate();
    	try { 
    		solrDelegate.indexarPendientes();
    	} catch (DelegateException e) { log.error("Error indexando pendientes", e); }
    	log.error("jobIndexacionNocturna..");
	}
}
