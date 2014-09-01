package es.caib.rolsac.api.v2.procediment.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.procediment.ejb.intf.ProcedimentQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class ProcedimentQueryServiceDelegate {

    private ProcedimentQueryServiceEJBLocator procedimentQueryServiceLocator;
    
    public void setProcedimentQueryServiceLocator(ProcedimentQueryServiceEJBLocator procedimentQueryServiceLocator) {
        this.procedimentQueryServiceLocator = procedimentQueryServiceLocator;
    }
 
    public int getNumTramits(long id) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.getNumTramits(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote  ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.getNumNormatives(id, tipus.ordinal());
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumMateries(long id) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote  ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.getNumMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumDocuments(long id) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.getNumDocuments(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFetsVitals(long id) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.getNumFetsVitals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.llistarTramits(id, tramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.llistarNormatives(id, normativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.llistarMateries(id, materiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
 
    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.llistarFetsVitals(id, fetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.llistarDocuments(id, documentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws DelegateException {
        try {
            ProcedimentQueryServiceEJBRemote ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
            return ejb.llistarPublicsObjectius(id, poCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
