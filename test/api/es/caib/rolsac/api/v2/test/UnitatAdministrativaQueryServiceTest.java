package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
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
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }

    @Test
    public void recuperarNumEdificis() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("632367");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumEdificis();
        Assert.assertTrue(resultat == 2);
    }
    
    @Test
    public void recuperarNumFilles() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628665");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumFilles();
        Assert.assertTrue(resultat == 19);
    }
    
    @Test
    public void recuperarNumFitxes() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("756");        
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumFitxes();
        Assert.assertTrue(resultat == 7);
    }
    
    @Test
    public void recuperarNumSeccions() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2064");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumSeccions();
        Assert.assertTrue(resultat == 3);
    }
    
    @Test
    public void recuperarNumMateries() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumMateries();
        Assert.assertTrue(resultat == 9);
    }
    
    @Test
    public void recuperarNumNormatives() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("197");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumNormatives();
        Assert.assertTrue(resultat == 27);
    }
    
    @Test
    public void recuperarNumPersonal() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("138318");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumPersonal();
        Assert.assertTrue(resultat == 83);
    }
    
    @Test
    public void recuperarNumProcediments() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("142752");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumProcediments();
        Assert.assertTrue(resultat == 14);
    }
   
    @Test
    public void recuperarNumTramits() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("40");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumTramits();
        Assert.assertTrue(resultat == 13);
    }
    
    @Test
    public void recuperarNumUsuaris() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("1464");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        int resultat = unitatAdministrativa.getNumUsuaris();
        Assert.assertTrue(resultat == 10);
    }
    
    @Test
    public void recuperarEdificis() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628717");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<EdificiQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarEdificis(new EdificiCriteria());        
        Assert.assertTrue(listQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void recuperarFilles() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("52");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<UnitatAdministrativaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarFilles(new UnitatAdministrativaCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 4);
    }
   
    @Test
    public void recuperarFitxes() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2064");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<FitxaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarFitxes(new FitxaCriteria());    
        Assert.assertTrue(listQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void recuperarSeccions() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("2064");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<SeccioQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarSeccions(new SeccioCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void recuperarMateries() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("3184");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<MateriaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarMateries(new MateriaCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void recuperarNormatives() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("621331");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<NormativaQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarNormatives(new NormativaCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 5);
    }
    
    @Test
    public void recuperarPersonal() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("138631");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<PersonalQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarPersonal(new PersonalCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 5);
    }
    
    @Test
    public void recuperarProcediments() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("138143");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<ProcedimentQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarProcediments(new ProcedimentCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 5);
    }
    
    @Test
    public void recuperarTramits() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("233");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<TramitQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarTramits(new TramitCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 5);
    }
    
    @Test
    public void recuperarUsuaris() {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("186");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        List<UsuariQueryServiceAdapter> listQueryServiceAdapter = unitatAdministrativa.llistarUsuaris(new UsuariCriteria());
        Assert.assertTrue(listQueryServiceAdapter.size() == 4);
    }
    
    @Test
    public void obtenirLogoV(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628675");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        ArxiuQueryServiceAdapter logoV = unitatAdministrativa.obtenirLogov();
        Assert.assertNotNull(logoV);
    }
   
    @Test
    public void obtenirLogoS(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628675");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        ArxiuQueryServiceAdapter logoS = unitatAdministrativa.obtenirLogos();
        Assert.assertNotNull(logoS);
    }
    
    @Test
    public void obtenirLogoH(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628675");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        ArxiuQueryServiceAdapter logoH = unitatAdministrativa.obtenirLogoh();
        Assert.assertNotNull(logoH);
    }
    
    @Test
    public void obtenirLogoT(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628675");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        ArxiuQueryServiceAdapter logoT = unitatAdministrativa.obtenirLogot();
        Assert.assertNotNull(logoT);
    }
    
    @Test
    public void obtenirFotoG(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628675");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        ArxiuQueryServiceAdapter fotoG = unitatAdministrativa.obtenirFotog();
        Assert.assertNotNull(fotoG);
    }
    
    @Test
    public void obtenirFotoP(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("628675");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        ArxiuQueryServiceAdapter fotop = unitatAdministrativa.obtenirFotop();
        Assert.assertNotNull(fotop);
    }
    @Test
    public void obtenirEspaiTerritorial(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("633590");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        EspaiTerritorialQueryServiceAdapter espaiTerritorial = unitatAdministrativa.obtenirEspaiTerritorial();
        Assert.assertNotNull(espaiTerritorial);
    }
    @Test
    public void obtenirTractament(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("621230");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        TractamentQueryServiceAdapter tractament = unitatAdministrativa.obtenirTractament();
        Assert.assertNotNull(tractament);
    } 
    
    @Test
    public void obtenirPare(){
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId("139811");
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        Assert.assertNotNull(unitatAdministrativa);
        UnitatAdministrativaQueryServiceAdapter pare = unitatAdministrativa.obtenirPare();
        Assert.assertNotNull(pare);
    }
}
