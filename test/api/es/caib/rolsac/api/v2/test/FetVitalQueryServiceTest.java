package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class FetVitalQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void llistarFitxes() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("220");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        List<FitxaQueryServiceAdapter> listFitxaQueryServiceAdapter = fetVital.llistarFitxes(new FitxaCriteria());        
        Assert.assertTrue(listFitxaQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void llistarProcedimentsLocals() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("226");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        List<ProcedimentQueryServiceAdapter> listProcedimentQueryServiceAdapter = fetVital.llistarProcedimentsLocals(new ProcedimentCriteria());
        Assert.assertTrue(listProcedimentQueryServiceAdapter.size() == 2);
    }

    @Test
    public void llistarFetsVitalsAgrupacionsFV() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("234");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        List<AgrupacioFetVitalQueryServiceAdapter> listAgrupacioFetVitalQueryServiceAdapter = fetVital.llistarFetsVitalsAgrupacionsFV(new AgrupacioFetVitalCriteria());
        Assert.assertTrue(listAgrupacioFetVitalQueryServiceAdapter.size() == 3);
    } 
    
    @Test
    public void getNumProcedimentsLocals() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("221");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        int numProcediments = fetVital.getNumProcedimentsLocals();        
        Assert.assertTrue(numProcediments == 1);
    }
    
    @Test
    public void getNumFitxes() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("221");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        int numFitxes = fetVital.getNumFitxes();        
        Assert.assertTrue(numFitxes == 3);
    }
    
    @Test
    public void getNumFetsVitalsAgrupacionsFV() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("243");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        int numAgrupacionsFetsVitals = fetVital.getNumFetsVitalsAgrupacionsFV();        
        Assert.assertTrue(numAgrupacionsFetsVitals == 2);
    }
    
}
