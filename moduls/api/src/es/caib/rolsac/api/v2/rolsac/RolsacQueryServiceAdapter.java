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
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
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
    
    public RolsacQueryServiceAdapter(String rolsacUrl) {
    	super();
    	this.rolsacUrl = rolsacUrl;
	}

	public void setRolsacQueryServiceStrategy(RolsacQueryServiceStrategy rolsacQueryServiceStrategy) {
        this.rolsacQueryServiceStrategy = rolsacQueryServiceStrategy;
        rolsacQueryServiceStrategy.setUrl(rolsacUrl);        
    }

    private STRATEGY getStrategy() {
        return rolsacQueryServiceStrategy instanceof RolsacQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ProcedimentQueryServiceAdapter obtenirProcediment(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            ProcedimentDTO dto = rolsacQueryServiceStrategy.obtenirProcediment(procedimentCriteria);
            ProcedimentQueryServiceAdapter pqsa = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
            
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { 
            	pqsa.setRolsacUrl(rolsacUrl); 
            }
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "procedimiento.", e);
        }
    }    
    
    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = rolsacQueryServiceStrategy.llistarProcediments(procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
            	ProcedimentQueryServiceAdapter procedimiento = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && procedimiento != null) { 
            		procedimiento.setRolsacUrl(rolsacUrl); 
            	}
                llistaQueryServiceAdapter.add(procedimiento);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }
    
    public CatalegDocumentsQueryServiceAdapter obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws QueryServiceException {
    	try {
    		CatalegDocumentsDTO dto = rolsacQueryServiceStrategy.obtenirCatalegDocuments(catalegDocumentsCriteria);
    		CatalegDocumentsQueryServiceAdapter cdqsa = (CatalegDocumentsQueryServiceAdapter) BeanUtils.getAdapter("catalegDocuments", getStrategy(), dto);
    		if (rolsacUrl != null && !rolsacUrl.isEmpty() && cdqsa != null) { cdqsa.setRolsacUrl(rolsacUrl); }
            return cdqsa;
    	} catch (StrategyException e) {
    		throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "catálogo de documentos.", e);
    	}
    }
    
    public List<CatalegDocumentsQueryServiceAdapter> llistarCatalegsDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws QueryServiceException {
        try {
            List<CatalegDocumentsDTO> llistaDTO = rolsacQueryServiceStrategy.llistarCatalegsDocuments(catalegDocumentsCriteria);
            List<CatalegDocumentsQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<CatalegDocumentsQueryServiceAdapter>();
            for (CatalegDocumentsDTO catalegDocumentsDTO : llistaDTO) {
            	CatalegDocumentsQueryServiceAdapter cdqsa = (CatalegDocumentsQueryServiceAdapter) BeanUtils.getAdapter("catalegDocuments", getStrategy(), catalegDocumentsDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && cdqsa != null) { 
            		cdqsa.setRolsacUrl(rolsacUrl); 
            	}		
                llistaQueryServiceAdapter.add(cdqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "catálogos de documentos.", e);
        }
    }    
    
    public ExcepcioDocumentacioQueryServiceAdapter obtenirExcepcioDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws QueryServiceException {
    	try {
    		ExcepcioDocumentacioDTO dto = rolsacQueryServiceStrategy.obtenirExcepcioDocumentacio(excepcioDocumentacioCriteria);
    		ExcepcioDocumentacioQueryServiceAdapter eqsa = (ExcepcioDocumentacioQueryServiceAdapter) BeanUtils.getAdapter("excepcioDocumentacio", getStrategy(), dto);
    		if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
            return eqsa;
    	} catch (StrategyException e) {
    		throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "excepción de documentación.", e);
    	}
    }
    
    public List<ExcepcioDocumentacioQueryServiceAdapter> llistarExcepcionsDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws QueryServiceException {
        try {
            List<ExcepcioDocumentacioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
            List<ExcepcioDocumentacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ExcepcioDocumentacioQueryServiceAdapter>();
            for (ExcepcioDocumentacioDTO excepcioDocumentacioDTO : llistaDTO) {
            	ExcepcioDocumentacioQueryServiceAdapter eqsa = (ExcepcioDocumentacioQueryServiceAdapter) BeanUtils.getAdapter("excepcioDocumentacio", getStrategy(), excepcioDocumentacioDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "excepciones de documentación.", e);
        }
    }    
    
    public int getNumProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            Integer num = rolsacQueryServiceStrategy.getNumProcediments(procedimentCriteria);
            return num;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.COUNT_GETTER + "procedimientos.", e);
        }
    }

    public AgrupacioFetVitalQueryServiceAdapter obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException {
        try {
            AgrupacioFetVitalDTO dto = rolsacQueryServiceStrategy.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            AgrupacioFetVitalQueryServiceAdapter afvqsa = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && afvqsa != null) { afvqsa.setRolsacUrl(rolsacUrl); }
            return afvqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion hecho vital.", e);
        }
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException {
        try {
            List<AgrupacioFetVitalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
            List<AgrupacioFetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
            for (AgrupacioFetVitalDTO afvDTO : llistaDTO) {
            	AgrupacioFetVitalQueryServiceAdapter afvqsa = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), afvDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && afvqsa != null) { afvqsa.setRolsacUrl(rolsacUrl); }
            	llistaQueryServiceAdapter.add(afvqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones hecho vital.", e);
        }
    }

    public AgrupacioMateriaQueryServiceAdapter obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException {
        try {
            AgrupacioMateriaDTO dto = rolsacQueryServiceStrategy.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
            AgrupacioMateriaQueryServiceAdapter amqsa = (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && amqsa != null) { amqsa.setRolsacUrl(rolsacUrl); }
            return amqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion materia.", e);
        }
        
    }

    public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException {
        try {
            List<AgrupacioMateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
            List<AgrupacioMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioMateriaQueryServiceAdapter>();
            for (AgrupacioMateriaDTO amDTO : llistaDTO) {
            	AgrupacioMateriaQueryServiceAdapter amqsa = (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), amDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && amqsa != null) { amqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(amqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public ButlletiQueryServiceAdapter obtenirButlleti(ButlletiCriteria butlletiCriteria) throws QueryServiceException {
        try {
            ButlletiDTO dto = rolsacQueryServiceStrategy.obtenirButlleti(butlletiCriteria);
            ButlletiQueryServiceAdapter bqsa = (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && bqsa != null) { bqsa.setRolsacUrl(rolsacUrl); }
            return bqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "boletin.", e);
        }
    }

    public List<ButlletiQueryServiceAdapter> llistarButlletins(ButlletiCriteria butlletiCriteria) throws QueryServiceException {
        try {
            List<ButlletiDTO> llistaDTO = rolsacQueryServiceStrategy.llistarButlletins(butlletiCriteria);
            List<ButlletiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ButlletiQueryServiceAdapter>();
            for (ButlletiDTO butlletiDTO : llistaDTO) {
            	ButlletiQueryServiceAdapter bqsa = (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), butlletiDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && bqsa != null) { bqsa.setRolsacUrl(rolsacUrl); }
                 llistaQueryServiceAdapter.add(bqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public DocumentQueryServiceAdapter obtenirDocument(DocumentCriteria documentCriteria) throws QueryServiceException {
        try {
            DocumentDTO dto = rolsacQueryServiceStrategy.obtenirDocument(documentCriteria);
            DocumentQueryServiceAdapter dqsa = (DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && dqsa != null) { dqsa.setRolsacUrl(rolsacUrl); }
            return dqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "boletin.", e);
        }
    }

    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) throws QueryServiceException {
        try {
            List<DocumentDTO> llistaDTO = rolsacQueryServiceStrategy.llistarDocuments(documentCriteria);
            List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
            for (DocumentDTO documentDTO : llistaDTO) {
            	DocumentQueryServiceAdapter dqsa = (DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO);
                if (rolsacUrl != null && !rolsacUrl.isEmpty() && dqsa != null) { dqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(dqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "boletines.", e);
        }
    }

    public DocumentTramitQueryServiceAdapter obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException {
        try {
            DocumentTramitDTO dto = rolsacQueryServiceStrategy.obtenirDocumentTramit(documentTramitCriteria);
            DocumentTramitQueryServiceAdapter tdqsa = (DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && tdqsa != null) { tdqsa.setRolsacUrl(rolsacUrl); }
            return tdqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "documento tramite.", e);
        }
    }

    public List<DocumentTramitQueryServiceAdapter> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException {
        try {
            List<DocumentTramitDTO> llistaDTO = rolsacQueryServiceStrategy.llistarDocumentTramit(documentTramitCriteria);
            List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
            for (DocumentTramitDTO documentTramitDTO : llistaDTO) {
            	DocumentTramitQueryServiceAdapter tdqsa = (DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), documentTramitDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && tdqsa != null) { tdqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(tdqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos tramite.", e);
        }
    }

    public EdificiQueryServiceAdapter obtenirEdifici(EdificiCriteria edificiCriteria) throws QueryServiceException {
        try {
            EdificiDTO dto = rolsacQueryServiceStrategy.obtenirEdifici(edificiCriteria);
            EdificiQueryServiceAdapter eqsa = (EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
            return eqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "edificio.", e);
        }
    }

    public List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria) throws QueryServiceException {
        try {
            List<EdificiDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEdificis(edificiCriteria);
            List<EdificiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EdificiQueryServiceAdapter>();
            for (EdificiDTO edificiDTO : llistaDTO) {
            	EdificiQueryServiceAdapter eqsa = (EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), edificiDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "edificios.", e);
        }
    }

    public EnllacQueryServiceAdapter obtenirEnllac(EnllacCriteria enllacCriteria) throws QueryServiceException {
        try {
            EnllacDTO dto = rolsacQueryServiceStrategy.obtenirEnllac(enllacCriteria);
            EnllacQueryServiceAdapter eqsa = (EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
            return eqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "enlace.", e);
        }
    }

    public List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria) throws QueryServiceException {
        try {
            List<EnllacDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEnllacos(enllacCriteria);
            List<EnllacQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EnllacQueryServiceAdapter>();
            for (EnllacDTO enllacDTO : llistaDTO) {
            	EnllacQueryServiceAdapter eqsa = (EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac", getStrategy(), enllacDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "enlaces.", e);
        }
    }

    public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException {
        try {
            EspaiTerritorialDTO dto = rolsacQueryServiceStrategy.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            EspaiTerritorialQueryServiceAdapter eqsa = (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); } 
            return eqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "espacio territorial.", e);
        }
    }

    public List<EspaiTerritorialQueryServiceAdapter> llistarEspaisTerritorials(EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException {
        try {
            List<EspaiTerritorialDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEspaisTerritorials(espaiTerritorialCriteria);
            List<EspaiTerritorialQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EspaiTerritorialQueryServiceAdapter>();
            for (EspaiTerritorialDTO espaiTerritorialDTO : llistaDTO) {
            	EspaiTerritorialQueryServiceAdapter eqsa = (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), espaiTerritorialDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && eqsa != null) { eqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "espacios territoriales.", e);
        }
    }

    public FamiliaQueryServiceAdapter obtenirFamilia(FamiliaCriteria familiaCriteria) throws QueryServiceException {
        try {
            FamiliaDTO dto = rolsacQueryServiceStrategy.obtenirFamilia(familiaCriteria);
            FamiliaQueryServiceAdapter fqsa = (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
            return fqsa;
        } catch (StrategyException e) { 
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "familia.", e);
        }
    }

    public List<FamiliaQueryServiceAdapter> llistarFamilies(FamiliaCriteria familiaCriteria) throws QueryServiceException {
        try {
            List<FamiliaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFamilies(familiaCriteria);
            List<FamiliaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FamiliaQueryServiceAdapter>();
            for (FamiliaDTO familiaDTO : llistaDTO) {
            	FamiliaQueryServiceAdapter fqsa = (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia", getStrategy(), familiaDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(fqsa); 
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "familias.", e);
        }
    }

    public FetVitalQueryServiceAdapter obtenirFetVital(FetVitalCriteria fetVitalCriteria) throws QueryServiceException {
        try {
            FetVitalDTO dto = rolsacQueryServiceStrategy.obtenirFetVital(fetVitalCriteria);
            FetVitalQueryServiceAdapter fqsa = (FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "hecho vital.", e);
        }
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws QueryServiceException {
        try {
            List<FetVitalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFetsVitals(fetVitalCriteria);
            List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
            for (FetVitalDTO fetVitalDTO : llistaDTO) {
            	FetVitalQueryServiceAdapter fqsa = (FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO); 
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hechos vitales.", e);
        }
    }

    public FitxaQueryServiceAdapter obtenirFitxa(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            FitxaDTO dto = rolsacQueryServiceStrategy.obtenirFitxa(fitxaCriteria);
            FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "ficha.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFitxes(fitxaCriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
            	FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    public FitxaUAQueryServiceAdapter obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) throws QueryServiceException {
        try {
            FitxaUADTO dto = rolsacQueryServiceStrategy.obtenirFitxaUA(fitxaUACriteria);
            FitxaUAQueryServiceAdapter fqsa = (FitxaUAQueryServiceAdapter) BeanUtils.getAdapter("fitxaUA", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "fichaUA.", e);
        }
    }

    public List<FitxaUAQueryServiceAdapter> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) throws QueryServiceException {
        try {
            List<FitxaUADTO> llistaDTO = rolsacQueryServiceStrategy.llistarFitxesUA(fitxaUACriteria);
            List<FitxaUAQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaUAQueryServiceAdapter>();
            for (FitxaUADTO fitxaUADTO : llistaDTO) {
            	FitxaUAQueryServiceAdapter fqsa = (FitxaUAQueryServiceAdapter) BeanUtils.getAdapter("fitxaUA", getStrategy(), fitxaUADTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichasUA.", e);
        }
    }

    public FormulariQueryServiceAdapter obtenirFormulari(FormulariCriteria formulariCriteria) throws QueryServiceException {
        try {
            FormulariDTO dto = rolsacQueryServiceStrategy.obtenirFormulari(formulariCriteria);
            FormulariQueryServiceAdapter fqsa= (FormulariQueryServiceAdapter) BeanUtils.getAdapter("formulari", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "formulario.", e);
        }
    }

    public List<FormulariQueryServiceAdapter> llistarFormularis(FormulariCriteria formulariCriteria) throws QueryServiceException {
        try {
            List<FormulariDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFormularis(formulariCriteria);
            List<FormulariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FormulariQueryServiceAdapter>();
            for (FormulariDTO formDTO : llistaDTO) {
            	FormulariQueryServiceAdapter fqsa = (FormulariQueryServiceAdapter) BeanUtils.getAdapter("formulari", getStrategy(), formDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "formularios.", e);
        }
        
    }

    public IconaFamiliaQueryServiceAdapter obtenirIconaFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) throws QueryServiceException {
        try {
            IconaFamiliaDTO dto = rolsacQueryServiceStrategy.obtenirIconaFamilia(iconaFamiliaCriteria);
            IconaFamiliaQueryServiceAdapter fqsa = (IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono familia.", e);
        }
    }

    public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria) throws QueryServiceException {
        try {
            List<IconaFamiliaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarIconesFamilies(iconaFamiliaCriteria);
            List<IconaFamiliaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaFamiliaQueryServiceAdapter>();
            for (IconaFamiliaDTO ifDTO : llistaDTO) {
            	IconaFamiliaQueryServiceAdapter fqsa = (IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), ifDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && fqsa != null) { fqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos familia.", e);
        }
    }

    public IconaMateriaQueryServiceAdapter obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException {
        try {
            IconaMateriaDTO dto = rolsacQueryServiceStrategy.obtenirIconaMateria(iconaMateriaCriteria);
            IconaMateriaQueryServiceAdapter iqsa = (IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) { iqsa.setRolsacUrl(rolsacUrl); } 
            return iqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono materia.", e);
        }
    }

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException {
        try {
            List<IconaMateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarIconesMateries(iconaMateriaCriteria);
            List<IconaMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaMateriaQueryServiceAdapter>();
            for (IconaMateriaDTO imDTO : llistaDTO) {
            	IconaMateriaQueryServiceAdapter iqsa = (IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), imDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) { iqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(iqsa); 
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos materia.", e);
        }
    }

    public MateriaQueryServiceAdapter obtenirMateria(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            MateriaDTO dto = rolsacQueryServiceStrategy.obtenirMateria(materiaCriteria);
            MateriaQueryServiceAdapter mqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) { mqsa.setRolsacUrl(rolsacUrl); } 
            return mqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
        }
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            List<MateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarMateries(materiaCriteria);
            List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
            for (MateriaDTO materiaDTO : llistaDTO) {
            	MateriaQueryServiceAdapter mqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) { mqsa.setRolsacUrl(rolsacUrl); } 
                 llistaQueryServiceAdapter.add(mqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

    public MateriaAgrupacioQueryServiceAdapter obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws QueryServiceException {
        try {
            MateriaAgrupacioDTO dto = rolsacQueryServiceStrategy.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
            MateriaAgrupacioQueryServiceAdapter mqsa = (MateriaAgrupacioQueryServiceAdapter) BeanUtils.getAdapter("materiaAgrupacio", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) { mqsa.setRolsacUrl(rolsacUrl); } 
            return mqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion materia.", e);
        }
    }

    public List<MateriaAgrupacioQueryServiceAdapter> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws QueryServiceException {
        try {
            List<MateriaAgrupacioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
            List<MateriaAgrupacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaAgrupacioQueryServiceAdapter>();
            for (MateriaAgrupacioDTO maDTO : llistaDTO) {
            	MateriaAgrupacioQueryServiceAdapter mqsa = (MateriaAgrupacioQueryServiceAdapter) BeanUtils.getAdapter("materiaAgrupacio", getStrategy(), maDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && mqsa != null) { mqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(mqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public NormativaQueryServiceAdapter obtenirNormativa(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            NormativaDTO dto = rolsacQueryServiceStrategy.obtenirNormativa(normativaCriteria);
            NormativaQueryServiceAdapter nqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && nqsa != null) { nqsa.setRolsacUrl(rolsacUrl); } 
            return nqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa.", e);
        }
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarNormatives(normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
            	NormativaQueryServiceAdapter nqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && nqsa != null) { nqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(nqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }

    public PerfilQueryServiceAdapter obtenirPerfil(PerfilCriteria perfilCriteria) throws QueryServiceException {
        try {
            PerfilDTO dto = rolsacQueryServiceStrategy.obtenirPerfil(perfilCriteria);
            PerfilQueryServiceAdapter pqsa = (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { pqsa.setRolsacUrl(rolsacUrl); } 
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "perfil.", e);
        }
    }

    public List<PerfilQueryServiceAdapter> llistarPerfils(PerfilCriteria perfilCriteria) throws QueryServiceException {
        try {
            List<PerfilDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPerfils(perfilCriteria);
            List<PerfilQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PerfilQueryServiceAdapter>();
            for (PerfilDTO perfilDTO : llistaDTO) {
            	PerfilQueryServiceAdapter pqsa = (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), perfilDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { pqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(pqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "perfiles.", e);
        }
    }

    public PersonalQueryServiceAdapter obtenirPersonal(PersonalCriteria personalCriteria) throws QueryServiceException {
        try {
            PersonalDTO dto = rolsacQueryServiceStrategy.obtenirPersonal(personalCriteria);
            PersonalQueryServiceAdapter pqsa = (PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { pqsa.setRolsacUrl(rolsacUrl); } 
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "personal.", e);
        }
    }

    public List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) throws QueryServiceException {
        try {
            List<PersonalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPersonal(personalCriteria);
            List<PersonalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PersonalQueryServiceAdapter>();
            for (PersonalDTO personalDTO : llistaDTO) {
            	PersonalQueryServiceAdapter pqsa = (PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), personalDTO); 
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { pqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(pqsa);
            } 
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "personal.", e);
        }
    }

    public PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) throws QueryServiceException {
        try {
            PublicObjectiuDTO dto = rolsacQueryServiceStrategy.obtenirPublicObjectiu(publicObjectiuCriteria);
            PublicObjectiuQueryServiceAdapter pqsa = (PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { pqsa.setRolsacUrl(rolsacUrl); } 
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "publico objetivo.", e);
        }
    }

    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) throws QueryServiceException {
        try {
            List<PublicObjectiuDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPublicsObjectius(publicObjectiuCriteria);
            List<PublicObjectiuQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PublicObjectiuQueryServiceAdapter>();
            for (PublicObjectiuDTO poDTO : llistaDTO) {
            	PublicObjectiuQueryServiceAdapter pqsa = (PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), poDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && pqsa != null) { pqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(pqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "publico objetivo.", e);
        }
    }

    public TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws QueryServiceException{
        try {
            TipusAfectacioDTO dto = rolsacQueryServiceStrategy.obtenirTipusAfectacio(tipusAfectacioCriteria);
            return (TipusAfectacioQueryServiceAdapter) BeanUtils.getAdapter("tipusAfectacio", getStrategy(), dto);            
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tipo afectacion.", e);
        }
    }
    
    public List<TipusAfectacioQueryServiceAdapter> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws QueryServiceException {
        try {
            List<TipusAfectacioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTipusAfectacio(tipusAfectacioCriteria);
            List<TipusAfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TipusAfectacioQueryServiceAdapter>();
            for (TipusAfectacioDTO taDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((TipusAfectacioQueryServiceAdapter) BeanUtils.getAdapter("tipusAfectacio", getStrategy(), taDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tipos de afectaciones.", e);
        }
    }
    
    public SeccioQueryServiceAdapter obtenirSeccio(SeccioCriteria seccioCriteria) throws QueryServiceException {
        try {
            SeccioDTO dto = rolsacQueryServiceStrategy.obtenirSeccio(seccioCriteria);
            SeccioQueryServiceAdapter sqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && sqsa != null) { sqsa.setRolsacUrl(rolsacUrl); } 
            return sqsa; 
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "seccion.", e);
        }
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException {
        try {
            List<SeccioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarSeccions(seccioCriteria);
            List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
            for (SeccioDTO poDTO : llistaDTO) {
            	SeccioQueryServiceAdapter sqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), poDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && sqsa != null) { sqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(sqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones.", e);
        }
    }

    public TaxaQueryServiceAdapter obtenirTaxa(TaxaCriteria taxaCriteria) throws QueryServiceException {
        try {
            TaxaDTO dto = rolsacQueryServiceStrategy.obtenirTaxa(taxaCriteria);
            TaxaQueryServiceAdapter tqsa = (TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) { tqsa.setRolsacUrl(rolsacUrl); } 
            return tqsa; 
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tasa.", e);
        }
    }

    public List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria) throws QueryServiceException {
        List<TaxaDTO> llistaDTO;
        try {
            llistaDTO = rolsacQueryServiceStrategy.llistarTaxes(taxaCriteria);
            List<TaxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TaxaQueryServiceAdapter>();
            for (TaxaDTO taxaDTO : llistaDTO) {
            	TaxaQueryServiceAdapter tqsa = (TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(), taxaDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) { tqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tasas.", e);
        }
    }

    public TipusQueryServiceAdapter obtenirTipus(TipusCriteria tipusCriteria) throws QueryServiceException {
        try {
            TipusDTO dto = rolsacQueryServiceStrategy.obtenirTipus(tipusCriteria);
            TipusQueryServiceAdapter tqsa = (TipusQueryServiceAdapter) BeanUtils.getAdapter("tipus", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) { tqsa.setRolsacUrl(rolsacUrl); } 
            return tqsa; 
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tipo.", e);
        }
    }

    public List<TipusQueryServiceAdapter> llistarTipus(TipusCriteria tipusCriteria) throws QueryServiceException {
        try {
            List<TipusDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTipus(tipusCriteria);
            List<TipusQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TipusQueryServiceAdapter>();
            for (TipusDTO tipusDTO : llistaDTO) {
            	TipusQueryServiceAdapter tqsa = (TipusQueryServiceAdapter) BeanUtils.getAdapter("tipus", getStrategy(), tipusDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) { tqsa.setRolsacUrl(rolsacUrl); } 
                llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tipos.", e);
        }
    }
    
    public TramitQueryServiceAdapter obtenirTramit(TramitCriteria tramitCriteria) throws QueryServiceException {
        try {
            TramitDTO dto = rolsacQueryServiceStrategy.obtenirTramit(tramitCriteria);
            TramitQueryServiceAdapter tqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) { tqsa.setRolsacUrl(rolsacUrl); } 
            return tqsa; 
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
        }
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException {
        try {
            List<TramitDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTramits(tramitCriteria);
            List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
            for (TramitDTO tramitDTO : llistaDTO) {
            	TramitQueryServiceAdapter tqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && tqsa != null) { tqsa.setRolsacUrl(rolsacUrl); } 
                 llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tramites.", e);
        }
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            UnitatAdministrativaDTO dto = rolsacQueryServiceStrategy.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            UnitatAdministrativaQueryServiceAdapter uaqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && uaqsa != null) { uaqsa.setRolsacUrl(rolsacUrl); }
            return uaqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO uaDTO : llistaDTO) {
            	UnitatAdministrativaQueryServiceAdapter uaqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), uaDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && uaqsa != null) { uaqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(uaqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
        }
    }

    public UnitatMateriaQueryServiceAdapter obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria) throws QueryServiceException {
        try {
            UnitatMateriaDTO dto = rolsacQueryServiceStrategy.obtenirUnitatMateria(unitatMateriaCriteria);
            UnitatMateriaQueryServiceAdapter uqsa = (UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) { uqsa.setRolsacUrl(rolsacUrl); }
            return uqsa;
        } catch (StrategyException e) {
            e.printStackTrace();
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad materia.", e);
        }
    }

    public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria) throws QueryServiceException {
        try {
            List<UnitatMateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUnitatsMateries(unitatMateriaCriteria);
            List<UnitatMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatMateriaQueryServiceAdapter>();
            for (UnitatMateriaDTO umDTO : llistaDTO) {
            	UnitatMateriaQueryServiceAdapter uqsa = (UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), umDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) { uqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(uqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades materia.", e);
        }
    }

    public UsuariQueryServiceAdapter obtenirUsuari(UsuariCriteria usuariCriteria) throws QueryServiceException {
        try {
            UsuariDTO dto = rolsacQueryServiceStrategy.obtenirUsuari(usuariCriteria);
            UsuariQueryServiceAdapter uqsa = (UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) { uqsa.setRolsacUrl(rolsacUrl); }
            return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "usuario.", e);
        }
    }

    public List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) throws QueryServiceException {
        try {
            List<UsuariDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUsuaris(usuariCriteria);
            List<UsuariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UsuariQueryServiceAdapter>();
            for (UsuariDTO uDTO : llistaDTO) {
            	 UsuariQueryServiceAdapter uqsa = (UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), uDTO);
            	if (rolsacUrl != null && !rolsacUrl.isEmpty() && uqsa != null) { uqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(uqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "usuarios.", e);
        }
    }
    
    public IniciacioQueryServiceAdapter obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws QueryServiceException {
    	
    	try {
    		
            IniciacioDTO dto = rolsacQueryServiceStrategy.obtenirTipusIniciacio(iniciacioCriteria);
            IniciacioQueryServiceAdapter iqsa = (IniciacioQueryServiceAdapter)BeanUtils.getAdapter("iniciacio", getStrategy(), dto);
            if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) { iqsa.setRolsacUrl(rolsacUrl); }
            return iqsa;
        } catch (StrategyException e) {
        	
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "iniciacion.", e);
            
        }
    	
    }
    
    public List<IniciacioQueryServiceAdapter> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws QueryServiceException {
    	
    	try {
    		
            List<IniciacioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTipusIniciacions(iniciacioCriteria);
            List<IniciacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IniciacioQueryServiceAdapter>();
            
            for (IniciacioDTO uDTO : llistaDTO) {
            	IniciacioQueryServiceAdapter iqsa = (IniciacioQueryServiceAdapter)BeanUtils.getAdapter("iniciacio", getStrategy(), uDTO);
                if (rolsacUrl != null && !rolsacUrl.isEmpty() && iqsa != null) { iqsa.setRolsacUrl(rolsacUrl); }
                llistaQueryServiceAdapter.add(iqsa);
            }
            
            return llistaQueryServiceAdapter;
            
        } catch (StrategyException e) {
        	
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "usuarios.", e);
            
        }
    	
    }
    
    public IdiomaQueryServiceAdapter obtenirIdioma(IdiomaCriteria idiomaCriteria) throws QueryServiceException {
        try {
            IdiomaDTO dto = rolsacQueryServiceStrategy.obtenirIdioma(idiomaCriteria);
            return (IdiomaQueryServiceAdapter) BeanUtils.getAdapter("idioma", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "idioma.", e);
        }
    }

	public List<IdiomaQueryServiceAdapter> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws QueryServiceException {
		try {
            List<IdiomaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarIdiomes(idiomaCriteria);
            List<IdiomaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IdiomaQueryServiceAdapter>();
            for (IdiomaDTO uDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((IdiomaQueryServiceAdapter) BeanUtils.getAdapter("idioma", getStrategy(), uDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "idiomas.", e);
        }
	}

    public int getNumFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            Integer num = rolsacQueryServiceStrategy.getNumFitxes(fitxaCriteria);
            return num;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.COUNT_GETTER + "fitxas.", e);
        }
    }

	public SilencioQueryServiceAdapter obtenirSilencio(Long codSilencio, String idioma) throws QueryServiceException {
		 try 
		 {
	         SilencioDTO dto = rolsacQueryServiceStrategy.obtenirSilenci(codSilencio, idioma);
			 SilencioQueryServiceAdapter sqsa = (SilencioQueryServiceAdapter) BeanUtils.getAdapter("silencio", getStrategy(), dto);
			 if (rolsacUrl != null && !rolsacUrl.isEmpty() && sqsa != null) { sqsa.setRolsacUrl(rolsacUrl); }
	            return sqsa;
	        } catch (StrategyException e) {
	            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "silencio.", e);
	        }
	}

}
