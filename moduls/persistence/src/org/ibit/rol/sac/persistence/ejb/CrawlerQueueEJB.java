package org.ibit.rol.sac.persistence.ejb;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.crawler.Crawler;
import org.ibit.rol.sac.persistence.crawler.OperacionCrawler;

/**
*
* EJB encargado de hacer el crawling de las fichas.
*
* @ejb.bean
*  name="sac/persistence/CrawlerQueueFacade"
*  destination-type="javax.jms.Queue"
*  view-type="remote"
*  transaction-type="Container"
*  destination-jndi-name="org.ibit.rol.sac.persistence.CrawlerQueue"
*  acknowledge-mode="Auto-acknowledge"
*  subscription-durability="NonDurable"
*  connection-factory-jndi-name="java:/ConnectionFactory"
* @ejb.transaction type="NotSupported"
*
* @jboss.container-configuration name="Singleton Message Driven Bean"
* @jboss.destination-jndi-name name="queue/CrawlerQueue"
*
*
*/
public class CrawlerQueueEJB implements MessageDrivenBean, MessageListener {
     MessageDrivenContext messageDrivenContext;
   private static Log log = LogFactory.getLog(CrawlerQueueEJB.class);
   private static String path;

   public void setMessageDrivenContext(MessageDrivenContext ctx) throws EJBException {
       this.messageDrivenContext = ctx;
   }

   public void ejbCreate() throws EJBException {          
	   try{
		   path=System.getProperty("es.caib.rolsac.crawler.indexLocation");
       }catch(Exception ex){
           log.error("Excepcion obteniendo el path :"+ex );
       }
         }
     public void ejbRemove() throws EJBException {      }

   public void onMessage(Message message) {
	   try {
		   if(message instanceof ObjectMessage)
		   {
			   ObjectMessage objectMessage = (ObjectMessage) message;
			   OperacionCrawler operacionCrawler =(OperacionCrawler) objectMessage.getObject();
			   
			   //ejecutamos el crawler
			   if(operacionCrawler.getTipo().equals("I")&& operacionCrawler.getIdObjeto()!=null){
				   
				   log.debug("Entro en la cola con Ficha: "+operacionCrawler.getIdObjeto());
				   Crawler crawler=new Crawler(path,operacionCrawler.getIdObjeto(),operacionCrawler.getUrls());
				   crawler.desindexarURLFicha();
				   crawler.indexarURLFicha();
				   log.debug("Saliendo de la cola con Ficha: "+operacionCrawler.getIdObjeto()); 
			   }
			   //Optimizamos el indice
			   else if(operacionCrawler.getTipo().equals("O")){
				   log.debug("Entro en la cola para optimizar el indice del crawler");
				   Crawler crawler = new Crawler(path);
				   crawler.optimizarIndiceCrawler();
			   }

		   }

	} catch (Exception e) {
		log.error("Excepcion obteniendo el objecto ficha en la cola :"+e.getMessage());
		//throw new EJBException(e);
	}

	   
   }



}
   

   
