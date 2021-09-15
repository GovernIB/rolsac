package es.caib.rolsac.back2.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MensajeDelegate;

/**
 * Componente que se arranca para el job a cierta hora del día.
 *
 * @author Indra
 *
 */
public class TaskEmail {

	/** El log. **/
	protected static Log log = LogFactory.getLog(TaskEmail.class);

	public void ejecutarEnvioEmail() {
		log.debug("Job que ejecuta para el envio de emails: inicio");
		try {
			final MensajeDelegate mensDelegate = DelegateUtil.getMensajeDelegate();
			mensDelegate.enviarEmailsPendientes();
		} catch (final Exception e) {
			log.error("Error enviando emails", e);
		}
		log.debug("Job que ejecuta para el envio de emails: fin");
	}

	public void ejecutarLimpiezaEmails() {
		log.debug("Job que ejecuta para la limpieza de emails: inicio");
		try {
			final MensajeDelegate mensDelegate = DelegateUtil.getMensajeDelegate();
			mensDelegate.limpiarEmails();
		} catch (final Exception e) {
			log.error("Error indexando pendientes", e);
		}
		log.debug("Job que ejecuta para la limpieza de emails: fin");
	}
}
