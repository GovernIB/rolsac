package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.ejb.intf.EspaiTerritorialQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceDelegate {
    
    private EspaiTerritorialQueryServiceEJBLocator espaiTerritorialQueryServiceLocator;

    public void setEspaiTerritorialQueryServiceLocator(EspaiTerritorialQueryServiceEJBLocator espaiTerritorialQueryServiceLocator) {
        this.espaiTerritorialQueryServiceLocator = espaiTerritorialQueryServiceLocator;
    }

    public int getNumFills(long id) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.getNumFills(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.getNumUnitatsAdministratives(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.llistarFills(id, espaiTerritorialCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public EspaiTerritorialDTO obtenirPare(Long idPadre) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.obtenirPare(idPadre);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirMapa(Long idMapa) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.obtenirMapa(idMapa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirLogo(Long idLogo) throws DelegateException {
        try {
            EspaiTerritorialQueryServiceEJBRemote ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
            return ejb.obtenirLogo(idLogo);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
