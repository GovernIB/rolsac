package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public interface FormulariQueryService {

    public ArxiuQueryServiceAdapter obtenirArchivo();
    
    public ArxiuQueryServiceAdapter obtenirManual();
    
    public TramitQueryServiceAdapter obtenirTramit();

}
