package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.SiaJobDTO;
import org.ibit.rol.sac.model.dto.SiaPendienteDTO;
import org.ibit.rol.sac.model.dto.SiaUADTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.util.FiltroSia;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.IndexacionJob;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.SiaPendienteJob;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/sia/")
public class TMSiaController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMIndexController.class);
	
	
    @RequestMapping(value = "/siaua.do", method = GET)
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMSiaUA.jsp");

        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmSiaUA.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    
    @RequestMapping(value = "/llistatSIAUA.do")
    public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {

    	List<SiaUADTO> llistaSiaPendienteDTO = new ArrayList<SiaUADTO>();
        final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        	// Información de paginación
    		String pagPag 		= request.getParameter("pagPag");		
    		String pagRes 		= request.getParameter("pagRes");
    		String ordenCampo  	= request.getParameter("ordreCamp");
    		String ordenAsc 	= request.getParameter("ordreTipus");
    		//String idioma 		= request.getLocale().getLanguage().toLowerCase();
    		
    		if (pagPag == null) 	{ pagPag = String.valueOf(0); } 
    		if (pagRes == null) 	{ pagRes = String.valueOf(10); }
    		if (ordenCampo  == null){ ordenCampo  = "id"; }
    		if (ordenAsc  == null)	{ ordenAsc  = "ASC"; }
    		
    		ResultadoBusqueda resultado = DelegateUtil.getSiaPendienteProcesoDelegate().getSiaUAs(Integer.parseInt(pagPag), Integer.parseInt(pagRes), ordenCampo, ordenAsc);
            
            resultats.put("total", resultado.getTotalResultados());
            llistaSiaPendienteDTO.addAll(convertSiaUAToSiaUADTO(resultado));
            resultats.put("nodes", llistaSiaPendienteDTO);

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                log.error("Error: " + dEx.getMessage());
            }
            if (dEx.getCause() == null) {
            	resultats.put("error", dEx.getMessage());
            } else {
            	resultats.put("error", dEx.getCause().getMessage());
            }
        }

         
        return resultats;
    }


    /**
     * Convierte SiaUA en SiaUADTOs.
     * 
     * @param resultadoBusqueda
     * @return
     * @throws DelegateException 
     */
    private List<SiaUADTO> convertSiaUAToSiaUADTO(final ResultadoBusqueda resultadoBusqueda) throws DelegateException {

        final List<SiaUADTO> siaUAsDTo = new ArrayList<SiaUADTO>();
        
        for (SiaUA siaUA : (List<SiaUA>)resultadoBusqueda.getListaResultados()) {
        	String uaTexto;
        	TraduccionUA trad = (TraduccionUA) siaUA.getUnidadAdministrativa().getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
        	
        	if (trad == null) {
        		uaTexto = siaUA.getUnidadAdministrativa().getId() + " - " + siaUA.getUnidadAdministrativa().getAbreviaturaUnidadAdministrativa();
        	} else {
        		uaTexto = siaUA.getUnidadAdministrativa().getId() + " - " + trad.getNombre();
        	}
        	siaUAsDTo.add(new SiaUADTO(
        			siaUA.getId(),
        			uaTexto,
        			siaUA.getUsuario(),
        			siaUA.getContrasenya()
        	         )
            );
        }
        return siaUAsDTo;
    }

    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
		Map<String, Object> resultats = new HashMap<String, Object>();
		try {
			Long id = new Long(request.getParameter("id"));
			SiaUA siaUA = DelegateUtil.getSiaPendienteProcesoDelegate().obtenerSiaUA(id);    
			
			resultats.put("item_id", siaUA.getId());
			resultats.put("item_usuario", siaUA.getUsuario());
			resultats.put("item_contrasenya", siaUA.getContrasenya());
			resultats.put("item_ua_id", siaUA.getUnidadAdministrativa().getId());
			resultats.put("item_ua", siaUA.getUnidadAdministrativa().getAbreviaturaUnidadAdministrativa());

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
    
    @RequestMapping(value = "/grabarSiaUA.do", method = POST)
    public @ResponseBody Map<String, Object> grabarSiaUA(HttpServletRequest request) {

    	 final Map<String, Object> resultats = new HashMap<String, Object>();     
    	 //Boolean result = false;
         
         try {
        	 	SiaUA siaUA = new SiaUA();
        	 	if (!request.getParameter("item_id").isEmpty()) {
        	 		Long id = Long.valueOf(request.getParameter("item_id"));
        	 		siaUA.setId(id);
        	 	}
        	 	
        	 	Long uaId = Long.parseLong(request.getParameter("item_ua_id"));
    			UnidadAdministrativaDelegate udelegate = DelegateUtil.getUADelegate();
    			UnidadAdministrativa ua = udelegate.obtenerUnidadAdministrativa(uaId);
    			siaUA.setUnidadAdministrativa(ua);
    			
        	 	String usuario = request.getParameter("item_usuario");
        	 	siaUA.setUsuario(usuario);
        	 	String contraseny = request.getParameter("item_contrasenya");
				siaUA.setContrasenya(contraseny);
				
				DelegateUtil.getSiaPendienteProcesoDelegate().grabarSiaUA(siaUA);            
         } catch (DelegateException dEx) {
        	if (dEx.isSecurityException()) {
                 log.error("Permisos insuficients: " + dEx.getMessage());
             } else {
                 log.error("Error: " + dEx.getMessage());
             }
            
        	 String mensajeError = ExceptionUtils.getCause(dEx).getMessage();
             if (mensajeError.contains("javax.ejb.EJBException:")) {
            	 int posicion = mensajeError.indexOf("javax.ejb.EJBException:") + 23; 
            	 mensajeError = mensajeError.substring(posicion);
             }
             resultats.put("error", mensajeError);
         } catch (Exception dEx) {
             if (dEx.getCause() == null) {
             	resultats.put("error", dEx.getMessage());
             } else {
             	resultats.put("error", dEx.getCause().getMessage());
             }
         }

         return resultats;
    }
  
    
    @RequestMapping(value = "/borrarSiaUA.do", method = POST)
    public @ResponseBody IdNomDTO borrarSiaUA(HttpServletRequest request) {

    	 IdNomDTO resultatStatus = new IdNomDTO();
 
         try {
        	 	Long id = Long.valueOf(request.getParameter("id"));
        	 	DelegateUtil.getSiaPendienteProcesoDelegate().borrarSiaUA(id);
        	 	
        	 	resultatStatus.setId(1l);
        		resultatStatus.setNom("correcte");
         } catch (DelegateException dEx) {
        	 if (dEx.isSecurityException()) {
  				resultatStatus.setId(-1l);
  			} else {
  				resultatStatus.setId(-2l);
  				logException(log, dEx);
  			}
         }

         return resultatStatus;
    }
    

    @RequestMapping(value = "/indexarTodo.do")
    public @ResponseBody Map<String, Object> indexarTodo(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
    	final SiaPendienteProcesoDelegate siaPendienteProcesoDel = DelegateUtil.getSiaPendienteProcesoDelegate(); 
        try {
        	//Paso 1. Comprobar si hay algo creado.
          	if ( siaPendienteProcesoDel.checkJobsActivos()) {
          		resultats.put("error", "Hi ha tasques en execucio");
          	} else {
          		
          		//Paso 3. Si todo correcto, ejecutar job 
          		ejecutarJob("todo");
          	}
        } catch (SchedulerException exception) {
        	log.error("Error: " + exception.getMessage());
            resultats.put("error", "No es pot generar el job");
        } catch (Exception dEx) {
           log.error("Error: " + dEx.getMessage());
            if (dEx.getCause() == null) {
            	resultats.put("error", dEx.getMessage());
            } else {
            	resultats.put("error", dEx.getCause().getMessage());
            }
        }
        return resultats; 
    }
    
   
    /**
     * Se ejecuta y crea un job con un tipo de indexacion.
     * 
     * @param tipoIndexacion
     * @throws SchedulerException 
     */
    private void ejecutarJob(final String tipoIndexacion) throws SchedulerException {
      	//Se ha simplificado, se verán los últimos jobs ejecutados y, si alguno de ellos está sin fecha fin
      	//  se da por hecho que se está ejecutando.
      	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
      	scheduler.start(); 
      	JobDetail jobDetail = new JobDetail(SiaUtils.SIA_JOB_QUARTZ_NAME, Scheduler.DEFAULT_GROUP, IndexacionJob.class);
      	Trigger trigger = TriggerUtils.makeImmediateTrigger(0, 0); 
      	scheduler.getContext().put("tipoindexacion", tipoIndexacion);
        trigger.setName("FireOnceNowTrigger");  
      	scheduler.scheduleJob(jobDetail, trigger);
    }
    
    
   
    
    @RequestMapping(value = "/sia.do", method = GET)
    public String pantallaSIA(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMSia.jsp");

        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmSia.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistatSIAJob.do")
    public @ResponseBody  Map<String, Object> llistatSIAJob(HttpServletRequest request) {

        List<SiaJobDTO> llistaSiaJobDTO = new ArrayList<SiaJobDTO>();
        final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        			
    		FiltroSia filtro = new FiltroSia();
    		
    		String pagPag 		= request.getParameter("pagPag");		
    		String pagRes 		= request.getParameter("pagRes");
    		String ordenCampo  	= request.getParameter("ordreCamp");
    		String ordenAsc 	= request.getParameter("ordreTipus");
    		String tipo			= request.getParameter("tipo");
    		
    		if (pagPag == null) 	{ pagPag = String.valueOf(0); } 
    		if (pagRes == null) 	{ pagRes = String.valueOf(10); }
    		if (ordenCampo  == null){ ordenCampo  = "id"; }
    		if (ordenAsc  == null)	{ ordenAsc  = "ASC"; }
    		if (tipo	  == null)  { tipo = "TOT"; }
    		
    		filtro.setTipo(tipo); 
    		filtro.setNumElementos(Integer.valueOf(pagRes));
    		filtro.setPagina(Integer.valueOf(pagPag));
    		filtro.setOrdenCampo(ordenCampo);
    		filtro.setOrdenAsc(ordenAsc);
    		
    		ResultadoBusqueda resultado = DelegateUtil.getSiaPendienteProcesoDelegate().getSiaProceso(filtro);
            
            resultats.put("total", resultado.getTotalResultados());
            llistaSiaJobDTO.addAll(convertirSIAToJobDTO(resultado.getListaResultados()));
            resultats.put("nodes", llistaSiaJobDTO);

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                log.error("Error: " + dEx.getMessage());
            }
            if (dEx.getCause() == null) {
            	resultats.put("error", dEx.getMessage());
            } else {
            	resultats.put("error", dEx.getCause().getMessage());
            }
        }

        resultats.put("nodes", llistaSiaJobDTO);

        return resultats;
    }

    
    
   
	@RequestMapping(value = "/llistatSIA.do")
    public @ResponseBody Map<String, Object> llistatSIA(HttpServletRequest request) {

        List<SiaPendienteDTO> llistaSiaDTO = new ArrayList<SiaPendienteDTO>();
        final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        	//FiltroSIA.		
    		FiltroSia filtro = new FiltroSia();
    		filtro.setNumElementos(20);
    		filtro.setEstado(0);
    		
    		//Obtenemos params.
    		String pagPag 		= request.getParameter("pagPag");		
    		String pagRes 		= request.getParameter("pagRes");
    		String ordenCampo  	= request.getParameter("ordreCamp");
    		String ordenAsc 	= request.getParameter("ordreTipus");
    		String estado 		= request.getParameter("estado");
    		
    		//Valores por defecto.
    		if (pagPag == null) 	{ pagPag = String.valueOf(0); } 
    		if (pagRes == null) 	{ pagRes = String.valueOf(10); }
    		if (ordenCampo  == null){ ordenCampo  = "id"; }
    		if (ordenAsc  == null)	{ ordenAsc  = "ASC"; }
    		if (estado == null) 	{ estado = "0"; }
    		
    		//Seteamos filtro valores
    		filtro.setTipo("TOT"); 
    		filtro.setNumElementos(Integer.valueOf(pagRes));
    		filtro.setPagina(Integer.valueOf(pagPag));
    		filtro.setOrdenCampo(ordenCampo);
    		filtro.setOrdenAsc(ordenAsc);
    		filtro.setEstado(Integer.valueOf(estado));
    		
    		ResultadoBusqueda resultado = DelegateUtil.getSiaPendienteProcesoDelegate().getSiaPendientes(filtro);
            
            resultats.put("total", resultado.getTotalResultados());
            llistaSiaDTO.addAll(convertirSIAToDTO(resultado.getListaResultados()));
            resultats.put("nodes", llistaSiaDTO);

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                log.error("Error: " + dEx.getMessage());
            }
            if (dEx.getCause() == null) {
            	resultats.put("error", dEx.getMessage());
            } else {
            	resultats.put("error", dEx.getCause().getMessage());
            }
        }

        resultats.put("nodes", llistaSiaDTO);

        return resultats;
    }
	
	/**
	 * Convertir SiaPendiente to SiaPendienteDTO
	 * @param siaPendientes
	 * @return
	 */
	private List<SiaPendienteDTO> convertirSIAToDTO(List<?> siaPendientes) {
		final List<SiaPendienteDTO> siaPendientesDTO = new ArrayList<SiaPendienteDTO>();
	        
	    for (Object oSia : siaPendientes) {
	       	final SiaPendiente sia = (SiaPendiente) oSia;
	       	siaPendientesDTO.add(new SiaPendienteDTO(
	       						sia.getId(),
	       						sia.getTipo(),
	       						sia.getIdElemento(),
	       						sia.getEstado(),
	       						sia.getFecAlta(),
	       						sia.getFecIdx(),
	       						sia.getMensaje())
	            );
	    }
	    return siaPendientesDTO;
	}
	
	/**
	 * Convertir SiaJob to SiaJobDTO
	 * @param siaJobs
	 * @return
	 */
	private List<SiaJobDTO> convertirSIAToJobDTO(List<?> siaJobs) {
		
		final List<SiaJobDTO> siaJobsDTO = new ArrayList<SiaJobDTO>();
		//StringBuffer bufferDesc = new StringBuffer();
		//StringBuffer bufferDescBreve = new StringBuffer();
		
        for (Object oSiaJob : siaJobs) {
        	
        	try {
        		final SiaJob siaJob = (SiaJob) oSiaJob;
        		
        		//bufferDesc = SiaUtils.obtenerContenidoClob(siaJob.getDescripcion());
        		//bufferDescBreve = SiaUtils.obtenerContenidoClob(siaJob.getDescBreve());        		
        		siaJobsDTO.add(new SiaJobDTO(
            			siaJob.getId(),
            			siaJob.getFechaIni(),
            			siaJob.getFechaFin(),
            		//	bufferDescBreve.toString(),
            		//	bufferDesc.toString(),
            			StringEscapeUtils.escapeJavaScript(siaJob.getDescBreve()),
            			StringEscapeUtils.escapeJavaScript(siaJob.getDescripcion()),
            			siaJob.getEstado(),
            			siaJob.getTipo())
                );
        		
		/*	} catch (IOException e) {
				log.error("Error: " + e.getMessage());
			} catch (SQLException e) {
				log.error("Error: " + e.getMessage());
			*/
        	}catch (Exception e) {
				log.error("Error: " + e.getMessage());
			}
        	
        	
        }
        return siaJobsDTO;
	}

	
	
	 @RequestMapping(value = "/enviarTodo.do")
	    public @ResponseBody Map<String, Object> enviarTodo(HttpServletRequest request) {

	    	final Map<String, Object> resultats = new HashMap<String, Object>();
	        try {
	        	//Paso 1. Comprobar si hay algo creado.
	          	if ( DelegateUtil.getSiaPendienteProcesoDelegate().checkJobsActivos()) {
	          		resultats.put("error", "Hi ha tasques en execucio");
	          	} else {
	          		//Paso 2. Si todo correcto, ejecutar job 
	          		ejecutarJobSIA("todo");
	          	}
	        } catch (SchedulerException exception) {
	        	log.error("Error: " + exception.getMessage());
	            resultats.put("error", "No es pot generar el job");
	        } catch (Exception dEx) {
	           log.error("Error: " + dEx.getMessage());
	            if (dEx.getCause() == null) {
	            	resultats.put("error", dEx.getMessage());
	            } else {
	            	resultats.put("error", dEx.getCause().getMessage());
	            }
	        }
	        return resultats; 
	    }
	 
	 /**
	  * Método que ejecuta a la fuerza el revisar los procedimientos que cambian estado SIA por tiempo.
	  * @param request
	  * @return
	  */
	 @RequestMapping(value = "/procTiempoSIA.do")
	    public @ResponseBody Map<String, Object> revisarTiempoSIA(HttpServletRequest request) {

	    	final Map<String, Object> resultats = new HashMap<String, Object>();
	        try {
	        	//Paso 1. Comprobar si hay algo creado.
	          	if ( DelegateUtil.getSiaPendienteProcesoDelegate().checkJobsActivos()) {
	          		resultats.put("error", "Hi ha tasques en execucio");
	          	} else {
	          		//Paso 2. Si todo correcto, ejecutar job 
	          		ejecutarJobSIA("tiempo");
	          	}
	        } catch (SchedulerException exception) {
	        	log.error("Error: " + exception.getMessage());
	            resultats.put("error", "No es pot generar el job");
	        } catch (Exception dEx) {
	           log.error("Error: " + dEx.getMessage());
	            if (dEx.getCause() == null) {
	            	resultats.put("error", dEx.getMessage());
	            } else {
	            	resultats.put("error", dEx.getCause().getMessage());
	            }
	        }
	        return resultats; 
	    }
	 
	 /**
     * Se ejecuta y crea un job con un tipo de indexacion.
     * 
     * @param tipoIndexacion
     * @throws SchedulerException 
     */
    private void ejecutarJobSIA(final String tipoEnvio) throws SchedulerException {
      	//Se ha simplificado, se verán los últimos jobs ejecutados y, si alguno de ellos está sin fecha fin
      	//  se da por hecho que se está ejecutando.
      	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
      	scheduler.start(); 
      	JobDetail jobDetail = new JobDetail("SiaPendienteJob", Scheduler.DEFAULT_GROUP, SiaPendienteJob.class);
      	Trigger trigger = TriggerUtils.makeImmediateTrigger(0, 0); 
      	scheduler.getContext().put("tipoEnvio", tipoEnvio);
        trigger.setName("FireOnceNowTrigger");  
      	scheduler.scheduleJob(jobDetail, trigger);
    }
	    
    @RequestMapping(value = "/cerrarJobsSIA.do")
    public @ResponseBody Map<String, Object> cerrarJobsSIA(HttpServletRequest request) {
    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
	    	Boolean retorno = DelegateUtil.getSiaPendienteProcesoDelegate().cerrarJobs();
	    	if (retorno) {
	    		log.debug("Todo correcto, se ha cerrado los jobs.");
	    	} else  {
	    		log.debug("Mal, no es pot cerrar els jobs.");
	    		resultats.put("error", "No es pot cerrar los jobs.");
	    	}
        } catch (Exception exception) {
            log.error("Error cerrando jobs." + exception.getMessage(), exception);
            resultats.put("error", exception.getCause().getMessage());
        }
    	
        return resultats;
    }
    
    @RequestMapping(value = "/enviarPendientes.do")
    public @ResponseBody Map<String, Object> enviarPendientes(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();     

        try {
        	//Paso 1. Comprobar si hay algo creado.
        	if (DelegateUtil.getSiaPendienteProcesoDelegate().checkJobsActivos()) {
        		resultats.put("error", "Hi ha tasques en execucio");
        	} else {
        		//Paso 2. Si todo correcto, ejecutar job 
        		ejecutarJobSIA("pendientes");
        	}
        	
        } catch (SchedulerException exception) {
        	log.error("Error: " + exception.getMessage());
            resultats.put("error", "No es pot generar el job");
        } catch (Exception dEx) {
           log.error("Error: " + dEx.getMessage());
            if (dEx.getCause() == null) {
            	resultats.put("error", dEx.getMessage());
            } else {
            	resultats.put("error", dEx.getCause().getMessage());
            }
        }

        return resultats;
    }
    
    
    @RequestMapping(value = "/getinfosia.do")
    public @ResponseBody Map<String, Object> getInfoSIA(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();     
        try {
        	//Paso 1. Comprobar si hay algo creado.
        	if (DelegateUtil.getSiaPendienteProcesoDelegate().checkJobsActivos()) {
        		resultats.put("error", "Hi ha tasques en execucio");
        	} else {
        		//Paso 2. Si todo correcto, ejecutar job 
        		ejecutarJobSIA("info");
        	}
        	
        } catch (SchedulerException exception) {
        	log.error("Error: " + exception.getMessage());
            resultats.put("error", "No es pot generar el job");
        } catch (Exception dEx) {
           log.error("Error: " + dEx.getMessage());
            if (dEx.getCause() == null) {
            	resultats.put("error", dEx.getMessage());
            } else {
            	resultats.put("error", dEx.getCause().getMessage());
            }
        }

        return resultats;
    }
}
