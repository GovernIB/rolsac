package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.general.BeanUtils;
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
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.EJB);
    }

    @Test
    public void obtenirMateria(){
        UnitatMateriaCriteria unitatMateriaCriteria = new UnitatMateriaCriteria();
        unitatMateriaCriteria.setId("621236");
        UnitatMateriaQueryServiceAdapter unitatMateria = rolsacQS.obtenirUnitatMateria(unitatMateriaCriteria);
        Assert.assertNotNull(unitatMateria);
        MateriaQueryServiceAdapter materia = unitatMateria.obtenirMateria();
        Assert.assertTrue(materia.getId() == 5530);
    }
    
    @Test
    public void obtenirUnitat(){
        UnitatMateriaCriteria unitatMateriaCriteria = new UnitatMateriaCriteria();
        unitatMateriaCriteria.setId("621236");
        UnitatMateriaQueryServiceAdapter unitatMateria = rolsacQS.obtenirUnitatMateria(unitatMateriaCriteria);
        Assert.assertNotNull(unitatMateria);
        UnitatAdministrativaQueryServiceAdapter unitatAdministrativa = unitatMateria.obtenirUnitatAdministrativa();
        Assert.assertTrue(unitatAdministrativa.getId() == 621230);
    }
}
