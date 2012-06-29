package es.caib.rolsac.api.v2.usuari.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.ejb.intf.UsuariQueryServiceEJBRemote;

public class UsuariQueryServiceDelegate {

    private UsuariQueryServiceEJBLocator usuariQueryServiceLocator;

    public void setUsuariQueryServiceLocator(UsuariQueryServiceEJBLocator usuariQueryServiceLocator) {
        this.usuariQueryServiceLocator = usuariQueryServiceLocator;
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            UsuariQueryServiceEJBRemote ejb = usuariQueryServiceLocator.getUsuariQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws DelegateException {
        try {
            UsuariQueryServiceEJBRemote ejb = usuariQueryServiceLocator.getUsuariQueryServiceEJB();
            return ejb.getNumUnitatsAdministratives(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
