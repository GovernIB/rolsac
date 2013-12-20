package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class PublicObjectiuQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }
    
    @Test
    public void getNumAgrupacionsFV() {
        PublicObjectiuCriteria poCriteria = new PublicObjectiuCriteria();
        poCriteria.setId("200");
        try {
            PublicObjectiuQueryServiceAdapter po = rolsacQS.obtenirPublicObjectiu(poCriteria);
            Assert.assertNotNull(po);
            int numAgrupacions = po.getNumAgrupacions();        
            Assert.assertTrue(numAgrupacions > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarAgrupacionsFV() {
        PublicObjectiuCriteria poCriteria = new PublicObjectiuCriteria();
        poCriteria.setId("200");
        try {
            PublicObjectiuQueryServiceAdapter po = rolsacQS.obtenirPublicObjectiu(poCriteria);
            Assert.assertNotNull(po);
            List<AgrupacioFetVitalQueryServiceAdapter> agrupacions = po.llistarAgrupacions(new AgrupacioFetVitalCriteria());
            Assert.assertTrue(agrupacions.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarProcediments() {
        PublicObjectiuCriteria poCriteria = new PublicObjectiuCriteria();
        poCriteria.setId("200");
        try {
            PublicObjectiuQueryServiceAdapter po = rolsacQS.obtenirPublicObjectiu(poCriteria);
            Assert.assertNotNull(po);
            List<ProcedimentQueryServiceAdapter> procediments = po.llistarProcediments(new ProcedimentCriteria());
            Assert.assertTrue(procediments.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarFitxes() {
        PublicObjectiuCriteria poCriteria = new PublicObjectiuCriteria();
        poCriteria.setId("200");
        try {
            PublicObjectiuQueryServiceAdapter po = rolsacQS.obtenirPublicObjectiu(poCriteria);
            Assert.assertNotNull(po);
            FitxaCriteria fitxaCriteria = new FitxaCriteria();
            fitxaCriteria.setIdioma("ca");
            List<FitxaQueryServiceAdapter> fitxes = po.llistarFitxes(fitxaCriteria);
            Assert.assertTrue(fitxes.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
}
