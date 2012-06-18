package es.caib.rolsac.api.v2.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceAdapter;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.co.ByDateCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public class RolsacQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }

    /**
     * Cas d'us: Proves temporals.
     */
//    @Test
    public void prova() {
        final String procedimentId = "9070";
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId(procedimentId);
        procedimentCriteria.setTamany("10");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        Assert.assertEquals(Long.parseLong(procedimentId), procediment.getId().longValue());
    }
    
    /**
     * Cas d'us: Es recupera 1 procediment que existeix.
     */
    @Test
    public void obtenirProcediment() {
        final String procedimentId = "9070";
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId(procedimentId);
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        Assert.assertEquals(Long.parseLong(procedimentId), procediment.getId().longValue());
    }

    /**
     * Cas d'us: Es recupera 1 procediment que no existeix.
     */
    @Test
    public void obtenirProcedimentNoExistent() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("-1");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNull(procediment);
    }

    /**
     * Cas d'us: Es recuperen els procediments del 5 al 15 en catala ordenats per data de caducidad descendentment.
     */
    @Test
    public void recuperar10ProcedimentsCatalaOrdreDataCaducitat() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setIdioma("ca");
        procedimentCriteria.setInici("5");
        procedimentCriteria.setTamany("10");
        procedimentCriteria.setOrdenacio("fechaCaducidad asc"); 
        List<ProcedimentQueryServiceAdapter> procediments = rolsacQS.llistarProcediments(procedimentCriteria);
        Assert.assertTrue(procediments.size() <= 10);
    }

    /**
     * Cas d'us: Es recuperen 10 procediments que contenguin un nom concret en catala.
     */
    @Test
    public void recuperarProcedimentsPerNom() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setIdioma("ca");
        procedimentCriteria.setT_nombre("%test%");
        procedimentCriteria.setTamany("10");
        List<ProcedimentQueryServiceAdapter> procediments = rolsacQS.llistarProcediments(procedimentCriteria);
        Assert.assertTrue(procediments.size() > 0);
        for (ProcedimentQueryServiceAdapter pa: procediments) {
            Assert.assertTrue(pa.getNombre().toLowerCase().contains("test"));
        }
    }
    
    /**
     * Cas d'us: Recuperar procediments per data d'actualitzacio.
     */
    @Test
    public void recuperarProcedimentsPerDataActualitzacio() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        Calendar c = new GregorianCalendar(2005, 6, 4);
        Date date = c.getTime();
        procedimentCriteria.setFechaActualizacion(date);
        procedimentCriteria.setTamany("10");
        List<ProcedimentQueryServiceAdapter> procediments = rolsacQS.llistarProcediments(procedimentCriteria);
        Assert.assertTrue(procediments.size() > 0);
        for (ProcedimentQueryServiceAdapter pa: procediments) {
            Assert.assertEquals(
                    ByDateCriteria.DATE_CRITERIA_FORMATTER.format(pa.getFechaActualizacion()),
                    ByDateCriteria.DATE_CRITERIA_FORMATTER.format(date)
            );
        }
    }
    
    /**
     * Cas d'us: Recupera 10 procediments actius.
     */
    @Test
    public void recuperar10ProcedimentsActius() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setActiu(true);
        procedimentCriteria.setTamany("10");
        List<ProcedimentQueryServiceAdapter> procediments = rolsacQS.llistarProcediments(procedimentCriteria);
        Assert.assertEquals(procediments.size(), 10);
    }
    
    /**
     * Cas d'us: Recupera 10 procediments caducats.
     */
    @Test
    public void recuperar10ProcedimentsCaducats() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setActiu(false);
        procedimentCriteria.setTamany("10");
        List<ProcedimentQueryServiceAdapter> procediments = rolsacQS.llistarProcediments(procedimentCriteria);
        Assert.assertEquals(procediments.size(), 10);
    }

    /**
     * Cas d'us: Recupera materia destacada.
     */
    @Test
    public void recuperarMateriaDestacada() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setDestacada(false);
        materiaCriteria.setId("5521");
        materiaCriteria.setCodigoEstandar("AGRICULTURA");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNull(materia);
    }
    
    /**
     * Cas d'us: Recupera materies.
     */
    @Test
    public void recuperarMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setT_nombre("%recerca%");
        materiaCriteria.setTamany("10");
        List<MateriaQueryServiceAdapter> materies = rolsacQS.llistarMateries(materiaCriteria);
        Assert.assertTrue(materies.size() > 0);
        Assert.assertTrue(materies.size() <= 10);
        for (MateriaQueryServiceAdapter m: materies) {
            Assert.assertTrue(m.getNombre().toLowerCase().contains("recerca"));
        }
    }
    
    /**
     * Cas d'us: Recupera unitatAdministrativa per nom de responsable
     */
    @Test
    public void recuperarUnitatAdministrativa() {
       UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
       unitatAdministrativaCriteria.setResponsable("Francisca Reus Beltran");
       UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
       Assert.assertNotNull(unitatAdministrativa);
    }
    /**
     * Cas d'us: Recupera tramit.
     */
    @Test
    public void recuperarTramit() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId("624493");
        TramitQueryServiceAdapter tramit = rolsacQS.obtenirTramit(tramitCriteria);
        Assert.assertNotNull(tramit);
    }
    
    /**
     * Cas d'us: Recupera tramits.
     */
    @Test
    public void recuperarTramits() {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setT_nombre("%sol·licitud%");
        tramitCriteria.setTamany("10");
        List<TramitQueryServiceAdapter> tramits = rolsacQS.llistarTramits(tramitCriteria);
        Assert.assertTrue(tramits.size() > 0);
        Assert.assertTrue(tramits.size() <= 10);
        for (TramitQueryServiceAdapter t: tramits) {
            Assert.assertTrue(t.getNombre().toLowerCase().contains("sol·licitud"));
        }
    }
    
    /**
     * Cas d'us: Recupera ua.
     */
    @Test
    public void recuperarUA() {
        UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
        uaCriteria.setId("1");
        UnitatAdministrativaQueryServiceAdapter ua = rolsacQS.obtenirUnitatAdministrativa(uaCriteria);
        Assert.assertNotNull(ua);
    }
    
    /**
     * Cas d'us: Recupera uas.
     */
    @Test
    public void recuperarUAs() {
        UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
        uaCriteria.setT_nombre("%balears%");
        uaCriteria.setTamany("10");
        List<UnitatAdministrativaQueryServiceAdapter> uas = rolsacQS.llistarUnitatsAdministratives(uaCriteria);
        Assert.assertTrue(uas.size() > 0);
        Assert.assertTrue(uas.size() <= 10);
        for (UnitatAdministrativaQueryServiceAdapter ua: uas) {
            Assert.assertTrue(ua.getNombre().toLowerCase().contains("balears"));
        }
    }
    
    /**
     * Cas d'us: Recupera fet vital.
     */
    @Test
    public void obtenirFetVital() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setId("236");
        FetVitalQueryServiceAdapter fetVital = rolsacQS.obtenirFetVital(fetVitalCriteria);
        Assert.assertNotNull(fetVital);
        Assert.assertTrue(fetVital.getCodigoEstandar().equals("EDUCACPRIM"));
        Assert.assertTrue(fetVital.getNombre().contains("ria (6-12)"));
    }
    
    /**
     * Cas d'us: Recupera fets vitals.
     */
    @Test
    public void llistarFetsVitals() {
        FetVitalCriteria fetVitalCriteria = new FetVitalCriteria();
        fetVitalCriteria.setT_palabrasclave("%matrimoni%");
        List<FetVitalQueryServiceAdapter> fetsVitals = rolsacQS.llistarFetsVitals(fetVitalCriteria);
        Assert.assertTrue(fetsVitals.size() > 0);
        for (FetVitalQueryServiceAdapter fv: fetsVitals) {
            Assert.assertTrue(fv.getPalabrasclave().toLowerCase().contains("matrimoni"));
        }
    }
    
    /**
     * Cas d'us: Recupera fitxa.
     */
    @Test
    public void recuperarFitxa() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId("206971");
        FitxaQueryServiceAdapter fitxa = rolsacQS.obtenirFitxa(fitxaCriteria);
        Assert.assertNotNull(fitxa);
    }
        
    /**
     * Cas d'us: Recupera fitxes.
     */
    @Test
    public void recuperarFitxes() {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setT_titulo("%associacio%");
        fitxaCriteria.setTamany("10");
        List<FitxaQueryServiceAdapter> fitxes = rolsacQS.llistarFitxes(fitxaCriteria);
        Assert.assertTrue(fitxes.size() > 0);
        Assert.assertTrue(fitxes.size() <= 10);
        for (FitxaQueryServiceAdapter f: fitxes) {
            Assert.assertTrue(f.getTitulo().toLowerCase().contains("associacio"));
        }
    }
    
    /**
     * Cas d'us: Recupera normativa.
     */
    @Test
    public void recuperarNormativa() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setId("75074"); // local
//        normativaCriteria.setIncluirExternas(true);
//        normativaCriteria.setId("74965"); // externa
        NormativaQueryServiceAdapter normativa = rolsacQS.obtenirNormativa(normativaCriteria);
        Assert.assertNotNull(normativa);
    }
    
    /**
     * Cas d'us: Recupera normatives.
     */
    @Test
    public void recuperarNormatives() {
        NormativaCriteria normativaCriteria = new NormativaCriteria();
        normativaCriteria.setT_titulo("%decret%");
        normativaCriteria.setTamany("10");
        List<NormativaQueryServiceAdapter> normatives = rolsacQS.llistarNormatives(normativaCriteria);
        Assert.assertTrue(normatives.size() > 0);
        Assert.assertTrue(normatives.size() <= 10);
        for (NormativaQueryServiceAdapter n: normatives) {
            Assert.assertTrue(n.getTitulo().toLowerCase().contains("decret"));
        }
    }
    
    /**
     * Cas d'us: Recupera persona.
     */
    @Test
    public void recuperarPersona() {
        PersonalCriteria personalCriteria = new PersonalCriteria();
        personalCriteria.setUsername("u83269");
        PersonalQueryServiceAdapter personal = rolsacQS.obtenirPersonal(personalCriteria);
        Assert.assertNotNull(personal);
    }
    
    /**
     * Cas d'us: Recupera personal.
     */
    @Test
    public void recuperarPersonal() {
        PersonalCriteria personalCriteria = new PersonalCriteria();
        personalCriteria.setNombre("%maria%");
        personalCriteria.setTamany("5");
        List<PersonalQueryServiceAdapter> personal = rolsacQS.llistarPersonal(personalCriteria);
        Assert.assertTrue(personal.size() > 0);
        Assert.assertTrue(personal.size() <= 5);
        for (PersonalQueryServiceAdapter p: personal) {
            Assert.assertTrue(p.getNombre().toLowerCase().contains("maria"));
        }
    }
    
    /**
     * Cas d'us: Recupera document tramit
     */
    @Test
    public void obtenirDocumentTramit() {
        DocumentTramitCriteria documentTramitCriteria = new DocumentTramitCriteria();
        documentTramitCriteria.setId("636328");
        DocumentTramitQueryServiceAdapter docTramit = rolsacQS.obtenirDocumentTramit(documentTramitCriteria);
        Assert.assertNotNull(docTramit);
    }
    
    /**
     * Cas d'us: Recupera documents tramit
     */
    @Test
    public void llistarDocumentTramit() {
        DocumentTramitCriteria documentTramitCriteria = new DocumentTramitCriteria();
        documentTramitCriteria.setT_titulo("%talles%");
        documentTramitCriteria.setTamany("10");
        List<DocumentTramitQueryServiceAdapter> documentsTramit = rolsacQS.llistarDocumentTramit(documentTramitCriteria);
        Assert.assertTrue(documentsTramit.size() > 0);
        for (DocumentTramitQueryServiceAdapter d: documentsTramit) {
            Assert.assertTrue(d.getTitulo().toLowerCase().contains("talles"));
        }
    }

    /**
     * Cas d'us: Recupera usuari.
     */
    @Test
    public void recuperarUsuari() {
        UsuariCriteria usuariCriteria = new UsuariCriteria();
        usuariCriteria.setUsername("e37336797t");
        UsuariQueryServiceAdapter usuari = rolsacQS.obtenirUsuari(usuariCriteria);
        Assert.assertNotNull(usuari);
    }
    
    /**
     * Cas d'us: Recupera usuaris.
     */
    @Test
    public void recuperarUsuaris() {
        UsuariCriteria usuariCriteria = new UsuariCriteria();
        usuariCriteria.setNombre("%antonio%");
        usuariCriteria.setTamany("5");
        List<UsuariQueryServiceAdapter> usuaris = rolsacQS.llistarUsuaris(usuariCriteria);
        Assert.assertTrue(usuaris.size() > 0);
        for (UsuariQueryServiceAdapter u: usuaris) {
            Assert.assertTrue(u.getNombre().toLowerCase().contains("antonio"));
        }
    }
    
    /**
     * Cas d'us: Recupera taxa.
     */
    @Test
    public void recuperarTaxa() {
        TaxaCriteria taxaCriteria = new TaxaCriteria();
        taxaCriteria.setId("637066");
        TaxaQueryServiceAdapter taxa = rolsacQS.obtenirTaxa(taxaCriteria);
        Assert.assertNotNull(taxa);
    }
    
    /**
     * Cas d'us: Recupera taxes.
     */
    @Test
    public void llistarTaxes() {
        TaxaCriteria taxaCriteria = new TaxaCriteria();
        taxaCriteria.setT_descripcio("%euros%");
        taxaCriteria.setTamany("5");
        List<TaxaQueryServiceAdapter> taxes = rolsacQS.llistarTaxes(taxaCriteria);
        Assert.assertTrue(taxes.size() > 0);
        for (TaxaQueryServiceAdapter t: taxes) {
            Assert.assertTrue(t.getDescripcio().toLowerCase().contains("euros"));
        }
    }

    /**
     * Cas d'us: Recupera agrupacio fet vital.
     */
    @Test
    public void recuperarAgrupacioFetVital() {
        AgrupacioFetVitalCriteria afvCriteria = new AgrupacioFetVitalCriteria();
        afvCriteria.setId("210");
        AgrupacioFetVitalQueryServiceAdapter afv = rolsacQS.obtenirAgrupacioFetVital(afvCriteria);
        Assert.assertNotNull(afv);
    }
    
    /**
     * Cas d'us: Recupera agrupacions fets vitals.
     */
    @Test
    public void recuperarAgrupacionsFetsVitals() {
        AgrupacioFetVitalCriteria afvCriteria = new AgrupacioFetVitalCriteria();
        afvCriteria.setT_palabrasclave("%prova%");
        afvCriteria.setTamany("5");
        List<AgrupacioFetVitalQueryServiceAdapter> afvs = rolsacQS.llistarAgrupacionsFetsVitals(afvCriteria);
        Assert.assertTrue(afvs.size() > 0);
        for (AgrupacioFetVitalQueryServiceAdapter afv: afvs) {
            Assert.assertTrue(afv.getPalabrasclave().toLowerCase().contains("prova"));
        }
    }
    
    /**
     * Cas d'us: Recupera agrupacio materia.
     */
    @Test
    public void recuperarAgrupacioMateria() {
        AgrupacioMateriaCriteria amCriteria = new AgrupacioMateriaCriteria();
        amCriteria.setId("635712");
        AgrupacioMateriaQueryServiceAdapter am = rolsacQS.obtenirAgrupacioMateria(amCriteria);
        Assert.assertNotNull(am);
    }
    
    /**
     * Cas d'us: Recupera agrupacions materies.
     */
    @Test
    public void recuperarAgrupacionsMateries() {
        AgrupacioMateriaCriteria amCriteria = new AgrupacioMateriaCriteria();
        amCriteria.setT_nombre("%cultura%");
        amCriteria.setTamany("5");
        List<AgrupacioMateriaQueryServiceAdapter> ams = rolsacQS.llistarAgrupacionsMateries(amCriteria);
        Assert.assertTrue(ams.size() > 0);
        for (AgrupacioMateriaQueryServiceAdapter am: ams) {
            Assert.assertTrue(am.getNombre().toLowerCase().contains("cultura"));
        }
    }

    /**
     * Cas d'us: Recupera butlleti.
     */
    @Test
    public void recuperarButlleti() {
        ButlletiCriteria butlletiCriteria = new ButlletiCriteria();
        butlletiCriteria.setId("1");
        ButlletiQueryServiceAdapter am = rolsacQS.obtenirButlleti(butlletiCriteria);
        Assert.assertNotNull(am);
    }
    
    /**
     * Cas d'us: Recupera butlletins.
     */
    @Test
    public void recuperarButlletins() {
        ButlletiCriteria butlletiCriteria = new ButlletiCriteria();
        butlletiCriteria.setEnlace("%caib%");
        List<ButlletiQueryServiceAdapter> butlletins = rolsacQS.llistarButlletins(butlletiCriteria);
        Assert.assertTrue(butlletins.size() > 0);
        for (ButlletiQueryServiceAdapter b: butlletins) {
            Assert.assertTrue(b.getEnlace().toLowerCase().contains("caib"));
        }
    }

    /**
     * Cas d'us: Recupera document.
     */
    @Test
    public void recuperarDocument() {
        DocumentCriteria docCriteria = new DocumentCriteria();
        docCriteria.setId("241559");
        DocumentQueryServiceAdapter doc = rolsacQS.obtenirDocument(docCriteria);
        Assert.assertNotNull(doc);
    }
    
    /**
     * Cas d'us: Recupera documents.
     */
    @Test
    public void recuperarDocuments() {
        DocumentCriteria docCriteria = new DocumentCriteria();
        docCriteria.setT_titulo("%decret%");
        docCriteria.setTamany("5");
        List<DocumentQueryServiceAdapter> docs = rolsacQS.llistarDocuments(docCriteria);
        Assert.assertTrue(docs.size() > 0);
        for (DocumentQueryServiceAdapter doc: docs) {
            Assert.assertTrue(doc.getTitulo().toLowerCase().contains("decret"));
        }
    }

    /**
     * Cas d'us: Recupera edifici.
     */
    @Test
    public void recuperarEdifici() {
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setId("81");
        EdificiQueryServiceAdapter doc = rolsacQS.obtenirEdifici(edificiCriteria);
        Assert.assertNotNull(doc);
    }
    
    /**
     * Cas d'us: Recupera edificis.
     */
    @Test
    public void recuperarEdificis() {
        EdificiCriteria edificiCriteria = new EdificiCriteria();
        edificiCriteria.setDireccion("%pl%");
        edificiCriteria.setTamany("5");
        List<EdificiQueryServiceAdapter> edificis = rolsacQS.llistarEdificis(edificiCriteria);
        Assert.assertTrue(edificis.size() > 0);
        for (EdificiQueryServiceAdapter edifici: edificis) {
            Assert.assertTrue(edifici.getDireccion().toLowerCase().contains("pl"));
        }
    }

    /**
     * Cas d'us: Recupera enllac.
     */
    @Test
    public void recuperarEnllac() {
        EnllacCriteria enllacCriteria = new EnllacCriteria();
        enllacCriteria.setId("633923");
        EnllacQueryServiceAdapter doc = rolsacQS.obtenirEnllac(enllacCriteria);
        Assert.assertNotNull(doc);
    }
    
    /**
     * Cas d'us: Recupera enllacos.
     */
    @Test
    public void recuperarEnllacos() {
        EnllacCriteria enllacCriteria = new EnllacCriteria();
        enllacCriteria.setT_enlace("%caib%");
        enllacCriteria.setTamany("5");
        List<EnllacQueryServiceAdapter> enllacos = rolsacQS.llistarEnllacos(enllacCriteria);
        Assert.assertTrue(enllacos.size() > 0);
        for (EnllacQueryServiceAdapter enllac: enllacos) {
            Assert.assertTrue(enllac.getEnlace().toLowerCase().contains("caib"));
        }
    }

    /**
     * Cas d'us: Recupera espai territorial.
     */
    @Test
    public void recuperarEspaiTerritorial() {
        EspaiTerritorialCriteria etCriteria = new EspaiTerritorialCriteria();
        etCriteria.setId("633281");
        EspaiTerritorialQueryServiceAdapter et = rolsacQS.obtenirEspaiTerritorial(etCriteria);
        Assert.assertNotNull(et);
    }
    
    /**
     * Cas d'us: Recupera espais territorials.
     */
    @Test
    public void recuperarEspaisTerritorials() {
        EspaiTerritorialCriteria etCriteria = new EspaiTerritorialCriteria();
        etCriteria.setT_nombre("%mallorca%");
        etCriteria.setTamany("5");
        List<EspaiTerritorialQueryServiceAdapter> ets = rolsacQS.llistarEspaisTerritorials(etCriteria);
        Assert.assertTrue(ets.size() > 0);
        for (EspaiTerritorialQueryServiceAdapter et: ets) {
            Assert.assertTrue(et.getNombre().toLowerCase().contains("mallorca"));
        }
    }

    /**
     * Cas d'us: Recupera public objectiu.
     */
    @Test
    public void recuperarPublicObjectiu() {
        PublicObjectiuCriteria publicObjectiuCriteria = new PublicObjectiuCriteria();
        publicObjectiuCriteria.setId("200");
        PublicObjectiuQueryServiceAdapter po = rolsacQS.obtenirPublicObjectiu(publicObjectiuCriteria);
        Assert.assertNotNull(po);
    }
    
    /**
     * Cas d'us: Recupera publics objectius.
     */
    @Test
    public void recuperarPublicsObjectius() {
        PublicObjectiuCriteria publicObjectiuCriteria = new PublicObjectiuCriteria();
        publicObjectiuCriteria.setT_titulo("%persones%");
        List<PublicObjectiuQueryServiceAdapter> pos = rolsacQS.llistarPublicsObjectius(publicObjectiuCriteria);
        Assert.assertTrue(pos.size() > 0);
        for (PublicObjectiuQueryServiceAdapter po: pos) {
            Assert.assertTrue(po.getTitulo().toLowerCase().contains("persones"));
        }
    }
    
    /**
     * Cas d'us: Recupera familia.
     */
    @Test
    public void recuperarFamilia() {
        FamiliaCriteria familiaCriteria = new FamiliaCriteria();
        familiaCriteria.setId("1");
        FamiliaQueryServiceAdapter familia = rolsacQS.obtenirFamilia(familiaCriteria);
        Assert.assertNotNull(familia);
    }
    
    /**
     * Cas d'us: Recupera families.
     */
    @Test
    public void recuperarFamilies() {
        FamiliaCriteria familiaCriteria = new FamiliaCriteria();
        familiaCriteria.setT_nombre("%test%");
        familiaCriteria.setTamany("5");
        List<FamiliaQueryServiceAdapter> families = rolsacQS.llistarFamilies(familiaCriteria);
        Assert.assertTrue(families.size() > 0);
        for (FamiliaQueryServiceAdapter f: families) {
            Assert.assertTrue(f.getNombre().toLowerCase().contains("test"));
        }
    }
    
    /**
     * Cas d'us: Recupera fitxaUA.
     */
    @Test
    public void recuperarFitxaUA() {
        FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
        fitxaUACriteria.setId("186414");
        FitxaUAQueryServiceAdapter fitxa = rolsacQS.obtenirFitxaUA(fitxaUACriteria);
        Assert.assertNotNull(fitxa);
    }

    /**
     * Cas d'us: Recupera fitxesUA.
     */
    @Test
    public void recuperarFitxesUA() {
        FitxaUACriteria fitxaUACriteria = new FitxaUACriteria();
        fitxaUACriteria.setOrden("1");
        fitxaUACriteria.setTamany("5");
        List<FitxaUAQueryServiceAdapter> fitxesUA = rolsacQS.llistarFitxesUA(fitxaUACriteria);
        Assert.assertTrue(fitxesUA.size() > 0);
        for (FitxaUAQueryServiceAdapter fua: fitxesUA) {
            Assert.assertTrue(fua.getOrden() == 1);
        }
    }

    /**
     * Cas d'us: Recupera formulari.
     */
    @Test
    public void recuperarFormulari() {
        FormulariCriteria formulariCriteria = new FormulariCriteria();
        formulariCriteria.setId("205938");
        FormulariQueryServiceAdapter form = rolsacQS.obtenirFormulari(formulariCriteria);
        Assert.assertNotNull(form);
    }
    
    /**
     * Cas d'us: Recupera Formularis.
     */
    @Test
    public void recuperarFormularis() {
        FormulariCriteria formulariCriteria = new FormulariCriteria();
        formulariCriteria.setNombre("%Instancia%");
        formulariCriteria.setTamany("5");
        List<FormulariQueryServiceAdapter> forms = rolsacQS.llistarFormularis(formulariCriteria);
        Assert.assertTrue(forms.size() > 0);
        for (FormulariQueryServiceAdapter f: forms) {
            Assert.assertTrue(f.getNombre().toLowerCase().contains("instancia"));
        }
    }

    /**
     * Cas d'us: Recupera iconaFamilia.
     */
    @Test
    public void recuperarIconaFamilia() {
        IconaFamiliaCriteria ifCriteria = new IconaFamiliaCriteria();
        ifCriteria.setId("635257");
        IconaFamiliaQueryServiceAdapter iFam = rolsacQS.obtenirIconaFamilia(ifCriteria);
        Assert.assertNotNull(iFam);
    }
    
    /**
     * Cas d'us: Recupera iconesFamilies.
     */
    @Test
    public void recuperarIconesFamilies() {
        IconaFamiliaCriteria ifCriteria = new IconaFamiliaCriteria();
        ifCriteria.setTamany("3");
        List<IconaFamiliaQueryServiceAdapter> iFams = rolsacQS.llistarIconesFamilies(ifCriteria);
        Assert.assertTrue(iFams.size() <= 3);
    }

    /**
     * Cas d'us: Recupera iconaMateria.
     */
    @Test
    public void recuperarIconaMateria() {
        IconaMateriaCriteria imCriteria = new IconaMateriaCriteria();
        imCriteria.setId("635655");
        IconaMateriaQueryServiceAdapter iMat = rolsacQS.obtenirIconaMateria(imCriteria);
        Assert.assertNotNull(iMat);
    }
    
    /**
     * Cas d'us: Recupera iconesMateries.
     */
    @Test
    public void recuperarIconesMateries() {
        IconaMateriaCriteria imCriteria = new IconaMateriaCriteria();
        imCriteria.setTamany("3");
        List<IconaMateriaQueryServiceAdapter> iMats = rolsacQS.llistarIconesMateries(imCriteria);
        Assert.assertTrue(iMats.size() <= 3);
    }

    /**
     * Cas d'us: Recupera materiaAgrupacio.
     */
    @Test
    public void recuperarMateriaAgrupacio() {
        MateriaAgrupacioCriteria maCriteria = new MateriaAgrupacioCriteria();
        maCriteria.setId("635948");
        MateriaAgrupacioQueryServiceAdapter ma = rolsacQS.obtenirMateriaAgrupacio(maCriteria);
        Assert.assertNotNull(ma);
    }
    
    /**
     * Cas d'us: Recupera materiesAgrupacions.
     */
    @Test
    public void recuperarMateriesAgrupacions() {
        MateriaAgrupacioCriteria maCriteria = new MateriaAgrupacioCriteria();
        maCriteria.setOrden("3");
        maCriteria.setTamany("3");
        List<MateriaAgrupacioQueryServiceAdapter> mas = rolsacQS.llistarMateriesAgrupacions(maCriteria);
        Assert.assertTrue(mas.size() > 0);
        for (MateriaAgrupacioQueryServiceAdapter ma: mas) {
            Assert.assertTrue(ma.getOrden() == 3);
        }
    }

    /**
     * Cas d'us: Recupera perfil.
     */
    @Test
    public void recuperarPerfil() {
        PerfilCriteria perfilCriteria = new PerfilCriteria();
        perfilCriteria.setId("634701");
        PerfilQueryServiceAdapter perfil = rolsacQS.obtenirPerfil(perfilCriteria);
        Assert.assertNotNull(perfil);
    }
    
    /**
     * Cas d'us: Recupera perfils.
     */
    @Test
    public void recuperarPerfils() {
        PerfilCriteria perfilCriteria = new PerfilCriteria();
        perfilCriteria.setPathIconografia("%prova%");
        List<PerfilQueryServiceAdapter> perfils = rolsacQS.llistarPerfils(perfilCriteria);
        Assert.assertTrue(perfils.size() > 0);
        for (PerfilQueryServiceAdapter p: perfils) {
            Assert.assertTrue(p.getPathIconografia().toLowerCase().contains("prova"));
        }
    }

    /**
     * Cas d'us: Recupera seccio.
     */
    @Test
    public void recuperarSeccio() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId("631907");
        SeccioQueryServiceAdapter seccio = rolsacQS.obtenirSeccio(seccioCriteria);
        Assert.assertNotNull(seccio);
    }
    
    /**
     * Cas d'us: Recupera Seccions.
     */
    @Test
    public void recuperarSeccions() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setT_nombre("%irlanda%");
        List<SeccioQueryServiceAdapter> seccions = rolsacQS.llistarSeccions(seccioCriteria);
        Assert.assertTrue(seccions.size() > 0);
        for (SeccioQueryServiceAdapter s: seccions) {
            Assert.assertTrue(s.getNombre().toLowerCase().contains("irlanda"));
        }
    }
    
    /**
     * Cas d'us: Recupera Seccions arrel.
     */
    @Test
    public void obtenirSeccionsArrel() {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setArrel(true);
        List<SeccioQueryServiceAdapter> listSeccioQueryServiceAdapter = rolsacQS.llistarSeccions(seccioCriteria);
        Assert.assertTrue(listSeccioQueryServiceAdapter.size() == 11);
    }
    
    /**
     * Cas d'us: Recupera unitat materia.
     */
    @Test
    public void recuperarUnitatMateria() {
        UnitatMateriaCriteria umCriteria = new UnitatMateriaCriteria();
        umCriteria.setId("621236");
        UnitatMateriaQueryServiceAdapter um = rolsacQS.obtenirUnitatMateria(umCriteria);
        Assert.assertNotNull(um);
    }
    
    /**
     * Cas d'us: Recupera unitats materies.
     */
    @Test
    public void recuperarUnitatsMateries() {
        UnitatMateriaCriteria umCriteria = new UnitatMateriaCriteria();
        umCriteria.setUnidadPrincipal("S");
        umCriteria.setTamany("5");
        List<UnitatMateriaQueryServiceAdapter> ums = rolsacQS.llistarUnitatsMateries(umCriteria);
        Assert.assertTrue(ums.size() > 0);
        for (UnitatMateriaQueryServiceAdapter um: ums) {
            Assert.assertTrue(um.getUnidadPrincipal().equalsIgnoreCase("s"));
        }
    }

    /**
     * Cas d'us: Recupera un tipus de normativa.
     */
    @Test
    public void recuperarUnTipusNormativa() {
        TipusCriteria tnCriteria = new TipusCriteria();
        tnCriteria.setId("11");
        TipusQueryServiceAdapter tn = rolsacQS.obtenirTipus(tnCriteria);
        Assert.assertTrue(tn.getNombre().equals("Resolució"));
    }
    
    /**
     * Cas d'us: Recupera tipus normativa.
     */
    @Test
    public void recuperarTipusNormativa() {
        TipusCriteria tnsCriteria = new TipusCriteria();
        tnsCriteria.setTamany("5");
        tnsCriteria.setT_nombre("%Llei%");
        List<TipusQueryServiceAdapter> tns = rolsacQS.llistarTipus(tnsCriteria);
        Assert.assertTrue(tns.size() == 3);
    }
    
}
