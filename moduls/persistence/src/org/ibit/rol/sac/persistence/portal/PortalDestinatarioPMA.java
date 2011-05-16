package org.ibit.rol.sac.persistence.portal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.ws.ActuacionTransferible;
import org.ibit.rol.sac.persistence.ws.ActualizadorBase;
import org.ibit.rol.sac.persistence.ws.invoker.ActualizacionServicio;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

public class PortalDestinatarioPMA extends PortalDestinatario {

	public PortalDestinatarioPMA(Destinatario destinatario) {
		super(destinatario);
	}

	public static Log log = LogFactory.getLog(PortalDestinatarioPMA.class);

	@Override
	public void actualizarActuacion(ActualizadorBase actualizadorBase) throws WSInvocatorException {

		//La transformo en transferible
		ActuacionTransferible actuacionTransferible = actualizadorBase.generarActuacionTransferible();
		
		//SOLO DE PRUEBAS // QUITAR!!!!
		log.info("actualizando el destinatario: "+destinatario.getNombre());
		final ActualizacionServicio actualizacionSvc = ActualizacionServicio.createActualizacionServicio(
				destinatario.getEndpoint(), destinatario.getIdRemoto());
						
		actualizadorBase.actualizarActuacion(actualizacionSvc , actuacionTransferible);
		log.info("actualizacion enviada OK");
	}
	
	@Override
	public void borrarActuacion(ActualizadorBase actualizadorBase)
			throws WSInvocatorException {
		final ActualizacionServicio actualizacionSvc = ActualizacionServicio.createActualizacionServicio(
				destinatario.getEndpoint(), destinatario.getIdRemoto());
		actualizadorBase.borrarActuacion(actualizacionSvc);
		
	}
}
