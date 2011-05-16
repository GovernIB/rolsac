package org.ibit.rol.sac.persistence.portal;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.ws.ActualizadorBase;
import org.ibit.rol.sac.persistence.ws.ActualizadorTramitePMA;
import org.ibit.rol.sac.persistence.ws.invoker.WSInvocatorException;

import es.caib.persistence.vuds.VentanillaUnica;

public class PortalDestinatarioVUDS extends PortalDestinatario {

	public PortalDestinatarioVUDS(Destinatario destinatario) {
		super(destinatario);
	}

	@Override
	public void actualizarActuacion(ActualizadorBase actualizadorBase) {

		ActualizadorTramitePMA actualizador =(ActualizadorTramitePMA)actualizadorBase;
		

		Tramite tramite = (Tramite)actualizador.getActuacion();

/*
 * 
 * TODO ejaen@dgtic - tot la actualizacio amb el vuds s'ha de refer, perque
 * ens han canviat el ws. 
 *
		if(hayQueActualizarTramiteEnlaVentanilla(tramite) ) {
			String[] camposSinValidar=VentanillaUnica.validarTramiteVuds(tramite, "es").getSinTraducir();
			if(0<camposSinValidar.length)
				throw new ValidateVudsException(camposSinValidar);
		}

			else if(Operativa.MODIFICA==tramite.getOperativa())throw new ActualizacionVudsException();

*/		
	}

	private boolean hayQueActualizarTramiteEnlaVentanilla(Tramite tramite) {
		return VentanillaUnica.estaVentanillaAbierta() && null!=tramite.getId() && tramite.esVentanillaUnica();
	}

	@Override
	public void borrarActuacion(ActualizadorBase actualizadorBase)
			throws WSInvocatorException {
		// TODO Auto-generated method stub
		
	}

}
