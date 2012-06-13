package es.caib.rolsac.api.v2.iconaFamilia;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public interface IconaFamiliaQueryServiceStrategy {

    FamiliaDTO obtenirFamilia(long id, FamiliaCriteria familiaCriteria);

    MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria);

}
