package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;

public interface PublicObjectiuQueryService {

    int getNumAgrupacions();

    List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria);

}
