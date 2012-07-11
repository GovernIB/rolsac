package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.materiaAgrupacio.ejb.intf.MateriaAgrupacioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.materiaAgrupacio.ejb.intf.MateriaAgrupacioQueryServiceEJBRemote;

public class MateriaAgrupacioQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "materiaAgrupacio.ejb.MateriaAgrupacioQueryServiceEJB";
    
    public MateriaAgrupacioQueryServiceEJBRemote getMateriaAgrupacioQueryServiceEJB() throws LocatorException{
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            MateriaAgrupacioQueryServiceEJBHome home = (MateriaAgrupacioQueryServiceEJBHome) PortableRemoteObject.narrow(ref, MateriaAgrupacioQueryServiceEJBHome.class);
            return (MateriaAgrupacioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), MateriaAgrupacioQueryServiceEJBRemote.class);
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