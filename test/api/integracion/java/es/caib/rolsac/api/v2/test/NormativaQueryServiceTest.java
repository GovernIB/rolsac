package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
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
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    @Test
    public void recuperarAfectades() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("13607");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            List<NormativaQueryServiceAdapter> listNormativaQueryServiceAdapter = normativa.llistarAfectades();
            Assert.assertTrue(listNormativaQueryServiceAdapter.size() == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumAfectades() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("328647");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            int numAfectades = normativa.getNumAfectades();
            Assert.assertTrue(numAfectades == 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarAfectants() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("12114");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            List<NormativaQueryServiceAdapter> listNormativaQueryServiceAdapter = normativa.llistarAfectants();
            Assert.assertTrue(listNormativaQueryServiceAdapter.size() == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumAfectants() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("12114");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            int numAfectants = normativa.getNumAfectants();
            Assert.assertTrue(numAfectants == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void recuperarProcediments() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("474368");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
            procedimentCriteria.setVisible(false);
            List<ProcedimentQueryServiceAdapter> listProcedimentQueryServiceAdapter = normativa.llistarProcediments(procedimentCriteria);
            Assert.assertTrue(listProcedimentQueryServiceAdapter.size() == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void recuperarNumProcediments() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("437325");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            int numProcediments = normativa.getNumProcediments();
            Assert.assertTrue(numProcediments == 18);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarButlleti() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("5314");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            ButlletiQueryServiceAdapter butlletiQueryServiceAdapter = normativa.obtenirButlleti();
            Assert.assertNotNull(butlletiQueryServiceAdapter);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }    
    
    @Test
    public void recuperarUnitatAdministrativa() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("79579");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativaQueryServiceAdapter = normativa.obtenirUnitatAdministrativa();
            Assert.assertTrue(unitatAdministrativaQueryServiceAdapter.getId() == 71562);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarArxiuNormativa() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("1372197");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            ArxiuQueryServiceAdapter arxiuvaQueryServiceAdapter = normativa.obtenirArxiuNormativa();
            Assert.assertNotNull(arxiuvaQueryServiceAdapter);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarAfectacionsAfectants() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("79579");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            List<AfectacioQueryServiceAdapter> llistatAfectantsQueryServiceAdapter = normativa.llistarAfectacionsAfectants();
            Assert.assertTrue(llistatAfectantsQueryServiceAdapter.size() == 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarAfectacionsAfectades() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("13607");
        try {
            NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
            Assert.assertNotNull(normativa);
            List<AfectacioQueryServiceAdapter> llistatAfectadesQueryServiceAdapter = normativa.llistarAfectacionsAfectades();
            Assert.assertTrue(llistatAfectadesQueryServiceAdapter.size() == 2);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
