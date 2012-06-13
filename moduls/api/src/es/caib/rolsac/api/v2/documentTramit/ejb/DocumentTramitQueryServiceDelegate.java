package es.caib.rolsac.api.v2.documentTramit.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceDelegate {

    private DocumentTramitQueryServiceEJBLocator documentTramitQueryServiceLocator;
    
    public void setDocumentTramitQueryServiceLocator(DocumentTramitQueryServiceEJBLocator documentTramitQueryServiceEJBLocator) {
        this.documentTramitQueryServiceLocator = documentTramitQueryServiceEJBLocator;
    }

    public TramitDTO obtenirTramit(long idTramit) {
        DocumentTramitQueryServiceEJB ejb = documentTramitQueryServiceLocator.getDocumentTramitQueryServiceEJB();
        return ejb.obtenirTramit(idTramit);
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) {
        DocumentTramitQueryServiceEJB ejb = documentTramitQueryServiceLocator.getDocumentTramitQueryServiceEJB();
        return ejb.obtenirArxiu(idArxiu);
    }
    
}
