package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;

public class MateriaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }

    @Test
    public void getNumProcedimentsLocals() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("630825");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        int numProcedimentsLocals = materia.getNumProcedimentsLocals();        
        Assert.assertTrue(numProcedimentsLocals == 43);
    }
    
    @Test
    public void getNumUnitatsMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5530");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        int numUnitatsMateria = materia.getNumUnitatsMateries();        
        Assert.assertTrue(numUnitatsMateria == 65);
    }
    
    @Test
    public void getNumFitxes() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5552");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        int numFitxes = materia.getNumFitxes();        
        Assert.assertTrue(numFitxes == 213);
    }
    
    @Test
    public void getNumAgrupacioMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5560");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        int numAgrupacioMateries = materia.getNumAgrupacioMateries();        
        Assert.assertTrue(numAgrupacioMateries == 2);
    }

    @Test
    public void getNumIcones() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5555");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        int numIcones = materia.getNumIcones();        
        Assert.assertTrue(numIcones == 1);
    }
    
    @Test
    public void llistarProcedimentsLocals() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("202504");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        List<ProcedimentQueryServiceAdapter> listProcedimentQueryServiceAdapter = materia.llistarProcedimentsLocals(new ProcedimentCriteria());        
        Assert.assertTrue(listProcedimentQueryServiceAdapter.size() == 7);
    }
    
    @Test
    public void llistarFitxes() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5525");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        List<FitxaQueryServiceAdapter> listFitxaQueryServiceAdapter = materia.llistarFitxes(new FitxaCriteria());        
        Assert.assertTrue(listFitxaQueryServiceAdapter.size() == 14);
    }

    @Test
    public void llistarAgrupacioMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5524");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        List<AgrupacioMateriaQueryServiceAdapter> listAgrupacioMateriaQueryServiceAdapter = materia.llistarAgrupacioMateries(new AgrupacioMateriaCriteria());
        Assert.assertTrue(listAgrupacioMateriaQueryServiceAdapter.size() == 2);
    }
    
    @Test
    public void llistarUnitatsMateria() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("178372");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        List<UnitatMateriaQueryServiceAdapter> listUnitatMateriaQueryServiceAdapter = materia.llistarUnitatsMateria(new UnitatMateriaCriteria());
        Assert.assertTrue(listUnitatMateriaQueryServiceAdapter.size() == 1);
    }

    @Test
    public void llistarUnitatsAdministratives() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("635627");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        List<UnitatAdministrativaQueryServiceAdapter> listUnitatAdministrativaQueryServiceAdapter = materia.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
        Assert.assertTrue(listUnitatAdministrativaQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void llistarIconesMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5521");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        List<IconaMateriaQueryServiceAdapter> listIconaMateriaQueryServiceAdapter = materia.llistarIconesMateries(new IconaMateriaCriteria());
        Assert.assertTrue(listIconaMateriaQueryServiceAdapter.size() == 3);
    }
    
    @Test
    public void getFotografia(){
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5522");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        ArxiuQueryServiceAdapter fotografia = materia.getFotografia();
        Assert.assertNotNull(fotografia);
    }

    @Test
    public void getIcona(){
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("635627");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        ArxiuQueryServiceAdapter icona = materia.getIcona();
        Assert.assertNotNull(icona);
    }
    
    @Test
    public void getIconaGran(){
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5521");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        ArxiuQueryServiceAdapter iconaGran = materia.getIconaGran();
        Assert.assertNotNull(iconaGran);
    }
    
    @Test
    public void getFotografiaNull(){
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5530");
        MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
        Assert.assertNotNull(materia);
        ArxiuQueryServiceAdapter iconaGran = materia.getIconaGran();
        Assert.assertNull(iconaGran);
    }
}
