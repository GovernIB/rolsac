package es.caib.rolsac.api.v2.iconaFamilia.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceGateway {

	IconaFamiliaWSSoapBindingStub stub;
	IconaFamiliaQueryServiceEJBRemoteServiceLocator locator;
	
	public IconaFamiliaQueryServiceGateway() {

		try {
			
			locator = new IconaFamiliaQueryServiceEJBRemoteServiceLocator();

			stub = new IconaFamiliaWSSoapBindingStub(new URL(
					locator.getIconaFamiliaWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public FamiliaDTO obtenirFamilia(long id) throws RemoteException {
    	return stub.obtenirFamilia(id);
    }

    public PerfilDTO obtenirPerfil(long id) throws RemoteException {
    	return stub.obtenirPerfil(id);
    }

    public ArxiuDTO obtenirIcona(long id) throws RemoteException {
    	return stub.obtenirIcona(id);
    }

}
