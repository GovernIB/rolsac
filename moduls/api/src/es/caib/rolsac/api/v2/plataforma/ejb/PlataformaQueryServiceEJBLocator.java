package es.caib.rolsac.api.v2.plataforma.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.plataforma.ejb.intf.PlataformaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.plataforma.ejb.intf.PlataformaQueryServiceEJBRemote;

public class PlataformaQueryServiceEJBLocator extends EJBLocator {

	private static final String JNDI_NAME = JNDI_NAME_PREFIX + "plataforma.ejb.PlataformaQueryServiceEJB";

	public PlataformaQueryServiceEJBRemote getPlataformaQueryServiceEJB() throws LocatorException {
		try {
			final Object ref = getRemoteReference(JNDI_NAME);
			final PlataformaQueryServiceEJBHome home = (PlataformaQueryServiceEJBHome) PortableRemoteObject.narrow(ref,
					PlataformaQueryServiceEJBHome.class);
			return (PlataformaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(),
					PlataformaQueryServiceEJBRemote.class);
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
