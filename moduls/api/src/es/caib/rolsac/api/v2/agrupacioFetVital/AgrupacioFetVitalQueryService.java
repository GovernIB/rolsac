package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;

public interface AgrupacioFetVitalQueryService {

    public PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu() throws QueryServiceException;

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getFotografia() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException;
    
    public int getNumFetsVitals() throws QueryServiceException;

}
