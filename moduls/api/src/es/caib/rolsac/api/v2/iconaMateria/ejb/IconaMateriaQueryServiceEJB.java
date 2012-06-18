package es.caib.rolsac.api.v2.iconaMateria.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

public class IconaMateriaQueryServiceEJB {

    public MateriaDTO obtenirMateria(long id) {
        MateriaCriteria famCriteria = new MateriaCriteria();
        famCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirMateria(famCriteria);
    }

    public PerfilDTO obtenirPerfil(long id) {
        PerfilCriteria perCriteria = new PerfilCriteria();
        perCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirPerfil(perCriteria);
    }

    public ArxiuDTO obtenirIcona(long id) {
        return EJBUtils.getArxiuDTO(id);
    }

}
