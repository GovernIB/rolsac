package es.caib.rolsac.api.v2.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaQueryServiceTest {

	RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }
    
    @Test
    public void adapterTest() {
        FitxaDTO dto = new FitxaDTO();
        dto.setId(Long.valueOf(Constants.ID_FITXA_EXISTENT));
        FitxaQueryServiceAdapter adapter = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", STRATEGY.WS, dto);
        try {
            int num = adapter.getNumDocuments();
            Assert.assertTrue(num > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumDocuments() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("999635");
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            int numElements = fitxa.getNumDocuments();
            Assert.assertTrue(numElements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirDocuments() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("999635");
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            List<DocumentQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarDocuments(new DocumentCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumEnllacos() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("1353300");
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            int numElements = fitxa.getNumEnllacos();
            Assert.assertTrue(numElements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirEnllassos() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            List<EnllacQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarEnllacos(new EnllacCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumFetsVitals() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("1380034");
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            int numElements = fitxa.getNumFetsVitals();
            Assert.assertTrue(numElements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirFetsVitals() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            List<FetVitalQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarFetsVitals(new FetVitalCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumSeccions() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            int numElements = fitxa.getNumSeccions();
            Assert.assertTrue(numElements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirSeccions() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            List<SeccioQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarSeccions(new SeccioCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
             Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirFitxesUA() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);

            FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
            fitxaUACriteria.setSeccion(Integer.toString(171212));

            List<FitxaUAQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarFitxesUA(fitxaUACriteria);
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
             Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumUnitatsAdministratives() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            int numElements = fitxa.getNumUnitatsAdministratives();
            Assert.assertTrue(numElements > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirUnitatsAdministratives() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            List<UnitatAdministrativaQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
             Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirBaner() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("1372158");
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            ArxiuQueryServiceAdapter baner = fitxa.obtenirBaner();
            Assert.assertNotNull(baner);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void obtenirIcona() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("1372158");
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            ArxiuQueryServiceAdapter icona = fitxa.obtenirIcona();
            Assert.assertNotNull(icona);
        } catch (QueryServiceException e) {
             Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirImatge() {

        List<String> lista = new ArrayList<String>();
        lista.add(Constants.ID_FITXA_EXISTENT);
        lista.add("1143969");
        lista.add("1144071");
        lista.add("1144276");
        lista.add("1144639");
        lista.add("1144659");

        FitxaCriteria fitxaCriteria = new FitxaCriteria();
//        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            for (String l : lista) {
                fitxaCriteria.setId(l);
                FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
                Assert.assertNotNull(fitxa);
                ArxiuQueryServiceAdapter imatge = fitxa.obtenirImatge();
                Assert.assertNotNull(imatge);
            }
            fitxaCriteria.setId("1143961");
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            ArxiuQueryServiceAdapter imatge = fitxa.obtenirImatge();
            Assert.assertNotNull(imatge);

        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarPublicsObjectius() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(Constants.ID_FITXA_EXISTENT);
        fitxaCriteria.setIdioma("ca");
        try {
            FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
            Assert.assertNotNull(fitxa);
            List<PublicObjectiuQueryServiceAdapter> listPOQueryServiceAdapter = fitxa.llistarPublicsObjectius(new PublicObjectiuCriteria());
            Assert.assertTrue(listPOQueryServiceAdapter.size() == 3);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarFitxesSeccio() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setSeccion("1121799");
        fitxaCriteria.setIdioma("ca");
        fitxaCriteria.setActiu(true);
        fitxaCriteria.setTamany("3");
        List<FitxaQueryServiceAdapter> fitxes;
        try {
            fitxes = rolsacQS.llistarFitxes(fitxaCriteria);
            Assert.assertTrue(fitxes.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

}
