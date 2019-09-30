package es.caib.rolsac.api.v2.rolsac.ejb;

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
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
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

public class RolsacQueryServiceEJBStrategy implements RolsacQueryServiceStrategy {

	private RolsacQueryServiceDelegate rolsacQueryServiceDelegate;

	@Override
	public void setUrl(final String url) {
		// Para EJB no necesario
	}

	public void setRolsacQueryServiceDelegate(final RolsacQueryServiceDelegate rolsacQueryServiceDelegate) {
		this.rolsacQueryServiceDelegate = rolsacQueryServiceDelegate;
	}

	@Override
	public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(final AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(
			final AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public AgrupacioMateriaDTO obtenirAgrupacioMateria(final AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(final AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public ButlletiDTO obtenirButlleti(final ButlletiCriteria butlletiCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirButlleti(butlletiCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<ButlletiDTO> llistarButlletins(final ButlletiCriteria butlletiCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarButlletins(butlletiCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public CatalegDocumentsDTO obtenirCatalegDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirCatalegDocuments(catalegDocumentsCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarCatalegsDocuments(catalegDocumentsCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(
			final ExcepcioDocumentacioCriteria catalegDocumentsCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirExcepcioDocumentacio(catalegDocumentsCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public DocumentDTO obtenirDocument(final DocumentCriteria documentCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirDocument(documentCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<DocumentDTO> llistarDocuments(final DocumentCriteria documentCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarDocuments(documentCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public DocumentTramitDTO obtenirDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirDocumentTramit(documentTramitCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<DocumentTramitDTO> llistarDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarDocumentTramit(documentTramitCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<DocumentoNormativaDTO> llistarDocumentoNormativa(
			final DocumentoNormativaCriteria documentoNormativaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarDocumentoNormativa(documentoNormativaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<DocumentoServicioDTO> llistarDocumentoServicio(
			final DocumentoServicioCriteria documentoServicioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarDocumentoServicio(documentoServicioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public EdificiDTO obtenirEdifici(final EdificiCriteria edificiCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirEdifici(edificiCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<EdificiDTO> llistarEdificis(final EdificiCriteria edificiCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarEdificis(edificiCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public EnllacDTO obtenirEnllac(final EnllacCriteria enllacCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirEnllac(enllacCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<EnllacDTO> llistarEnllacos(final EnllacCriteria enllacCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarEnllacos(enllacCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public EspaiTerritorialDTO obtenirEspaiTerritorial(final EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirEspaiTerritorial(espaiTerritorialCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(final EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarEspaisTerritorials(espaiTerritorialCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public FamiliaDTO obtenirFamilia(final FamiliaCriteria familiaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirFamilia(familiaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<FamiliaDTO> llistarFamilies(final FamiliaCriteria familiaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarFamilies(familiaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public PlataformaDTO obtenirPlataforma(final PlataformaCriteria plataformaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirPlataforma(plataformaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public FetVitalDTO obtenirFetVital(final FetVitalCriteria fetVitalCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirFetVital(fetVitalCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<FetVitalDTO> llistarFetsVitals(final FetVitalCriteria fetVitalCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarFetsVitals(fetVitalCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public FitxaDTO obtenirFitxa(final FitxaCriteria fitxaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirFitxa(fitxaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<FitxaDTO> llistarFitxes(final FitxaCriteria fitxaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarFitxes(fitxaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public FitxaUADTO obtenirFitxaUA(final FitxaUACriteria fitxaUACriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirFitxaUA(fitxaUACriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<FitxaUADTO> llistarFitxesUA(final FitxaUACriteria fitxaUACriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarFitxesUA(fitxaUACriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public FormulariDTO obtenirFormulari(final FormulariCriteria formulariCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirFormulari(formulariCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<FormulariDTO> llistarFormularis(final FormulariCriteria formulariCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarFormularis(formulariCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public IconaFamiliaDTO obtenirIconaFamilia(final IconaFamiliaCriteria iconaFamiliaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirIconaFamilia(iconaFamiliaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<IconaFamiliaDTO> llistarIconesFamilies(final IconaFamiliaCriteria iconaFamiliaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarIconesFamilies(iconaFamiliaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public IconaMateriaDTO obtenirIconaMateria(final IconaMateriaCriteria iconaMateriaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirIconaMateria(iconaMateriaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<IconaMateriaDTO> llistarIconesMateries(final IconaMateriaCriteria iconaMateriaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarIconesMateries(iconaMateriaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public MateriaDTO obtenirMateria(final MateriaCriteria materiaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirMateria(materiaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<MateriaDTO> llistarMateries(final MateriaCriteria materiaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarMateries(materiaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public MateriaAgrupacioDTO obtenirMateriaAgrupacio(final MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(final MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public NormativaDTO obtenirNormativa(final NormativaCriteria normativaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirNormativa(normativaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<NormativaDTO> llistarNormatives(final NormativaCriteria normativaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarNormatives(normativaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public PerfilDTO obtenirPerfil(final PerfilCriteria perfilCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirPerfil(perfilCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<PerfilDTO> llistarPerfils(final PerfilCriteria perfilCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarPerfils(perfilCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public PersonalDTO obtenirPersonal(final PersonalCriteria personalCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirPersonal(personalCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<PersonalDTO> llistarPersonal(final PersonalCriteria personalCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarPersonal(personalCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public ProcedimentDTO obtenirProcediment(final ProcedimentCriteria procedimentCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirProcediment(procedimentCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<ProcedimentDTO> llistarProcediments(final ProcedimentCriteria procedimentCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarProcediments(procedimentCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public Integer getNumProcediments(final ProcedimentCriteria procedimentCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.getNumProcediments(procedimentCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public ServicioDTO obtenirServicio(final ServicioCriteria servicioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirServicio(servicioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<ServicioDTO> llistarServicios(final ServicioCriteria servicioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarServicios(servicioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public Integer getNumServicios(final ServicioCriteria servicioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.getNumServicios(servicioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public PublicObjectiuDTO obtenirPublicObjectiu(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirPublicObjectiu(publicObjectiuCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<PublicObjectiuDTO> llistarPublicsObjectius(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarPublicsObjectius(publicObjectiuCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public SeccioDTO obtenirSeccio(final SeccioCriteria seccioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirSeccio(seccioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<SeccioDTO> llistarSeccions(final SeccioCriteria seccioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarSeccions(seccioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public TaxaDTO obtenirTaxa(final TaxaCriteria taxaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirTaxa(taxaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<TaxaDTO> llistarTaxes(final TaxaCriteria taxaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarTaxes(taxaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public TramitDTO obtenirTramit(final TramitCriteria tramitCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirTramit(tramitCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<TramitDTO> llistarTramits(final TramitCriteria tramitCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarTramits(tramitCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(
			final UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(
			final UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public UnitatMateriaDTO obtenirUnitatMateria(final UnitatMateriaCriteria unitatMateriaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirUnitatMateria(unitatMateriaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<UnitatMateriaDTO> llistarUnitatsMateries(final UnitatMateriaCriteria unitatMateriaCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarUnitatsMateries(unitatMateriaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public UsuariDTO obtenirUsuari(final UsuariCriteria usuariCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirUsuari(usuariCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<UsuariDTO> llistarUsuaris(final UsuariCriteria usuariCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarUsuaris(usuariCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public IdiomaDTO obtenirIdioma(final IdiomaCriteria idiomaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirIdioma(idiomaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<IdiomaDTO> llistarIdiomes(final IdiomaCriteria idiomaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarIdiomes(idiomaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public TipusDTO obtenirTipus(final TipusCriteria tipusCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirTipus(tipusCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<TipusDTO> llistarTipus(final TipusCriteria tipusCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarTipus(tipusCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public TipusAfectacioDTO obtenirTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirTipusAfectacio(tipusAfectacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<TipusAfectacioDTO> llistarTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarTipusAfectacio(tipusAfectacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public IniciacioDTO obtenirTipusIniciacio(final IniciacioCriteria iniciacioCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirTipusIniciacio(iniciacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public List<IniciacioDTO> llistarTipusIniciacions(final IniciacioCriteria iniciacioCriteria)
			throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.llistarTipusIniciacions(iniciacioCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public Integer getNumFitxes(final FitxaCriteria fitxaCriteria) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.getNumFitxes(fitxaCriteria);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

	@Override
	public SilencioDTO obtenirSilenci(final Long codSilencio, final String idioma) throws StrategyException {
		try {
			return rolsacQueryServiceDelegate.obtenirSilenci(codSilencio, idioma);
		} catch (final DelegateException e) {
			throw new StrategyException(e);
		}
	}

}
