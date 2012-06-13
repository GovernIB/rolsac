package es.caib.rolsac.api.v2.documentTramit.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceEJBStrategy implements DocumentTramitQueryServiceStrategy {

    private DocumentTramitQueryServiceDelegate documentTramitQueryServiceDelegate;

    public void setDocumentTramitQueryServiceDelegate(DocumentTramitQueryServiceDelegate documentTramitQueryServiceDelegate) {
        this.documentTramitQueryServiceDelegate = documentTramitQueryServiceDelegate;
    }

    public TramitDTO obtenirTramit(long idTramit) {
        return documentTramitQueryServiceDelegate.obtenirTramit(idTramit);
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) {
        return documentTramitQueryServiceDelegate.obtenirArxiu(idArxiu);
    }

}
