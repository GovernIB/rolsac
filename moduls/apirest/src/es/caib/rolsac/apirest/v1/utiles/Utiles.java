package es.caib.rolsac.apirest.v1.utiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clase de constantes. 
 * 
 * @author slromero
 *
 */
public class Utiles {

    /**
     * Método encargado de realizar el casting de listas no tipadas a listas
     * tipadas
     * 
     * @param <T>
     * @param clazz Clase del tipo de objeto contenido en la lista
     * @param c Colección a ser tipada
     * @return Lista tipada
     */
    public static <T>List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>();
        if (c != null) {     
            r = new ArrayList<T>(c.size());            
            for (Object o : c) {
            	r.add(clazz.cast(o));
            }
        }
        return r;
    } 
}






