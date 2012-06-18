package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class IconaMateriaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void obtenirMateria(){
        IconaMateriaCriteria imCriteria = new IconaMateriaCriteria();
        imCriteria.setId("635655");
        IconaMateriaQueryServiceAdapter ima = rolsacQS.obtenirIconaMateria(imCriteria);
        Assert.assertNotNull(ima);
        MateriaQueryServiceAdapter materia = ima.obtenirMateria();
        Assert.assertNotNull(materia);
    }
    
    @Test
    public void obtenirPerfil(){
        IconaMateriaCriteria imCriteria = new IconaMateriaCriteria();
        imCriteria.setId("635655");
        IconaMateriaQueryServiceAdapter ima = rolsacQS.obtenirIconaMateria(imCriteria);
        Assert.assertNotNull(ima);
        PerfilQueryServiceAdapter perfil = ima.obtenirPerfil();
        Assert.assertNotNull(perfil);
    }

    @Test
    public void obtenirIcona(){
        IconaMateriaCriteria imCriteria = new IconaMateriaCriteria();
        imCriteria.setId("635655");
        IconaMateriaQueryServiceAdapter ima = rolsacQS.obtenirIconaMateria(imCriteria);
        Assert.assertNotNull(ima);
        ArxiuQueryServiceAdapter icona = ima.obtenirIcona();
        Assert.assertNotNull(icona);
    }

}
