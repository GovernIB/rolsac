package es.caib.rolsac.api.v2.servicio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
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
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.servicio.ejb.intf.ServicioQueryServiceEJBRemote;

public class ServicioQueryServiceDelegate {

    private ServicioQueryServiceEJBLocator servicioQueryServiceLocator;
    
    public void setServicioQueryServiceLocator(ServicioQueryServiceEJBLocator servicioQueryServiceLocator) {
        this.servicioQueryServiceLocator = servicioQueryServiceLocator;
    }
 
    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote  ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.getNumNormatives(id, tipus.ordinal());
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumMateries(long id) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote  ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.getNumMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumDocuments(long id) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.getNumDocuments(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFetsVitals(long id) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.getNumFetsVitals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
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
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
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
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.llistarFetsVitals(id, fetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<DocumentoServicioDTO> llistarDocuments(long id, DocumentoServicioCriteria documentoServicioCriteria) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.llistarDocuments(id, documentoServicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws DelegateException {
        try {
            ServicioQueryServiceEJBRemote ejb = servicioQueryServiceLocator.getServicioQueryServiceEJB();
            return ejb.llistarPublicsObjectius(id, poCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
