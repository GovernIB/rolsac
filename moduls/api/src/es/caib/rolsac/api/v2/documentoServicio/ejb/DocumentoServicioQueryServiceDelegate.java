package es.caib.rolsac.api.v2.documentoServicio.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentoServicio.ws.DocumentoServicioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;

public class DocumentoServicioQueryServiceDelegate {

    private DocumentoServicioQueryServiceEJBLocator documentoServicioQueryServiceLocator;
    
    public void setDocumentoNormativaQueryServiceLocator(DocumentoServicioQueryServiceEJBLocator documentoServicioQueryServiceEJBLocator) {
        this.documentoServicioQueryServiceLocator = documentoServicioQueryServiceEJBLocator;
    }

  /*  public NormativaDTO obtenirNormativa(long idNormativa) throws DelegateException {
        try {
            DocumentoNormativaQueryServiceEJBRemote ejb = documentoNormativaQueryServiceLocator.getDocumentoNormativaQueryServiceEJB();
            return ejb.obtenirNormativa(idNormativa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }*/

    public ArxiuDTO obtenirArxiu(long idArxiu) throws DelegateException {
        try {
            DocumentoServicioQueryServiceEJBRemote ejb = documentoServicioQueryServiceLocator.getDocumentoServicioQueryServiceEJB();
            return ejb.obtenirArxiu(idArxiu);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
