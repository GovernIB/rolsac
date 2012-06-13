package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface EspaiTerritorialQueryServiceStrategy {

    int getNumFills();

    int getNumUnitatsAdministratives();

    int getNumAdministracionsRemotes();

    EspaiTerritorialDTO obtenirPare(long id, EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativa);
}
