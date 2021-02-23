/**
 *
 */
package es.indra.rol.sac.integracion.listener.traductor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

/**
 * Clase que inicializa el traductor y lo guarda como atributo de contexto a
 * partir de una propiedad de sistema
 *
 * @author Indra
 *
 */
public class TraductorInitializer implements ServletContextListener {

	protected static Log log = LogFactory.getLog(TraductorInitializer.class);

	/**
	 * Metodo que inicializa y guarda el traductor en contexto dependiendo de la
	 * propiedad de sistema "es.caib.rolsac.integracion.traductor" (valores S o N).
	 * En el caso de que no exista la propiedad de sistema el traductor no se
	 * inicializa
	 *
	 * @param event
	 *            evento de contexto
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(final ServletContextEvent event) {

		if (!traductorHabilitat()) {
			log.debug("Carregant Rolsac sense traduccio automatica");
			return;
		}
		try {
			log.debug("Iniciando contextInitialized");
			final Traductor traductor = crearTraductor();

			// El objeto de traductor se guarda como atributo de contexto que podr� ser
			// utilizado en
			// ambito de aplicacion
			log.debug("Iniciando contextInitialized P2");
			event.getServletContext().setAttribute("traductor", traductor);

			log.debug("Carregant Rolsac amb traduccio automatica");
			log.debug("URL de servidor de traduccio: " + System.getProperty("es.caib.rolsac.translatorib.url"));

		} catch (final Exception e) {
			log.error("Error iniciando la traduccion automatica");
			log.error("Carregant Rolsac sense traduccio automatica", e);
		}

	}

	protected Traductor crearTraductor() throws Exception {
		return new Traductor();
	}

	private boolean traductorHabilitat() {
		return flagTraductorHabilitat();
	}

	private boolean flagTraductorHabilitat() {
		if (propietatNoExisteix(FLAG_TRADUCTOR))
			return false;
		return estatFlagTraductor();
	}

	private boolean estatFlagTraductor() {
		return llegirPropietat(FLAG_TRADUCTOR).equals("S");
	}

	// TODO extraure metode en utils
	private boolean propietatNoExisteix(final String name) {
		final String value = llegirPropietat(name);
		return null == value;
	}

	// TODO extraure metode en utils
	private String llegirPropietat(final String name) {
		return System.getProperty(name);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(final ServletContextEvent event) {
		// no es necesario implementar codigo
	}

	static final String SERVIDOR_TRADUCTOR = "es.caib.rolsac.integracion.traductor.servidor";
	static final String FLAG_TRADUCTOR = "es.caib.rolsac.integracion.traductor";

}
