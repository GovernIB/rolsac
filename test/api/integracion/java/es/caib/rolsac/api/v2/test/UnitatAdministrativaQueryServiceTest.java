package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.tractament.TractamentCriteria;
import es.caib.rolsac.api.v2.tractament.TractamentQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public class UnitatAdministrativaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {        
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    @Test
    public void recuperarNumEdificis() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumEdificis();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumFilles() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumFilles();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumFitxes() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumFitxes();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumSeccions() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumSeccions();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumMateries() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumMateries();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumNormatives() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumNormatives();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumPersonal() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumPersonal();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumProcediments() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("6");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumProcediments();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
   
    @Test
    public void recuperarNumTramits() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("6");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumTramits();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNumUsuaris() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("6");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            int resultat = unitatAdministrativa.getNumUsuaris();
            Assert.assertTrue(resultat > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarEdificis() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("6");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<EdificiQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarEdificis(new EdificiCriteria());        
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarFilles() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("6");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<UnitatAdministrativaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarFilles(new UnitatAdministrativaCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
   
    @Test
    public void recuperarFitxes() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("7");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            FitxaCriteria fitxaCriteria = new FitxaCriteria();
            fitxaCriteria.setIdioma("ca");
            List<FitxaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarFitxes(fitxaCriteria);    
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarSeccions() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<SeccioQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarSeccions(new SeccioCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarMateries() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<MateriaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarMateries(new MateriaCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarNormatives() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<NormativaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarNormatives(new NormativaCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarPersonal() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<PersonalQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarPersonal(new PersonalCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarProcediments() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<ProcedimentQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarProcediments(new ProcedimentCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarTramits() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<TramitQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarTramits(new TramitCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarUsuaris() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<UsuariQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarUsuaris(new UsuariCriteria());
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirLogoV() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            ArxiuQueryServiceAdapter logoV = unitatAdministrativa.obtenirLogov();
            Assert.assertNotNull(logoV);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
   
    @Test
    public void obtenirLogoS() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            ArxiuQueryServiceAdapter logoS = unitatAdministrativa.obtenirLogos();
            Assert.assertNotNull(logoS);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirLogoH() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            ArxiuQueryServiceAdapter logoH = unitatAdministrativa.obtenirLogoh();
            Assert.assertNotNull(logoH);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirLogoT() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            ArxiuQueryServiceAdapter logoT = unitatAdministrativa.obtenirLogot();
            Assert.assertNotNull(logoT);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirFotoG() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            ArxiuQueryServiceAdapter fotoG = unitatAdministrativa.obtenirFotog();
            Assert.assertNull(fotoG);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirFotoP() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            ArxiuQueryServiceAdapter fotop = unitatAdministrativa.obtenirFotop();
            Assert.assertNotNull(fotop);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirEspaiTerritorial() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            EspaiTerritorialQueryServiceAdapter espaiTerritorial = unitatAdministrativa.obtenirEspaiTerritorial();
            Assert.assertNotNull(espaiTerritorial);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirTractament() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            TractamentQueryServiceAdapter tractament = unitatAdministrativa.obtenirTractament(new TractamentCriteria());
            Assert.assertNotNull(tractament);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    } 
    
    @Test
    public void obtenirPare() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            UnitatAdministrativaQueryServiceAdapter pare = unitatAdministrativa.obtenirPare();
            Assert.assertNotNull(pare);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarDescendents() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        try {
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
            Assert.assertNotNull(unitatAdministrativa);
            List<Long> listQueryServiceAdapter = unitatAdministrativa.llistarDescendents();
            Assert.assertTrue(listQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
