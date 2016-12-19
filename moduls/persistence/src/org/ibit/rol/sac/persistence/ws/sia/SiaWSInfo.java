package org.ibit.rol.sac.persistence.ws.sia;

import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.sia.consultar.WsSIAConsultarActuacionesIdentificacion_PortType;
import org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ParamSIAACTUACIONESACTUACION;
import org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ParamSIA;
import org.ibit.rol.sac.persistence.ws.sia.consultar.messages.enviaSIA.EnviaSIA;

/**
 * Representación info Sia.
 * Envio SIA
 */
public class SiaWSInfo {
	
	/**
	 * Obtener info de sia. 
	 * @param sia
	 * @throws Exception
	 */
	public static void infoSIA(Sia sia) throws Exception {
		if (!SiaUtils.isActivoEnvio()) {
			throw new Exception(" Estás en modo debug!");
		}
		final String usuario = SiaUtils.getUsuarioEnvio();
		final String password = SiaUtils.getPasswordEnvio();
		final WsSIAConsultarActuacionesIdentificacion_PortType client = SiaClient.createInfoClient(SiaUtils.getUrlEnvio());
		ParamSIAACTUACIONESACTUACION[] actuaciones = cargarDatosSiaInfo(sia);
		
		
		ParamSIA parameters = new ParamSIA(usuario, password, null, actuaciones);
		//ParamSIA parameters = new ParamSIA(usuario, password, null, actuaciones);
		EnviaSIA envioSia = client.consultarSIA(parameters);
	}
	
	private static org.ibit.rol.sac.persistence.ws.sia.consultar.messages.paramSIA.ParamSIAACTUACIONESACTUACION[] cargarDatosSiaInfo(Sia sia) {
		return null;
	}
	
}
