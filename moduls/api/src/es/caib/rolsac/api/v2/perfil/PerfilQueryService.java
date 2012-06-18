package es.caib.rolsac.api.v2.perfil;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;

public interface PerfilQueryService {

    public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilia(IconaFamiliaCriteria iconaFamiliaCriteria);

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateria(IconaMateriaCriteria iconaMateriaCriteria);

    public int getNumIconesFamilia();

    public int getNumIconesMateria();

}
