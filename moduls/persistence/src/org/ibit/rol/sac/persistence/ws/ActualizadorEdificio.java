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
	public
	ActuacionTransferible generarActuacionTransferible() {
		return EdificioTransferible.generar(edif);
	}

	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		
		actualizacionSvc.actualizarEdificio((EdificioTransferible) elemTransf);

	}

	@Override
	Object getActuacion() {
		return edif;
	}

	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		actualizacionSvc.borrarEdificio(edif.getId());
		
	}
	
	Edificio edif;
}
