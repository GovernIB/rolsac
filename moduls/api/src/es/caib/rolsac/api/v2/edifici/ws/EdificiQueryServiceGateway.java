package es.caib.rolsac.api.v2.edifici.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceGateway {

	EdificiWSSoapBindingStub stub;
	
	public EdificiQueryServiceGateway() {

		try {

			stub = new EdificiWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_EDIFICI)),
					null);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

	public int getNumUnitatsAdministratives(long id) throws RemoteException {
		return stub.getNumUnitatsAdministratives(id);
	}

	public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya)
			throws RemoteException {
		return stub.obtenirFotoPequenya(idFotoPequenya);
	}

	public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) throws RemoteException {
		return stub.obtenirFotoGrande(idFotoGrande);
	}

	public ArxiuDTO obtenirPlano(Long idPlano) throws RemoteException {
		return stub.obtenirPlano(idPlano);
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<UnitatAdministrativaDTO> llistaMateries = null;

		tmpLlista = stub.llistarUnitatsAdministratives(id,
				unitatAdministrativaCriteria);
		llistaMateries = new ArrayList<UnitatAdministrativaDTO>(Arrays.asList(
				tmpLlista).size());

		for (Object o : tmpLlista) {
			UnitatAdministrativaDTO uadto = (UnitatAdministrativaDTO) DTOUtil
					.object2DTO(o, UnitatAdministrativaDTO.class);
			llistaMateries.add(uadto);
		}

		return llistaMateries;

	}
}