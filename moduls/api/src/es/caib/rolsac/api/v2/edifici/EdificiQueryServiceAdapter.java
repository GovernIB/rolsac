package es.caib.rolsac.api.v2.edifici;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import es.caib.rolsac.api.v2.edifici.ejb.EdificiQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EdificiQueryServiceAdapter extends EdificiDTO implements EdificiQueryService {

    EdificiQueryServiceStrategy edificiQueryServiceStrategy;

    public EdificiQueryServiceAdapter() {
        edificiQueryServiceStrategy = new EdificiQueryServiceEJBStrategy();
    }
    
    public EdificiQueryServiceAdapter(EdificiDTO dto) {
        this();
        try {
            BeanUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public int getNumUnitatsAdministratives() {
        // TODO Auto-generated method stub
        return 0;
    }

    // Exemple de possible implementació amb llistes
    public List<UnitatAdministrativaQueryService> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {

        List<UnitatAdministrativaDTO> llistaDTO = edificiQueryServiceStrategy.llistarUnitatsAdministratives(id,
                unitatAdministrativaCriteria);

        List<UnitatAdministrativaQueryService> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryService>();

        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add(new UnitatAdministrativaQueryServiceAdapter(unitatAdministrativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

}