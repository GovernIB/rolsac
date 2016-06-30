package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.dto.SolrPendienteDTO;
import org.ibit.rol.sac.model.dto.SolrPendienteJobDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
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
              DelegateUtil.getSolrPendienteDelegate().crearJob("todo");   
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
    
    
    @RequestMapping(value = "/indexarTodoFicha.do")
    public @ResponseBody Map<String, Object> indexarTodoFicha(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
              DelegateUtil.getSolrPendienteDelegate().crearJob("ficha");   
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
    
    @RequestMapping(value = "/indexarTodoProcedimiento.do")
    public @ResponseBody Map<String, Object> indexarTodoProcedimiento(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
         try {
              DelegateUtil.getSolrPendienteDelegate().crearJob("procedimiento");    
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
    
    @RequestMapping(value = "/indexarTodoNormativa.do")
    public @ResponseBody Map<String, Object> indexarTodoNormativa(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
              DelegateUtil.getSolrPendienteDelegate().crearJob("normativa");    
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
    
    @RequestMapping(value = "/indexarTodoTramite.do")
    public @ResponseBody Map<String, Object> indexarTodoTramite(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
         try {
              DelegateUtil.getSolrPendienteDelegate().crearJob("tramite");    
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
    
    @RequestMapping(value = "/indexarTodoUA.do")
    public @ResponseBody Map<String, Object> indexarTodoUA(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();
        try {
              DelegateUtil.getSolrPendienteDelegate().crearJob("ua");    
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


    @RequestMapping(value = "/indexarPendientes.do")
    public @ResponseBody Map<String, Object> indexarPendientes(HttpServletRequest request) {

    	final Map<String, Object> resultats = new HashMap<String, Object>();     

        try {
             DelegateUtil.getSolrPendienteDelegate().crearJob("pendientes");            

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
  
}
