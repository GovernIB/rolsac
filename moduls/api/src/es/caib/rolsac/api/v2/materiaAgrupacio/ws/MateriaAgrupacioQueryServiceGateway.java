package es.caib.rolsac.api.v2.materiaAgrupacio.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public class MateriaAgrupacioQueryServiceGateway {

	MateriaAgrupacioWSSoapBindingStub stub;
	MateriaAgrupacioQueryServiceEJBRemoteServiceLocator locator;
	
	public MateriaAgrupacioQueryServiceGateway() {

		try {
			
			locator = new MateriaAgrupacioQueryServiceEJBRemoteServiceLocator();

			stub = new MateriaAgrupacioWSSoapBindingStub(new URL(
					locator.getMateriaAgrupacioWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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

