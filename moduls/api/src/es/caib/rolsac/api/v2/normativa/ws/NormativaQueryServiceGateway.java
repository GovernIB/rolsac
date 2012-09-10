package es.caib.rolsac.api.v2.normativa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceGateway {

	NormativaWSSoapBindingStub stub;
	NormativaQueryServiceEJBRemoteServiceLocator locator;
	
	public NormativaQueryServiceGateway() {

		try {
			
			locator = new NormativaQueryServiceEJBRemoteServiceLocator();

			stub = new NormativaWSSoapBindingStub(new URL(
					locator.getNormativaWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumAfectades(long id) throws RemoteException {
		return stub.getNumAfectades(id);
	}

	public int getNumAfectants(long id) throws RemoteException {
		return stub.getNumAfectants(id);
	}

	public int getNumProcediments(long id) throws RemoteException {
		return stub.getNumProcediments(id);
	}

	public ButlletiDTO obtenirButlleti(long idButlleti) throws RemoteException {
		return stub.obtenirButlleti(idButlleti);
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm)
			throws RemoteException {
		return stub.obtenirUnitatAdministrativa(idUniAdm);
	}

	public ArxiuDTO obtenirArxiuNormativa(Long idArchivo)
			throws RemoteException {
		return stub.obtenirArxiuNormativa(idArchivo);
	}
	
	public List<NormativaDTO> llistarAfectades(long id) throws RemoteException {
		Object[] tmpLlista = null;
		List<NormativaDTO> llistaAfectades = null;
		
		tmpLlista = stub.llistarAfectades(id);
		llistaAfectades = new ArrayList<NormativaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o, NormativaDTO.class);
			llistaAfectades.add(ndto);
		}
		
		return llistaAfectades;		
	}

	public List<NormativaDTO> llistarAfectants(long id) throws RemoteException {
		Object[] tmpLlista = null;
		List<NormativaDTO> llistaAfectants = null;
		
		tmpLlista = stub.llistarAfectants(id);
		llistaAfectants = new ArrayList<NormativaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o, NormativaDTO.class);
			llistaAfectants.add(ndto);
		}
		
		return llistaAfectants;		
	}

	public List<ProcedimentDTO> llistarProcediments(long id,
			ProcedimentCriteria procedimentCriteria) throws RemoteException {

		Object[] tmpLlista = null;
		List<ProcedimentDTO> llistaProcediments = null;
		
		tmpLlista = stub.llistarProcediments(id, procedimentCriteria);
		llistaProcediments = new ArrayList<ProcedimentDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			ProcedimentDTO pdto = (ProcedimentDTO) DTOUtil.object2DTO(o, ProcedimentDTO.class);
			llistaProcediments.add(pdto);
		}
		
		return llistaProcediments;		
		
	}

	public List<AfectacioDTO> llistarAfectacionsAfectants(Long id)
			throws RemoteException {
		return Arrays.asList(stub.llistarAfectacionsAfectants(id)); 
		
	}

	public List<AfectacioDTO> llistarAfectacionsAfectades(Long id)
			throws RemoteException {
		return Arrays.asList(stub.llistarAfectacionsAfectades(id));
	}

}
