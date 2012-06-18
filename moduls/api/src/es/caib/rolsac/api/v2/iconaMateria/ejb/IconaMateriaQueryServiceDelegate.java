package es.caib.rolsac.api.v2.iconaMateria.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceDelegate {
    
    private IconaMateriaQueryServiceEJBLocator iconaMateriaQueryServiceLocator;
    
    public void setIconaMateriaQueryServiceLocator(IconaMateriaQueryServiceEJBLocator iconaMateriaQueryServiceLocator) {
        this.iconaMateriaQueryServiceLocator = iconaMateriaQueryServiceLocator;
    }

    public MateriaDTO obtenirMateria(long id) {
        IconaMateriaQueryServiceEJB ejb = iconaMateriaQueryServiceLocator.getIconaMateriaQueryServiceEJB();
        return ejb.obtenirMateria(id);
    }

    public PerfilDTO obtenirPerfil(long id) {
        IconaMateriaQueryServiceEJB ejb = iconaMateriaQueryServiceLocator.getIconaMateriaQueryServiceEJB();
        return ejb.obtenirPerfil(id);
    }

    public ArxiuDTO obtenirIcona(long id) {
        IconaMateriaQueryServiceEJB ejb = iconaMateriaQueryServiceLocator.getIconaMateriaQueryServiceEJB();
        return ejb.obtenirIcona(id);
    }

}
