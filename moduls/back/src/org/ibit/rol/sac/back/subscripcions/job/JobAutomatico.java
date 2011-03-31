package org.ibit.rol.sac.back.subscripcions.job;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.back.subscripcions.utils.ClientHttpRequest;
import org.ibit.rol.sac.back.subscripcions.utils.StringUtil;
import org.ibit.rol.sac.back.subscripcions.utils.UsernamePasswordCallbackHandler;
import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.TipoSuscripcionDelegate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;




/**
 * Job que realiza el proceso diario de importacion
 *
 */
public abstract class JobAutomatico implements Job
{
	
	private Log log = LogFactory.getLog( JobAutomatico.class  );
	
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		LoginContext lc = null;
				
		try{
			
	        /*
			Properties props = new Properties();
	        String ls_file = "JobAutomatico.properties";
	        
	        props.load(this.getClass().getResourceAsStream(ls_file));  
	        
	        String user=props.getProperty("user");
	        String pass=props.getProperty("pass");
			
			CallbackHandler handler = new UsernamePasswordCallbackHandler( user, pass ); 					
			lc = new LoginContext("client-login", handler);
			lc.login();
			*/						
			
			
			// Realizamos proceso
			doProceso();
			 
				
									
		}catch (Exception ex){
			// Informamos al log			
			log.error("Error al realizar proceso automatico: " + ex.getMessage(),ex);			
		}finally{
			if ( lc != null ){
				try{lc.logout();}catch(Exception exl){}
			}
		}
		
		
		
		
		
	}
	
	protected boolean esHoraDeEnviar(TipoSuscripcion ts) throws ParseException
	{
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
		Date now = new Date();
		String hmNow = sdfHora.format(now);
		Date nowhm = sdfHora.parse(hmNow);
		Date horaComienzoEnvio = ts.getHoraComienzoEnvio();
		if(horaComienzoEnvio == null) return true;
		String hmComienzoStr = sdfHora.format(ts.getHoraComienzoEnvio());
		Date comienzohm = sdfHora.parse(hmComienzoStr);
		if(nowhm.before(comienzohm))
		{
			log.debug("Son las " +  hmNow + " y no se envia hasta las " + hmComienzoStr);
			return false;
		}
		return true;

	}
	
    protected String getContenido(String path) throws MalformedURLException, IOException
    {
    	ClientHttpRequest client = new ClientHttpRequest(new URL(path));
    	InputStream is = client.post();
    	return StringUtil.copyInputStream(is);
    }
	
	protected void addSuscriptores(Set tos, List suscriptores)
	{
    	for(Iterator it = suscriptores.iterator(); it.hasNext();)
    	{
    		Suscriptor sus = (Suscriptor) it.next();
    		tos.add(sus.getEmail());
    	}
	}


    protected boolean enviar(Set suscriptores,String titulo,String mensaje, TipoSuscripcion ts)  throws Exception{
    	try {    		
/*    		
    		InitialContext jndiContext = new InitialContext();
    		Session mailSession = (Session)jndiContext.lookup("java:/es.caib.subscripcions.mail");
    		Properties props = new Properties();
    		props = mailSession.getProperties();
    		
    		// Cambiar/ajustar... cuando tengamos varias suscripciones de este tipo¿?
    		
    		Session mailSession2 = Session.getDefaultInstance(props, null);
    		javax.mail.Message msg = new MimeMessage(mailSession2);
    		
    		msg.setRecipients(javax.mail.Message.RecipientType.BCC, getDirecciones(suscriptores));
    		msg.setSubject(titulo);
    		
    		msg.setFrom(new InternetAddress(ts.getEmail()));
    		
    		msg.setContent(mensaje, "text/html");
    		msg.setHeader("X-Mailer", "JavaMailer");
    		msg.setSentDate(new java.util.Date());
    		Transport transporte = mailSession.getTransport("smtp");
    		transporte.connect(mailSession.getProperty("mail.smtp.host"), ts.getEmail(), ts.getPassword());
    		transporte.sendMessage(msg,msg.getAllRecipients());
    		return true;  
  
    		*/
    		InitialContext jndiContext = new InitialContext();
    		//Session mailSession = (Session)jndiContext.lookup("java:/es.caib.sac.Mail");
    		Session mailSession = (Session)jndiContext.lookup("java:/es.caib.subscripcions.mail");
    		
    		// Cambiar/ajustar... cuando tengamos varias suscripciones de este tipo¿?
    		MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(ts.getEmail()));

    		msg.setRecipients(javax.mail.Message.RecipientType.BCC, getDirecciones(suscriptores));
    		msg.setSubject(titulo);
    		msg.setContent(mensaje, "text/html");
    		msg.setHeader("X-Mailer", "JavaMailer");
    		msg.setSentDate(new java.util.Date());
    		try {
                Transport.send(msg);
            } catch (Exception ex) { 
        		log.error("Excepción enviando correo a gestor con lista de mails, y tipo " + ts.getIdentificador(),ex);
                return false; 
            }

    		return true;

    	}catch (Exception ex){
    		log.error("Excepción enviando correo a gestor con lista de mails, y tipo " + ts.getId(),ex);
    		throw ex;
    	}
    }    
    
    private InternetAddress[] getDirecciones(Set suscriptores) throws AddressException
    {
    	InternetAddress[] direcciones = new InternetAddress[suscriptores.size()];
    	int i = 0;
    	for(Iterator it = suscriptores.iterator(); it.hasNext();i++)
    	{
    		String sus = (String) it.next();
    		direcciones[i] = new InternetAddress(sus);
    	}
    	return direcciones;
    }
	
    protected String obtenerServidor(){
    	String servidor = "";
    	String value = System.getProperty("es.indra.caib.rolsac.oficina");
        if ((value == null) || value.equals("N"))
        	servidor = "http://"+ Suscripcionback.RESOURCE_HOST;
        else
        {
        	value = System.getProperty("es.indra.caib.rolsac.servidor");
            if ((value == null) || value.equals(""))
            {
            	servidor = "http://localhost:8080";
            }
            else
            {
            	servidor = "http://" + value;
            }
        }
        return servidor;

    }

	public abstract void doProceso() throws Exception;
	
	
}	

