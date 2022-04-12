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
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaDTO;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoCriteria;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoDTO;
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
import es.caib.rolsac.api.v2.plantilla.PlantillaCriteria;
import es.caib.rolsac.api.v2.plantilla.PlantillaDTO;
import es.caib.rolsac.api.v2.plataforma.PlataformaCriteria;
import es.caib.rolsac.api.v2.plataforma.PlataformaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
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

	@Override
	public void setUrl(final String url) {
		gateway.setUrl(url);
	}

	public void setGateway(final RolsacQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public UnitatMateriaDTO obtenirUnitatMateria(final UnitatMateriaCriteria unitatMateriaCriteria)
			throws StrategyException {

		UnitatMateriaDTO unitatMateriaDTO = new UnitatMateriaDTO();
		try {
			unitatMateriaDTO = gateway.obtenirUnitatMateria(unitatMateriaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return unitatMateriaDTO;

	}

	@Override
	public CatalegDocumentsDTO obtenirCatalegDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws StrategyException {
		try {
			return gateway.obtenirCatalegDocuments(catalegDocumentsCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException {
		try {
			return gateway.obtenirExcepcioDocumentacioDTO(excepcioDocumentacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public UsuariDTO obtenirUsuari(final UsuariCriteria usuariCriteria) throws StrategyException {
		UsuariDTO usuariDTO = new UsuariDTO();

		try {
			usuariDTO = gateway.obtenirUsuari(usuariCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return usuariDTO;
	}

	@Override
	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(
			final UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {

		UnitatAdministrativaDTO unitatAdministrativaDTO = new UnitatAdministrativaDTO();

		try {
			unitatAdministrativaDTO = gateway.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return unitatAdministrativaDTO;
	}

	@Override
	public TramitDTO obtenirTramit(final TramitCriteria tramitCriteria) throws StrategyException {
		TramitDTO tramitDTO = new TramitDTO();

		try {
			tramitDTO = gateway.obtenirTramit(tramitCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return tramitDTO;
	}

	@Override
	public TipusDTO obtenirTipus(final TipusCriteria tipusCriteria) throws StrategyException {
		TipusDTO tipusDTO = new TipusDTO();

		try {
			tipusDTO = gateway.obtenirTipus(tipusCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return tipusDTO;

	}

	@Override
	public TaxaDTO obtenirTaxa(final TaxaCriteria taxaCriteria) throws StrategyException {
		TaxaDTO taxaDTO = new TaxaDTO();

		try {
			taxaDTO = gateway.obtenirTaxa(taxaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return taxaDTO;
	}

	@Override
	public SeccioDTO obtenirSeccio(final SeccioCriteria seccioCriteria) throws StrategyException {
		SeccioDTO seccioDTO = new SeccioDTO();

		try {
			seccioDTO = gateway.obtenirSeccio(seccioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return seccioDTO;
	}

	@Override
	public PublicObjectiuDTO obtenirPublicObjectiu(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws StrategyException {
		PublicObjectiuDTO publicObjectiuDTO = new PublicObjectiuDTO();

		try {
			publicObjectiuDTO = gateway.obtenirPublicObjectiu(publicObjectiuCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return publicObjectiuDTO;
	}

	@Override
	public ProcedimentDTO obtenirProcediment(final ProcedimentCriteria procedimentCriteria) throws StrategyException {

		ProcedimentDTO procedimentDTO = new ProcedimentDTO();

		try {
			procedimentDTO = gateway.obtenirProcediment(procedimentCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return procedimentDTO;
	}

	@Override
	public ServicioDTO obtenirServicio(final ServicioCriteria servicioCriteria) throws StrategyException {

		ServicioDTO servicioDTO = new ServicioDTO();

		try {
			servicioDTO = gateway.obtenirServicio(servicioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return servicioDTO;
	}

	@Override
	public PersonalDTO obtenirPersonal(final PersonalCriteria personalCriteria) throws StrategyException {

		PersonalDTO personalDTO = new PersonalDTO();

		try {
			personalDTO = gateway.obtenirPersonal(personalCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return personalDTO;
	}

	@Override
	public PerfilDTO obtenirPerfil(final PerfilCriteria perfilCriteria) throws StrategyException {

		PerfilDTO perfilDTO = new PerfilDTO();

		try {
			perfilDTO = gateway.obtenirPerfil(perfilCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return perfilDTO;

	}

	@Override
	public NormativaDTO obtenirNormativa(final NormativaCriteria normativaCriteria) throws StrategyException {

		NormativaDTO normativaDTO = new NormativaDTO();

		try {
			normativaDTO = gateway.obtenirNormativa(normativaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return normativaDTO;
	}

	@Override
	public MateriaDTO obtenirMateria(final MateriaCriteria materiaCriteria) throws StrategyException {

		MateriaDTO materiaDTO = new MateriaDTO();

		try {
			materiaDTO = gateway.obtenirMateria(materiaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return materiaDTO;
	}

	@Override
	public MateriaAgrupacioDTO obtenirMateriaAgrupacio(final MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws StrategyException {

		MateriaAgrupacioDTO materiaAgrupacioDTO = new MateriaAgrupacioDTO();

		try {
			materiaAgrupacioDTO = gateway.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return materiaAgrupacioDTO;
	}

	@Override
	public IconaMateriaDTO obtenirIconaMateria(final IconaMateriaCriteria iconaMateriaCriteria)
			throws StrategyException {

		IconaMateriaDTO iconaMateriaDTO = new IconaMateriaDTO();

		try {
			iconaMateriaDTO = gateway.obtenirIconaMateria(iconaMateriaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return iconaMateriaDTO;
	}

	@Override
	public IconaFamiliaDTO obtenirIconaFamilia(final IconaFamiliaCriteria iconaFamiliaCriteria)
			throws StrategyException {

		IconaFamiliaDTO iconaFamiliaDTO = new IconaFamiliaDTO();

		try {
			iconaFamiliaDTO = gateway.obtenirIconaFamilia(iconaFamiliaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return iconaFamiliaDTO;
	}

	@Override
	public FormulariDTO obtenirFormulari(final FormulariCriteria formulariCriteria) throws StrategyException {
		FormulariDTO formulariDTO = new FormulariDTO();

		try {
			formulariDTO = gateway.obtenirFormulari(formulariCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return formulariDTO;
	}

	@Override
	public FitxaUADTO obtenirFitxaUA(final FitxaUACriteria fitxaUACriteria) throws StrategyException {

		FitxaUADTO fitxaUADTO = new FitxaUADTO();

		try {
			fitxaUADTO = gateway.obtenirFitxaUA(fitxaUACriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return fitxaUADTO;
	}

	@Override
	public PlataformaDTO obtenirPlataforma(final PlataformaCriteria plataformaCriteria) throws StrategyException {
		PlataformaDTO plataformaDTO = new PlataformaDTO();
		try {
			plataformaDTO = gateway.obtenirPlataforma(plataformaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return plataformaDTO;
	}

	@Override
	public PlantillaDTO obtenirPlantilla(final PlantillaCriteria plantillaCriteria) throws StrategyException {
		PlantillaDTO plantillaDTO = new PlantillaDTO();
		try {
			plantillaDTO = gateway.obtenirPlantilla(plantillaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return plantillaDTO;
	}

	@Override
	public FetVitalDTO obtenirFetVital(final FetVitalCriteria fetVitalCriteria) throws StrategyException {
		FetVitalDTO fetVitalDTO = new FetVitalDTO();
		try {
			fetVitalDTO = gateway.obtenirFetVital(fetVitalCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return fetVitalDTO;
	}

	@Override
	public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(final AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws StrategyException {
		AgrupacioFetVitalDTO agrupacioFetVitalDTO = new AgrupacioFetVitalDTO();

		try {
			agrupacioFetVitalDTO = gateway.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return agrupacioFetVitalDTO;
	}

	@Override
	public AgrupacioMateriaDTO obtenirAgrupacioMateria(final AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws StrategyException {
		AgrupacioMateriaDTO agrupacioMateriaDTO = new AgrupacioMateriaDTO();

		try {
			agrupacioMateriaDTO = gateway.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return agrupacioMateriaDTO;
	}

	@Override
	public ButlletiDTO obtenirButlleti(final ButlletiCriteria butlletiCriteria) throws StrategyException {
		ButlletiDTO butlletiDTO = new ButlletiDTO();

		try {
			butlletiDTO = gateway.obtenirButlleti(butlletiCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return butlletiDTO;
	}

	@Override
	public DocumentDTO obtenirDocument(final DocumentCriteria documentCriteria) throws StrategyException {
		DocumentDTO documentDTO = new DocumentDTO();

		try {
			documentDTO = gateway.obtenirDocument(documentCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return documentDTO;
	}

	@Override
	public DocumentTramitDTO obtenirDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		DocumentTramitDTO documentTramitDTO = new DocumentTramitDTO();

		try {
			documentTramitDTO = gateway.obtenirDocumentTramit(documentTramitCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return documentTramitDTO;
	}

	@Override
	public EdificiDTO obtenirEdifici(final EdificiCriteria edificiCriteria) throws StrategyException {
		EdificiDTO edificiDTO = new EdificiDTO();

		try {
			edificiDTO = gateway.obtenirEdifici(edificiCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return edificiDTO;
	}

	@Override
	public EnllacDTO obtenirEnllac(final EnllacCriteria enllacCriteria) throws StrategyException {
		EnllacDTO enllacDTO = new EnllacDTO();

		try {
			enllacDTO = gateway.obtenirEnllac(enllacCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return enllacDTO;
	}

	@Override
	public EnllacTelematicoDTO obtenirEnllacTelematico(final EnllacTelematicoCriteria enllacCriteria)
			throws StrategyException {
		EnllacTelematicoDTO enllacDTO = new EnllacTelematicoDTO();

		try {
			enllacDTO = gateway.obtenirEnllacTelematico(enllacCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return enllacDTO;
	}

	@Override
	public EspaiTerritorialDTO obtenirEspaiTerritorial(final EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws StrategyException {
		EspaiTerritorialDTO espaiTerritorialDTO = new EspaiTerritorialDTO();

		try {
			espaiTerritorialDTO = gateway.obtenirEspaiTerritorial(espaiTerritorialCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return espaiTerritorialDTO;

	}

	@Override
	public FamiliaDTO obtenirFamilia(final FamiliaCriteria familiaCriteria) throws StrategyException {
		FamiliaDTO familiaDTO = new FamiliaDTO();

		try {
			familiaDTO = gateway.obtenirFamilia(familiaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return familiaDTO;
	}

	@Override
	public FitxaDTO obtenirFitxa(final FitxaCriteria fitxaCriteria) throws StrategyException {
		FitxaDTO fitxaDTO = new FitxaDTO();

		try {
			fitxaDTO = gateway.obtenirFitxa(fitxaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return fitxaDTO;
	}

	@Override
	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(
			final AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException {

		try {
			return gateway.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

	}

	@Override
	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(final AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<ButlletiDTO> llistarButlletins(final ButlletiCriteria butlletiCriteria) throws StrategyException {
		try {
			return gateway.llistarButlletins(butlletiCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<DocumentDTO> llistarDocuments(final DocumentCriteria documentCriteria) throws StrategyException {
		try {
			return gateway.llistarDocuments(documentCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<DocumentTramitDTO> llistarDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		try {
			return gateway.llistarDocumentTramit(documentTramitCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<DocumentoNormativaDTO> llistarDocumentoNormativa(final DocumentoNormativaCriteria idNormativa)
			throws StrategyException {
		try {
			return gateway.llistarDocumentoNormativa(idNormativa);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<DocumentoServicioDTO> llistarDocumentoServicio(final DocumentoServicioCriteria idServicio)
			throws StrategyException {
		try {
			return gateway.llistarDocumentoServicio(idServicio);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<EdificiDTO> llistarEdificis(final EdificiCriteria edificiTramit) throws StrategyException {
		try {
			return gateway.llistarEdificis(edificiTramit);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<EnllacDTO> llistarEnllacos(final EnllacCriteria enllacCriteria) throws StrategyException {
		try {
			return gateway.llistarEnllacos(enllacCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(final EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws StrategyException {
		try {
			return gateway.llistarEspaisTerritorials(espaiTerritorialCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<FamiliaDTO> llistarFamilies(final FamiliaCriteria familiaCriteria) throws StrategyException {
		try {
			return gateway.llistarFamilies(familiaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<FetVitalDTO> llistarFetsVitals(final FetVitalCriteria fetVitalCriteria) throws StrategyException {
		try {
			return gateway.llistarFetsVitals(fetVitalCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<FitxaDTO> llistarFitxes(final FitxaCriteria fitxaCriteria) throws StrategyException {
		try {
			return gateway.llistarFitxes(fitxaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<FitxaUADTO> llistarFitxesUA(final FitxaUACriteria fitxaUACriteria) throws StrategyException {
		try {
			return gateway.llistarFitxesUA(fitxaUACriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<FormulariDTO> llistarFormularis(final FormulariCriteria formulariCriteria) throws StrategyException {
		try {
			return gateway.llistarFormularis(formulariCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<IconaFamiliaDTO> llistarIconesFamilies(final IconaFamiliaCriteria iconaFamiliaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarIconesFamilies(iconaFamiliaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

	}

	@Override
	public List<IconaMateriaDTO> llistarIconesMateries(final IconaMateriaCriteria iconaMateriaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarIconesMateries(iconaMateriaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<MateriaDTO> llistarMateries(final MateriaCriteria materiaCriteria) throws StrategyException {
		try {
			return gateway.llistarMateries(materiaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(final MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws StrategyException {
		try {
			return gateway.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<NormativaDTO> llistarNormatives(final NormativaCriteria normativaCriteria) throws StrategyException {
		try {
			return gateway.llistarNormatives(normativaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<PerfilDTO> llistarPerfils(final PerfilCriteria perfilCriteria) throws StrategyException {
		try {
			return gateway.llistarPerfils(perfilCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

	}

	@Override
	public List<PersonalDTO> llistarPersonal(final PersonalCriteria personalCriteria) throws StrategyException {
		try {
			return gateway.llistarPersonal(personalCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<ProcedimentDTO> llistarProcediments(final ProcedimentCriteria procedimentCriteria)
			throws StrategyException {
		try {
			return gateway.llistarProcediments(procedimentCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public Integer getNumProcediments(final ProcedimentCriteria procedimentCriteria) throws StrategyException {
		try {
			return gateway.getNumProcediments(procedimentCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<ServicioDTO> llistarServicios(final ServicioCriteria servicioCriteria) throws StrategyException {
		try {
			return gateway.llistarServicios(servicioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public Integer getNumServicios(final ServicioCriteria servicioCriteria) throws StrategyException {
		try {
			return gateway.getNumServicios(servicioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<PublicObjectiuDTO> llistarPublicsObjectius(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws StrategyException {
		try {
			return gateway.llistarPublicsObjectius(publicObjectiuCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<SeccioDTO> llistarSeccions(final SeccioCriteria seccioCriteria) throws StrategyException {
		try {
			return gateway.llistarSeccions(seccioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<TaxaDTO> llistarTaxes(final TaxaCriteria taxaCriteria) throws StrategyException {
		try {
			return gateway.llistarTaxes(taxaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<TramitDTO> llistarTramits(final TramitCriteria tramitCriteria) throws StrategyException {
		try {
			return gateway.llistarTramits(tramitCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(
			final UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
		try {
			return gateway.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<UnitatMateriaDTO> llistarUnitatsMateries(final UnitatMateriaCriteria unitatMateriaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarUnitatsMateries(unitatMateriaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<UsuariDTO> llistarUsuaris(final UsuariCriteria usuariCriteria) throws StrategyException {
		try {
			return gateway.llistarUsuaris(usuariCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public IdiomaDTO obtenirIdioma(final IdiomaCriteria idiomaCriteria) throws StrategyException {
		IdiomaDTO idiomaDTO = new IdiomaDTO();

		try {
			idiomaDTO = gateway.obtenirIdioma(idiomaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

		return idiomaDTO;
	}

	@Override
	public List<IdiomaDTO> llistarIdiomes(final IdiomaCriteria idiomaCriteria) throws StrategyException {
		try {
			final List<IdiomaDTO> listaIdiomas = gateway.llistarIdiomes(idiomaCriteria);
			return listaIdiomas;
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<TipusDTO> llistarTipus(final TipusCriteria tipusCriteria) throws StrategyException {
		try {
			return gateway.llistarTipus(tipusCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<TipusAfectacioDTO> llistarTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws StrategyException {
		try {
			return gateway.llistarTipusAfectacio(tipusAfectacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws StrategyException {
		try {
			return gateway.llistarCatalegsDocuments(catalegDocumentsCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException {
		try {
			return gateway.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public TipusAfectacioDTO obtenirTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws StrategyException {
		try {
			return gateway.obtenirTipusAfectacio(tipusAfectacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public IniciacioDTO obtenirTipusIniciacio(final IniciacioCriteria iniciacioCriteria) throws StrategyException {

		try {
			return gateway.obtenirTipusIniciacio(iniciacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}

	}

	@Override
	public List<IniciacioDTO> llistarTipusIniciacions(final IniciacioCriteria iniciacioCriteria)
			throws StrategyException {

		try {
			return gateway.llistarTipusIniciacions(iniciacioCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public Integer getNumFitxes(final FitxaCriteria fitxaCriteria) throws StrategyException {

		try {
			return gateway.getNumFitxes(fitxaCriteria);
		} catch (final QueryServiceException qse) {
			throw new StrategyException(ExceptionMessages.REMOTE_SERVICE, qse);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@Override
	public SilencioDTO obtenirSilenci(final Long codSilencio, final String idioma)
			throws StrategyException, QueryServiceException {

		try {
			return gateway.obtenirSilenci(codSilencio, idioma);
		} catch (final RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

}