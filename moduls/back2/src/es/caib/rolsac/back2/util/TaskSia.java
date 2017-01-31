package es.caib.rolsac.back2.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SiaDelegate;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;

/**
 * Componente que se arranca para el job a cierta hora del día.
 * 
 * @author Indra
 *
 */
public class TaskSia {

	/** El log. **/
	protected static Log log = LogFactory.getLog(TaskSia.class);

	public void ejecutarJobInternal() {
		log.debug("Job que ejecuta para la enviar SIA pendiente: inicio");
		try {
			 SiaDelegate siaDelegate = DelegateUtil.getSiaDelegate();
			 SiaPendienteProcesoDelegate siaProcesoDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
			 
			 //Envia los pendientes
			 SiaJob siaJob = siaProcesoDelegate.crearSiaJob("PDT");
			 siaDelegate.enviarPendientes(siaJob);
			 siaProcesoDelegate.cerrarSiaJob(siaJob.getId());
			 
			 SiaJob siaJob2 = siaProcesoDelegate.crearSiaJob("TMP");
			 siaDelegate.revisarProcedimientosPorTiempo(siaJob2);
			 siaProcesoDelegate.cerrarSiaJob(siaJob2.getId());
			 
		} catch (Exception e) {
			log.error("Error enviando pendientes SIA", e);
		}
		log.debug("Job que ejecuta para el envio pendiente: fin");
	}
}
