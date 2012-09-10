package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class TaxaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService(STRATEGY.WS);
    }

    @Test
    public void obtenirTramit() {
        TaxaCriteria taxaCriteria = new TaxaCriteria();
        taxaCriteria.setId("622947");
        try {
            TaxaQueryServiceAdapter taxa = rolsacQS.obtenirTaxa(taxaCriteria);
            Assert.assertNotNull(taxa);
            TramitQueryServiceAdapter tramit = taxa.obtenirTramit();     
            Assert.assertTrue(tramit.getId() == 622922);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
 }
