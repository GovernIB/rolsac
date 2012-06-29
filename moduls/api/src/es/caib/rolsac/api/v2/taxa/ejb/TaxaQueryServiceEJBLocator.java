package es.caib.rolsac.api.v2.taxa.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.taxa.ejb.intf.TaxaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.taxa.ejb.intf.TaxaQueryServiceEJBRemote;

public class TaxaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "taxa.ejb.TaxaQueryServiceEJB";
    
    public TaxaQueryServiceEJBRemote getTaxaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            TaxaQueryServiceEJBHome home = (TaxaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, TaxaQueryServiceEJBHome.class);
            return (TaxaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), TaxaQueryServiceEJBRemote.class);
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
