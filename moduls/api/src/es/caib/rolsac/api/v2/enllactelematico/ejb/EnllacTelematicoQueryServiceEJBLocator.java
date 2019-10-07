package es.caib.rolsac.api.v2.enllactelematico.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.enllactelematico.ejb.intf.EnllacTelematicoQueryServiceEJBHome;
import es.caib.rolsac.api.v2.enllactelematico.ejb.intf.EnllacTelematicoQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class EnllacTelematicoQueryServiceEJBLocator extends EJBLocator {

	private static final String JNDI_NAME = JNDI_NAME_PREFIX + "enllactelematico.ejb.EnllacTelematicoQueryServiceEJB";

	public EnllacTelematicoQueryServiceEJBRemote getEnllacTelematicoQueryServiceEJB() throws LocatorException {
		try {
			final Object ref = getRemoteReference(JNDI_NAME);
			final EnllacTelematicoQueryServiceEJBHome home = (EnllacTelematicoQueryServiceEJBHome) PortableRemoteObject
					.narrow(ref, EnllacTelematicoQueryServiceEJBHome.class);
			return (EnllacTelematicoQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(),
					EnllacTelematicoQueryServiceEJBRemote.class);
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
