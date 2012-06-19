package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class NormativaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);       
    }

    @Test
    public void recuperarAfectades() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("13607");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        List<NormativaQueryServiceAdapter> listNormativaQueryServiceAdapter = normativa.llistarAfectades();
        Assert.assertTrue(listNormativaQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void recuperarNumAfectades() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("328647");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        int numAfectades = normativa.getNumAfectades();
        Assert.assertTrue(numAfectades == 2);
    }
    
    @Test
    public void recuperarAfectants() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("12114");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        List<NormativaQueryServiceAdapter> listNormativaQueryServiceAdapter = normativa.llistarAfectants();
        Assert.assertTrue(listNormativaQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void recuperarNumAfectants() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("13216");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        int numAfectants = normativa.getNumAfectants();
        Assert.assertTrue(numAfectants == 2);
    }

    @Test
    public void recuperarProcediments() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("474368");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        List<ProcedimentQueryServiceAdapter> listProcedimentQueryServiceAdapter = normativa.llistarProcediments(new ProcedimentCriteria());
        Assert.assertTrue(listProcedimentQueryServiceAdapter.size() == 2);
    }

    @Test
    public void recuperarNumProcediments() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("437325");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        int numProcediments = normativa.getNumProcediments();
        Assert.assertTrue(numProcediments == 18);
    }
    
    @Test
    public void recuperarButlleti() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("5314");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        ButlletiQueryServiceAdapter butlletiQueryServiceAdapter = normativa.obtenirButlleti();
        Assert.assertNotNull(butlletiQueryServiceAdapter);
    }    
    
    @Test
    public void recuperarUnitatAdministrativa() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("79579");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativaQueryServiceAdapter = normativa.obtenirUnitatAdministrativa();
        Assert.assertTrue(unitatAdministrativaQueryServiceAdapter.getId() == 71562);
    }
    
    @Test
    public void recuperarArxiuNormativa() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("621620");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        ArxiuQueryServiceAdapter arxiuvaQueryServiceAdapter = normativa.obtenirArxiuNormativa();
        Assert.assertNotNull(arxiuvaQueryServiceAdapter);
    }
    
    @Test
    public void recuperarAfectacionsAfectants() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("75074");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        List<AfectacioQueryServiceAdapter> llistatAfectantsQueryServiceAdapter = normativa.llistarAfectacionsAfectants();
        Assert.assertTrue(llistatAfectantsQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void recuperarAfectacionsAfectades() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("13607");
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
        List<AfectacioQueryServiceAdapter> llistatAfectadesQueryServiceAdapter = normativa.llistarAfectacionsAfectades();
        
        Assert.assertTrue(llistatAfectadesQueryServiceAdapter.size() == 2);
    }
}
