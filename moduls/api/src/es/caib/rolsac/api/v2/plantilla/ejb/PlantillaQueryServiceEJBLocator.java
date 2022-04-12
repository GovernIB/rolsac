package es.caib.rolsac.api.v2.plantilla.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.plantilla.ejb.intf.PlantillaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.plantilla.ejb.intf.PlantillaQueryServiceEJBRemote;

public class PlantillaQueryServiceEJBLocator extends EJBLocator {

	private static final String JNDI_NAME = JNDI_NAME_PREFIX + "plantilla.ejb.PlantillaQueryServiceEJB";

	public PlantillaQueryServiceEJBRemote getPlantillaQueryServiceEJB() throws LocatorException {
		try {
			final Object ref = getRemoteReference(JNDI_NAME);
			final PlantillaQueryServiceEJBHome home = (PlantillaQueryServiceEJBHome) PortableRemoteObject.narrow(ref,
					PlantillaQueryServiceEJBHome.class);
			return (PlantillaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(),
					PlantillaQueryServiceEJBRemote.class);
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
