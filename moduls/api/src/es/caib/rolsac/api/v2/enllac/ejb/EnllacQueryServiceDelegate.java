package es.caib.rolsac.api.v2.enllac.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.enllac.ejb.intf.EnllacQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;


public class EnllacQueryServiceDelegate {

    private EnllacQueryServiceEJBLocator  enllacQueryServiceLocator;

    public void setEnllacQueryServiceLocator(EnllacQueryServiceEJBLocator enllacQueryServiceLocator) {
        this.enllacQueryServiceLocator = enllacQueryServiceLocator;
    }

    public ProcedimentDTO obtenirProcediment(long idProcediment) throws DelegateException {
        try {
            EnllacQueryServiceEJBRemote ejb = enllacQueryServiceLocator.getEnllacQueryServiceEJB();
            return ejb.obtenirProcediment(idProcediment);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public FitxaDTO obtenirFitxa(long idFitxa) throws DelegateException {
        try {
        	EnllacQueryServiceEJBRemote ejb = enllacQueryServiceLocator.getEnllacQueryServiceEJB();
            return ejb.obtenirFitxa(idFitxa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }



}
