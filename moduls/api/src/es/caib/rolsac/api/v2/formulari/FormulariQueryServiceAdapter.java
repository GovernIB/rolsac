package es.caib.rolsac.api.v2.formulari;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.formulari.ejb.FormulariQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class FormulariQueryServiceAdapter extends FormulariDTO implements FormulariQueryService {

    private static final long serialVersionUID = 1747992039044768431L;

    FormulariQueryServiceStrategy formulariQueryServiceStrategy;
    
    public void setFormulariQueryServiceStrategy(FormulariQueryServiceStrategy formulariQueryServiceStrategy) {
        this.formulariQueryServiceStrategy = formulariQueryServiceStrategy;
    }
    
    private String url;
    public void setRolsacUrl(String url) {
    	this.url = url;
    	if (this.formulariQueryServiceStrategy != null) {
    		this.formulariQueryServiceStrategy.setUrl(url);
    	}
    }

    public FormulariQueryServiceAdapter(FormulariDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return formulariQueryServiceStrategy instanceof FormulariQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter obtenirArchivo() throws QueryServiceException {
        if (this.getArchivo() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter uqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), formulariQueryServiceStrategy.obtenirArchivo(this.getArchivo()));
        	if (uqsa != null && url != null) {
        		uqsa.setRolsacUrl(url);
        	}
        	return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "archivo.", e);
        }
    }
    
    public ArxiuQueryServiceAdapter obtenirManual() throws QueryServiceException {
        if (this.getManual() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter uqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), formulariQueryServiceStrategy.obtenirManual(this.getManual()));
            if (uqsa != null && url != null) {
        		uqsa.setRolsacUrl(url);
        	}
        	return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "manual.", e);
        }
    }
    
    public TramitQueryServiceAdapter obtenirTramit() throws QueryServiceException {
        if (this.getTramite() == null) {return null;}
        try {
        	TramitQueryServiceAdapter uqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), formulariQueryServiceStrategy.obtenirTramit(this.getTramite()));
            if (uqsa != null && url != null) {
        		uqsa.setRolsacUrl(url);
        	}
        	return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
        }        
    }

}
