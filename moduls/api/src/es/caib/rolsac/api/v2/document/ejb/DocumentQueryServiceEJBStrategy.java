package es.caib.rolsac.api.v2.document.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceEJBStrategy implements DocumentQueryServiceStrategy {

    private DocumentQueryServiceDelegate documentQueryServiceDelegate;
    
    public void setDocumentQueryServiceDelegate(DocumentQueryServiceDelegate documentQueryServiceDelegate) {
        this.documentQueryServiceDelegate = documentQueryServiceDelegate;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) {
        return documentQueryServiceDelegate.obtenirFitxa(idFitxa);
    }

    public ProcedimentDTO obtenirProcediment(long idProc) {
        return documentQueryServiceDelegate.obtenirProcediment(idProc);
    }
    
    public ArxiuDTO obtenirArxiu(long idArxiu) {
        return documentQueryServiceDelegate.obtenirArxiu(idArxiu);
    }
}
