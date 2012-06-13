package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
   
    @Test
    public void obtenirNumDocuments() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("92472");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        int numElements = fitxa.getNumDocuments();
        Assert.assertTrue(numElements == 36);
    }
    
    @Test
    public void obtenirDocuments() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("236084");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        List<DocumentQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarDocuments(new DocumentCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 5);
    }
    
    @Test
    public void obtenirNumEnllacos() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("128245");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        int numElements = fitxa.getNumEnllacos();
        Assert.assertTrue(numElements == 3);
    }
    
    @Test
    public void obtenirEnllassos() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("465200");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        List<EnllacQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarEnllacos(new EnllacCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void obtenirNumFetsVitals() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("635770");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        int numElements = fitxa.getNumFetsVitals();
        Assert.assertTrue(numElements == 2);
    }
    
    @Test
    public void obtenirFetsVitals() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("530057");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        List<FetVitalQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarFetsVitals(new FetVitalCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void obtenirNumSeccions() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("178144");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        int numElements = fitxa.getNumSeccions();
        Assert.assertTrue(numElements == 1);
    }
    
    @Test
    public void obtenirSeccions() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("554485");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        List<SeccioQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarSeccions(new SeccioCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void obtenirNumUnitatsAdministratives() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("123853");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        int numElements = fitxa.getNumUnitatsAdministratives();
        Assert.assertTrue(numElements == 3);
    }
    
    @Test
    public void obtenirUnitatsAdministratives() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("442580");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        List<UnitatAdministrativaQueryServiceAdapter> listQueryServiceAdapter = fitxa.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void obtenirBaner(){
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("466837");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        ArxiuQueryServiceAdapter baner = fitxa.obtenirBaner();
        Assert.assertNotNull(baner);
    }

    @Test
    public void obtenirIcona(){
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("466837");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        ArxiuQueryServiceAdapter icona = fitxa.obtenirIcona();
        Assert.assertNotNull(icona);
    }
    
    @Test
    public void obtenirImatge(){
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("466837");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
        ArxiuQueryServiceAdapter imatge = fitxa.obtenirImatge();
        Assert.assertNotNull(imatge);
    }
}
