package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.ejb.intf.MateriaAgrupacioQueryServiceEJBRemote;

public class MateriaAgrupacioQueryServiceDelegate {
    
    private MateriaAgrupacioQueryServiceEJBLocator materiaAgrupacioQueryServiceLocator;

    public void setMateriaAgrupacioQueryServiceLocator(MateriaAgrupacioQueryServiceEJBLocator materiaAgrupacioQueryServiceLocator) {
        this.materiaAgrupacioQueryServiceLocator = materiaAgrupacioQueryServiceLocator;
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws DelegateException {        
        try {
            MateriaAgrupacioQueryServiceEJBRemote ejb = materiaAgrupacioQueryServiceLocator.getMateriaAgrupacioQueryServiceEJB();
            return ejb.obtenirMateria(idMateria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) throws DelegateException {
        try {
            MateriaAgrupacioQueryServiceEJBRemote ejb = materiaAgrupacioQueryServiceLocator.getMateriaAgrupacioQueryServiceEJB();
            return ejb.obtenirAgrupacioMateria(idAgrupacion);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
