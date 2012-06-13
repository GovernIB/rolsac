package es.caib.rolsac.api.v2.iconaMateria;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public interface IconaMateriaQueryServiceStrategy {

    MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria);

    PerfilDTO obtenirPerfil(long id, PerfilCriteria perfilCriteria);

}
