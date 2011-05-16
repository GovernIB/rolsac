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
	public
	ActuacionTransferible generarActuacionTransferible() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public
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
	public void borrarActuacion(ActualizacionServicio actualizacionSvc)
			throws WSInvocatorException {
		final long idFicha = fichaUA.getIdFicha();
		final long idUA = fichaUA.getIdUnidadAdministrativa();
		final String codEs = fichaUA.getCodigoEstandarSeccion();
		actualizacionSvc.borrarFichaUA(idFicha, idUA, codEs);
		
	}
	

	
	FichaUATransferible fichaUA;
}
