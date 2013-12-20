package es.caib.rolsac.api.v2.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class FormulariQueryServiceTest {

    RolsacQueryService rolsacQS;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    @Test
    public void obtenirArchivo() {
        FormulariCriteria formulariCriteria = new FormulariCriteria();
        formulariCriteria.setId("205940");
        try {
            FormulariQueryServiceAdapter formulari = rolsacQS.obtenirFormulari(formulariCriteria);
            Assert.assertNotNull(formulari);
            ArxiuQueryServiceAdapter archivo = formulari.obtenirArchivo();
            Assert.assertNotNull(archivo);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirManual() {
        FormulariCriteria formulariCriteria = new FormulariCriteria();
        formulariCriteria.setId("205940");
        try {
            FormulariQueryServiceAdapter formulari = rolsacQS.obtenirFormulari(formulariCriteria);
            Assert.assertNotNull(formulari);
            ArxiuQueryServiceAdapter manual = formulari.obtenirManual();
            Assert.assertNull(manual);
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
    
    @Test
    public void obtenirTramit() {
        FormulariCriteria formulariCriteria = new FormulariCriteria();
        formulariCriteria.setId("205940");
        try {
            FormulariQueryServiceAdapter formulari = rolsacQS.obtenirFormulari(formulariCriteria);
            Assert.assertNotNull(formulari);
            TramitQueryServiceAdapter tramitQueryServiceAdapter = formulari.obtenirTramit();
            Assert.assertTrue(tramitQueryServiceAdapter.getNombre().equals("Comunicació de nou curs"));
        } catch (QueryServiceException e) {
            Assert.fail(e.toString());
        }
    }
}
