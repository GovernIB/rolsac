package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Document;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionModelsComuns;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.ModelsComunsDelegate;
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
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/modelsComuns/")
public class TMModelsComunsController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMModelsComunsController.class);
	
    @RequestMapping(value = "/modelsComuns.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMModelsComuns.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
				model.put("escriptori", "pantalles/taulesMestres/tmModelsComuns.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
  	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {
  	
  		List<Map<String, Object>> llistaModelsComunsDTO = new ArrayList<Map<String, Object>>();
  		Map<String, Object> modelsComunsDTO;
  		Map<String, Object> resultats = new HashMap<String, Object>();

  		//Información de paginación
  		String pagPag = request.getParameter("pagPag");		
  		String pagRes = request.getParameter("pagRes");
  		
  		if (pagPag == null) pagPag = String.valueOf(0); 
  		if (pagRes == null) pagRes = String.valueOf(10);
         		
  		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
  		
  		try {
  			ModelsComunsDelegate modelsComunsDelegate = DelegateUtil.geModelsComunsDelegate();
  			String idiomaPerDefecte = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
  			resultadoBusqueda = modelsComunsDelegate.llistarModelsComuns(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPerDefecte);
  			
			for (Object o : resultadoBusqueda.getListaResultados() ) {
							
				Long id =  (Long) ((Object[]) o)[0];
				String titulo = (String) ((Object[]) o)[1];
				String descripcion = (String) ((Object[]) o)[2];
				Long ordre = (Long) ((Object[]) o)[3];

				modelsComunsDTO = new HashMap<String, Object>();
				modelsComunsDTO.put("id", id);
				modelsComunsDTO.put("titol", titulo ); 
				modelsComunsDTO.put("descripcio", descripcion );
				modelsComunsDTO.put("ordre", ordre );
				
				llistaModelsComunsDTO.add(modelsComunsDTO);				
			}
  			  					
  		}catch (DelegateException dEx) {
  		  	log.error("Error: " + dEx.getMessage());
  		}

  		resultats.put("total", resultadoBusqueda.getTotalResultados());
  		resultats.put("nodes", llistaModelsComunsDTO);

  		return resultats;
  	}
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        ModelsComunsDelegate modelsComunsDelegate = DelegateUtil.geModelsComunsDelegate();
	        DocumentTramit modelComu = modelsComunsDelegate.obtenirModelComu(id);  	        
	        
	        omplirCampsTraduibles(resultats, modelComu);
	        
	        resultats.put("item_id", modelComu.getId());
            
	    } catch (DelegateException dEx) {
			log.error(ExceptionUtils.getStackTrace(dEx));
			resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
		}
        return resultats;
	}
    
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, DocumentTramit modelComu) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null != modelComu.getTraduccion(lang)) {
		    	HashMap<String, String> traduccioModelDTO = new HashMap<String, String>();
		    	TraduccionDocumento tm = (TraduccionDocumento) modelComu.getTraduccion(lang);
		    	
		    	traduccioModelDTO.put("item_titol", tm.getTitulo());
		    	traduccioModelDTO.put("item_descripcio", tm.getDescripcion());
		    	
		    	if (tm.getArchivo() != null) {
		    		traduccioModelDTO.put("item_model_enllas_arxiu", "documentsTramit/archivo.do?id=" + modelComu.getId() + "&lang=" + lang);
		    		traduccioModelDTO.put("item_model", tm.getArchivo().getNombre());
		    	} else {
		    		traduccioModelDTO.put("item_model_enllas_arxiu", "");
		    		traduccioModelDTO.put("item_model", "");
		    	}
		    	
	        	resultats.put(lang, traduccioModelDTO);
			} else {
				resultats.put(lang, new HashMap<String, String>());
			}
		}
	}
    
    
    @RequestMapping(value = "/guardar.do", method = POST)
	public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {	
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		DocumentTramit modelComu = new DocumentTramit();
		DocumentTramit modelComuOld = null;
		
		String jsonResult = null;
		Locale locale = request.getLocale();
        
		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
		
        try {
        	
    		//Aqu� nos llegar� un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los par�metros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
    		
        	FileItem fileItem;
    		List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

    		for (FileItem item : items) {
    			if (item.isFormField()) {
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			} else {
    				ficherosForm.put(item.getFieldName(), item);    				
    			}
    		}
    		ModelsComunsDelegate modelsComunsDelegate = DelegateUtil.geModelsComunsDelegate();
    		 
        	boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));
        	if (edicion) {      		        		
				Long idModelComu = ParseUtil.parseLong(valoresForm.get("item_id"));
        		modelComuOld = modelsComunsDelegate.obtenirModelComu(idModelComu);
        		modelComu.setId(modelComuOld.getId());
        		modelComu.setArchivo(modelComuOld.getArchivo());
        		modelComu.setTramit(modelComuOld.getTramit());
        		modelComu.setOrden(modelComuOld.getOrden());
        	}
        	modelComu.setTipus(Document.MODELCOMU);
        	
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			TraduccionDocumento tm;
					
			for (String lang: langs) {
				
				tm = new TraduccionDocumento();
																	
				tm.setTitulo( RolUtil.limpiaCadena(valoresForm.get("item_titol_" + lang)) );
				tm.setDescripcion( RolUtil.limpiaCadena(valoresForm.get("item_descripcio_" + lang)) );				
				
				// Archivo
				fileItem = ficherosForm.get("item_model_" + lang);
				
	    		if (fileItem != null && fileItem.getSize() > 0) {
	    			// nuevo fichero
	    			tm.setArchivo(UploadUtil.obtenerArchivo(tm.getArchivo(), fileItem));
	    		} else if (valoresForm.get("item_model_" + lang + "_delete") != null && !"".equals(valoresForm.get("item_model_" + lang + "_delete"))) {
	    			// borrar fichero
	    			tm.setArchivo(null);
	    		} else if (modelComuOld != null) {
	    			// mantener el fichero anterior
	    			TraduccionDocumento tmOld = (TraduccionDocumento) modelComuOld.getTraduccion(lang);
	    			if (tmOld != null) {
	    				tm.setArchivo(tmOld.getArchivo());
	    			}
	    		}

				modelComu.setTraduccion(lang, tm);
			}
        	
			Long idModel = modelsComunsDelegate.gravarModelComu(modelComu);
			String textoGuardadoCorrecto = "document.guardat.correcte";
			jsonResult = new IdNomDTO(idModel,  messageSource.getMessage(textoGuardadoCorrecto, null, locale)).getJson();
			
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
    

    @RequestMapping(value = "/esborrar.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			ModelsComunsDelegate modelsComunsDelegate = DelegateUtil.geModelsComunsDelegate();
			modelsComunsDelegate.esborrarModelComu(id);
			resultatStatus.setId(1l);
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de model comu no numeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
       
       @RequestMapping(value = "/reordenarModelsComuns.do", method = POST)
       public @ResponseBody IdNomDTO reordenarModelsComuns(HttpServletRequest request) {
       	IdNomDTO resultatStatus = new IdNomDTO();
       	
   		try {
   			
   			Long id = new Long(request.getParameter("id"));
   			Long ordenNuevo = new Long(request.getParameter("orden"));
   			Long ordenAnterior = new Long(request.getParameter("ordenAnterior"));
   			
   			ModelsComunsDelegate modelsComunsDelegate = DelegateUtil.geModelsComunsDelegate();
   			modelsComunsDelegate.reordenar(id, ordenNuevo, ordenAnterior);
   			
   		} catch (DelegateException dEx) {
   			if (dEx.isSecurityException()) {
   				resultatStatus.setId(-1l);
   			} else {
   				resultatStatus.setId(-2l);
   				log.error(ExceptionUtils.getStackTrace(dEx));
   			}
   		} catch (NumberFormatException nfEx) {
   			resultatStatus.setId(-3l);
   			log.error("Error: Id del model no numèric: " + ExceptionUtils.getStackTrace(nfEx));
   		}
   		
   		return resultatStatus;
       }
       
       
    @RequestMapping(value = "/traduir.do")
   	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
   	{
   		Map<String, Object> resultats = new HashMap<String, Object>();
   		
   		try {
   			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
   			
   			TraduccionModelsComuns traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
   			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
   			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
   			traduccions = traductor.translate(traduccioOrigen, idiomaOrigenTraductor);
   			
   			resultats.put("traduccions", traduccions);
   	        
   	    } catch (DelegateException dEx) {
   			logException(log, dEx);
   			if (dEx.isSecurityException()) {
   				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
   			} else {
   				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
   			}
   		} catch (NullPointerException npe) {
   			log.error("EspaiTerritorialBackController.traduir: El traductor no se encuentra en en contexto.");
   			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
   		} catch (Exception e) {
   			log.error("EspaiTerritorialBackController.traduir: Error en al traducir Espai Territorial: " + e);
   			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
   		}
   		
   		return resultats;
   	}
   	
   	
    private TraduccionModelsComuns getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
   	{
    	TraduccionModelsComuns traduccioOrigen = new TraduccionModelsComuns();
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_titol_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setTitulo(request.getParameter("item_titol_" + idiomaOrigenTraductor));
   		}
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
   		}
   		
   		return traduccioOrigen;
   	}
       
}
