package es.caib.rolsac.api.v2.iconaMateria.ejb;

import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceEJBStrategy implements IconaMateriaQueryServiceStrategy {

    IconaMateriaQueryServiceDelegate delegate;
    IconaMateriaQueryServiceLocator locator;

    public MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public PerfilDTO obtenirPerfil(long id, PerfilCriteria perfilCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
