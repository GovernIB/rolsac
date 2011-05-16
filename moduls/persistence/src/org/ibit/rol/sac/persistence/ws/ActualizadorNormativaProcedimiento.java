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
	public
	ActuacionTransferible generarActuacionTransferible() {
		return NormativaTransferible.generar(norm);
	}

	@Override
	public
	void actualizarActuacion(ActualizacionServicio actualizacionSvc,
			ActuacionTransferible elemTransf) throws WSInvocatorException {
		actualizacionSvc.actualizarNormativaProcedimiento((NormativaTransferible) elemTransf,idProc);
	}

	@Override
	Object getActuacion() {
		return norm;
	}

	
	@Override
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		
		actualizacionSvc.borrarNormativaProcedimiento(norm.getId(),idProc);
	}
	

	Normativa norm;
	Long idProc;
}
