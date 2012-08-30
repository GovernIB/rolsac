package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EspaiTerritorialQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService();
    }

    @Test
    public void obtenirNumFills() {
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("633281");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            int numElements = espaiTerritorial.getNumFills();
            Assert.assertTrue(numElements == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirFills() {
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("635504");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            List<EspaiTerritorialQueryServiceAdapter> listQueryServiceAdapter = espaiTerritorial.llistarFills(new EspaiTerritorialCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumUnitatsAdministratives() {
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("635359");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            int numElements = espaiTerritorial.getNumUnitatsAdministratives();
            Assert.assertTrue(numElements == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirUnitatsAdministratives() {
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("633281");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            List<UnitatAdministrativaQueryServiceAdapter> listQueryServiceAdapter = espaiTerritorial.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
             Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirLogo(){
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("635359");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            ArxiuQueryServiceAdapter logo = espaiTerritorial.obtenirLogo();
            Assert.assertNotNull(logo);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void obtenirMapa(){
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("635359");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            ArxiuQueryServiceAdapter mapa = espaiTerritorial.obtenirMapa();
            Assert.assertNotNull(mapa);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirPare(){
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId("635505");
        try {
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = rolsacQS.obtenirEspaiTerritorial(espaiTerritorialCriteria);
            Assert.assertNotNull(espaiTerritorial);
            EspaiTerritorialQueryServiceAdapter pare = espaiTerritorial.obtenirPare();
            Assert.assertTrue(pare.getId() == 635504);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
