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
	public
	ActuacionTransferible generarActuacionTransferible() {
		return UnidadAdministrativaTransferible.generar(unidad);
	}

	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarUnidadAdministrativa((UnidadAdministrativaTransferible) elemTransf);

	}

	@Override
	Object getActuacion() {
		return unidad;
	}

	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		actualizacionSvc.borrarUnidadAdministrativa(unidad.getId());
	}
	
	
	UnidadAdministrativa unidad;


	
}
