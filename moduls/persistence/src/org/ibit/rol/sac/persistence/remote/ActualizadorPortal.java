package org.ibit.rol.sac.persistence.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.remote.vuds.ActualizadorVuds;
import org.ibit.rol.sac.persistence.ws.Actualizador;


/**
 * @author enric@dgtic
 *
 */
public class ActualizadorPortal {

	public static Log log = LogFactory.getLog(ActualizadorPortal.class);

	public void actualizar(Object actuacion) {
 
		if(esPublico(actuacion)) {
			log.info("actualizando en actualizador pma");
			Actualizador.actualizar(actuacion);
			if(esTramiteVUDS(actuacion)) {
				log.info("actualizando en actualizador vuds");
				ActualizadorVuds.actualizar((Tramite)actuacion);
			}
		}
	}

		
	
	private boolean esTramiteVUDS(Object actuacion) {
		if(!esTramite(actuacion)) return false;
		return ((Tramite)actuacion).esVentanillaUnica();
	}



	boolean esPublico(Object actuacion) {
		//TODO s'hauria de refactorizar el model d'actuacion para incloure el 
		//metode esPublico() en totes les actuacions (com en el cas del tramite).
		
		if(esTramite(actuacion)) return ((Tramite)actuacion).esPublico();
		return true;
	}
	
	boolean esTramite(Object actuacion) {
		if(actuacion instanceof Tramite) return true;
		return false;
	}
}
