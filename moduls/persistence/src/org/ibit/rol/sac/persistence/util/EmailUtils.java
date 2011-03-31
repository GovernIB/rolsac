package org.ibit.rol.sac.persistence.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Envia emails con o sin seguridad
 * 
 * @author arodrigo
 *
 */
public class EmailUtils {
	
	private final Session session;
	protected final Log log = LogFactory.getLog(EmailUtils.class);
	/**
	 * Constructor que recoge la {@link Session} a apartir ne un nombre JNDI
	 * 
	 * @param jndi
	 * @throws MessagingException 
	 */
	public EmailUtils(String jndi) throws MessagingException {
		try {
			final Context context = new InitialContext();
			session = (Session)context.lookup(jndi);
		} catch (NamingException e) {
			throw new MessagingException("Error al recoger la Session del Mail",e);
		}
	}
	
	/**
	 * Constructor que genera la {@link Session} con los parametros de entrada.
	 * 
	 * @param servidorSMTP
	 * @param usuario
	 * @param password
	 */
	public EmailUtils(String servidorSMTP, String usuario, String password) {
		//Indicamos en servidor SMTP
		Properties props = new Properties();
		props.put("mail.smtp.host", servidorSMTP);
		if(usuario ==null || password==null || "".equals(usuario.trim()) || "".equals(password.trim())){
			session = Session.getDefaultInstance(props, null);
		}else {
			Authenticator auth = new SMTPAuthenticator(usuario,password);
			session = Session.getDefaultInstance(props, auth);
		}
		session.setDebug(false);
	}
	
	/**
	 * Envia un email
	 * 
	 * @param asunto 
	 * @param mensaje
	 * @param de
	 * @param para
	 * @throws MessagingException
	 */
	public void postMail(String asunto, String mensaje , String de, String... para) throws MessagingException{
		// Creamos el mensaje
		Message msg = new MimeMessage(session);
		
		// Indicamos la procedencia del mail
		if(de == null || de.trim().length()<=0)
			de = (String)session.getProperties().get("mail.from");
		InternetAddress addressFrom = new InternetAddress(de);
		msg.setFrom(addressFrom);
		
		// Indicamos los destinatarios
		InternetAddress[] addressTo = new InternetAddress[para.length]; 
		for (int i = 0; i < para.length; i++)
		{
			addressTo[i] = new InternetAddress(para[i]);
			//addressTo[i] = new InternetAddress(para[i].toString());
		}
		
		msg.setRecipients(Message.RecipientType.TO, addressTo);
		
		// Configuramos el asunto
		msg.setSubject(asunto);

		// Configuramos el contenido
		msg.setContent(mensaje, "text/plain");
		
		//Mandamos el mail
		Transport.send(msg);

	}
	
	private class SMTPAuthenticator extends Authenticator
	{
		private final String usuario;
		private final String password;
		
		public SMTPAuthenticator(String usuario, String password){
			this.usuario = usuario;
			this.password = password;
		}
		
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        return new PasswordAuthentication(usuario, password);
	    }
	}
}
