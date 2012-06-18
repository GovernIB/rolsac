package es.caib.rolsac.api.v2.perfil.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceStrategy;

public class PerfilQueryServiceEJBStrategy implements PerfilQueryServiceStrategy {

    PerfilQueryServiceEJBLocator locator;
    PerfilQueryServiceDelegate delegate;

    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
