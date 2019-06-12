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
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/publicObjectiu/")
public class TMPublicObjectiuController extends PantallaBaseController {
	
	private static Log log = LogFactory.getLog(TMPublicObjectiuController.class);
    
    @RequestMapping(value = "/publicObjectiu.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMPublicObjectiu.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmPublicObjectiu.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
   	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {
   	
   		List<Map<String, Object>> llistaPublicObjectiuDTO = new ArrayList<Map<String, Object>>();
   		Map<String, Object> publicObjectiuDTO;
   		Map<String, Object> resultats = new HashMap<String, Object>();
 		
		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();   			   		
   		
   		try {
   			PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
   			
   			resultadoBusqueda = publicObjectiuDelegate.listarPublicoObjetivo(Integer.parseInt(pagPag), Integer.parseInt(pagRes));
   			
   			for (Object o : resultadoBusqueda.getListaResultados()) {
   			
   				Long id = (Long) ((Object[]) o)[0];
   				Integer ordre = (Integer) ((Object[]) o) [1];
   				String codiEstandard = (String) ((Object[]) o )[2];
   				boolean interno = (Boolean) ((Object[]) o )[3];
   				
   				publicObjectiuDTO = new HashMap<String, Object>();
   				publicObjectiuDTO.put("id", id);
   				publicObjectiuDTO.put("ordre", ordre);
   				publicObjectiuDTO.put("codiEstandard", codiEstandard);
   				publicObjectiuDTO.put("interno", interno);
   				
   				llistaPublicObjectiuDTO.add(publicObjectiuDTO);
   			}
   			
   		} catch (DelegateException dEx) {
   			if (dEx.isSecurityException()) {
   				log.error("Permisos insuficients: " + dEx.getMessage());
   			} else {
   				log.error("Error: " + dEx.getMessage());
   			}
   		}

   		resultats.put("total", resultadoBusqueda.getTotalResultados());
   		resultats.put("nodes", llistaPublicObjectiuDTO);

   		return resultats;
   	}
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
	        PublicoObjetivo publicObjectiu = publicObjectiuDelegate.obtenerPublicoObjetivo(id);	        	        
	        
	        resultats.put("item_id", publicObjectiu.getId());
	      
	        resultats.put("item_codi_estandard", publicObjectiu.getCodigoEstandar());
	        resultats.put("item_es_interno", publicObjectiu.isInterno());
	      
			omplirCampsTraduibles(resultats, publicObjectiu);
	       
	        
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
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, PublicoObjetivo publicObjectiu) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = castList(String.class, idiomaDelegate.listarLenguajes());
		
		for (String lang: langs) {
		    if (null!=publicObjectiu.getTraduccion(lang)) {
				resultats.put(lang, publicObjectiu.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionPublicoObjetivo());
			}
		}
	}
    
    @RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			PublicoObjetivoDelegate publicoObjetivolDelegate = DelegateUtil.getPublicoObjetivoDelegate();
			PublicoObjetivo publicObjectiu = new PublicoObjetivo();
			PublicoObjetivo publicObjectiuOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				publicObjectiuOld = publicoObjetivolDelegate.obtenerPublicoObjetivo(id);
				edicion = true;
				publicObjectiu.setId(publicObjectiuOld.getId());
				publicObjectiu.setOrden(publicObjectiuOld.getOrden());
				publicObjectiu.setInterno(publicObjectiuOld.isInterno());
			} catch (NumberFormatException nfe) {
				publicObjectiuOld = null;
				edicion = false;
			}
			
			publicObjectiu.setCodigoEstandar(request.getParameter("item_codi_estandard"));
			publicObjectiu.setInterno(request.getParameter("item_es_interno") != null && !"".equals(request.getParameter("item_es_interno")));
			
			
			List<PublicoObjetivo> publicos =  publicoObjetivolDelegate.listarPublicoObjetivo();
			
			int numInternos=0; //numero de po internos que no son el actual
			for(PublicoObjetivo p: publicos) {
				//si es nuevo elemento los cogemos todos los internos, si es edicion no recuperamos el valor del actual
				if(p.isInterno() && (publicObjectiu.getId()== null || !p.getId().equals(publicObjectiu.getId()))) {
					numInternos++;
				}
			}
			
			if(numInternos > 0  && publicObjectiu.isInterno()) {
				throw new Exception("ERROR: se está intentando guardar un público objetivo como interno y ya existe otro.");
			}
			
					
			// Idiomas
			TraduccionPublicoObjetivo tpo;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = castList(String.class, idiomaDelegate.listarLenguajes());

			for (String lang: langs) {
				if (edicion) {
					tpo = (TraduccionPublicoObjetivo) publicObjectiuOld.getTraduccion(lang);
					if (tpo == null) {
						tpo = new TraduccionPublicoObjetivo();
					}
				} else {
					tpo = new TraduccionPublicoObjetivo();
				}

				tpo.setTitulo( RolUtil.limpiaCadena(request.getParameter("item_titol_" + lang)) );
				tpo.setDescripcion( RolUtil.limpiaCadena(request.getParameter("item_descripcio_" + lang)) );
				tpo.setPalabrasclave( RolUtil.limpiaCadena(request.getParameter("item_paraules_clau_" + lang)) );
				
				publicObjectiu.setTraduccion(lang, tpo);
			}
			// Fin idiomas
			
			Long tipusId = publicoObjetivolDelegate.grabarPublicoObjetivo(publicObjectiu);
			
			String ok = messageSource.getMessage("perfil.guardat.correcte", null, request.getLocale());
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
		} catch (Exception e) {
			error = messageSource.getMessage("error.po.interno.unico", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
		}
		return result;
	}	
    
    @RequestMapping(value = "/esborrarPublicObjectiu.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
			publicObjectiuDelegate.borrarPublicoObjetivo(id);
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
			log.error("Error: Id de pefil no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
    @RequestMapping(value = "/pujarPublicObjectiu.do", method = POST)
	public @ResponseBody IdNomDTO pujarPublicObjectiu(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
			publicObjectiuDelegate.subirOrden(id);
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
			log.error("Error: Id de pefil no numèric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}          
    
    @RequestMapping(value = "/reordenarPublicObjectiu.do", method = POST)
    public @ResponseBody IdNomDTO reordenar(HttpServletRequest request) {
    	IdNomDTO resultatStatus = new IdNomDTO();
    	
		try {
			Long id = new Long(request.getParameter("id"));
			Integer nuevoOrden = new Integer(request.getParameter("orden"));
			Integer ordenOld = new Integer(request.getParameter("ordenAnterior"));
			
			PublicoObjetivoDelegate publicObjectiuDelegate = DelegateUtil.getPublicoObjetivoDelegate();
			publicObjectiuDelegate.reordenar(id, nuevoOrden, ordenOld);
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de public objectiu no numèric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		
		return resultatStatus;
    }
    
    
    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
	{
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			TraduccionPublicoObjetivo traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			log.error("PublicObjectiuBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("PublicObjectiuBackController.traduir: Error en al traducir public obkectiu: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}
		
		return resultats;
	}
	
	
    private TraduccionPublicoObjetivo getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
	{
    	TraduccionPublicoObjetivo traduccioOrigen = new TraduccionPublicoObjetivo();
		
		if (StringUtils.isNotEmpty(request.getParameter("item_titol_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setTitulo(request.getParameter("item_titol_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}
		
		if (StringUtils.isNotEmpty(request.getParameter("item_paraules_clau_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setPalabrasclave(request.getParameter("item_paraules_clau_" + idiomaOrigenTraductor));
		}
		
		return traduccioOrigen;
	}
    
}
