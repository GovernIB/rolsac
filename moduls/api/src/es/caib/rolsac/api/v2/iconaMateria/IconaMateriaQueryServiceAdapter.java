package es.caib.rolsac.api.v2.iconaMateria;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.iconaMateria.ejb.IconaMateriaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilQueryService;

public class IconaMateriaQueryServiceAdapter extends IconaMateriaDTO implements IconaMateriaQueryService {

    IconaMateriaQueryServiceStrategy iconaMateriaQueryServiceStrategy;

    public IconaMateriaQueryServiceAdapter() {
        // FIXME: don't harcode the IconaMateriaQueryServiceEJBStrategy.
        iconaMateriaQueryServiceStrategy = new IconaMateriaQueryServiceEJBStrategy();
    }
    
    public IconaMateriaQueryServiceAdapter(IconaMateriaDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria) {

        MateriaDTO dto = iconaMateriaQueryServiceStrategy.obtenirMateria(id, materiaCriteria);
        return new MateriaQueryServiceAdapter(dto);
    }

    public PerfilQueryService obtenirPerfil(PerfilCriteria perfilCriteria) {
        // TODO Auto-generated method stub
        return null;
    }
}
