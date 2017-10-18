package es.caib.rolsac.api.v2.fetVital.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fetVital.ejb.intf.FetVitalQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;

public class FetVitalQueryServiceDelegate {

    private FetVitalQueryServiceEJBLocator fetVitalQueryServiceLocator;
    
    public void setFetVitalQueryServiceLocator(FetVitalQueryServiceEJBLocator fetVitalQueryServiceLocator) {
        this.fetVitalQueryServiceLocator = fetVitalQueryServiceLocator;
    }

    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.llistarFitxes(id, fitxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
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
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.llistarServicios(id, servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
       
    @SuppressWarnings("unchecked")
    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public ArxiuDTO getDistribuciCompetencial(long idDistribuciCompetencial) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getDistribuciCompetencial(idDistribuciCompetencial);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO getNormativa(long idNormativa) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getNormativa(idNormativa);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO getContingut(long idContingut) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getContingut(idContingut);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFitxes(long id) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getNumFitxes(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumProcedimentsLocals(long id) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getNumProcedimentsLocals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumServicios(long id) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getNumServicios(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFetsVitalsAgrupacionsFV(long id) throws DelegateException {
        try {
            FetVitalQueryServiceEJBRemote ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
            return ejb.getNumFetsVitalsAgrupacionsFV(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
