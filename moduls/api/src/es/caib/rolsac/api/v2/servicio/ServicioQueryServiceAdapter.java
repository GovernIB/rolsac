package es.caib.rolsac.api.v2.servicio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.servicio.ejb.ServicioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;

/**
 * Adapta el ServicioDTO a un Objecte amb dades i metodes
 * 
 */
public class ServicioQueryServiceAdapter extends ServicioDTO implements ServicioQueryService {

    private static final long serialVersionUID = -1655012673802302791L;

    private ServicioQueryServiceStrategy servicioQueryServiceStrategy;

    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
    	if ( this.servicioQueryServiceStrategy != null) {
    		 this.servicioQueryServiceStrategy.setUrl(rolsacUrl);
    	}
    }
    
    public void setServicioQueryServiceStrategy(ServicioQueryServiceStrategy servicioQueryServiceStrategy) {
        this.servicioQueryServiceStrategy = servicioQueryServiceStrategy;
    }

    public ServicioQueryServiceAdapter(ServicioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return servicioQueryServiceStrategy instanceof ServicioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    
    public int getNumNormatives() throws QueryServiceException {
        try {
            return servicioQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.TOTES);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas.", e);
        }
    }
    
    @Deprecated
    public int getNumNormativesLocals() throws QueryServiceException {
        try {
            return servicioQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.TOTES);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas locales.", e);
        }
    }
    
    @Deprecated
    public int getNumNormativesExternes() throws QueryServiceException {
        try {
            return servicioQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.TOTES);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas externas.", e);
        }
    }    
    
    public int getNumMateries() throws QueryServiceException {
        try {
            return servicioQueryServiceStrategy.getNumMateries(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de materias.", e);
        }
    }

    public int getNumDocuments() throws QueryServiceException {
        try {
            return servicioQueryServiceStrategy.getNumDocuments(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de documentos.", e);
        }
    }

    public int getNumFetsVitals() throws QueryServiceException {
        try {
            return servicioQueryServiceStrategy.getNumFetsVitals(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de hechos vitales.", e);
        }
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            List<MateriaDTO> llistaDTO = servicioQueryServiceStrategy.llistarMateries(getId(), materiaCriteria);
            List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
            for (MateriaDTO materiaDTO : llistaDTO) {
            	MateriaQueryServiceAdapter tqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO);
            	if (tqsa != null && rolsacUrl != null) {
            		tqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = servicioQueryServiceStrategy.llistarNormatives(getId(), normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
            	NormativaQueryServiceAdapter tqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO);
            	if (tqsa != null && rolsacUrl != null) {
            		tqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetsVitalsCriteria) throws QueryServiceException {
        try {
            List<FetVitalDTO> llistaDTO = servicioQueryServiceStrategy.llistarFetsVitals(getId(), fetsVitalsCriteria);
            List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
            for (FetVitalDTO fetVitalDTO : llistaDTO) {
            	FetVitalQueryServiceAdapter tqsa = (FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO);
    			if (tqsa != null && rolsacUrl != null) {
            		tqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hechos vitales.", e);
        }
    }
    
    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentoServicioCriteria documentoServicioCriteria) throws QueryServiceException {
        try {
            List<DocumentoServicioDTO> llistaDTO = servicioQueryServiceStrategy.llistarDocuments(getId(), documentoServicioCriteria);
            List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
            for (DocumentoServicioDTO documentDTO : llistaDTO) {
            	DocumentQueryServiceAdapter tqsa = (DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO);
            	if (tqsa != null && rolsacUrl != null) {
            		tqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos.", e);
        }
    }

    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) throws QueryServiceException {
        try {
            List<PublicObjectiuDTO> llistaDTO = servicioQueryServiceStrategy.llistarPublicsObjectius(getId(), poCriteria);
            List<PublicObjectiuQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PublicObjectiuQueryServiceAdapter>();
            for (PublicObjectiuDTO poDTO : llistaDTO) {
            	PublicObjectiuQueryServiceAdapter tqsa = (PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), poDTO);
            	if (tqsa != null && rolsacUrl != null) {
            		tqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

}
