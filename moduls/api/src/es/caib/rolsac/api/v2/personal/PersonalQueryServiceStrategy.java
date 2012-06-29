package es.caib.rolsac.api.v2.personal;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface PersonalQueryServiceStrategy {

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) throws StrategyException;

}
