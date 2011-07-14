package org.ibit.rol.sac.back.crawler.job;


import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJBException;
import javax.mail.MessagingException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.ProcedimientoRemoto;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IndexerDelegate;
import org.ibit.rol.sac.persistence.util.EmailUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CrawlerJob implements Job{

	private Log log = LogFactory.getLog( CrawlerJob.class  );
	private static final String BUNDLE_PATH = "email";

	public CrawlerJob(){

	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try{
			
				FichaDelegate ficdel = DelegateUtil.getFichaDelegate();
				IndexerDelegate indexer = DelegateUtil.getIndexerDelegate();
				log.debug("Reindexando indece Lucene");
				//Reindexamos procedimientos
				indexer.reindexarProcedimentos();
				//Reindexamos UA
				indexer.reindexarUOsPMA();
				//Reindexamos fichas
				indexer.reindexarFichasPMA();
				log.debug("Reindexando indece Lucene Crawler");
				//Listamos fichas y lanzamos el crawler por cada una
				List<Ficha> listaFichas = ficdel.listarFichasCrawler();
				for (Ficha ficha : listaFichas) {
					if (ficha instanceof FichaRemota) {
		        		try {
		        			indexer.envioColaCrawler("I",ficha);
					} catch (Exception e) {
						 throw new EJBException(e);
						}
		    		}
				}
				//Optimizamos el indice crawler
				indexer.envioColaCrawler("O",null);

		}catch (Exception ex){
			log.error("Error al realizar la tarea de reindexación de las fichas Remotas: " + ex.getMessage(),ex);			
			//Envio de mail
			envioMailError(ex.getMessage());
		}	
		

		
	}
	public void envioMailError(String error){
		//Leo el archivo con las propiedades del mail
		ResourceBundle resource = ResourceBundle.getBundle(BUNDLE_PATH);
		
		//Recojo los parametros necesarios del archivo d propiedades
		String jndi = resource.getString("jndi");
		String asunto = "S'ha produït un error quan es reindexaven els indexos de Lucene i del crawler del portal de l'informador";
		String destinatario = System.getProperty("es.caib.rolsac.crawler.responsable.errorReIndexacion");
		if(destinatario!=null && destinatario.length()>0){
			try {
				//Genero el EmailUtils con sus parametros necesarios
				EmailUtils emailUtils = new EmailUtils(jndi);
				//Envio el Email
				emailUtils.postMail(asunto, generarMensajeError(error),"", destinatario);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

	}
	
	private String generarMensajeError(String error) {
		StringBuffer mensaje = new StringBuffer();
			mensaje.append("\nDescripció de l'error: \n");
			mensaje.append("\n "+error);
		return mensaje.toString();
	}


	
 
	
}
