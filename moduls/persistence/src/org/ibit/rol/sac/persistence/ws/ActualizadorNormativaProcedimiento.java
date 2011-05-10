package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.NormativaTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorNormativaProcedimiento extends ActualizadorBase {

	
	
	public ActualizadorNormativaProcedimiento(Normativa norm, Long idProc) {
		this.norm = norm;
		this.idProc = idProc;
	}

	@Override
	ActuacionTransferible generarActuacionTransferible() {
		return NormativaTransferible.generar(norm);
	}

	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarNormativaProcedimiento((NormativaTransferible) elemTransf,idProc);
	}

	@Override
	Object getActuacion() {
		return norm;
	}

	@Override
	void borrar() {
		for (final Destinatario destinatario : destinatarios) {
			try{
		        if (calActualizarElDestinatari()) {
					final ActualizacionServicio actualizacion =ActualizacionServicio.createActualizacionServicio(
						destinatario.getEndpoint(), destinatario.getIdRemoto());
					actualizacion.borrarNormativaProcedimiento(norm.getId(),idProc);
		        }
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(norm, true, destinatario, e);
				log.error(e);
			}
		}
	}

	Normativa norm;
	Long idProc;
}
