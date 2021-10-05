package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.TraduccionTaxa;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.persistence.remote.vuds.ActualizacionVudsException;
import org.ibit.rol.sac.persistence.remote.vuds.ValidateVudsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.DateUtils;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@Controller
@RequestMapping("/tramit/")
public class TramiteBackController {

	private final String IDIOMA_ORIGEN_TRADUCTOR = "ca";

	private static Log log = LogFactory.getLog(TramiteBackController.class);
	private MessageSource messageSource = null;

	@Autowired
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/tramite.do", method = GET)
	public void obtenerTramite(final HttpServletRequest request, final HttpServletResponse response) {
	}

	@RequestMapping(value = "/carregarTramit.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(final HttpSession session,
			final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		final Map<String, Object> resultats = new HashMap<String, Object>();
		final Long idTramite = new Long(request.getParameter("id"));

		try {

			final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			final List<String> idiomas = idiomaDelegate.listarLenguajes();
			final Tramite tramite = DelegateUtil.getTramiteDelegate().obtenerTramite(idTramite);

			final ProcedimientoLocal procedimiento = tramite.getProcedimiento();
			resultats.put("idiomas", idiomas);
			resultats.put("idTramit", tramite.getId());
			resultats.put("id_tramit_actual", tramite.getId());
			resultats.put("id_procediment_tramit", procedimiento.getId());
			resultats.put("nom_procediment_tramit", ((TraduccionProcedimiento) procedimiento
					.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto())).getNombre());
			resultats.put("tramit_item_data_actualitzacio", DateUtils.formatDate(tramite.getDataActualitzacio()));
			resultats.put("tramit_item_data_publicacio", DateUtils.formatDateSimpleTime(tramite.getDataPublicacio()));
			resultats.put("tramit_item_data_caducitat", DateUtils.formatDate(tramite.getDataCaducitat()));
			resultats.put("tramit_item_data_inici", DateUtils.formatDateSimpleTime(tramite.getDataInici()));
			resultats.put("tramit_item_data_tancament", DateUtils.formatDateSimpleTime(tramite.getDataTancament()));
			resultats.put("item_moment_tramit", tramite.getFase());
			resultats.put("item_validacio_tramit", tramite.getValidacio());
			// ** resultats.put("item_url_tramit", tramite.getUrlExterna());
			resultats.put("item_tramite_tramit", tramite.getIdTraTel());
			resultats.put("item_version_tramit", tramite.getVersio());
			resultats.put("item_codivuds_tramit", tramite.getCodiVuds());
			resultats.put("item_parametros_tramit", tramite.getParametros());
			if (tramite.getPlataforma() != null) {
				resultats.put("item_plataforma_tramit", tramite.getPlataforma().getId());
			}
			resultats.put("tramit_item_data_vuds", tramite.getDataActualitzacioVuds());
			resultats.put("item_finestreta_unica", procedimiento.getVentanillaUnica());
			resultats.put("item_taxes", procedimiento.getTaxa());

			resultats.put("item_check_tramit_presencial", tramite.isPresencial());
			resultats.put("item_check_tramit_telematico", tramite.isTelematico());

			if (tramite.getOrganCompetent() != null) {
				resultats.put("tramits_item_organ_id", tramite.getOrganCompetent().getId());
			}

			// Idiomas
			for (final String idioma : idiomas) {
				resultats.put(idioma, tramite.getTraduccion(idioma));
				// TraduccionUA traduccionUA = ((TraduccionUA)
				// tramite.getOrganCompetent().getTraduccion(idioma));
				if (tramite.getOrganCompetent() != null) {
					final String nombreUA = tramite.getOrganCompetent().getNombreUnidadAdministrativa(idioma);
					resultats.put("ua_" + idioma, nombreUA);
				}
			}

			final List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			// Documentos relacionados
			resultats.put("documentosTramite",
					getListaDocumentsDTO(request, tramite, langs, tramite.getDocsInformatius()));
			// Formularios relacionados
			resultats.put("formulariosTramite",
					getListaDocumentsDTO(request, tramite, langs, tramite.getFormularios()));
			// Documents Requerits relacionats
			resultats.put("docRequeritsTramite",
					getListaDocumentsDTO(request, tramite, langs, tramite.getDocsRequerits()));
			// Tasas relacionadas
			resultats.put("tasasTramite", getListaTasasDTO(request, tramite, langs));

			// PermiteGuardar
			// No se permitirá guardar sólo si es gestor y se cumple: <br />
			// <ul>
			// <li>Estado de reserva </li>
			// <li>Estado interno y con el check pendiente validar </li></ul>
			final String permisos = getPermisosUsuario(request);
			final boolean gestor = !Usuario.tienePermiso(permisos, Usuario.PERMISO_PUBLICAR_INVENTARIO);
			if (gestor && (procedimiento.getValidacion() == Validacion.RESERVA.intValue()
					|| (procedimiento.isPendienteValidar()
							&& procedimiento.getValidacion() == Validacion.INTERNA.intValue()))) {
				resultats.put("permiteGuardar", "N");
			} else {
				resultats.put("permiteGuardar", "S");
			}

			// PermitirEliminar
			// No se permitirá eliminar al gestor
			if (gestor) { // && proc.getValidacion() == Validacion.RESERVA.intValue()) {
				resultats.put("permiteEliminar", "N");
			} else {
				resultats.put("permiteEliminar", "S");
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

	/**
	 * Obtención del documento en forma correcto con el multiidioma.
	 *
	 * @param request
	 * @param tramite
	 * @param idiomas
	 * @param listaDocuments
	 * @return
	 */
	private List<Map<String, Object>> getListaDocumentsDTO(final HttpServletRequest request, final Tramite tramite,
			final List<String> idiomas, final Set<DocumentTramit> listaDocuments) {
		List<Map<String, Object>> listaRequeritsDTO = null;

		if (listaDocuments != null) {
			listaRequeritsDTO = new ArrayList<Map<String, Object>>();

			for (final DocumentTramit documento : listaDocuments) {

				if (documento != null) {

					final Map<String, String> titulos = new HashMap<String, String>();
					String nombre;
					TraduccionDocumento tradTramite;
					for (final String idioma : idiomas) {

						tradTramite = (TraduccionDocumento) documento.getTraduccion(idioma);
						nombre = (tradTramite != null && tradTramite.getTitulo() != null) ? tradTramite.getTitulo()
								: "";

						titulos.put(idioma, nombre);

					}

					final Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", documento.getId());
					map.put("nom", titulos);
					map.put("moment", tramite.getFase());

					listaRequeritsDTO.add(map);
				}
			}
		}

		return listaRequeritsDTO;
	}

	/**
	 * Obtención de la taxa con el multiidoma.
	 *
	 * @param request
	 * @param tramite
	 * @param idiomas
	 * @return
	 */
	private List<Map<String, Object>> getListaTasasDTO(final HttpServletRequest request, final Tramite tramite,
			final List<String> idiomas) {
		final Set<Taxa> listaTasas = tramite.getTaxes();
		List<Map<String, Object>> listaTasasDTO = null;

		if (listaTasas != null) {
			listaTasasDTO = new ArrayList<Map<String, Object>>();
			for (final Taxa tasa : listaTasas) {

				if (tasa != null) {

					final Map<String, String> titulos = new HashMap<String, String>();
					String nombre;
					TraduccionTaxa tradTaxa;
					for (final String idioma : idiomas) {

						tradTaxa = (TraduccionTaxa) tasa.getTraduccion(idioma);
						nombre = (tradTaxa != null && tradTaxa.getCodificacio() != null) ? tradTaxa.getCodificacio()
								: "";

						titulos.put(idioma, nombre);

					}

					final Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", tasa.getId());
					map.put("nom", titulos);
					map.put("moment", tramite.getFase());

					listaTasasDTO.add(map);
				}
			}
		}

		return listaTasasDTO;
	}

	@RequestMapping(value = "/guardarTramit.do", method = POST)
	public @ResponseBody ResponseEntity<String> guardar(final HttpSession session, final HttpServletRequest request) {

		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		String error = null;
		IdNomDTO result = null;
		Tramite tramite = null;
		final Long idProcedimiento = new Long(request.getParameter("id_procediment_tramit"));

		try {

			final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			final ProcedimientoLocal procedimiento = procedimientoDelegate.obtenerProcedimientoNewBack(idProcedimiento);
			final TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();

			final String idTramite = request.getParameter("id_tramit_actual");
			final boolean edicion = !"".equals(idTramite);

			if (!edicion) {
				if (!tramiteDelegate.autorizaCrearTramite(idProcedimiento))
					throw new SecurityException("Avís: no té permis per a crear el tràmit");

				tramite = new Tramite();
				tramite.setOperativa(Tramite.Operativa.CREA);
				tramite.setOrden(0L);

			} else {

				tramite = tramiteDelegate.obtenerTramite(new Long(idTramite));

				if (!tramiteDelegate.autorizaModificarTramite(tramite.getId()))
					throw new SecurityException("Avís: no té� permis per a crear el tràmit");

				tramite.setOperativa(Tramite.Operativa.MODIFICA);

			}

			final String version = request.getParameter("item_version_tramit") == null ? ""
					: request.getParameter("item_version_tramit");
			final String parametros = request.getParameter("item_parametros") == null ? ""
					: request.getParameter("item_parametros");
			final String idPlataforma = request.getParameter("item_plataforma") == null ? ""
					: request.getParameter("item_plataforma");

			tramite.setVersio(StringUtils.isNumeric(version) && !"".equals(version) ? Integer.parseInt(version) : null);

			// ** tramite.setUrlExterna(
			// ** request.getParameter("item_url_tramit") == null ? "" :
			// request.getParameter("item_url_tramit"));

			tramite.setParametros(parametros);
			if (idPlataforma.isEmpty()) {
				tramite.setPlataforma(null);
			} else {
				final Plataforma plataforma = DelegateUtil.getPlataformaDelegate()
						.obtenerPlataforma(Long.valueOf(idPlataforma));
				tramite.setPlataforma(plataforma);
			}

			tramite.setIdTraTel(request.getParameter("item_tramite_tramit") == null ? ""
					: request.getParameter("item_tramite_tramit"));

			tramite.setTelematico(request.getParameter("item_check_tramit_telematico") != null
					&& !"".equals(request.getParameter("item_check_tramit_telematico")));
			tramite.setPresencial(request.getParameter("item_check_tramit_presencial") != null
					&& !"".equals(request.getParameter("item_check_tramit_presencial")));

			/*
			 * final boolean isTramiteExterno =
			 * request.getParameter("item_url_tramit_ca")!=null &&
			 * !"".equals(request.getParameter("item_check_tramit_telematico")); final
			 * boolean isTramiteInterno = !tramite.getIdTraTel().equals("") ||
			 * !version.isEmpty() || !idPlataforma.isEmpty(); final boolean
			 * isTramiteInternoTodo = !tramite.getIdTraTel().equals("") &&
			 * !version.isEmpty() && !idPlataforma.isEmpty();
			 * 
			 * // si es telematico debe estar rellenos url o version+id, pero no ambos. if
			 * (tramite.isTelematico()) { // Traramos la posible incoherencia de datos if
			 * ((isTramiteExterno && isTramiteInterno) || // estan los dos completados
			 * (!isTramiteExterno && !isTramiteInterno) || // ninguno esta completado
			 * (isTramiteInterno && !isTramiteInternoTodo)) { // Esta relleno todo lo de
			 * interno y no se deja // algo sin rellenar error =
			 * messageSource.getMessage("proc.formulari.error.telematic.sensedades", null,
			 * request.getLocale()); result = new IdNomDTO(-2l, error); return new
			 * ResponseEntity<String>(result.getJson(), responseHeaders,
			 * HttpStatus.ACCEPTED); } } else { // si no es telemático vaciamos los campos.
			 * 
			 * tramite.setVersio(null); //** tramite.setUrlExterna("");
			 * 
			 * tramite.setIdTraTel(""); tramite.setPlataforma(null);
			 * tramite.setParametros(null); }
			 */

			// Traducciones.
			tramite.setTraduccionMap(getTraduccionesTramite(request, tramite));

			// final boolean urlTramiteRelleno = !servicio.getTramiteUrl().equals("");
			// Buscamos si la url está en algún idioma
			boolean urlTramiteRelleno = false;
			for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
				final TraduccionTramite t = ((TraduccionTramite) tramite.getTraduccion(lang));
				if (t != null && t.getUrlTramiteExterno() != null && !"".equals(t.getUrlTramiteExterno())) {
					urlTramiteRelleno = true;
				}
			}

			final boolean isTramiteInterno = !tramite.getIdTraTel().equals("") || !version.isEmpty()
					|| !idPlataforma.isEmpty() || !parametros.isEmpty();
			final boolean isTramiteInternoTodo = !tramite.getIdTraTel().equals("") && !version.isEmpty()
					&& !idPlataforma.isEmpty();

			// si es telematico debe estar rellenos url o version+id, pero no ambos.
			if (tramite.isTelematico()) {
				// Tratamos la posible incoherencia de datos
				if ((urlTramiteRelleno && isTramiteInterno) || // estan los dos completados
						(!urlTramiteRelleno && !isTramiteInterno) || // ninguno esta completado
						(isTramiteInterno && !isTramiteInternoTodo)) { // Esta relleno todo lo de interno y no se deja
																		// algo sin rellenar
					error = messageSource.getMessage("proc.formulari.error.telematic.sensedades", null,
							request.getLocale());
					result = new IdNomDTO(-2l, error);
					return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);
				}
			} else {
				// si no es telemático vaciamos los campos.
				tramite.setVersio(null);
				tramite.setIdTraTel("");
				tramite.setPlataforma(null);
				tramite.setParametros(null);
			}

			// 1 - Inicialización
			// 2 - Instrucción
			// 3 - Finalización
			final int fase = Integer.parseInt(request.getParameter("item_moment_tramit"));
			final boolean isProcedimientoConEstadoPublicacionPublica = DelegateUtil.getProcedimientoDelegate()
					.isProcedimientoConEstadoPublicacionPublica(idProcedimiento);

			// Si se cambia el estado del trámite de iniciación a otro tipo y el
			// procedimiento
			// al cual está asociado tiene como estado de publicación público, impedimos
			// esta
			// edición, ya que en ese estado el procedimiento no puede quedarse sin trámite
			// de iniciación.
			if (edicion) {

				final int faseAnterior = tramite.getFase();

				if (faseAnterior == Tramite.INICIACION && fase != faseAnterior
						&& isProcedimientoConEstadoPublicacionPublica) {

					error = messageSource.getMessage("error.tramit_inici_no_es_pot_canviar_tipus", null,
							request.getLocale());
					result = new IdNomDTO(-2l, error);

					return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);

				}

			}

			// #400, tanto si es publico como si no,
			// solo puede haber un tramite de iniciciación
			if (fase == Tramite.INICIACION
					&& procedimientoDelegate.existeOtroTramiteInicioProcedimiento(idProcedimiento, tramite.getId())) {

				error = messageSource.getMessage("error.tramit_inici_ja_existeix", null, request.getLocale());
				result = new IdNomDTO(-2l, error);

				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);

			} else {

				tramite.setFase(fase);

			}

			// #4 si el tramite tiene momento=ini, el procedimiento es publico debe tener
			// modelo solicitud obligatoriamente
			if (edicion && isProcedimientoConEstadoPublicacionPublica && fase == 1
					&& (request.getParameter("formularisTramit") == null
							|| request.getParameter("formularisTramit").equals(""))
					&& tramite.isPresencial() // #438 solo se pide el modelo si es presencial
			) {

				error = messageSource.getMessage("error.tramit_inici_sin_model", null, request.getLocale());
				result = new IdNomDTO(-3l, error);

				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);

			}
			// 1 - Pública
			// 2 - Interna
			// 3 - Reserva
			if (!"".equals(request.getParameter("item_validacio_tramit"))) {
				tramite.setValidacio(new Long(request.getParameter("item_validacio_tramit")));
			}

			// Parsear fechas en request y asignarlas al trámite.
			procesarFechasTramite(request, tramite);
			// Rellenar los campos
			tramite.setCodiVuds(request.getParameter("item_id_codivuds_tramit"));
			// Traducciones.
			// tramite.setTraduccionMap(getTraduccionesTramite(request, tramite));

			final Scanner scanner = new Scanner(request.getParameter("tramits_item_organ_id"));
			if (scanner.hasNextLong()) {
				final UnidadAdministrativa unidadAdministrativa = DelegateUtil.getUADelegate()
						.obtenerUnidadAdministrativa(scanner.nextLong());
				tramite.setOrganCompetent(unidadAdministrativa);
			}
			scanner.close();

			tramite.setProcedimiento(procedimiento);
			final String idOrganCompetent = request.getParameter("tramits_item_organ_id");
			if (procedimiento.isComun()
					&& procedimiento.getUnidadAdministrativa().getId().compareTo(Long.valueOf(idOrganCompetent)) != 0) {
				error = messageSource.getMessage("error.tramit_comun_distinta_ua", null, request.getLocale());
				result = new IdNomDTO(-3l, error);

				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.ACCEPTED);
			}

			tramiteDelegate.grabarTramite(tramite, !"".equals(idOrganCompetent) ? new Long(idOrganCompetent)
					: procedimiento.getUnidadAdministrativa().getId());

			if (!edicion) {
				request.setAttribute("alert", "confirmacion.alta");
				procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
			} else {
				request.setAttribute("alert", "confirmacion.modificacion");
				// Guardar tasas.
				guardaTasasTramite(request, tramite, tramiteDelegate);
				// Guardar documentos y formularios.
				guardaDocumentosYFormularios(request, edicion, tramite, tramiteDelegate);
			}

			// TODO amartin: tras la refactorización usando métodos privados como soporte a
			// los públicos,
			// veo que esta instrucción ya se ejecuta un par de líneas antes. Desconozco si
			// es
			// código duplicado o algo necesario. ACLARAR.

			// #386 Recuperació col·leccions de tràmits amb elements nuls, esto hace que se
			// actualice el orden del tramite dos veces, y como
			// la lista está definida index column="TRA_ORDEN" deja el hueco del orden
			// anterior vacío
			// if (!edicion)
			// procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);

		} catch (final ValidateVudsException e) {

			String camps = "";
			for (final String camp : e.getCampsSenseValidar())
				camps += "\\n-" + camp;

			error = messageSource.getMessage("error.validacio.tramit" + ".Camps sense validar: " + camps, null,
					request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));

		} catch (final ActualizacionVudsException e) {

			try {

				// Ha fallado la actualización pero el trámite ha sido creado correctamente, así
				// que se añade al procedimiento.
				final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
				procedimientoDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
				error = messageSource.getMessage("error.enviament.tramit", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(e));
				request.setAttribute("idSelect", tramite.getId());

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

		if (result == null) {
			result = new IdNomDTO();
			result.setId(tramite.getId());
			result.setNom(((TraduccionTramite) tramite.getTraduccion("ca")).getNombre());
		}

		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);

	}

	private Map<String, Traduccion> getTraduccionesTramite(final HttpServletRequest request, final Tramite tramite)
			throws DelegateException {
		TraduccionTramite traduccionTramite;
		final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		final List<String> langs = idiomaDelegate.listarLenguajes();

		final Map<String, Traduccion> traducciones = new HashMap<String, Traduccion>(langs.size());
		for (final String lang : langs) {
			traduccionTramite = (TraduccionTramite) tramite.getTraduccion(lang);
			if (traduccionTramite == null)
				traduccionTramite = new TraduccionTramite();

			agregaTraduccionTramite(request, lang, traducciones, traduccionTramite);
		}

		return traducciones;
	}

	private void guardaDocumentosYFormularios(final HttpServletRequest request, final boolean edicion,
			final Tramite tramite, final TramiteDelegate tramiteDelegate)
			throws NumberFormatException, DelegateException {
		// Recuperamos todos los documentos del request
		String documentosTramite = request.getParameter("documentsTramit");
		String separador = (!"".equals(documentosTramite)) ? "," : "";
		documentosTramite += (!"".equals(request.getParameter("formularisTramit"))
				? separador + request.getParameter("formularisTramit")
				: "");
		separador = (!"".equals(documentosTramite)) ? "," : "";
		documentosTramite += (!"".equals(request.getParameter("documentsRequerits"))
				? separador + request.getParameter("documentsRequerits")
				: "");

		// Unimos todos los documentos en un solo Set
		final List<DocumentTramit> listaDocumentosOld = new ArrayList<DocumentTramit>();
		listaDocumentosOld.addAll(tramite.getDocsInformatius());
		listaDocumentosOld.addAll(tramite.getFormularios());
		listaDocumentosOld.addAll(tramite.getDocsRequerits());

		// Comprobamos de que tengamos tramites desde el request
		if ("".equals(documentosTramite) && listaDocumentosOld.size() > 0) {

			tramiteDelegate.borrarDocumentos(tramite, listaDocumentosOld);

		} else {

			final List<DocumentTramit> documentosNuevos = new ArrayList<DocumentTramit>();
			final String[] codigosDocumentosNuevos = documentosTramite.split(",");
			final List<DocumentTramit> listaDocumentosBorrar = new ArrayList<DocumentTramit>();
			if (edicion) {

				for (final DocumentTramit documentoTramite : listaDocumentosOld) {

					int i = 0;
					while (i < codigosDocumentosNuevos.length) {
						if (!"".equals(codigosDocumentosNuevos[i])
								&& documentoTramite.getId().equals(Long.valueOf(codigosDocumentosNuevos[i]))) {
							documentosNuevos.add(documentoTramite);
							i = codigosDocumentosNuevos.length;
						}
						i++;
					}

					// Eliminar los que se han quitado de la lista.
					if (!documentosNuevos.contains(documentoTramite)) {
						tramite.removeDocument(documentoTramite);
						listaDocumentosBorrar.add(documentoTramite);
					}
				}

				if (listaDocumentosBorrar.size() > 0)
					tramiteDelegate.borrarDocumentos(tramite, listaDocumentosBorrar);

			}

			// Actualizar el orden de la lista de documentos.
			final HashMap<String, String[]> actualizadorDocs = new HashMap<String, String[]>();
			for (final DocumentTramit documentTramit : documentosNuevos) {
				final Long idDoc = documentTramit.getId();
				String ordenDocumento = "";

				if (documentTramit.getTipus() == 0)
					ordenDocumento = request.getParameter("documentsTramit_orden_" + idDoc);
				else if (documentTramit.getTipus() == 1)
					ordenDocumento = request.getParameter("formularisTramit_orden_" + idDoc);
				else
					ordenDocumento = request.getParameter("documentsRequerits_orden_" + idDoc);

				final String[] orden = { ordenDocumento };
				actualizadorDocs.put("orden_doc" + idDoc, orden);
			}

			tramiteDelegate.actualizarOrdenDocs(actualizadorDocs, new Long(request.getParameter("id_tramit_actual")));

		}

	}

	private void guardaTasasTramite(final HttpServletRequest request, final Tramite tramite,
			final TramiteDelegate tramiteDelegate) throws DelegateException {
		final String tasasTramite = request.getParameter("taxesTramit");
		final Set<Taxa> listaTasasOld = tramite.getTaxes();

		if (!"".equals(tasasTramite)) {

			final List<Taxa> listaTasasBorrar = new ArrayList<Taxa>();
			final Set<Taxa> tasasNuevas = new HashSet<Taxa>();
			final String[] codigosTasasNuevas = tasasTramite.split(",");

			for (int i = 0; i < codigosTasasNuevas.length; i++) {
				for (final Taxa tasa : listaTasasOld) {
					if (!"".equals(codigosTasasNuevas[i]) && tasa.getId().equals(Long.valueOf(codigosTasasNuevas[i]))) {

						tasasNuevas.add(tasa);
						codigosTasasNuevas[i] = null;

						break;

					}
				}
			}

			// Eliminar los que se han quitado de la lista
			for (final Taxa tasa : listaTasasOld) {
				if (!tasasNuevas.contains(tasa))
					listaTasasBorrar.add(tasa);
			}

			for (final Taxa tasa : listaTasasBorrar) {
				tramite.removeTaxa(tasa);
				tramiteDelegate.borrarTaxa(tasa.getId());
			}

			// Crear los nuevos.
			if (!"".equals(codigosTasasNuevas)) {
				for (final String codigoTasa : codigosTasasNuevas) {
					if (codigoTasa != null) {
						for (final Taxa tasa : listaTasasOld) {
							if (!tasasNuevas.contains(tasa))
								tasasNuevas.add(tasa);
						}
					}
				}
			}

			// Actualizamos el orden de la lista de tasas.
			final HashMap<String, String[]> actualizadorTasas = new HashMap<String, String[]>();

			for (final Taxa tasa : tasasNuevas) {
				final String[] orden = { request.getParameter("taxesTramit_orden_" + tasa.getId()) };
				actualizadorTasas.put("orden_taxa" + tasa.getId(), orden);
			}

			DelegateUtil.getTramiteDelegate().actualizarOrdenTasas(actualizadorTasas, tramite.getId());

		} else {

			for (final Taxa taxa : listaTasasOld) {
				tramite.removeTaxa(taxa);
				tramiteDelegate.borrarTaxa(taxa.getId());
			}

		}

	}

	private void procesarFechasTramite(final HttpServletRequest request, final Tramite tramite) {
		final Date fechaInicio = DateUtils.parseDateSimpleTime(request.getParameter("tramit_item_data_inici"));
		tramite.setDataInici(fechaInicio);

		final Date fechaCierre = DateUtils.parseDateSimpleTime(request.getParameter("tramit_item_data_tancament"));
		tramite.setDataTancament(fechaCierre);

		final Date fechaPublicacion = DateUtils
				.parseDateSimpleTime(request.getParameter("tramit_item_data_publicacio"));
		tramite.setDataPublicacio(fechaPublicacion);

		final Date fechaActualizacion = DateUtils.parseDate(request.getParameter("tramit_item_data_actualitzacio"));
		tramite.setDataActualitzacio(fechaActualizacion);

		final Date fechaCaducidad = DateUtils.parseDate(request.getParameter("tramit_item_data_caducitat"));
		tramite.setDataCaducitat(fechaCaducidad);

		final Date fechaVUDS = DateUtils.parseDate(request.getParameter("tramit_item_data_vuds"));
		final String fechaActualizacionVUDS = (fechaVUDS != null) ? new SimpleDateFormat("dd/MM/yyyy").format(fechaVUDS)
				: "";
		tramite.setDataActualitzacioVuds(fechaActualizacionVUDS);
	}

	private void agregaTraduccionTramite(final HttpServletRequest request, final String lang,
			final Map<String, Traduccion> traducciones, final TraduccionTramite traduccionTramite) {
		traduccionTramite.setNombre(RolUtil.limpiaCadena(request.getParameter("item_nom_tramit_" + lang)));
		// #351 se cambia descripcion por observaciones
		// traduccionTramite.setDescripcion(
		// RolUtil.limpiaCadena(request.getParameter("item_descripcio_tramit_" + lang))
		// );
		traduccionTramite.setRequisits(RolUtil.limpiaCadena(request.getParameter("item_requisits_tramit_" + lang)));
		traduccionTramite
				.setDocumentacion(RolUtil.limpiaCadena(request.getParameter("item_documentacio_tramit_" + lang)));
		traduccionTramite.setPlazos(RolUtil.limpiaCadena(request.getParameter("item_termini_tramit_" + lang)));
		traduccionTramite.setLugar(RolUtil.limpiaCadena(request.getParameter("item_lloc_tramit_" + lang)));

		// TODO Este campo no existe en la tabla pero se deja por si se anyade en
		// futuras implementaciones.
		traduccionTramite.setObservaciones(request.getParameter("item_descripcio_tramit_" + lang));

		traduccionTramite.setUrlTramiteExterno(request.getParameter("item_url_tramit_" + lang));

		traducciones.put(lang, traduccionTramite);
	}

	@RequestMapping(value = "/esborrarTramit.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();

		final Long idTramite = new Long(request.getParameter("id"));
		final Long idProcedimiento = new Long(request.getParameter("idProcediment"));

		try {

			final ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			final TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();

			// Quita el tramite de la lista
			procedimientoDelegate.eliminarTramite(idTramite, idProcedimiento);
			tramiteDelegate.borrarTramite(idTramite, idProcedimiento);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else if (dEx.isIllegalStateException()) {
				resultatStatus.setId(-3l);
				logException(log, dEx);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}

		}

		return resultatStatus;

	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final TraduccionTramite traduccioOrigen = getTraduccionOrigen(request);
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();

			final String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
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

		} catch (final NullPointerException npe) {

			log.error("tramiteBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		} catch (final Exception e) {

			log.error("TramiteBackController.traduir: Error en al traducir tramite: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		}

		return resultats;

	}

	private TraduccionTramite getTraduccionOrigen(final HttpServletRequest request) {

		final TraduccionTramite traduccioOrigen = new TraduccionTramite();

		if (StringUtils.isNotEmpty(request.getParameter("item_nom_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			// #351 se cambia descripcion por observaciones
			// traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_tramit_"
			// + IDIOMA_ORIGEN_TRADUCTOR));
			traduccioOrigen.setObservaciones(request.getParameter("item_descripcio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_requisits_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setRequisits(request.getParameter("item_requisits_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_documentacio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen
					.setDocumentacion(request.getParameter("item_documentacio_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_termini_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setPlazos(request.getParameter("item_termini_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}
		if (StringUtils.isNotEmpty(request.getParameter("item_lloc_tramit_" + IDIOMA_ORIGEN_TRADUCTOR))) {
			traduccioOrigen.setLugar(request.getParameter("item_lloc_tramit_" + IDIOMA_ORIGEN_TRADUCTOR));
		}

		return traduccioOrigen;

	}

}