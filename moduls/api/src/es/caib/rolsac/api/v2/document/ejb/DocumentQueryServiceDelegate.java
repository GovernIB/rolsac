package es.caib.rolsac.api.v2.document.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceDelegate {

    private DocumentQueryServiceEJBLocator documentQueryServiceLocator;
    
    public void setDocumentQueryServiceLocator(DocumentQueryServiceEJBLocator documentQueryServiceEJBLocator) {
        this.documentQueryServiceLocator = documentQueryServiceEJBLocator;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) {
        DocumentQueryServiceEJB ejb = documentQueryServiceLocator.getDocumentQueryServiceEJB();
        return ejb.obtenirFitxa(idFitxa);
    }

    public ProcedimentDTO obtenirProcediment(long idProc) {
        DocumentQueryServiceEJB ejb = documentQueryServiceLocator.getDocumentQueryServiceEJB();
        return ejb.obtenirProcediment(idProc);
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) {
        DocumentQueryServiceEJB ejb = documentQueryServiceLocator.getDocumentQueryServiceEJB();
        return ejb.obtenirArxiu(idArxiu);
    }
    
}
