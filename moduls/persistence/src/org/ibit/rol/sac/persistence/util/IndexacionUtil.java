package org.ibit.rol.sac.persistence.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegate;

import es.caib.solr.api.model.PathUO;
import es.caib.solr.api.model.types.EnumCategoria;

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
		if (archivo == null || archivo.getDatos() == null || archivo.getNombre() == null || archivo.getNombre().isEmpty()) {
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
		
		if (unidadAdministrativa.getValidacion() != 1) {
			return null;
		}
		
		List<PathUO> uos = new ArrayList<PathUO>();
		PathUO uo = new PathUO();
		List<String> path = new ArrayList<String>();
		
		//Hay que extraer la id de los predecesores y luego el de uno mismo
		List<UnidadAdministrativa> predecesores = unidadAdministrativa.getPredecesores();
		if (predecesores != null) {
			for(UnidadAdministrativa predecesor : predecesores) {
				if (predecesor != null && predecesor.getId() != null) {
					if (predecesor.getValidacion() != 1) {
						return null;
					}
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
		
		if (unidadAdministrativa.getValidacion() != 1) {
			return null;
		}
		
		StringBuffer textoOptional = new StringBuffer();
		
		//Hay que extraer la id de los predecesores y luego el de uno mismo
		List<UnidadAdministrativa> predecesores = unidadAdministrativa.getPredecesores();
		if (predecesores != null) {
			for(UnidadAdministrativa predecesor : predecesores) {
				if (predecesor != null && predecesor.getId() != null) {
					if (predecesor.getValidacion() != 1) {
						return null;
					}
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
		List<Long> idUAs = new ArrayList<Long>();
		for (FichaUA fichaUA : ficha.getFichasua()) {
			if (fichaUA.getUnidadAdministrativa() == null || idUAs.contains(fichaUA.getUnidadAdministrativa().getId())) {
				continue;
			}	
			idUAs.add(fichaUA.getUnidadAdministrativa().getId());
			PathUO pathUo = IndexacionUtil.calcularPathUO(fichaUA.getUnidadAdministrativa());
			if (pathUo != null) {
				uos.add(pathUo);
			}
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
		boolean indexable;
		if (normativa.getValidacion() != 1 ) {
			indexable = false;
		} else {
			indexable = false;
			for(UnidadNormativa unidadNormativa : normativa.getUnidadesnormativas()) {
				if (unidadNormativa.getUnidadAdministrativa() == null || unidadNormativa.getUnidadAdministrativa().getValidacion() == 1) {
					indexable = true;
					break;
				}
			}
		}
		
		return indexable;
		
	}
	
	/**
	 * Comprueba si es indexable una ficha.
	 * @return
	 */
	public static boolean isIndexable(final Ficha ficha) {
		if (ficha.getValidacion() != 1 ) {
			return false;
		}
		
		// No es indexable si no tiene al menos una UA visible
		boolean existeUA = false;
		if (ficha.getFichasua() != null && ficha.getFichasua().size() > 0) {
			for (FichaUA fichaUA : ficha.getFichasua()) {
				if (fichaUA.getUnidadAdministrativa() != null && fichaUA.getUnidadAdministrativa().getValidacion() == 1) {
					existeUA = true;
					break;
				}
			}
		}
		
		if (!existeUA) {
			return false;
		}
		
		//Si tiene el url relleno, que salga
		for (String keyIdioma : ficha.getTraduccionMap().keySet()) {
			final TraduccionFicha traduccion = (TraduccionFicha) ficha.getTraduccion(keyIdioma);
			if (traduccion != null) {
				if (traduccion.getUrl() != null && !traduccion.getUrl().trim().isEmpty()) {
					return false;
				}
			}
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
		if (procedimiento.getUnidadAdministrativa().getValidacion() != 1 ) {
			return false;
		}
		return true;
	}
	
	
	 /**
		 * Comprueba si es indexable un servicio.
		 * @param servicio
		 * @return
		 */
		public static boolean isIndexable(final Servicio servicio) {
			if (servicio.getValidacion() != 1 ) {
				return false;
			}				
			if (servicio.getOrganoInstructor() == null) {
				return false;
			}
			if (servicio.getOrganoInstructor().getValidacion() != 1 ) {
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
		if (tramite.getProcedimiento() == null) {
			return false;
		}
		
		if (!IndexacionUtil.isIndexable(tramite.getProcedimiento())) {
			return false;
		}	
		
		return true;
	}
	
	
	public static String calcularExtensionArchivo(String filename) {
		if (filename == null) return null;
		String extension = FilenameUtils.getExtension(StringUtils.trim(filename));
		return StringUtils.lowerCase(extension);
	}
	
	
	
	public static void marcarIndexacionPendiente(EnumCategoria categoria, Long idElemento, boolean soloBorrar) throws DelegateException {
			SolrPendienteDelegate solrPendiente = DelegateUtil.getSolrPendienteDelegate();
			if (soloBorrar) {
				solrPendiente.grabarSolrPendiente(categoria.toString(), idElemento, SolrPendienteDelegate.DESINDEXAR);
			} else {
				solrPendiente.grabarSolrPendiente(categoria.toString(), idElemento, SolrPendienteDelegate.REINDEXAR);
			}
		}
	
	
	/**
	 * Compruebo si es telemático algún tramite del procedimiento.
	 * @param procedimiento
	 * @return
	 */
	public static boolean isTelematicoProcedimiento(ProcedimientoLocal procedimiento) {
		boolean telematico = false;
		for(Tramite tramite : procedimiento.getTramites()) {
			if (tramite != null && StringUtils.isNotBlank(tramite.getIdTraTel())) {
				telematico = true;
				break;
			}
		}
		return telematico;
	}

	/**
	 * Se obtiene el trámite de inicio de un procedimiento, en caso de no tenerlo, devuelve nulo. 
	 * 
	 * @param procedimiento
	 * @return
	 */
	public static Tramite getTramiteInicio(ProcedimientoLocal procedimiento) {
		Tramite tramiteInicio = null;
		for(Tramite tramite : procedimiento.getTramites()) {
			
			if (tramite != null && tramite.getFase() == Tramite.INICIACION) {
				tramiteInicio = tramite;
				break;
			}
		}
		return tramiteInicio;
	}
	
	
	
}
