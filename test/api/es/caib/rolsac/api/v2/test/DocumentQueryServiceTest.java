package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class DocumentQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void recuperarFitxa() {
        DocumentCriteria criteria = new DocumentCriteria();
        criteria.setId("241559");
        try {
            DocumentQueryServiceAdapter doc = rolsacQS.obtenirDocument(criteria);
            Assert.assertNotNull(doc);
            FitxaQueryServiceAdapter fa = (FitxaQueryServiceAdapter) doc.obtenirFitxa();
            Assert.assertNotNull(fa);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarProcediment() {
        DocumentCriteria criteria = new DocumentCriteria();
        criteria.setId("323498");
        try {
            DocumentQueryServiceAdapter doc = rolsacQS.obtenirDocument(criteria);
            Assert.assertNotNull(doc);
            ProcedimentQueryServiceAdapter pa = (ProcedimentQueryServiceAdapter) doc.obtenirProcediment();
            Assert.assertNotNull(pa);
        } catch (QueryServiceException e) {
             Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarArxiu() {
        DocumentCriteria criteria = new DocumentCriteria();
        criteria.setId("241559");
        try {
            DocumentQueryServiceAdapter doc = rolsacQS.obtenirDocument(criteria);
            Assert.assertNotNull(doc);
            ArxiuQueryServiceAdapter aa = (ArxiuQueryServiceAdapter) doc.obtenirArxiu();
            Assert.assertNotNull(aa);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

}
