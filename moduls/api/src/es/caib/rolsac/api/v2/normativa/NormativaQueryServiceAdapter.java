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
import es.caib.rolsac.api.v2.normativa.ejb.NormativaQueryServiceEJBStrategy;
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
    
    private String url;
    public void setUrl(String url) {
    	this.url = url;
		if (this.normativaQueryServiceStrategy != null) {
			this.normativaQueryServiceStrategy.setUrl(url);
		}
	}

    public NormativaQueryServiceAdapter(NormativaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return normativaQueryServiceStrategy instanceof NormativaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter obtenirArxiuNormativa() throws QueryServiceException{
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = normativaQueryServiceStrategy.obtenirArxiuNormativa(this.getArchivo());
            ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (aqsa != null && url != null) {
            	aqsa.setUrl(url);
            }
            return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
        }
    }
    
    public int getNumAfectades() throws QueryServiceException {
        try {
            return normativaQueryServiceStrategy.getNumAfectades(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas afectadas.", e);
        }
    }

    public int getNumAfectants() throws QueryServiceException {
        try {
            return normativaQueryServiceStrategy.getNumAfectants(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas afectantes.", e);
        }
    }

    public int getNumProcediments() throws QueryServiceException {
        try {
            return normativaQueryServiceStrategy.getNumProcediments(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectades() throws QueryServiceException{
        try {
            List<AfectacioDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectacionsAfectades(getId());
            List<AfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AfectacioQueryServiceAdapter>();
            for (AfectacioDTO afectacioDTO : llistaDTO) {
            	AfectacioQueryServiceAdapter aqsa =(AfectacioQueryServiceAdapter) BeanUtils.getAdapter("afectacio", getStrategy(), afectacioDTO); 
            	if (aqsa != null && url != null) {
                	aqsa.setUrl(url);
                }
                llistaQueryServiceAdapter.add(aqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "afectaciones afectadas.", e);
        }
    }
    
    public List<NormativaQueryServiceAdapter> llistarAfectades() throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectades(getId());
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
            	NormativaQueryServiceAdapter aqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO);
            	if (aqsa != null && url != null) {
                	aqsa.setUrl(url);
                }
                llistaQueryServiceAdapter.add(aqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas afectadas.", e);
        }
    }

    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectants() throws QueryServiceException{
        try {
            List<AfectacioDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectacionsAfectants(getId());
            List<AfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AfectacioQueryServiceAdapter>();
            for (AfectacioDTO afectacioDTO : llistaDTO) {
            	AfectacioQueryServiceAdapter aqsa = (AfectacioQueryServiceAdapter) BeanUtils.getAdapter("afectacio", getStrategy(), afectacioDTO);
            	if (aqsa != null && url != null) {
                	aqsa.setUrl(url);
                }
                llistaQueryServiceAdapter.add(aqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "afectaciones afectantes.", e);
        }
    }
    
    public List<NormativaQueryServiceAdapter> llistarAfectants() throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectants(getId());
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
            	NormativaQueryServiceAdapter aqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO);
            	if (aqsa != null && url != null) {
                	aqsa.setUrl(url);
                }
                llistaQueryServiceAdapter.add(aqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas afectantes.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = normativaQueryServiceStrategy.llistarProcediments(getId(), procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
            	ProcedimentQueryServiceAdapter aqsa = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO);
            	if (aqsa != null && url != null) {
                	aqsa.setUrl(url);
                }
                llistaQueryServiceAdapter.add(aqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public ButlletiQueryServiceAdapter obtenirButlleti() throws QueryServiceException {
        try {
        	ButlletiQueryServiceAdapter aqsa = (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), normativaQueryServiceStrategy.obtenirButlleti(this.getBoletin()));
            if (aqsa != null && url != null) {
            	aqsa.setUrl(url);
            }
            return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "boletin.", e);
        }
    }
    
    
    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException {
        if (this.getUnidadAdministrativa() == null) {return null;}
        try {
            UnitatAdministrativaDTO dto = (UnitatAdministrativaDTO) normativaQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa());
            UnitatAdministrativaQueryServiceAdapter aqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
             if (aqsa != null && url != null) {
             	aqsa.setUrl(url);
             }
             return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
        }
    }

	

}
