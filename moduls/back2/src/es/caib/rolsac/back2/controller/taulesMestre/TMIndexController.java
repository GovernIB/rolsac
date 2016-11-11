package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.dto.SiaJobDTO;
import org.ibit.rol.sac.model.dto.SiaPendienteDTO;
import org.ibit.rol.sac.model.dto.SolrPendienteDTO;
import org.ibit.rol.sac.model.dto.SolrPendienteJobDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
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

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.IndexacionJob;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/index/")
public class TMIndexController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMIndexController.class);
	
	
    @RequestMapping(value = "/index.do", method = GET)
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMIndex.jsp");

        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmIndex.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/cerrarJobs.do")
    public @ResponseBody Map<String, Object> cerrarJobs(HttpServletRequest request) {
    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
	    	Boolean retorno = DelegateUtil.getSolrPendienteDelegate().cerrarJobs();
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
    
    
    @RequestMapping(value = "/llistat.do")
    public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {

    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
        List<SolrPendienteDTO> llistaSolrPendienteDTO = new ArrayList<SolrPendienteDTO>();
        final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        	// Información de paginación
    		String pagPag = request.getParameter("pagPag");		
    		String pagRes = request.getParameter("pagRes");
    		
    		if (pagPag == null) pagPag = String.valueOf(0); 
    		if (pagRes == null) pagRes = String.valueOf(10);
    				
    		resultadoBusqueda = DelegateUtil.getSolrPendienteDelegate().getPendientes(Integer.parseInt(pagPag), Integer.parseInt(pagRes));
            llistaSolrPendienteDTO.addAll(convertSolrPendienteToSolrPendienteDTO((List<SolrPendiente>) resultadoBusqueda.getListaResultados()));

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

        resultats.put("total", resultadoBusqueda.getTotalResultados());
        resultats.put("nodes", llistaSolrPendienteDTO);

        return resultats;
    }


    private List<SolrPendienteDTO> convertSolrPendienteToSolrPendienteDTO(List<SolrPendiente> solrPendientes) {

        List<SolrPendienteDTO> solrPendienteDTO = new ArrayList<SolrPendienteDTO>();
        
        for (SolrPendiente solrPendiente : solrPendientes) {
        	solrPendienteDTO.add(new SolrPendienteDTO(
        			solrPendiente.getId(),
        			solrPendiente.getTipo(),
        			solrPendiente.getIdElemento(),
        			solrPendiente.getAccion(),
        			solrPendiente.getFechaCreacion(),
        	        solrPendiente.getFechaIndexacion(),
        	        solrPendiente.getResultado(),
        	        solrPendiente.getMensajeError()
        	         )
            );
        }
        return solrPendienteDTO;
                
    }


    @RequestMapping(value = "/indexarTodo.do")
    public @ResponseBody Map<String, Object> indexarTodo(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
        	//Paso 1. Comprobar si hay algo creado.
          	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
          		resultats.put("error", "Hi ha tasques en execució");
          	} else {
          		//Paso 2. Si todo correcto, ejecutar job 
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
    
    @RequestMapping(value = "/llistatJobs.do")
    public @ResponseBody Map<String, Object> listJobs(HttpServletRequest request) {

    	List<SolrPendienteJob> resultadoBusqueda = null;
    	final List<SolrPendienteJobDTO> llistaSolrPendienteJobDTO = new ArrayList<SolrPendienteJobDTO>();
    	final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        	resultadoBusqueda = DelegateUtil.getSolrPendienteDelegate().getListJobs(5);
            llistaSolrPendienteJobDTO.addAll(convertSolrPendienteJobToSolrPendienteJobDTO((List<SolrPendienteJob>) resultadoBusqueda));

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

        resultats.put("nodes", llistaSolrPendienteJobDTO);
        return resultats;
    }
    
    /***
     * Convierte de solrpendientejob a solrpendientejobDTO.
     * @param solrPendientes
     * @return
     */
    private List<SolrPendienteJobDTO> convertSolrPendienteJobToSolrPendienteJobDTO(List<SolrPendienteJob> solrPendientes) {

        List<SolrPendienteJobDTO> solrPendienteDTO = new ArrayList<SolrPendienteJobDTO>();
        
        for (SolrPendienteJob solrPendiente : solrPendientes) {
        	solrPendienteDTO.add(new SolrPendienteJobDTO(
        			solrPendiente.getId(),
        			solrPendiente.getFechaIni(),
        			solrPendiente.getFechaFin(),
        			solrPendiente.getFechaFicha(),
        			solrPendiente.getFechaProcedimiento(),
        			solrPendiente.getFechaNormativa(),
        			solrPendiente.getFechaTramite(),
        			solrPendiente.getFechaUnidadAdministrativa(),
        			solrPendiente.getTotalFicha(),
        			solrPendiente.getTotalFichaDoc(),
        			solrPendiente.getTotalProcedimiento(),
        			solrPendiente.getTotalProcedimientoDoc(),
        			solrPendiente.getTotalNormativa(),
        			solrPendiente.getTotalNormativaDoc(),
        			solrPendiente.getTotalTramite(),
        			solrPendiente.getTotalUnidadAdministrativa()
        	         )
            );
        }
        return solrPendienteDTO;
                
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
      	JobDetail jobDetail = new JobDetail("IndexacionJob", Scheduler.DEFAULT_GROUP, IndexacionJob.class);
      	Trigger trigger = TriggerUtils.makeImmediateTrigger(0, 0); 
      	scheduler.getContext().put("tipoindexacion", tipoIndexacion);
        trigger.setName("FireOnceNowTrigger");  
      	scheduler.scheduleJob(jobDetail, trigger);
    }
    
    @RequestMapping(value = "/indexarTodoFicha.do")
    public @ResponseBody Map<String, Object> indexarTodoFicha(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
        	//Paso 1. Comprobar si hay algo creado.
          	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
          		resultats.put("error", "Hi ha tasques en execució");
          	} else {
          		//Paso 2. Si todo correcto, ejecutar job 
          		ejecutarJob("ficha");
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
    
    @RequestMapping(value = "/indexarTodoProcedimiento.do")
    public @ResponseBody Map<String, Object> indexarTodoProcedimiento(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
         try {
        	//Paso 1. Comprobar si hay algo creado.
          	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
          		resultats.put("error", "Hi ha tasques en execució");
          	} else {
          		//Paso 2. Si todo correcto, ejecutar job 
          		ejecutarJob("procedimiento");
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
    
    @RequestMapping(value = "/indexarTodoNormativa.do")
    public @ResponseBody Map<String, Object> indexarTodoNormativa(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
        	//Paso 1. Comprobar si hay algo creado.
         	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
         		resultats.put("error", "Hi ha tasques en execució");
         	} else {
         		//Paso 2. Si todo correcto, ejecutar job 
         		ejecutarJob("normativa");
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
    
    @RequestMapping(value = "/indexarTodoTramite.do")
    public @ResponseBody Map<String, Object> indexarTodoTramite(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
         try {
        	//Paso 1. Comprobar si hay algo creado.
         	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
         		resultats.put("error", "Hi ha tasques en execució");
         	} else {
         		//Paso 2. Si todo correcto, ejecutar job 
         		ejecutarJob("tramite");
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
    
    @RequestMapping(value = "/indexarTodoUA.do")
    public @ResponseBody Map<String, Object> indexarTodoUA(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
              
            //Paso 1. Comprobar si hay algo creado.
          	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
          		resultats.put("error", "Hi ha tasques en execució");
          	} else {
          		//Paso 2. Si todo correcto, ejecutar job
          		ejecutarJob("ua");
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


    @RequestMapping(value = "/indexarPendientes.do")
    public @ResponseBody Map<String, Object> indexarPendientes(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();     

        try {
        	
        	//Paso 1. Comprobar si hay algo creado.
        	if ( DelegateUtil.getSolrPendienteDelegate().checkJobsActivos()) {
        		resultats.put("error", "Hi ha tasques en execució");
        	} else {
        		//Paso 2. Si todo correcto, ejecutar job 
        		ejecutarJob("pendientes");
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

    @RequestMapping(value = "/borrarCaducadas.do", method = POST)
    public @ResponseBody Map<String, Object> borrarCaducadas(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();     
    	 
         try {
        	 DelegateUtil.getSolrPendienteDelegate().borrarCaducadas();            

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


    @RequestMapping(value = "/grabarSolrPendiente.do", method = POST)
    public @ResponseBody Map<String, Object> grabarSolrPendiente(HttpServletRequest request) {

    	 final Map<String, Object> resultats = new HashMap<String, Object>();     
    	 Boolean result = false;
         
         try {
                String tipo = null;
				Long accion = null;
				Long idElemento = null;
				
				result =DelegateUtil.getSolrPendienteDelegate().grabarSolrPendiente(tipo, idElemento, accion);            

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
    public @ResponseBody Map<String, Object> llistatSIAJob(HttpServletRequest request) {

        List<SiaJobDTO> llistaSiaJobDTO = new ArrayList<SiaJobDTO>();
        final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        			
    		FiltroSia filtro = new FiltroSia();
    		filtro.setNumElementos(20);
    		llistaSiaJobDTO.addAll(convertirSIAToJobDTO(DelegateUtil.getSiaPendienteProcesoDelegate().getSiaProceso(filtro)));
            

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

        //resultats.put("total", llistaSiaJobDTO.size());
        resultats.put("nodes", llistaSiaJobDTO);

        return resultats;
    }

   
	@RequestMapping(value = "/llistatSIA.do")
    public @ResponseBody Map<String, Object> llistatSIA(HttpServletRequest request) {

        List<SiaPendienteDTO> llistaSiaDTO = new ArrayList<SiaPendienteDTO>();
        final Map<String, Object> resultats = new HashMap<String, Object>();

        try {
        			
    		FiltroSia filtro = new FiltroSia();
    		filtro.setNumElementos(20);
    		llistaSiaDTO.addAll(convertirSIAToDTO(DelegateUtil.getSiaPendienteProcesoDelegate().getSiaPendientes(filtro)));
            

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

        resultats.put("total", llistaSiaDTO.size());
        resultats.put("nodes", llistaSiaDTO);

        return resultats;
    }
	private List<SiaPendienteDTO> convertirSIAToDTO(List<SiaPendiente> siaPendientes) {
		 List<SiaPendienteDTO> siaPendienteDTO = new ArrayList<SiaPendienteDTO>();
	        
	        for (SiaPendiente sia : siaPendientes) {
	        	siaPendienteDTO.add(new SiaPendienteDTO(
	        			sia.getId(),
	        			sia.getTipo(),
	        			sia.getIdElemento(),
	        			sia.getEstado(),
	        			sia.getFecAlta(),
	        			sia.getFecIdx(),
	        			sia.getMensaje())
	            );
	        }
	        return siaPendienteDTO;
	}

	private List<SiaJobDTO> convertirSIAToJobDTO(List<SiaJob> siaProceso) {
		
		List<SiaJobDTO> siaJobDTO = new ArrayList<SiaJobDTO>();
		StringBuffer bufferDesc = new StringBuffer();
		StringBuffer bufferDescBreve = new StringBuffer();
			
        for (SiaJob siaJob : siaProceso) {
        	
        	try {
        		bufferDesc = SiaUtils.obtenerContenidoClob(siaJob.getDescripcion());
        		bufferDescBreve = SiaUtils.obtenerContenidoClob(siaJob.getDescBreve());
				
			} catch (IOException e) {
				log.error("Error: " + e.getMessage());
			} catch (SQLException e) {
				log.error("Error: " + e.getMessage());
			}
        	
        	siaJobDTO.add(new SiaJobDTO(
        			siaJob.getId(),
        			siaJob.getFechaIni(),
        			siaJob.getFechaFin(),
        			bufferDescBreve.toString(),
        			bufferDesc.toString())
            );
        }
        return siaJobDTO;
	}

	
	
	 @RequestMapping(value = "/enviarTodo.do")
	    public @ResponseBody Map<String, Object> enviarTodo(HttpServletRequest request) {

	    	final Map<String, Object> resultats = new HashMap<String, Object>();
	        try {
	        	//Paso 1. Comprobar si hay algo creado.
	          	if ( DelegateUtil.getSiaPendienteProcesoDelegate().checkJobsActivos()) {
	          		resultats.put("error", "Hi ha tasques en execució");
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
      	JobDetail jobDetail = new JobDetail("IndexacionJob", Scheduler.DEFAULT_GROUP, IndexacionJob.class);
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
        		resultats.put("error", "Hi ha tasques en execució");
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
}
