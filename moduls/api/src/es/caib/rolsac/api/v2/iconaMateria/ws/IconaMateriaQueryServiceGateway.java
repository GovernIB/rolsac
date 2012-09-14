package es.caib.rolsac.api.v2.iconaMateria.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceGateway {
	
	IconaMateriaWSSoapBindingStub stub;
	
	public IconaMateriaQueryServiceGateway() {

		try {
			stub = new IconaMateriaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_ICONA_MATERIA)),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

    public MateriaDTO obtenirMateria(long id) throws RemoteException {
    	return stub.obtenirMateria(id);
    }

    public PerfilDTO obtenirPerfil(long id) throws RemoteException {
    	return stub.obtenirPerfil(id);
    }

    public ArxiuDTO obtenirIcona(long id) throws RemoteException {
    	return stub.obtenirIcona(id);
    }	

}
