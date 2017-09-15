package es.caib.rolsac.api.v2.documentoNormativa.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;

public class DocumentoNormativaQueryServiceEJBStrategy implements DocumentoNormativaQueryServiceStrategy {

    private DocumentoNormativaQueryServiceDelegate DocumentoNormativaQueryServiceDelegate;

    public void setDocumentoNormativaQueryServiceDelegate(DocumentoNormativaQueryServiceDelegate DocumentoNormativaQueryServiceDelegate) {
        this.DocumentoNormativaQueryServiceDelegate = DocumentoNormativaQueryServiceDelegate;
    }

    public NormativaDTO obtenirNormativa(long idNormativa) throws StrategyException {
        try {
            return DocumentoNormativaQueryServiceDelegate.obtenirNormativa(idNormativa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
        try {
            return DocumentoNormativaQueryServiceDelegate.obtenirArxiu(idArxiu);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		// NO se utilizan en EJB.
	}
}
