package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.EdificioTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorEdificio extends ActualizadorBase {

	
	public ActualizadorEdificio(Edificio edif) {
		this.edif = edif;
	}

	@Override
	ActuacionTransferible generarActuacionTransferible() {
		return EdificioTransferible.generar(edif);
	}

	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		
		actualizacionSvc.actualizarEdificio((EdificioTransferible) elemTransf);

	}

	@Override
	Object getActuacion() {
		return edif;
	}

	@Override
	void borrar() {
		for (final Destinatario destinatario : destinatarios) {
			try{
		        if (calActualizarElDestinatari()) {					
					final ActualizacionServicio actualizacion = ActualizacionServicio.createActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarEdificio(edif.getId());
		        }
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(edif, true, destinatario, e);
				log.error(e);
			}
		}

	}

	Edificio edif;
}
