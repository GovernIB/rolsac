package es.caib.rolsac.api.v2.test;

import java.util.List;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAOrdenacio;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaOrdenacio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaOrdenacio;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class SeccioQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }
   
    @Test
    public void obtenirNumFilles() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("52");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            int numelements = seccio.getNumFilles();        
            Assert.assertTrue(numelements == 19);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumFitxes() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("148347");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            int numelements = seccio.getNumFitxes();        
            Assert.assertTrue(numelements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumPares() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("174499");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            int numelements = seccio.getNumPares();        
            Assert.assertTrue(numelements == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumUnitatsAdministratives() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("252709");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            int numelements = seccio.getNumUnitatsAdministratives();        
            Assert.assertTrue(numelements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarFilles() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("171256");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            seccioCriteria.setId("174499");
            List<SeccioQueryServiceAdapter> listSeccioQueryServiceAdapter = seccio.llistarFilles(seccioCriteria);
            Assert.assertTrue(listSeccioQueryServiceAdapter.size() == 5);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarFitxes() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("171215");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            FitxaCriteria fitxaCriteria = new FitxaCriteria();
            fitxaCriteria.setIdioma("ca");

            FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
            FitxaUAOrdenacio[] fitxaUAOrdenacio = new FitxaUAOrdenacio[] {
                    FitxaUAOrdenacio.ordenseccion_desc
            };
            fitxaUACriteria.setOrdenar(fitxaUAOrdenacio);

            List<FitxaQueryServiceAdapter> listFitxaQueryServiceAdapter = seccio.llistarFitxes(fitxaCriteria, fitxaUACriteria);
            Assert.assertTrue(listFitxaQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarOrdenarFitxes() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("171215");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            FitxaCriteria fitxaCriteria = new FitxaCriteria();
            fitxaCriteria.setIdioma("ca");
            FitxaOrdenacio[] ordenacio = {
                    FitxaOrdenacio.fechaActualizacion_asc
            };
            fitxaCriteria.setOrdenar(ordenacio);
            List<FitxaQueryServiceAdapter> listFitxaQueryServiceAdapter = seccio.llistarFitxes(fitxaCriteria, new FitxaUACriteria());
            Assert.assertTrue(listFitxaQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarOrdenarFitxes2() {

        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("171215");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);

            FitxaCriteria fitxaCriteria = new FitxaCriteria();
            fitxaCriteria.setIdioma("ca");
            FitxaOrdenacio[] ordenacio = {
                    FitxaOrdenacio.fechaActualizacion_asc
            };
            fitxaCriteria.setOrdenar(ordenacio);

            FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
            FitxaUAOrdenacio[] fitxaUAOrdenacio = new FitxaUAOrdenacio[] {
                    FitxaUAOrdenacio.ordenseccion_desc
            };
            fitxaUACriteria.setOrdenar(fitxaUAOrdenacio);

            List<FitxaQueryServiceAdapter> listFitxaQueryServiceAdapter = seccio.llistarFitxes(fitxaCriteria, fitxaUACriteria);
            Assert.assertTrue(listFitxaQueryServiceAdapter.size() > 0);

        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarPares() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("174499");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            List<SeccioQueryServiceAdapter> listSeccioQueryServiceAdapter = seccio.llistarPares();
            Assert.assertTrue(listSeccioQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void obtenirPare() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("174499");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);
            SeccioQueryServiceAdapter seccioQueryServiceAdapter = seccio.obtenirPare();
            Assert.assertNotNull(seccioQueryServiceAdapter);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarUnitatsAdministratives() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("171214");
        try {
            SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
            Assert.assertNotNull(seccio);

            UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
            UnitatAdministrativaOrdenacio[] unitatAdministrativaOrdenacio = new UnitatAdministrativaOrdenacio[] {
                    UnitatAdministrativaOrdenacio.responsable_asc
            };
            unitatAdministrativaCriteria.setOrdenar(unitatAdministrativaOrdenacio);
            FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
            FitxaUAOrdenacio[] fitxaUAOrdenacio = new FitxaUAOrdenacio[] {
                    FitxaUAOrdenacio.ordenseccion_desc
            };
            fitxaUACriteria.setOrdenar(fitxaUAOrdenacio);

            List<UnitatAdministrativaQueryServiceAdapter> listUaQueryServiceAdapter = seccio.llistarUnitatsAdministratives(unitatAdministrativaCriteria, fitxaUACriteria);
            Assert.assertTrue(listUaQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
}
