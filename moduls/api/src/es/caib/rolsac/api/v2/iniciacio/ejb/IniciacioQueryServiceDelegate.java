package es.caib.rolsac.api.v2.iniciacio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.iniciacio.ejb.intf.IniciacioQueryServiceEJBRemote;

public class IniciacioQueryServiceDelegate {
	
	private IniciacioQueryServiceEJBLocator iniciacioQueryServiceLocator;

    public void setIniciacioQueryServiceLocator(IniciacioQueryServiceEJBLocator iniciacioQueryServiceLocator) {
        this.iniciacioQueryServiceLocator = iniciacioQueryServiceLocator;
    }
    
    public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws DelegateException {
    	
        try {
        	
            IniciacioQueryServiceEJBRemote ejb = iniciacioQueryServiceLocator.getIniciacioQueryServiceEJB();
            return ejb.obtenirTipusIniciacio(iniciacioCriteria);
            
        } catch (LocatorException e) {
        	
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
            
        } catch (RemoteException e) {
        	
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
            
        }
        
    }

    @SuppressWarnings("unchecked")
    public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws DelegateException {
    	
        try {
        	
            IniciacioQueryServiceEJBRemote ejb = iniciacioQueryServiceLocator.getIniciacioQueryServiceEJB();
            return ejb.llistarTipusIniciacions(iniciacioCriteria);
            
        } catch (LocatorException e) {
        	
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
            
        } catch (RemoteException e) {
        	
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
            
        }
        
    }

}
