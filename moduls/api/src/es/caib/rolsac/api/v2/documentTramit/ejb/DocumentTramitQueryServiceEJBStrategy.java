package es.caib.rolsac.api.v2.documentTramit.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceEJBStrategy implements DocumentTramitQueryServiceStrategy {

    private DocumentTramitQueryServiceDelegate documentTramitQueryServiceDelegate;

    public void setDocumentTramitQueryServiceDelegate(DocumentTramitQueryServiceDelegate documentTramitQueryServiceDelegate) {
        this.documentTramitQueryServiceDelegate = documentTramitQueryServiceDelegate;
    }

    public TramitDTO obtenirTramit(long idTramit) throws StrategyException {
        try {
            return documentTramitQueryServiceDelegate.obtenirTramit(idTramit);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
        try {
            return documentTramitQueryServiceDelegate.obtenirArxiu(idArxiu);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
