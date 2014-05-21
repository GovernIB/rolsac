package es.caib.rolsac.api.v2.idioma.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.idioma.ejb.intf.IdiomaQueryServiceEJBRemote;

public class IdiomaQueryServiceDelegate {

    private IdiomaQueryServiceEJBLocator idiomaQueryServiceLocator;
    
    public void setIdiomaQueryServiceLocator(IdiomaQueryServiceEJBLocator idiomaQueryServiceLocator) {
        this.idiomaQueryServiceLocator = idiomaQueryServiceLocator;
    }
    
    public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws DelegateException {
    	
    	try {
            
    		IdiomaQueryServiceEJBRemote ejb = idiomaQueryServiceLocator.getIdiomaQueryServceEJB();
            return ejb.llistarIdiomes(idiomaCriteria);
            
        } catch (LocatorException e) {
            
        	throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        
        } catch (RemoteException e) {
        
        	throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        
        }
    	
    }
    
}
