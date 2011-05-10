package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorUnidadAdministrativa extends ActualizadorBase {

	
	
	public ActualizadorUnidadAdministrativa(UnidadAdministrativa unidad) {
		this.unidad = unidad;
	}

	@Override
	ActuacionTransferible generarActuacionTransferible() {
		return UnidadAdministrativaTransferible.generar(unidad);
	}

	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarUnidadAdministrativa((UnidadAdministrativaTransferible) elemTransf);

	}

	@Override
	Object getActuacion() {
		return unidad;
	}

	@Override
	void borrar() {
		for (final Destinatario destinatario : destinatarios) {
			try{
				if(calActualizarElDestinatari()) {
					log.info("Al Destinatario: "+destinatario.getNombre());
					final ActualizacionServicio actualizacion = ActualizacionServicio.createActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarUnidadAdministrativa(unidad.getId());
				}
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo
				ReportarFallo.reportar(unidad, true, destinatario, e);
				log.error(e);
			}
		}

	}

	
	UnidadAdministrativa unidad;


	
}
