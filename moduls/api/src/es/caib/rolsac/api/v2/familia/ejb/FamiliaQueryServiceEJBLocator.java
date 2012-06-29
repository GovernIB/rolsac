package es.caib.rolsac.api.v2.familia.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.familia.ejb.intf.FamiliaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.familia.ejb.intf.FamiliaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class FamiliaQueryServiceEJBLocator  extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "familia.ejb.FamiliaQueryServiceEJB";
    
    public FamiliaQueryServiceEJBRemote getFamiliaQueryServceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            FamiliaQueryServiceEJBHome home = (FamiliaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, FamiliaQueryServiceEJBHome.class);
            return (FamiliaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), FamiliaQueryServiceEJBRemote.class);
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
