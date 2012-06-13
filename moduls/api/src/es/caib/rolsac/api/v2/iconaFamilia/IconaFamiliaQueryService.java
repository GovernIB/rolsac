package es.caib.rolsac.api.v2.iconaFamilia;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaQueryService;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;

public interface IconaFamiliaQueryService {

    FamiliaQueryService obtenirFamilia(FamiliaCriteria familiaCriteria);

    MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria);

}
