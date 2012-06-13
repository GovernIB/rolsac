package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.estadistica.EstadisticaInsertService;
import es.caib.rolsac.api.v2.general.BeanUtils;

public class EstadisticaInsertServiceTest {

    EstadisticaInsertService service;

    @Before
    public void setup() {
        service = (EstadisticaInsertService) BeanUtils.getBean("estadisticaInsertServiceEJBAdapter");
    }

    /**
     * Cas d'us: Grabar una estadistica de fitxa que existeix.
     */
    @Test
    public void grabarEstadisticaFitxa() {
        Assert.assertTrue(service.gravarEstadisticaFitxa(206971));
    }
    
    /**
     * Cas d'us: Grabar una estadistica de fitxa que no existeix.
     */
    @Test
    public void grabarEstadisticaFitxaNoExisteix() {
        Assert.assertFalse(service.gravarEstadisticaFitxa(999999));
    }
    
    /**
     * Cas d'us: Grabar una estadistica de fitxa per materia.
     */
    @Test
    public void gravarEstadisticaFitxaPerMateria() {
        Assert.assertTrue(service.gravarEstadisticaFitxaPerMateria(206971, 635622));
    }

    /**
     * Cas d'us: Grabar una estadistica de fitxa per unitat administrativa.
     */
    @Test
    public void gravarEstadisticaFitxaPerUA() {
        Assert.assertTrue(service.gravarEstadisticaFitxaPerUA(206971, 1));
    }
    
    /**
     * Cas d'us: Grabar una estadistica de materia.
     */
    @Test
    public void gravarEstadisticaMateria() {
        Assert.assertTrue(service.gravarEstadisticaMateria(635622));
    }
    
    /**
     * Cas d'us: Grabar una estadistica de normativa.
     */
    @Test
    public void gravarEstadisticaNormativa() {
        Assert.assertTrue(service.gravarEstadisticaNormativa(74965));
    }
    
    /**
     * Cas d'us: Grabar una estadistica de fitxa per procediment.
     */
    @Test
    public void gravarEstadisticaProcediment() {
        Assert.assertTrue(service.gravarEstadisticaProcediment(460464));
    }
    
    /**
     * Cas d'us: Grabar una estadistica de fitxa per unitat administrativa.
     */
    @Test
    public void gravarEstadisticaUnitatAdministrativa() {
        Assert.assertTrue(service.gravarEstadisticaUnitatAdministrativa(1));
    }
    
}
