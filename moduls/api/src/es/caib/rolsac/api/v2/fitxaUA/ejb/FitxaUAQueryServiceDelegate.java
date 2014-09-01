package es.caib.rolsac.api.v2.fitxaUA.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.ejb.intf.FitxaUAQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceDelegate {

    private FitxaUAQueryServiceEJBLocator fitxaUAQueryServiceLocator;
    
    public void setFitxaUAQueryServiceLocator(FitxaUAQueryServiceEJBLocator fitxaUAQueryServiceLocator) {
        this.fitxaUAQueryServiceLocator = fitxaUAQueryServiceLocator;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) throws DelegateException {
        try {
            FitxaUAQueryServiceEJBRemote ejb = fitxaUAQueryServiceLocator.getFitxaUAQueryServiceEJB();
            return ejb.obtenirFitxa(idFitxa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public SeccioDTO obtenirSeccio(long idSeccio) throws DelegateException {
        try {
            FitxaUAQueryServiceEJBRemote ejb = fitxaUAQueryServiceLocator.getFitxaUAQueryServiceEJB();
            return ejb.obtenirSeccio(idSeccio);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) throws DelegateException {
        try {
            FitxaUAQueryServiceEJBRemote ejb = fitxaUAQueryServiceLocator.getFitxaUAQueryServiceEJB();
            return ejb.obtenirUnitatAdministrativa(idUnitatAdministrativa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
