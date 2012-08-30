package es.caib.rolsac.api.v2.agrupacioMateria.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import localhost.sacws_api.services.AgrupacioMateriaWS.AgrupacioMateriaWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceGateway {

	AgrupacioMateriaWSSoapBindingStub stub;

	public AgrupacioMateriaQueryServiceGateway() {

		try {
			stub = new AgrupacioMateriaWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/AgrupacioMateriaWS"),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public int getNumMateries(long id) throws RemoteException {   	
    	return stub.getNumMateries(id);
    }    
	
    public SeccioDTO obtenirSeccio(long idSeccio) throws RemoteException {
    	return stub.obtenirSeccio(idSeccio);
    }
	
	public List<MateriaDTO> llistarMateries(long id,
			MateriaCriteria materiaCriteria)
			throws RemoteException {
		
		Object[] tmpLlista = null;
		List<MateriaDTO> llistaMateries = null;
		
		tmpLlista = stub.llistarMateries(id, materiaCriteria);
		llistaMateries = new ArrayList<MateriaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			MateriaDTO mdto = (MateriaDTO) DTOUtil.object2DTO(o, MateriaDTO.class);
			llistaMateries.add(mdto);
		}
		
		return llistaMateries;
	}
    
}
