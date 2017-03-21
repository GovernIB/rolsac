package es.caib.rolsac.api.v2.fitxaUA;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.ejb.FitxaUAQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaUAQueryServiceAdapter extends FitxaUADTO implements FitxaUAQueryService {

    private static final long serialVersionUID = -3653784030169097078L;

    FitxaUAQueryServiceStrategy fitxaUAQueryServiceStrategy;    
    
    public void setFitxaUAQueryServiceStrategy(FitxaUAQueryServiceStrategy fitxaUAQueryServiceStrategy) {
        this.fitxaUAQueryServiceStrategy = fitxaUAQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
		if (fitxaUAQueryServiceStrategy != null) {
			fitxaUAQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    private STRATEGY getStrategy() {
        return fitxaUAQueryServiceStrategy instanceof FitxaUAQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public FitxaUAQueryServiceAdapter(FitxaUADTO dto) throws QueryServiceException {        
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException {
        if (this.getUnidadAdministrativa() == null) {return null;}
        try {
        	UnitatAdministrativaQueryServiceAdapter uaqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), fitxaUAQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa()));
        	if (uaqsa != null && rolsacUrl != null) {
        		uaqsa.setRolsacUrl(rolsacUrl);
        	}
        	return uaqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
        }
    }

    public FitxaQueryServiceAdapter obtenirFitxa() throws QueryServiceException {
        if (this.getFicha()== null) {return null;}
        try {
        	FitxaQueryServiceAdapter uaqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaUAQueryServiceStrategy.obtenirFitxa(this.getFicha()));
             if (uaqsa != null && rolsacUrl != null) {
         		uaqsa.setRolsacUrl(rolsacUrl);
         	}
         	return uaqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "ficha.", e);
        }
    }

    public SeccioQueryServiceAdapter obtenirSeccio() throws QueryServiceException {
        if (this.getSeccion() == null) {return null;}
        try {
        	SeccioQueryServiceAdapter uaqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), fitxaUAQueryServiceStrategy.obtenirSeccio(this.getSeccion()));
            if (uaqsa != null && rolsacUrl != null) {
         		uaqsa.setRolsacUrl(rolsacUrl);
         	}
         	return uaqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "seccion.", e);
        }
    }

	

}
