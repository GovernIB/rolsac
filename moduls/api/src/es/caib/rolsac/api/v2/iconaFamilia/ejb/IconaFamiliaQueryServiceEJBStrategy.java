package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceEJBStrategy implements IconaFamiliaQueryServiceStrategy {

    private IconaFamiliaQueryServiceDelegate iconaFamiliaQueryServiceDelegate;

    public void setIconaFamiliaQueryServiceDelegate(IconaFamiliaQueryServiceDelegate iconaFamiliaQueryServiceDelegate) {
        this.iconaFamiliaQueryServiceDelegate = iconaFamiliaQueryServiceDelegate;
    }

    public FamiliaDTO obtenirFamilia(long id) {
        return iconaFamiliaQueryServiceDelegate.obtenirFamilia(id);
    }

    public PerfilDTO obtenirPerfil(long id) {
        return iconaFamiliaQueryServiceDelegate.obtenirPerfil(id);
    }

    public ArxiuDTO obtenirIcona(long id) {
        return iconaFamiliaQueryServiceDelegate.obtenirIcona(id);
    }

}
