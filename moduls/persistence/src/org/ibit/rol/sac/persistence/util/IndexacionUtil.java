package org.ibit.rol.sac.persistence.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.solr.api.model.PathUO;

public class IndexacionUtil {
	
	/** LOG. **/
	protected final static Log log = LogFactory.getLog(IndexacionUtil.class);
	
	/** Extensiones. **/
	private static Map<String, String> extensiones = null;
	
	/**
	 * Devuelve true/false dependiendo de si cumple el mínimo exigido ene l fichero.
	 * @param archivo
	 * @return
	 */
	public static boolean isIndexableSolr(final Archivo archivo) {
		boolean retorno = true;
		
		//Si archivo es nulo, no intentar indexar.
		if (archivo == null || archivo.getDatos() == null) {
			retorno = false;
		} else {
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
				if (IndexacionUtil.extensiones == null) {
					final String ficheroPermitidos = System.getProperty("es.caib.rolsac.solr.ficheros");
					IndexacionUtil.extensiones = new HashMap<String, String>();
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
					final String extension = FilenameUtils.getExtension(archivo.getNombre().trim()).toLowerCase(Locale.ITALIAN);
					
					//Comprobamos si 
					if (extension == null || extension.isEmpty()) {
						retorno = false;
					} else if (!extensiones.containsKey(extension)) {
							retorno = false;
					}			
				
				}
			}
		}
		return retorno;
	}
	
	
	public static PathUO calcularPathUO(UnidadAdministrativa unidadAdministrativa) {
		
		if (unidadAdministrativa == null) {
			return null;
		}
		
		List<PathUO> uos = new ArrayList<PathUO>();
		PathUO uo = new PathUO();
		List<String> path = new ArrayList<String>();
		
		//Hay que extraer la id de los predecesores y luego el de uno mismo
		Set<UnidadAdministrativa> predecesores = unidadAdministrativa.getPredecesores();
		if (predecesores != null) {
			for(UnidadAdministrativa predecesor : predecesores) {
				if (predecesor != null && predecesor.getId() != null) {
					path.add(predecesor.getId().toString());				
				}
			}
		}
		path.add( unidadAdministrativa.getId().toString());
		
		uo.setPath(path);
		
		return uo;
	}
	
	
	public static String calcularPathTextUO(UnidadAdministrativa unidadAdministrativa, String idioma) {
		
		if (unidadAdministrativa == null) {
			return null;
		}
		
		StringBuffer textoOptional = new StringBuffer();
		
		//Hay que extraer la id de los predecesores y luego el de uno mismo
		Set<UnidadAdministrativa> predecesores = unidadAdministrativa.getPredecesores();
		if (predecesores != null) {
			for(UnidadAdministrativa predecesor : predecesores) {
				if (predecesor != null && predecesor.getId() != null) {
					TraduccionUA traduccionUA = ((TraduccionUA) predecesor.getTraduccion(idioma));
					if (traduccionUA != null && traduccionUA.getNombre() != null) {
						textoOptional.append(traduccionUA.getNombre());
						textoOptional.append(" ");
					}							
				}
			}
		}
				
		TraduccionUA traduccionUA = ((TraduccionUA) unidadAdministrativa.getTraduccion(idioma));
		if (traduccionUA != null && traduccionUA.getNombre() != null) {
			textoOptional.append(traduccionUA.getNombre());
			textoOptional.append(" ");
		}
		
		return textoOptional.toString();
	}
	
	public static List<PathUO> calcularPathUOsFicha(Ficha ficha) {
		//Unidades administrativas de las fichas.
		List<PathUO> uos = new ArrayList<PathUO>();
		for (FichaUA fichaUA : ficha.getFichasua()) {
			if (fichaUA.getUnidadAdministrativa() == null) {
				continue;
			}	
			uos.add(IndexacionUtil.calcularPathUO(fichaUA.getUnidadAdministrativa()));												
		}
		return uos;
	}
	
	public static String calcularPathTextUOsFicha(Ficha ficha, String idioma) {
		StringBuffer textoOptional = new StringBuffer();
		for (FichaUA fichaUA : ficha.getFichasua()) {
			if (fichaUA.getUnidadAdministrativa() == null) {
				continue;
			}	
			textoOptional.append(IndexacionUtil.calcularPathTextUO(fichaUA.getUnidadAdministrativa(), idioma));												
		}
		return textoOptional.toString();
	}
	
	
  public static  UnidadAdministrativa calcularPrimeraUAFicha(Ficha ficha) {
		UnidadAdministrativa primeraUA = null;
		for (FichaUA fichaUA : ficha.getFichasua()) {
			if (fichaUA.getUnidadAdministrativa() == null) {
				continue;
			}
			primeraUA = fichaUA.getUnidadAdministrativa();
			break;
																					
		}
		return primeraUA;
	}
	
	/**
	 * Comprueba si es indexable una normativa.
	 * 
	 * @return
	 */
	public static boolean isIndexable(final Normativa normativa) {
		if (normativa.getValidacion() != 1 ) {
			return false;
		}
		// TODO Pendiente tratar Normativas externas (sin UA)
		if (normativa.getUnidadAdministrativa() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si es indexable una ficha.
	 * @return
	 */
	public static boolean isIndexable(final Ficha ficha) {
		if (ficha.getValidacion() != 1 ) {
			return false;
		}
		
		// No es indexable si no tiene al menos una UA
		boolean existeUA = false;
		if (ficha.getFichasua() != null && ficha.getFichasua().size() > 0) {
			for (FichaUA fichaUA : ficha.getFichasua()) {
				if (fichaUA.getUnidadAdministrativa() != null) {
					existeUA = true;
					break;
				}
			}
		}
		if (!existeUA) {
			return false;
		}
		
		return true;
	}
	
	

	 /**
	 * Comprueba si es indexable un procedimiento local.
	 * @param procedimiento
	 * @return
	 */
	public static boolean isIndexable(final ProcedimientoLocal procedimiento) {
		if (procedimiento.getValidacion() != 1 ) {
			return false;
		}				
		if (procedimiento.getUnidadAdministrativa() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Comprueba si es indexable un tramite.
	 * 
	 * @return
	 */
	public static boolean isIndexable(final Tramite tramite) {
		if (tramite.getProcedimiento() != null) {
			if (tramite.getProcedimiento().getValidacion() != 1 ) {
				return false;
			}	
			
			if (tramite.getProcedimiento().getUnidadAdministrativa() == null) {
				return false;
			}
		}
		
		return true;
	}
	
	
}
