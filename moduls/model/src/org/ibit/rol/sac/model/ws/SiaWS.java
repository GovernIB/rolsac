package org.ibit.rol.sac.model.ws;

import java.util.Date;

import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.SiaJob;

/**
 * Representación  Sia.
 * Envio SIA
 */
public class SiaWS {
	
	public static SiaResultado enviarSIA(Sia sia) {
		
		return new SiaResultado(SiaResultado.RESULTADO_OK, "TODO CORRECTO");
	}

	public static SiaResultado enviarSIAJob(SiaJob sia) {
		return new SiaResultado(SiaResultado.RESULTADO_OK, "TODO CORRECTO");
	}
}
