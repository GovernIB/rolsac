package es.caib.rolsac.api.v2.iconaFamilia.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceGateway {

	IconaFamiliaWSSoapBindingStub stub;
	
	public IconaFamiliaQueryServiceGateway() {

		try {
			
			stub = new IconaFamiliaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_ICONA_FAMILIA)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
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
