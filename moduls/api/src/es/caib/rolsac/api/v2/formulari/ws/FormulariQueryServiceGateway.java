package es.caib.rolsac.api.v2.formulari.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceGateway {

	FormulariWSSoapBindingStub stub;
	
	public FormulariQueryServiceGateway() {

		try {
			stub = new FormulariWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_FORMULARI)),
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
	
	public ArxiuDTO obtenirArchivo(Long archivo) throws RemoteException {
		return stub.obtenirArchivo(archivo);
	}

	public ArxiuDTO obtenirManual(Long manual) throws RemoteException {
		return stub.obtenirManual(manual);
	}

	public TramitDTO obtenirTramit(Long idTramit) throws RemoteException {
		return stub.obtenirTramit(idTramit);
	}
}
