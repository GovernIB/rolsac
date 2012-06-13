package es.caib.rolsac.api.v2.usuari;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.ejb.UsuariQueryServiceEJBStrategy;

public class UsuariQueryServiceAdapter extends UsuariDTO implements UsuariQueryService {

    UsuariQueryServiceStrategy usuariQueryServiceStrategy;

    public UsuariQueryServiceAdapter() {
        // FIXME: don't harcode the UsuariQueryServiceEJBStrategy.
        usuariQueryServiceStrategy = new UsuariQueryServiceEJBStrategy();
    }

    public UsuariQueryServiceAdapter(UsuariDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public int getNumUnitatsAdministratives() {
        // TODO Auto-generated method stub
        return 0;
    }

    // Exemple de possible implementació dels mètodes que retornen llistes de
    // "query services"
    public List<UnitatAdministrativaQueryService> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {

        List<UnitatAdministrativaDTO> llistaDTO = usuariQueryServiceStrategy.llistarUnitatsAdministratives(id,
                unitatAdministrativaCriteria);

        List<UnitatAdministrativaQueryService> llistaUAQueryService = new ArrayList<UnitatAdministrativaQueryService>();

        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaUAQueryService.add(new UnitatAdministrativaQueryServiceAdapter(unitatAdministrativaDTO));
        }

        return llistaUAQueryService;
    }

}
