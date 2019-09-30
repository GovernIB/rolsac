package es.caib.rolsac.api.v2.perfil.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.perfil.ejb.intf.PerfilQueryServiceEJBHome;
import es.caib.rolsac.api.v2.perfil.ejb.intf.PerfilQueryServiceEJBRemote;

public class PerfilQueryServiceEJBLocator extends EJBLocator {

	private static final String JNDI_NAME = JNDI_NAME_PREFIX + "perfil.ejb.PerfilQueryServiceEJB";

	public PerfilQueryServiceEJBRemote getPerfilQueryServiceEJB() throws LocatorException {
		try {
			final Object ref = getRemoteReference(JNDI_NAME);
			final PerfilQueryServiceEJBHome home = (PerfilQueryServiceEJBHome) PortableRemoteObject.narrow(ref,
					PerfilQueryServiceEJBHome.class);
			return (PerfilQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(),
					PerfilQueryServiceEJBRemote.class);
		} catch (final NamingException e) {
			throw new LocatorException(ExceptionMessages.JNDI_NAMING, e);
		} catch (final RemoteException e) {
			throw new LocatorException(ExceptionMessages.JNDI_REMOTE, e);
		} catch (final CreateException e) {
			throw new LocatorException(ExceptionMessages.EJB_CREATE, e);
		} catch (final ClassCastException e) {
			throw new LocatorException(ExceptionMessages.EJB_CLASS_CAST, e);
		}
	}

}