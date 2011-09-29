package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.TraduccionEspacioTerritorial;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTratamiento;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/unitatadm/")
public class UnitatAdmBackController {
    
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
	@RequestMapping(value = "/unitatadm.htm", method = GET)
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
	
	@RequestMapping(value = "/pagDetall.htm", method = POST)
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
        
	    Map<String,Object> resultats = new HashMap<String,Object>();	    
	    Map<String,Object> idiomes = new HashMap<String,Object>();
	    List<IdNomTransient> llistaMateriesTransient = new ArrayList<IdNomTransient>();
	    List<IdNomTransient> llistaEdificisTransient = new ArrayList<IdNomTransient>();
	    
	    Map<String,String> ca = new HashMap<String,String>();
	    Map<String,String> es = new HashMap<String,String>();
	    Map<String,String> en = new HashMap<String,String>();
	    Map<String,String> de = new HashMap<String,String>();
	    Map<String,String> fr = new HashMap<String,String>();
	    
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
            
            //Condifuracion/gestion
            
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
                resultats.put("item_responsable_foto_petita", uni.getFotop().getNombre());
            } else {
                resultats.put("item_responsable_foto_petita", null);
            }
            if (uni.getFotog() != null){
                resultats.put("item_responsable_foto_gran", uni.getFotog().getNombre());
            } else {
                resultats.put("item_responsable_foto_gran", null);
            }           

            if (uni.getTratamiento() != null) {
                //resultats.put("item_tractament", ((TraduccionTratamiento)uni.getTratamiento().getTraduccion("ca")).getTipo());//TODO:idioma por defecto
                resultats.put("item_tractament", uni.getTratamiento().getId());
            }
            
            //Logotipos
            
            if(mostrarLogosUA()){    //Codigo de del sacback original       
                if (uni.getLogoh() != null) {
                    resultats.put("item_logo_horizontal", uni.getLogoh().getNombre());
                }
                if (uni.getLogov() != null) {
                    resultats.put("item_logo_vertical", uni.getLogov().getNombre());
                }
                if (uni.getLogos() != null) {
                    resultats.put("item_logo_salutacio_horizontal", uni.getLogos().getNombre());
                }
                if (uni.getLogot() != null) {
                    resultats.put("item_logo_salutacio_vertical", uni.getLogot().getNombre());
                }
            }
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
	
	@RequestMapping(value = "/guardar.htm", method = POST)
    public @ResponseBody IdNomTransient guardarUniAdm(HttpSession session, HttpServletRequest request) {
        
        IdNomTransient result = null;
        
        try {
            //TODO pendent de quins camps son obligatoris
            UnidadAdministrativa ua = (UnidadAdministrativa)session.getAttribute("unidadAdministrativa");
            String nom = request.getParameter("item_nom_ca");
            String username = request.getParameter("item_codi");
            
            //if (ua == null || nom == null || username == null || "".equals(nom) || "".equals(username)) {
            if (ua == null || nom == null || "".equals(nom)) {
                String error = messageSource.getMessage("unitatadm.formulari.error.falten_camps", null, request.getLocale());
                result = new IdNomTransient(-3l, error);
            } else {
                UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
                UnidadAdministrativa unitatAdministrativa = new UnidadAdministrativa();
                UnidadAdministrativa unitatAdministrativaOld = null;

                try {
                    Long id = Long.parseLong(request.getParameter("item_id"));
                    unitatAdministrativaOld = unitatAdministrativaDelegate.obtenerUnidadAdministrativa(id); 
                } catch (NumberFormatException nfe) {

                }

                if (unitatAdministrativaOld != null) {
                	unitatAdministrativa.setId(unitatAdministrativaOld.getId());
                	
                	/*
                	 * Setear Fitxers
                	 */
                	
                }
            	// Idiomas
                TraduccionUA tUA;
				IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
				List<String> langs = idiomaDelegate.listarLenguajes();
				for (String lang: langs) {
					tUA = new TraduccionUA();
					tUA.setNombre("item_nom_"+  lang );
					tUA.setPresentacion("item_presentacio_" + lang);
					tUA.setAbreviatura("item_abreviatura_" + lang);
					tUA.setUrl("item_url_" + lang);
					
					unitatAdministrativa.setTraduccion(lang, tUA);
				}
				// Fin idiomas
                
                
                
                //Condifuracion/gestion
                unitatAdministrativa.setClaveHita(request.getParameter("item_clau_hita"));
                unitatAdministrativa.setCodigoEstandar(request.getParameter("item_codi_estandar"));
                unitatAdministrativa.setDominio(request.getParameter("item_domini"));
                unitatAdministrativa.setValidacion(Integer.parseInt(request.getParameter("item_validacio")));
                unitatAdministrativa.setTelefono(request.getParameter("item_telefon"));
                unitatAdministrativa.setFax(request.getParameter("item_fax"));
                unitatAdministrativa.setEmail(request.getParameter("item_email"));
                
                
                try {
					Long espaiTerritorialId = Long.parseLong(request.getParameter("item_espai_territorial"));
					EspacioTerritorialDelegate espacioTerritorialDelegate = DelegateUtil.getEspacioTerritorialDelegate();
					EspacioTerritorial espacioTerritorial = espacioTerritorialDelegate.obtenerEspacioTerritorial(espaiTerritorialId);
					unitatAdministrativa.setEspacioTerrit(espacioTerritorial);
				} catch (NumberFormatException e) {
					// String error = messageSource.getMessage("error.permisos", null, request.getLocale());
					String error = "L'espai territorial és incorrecte.";
					result = new IdNomTransient(-3l, error);
				}
                
				
				try {
					Long unitatAdmPareId = Long.parseLong(request.getParameter("item_espai_territorial"));
					UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
					UnidadAdministrativa pare = unidadAdministrativaDelegate.obtenerUnidadAdministrativa(unitatAdmPareId);
					unitatAdministrativa.setPadre(pare);
				} catch (NumberFormatException e) {
					// String error = messageSource.getMessage("error.permisos", null, request.getLocale());
					String error = "L'Unitat Administrativa Pare és incorrecte.";
					result = new IdNomTransient(-3l, error);
				}
				
				
				
				//Responsable
				unitatAdministrativa.setResponsable(request.getParameter("item_responsable"));
				unitatAdministrativa.setSexoResponsable(Integer.parseInt(request.getParameter("item_responsable_sexe")));
				/*
				if (uni.getFotop() != null){
                    resultats.put("item_responsable_foto_petita", uni.getFotop().getNombre());
                } else {
                    resultats.put("item_responsable_foto_petita", null);
                }
                if (uni.getFotog() != null){
                    resultats.put("item_responsable_foto_gran", uni.getFotog().getNombre());
                } else {
                    resultats.put("item_responsable_foto_gran", null);
                }  
                
                */
                try {
					Long tractamentId = Long.parseLong(request.getParameter("item_tractament"));
					TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
					Tratamiento tratamiento = tratamientoDelegate.obtenerTratamiento(tractamentId);
					unitatAdministrativa.setTratamiento(tratamiento);
				} catch (NumberFormatException e) {
					// String error = messageSource.getMessage("error.permisos", null, request.getLocale());
					String error = "El tracatament és incorrecte.";
					result = new IdNomTransient(-3l, error);
				}
				
				
				//Logotipos
                /*
                if(mostrarLogosUA()){    //Codigo de del sacback original       
                    if (uni.getLogoh() != null) {
                        resultats.put("item_logo_horizontal", uni.getLogoh().getNombre());
                    }
                    if (uni.getLogov() != null) {
                        resultats.put("item_logo_vertical", uni.getLogov().getNombre());
                    }
                    if (uni.getLogos() != null) {
                        resultats.put("item_logo_salutacio_horizontal", uni.getLogos().getNombre());
                    }
                    if (uni.getLogot() != null) {
                        resultats.put("item_logo_salutacio_vertical", uni.getLogot().getNombre());
                    }
                }
				*/
				
				//Fichas de la portada web
                
				/*
                resultats.put("item_nivell_1", uni.getNumfoto1());
                resultats.put("item_nivell_2", uni.getNumfoto2());
                resultats.put("item_nivell_3", uni.getNumfoto3());
                resultats.put("item_nivell_4", uni.getNumfoto4());
				*/
               
				/*
                 
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
*/
				
				//unitatAdministrativaDelegate.actualizarUnidadAdministrativa(unitatAdministrativa, null);
				
				
                String ok = messageSource.getMessage("personal.guardat.correcte", null, request.getLocale());
                result = new IdNomTransient(unitatAdministrativa.getId(), ok);
            }
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                String error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomTransient(-1l, error);
            } else {
                String error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomTransient(-2l, error);
                dEx.printStackTrace();
            }
        }
        
        return result;
    }
	
    /**
     * Método que comprueba si hay que mostrar los logos
     *
     * @return boolean
     */
    private boolean mostrarLogosUA(){
       
        String value = System.getProperty("es.caib.rolsac.logos");
        return !((value == null) || value.equals("N"));
    }
    
    /*
//    @RequestMapping(value = "/guardar.htm", method = POST)
//    public @ResponseBody IdNomTransient guardar(HttpSession session, HttpServletRequest request) {
//         
//          //TODO: completar paso de normativa local a externa y viceversa.
//         
//    Normativa normativa = null;
//    Normativa normativaOld = null;          
//          IdNomTransient result = null;
//          boolean normativaLocal;
//     
//      try {
//          NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
//         
//          //Obtener la UA de la normativa. Si no tiene UA asignada es una normativa externa.
//          UnidadAdministrativa ua = null;
//          if (request.getParameter("item_ua_id") != null && !"".equals(request.getParameter("item_ua_id"))) {
//                Long idUA = new Long(request.getParameter("item_ua_id"));
//                ua = DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(idUA);
//          }
//
//          //Determinar si la normativa a guardar tiene que ser local/externa
//          if (ua == null) {
//                normativaLocal = false;
//                normativa = new NormativaExterna();
//          } else {
//                normativaLocal = true;
//                normativa = new NormativaLocal();     
//                //Asociar UA si es normativa local
//                ((NormativaLocal)normativa).setUnidadAdministrativa(ua);                    
//          }          
//                     
//          boolean edicion = request.getParameter("item_id") != null && !"".equals(request.getParameter("item_id"));
//          Long idNorm = Long.parseLong(request.getParameter("item_id"));
//          if (edicion) {
//                normativaOld = normativaDelegate.obtenerNormativa(idNorm);
//                normativa.setAfectadas(normativaOld.getAfectadas());
//                normativa.setAfectantes(normativaOld.getAfectantes());
//                normativa.setProcedimientos(normativaOld.getProcedimientos());
//                normativa.setId(idNorm);
//          }
//
//          //Campos por idioma
//          List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
//          for (String idioma : idiomas) {
//                TraduccionNormativa traNorm = normativaOld != null ? (TraduccionNormativa)normativaOld.getTraduccion(idioma) : null;
//                                 
//                if (traNorm != null) {
//                      /*
//                      //Paso de externa a local
//                      if (TraduccionNormativaExterna.class.isInstance(traNorm) && normativaLocal) {                             
//                           //TODO: borrar traducción externa?
//                          
//                           traNorm = null;
//                      }
//                      //Paso de local a externa
//                      else
//                      if (TraduccionNormativa.class.isInstance(traNorm) && !normativaLocal) {
//                           //TODO: borrar traduccion local?
//                          
//                           traNorm = null;
//                      }
//                      //Se queda igual
//                      else
//                      */
//                           normativa.setTraduccion(idioma, traNorm);
//                }
//                else
//                if (traNorm == null) {
//                      if (normativaLocal)
//                           traNorm = new TraduccionNormativa();
//                      else
//                           traNorm = new TraduccionNormativaExterna();
//
//                      normativa.setTraduccion(idioma, traNorm);
//                }
//
//                traNorm.setTitulo(request.getParameter("item_titol_" + idioma));
//                traNorm.setEnlace(request.getParameter("item_enllas_" + idioma));
//                traNorm.setApartado(request.getParameter("item_apartat_" + idioma));
//                if (request.getParameter("item_pagina_inicial_" + idioma) != null && !"".equals(request.getParameter("item_pagina_inicial_" + idioma)))
//                      traNorm.setPaginaInicial(Integer.parseInt(request.getParameter("item_pagina_inicial_" + idioma)));
//
//                if (request.getParameter("item_pagina_final_" + idioma) != null && !"".equals(request.getParameter("item_pagina_final_" + idioma)))
//                      traNorm.setPaginaFinal(Integer.parseInt(request.getParameter("item_pagina_final_" + idioma)));                        
//
//                traNorm.setObservaciones(request.getParameter("item_des_curta_" + idioma));    
//
//                //Responsable sólo en normativa externa
//                if (!normativaLocal) {                              
//                      ((TraduccionNormativaExterna)traNorm).setResponsable(request.getParameter("item_responsable_" + idioma));
//                }
//
//          }
//
//          //Los demás campos
//          if (request.getParameter("item_numero") != null && !"".equals(request.getParameter("item_numero")))
//                normativa.setNumero(Long.parseLong(request.getParameter("item_numero")));
//
//          normativa.setLey(request.getParameter("item_llei"));
//
//          if (request.getParameter("item_registre") != null && !"".equals(request.getParameter("item_registre")))
//                normativa.setRegistro(Long.parseLong(request.getParameter("item_registre")));
//
//          if (request.getParameter("item_data_butlleti") != null && !"".equals(request.getParameter("item_data_butlleti"))) {
//                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                normativa.setFechaBoletin(dateFormat.parse(request.getParameter("item_data_butlleti")));
//          }
//
//          if (request.getParameter("item_data") != null && !"".equals(request.getParameter("item_data"))) {
//                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                normativa.setFecha(dateFormat.parse(request.getParameter("item_data")));
//          }       
//
//          if (request.getParameter("item_tipus") != null && !"".equals(request.getParameter("item_tipus"))) {
//                Tipo tipo = DelegateUtil.getTipoNormativaDelegate().obtenerTipoNormativa(new Long(request.getParameter("item_tipus")));
//                normativa.setTipo(tipo);
//          }
//
//          if (request.getParameter("item_validacio") != null && !"".equals(request.getParameter("item_validacio")))
//                normativa.setValidacion(new Integer(request.getParameter("item_validacio")));
//
//
//          //Boletín
//          if (request.getParameter("item_butlleti_id") != null && !"".equals(request.getParameter("item_butlleti_id"))) {
//                Boletin boletin = DelegateUtil.getBoletinDelegate().obtenerBoletin(Long.parseLong(request.getParameter("item_butlleti_id")));
//                normativa.setBoletin(boletin);
//          }
//
//          if (normativaLocal) {
//                /*
//                //Era externa y la pasamos a local, eliminamos el registro anterior
//                if (edicion && NormativaExterna.class.isInstance(normativaOld))
//                      normativaDelegate.borrarNormativa(normativaOld.getId());
//                */
//                normativaDelegate.grabarNormativaLocal((NormativaLocal)normativa, ua.getId());
//          } else {
//                /*
//                //Era externa y la pasamos a local, eliminamos el registro anterior
//                if (edicion && NormativaLocal.class.isInstance(normativaOld))
//                      normativaDelegate.borrarNormativa(normativaOld.getId());             
//                */
//                normativaDelegate.grabarNormativaExterna((NormativaExterna)normativa);
//          }
//
//          String ok = messageSource.getMessage("normativa.guardat.correcte", null, request.getLocale());
//          result = new IdNomTransient(normativa.getId(), ok);
//         
//         
//      } catch (DelegateException dEx) {
//                if (dEx.getCause() instanceof SecurityException) {
//                      String error = messageSource.getMessage("error.permisos", null, request.getLocale());
//                      result = new IdNomTransient(-1l, error);
//                } else {
//                      String error = messageSource.getMessage("error.altres", null, request.getLocale());
//                      result = new IdNomTransient(-2l, error);
//                      dEx.printStackTrace();
//                }                      
//      } catch (ParseException e) {
//                String error = messageSource.getMessage("error.altres", null, request.getLocale());
//                result = new IdNomTransient(-2l, error);            
//                e.printStackTrace();
//          }
//                                
//         
//          return result;
//    }
//*/
}
