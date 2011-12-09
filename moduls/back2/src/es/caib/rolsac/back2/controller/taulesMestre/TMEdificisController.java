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
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
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
@RequestMapping("/edifici/")
public class TMEdificisController {
    
	private static Log log = LogFactory.getLog(TMEdificisController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/edifici.do")
    public String pantallaEdifici(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMEdificis.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmEdificis.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatEdifici(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaEdificiDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> edificiDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
			List<Edificio> llistaEdificis = edificiDelegate.listarEdificios();
			for (Edificio edifici: llistaEdificis) {
				TraduccionEdificio tp = (TraduccionEdificio) edifici.getTraduccion(request.getLocale().getLanguage());
				edificiDTO = new HashMap<String, Object>();
				edificiDTO.put("id", edifici.getId());
				edificiDTO.put("direccio", edifici.getDireccion());
				edificiDTO.put("descripcio", tp == null ? "" : tp.getDescripcion());
				llistaEdificiDTO.add(edificiDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaEdificiDTO.size());
		resultats.put("nodes", llistaEdificiDTO);

		return resultats;
	}
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
	        Edificio edifici = edificiDelegate.obtenerEdificio(id);	        	        
	        
	        resultats.put("item_id", edifici.getId());
	        resultats.put("item_direccio", edifici.getDireccion());
	        resultats.put("item_codi_postal", edifici.getCodigoPostal());
	        resultats.put("item_poblacio", edifici.getPoblacion());
	        
	        resultats.put("item_latitud", edifici.getLatitud());
	        resultats.put("item_longitud", edifici.getLongitud());
	        
	        resultats.put("item_telefon", edifici.getTelefono());
	        resultats.put("item_fax", edifici.getFax());
	        resultats.put("item_email", edifici.getEmail());
	        
	        if (edifici.getFotoPequenya() != null){
            	resultats.put("item_foto_petita_enllas_arxiu", "edifici/archivo.do?id=" + edifici.getId() + "&tipus=1");
                resultats.put("item_foto_petita", edifici.getFotoPequenya().getNombre());
            } else {
            	resultats.put("item_foto_petita_enllas_arxiu", "");
                resultats.put("item_foto_petita", "");
            }
            if (edifici.getFotoGrande() != null) {
            	resultats.put("item_foto_gran_enllas_arxiu", "edifici/archivo.do?id=" + edifici.getId() + "&tipus=2");
                resultats.put("item_foto_gran", edifici.getFotoGrande().getNombre());
            } else {
                resultats.put("item_foto_gran_enllas_arxiu", "");
                resultats.put("item_foto_gran", "");
            } 
            if (edifici.getPlano() != null) {
            	resultats.put("item_planol_enllas_arxiu", "edifici/archivo.do?id=" + edifici.getId() + "&tipus=3");
                resultats.put("item_planol", edifici.getPlano().getNombre());
            } else {
                resultats.put("item_planol_enllas_arxiu", "");
                resultats.put("item_planol", "");
            } 

			omplirCampsTraduibles(resultats, edifici);
	        
	        
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


	private void omplirCampsTraduibles(Map<String, Object> resultats,
			Edificio edifici) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null!=edifici.getTraduccion(lang)) {
				resultats.put(lang, (TraduccionEdificio) edifici.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionEdificio());
			}
		}
	}
    
    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardarEdifici(HttpSession session, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        IdNomDTO result = null;
        
        Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
        
        try {
        	//Aqu� nos llegar� un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los par�metros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
        	List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

    		for (FileItem item : items) {
    			if (item.isFormField()) {
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			} else {
    				ficherosForm.put(item.getFieldName(), item);    				
    			}
    		}
    		
    		//Campos obligatorios
            String direccio = valoresForm.get("item_direccio");
            
            
            if (direccio == null || "".equals(direccio)) {
                String error = messageSource.getMessage("edifici.formulari.error.falten_camps", null, request.getLocale());
                result = new IdNomDTO(-3l, error);
                return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);                
            } 
    		
            EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
            
            Edificio edifici = new Edificio();
            
			Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			if (id != null) { 
				edifici = edificiDelegate.obtenerEdificio(id);
			} else {									
				edifici = new Edificio();
			}
			
			edifici.setDireccion(direccio);
			edifici.setCodigoPostal(valoresForm.get("item_codi_postal"));
			edifici.setPoblacion(valoresForm.get("item_poblacio"));
			edifici.setLatitud(valoresForm.get("item_latitud"));
			edifici.setLongitud(valoresForm.get("item_longitud"));
			edifici.setTelefono(valoresForm.get("item_telefon"));
			edifici.setFax(valoresForm.get("item_fax"));
			edifici.setEmail(valoresForm.get("item_email"));
			
			//Foto Petita
    		FileItem fileFotoPetita = ficherosForm.get("item_foto_petita");
    		if ( fileFotoPetita.getSize() > 0 ) {
    			edifici.setFotoPequenya(UploadUtil.obtenerArchivo(edifici.getFotoPequenya(), fileFotoPetita));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_foto_petita_delete") != null && !"".equals(valoresForm.get("item_foto_petita_delete"))){
    			edifici.setFotoPequenya(null);
    		}
    		//FotoGran
    		FileItem fileFotoGran = ficherosForm.get("item_foto_gran");
    		if ( fileFotoGran.getSize() > 0 ) {
    			edifici.setFotoGrande(UploadUtil.obtenerArchivo(edifici.getFotoGrande(), fileFotoGran));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_foto_gran_delete") != null && !"".equals(valoresForm.get("item_foto_gran_delete"))){
    			edifici.setFotoGrande(null);
    		}
    		//Planol
    		FileItem filePlanol = ficherosForm.get("item_planol");
    		if ( filePlanol.getSize() > 0 ) {
    			edifici.setPlano(UploadUtil.obtenerArchivo(edifici.getPlano(), filePlanol));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_planol_delete") != null && !"".equals(valoresForm.get("item_planol_delete"))){
    			edifici.setPlano(null);
    		}
           

            // Idiomas
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			
			Map traduccions = new HashMap(langs.size());
			for (String lang: langs) {
				TraduccionEdificio tp = new TraduccionEdificio();
				tp.setDescripcion(valoresForm.get("item_descripcio_"+  lang ));		
				traduccions.put(lang, tp);
			}
			edifici.setTraduccionMap(traduccions);
			   	
            edificiDelegate.grabarEdificio(edifici);
            
            String ok = messageSource.getMessage("administracioRemota.guardat.correcte", null, request.getLocale());
            result = new IdNomDTO(edifici.getId(), ok);
            
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
			log.error(ExceptionUtils.getStackTrace(e));;
        }
        
        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/esborrarEdifici.do", method = POST)
	public @ResponseBody IdNomDTO esborrarEdifici(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
			edificiDelegate.borrarEdificio(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de pefil no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
}

//public ActionForward unidades(ActionMapping mapping, ActionForm form,
//        HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//	log.debug("Entramos en unidades");
//	EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
//
//	if (request.getParameter("edificio") != null && request.getParameter("unidad") != null){
//		Long idEdi = new Long(request.getParameter("edificio"));
//		Long idUnidad = new Long(request.getParameter("unidad"));
//
//		if ("alta".equals(request.getParameter("operacion")))
//			edificioDelegate.anyadirUnidad(idUnidad, idEdi);
//		if ("baja".equals(request.getParameter("operacion")))
//    		edificioDelegate.eliminarUnidad(idUnidad, idEdi);
//
//		request.setAttribute("idSelect", idEdi);
//		return dispatchMethod(mapping, form, request, response, "seleccionar");
//		
//	}
//	
//	return mapping.findForward("cancel");
//}
