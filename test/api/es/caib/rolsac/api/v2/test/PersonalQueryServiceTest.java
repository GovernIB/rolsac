package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class PersonalQueryServiceTest {

    private  RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService(STRATEGY.WS);
    }
    
    @Test
    public void recuperarFitxa() {
        PersonalCriteria criteria = new PersonalCriteria();
        criteria.setId("276400");
        try {
            PersonalQueryServiceAdapter pa = rolsacQS.obtenirPersonal(criteria);
            Assert.assertNotNull(pa);
            UnitatAdministrativaQueryServiceAdapter uaa = (UnitatAdministrativaQueryServiceAdapter) pa.obtenirUnitatAdministrativa();
            Assert.assertNotNull(uaa);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

}
