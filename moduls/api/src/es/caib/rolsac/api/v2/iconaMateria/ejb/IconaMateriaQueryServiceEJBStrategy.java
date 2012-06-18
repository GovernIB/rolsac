package es.caib.rolsac.api.v2.iconaMateria.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceEJBStrategy implements IconaMateriaQueryServiceStrategy {

    private IconaMateriaQueryServiceDelegate iconaMateriaQueryServiceDelegate;

    public void setIconaMateriaQueryServiceDelegate(IconaMateriaQueryServiceDelegate iconaMateriaQueryServiceDelegate) {
        this.iconaMateriaQueryServiceDelegate = iconaMateriaQueryServiceDelegate;
    }

    public MateriaDTO obtenirMateria(long id) {
        return iconaMateriaQueryServiceDelegate.obtenirMateria(id);
    }

    public PerfilDTO obtenirPerfil(long id) {
        return iconaMateriaQueryServiceDelegate.obtenirPerfil(id);
    }

    public ArxiuDTO obtenirIcona(long id) {
        return iconaMateriaQueryServiceDelegate.obtenirIcona(id);
    }

}
