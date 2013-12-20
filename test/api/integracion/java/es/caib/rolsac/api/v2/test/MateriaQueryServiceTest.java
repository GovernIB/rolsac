package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
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
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    @Test
    public void getNumProcedimentsLocals() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5522");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            int numProcedimentsLocals = materia.getNumProcedimentsLocals();
            Assert.assertTrue(numProcedimentsLocals > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getNumUnitatsMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5530");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            int numUnitatsMateria = materia.getNumUnitatsMateries();
            Assert.assertTrue(numUnitatsMateria > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getNumFitxes() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5552");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            int numFitxes = materia.getNumFitxes();
            Assert.assertTrue(numFitxes > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getNumAgrupacioMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5560");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            int numAgrupacioMateries = materia.getNumAgrupacioMateries();
            Assert.assertTrue(numAgrupacioMateries > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getNumIcones() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5555");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            int numIcones = materia.getNumIcones();
            Assert.assertTrue(numIcones == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarProcedimentsLocals() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("202504");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            List<ProcedimentQueryServiceAdapter> listProcedimentQueryServiceAdapter = materia.llistarProcedimentsLocals(new ProcedimentCriteria());
            Assert.assertTrue(listProcedimentQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarFitxes() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5552");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            FitxaCriteria fitxaCriteria = new FitxaCriteria();
            fitxaCriteria.setIdioma("ca");
            List<FitxaQueryServiceAdapter> listFitxaQueryServiceAdapter = materia.llistarFitxes(fitxaCriteria);
            Assert.assertTrue(listFitxaQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarAgrupacioMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5524");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            List<AgrupacioMateriaQueryServiceAdapter> listAgrupacioMateriaQueryServiceAdapter = materia.llistarAgrupacioMateries(new AgrupacioMateriaCriteria());
            Assert.assertTrue(listAgrupacioMateriaQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarUnitatsMateria() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("178372");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            List<UnitatMateriaQueryServiceAdapter> listUnitatMateriaQueryServiceAdapter = materia.llistarUnitatsMateria(new UnitatMateriaCriteria());
            Assert.assertTrue(listUnitatMateriaQueryServiceAdapter.size() == 1);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarUnitatsAdministratives() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5521");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            List<UnitatAdministrativaQueryServiceAdapter> listUnitatAdministrativaQueryServiceAdapter = materia.llistarUnitatsAdministratives(new UnitatAdministrativaCriteria());
            Assert.assertTrue(listUnitatAdministrativaQueryServiceAdapter.size() > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void llistarIconesMateries() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5521");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            List<IconaMateriaQueryServiceAdapter> listIconaMateriaQueryServiceAdapter = materia.llistarIconesMateries(new IconaMateriaCriteria());
            Assert.assertTrue(listIconaMateriaQueryServiceAdapter.size() == 3);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getFotografia() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5522");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            ArxiuQueryServiceAdapter fotografia = materia.getFotografia();
            Assert.assertNotNull(fotografia);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getIcona() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5522");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            ArxiuQueryServiceAdapter icona = materia.getIcona();
            Assert.assertNotNull(icona);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getIconaGran() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5522");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            ArxiuQueryServiceAdapter iconaGran = materia.getIconaGran();
            Assert.assertNotNull(iconaGran);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getFotografiaNull() {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId("5530");
        try {
            MateriaQueryServiceAdapter materia = rolsacQS.obtenirMateria(materiaCriteria);
            Assert.assertNotNull(materia);
            ArxiuQueryServiceAdapter iconaGran = materia.getIconaGran();
            Assert.assertNull(iconaGran);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
