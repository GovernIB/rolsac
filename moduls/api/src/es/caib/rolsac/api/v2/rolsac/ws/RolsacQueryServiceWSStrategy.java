package es.caib.rolsac.api.v2.rolsac.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class RolsacQueryServiceWSStrategy implements RolsacQueryServiceStrategy {

	// @Injected
	private RolsacQueryServiceGateway gateway;

	public void setGateway(RolsacQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public UnitatMateriaDTO obtenirUnitatMateria(
			UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException {
		
		UnitatMateriaDTO unitatMateriaDTO = new UnitatMateriaDTO();
		try {
			unitatMateriaDTO = gateway.obtenirUnitatMateria(unitatMateriaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return unitatMateriaDTO;

	}
	
	public CatalegDocumentsDTO obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws StrategyException {
			try {
				return  gateway.obtenirCatalegDocuments(catalegDocumentsCriteria);
			} catch (QueryServiceException qse) {
				throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
			} catch (RemoteException e) {
				throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
			} 
	}
	
	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException {
		try {
			return gateway.obtenirExcepcioDocumentacioDTO(excepcioDocumentacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} 
	}
	
	public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) throws StrategyException{
		UsuariDTO usuariDTO = new UsuariDTO();

		try {
			usuariDTO = gateway.obtenirUsuari(usuariCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return usuariDTO;
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
		
		UnitatAdministrativaDTO unitatAdministrativaDTO = new UnitatAdministrativaDTO();

		try {
			unitatAdministrativaDTO = gateway.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return unitatAdministrativaDTO;
	}
	
	public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) throws StrategyException {
		TramitDTO tramitDTO = new TramitDTO();

		try {
			tramitDTO = gateway.obtenirTramit(tramitCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return tramitDTO;
	}
	
	public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) throws StrategyException {
		TipusDTO tipusDTO = new TipusDTO();

		try {
			tipusDTO = gateway.obtenirTipus(tipusCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return tipusDTO;
		
	}

	public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) throws StrategyException {
		TaxaDTO taxaDTO = new TaxaDTO();

		try {
			taxaDTO = gateway.obtenirTaxa(taxaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return taxaDTO;
	}
	
	public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) throws StrategyException {
		SeccioDTO seccioDTO = new SeccioDTO();

		try {
			seccioDTO = gateway.obtenirSeccio(seccioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return seccioDTO;
	}
	
	public PublicObjectiuDTO obtenirPublicObjectiu(
			PublicObjectiuCriteria publicObjectiuCriteria) throws StrategyException {
		PublicObjectiuDTO publicObjectiuDTO = new PublicObjectiuDTO();

		try {
			publicObjectiuDTO = gateway.obtenirPublicObjectiu(publicObjectiuCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return publicObjectiuDTO;
	}
	
	public ProcedimentDTO obtenirProcediment(
			ProcedimentCriteria procedimentCriteria) throws StrategyException {

		ProcedimentDTO procedimentDTO = new ProcedimentDTO();

		try {
			procedimentDTO = gateway.obtenirProcediment(procedimentCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return procedimentDTO;
	}

	public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria)
			throws StrategyException {

		PersonalDTO personalDTO = new PersonalDTO();

		try {
			personalDTO = gateway.obtenirPersonal(personalCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return personalDTO;
	}

	public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria)
			throws StrategyException {

		PerfilDTO perfilDTO = new PerfilDTO();

		try {
			perfilDTO = gateway.obtenirPerfil(perfilCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return perfilDTO;

	}

	public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria)
			throws StrategyException {

		NormativaDTO normativaDTO = new NormativaDTO();

		try {
			normativaDTO = gateway.obtenirNormativa(normativaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return normativaDTO;
	}

	public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria)
			throws StrategyException {

		MateriaDTO materiaDTO = new MateriaDTO();

		try {
			materiaDTO = gateway.obtenirMateria(materiaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return materiaDTO;
	}

	public MateriaAgrupacioDTO obtenirMateriaAgrupacio(
			MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws StrategyException {

		MateriaAgrupacioDTO materiaAgrupacioDTO = new MateriaAgrupacioDTO();

		try {
			materiaAgrupacioDTO = gateway
					.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return materiaAgrupacioDTO;
	}

	public IconaMateriaDTO obtenirIconaMateria(
			IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {

		IconaMateriaDTO iconaMateriaDTO = new IconaMateriaDTO();

		try {
			iconaMateriaDTO = gateway.obtenirIconaMateria(iconaMateriaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return iconaMateriaDTO;
	}

	public IconaFamiliaDTO obtenirIconaFamilia(
			IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {

		IconaFamiliaDTO iconaFamiliaDTO = new IconaFamiliaDTO();

		try {
			iconaFamiliaDTO = gateway.obtenirIconaFamilia(iconaFamiliaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return iconaFamiliaDTO;
	}

	public FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria)
			throws StrategyException {
		FormulariDTO formulariDTO = new FormulariDTO();

		try {
			formulariDTO = gateway.obtenirFormulari(formulariCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return formulariDTO;
	}

	public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria)
			throws StrategyException {

		FitxaUADTO fitxaUADTO = new FitxaUADTO();

		try {
			fitxaUADTO = gateway.obtenirFitxaUA(fitxaUACriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return fitxaUADTO;
	}

	public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria)
			throws StrategyException {
		FetVitalDTO fetVitalDTO = new FetVitalDTO();
		try {
			fetVitalDTO = gateway.obtenirFetVital(fetVitalCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return fetVitalDTO;
	}

	public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws StrategyException {
		AgrupacioFetVitalDTO agrupacioFetVitalDTO = new AgrupacioFetVitalDTO();

		try {
			agrupacioFetVitalDTO = gateway
					.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return agrupacioFetVitalDTO;
	}

	public AgrupacioMateriaDTO obtenirAgrupacioMateria(
			AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws StrategyException {
		AgrupacioMateriaDTO agrupacioMateriaDTO = new AgrupacioMateriaDTO();

		try {
			agrupacioMateriaDTO = gateway
					.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return agrupacioMateriaDTO;
	}

	public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria)
			throws StrategyException {
		ButlletiDTO butlletiDTO = new ButlletiDTO();

		try {
			butlletiDTO = gateway.obtenirButlleti(butlletiCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return butlletiDTO;
	}

	public DocumentDTO obtenirDocument(DocumentCriteria documentCriteria)
			throws StrategyException {
		DocumentDTO documentDTO = new DocumentDTO();

		try {
			documentDTO = gateway.obtenirDocument(documentCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return documentDTO;
	}

	public DocumentTramitDTO obtenirDocumentTramit(
			DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		DocumentTramitDTO documentTramitDTO = new DocumentTramitDTO();

		try {
			documentTramitDTO = gateway
					.obtenirDocumentTramit(documentTramitCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return documentTramitDTO;
	}

	public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria)
			throws StrategyException {
		EdificiDTO edificiDTO = new EdificiDTO();

		try {
			edificiDTO = gateway.obtenirEdifici(edificiCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return edificiDTO;
	}

	public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria)
			throws StrategyException {
		EnllacDTO enllacDTO = new EnllacDTO();

		try {
			enllacDTO = gateway.obtenirEnllac(enllacCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return enllacDTO;
	}

	public EspaiTerritorialDTO obtenirEspaiTerritorial(
			EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws StrategyException {
		EspaiTerritorialDTO espaiTerritorialDTO = new EspaiTerritorialDTO();

		try {
			espaiTerritorialDTO = gateway
					.obtenirEspaiTerritorial(espaiTerritorialCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return espaiTerritorialDTO;

	}

	public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria)
			throws StrategyException {
		FamiliaDTO familiaDTO = new FamiliaDTO();

		try {
			familiaDTO = gateway.obtenirFamilia(familiaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return familiaDTO;
	}

	public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria)
			throws StrategyException {
		FitxaDTO fitxaDTO = new FitxaDTO();

		try {
			fitxaDTO = gateway.obtenirFitxa(fitxaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return fitxaDTO;
	}

	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws StrategyException {
		
		try {
			return gateway.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
		
	}

	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(
			AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException {
		try {
			return gateway.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) throws StrategyException {
		try {
			return gateway.llistarButlletins(butlletiCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria) throws StrategyException {
		try {
			return gateway.llistarDocuments(documentCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<DocumentTramitDTO> llistarDocumentTramit(
			DocumentTramitCriteria documentTramitCriteria) throws StrategyException {
		try {
			return gateway.llistarDocumentTramit(documentTramitCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<EdificiDTO> llistarEdificis(EdificiCriteria edificiTramit) throws StrategyException {
		try {
			return gateway.llistarEdificis(edificiTramit);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) throws StrategyException {
		try {
			return gateway.llistarEnllacos(enllacCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(
			EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException {
		try {
			return gateway.llistarEspaisTerritorials(espaiTerritorialCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) throws StrategyException {
		try {
			return gateway.llistarFamilies(familiaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws StrategyException {
		try {
			return gateway.llistarFetsVitals(fetVitalCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) throws StrategyException {
		try {
			return gateway.llistarFitxes(fitxaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) throws StrategyException {
		try {
			return gateway.llistarFitxesUA(fitxaUACriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<FormulariDTO> llistarFormularis(
			FormulariCriteria formulariCriteria) throws StrategyException {
		try {
			return gateway.llistarFormularis(formulariCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<IconaFamiliaDTO> llistarIconesFamilies(
			IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
		try {
			return gateway.llistarIconesFamilies(iconaFamiliaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
		
	}

	public List<IconaMateriaDTO> llistarIconesMateries(
			IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
		try {
			return gateway.llistarIconesMateries(iconaMateriaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}				
	}

	public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) throws StrategyException {
		try {
			return gateway.llistarMateries(materiaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(
			MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws StrategyException {
		try {
			return gateway.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<NormativaDTO> llistarNormatives(
			NormativaCriteria normativaCriteria) throws StrategyException {
		try {
			return gateway.llistarNormatives(normativaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) throws StrategyException {
		try {
			return gateway.llistarPerfils(perfilCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
		
	}

	public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) throws StrategyException {
		try {
			return gateway.llistarPersonal(personalCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<ProcedimentDTO> llistarProcediments(
            ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return gateway.llistarProcediments(procedimentCriteria);
        } catch (QueryServiceException qse) {
            throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
        } catch (RemoteException e) {
            throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        }                       
    }
	
	public Integer getNumProcediments(ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return gateway.getNumProcediments(procedimentCriteria);
        } catch (QueryServiceException qse) {
            throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
        } catch (RemoteException e) {
            throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        }                       
    }

	public List<PublicObjectiuDTO> llistarPublicsObjectius(
			PublicObjectiuCriteria publicObjectiuCriteria) throws StrategyException {
		try {
			return gateway.llistarPublicsObjectius(publicObjectiuCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}

	public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) throws StrategyException {
		try {
			return gateway.llistarSeccions(seccioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}

	public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) throws StrategyException {
		try {
			return gateway.llistarTaxes(taxaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria) throws StrategyException {
		try {
			return gateway.llistarTramits(tramitCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
		try {
			return gateway.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<UnitatMateriaDTO> llistarUnitatsMateries(
			UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException {
		try {
			return gateway.llistarUnitatsMateries(unitatMateriaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) throws StrategyException {
		try {
			return gateway.llistarUsuaris(usuariCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}
	
	public IdiomaDTO obtenirIdioma(IdiomaCriteria idiomaCriteria) throws StrategyException{
		IdiomaDTO idiomaDTO = new IdiomaDTO();

		try {
			idiomaDTO = gateway.obtenirIdioma(idiomaCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return idiomaDTO;
	}
	
	public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws StrategyException {
		try {
			List<IdiomaDTO> listaIdiomas = gateway.llistarIdiomes(idiomaCriteria);
			return listaIdiomas;
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) throws StrategyException {
		try {
			return gateway.llistarTipus(tipusCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}						
	}

	public List<TipusAfectacioDTO> llistarTipusAfectacio(
			TipusAfectacioCriteria tipusAfectacioCriteria) throws StrategyException {
		try {
			return gateway.llistarTipusAfectacio(tipusAfectacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}

	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(
			CatalegDocumentsCriteria catalegDocumentsCriteria) throws StrategyException {
		try {
			return gateway.llistarCatalegsDocuments(catalegDocumentsCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}		

	public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(
			ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException {
		try {
			return gateway.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}			
	
	public TipusAfectacioDTO obtenirTipusAfectacio(
			TipusAfectacioCriteria tipusAfectacioCriteria)
			throws StrategyException {
		try {
			return gateway.obtenirTipusAfectacio(tipusAfectacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}								
	}

	public IniciacioDTO obtenirTipusIniciacio(
			IniciacioCriteria iniciacioCriteria) throws StrategyException {
		
		try {
			return gateway.obtenirTipusIniciacio(iniciacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
		
	}

	public List<IniciacioDTO> llistarTipusIniciacions(
			IniciacioCriteria iniciacioCriteria) throws StrategyException {
		
		try {
			return gateway.llistarTipusIniciacions(iniciacioCriteria);
		} catch (QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
		
	}
	
}