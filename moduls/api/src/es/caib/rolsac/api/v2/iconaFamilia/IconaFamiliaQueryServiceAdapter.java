package es.caib.rolsac.api.v2.iconaFamilia;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.familia.FamiliaQueryService;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaFamilia.ejb.IconaFamiliaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;

public class IconaFamiliaQueryServiceAdapter extends IconaFamiliaDTO implements IconaFamiliaQueryService {

    IconaFamiliaQueryServiceStrategy iconaFamiliaQueryServiceStrategy;

    public IconaFamiliaQueryServiceAdapter() {
        // FIXME: don't harcode the IconaFamiliaQueryServiceEJBStrategy.
        iconaFamiliaQueryServiceStrategy = new IconaFamiliaQueryServiceEJBStrategy();
    }
    
    public IconaFamiliaQueryServiceAdapter(IconaFamiliaDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public FamiliaQueryService obtenirFamilia(FamiliaCriteria familiaCriteria) {
        FamiliaDTO dto = iconaFamiliaQueryServiceStrategy.obtenirFamilia(id, familiaCriteria);
        return new FamiliaQueryServiceAdapter(dto);
    }

    public MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria) {
        MateriaDTO dto = iconaFamiliaQueryServiceStrategy.obtenirMateria(id, materiaCriteria);
        return new MateriaQueryServiceAdapter(dto);
    }
}
