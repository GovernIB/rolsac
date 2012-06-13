package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class ProcedimentQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void getNumTramits() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("124506");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numTramits = procediment.getNumTramits();        
        Assert.assertTrue(numTramits == 5);
    }
    
    @Test
    public void getNumNormatives() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("627689");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numNormatives = procediment.getNumNormatives();
        Assert.assertTrue(numNormatives == 3);
    }
    
    @Test
    public void getNumNormativesLocals() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("627689");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numNormatives = procediment.getNumNormativesLocals();
        Assert.assertTrue(numNormatives == 2);
    }
    
    @Test
    public void getNumNormativesExternes() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("627689");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numNormatives = procediment.getNumNormativesExternes();
        Assert.assertTrue(numNormatives == 1);
    }
    
    @Test
    public void getNumMateries() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("299349");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numTramits = procediment.getNumMateries();        
        Assert.assertTrue(numTramits == 9);
    }
    
   
    @Test
    public void getNumFetsVitals() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("635901");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numTramits = procediment.getNumFetsVitals();        
        Assert.assertTrue(numTramits == 2);
    }
    
    @Test
    public void getNumDocuments() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("357915");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        int numDocuments = procediment.getNumDocuments();        
        Assert.assertTrue(numDocuments == 11);
    }
    
    @Test
    public void llistarTramits() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("108591");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        List<TramitQueryServiceAdapter> listTramitQueryServiceAdapter = procediment.llistarTramits(new TramitCriteria());        
        Assert.assertTrue(listTramitQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void llistarNormatives() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("471341");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        NormativaCriteria nc = new NormativaCriteria();
        nc.setIncluirExternas(true);
        List<NormativaQueryServiceAdapter> normativesService = procediment.llistarNormatives(nc);
        Assert.assertTrue(normativesService.size() > 0);
    }
    
    @Test
    public void llistarMateries() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("471341");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        List<MateriaQueryServiceAdapter> listMateriaQueryServiceAdapter = procediment.llistarMateries(new MateriaCriteria());        
        Assert.assertTrue(listMateriaQueryServiceAdapter.size() == 1);
    }
    
    @Test
    public void llistarFetsVitals() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("636006");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        List<FetVitalQueryServiceAdapter> listFetVitalQueryServiceAdapter = procediment.llistarFetsVitals(new FetVitalCriteria());        
        Assert.assertTrue(listFetVitalQueryServiceAdapter.size() == 4);
    }
    
    @Test
    public void llistarDocuments() {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId("344958");
        ProcedimentQueryServiceAdapter procediment = rolsacQS.obtenirProcediment(procedimentCriteria);
        Assert.assertNotNull(procediment);
        List<DocumentQueryServiceAdapter> listDocumentQueryServiceAdapter = procediment.llistarDocuments(new DocumentCriteria());        
        Assert.assertTrue(listDocumentQueryServiceAdapter.size() == 5);
    }

}
