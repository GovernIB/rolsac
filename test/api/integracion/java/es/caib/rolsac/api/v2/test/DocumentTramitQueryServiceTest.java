package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class DocumentTramitQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
    }
    
    @Test
    public void recuperarTramit() {
        DocumentTramitCriteria criteria = new DocumentTramitCriteria();
        criteria.setId("636328");
        try {
            DocumentTramitQueryServiceAdapter docTraAd = rolsacQS.obtenirDocumentTramit(criteria);
            Assert.assertNotNull(docTraAd);
            TramitQueryServiceAdapter ta = (TramitQueryServiceAdapter) docTraAd.obtenirTramit();
            Assert.assertNotNull(ta);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarArxiu() {
        DocumentTramitCriteria criteria = new DocumentTramitCriteria();
        criteria.setId("627763");
        try {
            DocumentTramitQueryServiceAdapter docTraAd = rolsacQS.obtenirDocumentTramit(criteria);
            Assert.assertNotNull(docTraAd);
            ArxiuQueryServiceAdapter aa = (ArxiuQueryServiceAdapter) docTraAd.obtenirArxiu();
            Assert.assertNotNull(aa);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

}
