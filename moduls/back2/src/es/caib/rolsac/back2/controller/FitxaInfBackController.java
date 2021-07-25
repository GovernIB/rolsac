package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaResumen;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilGestor;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionEnlace;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.dto.EnlaceDTO;
import org.ibit.rol.sac.model.dto.EnlacesFichaDTO;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.dto.FichaUADTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SeccionDTO;
import org.ibit.rol.sac.model.dto.UnidadDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaResumenDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.util.RolsacPropertiesUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.exception.TextNoValidException;
import es.caib.rolsac.back2.util.CSVUtil;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.GuardadoAjaxUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.LlistatUtil;
import es.caib.rolsac.back2.util.Parametros;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@SuppressWarnings("deprecation") // amartin: debido a org.ibit.rol.sac.model.Documento

@Controller
@RequestMapping("/fitxainf/")
public class FitxaInfBackController extends PantallaBaseController {

	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.fitxa.url";
	private static Log log = LogFactory.getLog(FitxaInfBackController.class);

	@RequestMapping(value = "/fitxainf.do", method = GET)
	public String pantallaFitxes(final Map<String, Object> model, final HttpServletRequest request,
			final HttpSession session) {

		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 3);
		model.put("titol_escriptori",
				messageSource.getMessage("submenu.fitxes_informatives", null, request.getLocale()));
		model.put("escriptori", "pantalles/fitxaInf.jsp");
		request.setAttribute("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));

		try {

			final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			if (session.getAttribute("unidadAdministrativa") != null) {
				model.put("idUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getId());
				model.put("nomUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa"))
						.getNombreUnidadAdministrativa(lang));
			}

			model.put("llistaMateries", LlistatUtil.llistarMaterias(lang));

			final HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
			List<HechoVital> llistaFetsVitals = new ArrayList<HechoVital>();
			final List<IdNomDTO> llistaFetsVitalsDTO = new ArrayList<IdNomDTO>();

			llistaFetsVitals = fetVitalDelegate.listarHechosVitales();
			for (final HechoVital fetVital : llistaFetsVitals) {
				final TraduccionHechoVital thv = (TraduccionHechoVital) fetVital.getTraduccion(lang);
				llistaFetsVitalsDTO.add(new IdNomDTO(fetVital.getId(), thv == null ? null : thv.getNombre()));
			}

			model.put("llistaFetsVitals", llistaFetsVitalsDTO);

			/*
			 * PublicoObjetivoDelegate publicObjectiuDelegate =
			 * DelegateUtil.getPublicoObjetivoDelegate(); List<PublicoObjetivo>
			 * llistaPublicsObjectiu = new ArrayList<PublicoObjetivo>(); List<IdNomDTO>
			 * llistaPublicsObjectiuDTO = new ArrayList<IdNomDTO>();
			 * 
			 * llistaPublicsObjectiu = publicObjectiuDelegate.listarPublicoObjetivo(); for
			 * (PublicoObjetivo publicObjectiu : llistaPublicsObjectiu) {
			 * TraduccionPublicoObjetivo tpo = (TraduccionPublicoObjetivo)
			 * publicObjectiu.getTraduccion(lang); llistaPublicsObjectiuDTO.add(new
			 * IdNomDTO(publicObjectiu.getId(), tpo == null ? null : tpo.getTitulo())); }
			 * 
			 * model.put("llistaPublicsObjectiu", llistaPublicsObjectiuDTO);
			 */

			model.put("llistaPublicsObjectiu", LlistatUtil.llistarPublicObjectius(lang));

		} catch (final DelegateException dEx) {

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
	public @ResponseBody Map<String, Object> llistat(final HttpServletRequest request, final HttpSession session) {

		final Map<String, Object> paramMap = new HashMap<String, Object>();
		final Map<String, String> tradMap = new HashMap<String, String>();
		final List<FichaDTO> llistaFitxesDTO = new ArrayList<FichaDTO>();
		final Map<String, Object> resultats = new HashMap<String, Object>();

		UnidadAdministrativa ua = null;
		if (session.getAttribute("unidadAdministrativa") != null) {
			ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
		}

		// Recuperamos los campos directamente des de el request
		final String campoOrdenacion = request.getParameter("ordreCamp"); // Recuperamos el parametro de ordenación por
																			// campo
		final String orden = request.getParameter("ordreTipus"); // Recuperamos el parametro de ordenación por tipo
		final boolean uaFilles = "1".equals(request.getParameter("uaFilles")); // Recuperamos si se debe buscar en las
																				// UAs hijas
		final boolean uaMeves = "1".equals(request.getParameter("uaMeves")); // Recuperamos si se debe buscar en las UAs
																				// propias

		final Long materia = recuperarParametroId(request, "materia"); // Recuperamos el id de la materia
		final Long fetVital = recuperarParametroId(request, "fetVital"); // Recuperamos el id del hecho vital
		final Long publicObjectiu = recuperarParametroId(request, "publicObjectiu"); // Recuperamos el id del público
																						// objetivo
		final String pagPag = recuperarPaginacion(request, "pagPag"); // Recuperamos la página actual
		final String pagRes = recuperarPaginacion(request, "pagRes"); // Recuperamos los resultados por página

		final int campoVisible = recuperarVisibilidad(request, paramMap); // Recuperamos la visibilidad de la ficha

		recuperarCodigo(request, paramMap); // Recuperamos el parametro del código
		recuperarTexto(request, tradMap, paramMap); // Recuperamos el texto y lo buscamos en todos los idiomas
		recuperarValidacio(request, paramMap); // Recuperamos si es válido

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {

			final FichaResumenDelegate fitxaResumenDelegate = DelegateUtil.getFichaResumenDelegate();

			resultadoBusqueda = fitxaResumenDelegate.buscarFichas(paramMap, tradMap, ua, fetVital, materia,
					publicObjectiu, uaFilles, uaMeves, campoOrdenacion, orden, pagPag, pagRes, campoVisible, false);

			for (final FichaResumen fitxaResumen : castList(FichaResumen.class,
					resultadoBusqueda.getListaResultados())) {

				final TraduccionFicha tfi = (TraduccionFicha) fitxaResumen
						.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());

				llistaFitxesDTO.add(
						new FichaDTO(fitxaResumen.getId(), tfi == null ? null : HtmlUtils.html2text(tfi.getTitulo()),
								DateUtils.formatDate(fitxaResumen.getFechaPublicacion()),
								DateUtils.formatDate(fitxaResumen.getFechaCaducidad()),
								DateUtils.formatDate(fitxaResumen.getFechaActualizacion()), fitxaResumen.isVisible()));

			}

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
			} else {
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaFitxesDTO);

		return resultats;

	}

	@RequestMapping(value = "/exportar.do", method = POST)
	public void exportar(final HttpServletRequest request, final HttpSession session,
			final HttpServletResponse response) throws Exception {

		final Map<String, Object> paramMap = new HashMap<String, Object>();
		final Map<String, String> tradMap = new HashMap<String, String>();

		UnidadAdministrativa ua = null;
		if (session.getAttribute("unidadAdministrativa") != null) {
			ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
		}

		// Recuperamos los campos directamente des de el request
		final String campoOrdenacion = request.getParameter("ordreCamp"); // Recuperamos el parametro de ordenación por
																			// campo
		final String orden = request.getParameter("ordreTipus"); // Recuperamos el parametro de ordenación por tipo
		final boolean uaFilles = "1".equals(request.getParameter("uaFilles")); // Recuperamos si se debe buscar en las
																				// UAs hijas
		final boolean uaMeves = "1".equals(request.getParameter("uaMeves")); // Recuperamos si se debe buscar en las UAs
																				// propias

		final Long materia = recuperarParametroId(request, "materia"); // Recuperamos el id de la materia
		final Long fetVital = recuperarParametroId(request, "fetVital"); // Recuperamos el id del hecho vital
		final Long publicObjectiu = recuperarParametroId(request, "publicObjectiu"); // Recuperamos el id del público
																						// objetivo
		final String pagPag = recuperarPaginacion(request, "pagPag"); // Recuperamos la página actual
		final String pagRes = recuperarPaginacion(request, "pagRes"); // Recuperamos los resultados por página

		final int campoVisible = recuperarVisibilidad(request, paramMap); // Recuperamos la visibilidad de la ficha

		recuperarCodigo(request, paramMap); // Recuperamos el parametro del código
		recuperarTexto(request, tradMap, paramMap); // Recuperamos el texto y lo buscamos en todos los idiomas
		recuperarValidacio(request, paramMap); // Recuperamos si es válido

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {

			final FichaResumenDelegate fitxaResumenDelegate = DelegateUtil.getFichaResumenDelegate();

			resultadoBusqueda = fitxaResumenDelegate.buscarFichas(paramMap, tradMap, ua, fetVital, materia,
					publicObjectiu, uaFilles, uaMeves, campoOrdenacion, orden, pagPag, pagRes, campoVisible, true);

			CSVUtil.mostrarCSV(response,
					convertirFitxaToCSV((List<Object[]>) resultadoBusqueda.getListaResultados(), ua, uaFilles));

		} catch (final Exception dEx) {
			log.error("Error generando el export de la búsqueda en fitxas.", dEx);
			throw new Exception(dEx);
		}
	}

	/**
	 * Convierte procedimiento a String para CSV.
	 * 
	 * @param listaResultados
	 * @return
	 */
	private String convertirFitxaToCSV(final List<Object[]> listaResultados, final UnidadAdministrativa ua,
			final boolean uaFilles) {
		final StringBuffer retorno = new StringBuffer();

		// cabecera!
		retorno.append("CODI_FITXA;");
		retorno.append("NOM_FITXA_CA;");
		retorno.append("NOM_FITXA_ES;");
		retorno.append("ESTAT_FITXA;");// DECODE(FIC_VALIDA,1,'PUBLIC',2,'INTERN','RESERVA')
		retorno.append("VISIBILITAT_FITXA;");// (ESTAT+DATA_PUB+DATA_CAD + UA_VISIBLE)
		retorno.append("PUBLIC_OBJECTIU;");// (ID_PUBLIC OBJECTIU SEPARATS PER COMES)
		retorno.append("NOM UA;");
		retorno.append("DATA_ACTUALITZACIO;");
		retorno.append("\n");

		final FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
		for (final Object[] resultado : listaResultados) {
			Ficha ficha;
			final Long idFitxa = Long.valueOf(resultado[0].toString());
			try {
				ficha = fitxaDelegate.obtenerFichaParaSolr(idFitxa, null);
			} catch (final Exception exception) {
				log.error("Error obteniendo la ficha con id : " + idFitxa, exception);
				retorno.append(CSVUtil.limpiar(idFitxa));
				retorno.append(ExceptionUtils.getCause(exception));
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}

			// Extraemos datos.
			final TraduccionFicha tradEs = (TraduccionFicha) ficha.getTraduccion("es");
			final TraduccionFicha tradCa = (TraduccionFicha) ficha.getTraduccion("ca");

			String nomEs, nomCa;
			if (tradEs == null) {
				nomEs = "";
			} else {
				nomEs = tradEs.getTitulo();
			}

			if (tradCa == null) {
				nomCa = "";
			} else {
				nomCa = tradCa.getTitulo();
			}

			String publicoObjectivo = "";
			if (ficha.getPublicosObjetivo() != null) {
				for (final PublicoObjetivo po : ficha.getPublicosObjetivo()) {
					publicoObjectivo += po.getId() + " ,";
				}

				if (publicoObjectivo.endsWith(",")) {
					publicoObjectivo = publicoObjectivo.substring(0, publicoObjectivo.length() - 1);
				}
			}

			String estado;
			if (ficha.getValidacion().compareTo(1) == 0) {
				estado = "PUBLIC";
			} else if (ficha.getValidacion().compareTo(2) == 0) {
				estado = "INTERN";
			} else {
				estado = "RESERVA";
			}

			String nomUA = " ";
			if (uaFilles) {
				/***
				 * En caso de estar lo de ua FIlles se realiza los siguientes pasos: - Primero
				 * ver si está la UA principal en alguna de las fichasUA. - Si no está, se busca
				 * en cualquier de las predecesores, y cuando se encuentre, se pone el nombre de
				 * la UA hoja.
				 */
				final Set<FichaUA> fichasUA = ficha.getFichasua();
				if (fichasUA != null) {
					boolean noEncontradoUA = true;
					for (final FichaUA fichaUA : fichasUA) {
						if (fichaUA.getUnidadAdministrativa() != null) {
							if (ua.getId().compareTo(fichaUA.getUnidadAdministrativa().getId()) == 0) {
								nomUA = CSVUtil.getNombreUA(ua);
								noEncontradoUA = false;
								break;
							}
						}
					}

					if (noEncontradoUA) {
						boolean salir = false;
						for (final FichaUA fichaUA : fichasUA) {
							if (salir) {
								break;
							}
							if (fichaUA.getUnidadAdministrativa() != null
									&& fichaUA.getUnidadAdministrativa().getPredecesores() != null) {
								final List<UnidadAdministrativa> predecesores = fichaUA.getUnidadAdministrativa()
										.getPredecesores();
								for (final UnidadAdministrativa uaPredecesor : predecesores) {
									if (ua.getId().compareTo(uaPredecesor.getId()) == 0) {
										nomUA = CSVUtil.getNombreUA(fichaUA.getUnidadAdministrativa()); // Ponemos la UA
																										// hoja
																										// seleccionada.
										salir = true;
										break;
									}
								}
							}
						}
					}
				}
			} else {
				nomUA = CSVUtil.getNombreUA(ua);
			}

			retorno.append(CSVUtil.limpiar(ficha.getId())); // CODI_FITXA,
			retorno.append(CSVUtil.limpiar(nomCa)); // NOM_FITXA_CA,
			retorno.append(CSVUtil.limpiar(nomEs)); // NOM_FITXA_CA,
			retorno.append(CSVUtil.limpiar(estado)); // ESTAT_FITXA DECODE(FIC_VALIDA,1,'PUBLIC',2,'INTERN','RESERVA'),
			retorno.append(CSVUtil.limpiar(ficha.isVisible())); // VISIBILITAT_FITXA (ESTAT+DATA_PUB+DATA_CAD +
																// UA_VISIBLE)
			retorno.append(CSVUtil.limpiar(publicoObjectivo)); // (ID_PUBLIC OBJECTIU SEPARATS PER COMES)
			retorno.append(CSVUtil.limpiar(nomUA)); // NOM UA
			retorno.append(CSVUtil.limpiar(ficha.getFechaActualizacion())); // DATA_ACTUALITZACIO
			retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
		}

		return retorno.toString();
	}

	/*
	 * Recuperamos el campo del código
	 */
	private void recuperarCodigo(final HttpServletRequest request, final Map<String, Object> paramMap) {

		final String idStr = request.getParameter("codi");
		Long id = -1l;

		if (idStr != null && StringUtils.isNumeric(idStr.trim())) {
			id = ParseUtil.parseLong(idStr.trim());
		}

		paramMap.put("id", idStr != null ? id : null);

	}

	/*
	 * Recuperamos el estado de la ficha para la validación
	 */
	private void recuperarValidacio(final HttpServletRequest request, final Map<String, Object> paramMap) {

		if (request.isUserInRole("sacoper")) {

			// En el back antiguo estaba asi.
			paramMap.put("validacion", "");

		} else {

			final String estat = request.getParameter("estat");
			try {
				final Integer validacion = Integer.parseInt(estat);
				paramMap.put("validacion", validacion);
			} catch (final NumberFormatException e) {
			}

		}

	}

	/*
	 * Recuperar id por parametro del request
	 */
	private Long recuperarParametroId(final HttpServletRequest request, final String parametro) {

		try {
			return Long.parseLong(request.getParameter(parametro));
		} catch (final NumberFormatException e) {
			return null;
		}

	}

	/*
	 * Recuperamos el campo texto para las traducciones
	 */
	private void recuperarTexto(final HttpServletRequest request, final Map<String, String> tradMap,
			final Map<String, Object> paramMap) {

		String textes = request.getParameter("textes");

		if (textes != null && !"".equals(textes)) {

			paramMap.put("textes", textes);

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
			} catch (final DelegateException dEx) {
				logException(log, dEx);
			}

		}

	}

	/*
	 * Recuperamos la visibilidad del request
	 */
	private int recuperarVisibilidad(final HttpServletRequest request, final Map<String, Object> paramMap) {

		final String visibilitat = request.getParameter("visibilitat");

		if (visibilitat != null) {

			if (visibilitat.equals("1")) {
				final Integer visible = Integer.parseInt(visibilitat);
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
	private String recuperarPaginacion(final HttpServletRequest request, final String parametro) {

		String pagina = request.getParameter(parametro);

		if (pagina == null)
			pagina = String.valueOf(0);

		return pagina;

	}

	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(final Long id, final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			final FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
			final Ficha fitxa = fitxaDelegate.obtenerFicha(id);

			resultats.put("item_id", fitxa.getId());
			resultats.put("item_estat", fitxa.getValidacion());
			resultats.put("item_data_publicacio", DateUtils.formatDateSimpleTime(fitxa.getFechaPublicacion()));
			resultats.put("item_data_caducitat", DateUtils.formatDateSimpleTime(fitxa.getFechaCaducidad()));
			resultats.put("item_responsable", fitxa.getResponsable());
			resultats.put("item_notes", fitxa.getInfo());

			recuperaIdioma(resultats, fitxa, lang); // Recuperar las fichas según el idioma.
			recuperaIcono(resultats, fitxa); // Recuperar el icono de una ficha.
			recuperaBanner(resultats, fitxa); // Recuperar los banners de una ficha.
			recuperaImatge(resultats, fitxa); // Recuperar la imagen de una ficha.
			recuperaPO(resultats, fitxa, lang); // Recuperar los públicos objetiovs de una ficha.
			recuperaRelacio(resultats, fitxa, lang); // Recuperar las relaciones ficha-sección-UA
			permisDuplicacio(resultats, request); // Determina si l'usuari pot duplicar la fitxa

		} catch (final DelegateException dEx) {

			log.error("Error: " + ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}

		}

		return resultats;

	}

	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(final Long id, final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			final List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			final FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
			final Ficha ficha = fitxaDelegate.obtenerFicha(id);

			// Pasamos el Set de materias relacionados a un List.
			final List<Materia> listaMaterias = new ArrayList<Materia>(ficha.getMaterias());
			resultats.put("materies",
					CargaModulosLateralesUtil.recuperaMateriasRelacionadas(listaMaterias, id, lang, false));

			// Pasamos el set de HHVV relacionados a un List.
			final List<HechoVital> listaHechosVitales = new ArrayList<HechoVital>(ficha.getHechosVitales());
			resultats.put("fetsVitals", CargaModulosLateralesUtil.recuperaHechosVitalesRelacionados(listaHechosVitales,
					HechoVital.class, id, lang, false));

			// Documentos relacionados.
			final List<Documento> listaDocumentos = ficha.getDocumentos();
			resultats.put("documents",
					CargaModulosLateralesUtil.recuperaDocumentosRelacionados(listaDocumentos, id, idiomas, true));

			recuperaEnllasos(resultats, ficha); // Recuperar los enlaces de una ficha.

		} catch (final DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}

	/*
	 * Función que recupera el contenido de las fichas según el idioma
	 */
	private void recuperaIdioma(final Map<String, Object> resultats, final Ficha fitxa, final String langDefault)
			throws DelegateException {

		final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		final List<String> langs = idiomaDelegate.listarLenguajes();

		for (final String lang : langs) {
			final HashMap<String, String> traduccionFichaDTO = new HashMap<String, String>();

			if (null != fitxa.getTraduccion(lang)) {

				final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);

				obtenerArchivosTraduccion(resultats, fitxa, lang, traduccionFichaDTO, tradF);

			} else if (fitxa.getTraduccion(langDefault) != null) {

				final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(langDefault);
				tradF.setImagen(null);
				tradF.setBaner(null);
				tradF.setIcono(null);
				obtenerArchivosTraduccion(resultats, fitxa, lang, traduccionFichaDTO, tradF);

			} else {

				resultats.put(lang, new HashMap<String, String>());

			}

		}

	}

	/**
	 * @param resultats
	 * @param fitxa
	 * @param lang
	 * @param traduccionFichaDTO
	 * @param tradF
	 */
	private void obtenerArchivosTraduccion(final Map<String, Object> resultats, final Ficha fitxa, final String lang,
			final HashMap<String, String> traduccionFichaDTO, final TraduccionFicha tradF) {
		traduccionFichaDTO.put("titulo", tradF.getTitulo());
		traduccionFichaDTO.put("descAbr", tradF.getDescAbr());
		traduccionFichaDTO.put("descripcion", tradF.getDescripcion());

		traduccionFichaDTO.put("url", tradF.getUrl());
		traduccionFichaDTO.put("urlVideo", tradF.getUrlVideo());
		traduccionFichaDTO.put("urlForo", tradF.getUrlForo());

		if (tradF.getIcono() != null) {
			traduccionFichaDTO.put("item_icona_enllas_arxiu",
					"fitxainf/archivo.do?id=" + fitxa.getId() + "&lang=" + lang + "&tipus=1");
			traduccionFichaDTO.put("item_icona", tradF.getIcono().getNombre());
		} else {
			traduccionFichaDTO.put("item_icona_enllas_arxiu", "");
			traduccionFichaDTO.put("item_icona", "");
		}

		if (tradF.getBaner() != null) {
			traduccionFichaDTO.put("item_banner_enllas_arxiu",
					"fitxainf/archivo.do?id=" + fitxa.getId() + "&lang=" + lang + "&tipus=2");
			traduccionFichaDTO.put("item_banner", tradF.getBaner().getNombre());
		} else {
			traduccionFichaDTO.put("item_banner_enllas_arxiu", "");
			traduccionFichaDTO.put("item_banner", "");
		}

		if (tradF.getImagen() != null) {
			traduccionFichaDTO.put("item_imatge_enllas_arxiu",
					"fitxainf/archivo.do?id=" + fitxa.getId() + "&lang=" + lang + "&tipus=3");
			traduccionFichaDTO.put("item_imatge", tradF.getImagen().getNombre());
		} else {
			traduccionFichaDTO.put("item_imatge_enllas_arxiu", "");
			traduccionFichaDTO.put("item_imatge", "");
		}

		resultats.put(lang, traduccionFichaDTO);
	}

	/*
	 * Función para recuperar el icono.
	 */
	private void recuperaIcono(final Map<String, Object> resultats, final Ficha fitxa) throws DelegateException {

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);
			if (tradF != null && tradF.getIcono() != null) {
				resultats.put("item_icona_enllas_arxiu",
						"fitxainf/archivo.do?id=" + fitxa.getId() + "&lang=" + lang + "&tipus=1");
				resultats.put("item_icona", tradF.getIcono().getNombre());
			} else {
				resultats.put("item_icona_enllas_arxiu", "");
				resultats.put("item_icona", "");
			}
		}

	}

	/*
	 * Función para recuperar el banner
	 */
	private void recuperaBanner(final Map<String, Object> resultats, final Ficha fitxa) throws DelegateException {

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);
			if (tradF != null && tradF.getBaner() != null) {
				resultats.put("item_banner_enllas_arxiu",
						"fitxainf/archivo.do?id=" + fitxa.getId() + "&lang=" + lang + "&tipus=2");
				resultats.put("item_banner", tradF.getBaner().getNombre());
			} else {
				resultats.put("item_banner_enllas_arxiu", "");
				resultats.put("item_banner", "");
			}
		}

	}

	/*
	 * Función para recuperar la imagen de una ficha.
	 */
	private void recuperaImatge(final Map<String, Object> resultats, final Ficha fitxa) throws DelegateException {

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);
			if (tradF != null && tradF.getImagen() != null) {
				resultats.put("item_imatge_enllas_arxiu",
						"fitxainf/archivo.do?id=" + fitxa.getId() + "&lang=" + lang + "&tipus=3");
				resultats.put("item_imatge", tradF.getImagen().getNombre());
			} else {
				resultats.put("item_imatge_enllas_arxiu", "");
				resultats.put("item_imatge", "");
			}
		}

	}

	/*
	 * Función para recuperar el público objetivo
	 */
	private void recuperaPO(final Map<String, Object> resultats, final Ficha fitxa, final String lang) {

		final List<IdNomDTO> llistaPublicObjectiuDTO = new ArrayList<IdNomDTO>();

		if (fitxa.getPublicosObjetivo() != null) {

			for (final PublicoObjetivo publicObj : fitxa.getPublicosObjetivo()) {
				final TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo) publicObj.getTraduccion(lang);
				llistaPublicObjectiuDTO.add(new IdNomDTO(publicObj.getId(), tpob == null ? "" : tpob.getTitulo()));
			}

			resultats.put("publicsObjectiu", llistaPublicObjectiuDTO);

		} else {

			resultats.put("publicsObjectiu", null);

		}

	}

	/*
	 * Función para recuperar si el usuario puede duplicar la ficha
	 */
	private void permisDuplicacio(final Map<String, Object> resultats, final HttpServletRequest request)
			throws DelegateException {

		final UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
		boolean permis = false;
		final Usuario usuari = usuariDelegate.obtenerUsuariobyUsername(request.getRemoteUser());
		final List<PerfilGestor> listaPerfilGestor = new ArrayList<PerfilGestor>();
		for (final PerfilGestor perfil : usuari.getPerfilsGestor()) {
			if (perfil.getDuplica() != null && perfil.getDuplica().equals("1")) {
				permis = true;
			}
		}

		resultats.put("permisDuplicacio", permis);
	}

	/*
	 * Función para recuperar la relación Ficha-Sección-UA
	 */
	private void recuperaRelacio(final Map<String, Object> resultats, final Ficha fitxa, final String lang)
			throws DelegateException {

		final List<FichaUADTO> llistaFichaUADTO = new ArrayList<FichaUADTO>();

		if (fitxa.getFichasua() != null) {

			for (final FichaUA fichaUA : DelegateUtil.getFichaDelegate().listFichasUA(fitxa.getId())) {

				final TraduccionSeccion tse = (TraduccionSeccion) fichaUA.getSeccion().getTraduccion(lang);

				llistaFichaUADTO.add(new FichaUADTO(fichaUA.getId(), fichaUA.getUnidadAdministrativa().getId(),
						fichaUA.getUnidadAdministrativa().getNombreUnidadAdministrativa(lang),
						fichaUA.getSeccion().getId(), tse == null ? "" : tse.getNombre(), null, null,
						fichaUA.getOrden(), fichaUA.getOrdenseccion()));

			}

			resultats.put("seccUA", llistaFichaUADTO);

		} else {

			resultats.put("seccUA", null);

		}

	}

	/*
	 * Función para recuperar los enlaces de una ficha.
	 */
	private void recuperaEnllasos(final Map<String, Object> resultats, final Ficha ficha) {

		final List<EnlaceDTO> llistaEnlacesDTO = new ArrayList<EnlaceDTO>();

		if (ficha.getEnlaces() != null) {

			for (final Enlace enlace : ficha.getEnlaces()) {

				if (enlace != null) {

					llistaEnlacesDTO.add(new EnlaceDTO(enlace.getId().toString(), enlace.getOrden(),
							enlace.getTraduccionMap(), ficha.getId(), enlace.getId().toString()));

				}

			}

			resultats.put("enllassos", llistaEnlacesDTO);

		} else {

			resultats.put("enllassos", null);

		}

	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public ResponseEntity<String> guardar(final HttpSession session, final HttpServletRequest request) {

		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox. Si no
		 * se fuerza el content type Spring lo calcula y curiosamente depende del
		 * navegador desde el que se hace la petici�n. Esto se debe a que como esta
		 * petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan
		 * la respuesta como un descargable o fichero vinculado a una aplicaci�n. De
		 * esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la
		 * respuesta.
		 */
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;

		try {

			// Aqui nos llegaría un multipart, de modo que no podemos obtener los datos
			// mediante request.getParameter().
			// Iremos recopilando los parametros de tipo fichero en el Map ficherosForm y el
			// resto en valoresForm.
			final Map<String, String> valoresForm = new HashMap<String, String>();
			final Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
			final Set<String> enllasos = new HashSet<String>();
			final List<String> docsIds = new ArrayList<String>();

			final List<FileItem> items = castList(FileItem.class,
					UploadUtil.obtenerServletFileUpload().parseRequest(request));

			for (final FileItem item : items) {

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
			final Ficha fitxaOld = guardarFitxaAntigua(valoresForm);

			// Comprobación de si se trata de una edición de una ficha ya existente o de una
			// nueva ficha
			final boolean edicion = (fitxaOld != null) ? true : false;

			Ficha fitxa = new Ficha();

			// Recuperación de los nuevos valores de una ficha, tanto si es edición como una
			// ficha nueva
			fitxa = guardarValidacion(request, fitxa, fitxaOld, valoresForm); // Es comprova que l'estat es un estat
																				// permes i es recupera
			fitxa = guardarAntiguo(edicion, fitxa, fitxaOld); // Guardamos los campos de la ficha en caso de que sea una
																// edición y no una ficha nueva
			fitxa = guardarFechaPublicacion(fitxa, valoresForm); // Recuperamos y controlamos el valor de la fecha de
																	// publicación
			fitxa = guardarFechaCaducidad(fitxa, valoresForm); // Recuperamos y controlamos el valor de la fecha de
																// caducidad
			fitxa = guardarIdiomas(edicion, fitxa, valoresForm); // Recuperamos y controlamos las traducciones de la
																	// ficha
			fitxa = guardarIcono(fitxa, valoresForm, ficherosForm, request.getLocale()); // Controlamos los cambios del
																							// icono
			fitxa = guardarBanner(fitxa, valoresForm, ficherosForm, request.getLocale()); // Controlamos los cambios del
																							// banner
			fitxa = guardarImatge(fitxa, valoresForm, ficherosForm, request.getLocale()); // Controlamos los cambios de
																							// la imagen
			fitxa = guardarPublicoObjetivo(edicion, fitxa, fitxaOld, valoresForm); // Controlamos los públicos objetivos
																					// modificados o incluidos

			fitxa.setFechaActualizacion(new Date()); // Guardamos la fecha actual al ser la última actualización
			fitxa.setResponsable(valoresForm.get("item_responsable")); // Guardamos el responsable de la ficha
			fitxa.setInfo(valoresForm.get("item_notes")); // Guardamos el campo de la información
			// Fin recuperación de los valores

			final Long idFitxa = guardarGrabar(fitxa); // Guardar los cambios de una ficha

			// Guardado de las relaciones de una ficha con otras entidades
			guardarSeccionessUA(edicion, fitxaOld, valoresForm, idFitxa); // Guardamos las relaciones de la ficha con
																			// las secciones y las UAs
			// Fin guardado relaciones

			// Finalitzat correctament
			result = new IdNomDTO(fitxa.getId(),
					messageSource.getMessage("fitxes.guardat.correcte", null, request.getLocale()));

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		} catch (final FileUploadException e) {

			error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));

		} catch (final UnsupportedEncodingException e) {

			error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));

		} catch (final NumberFormatException nfe) {

			error = messageSource.getMessage("proc.error.estat.incorrecte", null, request.getLocale());
			result = new IdNomDTO(-3l, error);

		} catch (final ParseException pe) {

			error = messageSource.getMessage(pe.getMessage(), null, request.getLocale());
			result = new IdNomDTO(-4l, error);

		} catch (final IOException e) {

			result = new IdNomDTO(-5l, e.getMessage());

		} catch (final TextNoValidException exception) {

			error = messageSource.getMessage("fitxer.error.texte.incorrecte", null, request.getLocale());
			if (exception.getCampo() != null && !exception.getCampo().isEmpty()) {
				error += " " + messageSource.getMessage(exception.getCampo(), null, request.getLocale());
			}
			result = new IdNomDTO(-6l, error);
		}

		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);

	}

	/*
	 * Función que comprueba los campos obligatorios
	 */
	private IdNomDTO guardarControlCampos(final HttpServletRequest request, final Map<String, String> valoresForm)
			throws DelegateException {

		String error;
		// Se cambia el "item_titol_ca" por el "item_titol_" + idioma
		final String titolCatala = valoresForm
				.get("item_titol_" + DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());

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
	 * Controlamos si se trata de una nueva ficha o es la edición de una ya
	 * existente
	 */
	private Ficha guardarFitxaAntigua(final Map<String, String> valoresForm) throws DelegateException {

		Ficha fitxaOld = null;
		final Long id = ParseUtil.parseLong(valoresForm.get("item_id"));

		if (id != null) {
			fitxaOld = DelegateUtil.getFichaDelegate().obtenerFicha(id);
		} else {
			fitxaOld = null;
		}

		return fitxaOld;

	}

	/*
	 * Recuperación y comprobación de si una ficha esta en un estado valido, es
	 * decir que este en un estado permitido
	 */
	private Ficha guardarValidacion(final HttpServletRequest request, final Ficha fitxa, final Ficha fitxaOld,
			final Map<String, String> valoresForm) throws DelegateException {

		final Integer validacion = Integer.parseInt(valoresForm.get("item_estat"));

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
	private Ficha guardarAntiguo(final boolean edicion, final Ficha fitxa, final Ficha fitxaOld)
			throws DelegateException {

		if (edicion) {

			// Mantenim els valors que te la fitxa.
			fitxa.setId(fitxaOld.getId());
			// fitxa.setBaner(fitxaOld.getBaner());
			// fitxa.setIcono(fitxaOld.getIcono());
			// fitxa.setImagen(fitxaOld.getImagen());
			fitxa.setResponsable(fitxaOld.getResponsable());
			fitxa.setForo_tema(fitxaOld.getForo_tema());
			fitxa.setFichasua(fitxaOld.getFichasua());
			fitxa.setDocumentos(fitxaOld.getDocumentos());
			fitxa.setEnlaces(fitxaOld.getEnlaces());
			fitxa.setMaterias(fitxaOld.getMaterias());
			fitxa.setHechosVitales(fitxaOld.getHechosVitales());
			fitxa.setPublicosObjetivo(fitxaOld.getPublicosObjetivo());

			for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
				final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang) == null
						? new TraduccionFicha()
						: (TraduccionFicha) fitxa.getTraduccion(lang);
				final TraduccionFicha tradOld = (TraduccionFicha) fitxaOld.getTraduccion(lang);

				if (tradOld != null) {
					tradF.setBaner(tradOld.getBaner());
					tradF.setIcono(tradOld.getIcono());
					tradF.setImagen(tradOld.getImagen());
				}

				fitxa.setTraduccion(lang, tradF);
			}

		}

		return fitxa;

	}

	/*
	 * Controlamos las modificaciones en la fecha de publicación
	 */
	private Ficha guardarFechaPublicacion(final Ficha fitxa, final Map<String, String> valoresForm)
			throws ParseException {

		if (!StringUtils.isEmpty(valoresForm.get("item_data_publicacio"))) {

			final Date data_publicacio = DateUtils.parseDateSimpleTime(valoresForm.get("item_data_publicacio"));
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
	private Ficha guardarFechaCaducidad(final Ficha fitxa, final Map<String, String> valoresForm)
			throws ParseException {

		if (!StringUtils.isEmpty(valoresForm.get("item_data_caducitat"))) {

			final Date data_caducitat = DateUtils.parseDateSimpleTime(valoresForm.get("item_data_caducitat"));

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
	private Ficha guardarIdiomas(final boolean edicion, final Ficha fitxa, final Map<String, String> valoresForm)
			throws DelegateException, TextNoValidException {

		TraduccionFicha tfi;

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

			tfi = (TraduccionFicha) ((fitxa != null) ? fitxa.getTraduccion(lang) : new TraduccionProcedimientoLocal());

			if (tfi == null) {
				tfi = new TraduccionFicha();
			}

			checkTextoValido(RolUtil.limpiaCadena(valoresForm.get("item_titol_" + lang)), "fitxes.formulari.titol");
			checkTextoValido(RolUtil.limpiaCadena(valoresForm.get("item_des_curta_" + lang)),
					"fitxes.formulari.descripcio.abreviada");
			checkTextoValido(RolUtil.limpiaCadena(valoresForm.get("item_des_llarga_" + lang)),
					"fitxes.formulari.descripcio.extensa");

			tfi.setTitulo(RolUtil.limpiaCadena(valoresForm.get("item_titol_" + lang)));
			tfi.setDescAbr(RolUtil.limpiaCadena(valoresForm.get("item_des_curta_" + lang)));
			tfi.setDescripcion(RolUtil.limpiaCadena(valoresForm.get("item_des_llarga_" + lang)));
			tfi.setUrl(valoresForm.get("item_url_" + lang));
			tfi.setUrlForo(valoresForm.get("item_forum_" + lang));
			tfi.setUrlVideo(valoresForm.get("item_youtube_" + lang));
			fitxa.setTraduccion(lang, tfi);

		}

		return fitxa;

	}

	private void checkTextoValido(final String texto, final String parametro) throws TextNoValidException {
		final int length = texto.length();
		char character;
		for (int i = 0; i < length; i++) {
			character = texto.charAt(i);
			switch (character) {
			case '"':
			case '&':
			case '<':
			case '>':
			case '\n':
			case '\r':
			case '\t':
				break;
			default:
				if (character < 0x20) {
					throw new TextNoValidException(parametro);
				}
				break;
			}
		}
	}

	/*
	 * Controlamos el icono de la ficha
	 */
	private Ficha guardarIcono(final Ficha fitxa, final Map<String, String> valoresForm,
			final Map<String, FileItem> ficherosForm, final Locale locale) throws DelegateException, IOException {

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

			final FileItem fileIcona = ficherosForm.get("item_icona_" + lang);
			final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);
			if (fileIcona != null && fileIcona.getSize() > 0) {

				// La extensión svg se descarta en las comprobaciones.
				if (!fileIcona.getName().endsWith(".svg")) {
					if (RolsacPropertiesUtil.getControlProporciones()) {
						final BufferedImage bimg = obtenerBufferedImage(fileIcona);
						final int width = bimg.getWidth();
						final int height = bimg.getHeight();

						final int hIcono = RolsacPropertiesUtil.getAltoIcono();
						final int wIcono = RolsacPropertiesUtil.getAnchoIcono();

						if (height > hIcono || width > wIcono) {
							String mensaje = messageSource.getMessage("error.tam.icon", null, locale);
							mensaje = mensaje.replace("%HEIGHT%", String.valueOf(hIcono));
							mensaje = mensaje.replace("%WIDTH%", String.valueOf(wIcono));

							throw new IOException(mensaje);
						}
					}
				}

				tradF.setIcono(UploadUtil.obtenerArchivo(tradF.getIcono(), fileIcona));
			} else if (valoresForm.get("item_icona_" + lang + "_delete") != null
					&& !"".equals(valoresForm.get("item_icona_" + lang + "_delete"))) {
				// borrar fichero si se solicita
				tradF.setIcono(null);
			}
		}

		return fitxa;

	}

	/**
	 * Obtiene un BufferedImage a partir de un FileItem
	 * 
	 * @param fileIcona
	 * @return
	 * @throws IOException
	 */
	private BufferedImage obtenerBufferedImage(final FileItem fileIcona) throws IOException {
		final InputStream in = new ByteArrayInputStream(fileIcona.get());
		final BufferedImage bimg = ImageIO.read(in);

		return bimg;
	}

	/*
	 * Controlamos las modificaciones del banner
	 */
	private Ficha guardarBanner(final Ficha fitxa, final Map<String, String> valoresForm,
			final Map<String, FileItem> ficherosForm, final Locale locale) throws DelegateException, IOException {

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			final FileItem fileBanner = ficherosForm.get("item_banner_" + lang);
			final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);

			if (fileBanner != null && fileBanner.getSize() > 0) {

				// La extensión svg se descarta en las comprobaciones.
				if (!fileBanner.getName().endsWith(".svg")) {
					if (RolsacPropertiesUtil.getControlProporciones()) {
						final BufferedImage bimg = obtenerBufferedImage(fileBanner);
						final int width = bimg.getWidth();
						final int height = bimg.getHeight();

						final int hBanner = RolsacPropertiesUtil.getAltoBanner();
						final int wBanner = RolsacPropertiesUtil.getAnchoBanner();

						if (height > hBanner || width > wBanner) {
							String mensaje = messageSource.getMessage("error.tam.banner", null, locale);
							mensaje = mensaje.replace("%HEIGHT%", String.valueOf(hBanner));
							mensaje = mensaje.replace("%WIDTH%", String.valueOf(wBanner));

							throw new IOException(mensaje);
						}
					}
				}

				tradF.setBaner(UploadUtil.obtenerArchivo(tradF.getBaner(), fileBanner));
			} else if (valoresForm.get("item_banner_" + lang + "_delete") != null
					&& !"".equals(valoresForm.get("item_banner_" + lang + "_delete"))) {
				// borrar fichero si se solicita
				tradF.setBaner(null);
			}
		}
		return fitxa;

	}

	/*
	 * Controlamos las modificaciones de la imagen
	 */
	private Ficha guardarImatge(final Ficha fitxa, final Map<String, String> valoresForm,
			final Map<String, FileItem> ficherosForm, final Locale locale) throws DelegateException, IOException {

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

			final FileItem fileImatge = ficherosForm.get("item_imatge_" + lang);
			final TraduccionFicha tradF = (TraduccionFicha) fitxa.getTraduccion(lang);

			if (fileImatge != null && fileImatge.getSize() > 0) {

				// La extensión svg se descarta en las comprobaciones.
				if (!fileImatge.getName().endsWith(".svg")) {
					if (RolsacPropertiesUtil.getControlProporciones()) {
						final BufferedImage bimg = obtenerBufferedImage(fileImatge);
						final int width = bimg.getWidth();
						final int height = bimg.getHeight();

						final int hImagen = RolsacPropertiesUtil.getAltoImagen();
						final int wImagen = RolsacPropertiesUtil.getAnchoImagen();

						if (height > hImagen || width > wImagen) {
							String mensaje = messageSource.getMessage("error.tam.imatge", null, locale);
							mensaje = mensaje.replace("%HEIGHT%", String.valueOf(hImagen));
							mensaje = mensaje.replace("%WIDTH%", String.valueOf(wImagen));
							throw new IOException(mensaje);
						}
					}
				}

				tradF.setImagen(UploadUtil.obtenerArchivo(tradF.getImagen(), fileImatge));
			} else if (valoresForm.get("item_imatge_" + lang + "_delete") != null
					&& !"".equals(valoresForm.get("item_imatge_" + lang + "_delete"))) {
				// borrar fichero si se solicita
				tradF.setImagen(null);
			}

		}
		return fitxa;

	}

	/*
	 * Controlamos los públicos objetivos modificados o incluidos
	 */
	private Ficha guardarPublicoObjetivo(final boolean edicion, final Ficha fitxa, final Ficha fitxaOld,
			final Map<String, String> valoresForm) throws DelegateException {

		if (isModuloModificado("modul_public_modificat", valoresForm)) {

			if (valoresForm.get("publicsObjectiu") != null && !"".equals(valoresForm.get("publicsObjectiu"))) {

				final String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				final PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
				final Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
				publicsNous.addAll(
						publicObjDelegate.obtenerPublicosObjetivoPorIDs(valoresForm.get("publicsObjectiu"), idioma));
				fitxa.setPublicosObjetivo(publicsNous);

			} else {

				fitxa.setPublicosObjetivo(new HashSet<PublicoObjetivo>());

			}

		}

		return fitxa;

	}

	/*
	 * Función de grabar() la ficha
	 */
	private Long guardarGrabar(final Ficha fitxa) throws DelegateException {

		/* XXX: NOTA IMPORTANTE PARA EL RENDIMIENTO */
		fitxa.setDocumentos(null); // Debemos ponerlo a null para que hibernate no vaya a actualizar todas la
									// relaciones
		/* FIN NOTA */

		final Long idFicha = DelegateUtil.getFichaDelegate().grabarFicha(fitxa);
		// DelegateUtil.getEstadisticaDelegate().grabarEstadisticaFicha(idFicha);

		return idFicha;

	}

	/*
	 * Función que asocia la ficha con la UA y las secciones
	 */
	private void guardarSeccionessUA(final boolean edicion, final Ficha fitxaOld, final Map<String, String> valoresForm,
			final Long idFitxa) throws DelegateException {

		final FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();

		if (isModuloModificado("modulo_seccionesua_modificado", valoresForm)) {

			final String[] codisSeccUaNous = valoresForm.get("seccUA").split(",");

			// Paso donde se eliminan las SeccionesUA que han sido eliminadas
			if (edicion) {

				final List<FichaUA> borrarFichasUA = new ArrayList<FichaUA>();
				boolean esborrarFichaUA;

				for (final FichaUA fichaUA : fitxaOld.getFichasua()) {

					esborrarFichaUA = true;

					for (int i = 0; i < codisSeccUaNous.length; i++) {
						if (codisSeccUaNous[i] != null) { // Per a no repetir cerques
							final String[] seccUA = codisSeccUaNous[i].split("#"); // En cas d'edicio es necesari
																					// verificar si les relacions
																					// anteriors se mantenen
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
			for (final String codiSeccUa : codisSeccUaNous) {

				if (codiSeccUa != null) {

					final String[] seccUA = codiSeccUa.split("#");
					final Long idSeccion = ParseUtil.parseLong(seccUA[1]);
					final Long idUA = ParseUtil.parseLong(seccUA[2]);
					fitxaDelegate.crearFichaUA2(idUA, idSeccion, idFitxa);
					final String pidip = System.getProperty("es.caib.rolsac.pidip");

					// Si se anyade una ficha a la seccion Actualidad, se añade tambien a Portada
					// Actualidad (PIDIP)
					if (!((pidip == null) || pidip.equals("N"))) {

						if (idSeccion.longValue() == new Long(Parametros.ESDEVENIMENTS).longValue()) {

							// comprobamos antes si ya exite la ficha en actualidad en portada en cuyo caso
							// no la insertamos para no duplicarla.
							boolean existe = false;
							final Long portadas = new Long(Parametros.PORTADAS_ACTUALIDAD);
							final List listac = fitxaDelegate.listarFichasSeccionTodas(portadas);

							final Iterator iter = listac.iterator();
							while (iter.hasNext()) {
								final Ficha ficac = (Ficha) iter.next();
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

	/**
	 * Devuelve true si ha habido algun cambio en el modulo.
	 *
	 * @param modulo
	 * @param valoresForm
	 * @return boolean
	 */
	private boolean isModuloModificado(final String modulo, final Map<String, String> valoresForm) {
		return "1".equals(valoresForm.get(modulo));
	}

	@RequestMapping(value = "/seccions.do", method = POST)
	public @ResponseBody Map<String, Object> arbreSeccions(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();
		List<Seccion> llistaSeccions = new ArrayList<Seccion>();
		final List<SeccionDTO> llistaSeccionsDTO = new ArrayList<SeccionDTO>();

		try {

			final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			final SeccionDelegate seccioDelegate = DelegateUtil.getSeccionDelegate();

			llistaSeccions = seccioDelegate
					.listarSeccionesRaizPerfilGestor(ParseUtil.parseLong(request.getParameter("pare")));

			for (final Seccion seccio : llistaSeccions) {

				final TraduccionSeccion tse = (TraduccionSeccion) seccio.getTraduccion(lang);

				llistaSeccionsDTO.add(new SeccionDTO(seccio.getId(), tse == null ? "" : tse.getNombre(),
						seccio.getPadre() == null ? null : seccio.getPadre().getId(),
						seccioDelegate.listarHijosSeccion(seccio.getId()).size() > 0 ? true : false));

			}

			resultats.put("llistaSeccions", llistaSeccionsDTO);

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
			} else {
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		}

		return resultats;

	}

	@RequestMapping(value = "/unitats.do", method = POST)
	public @ResponseBody Map<String, Object> arbreUnitats(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();
		List<UnidadAdministrativa> llistaUnitats = new ArrayList<UnidadAdministrativa>();
		final List<UnidadDTO> llistaUnitatsDTO = new ArrayList<UnidadDTO>();

		try {

			final UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();
			final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			if (request.getParameter("pare") == null || "".equals(request.getParameter("pare"))) {
				llistaUnitats = unitatDelegate.listarUnidadesAdministrativasRaiz();
			} else {
				llistaUnitats = unitatDelegate.listarHijosUA(ParseUtil.parseLong(request.getParameter("pare")));
			}

			for (final UnidadAdministrativa unitat : llistaUnitats) {

				final String nomUnitat = unitat.getNombreUnidadAdministrativa(lang);
				final String abreviatura = unitat.getAbreviaturaUnidadAdministrativa(lang);

				llistaUnitatsDTO.add(new UnidadDTO(unitat.getId(), nomUnitat == null ? "" : nomUnitat,
						abreviatura == null ? "" : abreviatura,
						unitat.getPadre() == null ? null : unitat.getPadre().getId(),
						unitatDelegate.listarHijosUA(unitat.getId()).size() > 0 ? true : false));

			}

			resultats.put("llistaUnitats", llistaUnitatsDTO);

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				log.error("Error de persimos: " + ExceptionUtils.getStackTrace(dEx));
			} else {
				log.error(ExceptionUtils.getStackTrace(dEx));
			}

		}

		return resultats;

	}

	@RequestMapping(value = "/esborrarFitxa.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();

		try {

			final Long id = new Long(request.getParameter("id"));
			final FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
			fitxaDelegate.borrarFicha(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (final DelegateException dEx) {

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
	public @ResponseBody Map<String, Object> traduir(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			final TraduccionFicha traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
			final Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			traduccions = traductor.translateTiny(traduccioOrigen, idiomaOrigenTraductor);

			resultats.put("traduccions", traduccions);

		} catch (final DelegateException dEx) {

			logException(log, dEx);

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}

		} catch (final NullPointerException npe) {

			log.error("FitxaBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		} catch (final Exception e) {

			log.error("FitxaBackController.traduir: Error en al traducir ficha: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		}

		return resultats;

	}

	private TraduccionFicha getTraduccionOrigen(final HttpServletRequest request, final String idiomaOrigenTraductor) {

		final TraduccionFicha traduccioOrigen = new TraduccionFicha();

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

	@RequestMapping(value = "/guardarHechosVitales.do", method = POST)
	public @ResponseBody IdNomDTO guardarHechosVitales(final Long id, final Long[] elementos,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;
		Ficha ficha = null;

		try {

			ficha = DelegateUtil.getFichaDelegate().obtenerFicha(id);

			if (elementos != null && elementos.length > 0) {

				final Set<HechoVital> hechosVitales = new HashSet<HechoVital>();

				hechosVitales.addAll(DelegateUtil.getHechoVitalDelegate().buscarPorIds(elementos));

				ficha.setHechosVitales(hechosVitales);

			} else {

				ficha.setHechosVitales(new HashSet<HechoVital>());

			}

			guardarGrabar(ficha);

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {

				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);

			} else {

				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));

			}

		}

		result = new IdNomDTO(ficha.getId(),
				messageSource.getMessage("fitxes.guardat.fetsVitals.correcte", null, request.getLocale()));

		return result;

	}

	@RequestMapping(value = "/guardarMaterias.do", method = POST)
	public @ResponseBody IdNomDTO guardarMaterias(final Long id, final Long[] elementos,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;
		Ficha ficha = null;

		try {

			ficha = DelegateUtil.getFichaDelegate().obtenerFicha(id);

			final Set<Materia> materias = GuardadoAjaxUtil.obtenerMateriasRelacionadas(elementos);
			ficha.setMaterias(materias);

			guardarGrabar(ficha);

			result = new IdNomDTO(ficha.getId(),
					messageSource.getMessage("fitxes.guardat.materies.correcte", null, request.getLocale()));

		} catch (final DelegateException dEx) {

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

	@RequestMapping(value = "/guardarEnlacesRelacionados.do", method = POST)
	public @ResponseBody IdNomDTO guardarEnlacesRelacionados(@RequestBody final EnlacesFichaDTO elementos,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result = null;
		String error = null;

		try {

			final Long id = elementos.getId(); // Id de la ficha.
			final FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
			final Ficha ficha = fichaDelegate.obtenerFicha(id);

			final List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			final List<Enlace> enlacesNuevos = new ArrayList<Enlace>();

			if (elementos.getListaEnlaces() != null) {

				// Construimos los elementos de tipo Enlace a partir de los elementos EnlaceDTO
				// que nos llegan del mantenimiento.
				for (final EnlaceDTO e : elementos.getListaEnlaces()) {

					final Enlace enlace = new Enlace();

					// Tratamiendo del ID del enlace.
					if (e.getId().startsWith("t")) // Nuevo elemento, con id temporal del tipo "tNNNNNN".
						enlace.setId(null);
					else
						enlace.setId(Long.valueOf(e.getId()));

					enlace.setOrden(e.getOrden());
					enlace.setFicha(ficha);

					for (final String lang : langs) {
						final TraduccionEnlace traduccion = (TraduccionEnlace) e.getTraduccion(lang);
						enlace.setTraduccion(lang, traduccion);
					}

					enlacesNuevos.add(enlace);

				}

				// Procesamos los enlaces anteriores, comparándolos con la lista de enlaces
				// nuevos, para obtener
				// la lista de enlaces que se han marcado para borrar.
				final List<Enlace> enlacesAEliminar = ficha.getEnlaces();
				for (final Enlace enlace : enlacesNuevos) {

					final Iterator<Enlace> it = enlacesAEliminar.iterator();
					while (it.hasNext()) {

						final Enlace e = it.next();

						// Si el enlace coincide con alguno de los "nuevos", lo quitamos de
						// la lista de enlaces pendientes de eliminar.
						if (e != null && e.getId().equals(enlace.getId()))
							it.remove();

					}

				}

				fichaDelegate.actualizaEnlacesFicha(id, enlacesNuevos, enlacesAEliminar);

			}

			result = new IdNomDTO(ficha.getId(),
					messageSource.getMessage("fitxes.guardat.enllassos.correcte", null, request.getLocale()));

		} catch (final DelegateException dEx) {

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

	@RequestMapping(value = "/guardarDocumentosRelacionados.do", method = POST)
	public @ResponseBody IdNomDTO guardarDocumentosRelacionados(final Long id, Long[] elementos,
			final HttpServletRequest request) {

		// Guardaremos el orden y borraremos los documentos que se hayan marcado para
		// borrar.
		// La creación se gestiona en el controlador DocumentBackController.

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;
		// Ficha ficha = null;

		try {
			if (elementos == null) {
				elementos = new Long[0];
			}
			DelegateUtil.getFichaDelegate().reordenarDocumentos(id, Arrays.asList(elementos), null, null);
			result = new IdNomDTO(id,
					messageSource.getMessage("fitxes.guardat.documents.correcte", null, request.getLocale()));
			/*
			 * ficha = DelegateUtil.getFichaDelegate().obtenerFicha(id); List<Documento>
			 * documentos =
			 * GuardadoAjaxUtil.actualizarYOrdenarDocumentosRelacionados(elementos, null,
			 * ficha); ficha.setDocumentos(documentos);
			 * 
			 * DelegateUtil.getFichaDelegate().grabarFicha(ficha);
			 * 
			 * result = new IdNomDTO(ficha.getId(),
			 * messageSource.getMessage("fitxes.guardat.documents.correcte", null,
			 * request.getLocale()));
			 **/

		} catch (final DelegateException dEx) {

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

}
