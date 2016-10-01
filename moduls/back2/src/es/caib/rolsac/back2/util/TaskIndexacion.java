package es.caib.rolsac.back2.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteProcesoDelegate;

/**
 * Componente que se arranca para el job a cierta hora del día.
 * 
 * @author Indra
 *
 */
public class TaskIndexacion {

	/** El log. **/
	protected static Log log = LogFactory.getLog(TaskIndexacion.class);

	public void ejecutarJobInternal() {
		log.debug("Job que ejecuta para la indexacion pendiente: inicio");
		try {
			SolrPendienteProcesoDelegate solrDelegate = DelegateUtil
					.getSolrPendienteProcesoDelegate();
			solrDelegate.indexarPendientes();
		} catch (Exception e) {
			log.error("Error indexando pendientes", e);
		}
		log.debug("Job que ejecuta para la indexacion pendiente: fin");
	}
}
