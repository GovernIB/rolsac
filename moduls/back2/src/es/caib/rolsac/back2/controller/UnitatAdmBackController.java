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

import sun.misc.Perf.GetPerfAction;

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
            //if (ua == null || nom == null || "".equals(nom)) {
            if (nom == null || "".equals(nom)) {
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
                	 * Seteamos con valores anteriores
                	 */
                	unitatAdministrativa.setUnidadesMaterias(unitatAdministrativaOld.getUnidadesMaterias());
                	unitatAdministrativa.setEdificios(unitatAdministrativaOld.getEdificios());
                	unitatAdministrativa.setFichasUA(unitatAdministrativaOld.getFichasUA());
                }
            	// Idiomas
                TraduccionUA tUA;
				IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
				List<String> langs = idiomaDelegate.listarLenguajes();
				for (String lang: langs) {
					tUA = new TraduccionUA();
					tUA.setNombre(request.getParameter("item_nom_"+  lang ));
					tUA.setPresentacion(request.getParameter("item_presentacio_" + lang));
					tUA.setAbreviatura(request.getParameter("item_abreviatura_" + lang));
					tUA.setUrl(request.getParameter("item_url_" + lang));
					
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
				    String error = messageSource.getMessage("unitatadm.formulari.error.espaiTerritorial_incorrecte", null, request.getLocale());
					result = new IdNomTransient(-3l, error);
				}
                
				
				try {
					Long unitatAdmPareId = Long.parseLong(request.getParameter("item_pare_id"));
					UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
					UnidadAdministrativa pare = unidadAdministrativaDelegate.obtenerUnidadAdministrativa(unitatAdmPareId);
					unitatAdministrativa.setPadre(pare);
				} catch (NumberFormatException e) {
					String error = messageSource.getMessage("unitatadm.formulari.error.unitatAdministrativaPare_incorrecte", null, request.getLocale());
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
					String error = messageSource.getMessage("unitatadm.formulari.error.tractament_incorrecte", null, request.getLocale());
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
				if (request.getParameter("item_nivell_1")!=null && !"".equals(request.getParameter("item_nivell_1"))){
					unitatAdministrativa.setNumfoto1(Integer.parseInt(request.getParameter("item_nivell_1")));
				}
				if (request.getParameter("item_nivell_2")!=null && !"".equals(request.getParameter("item_nivell_2"))){
					unitatAdministrativa.setNumfoto2(Integer.parseInt(request.getParameter("item_nivell_2")));
				}
				if (request.getParameter("item_nivell_3")!=null && !"".equals(request.getParameter("item_nivell_3"))){
					unitatAdministrativa.setNumfoto3(Integer.parseInt(request.getParameter("item_nivell_3")));
				}
				if (request.getParameter("item_nivell_4")!=null && !"".equals(request.getParameter("item_nivell_4"))){
					unitatAdministrativa.setNumfoto4(Integer.parseInt(request.getParameter("item_nivell_4")));
				}

				//Materias asociadas
               /*
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
				
				crearUnitatAdministrativa(unitatAdministrativaDelegate,	unitatAdministrativa);  
				
				// Sobre escrivim la unitat administrativa de la amollapa
				session.setAttribute("unidadAdministrativa", unitatAdministrativa);
				
                String ok = messageSource.getMessage("unitatadm.guardat.correcte", null, request.getLocale());
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
	 * @param unitatAdministrativaDelegate
	 * @param unitatAdministrativa
	 * @throws DelegateException
	 */
	private void crearUnitatAdministrativa(UnidadAdministrativaDelegate unitatAdministrativaDelegate,
			UnidadAdministrativa unitatAdministrativa) throws DelegateException {
		
		
		if (unitatAdministrativa.getId() != null) {
			unitatAdministrativaDelegate.actualizarUnidadAdministrativa(unitatAdministrativa,unitatAdministrativa.getPadre().getId());
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
	
    /**
     * Método que comprueba si hay que mostrar los logos
     *
     * @return boolean
     */
    private boolean mostrarLogosUA(){
       
        String value = System.getProperty("es.caib.rolsac.logos");
        return !((value == null) || value.equals("N"));
    }
    
	@RequestMapping(value = "/esborrar.htm", method = POST)
    public @ResponseBody IdNomTransient esborrarUniAdm(HttpServletRequest request) {
	    
	    IdNomTransient resultatStatus = new IdNomTransient(); 
	    
	    try {
            
            Long idUA = new Long(request.getParameter("id"));
            
            UnidadAdministrativaDelegate unidadAdministrativaDelegate = DelegateUtil.getUADelegate();
            unidadAdministrativaDelegate.eliminarUaSinRelaciones(idUA);             
            
            resultatStatus.setId(1l);
            resultatStatus.setNom("correcte");
            
        } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                resultatStatus.setId(-1l);
            } else {
                resultatStatus.setId(-2l);
                dEx.printStackTrace();
            }
        }
	    
	    return resultatStatus;
	}
    
	
	/**
     *  Descripción: Action que se ejecuta al eliminar una UA. En esta Accion se controla que la UA no tenga Microsites relacionados,
     *  se comprueba que la UA no tenga elmentos relacionados, y finalmente si no tiene elementos se elimina la UA.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return actionforward
     */
	/*
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws SecurityException,Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        UnidadAdministrativaDelegate unidadDelegate = DelegateUtil.getUADelegate();

        Long id = (Long) dForm.get("id");
        
        if (!hayMicrositesUA(id)) {
        
        	UnidadAdministrativa ua = unidadDelegate.consultarUnidadAdministrativa(id);
        	
        	//Validamos que se pueda eliminar la UA. Se podra elimnar si no tiene elementos relacionados. Ha excepción de usuarios y edificios.
        	ActionErrors errores =  validarEliminacionUA(ua,unidadDelegate);
        	if(!errores.isEmpty()){
        		saveErrors(request, errores);
        		request.setAttribute("idUA", id);
        		log.error("No se puede elimar la UA. La UA tiene elementos relacionados ");
        		return dispatchMethod(mapping, form, request, response, "seleccionar");
        		//return mapping.findForward("success");
	        }
	
        	unidadDelegate.eliminarUaSinRelaciones(id);

        	dForm.reset(mapping, request);
	        request.setAttribute("alert", "confirmacion.baja");
	        log.debug("Eliminada Unidad Administrativa: " + id);

        } else {
	        request.setAttribute("alert", "microsites.ua.asociados");
	        log.debug("No se ha eliminado Unidad Administrativa: " + id + " . Causa: Tiene asociado algún microsite");
        	
        }
        return mapping.findForward("cancel");
    }*/
	
}
