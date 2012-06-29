package es.caib.rolsac.api.v2.iconaMateria.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.iconaMateria.ejb.intf.IconaMateriaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceDelegate {
    
    private IconaMateriaQueryServiceEJBLocator iconaMateriaQueryServiceLocator;
    
    public void setIconaMateriaQueryServiceLocator(IconaMateriaQueryServiceEJBLocator iconaMateriaQueryServiceLocator) {
        this.iconaMateriaQueryServiceLocator = iconaMateriaQueryServiceLocator;
    }

    public MateriaDTO obtenirMateria(long id) throws DelegateException {
        try {
            IconaMateriaQueryServiceEJBRemote ejb = iconaMateriaQueryServiceLocator.getIconaMateriaQueryServiceEJB();
            return ejb.obtenirMateria(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public PerfilDTO obtenirPerfil(long id) throws DelegateException {
        try {
            IconaMateriaQueryServiceEJBRemote ejb = iconaMateriaQueryServiceLocator.getIconaMateriaQueryServiceEJB();
            return ejb.obtenirPerfil(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirIcona(long id) throws DelegateException {
        try {
            IconaMateriaQueryServiceEJBRemote ejb = iconaMateriaQueryServiceLocator.getIconaMateriaQueryServiceEJB();
            return ejb.obtenirIcona(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
