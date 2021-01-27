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
import org.ibit.rol.sac.model.TraduccionTipoUA;
import org.ibit.rol.sac.model.TraduccionTratamiento;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@Controller
@RequestMapping("/tipusUnitat/")
public class TMTipusUnitatController extends PantallaBaseController
{
	private static Log log = LogFactory.getLog(TMTipusUnitatController.class);
    
    @RequestMapping(value = "/tipusUnitat.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMTipusUnitat.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmTipusUnitat.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    

	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request)
	{
		List<Map<String, Object>> llistaUnitatsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> unitatDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		//Información de paginación
		String pagPag = request.getParameter("pagPag");
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0);
		if (pagRes == null) pagRes = String.valueOf(10);
		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		try {
			TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
			resultadoBusqueda = unitatDelegate.listarTratamientos(Integer.parseInt(pagPag), Integer.parseInt(pagRes));
			
			for (Tratamiento unitat: castList(Tratamiento.class, resultadoBusqueda.getListaResultados())) {
				TraduccionTratamiento tu = (TraduccionTratamiento) unitat.getTraduccion(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
				
				unitatDTO = new HashMap<String, Object>();
				unitatDTO.put("id", unitat.getId());
				unitatDTO.put("codi_estandard", unitat.getCodigoEstandar());
				unitatDTO.put("tipus", tu == null ? "" : tu.getTipo());
				unitatDTO.put("carreg_femeni", tu == null ? "" : tu.getCargoF());
				unitatDTO.put("carreg_masculi", tu == null ? "" : tu.getCargoM());
				unitatDTO.put("tratament_femeni", tu == null ? "" : tu.getTratamientoF());
				unitatDTO.put("tratament_masculi", tu == null ? "" : tu.getTratamientoM());
				
				llistaUnitatsDTO.add(unitatDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}
		
		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaUnitatsDTO);
		
		return resultats;
	}
	
	
	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();

	    try {
	        Long id = new Long(request.getParameter("id"));

	        TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
	        Tratamiento unitat = unitatDelegate.obtenerTratamiento(id);	        	        

	        resultats.put("item_id", unitat.getId());
	        resultats.put("item_codi_estandard", unitat.getCodigoEstandar());

	        // idiomes
	        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
	            if (unitat.getTraduccion(lang) != null) {
	                resultats.put(lang, (TraduccionTratamiento) unitat.getTraduccion(lang));
	            } else {
	                resultats.put(lang, new TraduccionTratamiento());
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
			TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
			Tratamiento unitat = new Tratamiento();
			Tratamiento unitatOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				unitatOld = unitatDelegate.obtenerTratamiento(id);
				edicion = true;
				unitat.setId(unitatOld.getId());
			} catch (NumberFormatException nfe) {
				unitatOld = null;
				edicion = false;
			}
			
			unitat.setCodigoEstandar(request.getParameter("item_codi_estandard"));
			
			// Idiomas
			TraduccionTratamiento tu;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tu = (TraduccionTratamiento) unitatOld.getTraduccion(lang);
					if (tu == null) {
						tu = new TraduccionTratamiento();
					}
				} else {
					tu = new TraduccionTratamiento();
				}

				tu.setTipo( RolUtil.limpiaCadena(request.getParameter("item_tipus_" + lang)) );
				tu.setCargoF( RolUtil.limpiaCadena(request.getParameter("item_carreg_femeni_" + lang)) );
				tu.setCargoM( RolUtil.limpiaCadena(request.getParameter("item_carreg_masculi_" + lang)) );
				tu.setTratamientoF( RolUtil.limpiaCadena(request.getParameter("item_tractament_femeni_" + lang)) );
				tu.setTratamientoM( RolUtil.limpiaCadena(request.getParameter("item_tractament_masculi_" + lang)) );
				
				unitat.setTraduccion(lang, tu);
			}
			// Fin idiomas
			
			Long tipusId = unitatDelegate.grabarTratamiento(unitat);
			
			String ok = messageSource.getMessage("tipusUnitat.guardat.correcte", null, request.getLocale());
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

	@RequestMapping(value = "/esborrarTipusUnitat.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			TratamientoDelegate tratamientoDelegate = DelegateUtil.getTratamientoDelegate();
			
			if (tratamientoDelegate.tieneUnidades(id)) {
				resultatStatus.setId(-1l);
				String error = messageSource.getMessage("tipusNormativa.guardat.error", null, request.getLocale());
				resultatStatus.setNom(error);
			} else {
				tratamientoDelegate.borrarTratamiento(id);
				resultatStatus.setId(1l);
				resultatStatus.setNom("correcte");
			}
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (NumberFormatException nfEx) {
			
			resultatStatus.setId(-3l);
			log.error("Error: Id de tipus d'unitat no numèric: " + ExceptionUtils.getStackTrace(nfEx));
			
		}
		
		return resultatStatus;
		
	}
	
	@RequestMapping(value = "/traduir.do")
   	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
   	{
   		Map<String, Object> resultats = new HashMap<String, Object>();
   		
   		try {
   			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
   			
   			TraduccionTipoUA traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
   	
   	
    private TraduccionTipoUA getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
   	{
    	TraduccionTipoUA traduccioOrigen = new TraduccionTipoUA();
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_tipus_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setTipo(request.getParameter("item_tipus_" + idiomaOrigenTraductor));
   		}
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_carreg_masculi_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setCargoMasculino(request.getParameter("item_carreg_masculi_" + idiomaOrigenTraductor));
   		}
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_tractament_masculi_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setTratamientoMasculino(request.getParameter("item_tractament_masculi_" + idiomaOrigenTraductor));
   		}
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_carreg_femeni_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setCargoFemenino(request.getParameter("item_carreg_femeni_" + idiomaOrigenTraductor));
   		}
   		
   		if (StringUtils.isNotEmpty(request.getParameter("item_tractament_femeni_" + idiomaOrigenTraductor))) {
   			traduccioOrigen.setTratamientoFemenino(request.getParameter("item_tractament_femeni_" + idiomaOrigenTraductor));
   		}
   		
   		return traduccioOrigen;
   	}
}
