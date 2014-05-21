package es.caib.rolsac.api.v2.idioma.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.idioma.ejb.intf.IdiomaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.idioma.ejb.intf.IdiomaQueryServiceEJBRemote;

public class IdiomaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "idioma.ejb.IdiomaQueryServiceEJB";
    
    public IdiomaQueryServiceEJBRemote getIdiomaQueryServceEJB() throws LocatorException {
    	
        try {
        	
            Object ref = getRemoteReference(JNDI_NAME);
            IdiomaQueryServiceEJBHome home = (IdiomaQueryServiceEJBHome)PortableRemoteObject.narrow(ref, IdiomaQueryServiceEJBHome.class);
            return (IdiomaQueryServiceEJBRemote)PortableRemoteObject.narrow(home.create(), IdiomaQueryServiceEJBRemote.class);
            
        } catch (NamingException e) {
        	
            throw new LocatorException(ExceptionMessages.JNDI_NAMING, e);
            
        } catch (RemoteException e) {
        	
            throw new LocatorException(ExceptionMessages.JNDI_REMOTE, e);
            
        } catch (CreateException e) {
        	
            throw new LocatorException(ExceptionMessages.EJB_CREATE, e);
            
        } catch (ClassCastException e) {
        	
            throw new LocatorException(ExceptionMessages.EJB_CLASS_CAST, e);
            
        }
        
    }

}
