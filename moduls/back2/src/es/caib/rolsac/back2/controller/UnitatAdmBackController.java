package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SeccionDTO;
import org.ibit.rol.sac.model.dto.SeccionFichaDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadMateriaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.DateUtils;

@Controller
@RequestMapping("/unitatadm/")
public class UnitatAdmBackController {

	private static Log log = LogFactory.getLog(UnitatAdmBackController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
	private static class TreeOrdenSeccionComparator implements Comparator {
		public int compare(Object element1, Object element2) {
			String lower1 =	 element1.toString();
			String lower2 =	 element2.toString();
			
			lower1 = lower1.substring(0, lower1.indexOf("#"));
			lower2 = lower2.substring(0, lower2.indexOf("#"));
			
			return new Long(lower1).compareTo(new Long(lower2));
		}
	}       
    
	@RequestMapping(value = "/unitatadm.do", method = GET)
	public String llistatUniAdm(Map<String, Object> model, HttpServletRequest request, HttpSession session) {	    
		
	    MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
	    
	    TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
	    EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
	    
	    List<Materia> llistaMateries = new ArrayList<Materia>();
	    List<Tratamiento> llistaTractaments = new ArrayList<Tratamiento>();
	    List<EspacioTerritorial> llistaEspaiTerritorial = new ArrayList<EspacioTerritorial>();
	    List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
	    List<IdNomDTO> llistaTractamentsDTO = new ArrayList<IdNomDTO>();
	    List<IdNomDTO> llistaEspaiTerritorialDTO = new ArrayList<IdNomDTO>();
	    	    
	    try {                                
            llistaMateries = materiaDelegate.listarMaterias();	    	            
            
            for(Materia materia : llistaMateries){                
                llistaMateriesDTO.add(new IdNomDTO(  materia.getId(), 
                                                                 materia.getNombreMateria(request.getLocale().getLanguage())
                                                                       ));                
               }
            
            llistaTractaments = tratamientoDelegate.listarTratamientos();
            
            for(Tratamiento tractament : llistaTractaments){                
                llistaTractamentsDTO.add(new IdNomDTO(  tractament.getId(), 
                                                                   tractament.getNombreTratamiento(request.getLocale().getLanguage())
                                                                       ));                
               }                       
            
            llistaEspaiTerritorial = espacioTerritorialDelegate.listarEspaciosTerritoriales();
            
            for(EspacioTerritorial espaiTerritorial : llistaEspaiTerritorial){                
                llistaEspaiTerritorialDTO.add(new IdNomDTO(  espaiTerritorial.getId(), 
                                                                   espaiTerritorial.getNombreEspacioTerritorial(request.getLocale().getLanguage())
                                                                       ));                
               }                       
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
            	log.error("Error de permiso: " + ExceptionUtils.getStackTrace(dEx));//TODO:mensajes de error
            } else {
                //model.put("error", "altres");
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }
	    
		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 2);
		model.put("titol_escriptori", messageSource.getMessage("submenu.unitatAdm", null, request.getLocale()));
		model.put("escriptori", "pantalles/unitatadm.jsp");
        if (session.getAttribute("unidadAdministrativa")!=null){
            model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(request.getLocale().getLanguage()));            
        }               
        
        model.put("llistaMateries", llistaMateriesDTO);        
        model.put("llistaTractaments", llistaTractamentsDTO);
        model.put("llistaEspaiTerritorial", llistaEspaiTerritorialDTO);
        
		return "index";
	}
	
	@RequestMapping(value = "/pagDetall.do", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
        
	    Map<String,Object> resultats = new HashMap<String,Object>();	    
	    List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
	    List<IdNomDTO> llistaEdificisDTO = new ArrayList<IdNomDTO>();
	    
	    UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();	    	    	    	    

	    if (request.getParameter("id") == null || "".equals(request.getParameter("id"))) {
	    	try {
		    	if (unitatDelegate.autorizarCrearUA()) {
		    		resultats.put("id", 0); // No hay id y tiene permisos para crear una UA
		    	} else {
		    		resultats.put("error", messageSource.getMessage("error.permisos.crearUA", null, request.getLocale()));
		    		resultats.put("id", -1);
		    	}
	    	} catch (DelegateException dEx) {
	    		if (dEx.isSecurityException()) {
	    			resultats.put("error", messageSource.getMessage("error.permisos.crearUA", null, request.getLocale()));
	    			resultats.put("id", -1);
	    		} else {
	    			resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
	            	resultats.put("id", -2);
	            	log.error(ExceptionUtils.getStackTrace(dEx));
		    	}
	    	} 
	    	return resultats;
        }
         
	    Long idUA = new Long(request.getParameter("id"));            
	    try {
	        UnidadAdministrativa uni = unitatDelegate.consultarUnidadAdministrativa(idUA);
	        
	        resultats.put("id", idUA);
	        
	        //Idiomas
	        
	        if (uni.getTraduccion("ca") != null) {
	            resultats.put("ca",(TraduccionUA)uni.getTraduccion("ca"));  
	        } else {
	            resultats.put("ca",new TraduccionUA());  
	        }
	        
	        if (uni.getTraduccion("es") != null) {
                resultats.put("es",(TraduccionUA)uni.getTraduccion("es"));  
            } else {
                resultats.put("es",new TraduccionUA());  
            }
	        
           if (uni.getTraduccion("en") != null) {
                resultats.put("en",(TraduccionUA)uni.getTraduccion("en"));  
            } else {
                resultats.put("en",new TraduccionUA());  
            }
           
           if (uni.getTraduccion("de") != null) {
                resultats.put("de",(TraduccionUA)uni.getTraduccion("de"));  
            } else {
                resultats.put("de",new TraduccionUA());  
            }
           
            if (uni.getTraduccion("fr") != null) {
                resultats.put("fr",(TraduccionUA)uni.getTraduccion("fr"));  
            } else {
                resultats.put("fr",new TraduccionUA());  
            }
            
            //Configuración/gestion
            
            //resultats.put("item_clau_hita", uni.getClaveHita());
            resultats.put("item_codi_estandar", uni.getCodigoEstandar());
            resultats.put("item_clave_primaria", idUA);
            resultats.put("item_domini", uni.getDominio());
            resultats.put("item_validacio", uni.getValidacion());
            resultats.put("item_telefon", uni.getTelefono());
            resultats.put("item_fax", uni.getFax());
            resultats.put("item_email", uni.getEmail());
            
            if (uni.getEspacioTerrit() != null) {
                //resultats.put("item_espai_territorial", uni.getEspacioTerrit().getNombreEspacioTerritorial(request.getLocale().getLanguage()));
                resultats.put("item_espai_territorial", uni.getEspacioTerrit().getId());
            } else {
                resultats.put("item_espai_territorial",null);
            }
            
            if (uni.getPadre() != null) {
                resultats.put("pareId", uni.getPadre().getId());
                resultats.put("pareNom", uni.getPadre().getNombreUnidadAdministrativa(request.getLocale().getLanguage()));                                       
            } else {
                resultats.put("idPadre", null);
                resultats.put("pareNom", null);
            }
            
            //Responsable
            
            resultats.put("item_responsable", uni.getResponsable());
            resultats.put("item_responsable_sexe", uni.getSexoResponsable());
            
            if (uni.getFotop() != null){

            	resultats.put("item_responsable_foto_petita_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=1");
                resultats.put("item_responsable_foto_petita", uni.getFotop().getNombre());
            } else {
            	resultats.put("item_responsable_foto_petita_enllas_arxiu", "");
                resultats.put("item_responsable_foto_petita", "");
            }
            if (uni.getFotog() != null) {
            	resultats.put("item_responsable_foto_gran_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=2");
                resultats.put("item_responsable_foto_gran", uni.getFotog().getNombre());
            } else {
                resultats.put("item_responsable_foto_gran_enllas_arxiu", "");
                resultats.put("item_responsable_foto_gran", "");
            }           

            if (uni.getTratamiento() != null) {
                //resultats.put("item_tractament", ((TraduccionTratamiento)uni.getTratamiento().getTraduccion("ca")).getTipo());//TODO:idioma por defecto
                resultats.put("item_tractament", uni.getTratamiento().getId());
            }
            
            //Logotipo horizontal            
            if (uni.getLogoh() != null) {
            	resultats.put("item_logo_horizontal_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=3");
            	resultats.put("item_logo_horizontal", uni.getLogoh().getNombre());
            } else {
            	resultats.put("item_logo_horizontal_enllas_arxiu", "");
            	resultats.put("item_log_horizontal", "");
            }
            
            //Logotipo vertical
            if (uni.getLogov() != null) {
            	resultats.put("item_logo_vertical_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=4");
            	resultats.put("item_logo_vertical", uni.getLogov().getNombre());
            } else {
            	resultats.put("item_log_vertical_enllas_arxiu", "");
            	resultats.put("item_log_vertical", "");
            }
            
            //Logo saludo horizontal
            if (uni.getLogos() != null) {
            	resultats.put("item_logo_salutacio_horizontal_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=5");            	
            	resultats.put("item_logo_salutacio_horizontal", uni.getLogos().getNombre());
            } else {
            	resultats.put("item_logo_salutacio_horizontal_enllas_arxiu", "");
            	resultats.put("item_logo_salutacio_horizontal", "");
            }
            
            //Logo saludo vertical
            if (uni.getLogot() != null) {
            	resultats.put("item_logo_salutacio_vertical_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipus=6");
            	resultats.put("item_logo_salutacio_vertical", uni.getLogot().getNombre());
            } else {
            	resultats.put("item_logo_salutacio_vertical_enllas_arxiu", "");
            	resultats.put("item_logo_salutacio_vertical", "");
            }
            
            //Fichas de la portada web            
            resultats.put("item_nivell_1", uni.getNumfoto1());
            resultats.put("item_nivell_2", uni.getNumfoto2());
            resultats.put("item_nivell_3", uni.getNumfoto3());
            resultats.put("item_nivell_4", uni.getNumfoto4());
	        
            //Secciones-Fichas           
            TreeMap arbolSecciones = ordenarArbolSecciones( (TreeMap) uni.getMapSeccionFichasUA() );
            
            List<SeccionFichaDTO> listaSecciones = new ArrayList<SeccionFichaDTO>();                                   
            
        	//Obtenemos el id y el nombre de la sección
        	Set secciones = arbolSecciones.keySet();
        	
        	for (Iterator iterator = secciones.iterator(); iterator.hasNext();) {
        		SeccionFichaDTO seccionFichaDTO = new SeccionFichaDTO();
        		
        		String claveSeccion = (String) iterator.next();
				String datosSeccion[] = claveSeccion.split("#");
				
				seccionFichaDTO.setId( new Long(datosSeccion[1]) );
				seccionFichaDTO.setNom( datosSeccion[2]);
				
				List<FichaUA> listaFichasUA = (ArrayList<FichaUA>) arbolSecciones.get(claveSeccion);
				List<FichaDTO> listaFichasDTO = new ArrayList<FichaDTO>();
				
				if (listaFichasUA != null) {
					
					for ( Iterator iterator2 = listaFichasUA.iterator(); iterator2.hasNext(); ) {
						
						FichaUA fichaUA = (FichaUA) iterator2.next();
						
						if (fichaUA.getFicha() != null) {
						
    						FichaDTO fichaDTO = new FichaDTO();
    						
    						fichaDTO.setId( fichaUA.getFicha().getId() );
    						fichaDTO.setTitulo( ( ((TraduccionFicha) fichaUA.getFicha().getTraduccion( request.getLocale().getLanguage())).getTitulo()).replaceAll("\\<.*?>", "") );
    						
    						listaFichasDTO.add( fichaDTO );
						
						}
						
					}
					
					seccionFichaDTO.setListaFichas( listaFichasDTO );
				}
				
				listaSecciones.add(seccionFichaDTO);
			} 

        	resultats.put("seccions", listaSecciones);
        	
            //Materias asociadas           
            if (uni.getUnidadesMaterias() != null) {             
            
                for(UnidadMateria unidadMateria : uni.getUnidadesMaterias()){                
                    llistaMateriesDTO.add(new IdNomDTO(  unidadMateria.getMateria().getId(), 
                                                                     unidadMateria.getMateria().getNombreMateria(request.getLocale().getLanguage())
                                                                           ));                
                   }
                
                resultats.put("materies", llistaMateriesDTO);
            
            } else {
                resultats.put("materies", null);
            }            
            
            //Edificios
            
            if (uni.getEdificios() != null) {             
            
                for(Object edifici : uni.getEdificios()){                
                    llistaEdificisDTO.add(new IdNomDTO(  ((Edificio)edifici).getId(), 
                                                                     ((Edificio)edifici).getDireccion())
                                                                           );                
                   }
                
                resultats.put("edificis", llistaEdificisDTO);
            
            } else {
                resultats.put("edificis", null);
            } 
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
                resultats.put("id", -1);
            } else {
            	resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
            	resultats.put("id", -2);
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }
        
	    return resultats;
    }
	
	@RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardarUniAdm(HttpSession session, HttpServletRequest request) {
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        IdNomDTO result = null;
        
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
            
            //Campos obligatorios
            String nom = valoresForm.get("item_nom_ca");
            String validacio = valoresForm.get("item_validacio");
            String sexeResponsable = valoresForm.get("item_responsable_sexe");
            String tractament = valoresForm.get("item_tractament");
            
            if (nom == null || "".equals(nom) || validacio == null || "".equals(validacio) || sexeResponsable == null || "".equals(sexeResponsable) || tractament == null || "".equals(tractament)) {
                String error = messageSource.getMessage("unitatadm.formulari.error.falten_camps", null, request.getLocale());
                result = new IdNomDTO(-3l, error);
                return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);                
            } 
            
            UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
            
            UnidadAdministrativa unitatAdministrativa;
            
			Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
			if (id != null) { 
				unitatAdministrativa = unitatAdministrativaDelegate.consultarUnidadAdministrativa(id);
			} else {									
				unitatAdministrativa = new UnidadAdministrativa();
			}

			// Idiomas
            //TraduccionUA tUA;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			Map traduccions = new HashMap(langs.size());
			for (String lang: langs) {
				TraduccionUA tUA = new TraduccionUA();
				tUA.setNombre(valoresForm.get("item_nom_"+  lang ));
				tUA.setPresentacion(valoresForm.get("item_presentacio_" + lang));
				tUA.setAbreviatura(valoresForm.get("item_abreviatura_" + lang));
				tUA.setUrl(valoresForm.get("item_url_" + lang));				
				traduccions.put(lang, tUA);
			}
			unitatAdministrativa.setTraduccionMap(traduccions);
			
			// Fin idiomas

			
            //Condifuracion/gestion
            //unitatAdministrativa.setClaveHita(valoresForm.get("item_clau_hita"));
            unitatAdministrativa.setCodigoEstandar(valoresForm.get("item_codi_estandar"));
            unitatAdministrativa.setDominio(valoresForm.get("item_domini"));
            unitatAdministrativa.setValidacion(Integer.parseInt(valoresForm.get("item_validacio")));
            unitatAdministrativa.setTelefono(valoresForm.get("item_telefon"));
            unitatAdministrativa.setFax(valoresForm.get("item_fax"));
            unitatAdministrativa.setEmail(valoresForm.get("item_email"));
            
            Long espaiTerritorialId = ParseUtil.parseLong(valoresForm.get("item_espai_territorial"));
            if (espaiTerritorialId != null) {
				EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
				EspacioTerritorial espacioTerritorial = espacioTerritorialDelegate.obtenerEspacioTerritorial(espaiTerritorialId);
				unitatAdministrativa.setEspacioTerrit(espacioTerritorial);                	
            } else {
            	unitatAdministrativa.setEspacioTerrit(null);
            }
            
            Long unitatAdmPareId = ParseUtil.parseLong(valoresForm.get("item_pare_id"));
            if (unitatAdmPareId != null) {
				UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
				UnidadAdministrativa pare = unidadAdministrativaDelegate.obtenerUnidadAdministrativa(unitatAdmPareId);
				unitatAdministrativa.setPadre(pare);                	
            } else {
            	unitatAdministrativa.setPadre(null); 
            }
			
			//Responsable
			unitatAdministrativa.setResponsable(valoresForm.get("item_responsable"));
			unitatAdministrativa.setSexoResponsable(Integer.parseInt(valoresForm.get("item_responsable_sexe")));
			
			//FotoPetita
    		FileItem fileFotoPetita = ficherosForm.get("item_responsable_foto_petita");
    		if ( fileFotoPetita.getSize() > 0 ) {
    			unitatAdministrativa.setFotop(UploadUtil.obtenerArchivo(unitatAdministrativa.getFotop(), fileFotoPetita));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_responsable_foto_petita_delete") != null && !"".equals(valoresForm.get("item_responsable_foto_petita_delete"))){
    			unitatAdministrativa.setFotop(null);
    		}
    		//FotoGran
    		FileItem fileFotoGran = ficherosForm.get("item_responsable_foto_gran");
    		if ( fileFotoGran.getSize() > 0 ) {
    			unitatAdministrativa.setFotog(UploadUtil.obtenerArchivo(unitatAdministrativa.getFotog(), fileFotoGran));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_responsable_foto_gran_delete") != null && !"".equals(valoresForm.get("item_responsable_foto_gran_delete"))){
    			unitatAdministrativa.setFotog(null);
    		}

			Long tractamentId = ParseUtil.parseLong(valoresForm.get("item_tractament"));
			if (tractamentId != null) {
				TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
				Tratamiento tratamiento = tratamientoDelegate.obtenerTratamiento(tractamentId);
				unitatAdministrativa.setTratamiento(tratamiento);
			} else {
				String error = messageSource.getMessage("unitatadm.formulari.error.tractament_incorrecte", null, request.getLocale());
				result = new IdNomDTO(-3l, error);	
				return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
			}
		
			//Logotipos
			//LogoHoritzontal
    		FileItem fileLogoHoritzontal = ficherosForm.get("item_logo_horizontal");
    		if ( fileLogoHoritzontal.getSize() > 0 ) {
    			unitatAdministrativa.setLogoh(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogoh(), fileLogoHoritzontal));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_logo_horizontal_delete") != null && !"".equals(valoresForm.get("item_logo_horizontal_delete"))){
    			unitatAdministrativa.setLogoh(null);
    		}
    		//LogoVertical
    		FileItem fileLogoVertical = ficherosForm.get("item_logo_vertical");
    		if ( fileLogoVertical.getSize() > 0 ) {
    			unitatAdministrativa.setLogov(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogov(), fileLogoVertical));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_logo_vertical_delete") != null && !"".equals(valoresForm.get("item_logo_vertical_delete"))){
    			unitatAdministrativa.setLogov(null);
    		}
    		//LogoSalutacioHoritzontal
    		FileItem fileLogoSalutacioHoritzontal = ficherosForm.get("item_logo_salutacio_horizontal");
    		if ( fileLogoSalutacioHoritzontal.getSize() > 0 ) {
    			unitatAdministrativa.setLogos(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogos(), fileLogoSalutacioHoritzontal));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_logo_salutacio_horizontal_delete") != null && !"".equals(valoresForm.get("item_logo_salutacio_horizontal_delete"))){
    			unitatAdministrativa.setLogos(null);
    		}
    		//LogoSalutacioVertical
    		FileItem fileLogoSalutacioVertical = ficherosForm.get("item_logo_salutacio_vertical");
    		if ( fileLogoSalutacioVertical.getSize() > 0 ) {
    			unitatAdministrativa.setLogot(UploadUtil.obtenerArchivo(unitatAdministrativa.getLogot(), fileLogoSalutacioVertical));
    		} else
    		//borrar fichero si se solicita
    		if (valoresForm.get("item_logo_salutacio_vertical_delete") != null && !"".equals(valoresForm.get("item_logo_salutacio_vertical_delete"))){
    			unitatAdministrativa.setLogot(null);
    		}
    		
			//Fichas de la portada web
			if (valoresForm.get("item_nivell_1")!=null && !"".equals(valoresForm.get("item_nivell_1"))){
				unitatAdministrativa.setNumfoto1(ParseUtil.parseInt(valoresForm.get("item_nivell_1")));
			}
			if (valoresForm.get("item_nivell_2")!=null && !"".equals(valoresForm.get("item_nivell_2"))){
				unitatAdministrativa.setNumfoto2(ParseUtil.parseInt(valoresForm.get("item_nivell_2")));
			}
			if (valoresForm.get("item_nivell_3")!=null && !"".equals(valoresForm.get("item_nivell_3"))){
				unitatAdministrativa.setNumfoto3(ParseUtil.parseInt(valoresForm.get("item_nivell_3")));
			}
			if (valoresForm.get("item_nivell_4")!=null && !"".equals(valoresForm.get("item_nivell_4"))){
				unitatAdministrativa.setNumfoto4(ParseUtil.parseInt(valoresForm.get("item_nivell_4")));
			}
			
			//Materias asociadas						
			if (valoresForm.get("materies") != null ) {
            	
            	UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
            	MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
				
            	Set<UnidadMateria> unidadesMateriasNuevas = new HashSet<UnidadMateria>();
            	
				String[] codisMateriesNoves = valoresForm.get("materies").split(",");				
				
				//Si es edición sólo tendremos en cuenta las nuevas materias
				if (id != null) {
										
					borrarUnidadesMateriaObsoletas(unitatAdministrativa, codisMateriesNoves );
					
					//Saltamos este paso si se han borrado todas las materias
					if ( !"".equals(valoresForm.get("materies")) ) {
						
		                for (int i = 0; i < codisMateriesNoves.length; i++) {
		                		   
		                    for ( UnidadMateria unidadMateria: unitatAdministrativa.getUnidadesMaterias() ) {
		                    	
		                        if ( unidadMateria.getMateria().getId().equals(Long.valueOf(codisMateriesNoves[i]) ) ) { //materia ya existente
		                            unidadesMateriasNuevas.add(unidadMateria);
		                            codisMateriesNoves[i] = null;
		                            break;
		                        }
		                        	                        
		                    }    
		                }
	                }                         					
				}
					
				if ( !"".equals(valoresForm.get("materies")) ) {				
	                for (String codiMateria: codisMateriesNoves) {
	                	
	                    if (codiMateria != null) {                    	
	                    	UnidadMateria nuevaUnidadMateria = new UnidadMateria();
	                    	Materia materia = materiaDelegate.obtenerMateria(Long.valueOf(codiMateria));
	                    	unidadMateriaDelegate.grabarUnidadMateria(nuevaUnidadMateria, unitatAdministrativa.getId(), materia.getId());	                    		                    
	                    }
	                }
				}
            }
            
			//Sólo en caso de edición
			if (id != null) {
				EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();			
				
				//Recollir els edificis actuals de la UA
				Set<Edificio> edificiosActuales = edificioDelegate.listarEdificiosUnidad(unitatAdministrativa.getId());
				
				// Esborrar els edificis actuals
				for (Edificio edificio : edificiosActuales)
					edificioDelegate.eliminarUnidad(unitatAdministrativa.getId(), edificio.getId());
				
				//Crear una llista amb els edificis assignats de la unitat
				String[] listaEdificios = valoresForm.get("llistaEdificis").replace(",", " ").trim().split(" ");		
				
				//Grabar en la unidad cada edificio de la lista (parámetro "listaEdificios")			
				if (!"".equals(listaEdificios[0])) {				
					for (int i = 0; i < listaEdificios.length; i++) 
						edificioDelegate.anyadirUnidad(unitatAdministrativa.getId(), new Long(listaEdificios[i]));
				}
			} 			
			
			//Secciones-Fichas											
			String llistaSeccions = valoresForm.get("llistaSeccions");						
			DelegateUtil.getFichaDelegate().crearSeccionesFichas(unitatAdministrativa, llistaSeccions.split("[,]"));
			
			crearOActualizarUnitatAdministrativa(unitatAdministrativaDelegate,	unitatAdministrativa);			
			
			// Sobre escrivim la unitat administrativa de la mollapa
			session.setAttribute("unidadAdministrativa", unitatAdministrativa);
			
            String ok = messageSource.getMessage("unitatadm.guardat.correcte", null, request.getLocale());
            result = new IdNomDTO(unitatAdministrativa.getId(), ok);            

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

	/**
	 * @param unitatAdministrativaDelegate
	 * @param unitatAdministrativa
	 * @throws DelegateException
	 */
	private void crearOActualizarUnitatAdministrativa(UnidadAdministrativaDelegate unitatAdministrativaDelegate, UnidadAdministrativa unitatAdministrativa) 
		throws DelegateException 
	{
		
		if (unitatAdministrativa.getId() != null) {
			if (unitatAdministrativa.getPadre() != null ) { 
				unitatAdministrativaDelegate.actualizarUnidadAdministrativa(unitatAdministrativa,unitatAdministrativa.getPadre().getId());
			} else {
				unitatAdministrativaDelegate.actualizarUnidadAdministrativa(unitatAdministrativa,null);
			}
		} else {
			Long id  ;
			if (unitatAdministrativa.getPadre() != null ) {
				id = unitatAdministrativaDelegate.crearUnidadAdministrativa(unitatAdministrativa, unitatAdministrativa.getPadre().getId());
			} else {
				id = unitatAdministrativaDelegate.crearUnidadAdministrativaRaiz(unitatAdministrativa);
			}
			unitatAdministrativa.setId(id);
		}
				
	}
	
//    /**
//     * Método que comprueba si hay que mostrar los logos
//     *
//     * @return boolean
//     */
//    private boolean mostrarLogosUA(){
//       
//        String value = System.getProperty("es.caib.rolsac.logos");
//        return !((value == null) || value.equals("N"));
//    }
    
	@RequestMapping(value = "/esborrar.do", method = POST)
    public @ResponseBody IdNomDTO esborrarUniAdm(HttpServletRequest request) {
	    

		Long id = new Long(request.getParameter("id"));
		IdNomDTO resultatStatus = new IdNomDTO(); 
	    
	    try {
	    	UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
	    		    		    	
	    	if ( !hayMicrositesUA(id) ) {
	    		UnidadAdministrativa unitatAdministrativa = unidadAdministrativaDelegate.consultarUnidadAdministrativa(id);
	    	
	    		// Validamos que se pueda eliminar la UA. Se podrá eliminar si no tiene elementos relacionados. A excepción de 
	    		// usuarios y edificios.
	    		boolean esBorrable = validarPermisosEliminacionUA(unitatAdministrativa,unidadAdministrativaDelegate);
	    		
            	if (!esBorrable )
            		return new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
	    	
            	String errorElementosRelacionados = validarElementosRelacionados(unitatAdministrativa);
            	
            	if ( "".equals(errorElementosRelacionados ) )
            		unidadAdministrativaDelegate.eliminarUaSinRelaciones(id);                	            	
            	else
            		return new IdNomDTO(-1l, messageSource.getMessage(errorElementosRelacionados, null, request.getLocale()));
            	
	    	} else 
	    		return new IdNomDTO(id, messageSource.getMessage("unitatadm.esborrat.incorrecte.microsites", null, request.getLocale()));	    	

	    } catch (DelegateException dEx) {
          if (dEx.isSecurityException()) {
              resultatStatus.setId(-1l);
          } else {
              resultatStatus.setId(-2l);              
          }	
          log.error(ExceptionUtils.getStackTrace(dEx));
	    }

	    request.getSession().setAttribute("unidadAdministrativa", null);	    
	    return new IdNomDTO(id, messageSource.getMessage("unitatadm.esborrat.correcte", null, request.getLocale()) );	    
	}

	@RequestMapping(value = "/llistatFitxesUA.do", method = POST)
	public @ResponseBody Map<String, Object> llistaFitxes(HttpServletRequest request) {
		
		List<Ficha> llistaFitxes = new ArrayList<Ficha>();
		List<FichaDTO> llistaFitxesDTO = new ArrayList<FichaDTO>();
		Map<String, Object> resultats = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> tradMap = new HashMap<String, Object>();
		
		String lang = request.getLocale().getLanguage();
				
		//Per defecte només carregarem les fitxes de la UA actual i de les seves UAs filles
		boolean uaMeves = false;
		boolean uaFilles = false;
		
		UnidadAdministrativa ua = new UnidadAdministrativa();
		
        if ( request.getSession().getAttribute("unidadAdministrativa") == null) {
        	
        	resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
        	resultats.put("id", -2);
        	
        	log.error("Error de sessión: Sessión expirada o no inciada");
        	
            return resultats; // Si no hay unidad administrativa se devuelve vacio
            
        } 
        
        ua = (UnidadAdministrativa) request.getSession().getAttribute("unidadAdministrativa");		
		
		try {			
			Long codiFitxa = ParseUtil.parseLong(request.getParameter("codiFitxa"));
			paramMap.put("id", codiFitxa);
		} catch (NumberFormatException e) {			
		}
		
		String textes = request.getParameter("texteFitxa");
		
		if ( textes != null && !"".equals(textes) ) {
			
			textes = textes.toUpperCase();
			tradMap.put("titulo", textes);
			
		} else {
			tradMap.put("idioma", lang);
		}
		
        try {
            FichaDelegate fitxaDelegate = DelegateUtil.getFichaDelegate();
            llistaFitxes = fitxaDelegate.buscarFichas(paramMap, tradMap, ua, null, null, uaFilles, uaMeves);           
            
            for (Ficha fitxa : llistaFitxes) {
                TraduccionFicha tfi = (TraduccionFicha) fitxa.getTraduccion(request.getLocale().getLanguage());
                llistaFitxesDTO.add(new FichaDTO(fitxa.getId(), 
                                                             tfi == null ? null : tfi.getTitulo(), 
                                                             DateUtils.formatDate(fitxa.getFechaPublicacion()), 
                                                             DateUtils.formatDate(fitxa.getFechaCaducidad()),
                                                             DateUtils.formatDate(fitxa.getFechaActualizacion()),
                                                             fitxa.isVisible()));
            }

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                // model.put("error", "permisos");
            	log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
            } else {
                // model.put("error", "altres");
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
        }
        
        resultats.put("total", llistaFitxesDTO.size());
        resultats.put("nodes", llistaFitxesDTO);

        return resultats;
		
	}
	
	@RequestMapping(value = "/llistatSeccions.do", method = POST)	
	public @ResponseBody Map<String, Object> llistaSeccions(HttpServletRequest request) {
		
		Map<String,Object> resultats = new HashMap<String,Object>();
		
		try {	
			
			String filtreNom = request.getParameter("nomSeccio").trim();									
			SeccionDelegate secDel = DelegateUtil.getSeccionDelegate();			
					
			List<Seccion> listaSecciones = secDel.listarSecciones();
			List<SeccionDTO> listaSeccionesDTO = new ArrayList<SeccionDTO>();
			
			for ( Iterator iterator = listaSecciones.iterator(); iterator.hasNext(); ) {
				
				SeccionDTO seccionDTO = new SeccionDTO();				
				Seccion seccion = (Seccion) iterator.next();
				
				String nomSeccio = ( (TraduccionSeccion) seccion.getTraduccion( request.getLocale().getLanguage()) ).getNombre().replaceAll("\\<.*?>", "");
				
				if ( toFormatComparacio( nomSeccio ).contains( toFormatComparacio(filtreNom) ) || "".equals(filtreNom) ) { 
					seccionDTO.setId( seccion.getId() );
					seccionDTO.setNom( nomSeccio );

					listaSeccionesDTO.add(seccionDTO);
				}
			}
			
			Collections.sort( listaSeccionesDTO );			
			
	        resultats.put("total", listaSeccionesDTO.size());        
	        resultats.put("nodes", listaSeccionesDTO);        			
			
		} catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
                resultats.put("id", -1);
            } else {
            	resultats.put("error", messageSource.getMessage("error.operacio_fallida", null, request.getLocale()));
            	resultats.put("id", -2);
            	log.error(ExceptionUtils.getStackTrace(dEx));
            }
		}
		
		return resultats;
	} 
	
	/**
     * Método que comprueba si hay microsites para una Unidad Orgánica
     * @param idua identificador de la unidad organica
     * @return boolean
     */
    private boolean hayMicrositesUA(Long idua){
    	boolean retorno=false;
    	
    	try {
	    	String value = System.getProperty("es.caib.rolsac.microsites");
	    	
	    	if ((value == null) || value.equals("N")) {
	    		retorno=false;
	    	} else {
	            retorno = tieneMicrosites(idua);
	    	}    	
		} catch (Exception e) {
			retorno=true; //para evitar inconsistencias
		}
    	
    	
    	return retorno;
    }
    
    private boolean tieneMicrosites(Long idua) throws Exception {
		boolean retorno = false;
		org.ibit.rol.sac.micropersistence.delegate.MicrositeDelegate micro = org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getMicrositeDelegate();
		List micros = micro.listarMicrosites();
		           
		Iterator iter = micros.iterator();
		while (iter.hasNext()) 
		{
			org.ibit.rol.sac.micromodel.Microsite mic = (org.ibit.rol.sac.micromodel.Microsite) iter.next();
			if (mic.getUnidadAdministrativa()==idua.intValue()) {
	        			retorno=true;
				break;
			}
		}
		return retorno;
	}
	
    /**
     * Descripción: Método que valida si la UA puede ser eliminada.
     * 
     * @author Indra
     * @param  ua Unidad administrativas
     * @param  unidadDelegate  Delegado de la Unidad administrativa
     * @return Devuelve true o false en función de si la unidad administrativa puede ser o no borrada
     */
    private boolean validarPermisosEliminacionUA(UnidadAdministrativa ua, UnidadAdministrativaDelegate unidadDelegate) {
    	
    	// Comprobar si el usuari puede eliminar UA
    	try {    		
    		Long id = ua.isRaiz() ? ua.getId() : ua.getPadre().getId();    		
    		return unidadDelegate.autorizarEliminarUA(id);    		    		
    	} catch(Exception e) {
    		return false;  
    	}
    }
    
    private String validarElementosRelacionados(UnidadAdministrativa ua) {
    	
    	boolean boolProcedIsEmpty =ua.getProcedimientos().isEmpty();    	
    	String ids = "";
    	
    	//Compronbar si la UA tiene elementos relacionados
        if(!ua.getHijos().isEmpty())
        	return "unitatadm.esborrat.incorrecte.uafilles";
        else if(!ua.getFichasUA().isEmpty())
        	return "unitatadm.esborrat.incorrecte.fitxes";
        else if(!ua.getPersonal().isEmpty())       
        	return "unitatadm.esborrat.incorrecte.personal";
        else if(!ua.getUnidadesMaterias().isEmpty())        	
        	return "unitatadm.esborrat.incorrecte.materies";
        else if(!boolProcedIsEmpty || !ua.getNormativas().isEmpty() ){
       	
        	List <Long> idsList=new ArrayList<Long>();

        	if(!boolProcedIsEmpty)
        		idsList = ua.getIdsProcedimientos();
        	else
        		idsList = ua.getIdsNormativas();
        	    	
        	Iterator<Long> iter = idsList.iterator();
        	int count = 0;
        	while(iter.hasNext()){
        		Long id = iter.next();
        		if (ids.equals("")){
          			ids = id.toString();
          			count++;
        		}else{
          			if ((count % 10) == 0)
          				ids = ids + ",<BR> " + id.toString();
          			else
          				ids = ids + ", " + id.toString();
          			
          			count++;
          		}
        	}
        	
        	if(!boolProcedIsEmpty)
        		return "unitatadm.esborrat.incorrecte.procediments";
        	else
        		return "unitatadm.esborrat.incorrecte.normatives";        	
        }

        //return errores;
        return "";
    }
    
	/**
	 * A partir de una lista de la entidad UnidadMateria, borra aquellos elementos que ya no pertenecerán
	 * a ella, según los códigos de Materia pasados por parámetro. Si la lista de códigos está vacía, se 
	 * borrarán todas las materias de la UA. 
	 */
	private void borrarUnidadesMateriaObsoletas(UnidadAdministrativa unidadAdministrativa, String[] codigosMateriasNuevas) throws DelegateException {
		
		UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
		Set<UnidadMateria> listaUnidadMateria = unidadAdministrativa.getUnidadesMaterias();			
		List<Long> listaIdUnidadMateriaObsoleta = new ArrayList();
				
		if ( !"".equals(codigosMateriasNuevas[0]) ) {
			for ( UnidadMateria unidadMateria : listaUnidadMateria ) {
				
				Materia materia = unidadMateria.getMateria();
				int totalMateriasNuevas = codigosMateriasNuevas.length;
				
				int i = 0;
				while ( i < totalMateriasNuevas && ( !materia.getId().equals(new Long(codigosMateriasNuevas[i])) ) ) 
					i++;
				
				//Si la materia no está entre los codigos nuevos, significa que ha sido eliminada
				//y, por tanto, la borraremos
				if ( i == totalMateriasNuevas  ) 
					listaIdUnidadMateriaObsoleta.add(new Long(unidadMateria.getId()));
				
			}
			
		//Si la lista de códigos está vacía, significará que hay que borrar todas las materias de la UA
		} else {
			for ( UnidadMateria unidadMateria : listaUnidadMateria ) 
				listaIdUnidadMateriaObsoleta.add(new Long(unidadMateria.getId()));
		}
		
		for ( Long id : listaIdUnidadMateriaObsoleta ) 
			unidadMateriaDelegate.borrarUnidadMateria( id );	
	}
	
	/**
	 * Ordena un treemap segun el key
	 *
	 */
	private TreeMap ordenarArbolSecciones(TreeMap arbolSecciones) {
	
		TreeMap newtreesecciones = new TreeMap( new TreeOrdenSeccionComparator() );
		for( Iterator it = arbolSecciones.keySet().iterator(); it.hasNext(); ) {
	    	String key = (String)it.next();
	    	
	    	//Eliminamos el código html que pueda haber en el nombre de la sección.
	    	key = key.split("#")[0] + "#" + (key.split("#")[1]).replaceAll("\\<.*?>", "");
	    	 
	    	//Obtenemos el orden de la sección de cualquier FichaUA de la sección actual
	    	//para reordenar el TreeMap original
	    	if (arbolSecciones.get(key) != null){
	            int orden = ((ArrayList<FichaUA>) arbolSecciones.get(key)).get(0).getOrdenseccion();
	            newtreesecciones.put(orden+"#"+key, arbolSecciones.get(key));   
	    	}	    	
	    }
		    
	    return newtreesecciones;
		  	
	}	
	
	/**
	 * Retorna una cadena que canvia les vocals amb accent o dièresi 
	 * per vocals sense aquestes (emprat per cercar registres coincidents 
	 * de seccions).
	 * 
	 * @param cadena
	 * @return String
	 */
	private String toFormatComparacio( String cadena ) {
		
		return cadena.toLowerCase().replaceAll("[áàä]", "a")
					 			   .replaceAll("[éèë]", "e")
					 			   .replaceAll("[íìï]", "i")
					 			   .replaceAll("[óòö]", "o")
					 			   .replaceAll("[úùü]", "u");		
	}
	
}
