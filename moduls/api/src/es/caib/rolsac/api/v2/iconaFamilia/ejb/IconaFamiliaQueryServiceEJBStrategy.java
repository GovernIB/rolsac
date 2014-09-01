package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceEJBStrategy implements IconaFamiliaQueryServiceStrategy {

    private IconaFamiliaQueryServiceDelegate iconaFamiliaQueryServiceDelegate;

    public void setIconaFamiliaQueryServiceDelegate(IconaFamiliaQueryServiceDelegate iconaFamiliaQueryServiceDelegate) {
        this.iconaFamiliaQueryServiceDelegate = iconaFamiliaQueryServiceDelegate;
    }

    public FamiliaDTO obtenirFamilia(long id) throws StrategyException {
        try {
            return iconaFamiliaQueryServiceDelegate.obtenirFamilia(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public PerfilDTO obtenirPerfil(long id) throws StrategyException {
        try {
            return iconaFamiliaQueryServiceDelegate.obtenirPerfil(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirIcona(long id) throws StrategyException {
        try {
            return iconaFamiliaQueryServiceDelegate.obtenirIcona(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
