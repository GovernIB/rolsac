package es.caib.rolsac.api.v2.excepcioDocumentacio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.excepcioDocumentacio.ejb.intf.ExcepcioDocumentacioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ejb.intf.ExcepcioDocumentacioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class ExcepcioDocumentacioQueryServiceEJBLocator extends EJBLocator {
	
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "excepcioDocumentacio.ejb.ExcepcioDocumentacioQueryServiceEJB";
    
    public ExcepcioDocumentacioQueryServiceEJBRemote getExcepcioDocumentacioQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            ExcepcioDocumentacioQueryServiceEJBHome home = (ExcepcioDocumentacioQueryServiceEJBHome) PortableRemoteObject.narrow(ref, ExcepcioDocumentacioQueryServiceEJBHome.class);
            return (ExcepcioDocumentacioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), ExcepcioDocumentacioQueryServiceEJBRemote.class);
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
