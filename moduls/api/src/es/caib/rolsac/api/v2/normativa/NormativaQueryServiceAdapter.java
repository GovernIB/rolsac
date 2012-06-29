package es.caib.rolsac.api.v2.normativa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class NormativaQueryServiceAdapter extends NormativaDTO implements NormativaQueryService {

    private static final long serialVersionUID = 1228676693463704668L;

    private NormativaQueryServiceStrategy normativaQueryServiceStrategy;

    public void setNormativaQueryServiceStrategy(NormativaQueryServiceStrategy normativaQueryServiceStrategy) {
        this.normativaQueryServiceStrategy = normativaQueryServiceStrategy;
    }

    public NormativaQueryServiceAdapter(NormativaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return normativaQueryServiceStrategy instanceof NormativaQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter obtenirArxiuNormativa() throws QueryServiceException{
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = normativaQueryServiceStrategy.obtenirArxiuNormativa(this.getArchivo());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
        }
    }
    
    public int getNumAfectades() throws QueryServiceException {
        try {
            return normativaQueryServiceStrategy.getNumAfectades(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas afectadas.", e);
        }
    }

    public int getNumAfectants() throws QueryServiceException {
        try {
            return normativaQueryServiceStrategy.getNumAfectants(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas afectantes.", e);
        }
    }

    public int getNumProcediments() throws QueryServiceException {
        try {
            return normativaQueryServiceStrategy.getNumProcediments(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectades() throws QueryServiceException{
        try {
            List<AfectacioDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectacionsAfectades(id);
            List<AfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AfectacioQueryServiceAdapter>();
            for (AfectacioDTO afectacioDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((AfectacioQueryServiceAdapter) BeanUtils.getAdapter("afectacio", getStrategy(), afectacioDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "afectaciones afectadas.", e);
        }
    }
    
    public List<NormativaQueryServiceAdapter> llistarAfectades() throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectades(id);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas afectadas.", e);
        }
    }

    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectants() throws QueryServiceException{
        try {
            List<AfectacioDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectacionsAfectants(id);
            List<AfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AfectacioQueryServiceAdapter>();
            for (AfectacioDTO afectacioDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((AfectacioQueryServiceAdapter) BeanUtils.getAdapter("afectacio", getStrategy(), afectacioDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "afectaciones afectantes.", e);
        }
    }
    
    public List<NormativaQueryServiceAdapter> llistarAfectants() throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectants(id);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas afectantes.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = normativaQueryServiceStrategy.llistarProcediments(id, procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public ButlletiQueryServiceAdapter obtenirButlleti() throws QueryServiceException {
        try {
            return (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), normativaQueryServiceStrategy.obtenirButlleti(this.getBoletin()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "boletin.", e);
        }
    }
    
    
    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException {
        if (this.getUnidadAdministrativa() == null) {return null;}
        try {
            UnitatAdministrativaDTO dto = (UnitatAdministrativaDTO) normativaQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa());
            return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
        }
    }

}
