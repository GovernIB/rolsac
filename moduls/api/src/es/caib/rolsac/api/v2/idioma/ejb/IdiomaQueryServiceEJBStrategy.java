package es.caib.rolsac.api.v2.idioma.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.idioma.IdiomaQueryServiceStrategy;

public class IdiomaQueryServiceEJBStrategy implements IdiomaQueryServiceStrategy {

	private IdiomaQueryServiceDelegate idiomaQueryServiceDelegate;

    public void setIdiomaQueryServiceDelegate(IdiomaQueryServiceDelegate idiomaQueryServiceDelegate) {
        this.idiomaQueryServiceDelegate = idiomaQueryServiceDelegate;
    }
    
    public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws StrategyException {
        try {
            return idiomaQueryServiceDelegate.llistarIdiomes(idiomaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
