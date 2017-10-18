package es.caib.rolsac.api.v2.servicio.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;


public class ServicioQueryServiceGateway {

	ServicioWSSoapBindingStub stub;
	
	public ServicioQueryServiceGateway() {

		try {			
			stub = new ServicioWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_PROCEDIMENT)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new ServicioWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_PROCEDIMENT),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public int getNumNormatives(long id, TIPUS_NORMATIVA tipus)
			throws RemoteException {
		return stub.getNumNormatives(id, tipus.ordinal());
	}

	public int getNumMateries(long id) throws RemoteException {
		return stub.getNumMateries(id);
	}

	public int getNumDocuments(long id) throws RemoteException {
		return stub.getNumDocuments(id);
	}

	public int getNumFetsVitals(long id) throws RemoteException {
		return stub.getNumFetsVitals(id);
	}


	public List<NormativaDTO> llistarNormatives(long id,
			NormativaCriteria normativaCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<NormativaDTO> llistaNormatives = null;

		tmpLlista = stub.llistarNormatives(id, normativaCriteria);
		llistaNormatives = new ArrayList<NormativaDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o,
					NormativaDTO.class);
			llistaNormatives.add(ndto);
		}

		return llistaNormatives;
	}

	public List<MateriaDTO> llistarMateries(long id,
			MateriaCriteria materiaCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<MateriaDTO> llistaMateries = null;

		tmpLlista = stub.llistarMateries(id, materiaCriteria);
		llistaMateries = new ArrayList<MateriaDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			MateriaDTO mdto = (MateriaDTO) DTOUtil.object2DTO(o,
					MateriaDTO.class);
			llistaMateries.add(mdto);
		}

		return llistaMateries;

	}

	public List<FetVitalDTO> llistarFetsVitals(long id,
			FetVitalCriteria fetsVitalsCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<FetVitalDTO> llistaFetsVitals = null;

		tmpLlista = stub.llistarFetsVitals(id, fetsVitalsCriteria);
		llistaFetsVitals = new ArrayList<FetVitalDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			FetVitalDTO fvdto = (FetVitalDTO) DTOUtil.object2DTO(o,
					FetVitalDTO.class);
			llistaFetsVitals.add(fvdto);
		}

		return llistaFetsVitals;
	}

	public List<DocumentoServicioDTO> llistarDocuments(long id,
			DocumentoServicioCriteria documentoServicioCriteria) throws RemoteException, APIException {
		Object[] tmpLlista = null;
		List<DocumentoServicioDTO> llistaDocuments = null;

		tmpLlista = stub.llistarDocuments(id, documentoServicioCriteria);
		llistaDocuments = new ArrayList<DocumentoServicioDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			DocumentoServicioDTO fvdto = (DocumentoServicioDTO) DTOUtil.object2DTO(o,
					DocumentoServicioDTO.class);
			llistaDocuments.add(fvdto);
		}

		return llistaDocuments;
	}
	
	public List<PublicObjectiuDTO> llistarPublicsObjectius(long id,
			PublicObjectiuCriteria poCriteria) throws RemoteException,
			APIException {

        Object[] tmpLlista = null;
        List<PublicObjectiuDTO> llistaPOs = null;

        tmpLlista = stub.llistarPublicsObjectius(id, poCriteria);
        llistaPOs = new ArrayList<PublicObjectiuDTO>(Arrays.asList(tmpLlista).size());

        for (Object o : tmpLlista) {
            PublicObjectiuDTO podto = (PublicObjectiuDTO) DTOUtil.object2DTO(o, PublicObjectiuDTO.class);
            llistaPOs.add(podto);
        }

        return llistaPOs;

    }

	
	
}
