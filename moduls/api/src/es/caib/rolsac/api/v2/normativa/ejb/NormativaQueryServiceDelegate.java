package es.caib.rolsac.api.v2.normativa.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.ejb.intf.NormativaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceDelegate {

    private NormativaQueryServiceEJBLocator normativaQueryServiceLocator;
    
    public void setNormativaQueryServiceLocator(NormativaQueryServiceEJBLocator normativaQueryServiceLocator) {
        this.normativaQueryServiceLocator = normativaQueryServiceLocator;
    }

    public ButlletiDTO obtenirButlleti(long idButlleti) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.obtenirButlleti(idButlleti);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }      
    }

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarAfectades(long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarAfectades(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumAfectades(long id) throws DelegateException {
        try  {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.getNumAfectades(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarProcediments(id, procedimentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarServicios(id, servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarAfectants(long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarAfectants(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumAfectants(long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.getNumAfectants(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumProcediments(long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.getNumProcediments(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumServicios(long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.getNumServicios(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.obtenirUnitatAdministrativa(idUniAdm);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    
    public List<DocumentoNormativaDTO> llistarDocumentoNormativa(DocumentoNormativaCriteria documentoNormativaCriteria)  throws DelegateException {
    	try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarDocumentoNormativa(documentoNormativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    
    }
    @SuppressWarnings("unchecked")
    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarAfectacionsAfectants(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) throws DelegateException {
        try {
            NormativaQueryServiceEJBRemote ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
            return ejb.llistarAfectacionsAfectades(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
