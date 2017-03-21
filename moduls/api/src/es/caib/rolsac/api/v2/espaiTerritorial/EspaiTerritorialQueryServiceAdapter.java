package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.ejb.EspaiTerritorialQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EspaiTerritorialQueryServiceAdapter extends EspaiTerritorialDTO implements EspaiTerritorialQueryService {

    private static final long serialVersionUID = 2251052816040318102L;

    EspaiTerritorialQueryServiceStrategy espaiTerritorialQueryServiceStrategy;
    
    public void setEspaiTerritorialQueryServiceStrategy(EspaiTerritorialQueryServiceStrategy espaiTerritorialQueryServiceStrategy) {
        this.espaiTerritorialQueryServiceStrategy = espaiTerritorialQueryServiceStrategy;
    }
    
    private String url;
    public void setRolsacUrl(String url) {
    	this.url = url;
		if ( this.espaiTerritorialQueryServiceStrategy  != null) {
			 this.espaiTerritorialQueryServiceStrategy.setUrl(url);
		}
	}

    private STRATEGY getStrategy() {
        return espaiTerritorialQueryServiceStrategy instanceof EspaiTerritorialQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public EspaiTerritorialQueryServiceAdapter(EspaiTerritorialDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public int getNumFills() throws QueryServiceException {
        try {
            return espaiTerritorialQueryServiceStrategy.getNumFills(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de edificios hijo.", e);
        }
    }

    public int getNumUnitatsAdministratives() throws QueryServiceException {
        try {
            return espaiTerritorialQueryServiceStrategy.getNumUnitatsAdministratives(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades administrativas.", e);
        }
    }

    public EspaiTerritorialQueryServiceAdapter obtenirPare() throws QueryServiceException {
        if (this.getPadre() == null) {return null;}
        try {
        	EspaiTerritorialQueryServiceAdapter eqsa =  (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), espaiTerritorialQueryServiceStrategy.obtenirPare(this.getPadre()));
        	if (eqsa != null && url != null) {
        		eqsa.setRolsacUrl(url);
        	}
        	return eqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "edificio padre.", e);
        }
    }

    public List<EspaiTerritorialQueryServiceAdapter> llistarFills(EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException {
        try {
            List<EspaiTerritorialDTO> llistaDTO = espaiTerritorialQueryServiceStrategy.llistarFills(getId(), espaiTerritorialCriteria);
            List<EspaiTerritorialQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EspaiTerritorialQueryServiceAdapter>();
            for (EspaiTerritorialDTO espaiTerritorialDTO : llistaDTO) {
            	EspaiTerritorialQueryServiceAdapter eqsa = (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(),espaiTerritorialDTO);
            	if (eqsa != null && url != null) {
            		eqsa.setRolsacUrl(url);
            	}
                llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "edificios hijo.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
            try {
                List<UnitatAdministrativaDTO> llistaDTO = espaiTerritorialQueryServiceStrategy.llistarUnitatsAdministratives(getId(), unitatAdministrativaCriteria);
                List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
                for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                	UnitatAdministrativaQueryServiceAdapter uqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO);
                    if (uqsa != null && url != null) {
                    	uqsa.setRolsacUrl(url);
                    }
                	llistaQueryServiceAdapter.add(uqsa);
                }
                return llistaQueryServiceAdapter;
            } catch (StrategyException e) {
                throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
            }
    }

    public ArxiuQueryServiceAdapter obtenirMapa() throws QueryServiceException {
        if (this.getMapa() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter uqsa=  (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), espaiTerritorialQueryServiceStrategy.obtenirMapa(this.getMapa()));
            if (uqsa != null && url != null) {
            	uqsa.setRolsacUrl(url);
            }
            return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "mapa.", e);
        }
    }
    
    public ArxiuQueryServiceAdapter obtenirLogo() throws QueryServiceException {
        if (this.getLogo() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter uqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), espaiTerritorialQueryServiceStrategy.obtenirLogo(this.getLogo()));
            if (uqsa != null && url != null) {
            	uqsa.setRolsacUrl(url);
            }
            return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo.", e);
        }
    }

	
    
}
