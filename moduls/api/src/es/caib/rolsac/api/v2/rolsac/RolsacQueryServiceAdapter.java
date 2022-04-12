package es.caib.rolsac.api.v2.rolsac;


import java.util.ArrayList;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoCriteria;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoDTO;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceAdapter;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariDTO;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.idioma.IdiomaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.plataforma.PlataformaCriteria;
import es.caib.rolsac.api.v2.plataforma.PlataformaDTO;
import es.caib.rolsac.api.v2.plantilla.PlantillaCriteria;
import es.caib.rolsac.api.v2.plantilla.PlantillaDTO;
import es.caib.rolsac.api.v2.plataforma.PlataformaQueryServiceAdapter;
import es.caib.rolsac.api.v2.plantilla.PlantillaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
import es.caib.rolsac.api.v2.silencio.SilencioQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public class RolsacQueryServiceAdapter implements RolsacQueryService {

	private RolsacQueryServiceStrategy rolsacQueryServiceStrategy;

	private String rolsacUrl;

	public RolsacQueryServiceAdapter() {
		super();
	}

	public RolsacQueryServiceAdapter(final String rolsacUrl) {
		super();
		this.rolsacUrl = rolsacUrl;
	}

	public void setRolsacQueryServiceStrategy(final RolsacQueryServiceStrategy rolsacQueryServiceStrategy) {
		this.rolsacQueryServiceStrategy = rolsacQueryServiceStrategy;
		rolsacQueryServiceStrategy.setUrl(rolsacUrl);
	}

	private STRATEGY getStrategy() {
		return rolsacQueryServiceStrategy instanceof RolsacQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
	}

	@Override
	public ProcedimentQueryServiceAdapter obtenirProcediment(final ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException {
		try {
			final ProcedimentDTO dto = rolsacQueryServiceStrategy.obtenirProcediment(procedimentCriteria);
			final ProcedimentQueryServiceAdapter pqsa = (ProcedimentQueryServiceAdapter) BeanUtils
					.getAdapter("procediment", getStrategy(), dto);

			if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
				pqsa.setRolsacUrl(rolsacUrl);
			}
			return pqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "procedimiento.", e);
		}
	}

	@Override
	public List<ProcedimentQueryServiceAdapter> llistarProcediments(final ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException {
		try {
			final List<ProcedimentDTO> llistaDTO = rolsacQueryServiceStrategy.llistarProcediments(procedimentCriteria);
			final List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
			for (final ProcedimentDTO procedimentDTO : llistaDTO) {
				final ProcedimentQueryServiceAdapter procedimiento = (ProcedimentQueryServiceAdapter) BeanUtils
						.getAdapter("procediment", getStrategy(), procedimentDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && procedimiento != null) {
					procedimiento.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(procedimiento);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
		}
	}

	@Override
	public ServicioQueryServiceAdapter obtenirServicio(final ServicioCriteria servicioCriteria)
			throws QueryServiceException {
		try {
			final ServicioDTO dto = rolsacQueryServiceStrategy.obtenirServicio(servicioCriteria);
			final ServicioQueryServiceAdapter pqsa = (ServicioQueryServiceAdapter) BeanUtils.getAdapter("servicio",
					getStrategy(), dto);

			if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
				pqsa.setRolsacUrl(rolsacUrl);
			}
			return pqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "servicio.", e);
		}
	}

	@Override
	public List<ServicioQueryServiceAdapter> llistarServicios(final ServicioCriteria servicioCriteria)
			throws QueryServiceException {
		try {
			final List<ServicioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarServicios(servicioCriteria);
			final List<ServicioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ServicioQueryServiceAdapter>();
			for (final ServicioDTO servicioDTO : llistaDTO) {
				final ServicioQueryServiceAdapter servicio = (ServicioQueryServiceAdapter) BeanUtils
						.getAdapter("servicio", getStrategy(), servicioDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && servicio != null) {
					servicio.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(servicio);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "servicio.", e);
		}
	}

	@Override
	public CatalegDocumentsQueryServiceAdapter obtenirCatalegDocuments(
			final CatalegDocumentsCriteria catalegDocumentsCriteria) throws QueryServiceException {
		try {
			final CatalegDocumentsDTO dto = rolsacQueryServiceStrategy
					.obtenirCatalegDocuments(catalegDocumentsCriteria);
			final CatalegDocumentsQueryServiceAdapter cdqsa = (CatalegDocumentsQueryServiceAdapter) BeanUtils
					.getAdapter("catalegDocuments", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && cdqsa != null) {
				cdqsa.setRolsacUrl(rolsacUrl);
			}
			return cdqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "catálogo de documentos.", e);
		}
	}

	@Override
	public List<CatalegDocumentsQueryServiceAdapter> llistarCatalegsDocuments(
			final CatalegDocumentsCriteria catalegDocumentsCriteria) throws QueryServiceException {
		try {
			final List<CatalegDocumentsDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarCatalegsDocuments(catalegDocumentsCriteria);
			final List<CatalegDocumentsQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<CatalegDocumentsQueryServiceAdapter>();
			for (final CatalegDocumentsDTO catalegDocumentsDTO : llistaDTO) {
				final CatalegDocumentsQueryServiceAdapter cdqsa = (CatalegDocumentsQueryServiceAdapter) BeanUtils
						.getAdapter("catalegDocuments", getStrategy(), catalegDocumentsDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && cdqsa != null) {
					cdqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(cdqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "catálogos de documentos.", e);
		}
	}

	@Override
	public ExcepcioDocumentacioQueryServiceAdapter obtenirExcepcioDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws QueryServiceException {
		try {
			final ExcepcioDocumentacioDTO dto = rolsacQueryServiceStrategy
					.obtenirExcepcioDocumentacio(excepcioDocumentacioCriteria);
			final ExcepcioDocumentacioQueryServiceAdapter eqsa = (ExcepcioDocumentacioQueryServiceAdapter) BeanUtils
					.getAdapter("excepcioDocumentacio", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
				eqsa.setRolsacUrl(rolsacUrl);
			}
			return eqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "excepción de documentación.", e);
		}
	}

	@Override
	public List<ExcepcioDocumentacioQueryServiceAdapter> llistarExcepcionsDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws QueryServiceException {
		try {
			final List<ExcepcioDocumentacioDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
			final List<ExcepcioDocumentacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ExcepcioDocumentacioQueryServiceAdapter>();
			for (final ExcepcioDocumentacioDTO excepcioDocumentacioDTO : llistaDTO) {
				final ExcepcioDocumentacioQueryServiceAdapter eqsa = (ExcepcioDocumentacioQueryServiceAdapter) BeanUtils
						.getAdapter("excepcioDocumentacio", getStrategy(), excepcioDocumentacioDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
					eqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(eqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "excepciones de documentación.", e);
		}
	}

	@Override
	public int getNumProcediments(final ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
		try {
			final Integer num = rolsacQueryServiceStrategy.getNumProcediments(procedimentCriteria);
			return num;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.COUNT_GETTER + "procedimientos.", e);
		}
	}

	@Override
	public int getNumServicios(final ServicioCriteria servicioCriteria) throws QueryServiceException {
		try {
			final Integer num = rolsacQueryServiceStrategy.getNumServicios(servicioCriteria);
			return num;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.COUNT_GETTER + "servicios.", e);
		}
	}

	@Override
	public AgrupacioFetVitalQueryServiceAdapter obtenirAgrupacioFetVital(
			final AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException {
		try {
			final AgrupacioFetVitalDTO dto = rolsacQueryServiceStrategy
					.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
			final AgrupacioFetVitalQueryServiceAdapter afvqsa = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils
					.getAdapter("agrupacioFetVital", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && afvqsa != null) {
				afvqsa.setRolsacUrl(rolsacUrl);
			}
			return afvqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion hecho vital.", e);
		}
	}

	@Override
	public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacionsFetsVitals(
			final AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException {
		try {
			final List<AgrupacioFetVitalDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
			final List<AgrupacioFetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
			for (final AgrupacioFetVitalDTO afvDTO : llistaDTO) {
				final AgrupacioFetVitalQueryServiceAdapter afvqsa = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils
						.getAdapter("agrupacioFetVital", getStrategy(), afvDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && afvqsa != null) {
					afvqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(afvqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones hecho vital.", e);
		}
	}

	@Override
	public AgrupacioMateriaQueryServiceAdapter obtenirAgrupacioMateria(
			final AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException {
		try {
			final AgrupacioMateriaDTO dto = rolsacQueryServiceStrategy
					.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
			final AgrupacioMateriaQueryServiceAdapter amqsa = (AgrupacioMateriaQueryServiceAdapter) BeanUtils
					.getAdapter("agrupacioMateria", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && amqsa != null) {
				amqsa.setRolsacUrl(rolsacUrl);
			}
			return amqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion materia.", e);
		}

	}

	@Override
	public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacionsMateries(
			final AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException {
		try {
			final List<AgrupacioMateriaDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
			final List<AgrupacioMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioMateriaQueryServiceAdapter>();
			for (final AgrupacioMateriaDTO amDTO : llistaDTO) {
				final AgrupacioMateriaQueryServiceAdapter amqsa = (AgrupacioMateriaQueryServiceAdapter) BeanUtils
						.getAdapter("agrupacioMateria", getStrategy(), amDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && amqsa != null) {
					amqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(amqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
		}
	}

	@Override
	public ButlletiQueryServiceAdapter obtenirButlleti(final ButlletiCriteria butlletiCriteria)
			throws QueryServiceException {
		try {
			final ButlletiDTO dto = rolsacQueryServiceStrategy.obtenirButlleti(butlletiCriteria);
			final ButlletiQueryServiceAdapter bqsa = (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && bqsa != null) {
				bqsa.setRolsacUrl(rolsacUrl);
			}
			return bqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "boletin.", e);
		}
	}

	@Override
	public List<ButlletiQueryServiceAdapter> llistarButlletins(final ButlletiCriteria butlletiCriteria)
			throws QueryServiceException {
		try {
			final List<ButlletiDTO> llistaDTO = rolsacQueryServiceStrategy.llistarButlletins(butlletiCriteria);
			final List<ButlletiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ButlletiQueryServiceAdapter>();
			for (final ButlletiDTO butlletiDTO : llistaDTO) {
				final ButlletiQueryServiceAdapter bqsa = (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti",
						getStrategy(), butlletiDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && bqsa != null) {
					bqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(bqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
		}
	}

	@Override
	public DocumentQueryServiceAdapter obtenirDocument(final DocumentCriteria documentCriteria)
			throws QueryServiceException {
		try {
			final DocumentDTO dto = rolsacQueryServiceStrategy.obtenirDocument(documentCriteria);
			final DocumentQueryServiceAdapter dqsa = (DocumentQueryServiceAdapter) BeanUtils.getAdapter("document",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && dqsa != null) {
				dqsa.setRolsacUrl(rolsacUrl);
			}
			return dqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "boletin.", e);
		}
	}

	@Override
	public List<DocumentQueryServiceAdapter> llistarDocuments(final DocumentCriteria documentCriteria)
			throws QueryServiceException {
		try {
			final List<DocumentDTO> llistaDTO = rolsacQueryServiceStrategy.llistarDocuments(documentCriteria);
			final List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
			for (final DocumentDTO documentDTO : llistaDTO) {
				final DocumentQueryServiceAdapter dqsa = (DocumentQueryServiceAdapter) BeanUtils.getAdapter("document",
						getStrategy(), documentDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && dqsa != null) {
					dqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(dqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "boletines.", e);
		}
	}

	@Override
	public DocumentTramitQueryServiceAdapter obtenirDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException {
		try {
			final DocumentTramitDTO dto = rolsacQueryServiceStrategy.obtenirDocumentTramit(documentTramitCriteria);
			final DocumentTramitQueryServiceAdapter tdqsa = (DocumentTramitQueryServiceAdapter) BeanUtils
					.getAdapter("documentTramit", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && tdqsa != null) {
				tdqsa.setRolsacUrl(rolsacUrl);
			}
			return tdqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "documento tramite.", e);
		}
	}

	@Override
	public List<DocumentoNormativaQueryServiceAdapter> llistarDocumentoNormativa(
			final DocumentoNormativaCriteria documentoNormativaCriteria) throws QueryServiceException {
		try {
			final List<DocumentoNormativaDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarDocumentoNormativa(documentoNormativaCriteria);
			final List<DocumentoNormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentoNormativaQueryServiceAdapter>();
			for (final DocumentoNormativaDTO documentoNormativaDTO : llistaDTO) {
				final DocumentoNormativaQueryServiceAdapter tdqsa = (DocumentoNormativaQueryServiceAdapter) BeanUtils
						.getAdapter("documentoNormativa", getStrategy(), documentoNormativaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && tdqsa != null) {
					tdqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(tdqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos tramite.", e);
		}
	}

	@Override
	public List<DocumentoServicioQueryServiceAdapter> llistarDocumentoServicio(
			final DocumentoServicioCriteria documentoServicioCriteria) throws QueryServiceException {
		try {
			final List<DocumentoServicioDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarDocumentoServicio(documentoServicioCriteria);
			final List<DocumentoServicioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentoServicioQueryServiceAdapter>();
			for (final DocumentoServicioDTO documentoServicioDTO : llistaDTO) {
				final DocumentoServicioQueryServiceAdapter tdqsa = (DocumentoServicioQueryServiceAdapter) BeanUtils
						.getAdapter("documentoServicio", getStrategy(), documentoServicioDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && tdqsa != null) {
					tdqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(tdqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos tramite.", e);
		}
	}

	@Override
	public List<DocumentTramitQueryServiceAdapter> llistarDocumentTramit(
			final DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException {
		try {
			final List<DocumentTramitDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarDocumentTramit(documentTramitCriteria);
			final List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
			for (final DocumentTramitDTO documentTramitDTO : llistaDTO) {
				final DocumentTramitQueryServiceAdapter tdqsa = (DocumentTramitQueryServiceAdapter) BeanUtils
						.getAdapter("documentTramit", getStrategy(), documentTramitDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && tdqsa != null) {
					tdqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(tdqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos tramite.", e);
		}
	}

	@Override
	public EdificiQueryServiceAdapter obtenirEdifici(final EdificiCriteria edificiCriteria)
			throws QueryServiceException {
		try {
			final EdificiDTO dto = rolsacQueryServiceStrategy.obtenirEdifici(edificiCriteria);
			final EdificiQueryServiceAdapter eqsa = (EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
				eqsa.setRolsacUrl(rolsacUrl);
			}
			return eqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "edificio.", e);
		}
	}

	@Override
	public List<EdificiQueryServiceAdapter> llistarEdificis(final EdificiCriteria edificiCriteria)
			throws QueryServiceException {
		try {
			final List<EdificiDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEdificis(edificiCriteria);
			final List<EdificiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EdificiQueryServiceAdapter>();
			for (final EdificiDTO edificiDTO : llistaDTO) {
				final EdificiQueryServiceAdapter eqsa = (EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici",
						getStrategy(), edificiDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
					eqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(eqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "edificios.", e);
		}
	}

	@Override
	public EnllacQueryServiceAdapter obtenirEnllac(final EnllacCriteria enllacCriteria) throws QueryServiceException {
		try {
			final EnllacDTO dto = rolsacQueryServiceStrategy.obtenirEnllac(enllacCriteria);
			final EnllacQueryServiceAdapter eqsa = (EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
				eqsa.setRolsacUrl(rolsacUrl);
			}
			return eqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "enlace.", e);
		}
	}

	@Override
	public EnllacTelematicoQueryServiceAdapter obtenirEnllacTelematico(final EnllacTelematicoCriteria enllacCriteria)
			throws QueryServiceException {
		try {
			final EnllacTelematicoDTO dto = rolsacQueryServiceStrategy.obtenirEnllacTelematico(enllacCriteria);
			final EnllacTelematicoQueryServiceAdapter eqsa = (EnllacTelematicoQueryServiceAdapter) BeanUtils
					.getAdapter("enllacTelematico", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
				eqsa.setRolsacUrl(rolsacUrl);
			}
			return eqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "enlace.", e);
		}
	}

	@Override
	public List<EnllacQueryServiceAdapter> llistarEnllacos(final EnllacCriteria enllacCriteria)
			throws QueryServiceException {
		try {
			final List<EnllacDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEnllacos(enllacCriteria);
			final List<EnllacQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EnllacQueryServiceAdapter>();
			for (final EnllacDTO enllacDTO : llistaDTO) {
				final EnllacQueryServiceAdapter eqsa = (EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac",
						getStrategy(), enllacDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
					eqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(eqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "enlaces.", e);
		}
	}

	@Override
	public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial(
			final EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException {
		try {
			final EspaiTerritorialDTO dto = rolsacQueryServiceStrategy
					.obtenirEspaiTerritorial(espaiTerritorialCriteria);
			final EspaiTerritorialQueryServiceAdapter eqsa = (EspaiTerritorialQueryServiceAdapter) BeanUtils
					.getAdapter("espaiTerritorial", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
				eqsa.setRolsacUrl(rolsacUrl);
			}
			return eqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "espacio territorial.", e);
		}
	}

	@Override
	public List<EspaiTerritorialQueryServiceAdapter> llistarEspaisTerritorials(
			final EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException {
		try {
			final List<EspaiTerritorialDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarEspaisTerritorials(espaiTerritorialCriteria);
			final List<EspaiTerritorialQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EspaiTerritorialQueryServiceAdapter>();
			for (final EspaiTerritorialDTO espaiTerritorialDTO : llistaDTO) {
				final EspaiTerritorialQueryServiceAdapter eqsa = (EspaiTerritorialQueryServiceAdapter) BeanUtils
						.getAdapter("espaiTerritorial", getStrategy(), espaiTerritorialDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) {
					eqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(eqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "espacios territoriales.", e);
		}
	}

	@Override
	public FamiliaQueryServiceAdapter obtenirFamilia(final FamiliaCriteria familiaCriteria)
			throws QueryServiceException {
		try {
			final FamiliaDTO dto = rolsacQueryServiceStrategy.obtenirFamilia(familiaCriteria);
			final FamiliaQueryServiceAdapter fqsa = (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "familia.", e);
		}
	}

	@Override
	public List<FamiliaQueryServiceAdapter> llistarFamilies(final FamiliaCriteria familiaCriteria)
			throws QueryServiceException {
		try {
			final List<FamiliaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFamilies(familiaCriteria);
			final List<FamiliaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FamiliaQueryServiceAdapter>();
			for (final FamiliaDTO familiaDTO : llistaDTO) {
				final FamiliaQueryServiceAdapter fqsa = (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia",
						getStrategy(), familiaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
					fqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(fqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "familias.", e);
		}
	}

	@Override
	public PlataformaQueryServiceAdapter obtenirPlataforma(final PlataformaCriteria plataformaCriteria)
			throws QueryServiceException {
		try {
			final PlataformaDTO dto = rolsacQueryServiceStrategy.obtenirPlataforma(plataformaCriteria);
			final PlataformaQueryServiceAdapter fqsa = (PlataformaQueryServiceAdapter) BeanUtils
					.getAdapter("plataforma", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "plataforma.", e);
		}
	}

	@Override
	public PlantillaQueryServiceAdapter obtenirPlantilla(final PlantillaCriteria plantillaCriteria)
			throws QueryServiceException {
		try {
			final PlantillaDTO dto = rolsacQueryServiceStrategy.obtenirPlantilla(plantillaCriteria);
			final PlantillaQueryServiceAdapter fqsa = (PlantillaQueryServiceAdapter) BeanUtils.getAdapter("plantilla",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "plantilla.", e);
		}
	}

	@Override
	public FetVitalQueryServiceAdapter obtenirFetVital(final FetVitalCriteria fetVitalCriteria)
			throws QueryServiceException {
		try {
			final FetVitalDTO dto = rolsacQueryServiceStrategy.obtenirFetVital(fetVitalCriteria);
			final FetVitalQueryServiceAdapter fqsa = (FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "hecho vital.", e);
		}
	}

	@Override
	public List<FetVitalQueryServiceAdapter> llistarFetsVitals(final FetVitalCriteria fetVitalCriteria)
			throws QueryServiceException {
		try {
			final List<FetVitalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFetsVitals(fetVitalCriteria);
			final List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
			for (final FetVitalDTO fetVitalDTO : llistaDTO) {
				final FetVitalQueryServiceAdapter fqsa = (FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital",
						getStrategy(), fetVitalDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
					fqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(fqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hechos vitales.", e);
		}
	}

	@Override
	public FitxaQueryServiceAdapter obtenirFitxa(final FitxaCriteria fitxaCriteria) throws QueryServiceException {
		try {
			final FitxaDTO dto = rolsacQueryServiceStrategy.obtenirFitxa(fitxaCriteria);
			final FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "ficha.", e);
		}
	}

	@Override
	public List<FitxaQueryServiceAdapter> llistarFitxes(final FitxaCriteria fitxaCriteria)
			throws QueryServiceException {
		try {
			final List<FitxaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFitxes(fitxaCriteria);
			final List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
			for (final FitxaDTO fitxaDTO : llistaDTO) {
				final FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa",
						getStrategy(), fitxaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
					fqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(fqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
		}
	}

	@Override
	public FitxaUAQueryServiceAdapter obtenirFitxaUA(final FitxaUACriteria fitxaUACriteria)
			throws QueryServiceException {
		try {
			final FitxaUADTO dto = rolsacQueryServiceStrategy.obtenirFitxaUA(fitxaUACriteria);
			final FitxaUAQueryServiceAdapter fqsa = (FitxaUAQueryServiceAdapter) BeanUtils.getAdapter("fitxaUA",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "fichaUA.", e);
		}
	}

	@Override
	public List<FitxaUAQueryServiceAdapter> llistarFitxesUA(final FitxaUACriteria fitxaUACriteria)
			throws QueryServiceException {
		try {
			final List<FitxaUADTO> llistaDTO = rolsacQueryServiceStrategy.llistarFitxesUA(fitxaUACriteria);
			final List<FitxaUAQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaUAQueryServiceAdapter>();
			for (final FitxaUADTO fitxaUADTO : llistaDTO) {
				final FitxaUAQueryServiceAdapter fqsa = (FitxaUAQueryServiceAdapter) BeanUtils.getAdapter("fitxaUA",
						getStrategy(), fitxaUADTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
					fqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(fqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichasUA.", e);
		}
	}

	@Override
	public FormulariQueryServiceAdapter obtenirFormulari(final FormulariCriteria formulariCriteria)
			throws QueryServiceException {
		try {
			final FormulariDTO dto = rolsacQueryServiceStrategy.obtenirFormulari(formulariCriteria);
			final FormulariQueryServiceAdapter fqsa = (FormulariQueryServiceAdapter) BeanUtils.getAdapter("formulari",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "formulario.", e);
		}
	}

	@Override
	public List<FormulariQueryServiceAdapter> llistarFormularis(final FormulariCriteria formulariCriteria)
			throws QueryServiceException {
		try {
			final List<FormulariDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFormularis(formulariCriteria);
			final List<FormulariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FormulariQueryServiceAdapter>();
			for (final FormulariDTO formDTO : llistaDTO) {
				final FormulariQueryServiceAdapter fqsa = (FormulariQueryServiceAdapter) BeanUtils
						.getAdapter("formulari", getStrategy(), formDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
					fqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(fqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "formularios.", e);
		}

	}

	@Override
	public IconaFamiliaQueryServiceAdapter obtenirIconaFamilia(final IconaFamiliaCriteria iconaFamiliaCriteria)
			throws QueryServiceException {
		try {
			final IconaFamiliaDTO dto = rolsacQueryServiceStrategy.obtenirIconaFamilia(iconaFamiliaCriteria);
			final IconaFamiliaQueryServiceAdapter fqsa = (IconaFamiliaQueryServiceAdapter) BeanUtils
					.getAdapter("iconaFamilia", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
				fqsa.setRolsacUrl(rolsacUrl);
			}
			return fqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono familia.", e);
		}
	}

	@Override
	public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilies(final IconaFamiliaCriteria iconaFamiliaCriteria)
			throws QueryServiceException {
		try {
			final List<IconaFamiliaDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarIconesFamilies(iconaFamiliaCriteria);
			final List<IconaFamiliaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaFamiliaQueryServiceAdapter>();
			for (final IconaFamiliaDTO ifDTO : llistaDTO) {
				final IconaFamiliaQueryServiceAdapter fqsa = (IconaFamiliaQueryServiceAdapter) BeanUtils
						.getAdapter("iconaFamilia", getStrategy(), ifDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) {
					fqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(fqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos familia.", e);
		}
	}

	@Override
	public IconaMateriaQueryServiceAdapter obtenirIconaMateria(final IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException {
		try {
			final IconaMateriaDTO dto = rolsacQueryServiceStrategy.obtenirIconaMateria(iconaMateriaCriteria);
			final IconaMateriaQueryServiceAdapter iqsa = (IconaMateriaQueryServiceAdapter) BeanUtils
					.getAdapter("iconaMateria", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) {
				iqsa.setRolsacUrl(rolsacUrl);
			}
			return iqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono materia.", e);
		}
	}

	@Override
	public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(final IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException {
		try {
			final List<IconaMateriaDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarIconesMateries(iconaMateriaCriteria);
			final List<IconaMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaMateriaQueryServiceAdapter>();
			for (final IconaMateriaDTO imDTO : llistaDTO) {
				final IconaMateriaQueryServiceAdapter iqsa = (IconaMateriaQueryServiceAdapter) BeanUtils
						.getAdapter("iconaMateria", getStrategy(), imDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) {
					iqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(iqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos materia.", e);
		}
	}

	@Override
	public MateriaQueryServiceAdapter obtenirMateria(final MateriaCriteria materiaCriteria)
			throws QueryServiceException {
		try {
			final MateriaDTO dto = rolsacQueryServiceStrategy.obtenirMateria(materiaCriteria);
			final MateriaQueryServiceAdapter mqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) {
				mqsa.setRolsacUrl(rolsacUrl);
			}
			return mqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
		}
	}

	@Override
	public List<MateriaQueryServiceAdapter> llistarMateries(final MateriaCriteria materiaCriteria)
			throws QueryServiceException {
		try {
			final List<MateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarMateries(materiaCriteria);
			final List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
			for (final MateriaDTO materiaDTO : llistaDTO) {
				final MateriaQueryServiceAdapter mqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia",
						getStrategy(), materiaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) {
					mqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(mqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
		}
	}

	@Override
	public MateriaAgrupacioQueryServiceAdapter obtenirMateriaAgrupacio(
			final MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws QueryServiceException {
		try {
			final MateriaAgrupacioDTO dto = rolsacQueryServiceStrategy
					.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
			final MateriaAgrupacioQueryServiceAdapter mqsa = (MateriaAgrupacioQueryServiceAdapter) BeanUtils
					.getAdapter("materiaAgrupacio", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) {
				mqsa.setRolsacUrl(rolsacUrl);
			}
			return mqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion materia.", e);
		}
	}

	@Override
	public List<MateriaAgrupacioQueryServiceAdapter> llistarMateriesAgrupacions(
			final MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws QueryServiceException {
		try {
			final List<MateriaAgrupacioDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
			final List<MateriaAgrupacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaAgrupacioQueryServiceAdapter>();
			for (final MateriaAgrupacioDTO maDTO : llistaDTO) {
				final MateriaAgrupacioQueryServiceAdapter mqsa = (MateriaAgrupacioQueryServiceAdapter) BeanUtils
						.getAdapter("materiaAgrupacio", getStrategy(), maDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) {
					mqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(mqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
		}
	}

	@Override
	public NormativaQueryServiceAdapter obtenirNormativa(final NormativaCriteria normativaCriteria)
			throws QueryServiceException {
		try {
			final NormativaDTO dto = rolsacQueryServiceStrategy.obtenirNormativa(normativaCriteria);
			final NormativaQueryServiceAdapter nqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && nqsa != null) {
				nqsa.setRolsacUrl(rolsacUrl);
			}
			return nqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa.", e);
		}
	}

	@Override
	public List<NormativaQueryServiceAdapter> llistarNormatives(final NormativaCriteria normativaCriteria)
			throws QueryServiceException {
		try {
			final List<NormativaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarNormatives(normativaCriteria);
			final List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
			for (final NormativaDTO normativaDTO : llistaDTO) {
				final NormativaQueryServiceAdapter nqsa = (NormativaQueryServiceAdapter) BeanUtils
						.getAdapter("normativa", getStrategy(), normativaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && nqsa != null) {
					nqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(nqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
		}
	}

	@Override
	public PerfilQueryServiceAdapter obtenirPerfil(final PerfilCriteria perfilCriteria) throws QueryServiceException {
		try {
			final PerfilDTO dto = rolsacQueryServiceStrategy.obtenirPerfil(perfilCriteria);
			final PerfilQueryServiceAdapter pqsa = (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
				pqsa.setRolsacUrl(rolsacUrl);
			}
			return pqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "perfil.", e);
		}
	}

	@Override
	public List<PerfilQueryServiceAdapter> llistarPerfils(final PerfilCriteria perfilCriteria)
			throws QueryServiceException {
		try {
			final List<PerfilDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPerfils(perfilCriteria);
			final List<PerfilQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PerfilQueryServiceAdapter>();
			for (final PerfilDTO perfilDTO : llistaDTO) {
				final PerfilQueryServiceAdapter pqsa = (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil",
						getStrategy(), perfilDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
					pqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(pqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "perfiles.", e);
		}
	}

	@Override
	public PersonalQueryServiceAdapter obtenirPersonal(final PersonalCriteria personalCriteria)
			throws QueryServiceException {
		try {
			final PersonalDTO dto = rolsacQueryServiceStrategy.obtenirPersonal(personalCriteria);
			final PersonalQueryServiceAdapter pqsa = (PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
				pqsa.setRolsacUrl(rolsacUrl);
			}
			return pqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "personal.", e);
		}
	}

	@Override
	public List<PersonalQueryServiceAdapter> llistarPersonal(final PersonalCriteria personalCriteria)
			throws QueryServiceException {
		try {
			final List<PersonalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPersonal(personalCriteria);
			final List<PersonalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PersonalQueryServiceAdapter>();
			for (final PersonalDTO personalDTO : llistaDTO) {
				final PersonalQueryServiceAdapter pqsa = (PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal",
						getStrategy(), personalDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
					pqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(pqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "personal.", e);
		}
	}

	@Override
	public PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws QueryServiceException {
		try {
			final PublicObjectiuDTO dto = rolsacQueryServiceStrategy.obtenirPublicObjectiu(publicObjectiuCriteria);
			final PublicObjectiuQueryServiceAdapter pqsa = (PublicObjectiuQueryServiceAdapter) BeanUtils
					.getAdapter("publicObjectiu", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
				pqsa.setRolsacUrl(rolsacUrl);
			}
			return pqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "publico objetivo.", e);
		}
	}

	@Override
	public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(
			final PublicObjectiuCriteria publicObjectiuCriteria) throws QueryServiceException {
		try {
			final List<PublicObjectiuDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarPublicsObjectius(publicObjectiuCriteria);
			final List<PublicObjectiuQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PublicObjectiuQueryServiceAdapter>();
			for (final PublicObjectiuDTO poDTO : llistaDTO) {
				final PublicObjectiuQueryServiceAdapter pqsa = (PublicObjectiuQueryServiceAdapter) BeanUtils
						.getAdapter("publicObjectiu", getStrategy(), poDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) {
					pqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(pqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "publico objetivo.", e);
		}
	}

	@Override
	public TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws QueryServiceException {
		try {
			final TipusAfectacioDTO dto = rolsacQueryServiceStrategy.obtenirTipusAfectacio(tipusAfectacioCriteria);
			return (TipusAfectacioQueryServiceAdapter) BeanUtils.getAdapter("tipusAfectacio", getStrategy(), dto);
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tipo afectacion.", e);
		}
	}

	@Override
	public List<TipusAfectacioQueryServiceAdapter> llistarTipusAfectacio(
			final TipusAfectacioCriteria tipusAfectacioCriteria) throws QueryServiceException {
		try {
			final List<TipusAfectacioDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarTipusAfectacio(tipusAfectacioCriteria);
			final List<TipusAfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TipusAfectacioQueryServiceAdapter>();
			for (final TipusAfectacioDTO taDTO : llistaDTO) {
				llistaQueryServiceAdapter.add((TipusAfectacioQueryServiceAdapter) BeanUtils.getAdapter("tipusAfectacio",
						getStrategy(), taDTO));
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tipos de afectaciones.", e);
		}
	}

	@Override
	public SeccioQueryServiceAdapter obtenirSeccio(final SeccioCriteria seccioCriteria) throws QueryServiceException {
		try {
			final SeccioDTO dto = rolsacQueryServiceStrategy.obtenirSeccio(seccioCriteria);
			final SeccioQueryServiceAdapter sqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && sqsa != null) {
				sqsa.setRolsacUrl(rolsacUrl);
			}
			return sqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "seccion.", e);
		}
	}

	@Override
	public List<SeccioQueryServiceAdapter> llistarSeccions(final SeccioCriteria seccioCriteria)
			throws QueryServiceException {
		try {
			final List<SeccioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarSeccions(seccioCriteria);
			final List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
			for (final SeccioDTO poDTO : llistaDTO) {
				final SeccioQueryServiceAdapter sqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio",
						getStrategy(), poDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && sqsa != null) {
					sqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(sqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones.", e);
		}
	}

	@Override
	public TaxaQueryServiceAdapter obtenirTaxa(final TaxaCriteria taxaCriteria) throws QueryServiceException {
		try {
			final TaxaDTO dto = rolsacQueryServiceStrategy.obtenirTaxa(taxaCriteria);
			final TaxaQueryServiceAdapter tqsa = (TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(),
					dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) {
				tqsa.setRolsacUrl(rolsacUrl);
			}
			return tqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tasa.", e);
		}
	}

	@Override
	public List<TaxaQueryServiceAdapter> llistarTaxes(final TaxaCriteria taxaCriteria) throws QueryServiceException {
		List<TaxaDTO> llistaDTO;
		try {
			llistaDTO = rolsacQueryServiceStrategy.llistarTaxes(taxaCriteria);
			final List<TaxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TaxaQueryServiceAdapter>();
			for (final TaxaDTO taxaDTO : llistaDTO) {
				final TaxaQueryServiceAdapter tqsa = (TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa",
						getStrategy(), taxaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) {
					tqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(tqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tasas.", e);
		}
	}

	@Override
	public TipusQueryServiceAdapter obtenirTipus(final TipusCriteria tipusCriteria) throws QueryServiceException {
		try {
			final TipusDTO dto = rolsacQueryServiceStrategy.obtenirTipus(tipusCriteria);
			final TipusQueryServiceAdapter tqsa = (TipusQueryServiceAdapter) BeanUtils.getAdapter("tipus",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) {
				tqsa.setRolsacUrl(rolsacUrl);
			}
			return tqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tipo.", e);
		}
	}

	@Override
	public List<TipusQueryServiceAdapter> llistarTipus(final TipusCriteria tipusCriteria) throws QueryServiceException {
		try {
			final List<TipusDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTipus(tipusCriteria);
			final List<TipusQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TipusQueryServiceAdapter>();
			for (final TipusDTO tipusDTO : llistaDTO) {
				final TipusQueryServiceAdapter tqsa = (TipusQueryServiceAdapter) BeanUtils.getAdapter("tipus",
						getStrategy(), tipusDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) {
					tqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(tqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tipos.", e);
		}
	}

	@Override
	public TramitQueryServiceAdapter obtenirTramit(final TramitCriteria tramitCriteria) throws QueryServiceException {
		try {
			final TramitDTO dto = rolsacQueryServiceStrategy.obtenirTramit(tramitCriteria);
			final TramitQueryServiceAdapter tqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) {
				tqsa.setRolsacUrl(rolsacUrl);
			}
			return tqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
		}
	}

	@Override
	public List<TramitQueryServiceAdapter> llistarTramits(final TramitCriteria tramitCriteria)
			throws QueryServiceException {
		try {
			final List<TramitDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTramits(tramitCriteria);
			final List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
			for (final TramitDTO tramitDTO : llistaDTO) {
				final TramitQueryServiceAdapter tqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit",
						getStrategy(), tramitDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) {
					tqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(tqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tramites.", e);
		}
	}

	@Override
	public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa(
			final UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
		try {
			final UnitatAdministrativaDTO dto = rolsacQueryServiceStrategy
					.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
			final UnitatAdministrativaQueryServiceAdapter uaqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils
					.getAdapter("unitatAdministrativa", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && uaqsa != null) {
				uaqsa.setRolsacUrl(rolsacUrl);
			}
			return uaqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
		}
	}

	@Override
	public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(
			final UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
		try {
			final List<UnitatAdministrativaDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
			final List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
			for (final UnitatAdministrativaDTO uaDTO : llistaDTO) {
				final UnitatAdministrativaQueryServiceAdapter uaqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils
						.getAdapter("unitatAdministrativa", getStrategy(), uaDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && uaqsa != null) {
					uaqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(uaqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
		}
	}

	@Override
	public UnitatMateriaQueryServiceAdapter obtenirUnitatMateria(final UnitatMateriaCriteria unitatMateriaCriteria)
			throws QueryServiceException {
		try {
			final UnitatMateriaDTO dto = rolsacQueryServiceStrategy.obtenirUnitatMateria(unitatMateriaCriteria);
			final UnitatMateriaQueryServiceAdapter uqsa = (UnitatMateriaQueryServiceAdapter) BeanUtils
					.getAdapter("unitatMateria", getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) {
				uqsa.setRolsacUrl(rolsacUrl);
			}
			return uqsa;
		} catch (final StrategyException e) {
			e.printStackTrace();
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad materia.", e);
		}
	}

	@Override
	public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateries(
			final UnitatMateriaCriteria unitatMateriaCriteria) throws QueryServiceException {
		try {
			final List<UnitatMateriaDTO> llistaDTO = rolsacQueryServiceStrategy
					.llistarUnitatsMateries(unitatMateriaCriteria);
			final List<UnitatMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatMateriaQueryServiceAdapter>();
			for (final UnitatMateriaDTO umDTO : llistaDTO) {
				final UnitatMateriaQueryServiceAdapter uqsa = (UnitatMateriaQueryServiceAdapter) BeanUtils
						.getAdapter("unitatMateria", getStrategy(), umDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) {
					uqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(uqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades materia.", e);
		}
	}

	@Override
	public UsuariQueryServiceAdapter obtenirUsuari(final UsuariCriteria usuariCriteria) throws QueryServiceException {
		try {
			final UsuariDTO dto = rolsacQueryServiceStrategy.obtenirUsuari(usuariCriteria);
			final UsuariQueryServiceAdapter uqsa = (UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) {
				uqsa.setRolsacUrl(rolsacUrl);
			}
			return uqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "usuario.", e);
		}
	}

	@Override
	public List<UsuariQueryServiceAdapter> llistarUsuaris(final UsuariCriteria usuariCriteria)
			throws QueryServiceException {
		try {
			final List<UsuariDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUsuaris(usuariCriteria);
			final List<UsuariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UsuariQueryServiceAdapter>();
			for (final UsuariDTO uDTO : llistaDTO) {
				final UsuariQueryServiceAdapter uqsa = (UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari",
						getStrategy(), uDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) {
					uqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(uqsa);
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "usuarios.", e);
		}
	}

	@Override
	public IniciacioQueryServiceAdapter obtenirTipusIniciacio(final IniciacioCriteria iniciacioCriteria)
			throws QueryServiceException {

		try {

			final IniciacioDTO dto = rolsacQueryServiceStrategy.obtenirTipusIniciacio(iniciacioCriteria);
			final IniciacioQueryServiceAdapter iqsa = (IniciacioQueryServiceAdapter) BeanUtils.getAdapter("iniciacio",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) {
				iqsa.setRolsacUrl(rolsacUrl);
			}
			return iqsa;
		} catch (final StrategyException e) {

			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "iniciacion.", e);

		}

	}

	@Override
	public List<IniciacioQueryServiceAdapter> llistarTipusIniciacions(final IniciacioCriteria iniciacioCriteria)
			throws QueryServiceException {

		try {

			final List<IniciacioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTipusIniciacions(iniciacioCriteria);
			final List<IniciacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IniciacioQueryServiceAdapter>();

			for (final IniciacioDTO uDTO : llistaDTO) {
				final IniciacioQueryServiceAdapter iqsa = (IniciacioQueryServiceAdapter) BeanUtils
						.getAdapter("iniciacio", getStrategy(), uDTO);
				if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) {
					iqsa.setRolsacUrl(rolsacUrl);
				}
				llistaQueryServiceAdapter.add(iqsa);
			}

			return llistaQueryServiceAdapter;

		} catch (final StrategyException e) {

			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "usuarios.", e);

		}

	}

	@Override
	public IdiomaQueryServiceAdapter obtenirIdioma(final IdiomaCriteria idiomaCriteria) throws QueryServiceException {
		try {
			final IdiomaDTO dto = rolsacQueryServiceStrategy.obtenirIdioma(idiomaCriteria);
			return (IdiomaQueryServiceAdapter) BeanUtils.getAdapter("idioma", getStrategy(), dto);
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "idioma.", e);
		}
	}

	@Override
	public List<IdiomaQueryServiceAdapter> llistarIdiomes(final IdiomaCriteria idiomaCriteria)
			throws QueryServiceException {
		try {
			final List<IdiomaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarIdiomes(idiomaCriteria);
			final List<IdiomaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IdiomaQueryServiceAdapter>();
			for (final IdiomaDTO uDTO : llistaDTO) {
				llistaQueryServiceAdapter
						.add((IdiomaQueryServiceAdapter) BeanUtils.getAdapter("idioma", getStrategy(), uDTO));
			}
			return llistaQueryServiceAdapter;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "idiomas.", e);
		}
	}

	@Override
	public int getNumFitxes(final FitxaCriteria fitxaCriteria) throws QueryServiceException {
		try {
			final Integer num = rolsacQueryServiceStrategy.getNumFitxes(fitxaCriteria);
			return num;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.COUNT_GETTER + "fitxas.", e);
		}
	}

	@Override
	public SilencioQueryServiceAdapter obtenirSilencio(final Long codSilencio, final String idioma)
			throws QueryServiceException {
		try {
			final SilencioDTO dto = rolsacQueryServiceStrategy.obtenirSilenci(codSilencio, idioma);
			final SilencioQueryServiceAdapter sqsa = (SilencioQueryServiceAdapter) BeanUtils.getAdapter("silencio",
					getStrategy(), dto);
			if (rolsacUrl != null && !rolsacUrl.isEmpty() && sqsa != null) {
				sqsa.setRolsacUrl(rolsacUrl);
			}
			return sqsa;
		} catch (final StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "silencio.", e);
		}
	}

}
