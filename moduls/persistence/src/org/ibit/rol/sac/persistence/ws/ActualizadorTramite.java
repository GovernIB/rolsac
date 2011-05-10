package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import es.caib.persistence.vuds.VentanillaUnica;

public class ActualizadorTramite extends ActualizadorBase {

	
	public ActualizadorTramite(Tramite tramite) {
		this.tramite = tramite;
	}


	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	ActuacionTransferible generarActuacionTransferible() {
		return null; 
	}
	
	@Override
	Object getActuacion() {
		return tramite;
	}

	
	@Override
	void borrar() {
		String value = System.getProperty("es.indra.caib.rolsac.oficina");
		for (final Destinatario destinatario : destinatarios) {
			try{
				if ((value == null) || value.equals("N")) {		
			        	log.info("Actualizo para el destinario : "+destinatario.getIdRemoto());
			        	log.info("Actualizo para end point : "+destinatario.getEndpoint());
					final ActualizacionServicio actualizacion = ActualizacionServicio.createActualizacionServicio(
						destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarTramite(tramite.getId());
		        }
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(tramite, true, destinatario, e);
				log.error(e);
			}
		}
		
	}

	Tramite tramite;
}
