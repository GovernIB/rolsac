package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceAdapter;

public class TipusQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    @Test
    public void obtenirNumNormativa() {
        TipusCriteria tipusCriteria = new TipusCriteria();
        tipusCriteria.setId("16");
        try {
            TipusQueryServiceAdapter tipus = rolsacQS.obtenirTipus(tipusCriteria);
            Assert.assertNotNull(tipus);
            int numNormatives = tipus.getNumNormatives();        
            Assert.assertTrue(numNormatives > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumNormativaLocals() {
        TipusCriteria tipusCriteria = new TipusCriteria();
        tipusCriteria.setId("16");
        try {
            TipusQueryServiceAdapter tipus = rolsacQS.obtenirTipus(tipusCriteria);
            Assert.assertNotNull(tipus);
            int numNormatives = tipus.getNumNormativesLocals();     
            Assert.assertTrue(numNormatives == 11);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirNumNormativaExternes() {
        TipusCriteria tipusCriteria = new TipusCriteria();
        tipusCriteria.setId("16");
        try {
            TipusQueryServiceAdapter tipus = rolsacQS.obtenirTipus(tipusCriteria);
            Assert.assertNotNull(tipus);
            int numNormatives = tipus.getNumNormativesExternes();        
            Assert.assertTrue(numNormatives == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarNormatives() {
        TipusCriteria tipusCriteria = new TipusCriteria();
        tipusCriteria.setId("16");
        try {
            TipusQueryServiceAdapter tipus = rolsacQS.obtenirTipus(tipusCriteria);
            Assert.assertNotNull(tipus);
            NormativaCriteria normativaCriteria = new NormativaCriteria();
            normativaCriteria.setT_titulo("%Etiqueta Ecològica comunitària%");
            List<NormativaQueryServiceAdapter> listNormativaQueryServiceAdapter = tipus.llistarNormatives(normativaCriteria);
            Assert.assertTrue(listNormativaQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
