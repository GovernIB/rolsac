package es.caib.rolsac.api.v2.iconaMateria;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilQueryService;

public interface IconaMateriaQueryService {

    MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria);

    PerfilQueryService obtenirPerfil(PerfilCriteria perfilCriteria);

}
