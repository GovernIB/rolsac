package org.ibit.rol.sac.persistence.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;

/**
 * Clase de utiles para tratar los archivos.
 * @author slromero
 *
 */
public class ArchivoUtils {

	/** LOG. **/
	protected final static Log log = LogFactory.getLog(ArchivoUtils.class);
	
	/** Extensiones. **/
	private static Map<String, String> extensiones = null;
	
	/**
	 * Devuelve true/false dependiendo de si cumple el mínimo exigido ene l fichero.
	 * @param archivo
	 * @return
	 */
	public static boolean isIndexableSolr(final Archivo archivo) {
		boolean retorno = true;
		final String sTamanyoMaximo = System.getProperty("es.caib.rolsac.solr.tamanyomaximo");
		Long tamanyoMaximo = 10l;
		try {
			tamanyoMaximo = Long.valueOf(sTamanyoMaximo.trim());
		} catch (Exception e) {
			log.error("Error tratanto de convertir a long el tamanyoMaximo"+sTamanyoMaximo, e);
		}
		
		if (archivo.getPeso() > tamanyoMaximo*1024l*1024l) {
			retorno = false;
		} else {
			//Preparamos la variable extensiones si está a null.
			if (ArchivoUtils.extensiones == null) {
				final String ficheroPermitidos = System.getProperty("es.caib.rolsac.solr.ficheros");
				ArchivoUtils.extensiones = new HashMap<String, String>();
				String[] extensionesSplit = ficheroPermitidos.split(",");
				for(String extensionSplit : extensionesSplit) {
					//Se limpian las extensiones.
					extensiones.put(extensionSplit.trim().toLowerCase(Locale.ITALIAN), extensionSplit);
				}
			}
			
			//Si el nombre esta vacío, entonces se da por incorrecto.
			if (archivo.getNombre() == null || archivo.getNombre().isEmpty()) {
				retorno = false;
			} else {
				//Extraemos la extensión.
				final String extension = FilenameUtils.getExtension(archivo.getNombre()).trim().toLowerCase(Locale.ITALIAN);
				
				//Comprobamos si 
				if (extension == null || extension.isEmpty()) {
					retorno = false;
				} else if (!extensiones.containsKey(extension)) {
						retorno = false;
				}			
			
			}
		}
		return retorno;
	}
}
