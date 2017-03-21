package es.caib.rolsac.api.v2.seccio;

import java.util.ArrayList;
import java.util.List;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.seccio.ejb.SeccioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class SeccioQueryServiceAdapter extends SeccioDTO implements SeccioQueryService {

    private static final long serialVersionUID = -2467698240719109343L;

    private SeccioQueryServiceStrategy seccioQueryServiceStrategy;
    
    public void setSeccioQueryServiceStrategy(SeccioQueryServiceStrategy seccioQueryServiceStrategy) {
        this.seccioQueryServiceStrategy = seccioQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
		if (this.seccioQueryServiceStrategy != null) {
			this.seccioQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    private STRATEGY getStrategy() {
        return seccioQueryServiceStrategy instanceof SeccioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public SeccioQueryServiceAdapter(SeccioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    public int getNumFilles() throws QueryServiceException {
        try {
            return seccioQueryServiceStrategy.getNumFilles(getId());
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de secciones hijas.", e);
        }
    }

    public int getNumFitxes() throws QueryServiceException {
        try {
            return seccioQueryServiceStrategy.getNumFitxes(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de fichas.", e);
        }
    }

    /**
     * Llama a llistarPares y devuelve el tamanyo de la lista. No es mas eficiente que ejecutar un .size()
     * a nivel de cliente.
     * @throws QueryServiceException 
     */
    public int getNumPares() throws QueryServiceException {
        try {
            return seccioQueryServiceStrategy.getNumPares(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de secciones padre.", e);
        }
    }

    public int getNumUnitatsAdministratives() throws QueryServiceException {
        try {
            return seccioQueryServiceStrategy.getNumUnitatsAdministratives(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades administrativas.", e);
        }
    }
    
    public List<SeccioQueryServiceAdapter> llistarFilles(SeccioCriteria seccioCriteria) throws QueryServiceException {
        try {
            List<SeccioDTO> llistaDTO = seccioQueryServiceStrategy.llistarFilles(getId(), seccioCriteria);
            List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
            for (SeccioDTO seccioDTO : llistaDTO) {
            	SeccioQueryServiceAdapter sqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO);
            	if (sqsa != null && rolsacUrl != null) {
            		sqsa.setRolsacUrl(rolsacUrl);
            	}
                llistaQueryServiceAdapter.add(sqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones hijas.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria, FitxaUACriteria fitxaUACriteria) throws QueryServiceException {
        try{ 
            List<FitxaDTO> llistaDTO = seccioQueryServiceStrategy.llistarFitxes(getId(), fitxaCriteria, fitxaUACriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
            	FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO);
            	if (fqsa != null && rolsacUrl != null) {
            		fqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    // Ejecuta recursivamente obtenirPare() hasta llegar a la seccion raiz, construyendo la lista a devolver por el camino.
    public List<SeccioQueryServiceAdapter> llistarPares() throws QueryServiceException {
        try {
            List<SeccioDTO> llistaDTO = seccioQueryServiceStrategy.llistarPares(getId());
            List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
            for (SeccioDTO seccioDTO : llistaDTO) {
            	SeccioQueryServiceAdapter fqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO);
            	if (fqsa != null && rolsacUrl != null) {
            		fqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones padre.", e);
        }
    }
    
    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria, FitxaUACriteria fitxaUACriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = seccioQueryServiceStrategy.llistarUnitatsAdministratives(getId(), unitatAdministrativaCriteria, fitxaUACriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            	UnitatAdministrativaQueryServiceAdapter fqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO);
            	if (fqsa != null && rolsacUrl != null) {
            		fqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
        }
    }

    public SeccioQueryServiceAdapter obtenirPare() throws QueryServiceException {
        if (this.getPadre() == null) {return null;}
        try {
        	SeccioQueryServiceAdapter fqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioQueryServiceStrategy.obtenirPare(this.getPadre()));
            if (fqsa != null && rolsacUrl != null) {
        		fqsa.setRolsacUrl(rolsacUrl);
        	}
            return fqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "seccion padre.", e);
        }
    }

	
    
}
