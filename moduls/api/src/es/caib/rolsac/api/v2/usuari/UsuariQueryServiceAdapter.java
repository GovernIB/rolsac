package es.caib.rolsac.api.v2.usuari;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.ejb.UsuariQueryServiceEJBStrategy;

public class UsuariQueryServiceAdapter extends UsuariDTO implements UsuariQueryService {

    private UsuariQueryServiceStrategy usuariQueryServiceStrategy;

    public void setUsuariQueryServiceStrategy(UsuariQueryServiceStrategy usuariQueryServiceStrategy) {
        this.usuariQueryServiceStrategy = usuariQueryServiceStrategy;
    }

    public UsuariQueryServiceAdapter(UsuariDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return usuariQueryServiceStrategy instanceof UsuariQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumUnitatsAdministratives() {
        return usuariQueryServiceStrategy.getNumUnitatsAdministratives(id);
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> llistaDTO = usuariQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaUAQueryService = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaUAQueryService.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
        }
        return llistaUAQueryService;
    }

}
