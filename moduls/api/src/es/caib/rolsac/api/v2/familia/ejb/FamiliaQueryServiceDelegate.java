package es.caib.rolsac.api.v2.familia.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.familia.ejb.intf.FamiliaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;

public class FamiliaQueryServiceDelegate {

    private FamiliaQueryServiceEJBLocator familiaQueryServiceLocator;

    public void setFamiliaQueryServiceLocator(FamiliaQueryServiceEJBLocator familiaQueryServiceLocator) {
        this.familiaQueryServiceLocator = familiaQueryServiceLocator;
    }
    
    public int getNumProcedimentsLocals(long id) throws DelegateException {
        try {
            FamiliaQueryServiceEJBRemote ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
            return ejb.getNumProcedimentsLocals(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumServicios(long id) throws DelegateException {
        try {
            FamiliaQueryServiceEJBRemote ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
            return ejb.getNumServicios(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            FamiliaQueryServiceEJBRemote ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
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
            FamiliaQueryServiceEJBRemote ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
            return ejb.llistarServicios(id, servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumIcones(long id) throws DelegateException {
        try {
            FamiliaQueryServiceEJBRemote ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
            return ejb.getNumIcones(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) throws DelegateException {
        try {
            FamiliaQueryServiceEJBRemote ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
            return ejb.llistarIcones(id, iconaFamiliaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
