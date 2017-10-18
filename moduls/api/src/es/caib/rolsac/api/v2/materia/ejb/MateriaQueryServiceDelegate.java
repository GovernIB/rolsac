package es.caib.rolsac.api.v2.materia.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.ejb.intf.MateriaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQueryServiceDelegate {

    private MateriaQueryServiceEJBLocator materiaQueryServiceLocator;
    
    public void setMateriaQueryServiceLocator(MateriaQueryServiceEJBLocator materiaQueryServiceLocator) {
        this.materiaQueryServiceLocator = materiaQueryServiceLocator;
    }

    public ArxiuDTO getFotografia(long idFoto) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getFotografia(idFoto);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO getIcona(long idIcona) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getIcona(idIcona);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO getIconaGran(long idIconaGran) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getIconaGran(idIconaGran);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
   
    public int getNumFitxes(long id) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getNumFitxes(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumAgrupacioMateries(long id) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getNumAgrupacioMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumProcedimentsLocals(long id) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getNumProcedimentsLocals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumServicios(long id) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getNumServicios(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }


    public int getNumUnitatsMateries(long id) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getNumUnitatsMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumIcones(long id) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.getNumIcones(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarProcedimentsLocals(id, procedimentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarServicios(id, servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarFitxes(id, fitxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarUnitatsMateria(id, unitatMateriaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarIconesMateries(id, iconaMateriaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            MateriaQueryServiceEJBRemote ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
