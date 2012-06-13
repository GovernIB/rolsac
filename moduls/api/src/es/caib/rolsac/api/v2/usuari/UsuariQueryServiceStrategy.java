package es.caib.rolsac.api.v2.usuari;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface UsuariQueryServiceStrategy {

    int getNumUnitatsAdministratives(long id);

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

}
