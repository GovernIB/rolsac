package es.caib.rolsac.api.v2.idioma.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;

public class IdiomaQueryServiceGateway {
	
	IdiomaWSSoapBindingStub stub;
		
	public IdiomaQueryServiceGateway() {
		
		try {
			stub = new IdiomaWSSoapBindingStub(
				new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_IDIOMA)),
				null
			);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<IdiomaDTO> llistaIdiomes = null;
		
		tmpLlista = stub.llistarIdiomes(idiomaCriteria);
		llistaIdiomes = new ArrayList<IdiomaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			IdiomaDTO ifdto = (IdiomaDTO)DTOUtil.object2DTO(o, IdiomaDTO.class);
			llistaIdiomes.add(ifdto);
		}
		
		return llistaIdiomes;		
		
	}
	
}
