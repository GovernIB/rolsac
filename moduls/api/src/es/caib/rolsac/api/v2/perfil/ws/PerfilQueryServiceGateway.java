package es.caib.rolsac.api.v2.perfil.ws;

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
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;

public class PerfilQueryServiceGateway {

	PerfilWSSoapBindingStub stub;
	
	public PerfilQueryServiceGateway() {

		try {
			stub = new PerfilWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_PERFIL)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumIconesFamilia(long id) throws RemoteException {
		return stub.getNumIconesFamilia(id);
	}

	public int getNumIconesMateria(long id) throws RemoteException {
		return stub.getNumIconesMateria(id);
	}

	public List<IconaFamiliaDTO> llistarIconesFamilia(long id,
			IconaFamiliaCriteria iconaFamiliaCriteria) throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<IconaFamiliaDTO> llistaIconesFamilia = null;
		
		tmpLlista = stub.llistarIconesFamilia(id, iconaFamiliaCriteria);
		llistaIconesFamilia = new ArrayList<IconaFamiliaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			IconaFamiliaDTO ifdto = (IconaFamiliaDTO) DTOUtil.object2DTO(o, IconaFamiliaDTO.class);
			llistaIconesFamilia.add(ifdto);
		}
		
		return llistaIconesFamilia;		
		
	}

	public List<IconaMateriaDTO> llistarIconesMateria(long id,
			IconaMateriaCriteria iconaMateriaCriteria) throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<IconaMateriaDTO> llistaIconesMateria = null;
		
		tmpLlista = stub.llistarIconesMateria(id, iconaMateriaCriteria);
		llistaIconesMateria = new ArrayList<IconaMateriaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			IconaMateriaDTO imdto = (IconaMateriaDTO) DTOUtil.object2DTO(o, IconaMateriaDTO.class);
			llistaIconesMateria.add(imdto);
		}
		
		return llistaIconesMateria;		
	}
}
