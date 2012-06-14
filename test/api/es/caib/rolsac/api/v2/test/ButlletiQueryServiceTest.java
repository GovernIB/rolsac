package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class ButlletiQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void getNumNormatives() {
        ButlletiCriteria butlletiCriteria = new ButlletiCriteria();
        butlletiCriteria.setId("21");
        ButlletiQueryServiceAdapter ba = rolsacQS.obtenirButlleti(butlletiCriteria);
        Assert.assertNotNull(ba);
        int numNormatives = ba.getNumNormatives();
        int numNormativesLocals = ba.getNumNormativesLocals();
        int numNormativesexternes = ba.getNumNormativesExternes();
        Assert.assertTrue(numNormatives > 0);
        Assert.assertTrue(numNormatives == numNormativesLocals + numNormativesexternes);
    }
    
    @Test
    public void llistarNormatives() {
        ButlletiCriteria butlletiCriteria = new ButlletiCriteria();
        butlletiCriteria.setId("21");
        ButlletiQueryServiceAdapter ba = rolsacQS.obtenirButlleti(butlletiCriteria);
        Assert.assertNotNull(ba);
        NormativaCriteria nc = new NormativaCriteria();
        nc.setIncluirExternas(true);
        List<NormativaQueryServiceAdapter> normativesService = ba.llistarNormatives(nc);
        Assert.assertTrue(normativesService.size() > 0);
    }

}
