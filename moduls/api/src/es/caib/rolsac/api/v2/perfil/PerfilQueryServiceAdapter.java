package es.caib.rolsac.api.v2.perfil;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryService;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryService;
import es.caib.rolsac.api.v2.perfil.ejb.PerfilQueryServiceEJBStrategy;

public class PerfilQueryServiceAdapter extends PerfilDTO implements PerfilQueryService {

    PerfilQueryServiceStrategy perfilQueryServiceStrategy;

    public PerfilQueryServiceAdapter() {
        perfilQueryServiceStrategy = new PerfilQueryServiceEJBStrategy();
    }
    
    public PerfilQueryServiceAdapter(PerfilDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public List<IconaFamiliaQueryService> llistarIconesFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IconaMateriaQueryService> llistarIconesMateria(IconaMateriaCriteria iconaMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }
}
