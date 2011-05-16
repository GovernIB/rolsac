package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.model.ws.NormativaTransferible;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class ActualizadorNormativa extends ActualizadorBase {

	
	
	public ActualizadorNormativa(Normativa norm) {
		this.norm = norm;
	}

	@Override
	public
	ActuacionTransferible generarActuacionTransferible() {
		return NormativaTransferible.generar(norm);
	}

	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarNormativa((NormativaTransferible) elemTransf);
	}

	@Override
	Object getActuacion() {
		return norm;
	}

	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {

		actualizacionSvc.borrarNormativa(norm.getId());

	}
	
	Normativa norm;
}
