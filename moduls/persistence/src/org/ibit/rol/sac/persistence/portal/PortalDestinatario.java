package org.ibit.rol.sac.persistence.portal;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.persistence.ws.ActualizadorBase;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public abstract class PortalDestinatario {

	Destinatario destinatario;
	
	public PortalDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario; 
	}
	public abstract void actualizarActuacion(ActualizadorBase actualizadorBase) throws WSInvocatorException;
	
	public abstract void borrarActuacion(ActualizadorBase actualizadorBase) throws WSInvocatorException;

}
