package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;

public class UnitatMateriaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {        
        rolsacQS = TestUtils.getRolsacQueryService(STRATEGY.WS);
    }

    @Test
    public void obtenirMateria(){
        UnitatMateriaCriteria unitatMateriaCriteria = new UnitatMateriaCriteria();
        unitatMateriaCriteria.setId("621236");
        try {
            UnitatMateriaQueryServiceAdapter unitatMateria = rolsacQS.obtenirUnitatMateria(unitatMateriaCriteria);
            Assert.assertNotNull(unitatMateria);
            MateriaQueryServiceAdapter materia = unitatMateria.obtenirMateria();
            Assert.assertTrue(materia.getId() == 5530);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirUnitat(){
        UnitatMateriaCriteria unitatMateriaCriteria = new UnitatMateriaCriteria();
        unitatMateriaCriteria.setId("621236");
        try {
            UnitatMateriaQueryServiceAdapter unitatMateria = rolsacQS.obtenirUnitatMateria(unitatMateriaCriteria);
            Assert.assertNotNull(unitatMateria);
            UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = unitatMateria.obtenirUnitatAdministrativa();
            Assert.assertTrue(unitatAdministrativa.getId() == 621230);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
