package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EdificiQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService();
    }
   
    @Test
    public void obtenirNumUnitatsAdministratives() {
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setId("97");
        try {
            EdificiQueryServiceAdapter edifici = rolsacQS.obtenirEdifici(edificiCriteria);
            Assert.assertNotNull(edifici);
            int numElements = edifici.getNumUnitatsAdministratives();
            Assert.assertTrue(numElements == 16);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirUnitatsAdministratives() {
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setId("287297");
        try {
            EdificiQueryServiceAdapter edifici = rolsacQS.obtenirEdifici(edificiCriteria);
            Assert.assertNotNull(edifici);
            List<UnitatAdministrativaQueryServiceAdapter> listQueryServiceAdapter = edifici.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() == 3);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirFotoGrande(){
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setId("93");
        try {
            EdificiQueryServiceAdapter edifici = rolsacQS.obtenirEdifici(edificiCriteria);
            Assert.assertNotNull(edifici);
            ArxiuQueryServiceAdapter fotoGrande = edifici.obtenirFotoGrande();
            Assert.assertTrue(fotoGrande.getId() == 139607);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void obtenirFotoPequenya(){
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setId("93");
        try {
            EdificiQueryServiceAdapter edifici = rolsacQS.obtenirEdifici(edificiCriteria);
            Assert.assertNotNull(edifici);
            ArxiuQueryServiceAdapter fotoPequenya = edifici.obtenirFotoPequenya();
            Assert.assertTrue(fotoPequenya.getId() == 112302);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirPlano(){
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setId("93");
        try {
            EdificiQueryServiceAdapter edifici = rolsacQS.obtenirEdifici(edificiCriteria);
            Assert.assertNotNull(edifici);
            ArxiuQueryServiceAdapter plano = edifici.obtenirPlano();
            Assert.assertTrue(plano.getId() == 139605);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
}
