package es.caib.rolsac.api.v2.documentTramit;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface DocumentTramitQueryServiceStrategy {

	public TramitDTO obtenirTramit(long idTramit);
	
	public ArxiuDTO obtenirArxiu(long idArxiu);
	
}
