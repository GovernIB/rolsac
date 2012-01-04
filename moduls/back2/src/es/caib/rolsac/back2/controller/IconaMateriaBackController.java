package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IconoMateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.UploadUtil;


@Controller
@RequestMapping("/iconesMateria/")
public class IconaMateriaBackController extends ArchivoController {
	
	private static Log log = LogFactory.getLog(IconaMateriaBackController.class);

	private MessageSource messageSource = null;
	
	@Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }	
	
	
	@RequestMapping(value = "/guardarIcona.do", method = POST)
	public ResponseEntity<String> guardarIcona(HttpServletRequest request, HttpSession session)  {
		/* Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		Locale locale = request.getLocale();
		String jsonResult = null;

		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
		
		try {
			//Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
			//Iremos recopilando los parámetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
			List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
				} else {
					ficherosForm.put(item.getFieldName(), item);    				
				}
			}
			
			IconoMateriaDelegate iconaDelegate = DelegateUtil.getIconoMateriaDelegate();
			IconoMateria icona = new IconoMateria();
			IconoMateria iconaOld = null;
			Long iconaId = null;
			if (valoresForm.get("iconaId") != null && !"".equals(valoresForm.get("iconaId"))) {
				iconaId = Long.parseLong(valoresForm.get("iconaId"));
				iconaOld = iconaDelegate.obtenerIconoMateria(iconaId);
				icona.setId(iconaOld.getId());
			}
			
			// Archivo
    		FileItem fileItem = ficherosForm.get("icona_arxiu");
    		if (fileItem != null && fileItem.getSize() > 0) {
    			// nuevo fichero
    			icona.setIcono(UploadUtil.obtenerArchivo(icona.getIcono(), fileItem));
    		} else if (iconaOld != null) {
    			// mantener el fichero anterior
    			icona.setIcono(iconaOld.getIcono());
    		} else {
    			String error = messageSource.getMessage("icona.error.obligatori", null, locale);
    			jsonResult = new IdNomDTO(-4l, error).getJson();
    		}
			
    		if (jsonResult == null) {
	    		PerfilCiudadano perfil = null;
	    		if (valoresForm.get("icona_perfil") != null && !"".equals(valoresForm.get("icona_perfil"))) {
					Long perfilId = Long.parseLong(valoresForm.get("icona_perfil"));
					PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
					perfil = perfilDelegate.obtenerPerfil(perfilId);
					icona.setPerfil(perfil);
				}
	    		
				if (valoresForm.get("materiaId") != null && !"".equals(valoresForm.get("materiaId"))) {
					Long materiaId = Long.parseLong(valoresForm.get("materiaId"));
					MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
					Materia materia = materiaDelegate.obtenerMateria(materiaId);
					icona.setMateria(materia);
					iconaId = iconaDelegate.grabarIconoMateria(icona, materia.getId(), perfil.getId());
					jsonResult = new IdNomDTO(iconaId,  messageSource.getMessage("icona.guardat.correcte", null, locale)).getJson();
				} else {
					String error = messageSource.getMessage("error.altres", null, locale);
					jsonResult = new IdNomDTO(-2l, error).getJson();
					log.error("Error guardant icona: No s'ha especificat id de materia.");
				}
    		}
			
		} catch (FileUploadException fue) {
			String error = messageSource.getMessage("error.fitxer.tamany", null, locale);
			jsonResult = new IdNomDTO(-3l, error).getJson();
			log.error(error + ": " + fue.toString());
			
		} catch (UnsupportedEncodingException uee) {
			String error = messageSource.getMessage("error.altres", null, locale);
			jsonResult = new IdNomDTO(-2l, error).getJson();
			log.error(error + ": " + uee.toString());
			
		} catch (NumberFormatException nfe) {
			String error = messageSource.getMessage("error.altres", null, locale);
			jsonResult = new IdNomDTO(-2l, error).getJson();
			log.error(error + ": " + nfe.toString());
			
		} catch (DelegateException de) {
			String error = null;
			if (de.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, locale);
				jsonResult = new IdNomDTO(-1l, error).getJson();
			} else {
				error = messageSource.getMessage("error.altres", null, locale);
				jsonResult = new IdNomDTO(-2l, error).getJson();
			}
			log.error(error + ": " + de.toString());
		}

		return new ResponseEntity<String>(jsonResult, responseHeaders, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "/carregarIcona.do")
	public @ResponseBody Map<String, Object> carregarIcona(HttpServletRequest request)  {
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			Long id = new Long(request.getParameter("id"));
			IconoMateriaDelegate iconaDelegate = DelegateUtil.getIconoMateriaDelegate();
			IconoMateria icona = iconaDelegate.obtenerIconoMateria(id);
			Map<String, Object> mapIcona = new HashMap<String, Object>();
			
			// archivo
	        if (icona.getIcono() != null) {
	        	mapIcona.put("enllas_arxiu", "iconesMateria/archivo.do?id=" + icona.getId());
	        	mapIcona.put("nom_arxiu", icona.getIcono().getNombre());
	        } else {
	        	mapIcona.put("enllas_arxiu", "");
	        	mapIcona.put("nom_arxiu", "");
	        }
				
			mapIcona.put("item_id", icona.getId());
			mapIcona.put("perfil", icona.getPerfil().getId());

			resultats.put("icona", mapIcona);
			resultats.put("id", icona.getId());
			
		} catch (NumberFormatException nfe) {
			log.error("El id de la icona no es númeric: " + nfe.toString());
			resultats.put("id", -3);
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + dEx.toString());
				resultats.put("id", -1);
			} else {
				log.error("Error: " + dEx.toString());
				resultats.put("id", -2);
			}
		}
		
		return resultats;
	}
	

	@RequestMapping(value = "/archivo.do")
    public void devolverArchivoIcono(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }

	@Override
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {		
        //obtener archivo concreto con el delegate
        Long id = new Long(request.getParameter("id"));
        IconoMateriaDelegate iconaDelegate = DelegateUtil.getIconoMateriaDelegate();
        return iconaDelegate.obtenerIcono(id);
	}
	
}
