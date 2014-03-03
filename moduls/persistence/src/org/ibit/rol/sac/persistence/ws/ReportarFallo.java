package org.ibit.rol.sac.persistence.ws;

import java.util.ResourceBundle;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.persistence.util.EmailUtils;

/**
 * Se encarga de gererar un informe con los datos de el objeto y de la excepcion
 * para a continuación mandarlos por email al destinatario.
 * 
 * Las propiedades pueden ser configuradas antes de compilar en el buil.properties
 * del proyecto. O una vez compilado, deltro del jar de persistence en el archivo
 * email.properties
 * 
 * @author arodrigo
 *
 */
public class ReportarFallo {
	
	protected static Log log = LogFactory.getLog(Actualizador.class);

	//Indica la ruta de ResourceBundle con los datos de los Emails
	private static final String BUNDLE_PATH = "email";
	
	/**
	 * Lanza un proceso que se encargara de mandar el Email con el
	 * informe del Fallo
	 * 
	 * @param object Objeto el el que el proceso fallo
	 * @param borrar Indica si el proceso era de borrado o no
	 * @param destinatario {@link Destinatario} al que enviar el mail
	 * @param exception {@link Exception} ocurrida al intentar la actualizacion
	 */
	public static void reportar(Object object, boolean borrar,
			Destinatario destinatario, Exception exception) {
		ReportadorThread reportador = new ReportarFallo().new ReportadorThread(
				object, borrar, destinatario, exception);
		reportador.run();
	}
	
	/**
	 * proceso que se encargara de mandar el Email con el
	 * informe del Fallo
	 * 
	 * @author arodrigo
	 *
	 */
	private class ReportadorThread extends Thread {

		private final Exception exception;
		private final Destinatario destinatario;
		private final Object object;
		private final boolean borrar;
		
		/**
		 * Constructor con los parametros necesarios para que el proceso mande
		 * el mail con el informe del Fallo
		 * 
		 * @param object Objeto el el que el proceso fallo
		 * @param borrar Indica si el proceso era de borrado o no
		 * @param destinatario {@link Destinatario} al que enviar el mail
		 * @param exception {@link Exception} ocurrida al intentar la actualizacion
		 */
		public ReportadorThread(Object object, boolean borrar,
				Destinatario destinatario, Exception exception) {
			this.object = object;
			this.borrar = borrar;
			this.exception = exception;
			this.destinatario = destinatario;
		}

		@Override
		public void run() {
			//Leo el archivo con las propiedades del mail
			ResourceBundle resource = ResourceBundle.getBundle(BUNDLE_PATH);
			
			//Recojo los parametros necesarios del archivo d epropiedades
			String jndi = resource.getString("jndi");
			String administracion = resource.getString("administracion");
			String email = resource.getString("email");
			
			//Construyo un Asunto
			String asunto = "Se ha producido un ERROR en " + administracion;
			
			try {
			
				String mensaje=generarMensaje(administracion,destinatario.getNombre());
				log.debug("asunto:"+asunto);
				log.debug("mensaje:"+mensaje);
				log.debug("de:"+email);
				log.debug("para:"+destinatario.getEmail());

				//Genero el EmailUtils con sus parametros necesarios
				EmailUtils emailUtils = new EmailUtils(jndi);
			
				//Envio el Email
				emailUtils.postMail(
						asunto, 
						mensaje,
						email, 
						destinatario.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Construye el cuerpo del infrome de Fallo
		 * 
		 * @param administracion : Nombre de la administracion el la que 
		 * ha ocurrido el error
		 * @return El contenido del mail
		 */
		private String generarMensaje(String administracion, String destino) {
			StringBuffer mensaje = new StringBuffer();
			mensaje.append("Se ha producido un Error en el destinatario " +destino+" al ");
			if (borrar) {
				mensaje.append("borrar \n");
			} else {
				mensaje.append("actualizar \n");
			}

			mensaje.append(object.toString());

			mensaje.append("\n\nEn la Administracion Remota -> ");
			mensaje.append(administracion);
			mensaje.append("\n\n A continuación se mostrara la informaci�n de la Excepción:\n\n");
			mensaje.append(exception.toString());

			return mensaje.toString();
		}

	}

}
