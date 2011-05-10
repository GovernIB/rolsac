package es.caib.persistence.vuds;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.Tramite.Operativa;

public class DestinatarioVuds extends Destinatario {

	@Override
	public boolean calActualizar(Object elem) {
/*
		String value = System.getProperty("es.indra.caib.rolsac.oficina");
		if(elem instanceof Tramite) {
				//cal si es un tram vuds + dest es vuds + es una modificacio
				Tramite t=(Tramite)elem;
				if("1".equals(t.getProcedimiento().getVentanillaUnica()) && Operativa.MODIFICA == t.getOperativa()) 
					return true;
			}
		return false;
*/
		return true;
	}
	

}
