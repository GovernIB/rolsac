package org.ibit.rol.sac.persistence.ws;

import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.FichaUATransferible;

public class ActualizadorFactory {

	public static ActualizadorBase creaActualizador(Object actuacion, Object[] params)  {

		if (actuacion instanceof Tramite && params.length==0) 
			return new ActualizadorTramite((Tramite)actuacion);
		
		if (actuacion instanceof Tramite && params.length==1) 
			return new ActualizadorTramitePMA((Tramite)actuacion);
		
		if (actuacion instanceof ProcedimientoLocal) 
			return new ActualizadorProcedimiento((ProcedimientoLocal)actuacion);
		
		if (actuacion instanceof Ficha) 
			return new ActualizadorFicha((Ficha)actuacion);

		if (actuacion instanceof UnidadAdministrativa)
			return new ActualizadorUnidadAdministrativa((UnidadAdministrativa) actuacion);

		
		if (actuacion instanceof FichaUATransferible) 
			return new ActualizadorFichaUATransferible((FichaUATransferible) actuacion);

		if (actuacion instanceof Edificio && params.length==0)  {
			return new ActualizadorEdificio((Edificio) actuacion);
			
		}
			
		if (actuacion instanceof Edificio && params.length==1)  {
			final Long idUA = (Long) params[0];
			return new ActualizadorEdificioUA((Edificio) actuacion, idUA);
			
		}

		if (actuacion  instanceof Normativa && params.length==0)  {
			return new ActualizadorNormativa((Normativa) actuacion);
		}

		
		if (actuacion  instanceof Normativa && params.length==1)  {
			final Long idProc = (Long) params[0];
			return new ActualizadorNormativaProcedimiento((Normativa) actuacion, idProc);
		}

		if (actuacion  instanceof Documento && params.length==1)  {
			final Long idProc = (Long) params[0];
			return new ActualizadorDocumentoProcedimiento((Documento) actuacion, idProc);
		}
		
		return null;
	}
}