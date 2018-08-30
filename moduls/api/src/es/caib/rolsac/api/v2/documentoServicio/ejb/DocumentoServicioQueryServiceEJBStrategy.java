package es.caib.rolsac.api.v2.documentoServicio.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class DocumentoServicioQueryServiceEJBStrategy implements DocumentoServicioQueryServiceStrategy {

    private DocumentoServicioQueryServiceDelegate DocumentoServicioQueryServiceDelegate;

    public void setDocumentoNormativaQueryServiceDelegate(DocumentoServicioQueryServiceDelegate DocumentoServicioQueryServiceDelegate) {
        this.DocumentoServicioQueryServiceDelegate = DocumentoServicioQueryServiceDelegate;
    }

  /*  public NormativaDTO obtenirNormativa(long idNormativa) throws StrategyException {
        try {
            return DocumentoNormativaQueryServiceDelegate.obtenirNormativa(idNormativa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }*/

    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
        try {
            return DocumentoServicioQueryServiceDelegate.obtenirArxiu(idArxiu);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		// NO se utilizan en EJB.
	}
}
