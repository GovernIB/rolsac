package es.caib.rolsac.api.v2.taxa.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.taxa.ejb.intf.TaxaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceDelegate {

    private TaxaQueryServiceEJBLocator taxaQueryServiceLocator;
    
    public void setTaxaQueryServiceLocator(TaxaQueryServiceEJBLocator taxaQueryServiceLocator) {
        this.taxaQueryServiceLocator = taxaQueryServiceLocator;
    }
        
    public TramitDTO obtenirTramit(long idTramit) throws DelegateException {
        try {
            TaxaQueryServiceEJBRemote ejb = taxaQueryServiceLocator.getTaxaQueryServiceEJB();
            return ejb.obtenirTramit(idTramit);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }


}
