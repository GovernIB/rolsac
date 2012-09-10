package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class TramitQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService(STRATEGY.WS);
    }

    @Test
    public void getNumDocumentsInformatius() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("627586");
        try {
            TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
            Assert.assertNotNull(tramit);
            int numDocumentsInformatius = tramit.getNumDocumentsInformatius();        
            Assert.assertTrue(numDocumentsInformatius == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void getNumFormularis() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("627586");
        try {
            TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
            Assert.assertNotNull(tramit);
            int numFormularis = tramit.getNumFormularis();        
            Assert.assertTrue(numFormularis == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void getNumTaxes() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("636282");
        try {
            TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
            Assert.assertNotNull(tramit);
            int numTaxes = tramit.getNumTaxes();        
            Assert.assertTrue(numTaxes == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirDocumentsInformatius() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("622965");
        try {
            TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
            Assert.assertNotNull(tramit);
            List<DocumentTramitQueryServiceAdapter> listDocumentTramitQueryServiceAdapter = tramit.llistatDocumentsInformatius(new DocumentTramitCriteria());        
            Assert.assertTrue(listDocumentTramitQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
  
    @Test
    public void obtenirFormularis() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("622965");
        try {
            TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
            Assert.assertNotNull(tramit);
            List<DocumentTramitQueryServiceAdapter> listDocumentTramitQueryServiceAdapter = tramit.llistarFormularis(new DocumentTramitCriteria());     
            Assert.assertTrue(listDocumentTramitQueryServiceAdapter.size() == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarTaxes() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("627587");
        try {
            TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
            Assert.assertNotNull(tramit);
            List<TaxaQueryServiceAdapter> listTaxaQueryServiceAdapter = tramit.llistarTaxes(new TaxaCriteria());
            Assert.assertTrue(listTaxaQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
}
