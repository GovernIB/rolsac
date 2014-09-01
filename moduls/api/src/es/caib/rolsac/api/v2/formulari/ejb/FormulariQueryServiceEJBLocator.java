package es.caib.rolsac.api.v2.formulari.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.formulari.ejb.intf.FormulariQueryServiceEJBHome;
import es.caib.rolsac.api.v2.formulari.ejb.intf.FormulariQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class FormulariQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "formulari.ejb.FormulariQueryServiceEJB";
    
    public FormulariQueryServiceEJBRemote getFormulariQueryServiceEJB() throws LocatorException{
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            FormulariQueryServiceEJBHome home = (FormulariQueryServiceEJBHome) PortableRemoteObject.narrow(ref, FormulariQueryServiceEJBHome.class);
            return (FormulariQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), FormulariQueryServiceEJBRemote.class);
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
