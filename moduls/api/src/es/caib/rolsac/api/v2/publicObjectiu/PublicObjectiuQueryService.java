package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface PublicObjectiuQueryService {

    public int getNumAgrupacions() throws QueryServiceException;

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) throws QueryServiceException;

}
