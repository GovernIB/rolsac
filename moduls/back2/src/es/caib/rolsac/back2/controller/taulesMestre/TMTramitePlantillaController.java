package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
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
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Plataforma;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionTramitePlantilla;
import org.ibit.rol.sac.model.TramitePlantilla;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.TramitePlantillaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@Controller
@RequestMapping("/plantillaTram/")
public class TMTramitePlantillaController extends PantallaBaseController {

	private static Log log = LogFactory.getLog(TMTramitePlantillaController.class);

	@RequestMapping(value = "/plantillaTram.do")
	public String pantalla(final Map<String, Object> model, final HttpServletRequest request) {

		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMTramitePlantilla.jsp");

		final RolUtil rolUtil = new RolUtil(request);

		if (rolUtil.userIsAdmin()) {

			model.put("escriptori", "pantalles/taulesMestres/tmPlantillaTramite.jsp");

			// afegir llista de perfils
			String nombrePerfil;
			final List<IdNomDTO> perfilsDTO = new LinkedList<IdNomDTO>();
			final PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();

			try {

				final String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

				for (final PerfilCiudadano perfil : (List<PerfilCiudadano>) perfilDelegate.listarPerfiles()) {
					final Traduccion traPerfil = perfil.getTraduccion(lang);
					nombrePerfil = traPerfil == null ? "" : ((TraduccionPerfilCiudadano) traPerfil).getNombre();
					perfilsDTO.add(new IdNomDTO(perfil.getId(), nombrePerfil));
				}

				model.put("perfils", perfilsDTO);

				// Boletines.
				model.put("llistaPlataforma", getListaPlataformaDTO());

			} catch (final DelegateException dEx) {

				if (dEx.isSecurityException()) {
					log.error("Permisos insuficients: " + dEx.getMessage());
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

	/**
	 * Get lista plataforma dto
	 *
	 * @throws DelegateException
	 **/
	private List<IdNomDTO> getListaPlataformaDTO() throws DelegateException {
		final List<Plataforma> listaPlataforma = DelegateUtil.getPlataformaDelegate().listarPlataforma();
		final List<IdNomDTO> listaPlataformaDTO = new ArrayList<IdNomDTO>();
		for (final Plataforma plataforma : listaPlataforma) {
			final IdNomDTO bol = new IdNomDTO(plataforma.getId(), plataforma.getIdentificador());
			listaPlataformaDTO.add(bol);
		}

		return listaPlataformaDTO;
	}

	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(final HttpServletRequest request) {

		final List<Map<String, Object>> llistaTramitesplantilla = new ArrayList<Map<String, Object>>();
		Map<String, Object> plantillaDTO;
		final Map<String, Object> resultats = new HashMap<String, Object>();

		// Información de paginación
		String pagPag = request.getParameter("pagPag");
		String pagRes = request.getParameter("pagRes");

		if (pagPag == null)
			pagPag = String.valueOf(0);
		if (pagRes == null)
			pagRes = String.valueOf(10);

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {

			final TramitePlantillaDelegate tramitePlantillaDelegate = DelegateUtil.getTramitePlantillaDelegate();
			final String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			resultadoBusqueda = tramitePlantillaDelegate.listarTramitePlantilla(Integer.parseInt(pagPag),
					Integer.parseInt(pagRes), idiomaPorDefecto);

			for (final Object o : resultadoBusqueda.getListaResultados()) {

				final Long codigo = (Long) ((Object[]) o)[0];
				final String nom = ((Object[]) o)[1] == null ? "" : (String) ((Object[]) o)[1];
				// final String descripcio = ((Object[]) o)[2] == null ? "" : (String)
				// ((Object[]) o)[2];

				plantillaDTO = new HashMap<String, Object>();
				plantillaDTO.put("id", codigo);// 366
				plantillaDTO.put("nom", nom);

				llistaTramitesplantilla.add(plantillaDTO);

			}

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}

		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaTramitesplantilla);
		return resultats;

	}

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(final String id, final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			final TramitePlantillaDelegate tramitePlantillaDelegate = DelegateUtil.getTramitePlantillaDelegate();
			final TramitePlantilla tramitePlantilla = tramitePlantillaDelegate
					.obtenerTramitePlantilla(Long.valueOf(id));
			resultats.put("item_id", tramitePlantilla.getId());
			resultats.put("item_identificador", tramitePlantilla.getIdentificador());
			resultats.put("item_version", tramitePlantilla.getVersion());
			resultats.put("item_parametros", tramitePlantilla.getParametros());
			resultats.put("item_plataforma", tramitePlantilla.getPlataforma().getId());
			omplirCampsTraduibles(resultats, tramitePlantilla);

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

	private void omplirCampsTraduibles(final Map<String, Object> resultats, final TramitePlantilla tramitePlantilla)
			throws DelegateException {

		final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		final List<String> langs = idiomaDelegate.listarLenguajes();

		for (final String lang : langs) {

			if (null != tramitePlantilla.getTraduccion(lang)) {
				final HashMap<String, String> traduccionDTO = new HashMap<String, String>();
				final TraduccionTramitePlantilla tm = (TraduccionTramitePlantilla) tramitePlantilla.getTraduccion(lang);

				traduccionDTO.put("item_nombre", tm.getNombre());
				resultats.put(lang, traduccionDTO);

			} else {
				resultats.put(lang, new TraduccionTramitePlantilla());
			}

		}

	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody Map<String, Object> guardar(final HttpServletRequest request) {

		IdNomDTO result = null;
		final Map<String, Object> resultats = new HashMap<String, Object>();
		String error = null;

		try {

			final TramitePlantillaDelegate tramitePlantillaDelegate = DelegateUtil.getTramitePlantillaDelegate();
			final TramitePlantilla tramitePlantilla = new TramitePlantilla();
			TramitePlantilla tramitePlantillaOld = null;
			boolean edicion;

			edicion = request.getParameter("item_idAnt") != null && !"".equals(request.getParameter("item_idAnt"));
			Long id = null;
			if (request.getParameter("item_id") != null && !request.getParameter("item_id").isEmpty()) {
				id = Long.valueOf(request.getParameter("item_id"));
				tramitePlantilla.setId(id);
			}

			if (edicion) {
				tramitePlantillaOld = tramitePlantillaDelegate.obtenerTramitePlantilla(id);
			}

			// Idiomas
			TraduccionTramitePlantilla tf;
			final IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			final List<String> langs = idiomaDelegate.listarLenguajes();

			for (final String lang : langs) {

				if (edicion) {

					tf = (TraduccionTramitePlantilla) tramitePlantillaOld.getTraduccion(lang);
					if (tf == null) {
						tf = new TraduccionTramitePlantilla();
					}

				} else {

					tf = new TraduccionTramitePlantilla();

				}

				tf.setNombre(RolUtil.limpiaCadena(request.getParameter("item_nombre_" + lang)));
				tramitePlantilla.setTraduccion(lang, tf);

			}

			// Fin idiomas
			tramitePlantilla.setIdentificador(request.getParameter("item_identificador")); // Identificador
			tramitePlantilla.setParametros(request.getParameter("item_parametros")); // Signatura
			tramitePlantilla.setVersion(request.getParameter("item_version")); // Signatura
			final String plata = request.getParameter("item_plataforma");
			final Plataforma plataforma = DelegateUtil.getPlataformaDelegate().obtenerPlataforma(Long.valueOf(plata));
			tramitePlantilla.setPlataforma(plataforma);

			tramitePlantillaDelegate.grabarTramitePlantilla(tramitePlantilla, edicion);

			final String ok = messageSource.getMessage("tramitePlantilla.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(1L, ok);
			resultats.put("nodo", result);
			resultats.put("codigo", tramitePlantilla.getId());

		} catch (final DelegateException dEx) {

			if (dEx.isSecurityException()) {

				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				resultats.put("nodo", result);

			} else {

				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				resultats.put("nodo", result);

				log.error(ExceptionUtils.getStackTrace(dEx));

			}

		} catch (final NumberFormatException nfe) {

			result = new IdNomDTO(-3l, error);
			resultats.put("nodo", result);

		}

		return resultats;

	}

	@RequestMapping(value = "/esborrar.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(final HttpServletRequest request) {

		final IdNomDTO resultatStatus = new IdNomDTO();

		try {
			final Long id = Long.valueOf(request.getParameter("id"));
			final TramitePlantillaDelegate tramitePlantillaDelegate = DelegateUtil.getTramitePlantillaDelegate();

			final int cuantos = tramitePlantillaDelegate.cuantosProcedimientosConTramitePlantilla(id);
			if (cuantos == 0) {
				tramitePlantillaDelegate.borrarTramitePlantilla(id);
				resultatStatus.setId(1l);
			} else {
				resultatStatus.setId(-4l);
				resultatStatus.setNom("Error: Hi ha procediments amb aquest tramite plantilla.");
				log.error("Error: Hi ha procediments amb aquest tramite plantilla.");
			}
		} catch (final DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (final NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Código de plantilla tramite no numeric: " + ExceptionUtils.getStackTrace(nfEx));
		}

		return resultatStatus;

	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(final HttpServletRequest request) {

		final Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			final String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			final TraduccionTramitePlantilla traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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

			log.error("TraduccionPlantillaBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		} catch (final Exception e) {

			log.error("TraduccionPlantillaBackController.traduir: Error  al traducir plantilla: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));

		}

		return resultats;

	}

	private TraduccionTramitePlantilla getTraduccionOrigen(final HttpServletRequest request,
			final String idiomaOrigenTraductor) {

		final TraduccionTramitePlantilla traduccioOrigen = new TraduccionTramitePlantilla();

		if (StringUtils.isNotEmpty(request.getParameter("item_nombre_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nombre_" + idiomaOrigenTraductor));
		}

		return traduccioOrigen;

	}

}
