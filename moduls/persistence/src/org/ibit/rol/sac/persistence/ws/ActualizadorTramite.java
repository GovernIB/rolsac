package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.TramiteTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;


public class ActualizadorTramite extends ActualizadorBase {

	
	public ActualizadorTramite(Tramite tramite) {
		this.tramite = tramite;
	}


	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {

	}
	
	@Override
	public
	ActuacionTransferible generarActuacionTransferible() {
		return null; 
	}
	
	@Override
	public Object getActuacion() {
		return tramite;
	}

	
	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		actualizacionSvc.borrarTramite(tramite.getId());
		
	}
	
	Tramite tramite;
}
