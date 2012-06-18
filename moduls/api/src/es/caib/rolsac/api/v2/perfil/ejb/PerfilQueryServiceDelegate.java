package es.caib.rolsac.api.v2.perfil.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;


public class PerfilQueryServiceDelegate {
    
    private PerfilQueryServiceEJBLocator perfilQueryServiceLocator;

    public void setPerfilQueryServiceLocator(PerfilQueryServiceEJBLocator perfilQueryServiceLocator) {
        this.perfilQueryServiceLocator = perfilQueryServiceLocator;
    }
    
    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        PerfilQueryServiceEJB ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
        return ejb.llistarIconesFamilia(id, iconaFamiliaCriteria);
    }

    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        PerfilQueryServiceEJB ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
        return ejb.llistarIconesMateria(id, iconaMateriaCriteria);
    }
    
    public int getNumIconesFamilia(long id) {
        PerfilQueryServiceEJB ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
        return ejb.getNumIconesFamilia(id);
    }
    
    public int getNumIconesMateria(long id) {
        PerfilQueryServiceEJB ejb = perfilQueryServiceLocator.getPerfilQueryServiceEJB();
        return ejb.getNumIconesMateria(id);
    }

}
