package es.caib.rolsac.api.v2.tramit.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tramit.ejb.intf.TramitQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceDelegate {

    private TramitQueryServiceEJBLocator tramitQueryServiceLocator;
    
    public void setTramitQueryServiceLocator(TramitQueryServiceEJBLocator tramitQueryServiceLocator) {
        this.tramitQueryServiceLocator = tramitQueryServiceLocator;
    }

    public int getNumDocumentsInformatius(long id) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.getNumDocumentsInformatius(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFormularis(long id) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.getNumFormularis(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumTaxes(long id) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.getNumTaxes(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public ProcedimentDTO obtenirProcediment(long idProc) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.obtenirProcediment(idProc);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public UnitatAdministrativaDTO obtenirOrganCompetent(long idUa) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.obtenirOrganCompetent(idUa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id, DocumentTramitCriteria documentTramitCriteria) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.llistatDocumentsInformatius(id, documentTramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.llistarFormularis(id, documentTramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) throws DelegateException {
        try {
            TramitQueryServiceEJBRemote ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
            return ejb.llistarTaxes(id, taxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
}
