package org.ibit.rol.sac.persistence.ejb;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import test.unitario.persistence.mock.MockIndexerFacadeEJB;
import es.caib.rolsac.persistence.lucene.model.Catalogo;
import es.caib.rolsac.persistence.lucene.model.IndexResultados;

public class IndexerFacadeEJBTest {

    private static final String idioma = "ca";

    MockIndexerFacadeEJB indexerFacadeMock;

    Directory directory;

    @Before
    public void setup() {
        indexerFacadeMock = new MockIndexerFacadeEJB();
        // Necesario para indicarle que tipo de indice se trata, al no pasar por los delegates
        indexerFacadeMock.setIndexType(IndexerFacadeEJB.FILESYSTEM_INDEX_TYPE);
        try {
            directory = FSDirectory.open(new File("/app/caib/rolsac/" + idioma));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void busquedaLuceneUA() {

        String buscarTodas = "turisme";
        String buscarAlguna = null;
        String buscarFrase = null;
        String tipo = null;
        String uo = null;
        String materia = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        boolean sugerir = true;
        boolean restringir = true;

        try {
            IndexResultados resultados = indexerFacadeMock.buscarAvanzado(
                buscarTodas, buscarAlguna, buscarFrase, null,   tipo,    uo,         materia,
                fechaInicio, fechaFin,     null,        idioma, sugerir, restringir, directory);

            Assert.assertNotNull(resultados);
            Assert.assertTrue(resultados.getNumEncontrados() > 0);

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }


    @Test
    public void busquedaLuceneNormativa() {

        String buscarTodas = "";
        String buscarAlguna = null;
        String buscarFrase = null;
        String tipo = Catalogo.SRVC_NORMATIVA_LOCAL;
        String uo = "8";
        String materia = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        boolean sugerir = true;
        boolean restringir = true;

        try {
            IndexResultados resultados = indexerFacadeMock.buscarAvanzado(
                buscarTodas, buscarAlguna, buscarFrase, null,   tipo,    uo,         materia,
                fechaInicio, fechaFin,     null,        idioma, sugerir, restringir, directory);

            Assert.assertNotNull(resultados);
            Assert.assertTrue(resultados.getNumEncontrados() > 0);

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }


    @Test
    public void busquedaLuceneVacioSinSugerir() {

        String buscarTodas = "zanzibar";
        String buscarAlguna = null;
        String buscarFrase = null;
        String tipo = null;
        String uo = null;
        String materia = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        boolean sugerir = false;
        boolean restringir = true;

        try {
            IndexResultados resultados = indexerFacadeMock.buscarAvanzado(
                buscarTodas, buscarAlguna, buscarFrase, null,   tipo,    uo,         materia,
                fechaInicio, fechaFin,     null,        idioma, sugerir, restringir, directory);

            Assert.assertNotNull(resultados);
            Assert.assertTrue(resultados.getNumEncontrados() == 0);

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }


    @Test
    public void busquedaLuceneVacioConSugerir() {

        String buscarTodas = "zanzibar";
        String buscarAlguna = null;
        String buscarFrase = null;
        String tipo = null;
        String uo = null;
        String materia = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        boolean sugerir = false;
        boolean restringir = true;

        try {
            IndexResultados resultados = indexerFacadeMock.buscarAvanzado(
                buscarTodas, buscarAlguna, buscarFrase, null,   tipo,    uo,         materia,
                fechaInicio, fechaFin,     null,        idioma, sugerir, restringir, directory);

            Assert.assertNotNull(resultados);
            Assert.assertTrue(resultados.getNumEncontrados() == 0);

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }


    @Test
    public void busquedaLuceneProcedimiento() {

        String buscarTodas = "turisme";
        String buscarAlguna = null;
        String buscarFrase = null;
        String tipo = Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO;
        String uo = null;
        String materia = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        boolean sugerir = false;
        boolean restringir = true;

        try {
            IndexResultados resultados = indexerFacadeMock.buscarAvanzado(
                buscarTodas, buscarAlguna, buscarFrase, null,   tipo,    uo,         materia,
                fechaInicio, fechaFin,     null,        idioma, sugerir, restringir, directory);

            Assert.assertNotNull(resultados);
            Assert.assertTrue(resultados.getNumEncontrados() > 0);

        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

}
