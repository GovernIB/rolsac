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

		if(!traductorHabilitat()) {
			log.debug("Carregant Rolsac sense traducció automàtica");
			return;
		}
		try {

			Traductor traductor = crearTraductor();
			
			establecerServidorTraduccion(traductor);

			//El objeto de traductor se guarda como atributo de contexto que podrá ser utilizado en
			//ámbito de aplicación
			event.getServletContext().setAttribute("traductor", traductor);

			log.debug("Carregant Rolsac amb traducció automàtica");
			log.debug("URL de servidor de traducció: " + traductor.getTranslationServerUrl());

		} catch (Exception e) {
			log.debug("Carregant Rolsac sense traducció automàtica");
		}

	}

	protected Traductor crearTraductor() throws Exception {
		return new Traductor();
	}

	private boolean traductorHabilitat() {
		return flagTraductorHabilitat();
	}


	private boolean flagTraductorHabilitat() {
		if(propietatNoExisteix(FLAG_TRADUCTOR))
			return false;
		return estatFlagTraductor();
	}

	private boolean estatFlagTraductor() {
		return llegirPropietat(FLAG_TRADUCTOR).equals("S");
	}
	
	private void establecerServidorTraduccion(Traductor traductor) {
		if(propietatNoExisteix(SERVIDOR_TRADUCTOR)) 
			return;
		traductor.setTranslationServerUrl(llegirPropietat(SERVIDOR_TRADUCTOR));
	}

	
	
	//TODO extraure metode en utils 
	private boolean propietatNoExisteix(String name) {
		String value = llegirPropietat(name);
		return null==value;
	}
	
	//TODO extraure metode en utils
	private String llegirPropietat(String name) {
		return System.getProperty(name);
	}
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		// no es necesario implementar código
	}	

	
	static final String SERVIDOR_TRADUCTOR = "es.caib.rolsac.integracion.traductor.servidor";
	static final String FLAG_TRADUCTOR = "es.caib.rolsac.integracion.traductor";
	
}
