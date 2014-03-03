package org.ibit.rol.sac.persistence.remote;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.persistence.remote.vuds.ActualizadorVuds;


/**
 * @author enric@dgtic
 * Definit com un facade perque entenc que aquesta classe 
 * simplifica l'accés als diferents actualitzadors  
 *
 */
public class ActualizadorFacade {

	public static Log log = LogFactory.getLog(ActualizadorFacade.class);

	public void actualizar(Object actuacion) {
 
		if(esPublico(actuacion)) {
			log.debug("actualizando en actualizador pma");
			Actualizador.actualizar(actuacion);
			if(esTramiteVUDS(actuacion)) {
				log.debug("actualizando en actualizador vuds");
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
