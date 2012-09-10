package es.caib.rolsac.api.v2.unitatMateria.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class UnitatMateriaQueryServiceGateway {

	UnitatMateriaWSSoapBindingStub stub;
	UnitatMateriaQueryServiceEJBRemoteServiceLocator locator;
	
	public UnitatMateriaQueryServiceGateway() {

		try {
			
			locator = new UnitatMateriaQueryServiceEJBRemoteServiceLocator();

			stub = new UnitatMateriaWSSoapBindingStub(new URL(
					locator.getUnitatMateriaWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) throws RemoteException {
    	return stub.obtenirUnitatAdministrativa(idUnitat);
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws RemoteException {
    	return stub.obtenirMateria(idMateria);
    }
}