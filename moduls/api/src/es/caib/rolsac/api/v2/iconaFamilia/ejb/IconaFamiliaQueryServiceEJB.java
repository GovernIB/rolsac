package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

public class IconaFamiliaQueryServiceEJB {

    public FamiliaDTO obtenirFamilia(long id) {
        FamiliaCriteria famCriteria = new FamiliaCriteria();
        famCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirFamilia(famCriteria);
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
