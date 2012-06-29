package es.caib.rolsac.api.v2.perfil.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceStrategy;

public class PerfilQueryServiceEJBStrategy implements PerfilQueryServiceStrategy {

    private PerfilQueryServiceDelegate perfilQueryServiceDelegate;

    public void setPerfilQueryServiceDelegate(PerfilQueryServiceDelegate perfilQueryServiceDelegate) {
        this.perfilQueryServiceDelegate = perfilQueryServiceDelegate;
    }

    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
        try {
            return perfilQueryServiceDelegate.llistarIconesFamilia(id, iconaFamiliaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
        try {
            return perfilQueryServiceDelegate.llistarIconesMateria(id, iconaMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumIconesFamilia(long id) throws StrategyException {
        try {
            return perfilQueryServiceDelegate.getNumIconesFamilia(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumIconesMateria(long id) throws StrategyException {
        try {
            return perfilQueryServiceDelegate.getNumIconesMateria(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
