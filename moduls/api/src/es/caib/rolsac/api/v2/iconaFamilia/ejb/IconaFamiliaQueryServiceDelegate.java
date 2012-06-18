package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceDelegate {
    
    private IconaFamiliaQueryServiceEJBLocator iconaFamiliaQueryServiceLocator;
    
    public void setIconaFamiliaQueryServiceLocator(IconaFamiliaQueryServiceEJBLocator iconaFamiliaQueryServiceLocator) {
        this.iconaFamiliaQueryServiceLocator = iconaFamiliaQueryServiceLocator;
    }

    public FamiliaDTO obtenirFamilia(long id) {
        IconaFamiliaQueryServiceEJB ejb = iconaFamiliaQueryServiceLocator.getIconaFamiliaQueryServiceEJB();
        return ejb.obtenirFamilia(id);
    }

    public PerfilDTO obtenirPerfil(long id) {
        IconaFamiliaQueryServiceEJB ejb = iconaFamiliaQueryServiceLocator.getIconaFamiliaQueryServiceEJB();
        return ejb.obtenirPerfil(id);
    }

    public ArxiuDTO obtenirIcona(long id) {
        IconaFamiliaQueryServiceEJB ejb = iconaFamiliaQueryServiceLocator.getIconaFamiliaQueryServiceEJB();
        return ejb.obtenirIcona(id);
    }

}
