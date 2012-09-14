package es.caib.rolsac.api.v2.enllac.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class EnllacQueryServiceGateway {

	EnllacWSSoapBindingStub stub;

	public EnllacQueryServiceGateway() {

		try {
			stub = new EnllacWSSoapBindingStub(new URL(
					ConfiguracioServeis.NOM_SERVEI_ENLLAC), null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FitxaDTO obtenirFitxa(long id) throws RemoteException {
		return stub.obtenirFitxa(id);
	}

	public ProcedimentDTO obtenirProcediment(long id) throws RemoteException {
		return stub.obtenirProcediment(id);
	}
}
