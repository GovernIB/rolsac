package es.caib.rolsac.api.v2.publicObjectiu.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.general.DTOUtil;

public class PublicObjectiuQueryServiceGateway {

	PublicObjectiuWSSoapBindingStub stub;
	PublicObjectiuQueryServiceEJBRemoteServiceLocator locator;
	
	public PublicObjectiuQueryServiceGateway() {

		try {
			
			locator = new PublicObjectiuQueryServiceEJBRemoteServiceLocator();

			stub = new PublicObjectiuWSSoapBindingStub(new URL(
					locator.getPublicObjectiuWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumAgrupacions(long id) throws RemoteException {
		return stub.getNumAgrupacions(id);
	}

	public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id,
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws RemoteException {

    	Object[] tmpLlista = null;
    	List<AgrupacioFetVitalDTO> llistaAgrupacionsFetVitals = null;
    	
		tmpLlista = stub.llistarAgrupacions(id, agrupacioFetVitalCriteria);
		llistaAgrupacionsFetVitals = new ArrayList<AgrupacioFetVitalDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {
			AgrupacioFetVitalDTO afvdto = (AgrupacioFetVitalDTO) DTOUtil.object2DTO(o, AgrupacioFetVitalDTO.class);
			llistaAgrupacionsFetVitals.add(afvdto);
		}
    	
		return llistaAgrupacionsFetVitals;		
		
	}
	
	
}
