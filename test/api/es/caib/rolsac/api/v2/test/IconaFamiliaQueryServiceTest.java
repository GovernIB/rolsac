package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class IconaFamiliaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void obtenirFamilia(){
        IconaFamiliaCriteria ifCriteria = new IconaFamiliaCriteria();
        ifCriteria.setId("635257");
        try {
            IconaFamiliaQueryServiceAdapter ifam = rolsacQS.obtenirIconaFamilia(ifCriteria);
            Assert.assertNotNull(ifam);
            FamiliaQueryServiceAdapter familia = ifam.obtenirFamilia();
            Assert.assertNotNull(familia);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirPerfil(){
        IconaFamiliaCriteria ifCriteria = new IconaFamiliaCriteria();
        ifCriteria.setId("635257");
        try {
            IconaFamiliaQueryServiceAdapter ifam = rolsacQS.obtenirIconaFamilia(ifCriteria);
            Assert.assertNotNull(ifam);
            PerfilQueryServiceAdapter perfil = ifam.obtenirPerfil();
            Assert.assertNotNull(perfil);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void obtenirIcona(){
        IconaFamiliaCriteria ifCriteria = new IconaFamiliaCriteria();
        ifCriteria.setId("635257");
        try {
            IconaFamiliaQueryServiceAdapter ifam = rolsacQS.obtenirIconaFamilia(ifCriteria);
            Assert.assertNotNull(ifam);
            ArxiuQueryServiceAdapter icona = ifam.obtenirIcona();
            Assert.assertNotNull(icona);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

}
