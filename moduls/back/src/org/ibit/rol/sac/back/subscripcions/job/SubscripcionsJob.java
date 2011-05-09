package org.ibit.rol.sac.back.subscripcions.job;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EnvioSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.SuscriptorDelegate;



public class SubscripcionsJob extends JobAutomatico{

	private Log log = LogFactory.getLog( SubscripcionsJob.class  );
	
	public void doProceso() throws Exception {
		Set tos = new HashSet();
		try{
			
			String value = System.getProperty("es.caib.webcaib.principal");
			if ((value != null) && value.equals("S")) {
				EnvioSuscripcionDelegate delegate = DelegateUtil.getEnvioSuscripcionDelegate();
				SuscriptorDelegate suscriptorDelegate = DelegateUtil.getSuscriptorDelegate();
				List envios = delegate.listarPendientes();
				log.debug("Numero de envios pendientes: " + envios.size());
				for(Iterator it=envios.iterator(); it.hasNext();)
				{
					EnvioSuscripcion envio = (EnvioSuscripcion) it.next();
					Set grupos = envio.getGrupos();
					for(Iterator itG = grupos.iterator(); itG.hasNext();)
					{
						GrupoSuscripcion grupo = (GrupoSuscripcion) itG.next();
						List suscriptores = suscriptorDelegate.listarPorEstadoTipo(envio.getTipo(),Suscriptor.TIPO_ACTIVO,grupo.getId());
						addSuscriptores(tos,suscriptores);
					}
					// TODO De momento enviamos solo correos. En futuras versiones
					// podremos enviar tambien SMS
					// De momento todas las subscripciones son del tipo Boletin de Noticias
					enviar(tos,envio.getAsunto(),envio.getContenidoHtml(), envio.getTipoSuscripcion());
					delegate.actualizaComoEnviado(envio.getId());
				}
			} else {
				log.info("El jboss en donde está la aplicación no es el principal.");
			}
		}catch (Exception ex){
			log.error("Error al realizar el enviar Correos: " + ex.getMessage(),ex);			
		}		
		
	}
	
	
 
	
}
