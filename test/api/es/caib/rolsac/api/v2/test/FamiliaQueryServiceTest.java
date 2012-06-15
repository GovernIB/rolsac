package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class FamiliaQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }
    
    @Test
    public void getNumProcedimentsLocals() {
        FamiliaCriteria fCriteria = new FamiliaCriteria();
        fCriteria.setId("1");
        FamiliaQueryServiceAdapter f = rolsacQS.obtenirFamilia(fCriteria);
        Assert.assertNotNull(f);
        int numProcs = f.getNumProcedimentsLocals();
        Assert.assertTrue(numProcs > 0);
    }
    
    @Test
    public void llistarProcedimentsLocals() {
        FamiliaCriteria fCriteria = new FamiliaCriteria();
        fCriteria.setId("1");
        FamiliaQueryServiceAdapter f = rolsacQS.obtenirFamilia(fCriteria);
        Assert.assertNotNull(f);
        List<ProcedimentQueryServiceAdapter> procs = f.llistarProcedimentsLocals(new ProcedimentCriteria());
        Assert.assertTrue(procs.size() > 0);
    }

    @Test
    public void getNumIcones() {
        FamiliaCriteria fCriteria = new FamiliaCriteria();
        fCriteria.setId("1");
        FamiliaQueryServiceAdapter f = rolsacQS.obtenirFamilia(fCriteria);
        Assert.assertNotNull(f);
        int numIcones = f.getNumIcones();
        Assert.assertTrue(numIcones > 0);
    }
    
    @Test
    public void llistarIcones() {
        FamiliaCriteria fCriteria = new FamiliaCriteria();
        fCriteria.setId("1");
        FamiliaQueryServiceAdapter f = rolsacQS.obtenirFamilia(fCriteria);
        Assert.assertNotNull(f);
        List<IconaFamiliaQueryServiceAdapter> icones = f.llistarIcones(new IconaFamiliaCriteria());
        Assert.assertTrue(icones.size() > 0);
    }
    
}
