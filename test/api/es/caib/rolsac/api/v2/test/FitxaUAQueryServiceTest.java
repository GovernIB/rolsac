package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaUAQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService(STRATEGY.WS);
    }

    @Test
    public void obtenirFitxa() {
        FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
        fitxaUACriteria.setId("164707");
        try {
            FitxaUAQueryServiceAdapter fitxaUA = rolsacQS.obtenirFitxaUA(fitxaUACriteria);
            Assert.assertNotNull(fitxaUA);
            FitxaQueryServiceAdapter fitxa = fitxaUA.obtenirFitxa();
            Assert.assertTrue(fitxa.getId() == 164704);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirSeccio() {
        FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
        fitxaUACriteria.setId("164707");
        try {
            FitxaUAQueryServiceAdapter fitxaUA = rolsacQS.obtenirFitxaUA(fitxaUACriteria);
            Assert.assertNotNull(fitxaUA);
            SeccioQueryServiceAdapter seccio = fitxaUA.obtenirSeccio();
            Assert.assertTrue(seccio.getId() == 268);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirUnitatAdministrativa() {
        FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
        fitxaUACriteria.setId("164707");
        try {
            FitxaUAQueryServiceAdapter fitxaUA = rolsacQS.obtenirFitxaUA(fitxaUACriteria);
            Assert.assertNotNull(fitxaUA);
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativaQueryServiceAdapter = fitxaUA.obtenirUnitatAdministrativa();
            Assert.assertTrue(unitatAdministrativaQueryServiceAdapter.getId() == 142752);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
