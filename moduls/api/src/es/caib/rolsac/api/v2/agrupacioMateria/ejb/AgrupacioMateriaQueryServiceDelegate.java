package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.ejb.intf.AgrupacioMateriaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceDelegate {

    private AgrupacioMateriaQueryServiceEJBLocator agrupacioMateriaQueryServiceLocator;
    
    public void setAgrupacioMateriaQueryServiceLocator(AgrupacioMateriaQueryServiceEJBLocator agrupacioMateriaQueryServiceLocator) {
        this.agrupacioMateriaQueryServiceLocator = agrupacioMateriaQueryServiceLocator;
    }

    public SeccioDTO obtenirSeccio(long idSeccio) throws DelegateException {
        try {
            AgrupacioMateriaQueryServiceEJBRemote ejb = agrupacioMateriaQueryServiceLocator.getAgrupacioMateriaQueryServiceEJB();
            return ejb.obtenirSeccio(idSeccio);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws DelegateException {
        try {
            AgrupacioMateriaQueryServiceEJBRemote ejb = agrupacioMateriaQueryServiceLocator.getAgrupacioMateriaQueryServiceEJB();
            return ejb.llistarMateries(id, materiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumMateries(long id) throws DelegateException {
        try {
            AgrupacioMateriaQueryServiceEJBRemote ejb = agrupacioMateriaQueryServiceLocator.getAgrupacioMateriaQueryServiceEJB();
            return ejb.getNumMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
