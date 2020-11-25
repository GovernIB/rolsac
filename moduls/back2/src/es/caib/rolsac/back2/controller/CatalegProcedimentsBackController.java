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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.Procedimiento;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
import org.ibit.rol.sac.model.TraduccionExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.TraduccionTipoAfectacion;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.dto.CodNomDTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
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
import org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.util.POUtils;
import org.ibit.rol.sac.persistence.util.RolsacPropertiesUtil;
import org.ibit.rol.sac.persistence.util.SiaEnviableResultado;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.CSVUtil;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.GuardadoAjaxUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.LlistatUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;
import es.indra.rol.sac.integracion.traductorTranslatorIB.TraductorException;

@SuppressWarnings("deprecation") // amartin: debido a org.ibit.rol.sac.model.Documento

@Controller
@RequestMapping("/catalegProcediments/")
public class CatalegProcedimentsBackController extends PantallaBaseController {

	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.procediment.url";
	private static Log log = LogFactory.getLog(CatalegProcedimentsBackController.class);

	@RequestMapping(value = "/catalegProcediments.do")
	public String pantalla(final Map<String, Object> model, final HttpSession session,
			final HttpServletRequest request) {

		String lang;

		try {
			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
		} catch (final DelegateException dEx) {
			log.error("Error al recuperar el idioma por defecto.");
			lang = "ca";
		}

		if (estemEnUnitatAdministrativa(session)) {
			crearModelComplert_pantalla(model, session, request, lang);
		} else {
			crearModelSencill_pantalla(model, session, request, lang);
		}

		loadIndexModel(model, request);

		// #427 Listas para el buscador de normativas. Las pasamos a DTO.
		// Lo ponemos en try catch para evitar que esto bloquee cualquier recuperación
		try {
			// Boletines.
			model.put("llistaButlletins", getListaBoletinesDTO());
			// Tipos normativa.
			model.put("llistaTipusNormativa", getListaTiposNormativaDTO(lang));
			// Tipos afectacion.
			model.put("llistaTipusAfectacio", getListaTiposAfectacionDTO(lang));
			// Plataforma.
			model.put("llistaPlataformas", getListaPlataformasDTO());

		} catch (final DelegateException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		// Comprobamos si esta en la raiz y si tiene los permisos para comunes
		final String permisos = getPermisosUsuario(request);
		final UnidadAdministrativa ua = getUAFromSession(session);
		if (ua == null || !ua.isRaiz() || permisos == null
				|| !Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)) {
			model.put("comunes", false);
		} else {
			model.put("comunes", true);
			// Ponemos los dos idiomas
			model.put("comunesUA", RolsacPropertiesUtil.getUAComun(true));
			model.put("comunesUAESP", RolsacPropertiesUtil.getUAComun(false));
		}

		return "index";

	}

	private boolean estemEnUnitatAdministrativa(final HttpSession session) {
		return null != getUAFromSession(session);
	}

	private void crearModelComplert_pantalla(final Map<String, Object> model, final HttpSession session,
			final HttpServletRequest request, final String lang) {

		crearModelSencill_pantalla(model, session, request, lang);
		model.put("idUA", getUAFromSession(session).getId());
		model.put("nomUA", getUAFromSession(session).getNombreUnidadAdministrativa(lang));

	}

	private void crearModelSencill_pantalla(final Map<String, Object> model, final HttpSession session,
			final HttpServletRequest request, final String lang) {

		try {

			model.put("menu", 0);
			model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
			model.put("submenu_seleccionado", 2);
			model.put("escriptori", "pantalles/catalegProcediments.jsp");
			model.put("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));
			model.put("llistaMateries", LlistatUtil.llistarMaterias(lang));
			model.put("llistaPublicsObjectiu", POUtils.llistarPublicObjectius(lang, true));
			model.put("families", LlistatUtil.llistarFamilias(lang));
			model.put("iniciacions", LlistatUtil.llistarIniciacions(lang));
			model.put("llistaSilenci", llistarSilenci(lang));
			model.put("excepcions", llistarExcepcionsDocumentacio(lang));
			model.put("cataleg", llistarCatalegDocuments(lang));
			model.put("publicObjectiuIntern", POUtils.getPublicoObjetivoInterno());

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				logException(log, dEx);
			}
		}
	}

	private List<CodNomDTO> llistarSilenci(final String lang) throws DelegateException {
		// #366 se carga el combo silencio adm y su selección
		final SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
		final List<CodNomDTO> llistaSilencioDTO = new ArrayList<CodNomDTO>();
		List<SilencioAdm> llistaSilencio = new ArrayList<SilencioAdm>();

		llistaSilencio = silencioDelegate.listarSilencioAdm();
		for (final SilencioAdm silAdm : llistaSilencio) {
			llistaSilencioDTO.add(new CodNomDTO(silAdm.getId().toString(),
					silAdm.getNombreSilencio(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto())));
		}
		return llistaSilencioDTO;
	}

	private List<IdNomDTO> llistarExcepcionsDocumentacio(final String lang) throws DelegateException {

		final ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
		final List<IdNomDTO> excepcioObjDTOList = new ArrayList<IdNomDTO>();
		final List<ExcepcioDocumentacio> llistaExcepcionsDocumentacio = excepcioDelegate.llistarExcepcioDocumentacio();
		TraduccionExcepcioDocumentacio ted;

		for (final ExcepcioDocumentacio excepcio : llistaExcepcionsDocumentacio) {
			ted = (TraduccionExcepcioDocumentacio) excepcio.getTraduccion(lang);
			excepcioObjDTOList.add(new IdNomDTO(excepcio.getId(), ted.getNombre()));
		}

		return excepcioObjDTOList;

	}

	private List<IdNomDTO> llistarCatalegDocuments(final String lang) throws DelegateException {

		final CatalegDocumentsDelegate catdocDelegate = DelegateUtil.getCatalegDocumentsDelegate();
		final List<IdNomDTO> catalegObjDTOList = new ArrayList<IdNomDTO>();
		final List<CatalegDocuments> llistaCatalegDocuments = catdocDelegate.llistarCatalegDocuments();
		TraduccionCatalegDocuments tcd;

		for (final CatalegDocuments document : llistaCatalegDocuments) {
			tcd = (TraduccionCatalegDocuments) document.getTraduccion(lang);
			catalegObjDTOList.add(new IdNomDTO(document.getId(), tcd.getNombre()));
		}

		return catalegObjDTOList;

	}

	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistat(final String criteria, final HttpServletRequest request,
			final HttpSession session) {

		final Map<String, Object> resultats = new HashMap<String, Object>();
		final BuscadorProcedimientoCriteria buscadorCriteria = this.jsonToBuscadorProcedimientoCriteria(criteria);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		final List<ProcedimientoLocalDTO> llistaProcedimientoLocalDTO = new ArrayList<ProcedimientoLocalDTO>();

		final UnidadAdministrativa ua = getUAFromSession(session);
		if (ua != null && buscadorCriteria != null) {

			try {

				buscadorCriteria.setUnidadAdministrativa(ua);
				final String permisos = getPermisosUsuario(request);
				if (!Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)) {
					buscadorCriteria.setComun(0);
				}

				final ProcedimientoDelegate procedimientosDelegate = DelegateUtil.getProcedimientoDelegate();
				resultadoBusqueda = procedimientosDelegate.buscadorProcedimientos(buscadorCriteria);
				llistaProcedimientoLocalDTO.addAll(convertirProcLocales(resultadoBusqueda, request));

			} catch (final DelegateException dEx) {

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

	private String getPermisosUsuario(final HttpServletRequest request) {

		String username = request.getRemoteUser();
		if (StringUtils.isEmpty(username)) {
			username = (String) request.getSession().getAttribute("username");
		}

		final UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
		Usuario usuari = null;
		String permisos = "";
		try {
			usuari = usuariDelegate.obtenerUsuariobyUsername(username);
		} catch (final DelegateException e) {
			e.printStackTrace();
		}

		if (usuari != null && !StringUtils.isEmpty(usuari.getPermisos())) {
			permisos = usuari.getPermisos();
		}
		return permisos;

	}

	@RequestMapping(value = "/exportar.do", method = POST)
	public void exportar(final String criteria, final HttpServletRequest request, final HttpSession session,
			final HttpServletResponse response) throws Exception {

		final BuscadorProcedimientoCriteria buscadorCriteria = this.jsonToBuscadorProcedimientoCriteria(criteria);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		if (getUAFromSession(session) != null && buscadorCriteria != null) {

			try {

				final UnidadAdministrativa ua = getUAFromSession(session);
				buscadorCriteria.setUnidadAdministrativa(ua);
				final String permisos = getPermisosUsuario(request);
				if (!Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)) {
					buscadorCriteria.setComun(0);
				}

				final ProcedimientoDelegate procedimientosDelegate = DelegateUtil.getProcedimientoDelegate();
				buscadorCriteria.setSoloId(true);
				resultadoBusqueda = procedimientosDelegate.buscadorProcedimientos(buscadorCriteria);
				CSVUtil.mostrarCSV(response,
						convertirProcLocalesToCSV((List<Object[]>) resultadoBusqueda.getListaResultados(),
								Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)));

			} catch (final Exception dEx) {
				log.error("Error generando el export de la búsqueda en procedimientos.", dEx);
				throw new Exception(dEx);
			}

		}

	}

	/**
	 * Convierte procedimiento a String para CSV.
	 *
	 * @param listaResultados
	 * @return
	 */
	private String convertirProcLocalesToCSV(final List<Object[]> listaResultados, final boolean tienePermisoComun) {
		final StringBuffer retorno = new StringBuffer();

		// cabecera!
		retorno.append("CODI_PROCEDIMENT;");
		retorno.append("CODI_SIA;");
		retorno.append("ESTAT_SIA;");
		retorno.append("DATA_ACTUALITZACIO_SIA;");
		retorno.append("ESTAT_PROCEDIMENT;");
		retorno.append("VISIBILITAT_PROCEDIMENT;");
		retorno.append("NOM_PROCEDIMENT_CA;");
		retorno.append("NOM_PROCEDIMENT_ES;");
		retorno.append("OBJECTE_CA;");
		retorno.append("PUBLIC_OBJECTIU;");
		retorno.append("NOM_UA_INSTRUCTURA;");
		retorno.append("NOM_UA_RESPONSABLE;");
		retorno.append("NOM_RESPONSABLE;");
		retorno.append("NOM_UA_RESOLUTORIA;");
		retorno.append("NUM TRAMITS;");
		retorno.append("NUM TRAMITS TELEMATICS;");
		retorno.append("NUM NORMES;");
		retorno.append("DATA_ACTUALITZACIO;");
		retorno.append("COD_USUARI_DARRERA_ACT;");
		retorno.append("NOM_USUARI_DARRERA_ACT;");
		if (tienePermisoComun) {
			retorno.append("COMU;");
		}
		retorno.append("\n");

		final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
		final AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
		final UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
		// Contenido.
		for (final Object[] filaResultado : listaResultados) {
			ProcedimientoLocal procedimiento = null;
			final Long idProcedimiento = Long.valueOf(filaResultado[0].toString());
			try {
				procedimiento = procedimientoDelegate.obtenerProcedimientoParaSolr(idProcedimiento, null);
			} catch (final Exception exception) {
				log.error("Error obteniendo el procedimiento con id : " + idProcedimiento, exception);
				retorno.append(CSVUtil.limpiar(idProcedimiento));
				retorno.append(ExceptionUtils.getCause(exception));
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}

			if (procedimiento == null) {
				retorno.append(CSVUtil.limpiar(idProcedimiento));
				retorno.append("Procedimiento nulo");
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}

			// Extraemos datos.
			final TraduccionProcedimiento tradEs = (TraduccionProcedimiento) procedimiento.getTraduccion("es");
			final TraduccionProcedimiento tradCa = (TraduccionProcedimiento) procedimiento.getTraduccion("ca");
			String nomEs, nomCa;
			String objecte;
			if (tradEs == null) {
				nomEs = "";
			} else {
				nomEs = tradEs.getNombre();
			}

			if (tradCa == null) {
				nomCa = "";
			} else {
				nomCa = tradCa.getNombre();
			}

			if (tradCa != null) {
				objecte = tradCa.getResumen();
			} else if (tradEs != null) {
				objecte = tradEs.getResumen();
			} else {
				objecte = "";
			}

			String publicoObjectivo = "";
			if (procedimiento.getPublicosObjetivo() != null) {
				for (final PublicoObjetivo po : procedimiento.getPublicosObjetivo()) {
					publicoObjectivo += po.getId() + " ,";
				}

				if (publicoObjectivo.endsWith(",")) {
					publicoObjectivo = publicoObjectivo.substring(0, publicoObjectivo.length() - 1);
				}
			}

			int numTramits = 0, numTramitsTelematics = 0;
			if (procedimiento.getTramites() != null) {
				for (final Tramite tramite : procedimiento.getTramites()) {
					if (tramite == null) {
						continue;
					}
					numTramits++;
					if (tramite.isTelematico()) {
						numTramitsTelematics++;
					}
				}
			}

			int num_nombres;
			if (procedimiento.getNormativas() == null) {
				num_nombres = 0;
			} else {
				num_nombres = procedimiento.getNormativas().size();
			}

			String estado;
			if (procedimiento.getValidacion().compareTo(1) == 0) {
				estado = "PUBLIC";
			} else if (procedimiento.getValidacion().compareTo(2) == 0) {
				estado = "INTERN";
			} else {
				estado = "RESERVA";
			}

			String codUsuario = "", nomUsuario = "";
			try {
				final List<Auditoria> llista = auditoriaDelegate.listarAuditoriasProcedimiento(idProcedimiento);
				Collections.sort(llista, new Comparator<Auditoria>() {

					/**
					 * Teniendo en cuenta la siguiente simbología:
					 * <p>
					 * -1 : o1 < o2
					 * </p>
					 * <p>
					 * 0 : o1 == o2
					 * </p>
					 * <p>
					 * +1 : o1 > o2
					 * </p>
					 * Se penaliza que no tenga usuario o que no tenga fecha.
					 *
					 * En caso que ambos tengan usuario y fecha, se compara la fecha. <b>Como se ve,
					 * se da el valor al revés, para ordenar hacia abajo por fecha.</b>
					 *
					 * @param o1
					 * @param o2
					 * @return
					 */
					@Override
					public int compare(final Auditoria o1, final Auditoria o2) {
						// Si o1 no tiene usuario o fecha
						if (o1.getFecha() == null || o1.getUsuario() == null || o1.getUsuario().isEmpty()) {
							return 1;
						}

						// Si o2 no tiene usuario o fecha.
						if (o2.getFecha() == null || o2.getUsuario() == null || o2.getUsuario().isEmpty()) {
							return -1;
						}

						// Se compara por fecha.
						return o2.getFecha().compareTo(o1.getFecha());
					}

				});

				for (final Auditoria auditoria : llista) {
					final Usuario usuario = usuarioDelegate.obtenerUsuariobyUsernamePMA(auditoria.getUsuario());
					if (usuario != null) {
						codUsuario = usuario.getUsername();
						nomUsuario = usuario.getNombre();
						break;
					}
				}
			} catch (final DelegateException e) {
				log.error("Error obteniendo auditorias del procedimiento id:" + idProcedimiento, e);
			}

			String estadoSIA = procedimiento.getEstadoSIA();
			if (estadoSIA != null) {
				if ("A".equals(estadoSIA)) {
					estadoSIA = "Alta";
				} else if ("B".equals(estadoSIA)) {
					estadoSIA = "Baixa";
				} else if ("M".equals(estadoSIA)) {
					estadoSIA = "Modificació";
				} else if ("R".equals(estadoSIA)) {
					estadoSIA = "Reactivació";
				}
			}

			retorno.append(CSVUtil.limpiar(procedimiento.getId())); // CODI_PROCEDIEMNT,
			retorno.append(CSVUtil.limpiar(procedimiento.getCodigoSIA())); // CODI_SIA
			retorno.append(CSVUtil.limpiar(estadoSIA)); // ESTAT_SIA
			retorno.append(CSVUtil.limpiar(procedimiento.getFechaSIA())); // DATA_ACTUALITZACIO_SIA
			retorno.append(CSVUtil.limpiar(estado)); // ESTAT_PROCEDIMENT
														// DECODE(PRO_VALIDA,1,'PUBLIC',2,'INTERN','RESERVA')
			retorno.append(CSVUtil.limpiar(procedimiento.isVisible())); // VISIBILITAT_PROCEDIMENT
																		// (ESTAT+DATA_PUB+DATA_CAD + UA_VISIBLE)
			retorno.append(CSVUtil.limpiar(nomCa)); // NOM_PROCEDIMENT_CA,
			retorno.append(CSVUtil.limpiar(nomEs)); // NOM_PROCEDIMENT_ES,
			retorno.append(CSVUtil.limpiar(objecte)); // OBJECTE_CA
			retorno.append(CSVUtil.limpiar(publicoObjectivo)); // PUBLIC_OBJECTIU (ID_PUBLIC OBJECTIU SEPARATS PER
																// COMES)
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(procedimiento.getUnidadAdministrativa()))); // NOM
																											// UA_INSTRUCTURA
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(procedimiento.getServicioResponsable()))); // NOM
																											// UA_RESPONSABLE
			retorno.append(CSVUtil.limpiar(procedimiento.getResponsable()));// NOM_RESPONSABLE
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(procedimiento.getOrganResolutori()))); // NOM
																										// NOM_UA_RESOLUTORIA
			retorno.append(CSVUtil.limpiar(numTramits)); // NUM TRAMITS
			retorno.append(CSVUtil.limpiar(numTramitsTelematics)); // NUM TRAMITS TELEMÀTICS
			retorno.append(CSVUtil.limpiar(num_nombres)); // NUM NORMES
			retorno.append(CSVUtil.limpiar(procedimiento.getFechaActualizacion())); // DATA_ACTUALITZACIO
			retorno.append(CSVUtil.limpiar(codUsuario)); // COD_USUARI_DARRERA_ACT
			retorno.append(CSVUtil.limpiar(nomUsuario)); // NOM_USUARI_DARRERA_ACT
			if (tienePermisoComun) {
				retorno.append(CSVUtil.limpiar(procedimiento.isComun())); // NOM_USUARI_DARRERA_ACT
			}
			retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
		}

		return retorno.toString();
	}

	/**
	 * Método que se encarga de convertir un String en formato json a una instancia
	 * de BuscadorProcedimientoCriteria
	 *
	 * @param criteria
	 * @return
	 */
	private BuscadorProcedimientoCriteria jsonToBuscadorProcedimientoCriteria(final String criteria) {

		BuscadorProcedimientoCriteria buscadorCriteria = null;

		try {

			final ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

			buscadorCriteria = mapper.readValue(criteria, BuscadorProcedimientoCriteria.class);
			buscadorCriteria.setLocale(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());

		} catch (final JsonParseException e) {
			log.error(e.getMessage());
		} catch (final JsonMappingException e) {
			log.error(e.getMessage());
		} catch (final IOException e) {
			log.error(e.getMessage());
		} catch (final DelegateException e) {
			log.error(e.getMessage());
		}

		return buscadorCriteria;

	}

	/**
	 * Función para convertir a procedimientos locales los resultados
	 *
	 * @param resultadoBusqueda
	 * @param request
	 * @return
	 */
	private List<ProcedimientoLocalDTO> convertirProcLocales(final ResultadoBusqueda resultadoBusqueda,
			final HttpServletRequest request) {

		final List<ProcedimientoLocalDTO> llistaProcedimientoLocalDTO = new ArrayList<ProcedimientoLocalDTO>();
		for (final ProcedimientoLocal pl : castList(ProcedimientoLocal.class, resultadoBusqueda.getListaResultados())) {

			final ProcedimientoLocalDTO procLocalDTO = new ProcedimientoLocalDTO(pl.getId(),
					HtmlUtils.html2text(pl.getNombreProcedimiento()), pl.isVisible(),
					DateUtils.formatDate(pl.getFechaActualizacion()), pl.getNombreFamilia(), pl.isComun());

			llistaProcedimientoLocalDTO.add(procLocalDTO);

		}

		return llistaProcedimientoLocalDTO;

	}

	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(final Long id, final HttpSession session,
			final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

			final String lang = idiomaDelegate.lenguajePorDefecto();

			final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			final ProcedimientoLocal proc = procedimientoDelegate.obtenerProcedimientoNewBack(id);

			// Si no tiene el permiso de comunes, si el servicio es comun, no puede verlo.
			final String permisos = getPermisosUsuario(request);
			if (proc.isComun() && !Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)) {
				resultats.put("error",
						messageSource.getMessage("error.permisos.proc.comun", null, request.getLocale()));
				return resultats;
			}

			resultats.put("item_id", proc.getId());
			resultats.put("item_codigo_pro", proc.getSignatura());
			resultats.put("item_codigo_sia", proc.getCodigoSIA()); // #366 Se añade SIA
			resultats.put("item_fecha_sia", DateUtils.formatDateSimpleTime(proc.getFechaSIA())); // #366 Se añade fecha
																									// SIA
			resultats.put("item_estado_sia", proc.getEstadoSIA()); // #366 Se añade estado SIA
			resultats.put("item_estat", proc.getValidacion());
			resultats.put("item_data_actualitzacio", DateUtils.formatDate(proc.getFechaActualizacion()));
			resultats.put("item_data_publicacio", DateUtils.formatDateSimpleTime(proc.getFechaPublicacion()));
			resultats.put("item_data_caducitat", DateUtils.formatDateSimpleTime(proc.getFechaCaducidad()));
			resultats.put("item_codi", proc.getSignatura());
			resultats.put("item_tramite", proc.getTramite());
			resultats.put("item_url", proc.getUrl());
			resultats.put("item_responsable", proc.getResponsable());
			resultats.put("item_finestreta_unica", proc.esVentanillaUnica());
			// #351cambio info por dir electrÃ³nica
			// resultats.put("item_notes", proc.getInfo());
			resultats.put("item_notes", proc.getDirElectronica());
			resultats.put("item_fi_vida_administrativa", proc.getIndicador() == null ? "" : (proc.getIndicador()));
			resultats.put("item_taxa", (proc.getTaxa() == null || "0".equals(proc.getTaxa())) ? false : true);

			resultats.put("item_comun", (proc.isComun() ? true : false));

			resultats.put("item_finestreta_unica",
					(proc.getVentanillaUnica() == null || "0".equals(proc.getVentanillaUnica())) ? false : true);

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
				final UnidadAdministrativa uaOrganoResponsable = proc.getUnidadAdministrativa();
				resultats.put("item_organ_responsable_id", uaOrganoResponsable.getId());
				resultats.put("item_organ_responsable_nom", uaOrganoResponsable.getNombreUnidadAdministrativa(lang));
			}

			if (proc.getServicioResponsable() != null) {
				final UnidadAdministrativa uaServicioResponsable = proc.getServicioResponsable();
				resultats.put("item_servei_responsable_id", uaServicioResponsable.getId());
				resultats.put("item_servei_responsable_nom", uaServicioResponsable.getNombreUnidadAdministrativa(lang));
			}

			if (proc.getOrganResolutori() != null) {
				final UnidadAdministrativa uaOrganoresolutorio = proc.getOrganResolutori();
				resultats.put("item_organ_id", uaOrganoresolutorio.getId());
				resultats.put("item_organ_nom", uaOrganoresolutorio.getNombreUnidadAdministrativa(lang));
			}

			if (proc.getUnidadAdministrativa().isRaiz()
					&& Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)) {
				resultats.put("comun_tramite", true);
			} else {
				resultats.put("comun_tramite", false);
			}

			// Obtención de listado de posibles hechos vitales del procedimiento
			final Set<PublicoObjetivo> listaPublicosObjetivos = proc.getPublicosObjetivo();
			if (!listaPublicosObjetivos.isEmpty()) {
				resultats.put("listadoHechosVitales", LlistatUtil.llistarHechosVitales(listaPublicosObjetivos, lang));
			} else {
				resultats.put("listadoHechosVitales", Collections.EMPTY_SET);
			}

			recuperaIdiomas(resultats, proc, lang); // Recuperar los procedimientos según los idiomas
			recuperaTramites(resultats, proc, request); // Recuperar los trámites relacionados de un procedimiento
			recuperaPO(resultats, proc, lang); // Recuperar los públicos objetivos asociados a un procedimiento

			// #366 se carga el combo silencio adm y su seleccion
			if (proc.getSilencio() != null) {
				resultats.put("item_silenci_combo", proc.getSilencio().getId());
			}

			// #431 Activar boton de envio a SIA en estado no activo
			if (proc.getEstadoSIA() == null || proc.getEstadoSIA().isEmpty()
					|| proc.getEstadoSIA().equals(SiaUtils.ESTADO_BAJA)) {
				resultats.put("boto_sia_no_activo", "S");
			} else {
				resultats.put("boto_sia_no_activo", "N");
			}

		} catch (final DelegateException dEx) {

			logException(log, dEx);

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}

		}

		return resultats;

	}

	private List<IdNomDTO> getListaTiposAfectacionDTO(final String idioma) throws DelegateException {

		final List<TipoAfectacion> listaTiposAfectacion = DelegateUtil.getTipoAfectacionDelegate()
				.listarTiposAfectaciones();
		final List<IdNomDTO> listaTiposAfectacionDTO = new ArrayList<IdNomDTO>();
		for (final TipoAfectacion tipoAfec : listaTiposAfectacion) {
			TraduccionTipoAfectacion traTipAfec = (TraduccionTipoAfectacion) tipoAfec.getTraduccion(idioma);
			if (traTipAfec == null) {
				traTipAfec = (TraduccionTipoAfectacion) tipoAfec.getTraduccion();
			}

			final IdNomDTO tipAfecTran = new IdNomDTO(tipoAfec.getId(), traTipAfec.getNombre());
			listaTiposAfectacionDTO.add(tipAfecTran);
		}

		return listaTiposAfectacionDTO;

	}

	private List<IdNomDTO> getListaTiposNormativaDTO(final String idioma) throws DelegateException {

		final List<Tipo> listaTiposNormativa = DelegateUtil.getTipoNormativaDelegate().listarTiposNormativas();
		final List<IdNomDTO> listaTiposNormativaDTO = new ArrayList<IdNomDTO>();
		for (final Tipo tipo : listaTiposNormativa) {
			TraduccionTipo traTipo = (TraduccionTipo) tipo.getTraduccion(idioma);
			if (traTipo == null) {
				traTipo = (TraduccionTipo) tipo.getTraduccion();
			}

			IdNomDTO tipoTran;
			if (traTipo != null) {
				tipoTran = new IdNomDTO(tipo.getId(), traTipo.getNombre());
			} else {
				tipoTran = new IdNomDTO(tipo.getId(), "");
			}

			listaTiposNormativaDTO.add(tipoTran);
		}

		return listaTiposNormativaDTO;

	}

	private List<IdNomDTO> getListaPlataformasDTO() throws DelegateException {
		final Map parametros = new HashMap();
		final int pagina = 0;
		final int resultats = 100;
		final ResultadoBusqueda resultado = DelegateUtil.getPlataformaDelegate().buscadorListarPlataforma(parametros,
				pagina, resultats, true, true);
		final List<IdNomDTO> listaPlataformasDTO = new ArrayList<IdNomDTO>();
		if (resultado.getListaResultados() != null) {
			for (final Object oplataforma : resultado.getListaResultados()) {
				final Plataforma plataforma = (Plataforma) oplataforma;
				final IdNomDTO plat = new IdNomDTO(plataforma.getId(), plataforma.getIdentificador());
				listaPlataformasDTO.add(plat);
			}
		}

		return listaPlataformasDTO;
	}

	private List<IdNomDTO> getListaBoletinesDTO() throws DelegateException {

		final List<Boletin> listaBoletines = DelegateUtil.getBoletinDelegate().listarBoletines();
		final List<IdNomDTO> listaBoletinesDTO = new ArrayList<IdNomDTO>();
		for (final Boletin boletin : listaBoletines) {
			final IdNomDTO bol = new IdNomDTO(boletin.getId(), boletin.getNombre());
			listaBoletinesDTO.add(bol);
		}

		return listaBoletinesDTO;

	}

	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(final Long id, final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			final List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			final ProcedimientoLocal proc = procedimientoDelegate.obtenerProcedimientoNewBack(id);

			// Pasamos el Set de materias relacionados a un List.
			final List<Materia> listaMaterias = new ArrayList<Materia>(proc.getMaterias());
			resultats.put("materies",
					CargaModulosLateralesUtil.recuperaMateriasRelacionadas(listaMaterias, id, lang, false));

			// Pasamos el Set de HHVV relacionados a un List.
			final List<HechoVitalProcedimiento> listaHechosVitales = new ArrayList<HechoVitalProcedimiento>(
					proc.getHechosVitalesProcedimientos());
			resultats.put("fetsVitals", CargaModulosLateralesUtil.recuperaHechosVitalesRelacionados(listaHechosVitales,
					HechoVitalProcedimiento.class, id, lang, true));

			// Documentos relacionados.
			final List<Documento> listaDocumentos = proc.getDocumentos();
			resultats.put("documents",
					CargaModulosLateralesUtil.recuperaDocumentosRelacionados(listaDocumentos, id, idiomas, true));

			recuperaNormativas(resultats, proc, lang, id); // Recuperar las normativas asociadas a un procedimiento

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
	 * Función que recupera el contenido de los procedimientos según el idioma.
	 */
	private void recuperaIdiomas(final Map<String, Object> resultats, final ProcedimientoLocal proc,
			final String langDefault) throws DelegateException {

		final List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

		for (final String lang : langs) {
			if (proc.getTraduccion(lang) != null) {
				resultats.put(lang, proc.getTraduccion(lang));
			} else {
				if (proc.getTraduccion(langDefault) != null) {
					resultats.put(lang, proc.getTraduccion(langDefault));
				} else {
					resultats.put(lang, new TraduccionProcedimientoLocal());
				}
			}
		}

	}

	/**
	 * Devuelve los trámites, poniendo en el campo nom tanto el ca como el es.
	 *
	 * @param resultats
	 * @param proc
	 * @param request
	 * @throws DelegateException
	 */
	private void recuperaTramites(final Map<String, Object> resultats, final ProcedimientoLocal proc,
			final HttpServletRequest request) throws DelegateException {
		List<Map<String, Object>> listaTramitesDTO = null;
		final List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
		if (proc.getTramites() != null && proc.getTramites().size() != 0) {
			listaTramitesDTO = new ArrayList<Map<String, Object>>();

			for (final Tramite tramite : proc.getTramites()) {
				if (tramite != null) {

					final Map<String, String> titulos = new HashMap<String, String>();
					String nombreTramite;
					TraduccionTramite tradTramite;
					for (final String idioma : idiomas) {

						tradTramite = (TraduccionTramite) tramite.getTraduccion(idioma);
						nombreTramite = (tradTramite != null && tradTramite.getNombre() != null)
								? tradTramite.getNombre()
								: "";

						titulos.put(idioma, nombreTramite);

					}

					final Map<String, Object> map = new HashMap<String, Object>();
					map.put("orden", tramite.getOrden());
					map.put("id", tramite.getId());
					map.put("nom", titulos);
					map.put("moment", tramite.getFase());

					listaTramitesDTO.add(map);
				}
			}

		}

		resultats.put("tramites", listaTramitesDTO);

	}

	/*
	 * Función para recuperar los públicos objeticos de un procedimiento
	 */
	private void recuperaPO(final Map<String, Object> resultats, final ProcedimientoLocal proc, final String lang) {

		if (proc.getPublicosObjetivo() != null) {

			final List<IdNomDTO> llistaPublicsDTO = new ArrayList<IdNomDTO>();

			for (final PublicoObjetivo pob : proc.getPublicosObjetivo()) {
				final TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo) pob.getTraduccion(lang);
				llistaPublicsDTO.add(new IdNomDTO(pob.getId(), tpob == null ? "" : tpob.getTitulo()));
			}

			resultats.put("publicsObjectiu", llistaPublicsDTO);

		} else {

			resultats.put("publicsObjectiu", null);

		}

	}

	/*
	 * Función para recuperar las normativas de un procedimiento
	 */
	private void recuperaNormativas(final Map<String, Object> resultats, final ProcedimientoLocal proc,
			final String lang, final Long idProcedimiento) {

		if (proc.getNormativas() != null) {

			Map<String, String> map;
			final List<Map<String, String>> llistaNormatives = new ArrayList<Map<String, String>>();
			TraduccionNormativa traNor;
			String titulo;

			for (final Normativa normativa : proc.getNormativas()) {

				traNor = (TraduccionNormativa) normativa.getTraduccion(lang);

				// Retirar posible enlace incrustado en titulo
				titulo = (traNor == null) ? "" : HtmlUtils.obtenerTituloDeEnlaceHtml(traNor.getTitulo());
				map = new HashMap<String, String>();
				map.put("id", normativa.getId().toString());
				map.put("nombre", titulo);
				map.put("idMainItem", idProcedimiento.toString());
				map.put("idRelatedItem", normativa.getId().toString());
				if (!normativa.isDatosValidos()) {
					map.put("color", "red");
				}
				llistaNormatives.add(map);

			}

			resultats.put("normatives", llistaNormatives);

		} else {

			resultats.put("normatives", null);

		}

	}

	@RequestMapping(value = "/esborrarProcediment.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();

		try {

			final Long id = new Long(request.getParameter("id"));
			final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			procedimientoDelegate.borrarProcedimiento(id);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}

		}

		return resultatStatus;

	}

	@RequestMapping(value = "/envioSiaNoActivo.do", method = POST)
	public @ResponseBody IdNomDTO envioSiaNoActivo(final HttpSession session, final HttpServletRequest request) {
		IdNomDTO result = null;
		String error = null;

		try {

			Long id = null;
			if (request.getParameter("id").isEmpty()) {

				result = new IdNomDTO(-65l, messageSource.getMessage("proc.error.esnulo", null, request.getLocale()));

			} else {

				final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
				id = Long.parseLong(request.getParameter("id"));
				final ProcedimientoLocal procedimiento = procedimientoDelegate.obtenerProcedimientoNewBack(id);
				if (procedimiento.getEstadoSIA() != null && procedimiento.getEstadoSIA().equals(SiaUtils.ESTADO_ALTA)) {
					result = new IdNomDTO(-65l,
							messageSource.getMessage("proc.error.estaactivo", null, request.getLocale()));
				} else {
					final SiaResultado resultado = DelegateUtil.getSiaDelegate()
							.enviarProcedimientoNoActivo(procedimiento);
					if (resultado.isCorrecto()) {
						result = new IdNomDTO(id, "");
					} else {
						result = new IdNomDTO(-66l, resultado.getMensaje());
					}
				}
			}
			return result;

		} catch (final Exception exception) {
			error = exception.getMessage();
			result = new IdNomDTO(-64l, error);

		}

		return result;

	}

	@RequestMapping(value = "/checkEnvioSiaNoActivo.do", method = POST)
	public @ResponseBody IdNomDTO checkEnvioSiaNoActivo(final HttpSession session, final HttpServletRequest request) {
		IdNomDTO result = null;
		String error = null;

		try {

			Long id = null;
			if (request.getParameter("id").isEmpty()) {
				result = new IdNomDTO(-65l,
						messageSource.getMessage("txt.sia.error.noproc", null, request.getLocale()));
			} else {
				final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();

				id = Long.parseLong(request.getParameter("id"));
				final ProcedimientoLocal procedimiento = procedimientoDelegate.obtenerProcedimiento(id);
				final SiaEnviableResultado resultado = SiaUtils.isEnviableNoActivo(procedimiento);
				if (resultado.isNotificiarSIA()) {
					result = new IdNomDTO(id, "");
				} else {
					if (resultado.getRespuesta() == null || resultado.getRespuesta().isEmpty()) {
						result = new IdNomDTO(-67l,
								messageSource.getMessage("proc.error.noenviar", null, request.getLocale()));
					} else {
						result = new IdNomDTO(-66l,
								messageSource.getMessage(resultado.getRespuesta(), null, request.getLocale()));
					}
				}
			}

		} catch (final Exception exception) {
			error = exception.getMessage();
			result = new IdNomDTO(-64l, error);

		}

		return result;

	}

	@RequestMapping(value = "/checkNormativaVigente.do", method = POST)
	public @ResponseBody IdNomDTO checkNormativaVigente(final HttpSession session, final HttpServletRequest request) {
		IdNomDTO result = null;
		String error = null;

		try {

			Long id = null;
			if (!request.getParameter("id").isEmpty()) {

				final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
				id = Long.parseLong(request.getParameter("id"));

				if (id != null) {
					if (procedimientoDelegate.isNormativaDerogada(id)) {
						error = messageSource.getMessage("serv.error.normativa.derogadas", null, request.getLocale());
						return result = new IdNomDTO(-1l, error);
					}
				}
			}

			return new IdNomDTO(id, "");
		} catch (final Exception exception) {
			error = exception.getMessage();
			result = new IdNomDTO(-66l, error);

		}

		return result;

	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(final HttpSession session, final HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {

			if (request.getParameter("publicsObjectiu") == null || request.getParameter("publicsObjectiu").equals("")) {
				error = messageSource.getMessage("proc.error.falta.public", null, request.getLocale());
				return result = new IdNomDTO(-3l, error);
			}

			if (!POUtils.validaPublicosObjetivos(request.getParameter("publicsObjectiu"))) {
				error = messageSource.getMessage("proc.error.public.objectiu.intern", null, request.getLocale());
				return result = new IdNomDTO(-3l, error);
			}

			final ProcedimientoDelegate procedimentDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal procediment = new ProcedimientoLocal();
			ProcedimientoLocal procedimentOld;

			boolean edicion;
			try {
				final Long id = Long.parseLong(request.getParameter("item_id"));
				procedimentOld = procedimentDelegate.obtenerProcedimientoNewBack(id);
				edicion = true;
			} catch (final NumberFormatException nfe) {
				procedimentOld = null;
				edicion = false;
			}

			// Solo si es edicion, es obligado tener materias
			if (edicion && (request.getParameter("materies") == null || request.getParameter("materies").equals(""))) {
				error = messageSource.getMessage("proc.error.falta.materia", null, request.getLocale());
				return result = new IdNomDTO(-4l, error);
			}

			procediment = guardarVersion(request, procediment, procedimentOld, error); // Versión
			procediment = guardarPublicoObjetivo(request, procediment, procedimentOld); // Procesar Público Objectivo
			/// Actualizamos lo que viene de pantalla para procediment Publico Objetivo en
			/// procedimentOld para que
			/// en guardarProcedimientoAntiguo no lo machaque en modo edicion
			if (edicion) {
				procedimentOld.setPublicosObjetivo(procediment.getPublicosObjetivo()); // Procesar Público Objectivo
			}
			///
			procediment = guardarIdioma(request, procediment, procedimentOld); // Idiomas
			procediment = guardarValidacion(request, procediment, procedimentOld, error); // Validación
			procediment = guardarFechaPublicacion(request, procediment); // Fecha Publicación
			procediment = guardarFechaCaducidad(request, procediment); // Fecha Caducidad
			procediment = guardarIniciacion(request, procediment, error); // Iniciación
			procediment = guardarFamilia(request, procediment, error); // Familia
			procediment = guardarOrganResolutori(request, procediment, error); // Organ Resolutori
			procediment = guardarUnidadAdministrativa(request, procediment, error); // Unidad Administrativa
			procediment = guardarServeiResponsable(request, procediment, error); // Servei Responsable

			procediment.setResponsable(request.getParameter("item_responsable")); // Responsable
			procediment.setSignatura(request.getParameter("item_codigo_pro")); // Signatura
			procediment = guardarSilencio(request, procediment, error); // Silencio

			// #351 cambio info por dir electronica
			// procediment.setInfo(request.getParameter("item_notes")); // Info
			procediment.setDirElectronica(request.getParameter("item_notes"));
			procediment.setTaxa("on".equalsIgnoreCase(request.getParameter("item_taxa")) ? "1" : "0"); // Taxa
			procediment
					.setIndicador(Long.parseLong(request.getParameter("item_fi_vida_administrativa")) == 1 ? "1" : "0"); // Indicador
			procediment.setVentanillaUnica(
					"on".equalsIgnoreCase(request.getParameter("item_finestreta_unica")) ? "1" : "0"); // Ventanilla
																										// Única
			if (request.getParameter("item_comun") == null) {
				procediment.setComun(false);
			} else {
				procediment.setComun("on".equalsIgnoreCase(request.getParameter("item_comun")));
				if (procediment.isComun()) {
					final UnidadAdministrativa ua = getUAFromSession(session);
					procediment.setUnidadAdministrativa(ua);
					procediment.setOrganResolutori(ua);
				}
			}

			final String permisos = getPermisosUsuario(request);
			if (procediment.isComun() && !Usuario.tienePermiso(permisos, Usuario.PERMISO_GESTION_COMUNES)) {
				error = messageSource.getMessage("error.permisos.proc.comun", null, request.getLocale());
				return new IdNomDTO(-1l, error);
			}

			List<Tramite> listaTramitesParaBorrar = null;
			List<Long> listaIdsTramitesParaActualizar = null;

			if (edicion) {

				// #427 Comprobamos que no tiene ninguna normativa caducada asociada.
				if (!procedimentDelegate.isNormativaValidas(procedimentOld.getId())) {
					// Si estamos en modo edicición, tiene que tener todas las normativas con datos
					// validos.
					error = messageSource.getMessage("proc.error.normativa.datosinvalidos", null, request.getLocale());
					return new IdNomDTO(-5l, error);
				}

				// #414 Comprobamos si hay un cambio de entidad raíz (sólo en edición y si está
				// con código SIA).
				if (procedimentOld.getCodigoSIA() != null && !procedimentOld.getCodigoSIA().isEmpty()
						&& procedimentOld.getEstadoSIA() != null
						&& ("A".equals(procedimentOld.getEstadoSIA()) || "R".equals(procedimentOld.getEstadoSIA())
								|| "M".equals(procedimentOld.getEstadoSIA()))
						&& !SiaUtils.mismaSiaUA(procediment, procedimentOld)) {
					// Si estamos en modo edicición, tiene código sia y está en SIA (Alta, Modif. o
					// Reactiv) entonces comprobamos si hay cambio de raíz.
					error = messageSource.getMessage("proc.error.siaUA.misma", null, request.getLocale());
					return result = new IdNomDTO(-5l, error);
				}

				// Verificamos que no se "pierda" el cod SIA
				if (StringUtils.isNotBlank(request.getParameter("item_codigo_sia")) && !StringUtils
						.equals(request.getParameter("item_codigo_sia"), procedimentOld.getCodigoSIA())) {
					log.error("Error: el parámetro item_codigo_sia de la pantalla no concuerda con el de la BBDD.");
					throw new IllegalStateException("error_sia_incorrecto");
				}

				procediment = guardarProcedimientoAntiguo(procediment, procedimentOld); // Si estamos guardando un
																						// procedimiento ya existente en
																						// vez de uno nuevo

				// Obtenemos las listas necesarias para el tratamiento de los trámites.
				listaTramitesParaBorrar = new ArrayList<Tramite>();
				listaIdsTramitesParaActualizar = new ArrayList<Long>();

				// Dentro de este método se asignan los nuevos trámites asociados al
				// procedimiento
				// y se rellenan las listas listaTramitesParaBorrar y
				// listaIdsTramitesParaActualizar.
				procediment = guardarTramites(procediment, procedimentOld, request, listaTramitesParaBorrar,
						listaIdsTramitesParaActualizar);

				// Comprobamos cambio de estado del procedimiento a estado "público".
				// Si es así, comprobar lo siguiente: se avisará de si falta un trámite de
				// inicio o de si se posee más de uno de ese tipo.
				if (Procedimiento.ESTADO_PUBLICACION_PUBLICA.equals(procediment.getValidacion())
						&& !procedimentOld.getValidacion().equals(procediment.getValidacion())) {

					// Comprobar que haya al menos un trámite de inicio y comprobar si hay más de un
					// trámite de inicio.
					boolean hayTramiteDeIniciacion = false;
					int numTramitesIniciacion = 0;
					boolean hayTramiteSinModelo = false;

					for (final Long id : listaIdsTramitesParaActualizar) {
						final Tramite tramite = DelegateUtil.getTramiteDelegate().obtenerTramite(id);
						if (tramite.getFase() == Tramite.INICIACION) {
							hayTramiteDeIniciacion = true;
							numTramitesIniciacion++;
							// #349 si el tramite de ini no tiene modelo solicitud y (#438) es presencial ,
							// los telematicos no requieren modelo
							if ((tramite.getFormularios() == null || tramite.getFormularios().size() == 0)
									&& tramite.isPresencial()) {
								hayTramiteSinModelo = true;
							}
						}
					}

					if (!hayTramiteDeIniciacion) {
						throw new IllegalStateException("error_no_tramite_iniciacion");
					}

					if (numTramitesIniciacion > 1) {
						throw new IllegalStateException("error_mas_de_un_tramite_de_iniciacion");
					}

					if (hayTramiteSinModelo) {
						error = messageSource.getMessage("error.model_sol_obligatori", null, request.getLocale());
						return new IdNomDTO(-1l, error);
					}
				}

				// #391
				// Validamos las fechas de inicio y publicación los trámites,
				// deben ser iguales o posteriores a la fecha de
				// publicación del procedimiento
				for (final Long id : listaIdsTramitesParaActualizar) {
					final Tramite tramite = DelegateUtil.getTramiteDelegate().obtenerTramite(id);
					if (tramite != null) {
						final Date tdp = tramite.getDataPublicacio();
						final Date tdi = tramite.getDataInici();
						final Date pdp = procediment.getFechaPublicacion();

						if (tdp == null || tdi == null || pdp == null || tdi.before(pdp) || tdp.before(pdp)) {
							error = messageSource.getMessage("error.data_publicacio_e_inici_tramits_obligatori", null,
									request.getLocale());
							return new IdNomDTO(-5l, error);
						}
					}
				}

			}

			final Long procId = guardarGrabar(procediment, listaTramitesParaBorrar, listaIdsTramitesParaActualizar);

			final String ok = messageSource.getMessage("proc.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(procId, ok);

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {

				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);

			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				logException(log, dEx);
			}

		} catch (final NumberFormatException nfe) {

			error = nfe.getMessage();
			result = new IdNomDTO(-3l, error);

		} catch (final ParseException pe) {

			error = pe.getMessage();
			result = new IdNomDTO(-4l, error);

		} catch (final IllegalStateException ise) {

			if ("error_no_tramite_iniciacion".equals(ise.getMessage()))
				error = messageSource.getMessage("error.procediment_sense_tramit_iniciacio", null, request.getLocale());

			else if ("error_mas_de_un_tramite_de_iniciacion".equals(ise.getMessage()))
				error = messageSource.getMessage("error.procediment_multiples_tramits_iniciacio", null,
						request.getLocale());

			else if ("error_sia_incorrecto".equals(ise.getMessage()))
				error = messageSource.getMessage("error.procediment_codigo_sia_incorrecto", null, request.getLocale());

			else
				error = ise.getMessage();

			result = new IdNomDTO(-5l, error);

		} catch (final Exception pe) {

			error = pe.getMessage();
			result = new IdNomDTO(-4l, error);

		}

		return result;

	}

	private ProcedimientoLocal guardarSilencio(final HttpServletRequest request, final ProcedimientoLocal procediment,
			String error) throws DelegateException {
		try {
			if (request.getParameter("item_silenci_combo").isEmpty()) {
				procediment.setSilencio(null);
			} else {
				final Long codigo = Long.valueOf(request.getParameter("item_silenci_combo"));
				final SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
				final SilencioAdm silencio = silencioDelegate.obtenerSilencioAdm(codigo);
				procediment.setSilencio(silencio);
			}

		} catch (final NumberFormatException e) {

			error = messageSource.getMessage("proc.error.formaIniciacio.incorrecta", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());

		}

		return procediment;
	}

	private ProcedimientoLocal guardarTramites(final ProcedimientoLocal procedimiento,
			final ProcedimientoLocal procedimientoOld, final HttpServletRequest request,
			final List<Tramite> listaTramitesParaEliminar, final List<Long> listaIdsTramitesParaActualizar) {

		final List<String> idsNuevosTramites = Arrays.asList(request.getParameter("tramitsProcediment").split(","));
		List<Tramite> tramitesParaCrear = null;

		if (idsNuevosTramites.size() == 1 && idsNuevosTramites.get(0).equals("")) {

			// La lista esta vacía y por tanto se pueden borrar todos los anteriores.
			listaTramitesParaEliminar.addAll(procedimientoOld.getTramites());

		} else {

			// La lista no esta vacia y se deben gestionar los que se han eliminado de la
			// lista
			final HashMap<Long, Tramite> mapaTramitesParaEliminar = new HashMap<Long, Tramite>();
			for (final Tramite tramite : procedimientoOld.getTramites()) {
				if (tramite != null) {
					mapaTramitesParaEliminar.put(tramite.getId(), tramite);
				}
			}

			// Lista para actualizar el orden
			tramitesParaCrear = new ArrayList<Tramite>();
			for (final String id : idsNuevosTramites) {
				listaIdsTramitesParaActualizar.add(Long.parseLong(id));
				tramitesParaCrear.add(mapaTramitesParaEliminar.get(Long.parseLong(id)));
				mapaTramitesParaEliminar.remove(Long.parseLong(id));
			}

			listaTramitesParaEliminar.addAll(mapaTramitesParaEliminar.values());

		}

		// Si es comun, hay que setear todos los valores a la UA raiz
		if (procedimiento.isComun() && tramitesParaCrear != null) {
			for (final Tramite tramite : tramitesParaCrear) {
				tramite.setOrganCompetent(procedimiento.getOrganResolutori());
			}
		}
		procedimiento.setTramites(tramitesParaCrear);

		return procedimiento;

	}

	/*
	 * Guardamos el procedimiento anterior si se trata de una edición.
	 */
	private ProcedimientoLocal guardarProcedimientoAntiguo(final ProcedimientoLocal procediment,
			final ProcedimientoLocal procedimentOld) {

		// Mantenemos los valores originales que tiene el procedimiento.
		procediment.setId(procedimentOld.getId());
		procediment.setTramites(procedimentOld.getTramites());
		// #349 procediment.setOrganResolutori(procedimentOld.getOrganResolutori());
		procediment.setPublicosObjetivo(procedimentOld.getPublicosObjetivo());
		procediment.setMaterias(procedimentOld.getMaterias());
		procediment.setNormativas(procedimentOld.getNormativas());

		// Estos campos no se encuentran en la pantalla y se perderian sus valores al
		// guardar
		procediment.setFechaSIA(procedimentOld.getFechaSIA());
		procediment.setEstadoSIA(procedimentOld.getEstadoSIA());
		procediment.setCodigoSIA(procedimentOld.getCodigoSIA());
		return procediment;

	}

	/*
	 * Para hacer menos accesos a BBDD se comprueba si es edicion o no. En el primer
	 * caso es bastante probable que se repitan la mayoria de public objectiu.
	 */
	private ProcedimientoLocal guardarPublicoObjetivo(final HttpServletRequest request,
			final ProcedimientoLocal procediment, final ProcedimientoLocal procedimentOld) throws DelegateException {

		if (isModuloModificado("modul_public_modificat", request)) {

			if (request.getParameter("publicsObjectiu") != null
					&& !"".equals(request.getParameter("publicsObjectiu"))) {

				final String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				final PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
				final Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
				publicsNous.addAll(publicObjDelegate
						.obtenerPublicosObjetivoPorIDs(request.getParameter("publicsObjectiu"), idioma));
				procediment.setPublicosObjetivo(publicsNous);
			} else {
				procediment.setPublicosObjetivo(new HashSet<PublicoObjetivo>());
			}

		} else {
			// #349
			procediment.setPublicosObjetivo(procedimentOld.getPublicosObjetivo());
		}
		return procediment;
	}

	/*
	 * Traducimos al idioma deseado del procedimiento.
	 */
	private ProcedimientoLocal guardarIdioma(final HttpServletRequest request, final ProcedimientoLocal procediment,
			final ProcedimientoLocal procedimentOld) throws DelegateException {

		TraduccionProcedimientoLocal tpl;

		for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

			tpl = (TraduccionProcedimientoLocal) ((procedimentOld != null) ? procedimentOld.getTraduccion(lang)
					: new TraduccionProcedimientoLocal());

			if (tpl == null) {
				tpl = new TraduccionProcedimientoLocal();
			}

			tpl.setNombre(RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)));
			tpl.setDestinatarios(RolUtil.limpiaCadena(request.getParameter("item_destinataris_" + lang)));
			tpl.setResumen(RolUtil.limpiaCadena(request.getParameter("item_objecte_" + lang)));
			tpl.setResultat(RolUtil.limpiaCadena(request.getParameter("item_resultat_" + lang)));
			tpl.setResolucion(RolUtil.limpiaCadena(request.getParameter("item_resolucio_" + lang)));
			// El campo notificacion queda obsoleto se ha eliminado del back #8 y que no se
			// elimina para permitir compatibilidad entre la version 1.2 y 1.3
			tpl.setNotificacion(RolUtil.limpiaCadena(request.getParameter("item_notificacio_" + lang)));
			// #366
			// tpl.setSilencio(RolUtil.limpiaCadena(request.getParameter("item_silenci_" +
			// lang)));
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
	private ProcedimientoLocal guardarValidacion(final HttpServletRequest request, final ProcedimientoLocal procediment,
			final ProcedimientoLocal procedimentOld, String error) throws DelegateException {

		try {

			final Integer validacion = Integer.parseInt(request.getParameter("item_estat"));

			// Si es superusuario no haremos ninguna comprobación.
			if (!request.isUserInRole("sacsystem")) {
				// Comprobar que no se haya cambiado la validacion/estado siendo operador.
				if (request.isUserInRole("sacoper") && procedimentOld != null
						&& !procedimentOld.getValidacion().equals(validacion)) {
					throw new DelegateException(new SecurityException());
				}
			}

			procediment.setValidacion(validacion);

		} catch (final NumberFormatException e) {

			error = messageSource.getMessage("proc.error.estat.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());

		}

		return procediment;

	}

	/*
	 * Controlamos el formato de la fecha de publicación.
	 */
	private ProcedimientoLocal guardarFechaPublicacion(final HttpServletRequest request,
			final ProcedimientoLocal procediment) throws ParseException {

		if (!StringUtils.isEmpty(request.getParameter("item_data_publicacio"))) {

			final Date data_publicacio = DateUtils.parseDateSimpleTime(request.getParameter("item_data_publicacio"));

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
	private ProcedimientoLocal guardarFechaCaducidad(final HttpServletRequest request,
			final ProcedimientoLocal procediment) throws ParseException {

		if (!StringUtils.isEmpty(request.getParameter("item_data_caducitat"))) {

			final Date data_caducitat = DateUtils.parseDateSimpleTime(request.getParameter("item_data_caducitat"));

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
	private ProcedimientoLocal guardarIniciacion(final HttpServletRequest request, final ProcedimientoLocal procediment,
			String error) throws DelegateException {

		try {

			final Long iniciacionId = Long.parseLong(request.getParameter("item_iniciacio"));
			final IniciacionDelegate iDelegate = DelegateUtil.getIniciacionDelegate();
			final Iniciacion iniciacion = iDelegate.obtenerIniciacion(iniciacionId);
			procediment.setIniciacion(iniciacion);

		} catch (final NumberFormatException e) {

			error = messageSource.getMessage("proc.error.formaIniciacio.incorrecta", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());

		}

		return procediment;

	}

	/*
	 * Obtenemos las famílias de los procedimientos.
	 */
	private ProcedimientoLocal guardarFamilia(final HttpServletRequest request, final ProcedimientoLocal procediment,
			String error) throws DelegateException {

		try {

			final Long familiaId = Long.parseLong(request.getParameter("item_familia"));
			final FamiliaDelegate fDelegate = DelegateUtil.getFamiliaDelegate();
			final Familia familia = fDelegate.obtenerFamilia(familiaId);
			procediment.setFamilia(familia);

		} catch (final NumberFormatException e) {

			error = messageSource.getMessage("proc.error.familia.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());

		}

		return procediment;

	}

	/*
	 * Controlamos la versión del procedimiento.
	 */
	private ProcedimientoLocal guardarVersion(final HttpServletRequest request, final ProcedimientoLocal procediment,
			final ProcedimientoLocal procedimentOld, String error) {

		try {

			final String versionStr = request.getParameter("item_version");

			if (versionStr != null && !"".equals(versionStr)) {

				final Long version = Long.parseLong(versionStr);
				procediment.setVersion(version);

			}

		} catch (final NumberFormatException e) {

			error = messageSource.getMessage("proc.error.versio.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());

		}

		return procediment;

	}

	/*
	 * Obtenemos el Organo resolutorio del procedimiento.
	 */
	private ProcedimientoLocal guardarOrganResolutori(final HttpServletRequest request,
			final ProcedimientoLocal procediment, String error) throws DelegateException {

		if (!"".equals(request.getParameter("item_organ_id"))) {

			try {

				final Long organId = Long.parseLong(request.getParameter("item_organ_id"));
				final UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				final UnidadAdministrativa organ = uaDelegate.obtenerUnidadAdministrativa(organId);
				procediment.setOrganResolutori(organ);

			} catch (final NumberFormatException e) {

				error = messageSource.getMessage("proc.error.organ.incorrecte", null, request.getLocale());
				throw new NumberFormatException(e.getMessage());

			}

		}

		return procediment;

	}

	/*
	 * Obtenemos el Servei del responsable del procedimiento.
	 */
	private ProcedimientoLocal guardarServeiResponsable(final HttpServletRequest request,
			final ProcedimientoLocal procediment, String error) throws DelegateException {

		if (!"".equals(request.getParameter("item_servei_responsable_id"))) {

			try {

				final Long organId = Long.parseLong(request.getParameter("item_servei_responsable_id"));
				final UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				final UnidadAdministrativa organ = uaDelegate.obtenerUnidadAdministrativa(organId);
				procediment.setServicioResponsable(organ);

			} catch (final NumberFormatException e) {

				error = messageSource.getMessage("proc.error.organ.incorrecte", null, request.getLocale());
				throw new NumberFormatException(e.getMessage());

			}

		}

		return procediment;

	}

	/*
	 * Obtenemos la unidad administrativa del procedimiento.
	 */
	private ProcedimientoLocal guardarUnidadAdministrativa(final HttpServletRequest request,
			final ProcedimientoLocal procediment, String error) throws DelegateException {

		try {

			final Long organRespID = Long.parseLong(request.getParameter("item_organ_responsable_id"));
			final UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			final UnidadAdministrativa organResp = uaDelegate.obtenerUnidadAdministrativa(organRespID);
			procediment.setUnidadAdministrativa(organResp);

		} catch (final NumberFormatException e) {

			error = messageSource.getMessage("proc.error.organ.responsable.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());

		}

		return procediment;

	}

	/*
	 * Función de grabar() procedimiento
	 */
	private Long guardarGrabar(final ProcedimientoLocal procediment) throws DelegateException {

		/* XXX: NOTA IMPORTANTE PARA EL RENDIMIENTO */
		procediment.setDocumentos(null);
		procediment.setTramites(null);
		/* FIN NOTA */

		final Long procId = DelegateUtil.getProcedimientoDelegate().grabarProcedimiento(procediment,
				procediment.getUnidadAdministrativa().getId());

		// Actualiza estadísticas
		// DelegateUtil.getEstadisticaDelegate().grabarEstadisticaProcedimiento(procId);

		return procId;

	}

	private Long guardarGrabar(final ProcedimientoLocal procediment, final List<Tramite> listaTramitesParaBorrar,
			final List<Long> listaIdsTramitesParaActualizar) throws DelegateException {

		// Si no hay trámites que procesar, invocamos guardado normal.
		if (listaTramitesParaBorrar == null && listaIdsTramitesParaActualizar == null) {

			return guardarGrabar(procediment);

			// Si no, procesamos con la actualización de los trámites.
		} else {

			/* XXX: NOTA IMPORTANTE PARA EL RENDIMIENTO */
			procediment.setDocumentos(null);
			/* FIN NOTA */

			final Long procId = DelegateUtil.getProcedimientoDelegate().grabarProcedimientoConTramites(procediment,
					procediment.getUnidadAdministrativa().getId(), listaTramitesParaBorrar,
					listaIdsTramitesParaActualizar);

			// Actualiza estadísticas
			// DelegateUtil.getEstadisticaDelegate().grabarEstadisticaProcedimiento(procId);

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
	private boolean isModuloModificado(final String modulo, final HttpServletRequest request) {
		return "1".equals(request.getParameter(modulo));
	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			final TraduccionProcedimientoLocal traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
			final Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			traduccions = traductor.translate(traduccioOrigen, idiomaOrigenTraductor);

			resultats.put("traduccions", traduccions);

		} catch (final DelegateException dEx) {

			logException(log, dEx);
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}

		} catch (final TraductorException traEx) {

			log.error("CatalegProcedimentBackController.traduir: El traductor no puede traducir todos los idiomas");
			resultats.put("error", messageSource.getMessage("traductor.no_traduible", null, request.getLocale()));

		} catch (final NullPointerException npe) {

			log.error("CatalegProcedimentBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		} catch (final Exception e) {

			log.error("CatalegProcedimentBackController.traduir: Error en al traducir procedimiento: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		}

		return resultats;

	}

	private TraduccionProcedimientoLocal getTraduccionOrigen(final HttpServletRequest request,
			final String idiomaOrigenTraductor) {

		final TraduccionProcedimientoLocal traduccioOrigen = new TraduccionProcedimientoLocal();

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
		// El campo notificacion queda obsoleto se ha eliminado del back #8 y que no se
		// elimina para permitir compatibilidad entre la version 1.2 y 1.3
		if (StringUtils.isNotEmpty(request.getParameter("item_notificacio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNotificacion(request.getParameter("item_notificacio_" + idiomaOrigenTraductor));
		}
		// #366
		// if (StringUtils.isNotEmpty(request.getParameter("item_silenci_" +
		// idiomaOrigenTraductor))) {
		// traduccioOrigen.setSilencio(request.getParameter("item_silenci_" +
		// idiomaOrigenTraductor));
		// }
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

	// Actualiza los hechos vitales que se pueden seleccionar en el mantenimiento de
	// un procedimiento,
	// en función de los públicos objetivo seleccionados.
	@RequestMapping(value = "/listarHechosVitales.do", method = POST)
	public @ResponseBody Map<String, Object> listarHechosVitales(
			@RequestParam final Set<Long> publicosObjectivosSeleccionados, final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			resultats.put("listadoHechosVitales", LlistatUtil.llistarHechosVitales(publicosObjectivosSeleccionados,
					DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));

		} catch (final DelegateException e) {

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
	public @ResponseBody IdNomDTO guardarHechosVitales(final Long id, final Long[] elementos,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
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
				final List<HechoVital> listHV = DelegateUtil.getHechoVitalDelegate().buscarPorIds(elementos);
				final Set<HechoVitalProcedimiento> hvpsAGuardar = new HashSet<HechoVitalProcedimiento>();

				for (final HechoVital hv : listHV) {

					final HechoVitalProcedimiento hvp = new HechoVitalProcedimiento();
					hvp.setProcedimiento(procedimiento);
					hvp.setHechoVital(hv);

					int maxOrden = 0;
					for (final HechoVitalProcedimiento hechoVitalProcedimiento : hv.getHechosVitalesProcedimientos()) {
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

			result = new IdNomDTO(procedimiento.getId(),
					messageSource.getMessage("proc.guardat.fetsVitals.correcte", null, request.getLocale()));

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

	private void borrarHechosVitalesProcedimientos(final ProcedimientoLocal procedimiento) throws DelegateException {

		final HechoVitalProcedimientoDelegate hvpDelegate = DelegateUtil.getHechoVitalProcedimientoDelegate();
		final List<Long> hvpIds = new LinkedList<Long>();

		if (procedimiento.getHechosVitalesProcedimientos() != null) {

			for (final HechoVitalProcedimiento hvp : procedimiento.getHechosVitalesProcedimientos())
				hvpIds.add(hvp.getId());

			hvpDelegate.borrarHechoVitalProcedimientos(hvpIds);

		}

		procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());

	}

	@RequestMapping(value = "/guardarMaterias.do", method = POST)
	public @ResponseBody IdNomDTO guardarMaterias(final Long id, final Long[] elementos,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;
		ProcedimientoLocal procedimiento = null;

		try {

			procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);

			final Set<Materia> materias = GuardadoAjaxUtil.obtenerMateriasRelacionadas(elementos);
			if (procedimiento.getValidacion() == 1 && materias.isEmpty()) {
				error = messageSource.getMessage("proc.error.falta.materia", null, request.getLocale());
				return result = new IdNomDTO(-6l, error);

			}
			procedimiento.setMaterias(materias);
			guardarGrabar(procedimiento);
			result = new IdNomDTO(procedimiento.getId(),
					messageSource.getMessage("proc.guardat.materies.correcte", null, request.getLocale()));

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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarNormativas.do", method = POST)
	public @ResponseBody IdNomDTO guardarNormativas(final Long id, final Long[] elementos,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result;
		String error = null;
		ProcedimientoLocal procedimiento = null;
		final Set<Normativa> normativas = new HashSet<Normativa>();

		if (id != null) {

			try {

				procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);

				if (elementos != null && elementos.length > 0) {

					final List<Long> normativasList = new Vector<Long>();
					final NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

					for (int i = 0; i < elementos.length; i++)
						normativasList.add(elementos[i]);

					normativas.addAll(normativaDelegate.buscarNormativas(normativasList));

					procedimiento.setNormativas(normativas);

				} else {
					if (procedimiento.getValidacion() == 1 && normativas.isEmpty()) {
						error = messageSource.getMessage("proc.error.falta.normativa", null, request.getLocale());
						return result = new IdNomDTO(-6l, error);
					}

					procedimiento.setNormativas(new HashSet<Normativa>());

				}

				guardarGrabar(procedimiento);

				result = new IdNomDTO(procedimiento.getId(),
						messageSource.getMessage("proc.guardat.normatives.correcte", null, request.getLocale()));

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

		} else {

			error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);

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
		// ProcedimientoLocal procedimiento = null;

		try {
			if (elementos == null) {
				elementos = new Long[0];
			}
			/*
			 * procedimiento =
			 * DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoNewBack(id);
			 *
			 * List<Documento> documentos =
			 * GuardadoAjaxUtil.actualizarYOrdenarDocumentosRelacionados(elementos,
			 * procedimiento, null); procedimiento.setDocumentos(documentos);
			 *
			 * guardarGrabar(procedimiento);
			 */
			DelegateUtil.getProcedimientoDelegate().reordenarDocumentos(id, Arrays.asList(elementos));
			result = new IdNomDTO(id,
					messageSource.getMessage("proc.guardat.documents.correcte", null, request.getLocale()));

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

	class HechoVitalProcedimientoDTOComparator implements Comparator<Map<String, Object>> {

		@Override
		public int compare(final Map<String, Object> hvp1, final Map<String, Object> hvp2) {

			final Integer orden1 = (Integer) hvp1.get("orden");
			final Integer orden2 = (Integer) hvp2.get("orden");

			return orden1.compareTo(orden2);

		}

	}

}
