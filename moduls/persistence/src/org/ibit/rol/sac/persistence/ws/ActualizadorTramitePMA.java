package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.TramiteTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;


public class ActualizadorTramitePMA extends ActualizadorTramite {

	public ActualizadorTramitePMA(Tramite tramite) {
		super(tramite);
	}

	
	@Override
	ActuacionTransferible generarActuacionTransferible() {
		return TramiteTransferible.generar(tramite);
	}
	
	
	
	@Override
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible actuacionTransferible) throws WSInvocatorException {
		log.info("actualizando un TramitePMA");
		actualizacionSvc.actualizarTramite((TramiteTransferible) actuacionTransferible);
		
	}
	
	@Override
	Object getActuacion() {
		return tramite;
	}
	
	
	
	@Override
	void borrar() {
		// TODO Auto-generated method stub
		super.borrar();
	}
}
