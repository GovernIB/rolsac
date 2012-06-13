package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;

public interface AgrupacioFetVitalQueryService {

    PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu();

    List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria);
    
    ArxiuQueryServiceAdapter getFotografia();
    
    ArxiuQueryServiceAdapter getIcona();
    
    ArxiuQueryServiceAdapter getIconaGran();
    
    int getNumFetsVitals();

}
