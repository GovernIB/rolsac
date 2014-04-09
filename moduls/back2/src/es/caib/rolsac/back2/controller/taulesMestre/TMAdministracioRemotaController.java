package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.integracion.ws.UnidadAdminCENoEncontradaException;
import org.ibit.rol.sac.integracion.ws.sicronizacion.CapaDeDatosException;
import org.ibit.rol.sac.integracion.ws.sicronizacion.ComunicacionException;
import org.ibit.rol.sac.integracion.ws.sicronizacion.SincronizacionTrabajadoException;
import org.ibit.rol.sac.integracion.ws.sicronizacion.SincronizadorSingleton;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/administracioRemota/")
public class TMAdministracioRemotaController extends PantallaBaseController
{
	private static Log log = LogFactory.getLog(TMAdministracioRemotaController.class);
	
	@RequestMapping(value = "/administracioRemota.do")
	public String pantalla(Map<String, Object> model, HttpServletRequest request)
	{
		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMAdministracioRemota.jsp");
		
		RolUtil rolUtil = new RolUtil(request);
		if (rolUtil.userIsAdmin()) {
			model.put("escriptori", "pantalles/taulesMestres/tmAdministracioRemota.jsp");
			
			EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
			List<IdNomDTO> llistaEspaiTerritorialDTO = new ArrayList<IdNomDTO>();
			List<EspacioTerritorial> llistaEspaiTerritorial = new ArrayList<EspacioTerritorial>();
			try {
				llistaEspaiTerritorial = espacioTerritorialDelegate.listarEspaciosTerritoriales();
				for (EspacioTerritorial espaiTerritorial : llistaEspaiTerritorial) {
					llistaEspaiTerritorialDTO.add(new IdNomDTO(
							espaiTerritorial.getId(),
							espaiTerritorial.getNombreEspacioTerritorial(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto())
					));
				}
				
			} catch (DelegateException dEx) {
				if (dEx.isSecurityException())
					log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx));
				else
					log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
			model.put("llistaEspaiTerritorial", llistaEspaiTerritorialDTO);
		} else {
			model.put("error", "permisos");
		}
		loadIndexModel (model, request);
		return "index";
	}
	
	
	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request)
	{
		List<Map<String, Object>> llistaAdiministracionsRemotesDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> adRemotaDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();				
		
		try {
			AdministracionRemotaDelegate adRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
			
			resultadoBusqueda = adRemotaDelegate.listarAdministracionRemota( Integer.parseInt(pagPag), Integer.parseInt(pagRes) );
			
			for ( Object o : resultadoBusqueda.getListaResultados() ) {
				Long id = (Long) ((Object[]) o)[0];
				String idRemoto = (String) ((Object[]) o)[1];
				String nom = (String) ((Object[]) o)[2];
				
				adRemotaDTO = new HashMap<String, Object>();
				adRemotaDTO.put("id", id);
				adRemotaDTO.put("idRemoto", idRemoto);
				adRemotaDTO.put("nom", nom);
				
				llistaAdiministracionsRemotesDTO.add(adRemotaDTO);
			}
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaAdiministracionsRemotesDTO);

		return resultats;
	}
	

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
		Map<String, Object> resultats = new HashMap<String, Object>();
		try {
			Long id = new Long(request.getParameter("id"));

			AdministracionRemotaDelegate adRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
			AdministracionRemota adRemota = adRemotaDelegate.obtenerAdministracionRemota(id);
						
			resultats.put("item_id", adRemota.getId());
			resultats.put("item_nom", adRemota.getNombre());
			resultats.put("item_endPoint", adRemota.getEndpoint());
			resultats.put("item_profunditat", adRemota.getProfundidad());
			resultats.put("item_codi_estandart", adRemota.getCodigoEstandarUA());

			if (adRemota.getLogop() != null) {
				resultats.put("item_logo_petit_enllas_arxiu", "administracioRemota/archivo.do?id=" + adRemota.getId() + "&tipus=1");
				resultats.put("item_logo_petit", adRemota.getLogop().getNombre());
			} else {
				resultats.put("item_logo_petit_enllas_arxiu", "");
				resultats.put("item_logo_petit", "");
			}
			if (adRemota.getLogog() != null) {
				resultats.put("item_logo_gran_enllas_arxiu", "administracioRemota/archivo.do?id=" + adRemota.getId() + "&tipus=2");
				resultats.put("item_logo_gran", adRemota.getLogog().getNombre());
			} else {
				resultats.put("item_logo_gran_enllas_arxiu", "");
				resultats.put("item_logo_gran", "");
			}

			if (adRemota.getEspacioTerrit() != null) {
				resultats.put("item_espai_territorial", adRemota.getEspacioTerrit().getId());
			} else {
				resultats.put("item_espai_territorial", null);
			}

			resultats.put("item_idRemot", adRemota.getIdRemoto());
			resultats.put("item_responsable", adRemota.getResponsable());
			resultats.put("item_versio_ws", adRemota.getVersion());
			
			resultats.put("item_sincronitzada", !adRemota.getUnidadesRemotas().isEmpty());

		} catch (DelegateException dEx) {
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
	public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en
		 * Firefox. Si no se fuerza el content type Spring lo calcula y
		 * curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame
		 * (oculto) algunos navegadores interpretan la respuesta como un
		 * descargable o fichero vinculado a una aplicaci�n. De esta forma, y
		 * devolviendo un ResponseEntity, forzaremos el Content-Type de la
		 * respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result = null;

		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

		try {
			// Aqu� nos llegar� un multipart, de modo que no podemos obtener los
			// datos mediante request.getParameter().
			// Iremos recopilando los par�metros de tipo fichero en el Map
			// ficherosForm y el resto en valoresForm.
			List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) {
					valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
				} else {
					ficherosForm.put(item.getFieldName(), item);
				}
			}

			// Campos obligatorios
			String nom = valoresForm.get("item_nom");
			String idRemot = valoresForm.get("item_idRemot");
			String responsable = valoresForm.get("item_responsable");

			if (nom == null || "".equals(nom) || idRemot == null || "".equals(idRemot) || responsable == null || "".equals(responsable)) {
				String error = messageSource.getMessage("administracioRemota.formulari.error.falten_camps",null, request.getLocale());
				result = new IdNomDTO(-3l, error);
				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
			}

			AdministracionRemotaDelegate adRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
			AdministracionRemota adRemota;

			Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			if (id != null) {
				adRemota = adRemotaDelegate.obtenerAdministracionRemota(id);
			} else {
				adRemota = new AdministracionRemota();
			}

			adRemota.setNombre(nom);
			adRemota.setEndpoint(valoresForm.get("item_endPoint"));
			adRemota.setProfundidad(ParseUtil.parseInt(valoresForm.get("item_profunditat")));
			adRemota.setCodigoEstandarUA(valoresForm.get("item_codi_estandart"));

			// LogoPetit
			FileItem fileLogoPetit = ficherosForm.get("item_logo_petit");
			if (fileLogoPetit.getSize() > 0) {
				adRemota.setLogop(UploadUtil.obtenerArchivo(adRemota.getLogop(), fileLogoPetit));
			} else
			// borrar fichero si se solicita
			if (valoresForm.get("item_logo_petit_delete") != null && !"".equals(valoresForm.get("item_logo_petit_delete"))) {
				adRemota.setLogop(null);
			}
			// LogoGran
			FileItem fileLogoGran = ficherosForm.get("item_logo_gran");
			if (fileLogoGran.getSize() > 0) {
				adRemota.setLogog(UploadUtil.obtenerArchivo(adRemota.getLogog(), fileLogoGran));
			} else
			// borrar fichero si se solicita
			if (valoresForm.get("item_logo_gran_delete") != null && !"".equals(valoresForm.get("item_logo_gran_delete"))) {
				adRemota.setLogog(null);
			}

			Long espaiTerritorialId = ParseUtil.parseLong(valoresForm .get("item_espai_territorial"));
			if (espaiTerritorialId != null) {
				EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
				EspacioTerritorial espacioTerritorial = espacioTerritorialDelegate.obtenerEspacioTerritorial(espaiTerritorialId);
				adRemota.setEspacioTerrit(espacioTerritorial);
			} else {
				adRemota.setEspacioTerrit(null);
			}

			adRemota.setIdRemoto(valoresForm.get("item_idRemot"));
			adRemota.setResponsable(valoresForm.get("item_responsable"));
			adRemota.setVersion(ParseUtil.parseLong(valoresForm.get("item_versio_ws")));

			adRemotaDelegate.grabarAdministracionRemota(adRemota);

			String ok = messageSource.getMessage("administracioRemota.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(adRemota.getId(), ok);

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

	
	@RequestMapping(value = "/esborrarAdministracioRemota.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			AdministracionRemotaDelegate adRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();

			if (adRemotaDelegate.isEmpty(id)) {
				adRemotaDelegate.borrarAdministracionRemota(id);
				resultatStatus.setId(1l);
			} else {
				resultatStatus.setId(-1l);
				String error = messageSource.getMessage("administracioRemota.guardat.error", null, request.getLocale());
				resultatStatus.setNom(error);
			}

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de destinatari no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}

	// Mètodes de sincronització
	@RequestMapping(value="/sincronitzacio.do", method = POST)
	public @ResponseBody IdNomDTO sincronitzaAdministracioRemota(HttpServletRequest request, HttpServletResponse response) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		AdministracionRemotaDelegate administracionRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
		
		Long id = new Long(request.getParameter("id"));
		String operacio = request.getParameter("op");

		try {
			
			resultatStatus.setId(1l);

			if (! SincronizadorSingleton.Estado.Parado.equals(SincronizadorSingleton.running() ) ) {
				
				resultatStatus.setId(-1l);
				String error = messageSource.getMessage("administracioRemota.sincronitzacio.error.no_disponible", null,  request.getLocale());
				resultatStatus.setNom(error);
				
			} else if ( id != null) {
				AdministracionRemota administracionRemota = administracionRemotaDelegate.obtenerAdministracionRemota(id);
				
				SincronizadorSingleton sing = SincronizadorSingleton.getInstance();
				
				if ("a".equals(operacio))
					sing.alta(administracionRemota);
				else if ("b".equals(operacio))
					sing.baja(administracionRemota);
				
				if ( administracionRemotaDelegate.isEmpty(id)) {
					resultatStatus.setId(-1l);
					String error = messageSource.getMessage("administracioRemota.sincronitzacio.error.ua_buida", null,  request.getLocale());
					resultatStatus.setNom(error);
				}
			} 
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (UnidadAdminCENoEncontradaException e) {
			resultatStatus.setId(-3l);
			resultatStatus.setNom("Error de sincronitzaci�: Unitat no trobada.");
			log.error("Error de sincronitzaci� (UA no trobada): " + ExceptionUtils.getStackTrace(e));
		} catch (SincronizacionTrabajadoException e) {
			resultatStatus.setId(-4l);
			resultatStatus.setNom("Error de sincronitzaci�: Sincronitzador ocupat.");
			log.error("Error de sincronitzaci� (Sincronitzador ocupat): " + ExceptionUtils.getStackTrace(e));
		} catch (ComunicacionException e) {
			resultatStatus.setId(-5l);
			resultatStatus.setNom("Error de sincronitzaci�: Ha ocorregut un error amb la comunicaci�.");
			log.error("Error de comunicaci�: " + ExceptionUtils.getStackTrace(e));
		} catch (CapaDeDatosException e) {
			resultatStatus.setId(-6l);
			resultatStatus.setNom("Error de sincronitzaci�: Ha ocorregut un error amb les dades.");
			log.error("Error en la capa de dades: " + ExceptionUtils.getStackTrace(e));
		}
		
		return resultatStatus;
		
	} 
}
