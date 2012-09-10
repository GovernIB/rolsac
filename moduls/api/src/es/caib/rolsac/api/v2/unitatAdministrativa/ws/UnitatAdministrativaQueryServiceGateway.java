package es.caib.rolsac.api.v2.unitatAdministrativa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.tractament.TractamentDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class UnitatAdministrativaQueryServiceGateway {

	UnitatAdministrativaWSSoapBindingStub stub;
	UnitatAdministrativaQueryServiceEJBRemoteServiceLocator locator;

	public UnitatAdministrativaQueryServiceGateway() {
		try {
			locator = new UnitatAdministrativaQueryServiceEJBRemoteServiceLocator();

			stub = new UnitatAdministrativaWSSoapBindingStub(new URL(
					locator.getUnitatAdministrativaWSAddress()), null);

		} catch (RemoteException re) {
		} catch (MalformedURLException mue) {
		}
	}

	public ArxiuDTO obtenirFotop(Long fotop) throws RemoteException {
		return stub.obtenirFotop(fotop);
	}

	public ArxiuDTO obtenirFotog(Long fotog) throws RemoteException {
		return stub.obtenirFotog(fotog);
	}

	public ArxiuDTO obtenirLogoh(Long logoh) throws RemoteException {
		return stub.obtenirLogoh(logoh);
	}

	public ArxiuDTO obtenirLogov(Long logov) throws RemoteException {
		return stub.obtenirLogov(logov);
	}

	public ArxiuDTO obtenirLogos(Long logos) throws RemoteException {
		return stub.obtenirLogos(logos);
	}

	public ArxiuDTO obtenirLogot(Long logot) throws RemoteException {
		return stub.obtenirLogot(logot);
	}	
	
	public int getNumFilles(Long id) throws RemoteException {
		return stub.getNumFilles(id);

	}

	public int getNumEdificis(Long id) throws RemoteException {
		return stub.getNumEdificis(id);

	}

	public int getNumPersonal(Long id) throws RemoteException {
		return stub.getNumPersonal(id);

	}

	public int getNumNormatives(Long id) throws RemoteException {
		return stub.getNumNormatives(id);

	}

	public int getNumProcediments(Long id) throws RemoteException {
		return stub.getNumProcediments(id);

	}

	public int getNumTramits(Long id) throws RemoteException {
		return stub.getNumTramits(id);

	}

	public int getNumUsuaris(Long id) throws RemoteException {
		return stub.getNumUsuaris(id);

	}

	public int getNumFitxes(Long id) throws RemoteException {
		return stub.getNumFitxes(id);

	}

	public int getNumSeccions(Long id) throws RemoteException {
		return stub.getNumSeccions(id);

	}

	public int getNumMateries(Long id) throws RemoteException {
		return stub.getNumMateries(id);

	}

	public UnitatAdministrativaDTO obtenirPare(long idPare)
			throws RemoteException {
		return stub.obtenirPare(idPare);
	}

	public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt)
			throws RemoteException {
		return stub.obtenirEspaiTerritorial(idEt);
	}

	public TractamentDTO obtenirTractament(long idTract) throws RemoteException {
		return stub.obtenirTractament(idTract);
	}

	public List<UnitatAdministrativaDTO> llistarFilles(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException {

		return Arrays.asList( stub.llistarFilles(id, unitatAdministrativaCriteria));
	}

	public List<EdificiDTO> llistarEdificis(long id,
			EdificiCriteria edificiCriteria) throws RemoteException {

		Object[] tmpLlista = null;
		List<EdificiDTO> llistaEdificis = null;

		tmpLlista = stub.llistarEdificis(id, edificiCriteria);
		llistaEdificis = new ArrayList<EdificiDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			EdificiDTO edto = (EdificiDTO) DTOUtil.object2DTO(o,
					EdificiDTO.class);
			llistaEdificis.add(edto);
		}

		return llistaEdificis;
	}

	public List<PersonalDTO> llistarPersonal(long id,
			PersonalCriteria personalCriteria) throws RemoteException {

		Object[] tmpLlista = null;
		List<PersonalDTO> llistaPersonal = null;

		tmpLlista = stub.llistarPersonal(id, personalCriteria);
		llistaPersonal = new ArrayList<PersonalDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			PersonalDTO pdto = (PersonalDTO) DTOUtil.object2DTO(o,
					PersonalDTO.class);
			llistaPersonal.add(pdto);
		}

		return llistaPersonal;
	}

	public List<NormativaDTO> llistarNormatives(long id,
			NormativaCriteria normativaCriteria) throws RemoteException {

		Object[] tmpLlista = null;
		List<NormativaDTO> llistaNormatives = null;

		tmpLlista = stub.llistarNormatives(id, normativaCriteria);
		llistaNormatives = new ArrayList<NormativaDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			NormativaDTO pdto = (NormativaDTO) DTOUtil.object2DTO(o,
					NormativaDTO.class);
			llistaNormatives.add(pdto);
		}

		return llistaNormatives;
	}

	public List<ProcedimentDTO> llistarProcediments(long id,
			ProcedimentCriteria procedimentCriteria) throws RemoteException {

		Object[] tmpLlista = null;
		List<ProcedimentDTO> llistaNormatives = null;

		tmpLlista = stub.llistarProcediments(id, procedimentCriteria);
		llistaNormatives = new ArrayList<ProcedimentDTO>(Arrays.asList(
				tmpLlista).size());

		for (Object o : tmpLlista) {
			ProcedimentDTO pdto = (ProcedimentDTO) DTOUtil.object2DTO(o,
					ProcedimentDTO.class);
			llistaNormatives.add(pdto);
		}

		return llistaNormatives;
	}

	public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria)
			throws RemoteException {

		Object[] tmpLlista = null;
		List<TramitDTO> llistaTramits = null;

		tmpLlista = stub.llistarTramits(id, tramitCriteria);
		llistaTramits = new ArrayList<TramitDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			TramitDTO tdto = (TramitDTO) DTOUtil.object2DTO(o, TramitDTO.class);
			llistaTramits.add(tdto);
		}

		return llistaTramits;
	}

	public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria)
			throws RemoteException {

		Object[] tmpLlista = null;
		List<UsuariDTO> llistaUsuaris = null;

		tmpLlista = stub.llistarUsuaris(id, usuariCriteria);
		llistaUsuaris = new ArrayList<UsuariDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			UsuariDTO udto = (UsuariDTO) DTOUtil.object2DTO(o, UsuariDTO.class);
			llistaUsuaris.add(udto);
		}

		return llistaUsuaris;
	}

	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws RemoteException {

		Object[] tmpLlista = null;
		List<FitxaDTO> llistaFitxes = null;

		tmpLlista = stub.llistarFitxes(id, fitxaCriteria);
		llistaFitxes = new ArrayList<FitxaDTO>(Arrays.asList(tmpLlista).size());

		for (Object o : tmpLlista) {
			FitxaDTO fdto = (FitxaDTO) DTOUtil.object2DTO(o, FitxaDTO.class);
			llistaFitxes.add(fdto);
		}

		return llistaFitxes;
	}

	public List<SeccioDTO> llistarSeccions(long id,
			SeccioCriteria seccioCriteria) throws RemoteException {
		
		Object[] tmpLlista = null;
		List<SeccioDTO> llistaSeccions = null;

		tmpLlista = stub.llistarSeccions(id, seccioCriteria);
		llistaSeccions = new ArrayList<SeccioDTO>(Arrays.asList(tmpLlista).size());

		for (Object o : tmpLlista) {
			SeccioDTO sdto = (SeccioDTO) DTOUtil.object2DTO(o, SeccioDTO.class);
			llistaSeccions.add(sdto);
		}

		return llistaSeccions;
	}

	public List<MateriaDTO> llistarMateries(long id,
			MateriaCriteria materiaCriteria) throws RemoteException {
		Object[] tmpLlista = null;
		List<MateriaDTO> llistaMateries = null;

		tmpLlista = stub.llistarMateries(id, materiaCriteria);
		llistaMateries = new ArrayList<MateriaDTO>(Arrays.asList(tmpLlista).size());

		for (Object o : tmpLlista) {
			MateriaDTO sdto = (MateriaDTO) DTOUtil.object2DTO(o, MateriaDTO.class);
			llistaMateries.add(sdto);
		}

		return llistaMateries;
	}
}