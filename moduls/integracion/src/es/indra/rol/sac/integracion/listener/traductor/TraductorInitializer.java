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
	 * M�todo que inicializa y guarda el traductor en contexto dependiendo de la
	 * propiedad de sistema "es.caib.rolsac.integracion.traductor" (valores S � N).
	 * En el caso de que no exista la propiedad de sistema el traductor no se inicializa
	 * 
	 * @param event	evento de contexto
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {

		if(!traductorHabilitat()) {
			log.debug("Carregant Rolsac sense traducci� autom�tica");
			return;
		}
		try {

			Traductor traductor = crearTraductor();
			
			establecerServidorTraduccion(traductor);

			//El objeto de traductor se guarda como atributo de contexto que podr� ser utilizado en
			//�mbito de aplicaci�n
			event.getServletContext().setAttribute("traductor", traductor);

			log.debug("Carregant Rolsac amb traducci� autom�tica");
			log.debug("URL de servidor de traducci�: " + traductor.getTranslationServerUrl());

		} catch (Exception e) {
			log.debug("Carregant Rolsac sense traducci� autom�tica");
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
		// no es necesario implementar c�digo
	}	

	
	static final String SERVIDOR_TRADUCTOR = "es.caib.rolsac.integracion.traductor.servidor";
	static final String FLAG_TRADUCTOR = "es.caib.rolsac.integracion.traductor";
	
}
