package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.publicObjectiu.ejb.intf.PublicObjectiuQueryServiceEJBRemote;

public class PublicObjectiuQueryServiceDelegate {

    private PublicObjectiuQueryServiceEJBLocator publicObjectiuQueryServiceLocator;

    public void setPublicObjectiuQueryServiceLocator(PublicObjectiuQueryServiceEJBLocator publicObjectiuQueryServiceLocator) {
        this.publicObjectiuQueryServiceLocator = publicObjectiuQueryServiceLocator;
    }
    
    public int getNumAgrupacions(long id) throws DelegateException {
        try {
            PublicObjectiuQueryServiceEJBRemote ejb = publicObjectiuQueryServiceLocator.getPublicObjectiuQueryServceEJB();
            return ejb.getNumAgrupacions(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id, AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) throws DelegateException {
        try {
            PublicObjectiuQueryServiceEJBRemote ejb = publicObjectiuQueryServiceLocator.getPublicObjectiuQueryServceEJB();
            return ejb.llistarAgrupacions(id, agurpacioFetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
