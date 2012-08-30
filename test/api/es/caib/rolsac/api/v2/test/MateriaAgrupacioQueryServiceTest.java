package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public class MateriaAgrupacioQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = TestUtils.getRolsacQueryService();
    }

    @Test
    public void obtenirMateria() {
        MateriaAgrupacioCriteria materiaAgrupacioCriteria = new MateriaAgrupacioCriteria();
        materiaAgrupacioCriteria.setId("174532");
        try {
            MateriaAgrupacioQueryServiceAdapter materiaAgrupacio = rolsacQS.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
            Assert.assertNotNull(materiaAgrupacio);
            MateriaQueryServiceAdapter materia = materiaAgrupacio.obtenirMateria();
            Assert.assertTrue(materia.getNombre().equals("Dona"));
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirAgrupacio() {
        MateriaAgrupacioCriteria materiaAgrupacioCriteria = new MateriaAgrupacioCriteria();
        materiaAgrupacioCriteria.setId("174532");
        try {
            MateriaAgrupacioQueryServiceAdapter materiaAgrupacio = rolsacQS.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
            Assert.assertNotNull(materiaAgrupacio);
            AgrupacioMateriaQueryServiceAdapter agrupacioMateria = materiaAgrupacio.obtenirAgrupacio();
            Assert.assertTrue(agrupacioMateria.getNombre().equals("Cultura i societat"));
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
