package es.caib.rolsac.api.v2.iconaMateria.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceEJBStrategy implements IconaMateriaQueryServiceStrategy {

    private IconaMateriaQueryServiceDelegate iconaMateriaQueryServiceDelegate;

    public void setIconaMateriaQueryServiceDelegate(IconaMateriaQueryServiceDelegate iconaMateriaQueryServiceDelegate) {
        this.iconaMateriaQueryServiceDelegate = iconaMateriaQueryServiceDelegate;
    }

    public MateriaDTO obtenirMateria(long id) throws StrategyException {
        try {
            return iconaMateriaQueryServiceDelegate.obtenirMateria(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public PerfilDTO obtenirPerfil(long id) throws StrategyException {
        try {
            return iconaMateriaQueryServiceDelegate.obtenirPerfil(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirIcona(long id) throws StrategyException {
        try {
            return iconaMateriaQueryServiceDelegate.obtenirIcona(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
