package es.caib.rolsac.api.v2.silencio.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
import es.caib.rolsac.api.v2.silencio.ejb.intf.SilencioQueryServiceEJBRemote;

public class SilencioQueryServiceDelegate {
	
	private SilencioQueryServiceEJBLocator silencioQueryServiceLocator;

    public void setSilencioQueryServiceLocator(SilencioQueryServiceEJBLocator silencioQueryServiceLocator) {
        this.silencioQueryServiceLocator = silencioQueryServiceLocator;
    }
    
    public SilencioDTO obtenirSilenci(String codSilencio, String idioma) throws DelegateException {
    	
        try {
        	
            SilencioQueryServiceEJBRemote ejb = silencioQueryServiceLocator.getSilencioQueryServiceEJB();
            return ejb.obtenirSilenci(codSilencio, idioma);
            
        } catch (LocatorException e) {
        	
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
            
        } catch (RemoteException e) {
        	
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
            
        }
        
    }


}
