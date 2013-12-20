package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;

public class TaxaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    /**
     * Actualmente no se usan las tasas, por tanto siempre debe devolver null
     */
    @Test
    public void obtenirTramit() {
        TaxaCriteria taxaCriteria = new TaxaCriteria();
        taxaCriteria.setId("622947");
        try {
            TaxaQueryServiceAdapter taxa = rolsacQS.obtenirTaxa(taxaCriteria);
            Assert.assertNull(taxa);
//            TramitQueryServiceAdapter tramit = taxa.obtenirTramit();     
//            Assert.assertTrue(tramit.getId() == 622922);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
 }
