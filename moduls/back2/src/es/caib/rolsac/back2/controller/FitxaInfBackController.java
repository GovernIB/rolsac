package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.DocumentoResumen;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaResumen;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionEnlace;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.EnlaceDTO;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.dto.FichaUADTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SeccionDTO;
import org.ibit.rol.sac.model.dto.UnidadDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoResumenDelegate;
import org.ibit.rol.sac.persistence.delegate.EnlaceDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaResumenDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.Parametros;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/fitxainf/")
public class FitxaInfBackController extends PantallaBaseController {

	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.fitxa.url";
	private static Log log = LogFactory.getLog(FitxaInfBackController.class);

	@RequestMapping(value = "/fitxainf.do", method = GET)
	public String pantallaFitxes(Map<String, Object> model, HttpServletRequest request, HttpSession session) {

		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 3);
		model.put("titol_escriptori", messageSource.getMessage("submenu.fitxes_informatives", null, request.getLocale()));
		model.put("escriptori", "pantalles/fitxaInf.jsp");
		request.setAttribute("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));

		try {
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			if (session.getAttribute("unidadAdministrativa") != null) {
				model.put("idUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getId());
				model.put("nomUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(lang));
			}
			
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			List<Materia> llistaMateries = new ArrayList<Materia>();
			List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();

			llistaMateries = castList(Materia.class, materiaDelegate.listarMaterias());

			for (Materia materia : llistaMateries) {
			    llistaMateriesDTO.add(new IdNomDTO(materia.getId(), materia.getNombreMateria(lang)));
			}

			model.put("llistaMateries", llistaMateriesDTO);

			HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
			List<HechoVital> llistaFetsVitals = new ArrayList<HechoVital>();
			List<IdNomDTO> llistaFetsVitalsDTO = new ArrayList<IdNomDTO>();

			llistaFetsVitals = fetVitalDelegate.listarHechosVitales();
			for (HechoVital fetVital : llistaFetsVitals) {
				TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
				llistaFetsVitalsDTO.add(new IdNomDTO(fetVital.getId(), thv == null ? null : thv.getNombre()));
			}
			model.put("llistaFetsVitals", llistaFetsVitalsDTO);

			PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
			List<PublicoObjetivo> llistaPublicsObjectiu = new ArrayList<PublicoObjetivo>();
			List<IdNomDTO> llistaPublicsObjectiuDTO = new ArrayList<IdNomDTO>();

			llistaPublicsObjectiu = publicObjectiuDelegate.listarPublicoObjetivo();
			for (PublicoObjetivo publicObjectiu : llistaPublicsObjectiu) {
				TraduccionPublicoObjetivo tpo = (TraduccionPublicoObjetivo) publicObjectiu.getTraduccion(lang);
				llistaPublicsObjectiuDTO.add(new IdNomDTO(publicObjectiu.getId(), tpo == null ? null : tpo.getTitulo()));
			}
			model.put("llistaPublicsObjectiu", llistaPublicsObjectiuDTO);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
			    log.error("Error de permisos " + ExceptionUtils.getStackTrace(dEx));
			} else {
			    log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}

		loadIndexModel(model, request);
		return "index";
	}


    @RequestMapping(value = "/llistat.do", method = POST)
    public @ResponseBody Map<String, Object> llistatFitxes(HttpServletRequest request, HttpSession session) {

    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	Map<String, String> tradMap = new HashMap<String, String>();
    	List<FichaDTO> llistaFitxesDTO = new ArrayList<FichaDTO>();
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	
    	UnidadAdministrativa ua = null;
    	if (session.getAttribute("unidadAdministrativa") != null) {
    	    ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
    	}

    	// Recuperamos los campos directamente des de el request
    	String campoOrdenacion = request.getParameter("ordreCamp");				// Recuperamos el parametro de ordenación por campo
    	String orden = request.getParameter("ordreTipus");						// Recuperamos el parametro de ordenación por tipo
    	boolean uaFilles = "1".equals(request.getParameter("uaFilles"));		// Recuperamos si se debe buscar en las UAs hijas
        boolean uaMeves = "1".equals(request.getParameter("uaMeves"));			// Recuperamos si se debe buscar en las UAs propias

        Long materia = recuperarParametroId(request,"materia");		       		// Recuperamos el id de la materia
        Long fetVital = recuperarParametroId(request, "fetVital");				// Recuperamos el id del hecho vital
        Long publicObjectiu = recuperarParametroId(request, "publicObjectiu");	// Recuperamos el id del público objetivo
		String pagPag = recuperarPaginacion(request, "pagPag");			    	// Recuperamos la página actual
		String pagRes = recuperarPaginacion(request, "pagRes");			    	// Recuperamos los resultados por página

		int campoVisible = recuperarVisibilidad(request, paramMap);		     	// Recuperamos la visibilidad de la ficha

		recuperarCodigo(request, paramMap);						     			// Recuperamos el parametro del código
        recuperarTexto(request, tradMap);										// Recuperamos el texto y lo buscamos en todos los idiomas
        recuperarValidacio(request, paramMap);									// Recuperamos si es válido

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
        try {
        	FichaResumenDelegate fitxaResumenDelegate = DelegateUtil.getFichaResumenDelegate();

        	resultadoBusqueda = fitxaResumenDelegate.buscarFichas(paramMap, tradMap, ua, fetVital, materia, publicObjectiu, uaFilles, uaMeves, campoOrdenacion, orden, pagPag, pagRes, campoVisible);

        	for (FichaResumen fitxaResumen : castList(FichaResumen.class, resultadoBusqueda.getListaResultados() ) ) {
        		TraduccionFicha tfi = (TraduccionFicha) fitxaResumen.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
        		llistaFitxesDTO.add(new FichaDTO(
        				fitxaResumen.getId(),
        				tfi == null ? null : tfi.getTitulo(),
        				DateUtils.formatDate(fitxaResumen.getFechaPublicacion()),
        				DateUtils.formatDate(fitxaResumen.getFechaCaducidad()),
        				DateUtils.formatDate(fitxaResumen.getFechaActualizacion()),
        				fitxaResumen.isVisible()));
        		}

        } catch (DelegateException dEx) {
        	if (dEx.isSecurityException()) {
        	    log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
        	} else {
        	    log.error(ExceptionUtils.getStackTrace(dEx));
        	}
        }

        resultats.put("total",  resultadoBusqueda.getTotalResultados());
        resultats.put("nodes", llistaFitxesDTO);

        return resultats;
    }
    
    /*
	 * Recuperamos el campo del código
	 */
	private void recuperarCodigo(HttpServletRequest request, Map<String, Object> paramMap) {

		String idStr = request.getParameter("codi");
		Long id = -1l;
		if (idStr != null && StringUtils.isNumeric(idStr.trim())) {
		    id = ParseUtil.parseLong( idStr.trim() );
		}
		paramMap.put("id", idStr != null ? id : null);
	}

    /*
	 * Recuperamos el estado de la ficha para la validación
	 */
	private void recuperarValidacio(HttpServletRequest request, Map<String, Object> paramMap) {

		if (request.isUserInRole("sacoper")) {
		    // En el back antiguo estaba asi.
			paramMap.put("validacion", "");
		} else {
			String estat = request.getParameter("estat");
			try {
				Integer validacion = Integer.parseInt(estat);
				paramMap.put("validacion", validacion);
			} catch (NumberFormatException e) {}
		}
	}

    /*
     * Recuperar id por parametro del request
     */
    private Long recuperarParametroId(HttpServletRequest request, String parametro) {

    	try {
    		return Long.parseLong(request.getParameter(parametro));
    	} catch (NumberFormatException e) {
    		return null;
    	}
    }

    /*
     * Recuperamos el campo texto para las traducciones
     */
    private void recuperarTexto(HttpServletRequest request, Map<String, String> tradMap) {

    	String textes = request.getParameter("textes");
    	if (textes != null && !"".equals(textes)) {
    		textes = textes.toUpperCase();
    		if (tradMap.get("titulo") == null) {
    			tradMap.put("titulo", textes);
    		}
    		tradMap.put("url", textes);
    		textes = HtmlUtils.eliminarTagsHtml(textes);
    		tradMap.put("descAbr", textes);
    		tradMap.put("descripcion", textes);

    	} else {
    		try {
    			tradMap.put("idioma", DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
			} catch  (DelegateException dEx) {
				logException(log, dEx);
			}
    	}
    }

    /*
     * Recuperamos la visibilidad del request
     */
    private int recuperarVisibilidad(HttpServletRequest request, Map<String, Object> paramMap) {

    	String visibilitat = request.getParameter("visibilitat");
    	if (visibilitat != null) {
    		if (visibilitat.equals("1")) {
    			Integer visible = Integer.parseInt(visibilitat);
    			paramMap.put("validacion", visible);
    			return 1;
    		} else if (visibilitat.equals("2")) {
    			return 2;
    		}
    	}
    	return 0;
    }

    /*
	 * Recuperamos parametros de paginación
	 */
	private String recuperarPaginacion(HttpServletRequest request, String parametro) {

		String pagina = request.getParameter(parametro);
		if (pagina == null) pagina = String.valueOf(0);
		return pagina;
	}


    @RequestMapping(value = "/pagDetall.do", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {

    	Map<String, Object> resultats = new HashMap<String, Object>();

    	try {
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            Long id = new Long(request.getParameter("id"));
        	FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
        	Ficha fitxa = fitxaDelegate.obtenerFicha(id);

        	resultats.put("item_id", fitxa.getId());
        	resultats.put("item_estat", fitxa.getValidacion());
        	resultats.put("item_data_publicacio", DateUtils.formatDateSimpleTime(fitxa.getFechaPublicacion()));
        	resultats.put("item_data_caducitat", DateUtils.formatDateSimpleTime(fitxa.getFechaCaducidad()));
        	resultats.put("item_youtube", fitxa.getUrlVideo());
            resultats.put("item_forum", fitxa.getUrlForo());
            resultats.put("item_responsable", fitxa.getResponsable());
            resultats.put("item_notes", fitxa.getInfo());

        	recuperaIdioma(resultats, fitxa, lang);		// Recuperar las fichas según el idioma.
        	recuperaDocs(resultats, fitxa);				// Recuperar los documentos asociados a una ficha.
        	recuperaIcono(resultats, fitxa);			// Recuperar el icono de una ficha.
        	recuperaBanner(resultats, fitxa);			// Recuperar los banners de una ficha.
        	recuperaImatge(resultats, fitxa);			// Recuperar la imagen de una ficha.
        	recuperaMateries(resultats, fitxa, lang);	// Recuperar las materias asociadas a una ficha.
        	recuperaFetsVitals(resultats, fitxa, lang);	// Recuperar los hechos vitales asociados a una ficha.
        	recuperaPO(resultats, fitxa, lang);			// Recuperar los públicos objetiovs de una ficha.
        	recuperaRelacio(resultats, fitxa, lang);	// Recuperar las relaciones ficha-sección-UA
        	recuperaEnllasos(resultats, fitxa);			// Recuperar los enlaces de una ficha.

        } catch (DelegateException dEx) {
        	log.error("Error: " + ExceptionUtils.getStackTrace(dEx));
        	if (dEx.isSecurityException()) {
        	    resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
        	} else {
        	    resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
        	}
        }
    	return resultats;
    }

    /*
     * Función que recupera el contenido de las fichas según el idioma
     */
    private void recuperaIdioma(Map<String, Object> resultats, Ficha fitxa, String langDefault) throws DelegateException {

        List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        for (String lang : langs) {
            if (fitxa.getTraduccion(lang) != null) {
                resultats.put(lang, (TraduccionFicha) fitxa.getTraduccion(lang));
            } else {
                if (fitxa.getTraduccion(langDefault) != null) {
                    resultats.put(lang, (TraduccionFicha) fitxa.getTraduccion(langDefault));
                } else {
                    resultats.put(lang, new TraduccionFicha());
                }
            }
        }
    }

    /*
	 * Función para recuperar los documentos relaciohnados con la ficha.
	 */
	private void recuperaDocs(Map<String, Object> resultats, Ficha fitxa) throws DelegateException {

		if (fitxa.getDocumentos() != null) {
			Map<String, Object> mapDoc;
			List<Map<String, Object>> llistaDocuments = new ArrayList<Map<String, Object>>();
			List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			for (Documento doc: fitxa.getDocumentos()) {
				if (doc != null) {

					// Montar map solo con los campos 'titulo' de las traducciones del documento.
					Map<String, String> titulos = new HashMap<String, String>();
					String nombre;
					TraduccionDocumento traDoc;

					for (String idioma: idiomas) {
						traDoc = (TraduccionDocumento) doc.getTraduccion(idioma);
						nombre = (traDoc != null && traDoc.getTitulo() != null) ? traDoc.getTitulo() :  "";
						titulos.put(idioma, nombre);
					}

					mapDoc = new HashMap<String, Object>(3);
					mapDoc.put("id", doc.getId());
					mapDoc.put("orden", doc.getOrden());
					mapDoc.put("nombre", titulos);
					llistaDocuments.add(mapDoc);

				} else {
					log.error("La fitxa " + fitxa.getId() + " te un document null.");
				}
			}
			resultats.put("documents", llistaDocuments);

		} else {
			resultats.put("documents", null);
		}
	}

	/*
	 * Función para recuperar el icono.
	 */
	private void recuperaIcono(Map<String, Object> resultats, Ficha fitxa) {

		if (fitxa.getIcono() != null) {
			resultats.put("item_icona_enllas_arxiu", "fitxainf/archivo.do?id=" + fitxa.getId() + "&tipus=1");
			resultats.put("item_icona", fitxa.getIcono().getNombre());
		} else {
			resultats.put("item_icona_enllas_arxiu", "");
			resultats.put("item_icona", "");
		}
	}

	/*
	 * Función para recuperar el banner
	 */
	private void recuperaBanner(Map<String, Object> resultats, Ficha fitxa) {

		if (fitxa.getBaner() != null) {
			resultats.put("item_banner_enllas_arxiu", "fitxainf/archivo.do?id=" + fitxa.getId() + "&tipus=2");
			resultats.put("item_banner", fitxa.getBaner().getNombre());
		} else {
			resultats.put("item_banner_enllas_arxiu", "");
			resultats.put("item_banner", "");
		}
	}

	/*
	 * Función para recuperar la imagen de una ficha.
	 */
	private void recuperaImatge(Map<String, Object> resultats, Ficha fitxa) {

		if (fitxa.getImagen() != null) {
			resultats.put("item_imatge_enllas_arxiu", "fitxainf/archivo.do?id=" + fitxa.getId() + "&tipus=3");
			resultats.put("item_imatge", fitxa.getImagen().getNombre());
		} else {
			resultats.put("item_imatge_enllas_arxiu", "");
			resultats.put("item_imatge", "");
		}
	}

	/*
	 * Función para recuperar las materias asociadas a una ficha
	 */
	private void recuperaMateries(Map<String, Object> resultats, Ficha fitxa, String lang) {

		List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
		if (fitxa.getMaterias() != null) {
			for (Materia materia : fitxa.getMaterias()) {
			    llistaMateriesDTO.add(new IdNomDTO(materia.getId(), materia.getNombreMateria(lang)));
			}
			resultats.put("materies", llistaMateriesDTO);
			
		} else {
			resultats.put("materies", null);
		}
	}

	/*
	 * Función para recuperar los hechos vitales de una ficha
	 */
	private void recuperaFetsVitals(Map<String, Object> resultats, Ficha fitxa, String lang) {

		List<IdNomDTO> llistaFetsVitalsDTO = new ArrayList<IdNomDTO>();
		if (fitxa.getHechosVitales() != null) {
			for (HechoVital fetVital : fitxa.getHechosVitales()) {
				TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
				llistaFetsVitalsDTO.add(new IdNomDTO(fetVital.getId(), thv == null ? "" : thv.getNombre()));
			}
			resultats.put("fetsVitals", llistaFetsVitalsDTO);

		} else {
			resultats.put("fetsVitals", null);
		}
	}

	/*
	 * Función para recuperar el público objetivo
	 */
	private void recuperaPO(Map<String, Object> resultats, Ficha fitxa, String lang) {

		List<IdNomDTO> llistaPublicObjectiuDTO = new ArrayList<IdNomDTO>();
		if (fitxa.getPublicosObjetivo() != null) {
			for (PublicoObjetivo publicObj : fitxa.getPublicosObjetivo()) {
				TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo) publicObj.getTraduccion(lang);
				llistaPublicObjectiuDTO.add(new IdNomDTO(publicObj.getId(), tpob == null ? "" : tpob.getTitulo()));
			}
			resultats.put("publicsObjectiu", llistaPublicObjectiuDTO);
			
		} else {
			resultats.put("publicsObjectiu", null);
		}
	}

	/*
	 * Función para recuperar la relación Ficha-Sección-UA
	 */
	private void recuperaRelacio(Map<String, Object> resultats, Ficha fitxa, String lang) throws DelegateException {

		List<FichaUADTO> llistaFichaUADTO = new ArrayList<FichaUADTO>();
		if (fitxa.getFichasua() != null) {
			for (FichaUA fichaUA : DelegateUtil.getFichaDelegate().listFichasUA(fitxa.getId())) {
				TraduccionSeccion tse = (TraduccionSeccion) fichaUA.getSeccion().getTraduccion(lang);
				llistaFichaUADTO.add(new FichaUADTO(
						fichaUA.getId(),
						fichaUA.getUnidadAdministrativa().getId(),
						fichaUA.getUnidadAdministrativa().getNombreUnidadAdministrativa(lang),
						fichaUA.getSeccion().getId(),
						tse == null ? "" : tse.getNombre(),
						null,
						null,
						fichaUA.getOrden(),
						fichaUA.getOrdenseccion())
				);
			}
			resultats.put("seccUA", llistaFichaUADTO);
			
		} else {
			resultats.put("seccUA", null);
		}
	}

	/*
	 * Función para recuperar los enlaces de una ficha.
	 */
	private void recuperaEnllasos(Map<String, Object> resultats, Ficha fitxa) {

		List<EnlaceDTO> llistaEnllassosDTO = new ArrayList<EnlaceDTO>();
		if (fitxa.getEnlaces() != null) {
			for (Enlace enllas : fitxa.getEnlaces()) {
				if (enllas != null)
					llistaEnllassosDTO.add(new EnlaceDTO(enllas.getId(), enllas.getOrden(), enllas.getTraduccionMap()));
			}
			resultats.put("enllassos", llistaEnllassosDTO);

		} else {
			resultats.put("enllassos", null);
		}
	}


    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardarFicha(HttpSession session, HttpServletRequest request) {
    	/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;
		
		try {
			// Aqui nos llegaría un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
			// Iremos recopilando los parametros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
			Map<String, String> valoresForm = new HashMap<String, String>();
			Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
			Set<String> enllasos = new HashSet<String>();
			List<String> docsIds = new ArrayList<String>();

			List<FileItem> items = castList(FileItem.class, UploadUtil.obtenerServletFileUpload().parseRequest(request));
			for (FileItem item : items) {
				if (item.isFormField()) {
					if (item.getFieldName().startsWith("enllas_")) {
					    enllasos.add(item.getFieldName());
					}
					if (item.getFieldName().startsWith("documents_id_")) {
					    docsIds.add(item.getFieldName());
					}
					valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
					
				} else {
					ficherosForm.put(item.getFieldName(), item);
				}
			}

			// Comprovam camps obligatoris
			result = guardarControlCampos(request, valoresForm);
			if (result != null) {
			    return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
			}

			// Recuperamos la ficha antigua
			Ficha fitxaOld = guardarFitxaAntigua(valoresForm);

			// Comprobación de si se trata de una edición de una ficha ya existente o de una nueva ficha
			boolean edicion = (fitxaOld != null) ? true : false;

			Ficha fitxa = new Ficha();
			// Recuperación de los nuevos valores de una ficha, tanto si es edición como una ficha nueva
			fitxa = guardarValidacion(request, fitxa, fitxaOld, valoresForm);           // Es comprova que l'estat es un estat permes i es recupera
			fitxa = guardarAntiguo(edicion, fitxa, fitxaOld);                           // Guardamos los campos de la ficha en caso de que sea una edición y no una ficha nueva 
			fitxa = guardarFechaPublicacion(fitxa, valoresForm);                        // Recuperamos y controlamos el valor de la fecha de publicación
			fitxa = guardarFechaCaducidad(fitxa, valoresForm);                          // Recuperamos y controlamos el valor de la fecha de caducidad
			fitxa = guardarIdiomas(edicion, fitxa, valoresForm);                        // Recuperamos y controlamos las traducciones de la ficha
			fitxa = guardarIcono(fitxa, valoresForm, ficherosForm);                     // Controlamos los cambios del icono
			fitxa = guardarBanner(fitxa, valoresForm, ficherosForm);                    // Controlamos los cambios del banner
			fitxa = guardarImatge(fitxa, valoresForm, ficherosForm);                    // Controlamos los cambios de la imagen
			fitxa = guardarMaterias(edicion, fitxa, fitxaOld, valoresForm);             // Controlamos las materias modificadas o incluidas
            fitxa = guardarHechosVitales(edicion, fitxa, fitxaOld, valoresForm);        // Controlamos los hechos vitales modificados o incluidos
            fitxa = guardarPublicoObjetivo(edicion, fitxa, fitxaOld, valoresForm);      // Controlamos los públicos objetivos modificados o incluidos
            fitxa = guardarDocumentos(edicion, fitxa, fitxaOld, valoresForm, docsIds);  // Controlamos los documentos asociados a una ficha

			fitxa.setFechaActualizacion(new Date());									// Guardamos la fecha actual al ser la última actualización
			fitxa.setUrlForo(valoresForm.get("item_forum"));							// Guardamos el valor de la URL del foro
            fitxa.setUrlVideo(valoresForm.get("item_youtube"));							// Guardamos el valor de la URL del video
            fitxa.setResponsable(valoresForm.get("item_responsable"));					// Guardamos el responsable de la ficha
            fitxa.setInfo(valoresForm.get("item_notes"));								// Guardamos el campo de la información
            // Fin recuperación de los valores

            Long idFitxa = guardarGrabar(fitxa);										// Guardar los cambios de una ficha

            // Guardado de las relaciones de una ficha con otras entidades
            guardarSecciosUA(edicion, fitxaOld, valoresForm, idFitxa);					// Guardamos las relaciones de la ficha con las secciones y las UAs
            guardarEnlaces(edicion, fitxa, fitxaOld, valoresForm, idFitxa, enllasos);	// Guardamos llas relaciones con los enlaces
            // Fin guardado relaciones

            // Finalitzat correctament
            result = new IdNomDTO(fitxa.getId(), messageSource.getMessage("fitxes.guardat.correcte", null, request.getLocale()));

        } catch (DelegateException dEx) {
        	if (dEx.isSecurityException()) {
        		error = messageSource.getMessage("error.permisos", null, request.getLocale());
        		result = new IdNomDTO(-1l, error);
        	} else {
        		error = messageSource.getMessage("error.altres", null, request.getLocale());
        		result = new IdNomDTO(-2l, error);
        		log.error(ExceptionUtils.getStackTrace(dEx));
        	}
        } catch (FileUploadException e) {
        	error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
        	result = new IdNomDTO(-3l, error);
        	log.error(ExceptionUtils.getStackTrace(e));
        } catch (UnsupportedEncodingException e) {
        	error = messageSource.getMessage("error.altres", null, request.getLocale());
        	result = new IdNomDTO(-2l, error);
        	log.error(ExceptionUtils.getStackTrace(e));
        } catch (NumberFormatException nfe) {
        	error = messageSource.getMessage("proc.error.estat.incorrecte", null, request.getLocale());
        	result = new IdNomDTO(-3l, error);
        } catch (ParseException pe) {
        	error = messageSource.getMessage(pe.getMessage(), null, request.getLocale());
        	result = new IdNomDTO(-4l, error);
        }

		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    }

    /*
	 * Función que comprueba los campos obligatorios
	 */
	private IdNomDTO guardarControlCampos(HttpServletRequest request, Map<String, String> valoresForm) throws DelegateException {

		String error;
		// Se cambia el "item_titol_ca" por el "item_titol_" + idioma
		String titolCatala = valoresForm.get("item_titol_" + DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
		if (titolCatala == null || "".equals(titolCatala)) {
			error = messageSource.getMessage("fitxes.formulari.error.falten.camps", null, request.getLocale());
			return new IdNomDTO(-3l, error);
		}
		if (valoresForm.get("seccUA") == null || valoresForm.get("seccUA").split("#").length < 1) {
			error = messageSource.getMessage("fitxes.missatge.es_necessari", null, request.getLocale());
			return new IdNomDTO(-3l, error);
		}
		if (valoresForm.get("publicsObjectiu") == null || valoresForm.get("publicsObjectiu").equals("")) {
			error = messageSource.getMessage("fitxes.missatge.es_necessari_public", null, request.getLocale());
			return new IdNomDTO(-3l, error);
		}

		return null;
	}

    /*
	 * Controlamos si se trata de una nueva ficha o es la edición de una ya existente
	 */
	private Ficha guardarFitxaAntigua(Map<String, String> valoresForm) throws DelegateException {

		Ficha fitxaOld = null;
		Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
		if (id != null) {
			fitxaOld = DelegateUtil.getFichaDelegate().obtenerFicha(id);
		} else {
			fitxaOld = null;
		}

		return fitxaOld;
	}

	/*
	 * Recuperación y comprobación de si una ficha esta en un estado valido, es decir que este en un estado permitido
	 */
	private Ficha guardarValidacion(HttpServletRequest request, Ficha fitxa, Ficha fitxaOld, Map<String, String> valoresForm) throws DelegateException {

		Integer validacion = Integer.parseInt(valoresForm.get("item_estat"));
		// Comprobar que no se haya cambiado la validacion/estado siendo operador
		if (request.isUserInRole("sacoper") && fitxaOld != null && !fitxaOld.getValidacion().equals(validacion)) {
		    throw new DelegateException(new SecurityException());
		}

		fitxa.setValidacion(validacion);
		return fitxa;
	}

	/*
	 * Guardamos la ficha anterior si se trata de una edición. 
	 */
	private Ficha guardarAntiguo(boolean edicion, Ficha fitxa, Ficha fitxaOld) {

		if (edicion) {
			// Mantenim els valors que te la fitxa.
			fitxa.setId(fitxaOld.getId());
			fitxa.setBaner(fitxaOld.getBaner());
			fitxa.setIcono(fitxaOld.getIcono());
			fitxa.setImagen(fitxaOld.getImagen());
			fitxa.setResponsable(fitxaOld.getResponsable());
			fitxa.setForo_tema(fitxaOld.getForo_tema());
			fitxa.setFichasua(fitxaOld.getFichasua());
			fitxa.setDocumentos(fitxaOld.getDocumentos());
			fitxa.setEnlaces(fitxaOld.getEnlaces());
			fitxa.setMaterias(fitxaOld.getMaterias());
			fitxa.setHechosVitales(fitxaOld.getHechosVitales());
			fitxa.setPublicosObjetivo(fitxaOld.getPublicosObjetivo());
		}

		return fitxa;
	}

	/*
	 * Controlamos las modificaciones en la fecha de publicación
	 */
	private Ficha guardarFechaPublicacion(Ficha fitxa, Map<String, String> valoresForm) throws ParseException {

		if (!StringUtils.isEmpty(valoresForm.get("item_data_publicacio"))) {
			Date data_publicacio = DateUtils.parseDateSimpleTime(valoresForm.get("item_data_publicacio"));
			if (data_publicacio == null) {
			    throw new ParseException("error.data_publicacio", 0);
			}
			fitxa.setFechaPublicacion(data_publicacio);
		}

		return fitxa;
	}

	/*
	 * Controlamos los cambios en la fecha de caducidad
	 */
	private Ficha guardarFechaCaducidad(Ficha fitxa, Map<String, String> valoresForm) throws ParseException {

		if (!StringUtils.isEmpty(valoresForm.get("item_data_caducitat"))) {
			Date data_caducitat = DateUtils.parseDateSimpleTime(valoresForm.get("item_data_caducitat"));
			if (data_caducitat == null) {
			    throw new ParseException("error.data_caducitat", 0);
			}
			fitxa.setFechaCaducidad(data_caducitat);
		}

		return fitxa;
	}

	/*
	 * Controlamos los diferentes idiomas de una ficha
	 */
	private Ficha guardarIdiomas(boolean edicion, Ficha fitxa, Map<String, String> valoresForm) throws DelegateException {

		TraduccionFicha tfi;
		for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			tfi = (TraduccionFicha) ((fitxa != null) ? fitxa.getTraduccion(lang) : new TraduccionProcedimientoLocal());
            if (tfi == null) {
                tfi = new TraduccionFicha();
            }

			tfi.setTitulo(RolUtil.limpiaCadena(valoresForm.get("item_titol_" + lang)));
			tfi.setDescAbr(RolUtil.limpiaCadena(valoresForm.get("item_des_curta_" + lang)));
			tfi.setDescripcion(RolUtil.limpiaCadena(valoresForm.get("item_des_llarga_" + lang)));
			tfi.setUrl(valoresForm.get("item_url_" + lang));
			fitxa.setTraduccion(lang, tfi);
		}

		return fitxa;
	}

	/*
	 * Controlamos el icono de la ficha
	 */
	private Ficha guardarIcono(Ficha fitxa, Map<String, String> valoresForm, Map<String, FileItem> ficherosForm) {

		FileItem fileIcona = ficherosForm.get("item_icona");
		if (fileIcona != null && fileIcona.getSize() > 0) {
		    fitxa.setIcono(UploadUtil.obtenerArchivo(fitxa.getIcono(), fileIcona));
		} else if (valoresForm.get("item_icona_delete") != null && !"".equals(valoresForm.get("item_icona_delete"))) {
		    // borrar fichero si se solicita
		    fitxa.setIcono(null);
		}

		return fitxa;
	}

	/*
	 * Controlamos las modificaciones del banner
	 */
	private Ficha guardarBanner(Ficha fitxa, Map<String, String> valoresForm, Map<String, FileItem> ficherosForm) {

		FileItem fileBanner = ficherosForm.get("item_banner");
		if (fileBanner != null && fileBanner.getSize() > 0) {
		    fitxa.setBaner(UploadUtil.obtenerArchivo(fitxa.getBaner(), fileBanner));
		} else if (valoresForm.get("item_banner_delete") != null && !"".equals(valoresForm.get("item_banner_delete"))) {
		    // borrar fichero si se solicita
		    fitxa.setBaner(null);
		}

		return fitxa;
	}

	/*
	 * Controlamos las modificaciones de la imagen
	 */
	private Ficha guardarImatge(Ficha fitxa, Map<String, String> valoresForm, Map<String, FileItem> ficherosForm) {

		FileItem fileImatge = ficherosForm.get("item_imatge");
		if (fileImatge != null && fileImatge.getSize() > 0) {
		    fitxa.setImagen(UploadUtil.obtenerArchivo(fitxa.getImagen(), fileImatge));
		} else if (valoresForm.get("item_imatge_delete") != null && !"".equals(valoresForm.get("item_imatge_delete"))) {
		    // borrar fichero si se solicita
		    fitxa.setImagen(null);
		}

		return fitxa;
	}

	/*
     * Para hacer menos accesos a BBDD se comprueba si es edicion o no, en el primer caso, es bastante
     * probable que se repitan la mayoria de materias.
     */
    private Ficha guardarMaterias(boolean edicion, Ficha fitxa, Ficha fitxaOld, Map<String, String> valoresForm) throws DelegateException {

    	if (isModuloModificado("modulo_materias_modificado", valoresForm)) {

    		if (valoresForm.get("materies") != null && !"".equals(valoresForm.get("materies"))) {
    			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
				Set<Materia> materias = new HashSet<Materia>();
				materias.addAll(materiaDelegate.obtenerMateriasPorIDs(valoresForm.get("materies"), DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));
				fitxa.setMaterias(materias); 

    		} else {
    			fitxa.setMaterias(new HashSet<Materia>());
    		}
    	}

    	return fitxa;
    }

    /*
     * Controlamos los hechos vitales modificados o incluidos
     */
    private Ficha guardarHechosVitales(boolean edicion, Ficha fitxa, Ficha fitxaOld, Map<String, String> valoresForm) throws DelegateException {

    	if (isModuloModificado("modulo_hechos_modificado", valoresForm)) {
        	if (valoresForm.get("fetsVitals") != null && !"".equals(valoresForm.get("fetsVitals"))) {
        		Set<HechoVital> hechosVitales = new HashSet<HechoVital>();
        		List<Long> ids = new ArrayList<Long>();
    			for (String cod : valoresForm.get("fetsVitals").split(",")) {
    			    ids.add(Long.parseLong(cod));
    			}
    			hechosVitales.addAll(DelegateUtil.getHechoVitalDelegate().buscarPorIds(ids));
        		fitxa.setHechosVitales(hechosVitales);

        	} else {
        		fitxa.setHechosVitales(new HashSet<HechoVital>());
        	}
        }

    	return fitxa;
    }

    /*
     * Controlamos los públicos objetivos modificados o incluidos
     */
    private Ficha guardarPublicoObjetivo(boolean edicion, Ficha fitxa, Ficha fitxaOld, Map<String, String> valoresForm) throws DelegateException {

    	if (isModuloModificado("modul_public_modificat", valoresForm)) {
    		if (valoresForm.get("publicsObjectiu") != null && !"".equals(valoresForm.get("publicsObjectiu"))) {
    		    String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
                PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
                Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
                publicsNous.addAll(publicObjDelegate.obtenerPublicosObjetivoPorIDs(valoresForm.get("publicsObjectiu"), idioma));                
                fitxa.setPublicosObjetivo(publicsNous);

    		} else {
                fitxa.setPublicosObjetivo(new HashSet<PublicoObjetivo>());
            }
        }

    	return fitxa;
    }

    /*
     * Función para recuperar y controlas los documendos asociados
     */
    private Ficha guardarDocumentos(boolean edicion, Ficha fitxa, Ficha fitxaOld, Map<String, String> valoresForm, List<String> docsIds) throws DelegateException {

        if (isModuloModificado("modulo_documents_modificado", valoresForm)) {

            HashSet<String> hashSet = new HashSet<String>(docsIds);
            docsIds.clear();
            docsIds.addAll(hashSet);

            List<String> ids = new ArrayList<String>(docsIds.size());
            Map<String, String> idOrden = new HashMap<String, String>();
            for (String id : docsIds) {
                ids.add(id.split("_")[2]);
                String orden = valoresForm.get("documents_orden_" + id.split("_")[2]);
                idOrden.put(id.split("_")[2], orden);
            }

            List<Documento> documents = cuerpo(fitxaOld.getDocumentos(), ids, idOrden, edicion);

            // Assignar els documents a la fitxa
            fitxa.setDocumentos(documents);
        }

        return fitxa;
    }

    private List<Documento> cuerpo(List<Documento> docsOld, List<String> docsIds, Map<String, String> idOrden, boolean edicion) throws DelegateException {

        DocumentoResumen documentResumen;
        DocumentoResumenDelegate docDelegate = DelegateUtil.getDocumentoResumenDelegate();
        List<Documento> documents = new ArrayList<Documento>();
        Map <String,String[]> actulitzadorMap = new HashMap<String, String[]>();

        // Obtenim  els documents i els seus ordres
        for (String docId : docsIds) {
            Long idDoc = ParseUtil.parseLong(docId);
            if (idDoc != null) {
                documentResumen = docDelegate.obtenerDocumentoResumen(idDoc);
                Documento doc = new Documento();
                doc.setId(documentResumen.getId());
                doc.setFicha(documentResumen.getFicha());
                doc.setOrden(documentResumen.getOrden());
                doc.setProcedimiento(documentResumen.getProcedimiento());
                doc.setTraduccionMap(documentResumen.getTraduccionMap());
                documents.add(doc);
                // Se coge el orden de la web. Si se quisiesen poner del 0 al x, hacer que orden valga 0 e ir incrementandolo.
                String[] orden = {idOrden.get(idDoc.toString())};
                actulitzadorMap.put("orden_doc" + idDoc, orden);

            } else {
                log.warn("S'ha rebut un id de document no númeric: " + idDoc);
            }
        }

        // Actualitzam ordres
        docDelegate.actualizarOrdenDocs(actulitzadorMap);

        if (edicion) {
            boolean eliminarDoc;
            for (Documento docAntiguo : docsOld) {
                eliminarDoc = true;
                for (Documento docNuevo : documents) {
                    if (docAntiguo.getId().equals(docNuevo.getId())) {
                        eliminarDoc = false;
                    }
                }
                if (eliminarDoc) {
                    docDelegate.borrarDocumento(docAntiguo.getId());
                }
            }
        }

        return documents;
    }

    /*
     * Función de grabar() la ficha
     */
    private Long guardarGrabar(Ficha fitxa) throws DelegateException {

    	/* NOTA IMPORTANTE PARA EL RENDIMIENTO */
        fitxa.setDocumentos(null);	// Debemos ponerlo a null para que hibernate no vaya a actualizar todas la relaciones
        /* FIN NOTA */
    	Long idFicha = DelegateUtil.getFichaDelegate().grabarFicha(fitxa);
    	DelegateUtil.getEstadisticaDelegate().grabarEstadisticaFicha(idFicha);

    	return idFicha;
    }

    /*
     * Función que asocia la ficha con la UA y las secciones
     */
    private void guardarSecciosUA(boolean edicion, Ficha fitxaOld, Map<String, String> valoresForm, Long idFitxa) throws DelegateException {

    	FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
    	if (isModuloModificado("modulo_seccionesua_modificado", valoresForm)) {
    		String[] codisSeccUaNous = valoresForm.get("seccUA").split(",");

    		// Paso donde se eliminan las SeccionesUA que han sido eliminadas
    		if (edicion) {
    			List<FichaUA> borrarFichasUA = new ArrayList<FichaUA>();
    			boolean esborrarFichaUA;
    			for (FichaUA fichaUA : fitxaOld.getFichasua()) {
    				esborrarFichaUA = true;
    				for (int i = 0; i < codisSeccUaNous.length; i++) {
    					if (codisSeccUaNous[i] != null) { //Per a no repetir cerques
    						String[] seccUA = codisSeccUaNous[i].split("#"); //En cas d'edicio es necesari verificar si les relacions anteriors se mantenen
    						if (fichaUA.getId().equals(ParseUtil.parseLong(seccUA[0]))) {
    							esborrarFichaUA = false;
    							codisSeccUaNous[i] = null;
    							break;
    						}
    					}
    				}
    				if (esborrarFichaUA) {
                    	borrarFichasUA.add(fichaUA);
                    }
    			}
    			fitxaDelegate.borrarFichasUAdeFicha(borrarFichasUA);
    		}

    		// Paso donde se crean las nuevas SeccionesUA añadidas y que no existian
    		// Tots els que tenen id = -1, son nous i se poden afegir directament
    		for (String codiSeccUa : codisSeccUaNous) {
    			if (codiSeccUa != null) {
    				String[] seccUA = codiSeccUa.split("#");
    				Long idSeccion = ParseUtil.parseLong(seccUA[1]);
    				Long idUA = ParseUtil.parseLong(seccUA[2]);
    				fitxaDelegate.crearFichaUA2(idUA, idSeccion, idFitxa);
    				String pidip = System.getProperty("es.caib.rolsac.pidip");
    				// Si se anyade una ficha a la seccion Actualidad, se añade tambien a Portada Actualidad (PIDIP)
    				if (!((pidip == null) || pidip.equals("N"))) {
    					if (idSeccion.longValue() == new Long(Parametros.ESDEVENIMENTS).longValue()) {
    						//comprobamos  antes si ya exite la ficha en actualidad  en portada en cuyo caso no la insertamos para no duplicarla.
    						boolean existe = false;
    						Long portadas = new Long(Parametros.PORTADAS_ACTUALIDAD);
    						List listac = fitxaDelegate.listarFichasSeccionTodas(portadas);
    						
    						Iterator iter = listac.iterator();
    						while (iter.hasNext()) {
    							Ficha ficac = (Ficha) iter.next();
    							if (("" + ficac.getId()).equals("" + idFitxa)) {
    								existe = true;
    							}
    						}
    						if (!existe) {
    							fitxaDelegate.crearFichaUA2(idUA, portadas, idFitxa);
    						}
    					}
    				}
    			}
    		}
    	}
    }

    /*
     * Función para controlar y guardar los enlaces de una ficha
     */
    private void guardarEnlaces(boolean edicion, Ficha fitxa, Ficha fitxaOld, Map<String, String> valoresForm, Long idFitxa, Set<String> enllasos) throws DelegateException {

    	if (isModuloModificado("modulo_enlaces_modificado", valoresForm)) {
    	    EnlaceDelegate enllasDelegate = DelegateUtil.getEnlaceDelegate();
    	    
    	    // Guardar los nuevos enlaces y actualizar los ya existentes.
    		List<Enlace> enllassosNous = new ArrayList<Enlace>();
    		for (Iterator<String> iterator = enllasos.iterator(); iterator.hasNext();) {
    			String nomParameter = (String) iterator.next();
    			String[] elements = nomParameter.split("_");
    			if (elements[0].equals("enllas") && elements[1].equals("id")) {
    				// En aquest cas, elements[2] es igual al id del enllas
    				Enlace enllas = new Enlace();
    				enllas.setId((elements[2].charAt(0) == 't') ? null : ParseUtil.parseLong(valoresForm.get(nomParameter)));
    				enllas.setOrden(ParseUtil.parseLong(valoresForm.get("enllas_orden_" + elements[2])));
    				for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
    					TraduccionEnlace traduccio = new TraduccionEnlace();
    					traduccio.setTitulo(valoresForm.get("enllas_nombre_" + lang + "_" + elements[2]));
    					traduccio.setEnlace(valoresForm.get("enllas_url_" + lang + "_" + elements[2]));
    					enllas.setTraduccion(lang, traduccio);
    					
    				}
    				enllas.setFicha(fitxa);
    				enllasDelegate.grabarEnlace(enllas, null, idFitxa);
    				enllassosNous.add(enllas);
    			}
    		}
    		
    		// Eliminar los enlaces ya no existentes
    		if (edicion) {
    			List<Enlace> enllassosAntics = fitxaOld.getEnlaces();
    			boolean eliminarEnlace;
    			for (Enlace enllas: enllassosNous) {
    			    eliminarEnlace = true;
    				for (Enlace enlace : enllassosAntics) {
    					if (enlace.getId() == enllas.getId()) {
    					    eliminarEnlace = false;
    					}
    				}
    				if (eliminarEnlace) {
    				    enllasDelegate.borrarEnlace(enllas.getId());
    				}
    			}
    		}
    	}
    }

    /**
     * Devuelve true si ha habido algun cambio en el modulo.
     * 
     * @param modulo
     * @param valoresForm
     * @return boolean
     */
    private boolean isModuloModificado(String modulo, Map<String, String> valoresForm) {
        return "1".equals(valoresForm.get(modulo));
    }


    @RequestMapping(value = "/seccions.do", method = POST)
    public @ResponseBody Map<String, Object> arbreSeccions(HttpServletRequest request) {

    	Map<String, Object> resultats = new HashMap<String, Object>();
    	List<Seccion> llistaSeccions = new ArrayList<Seccion>();
    	List<SeccionDTO> llistaSeccionsDTO = new ArrayList<SeccionDTO>();
    	
    	try {
    		String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
    		SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();

    		if (request.getParameter("pare") == null || "".equals(request.getParameter("pare"))) {
    		    llistaSeccions = seccioDelegate.listarSeccionesRaizPerfil();
    		} else {
    		    llistaSeccions = seccioDelegate.listarHijosSeccion(ParseUtil.parseLong(request.getParameter("pare")));
    		}

    		for (Seccion seccio: llistaSeccions) {
    			TraduccionSeccion tse = (TraduccionSeccion) seccio.getTraduccion(lang);
    			llistaSeccionsDTO.add(new SeccionDTO(
    					seccio.getId(),
    					tse == null ? "" : tse.getNombre(),
    					seccio.getPadre() == null ? null : seccio.getPadre().getId(),
    					seccioDelegate.listarHijosSeccion(seccio.getId()).size() > 0 ? true : false
    			));
    		}
    		resultats.put("llistaSeccions", llistaSeccionsDTO);

    	} catch (DelegateException dEx) {
    		if (dEx.isSecurityException()) {
    		    log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
    		} else {
    		    log.error(ExceptionUtils.getStackTrace(dEx));
    		}
    	}

    	return resultats;
    }


    @RequestMapping(value = "/unitats.do", method = POST)
    public @ResponseBody Map<String, Object> arbreUnitats(HttpServletRequest request) {

    	Map<String, Object> resultats = new HashMap<String, Object>();
    	List<UnidadAdministrativa> llistaUnitats = new ArrayList<UnidadAdministrativa>();
    	List<UnidadDTO> llistaUnitatsDTO = new ArrayList<UnidadDTO>();
    	
    	try {
    		UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();
    		String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

    		if (request.getParameter("pare") == null || "".equals(request.getParameter("pare"))) {
    		    llistaUnitats = unitatDelegate.listarUnidadesAdministrativasRaiz();
    		} else {
    		    llistaUnitats = unitatDelegate.listarHijosUA(ParseUtil.parseLong(request.getParameter("pare")));
    		}

    		for (UnidadAdministrativa unitat: llistaUnitats) {
    			String nomUnitat = unitat.getNombreUnidadAdministrativa(lang);
    			String abreviatura = unitat.getAbreviaturaUnidadAdministrativa(lang);
    			llistaUnitatsDTO.add(new UnidadDTO(
    					unitat.getId(),
    					nomUnitat == null ? "" : nomUnitat,
    					abreviatura == null ? "" : abreviatura,
    					unitat.getPadre() == null ? null : unitat.getPadre().getId(),
    					unitatDelegate.listarHijosUA(unitat.getId()).size() > 0 ? true : false
    			));
    		}

    		resultats.put("llistaUnitats", llistaUnitatsDTO);

    	} catch (DelegateException dEx) {
    		if (dEx.isSecurityException()) {
    		    log.error("Error de persimos: " + ExceptionUtils.getStackTrace(dEx));
    		} else {
    		    log.error(ExceptionUtils.getStackTrace(dEx));
    		}
    	}

    	return resultats;
    }


    @RequestMapping(value = "/esborrarFitxa.do", method = POST)
    public @ResponseBody IdNomDTO esborrarFitxa(HttpServletRequest request) {

    	IdNomDTO resultatStatus = new IdNomDTO();
        try {
            Long id = new Long(request.getParameter("id"));
            FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
            fitxaDelegate.borrarFicha(id);
            resultatStatus.setId(1l);
            resultatStatus.setNom("correcte");

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultatStatus.setId(-1l);
            } else {
                resultatStatus.setId(-2l);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }

        return resultatStatus;
    }


    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {

    	Map<String, Object> resultats = new HashMap<String, Object>();		
		try {
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			TraduccionFicha traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			log.error("FitxaBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("FitxaBackController.traduir: Error en al traducir ficha: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}

		return resultats;
	}

    private TraduccionFicha getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {

		TraduccionFicha traduccioOrigen = new TraduccionFicha();

		if (StringUtils.isNotEmpty(request.getParameter("item_titol_" + idiomaOrigenTraductor))) {
		    traduccioOrigen.setTitulo(request.getParameter("item_titol_" + idiomaOrigenTraductor));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_des_curta_" + idiomaOrigenTraductor))) {
		    traduccioOrigen.setDescAbr(request.getParameter("item_des_curta_" + idiomaOrigenTraductor));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_des_llarga_" + idiomaOrigenTraductor))) {
		    traduccioOrigen.setDescripcion(request.getParameter("item_des_llarga_" + idiomaOrigenTraductor));
		}

		return traduccioOrigen;
	}

}
