package es.caib.rolsac.api.v2.seccio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.ejb.intf.SeccioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;


public class SeccioQueryServiceDelegate {
    
    private SeccioQueryServiceEJBLocator  seccioQueryServiceLocator;

    public void setSeccioQueryServiceLocator(SeccioQueryServiceEJBLocator seccioQueryServiceLocator) {
        this.seccioQueryServiceLocator = seccioQueryServiceLocator;
    }

    public int getNumFilles(long id) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.getNumFilles(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFitxes(long id) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.getNumFitxes(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumPares(long id) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.getNumPares(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.getNumUnitatsAdministratives(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarPares(long id) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.llistarPares(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.llistarFilles(id, seccioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.llistarFitxes(id, fitxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public SeccioDTO obtenirPare(Long padre) throws DelegateException {
        try {
            SeccioQueryServiceEJBRemote ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
            return ejb.obtenirPare(padre);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
