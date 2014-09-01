package es.caib.rolsac.api.v2.perfil.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.ejb.intf.PerfilQueryServiceEJBRemote;


public class PerfilQueryServiceDelegate {
    
    private PerfilQueryServiceEJBLocator perfilQueryServiceLocator;

    public void setPerfilQueryServiceLocator(PerfilQueryServiceEJBLocator perfilQueryServiceLocator) {
        this.perfilQueryServiceLocator = perfilQueryServiceLocator;
    }
    
    @SuppressWarnings("unchecked")
    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) throws DelegateException { 
        try {
            PerfilQueryServiceEJBRemote ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
            return ejb.llistarIconesFamilia(id, iconaFamiliaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) throws DelegateException {
        try {
            PerfilQueryServiceEJBRemote ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
            return ejb.llistarIconesMateria(id, iconaMateriaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumIconesFamilia(long id) throws DelegateException {
        try {
            PerfilQueryServiceEJBRemote ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
            return ejb.getNumIconesFamilia(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumIconesMateria(long id) throws DelegateException {
        try {
            PerfilQueryServiceEJBRemote ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
            return ejb.getNumIconesMateria(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
