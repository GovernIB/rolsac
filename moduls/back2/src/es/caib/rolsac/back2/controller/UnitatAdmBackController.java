package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
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

@Controller
@RequestMapping("/unitatadm/")
public class UnitatAdmBackController {

    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
	@RequestMapping(value = "/unitatadm.do", method = GET)
	public String llistatUniAdm(Map<String, Object> model, HttpServletRequest request, HttpSession session) {	    
		
	    MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
	    
	    TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
	    EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
	    
	    List<Materia> llistaMateries = new ArrayList<Materia>();
	    List<Tratamiento> llistaTractaments = new ArrayList<Tratamiento>();
	    List<EspacioTerritorial> llistaEspaiTerritorial = new ArrayList<EspacioTerritorial>();
	    List<IdNomTransient> llistaMateriesTransient = new ArrayList<IdNomTransient>();
	    List<IdNomTransient> llistaTractamentsTransient = new ArrayList<IdNomTransient>();
	    List<IdNomTransient> llistaEspaiTerritorialTransient = new ArrayList<IdNomTransient>();
	    
	    try {                                
            llistaMateries = materiaDelegate.listarMaterias();	    	            
            
            for(Materia materia : llistaMateries){                
                llistaMateriesTransient.add(new IdNomTransient(  materia.getId(), 
                                                                 materia.getNombreMateria(request.getLocale().getLanguage())
                                                                       ));                
               }
            
            llistaTractaments = tratamientoDelegate.listarTratamientos();
            
            for(Tratamiento tractament : llistaTractaments){                
                llistaTractamentsTransient.add(new IdNomTransient(  tractament.getId(), 
                                                                   tractament.getNombreTratamiento(request.getLocale().getLanguage())
                                                                       ));                
               }                       
            
            llistaEspaiTerritorial = espacioTerritorialDelegate.listarEspaciosTerritoriales();
            
            for(EspacioTerritorial espaiTerritorial : llistaEspaiTerritorial){                
                llistaEspaiTerritorialTransient.add(new IdNomTransient(  espaiTerritorial.getId(), 
                                                                   espaiTerritorial.getNombreEspacioTerritorial(request.getLocale().getLanguage())
                                                                       ));                
               }                       
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                //model.put("error", "permisos");//TODO:mensajes de error
            } else {
                //model.put("error", "altres");
                dEx.printStackTrace();
            }
        }
	    
		model.put("menu", 0);
		model.put("submenu", "layout/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 2);
		model.put("titol_escriptori", messageSource.getMessage("submenu.unitatAdm", null, request.getLocale()));
		model.put("escriptori", "pantalles/unitatadm.jsp");
        if (session.getAttribute("unidadAdministrativa")!=null){
            model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(request.getLocale().getLanguage()));            
        }               
        
        model.put("llistaMateries", llistaMateriesTransient);        
        model.put("llistaTractaments", llistaTractamentsTransient);
        model.put("llistaEspaiTerritorial", llistaEspaiTerritorialTransient);
        
		return "index";
	}
	
	@RequestMapping(value = "/pagDetall.do", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
        
	    Map<String,Object> resultats = new HashMap<String,Object>();	    
	    List<IdNomTransient> llistaMateriesTransient = new ArrayList<IdNomTransient>();
	    List<IdNomTransient> llistaEdificisTransient = new ArrayList<IdNomTransient>();
	    
	    UnidadAdministrativaDelegate unitatDelegate = DelegateUtil.getUADelegate();	    	    	    	    

	    Long idUA = null;
	    
	    if (request.getParameter("id") == null || "".equals(request.getParameter("id"))){
	        resultats.put("id", -1);
            return resultats;//Si no hay unidad administrativa se devuelve vacio
        } else {
            idUA = new Long(request.getParameter("id"));            
        } 	  
   
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
            
            resultats.put("item_clau_hita", uni.getClaveHita());
            resultats.put("item_codi_estandar", uni.getCodigoEstandar());
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
            	resultats.put("item_responsable_foto_gran_enllas_arxiu", "unitatadm/archivo.do?id=" + uni.getId() + "&tipuus=2");
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
                        
//            if(mostrarLogosUA()){    //Codigo de del sacback original       
//                if (uni.getLogoh() != null) {
//                    resultats.put("item_logo_horizontal", uni.getLogoh().getNombre());
//                }
//                if (uni.getLogov() != null) {
//                    resultats.put("item_logo_vertical", uni.getLogov().getNombre());
//                }
//                if (uni.getLogos() != null) {
//                    resultats.put("item_logo_salutacio_horizontal", uni.getLogos().getNombre());
//                }
//                if (uni.getLogot() != null) {
//                    resultats.put("item_logo_salutacio_vertical", uni.getLogot().getNombre());
//                }
//            }                        
            
            //Fichas de la portada web            
            resultats.put("item_nivell_1", uni.getNumfoto1());
            resultats.put("item_nivell_2", uni.getNumfoto2());
            resultats.put("item_nivell_3", uni.getNumfoto3());
            resultats.put("item_nivell_4", uni.getNumfoto4());
	          
            //Materias asociadas
           
            if (uni.getUnidadesMaterias() != null) {             
            
                for(UnidadMateria unidadMateria : uni.getUnidadesMaterias()){                
                    llistaMateriesTransient.add(new IdNomTransient(  unidadMateria.getMateria().getId(), 
                                                                     unidadMateria.getMateria().getNombreMateria(request.getLocale().getLanguage())
                                                                           ));                
                   }
                
                resultats.put("materies", llistaMateriesTransient);
            
            } else {
                resultats.put("materies", null);
            }            
            
            
            //Edificios
            
            if (uni.getEdificios() != null) {             
            
                for(Object edifici : uni.getEdificios()){                
                    llistaEdificisTransient.add(new IdNomTransient(  ((Edificio)edifici).getId(), 
                                                                     ((Edificio)edifici).getDireccion())
                                                                           );                
                   }
                
                resultats.put("edificis", llistaEdificisTransient);
            
            } else {
                resultats.put("edificis", null);
            } 
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                //model.put("error", "permisos");//TODO:mensajes de error
            } else {
                //model.put("error", "altres");
                dEx.printStackTrace();
            }
            
        }
/*
        request.setAttribute("edificioOptions", uni.getEdificios());
        log.info("edificioOptions ->"+Arrays.toString(uni.getEdificios().toArray()));

        request.setAttribute("unidadesmateriasOptions", uni.getUnidadesMaterias());
        request.setAttribute("usuarioOptions", uni.getUsuarios());
        //request.setAttribute("fichaUAOptions", unidad.getFichasUA());
        TreeMap treesecciones = (TreeMap)uni.getMapSeccionFichasUA();
        TreeMap treeSortSecciones = ordenartreeseccion(treesecciones);
        request.setAttribute("fichaUAOptions", treeSortSecciones);
        
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
        List listaProcedimientos;
        
        if (uni.getPadre() == null) // es el govern, ( su padre es nulo)
        {
             
            request.setAttribute("conse",1 );
            listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,1);
        }
        else // la unidad tiene padre distinto de nulo.
        {
            
            UnidadAdministrativa elpadre = uni.getPadre();
            //Listamos los procedimiento asociados a la UA.
            // vemos si es a nievel conselleria o si es  a nivel interno.
            if (elpadre.getPadre()==null) //nivel conselleria
            {
                request.setAttribute("conse",1 );
                listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,1);
            }
            else
            {
                // analizamos el padre del padre para ver si es nivel direccion o servicio
                UnidadAdministrativa padrepadre = uni.getPadre().getPadre();
                if (padrepadre.getPadre()==null)
                {
                    request.setAttribute("conse",2 );
                    listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,2);
                }
                else
                {
                    request.setAttribute("conse",3 );
                    listaProcedimientos = procedimientoDelegate.listarProcedimientosUO(id,3);
                }   
              
            }
        }


        request.setAttribute("procedimientoOptions",listaProcedimientos ); 

*/        
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

        IdNomTransient result = null;
        
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
                result = new IdNomTransient(-3l, error);
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
            unitatAdministrativa.setClaveHita(valoresForm.get("item_clau_hita"));
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
				result = new IdNomTransient(-3l, error);	
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
            if (valoresForm.get("materies") != null && !"".equals(valoresForm.get("materies"))) {
            	
            	UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
            	MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
				
            	Set<UnidadMateria> unidadesMateriasNuevas = new HashSet<UnidadMateria>();
            	
				String[] codisMateriesNoves = valoresForm.get("materies").split(",");				
				
				//Si es edición sólo tendremos en cuenta las nuevas materias
				if (id != null) {
										
					borrarUnidadesMateriaObsoletas(unitatAdministrativa, codisMateriesNoves );
					
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
								
                for (String codiMateria: codisMateriesNoves) {
                	
                    if (codiMateria != null) {                    	
                    	UnidadMateria nuevaUnidadMateria = new UnidadMateria();
                    	Materia materia = materiaDelegate.obtenerMateria(Long.valueOf(codiMateria));
                    	unidadMateriaDelegate.grabarUnidadMateria(nuevaUnidadMateria, unitatAdministrativa.getId(), materia.getId());                     	                        
                    }
                }                
            }
            
			crearOActualizarUnitatAdministrativa(unitatAdministrativaDelegate,	unitatAdministrativa);			
			
			// Sobre escrivim la unitat administrativa de la mollapa
			session.setAttribute("unidadAdministrativa", unitatAdministrativa);
			
            String ok = messageSource.getMessage("unitatadm.guardat.correcte", null, request.getLocale());
            result = new IdNomTransient(unitatAdministrativa.getId(), ok);
            
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                String error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomTransient(-1l, error);
            } else {
                String error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomTransient(-2l, error);
                dEx.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomTransient(-2l, error);
			e.printStackTrace();
        } catch (FileUploadException e) {
			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomTransient(-3l, error);
			e.printStackTrace();
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
    public @ResponseBody IdNomTransient esborrarUniAdm(HttpServletRequest request) {
	    
	    IdNomTransient resultatStatus = new IdNomTransient(); 
	    
//	    try {
//            
//	    	UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
//	    	
//            Long id = new Long(request.getParameter("id"));
//                       
//            if (!hayMicrositesUA(id)) {
//            
//            	UnidadAdministrativa unitatAdministrativa = unidadAdministrativaDelegate.consultarUnidadAdministrativa(id);
//            	
//            	// Validamos que se pueda eliminar la UA. Se podra elimnar si no tiene elementos relacionados. Ha excepción de usuarios y edificios.
//            	ActionErrors errores =  validarEliminacionUA(unitatAdministrativa,unidadAdministrativaDelegate);
//            	if(!errores.isEmpty()){
//            	//	saveErrors(request, errores);
//            		request.setAttribute("idUA", id);
//            		return dispatchMethod(mapping, form, request, response, "seleccionar");
//            		//return mapping.findForward("success");
//    	        }
//    	
//            	unidadAdministrativaDelegate.eliminarUaSinRelaciones(id);  
//
//            	resultatStatus.setId(1l);
//                resultatStatus.setNom("correcte");
//            	
//            	dForm.reset(mapping, request);
//    	        request.setAttribute("alert", "confirmacion.baja");
//    	        log.debug("Eliminada Unidad Administrativa: " + id);
//
//            } else {
//    	        request.setAttribute("alert", "microsites.ua.asociados");
//    	        log.debug("No se ha eliminado Unidad Administrativa: " + id + " . Causa: Tiene asociado algún microsite");
//            	
//            }
//            
//            
//            if (!hayMicrositesUA(id)) {
//            
//            unidadAdministrativaDelegate.eliminarUaSinRelaciones(id);             
//            
//            resultatStatus.setId(1l);
//            resultatStatus.setNom("correcte");
//            } else {
//            	resultatStatus.setId(-1l);
//                resultatStatus.setNom("No s'ha eliminat l'Unitat Orgànica perquè té microsites associats");
//            }
//            
//        } catch (DelegateException dEx) {
//            if (dEx.getCause() instanceof SecurityException) {
//                resultatStatus.setId(-1l);
//            } else {
//                resultatStatus.setId(-2l);
//                dEx.printStackTrace();
//            }
//        }
	    
	    return resultatStatus;
	}
    
	
//	/**
//     * Método que comprueba si hay microsites para una Unidad Orgánica
//     * @param idua identificador de la unidad organica
//     * @return boolean
//     */
//    private boolean hayMicrositesUA(Long idua){
//    	boolean retorno=false;
//    	
//    	try {
//	    	String value = System.getProperty("es.caib.rolsac.microsites");
//	    	
//	    	if ((value == null) || value.equals("N")) {
//	    		retorno=false;
//	    	} else {
//	            retorno = tieneMicrosites(idua);
//	    	}    	
//		} catch (Exception e) {
//			retorno=true; //para evitar inconsistencias
//		}
//    	
//    	
//    	return retorno;
//    }
//    
//    private boolean tieneMicrosites(Long idua) throws Exception {
//		boolean retorno = false;
//		MicrositeDelegate micro = org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getMicrositeDelegate();
//		List micros = micro.listarMicrosites();
//		           
//		Iterator iter = micros.iterator();
//		while (iter.hasNext()) 
//		{
//			Microsite mic = (Microsite) iter.next();
//			if (mic.getUnidadAdministrativa()==idua.intValue()) {
//	        			retorno=true;
//				break;
//			}
//		}
//		return retorno;
//	}
//	
//    /**
//     * Descripción: Método que valida si la UA puede ser eliminada.
//     * 
//     * @author Indra
//     * @param  ua  Unidad administrativa
//     * @param  unidadDelegate  Delegado de la Unidad administrativa
//     * @return Devuelve un objeto actionerrors, si el objeto contiene errores la UnidadAdministrativa no podra ser borrada
//     */
//    private ActionErrors validarEliminacionUA(UnidadAdministrativa ua, UnidadAdministrativaDelegate unidadDelegate) {
//    	
//    	ActionErrors errores = new ActionErrors();
//    	String ids ="";
//    	boolean boolProcedIsEmpty =ua.getProcedimientos().isEmpty();
//    	
//    	// Comprobar si el usuari puede eliminar UA
//    	try{
//    		if (!ua.isRaiz()) { 
//    			if (!unidadDelegate.autorizarEliminarUA(ua.getPadre().getId()))
//    				errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.permisosAntecesorUA"));
//    		} else {
//    			if (!unidadDelegate.autorizarEliminarUA(ua.getId()))
//    				errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.permisosUA"));
//    		}
//    		
//    	}catch(Exception e){
//    		errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.permisos"));
//    		return errores;  
//    	}
//
//    	//Compronbar si la UA tiene elementos relacionados
//        if(!ua.getHijos().isEmpty()){
//        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneHijos"));
//        }else if(!ua.getFichasUA().isEmpty()){
//        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneFichas"));
//        }else if(!ua.getPersonal().isEmpty()){
//        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tienePersonal"));
//        }else if(!ua.getUnidadesMaterias().isEmpty()){
//        	errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneUnidadesMaterias"));
//        }else if(!boolProcedIsEmpty || !ua.getNormativas().isEmpty() ){
//       	
//        	List <Long> idsList=new ArrayList<Long>();
//
//        	if(!boolProcedIsEmpty)
//        		idsList = ua.getIdsProcedimientos();
//        	else
//        		idsList = ua.getIdsNormativas();
//        	    	
//        	Iterator<Long> iter = idsList.iterator();
//        	int count = 0;
//        	while(iter.hasNext()){
//        		Long id = iter.next();
//        		if (ids.equals("")){
//          			ids = id.toString();
//          			count++;
//        		}else{
//          			if ((count % 10) == 0)
//          				ids = ids + ",<BR> " + id.toString();
//          			else
//          				ids = ids + ", " + id.toString();
//          			
//          			count++;
//          		}
//        	}
//        	
//        	if(!boolProcedIsEmpty)
//        		errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneProcedimientos",ids));
//        	else
//        		errores.add("ErrorEliminarUA", new ActionError("errors.borrarUA.tieneNormativas",ids));
//        	
//        }
//
//        return errores;
//    }
	/**
	 * A partir de una lista de la entidad UnidadMateria, borra aquellos elementos que ya no pertenecerán
	 * a ella, según los códigos de Materia pasados por parámetro. 
	 */
	private void borrarUnidadesMateriaObsoletas(UnidadAdministrativa unidadAdministrativa, String[] codigosMateriasNuevas) throws DelegateException {
		
		UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
		Set<UnidadMateria> listaUnidadMateria = unidadAdministrativa.getUnidadesMaterias();			
		List<Long> listaIdUnidadMateriaObsoleta = new ArrayList();
		
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
		
		for ( Long id : listaIdUnidadMateriaObsoleta ) 
			unidadMateriaDelegate.borrarUnidadMateria( id );
		
	}
}
