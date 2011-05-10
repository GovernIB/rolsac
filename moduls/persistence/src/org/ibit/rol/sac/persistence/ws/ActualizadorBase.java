package org.ibit.rol.sac.persistence.ws;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public abstract class ActualizadorBase {
	protected static Log log = LogFactory.getLog(ActualizadorBase.class);


	void actualizar() {
		
		//if(0==destinatarios.size())  return;
		
		//La transformo en transferible
		ActuacionTransferible actuacionTransferible = generarActuacionTransferible();
		

		//Y voy destinatario a destinatario mandando la actualizacion
		for (final Destinatario destinatario : destinatarios) {
			try{
	        	
		        if (calActualizarElDestinatari()) {
		        	//SOLO DE PRUEBAS // QUITAR!!!!
		        		log.info("actualizando el destinatario: "+destinatario.getNombre());
						final ActualizacionServicio actualizacionSvc = ActualizacionServicio.createActualizacionServicio(
								destinatario.getEndpoint(), destinatario.getIdRemoto());
						
						actualizarActuacion(actualizacionSvc , actuacionTransferible);
						log.info("actualizacion enviada OK");
		        	}
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(getActuacion(), false, destinatario, e);
				log.error("error actualizando -> "+ e.getMessage());			}
		}
	}
	
	abstract ActuacionTransferible generarActuacionTransferible() ;
	
	abstract void actualizarActuacion(ActualizacionServicio actualizacionSvc, ActuacionTransferible elemTransf) 
		throws WSInvocatorException;
	
	abstract Object getActuacion();
	
	abstract void borrar();

	public boolean calActualizarElDestinatari() {
		if (estemOficinaIndra()) return false;
		return true;
	}
	
	boolean estemOficinaIndra() {
		String value = System.getProperty("es.indra.caib.rolsac.oficina");
		return (value != null) && value.equals("S");
	}
	
	protected List<Destinatario> destinatarios;
	
	public void setDestinatarios(List<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public List<Destinatario> getDestinatarios() {
		return destinatarios;
	}
		
}
