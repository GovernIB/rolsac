package es.caib.rolsac.api.v2.personal.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.personal.ejb.intf.PersonalQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceDelegate {
    
    private PersonalQueryServiceEJBLocator personalQueryServiceLocator;

    public void setPersonalQueryServiceLocator(PersonalQueryServiceEJBLocator personalQueryServiceLocator) {
        this.personalQueryServiceLocator = personalQueryServiceLocator;
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) throws DelegateException {
        try {
            PersonalQueryServiceEJBRemote ejb = personalQueryServiceLocator.getPersonalQueryServiceEJB();
            return ejb.obtenirUnitatAdministrativa(idUA);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
