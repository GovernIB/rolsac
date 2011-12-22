package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
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
@RequestMapping("/materies/")
public class TMMateriesController {
    
	private static Log log = LogFactory.getLog(TMMateriesController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/materia.do")
    public String pantallaMateria(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMMateries.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmMateries.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatMateria(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaMateriaDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> materiaDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			List<Materia> materiesFamilies = materiaDelegate.listarMaterias();
			for (Materia materia: materiesFamilies) {
				TraduccionMateria tm = (TraduccionMateria) materia.getTraduccion(request.getLocale().getLanguage());
				materiaDTO = new HashMap<String, Object>();
				materiaDTO.put("id", materia.getId());
				materiaDTO.put("nom", tm == null ? "" : tm.getNombre());
				materiaDTO.put("codi_estandar", materia.getCodigoEstandar());
				llistaMateriaDTO.add(materiaDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaMateriaDTO.size());
		resultats.put("nodes", llistaMateriaDTO);

		return resultats;
	}
    
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
	        Materia materia = materiaDelegate.obtenerMateria(id);	        	        
	        
	        omplirCampsTraduibles(resultats, materia);
	        
	        resultats.put("item_id", materia.getId());
	        resultats.put("codi_hita", materia.getCodiHita());
	        resultats.put("codi_estandar", materia.getCodigoEstandar());
	        	        
	        // Foto
            if (materia.getFoto() != null) {
            	resultats.put("foto_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&tipus=1");
            	resultats.put("foto", materia.getFoto().getNombre());
            } else {
            	resultats.put("foto_enllas_arxiu", "");
            	resultats.put("foto", "");
            }
            
            // Icona
            if (materia.getIcono() != null){
            	resultats.put("icona_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&tipus=2");
                resultats.put("icona", materia.getIcono().getNombre());
            } else {
            	resultats.put("icona_enllas_arxiu", "");
                resultats.put("icona", "");
            }
            
            // Icona gran
            if (materia.getIconoGrande() != null){
            	resultats.put("icona_gran_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&tipus=3");
                resultats.put("icona_gran", materia.getIcono().getNombre());
            } else {
            	resultats.put("icona_enllas_arxiu", "");
                resultats.put("icona", "");
            }  
            
            resultats.put("uas_materia", materia.getUnidadesmaterias());
            resultats.put("ua_principal", getUAPrincipal(materia.getUnidadesmaterias()));
            resultats.put("destacada", materia.isDestacada());
            
            // TODO: Icones materies
            
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
    
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, Materia materia) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null != materia.getTraduccion(lang)) {
		    	HashMap<String, String> traduccionMateriaDTO = new HashMap<String, String>();
		    	TraduccionMateria tm = (TraduccionMateria) materia.getTraduccion(lang);
		    	
		    	traduccionMateriaDTO.put("nombre", tm.getNombre());
		    	traduccionMateriaDTO.put("descripcion", tm.getDescripcion());
		    	traduccionMateriaDTO.put("palabras_clave", tm.getPalabrasclave());
		    	
		    	if (tm.getDistribComp() != null) {
		    		traduccionMateriaDTO.put("distribucion_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&lang=" + lang + "&tipus=4");
		    		traduccionMateriaDTO.put("distribucion", tm.getDistribComp().getNombre());
		    	} else {
		    		traduccionMateriaDTO.put("distribucion_enllas_arxiu", "");
		    		traduccionMateriaDTO.put("distribucion", "");
		    	}
		    	
		    	if (tm.getNormativa() != null) {
		    		traduccionMateriaDTO.put("normativa_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&lang=" + lang + "&tipus=5");
		    		traduccionMateriaDTO.put("normativa", tm.getNormativa().getNombre());
		    	} else {
		    		traduccionMateriaDTO.put("normativa_enllas_arxiu", "");
		    		traduccionMateriaDTO.put("normativa", "");
		    	}
		    	
		    	if (tm.getContenido() != null) {
					traduccionMateriaDTO.put("contenido_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&lang=" + lang + "&tipus=6");
		    		traduccionMateriaDTO.put("contenido", tm.getContenido().getNombre());
		    	} else {
		    		traduccionMateriaDTO.put("contenido_enllas_arxiu", "");
		    		traduccionMateriaDTO.put("contenido", "");
		    	}
		    	
	        	resultats.put(lang, traduccionMateriaDTO);
			} else {
				resultats.put(lang, null);
			}
		}
	}
    
    // Dado un set devolvemos el id de la principal
    private Long getUAPrincipal(Set uas) {
    	Iterator it=uas.iterator();
    	
    	while (it.hasNext()) {
    		UnidadMateria unimat= (UnidadMateria)it.next();
    		if (unimat.getUnidadPrincipal()!=null && unimat.getUnidadPrincipal().equals("S"))
    			return unimat.getId(); 
    	}
    	return null;
    }
    
    // Dado un set lo actualizamos con la principal seleccionada
    private Set setUAPrincipal(Set uas, Long id) {
    	Iterator it=uas.iterator();

    	if (id==null) return uas;
    	
    	while (it.hasNext()) {
    		UnidadMateria unimat= (UnidadMateria)it.next();
    		if (unimat.getId().longValue()==id.longValue()) {
    			unimat.setUnidadPrincipal("S");
    		}
    		else {
    			unimat.setUnidadPrincipal("N");
    		}
    	}
    	return uas;
    }
    
    
    @RequestMapping(value = "/guardar.do", method = POST)
	public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {	
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
    	Materia materia = null;
    	Materia materiaOld = null; 		
		IdNomDTO result = null;
        
		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
		
        try {
        	
    		//Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los parámetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
    		
        	FileItem fileItem;
    		List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

    		for (FileItem item : items) {
    			if (item.isFormField()) {
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			} else {
    				ficherosForm.put(item.getFieldName(), item);    				
    			}
    		}
        	
        	MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

        	boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));
        	if (edicion) {      		        		
				Long idMateria = ParseUtil.parseLong(valoresForm.get("item_id"));
        		materiaOld = materiaDelegate.obtenerMateria(idMateria);
        		
        		//Comprobar permisos para modificar normativa
//            	if (!materiaDelegate.autorizaModificarNormativa(materiaOld.getId())) {
//    				IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
//    				return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
//            	}
            	
        	    materia.setId(idMateria);
        	    materia.setProcedimientosLocales(materiaOld.getProcedimientosLocales());
        		materia.setUnidadesmaterias(materiaOld.getUnidadesmaterias());
        		materia.setFichas(materiaOld.getFichas());
        		materia.setIconos(materiaOld.getIconos());
        		materia.setMateriasAgrupacionM(materiaOld.getMateriasAgrupacionM());
        	}
        	
        	// Obtener campos por idioma
        	TraduccionMateria traMat; 
        	List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

        	for (String idioma : idiomas) {
        		traMat = materiaOld != null ? (TraduccionMateria)materiaOld.getTraduccion(idioma) : null;
        		if (traMat != null) {
            		materia.setTraduccion(idioma, traMat);
        		} else {
    				traMat = new TraduccionMateria();
        			materia.setTraduccion(idioma, traMat);
        		}

        		traMat.setDescripcion(valoresForm.get("item_descripcio_" + idioma));
        		traMat.setNombre(valoresForm.get("item_nom_" + idioma));
        		traMat.setPalabrasclave(valoresForm.get("item_paraula_clau_" + idioma));
        		
        		// Archivos
        		fileItem = ficherosForm.get("item_distribucio_" + idioma);
        		if (fileItem.getSize() > 0) {
        			traMat.setDistribComp(UploadUtil.obtenerArchivo(traMat.getDistribComp(), fileItem));
        		} else if (valoresForm.get("item_distribucio_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_distribucio_" + idioma + "_delete"))){
        			traMat.setDistribComp(null);
        		}
        		
        		fileItem = ficherosForm.get("item_normativa_" + idioma);
        		if (fileItem.getSize() > 0) {
        			traMat.setNormativa(UploadUtil.obtenerArchivo(traMat.getNormativa(), fileItem));
        		} else if (valoresForm.get("item_normativa_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_normativa_" + idioma + "_delete"))){
        			traMat.setNormativa(null);
        		}
		    	
        		fileItem = ficherosForm.get("item_contingut_" + idioma);
        		if (fileItem.getSize() > 0) {
        			traMat.setContenido(UploadUtil.obtenerArchivo(traMat.getContenido(), fileItem));
        		} else if (valoresForm.get("item_contingut_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_contingut_" + idioma + "_delete"))){
        			traMat.setContenido(null);
        		}
        	}

        	//Obtener los demás campos
        	materia.setCodiHita(valoresForm.get("item_codi_hita"));
        	materia.setCodigoEstandar(valoresForm.get("item_codi_estandar"));
        	if (edicion){
                // ACTUALIZAMOS LA UA SELECCIONADA COMO UA PRINCIPAL
                Long uaprincipal = ParseUtil.parseLong(valoresForm.get("item_ua_principal"));
                materia.setUnidadesmaterias(setUAPrincipal(materiaOld.getUnidadesmaterias(), uaprincipal));        	
            }
            materia.setDestacada(valoresForm.get("item_destacada") != null && !"".equals(valoresForm.get("item_destacada")));
            
	        // Foto
            fileItem = ficherosForm.get("item_foto");
    		if (fileItem.getSize() > 0) {
    			materia.setFoto(UploadUtil.obtenerArchivo(materia.getFoto(), fileItem));
    		} else if (valoresForm.get("item_foto_delete") != null && !"".equals(valoresForm.get("item_foto_delete"))){
    			materia.setFoto(null);
    		}
    		
            // Icona
    		fileItem = ficherosForm.get("item_icona");
    		if (fileItem.getSize() > 0) {
    			materia.setIcono(UploadUtil.obtenerArchivo(materia.getIcono(), fileItem));
    		} else if (valoresForm.get("item_icona_delete") != null && !"".equals(valoresForm.get("item_icona_delete"))){
    			materia.setIcono(null);
    		}
    		
            // Icona gran
    		fileItem = ficherosForm.get("item_icona_gran");
    		if (fileItem.getSize() > 0) {
    			materia.setIconoGrande(UploadUtil.obtenerArchivo(materia.getIconoGrande(), fileItem));
    		} else if (valoresForm.get("item_icona_gran_delete") != null && !"".equals(valoresForm.get("item_icona_gran_delete"))){
    			materia.setIconoGrande(null);
    		}

        	materiaDelegate.grabarMateria(materia);
        	result = new IdNomDTO(materia.getId(), messageSource.getMessage("materia.guardat.correcte", null, request.getLocale()) );
        	
        } catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}        	        	
		} catch (FileUploadException e) {
			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));
		} catch (UnsupportedEncodingException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
		}
		
		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
	}
    

    @RequestMapping(value = "/esborrarMateria.do", method = POST)
	public @ResponseBody IdNomDTO esborrarMateria(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			materiaDelegate.borrarMateria(id);
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
			log.error("Error: Id de materia no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
}
