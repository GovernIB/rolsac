package es.caib.rolsac.api.v2.usuari;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;

public interface UsuariQueryService {

    int getNumUnitatsAdministratives();

    List<UnitatAdministrativaQueryService> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

}
