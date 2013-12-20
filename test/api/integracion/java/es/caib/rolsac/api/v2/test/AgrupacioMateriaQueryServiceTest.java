package es.caib.rolsac.api.v2.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;

public class AgrupacioMateriaQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }
    
    @Test
    public void adapterTest() {
        AgrupacioMateriaDTO dto = new AgrupacioMateriaDTO();
        dto.setId((long) 174535);
        AgrupacioMateriaQueryServiceAdapter adapter = (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", STRATEGY.WS, dto);
        try {
            int numMateries = adapter.getNumMateries();
            Assert.assertTrue(numMateries > 0);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
   
    @Test
    public void recuperarNumMateries() {
        AgrupacioMateriaCriteria agrupacioMateriaCriteria = new AgrupacioMateriaCriteria();
        agrupacioMateriaCriteria.setId("174535");
        try {
            AgrupacioMateriaQueryServiceAdapter agrupacioMateria = rolsacQS.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
            Assert.assertNotNull(agrupacioMateria);
            int numMateries = agrupacioMateria.getNumMateries();        
            Assert.assertTrue(numMateries == 9);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarMateries() {
        AgrupacioMateriaCriteria agrupacioMateriaCriteria = new AgrupacioMateriaCriteria();
        agrupacioMateriaCriteria.setId("174535");        
        try {
            AgrupacioMateriaQueryServiceAdapter agrupacioMateria = rolsacQS.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
            Assert.assertNotNull(agrupacioMateria);
            List<MateriaQueryServiceAdapter> listMateriaQueryServiceAdapter = agrupacioMateria.llistarMateries(new MateriaCriteria());        
            Assert.assertTrue(listMateriaQueryServiceAdapter.size() == 9);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void recuperarSeccio(){
        AgrupacioMateriaCriteria agrupacioMateriaCriteria = new AgrupacioMateriaCriteria();
        agrupacioMateriaCriteria.setId("174535");
        try {
            AgrupacioMateriaQueryServiceAdapter agrupacioMateria = rolsacQS.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
            Assert.assertNotNull(agrupacioMateria);
            SeccioQueryServiceAdapter seccio = agrupacioMateria.obtenirSeccio();
            Assert.assertTrue(seccio.getNombre().equals("Entorn i territori"));
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
}
