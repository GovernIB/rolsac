package es.caib.rolsac.api.v2.butlleti.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.butlleti.ejb.intf.ButlletiQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceDelegate {

    private ButlletiQueryServiceEJBLocator butlletiQueryServiceLocator;

    public void setButlletiQueryServiceLocator(ButlletiQueryServiceEJBLocator butlletiQueryServiceLocator) {
        this.butlletiQueryServiceLocator = butlletiQueryServiceLocator;
    }
    
    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws DelegateException {
        try {
            ButlletiQueryServiceEJBRemote ejb = butlletiQueryServiceLocator.getButlletiQueryServiceEJB();
            return ejb.getNumNormatives(id, tipus);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws DelegateException {
        ButlletiQueryServiceEJBRemote ejb;
        try {
            ejb = butlletiQueryServiceLocator.getButlletiQueryServiceEJB();
            return ejb.llistarNormatives(id, normativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
}
