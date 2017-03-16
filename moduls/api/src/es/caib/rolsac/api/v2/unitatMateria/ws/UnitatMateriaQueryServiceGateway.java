package es.caib.rolsac.api.v2.unitatMateria.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class UnitatMateriaQueryServiceGateway {

	UnitatMateriaWSSoapBindingStub stub;

	public UnitatMateriaQueryServiceGateway() {

		try {
			stub = new UnitatMateriaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_UNITAT_MATERIA)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public void setUrl(String url) {
		try {
			stub = new UnitatMateriaWSSoapBindingStub(
	 				new URL(url + ConfiguracioServeis.NOM_SERVEI_UNITAT_MATERIA),
	 				null
	 			);
		} catch (RemoteException re) {
			re.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} 
	}
	
	
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) throws RemoteException {
    	return stub.obtenirUnitatAdministrativa(idUnitat);
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws RemoteException {
    	return stub.obtenirMateria(idMateria);
    }

	
}