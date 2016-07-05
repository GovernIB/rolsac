package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SeccionDTO;
import org.ibit.rol.sac.model.dto.SeccionFichaDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadMateriaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.api.v1.UnidadAdministrativaDTO;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/unitatadm/")
public class UnitatAdmBackController extends PantallaBaseController {

	private static Log  log = LogFactory.getLog(UnitatAdmBackController.class);
	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.ua.url";
	private static final String OPERACION_FALLIDA = "Error de sessi�n: Sessi�n expirada o no inciada";

	@RequestMapping(value = "/unitatadm.do", method = GET)
	public String llistatUniAdm(Map<String, Object> model, HttpServletRequest request, HttpSession session) {

		MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
		TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
		EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();

		List<Materia> llistaMateries = new ArrayList<Materia>();
		List<Tratamiento> llistaTractaments = new ArrayList<Tratamiento>();
		List<EspacioTerritorial> llistaEspaiTerritorial = new ArrayList<EspacioTerritorial>();
		List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
		List<IdNomDTO> llistaTractamentsDTO = new ArrayList<IdNomDTO>();
		List<IdNomDTO> llistaEspaiTerritorialDTO = new ArrayList<IdNomDTO>();

		String lang = null;

		try {

			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			llistaMateries = materiaDelegate.listarMaterias();

			for (Materia materia : llistaMateries)
				llistaMateriesDTO.add(new IdNomDTO(materia.getId(),materia.getNombreMateria(lang)));


			llistaTractaments = tratamientoDelegate.listarTratamientos();

			for (Tratamiento tractament : llistaTractaments)
				llistaTractamentsDTO.add(new IdNomDTO(tractament.getId(), tractament.getNombreTratamiento(lang)));


			llistaEspaiTerritorial = espacioTerritorialDelegate.listarEspaciosTerritoriales();

			for (EspacioTerritorial espaiTerritorial : llistaEspaiTerritorial)
				llistaEspaiTerritorialDTO.add(new IdNomDTO(espaiTerritorial.getId(), espaiTerritorial.getNombreEspacioTerritorial(lang)));

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException())
				log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx));
			else
				log.error(ExceptionUtils.getStackTrace(dEx));


			if (lang == null) lang = "ca";

		}

		// Control de si se dan permisos extrar al rol SUPER
		boolean accesoSuper = System.getProperty("es.caib.rolsac.permisosSuperAdicionales") != null && System.getProperty("es.caib.rolsac.permisosSuperAdicionales").equals("Y") && request.isUserInRole("sacsuper");
		boolean accesoOtros = request.isUserInRole("sacsystem") || request.isUserInRole("sacadmin");
		boolean acceso = (accesoSuper || accesoOtros) ? true : false;

		model.put("nuevaUA", acceso);
		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 1);
		model.put("titol_escriptori", messageSource.getMessage("submenu.unitatAdm", null, request.getLocale()));
		model.put("escriptori", "pantalles/unitatadm.jsp");

		if (session.getAttribute("unidadAdministrativa") != null) {
			model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
			model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(lang));            
		}               

		model.put("llistaMateries", llistaMateriesDTO);        
		model.put("llistaTractaments", llistaTractamentsDTO);
		model.put("llistaEspaiTerritorial", llistaEspaiTerritorialDTO);

		model.put("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));

		loadIndexModel (model, request);

		return "index";

	}


	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {

		Map<String,Object> resultats = new HashMap<String,Object>();
		UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();

		if (request.getParameter("id") == null || "".equals(request.getParameter("id")) || "0".equals(request.getParameter("id"))) {

			try {
				if (unitatDelegate.autorizarCrearUA()) {
					resultats.put("id", 0); // No hay id y tiene permisos para crear una UA
				} else {
					resultats.put("error", messageSource.getMessage("error.permisos.crearUA", null, request.getLocale()));
					resultats.put("id", -1);
				}

			} catch (DelegateException dEx) {

				if (dEx.isSecurityException()) {
					resultats.put("error", messageSource.getMessage("error.permisos.crearUA", null, request.getLocale()));
					resultats.put("id", -1);
				} else {
					resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
					resultats.put("id", -2);
					log.error(ExceptionUtils.getStackTrace(dEx));
				}

			}

			return resultats;

		}

		Long idUA = new Long(request.getParameter("id"));

		try {

			UnidadAdministrativa uni = unitatDelegate.consultarUnidadAdministrativaSinFichas(idUA);
			resultats.put("id", idUA);

			// Idiomas.
			agregaTraduccionesADetalle(resultats, uni);			

			// Configuración/gestión.
			//resultats.put("item_clau_hita", uni.getClaveHita());
			resultats.put("item_codi_estandar", uni.getCodigoEstandar());
			resultats.put("item_clave_primaria", idUA);
			resultats.put("item_domini", uni.getDominio());
			resultats.put("item_validacio", uni.getValidacion());
			resultats.put("item_telefon", uni.getTelefono());
			resultats.put("item_fax", uni.getFax());
			resultats.put("item_email", uni.getEmail());

			// Espacio territorial.
			if (uni.getEspacioTerrit() != null) {
				resultats.put("item_espai_territorial", uni.getEspacioTerrit().getId());
			} else {
				resultats.put("item_espai_territorial",null);
			}

			// UA Padre.
			if (uni.getPadre() != null) {
				resultats.put("pareId", uni.getPadre().getId());
				resultats.put("pareNom", uni.getPadre().getNombreUnidadAdministrativa(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));
			} else {
				resultats.put("idPadre", null);
				resultats.put("pareNom", null);
			}

			// Responsable.
			agregaResponsableADetalle(resultats, uni);

			// Tratamiento.
			if (uni.getTratamiento() != null)
				resultats.put("item_tractament", uni.getTratamiento().getId());

			// Logotipo horizontal.
			if (uni.getLogoh() != null) {
				resultats.put("item_logo_horizontal_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=3");
				resultats.put("item_logo_horizontal", uni.getLogoh().getNombre());
			} else {
				resultats.put("item_logo_horizontal_enllas_arxiu", "");
				resultats.put("item_log_horizontal", "");
			}

			// Logotipo vertical.
			if (uni.getLogov() != null) {
				resultats.put("item_logo_vertical_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=4");
				resultats.put("item_logo_vertical", uni.getLogov().getNombre());
			} else {
				resultats.put("item_log_vertical_enllas_arxiu", "");
				resultats.put("item_log_vertical", "");
			}

			// Logo saludo horizontal.
			if (uni.getLogos() != null) {
				resultats.put("item_logo_salutacio_horizontal_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=5");
				resultats.put("item_logo_salutacio_horizontal", uni.getLogos().getNombre());
			} else {
				resultats.put("item_logo_salutacio_horizontal_enllas_arxiu", "");
				resultats.put("item_logo_salutacio_horizontal", "");
			}

			// Logo saludo vertical.
			if (uni.getLogot() != null) {
				resultats.put("item_logo_salutacio_vertical_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=6");
				resultats.put("item_logo_salutacio_vertical", uni.getLogot().getNombre());
			} else {
				resultats.put("item_logo_salutacio_vertical_enllas_arxiu", "");
				resultats.put("item_logo_salutacio_vertical", "");
			}

			// Fichas de la portada web.
			agregaFichasPortadaADetalle(resultats, uni);

			// Secciones asociadas a la UA.
			resultats.put("seccions", getListaSeccionesDTO(idUA, unitatDelegate));


		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
				resultats.put("id", -1);
			} else {
				resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
				resultats.put("id", -2);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		}

		return resultats;

	}


	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();
			UnidadAdministrativa uni = unitatDelegate.consultarUnidadAdministrativaSinFichas(id);
			
			// Materias asociadas.
			List<Materia> listaMaterias = obtenerListaMateriasUA(uni); // Necesitamos obtener primero la lista de materias a partir del Set de elementos UnidadMateria.
			resultats.put("materies", CargaModulosLateralesUtil.recuperaMateriasRelacionadas(listaMaterias, id, DelegateUtil.getIdiomaDelegate().lenguajePorDefecto(), false));

			// Edificios.
			resultats.put("edificis", getLlistaEdificisDTO(resultats, uni));			

			// Usuaris.
			resultats.put("usuaris", getLlistaUsuarisDTO(resultats, uni));	

		} catch (DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}

	private Object getLlistaUsuarisDTO(Map<String, Object> resultats, UnidadAdministrativa ua) {
		
		List<Map<String, String>> llistaUsuarisDTO = null;
		
		try {
		
			if (ua.getUsuarios() != null) {
				
				llistaUsuarisDTO = new ArrayList<Map<String, String>>();
				
				for (Object u : ua.getUsuarios()) {
					
					Map<String, String> map = new HashMap<String, String>();
					Usuario usuario = (Usuario)u;
								
					map.put("id", usuario.getId().toString());
					map.put("nom", usuario.getNombre());
					map.put("idMainItem", ua.getId().toString());
					map.put("idRelatedItem", usuario.getId().toString());
					
					llistaUsuarisDTO.add(map);
				
				}
			
			}
		
		} catch (Exception ex) {
		
			log.error("Error recuperando los usuarios.");
		
		}

		return llistaUsuarisDTO;
		
	}

	private Object getLlistaEdificisDTO(Map<String, Object> resultats, UnidadAdministrativa ua) {

		List<Map<String, String>> llistaEdificisDTO = null;

		if (ua.getEdificios() != null) {

			llistaEdificisDTO = new ArrayList<Map<String, String>>();

			for (Edificio e : ua.getEdificios()) {
				
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("id", e.getId().toString());
				map.put("nom", e.getDireccion());
				map.put("idMainItem", ua.getId().toString());
				map.put("idRelatedItem", e.getId().toString());
			
				llistaEdificisDTO.add( map );
				
			}

		}

		return llistaEdificisDTO;

	}
	
	// Devuelve un List de Materia a partir del Set de elementos UnidadMateria de la UA.
	private List<Materia> obtenerListaMateriasUA(UnidadAdministrativa ua) {
		
		List<Materia> listaMaterias = new ArrayList<Materia>();
		Set<UnidadMateria> listaUnidadesMateria = ua.getUnidadesMaterias();
		
		if (listaUnidadesMateria != null) {
			for (UnidadMateria unidadMateria : listaUnidadesMateria) {
				listaMaterias.add(unidadMateria.getMateria());
			}
		}
		
		return listaMaterias;
		
	}

	private List<SeccionFichaDTO> getListaSeccionesDTO(Long idUA, UnidadAdministrativaDelegate unitatDelegate) throws DelegateException {

		// Obtenemos las secciones asociadas con la UA.
		List<SeccionFichaDTO> listaSeccionesDTO = new ArrayList<SeccionFichaDTO>();
		List<Seccion> listaSecciones = unitatDelegate.listarSeccionesUA(idUA);

		Iterator<Seccion> itSeccion = listaSecciones.iterator();
		while ( itSeccion.hasNext() ) {

			Seccion seccion = itSeccion.next();
			SeccionFichaDTO seccionFichaDTO = new SeccionFichaDTO();

			seccionFichaDTO.setId(seccion.getId());

			seccionFichaDTO.setNumFichas( unitatDelegate.cuentaFichasSeccionUA(idUA, seccion.getId()) );

			TraduccionSeccion tr = (TraduccionSeccion) seccion.getTraduccion();
			if ( tr == null ) {

				tr = new TraduccionSeccion(); 
				tr.setNombre("");

			}

			seccionFichaDTO.setNom(tr.getNombre());

			listaSeccionesDTO.add(seccionFichaDTO);

		}

		// Ordenamos por nombre, ascendente.
		Comparator<SeccionFichaDTO> comparatorASC = new Comparator<SeccionFichaDTO>() {

			public int compare(SeccionFichaDTO s1, SeccionFichaDTO s2) {
				return s1.getNom().compareTo( s2.getNom() );
			}

		};

		Collections.sort(listaSeccionesDTO, comparatorASC);

		return listaSeccionesDTO;

	}


	private void agregaFichasPortadaADetalle(Map<String, Object> resultats, UnidadAdministrativa uni) {

		resultats.put("item_nivell_1", uni.getNumfoto1());
		resultats.put("item_nivell_2", uni.getNumfoto2());
		resultats.put("item_nivell_3", uni.getNumfoto3());
		resultats.put("item_nivell_4", uni.getNumfoto4());

	}

	private void agregaResponsableADetalle(Map<String, Object> resultats, UnidadAdministrativa uni) {

		resultats.put("item_responsable", uni.getResponsable());
		resultats.put("item_responsable_sexe", uni.getSexoResponsable());
		resultats.put("item_responsable_email", uni.getResponsableEmail());

		if (uni.getFotop() != null) {
			resultats.put("item_responsable_foto_petita_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=1");
			resultats.put("item_responsable_foto_petita", uni.getFotop().getNombre());
		} else {
			resultats.put("item_responsable_foto_petita_enllas_arxiu", "");
			resultats.put("item_responsable_foto_petita", "");
		}

		if (uni.getFotog() != null) {
			resultats.put("item_responsable_foto_gran_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=2");
			resultats.put("item_responsable_foto_gran", uni.getFotog().getNombre());
		} else {
			resultats.put("item_responsable_foto_gran_enllas_arxiu", "");
			resultats.put("item_responsable_foto_gran", "");
		}

	}

	private void agregaTraduccionesADetalle(Map<String,
			Object> resultats, UnidadAdministrativa uni) {

		String langDefault = System.getProperty("es.caib.rolsac.idiomaDefault");

		if (uni.getTraduccion("ca") != null) {
			resultats.put("ca",(TraduccionUA)uni.getTraduccion("ca"));
		} else {
			if (uni.getTraduccion(langDefault) != null)
				resultats.put("ca",(TraduccionUA)uni.getTraduccion(langDefault));
			else
				resultats.put("ca", new TraduccionUA());
		}

		if (uni.getTraduccion("es") != null) {
			resultats.put("es",(TraduccionUA)uni.getTraduccion("es"));
		} else {
			if (uni.getTraduccion(langDefault) != null)
				resultats.put("es",(TraduccionUA)uni.getTraduccion(langDefault));
			else
				resultats.put("es", new TraduccionUA());
		}

		if (uni.getTraduccion("en") != null) {
			resultats.put("en",(TraduccionUA)uni.getTraduccion("en"));
		} else {
			if (uni.getTraduccion(langDefault) != null)
				resultats.put("en",(TraduccionUA)uni.getTraduccion(langDefault));
			else
				resultats.put("en", new TraduccionUA());
		}

		if (uni.getTraduccion("de") != null) {
			resultats.put("de",(TraduccionUA)uni.getTraduccion("de"));
		} else {
			if (uni.getTraduccion(langDefault) != null)
				resultats.put("de",(TraduccionUA)uni.getTraduccion(langDefault));
			else
				resultats.put("de", new TraduccionUA());
		}

		if (uni.getTraduccion("fr") != null) {
			resultats.put("fr",(TraduccionUA)uni.getTraduccion("fr"));
		} else {
			if (uni.getTraduccion(langDefault) != null)
				resultats.put("fr",(TraduccionUA)uni.getTraduccion(langDefault));
			else
				resultats.put("fr", new TraduccionUA());
		}

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
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

		try {

			// Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
			// Iremos recopilando los parámetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
			List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

			//TODO: 03/10/2013: Ya no se contempla la posibilidad de enviar las fichas mediante éste método??
			for (FileItem item : items) {
				if (item.isFormField()) {
					valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
				} else {
					ficherosForm.put(item.getFieldName(), item);    				
				}
			}

			// Campos obligatorios.
			// Se cambia el "item_nom_ca" por el "item_nom_" + idioma
			String nom = valoresForm.get("item_nom_" + DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
			String validacio = valoresForm.get("item_validacio");
			String sexeResponsable = valoresForm.get("item_responsable_sexe");
			String tractament = valoresForm.get("item_tractament");

			if (nom == null || "".equals(nom) || validacio == null || "".equals(validacio) || sexeResponsable == null || "".equals(sexeResponsable) || tractament == null || "".equals(tractament)) {
				String error = messageSource.getMessage("unitatadm.formulari.error.falten_camps", null, request.getLocale());
				result = new IdNomDTO(-3l, error);
				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);                
			} 

			UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
			UnidadAdministrativa unitatAdministrativa;

			Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			boolean edicion = id != null;
			if (edicion) { 
				unitatAdministrativa = unitatAdministrativaDelegate.consultarUnidadAdministrativaSinFichas(id);
			} else {									
				unitatAdministrativa = new UnidadAdministrativa();
			}

			// Configuración/gestion.
			// unitatAdministrativa.setClaveHita(valoresForm.get("item_clau_hita"));
			unitatAdministrativa.setCodigoEstandar(valoresForm.get("item_codi_estandar"));
			unitatAdministrativa.setDominio(valoresForm.get("item_domini"));
			unitatAdministrativa.setValidacion(Integer.parseInt(valoresForm.get("item_validacio")));
			unitatAdministrativa.setTelefono(valoresForm.get("item_telefon"));
			unitatAdministrativa.setFax(valoresForm.get("item_fax"));
			unitatAdministrativa.setEmail(valoresForm.get("item_email"));

			// Idiomas.
			unitatAdministrativa.setTraduccionMap(getTraduccionesFormulario(valoresForm));

			// Espai territorial.
			unitatAdministrativa.setEspacioTerrit(getEspacioTerritorialFormulario(valoresForm));

			// Responsable.
			guardaResponsable(valoresForm, ficherosForm, unitatAdministrativa);

			// Tratamiento.
			Long tractamentId = ParseUtil.parseLong(valoresForm.get("item_tractament"));
			if (tractamentId != null) {
				TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
				Tratamiento tratamiento = tratamientoDelegate.obtenerTratamiento(tractamentId);
				unitatAdministrativa.setTratamiento(tratamiento);
			} else {
				String error = messageSource.getMessage("unitatadm.formulari.error.tractament_incorrecte", null, request.getLocale());
				result = new IdNomDTO(-3l, error);	
				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
			}

			// Logotipos.
			guardaLogotipos(valoresForm, ficherosForm, unitatAdministrativa);

			// Fichas de la portada web.
			guardarFichasPortada(valoresForm, unitatAdministrativa);

			// UA Padre.
			Long unitatAdmPareId = ParseUtil.parseLong(valoresForm.get("item_pare_id"));			
			crearOActualizarUnitatAdministrativa(unitatAdministrativa, unitatAdmPareId);

			// Secciones-Fichas.
			// Funcionalidad trasladada a método guardarFitxesUASeccio().
			// Se guarda el estado al pulsar el botón "Finalitza".

			// TODO: aclarar => ¿Por qué se ejecuta también esta misma llamada, líneas antes, en el guardado de la UA padre?
			// ¿Es posible que sobre?
			crearOActualizarUnitatAdministrativa(unitatAdministrativa, unitatAdmPareId);

			//Actualiza estadísticas
			DelegateUtil.getEstadisticaDelegate().grabarEstadisticaUnidadAdministrativa( unitatAdministrativa.getId() );

			// Sobre escrivim la unitat administrativa de la mollapa
			UnidadAdministrativaController.actualizarUAMigaPan(session, unitatAdministrativa);

			String ok = messageSource.getMessage("unitatadm.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(unitatAdministrativa.getId(), ok);            

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		} catch (UnsupportedEncodingException e) {

			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));

		} catch (FileUploadException e) {

			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));

		}

		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);

	}

	private void guardarFichasPortada(Map<String, String> valoresForm, UnidadAdministrativa unitatAdministrativa) {

		if (valoresForm.get("item_nivell_1") != null
				&& !"".equals(valoresForm.get("item_nivell_1"))) {
			unitatAdministrativa.setNumfoto1(ParseUtil.parseInt(valoresForm
					.get("item_nivell_1")));
		}
		if (valoresForm.get("item_nivell_2") != null
				&& !"".equals(valoresForm.get("item_nivell_2"))) {
			unitatAdministrativa.setNumfoto2(ParseUtil.parseInt(valoresForm
					.get("item_nivell_2")));
		}
		if (valoresForm.get("item_nivell_3") != null
				&& !"".equals(valoresForm.get("item_nivell_3"))) {
			unitatAdministrativa.setNumfoto3(ParseUtil.parseInt(valoresForm
					.get("item_nivell_3")));
		}
		if (valoresForm.get("item_nivell_4") != null
				&& !"".equals(valoresForm.get("item_nivell_4"))) {
			unitatAdministrativa.setNumfoto4(ParseUtil.parseInt(valoresForm
					.get("item_nivell_4")));
		}

	}

	private void guardaLogotipos(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm, UnidadAdministrativa unitatAdministrativa) throws DelegateException
	{
		UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();

		//LogoHoritzontal
		FileItem fileLogoHoritzontal = ficherosForm.get("item_logo_horizontal");
		if ( fileLogoHoritzontal != null && fileLogoHoritzontal.getSize() > 0 ) {
			unitatAdministrativa.setLogoh(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogoh(), fileLogoHoritzontal));
		} else if (valoresForm.get("item_logo_horizontal_delete") != null && !"".equals(valoresForm.get("item_logo_horizontal_delete"))) {
			//borrar fichero si se solicita
			unitatAdministrativaDelegate.eliminarLogoHorizontal(unitatAdministrativa.getId());
			unitatAdministrativa.setLogoh(null);
		}
		//LogoVertical
		FileItem fileLogoVertical = ficherosForm.get("item_logo_vertical");
		if ( fileLogoVertical != null && fileLogoVertical.getSize() > 0 ) {
			unitatAdministrativa.setLogov(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogov(), fileLogoVertical));
		} else if (valoresForm.get("item_logo_vertical_delete") != null && !"".equals(valoresForm.get("item_logo_vertical_delete"))) {
			//borrar fichero si se solicita
			unitatAdministrativaDelegate.eliminarLogoVertical(unitatAdministrativa.getId());
			unitatAdministrativa.setLogov(null);
		}
		//LogoSalutacioHoritzontal
		FileItem fileLogoSalutacioHoritzontal = ficherosForm.get("item_logo_salutacio_horizontal");
		if ( fileLogoSalutacioHoritzontal != null && fileLogoSalutacioHoritzontal.getSize() > 0 ) {
			unitatAdministrativa.setLogos(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogos(), fileLogoSalutacioHoritzontal));
		} else if (valoresForm.get("item_logo_salutacio_horizontal_delete") != null && !"".equals(valoresForm.get("item_logo_salutacio_horizontal_delete"))) {
			//borrar fichero si se solicita
			unitatAdministrativaDelegate.eliminarLogoSalutacio(unitatAdministrativa.getId());
			unitatAdministrativa.setLogos(null);
		}
		//LogoSalutacioVertical
		FileItem fileLogoSalutacioVertical = ficherosForm.get("item_logo_salutacio_vertical");
		if ( fileLogoSalutacioVertical != null && fileLogoSalutacioVertical.getSize() > 0 ) {
			unitatAdministrativa.setLogot(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogot(), fileLogoSalutacioVertical));
		} else if (valoresForm.get("item_logo_salutacio_vertical_delete") != null && !"".equals(valoresForm.get("item_logo_salutacio_vertical_delete"))) {
			//borrar fichero si se solicita
			unitatAdministrativaDelegate.eliminarLogoTipos(unitatAdministrativa.getId());
			unitatAdministrativa.setLogot(null);
		}

	}

	private void guardaResponsable(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm, UnidadAdministrativa unitatAdministrativa) throws DelegateException {

		unitatAdministrativa.setResponsable(valoresForm.get("item_responsable"));
		unitatAdministrativa.setSexoResponsable(Integer.parseInt(valoresForm.get("item_responsable_sexe")));
		unitatAdministrativa.setResponsableEmail(valoresForm.get("item_responsable_email"));
		
		UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();

		//FotoPetita
		FileItem fileFotoPetita = ficherosForm.get("item_responsable_foto_petita");

		if ( fileFotoPetita != null && fileFotoPetita.getSize() > 0 ) {

			unitatAdministrativa.setFotop(UploadUtil.obtenerArchivo(unitatAdministrativa.getFotop(), fileFotoPetita));

		} else if (valoresForm.get("item_responsable_foto_petita_delete") != null && !"".equals(valoresForm.get("item_responsable_foto_petita_delete"))) {

			//borrar fichero si se solicita
			unitatAdministrativaDelegate.eliminarFotoPetita(unitatAdministrativa.getId());
			unitatAdministrativa.setFotop(null);

		}

		//FotoGran
		FileItem fileFotoGran = ficherosForm.get("item_responsable_foto_gran");

		if ( fileFotoGran != null && fileFotoGran.getSize() > 0 ) {

			unitatAdministrativa.setFotog( UploadUtil.obtenerArchivo( unitatAdministrativa.getFotog(), fileFotoGran ) );

		} else if (valoresForm.get("item_responsable_foto_gran_delete") != null && !"".equals(valoresForm.get("item_responsable_foto_gran_delete"))) {

			//borrar fichero si se solicita
			unitatAdministrativaDelegate.eliminarFotoGrande( unitatAdministrativa.getId() );
			unitatAdministrativa.setFotog(null);

		}

	}

	private EspacioTerritorial getEspacioTerritorialFormulario(Map<String, String> valoresForm) throws DelegateException {

		Long espaiTerritorialId = ParseUtil.parseLong( valoresForm.get("item_espai_territorial") );

		if ( espaiTerritorialId != null ) {

			EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
			EspacioTerritorial espacioTerritorial = espacioTerritorialDelegate.obtenerEspacioTerritorial(espaiTerritorialId);

			return espacioTerritorial;       

		} else {

			return null;

		}

	}

	private Map<String, Traduccion> getTraduccionesFormulario(Map<String, String> valoresForm) throws DelegateException {

		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		Map<String, Traduccion> traduccions = new HashMap<String, Traduccion>();

		for ( String lang : langs ) {

			TraduccionUA tUA = new TraduccionUA();

			tUA.setNombre( RolUtil.limpiaCadena( valoresForm.get( "item_nom_" +  lang ) ) );
			tUA.setPresentacion( RolUtil.limpiaCadena( valoresForm.get("item_presentacio_" + lang ) ) );
			tUA.setCvResponsable( RolUtil.limpiaCadena( valoresForm.get( "item_cvResponsable_" + lang ) ) );
			tUA.setAbreviatura( RolUtil.limpiaCadena( valoresForm.get( "item_abreviatura_" + lang) ) );
			tUA.setUrl( RolUtil.limpiaCadena( valoresForm.get( "item_url_" + lang ) ) );

			traduccions.put(lang, tUA);

		}

		return traduccions;

	}

	/**
	 * @param unitatAdministrativa
	 * @param unitatAdmPareId
	 * @throws DelegateException
	 */
	private void crearOActualizarUnitatAdministrativa(UnidadAdministrativa unitatAdministrativa, Long unitatAdmPareId) throws DelegateException	{

		UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();

		if ( unitatAdministrativa.getId() != null ) {

			unitatAdministrativaDelegate.actualizarUnidadAdministrativa(unitatAdministrativa, unitatAdmPareId);

		} else {

			Long id;
			if ( unitatAdmPareId != null )
				id = unitatAdministrativaDelegate.crearUnidadAdministrativa(unitatAdministrativa, unitatAdmPareId);

			else
				id = unitatAdministrativaDelegate.crearUnidadAdministrativaRaiz(unitatAdministrativa);


			unitatAdministrativa.setId(id);

		}

	}

	@RequestMapping(value = "/esborrar.do", method = POST)
	public @ResponseBody IdNomDTO esborrarUniAdm(HttpServletRequest request) {

		Long id = new Long(request.getParameter("id"));
		IdNomDTO resultatStatus = new IdNomDTO(); 

		try {

			UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();

			if ( !hayMicrositesUA(id) ) {
				UnidadAdministrativa unitatAdministrativa = unidadAdministrativaDelegate.consultarUnidadAdministrativa(id);

				// Validamos que se pueda eliminar la UA. Se podr� eliminar si no tiene elementos relacionados. A excepci�n de 
				// usuarios y edificios.
				boolean esBorrable = validarPermisosEliminacionUA(unitatAdministrativa,unidadAdministrativaDelegate);

				if ( !esBorrable )
					return new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));

				String errorElementosRelacionados = validarElementosRelacionados(unitatAdministrativa);

				if ( "".equals(errorElementosRelacionados ) ) {

					// Esborrar els edificis actuals
					EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
					Set<Edificio> edificiosActuales = edificioDelegate.listarEdificiosUnidad(unitatAdministrativa.getId());
					for (Edificio edificio : edificiosActuales)
						edificioDelegate.eliminarUnidad(unitatAdministrativa.getId(), edificio.getId());

					unidadAdministrativaDelegate.eliminarUaSinRelaciones(id);    

				} else {

					return new IdNomDTO(-1l, messageSource.getMessage(errorElementosRelacionados, null, request.getLocale()));

				}

			} else {
			
				return new IdNomDTO(id, messageSource.getMessage("unitatadm.esborrat.incorrecte.microsites", null, request.getLocale()));
			
			}

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);              
			}	

			log.error(ExceptionUtils.getStackTrace(dEx));

		}

		request.getSession().setAttribute("unidadAdministrativa", null);
		
		return new IdNomDTO(id, messageSource.getMessage("unitatadm.esborrat.correcte", null, request.getLocale()));	

	}

	@RequestMapping(value = "/llistatFitxesUA.do", method = POST)
	public @ResponseBody Map<String, Object> llistaFitxes(HttpServletRequest request) {

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		List<FichaDTO> llistaFitxesDTO = new ArrayList<FichaDTO>();
		Map<String, Object> resultats = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> tradMap = new HashMap<String, Object>();

		// Per defecte només carregarem les fitxes de la UA actual i de les seves UAs filles.
		boolean uaMeves = false;
		boolean uaFilles = false;

		UnidadAdministrativa ua = new UnidadAdministrativa();

		if ( request.getSession().getAttribute("unidadAdministrativa") == null) {

			resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
			resultats.put("id", -2);

			log.error("Error de sessi�n: Sessi�n expirada o no inciada");

			return resultats; // Si no hay unidad administrativa se devuelve vacío.

		} 

		ua = (UnidadAdministrativa) request.getSession().getAttribute("unidadAdministrativa");		

		try {

			Long codiFitxa = ParseUtil.parseLong(request.getParameter("idFicha"));
			paramMap.put("id", codiFitxa);

		} catch (NumberFormatException e) {

			// FIXME: avisar de error y cancelar consulta de datos.

		}

		try {

			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			String textes = request.getParameter("nombreFicha");

			if (textes != null && !"".equals(textes)) {
				textes = textes.toUpperCase();
				tradMap.put("titulo", textes);
			} else {
				tradMap.put("idioma", lang);
			}

			FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();

			//Información de paginación
			//    		String pagPag = request.getParameter("pagPag");		
			//    		String pagRes = request.getParameter("pagRes");
			//    		
			//    		if (pagPag == null) pagPag = String.valueOf(0); 
			//    		if (pagRes == null) pagRes = String.valueOf(10);

			//Información de paginación
			String pagPag = "0";
			String pagRes = "99999";

			resultadoBusqueda = fitxaDelegate.buscarFichas( paramMap, tradMap, ua, null, null,null, uaFilles, uaMeves, null, null, pagPag, pagRes, 0);           

			for (Ficha fitxa : castList(Ficha.class, resultadoBusqueda.getListaResultados()) ) {

				TraduccionFicha tfi = (TraduccionFicha) fitxa.getTraduccion(lang);
				llistaFitxesDTO.add(new FichaDTO(fitxa.getId(), 
						tfi == null ? null : tfi.getTitulo(), 
								DateUtils.formatDate(fitxa.getFechaPublicacion()), 
								DateUtils.formatDate(fitxa.getFechaCaducidad()),
								DateUtils.formatDate(fitxa.getFechaActualizacion()),
								!fitxa.isVisible()));
			}

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				// model.put("error", "permisos");
				log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
			} else {
				// model.put("error", "altres");
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}

		resultats.put("total", llistaFitxesDTO.size());
		//resultats.put("nodes", llistaFitxesDTO);
		resultats.put("fitxes", llistaFitxesDTO);

		return resultats;

	}


	@RequestMapping(value = "/llistatSeccions.do", method = POST)
	public @ResponseBody Map<String, Object> llistaSeccions(HttpServletRequest request)
	{
		Map<String,Object> resultats = new HashMap<String,Object>();

		try {
			String filtreNom = request.getParameter("nomSeccio").trim();
			SeccionDelegate secDel = DelegateUtil.getSeccionDelegate();

			List<Seccion> listaSecciones = secDel.listarSecciones();
			List<SeccionDTO> listaSeccionesDTO = new ArrayList<SeccionDTO>();

			for (Iterator iterator = listaSecciones.iterator(); iterator.hasNext();) {
				SeccionDTO seccionDTO = new SeccionDTO();
				Seccion seccion = (Seccion) iterator.next();

				String nomSeccio = ( (TraduccionSeccion) seccion.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto())).getNombre().replaceAll("\\<.*?>", "");

				if (toFormatComparacio(nomSeccio).contains(toFormatComparacio(filtreNom)) || "".equals(filtreNom)) {
					seccionDTO.setId(seccion.getId());
					seccionDTO.setNom(nomSeccio);
					listaSeccionesDTO.add(seccionDTO);
				}
			}

			Collections.sort(listaSeccionesDTO);

			resultats.put("total", listaSeccionesDTO.size());
			resultats.put("nodes", listaSeccionesDTO);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
				resultats.put("id", -1);
			} else {
				resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
				resultats.put("id", -2);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}

		return resultats;
	}


	@RequestMapping(value = "/reordenarUAs.do", method = POST) 
	public @ResponseBody IdNomDTO reordenarUAs(HttpServletRequest request) {

		IdNomDTO resultatStatus = new IdNomDTO();

		try {

			// Control de si se dan permisos extrar al rol SUPER.
			boolean accesoSuper = System.getProperty("es.caib.rolsac.permisosSuperAdicionales").equals("Y") && request.isUserInRole("sacsuper");
			boolean accesoOtros = request.isUserInRole("sacsystem") || request.isUserInRole("sacadmin");
			boolean acceso = (accesoSuper || accesoOtros) ? true : false;

			if (!acceso)
				return resultatStatus;

			Long id = new Long(request.getParameter("id")); 
			Integer ordenNuevo = new Integer(request.getParameter("orden"));
			Integer ordenAnterior = new Integer(request.getParameter("ordenAnterior"));

			String tmpId = request.getParameter("idPadre");
			Long idPadre = null;

			if ( !"".equals(tmpId) && StringUtils.isNumeric(tmpId) )
				idPadre = new Long(tmpId);

			UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
			unidadAdministrativaDelegate.reordenar(id, ordenNuevo, ordenAnterior, idPadre);

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		} catch (NumberFormatException nfEx) {

			resultatStatus.setId(-3l);
			log.error("Error: Id de UA no numèrica: " + ExceptionUtils.getStackTrace(nfEx));

		}

		return resultatStatus;

	}    

	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> listarUnidadesAdministrativas(HttpServletRequest request, HttpSession session)
	{
		List<UnidadAdministrativaDTO> listaUnidadesAdministrativas = new ArrayList<UnidadAdministrativaDTO>();
		Map<String, Object> resultats   = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> tradMap     = new HashMap<String, String>();
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

		String id = request.getParameter("codi");
		int resultadosDescartados  = 0;
		String espacioTerritorial = request.getParameter("espacio_territorial");
		String tratamiento = request.getParameter("tratamiento");
		if ( !"".equals(espacioTerritorial) && StringUtils.isNumeric(espacioTerritorial) ) {
			paramMap.put("espacioTerrit", espacioTerritorial);
		}
		if ( !"".equals(tratamiento) && StringUtils.isNumeric(tratamiento) ) {
			paramMap.put("tratamiento", tratamiento);
		}
		String lang;
		try {
			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
		} catch (DelegateException dEx) {
			log.error("Idioma por defecto no encontrado");
			lang = "ca";
		}

		if (StringUtils.isNotEmpty(id)) {
			UnidadAdministrativa uni;
			UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();
			try {
				uni = unitatDelegate.consultarUnidadAdministrativa(Long.parseLong(id));
				TraduccionUA traUA = (TraduccionUA)uni.getTraduccion(lang);
				resultats.put("total", 1);
				UnidadAdministrativaDTO dto = new UnidadAdministrativaDTO(uni.getId(), uni.getCodigoEstandar(), traUA.getNombre(), uni.getOrden());
				listaUnidadesAdministrativas.add(dto);
				resultats.put("nodes", listaUnidadesAdministrativas);
			} catch (NumberFormatException e) {
				// FIXME: aplicar tratamiento, seguramente igual al del bloque de la DelegateException que va justo después.
				e.printStackTrace();
			} catch (DelegateException e) {
				uni = null;
				resultats.put("total", 0);
			}

			return resultats;
		}

		try {
			String estat = request.getParameter("estat");
			Integer validacion = Integer.parseInt(estat);
			if (validacion > 0 && validacion < 4) {
				paramMap.put("validacion", validacion);
			}
		} catch (NumberFormatException e) {
			// FIXME: si no se va a tratar el error, al menos, avisar del mismo.
		}

		// Textes (en todos los campos todos los idiomas)
		String textes = request.getParameter("textes");
		if (textes != null && !"".equals(textes)) {
			textes = textes.toUpperCase();
			tradMap.put("nombre", textes);
			tradMap.put("abreviatura", textes);
			tradMap.put("url", textes);
			textes = HtmlUtils.eliminarTagsHtml(textes);	// Filtrar los tags de Html puestos por el tiny
			tradMap.put("presentacion", textes);
			tradMap.put("cvResponsable", textes);
		} else {
			tradMap.put("idioma", lang);
		}

		UnidadAdministrativa ua = null;

		if (getUAFromSession(session) != null) {
			ua = (UnidadAdministrativa) getUAFromSession(session);
		}

		boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
		boolean uaMeves = "1".equals(request.getParameter("uaMeves"));		

		Long materia = null;
		String materiaString = request.getParameter("materia");
		if (materiaString != null) {
			Scanner scanner = new Scanner(materiaString);
			if (scanner.hasNextLong()) {
				materia = scanner.nextLong();
			}
		}

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");

		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {
			resultadoBusqueda = uaDelegate.buscadorUnidadesAdministrativas(paramMap, tradMap, (ua == null ? null : ua.getId()), lang, uaFilles, uaMeves, materia, pagPag, pagRes);
			for (UnidadAdministrativa uniAdm : castList(UnidadAdministrativa.class, resultadoBusqueda.getListaResultados())) {
				if (lang.equals(uniAdm.getIdioma())) {
					UnidadAdministrativaDTO dto = new UnidadAdministrativaDTO(uniAdm.getId(), uniAdm.getCodigoEstandar(), uniAdm.getNombre(), uniAdm.getOrden());
					listaUnidadesAdministrativas.add(dto);
				} else {
					resultadosDescartados++;
				}
			}

		} catch (DelegateException dEx) {
			resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
			resultats.put("id", -2);
			log.error(ExceptionUtils.getStackTrace(dEx));
		} 
		//Total de registros
		resultats.put("total", resultadoBusqueda.getTotalResultados() - resultadosDescartados);
		resultats.put("nodes", listaUnidadesAdministrativas);

		return resultats;
	}

	/**
	 * Método que comprueba si hay microsites para una Unidad Orgánica
	 * @param idua identificador de la unidad organica
	 * @return boolean
	 */
	// TODO amartin: averiguar hasta cuándo se ha de devolver false y dejar constancia de la explicación en el código.
	private boolean hayMicrositesUA(Long idua) {
		//    	boolean retorno=false;
		//    	try {
		//	    	String value = System.getProperty("es.caib.rolsac.microsites");
		//    		return "Y".equals(value) ? tieneMicrosites(idua) : false;    	    		
		//		} catch (Exception e) {
		//			log.error("Error al determinar si la ua " + idua + " tiene microsites: " + ExceptionUtils.getStackTrace(e));
		//			retorno = true; //para evitar inconsistencias
		//		}
		return false;
	}

	/**
	 * Descripción: Método que valida si la UA puede ser eliminada.
	 * 
	 * @author Indra
	 * @param  ua Unidad administrativas
	 * @param  unidadDelegate  Delegado de la Unidad administrativa
	 * @return Devuelve true o false en funci�n de si la unidad administrativa puede ser o no borrada
	 */
	private boolean validarPermisosEliminacionUA(UnidadAdministrativa ua, UnidadAdministrativaDelegate unidadDelegate) {

		// Comprobar si el usuario puede eliminar UA.
		try {    		
			Long id = ua.isRaiz() ? ua.getId() : ua.getPadre().getId();    		
			return unidadDelegate.autorizarEliminarUA(id);    		    		
		} catch(Exception e) {
			return false;  
		}

	}

	private String validarElementosRelacionados(UnidadAdministrativa ua) {

		boolean boolProcedIsEmpty =ua.getProcedimientos().isEmpty();    	
		String ids = "";

		// Compronbar si la UA tiene elementos relacionados.
		if (!ua.getHijos().isEmpty())
			return "unitatadm.esborrat.incorrecte.uafilles";
		else if(!ua.getFichasUA().isEmpty())
			return "unitatadm.esborrat.incorrecte.fitxes";
		else if(!ua.getPersonal().isEmpty())       
			return "unitatadm.esborrat.incorrecte.personal";
		else if(!ua.getUnidadesMaterias().isEmpty())        	
			return "unitatadm.esborrat.incorrecte.materies";
		else if(!boolProcedIsEmpty || !ua.getNormativas().isEmpty() ) {

			List <Long> idsList=new ArrayList<Long>();

			if(!boolProcedIsEmpty)
				idsList = ua.getIdsProcedimientos();
			else
				idsList = ua.getIdsNormativas();

			Iterator<Long> iter = idsList.iterator();
			int count = 0;

			while ( iter.hasNext() ) {

				Long id = iter.next();

				if ( ids.equals("") ) {
					ids = id.toString();
					count++;
				} else {
					if ( (count % 10) == 0 )
						ids = ids + ",<BR> " + id.toString();
					else
						ids = ids + ", " + id.toString();

					count++;
				}

			}

			if (!boolProcedIsEmpty)
				return "unitatadm.esborrat.incorrecte.procediments";
			else
				return "unitatadm.esborrat.incorrecte.normatives"; 

		}

		//return errores;
		return "";

	}


	/**
	 * Solicita las fichas relacionadas con una UA y una sección.
	 */
	@RequestMapping(value = "/obtenirFitxesUASeccio.do", method = POST)
	public @ResponseBody Map<String, Object> llistaFitxesUASeccio(Long idSeccion, Integer pagPag, Integer pagRes, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		if ( request.getSession().getAttribute("unidadAdministrativa") == null || !this.validarParametro(idSeccion) ) { // Si no hay unidad administrativa se devuelve vacío

			this.mostrarErrorOperacionFallida(resultats, request.getLocale(), OPERACION_FALLIDA);

		} else {

			UnidadAdministrativa ua = new UnidadAdministrativa();
			ua = (UnidadAdministrativa) request.getSession().getAttribute("unidadAdministrativa");	


			try {

				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

				PaginacionCriteria paginacion = new PaginacionCriteria();
				paginacion.setPagPag(pagPag);
				paginacion.setPagRes(pagRes);

				List<FichaDTO> fichas = uaDelegate.listarFichasSeccionUA(ua.getId(), idSeccion, idioma, paginacion);				

				resultats.put("fitxes", fichas);
				resultats.put("totalRegistros", fichas.size());

			} catch (DelegateException e) {

				this.mostrarErrorOperacionFallida( resultats, request.getLocale(), ExceptionUtils.getStackTrace(e) );

			}


		}

		return resultats;

	}


	/**
	 * Método que recibe petición AJAX de guardado del estado de las fichasUA de la UA.
	 * @param request
	 * @throws DelegateException 
	 * @return
	 */
	@RequestMapping(value = "/guardarFitxesUASeccio.do", method = POST)
	public @ResponseBody Map<String, Object> guardarFitxesUASeccio(Long idUA, Long idSeccion, String listaFichas, HttpServletRequest request) throws DelegateException {

		Map<String, Object> resultats = new HashMap<String, Object>();

		if ( idUA == null || idSeccion == null || !this.validarParametro(listaFichas) ) {

			String mensaje = "Falta alguno de los parámetros para completar el guardado de las fichas de la sección";
			this.mostrarErrorOperacionFallida(resultats, request.getLocale(), mensaje);

			return resultats;

		}
		
		try {

			List<FichaDTO> fichas = this.castJsonListToHashTable(listaFichas);
			
			if (fichas.size() == 0 ){
				//Busco las fichas que habia anteriormente y si es mayor que 0 entonces no dejamos borrar
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				
				List<FichaDTO> fichasOld = uaDelegate.listarFichasSeccionUASinPaginacion(idUA, idSeccion, idioma);
				
				if (fichasOld.size()> 0){
					resultats.put( "error", messageSource.getMessage("error.seccio", null, request.getLocale() ) );
					resultats.put("id", -3);
					return resultats;
				}
			}
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			uaDelegate.actualizaFichasSeccionUA(idUA, idSeccion, fichas);

		} catch (DelegateException e) {

			if (e.isSecurityException()) {

				this.mostrarErrorPermisos(resultats, request.getLocale());

			} else {

				this.mostrarErrorOperacionFallida( resultats, request.getLocale(), ExceptionUtils.getStackTrace(e) );

			}

		}

		return resultats;

	}

	/**
	 * Retorna una cadena que canvia les vocals amb accent o dièresi 
	 * per vocals sense aquestes (emprat per cercar registres coincidents 
	 * de seccions).
	 * 
	 * @param cadena
	 * @return String
	 */
	private String toFormatComparacio( String cadena ) {
		return cadena.toLowerCase().replaceAll("[���]", "a")
				.replaceAll("[���]", "e")
				.replaceAll("[���]", "i")
				.replaceAll("[���]", "o")
				.replaceAll("[���]", "u");		
	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
	{
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			TraduccionUA traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			traduccions = traductor.translateTiny(traduccioOrigen, idiomaOrigenTraductor);

			resultats.put("traduccions", traduccions);

		} catch (DelegateException dEx) {
			logException(log, dEx);
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
		} catch (NullPointerException npe) {
			log.error("CatalegProcedimentBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("CatalegProcedimentBackController.traduir: Error en al traducir procedimiento: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}

		return resultats;
	}


	private void mostrarErrorOperacionFallida(Map<String, Object> resultats, Locale locale, String mensaje) {

		resultats.put( "error", messageSource.getMessage("error.operacio_fallida", null, locale ) );
		resultats.put("id", -2);
		log.error(mensaje);
	}

	private void mostrarErrorPermisos(Map<String, Object> resultats, Locale locale)  {

		resultats.put( "error", messageSource.getMessage("error.permisos", null, locale ) );
		resultats.put("id", -1);

	}


	/**
	 * @param parametro	Propiedad de tipo Long a evaluar
	 * @return Devuelve <code>false</code>  si el parámetro es nulo o menor de 1
	 */
	private boolean validarParametro(Long parametro) {

		boolean b = true;
		if ( parametro == null || parametro < 1 )
			b = false;

		return b;

	}


	private boolean validarParametro(String parametro) {

		boolean b = true;
		if ( parametro == null || "".equals(parametro) )
			b = false;

		return b;

	}

	private List<FichaDTO> castJsonListToHashTable(String jsonList) {

		List<FichaDTO> lista = Collections.EMPTY_LIST;

		try {

			lista = Arrays.asList( new ObjectMapper().readValue(jsonList, FichaDTO[].class) );

		} catch (JsonParseException e) {

			log.error(ExceptionUtils.getStackTrace(e));

		} catch (JsonMappingException e) {

			log.error(ExceptionUtils.getStackTrace(e));

		} catch (IOException e) {

			log.error(ExceptionUtils.getStackTrace(e));

		}

		return lista;

	}


	private TraduccionUA getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
	{
		TraduccionUA traduccioOrigen = new TraduccionUA();

		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}

		if (StringUtils.isNotEmpty(request.getParameter("item_presentacio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setPresentacion(request.getParameter("item_presentacio_" + idiomaOrigenTraductor));
		}

		if (StringUtils.isNotEmpty(request.getParameter("item_cvResponsable_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setCvResponsable(request.getParameter("item_cvResponsable_" + idiomaOrigenTraductor));
		}

		if (StringUtils.isNotEmpty(request.getParameter("item_abreviatura_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setAbreviatura(request.getParameter("item_abreviatura_" + idiomaOrigenTraductor));
		}

		return traduccioOrigen;
	}


	/**
	 * Método que recibe petición AJAX de consultar si la ficha no tiene más secciones, entonces se decide si se puede o no borrar
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fitxaBorrable.do", method = POST)
	public @ResponseBody Map<String, Object> fitxaBorrable(HttpServletRequest request, Long idFitxa) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			resultats.put("num", DelegateUtil.getFichaDelegate().listFichasUA(idFitxa).size());

		} catch (DelegateException e) {

			resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
			resultats.put("id", -2);
			log.error(ExceptionUtils.getStackTrace(e));

		}

		return resultats;

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarMateriasRelacionadas.do")
	public @ResponseBody IdNomDTO guardarMateriasRelacionadas(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
			
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			UnidadMateriaDelegate uaMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
			
			UnidadAdministrativa ua = uaDelegate.consultarUnidadAdministrativaSinFichas(id);
			Set<UnidadMateria> setUnidadesMateriaABorrar = ua.getUnidadesMaterias();
			
			// Generamos una lista con los IDs de las UnidadMateria a borrar.
			List<Long> listaIdsUnidadesMateriaABorrar = new ArrayList<Long>();
			for (UnidadMateria um : setUnidadesMateriaABorrar)
				listaIdsUnidadesMateriaABorrar.add(um.getId());
			
			// Creamos y obtenemos las actuales.
			List<UnidadMateria> unidadesMateriaNuevas = new ArrayList<UnidadMateria>();
			
			if ( elementos != null ) {
								
				for ( int i = 0; i < elementos.length; i++ ) {
					
					if ( elementos[i] != null ) {
					
						UnidadMateria uam = new UnidadMateria();
						Materia materia = materiaDelegate.obtenerMateria(elementos[i]);
						
						uam.setUnidad(ua);
						uam.setMateria(materia);
												
						unidadesMateriaNuevas.add(uam);
					
					}
					
				}
				
			}
			uaMateriaDelegate.grabarUnidadesMateria(unidadesMateriaNuevas, listaIdsUnidadesMateriaABorrar);
			
			// Las asociamos a la UA.
			ua.setUnidadesMaterias(new HashSet<UnidadMateria>(unidadesMateriaNuevas));
			
			crearOActualizarUnitatAdministrativa(
				ua, 
				ua.getPadre() != null ? ua.getPadre().getId() : null
			);
			
			String ok = messageSource.getMessage("unitatadm.guardat.materies.correcte", null, request.getLocale());
			result = new IdNomDTO(ua.getId(), ok);            

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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarEdificiosRelacionados.do")
	public @ResponseBody IdNomDTO guardarEdificiosRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
			
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			UnidadAdministrativa ua = uaDelegate.consultarUnidadAdministrativaSinFichas(id);
			
			List<Long> idsNuevosEdificios = (elementos != null) ? Arrays.asList(elementos) : new ArrayList<Long>();
			uaDelegate.actualizarEdificiosUnidadAdministrativa(ua, idsNuevosEdificios);
						
			String ok = messageSource.getMessage("unitatadm.guardat.edificis.correcte", null, request.getLocale());
			result = new IdNomDTO(ua.getId(), ok);            

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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarUsuariosRelacionados.do")
	public @ResponseBody IdNomDTO guardarUsuariosRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
			
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			UnidadAdministrativa ua = uaDelegate.consultarUnidadAdministrativaSinFichas(id);
			
			List<Long> idsNuevosUsuarios = (elementos != null) ? Arrays.asList(elementos) : new ArrayList<Long>();
			uaDelegate.actualizarUsuariosUnidadAdministrativa(ua, idsNuevosUsuarios);
						
			String ok = messageSource.getMessage("unitatadm.guardat.usuaris.correcte", null, request.getLocale());
			result = new IdNomDTO(ua.getId(), ok);            

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
