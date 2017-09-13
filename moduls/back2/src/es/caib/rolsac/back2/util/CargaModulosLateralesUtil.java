package es.caib.rolsac.back2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

/**
 * @author e43155798r
 *
 */

@SuppressWarnings("deprecation") // amartin: debido a la clase org.ibit.rol.sac.model.Documento
public class CargaModulosLateralesUtil {
	
	private static Log log = LogFactory.getLog(CargaModulosLateralesUtil.class);
	
	// TODO amartin: ¿agregar todos los métodos restantes de carga de datos de los módulos laterales no repetidos?
	
	/**
	 * Devuelve una lista con los documentos relacionados con el registro con clave primaria id.
	 * 
	 * @param listaMaterias
	 * @param id
	 * @param lang
	 * @param ordenable
	 * @return
	 */
	public static List<Map<String, Object>> recuperaDocumentosRelacionados(List<Documento> listaDocumentos, Long id, 
			List<String> idiomas, boolean ordenable) {
		
		List<Map<String, Object>> listaDocumentosDTO = new ArrayList<Map<String, Object>>();
		
		for (Documento doc : listaDocumentos) {
			
			if (doc != null) {
			    
				// Montar map solo con los campos 'titulo' de las traducciones del documento.
				Map<String, String> titulos = new HashMap<String, String>();
				String nombre;
				TraduccionDocumento traDoc;

				for (String idioma : idiomas) {
					
					traDoc = (TraduccionDocumento)doc.getTraduccion(idioma);
					nombre = (traDoc != null && traDoc.getTitulo() != null) ? traDoc.getTitulo() : "";
					
					titulos.put(idioma, nombre);
					
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", doc.getId());
				
				if (ordenable)
					map.put("orden", doc.getOrden());
				
				map.put("nombre", titulos);
				map.put("idMainItem", id);
				map.put("idRelatedItem", doc.getId());
				
				listaDocumentosDTO.add(map);
				
			} else {
				
				log.error("El registre amb ID " + id + " té un document nul.");
				
			}
			
		}
		
		return listaDocumentosDTO;
		
	}
	
	/**
	 * Devuelve una lista con los iconos relacionados con el registro con clave primaria id.
	 * Se especifica como segundo parámetro la clase con la cual se ha parametrizado la lista de iconos
	 * (i.e. IconoMateria ó IconoFamilia).
	 * 
	 * @param listaIconos
	 * @param c
	 * @param id
	 * @param lang
	 * @param ordenable
	 * @return
	 */
	public static List<Map<String, Object>> recuperaIconosRelacionados(List listaIconos, Class c, Long id) {
		
		List<Map<String, Object>> listaIconosDTO = new ArrayList<Map<String, Object>>();
		
		if (listaIconos != null && listaIconos.size() > 0) {
					
			if (c.isInstance(new IconoMateria()))
				listaIconosDTO = recuperaIconosMateriaRelacionados(listaIconos, id);
					
			else if (c.isInstance(new IconoFamilia()))
				listaIconosDTO = recuperaIconosFamiliaRelacionados(listaIconos, id);
		
		}
				
		return listaIconosDTO;
		
	}
	
	private static List<Map<String, Object>> recuperaIconosMateriaRelacionados(List<IconoMateria> listaIconos, Long id) {
		
		List<Map<String, Object>> listaIconosDTO = new ArrayList<Map<String, Object>>();
		
		for (IconoMateria icono : listaIconos) {
			
			if (icono != null && StringUtils.isNotEmpty(icono.getNombre()) ) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", icono.getId());
				map.put("nombre", icono.getNombre());
				map.put("idMainItem", id);
				map.put("idRelatedItem", icono.getId());
				
				listaIconosDTO.add(map);
				
			} else {
				
				log.error("La materia " + id + " té una icona null o sense arxiu.");
				
			}
			
		}
		
		return listaIconosDTO;
		
	}
	
	private static List<Map<String, Object>> recuperaIconosFamiliaRelacionados(List<IconoFamilia> listaIconos, Long id) {
		
		List<Map<String, Object>> listaIconosDTO = new ArrayList<Map<String, Object>>();
		
		for (IconoFamilia icono : listaIconos) {
			
			if ( icono != null && icono.getIcono() != null ) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", icono.getId());
				map.put("nombre", icono.getIcono().getNombre());
				map.put("idMainItem", id);
				map.put("idRelatedItem", icono.getId());
				
				listaIconosDTO.add(map);
				
			} else {
				
				log.error("La família " + id + " té una icona nul·la o sense arxiu.");
				
			}
			
		}
		
		return listaIconosDTO;
		
	}
	
	/**
	 * Devuelve una lista con los hechos vitales relacionados con el registro con clave primaria id.
	 * Se especifica como segundo parámetro la clase con la cual se ha parametrizado la lista de hechos vitales
	 * (i.e. HechoVital, HechoVitalProcedimiento ó HechoVitalAgrupacionHV).
	 * 
	 * @param listaHechosVitales
	 * @param c
	 * @param id
	 * @param lang
	 * @param ordenable
	 * @return
	 */
	public static List<Map<String, Object>> recuperaHechosVitalesRelacionados(List listaHechosVitales, Class c, Long id, 
			String lang, boolean ordenable) {
		
		List<Map<String, Object>> listaHechosVitalesDTO = new ArrayList<Map<String, Object>>();
		
		if (listaHechosVitales != null && listaHechosVitales.size() > 0) {
		
			// Diferentes tratamientos de los datos en función de la clase especificada.
			if (c.isInstance(new HechoVital()))
				listaHechosVitalesDTO = recuperaHechosVitalesGenericoRelacionados((List<HechoVital>)listaHechosVitales, id, lang);
			
			else if (c.isInstance(new HechoVitalProcedimiento()))
				listaHechosVitalesDTO = recuperaHechosVitalesProcedimientoRelacionados((List<HechoVitalProcedimiento>)listaHechosVitales, id, lang, ordenable);
			
			else if (c.isInstance(new HechoVitalAgrupacionHV()))
				listaHechosVitalesDTO = recuperaHechosVitalesAgrupacionRelacionados((List<HechoVitalAgrupacionHV>)listaHechosVitales, id, lang, ordenable);
				
		}
		
		return listaHechosVitalesDTO;
		
	}
	
	private static List<Map<String, Object>> recuperaHechosVitalesGenericoRelacionados(List<HechoVital> listaHechosVitales, Long id, String lang) {
		
		List<Map<String, Object>> listaHechosVitalesDTO = new ArrayList<Map<String, Object>>();
		
		for (HechoVital hechoVital : listaHechosVitales) {
			
			TraduccionHechoVital thv = (TraduccionHechoVital)hechoVital.getTraduccion(lang);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", hechoVital.getId());
			map.put("nom", thv.getNombre());
			map.put("idMainItem", id);
			map.put("idRelatedItem", hechoVital.getId());
			
			listaHechosVitalesDTO.add(map);
			
		}
		
		return listaHechosVitalesDTO;
		
	}
	
	private static List<Map<String, Object>> recuperaHechosVitalesProcedimientoRelacionados(List<HechoVitalProcedimiento> listaHechosVitales, Long id, 
			String lang, boolean ordenable) {
		
		List<Map<String, Object>> listaHechosVitalesDTO = new ArrayList<Map<String, Object>>();
		
		for (HechoVitalProcedimiento hechoVitalProc : listaHechosVitales) {
			
			TraduccionHechoVital thv = (TraduccionHechoVital)hechoVitalProc.getHechoVital().getTraduccion(lang);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", hechoVitalProc.getHechoVital().getId());
			map.put("nom", thv.getNombre());
			
			if (ordenable)
				map.put("orden", hechoVitalProc.getOrden());
			
			map.put("idMainItem", id);
			map.put("idRelatedItem", hechoVitalProc.getHechoVital().getId());
			
			listaHechosVitalesDTO.add(map);
			
		}
		
		Collections.sort(listaHechosVitalesDTO, new HechoVitalProcedimientoDTOComparator());
		
		return listaHechosVitalesDTO;
		
	}
	
	private static List<Map<String, Object>> recuperaHechosVitalesAgrupacionRelacionados(List<HechoVitalAgrupacionHV> listaHechosVitales, Long id, 
			String lang, boolean ordenable) {
		
		List<Map<String, Object>> listaHechosVitalesDTO = new ArrayList<Map<String, Object>>();		
			
		for (HechoVitalAgrupacionHV fetVitalAgrupacioFV : listaHechosVitales) {
			
			if (fetVitalAgrupacioFV != null) {
				
				TraduccionHechoVital thv = (TraduccionHechoVital)fetVitalAgrupacioFV.getHechoVital().getTraduccion(lang);
				String nombre = "";
				
				if (thv != null) {
					//Retirar posible enlace incrustado en titulo
					nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(thv.getNombre());
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", fetVitalAgrupacioFV.getHechoVital().getId());
				map.put("nombre", nombre);
				
				if (ordenable)
					map.put("orden", fetVitalAgrupacioFV.getOrden());
				
				map.put("idMainItem", id);
				map.put("idRelatedItem", fetVitalAgrupacioFV.getHechoVital().getId());
				
				listaHechosVitalesDTO.add(map);
                
			}
			
		}
		
		return listaHechosVitalesDTO;
		
	}
	
	/**
	 * Devuelve una lista con las materias relacionadas con el registro con clave primaria id.
	 * 
	 * @param listaMaterias
	 * @param id
	 * @param lang
	 * @param ordenable
	 * @return
	 */
	public static List<Map<String, Object>> recuperaMateriasRelacionadas(List<Materia> listaMaterias, Long id, 
			String lang, boolean ordenable) {
		
		List<Map<String, Object>> listaMateriasDTO = new ArrayList<Map<String, Object>>();
		
		if (listaMaterias != null && listaMaterias.size() > 0) {

			Map<String, Object> map;

			for (Materia materia : listaMaterias) {

				map = new HashMap<String, Object>();

				map.put("id", materia.getId());
				
				if (lang != null)
					map.put("nom", materia.getNombreMateria(lang));
				else
					map.put("nom", materia.getNombre());
				
				if (ordenable)
					map.put("orden", materia.getOrden());
				
				map.put("idMainItem", id);
				map.put("idRelatedItem", materia.getId());
				
				listaMateriasDTO.add(map);

			}

		}
		
		return listaMateriasDTO;
		
	}
	
	/**
	 * Devuelve una lista con los perfiles gestores relacionados con el registro con clave primaria id.
	 * 
	 * @param listaPerfilGestor
	 * @param id
	 * @param lang
	 * @param ordenable
	 * @return
	 */
	public static List<Map<String, Object>> recuperaPerfilesGestorRelacionados(List<PerfilGestor> listaPerfilGestor, Long id, 
			String lang, boolean ordenable) {
		
		List<Map<String, Object>> listaPerfilGestorDTO = new ArrayList<Map<String, Object>>();
		
		if (listaPerfilGestor != null && listaPerfilGestor.size() > 0) {

			Map<String, Object> map;

			for (PerfilGestor perfil : listaPerfilGestor) {

				map = new HashMap<String, Object>();

				map.put("id", perfil.getId());
				map.put("nom", perfil.getNombrePerfilGestor(lang));
				map.put("idMainItem", id);
				map.put("idRelatedItem", perfil.getId());
				
				listaPerfilGestorDTO.add(map);

			}

		}
		
		return listaPerfilGestorDTO;
		
	}
	/**
	 * Devuelve una lista con las UAs relacionadas con el registro con clave primaria id.
	 * 
	 * @param listaUAs
	 * @param id
	 * @param lang
	 * @return
	 */
	public static List<Map<String, Object>> recuperaUAsRelacionadas(final List<UnidadAdministrativa> listaUAs, final Long id, 
			final String lang, final boolean ordenable) {
		
		List<Map<String, Object>> listaUAsDTO = new ArrayList<Map<String, Object>>();
		
		if (listaUAs != null && listaUAs.size() > 0) {
			
			// Ordenamos alfabéticamente el listado de UAs.
			Collections.sort(listaUAs, new UAComparator());
			
			Iterator<UnidadAdministrativa> it = listaUAs.iterator();
			
			while (it.hasNext()) {
				
				UnidadAdministrativa ua = it.next();
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", ua.getId());
				map.put("nombre", ua.getNombreUnidadAdministrativa(lang));
				
				if (ordenable)
					map.put("orden", ua.getOrden());
				
				map.put("idMainItem", id);
				map.put("idRelatedItem", ua.getId());
				
				listaUAsDTO.add(map);
				
			}
		
		}
		
		return listaUAsDTO;
		
	}
	
	static class UAComparator implements Comparator<UnidadAdministrativa> {

		public int compare(UnidadAdministrativa o1, UnidadAdministrativa o2) {
			
			TraduccionUA tr1 = (TraduccionUA)o1.getTraduccion();
			TraduccionUA tr2 = (TraduccionUA)o2.getTraduccion();
	
			if (tr1 == null) {
	
				return -1;
	
			} else if (tr2 == null) {
	
				return 1;
	
			} else {
	
				String nombre1 = tr1.getNombre();
				String nombre2 = tr2.getNombre();
	
				// TODO amartin 30/10/2013: código comentado para compatibilidad con JDK 1.6.
				// Habría que encontrar un modo de normalizar las cadenas de texto que sea compatible 
				// en JDK1.5 y 1.6 ó superior a la vez. Ahora mismo el problema es que la clase Normalizer pertenece a:
				// - Package java.text.Normalizer en 1.6
				// - Package sun.text.Normalizer en 1.5.
				            			
				/*
				// Normalizamos y pasamos a ASCII para ordenar ignorando acentos o resto de caracteres extraños.
				nombre1 = Normalizer.normalize(nombre1, Normalizer.DECOMP_COMPAT, 0).replaceAll("[^\\p{ASCII}]", "");
				nombre2 = Normalizer.normalize(nombre2, Normalizer.DECOMP_COMPAT, 0).replaceAll("[^\\p{ASCII}]", "");
				*/
				
				// Normalización básica temporal, hasta pasar definitivamente a JDK1.6 ó superior
				// y así poder usar la clase java.text.Normalizer.
				nombre1 = nombre1.replaceAll("[\u00E0\u00E1]", "a"); // áà
				nombre1 = nombre1.replaceAll("[\u00E8\u00E9]", "e"); // éè
				nombre1 = nombre1.replaceAll("[\u00ED\u00EE]", "i"); // íì
				nombre1 = nombre1.replaceAll("[\u00F2\u00F3]", "o"); // óò
				nombre1 = nombre1.replaceAll("[\u00F9\u00FA]", "u"); // úù
				nombre1 = nombre1.replaceAll("[\u00C0\u00C1]", "A"); // ÁÀ
				nombre1 = nombre1.replaceAll("[\u00C8\u00C9]", "E"); // ÉÈ
				nombre1 = nombre1.replaceAll("[\u00CC\u00CD]", "I"); // ÍÌ
				nombre1 = nombre1.replaceAll("[\u00D2\u00D3]", "O"); // ÓÒ
				nombre1 = nombre1.replaceAll("[\u00D9\u00DA]", "U"); // ÚÙ    					
				
				nombre2 = nombre2.replaceAll("[\u00E0\u00E1]", "a"); // áà
				nombre2 = nombre2.replaceAll("[\u00E8\u00E9]", "e"); // éè
				nombre2 = nombre2.replaceAll("[\u00ED\u00EE]", "i"); // íì
				nombre2 = nombre2.replaceAll("[\u00F2\u00F3]", "o"); // óò
				nombre2 = nombre2.replaceAll("[\u00F9\u00FA]", "u"); // úù
				nombre2 = nombre2.replaceAll("[\u00C0\u00C1]", "A"); // ÁÀ
				nombre2 = nombre2.replaceAll("[\u00C8\u00C9]", "E"); // ÉÈ
				nombre2 = nombre2.replaceAll("[\u00CC\u00CD]", "I"); // ÍÌ
				nombre2 = nombre2.replaceAll("[\u00D2\u00D3]", "O"); // ÓÒ
				nombre2 = nombre2.replaceAll("[\u00D9\u00DA]", "U"); // ÚÙ    					
	
				return nombre1.compareToIgnoreCase(nombre2);
				
			}
		
		}
		
	}
	
	static class HechoVitalProcedimientoDTOComparator implements Comparator<Map<String, Object>> {
		
        public int compare(Map<String, Object> hvp1, Map<String, Object> hvp2) {
            
        	Integer orden1 = (Integer)hvp1.get("orden");
            Integer orden2 = (Integer)hvp2.get("orden");
            
            return orden1.compareTo(orden2); 
            
        }
        
    }

}


