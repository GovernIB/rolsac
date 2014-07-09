package es.caib.rolsac.api.v2.fitxa.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.ejb.intf.FitxaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceDelegate {

    private FitxaQueryServiceEJBLocator fitxaQueryServiceLocator;        
    
    public void setFitxaQueryServiceLocator(FitxaQueryServiceEJBLocator fitxaQueryServiceLocator) {
        this.fitxaQueryServiceLocator = fitxaQueryServiceLocator;
    }

    @SuppressWarnings("unchecked")
    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarEnllacos(id, enllacCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarMateries(id, materiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarDocuments(id, documentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarFetsVitals(id, fetVitalCritera);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumDocuments(long id) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.getNumDocuments(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumEnllacos(long id) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.getNumEnllacos(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFetsVitals(long id) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.getNumFetsVitals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.getNumUnitatsAdministratives(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumSeccions(long id) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.getNumSeccions(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumMateries(long id) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.getNumMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarSeccions(id, seccioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FitxaUADTO> llistarFitxesUA(long id, FitxaUACriteria fitxaUACriteria) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarFitxesUA(id, fitxaUACriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirIcona(Long icono) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.obtenirIcona(icono);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirImatge(Long imagen) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.obtenirImatge(imagen);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirBaner(Long baner) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.obtenirBaner(baner);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")    
    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCritera) throws DelegateException {
        try {
            FitxaQueryServiceEJBRemote ejb = fitxaQueryServiceLocator.getFitxaQueryServiceEJB();
            return ejb.llistarPublicsObjectius(id, poCritera);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
