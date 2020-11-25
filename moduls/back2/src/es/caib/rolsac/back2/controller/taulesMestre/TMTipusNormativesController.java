package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TipoNormativaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@Controller
@RequestMapping("/tipusNormatives/")
public class TMTipusNormativesController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMTipusNormativesController.class);
	
    @RequestMapping(value = "/tipusNormatives.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMTipusNormatives.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmTipusNormatives.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {

       List<IdNomDTO> llistaTipus = new ArrayList<IdNomDTO>();
       Map<String,Object> resultats = new HashMap<String,Object>();

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
      		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();              
       
       try {                      		  
    	   
			TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = tipoNormativaDelegate.listarTiposNormativas(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for (Object o : resultadoBusqueda.getListaResultados()) {
				Long id = (Long) ((Object[]) o)[0];
				String nom = ((Object[]) o)[1] == null ? "" : (String) ((Object[]) o)[1];
				
				llistaTipus.add(new IdNomDTO(id, nom) );
			}
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
        resultats.put("nodes", llistaTipus);

		return resultats;
	}

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();

	    try {
	        Long id = new Long(request.getParameter("id"));

	        TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
	        Tipo tipus = tipoNormativaDelegate.obtenerTipoNormativa(id);	        	        

	        resultats.put("item_id", tipus.getId());

	        // idiomes
	        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
	            if (tipus.getTraduccion(lang) != null) {
	                resultats.put(lang, (TraduccionTipo) tipus.getTraduccion(lang));
	            } else {
	                resultats.put(lang, new TraduccionTipo());
	            }
            }
	        // fi idiomes

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
    
	
	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
			Tipo tipus = new Tipo();
			Tipo tipusOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				tipusOld = tipoNormativaDelegate.obtenerTipoNormativa(id);
				edicion = true;
				// Mantenemos los valores originales que tiene el procedimiento.
				tipus.setId(tipusOld.getId());
			} catch (NumberFormatException nfe) {
				tipusOld = null;
				edicion = false;
			}
					
			// Idiomas
			TraduccionTipo tp;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tp = (TraduccionTipo) tipusOld.getTraduccion(lang);
					if (tp == null) {
						tp = new TraduccionTipo();
					}
				} else {
					tp = new TraduccionTipo();
				}

				tp.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)) );
				tipus.setTraduccion(lang, tp);
			}
			// Fin idiomas
			
			Long tipusId = tipoNormativaDelegate.grabarTipoNormativa(tipus);

			
			String ok = messageSource.getMessage("tipusNormativa.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(tipusId, ok);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfe) {
			result = new IdNomDTO(-3l, error);
		}

		return result;
	}
	
	
	@RequestMapping(value = "/esborrarTipusNormativa.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
			tipoNormativaDelegate.borrarTipoNormativa(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de tipus de normativa no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
	
	@RequestMapping(value = "/traduir.do")
   	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
   	{
   		Map<String, Object> resultats = new HashMap<String, Object>();
   		
   		try {
   			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
   			
   			TraduccionTipo traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
   			log.error("TipusNormativaBackController.traduir: El traductor no se encuentra en en contexto.");
   			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
   		} catch (Exception e) {
   			log.error("TipusNormativaBackController.traduir: Error en al traducir Tipus Normativa: " + e);
   			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
   		}
   		
   		return resultats;
   	}
   	
   	
    private TraduccionTipo getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
   	{
    	TraduccionTipo traduccioOrigen = new TraduccionTipo();
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
   		}
   		
   		return traduccioOrigen;
   	}
	
}
