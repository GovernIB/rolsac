package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class AgrupacioFetVitalQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }
    
    @Test
    public void adapterTest() {
        AgrupacioFetVitalDTO dto = new AgrupacioFetVitalDTO();
        dto.setId(211);
        AgrupacioFetVitalQueryServiceAdapter adapter = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", STRATEGY.WS, dto);
        try {
            int numfetsVitals = adapter.getNumFetsVitals();
            Assert.assertTrue(numfetsVitals > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void getNumFetsVitals() {
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("211");
        try {
            AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            Assert.assertNotNull(agrupacioFetVital);
            int numFetsVitals = agrupacioFetVital.getNumFetsVitals();
            Assert.assertTrue(numFetsVitals == 5);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarFetsVitals() {
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("210");
        try {
            AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            Assert.assertNotNull(agrupacioFetVital);
            List<FetVitalQueryServiceAdapter> listFetVitalQueryServiceAdapter = agrupacioFetVital.llistarFetsVitals(new FetVitalCriteria());
            Assert.assertTrue(listFetVitalQueryServiceAdapter.size() == 8);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void getFotografia(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("203");
        try {
            AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            Assert.assertNotNull(agrupacioFetVital);
            ArxiuQueryServiceAdapter fotografia = agrupacioFetVital.getFotografia();
            Assert.assertNotNull(fotografia);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getIcona(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("203");
        try {
            AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            Assert.assertNotNull(agrupacioFetVital);
            ArxiuQueryServiceAdapter icona = agrupacioFetVital.getIcona();
            Assert.assertNotNull(icona);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void getIconaGran(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setId("203");
        try {
            AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            Assert.assertNotNull(agrupacioFetVital);
            ArxiuQueryServiceAdapter iconaGran = agrupacioFetVital.getIconaGran();
            Assert.assertNotNull(iconaGran);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirPublicObjectiu(){
        AgrupacioFetVitalCriteria agrupacioFetVitalCriteria = new AgrupacioFetVitalCriteria();
        agrupacioFetVitalCriteria.setT_nombre("Conviure en família");
        try {
            AgrupacioFetVitalQueryServiceAdapter agrupacioFetVital = rolsacQS.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
            Assert.assertNotNull(agrupacioFetVital);
            PublicObjectiuQueryServiceAdapter publicObjectiu = agrupacioFetVital.obtenirPublicObjectiu();
            Assert.assertTrue(publicObjectiu.getCodigoEstandar().equals("EMPRESES"));
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
