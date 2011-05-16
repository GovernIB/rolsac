package org.ibit.rol.sac.persistence.portal;

import org.ibit.rol.sac.model.Destinatario;

public class PortalDestinatarioFactory {

	public static PortalDestinatario creaPortalDestinatario(Destinatario destino) {
		String nombrePortalDestino = destino.getNombre();

		if(elDestinariEsPortalVuds(nombrePortalDestino))
			return new PortalDestinatarioVUDS(destino);
		
		if(elDestinariEsPortalPMA(nombrePortalDestino))
			return new PortalDestinatarioPMA(destino);
		
		return null;
	}
	
	private static boolean elDestinariEsPortalVuds(String nombrePortalDestino) {
		return getNombrePortal("es.caib.rolsac.persistence.portal.vuds").equals(nombrePortalDestino);
	}

	private static boolean elDestinariEsPortalPMA(String nombrePortalDestino) {
		return getNombrePortal("es.caib.rolsac.persistence.portal.pma").equals(nombrePortalDestino);
	}


	
	private static String getNombrePortal(String portal) {
		return System.getProperty(portal);
	}
}
