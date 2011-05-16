package org.ibit.rol.sac.persistence.ws;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.persistence.portal.PortalDestinatario;
import org.ibit.rol.sac.persistence.portal.PortalDestinatarioFactory;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public abstract class ActualizadorBase {
	protected static Log log = LogFactory.getLog(ActualizadorBase.class);


	
	public abstract ActuacionTransferible generarActuacionTransferible() ;
	
	public abstract void actualizarActuacion(ActualizacionServicio actualizacionSvc, ActuacionTransferible elemTransf) 
		throws WSInvocatorException;

	public abstract void borrarActuacion(ActualizacionServicio actualizacionSvc) 
	throws WSInvocatorException;

	
	abstract Object getActuacion();
	

		
}
