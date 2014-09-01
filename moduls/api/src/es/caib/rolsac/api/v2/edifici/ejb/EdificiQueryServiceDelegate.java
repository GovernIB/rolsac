package es.caib.rolsac.api.v2.edifici.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.ejb.intf.EdificiQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceDelegate {

    private EdificiQueryServiceEJBLocator edificiQueryServiceLocator;
    
    public void setEdificiQueryServiceLocator(EdificiQueryServiceEJBLocator edificiQueryServiceLocator) {
        this.edificiQueryServiceLocator = edificiQueryServiceLocator;
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            EdificiQueryServiceEJBRemote ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws DelegateException {
        try {
            EdificiQueryServiceEJBRemote ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
            return ejb.getNumUnitatsAdministratives(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) throws DelegateException {
        try {
            EdificiQueryServiceEJBRemote ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
            return ejb.obtenirFotoPequenya(idFotoPequenya);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) throws DelegateException {
        try {
            EdificiQueryServiceEJBRemote ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
            return ejb.obtenirFotoGrande(idFotoGrande);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirPlano(Long idPlano) throws DelegateException {
        try {
            EdificiQueryServiceEJBRemote ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
            return ejb.obtenirPlano(idPlano);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
