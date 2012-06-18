package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class PerfilQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }

    @Test
    public void llistarIconesFamilia() {
        PerfilCriteria pCriteria = new PerfilCriteria();
        pCriteria.setId("634701");
        PerfilQueryServiceAdapter p = rolsacQS.obtenirPerfil(pCriteria);
        Assert.assertNotNull(p);
        List<IconaFamiliaQueryServiceAdapter> icoList = p.llistarIconesFamilia(new IconaFamiliaCriteria()); 
        Assert.assertTrue(icoList.size() > 0);
    }
    
    @Test
    public void llistarIconesMateria() {
        PerfilCriteria pCriteria = new PerfilCriteria();
        pCriteria.setId("634701");
        PerfilQueryServiceAdapter p = rolsacQS.obtenirPerfil(pCriteria);
        Assert.assertNotNull(p);
        List<IconaMateriaQueryServiceAdapter> icoList = p.llistarIconesMateria(new IconaMateriaCriteria()); 
        Assert.assertTrue(icoList.size() > 0);
    }
    
    @Test
    public void getNumIconesFamilia() {
        PerfilCriteria pCriteria = new PerfilCriteria();
        pCriteria.setId("634701");
        PerfilQueryServiceAdapter p = rolsacQS.obtenirPerfil(pCriteria);
        Assert.assertNotNull(p);
        int numIcones = p.getNumIconesFamilia();
        Assert.assertTrue(numIcones > 0);
    }
    
    @Test
    public void getNumIconesMateria() {
        PerfilCriteria pCriteria = new PerfilCriteria();
        pCriteria.setId("634701");
        PerfilQueryServiceAdapter p = rolsacQS.obtenirPerfil(pCriteria);
        Assert.assertNotNull(p);
        int numIcones = p.getNumIconesMateria();
        Assert.assertTrue(numIcones > 0);
    }
    
}
