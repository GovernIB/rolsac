package es.caib.rolsac.api.v2.usuari.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceStrategy;

public class UsuariQueryServiceEJBStrategy implements UsuariQueryServiceStrategy {

    private UsuariQueryServiceDelegate usuariQueryServiceDelegate;

    public void setUsuariQueryServiceDelegate(UsuariQueryServiceDelegate usuariQueryServiceDelegate) {
        this.usuariQueryServiceDelegate = usuariQueryServiceDelegate;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return usuariQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws StrategyException {
        try {
            return usuariQueryServiceDelegate.getNumUnitatsAdministratives(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
