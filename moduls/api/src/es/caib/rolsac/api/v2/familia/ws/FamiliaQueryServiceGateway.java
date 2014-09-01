package es.caib.rolsac.api.v2.familia.ws;

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
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FamiliaQueryServiceGateway {
	
	FamiliaWSSoapBindingStub stub;
	
	public FamiliaQueryServiceGateway() {

		try {
			stub = new FamiliaWSSoapBindingStub(
				new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_FAMILIA)),
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
		
	public int getNumProcedimentsLocals(long id) throws RemoteException {
		return stub.getNumProcedimentsLocals(id);
	}

	public int getNumIcones(long id) throws RemoteException {
		return stub.getNumIcones(id);
	}
	
	public List<ProcedimentDTO> llistarProcedimentsLocals(long id,
			ProcedimentCriteria procedimentCriteria) throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<ProcedimentDTO> llistaMateries = null;
		
		tmpLlista = stub.llistarProcedimentsLocals(id, procedimentCriteria);
		llistaMateries = new ArrayList<ProcedimentDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			ProcedimentDTO pdto = (ProcedimentDTO) DTOUtil.object2DTO(o, ProcedimentDTO.class);
			llistaMateries.add(pdto);
		}
		
		return llistaMateries;

	}

	public List<IconaFamiliaDTO> llistarIcones(long id,
			IconaFamiliaCriteria iconaFamiliaCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<IconaFamiliaDTO> llistaIcones = null;
		
		tmpLlista = stub.llistarIcones(id, iconaFamiliaCriteria);
		llistaIcones = new ArrayList<IconaFamiliaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			IconaFamiliaDTO ifdto = (IconaFamiliaDTO) DTOUtil.object2DTO(o, IconaFamiliaDTO.class);
			llistaIcones.add(ifdto);
		}
		
		return llistaIcones;		
		
	}
}
