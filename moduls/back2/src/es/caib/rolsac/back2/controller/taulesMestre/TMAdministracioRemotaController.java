package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;

@Controller
@RequestMapping("/administracioRemota/")
public class TMAdministracioRemotaController {

	private static Log log = LogFactory.getLog(TMAdministracioRemotaController.class);

	private MessageSource messageSource = null;

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	
	@RequestMapping(value = "/administracioRemota.do")
	public String pantallaAdministracioRemota(Map<String, Object> model, HttpServletRequest request) {
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
					llistaEspaiTerritorialDTO.add(new IdNomDTO(espaiTerritorial
							.getId(), espaiTerritorial
							.getNombreEspacioTerritorial(request.getLocale().getLanguage())
					));
				}

			} catch (DelegateException dEx) {
				if (dEx.isSecurityException()) {
					log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx)); 
				} else {
					log.error(ExceptionUtils.getStackTrace(dEx));
				}
			}

			model.put("llistaEspaiTerritorial", llistaEspaiTerritorialDTO);
		} else {
			model.put("error", "permisos");
		}
		return "index";
	}

	
	@RequestMapping(value = "/llistat.do")
	public @ResponseBody
	Map<String, Object> llistatAdministracioRemota(HttpServletRequest request) {

		List<Map<String, Object>> llistaAdinistracionsRemotesDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> adRemotaDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			AdministracionRemotaDelegate adRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
			List<AdministracionRemota> llistaAdRemotes = adRemotaDelegate.listarAdministracionRemota();
			for (AdministracionRemota adRemota : llistaAdRemotes) {
				adRemotaDTO = new HashMap<String, Object>();
				adRemotaDTO.put("id", adRemota.getId());
				adRemotaDTO.put("idRemoto", adRemota.getIdRemoto());
				adRemotaDTO.put("nom", adRemota.getNombre());
				llistaAdinistracionsRemotesDTO.add(adRemotaDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaAdinistracionsRemotesDTO.size());
		resultats.put("nodes", llistaAdinistracionsRemotesDTO);

		return resultats;
	}
	

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody
	Map<String, Object> recuperaDetall(HttpServletRequest request) {
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
	public ResponseEntity<String> guardarAdministracioRemota(HttpSession session, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en
		 * Firefox. Si no se fuerza el content type Spring lo calcula y
		 * curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame
		 * (oculto) algunos navegadores interpretan la respuesta como un
		 * descargable o fichero vinculado a una aplicación. De esta forma, y
		 * devolviendo un ResponseEntity, forzaremos el Content-Type de la
		 * respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		IdNomDTO result = null;

		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

		try {
			// Aquí nos llegará un multipart, de modo que no podemos obtener los
			// datos mediante request.getParameter().
			// Iremos recopilando los parámetros de tipo fichero en el Map
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
	public @ResponseBody IdNomDTO esborrarAdministracioRemota(HttpServletRequest request) {
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
			log.error("Error: Id de destinatari no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
}
