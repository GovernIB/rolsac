package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.EdificioTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorEdificioUA extends ActualizadorBase {

	
	public ActualizadorEdificioUA(Edificio edif, Long idUA) {
		this.edif = edif;
		this.idUA = idUA;
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
		
		actualizacionSvc.actualizarEdificioUA((EdificioTransferible) elemTransf,idUA);

	}

	@Override
	Object getActuacion() {
		return edif;
	}

	
	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc) throws WSInvocatorException {
		actualizacionSvc.borrarEdificioUA(edif.getId(),idUA);
	}
	
	

	Edificio edif;
	Long idUA;
}
