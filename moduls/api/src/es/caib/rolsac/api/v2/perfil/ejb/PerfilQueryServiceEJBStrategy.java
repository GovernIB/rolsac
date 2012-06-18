package es.caib.rolsac.api.v2.perfil.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceStrategy;

public class PerfilQueryServiceEJBStrategy implements PerfilQueryServiceStrategy {

    private PerfilQueryServiceDelegate perfilQueryServiceDelegate;

    public void setPerfilQueryServiceDelegate(PerfilQueryServiceDelegate perfilQueryServiceDelegate) {
        this.perfilQueryServiceDelegate = perfilQueryServiceDelegate;
    }

    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        return perfilQueryServiceDelegate.llistarIconesFamilia(id, iconaFamiliaCriteria);
    }

    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        return perfilQueryServiceDelegate.llistarIconesMateria(id, iconaMateriaCriteria);
    }

    public int getNumIconesFamilia(long id) {
        return perfilQueryServiceDelegate.getNumIconesFamilia(id);
    }

    public int getNumIconesMateria(long id) {
        return perfilQueryServiceDelegate.getNumIconesMateria(id);
    }

}
