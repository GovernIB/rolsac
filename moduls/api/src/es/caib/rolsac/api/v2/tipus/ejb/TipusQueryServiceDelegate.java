package es.caib.rolsac.api.v2.tipus.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.tipus.ejb.intf.TipusQueryServiceEJBRemote;

public class TipusQueryServiceDelegate {

    private TipusQueryServiceEJBLocator tipusQueryServiceLocator;    
    
    public void setTipusQueryServiceLocator(TipusQueryServiceEJBLocator tipusQueryServiceLocator) {
        this.tipusQueryServiceLocator = tipusQueryServiceLocator;
    }

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) throws DelegateException {
        try {
            TipusQueryServiceEJBRemote ejb = tipusQueryServiceLocator.getTipusQueryServiceEJB();
            return ejb.llistarNormatives(id, normativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumNormatives(Long id, TIPUS_NORMATIVA totes) throws DelegateException {
        try {
            TipusQueryServiceEJBRemote ejb = tipusQueryServiceLocator.getTipusQueryServiceEJB();
            return ejb.getNumNormatives(id, totes);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
