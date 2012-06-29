package es.caib.rolsac.api.v2.afectacio.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.afectacio.ejb.intf.AfectacioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

public class AfectacioQueryServiceDelegate {

    private AfectacioQueryServiceEJBLocator afectacioQueryServiceLocator;
    
    public void setAfectacioQueryServiceLocator(AfectacioQueryServiceEJBLocator afectacioQueryServiceLocator) {
        this.afectacioQueryServiceLocator = afectacioQueryServiceLocator;
    }

    public NormativaDTO obtenirAfectant(long idAfectant) throws DelegateException {
        try {
            AfectacioQueryServiceEJBRemote ejb = afectacioQueryServiceLocator.getAfectacioQueryServiceEJB();
            return ejb.obtenirAfectant(idAfectant);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public NormativaDTO obtenirNormativa(long idNormativa) throws DelegateException {
        AfectacioQueryServiceEJBRemote ejb;
        try {
            ejb = afectacioQueryServiceLocator.getAfectacioQueryServiceEJB();
            return ejb.obtenirNormativa(idNormativa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) throws DelegateException {
        AfectacioQueryServiceEJBRemote ejb;
        try {
            ejb = afectacioQueryServiceLocator.getAfectacioQueryServiceEJB();
            return ejb.obtenirTipusAfectacio(idTipusAfectacio);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
