package org.ibit.rol.sac.model;

import org.ibit.lucene.indra.model.ModelIndexObject;
import org.ibit.rol.sac.extractor.Extractor;
import org.ibit.rol.sac.extractor.ExtractorFactory;

import java.io.Serializable;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Objeto que encapsula los datos a indexar.
 */
public class IndexObject extends ModelIndexObject {



    public void addArchivo(Archivo archivo) {
        if (archivo != null && archivo.getPeso() > 0) {
            Extractor extractor = ExtractorFactory.getExtractor(archivo.getMime());
            if (extractor != null) {
                try {
                    String aText = extractor.extractText(new ByteArrayInputStream(archivo.getDatos()));
                    addTextLine(aText);
                } catch (IOException e) {
                    ;
                }
            }
        }
    }
    
    public void addArchivo(ArchivoResumen archivo) {
        if (archivo != null && archivo.getPeso() > 0) {
            Extractor extractor = ExtractorFactory.getExtractor(archivo.getMime());
            if (extractor != null) {
//                try {
//                    String aText = extractor.extractText(new ByteArrayInputStream(archivo.getDatos()));
//                    addTextLine(aText);
//                } catch (IOException e) {
//                    ;
//                }
            }
        }
    }
    
     public String toString() {
        return getClass().getName() + "@" + super.getId() + "," + super.getClasificacion();
    }
}
