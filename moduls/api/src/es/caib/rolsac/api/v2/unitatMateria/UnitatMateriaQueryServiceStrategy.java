package es.caib.rolsac.api.v2.unitatMateria;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface UnitatMateriaQueryServiceStrategy {

    MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria);

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

}
