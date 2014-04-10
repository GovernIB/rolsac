package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionAgrupacionM;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.AgrupacionMDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaAgrupacionMDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/agrupacioMateries/")
public class TMAgrupacioMateriesController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(TMMateriesController.class);
	
	private static String obtenerIdioma() throws DelegateException {
		return DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
	}

	@RequestMapping(value = "/agrupacioMateries.do")
	public String pantalla(Map<String, Object> model, HttpServletRequest request) {
		
		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMAgrupacioMateries.jsp");

		RolUtil rolUtil = new RolUtil(request);
		if (rolUtil.userIsAdmin()) {
			String codiEstandarSec = System.getProperty("es.caib.rolsac.codiEstandarSecGrupMat");

			// Listado secciones
			SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
			List<IdNomDTO> llistaSeccioDTO = new ArrayList<IdNomDTO>();
			List<Seccion> llistaSeccio = new ArrayList<Seccion>();

			String lang = null;
			try {
				lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			} catch (DelegateException dEx) {
				log.error("Error al recuperar el idioma por defecto");
				lang = "ca";
			}

			try {
				
				Seccion seccion = seccioDelegate.obtenerSeccionCE(codiEstandarSec);
				if (seccion != null) {
					llistaSeccio = seccioDelegate.listarHijosSeccion(seccion.getId());
					for (Seccion seccio : llistaSeccio) {
						TraduccionSeccion tpo = (TraduccionSeccion) seccio.getTraduccion(lang);
						llistaSeccioDTO.add(new IdNomDTO(seccio.getId(),tpo == null ? null : tpo.getNombre()));
					}
				} else {
					log.warn("No se ha encontrado una seccion con el codigo estandar " + codiEstandarSec);
				}

			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException())
					log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx));
				else
					log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
			model.put("llistaSeccio", llistaSeccioDTO);

			// Listado materias
			List<IdNomDTO> llistaMateriaDTO = new ArrayList<IdNomDTO>();
			try {
				
				MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
				List<Materia> materies = materiaDelegate.listarMaterias();
				for (Materia materia: materies) {
					TraduccionMateria tm = (TraduccionMateria) materia.getTraduccion(lang);
					llistaMateriaDTO.add(new IdNomDTO(materia.getId(),tm == null ? null : tm.getNombre()));
				}

			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException())
					log.error("Permisos insuficients: " + dEx.getMessage());
				else
					log.error("Error: " + dEx.getMessage());
				
			}
			
			model.put("llistaMateries", llistaMateriaDTO);

			PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
			try {
				
				List<IdNomDTO> perfilesDTO = new LinkedList<IdNomDTO>();
				for (PerfilCiudadano perfil: (List<PerfilCiudadano>) perfilDelegate.listarPerfiles()) {
					TraduccionPerfilCiudadano tpc = (TraduccionPerfilCiudadano) perfil.getTraduccion();
					perfilesDTO.add(new IdNomDTO(perfil.getId(), tpc != null ? tpc.getNombre() : ""));
				}
				
				model.put("perfils", perfilesDTO);
				model.put("escriptori", "pantalles/taulesMestres/tmAgrupacioMateries.jsp");

			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
					model.put("error", "permisos");
				} else {
					log.error("Error: " + dEx.getMessage());
					model.put("error", "altres");
				}
				
			}

		} else {
			
			model.put("error", "permisos");
			
		}

		loadIndexModel(model, request);
		
		return "index";
		
	}

	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {

		List<Map<String, Object>> llistaAgrupacioMateriaDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> agrupacioMateriaDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");

		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {

			MateriaAgrupacionMDelegate agrupacioMateriaDelegate = DelegateUtil.getMateriaAgrupacionMDelegate();			
			resultadoBusqueda = agrupacioMateriaDelegate.listarAgrupacionMaterias(Integer.parseInt(pagPag), Integer.parseInt(pagRes));			

			for (AgrupacionMateria agrupacionMateria: castList(AgrupacionMateria.class, resultadoBusqueda.getListaResultados() ) ) {

				TraduccionAgrupacionM tam = (TraduccionAgrupacionM) agrupacionMateria.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
				agrupacioMateriaDTO = new HashMap<String, Object>();
				agrupacioMateriaDTO.put("id", agrupacionMateria.getId());
				agrupacioMateriaDTO.put("nom", tam == null ? "" : tam.getNombre());
				agrupacioMateriaDTO.put("codi_estandar", agrupacionMateria.getCodigoEstandar());

				llistaAgrupacioMateriaDTO.add(agrupacioMateriaDTO);

			}

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}

		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaAgrupacioMateriaDTO);

		return resultats;
		
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {
		
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result = null;

		Map<String, String> valoresForm = new HashMap<String, String>();

		try {
			
			Set<String> materiesForm = new HashSet<String>();
			Iterator<String> itParams = request.getParameterMap().keySet().iterator();
			
			while (itParams.hasNext()) {
				String key = itParams.next();
				String value = request.getParameter(key);
				if (key.startsWith("materia_")){
					materiesForm.add(key);
				} 
				valoresForm.put(key, value);
			}

			//Campos obligatorios
			Long codiSeccio = ParseUtil.parseLong(valoresForm.get("item_seccio"));

			if (codiSeccio == null) {
				String error = messageSource.getMessage("agrupacioM.formulari.error.falten_camps", null, request.getLocale());
				result = new IdNomDTO(-3l, error);
				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);                
			}

			AgrupacionMDelegate agrupacioMDelegate = DelegateUtil.getAgrupacionMDelegate();
			SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
			Seccion seccion = seccionDelegate.obtenerSeccionSinFichasUA(codiSeccio);

			AgrupacionMateria agrupacioMateria = new AgrupacionMateria();

			Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			if (id != null) { 
				agrupacioMateria = agrupacioMDelegate.obtenerAgrupacionM(id);
			} else {									
				agrupacioMateria = new AgrupacionMateria();
			}

			agrupacioMateria.setSeccion(seccion);

			//Codi estandard
			String codiEstandard = valoresForm.get("item_codi_estandard");
			agrupacioMateria.setCodigoEstandar(codiEstandard);

			// Idiomas
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			Map traduccions = new HashMap(langs.size());
			for (String lang: langs) {
				TraduccionAgrupacionM tam = new TraduccionAgrupacionM();
				tam.setNombre( RolUtil.limpiaCadena(valoresForm.get("item_nom_"+  lang )) );

				traduccions.put(lang, tam);
			}
			
			agrupacioMateria.setTraduccionMap(traduccions);

			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			List<MateriaAgrupacionM> materiesNew = new ArrayList<MateriaAgrupacionM>();
			List<MateriaAgrupacionM> materiesOld = agrupacioMateria.getMateriasAgrupacionM();

			if (agrupacioMateria.getMateriasAgrupacionM() != null || materiesForm.size() > 0) {
				
				StringBuilder idsMateria = new StringBuilder();
				
				// Recorrem el formulari
				for (Iterator<String> iterator = materiesForm.iterator(); iterator.hasNext();) {
					String nomParameter = (String)iterator.next();
					String[] elements = nomParameter.split("_");
					if (elements[0].equals("materia") && elements[1].equals("id")) {
						//En aquest cas, elements[2] es igual al id del fetVital
						if (idsMateria.length() == 0) {
							idsMateria.append(elements[2]);
						} else {
							idsMateria.append(", ");
							idsMateria.append(elements[2]);
						}
					}
				}
				
				List<Materia> materias = materiaDelegate.obtenerMateriasPorIDs(idsMateria.toString(), DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
				for (Materia materia: materias) {
					MateriaAgrupacionM materiaAgrupacionM = new MateriaAgrupacionM();
					materiaAgrupacionM.setAgrupacion(agrupacioMateria);
					materiaAgrupacionM.setMateria(materia);
					materiaAgrupacionM.setOrden(ParseUtil.parseInt(valoresForm.get("materia_orden_" + materia.getId())));
					materiesNew.add(materiaAgrupacionM);					
				}
				
			}

			// Objectiu
			agrupacioMateria.setMateriasAgrupacionM(materiesNew);
			agrupacioMDelegate.guardarAgrupacionM(agrupacioMateria, materiesOld);

			String ok = messageSource.getMessage("agrupacioM.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(agrupacioMateria.getId(), ok);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}

		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
		
	}

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(Long id, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			AgrupacionMateria agrupacioMateries = DelegateUtil.getAgrupacionMDelegate().obtenerAgrupacionM(id);
			String codiEstandarSec = System.getProperty("es.caib.rolsac.codiEstandarSecGrupMat");
			
			resultats.put("item_id", id);
			resultats.put("item_codi_estandard", agrupacioMateries.getCodigoEstandar());
			resultats.put("item_seccions", getJSONSecciones(codiEstandarSec, obtenerIdioma() ));

			// XXX: No la sección no debería ser nula ya que el back nuevo no permite generarlo así. Se mantiene ya que es probable que se generaran valores nulos desde el back antiguo.
			resultats.put("item_seccio", agrupacioMateries.getSeccion() != null ? agrupacioMateries.getSeccion().getId() : null);  

			omplirCampsTraduibles(resultats, agrupacioMateries);

		} catch (DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else 
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}
	
	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {
		
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
						
			// Materias relacionadas
			List<Materia> materias = DelegateUtil.getMateriaAgrupacionMDelegate().obtenerMateriasRelacionadas(id, obtenerIdioma());
			resultats.put("materies", CargaModulosLateralesUtil.recuperaMateriasRelacionadas(materias, id, null, true));
						
		} catch (DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else 
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
		}
		
		return resultats;
		
	}

	// Devuelve lista de unidades administrativas materias (id uamateria y nombre ua).  
	private List<IdNomDTO> getJSONSecciones(String codiEstandarSec, String lang) {

		SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();
		List<IdNomDTO> llistaSeccioDTO = new ArrayList<IdNomDTO>();
		List<Seccion> llistaSeccio = new ArrayList<Seccion>();

		try {

			Seccion seccion = seccioDelegate.obtenerSeccionCE(codiEstandarSec);

			if (seccion != null) {

				llistaSeccio = seccioDelegate.listarHijosSeccion(seccion.getId());

				for (Seccion seccio : llistaSeccio)
					llistaSeccioDTO.add( new IdNomDTO(seccio.getId(), seccio.getNombre()) );

			} else {

				log.warn("No se ha encontrado una seccion con el codigo estandar " + codiEstandarSec);

			}

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException())
				log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx));

			else
				log.error(ExceptionUtils.getStackTrace(dEx));

		}

		return llistaSeccioDTO;

	}

	private void omplirCampsTraduibles(Map<String, Object> resultats, AgrupacionMateria agrupacioM)
			throws DelegateException {
		
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();

		for (String lang: langs) {
			if (null != agrupacioM.getTraduccion(lang)) {
				resultats.put(lang, (TraduccionAgrupacionM) agrupacioM.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionAgrupacionM());
			}
		}
		
	}

	@RequestMapping(value = "/esborrarAgrupacioMateries.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			AgrupacionMDelegate agrupacioMDelegate = DelegateUtil.getAgrupacionMDelegate();
			agrupacioMDelegate.borrarAgrupacionM(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (NumberFormatException nfEx) {
			
			resultatStatus.setId(-3l);
			log.error("Error: Id de pefil no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
			
		}
		
		return resultatStatus;
		
	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
		
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			TraduccionAgrupacionM traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			traduccions = traductor.translate(traduccioOrigen, idiomaOrigenTraductor);

			resultats.put("traduccions", traduccions);

		} catch (DelegateException dEx) {
			
			logException(log, dEx);
			
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
			
		} catch (NullPointerException npe) {
			
			log.error("AgrupacionMBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("AgrupacionMBackController.traduir: Error en al traducir Agrupacion Materies: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}

		return resultats;
		
	}

	private TraduccionAgrupacionM getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {
		
		TraduccionAgrupacionM traduccioOrigen = new TraduccionAgrupacionM();

		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}

		return traduccioOrigen;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarMateriasRelacionadas.do")
	public @ResponseBody IdNomDTO guardarMateriasRelacionadas(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
			
			AgrupacionMDelegate agrupacioMDelegate = DelegateUtil.getAgrupacionMDelegate();
			AgrupacionMateria agrupacioMateria = agrupacioMDelegate.obtenerAgrupacionM(id);
			
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			List<MateriaAgrupacionM> materiesNew = new ArrayList<MateriaAgrupacionM>();
			
			// FIXME amartin: esto no funciona. Parece que hay algún fallo en el modelo de Hibernate => AgrupacionMateria.hbm.xml
			// No se devuelven correctamente las materias relacionadas con la agrupación de materias.
			// List<MateriaAgrupacionM> materiesOld = agrupacioMateria.getMateriasAgrupacionM();
			
			List<MateriaAgrupacionM> materiesOld = agrupacioMDelegate.obtenerMateriasAgrupacion(id);
			
			// Es posible que hayan vaciado de elementos el módulo. En ese caso, elementos será null.
			if ( elementos != null ) {
				
				for (int i = 0; i < elementos.length; i++) {
					
					Materia materia = materiaDelegate.obtenerMateria(elementos[i]);
					
					MateriaAgrupacionM materiaAgrupacionM = new MateriaAgrupacionM();
					materiaAgrupacionM.setAgrupacion(agrupacioMateria);
					materiaAgrupacionM.setMateria(materia);
					materiaAgrupacionM.setOrden(i + 1);
					
					materiesNew.add(materiaAgrupacionM);	
					
				}
					
			}
			
			// Actualizamos
			agrupacioMateria.setMateriasAgrupacionM(materiesNew);
			agrupacioMDelegate.guardarAgrupacionM(agrupacioMateria, materiesOld);
			
			String ok = messageSource.getMessage("agrupacioM.guardat.materies.correcte", null, request.getLocale());
			result = new IdNomDTO(agrupacioMateria.getId(), ok);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}

		return result;
		
	}

}
