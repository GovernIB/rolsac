/**
 * 
 */
package es.indra.rol.sac.integracion.listener.traductor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.indra.rol.sac.integracion.traductor.Traductor;

/**
 * Clase que inicializa el traductor y lo guarda como atributo de
 * contexto a partir de una propiedad de sistema 
 * @author Indra
 *
 */
public class TraductorInitializer implements ServletContextListener {

	protected static Log log = LogFactory.getLog(TraductorInitializer.class);

	/**
	 * Método que inicializa y guarda el traductor en contexto dependiendo de la
	 * propiedad de sistema "es.caib.rolsac.integracion.traductor" (valores S ó N).
	 * En el caso de que no exista la propiedad de sistema el traductor no se inicializa
	 * 
	 * @param event	evento de contexto
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {

		if(!traductorHabilitat()) return;
		try {

			Traductor traductor = new Traductor();

			if (System.getProperty("es.caib.rolsac.integracion.traductor.servidor") != null) 
				traductor.setTranslationServerUrl(System.getProperty("es.caib.rolsac.integracion.traductor.servidor"));

			//El objeto de traductor se guarda como atributo de contexto que podrá ser utilizado en
			//ámbito de aplicación
			event.getServletContext().setAttribute("traductor", traductor);

			log.info("Carregant Rolsac amb traducció automàtica");
			log.info("URL de servidor de traducció: " + traductor.getTranslationServerUrl());

		} catch (Exception e) {
			log.info("No s'ha trobat paràmetre d' inicialització de traducció automàtica");
			log.info("Carregant Rolsac sense traducció automàtica");
		}

	}
	
	private boolean traductorHabilitat() {
		return System.getProperty("es.caib.rolsac.integracion.traductor").equals("S");
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		// no es necesario implementar código
	}	

}
