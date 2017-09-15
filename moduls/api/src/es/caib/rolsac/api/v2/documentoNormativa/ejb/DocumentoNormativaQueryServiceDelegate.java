package es.caib.rolsac.api.v2.documentoNormativa.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentoNormativa.ejb.intf.DocumentoNormativaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;

public class DocumentoNormativaQueryServiceDelegate {

    private DocumentoNormativaQueryServiceEJBLocator documentoNormativaQueryServiceLocator;
    
    public void setDocumentoNormativaQueryServiceLocator(DocumentoNormativaQueryServiceEJBLocator documentoNormativaQueryServiceEJBLocator) {
        this.documentoNormativaQueryServiceLocator = documentoNormativaQueryServiceEJBLocator;
    }

    public NormativaDTO obtenirNormativa(long idNormativa) throws DelegateException {
        try {
            DocumentoNormativaQueryServiceEJBRemote ejb = documentoNormativaQueryServiceLocator.getDocumentoNormativaQueryServiceEJB();
            return ejb.obtenirNormativa(idNormativa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws DelegateException {
        try {
            DocumentoNormativaQueryServiceEJBRemote ejb = documentoNormativaQueryServiceLocator.getDocumentoNormativaQueryServiceEJB();
            return ejb.obtenirArxiu(idArxiu);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
