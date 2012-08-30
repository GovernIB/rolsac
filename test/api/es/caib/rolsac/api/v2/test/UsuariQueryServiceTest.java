package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public class UsuariQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService();
    }
    
    @Test
    public void getNumUnitatsAdministratives() {
        UsuariCriteria usuariCriteria = new UsuariCriteria();
        usuariCriteria.setId("206345");
        try {
            UsuariQueryServiceAdapter u = rolsacQS.obtenirUsuari(usuariCriteria);
            Assert.assertNotNull(u);
            int numUAs = u.getNumUnitatsAdministratives();
            Assert.assertTrue(numUAs > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void llistarUnitatsAdministratives() {
        UsuariCriteria usuariCriteria = new UsuariCriteria();
        usuariCriteria.setId("206345");
        try {
            UsuariQueryServiceAdapter u = rolsacQS.obtenirUsuari(usuariCriteria);
            Assert.assertNotNull(u);
            List<UnitatAdministrativaQueryServiceAdapter> uas = u.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
            Assert.assertTrue(uas.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

}
