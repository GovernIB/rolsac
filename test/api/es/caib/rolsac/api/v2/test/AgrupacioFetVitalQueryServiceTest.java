package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class AgrupacioFetVitalQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void adapterTest() {
        AgrupacioFetVitalDTO dto = new AgrupacioFetVitalDTO();
        dto.setId(211);
        AgrupacioFetVitalQueryServiceAdapter adapter = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter(
                "agrupacioFetVital", STRATEGY.EJB, dto);
        int numfetsVitals = adapter.getNumFetsVitals();
        Assert.assertTrue(numfetsVitals > 0);
    }
    
    @Test
    public void getNumFetsVitals() {
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("211");
        AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        Assert.assertNotNull(agrupacioFetVital);
        int numFetsVitals = agrupacioFetVital.getNumFetsVitals();
        Assert.assertTrue(numFetsVitals == 5);
    }
    
    @Test
    public void llistarFetsVitals() {
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("210");
        AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        Assert.assertNotNull(agrupacioFetVital);
        List<FetVitalQueryServiceAdapter> listFetVitalQueryServiceAdapter = agrupacioFetVital.llistarFetsVitals(new FetVitalCriteria());
        Assert.assertTrue(listFetVitalQueryServiceAdapter.size() == 8);
    }
    
    @Test
    public void getFotografia(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("635670");
        AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        Assert.assertNotNull(agrupacioFetVital);
        ArxiuQueryServiceAdapter fotografia = agrupacioFetVital.getFotografia();
        Assert.assertNotNull(fotografia);
    }

    @Test
    public void getIcona(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("634786");
        AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        Assert.assertNotNull(agrupacioFetVital);
        ArxiuQueryServiceAdapter icona = agrupacioFetVital.getIcona();
        Assert.assertNotNull(icona);
    }
    
    @Test
    public void getIconaGran(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("634790");
        AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        Assert.assertNotNull(agrupacioFetVital);
        ArxiuQueryServiceAdapter iconaGran = agrupacioFetVital.getIconaGran();
        Assert.assertNotNull(iconaGran);
    }
    
    @Test
    public void obtenirPublicObjectiu(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setT_nombre("Conviure en família");
        AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        Assert.assertNotNull(agrupacioFetVital);
        PublicObjectiuQueryServiceAdapter publicObjectiu = agrupacioFetVital.obtenirPublicObjectiu();
        Assert.assertTrue(publicObjectiu.getCodigoEstandar().equals("PERSONES"));
    }
}
