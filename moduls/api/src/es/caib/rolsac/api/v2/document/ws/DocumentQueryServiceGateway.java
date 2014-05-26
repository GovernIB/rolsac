package es.caib.rolsac.api.v2.document.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceGateway {

	DocumentWSSoapBindingStub stub;
	
	public DocumentQueryServiceGateway() {

		try {

			stub = new DocumentWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_DOCUMENT)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

	public FitxaDTO obtenirFitxa(long id) throws RemoteException {
		return stub.obtenirFitxa(id);
	}

	public ProcedimentDTO obtenirProcediment(long id) throws RemoteException {
		return stub.obtenirProcediment(id);
	}

	public ArxiuDTO obtenirArxiu(long id) throws RemoteException {
		return stub.obtenirArxiu(id);
	}	
	
}
