package es.caib.rolsac.api.v2.estadistica.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

public class EstadisticaInsertServiceGateway {
	
	EstadisticaWSSoapBindingStub stub;
	EstadisticaInsertServiceEJBRemoteServiceLocator locator;
	
	public EstadisticaInsertServiceGateway() {

		try {
			
			locator = new EstadisticaInsertServiceEJBRemoteServiceLocator();

			stub = new EstadisticaWSSoapBindingStub(new URL(
					locator.getEstadisticaWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean gravarEstadisticaFitxa(long fitxaId)
			throws RemoteException {
		return stub.gravarEstadisticaFitxa(fitxaId);
	}

	public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId)
			throws RemoteException {
		return stub.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
	}

	public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId)
			throws RemoteException {
		return stub.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
	}

	public boolean gravarEstadisticaMateria(long materiaId)
			throws RemoteException {
		return stub.gravarEstadisticaMateria(materiaId);
	}

	public boolean gravarEstadisticaNormativa(long normativaId)
			throws RemoteException {
		return stub.gravarEstadisticaNormativa(normativaId);
	}

	public boolean gravarEstadisticaProcediment(long procedimentId)
			throws RemoteException {
		return stub.gravarEstadisticaProcediment(procedimentId);
	}

	public boolean gravarEstadisticaUnitatAdministrativa(long uaId)
			throws RemoteException {
		return stub.gravarEstadisticaUnitatAdministrativa(uaId);
	}	

}
