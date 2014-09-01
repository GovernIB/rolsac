package es.caib.rolsac.api.v2.unitatMateria.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.ejb.intf.UnitatMateriaQueryServiceEJBRemote;

public class UnitatMateriaQueryServiceDelegate {
    
    private UnitatMateriaQueryServiceEJBLocator unitatMateriaQueryServiceLocator;

    public void setUnitatMateriaQueryServiceLocator(UnitatMateriaQueryServiceEJBLocator unitatMateriaQueryServiceLocator) {
        this.unitatMateriaQueryServiceLocator = unitatMateriaQueryServiceLocator;
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws DelegateException {
        try {
            UnitatMateriaQueryServiceEJBRemote ejb = unitatMateriaQueryServiceLocator.getUnitatMateriaQueryServiceEJB();
            return ejb.obtenirMateria(idMateria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) throws DelegateException {
        try {
            UnitatMateriaQueryServiceEJBRemote ejb = unitatMateriaQueryServiceLocator.getUnitatMateriaQueryServiceEJB();
            return ejb.obtenirUnitatAdministrativa( idUnitat);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    

}
