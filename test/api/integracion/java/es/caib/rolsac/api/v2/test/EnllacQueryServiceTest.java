package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class EnllacQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }
   
    @Test
    public void obtenirFitxa() {
        EnllacCriteria enllacCriteria = new EnllacCriteria();
        enllacCriteria.setId("1381356");
        try {
            EnllacQueryServiceAdapter enllac = rolsacQS.obtenirEnllac(enllacCriteria);
            Assert.assertNotNull(enllac);
            FitxaQueryServiceAdapter fitxa = enllac.obtenirFitxa();      
            Assert.assertTrue(fitxa.getId() == 1372158);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    /**
     * Sempre han de ser null, ja que actualment els procediments
     * no utilitzen enllaços
     */
    @Test
    public void obtenirProcediment() {
        EnllacCriteria enllacCriteria = new EnllacCriteria();
        enllacCriteria.setId("570970");
        try {
            EnllacQueryServiceAdapter enllac = rolsacQS.obtenirEnllac(enllacCriteria);
            Assert.assertNotNull(enllac);
            ProcedimentQueryServiceAdapter procediment = enllac.obtenirProcediment();
            Assert.assertNull(procediment);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
}
