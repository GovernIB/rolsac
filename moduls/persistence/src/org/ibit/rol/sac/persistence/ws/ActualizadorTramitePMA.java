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
	public
	ActuacionTransferible generarActuacionTransferible() {
		return TramiteTransferible.generar(tramite);
	}
	
	
	
	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible actuacionTransferible) throws WSInvocatorException {
		log.info("actualizando un TramitePMA");
		actualizacionSvc.actualizarTramite((TramiteTransferible) actuacionTransferible);
		
	}
	
	@Override
	public Object getActuacion() {
		return tramite;
	}

	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		
		super.borrarActuacion(actualizacionSvc);
	}
	
	
}
