package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Procedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
import org.ibit.rol.sac.model.TraduccionExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.ListadoModuloTramiteDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoNormativaDTO;
import org.ibit.rol.sac.persistence.delegate.CatalegDocumentsDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ExcepcioDocumentacioDelegate;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.GuardadoAjaxUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.LlistatUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;
import es.indra.rol.sac.integracion.traductor.TraductorException;

@SuppressWarnings("deprecation") // amartin: debido a org.ibit.rol.sac.model.Documento

@Controller
@RequestMapping("/catalegProcediments/")
public class CatalegProcedimentsBackController extends PantallaBaseController {

	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.procediment.url";
	private static Log log = LogFactory.getLog(CatalegProcedimentsBackController.class);

	@RequestMapping(value = "/catalegProcediments.do")
	public String pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request) {

		String lang;
		
		try {
			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
		} catch (DelegateException dEx) {
			log.error("Error al recuperar el idioma por defecto.");
			lang = "ca";
		}

		if (estemEnUnitatAdministrativa(session)) {
		    crearModelComplert_pantalla(model, session, request, lang);
		} else {
		    crearModelSencill_pantalla(model, session, request, lang);
		}

		loadIndexModel (model, request);
		
		return "index";
		
	}

	private boolean estemEnUnitatAdministrativa(HttpSession session) {
		return null != getUAFromSession(session);
	}

	private void crearModelComplert_pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request, String lang) {

		crearModelSencill_pantalla(model, session, request, lang);
		model.put("idUA", getUAFromSession(session).getId());
		model.put("nomUA", getUAFromSession(session).getNombreUnidadAdministrativa(lang));

	}

	private void crearModelSencill_pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request, String lang) {

		try {

			model.put("menu", 0);
			model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
			model.put("submenu_seleccionado", 2);
			model.put("escriptori", "pantalles/catalegProcediments.jsp");
			model.put("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));
			model.put("llistaMateries", LlistatUtil.llistarMaterias(lang));
			model.put("llistaPublicsObjectiu", LlistatUtil.llistarPublicObjectius(lang));
			model.put("families", LlistatUtil.llistarFamilias(lang));
			model.put("iniciacions", LlistatUtil.llistarIniciacions(lang));
			model.put("excepcions", llistarExcepcionsDocumentacio(lang));
			model.put("cataleg", llistarCatalegDocuments(lang));

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				logException(log, dEx);
			}
			
		}
		
	}

	private List<IdNomDTO> llistarExcepcionsDocumentacio(String lang) throws DelegateException {
		
        ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
        List<IdNomDTO> excepcioObjDTOList = new ArrayList<IdNomDTO>();
        List<ExcepcioDocumentacio> llistaExcepcionsDocumentacio = excepcioDelegate.llistarExcepcioDocumentacio();
        TraduccionExcepcioDocumentacio ted;
        
        for (ExcepcioDocumentacio excepcio : llistaExcepcionsDocumentacio ) {
            ted = (TraduccionExcepcioDocumentacio) excepcio.getTraduccion(lang);
            excepcioObjDTOList.add(new IdNomDTO(excepcio.getId(), ted.getNombre()));
        }
        
        return excepcioObjDTOList;
        
    }

	private List<IdNomDTO> llistarCatalegDocuments(String lang) throws DelegateException {

        CatalegDocumentsDelegate catdocDelegate = DelegateUtil.getCatalegDocumentsDelegate();
        List<IdNomDTO> catalegObjDTOList = new ArrayList<IdNomDTO>();
        List<CatalegDocuments> llistaCatalegDocuments = catdocDelegate.llistarCatalegDocuments();
        TraduccionCatalegDocuments tcd;
        
        for (CatalegDocuments document : llistaCatalegDocuments ) {
            tcd = (TraduccionCatalegDocuments) document.getTraduccion(lang);
            catalegObjDTOList.add(new IdNomDTO(document.getId(), tcd.getNombre()));
        }
        
        return catalegObjDTOList;
        
    }


	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistat(String criteria, HttpServletRequest request, HttpSession session) {

		Map<String, Object> resultats = new HashMap<String, Object>();
		BuscadorProcedimientoCriteria buscadorCriteria = this.jsonToBuscadorProcedimientoCriteria(criteria);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		List<ProcedimientoLocalDTO> llistaProcedimientoLocalDTO = new ArrayList<ProcedimientoLocalDTO>();

		if (getUAFromSession(session) != null && buscadorCriteria != null) {
			
			try {
				
				UnidadAdministrativa ua = (UnidadAdministrativa) getUAFromSession(session);
				buscadorCriteria.setUnidadAdministrativa(ua);

				ProcedimientoDelegate procedimientosDelegate = DelegateUtil.getProcedimientoDelegate();
				resultadoBusqueda = procedimientosDelegate.buscadorProcedimientos(buscadorCriteria);
				llistaProcedimientoLocalDTO.addAll(convertirProcLocales(resultadoBusqueda, request));

			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
				    resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
				} else {
				    resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
				}
				
			}
			
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaProcedimientoLocalDTO);

		return resultats;
		
	}

	/** Método que se encarga de convertir un String en formato json a una instancia de BuscadorProcedimientoCriteria */
	private BuscadorProcedimientoCriteria jsonToBuscadorProcedimientoCriteria (String criteria) {

		BuscadorProcedimientoCriteria buscadorCriteria = null;

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

			buscadorCriteria = mapper.readValue(criteria, BuscadorProcedimientoCriteria.class);
			buscadorCriteria.setLocale( DelegateUtil.getIdiomaDelegate().lenguajePorDefecto() ); 

		} catch (JsonParseException e) {
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (DelegateException e) {
			log.error(e.getMessage());
		} 

		return buscadorCriteria;
		
	}

	/*
	 * Función para convertir a procedimientos locales los resultados
	 */
	private List<ProcedimientoLocalDTO> convertirProcLocales(ResultadoBusqueda resultadoBusqueda, HttpServletRequest request) {

		List<ProcedimientoLocalDTO> llistaProcedimientoLocalDTO = new ArrayList<ProcedimientoLocalDTO>();
		for (ProcedimientoLocal pl : castList(ProcedimientoLocal.class, resultadoBusqueda.getListaResultados())) {
			
			ProcedimientoLocalDTO procLocalDTO = new ProcedimientoLocalDTO(
			    pl.getId(),
			    pl.getNombreProcedimiento(),
			    pl.isVisible(),
			    DateUtils.formatDate(pl.getFechaActualizacion()),
			    pl.getNombreFamilia()
			);

			llistaProcedimientoLocalDTO.add(procLocalDTO);
			
		}
		
		return llistaProcedimientoLocalDTO;
		
	}


	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(Long id, HttpSession session, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
		    IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			
			String lang = idiomaDelegate.lenguajePorDefecto();

			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal proc = procedimientoDelegate.obtenerProcedimientoNewBack(id);

			resultats.put("item_id", proc.getId());
			resultats.put("item_codigo_pro", proc.getSignatura());			
			resultats.put("item_estat", proc.getValidacion());						
			resultats.put("item_data_actualitzacio", DateUtils.formatDate(proc.getFechaActualizacion()));
			resultats.put("item_data_publicacio", DateUtils.formatDate(proc.getFechaPublicacion()));
			resultats.put("item_data_caducitat", DateUtils.formatDate(proc.getFechaCaducidad()));
			resultats.put("item_codi", proc.getSignatura());
			resultats.put("item_tramite", proc.getTramite());
			resultats.put("item_url", proc.getUrl());
			resultats.put("item_responsable", proc.getResponsable());
			resultats.put("item_finestreta_unica", proc.esVentanillaUnica());
			resultats.put("item_notes", proc.getInfo());
			resultats.put("item_fi_vida_administrativa", (proc.getIndicador() == null || "0".equals(proc.getIndicador())) ? false : true);           
            resultats.put("item_taxa", (proc.getTaxa() == null || "0".equals(proc.getTaxa())) ? false : true);
            resultats.put("item_finestreta_unica", (proc.getVentanillaUnica() == null || "0".equals(proc.getVentanillaUnica())) ? false : true);

			if (proc.getIniciacion() != null) {
				resultats.put("item_iniciacio", proc.getIniciacion().getId());
			}

			if (proc.getFamilia() != null) {
                resultats.put("item_familia", proc.getFamilia().getId());
            }

            if (proc.getVersion() != null) {
                resultats.put("item_version", proc.getVersion());
            }
            
			if (proc.getUnidadAdministrativa() != null) {
				UnidadAdministrativa ua = proc.getUnidadAdministrativa();
				resultats.put("item_organ_responsable_id", ua.getId());
				resultats.put("item_organ_responsable_nom", ua.getNombreUnidadAdministrativa(lang));
			}

			if (proc.getOrganResolutori() != null) {
				UnidadAdministrativa ua = proc.getOrganResolutori();
				resultats.put("item_organ_id", ua.getId());
				resultats.put("item_organ_nom", ua.getNombreUnidadAdministrativa(lang));
			}

			// Obtención de listado de posibles hechos vitales del procedimiento
			Set<PublicoObjetivo> listaPublicosObjetivos =  proc.getPublicosObjetivo();
			if (!listaPublicosObjetivos.isEmpty()) {
				resultats.put("listadoHechosVitales", LlistatUtil.llistarHechosVitales(listaPublicosObjetivos, lang));
			} else {
				resultats.put("listadoHechosVitales", Collections.EMPTY_SET);
			}

			recuperaIdiomas(resultats, proc, lang);         // Recuperar los procedimientos según los idiomas
            recuperaTramites(resultats, proc, request);     // Recuperar los trámites relacionados de un procedimiento
            recuperaPO(resultats, proc, lang);              // Recuperar los públicos objetivos asociados a un procedimiento

		} catch (DelegateException dEx) {
			
			logException(log, dEx);
			
			if ( dEx.isSecurityException() ){
				resultats.put( "error", messageSource.getMessage( "error.permisos", null, request.getLocale() ) );
			} else {
				resultats.put( "error", messageSource.getMessage( "error.altres", null, request.getLocale() ) );
			}
			
		}

		return resultats;
		
	}
	
	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal proc = procedimientoDelegate.obtenerProcedimientoNewBack(id);
			
			//  Pasamos el Set de materias relacionados a un List.
			List<Materia> listaMaterias = new ArrayList<Materia>(proc.getMaterias());
            resultats.put("materies", CargaModulosLateralesUtil.recuperaMateriasRelacionadas(listaMaterias, id, lang, false));
            
            // Pasamos el Set de HHVV relacionados a un List.
            List<HechoVitalProcedimiento> listaHechosVitales = new ArrayList<HechoVitalProcedimiento>(proc.getHechosVitalesProcedimientos());
            resultats.put("fetsVitals", CargaModulosLateralesUtil.recuperaHechosVitalesRelacionados(listaHechosVitales, HechoVitalProcedimiento.class, id, lang, true));
            
			// Documentos relacionados.
			List<Documento> listaDocumentos = proc.getDocumentos();
			resultats.put("documents", CargaModulosLateralesUtil.recuperaDocumentosRelacionados(listaDocumentos, id, idiomas, true));
           
			recuperaNormativas(resultats, proc, lang, id);      // Recuperar las normativas asociadas a un procedimiento
			
		} catch (DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}

	/*
	 * Función que recupera el contenido de los procedimientos según el idioma.
	 */
	private void recuperaIdiomas(Map<String, Object> resultats, ProcedimientoLocal proc, String langDefault) throws DelegateException {

	    List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
	    
        for (String lang : langs) {
            if (proc.getTraduccion(lang) != null) {
                resultats.put(lang, (TraduccionProcedimientoLocal) proc.getTraduccion(lang));
            } else {
                if (proc.getTraduccion(langDefault) != null) {
                    resultats.put(lang, (TraduccionProcedimientoLocal) proc.getTraduccion(langDefault));
                } else {
                    resultats.put(lang, new TraduccionProcedimientoLocal());
                }
            }
        }
        
	}

	/*
	 * Función para recuperar los trámites de un procedimiento
	 */
	private void recuperaTramites(Map<String, Object> resultats, ProcedimientoLocal proc, HttpServletRequest request) throws DelegateException {

		List<ListadoModuloTramiteDTO> listaTramitesDTO = null;
		
		if (proc.getTramites() != null && proc.getTramites().size() != 0) {
			
			listaTramitesDTO = new ArrayList<ListadoModuloTramiteDTO>();
			String lenguajePorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			for (Tramite tramite : proc.getTramites()) {
				if (tramite != null) {
					String nombreTramite;
					if (tramite.getTraduccion(lenguajePorDefecto) != null) {
					    nombreTramite = ((TraduccionTramite) tramite.getTraduccion(lenguajePorDefecto)).getNombre();
					} else {
					    nombreTramite = "void";
					}

					listaTramitesDTO.add(new ListadoModuloTramiteDTO(tramite.getId(), nombreTramite, tramite.getFase()));
				}
			}
			
		}

		resultats.put("tramites", listaTramitesDTO);
		
	}

	/*
	 * Función para recuperar los públicos objeticos de un procedimiento
	 */
	private void recuperaPO(Map<String, Object> resultats, ProcedimientoLocal proc, String lang) {

		if (proc.getPublicosObjetivo() != null) {
			
			List<IdNomDTO> llistaPublicsDTO = new ArrayList<IdNomDTO>();
			
			for (PublicoObjetivo pob : proc.getPublicosObjetivo()) {
				TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo)pob.getTraduccion(lang);
				llistaPublicsDTO.add(new IdNomDTO(pob.getId(), tpob == null ? "" : tpob.getTitulo()));
			}
			
			resultats.put("publicsObjectiu", llistaPublicsDTO);

		} else {
			
			resultats.put("publicsObjectiu", null);
			
		}
		
	}

	/*
	 * Función para recuperar las materias de un procedimiento
	 */
	private void recuperaNormativas(Map<String, Object> resultats, ProcedimientoLocal proc, String lang, Long idProcedimiento) {

		if (proc.getNormativas() != null) {
			
			Map<String, String> map;
			List<Map<String, String>> llistaNormatives = new ArrayList<Map<String, String>>();
			TraduccionNormativa traNor;
			String titulo;

			for (Normativa normativa : proc.getNormativas()) {
				
				traNor = (TraduccionNormativa)normativa.getTraduccion(lang);
				
				// Retirar posible enlace incrustado en titulo
				titulo = (traNor == null) ? "" : HtmlUtils.obtenerTituloDeEnlaceHtml(traNor.getTitulo());
				map = new HashMap<String, String>();
				map.put("id", normativa.getId().toString());
				map.put("nombre", titulo);
				map.put("idMainItem", idProcedimiento.toString());
				map.put("idRelatedItem", normativa.getId().toString());
				
				llistaNormatives.add(map);
				
			}
			
			resultats.put("normatives", llistaNormatives);

		} else {
			
			resultats.put("normatives", null);
			
		}
		
	}

	@RequestMapping(value = "/esborrarProcediment.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {

		IdNomDTO resultatStatus = new IdNomDTO();

		try {
			
			Long id = new Long(request.getParameter("id"));
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			procedimientoDelegate.borrarProcedimiento(id);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}
			
		}

		return resultatStatus;
		
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpSession session, HttpServletRequest request) {
		
		IdNomDTO result = null;
		String error = null;

		try {
			
			if (request.getParameter("publicsObjectiu") == null || request.getParameter("publicsObjectiu").equals("")) {
				error = messageSource.getMessage("proc.error.falta.public", null, request.getLocale());
				return result = new IdNomDTO(-3l, error);
			}

			ProcedimientoDelegate procedimentDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal procediment = new ProcedimientoLocal();
			ProcedimientoLocal procedimentOld;

			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				procedimentOld = procedimentDelegate.obtenerProcedimientoNewBack(id);
				edicion = true;
			} catch (NumberFormatException nfe) {
				procedimentOld = null;
				edicion = false;
			}

			procediment = guardarVersion(request, procediment, procedimentOld, error);			// Versión
			procediment = guardarPublicoObjetivo(request, procediment, procedimentOld);			// Procesar Público Objectivo
			procediment = guardarIdioma(request, procediment, procedimentOld);       			// Idiomas
			procediment = guardarValidacion(request, procediment, procedimentOld, error);		// Validación
			procediment = guardarFechaPublicacion(request, procediment);						// Fecha Publicación
			procediment = guardarFechaCaducidad(request, procediment);							// Fecha Caducidad
			procediment = guardarIniciacion(request, procediment, error);				    	// Iniciación
			procediment = guardarFamilia(request, procediment, error);							// Familia
			procediment = guardarOrganResolutori(request, procediment, error);					// Organ Resolutori
			procediment = guardarUnidadAdministrativa(request, procediment, error);             // Unidad Administrativa			
			
			procediment.setResponsable(request.getParameter("item_responsable"));				// Responsable
			procediment.setSignatura(request.getParameter("item_codigo_pro"));					// Signatura
			procediment.setInfo(request.getParameter("item_notes"));							// Info
			procediment.setTaxa("on".equalsIgnoreCase(request.getParameter("item_taxa")) ? "1" : "0");							// Taxa
			procediment.setIndicador("on".equalsIgnoreCase(request.getParameter("item_fi_vida_administrativa")) ? "1" : "0");	// Indicador
			procediment.setVentanillaUnica("on".equalsIgnoreCase(request.getParameter("item_finestreta_unica")) ? "1" : "0");	// Ventanilla Única

			List<Tramite> listaTramitesParaBorrar = null;
			List<Long> listaIdsTramitesParaActualizar = null;

			if (edicion) {
				
				procediment = guardarProcedimientoAntiguo(procediment, procedimentOld);		// Si estamos guardando un procedimiento ya existente en vez de uno nuevo
				
				// Obtenemos las listas necesarias para el tratamiento de los trámites.
				listaTramitesParaBorrar = new ArrayList<Tramite>();
				listaIdsTramitesParaActualizar = new ArrayList<Long>();
				
				// Dentro de este método se asignan los nuevos trámites asociados al procedimiento
				// y se rellenan las listas listaTramitesParaBorrar y listaIdsTramitesParaActualizar.
				procediment = guardarTramites(procediment, procedimentOld, request, listaTramitesParaBorrar, listaIdsTramitesParaActualizar);
				
				// Comprobamos cambio de estado del procedimiento a estado "público". 
				// Si es así, comprobar lo siguiente: se avisará de si falta un trámite de inicio o de si se posee más de uno de ese tipo.
				if (Procedimiento.ESTADO_PUBLICACION_PUBLICA.equals(procediment.getValidacion()) 
						&& !procedimentOld.getValidacion().equals(procediment.getValidacion())) {
					
					// Comprobar que haya al menos un trámite de inicio y comprobar si hay más de un trámite de inicio.
					boolean hayTramiteDeIniciacion = false;
					int numTramitesIniciacion = 0;
					
					for (Long id : listaIdsTramitesParaActualizar) {
						Tramite tramite = DelegateUtil.getTramiteDelegate().obtenerTramite(id);
						if (tramite.getFase() == Tramite.INICIACION) {
							hayTramiteDeIniciacion = true;
							numTramitesIniciacion++;
						}
					}
					
					if (!hayTramiteDeIniciacion)
						throw new IllegalStateException("error_no_tramite_iniciacion");
					
					if (numTramitesIniciacion > 1)
						throw new IllegalStateException("error_mas_de_un_tramite_de_iniciacion");

				}
				
			}

			Long procId = guardarGrabar(procediment, listaTramitesParaBorrar, listaIdsTramitesParaActualizar);

			String ok = messageSource.getMessage("proc.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(procId, ok);

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				logException(log, dEx);
				
			}

		} catch (NumberFormatException nfe) {
			
			error = nfe.getMessage();
			result = new IdNomDTO(-3l, error);

		} catch (ParseException pe) {
			
			error = pe.getMessage();
			result = new IdNomDTO(-4l, error);
			
		} catch (IllegalStateException ise) {
			
			if ("error_no_tramite_iniciacion".equals(ise.getMessage()))
				error = messageSource.getMessage("error.procediment_sense_tramit_iniciacio", null, request.getLocale());
			
			else if ("error_mas_de_un_tramite_de_iniciacion".equals(ise.getMessage()))
				error = messageSource.getMessage("error.procediment_multiples_tramits_iniciacio", null, request.getLocale());
			
			else
				error = ise.getMessage();
			
			result = new IdNomDTO(-5l, error);
			
		}

		return result;
		
	}

	private ProcedimientoLocal guardarTramites(ProcedimientoLocal procedimiento, ProcedimientoLocal procedimientoOld, 
			HttpServletRequest request, List<Tramite> listaTramitesParaEliminar, List<Long> listaIdsTramitesParaActualizar) {
		
		List<String> idsNuevosTramites = Arrays.asList(request.getParameter("tramitsProcediment").split(","));
		List<Tramite> tramitesParaCrear = null;
		
		if (idsNuevosTramites.size() == 1 && idsNuevosTramites.get(0).equals("")) {
	    	
	        // La lista esta vacía y por tanto se pueden borrar todos los anteriores.
	        listaTramitesParaEliminar.addAll(procedimientoOld.getTramites());	        

	    } else {
	    	
	    	// La lista no esta vacia y se deben gestionar los que se han eliminado de la lista
	        HashMap<Long, Tramite> mapaTramitesParaEliminar = new HashMap<Long, Tramite>();
	        for (Tramite tramite : procedimientoOld.getTramites()) {
	            if (tramite != null) {
	            	mapaTramitesParaEliminar.put(tramite.getId(), tramite);
	            }
	        }

	        // Lista para actualizar el orden
	        tramitesParaCrear = new ArrayList<Tramite>();
	        for (String id : idsNuevosTramites) {
	            listaIdsTramitesParaActualizar.add(Long.parseLong(id));
	            tramitesParaCrear.add(mapaTramitesParaEliminar.get(Long.parseLong(id)));
	            mapaTramitesParaEliminar.remove(Long.parseLong(id));
	        }

	        listaTramitesParaEliminar.addAll(mapaTramitesParaEliminar.values());
	        
	    }
	    			
		procedimiento.setTramites(tramitesParaCrear);
		
		return procedimiento;
		
	}

	/*
	 * Guardamos el procedimiento anterior si se trata de una edición. 
	 */
	private ProcedimientoLocal guardarProcedimientoAntiguo(ProcedimientoLocal procediment, ProcedimientoLocal procedimentOld) {

		// Mantenemos los valores originales que tiene el procedimiento.
		procediment.setId(procedimentOld.getId());
		procediment.setTramites(procedimentOld.getTramites());
		procediment.setOrganResolutori(procedimentOld.getOrganResolutori());
		procediment.setPublicosObjetivo(procedimentOld.getPublicosObjetivo());
		
		return procediment;
		
	}

	/*
	 * Para hacer menos accesos a BBDD se comprueba si es edicion o no.
	 * En el primer caso es bastante probable que se repitan la mayoria de public objectiu.
	 */
	private ProcedimientoLocal guardarPublicoObjetivo(HttpServletRequest request, ProcedimientoLocal procediment, 
			ProcedimientoLocal procedimentOld) throws DelegateException {

	    if (isModuloModificado("modul_public_modificat", request)) {

			if (request.getParameter("publicsObjectiu") != null && !"".equals(request.getParameter("publicsObjectiu"))) {
			    
				String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
				Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
				publicsNous.addAll(publicObjDelegate.obtenerPublicosObjetivoPorIDs(request.getParameter("publicsObjectiu"), idioma));
				procediment.setPublicosObjetivo(publicsNous);

			} else {
				
				procediment.setPublicosObjetivo(new HashSet<PublicoObjetivo>());
				
			}
			
		}

		return procediment;
		
	}

	/*
	 * Traducimos al idioma deseado del procedimiento.
	 */
	private ProcedimientoLocal guardarIdioma(HttpServletRequest request, ProcedimientoLocal procediment, ProcedimientoLocal procedimentOld) 
			throws DelegateException {

		TraduccionProcedimientoLocal tpl;
		
		for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			
			tpl = (TraduccionProcedimientoLocal) ((procedimentOld != null) ? procedimentOld.getTraduccion(lang) : new TraduccionProcedimientoLocal());
			
			if (tpl == null) {
                tpl = new TraduccionProcedimientoLocal();
            }
			
			tpl.setNombre(RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)));
			tpl.setDestinatarios(RolUtil.limpiaCadena(request.getParameter("item_destinataris_" + lang)));
			tpl.setResumen(RolUtil.limpiaCadena(request.getParameter("item_objecte_" + lang)));
			tpl.setResultat(RolUtil.limpiaCadena(request.getParameter("item_resultat_" + lang)));
			tpl.setResolucion(RolUtil.limpiaCadena(request.getParameter("item_resolucio_" + lang)));
			tpl.setNotificacion(RolUtil.limpiaCadena(request.getParameter("item_notificacio_" + lang)));
			tpl.setSilencio(RolUtil.limpiaCadena(request.getParameter("item_silenci_" + lang)));
			tpl.setObservaciones(RolUtil.limpiaCadena(request.getParameter("item_observacions_" + lang)));
			tpl.setPlazos(RolUtil.limpiaCadena(request.getParameter("item_presentacio_" + lang)));
			tpl.setLugar(RolUtil.limpiaCadena(request.getParameter("item_lloc_" + lang)));

			procediment.setTraduccion(lang, tpl);
			
		}

		return procediment;
		
	}

	/*
	 * Controlamos la validación del procedimiento.
	 */
	private ProcedimientoLocal guardarValidacion(HttpServletRequest request, ProcedimientoLocal procediment, 
			ProcedimientoLocal procedimentOld, String error) throws DelegateException {

		try {
			
			Integer validacion = Integer.parseInt(request.getParameter("item_estat"));
			
			// Si es superusuario no haremos ninguna comprobación.
			if (!request.isUserInRole("sacsystem")) {
				// Comprobar que no se haya cambiado la validacion/estado siendo operador.
				if (request.isUserInRole("sacoper") && procedimentOld != null && !procedimentOld.getValidacion().equals(validacion)) {
				    throw new DelegateException(new SecurityException());
				}
			}

			procediment.setValidacion(validacion);

		} catch (NumberFormatException e) {
			
			error = messageSource.getMessage("proc.error.estat.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());
			
		}

		return procediment;
		
	}

	/*
	 * Controlamos el formato de la fecha de publicación.
	 */
	private ProcedimientoLocal guardarFechaPublicacion(HttpServletRequest request, ProcedimientoLocal procediment) 
			throws ParseException {

		if (!StringUtils.isEmpty(request.getParameter("item_data_publicacio"))) {
			
			Date data_publicacio = DateUtils.parseDate(request.getParameter("item_data_publicacio"));
			
			if (data_publicacio == null) {
			    throw new ParseException("error.data_publicacio", 0);
			}

			procediment.setFechaPublicacion(data_publicacio);
			
		}

		return procediment;
		
	}

	/*
	 * Controlamos el formato de la fecha de caducidad del procedimiento.
	 */
	private ProcedimientoLocal guardarFechaCaducidad(HttpServletRequest request, ProcedimientoLocal procediment) 
			throws ParseException {

		if (!StringUtils.isEmpty(request.getParameter("item_data_caducitat"))) {
			
			Date data_caducitat = DateUtils.parseDate(request.getParameter("item_data_caducitat"));
			
			if (data_caducitat == null) {
			    throw new ParseException("error.data_caducitat", 0);
			}

			procediment.setFechaCaducidad(data_caducitat);
			
		}

		return procediment;
		
	}

	/*
	 * Obtenemos la fecha de iniciación del procedimiento.
	 */
	private ProcedimientoLocal guardarIniciacion(HttpServletRequest request, ProcedimientoLocal procediment, String error) 
			throws DelegateException {

		try {
			
			Long iniciacionId = Long.parseLong(request.getParameter("item_iniciacio"));
			IniciacionDelegate iDelegate = DelegateUtil.getIniciacionDelegate();
			Iniciacion iniciacion = iDelegate.obtenerIniciacion(iniciacionId);
			procediment.setIniciacion(iniciacion);

		} catch (NumberFormatException e) {
			
			error = messageSource.getMessage("proc.error.formaIniciacio.incorrecta", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());
			
		}

		return procediment;
		
	}

	/*
	 * Obtenemos las famílias de los procedimientos.
	 */
	private ProcedimientoLocal guardarFamilia(HttpServletRequest request, ProcedimientoLocal procediment, String error) 
			throws DelegateException {

		try {
			
			Long familiaId = Long.parseLong(request.getParameter("item_familia"));
			FamiliaDelegate fDelegate = DelegateUtil.getFamiliaDelegate();
			Familia familia = fDelegate.obtenerFamilia(familiaId);
			procediment.setFamilia(familia);

		} catch (NumberFormatException e) {
			
			error = messageSource.getMessage("proc.error.familia.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());
			
		}

		return procediment;
		
	}

    /*
	 * Controlamos la versión del procedimiento.
	 */
	private ProcedimientoLocal guardarVersion(HttpServletRequest request, ProcedimientoLocal procediment, 
			ProcedimientoLocal procedimentOld, String error) {

		try {
			
			String versionStr = request.getParameter("item_version");
			
			if (versionStr != null && !"".equals(versionStr)) {
				
				Long version = Long.parseLong(versionStr);
				procediment.setVersion(version);

			}
			
		} catch (NumberFormatException e) {
			
			error = messageSource.getMessage("proc.error.versio.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());
			
		}

		return procediment;
		
	}

	/*
	 * Obtenemos el Organo resolutorio del procedimiento.
	 */
	private ProcedimientoLocal guardarOrganResolutori(HttpServletRequest request, ProcedimientoLocal procediment, String error) 
			throws DelegateException {

		if (!"".equals(request.getParameter("item_organ_id"))) {
			
			try {
				
				Long organId = Long.parseLong(request.getParameter("item_organ_id"));
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				UnidadAdministrativa organ = uaDelegate.obtenerUnidadAdministrativa(organId);
				procediment.setOrganResolutori(organ);

			} catch (NumberFormatException e) {
				
				error = messageSource.getMessage("proc.error.organ.incorrecte", null, request.getLocale());
				throw new NumberFormatException(e.getMessage());
				
			}
			
		}

		return procediment;
		
	}

	/*
	 * Obtenemos la unidad administrativa del procedimiento.
	 */
	private ProcedimientoLocal guardarUnidadAdministrativa(HttpServletRequest request, ProcedimientoLocal procediment, String error) 
			throws DelegateException {

		try {
			
			Long organRespID = Long.parseLong(request.getParameter("item_organ_responsable_id"));
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			UnidadAdministrativa organResp = uaDelegate.obtenerUnidadAdministrativa(organRespID);
			procediment.setUnidadAdministrativa(organResp);

		} catch (NumberFormatException e) {
			
			error = messageSource.getMessage("proc.error.organ.responsable.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());
			
		}

		return procediment;
		
	}

	/*
	 * Función de grabar() procedimiento
     */
    private Long guardarGrabar(ProcedimientoLocal procediment) throws DelegateException {

        /* XXX: NOTA IMPORTANTE PARA EL RENDIMIENTO */
        procediment.setDocumentos(null);
        procediment.setTramites(null);
        /* FIN NOTA */
        
        Long procId = DelegateUtil.getProcedimientoDelegate().grabarProcedimiento(
    		procediment, 
    		procediment.getUnidadAdministrativa().getId()
		);
        
        // Actualiza estadísticas
        DelegateUtil.getEstadisticaDelegate().grabarEstadisticaProcedimiento(procId);

        return procId;
        
    }
    
    private Long guardarGrabar(ProcedimientoLocal procediment, List<Tramite> listaTramitesParaBorrar,
			List<Long> listaIdsTramitesParaActualizar) throws DelegateException {
    	
    	// Si no hay trámites que procesar, invocamos guardado normal.
    	if (listaTramitesParaBorrar == null && listaIdsTramitesParaActualizar == null) {
    		
    		return guardarGrabar(procediment);
    	
    	// Si no, procesamos con la actualización de los trámites.
    	} else {
		
    		/* XXX: NOTA IMPORTANTE PARA EL RENDIMIENTO */
            procediment.setDocumentos(null);
            /* FIN NOTA */
            
            Long procId = DelegateUtil.getProcedimientoDelegate().grabarProcedimientoConTramites(
        		procediment, 
        		procediment.getUnidadAdministrativa().getId(),
        		listaTramitesParaBorrar,
        		listaIdsTramitesParaActualizar
    		);
            
            // Actualiza estadísticas
            DelegateUtil.getEstadisticaDelegate().grabarEstadisticaProcedimiento(procId);

            return procId;
		
    	}
    	
	}

	/**
     * Devuelve true si ha habido algun cambio en el modulo.
     * 
     * @param modulo
     * @param request
     * @return boolean
     */
    private boolean isModuloModificado(String modulo, HttpServletRequest request) {
        return "1".equals(request.getParameter(modulo));
    }

	@RequestMapping(value = "/cercarNormatives.do", method = POST)
	public @ResponseBody Map<String, Object> llistatNormatives(HttpServletRequest request, HttpSession session) {

		//Listar las normativas de la unidad administrativa
		List<ProcedimientoNormativaDTO>llistaNormativesDTO= new ArrayList<ProcedimientoNormativaDTO>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> paramTrad = new HashMap<String, String>();

		// TODO obtener la ordenación por parámetro
		String campoOrdenacion = "fecha";
		String orden = "desc";

		//Si no hay unidad administrativa se devuelve vacío
		if (getUAFromSession(session) == null) {
		    return resultats;	
		}

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {
			
			String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			// Obtener parámetros de búsqueda
			if (request.getParameter("data") != null && !request.getParameter("data").equals("")) {
				Date fecha = DateUtils.parseDate(request.getParameter("data"));
				paramMap.put("fecha", fecha);
			}

			if (request.getParameter("dataButlleti") != null && !request.getParameter("dataButlleti").equals("")) {
				Date fechaBoletin = DateUtils.parseDate(request.getParameter("dataButlleti"));
				paramMap.put("fechaBoletin", fechaBoletin);
			}

			// Título (en todos los idiomas)
			String text = request.getParameter("titol");
			if (text != null && !"".equals(text)) {
				text = text.toUpperCase();
				paramTrad.put("titulo", text);
			} else {
				paramTrad.put("idioma", idioma);
			}

			//Información de paginación
			String pagPag = request.getParameter("pagPagina");
			String pagRes = request.getParameter("pagRes");
			
			if (pagPag == null) {
			    pagPag = String.valueOf(0);
			}
			if (pagRes == null) {
			    pagRes = String.valueOf(10);
			}

			resultadoBusqueda = new ResultadoBusqueda();

			// Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

			//La búsqueda de normativas no tendrá en cuenta la UA actual (idua = null)
			resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, "todas", null, false, false, campoOrdenacion, orden, pagPag, pagRes);

			for (Normativa normativa : castList(Normativa.class, resultadoBusqueda.getListaResultados()) ) {
				long idNormativa = normativa.getId();
				String tituloEnlaceHTML = HtmlUtils.obtenerTituloDeEnlaceHtml( ( (TraduccionNormativa) normativa.getTraduccion("ca") ).getTitulo() );
				String fecha = normativa.getFecha() == null  ?  " "  :  DateUtils.formatDate( normativa.getFecha() );
				String fechaBoletin = normativa.getFechaBoletin() == null  ?  " "  :  DateUtils.formatDate( normativa.getFechaBoletin() );

				llistaNormativesDTO.add( new ProcedimientoNormativaDTO( idNormativa, tituloEnlaceHTML, fecha, fechaBoletin ) );
			}

		} catch (DelegateException dEx) {
			
			if (!dEx.isSecurityException()) {
			    logException(log, dEx);
			}
			
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaNormativesDTO);

		return resultats;
		
	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
		    String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            TraduccionProcedimientoLocal traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			
		} catch (TraductorException traEx) {
			
		    log.error("CatalegProcedimentBackController.traduir: El traductor no puede traducir todos los idiomas");
		    resultats.put("error", messageSource.getMessage("traductor.no_traduible", null, request.getLocale()));
		    
		} catch (NullPointerException npe) {
			
			log.error("CatalegProcedimentBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("CatalegProcedimentBackController.traduir: Error en al traducir procedimiento: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}

		return resultats;
		
	}

	private TraduccionProcedimientoLocal getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {

	    TraduccionProcedimientoLocal traduccioOrigen = new TraduccionProcedimientoLocal();

	    if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_objecte_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setResumen(request.getParameter("item_objecte_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_resultat_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setResultat(request.getParameter("item_resultat_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_destinataris_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setDestinatarios(request.getParameter("item_destinataris_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_resolucio_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setResolucion(request.getParameter("item_resolucio_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_notificacio_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setNotificacion(request.getParameter("item_notificacio_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_silenci_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setSilencio(request.getParameter("item_silenci_" + idiomaOrigenTraductor));
	    }
        if (StringUtils.isNotEmpty(request.getParameter("item_observacions_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setObservaciones(request.getParameter("item_observacions_" + idiomaOrigenTraductor));
        }

        /* No es seguro que se utilizen */
        if (StringUtils.isNotEmpty(request.getParameter("item_presentacio_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setPlazos(request.getParameter("item_presentacio_" + idiomaOrigenTraductor));
        }
        if (StringUtils.isNotEmpty(request.getParameter("item_lloc_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setLugar(request.getParameter("item_lloc_" + idiomaOrigenTraductor));
        }
        /*------------------------------*/

        return traduccioOrigen;
        
	}


	// Actualiza los hechos vitales que se pueden seleccionar en el mantenimiento de un procedimiento,
	// en función de los públicos objetivo seleccionados.
	@RequestMapping( value = "/listarHechosVitales.do" , method = POST)
	public @ResponseBody Map<String, Object> listarHechosVitales(@RequestParam Set<Long> publicosObjectivosSeleccionados, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			resultats.put("listadoHechosVitales", LlistatUtil.llistarHechosVitales(publicosObjectivosSeleccionados, DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));

		} catch (DelegateException e) {
			
			logException(log, e);
			
			if (e.isSecurityException()) {
			    resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
			    resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
			
		}

		return resultats;
		
	}
	
	@RequestMapping(value = "/guardarHechosVitales.do", method = POST)
	public @ResponseBody IdNomDTO guardarHechosVitales(Long id, Long[] elementos, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		ProcedimientoLocal procedimiento = null;
					
		try {
			
			procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);
			
			// Borramos los anteriores.
			borrarHechosVitalesProcedimientos(procedimiento);
			
			if (elementos != null && elementos.length > 0) {
				
	            // Guardamos los nuevos.
	            List<HechoVital> listHV = DelegateUtil.getHechoVitalDelegate().buscarPorIds(elementos);
	            Set<HechoVitalProcedimiento> hvpsAGuardar = new HashSet<HechoVitalProcedimiento>();
	            
	            for (HechoVital hv : listHV) {
	            	
	                HechoVitalProcedimiento hvp = new HechoVitalProcedimiento();
	                hvp.setProcedimiento(procedimiento);
	                hvp.setHechoVital(hv);
	               
	                int maxOrden = 0;
	                for (HechoVitalProcedimiento hechoVitalProcedimiento : (List<HechoVitalProcedimiento>)hv.getHechosVitalesProcedimientos()) {
	                    if (hechoVitalProcedimiento != null) {
	                        if (maxOrden < hechoVitalProcedimiento.getOrden()) {
	                            maxOrden = hechoVitalProcedimiento.getOrden();
	                        }
	                    }
	                }
	                
	                maxOrden++;
	                hvp.setOrden(maxOrden);
	                hvpsAGuardar.add(hvp);
	                
	            }

	            procedimiento.setHechosVitalesProcedimientos(hvpsAGuardar);

			} else {
				
				procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());
				
			}
			
			guardarGrabar(procedimiento);
			
			result = new IdNomDTO(procedimiento.getId(), messageSource.getMessage("proc.guardat.fetsVitals.correcte", null, request.getLocale()));
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
		}
				
		return result;

	}
	
	private void borrarHechosVitalesProcedimientos(ProcedimientoLocal procedimiento) throws DelegateException {
		
        HechoVitalProcedimientoDelegate hvpDelegate = DelegateUtil.getHechoVitalProcedimientoDelegate();
        List<Long> hvpIds = new LinkedList<Long>();
        
        if (procedimiento.getHechosVitalesProcedimientos() != null) {
        	
            for (HechoVitalProcedimiento hvp : procedimiento.getHechosVitalesProcedimientos())
                hvpIds.add(hvp.getId());

            hvpDelegate.borrarHechoVitalProcedimientos(hvpIds);
            
        }
        
        procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());
        
	}
	
	@RequestMapping(value = "/guardarMaterias.do", method = POST)
	public @ResponseBody IdNomDTO guardarMaterias(Long id, Long[] elementos, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		ProcedimientoLocal procedimiento = null;
					
		try {
			
			procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);
			
			Set<Materia> materias = GuardadoAjaxUtil.obtenerMateriasRelacionadas(elementos);
			procedimiento.setMaterias(materias); 			

			guardarGrabar(procedimiento);
			
			result = new IdNomDTO(procedimiento.getId(), messageSource.getMessage("proc.guardat.materies.correcte", null, request.getLocale()));

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
		}

		return result;

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarNormativas.do", method = POST)
	public @ResponseBody IdNomDTO guardarNormativas(Long id, Long[] elementos, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		ProcedimientoLocal procedimiento = null;
		
		if (id != null) {
			
			try {
				
				procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);
				
				if (elementos != null && elementos.length > 0) {

					List<Long> normativasList = new Vector<Long>();
					NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
					
					for (int i = 0; i < elementos.length; i++)
						normativasList.add(elementos[i]);
					
					Set<Normativa> normativas = new HashSet<Normativa>();
					normativas.addAll(normativaDelegate.buscarNormativas(normativasList));
					
					procedimiento.setNormativas(normativas); 
					
				} else {
					
					procedimiento.setNormativas(new HashSet<Normativa>());
					
				}
				
				guardarGrabar(procedimiento);
				
				result = new IdNomDTO(procedimiento.getId(), messageSource.getMessage("proc.guardat.normatives.correcte", null, request.getLocale()));

			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
					
					error = messageSource.getMessage("error.permisos", null, request.getLocale());
					result = new IdNomDTO(-1l, error);
					
				} else {
					
					error = messageSource.getMessage("error.altres", null, request.getLocale());
					result = new IdNomDTO(-2l, error);
					log.error(ExceptionUtils.getStackTrace(dEx));
					
				}
				
			}
			
		} else {
			
			error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			
		}
				
		return result;

	}
	
	@RequestMapping(value = "/guardarDocumentosRelacionados.do", method = POST)
	public @ResponseBody IdNomDTO guardarDocumentosRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
		
		// Guardaremos el orden y borraremos los documentos que se hayan marcado para borrar.
		// La creación se gestiona en el controlador DocumentBackController.
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		ProcedimientoLocal procedimiento = null;
		
		try {
			
			procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);
			List<Documento> documentos = GuardadoAjaxUtil.actualizarYOrdenarDocumentosRelacionados(elementos, procedimiento, null);
			procedimiento.setDocumentos(documentos);
			
			guardarGrabar(procedimiento);
			
			result = new IdNomDTO(procedimiento.getId(), messageSource.getMessage("proc.guardat.documents.correcte", null, request.getLocale()));
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
		}		
		
		return result;

	}

	class HechoVitalProcedimientoDTOComparator implements Comparator<Map<String, Object>> {
		
        public int compare(Map<String, Object> hvp1, Map<String, Object> hvp2) {
            
        	Integer orden1 = (Integer) hvp1.get("orden");
            Integer orden2 = (Integer) hvp2.get("orden");
            
            return orden1.compareTo(orden2); 
            
        }
        
    }

}
