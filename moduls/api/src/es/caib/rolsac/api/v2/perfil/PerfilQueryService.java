package es.caib.rolsac.api.v2.perfil;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryService;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryService;

public interface PerfilQueryService {

    List<IconaFamiliaQueryService> llistarIconesFamilia(IconaFamiliaCriteria iconaFamiliaCriteria);

    List<IconaMateriaQueryService> llistarIconesMateria(IconaMateriaCriteria iconaMateriaCriteria);
    
    // getnumiconofamilia
    // getnumiconomateria

}
