package es.caib.rolsac.api.v2.formulari.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.formulari.ejb.intf.FormulariQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceDelegate {
    
    private FormulariQueryServiceEJBLocator formulariQueryServiceLocator;

    public void setFormulariQueryServiceLocator(FormulariQueryServiceEJBLocator formulariQueryServiceLocator) {
        this.formulariQueryServiceLocator = formulariQueryServiceLocator;
    }

    public ArxiuDTO obtenirArchivo(Long idArchivo) throws DelegateException {
        try {
            FormulariQueryServiceEJBRemote ejb = formulariQueryServiceLocator.getFormulariQueryServiceEJB();
            return ejb.obtenirArchivo(idArchivo);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirManual(Long idmanual) throws DelegateException {
        try {
            FormulariQueryServiceEJBRemote ejb = formulariQueryServiceLocator.getFormulariQueryServiceEJB();
            return ejb.obtenirManual(idmanual);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public TramitDTO obtenirTramit(Long idtramit) throws DelegateException {
        try {
            FormulariQueryServiceEJBRemote ejb = formulariQueryServiceLocator.getFormulariQueryServiceEJB();
            return ejb.obtenirTramit(idtramit);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
