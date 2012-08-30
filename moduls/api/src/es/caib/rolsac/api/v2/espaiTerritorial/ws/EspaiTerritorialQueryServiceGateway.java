package es.caib.rolsac.api.v2.espaiTerritorial.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialQueryServiceEJBRemoteServiceLocator;
import localhost.sacws_api.services.EspaiTerritorialWS.EspaiTerritorialWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceGateway {

	EspaiTerritorialWSSoapBindingStub stub;
	EspaiTerritorialQueryServiceEJBRemoteServiceLocator locator;
	
	public EspaiTerritorialQueryServiceGateway() {

		try {
			stub = new EspaiTerritorialWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/EspaiTerritorialWS"),
					null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumFills(long id) throws RemoteException {
		return stub.getNumFills(id);
	}

	public int getNumUnitatsAdministratives(long id) throws RemoteException {
		return stub.getNumUnitatsAdministratives(id);
	}
	
	public ArxiuDTO obtenirMapa(Long idMapa) throws RemoteException {
		return stub.obtenirMapa(idMapa);
	}

	public ArxiuDTO obtenirLogo(Long idLogo) throws RemoteException {
		return stub.obtenirLogo(idLogo);
	}

	public EspaiTerritorialDTO obtenirPare(Long idPadre) throws RemoteException {
		return stub.obtenirPare(idPadre);
	}	

	public List<EspaiTerritorialDTO> llistarFills(long id,
			EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws RemoteException {

    	Object[] tmpLlista = null;
    	List<EspaiTerritorialDTO> llistaEspaisTerritorials = null;
    	
		tmpLlista = stub.llistarFills(id, espaiTerritorialCriteria);
		llistaEspaisTerritorials = new ArrayList<EspaiTerritorialDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {
			EspaiTerritorialDTO etdto = (EspaiTerritorialDTO) DTOUtil.object2DTO(o, EspaiTerritorialDTO.class);
			llistaEspaisTerritorials.add(etdto);
		}
    	
		return llistaEspaisTerritorials;
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException {
		
    	Object[] tmpLlista = null;
    	List<UnitatAdministrativaDTO> llistaUnitatsAdministratives = null;
    	
		tmpLlista = stub.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		llistaUnitatsAdministratives = new ArrayList<UnitatAdministrativaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {
			UnitatAdministrativaDTO etdto = (UnitatAdministrativaDTO) DTOUtil.object2DTO(o, UnitatAdministrativaDTO.class);
			llistaUnitatsAdministratives.add(etdto);
		}
    	
		return llistaUnitatsAdministratives;	
	}
}
