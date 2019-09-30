package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.TraduccionPlataforma;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.PlataformasDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PlataformaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/plataformes/")
public class TMPlataformesTramitacioController extends PantallaBaseController {

	private static Log log = LogFactory.getLog(TMPlataformesTramitacioController.class);

	@RequestMapping(value = "/plataformes.do")
	public String pantalla(final Map<String, Object> model, final HttpServletRequest request) {

		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMPlataformesTramitacio.jsp");

		final RolUtil rolUtil = new RolUtil(request);
		if (rolUtil.userIsAdmin()) {
			model.put("escriptori", "pantalles/taulesMestres/tmPlataformesTramitacio.jsp");
		} else {
			model.put("error", "permisos");
		}

		loadIndexModel(model, request);
		return "index";
	}

	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(final HttpServletRequest request) {

		final List<PlataformasDTO> llistaPlataformaslDTO = new ArrayList<PlataformasDTO>();
		final Map<String, Object> resultats = new HashMap<String, Object>();
		final Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("username", request.getParameter("cerca_codi"));
		paramMap.put("ordreCamp", request.getParameter("ordreCamp"));
		paramMap.put("ordreTipus", request.getParameter("ordreTipus"));

		// Información de paginación
		String pagPag = request.getParameter("pagPag");
		String pagRes = request.getParameter("pagRes");

		if (pagPag == null) {
			pagPag = String.valueOf(0);
		}
		if (pagRes == null) {
			pagRes = String.valueOf(100);
		}

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {
			final PlataformaDelegate plataformaDelegate = DelegateUtil.getPlataformaDelegate();
			resultadoBusqueda = plataformaDelegate.buscadorListarPlataforma(paramMap, Integer.parseInt(pagPag),
					Integer.parseInt(pagRes), false, false);

			for (final Plataforma plataforma : (List<Plataforma>) resultadoBusqueda.getListaResultados()) {
				llistaPlataformaslDTO.add(
						new PlataformasDTO(plataforma.getId(), plataforma.getIdentificador(), plataforma.getOrden()));
			}

		} catch (final DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaPlataformaslDTO);
		return resultats;
	}

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();
		try {
			final String codigo = request.getParameter("id");
			final Plataforma plataforma = DelegateUtil.getPlataformaDelegate().obtenerPlataforma(Long.valueOf(codigo));

			resultats.put("item_codi", plataforma.getId());
			resultats.put("item_nombre", plataforma.getIdentificador());
			resultats.put("item_orden", plataforma.getOrden());
			final String langDefault = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			final List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			for (final String lang : langs) {
				if (plataforma.getTraduccion(lang) != null) {
					resultats.put(lang, plataforma.getTraduccion(lang));
				} else {
					if (plataforma.getTraduccion(langDefault) != null) {
						resultats.put(lang, plataforma.getTraduccion(langDefault));
					} else {
						resultats.put(lang, new TraduccionProcedimientoLocal());
					}
				}
			}

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

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(final HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			final PlataformaDelegate plataformaDelegate = DelegateUtil.getPlataformaDelegate();

			final Plataforma plataforma = getPlataforma(request.getParameter("item_id"));
			final String identificador = request.getParameter("item_nom");
			plataforma.setIdentificador(identificador);

			TraduccionPlataforma tpl;

			for (final String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

				tpl = (TraduccionPlataforma) plataforma.getTraduccion(lang);

				if (tpl == null) {
					tpl = new TraduccionPlataforma();
				}

				tpl.setDescripcion(request.getParameter("item_descripcion_" + lang));
				tpl.setUrlAcceso(request.getParameter("item_url_" + lang));

				plataforma.setTraduccion(lang, tpl);

			}

			plataformaDelegate.grabarPlataforma(plataforma);

			final String ok = messageSource.getMessage("plataformas.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(null, ok);

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

	/**
	 * Obtiene la plataforma o de BBDD o si no existe la crea.
	 *
	 * @param item_id
	 * @return
	 * @throws DelegateException
	 */
	private Plataforma getPlataforma(final String item_id) throws DelegateException {
		Plataforma plataforma;
		try {
			final Long id = Long.parseLong(item_id);
			plataforma = DelegateUtil.getPlataformaDelegate().obtenerPlataforma(id);
		} catch (final NumberFormatException nfe) {
			plataforma = new Plataforma();
		}
		return plataforma;
	}

	/**
	 * Borra plataforma.
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/esborrarPlataforma.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();
		try {
			final String id = request.getParameter("id");

			final boolean existeRelacion = DelegateUtil.getPlataformaDelegate()
					.plataformaConAsociaciones(Long.valueOf(id));
			if (existeRelacion) {
				final String error = messageSource.getMessage("plataformas.error.asociado", null, request.getLocale());
				resultatStatus.setNom(error);
				resultatStatus.setId(-2l);
			} else {
				DelegateUtil.getPlataformaDelegate().borrarPlataforma(Long.valueOf(id));
				resultatStatus.setId(1l);
				resultatStatus.setNom("correcte");
			}

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

	@RequestMapping(value = "/reordenarPlataformes.do", method = POST)
	public @ResponseBody IdNomDTO reordenarPlataformes(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();

		try {
			final String id = request.getParameter("id");
			final Integer nuevoOrden = new Integer(request.getParameter("orden")) + 1;
			final Integer ordenOld = new Integer(request.getParameter("ordenAnterior")) + 1;

			DelegateUtil.getPlataformaDelegate().reordenar(Long.valueOf(id), nuevoOrden, ordenOld);

		} catch (final DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (final NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de plataforma no numèric: " + ExceptionUtils.getStackTrace(nfEx));
		}

		return resultatStatus;
	}

}
