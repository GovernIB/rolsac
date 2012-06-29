package es.caib.rolsac.api.v2.document.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceEJBStrategy implements DocumentQueryServiceStrategy {

    private DocumentQueryServiceDelegate documentQueryServiceDelegate;
    
    public void setDocumentQueryServiceDelegate(DocumentQueryServiceDelegate documentQueryServiceDelegate) {
        this.documentQueryServiceDelegate = documentQueryServiceDelegate;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) throws StrategyException {
        try {
            return documentQueryServiceDelegate.obtenirFitxa(idFitxa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ProcedimentDTO obtenirProcediment(long idProc) throws StrategyException {
        try {
            return documentQueryServiceDelegate.obtenirProcediment(idProc);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
        try {
            return documentQueryServiceDelegate.obtenirArxiu(idArxiu);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
}
