package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.ejb.intf.IconaFamiliaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceDelegate {
    
    private IconaFamiliaQueryServiceEJBLocator iconaFamiliaQueryServiceLocator;
    
    public void setIconaFamiliaQueryServiceLocator(IconaFamiliaQueryServiceEJBLocator iconaFamiliaQueryServiceLocator) {
        this.iconaFamiliaQueryServiceLocator = iconaFamiliaQueryServiceLocator;
    }

    public FamiliaDTO obtenirFamilia(long id) throws DelegateException {
        try {
            IconaFamiliaQueryServiceEJBRemote ejb = iconaFamiliaQueryServiceLocator.getIconaFamiliaQueryServiceEJB();
            return ejb.obtenirFamilia(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public PerfilDTO obtenirPerfil(long id) throws DelegateException {
        IconaFamiliaQueryServiceEJBRemote ejb;
        try {
            ejb = iconaFamiliaQueryServiceLocator.getIconaFamiliaQueryServiceEJB();
            return ejb.obtenirPerfil(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirIcona(long id) throws DelegateException {
        try {
            IconaFamiliaQueryServiceEJBRemote ejb = iconaFamiliaQueryServiceLocator.getIconaFamiliaQueryServiceEJB();
            return ejb.obtenirIcona(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
