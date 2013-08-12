package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionEspacioTerritorial;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/espaisTerritorials/")
public class TMEspaiTerritorialController extends PantallaBaseController
{
	private static Log log = LogFactory.getLog(TMEspaiTerritorialController.class);
	
	@RequestMapping(value = "/espaiTerritorialBreadcrumb.do")
	public @ResponseBody Map<String, Object> getBrearcrumb(HttpServletRequest request)
	{
		Map<String, Object> resultats = new HashMap<String, Object>();
    	try {
    		// Breadcrumb del elemento
    		List<Map<String, Object>> breadcrumbDTO = new ArrayList<Map<String, Object>>();
    		Map<String, Object> elementoDTO;
    		
    		if (request.getParameter("id") != null) {
    			Long id = new Long(request.getParameter("id"));
    			EspacioTerritorialDelegate espaiDelegate = DelegateUtil.getEspacioTerritorialDelegate();
    			EspacioTerritorial espai = espaiDelegate.obtenerEspacioTerritorial(id);
    			List<EspacioTerritorial> antecesores = espaiDelegate.listarAntecesoresEspacioTerritorial(id);
    			for (EspacioTerritorial espacio: antecesores) {
    				TraduccionEspacioTerritorial tet = (TraduccionEspacioTerritorial) espacio.getTraduccion(request.getLocale().getLanguage());
    				elementoDTO = new HashMap<String, Object>();
    				elementoDTO.put("id", espacio.getId());
    				elementoDTO.put("nom", tet == null ? "" : tet.getNombre());
    				breadcrumbDTO.add(elementoDTO);
    			}
    		}
    		resultats.put("breadcrumb", breadcrumbDTO);
    		
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException())
				log.error("Permisos insuficients: " + dEx.getMessage());
			else
				log.error("Error: " + dEx.getMessage());
		}
    	
		return resultats;
    }
	
	
	@RequestMapping(value = "/espaiTerritorial.do")
    public String pantallaEspaiTerritorial(Map<String, Object> model, HttpServletRequest request)
    {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMEspaiTerritorial.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
        	try {
        		List<IdNomDTO> perfilesDTO = new LinkedList<IdNomDTO>();
        		for (PerfilCiudadano perfil: (List<PerfilCiudadano>) perfilDelegate.listarPerfiles()) {
        			TraduccionPerfilCiudadano tpc = (TraduccionPerfilCiudadano) perfil.getTraduccion();
        			perfilesDTO.add(new IdNomDTO(perfil.getId(), tpc != null ? tpc.getNombre() : ""));
        		}
				model.put("perfils", perfilesDTO);
				model.put("escriptori", "pantalles/taulesMestres/tmEspaisTerritorials.jsp");
				
				// Listado padres
	    		List<Map<String, Object>> llistaEspaiDTO = new ArrayList<Map<String, Object>>();
	    		Map<String, Object> espaiDTO;
				
				EspacioTerritorialDelegate espaiDelegate = DelegateUtil.getEspacioTerritorialDelegate();
				List<EspacioTerritorial> espais = espaiDelegate.listarEspacioTerritorialesRaiz();
				for (EspacioTerritorial espacio: espais) {
					TraduccionEspacioTerritorial tet = (TraduccionEspacioTerritorial) espacio.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
					espaiDTO = new HashMap<String, Object>();
					espaiDTO.put("id", espacio.getId());
					espaiDTO.put("nom", tet == null ? "" : tet.getNombre());
					llistaEspaiDTO.add(espaiDTO);
				}
	    		model.put("llistaPares", llistaEspaiDTO);
	    		
        	} catch (DelegateException dEx) {
    			if (dEx.isSecurityException()) {
    				model.put("error", "permisos");
    			} else {
    				log.error("Error: " + dEx.getMessage());
    				model.put("error", "altres");
    			}
    		}
        } else {
        	model.put("error", "permisos");
        }
        
		loadIndexModel (model, request);
        return "index";
    }
	
	
	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatEspaiTerritorial(HttpServletRequest request)
	{
		List<Map<String, Object>> llistaEspaiDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> espaiDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		// Información de paginación
		String pagPag = request.getParameter("pagPag");
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0);
		if (pagRes == null) pagRes = String.valueOf(10);
		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		try {
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			EspacioTerritorialDelegate espaiDelegate = DelegateUtil.getEspacioTerritorialDelegate();
			
			resultadoBusqueda = espaiDelegate.listarEspaciosTerritoriales(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for ( Object o : resultadoBusqueda.getListaResultados() ) {
				
				Long id = (Long) ((Object[]) o)[0];
				String nom = ((Object[]) o)[1] != null ? (String) ((Object[]) o)[1] : "";
				
				espaiDTO = new HashMap<String, Object>();
				espaiDTO.put("id", id);
				espaiDTO.put("nom", nom);
				
				llistaEspaiDTO.add(espaiDTO);
			}
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException())
				log.error("Permisos insuficients: " + dEx.getMessage());
			else
				log.error("Error: " + dEx.getMessage());
		}
		
		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaEspaiDTO);

		return resultats;
	}
	
	
	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request)
	{
		Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
	        EspacioTerritorial espai = espacioDelegate.obtenerEspacioTerritorial(id);
	        
	        omplirCampsTraduibles(resultats, espai);
	        
	        resultats.put("item_id", espai.getId());
	        
	        // Mapa
            if (espai.getMapa() != null) {
            	resultats.put("item_mapa_enllas_arxiu", "espaisTerritorials/archivo.do?id=" + espai.getId() + "&tipus=1");
            	resultats.put("item_mapa", espai.getMapa().getNombre());
            } else {
            	resultats.put("item_mapa_enllas_arxiu", "");
            	resultats.put("item_mapa", "");
            }
            
            // Logo
            if (espai.getLogo() != null) {
            	resultats.put("item_logo_enllas_arxiu", "espaisTerritorials/archivo.do?id=" + espai.getId() + "&tipus=2");
                resultats.put("item_logo", espai.getLogo().getNombre());
            } else {
            	resultats.put("item_logo_enllas_arxiu", "");
                resultats.put("item_logo", "");
            }
            
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            
            Long idPadre = null;
            String nomPadre = "";
            if (espai.getPadre() != null) {
            	idPadre = espai.getPadre().getId();
            	nomPadre = espai.getPadre().getNombreEspacioTerritorial(lang);
            } else if (espai.getPadre() == null && espai.getNivel().intValue() == 0) {
            	nomPadre = messageSource.getMessage("espai.arrel", null, request.getLocale());
            }
            resultats.put("item_codi_pare", idPadre);
            resultats.put("item_pare", nomPadre);
            resultats.put("item_coordenades", espai.getCoordenadas());
            
            // Espais relacionats
            if (espai.getHijos() != null) {
            	Map<String, String> map;
            	List<Map<String, String>> llistaEspaisRelacionats = new ArrayList<Map<String, String>>();
            	TraduccionEspacioTerritorial traET;
				String nombre;
                
				for (Iterator<EspacioTerritorial> it = espai.getHijos().iterator(); it.hasNext();) {
					EspacioTerritorial espaiFill = it.next();
					
					if (espaiFill != null) {
						traET = (TraduccionEspacioTerritorial) espaiFill.getTraduccion(lang);
						nombre = "";
	    				if (traET != null) {
	    					// Retirar posible enlace incrustado en titulo
	    					nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(traET.getNombre());
	    				}
	    				map = new HashMap<String, String>(2);
	    				map.put("id", espaiFill.getId().toString());
	    				map.put("nombre", nombre);
	    				llistaEspaisRelacionats.add(map);
					}
				}
				resultats.put("espaisRelacionats", llistaEspaisRelacionats);
            } else {
                resultats.put("espaisRelacionats", null);
            }
            // Fi Matéries asociades
            
	    } catch (DelegateException dEx) {
			log.error(ExceptionUtils.getStackTrace(dEx));
			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
		}
	    
        return resultats;
	}
	
	private void omplirCampsTraduibles(Map<String, Object> resultats, EspacioTerritorial espai) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null != espai.getTraduccion(lang)) {
		    	HashMap<String, String> traduccionEspacioDTO = new HashMap<String, String>();
		    	TraduccionEspacioTerritorial tet = (TraduccionEspacioTerritorial) espai.getTraduccion(lang);
		    	
		    	traduccionEspacioDTO.put("item_nombre", tet.getNombre());
		    	
	        	resultats.put(lang, traduccionEspacioDTO);
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
		
    	EspacioTerritorial espacio = new EspacioTerritorial();
    	EspacioTerritorial espacioOld = null; 		
		IdNomDTO result = null;
        
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
        	
        	EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();

        	boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));
        	if (edicion) {
				Long idEspai = ParseUtil.parseLong(valoresForm.get("item_id"));
        		espacioOld = espacioDelegate.obtenerEspacioTerritorial(idEspai);
        		espacio.setId(idEspai);
        		espacio.setPadre(espacioOld.getPadre());
        		espacio.setCoordenadas(espacioOld.getCoordenadas());
        		espacio.setMapa(espacioOld.getMapa());
        		espacio.setLogo(espacioOld.getLogo());
        		espacio.setNivel(espacioOld.getNivel());
        		//espacio.setHijos(espacioOld.getHijos());
        	}
        	
        	// Obtener campos por idioma
        	TraduccionEspacioTerritorial traEsp; 
        	List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	
        	for (String idioma : idiomas) {
        		traEsp = espacioOld != null ? (TraduccionEspacioTerritorial)espacioOld.getTraduccion(idioma) : null;
        		if (traEsp != null) {
        			espacio.setTraduccion(idioma, traEsp);
        		} else {
        			traEsp = new TraduccionEspacioTerritorial();
    				espacio.setTraduccion(idioma, traEsp);
        		}

        		traEsp.setNombre( RolUtil.limpiaCadena(valoresForm.get("item_nom_" + idioma)) );
        		
        	}
        	
        	//Obtener los demás campos
        	Long idEspaiPare = null;
        	if (valoresForm.get("item_codi_pare") != null && !"".equals(valoresForm.get("item_codi_pare"))) {
        		idEspaiPare = ParseUtil.parseLong(valoresForm.get("item_codi_pare"));
        	}
        	espacio.setCoordenadas(valoresForm.get("item_coordenades"));
            
        	List<EspacioTerritorial> espaciosRelacionados = new ArrayList<EspacioTerritorial>();
        	// Espacios relacionados
        	if (valoresForm.size() > 0) {
				// Recorrem el formulari
        		for (String nomParametre: valoresForm.keySet()) {
	                String[] elements = nomParametre.split("_");
					if (elements[0].equals("espai") && elements[1].equals("id")){
	                    //En aquest cas, elements[2] es igual al id del fetVital
						
						Long idEspaiRel = ParseUtil.parseLong(elements[2]);
						
						// Cercam espai relacionat i insertam
						EspacioTerritorial espaiT = espacioDelegate.obtenerEspacioTerritorial(idEspaiRel);
						
						espaciosRelacionados.add(espaiT);
					}
				}
			}
        	
	        // Mapa
            fileItem = ficherosForm.get("item_mapa");
    		if (fileItem != null && fileItem.getSize() > 0) {
    			espacio.setMapa(UploadUtil.obtenerArchivo(espacio.getMapa(), fileItem));
    		} else if (valoresForm.get("item_mapa_delete") != null && !"".equals(valoresForm.get("item_mapa_delete"))){
    			espacio.setMapa(null);
    		} else if (edicion) {
    			espacio.setMapa(espacioOld.getMapa());
    		}
    		
            // Logo
    		fileItem = ficherosForm.get("item_logo");
    		if (fileItem != null && fileItem.getSize() > 0) {
    			espacio.setLogo(UploadUtil.obtenerArchivo(espacio.getLogo(), fileItem));
    		} else if (valoresForm.get("item_logo_delete") != null && !"".equals(valoresForm.get("item_logo_delete"))){
    			espacio.setLogo(null);
    		} else if (edicion) {
    			espacio.setLogo(espacioOld.getLogo());
    		}
    		
    		espacioDelegate.grabarEspacioTerritorial(espacio, idEspaiPare);
        	result = new IdNomDTO(espacio.getId(), messageSource.getMessage("espai.guardat.correcte", null, request.getLocale()) );
        	
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
    

    @RequestMapping(value = "/esborrarEspaiTerritorial.do", method = POST)
	public @ResponseBody IdNomDTO esborrarEspaiTerritorial(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			EspacioTerritorialDelegate espaiDelegate = DelegateUtil.getEspacioTerritorialDelegate();
			espaiDelegate.borrarEspacioTerritorial(id);
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
			log.error("Error: Id de espai territorial no num�ric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
}
