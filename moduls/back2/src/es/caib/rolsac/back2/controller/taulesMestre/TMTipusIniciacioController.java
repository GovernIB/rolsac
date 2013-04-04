package es.caib.rolsac.back2.controller.taulesMestre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/tipusIniciacio/")
public class TMTipusIniciacioController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMTipusIniciacioController.class);
	
    @RequestMapping(value = "/tipusIniciacions.do")
    public String pantallaTipusIniciacio(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMTipusIniciacio.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmTipusIniciacio.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatTipusIniciacions(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaIniciacionsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> iniciacioDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();						

		try {
			IniciacionDelegate iniciacioDelegate = DelegateUtil.getIniciacionDelegate();
			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			resultadoBusqueda = iniciacioDelegate.listarIniciacion(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
			
			for ( Object o : resultadoBusqueda.getListaResultados() ) {
				
				Long id = (Long) ((Object[]) o)[0];
				String codiEstandard = (String) ((Object[]) o)[1];
				String nom = ((Object[]) o)[2] == null ? "" : (String) ((Object[]) o)[2];

				iniciacioDTO = new HashMap<String, Object>();
				iniciacioDTO.put("id", id);
				iniciacioDTO.put("codi_estandard", codiEstandard);
				iniciacioDTO.put("nom", nom);
				
				llistaIniciacionsDTO.add(iniciacioDTO);
				
			}
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados() );
		resultats.put("nodes", llistaIniciacionsDTO);

		return resultats;
	}
	
	
	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        IniciacionDelegate iniciacioDelegate = DelegateUtil.getIniciacionDelegate();
	        Iniciacion iniciacion = iniciacioDelegate.obtenerIniciacion(id);	        	        
	        
	        resultats.put("item_id", iniciacion.getId());
	        resultats.put("item_codi_estandard", iniciacion.getCodigoEstandar());
	        
	        // idiomes
	        if (iniciacion.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionIniciacion) iniciacion.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionIniciacion());
			}
	        if (iniciacion.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionIniciacion) iniciacion.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionIniciacion());
			}
	        if (iniciacion.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionIniciacion) iniciacion.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionIniciacion());
			}
	        if (iniciacion.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionIniciacion) iniciacion.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionIniciacion());
			}
	        if (iniciacion.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionIniciacion) iniciacion.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionIniciacion());
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
	public @ResponseBody IdNomDTO guardarIniciacio(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			IniciacionDelegate iniciacioDelegate = DelegateUtil.getIniciacionDelegate();
			Iniciacion iniciacio = new Iniciacion();
			Iniciacion iniciacioOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				iniciacioOld = iniciacioDelegate.obtenerIniciacion(id);
				edicion = true;
				iniciacio.setId(iniciacioOld.getId());
			} catch (NumberFormatException nfe) {
				iniciacioOld = null;
				edicion = false;
			}
			
			iniciacio.setCodigoEstandar(request.getParameter("item_codi_estandard"));
			
			// Idiomas
			TraduccionIniciacion ti;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					ti = (TraduccionIniciacion) iniciacioOld.getTraduccion(lang);
					if (ti == null) {
						ti = new TraduccionIniciacion();
					}
				} else {
					ti = new TraduccionIniciacion();
				}

				ti.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)) );
				iniciacio.setTraduccion(lang, ti);
			}
			// Fin idiomas
			
			Long tipusId = iniciacioDelegate.grabarIniciacion(iniciacio);
			
			String ok = messageSource.getMessage("tipusIniciacio.guardat.correcte", null, request.getLocale());
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

	@RequestMapping(value = "/esborrarTipusIniciacio.do", method = POST)
	public @ResponseBody IdNomDTO esborrarTipusIniciacio(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			IniciacionDelegate iniciacioDelegate = DelegateUtil.getIniciacionDelegate();
			iniciacioDelegate.borrarIniciacion(id);
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
			log.error("Error: Id de tipus d'iniciaci� no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
}
