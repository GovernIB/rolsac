package es.caib.rolsac.api.v2.personal;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.personal.ejb.PersonalQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class PersonalQueryServiceAdapter extends PersonalDTO implements PersonalQueryService {

    private static final long serialVersionUID = -7266758574463322225L;

    private PersonalQueryServiceStrategy personalQueryServiceStrategy;

    public void setPersonalQueryServiceStrategy(PersonalQueryServiceStrategy personalQueryServiceStrategy) {
        this.personalQueryServiceStrategy = personalQueryServiceStrategy;
    }
    
    private String url;
	public void setRolsacUrl(String url) {
		this.url = url;
		if (this.personalQueryServiceStrategy != null) {
			this.personalQueryServiceStrategy.setUrl(url);
		}
	}

    public PersonalQueryServiceAdapter(PersonalDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return personalQueryServiceStrategy instanceof PersonalQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException {
        if (this.getUnidadAdministrativa() == null) {return null;}
        try {
            UnitatAdministrativaDTO dto = personalQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa());
            UnitatAdministrativaQueryServiceAdapter uqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
            if (uqsa != null && url != null) {
            	uqsa.setRolsacUrl(url);
            }
            return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "unidad administrativa.", e);
        }
    }



}
