package es.caib.rolsac.api.v2.unitatMateria;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.ejb.UnitatMateriaQueryServiceEJBStrategy;

public class UnitatMateriaQueryServiceAdapter extends UnitatMateriaDTO implements UnitatMateriaQueryService {

    private static final long serialVersionUID = 4824532013406119889L;

    UnitatMateriaQueryServiceStrategy unitatMateriaQueryServiceStrategy;
    
    public void setUnitatMateriaQueryServiceStrategy(UnitatMateriaQueryServiceStrategy unitatMateriaQueryServiceStrategy) {
        this.unitatMateriaQueryServiceStrategy = unitatMateriaQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
    	if (unitatMateriaQueryServiceStrategy != null) {
    		unitatMateriaQueryServiceStrategy.setUrl(rolsacUrl);
    	}    	
    }

    private STRATEGY getStrategy() {
        return unitatMateriaQueryServiceStrategy instanceof UnitatMateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public UnitatMateriaQueryServiceAdapter(UnitatMateriaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    public MateriaQueryServiceAdapter obtenirMateria() throws QueryServiceException {
        if (this.getMateria() == null) {return null;}
        try {
        	 MateriaQueryServiceAdapter mqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), unitatMateriaQueryServiceStrategy.obtenirMateria(this.getMateria()));
        	 if (mqsa != null && rolsacUrl != null) {
        		 mqsa.setRolsacUrl(rolsacUrl);
        	 }
        	 return mqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
        }
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException {
        if (this.getUnidad() == null) {return null;}
        try {
        	UnitatAdministrativaQueryServiceAdapter mqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatMateriaQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidad()));
            if (mqsa != null && rolsacUrl != null) {
            	mqsa.setRolsacUrl(rolsacUrl);
	       	 }
	       	 return mqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
        }
    }

}
