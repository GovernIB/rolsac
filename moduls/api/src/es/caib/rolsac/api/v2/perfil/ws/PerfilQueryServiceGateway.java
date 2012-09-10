package es.caib.rolsac.api.v2.perfil.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;

public class PerfilQueryServiceGateway {

	PerfilWSSoapBindingStub stub;
	PerfilQueryServiceEJBRemoteServiceLocator locator;
	
	public PerfilQueryServiceGateway() {

		try {
			
			locator = new PerfilQueryServiceEJBRemoteServiceLocator();

			stub = new PerfilWSSoapBindingStub(new URL(
					locator.getPerfilWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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
			IconaFamiliaCriteria iconaFamiliaCriteria) throws RemoteException {
		
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
			IconaMateriaCriteria iconaMateriaCriteria) throws RemoteException {
		
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
