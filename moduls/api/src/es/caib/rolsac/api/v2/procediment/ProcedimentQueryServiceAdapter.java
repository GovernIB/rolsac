package es.caib.rolsac.api.v2.procediment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
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
import es.caib.rolsac.api.v2.procediment.ejb.ProcedimentQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

/**
 * Adapta el ProcedimientoDTO a un Objecte amb dades i metodes
 * 
 */
public class ProcedimentQueryServiceAdapter extends ProcedimentDTO implements ProcedimentQueryService {

    private static final long serialVersionUID = -1655012673802302791L;

    private ProcedimentQueryServiceStrategy procedimentQueryServiceStrategy;

    public void setProcedimentQueryServiceStrategy(ProcedimentQueryServiceStrategy procedimentQueryServiceStrategy) {
        this.procedimentQueryServiceStrategy = procedimentQueryServiceStrategy;
    }

    public ProcedimentQueryServiceAdapter(ProcedimentDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return procedimentQueryServiceStrategy instanceof ProcedimentQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public int getNumTramits() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumTramits(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de tramites.", e);
        }
    }

    public int getNumNormatives() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.TOTES);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas.", e);
        }
    }
    
    public int getNumNormativesLocals() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.LOCAL);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas locales.", e);
        }
    }
    
    public int getNumNormativesExternes() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.EXTERNA);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas externas.", e);
        }
    }    
    
    public int getNumMateries() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumMateries(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de materias.", e);
        }
    }

    public int getNumDocuments() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumDocuments(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de documentos.", e);
        }
    }

    public int getNumFetsVitals() throws QueryServiceException {
        try {
            return procedimentQueryServiceStrategy.getNumFetsVitals(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de hechos vitales.", e);
        }
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException {
        try {
            List<TramitDTO> llistaDTO = procedimentQueryServiceStrategy.llistarTramits(getId(), tramitCriteria);
            List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
            for (TramitDTO tramitDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tramites.", e);
        }
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            List<MateriaDTO> llistaDTO = procedimentQueryServiceStrategy.llistarMateries(getId(), materiaCriteria);
            List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
            for (MateriaDTO materiaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = procedimentQueryServiceStrategy.llistarNormatives(getId(), normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetsVitalsCriteria) throws QueryServiceException {
        try {
            List<FetVitalDTO> llistaDTO = procedimentQueryServiceStrategy.llistarFetsVitals(getId(), fetsVitalsCriteria);
            List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
            for (FetVitalDTO fetVitalDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hechos vitales.", e);
        }
    }
    
    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) throws QueryServiceException {
        try {
            List<DocumentDTO> llistaDTO = procedimentQueryServiceStrategy.llistarDocuments(getId(), documentCriteria);
            List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
            for (DocumentDTO documentDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos.", e);
        }
    }

}
