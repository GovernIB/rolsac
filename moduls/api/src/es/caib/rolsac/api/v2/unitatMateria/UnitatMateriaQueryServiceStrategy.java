package es.caib.rolsac.api.v2.unitatMateria;

import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface UnitatMateriaQueryServiceStrategy {

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat);

    MateriaDTO obtenirMateria(Long idMateria);

}
