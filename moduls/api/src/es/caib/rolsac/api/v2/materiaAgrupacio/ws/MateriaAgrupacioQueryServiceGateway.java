package es.caib.rolsac.api.v2.materiaAgrupacio.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public class MateriaAgrupacioQueryServiceGateway {

	MateriaAgrupacioWSSoapBindingStub stub;
	
	public MateriaAgrupacioQueryServiceGateway() {

		try {
			stub = new MateriaAgrupacioWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_MATERIA_AGRUPACIO)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
    public MateriaDTO obtenirMateria(Long idMateria) throws RemoteException {
    	return stub.obtenirMateria(idMateria);
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(long idAgrupacion) throws RemoteException {
    	return stub.obtenirAgrupacioMateria(idAgrupacion);
    }
	
}

