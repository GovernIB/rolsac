package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.LopdLegitimacion;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionLopdLegitimacion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.LopdLegitimacionDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@Controller
@RequestMapping("/lopd/")
public class TMLopdController extends PantallaBaseController {

	private static Log log = LogFactory.getLog(TMIndexController.class);

	@RequestMapping(value = "/legitimacion.do", method = GET)
	public String pantalla(final Map<String, Object> model, final HttpServletRequest request) {
		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMLopdLegitimacion.jsp");

		final RolUtil rolUtil = new RolUtil(request);
		if (rolUtil.userIsAdmin()) {
			model.put("escriptori", "pantalles/taulesMestres/tmLopdLegitimacion.jsp");
		} else {
			model.put("error", "permisos");
		}

		loadIndexModel(model, request);
		return "index";
	}

	@RequestMapping(value = "/llistatLopdLegitimacion.do")
	public @ResponseBody Map<String, Object> llistat(final HttpServletRequest request) {

		final List<LopdLegitimacionDTO> llistaLopdLegitimacionDTO = new ArrayList<LopdLegitimacionDTO>();
		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			// Información de paginación
			String pagPag = request.getParameter("pagPag");
			String pagRes = request.getParameter("pagRes");
			String ordenCampo = request.getParameter("ordreCamp");
			String ordenAsc = request.getParameter("ordreTipus");
			// String idioma = request.getLocale().getLanguage().toLowerCase();

			if (pagPag == null) {
				pagPag = String.valueOf(0);
			}
			if (pagRes == null) {
				pagRes = String.valueOf(10);
			}
			if (ordenCampo == null) {
				ordenCampo = "id";
			}
			if (ordenAsc == null) {
				ordenAsc = "ASC";
			}

			final ResultadoBusqueda resultado = DelegateUtil.getLopdLegitimacionDelegate()
					.getLopdLegitimacion(Integer.parseInt(pagPag), Integer.parseInt(pagRes), ordenCampo, ordenAsc);

			resultats.put("total", resultado.getTotalResultados());
			llistaLopdLegitimacionDTO.addAll(convertLopdLegitimacionToLopdLegitimacionDTO(resultado));
			resultats.put("nodes", llistaLopdLegitimacionDTO);

		} catch (final DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
			if (dEx.getCause() == null) {
				resultats.put("error", dEx.getMessage());
			} else {
				resultats.put("error", dEx.getCause().getMessage());
			}
		}

		return resultats;
	}

	/**
	 * Convierte LopdLegitimacion en LopdLegitimacionDTOs.
	 *
	 * @param resultadoBusqueda
	 * @return
	 * @throws DelegateException
	 */
	private List<LopdLegitimacionDTO> convertLopdLegitimacionToLopdLegitimacionDTO(
			final ResultadoBusqueda resultadoBusqueda) throws DelegateException {

		final List<LopdLegitimacionDTO> lopdLegitimacionsDTo = new ArrayList<LopdLegitimacionDTO>();

		for (final LopdLegitimacion lopdLegitimacion : (List<LopdLegitimacion>) resultadoBusqueda
				.getListaResultados()) {
			lopdLegitimacionsDTo.add(new LopdLegitimacionDTO(lopdLegitimacion.getId(),
					lopdLegitimacion.getIdentificador(), lopdLegitimacion.isPorDefecto()));
		}
		return lopdLegitimacionsDTo;
	}

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(final HttpServletRequest request) {
		final Map<String, Object> resultats = new HashMap<String, Object>();
		try {
			final Long id = new Long(request.getParameter("id"));
			final LopdLegitimacion lopdLeg = DelegateUtil.getLopdLegitimacionDelegate().obtenerLopdLegitimacion(id);

			resultats.put("item_id", lopdLeg.getId());
			resultats.put("item_por_defecto", lopdLeg.isPorDefecto());
			resultats.put("item_identificador", lopdLeg.getIdentificador());
			omplirCampsTraduibles(resultats, lopdLeg);

		} catch (final DelegateException dEx) {
			log.error(ExceptionUtils.getStackTrace(dEx));
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
		}

		return resultats;
	}

	private void omplirCampsTraduibles(final Map<String, Object> resultats, final LopdLegitimacion lopdLeg)
			throws DelegateException {

		final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		final List<String> langs = idiomaDelegate.listarLenguajes();

		for (final String lang : langs) {

			if (null != lopdLeg.getTraduccion(lang)) {

				final HashMap<String, String> traduccionMateriaDTO = new HashMap<String, String>();
				final TraduccionLopdLegitimacion tm = (TraduccionLopdLegitimacion) lopdLeg.getTraduccion(lang);
				traduccionMateriaDTO.put("item_nombre_" + lang, tm.getNombre());
				resultats.put(lang, traduccionMateriaDTO);
			} else {
				resultats.put(lang, new HashMap<String, String>());
			}
		}
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody Map<String, Object> grabarLopdLegitimacion(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();
		// Boolean result = false;

		try {
			final LopdLegitimacion lopdLegitimacion = new LopdLegitimacion();
			if (!request.getParameter("item_id").isEmpty()) {
				final Long id = Long.valueOf(request.getParameter("item_id"));
				lopdLegitimacion.setId(id);
			}

			final String identificador = request.getParameter("item_identificador");
			lopdLegitimacion.setIdentificador(identificador);
			lopdLegitimacion.setPorDefecto(false);
			if (request.getParameter("item_por_defecto") != null) {
				lopdLegitimacion.setPorDefecto(request.getParameter("item_por_defecto").equals("1")
						|| request.getParameter("item_por_defecto").equals("true"));
			}

			final Map<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
			final TraduccionLopdLegitimacion tradEs = new TraduccionLopdLegitimacion();
			tradEs.setNombre(request.getParameter("item_nombre_es"));
			traducciones.put("es", tradEs);

			final TraduccionLopdLegitimacion tradCat = new TraduccionLopdLegitimacion();
			tradCat.setNombre(request.getParameter("item_nombre_ca"));
			traducciones.put("ca", tradCat);

			lopdLegitimacion.setTraducciones(traducciones);

			DelegateUtil.getLopdLegitimacionDelegate().grabarLopdLegitimacion(lopdLegitimacion);
		} catch (final DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}

			String mensajeError = ExceptionUtils.getCause(dEx).getMessage();
			if (mensajeError.contains("javax.ejb.EJBException:")) {
				final int posicion = mensajeError.indexOf("javax.ejb.EJBException:") + 23;
				mensajeError = mensajeError.substring(posicion);
			}
			resultats.put("error", mensajeError);
		} catch (final Exception dEx) {
			if (dEx.getCause() == null) {
				resultats.put("error", dEx.getMessage());
			} else {
				resultats.put("error", dEx.getCause().getMessage());
			}
		}

		return resultats;
	}

	@RequestMapping(value = "/borrar.do", method = POST)
	public @ResponseBody IdNomDTO borrarLopdLegitimacion(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();

		try {
			final Long id = Long.valueOf(request.getParameter("id"));
			DelegateUtil.getLopdLegitimacionDelegate().borrarLopdLegitimacion(id);

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

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			final String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			final TraduccionLopdLegitimacion traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
		} catch (final NullPointerException npe) {
			log.error("FetsVitalsBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (final Exception e) {
			log.error("FetsVitalsBackController.traduir: Error en al traducir normativa: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}

		return resultats;
	}

	/**
	 * GetTraduccionOrigen
	 *
	 * @param request
	 * @param idiomaOrigenTraductor
	 * @return
	 */
	private TraduccionLopdLegitimacion getTraduccionOrigen(final HttpServletRequest request,
			final String idiomaOrigenTraductor) {
		final TraduccionLopdLegitimacion traduccioOrigen = new TraduccionLopdLegitimacion();
		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}
		return traduccioOrigen;
	}
}
