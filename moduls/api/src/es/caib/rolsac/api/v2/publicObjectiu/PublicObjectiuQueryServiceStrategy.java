package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;

public interface PublicObjectiuQueryServiceStrategy {

    public int getNumAgrupacions(long id) throws StrategyException;

    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id, AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) throws StrategyException;

}
