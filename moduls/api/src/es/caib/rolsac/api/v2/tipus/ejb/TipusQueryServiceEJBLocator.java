package es.caib.rolsac.api.v2.tipus.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.tipus.ejb.intf.TipusQueryServiceEJBHome;
import es.caib.rolsac.api.v2.tipus.ejb.intf.TipusQueryServiceEJBRemote;

public class TipusQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "tipus.ejb.TipusQueryServiceEJB";
    
    public TipusQueryServiceEJBRemote getTipusQueryServiceEJB() throws LocatorException{
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            TipusQueryServiceEJBHome home = (TipusQueryServiceEJBHome) PortableRemoteObject.narrow(ref, TipusQueryServiceEJBHome.class);
            return (TipusQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), TipusQueryServiceEJBRemote.class);
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
