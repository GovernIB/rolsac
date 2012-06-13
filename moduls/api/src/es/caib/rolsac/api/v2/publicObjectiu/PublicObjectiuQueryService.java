package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryService;

public interface PublicObjectiuQueryService {

    int getNumAgrupacions();

    List<AgrupacioFetVitalQueryService> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria);

}
