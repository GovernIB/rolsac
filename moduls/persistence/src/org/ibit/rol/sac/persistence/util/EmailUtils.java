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

	public void sendMail(final String to, final String subject, final String content, final String user,
			final String host, final String port, final String pass) throws MessagingException {
		final Properties props = new Properties();
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		final boolean debugConf = true;
		final boolean authConf = true;
		final boolean trustConf = true;
		props.put("mail.smtp.debug", debugConf);
		props.put("mail.smtp.auth", authConf);
		props.put("mail.smtp.ssl.trust", trustConf);
		final SMTPAuthenticator auth = new SMTPAuthenticator(user, pass);
		final Session session = Session.getInstance(props, auth);
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(content, "text/html");
		// Send message
		Transport.send(message);

	}

	/**
	 * Constructor que recoge la {@link Session} a apartir ne un nombre JNDI
	 *
	 * @param jndi
	 * @throws MessagingException
	 */
	public EmailUtils(final String jndi) throws MessagingException {
		try {
			final Context context = new InitialContext();
			session = (Session) context.lookupLink(jndi);
		} catch (final NamingException e) {
			throw new MessagingException("Error al recoger la Session del Mail", e);
		}
	}

	/**
	 * Constructor que genera la {@link Session} con los parametros de entrada.
	 *
	 * @param servidorSMTP
	 * @param usuario
	 * @param password
	 */
	public EmailUtils(final String servidorSMTP, final String servidorSMTPPort, final String usuario,
			final String password) {
		// Indicamos en servidor SMTP
		final Properties props = new Properties();
		// props.put("mail.smtp.ssl.enable", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", servidorSMTPPort);

		props.put("mail.pop3s.ssl.trust", servidorSMTPPort); // add this line
		props.put("mail.pop3.host", servidorSMTPPort);
		props.put("mail.pop3.port", servidorSMTPPort);
		props.put("mail.pop3.starttls.enable", "true");
		if (usuario == null || password == null || "".equals(usuario.trim()) || "".equals(password.trim())) {
			session = Session.getDefaultInstance(props, null);
		} else {
			// final Authenticator auth = new SMTPAuthenticator(usuario, password);
			final Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(usuario, password);
				}
			};
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
	public void postMail(final String asunto, final String mensaje, String de, final String... para)
			throws MessagingException {
		// Creamos el mensaje
		final Message msg = new MimeMessage(session);

		// Indicamos la procedencia del mail
		if (de == null || de.trim().length() <= 0)
			de = (String) session.getProperties().get("mail.from");
		final InternetAddress addressFrom = new InternetAddress(de);
		msg.setFrom(addressFrom);

		// Indicamos los destinatarios
		final InternetAddress[] addressTo = new InternetAddress[para.length];
		for (int i = 0; i < para.length; i++) {
			addressTo[i] = new InternetAddress(para[i]);
			// addressTo[i] = new InternetAddress(para[i].toString());
		}

		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Configuramos el asunto
		msg.setSubject(asunto);

		// Configuramos el contenido
		final String contenid = "text/plain; charset=UTF-8"; // text/html
		msg.setContent(mensaje, contenid);

		// Mandamos el mail
		Transport.send(msg);

	}

	private class SMTPAuthenticator extends Authenticator {
		private final String usuario;
		private final String password;

		public SMTPAuthenticator(final String usuario, final String password) {
			this.usuario = usuario;
			this.password = password;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(usuario, password);
		}
	}
}
