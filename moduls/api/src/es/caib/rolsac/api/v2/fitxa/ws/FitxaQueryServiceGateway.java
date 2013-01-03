package es.caib.rolsac.api.v2.fitxa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceGateway {

	FitxaWSSoapBindingStub stub;
	
	public FitxaQueryServiceGateway() {

		try {
			stub = new FitxaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_FITXA)),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(APIException e) {
			e.printStackTrace();
		}
	}

	public int getNumUnitatsAdministratives(long id) throws RemoteException {
		return stub.getNumUnitatsAdministratives(id);
	}

	public int getNumDocuments(long id) throws RemoteException {
		return stub.getNumDocuments(id);
	}

	public int getNumEnllacos(long id) throws RemoteException {
		return stub.getNumEnllacos(id);				
	}

	public int getNumFetsVitals(long id) throws RemoteException {
		return stub.getNumEnllacos(id);
	}

	public int getNumSeccions(long id) throws RemoteException {
		return stub.getNumSeccions(id);
	}

	public ArxiuDTO obtenirIcona(Long icono) throws RemoteException {
		return stub.obtenirIcona(icono);
	}

	public ArxiuDTO obtenirImatge(Long imagen) throws RemoteException {
		return stub.obtenirImatge(imagen);
	}

	public ArxiuDTO obtenirBaner(Long baner) throws RemoteException {
		return stub.obtenirBaner(baner);
	}
	
	public List<EnllacDTO> llistarEnllacos(long id,
			EnllacCriteria enllacCriteria) throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<EnllacDTO> llistaEnllacos = null;
		
		tmpLlista = stub.llistarEnllacos(id, enllacCriteria);
		llistaEnllacos = new ArrayList<EnllacDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			EnllacDTO edto = (EnllacDTO) DTOUtil.object2DTO(o, EnllacDTO.class);
			llistaEnllacos.add(edto);
		}
		
		return llistaEnllacos;
		
	}

	public List<DocumentDTO> llistarDocuments(long id,
			DocumentCriteria documentCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<DocumentDTO> llistaDocuments = null;
		
		tmpLlista = stub.llistarDocuments(id, documentCriteria);
		llistaDocuments = new ArrayList<DocumentDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			DocumentDTO ddto = (DocumentDTO) DTOUtil.object2DTO(o, DocumentDTO.class);
			llistaDocuments.add(ddto);
		}
		
		return llistaDocuments;		
		
	}

	public List<FetVitalDTO> llistarFetsVitals(long id,
			FetVitalCriteria fetVitalCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<FetVitalDTO> llistaFetsVitals = null;
		
		tmpLlista = stub.llistarFetsVitals(id, fetVitalCriteria);
		llistaFetsVitals= new ArrayList<FetVitalDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			FetVitalDTO fvto = (FetVitalDTO) DTOUtil.object2DTO(o, FetVitalDTO.class);
			llistaFetsVitals.add(fvto);
		}
		
		return llistaFetsVitals;
		
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<UnitatAdministrativaDTO> llistaUnitatsAdministratives = null;
		
		tmpLlista = stub.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		llistaUnitatsAdministratives = new ArrayList<UnitatAdministrativaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			UnitatAdministrativaDTO uadto = (UnitatAdministrativaDTO) DTOUtil.object2DTO(o, UnitatAdministrativaDTO.class);
			llistaUnitatsAdministratives.add(uadto);
		}
		
		return llistaUnitatsAdministratives;		
		
	}

	public List<SeccioDTO> llistarSeccions(long id,
			SeccioCriteria seccioCriteria) throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<SeccioDTO> llistaSeccions = null;
		
		tmpLlista = stub.llistarSeccions(id, seccioCriteria);
		llistaSeccions = new ArrayList<SeccioDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			SeccioDTO sdto = (SeccioDTO) DTOUtil.object2DTO(o, SeccioDTO.class);
			llistaSeccions.add(sdto);
		}
		
		return llistaSeccions;		
		
	}
	
	public List<PublicObjectiuDTO> llistarPublicsObjectius(long id,
			PublicObjectiuCriteria poCriteria) throws RemoteException,
			APIException {
        Object[] tmpLlista = null;
        List<PublicObjectiuDTO> llistaPOs = null;
        
        tmpLlista = stub.llistarPublicsObjectius(id, poCriteria);
        llistaPOs = new ArrayList<PublicObjectiuDTO>(Arrays.asList(tmpLlista).size());
            
        for ( Object o : tmpLlista ) {
            PublicObjectiuDTO podto = (PublicObjectiuDTO) DTOUtil.object2DTO(o, PublicObjectiuDTO.class);
            llistaPOs.add(podto);
        }
        
        return llistaPOs;        
    }
	
}
