package es.caib.rolsac.api.v2.usuari;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.ejb.UsuariQueryServiceEJBStrategy;

public class UsuariQueryServiceAdapter extends UsuariDTO implements UsuariQueryService {

    private static final long serialVersionUID = -1735455660872334975L;

    private UsuariQueryServiceStrategy usuariQueryServiceStrategy;

    public void setUsuariQueryServiceStrategy(UsuariQueryServiceStrategy usuariQueryServiceStrategy) {
        this.usuariQueryServiceStrategy = usuariQueryServiceStrategy;
    }

    public UsuariQueryServiceAdapter(UsuariDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return usuariQueryServiceStrategy instanceof UsuariQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumUnitatsAdministratives() throws QueryServiceException {
        try {
            return usuariQueryServiceStrategy.getNumUnitatsAdministratives(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades administrativas.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = usuariQueryServiceStrategy.llistarUnitatsAdministratives(getId(), unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaUAQueryService = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                llistaUAQueryService.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
            }
            return llistaUAQueryService;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
        }
    }

}
