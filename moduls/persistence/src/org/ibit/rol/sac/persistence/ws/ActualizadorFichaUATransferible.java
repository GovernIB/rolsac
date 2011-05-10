package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.FichaUATransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorFichaUATransferible extends ActualizadorBase {

	
	
	public ActualizadorFichaUATransferible(FichaUATransferible fichaUA) {
		super();
		this.fichaUA = fichaUA;
	}

	@Override
	ActuacionTransferible generarActuacionTransferible() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		// TODO Auto-generated method stub

	}

	@Override
	Object getActuacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void borrar() {
		final long idFicha = fichaUA.getIdFicha();
		final long idUA = fichaUA.getIdUnidadAdministrativa();
		final String codEs = fichaUA.getCodigoEstandarSeccion();
		for (final Destinatario destinatario : destinatarios) {
			try{
				if(calActualizarElDestinatari()) {
					log.info("Al Destinatario: "+destinatario.getNombre());
					final ActualizacionServicio actualizacion = ActualizacionServicio.createActualizacionServicio(
							destinatario.getEndpoint(), destinatario.getIdRemoto());
						actualizacion.borrarFichaUA(idFicha, idUA, codEs);
		        }
			} catch (WSInvocatorException e) {
				//Si falla mando un Email informando del fallo al destinatario
				ReportarFallo.reportar(fichaUA, true, destinatario, e);
				log.error(e);
			}
		}

	}

	
	FichaUATransferible fichaUA;
}
