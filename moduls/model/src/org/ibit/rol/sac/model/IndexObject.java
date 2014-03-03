package org.ibit.rol.sac.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.extractor.Extractor;
import org.ibit.rol.sac.extractor.ExtractorFactory;

import es.caib.rolsac.persistence.lucene.model.ModelIndexObject;

/**
 * Objeto que encapsula los datos a indexar.
 */
public class IndexObject extends ModelIndexObject {

    private static Log log = LogFactory.getLog(IndexObject.class);

	public void addArchivo(Archivo archivo) {
        if (archivo != null && archivo.getPeso() > 0) {
            Extractor extractor = ExtractorFactory.getExtractor(archivo.getMime());
            if (extractor != null) {
                try {
                    String aText = extractor.extractText(new ByteArrayInputStream(archivo.getDatos()));
                    addTextLine(aText);
                } catch (IOException e) {
                    log.error("Error de entrada/salida en la extracción del texto del archivo.");
                } catch (Exception ex) {
                    log.error("No se ha podido extraer el texto del archivo: " + archivo.getId());
                }
            }
        }
    }

    public void addArchivo(ArchivoResumen archivo) {
        if (archivo != null && archivo.getPeso() > 0) {
            Extractor extractor = ExtractorFactory.getExtractor(archivo.getMime());
            if (extractor != null) {}
        }
    }

    public String toString() {
        return getClass().getName() + "@" + super.getId() + "," + super.getClasificacion();
    }

}
