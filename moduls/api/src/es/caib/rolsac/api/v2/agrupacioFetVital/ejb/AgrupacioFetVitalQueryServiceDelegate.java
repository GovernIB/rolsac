package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.ejb.intf.AgrupacioFetVitalQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;


public class AgrupacioFetVitalQueryServiceDelegate {
    
    private AgrupacioFetVitalQueryServiceEJBLocator agrupacioFetVitalQueryServiceLocator;
    
    public void setAgrupacioFetVitalQueryServiceLocator(AgrupacioFetVitalQueryServiceEJBLocator agrupacioFetVitalQueryServiceLocator) {
        this.agrupacioFetVitalQueryServiceLocator = agrupacioFetVitalQueryServiceLocator;
    }

    public ArxiuDTO getFotografia(long idFoto) throws DelegateException {
        try {
            AgrupacioFetVitalQueryServiceEJBRemote ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
            return ejb.getFotografia(idFoto);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO getIcona(long idIcona) throws DelegateException {
        try {
            AgrupacioFetVitalQueryServiceEJBRemote ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
            return ejb.getIcona(idIcona);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO getIconaGran(long idIconaGran) throws DelegateException {
        try {
            AgrupacioFetVitalQueryServiceEJBRemote ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
            return ejb.getIconaGran(idIconaGran);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) throws DelegateException {
        try {
            AgrupacioFetVitalQueryServiceEJBRemote ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
            return ejb.obtenirPublicObjectiu(idPublic);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumFetsVitals(long id) throws DelegateException {
        try {
            AgrupacioFetVitalQueryServiceEJBRemote ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
            return ejb.getNumFetsVitals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) throws DelegateException {
        try {
            AgrupacioFetVitalQueryServiceEJBRemote ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
            return ejb.llistarFetsVitals(id, fetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
