package es.caib.rolsac.api.v2.document.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.ejb.intf.DocumentQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceDelegate {

    private DocumentQueryServiceEJBLocator documentQueryServiceLocator;
    
    public void setDocumentQueryServiceLocator(DocumentQueryServiceEJBLocator documentQueryServiceEJBLocator) {
        this.documentQueryServiceLocator = documentQueryServiceEJBLocator;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) throws DelegateException {
        try {
            DocumentQueryServiceEJBRemote ejb = documentQueryServiceLocator.getDocumentQueryServiceEJB();
            return ejb.obtenirFitxa(idFitxa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ProcedimentDTO obtenirProcediment(long idProc) throws DelegateException {
        try {
            DocumentQueryServiceEJBRemote ejb = documentQueryServiceLocator.getDocumentQueryServiceEJB();
            return ejb.obtenirProcediment(idProc);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws DelegateException {
        try {
            DocumentQueryServiceEJBRemote ejb = documentQueryServiceLocator.getDocumentQueryServiceEJB();
            return ejb.obtenirArxiu(idArxiu);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
