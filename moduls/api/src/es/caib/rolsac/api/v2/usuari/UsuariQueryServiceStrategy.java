package es.caib.rolsac.api.v2.usuari;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface UsuariQueryServiceStrategy {

    public int getNumUnitatsAdministratives(long id) throws StrategyException;

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

}
