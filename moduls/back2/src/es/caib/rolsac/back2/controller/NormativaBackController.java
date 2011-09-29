package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.transients.AfectacionTransient;
import org.ibit.rol.sac.model.transients.NormativaTransient;
import org.ibit.rol.sac.model.transients.ProcedimientoLocalTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/normativa/")
public class NormativaBackController {

	@RequestMapping(value = "/normativa.htm", method = GET)
	public String llistatNormatives(Map<String, Object> model, HttpSession session) {

		model.put("menu", 0);
		model.put("submenu", "layout/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 4);
		model.put("titol_escriptori", "Normativa");
		model.put("escriptori", "pantalles/normativa.jsp");

        if (session.getAttribute("unidadAdministrativa")!=null){
            model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa("ca"));            
        } 		
		
		return "index";
	}
	
	
	
	@RequestMapping(value = "/llistat.htm", method = POST)
	public @ResponseBody Map<String, Object> llistatNormatives(HttpServletRequest request, HttpSession session)  {

		//Listar las normativas de la unidad administrativa
		List<Normativa>llistaNormatives = new ArrayList<Normativa>();
		List<NormativaTransient>llistaNormativesTransient= new ArrayList<NormativaTransient>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		Map<String, String> paramTrad = new HashMap<String, String>();
		
		//Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas
		boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));
		
		if (request.getParameter("idUA") == null || request.getParameter("idUA").equals("")){                      
			return resultats;//Si no hay unidad administrativa se devuelve vacio
		}		
		
		//Obtener parámetros de búsqueda
		try {
			if (!buscaExternas)
				paramMap.put("unidadAdministrativa.id", new Long(request.getParameter("idUA")));
			
			if ("true".equals(request.getParameter("totesUnitats")) ) {
				//TODO: Buscar sobre las unidades orgánicas del usuario
			}

			if (request.getParameter("data_butlleti") != null && !request.getParameter("data_butlleti").equals("")) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date dataButlleti = df.parse(request.getParameter("data_butlleti"));
				paramMap.put("fechaBoletin", dataButlleti);
			}
			//TODO: campo 'estat'?
			
			paramTrad.put("idioma", "ca"); //TODO: cambiar por idioma por defecto del usuario		
			paramTrad.put("titulo", request.getParameter("titol") != null ? request.getParameter("titol").toUpperCase() : null);
			//TODO: verificar sobre qué campo actúa 'text'
			paramTrad.put("observaciones", request.getParameter("text") != null ? request.getParameter("text").toUpperCase() : null);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Realizar la consutla y obtener resultados
		try {
			
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			
			llistaNormatives = normativaDelegate.buscarNormativas(paramMap, paramTrad, buscaExternas ? "externa" : "local");
			
			
			for (Normativa normativa : llistaNormatives) {
				TraduccionNormativa traNor = (TraduccionNormativa)normativa.getTraduccion("ca"); //TODO: cambiar por idioma por defecto del usuario
				String titulo = "";
				if (traNor != null)
					titulo = traNor.getTitulo();
				
				TraduccionTipo traTip = (TraduccionTipo)normativa.getTipo().getTraduccion("ca"); //TODO: cambiar por idioma por defecto del usuario
				String tipus = "";
				
				if (traTip != null)
					tipus = traTip.getNombre();
				
				
				llistaNormativesTransient.add(
							new NormativaTransient(
									normativa.getId() != null ? normativa.getId().longValue() : 0, 
									normativa.getNumero() != null ? normativa.getNumero().longValue() : 0, 
									titulo, 
									normativa.getFecha() != null ? DateUtils.formatearddMMyyyy(normativa.getFecha()) : "", 
									tipus)
						);
			}
			
		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				//model.put("error", "permisos");
			} else {
				//model.put("error", "altres");
				dEx.printStackTrace();
			}
		}
		
		resultats.put("total", llistaNormativesTransient.size());
		resultats.put("nodes", llistaNormativesTransient);

		return resultats;
		
	}	
	
	@RequestMapping(value = "/pagDetall.htm", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request, Map<String, Object> model) {
		
	    Map<String, Object> normativaDetall = new HashMap<String, Object>();
	    List<Object> afectadas = new ArrayList<Object>();
	    List<Object> procedimientos = new ArrayList<Object>();
	    
	    try {
	        
	        Long id = new Long(request.getParameter("id"));		
	        
	        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
	        Normativa normativa = normativaDelegate.obtenerNormativa(id);
	        
	        TraduccionNormativa normCA = (TraduccionNormativa)normativa.getTraduccion("ca");
	        TraduccionNormativa normES = (TraduccionNormativa)normativa.getTraduccion("es");
	        TraduccionNormativa normEN = (TraduccionNormativa)normativa.getTraduccion("en");
	        TraduccionNormativa normDE = (TraduccionNormativa)normativa.getTraduccion("de");
	        TraduccionNormativa normFR = (TraduccionNormativa)normativa.getTraduccion("fr");
	        
	        TraduccionTipo traTip = (TraduccionTipo)normativa.getTipo().getTraduccion("ca"); //TODO: cambiar por idioma por defecto del usuario
	        
	        normativaDetall.put("id", normativa.getId());
	        normativaDetall.put("data", normativa.getFecha() != null ? DateUtils.formatearddMMyyyy(normativa.getFecha()) : "");	        
	        normativaDetall.put("idioma_ca_titol", normCA != null ? normCA.getTitulo() : "");
	        normativaDetall.put("idioma_ca_enllac", normCA != null ? normCA.getEnlace() : "");
	        normativaDetall.put("idioma_ca_apartat", normCA != null ? normCA.getApartado() : "");
	        normativaDetall.put("idioma_ca_pagini", normCA != null ? normCA.getPaginaInicial() : "");
	        normativaDetall.put("idioma_ca_pagfin", normCA != null ? normCA.getPaginaFinal() : "");
	        normativaDetall.put("idioma_ca_observacions", normCA != null ? normCA.getObservaciones() : "");
	        
	        normativaDetall.put("idioma_es_titol", normES != null ? normES.getTitulo() : "");
	        normativaDetall.put("idioma_es_enllac", normES != null ? normES.getEnlace() : "");
	        normativaDetall.put("idioma_es_apartat", normES != null ? normES.getApartado() : "");
	        normativaDetall.put("idioma_es_pagini", normES != null ? normES.getPaginaInicial() : "");
	        normativaDetall.put("idioma_es_pagfin", normES != null ? normES.getPaginaFinal() : "");
	        normativaDetall.put("idioma_es_observacions", normES != null ? normES.getObservaciones() : "");	        
	        
	        normativaDetall.put("idioma_en_titol", normEN != null ? normEN.getTitulo() : "");
	        normativaDetall.put("idioma_en_enllac", normEN != null ? normEN.getEnlace() : "");
	        normativaDetall.put("idioma_en_apartat", normEN != null ? normEN.getApartado() : "");
	        normativaDetall.put("idioma_en_pagini", normEN != null ? normEN.getPaginaInicial() : "");
	        normativaDetall.put("idioma_en_pagfin", normEN != null ? normEN.getPaginaFinal() : "");
	        normativaDetall.put("idioma_en_observacions", normEN != null ? normEN.getObservaciones() : "");	        
	        
	        normativaDetall.put("idioma_de_titol", normDE != null ? normDE.getTitulo() : "");
	        normativaDetall.put("idioma_de_enllac", normDE != null ? normDE.getEnlace() : "");
	        normativaDetall.put("idioma_de_apartat", normDE != null ? normDE.getApartado() : "");
	        normativaDetall.put("idioma_de_pagini", normDE != null ? normDE.getPaginaInicial() : "");
	        normativaDetall.put("idioma_de_pagfin", normDE != null ? normDE.getPaginaFinal() : "");
	        normativaDetall.put("idioma_de_observacions", normDE != null ? normDE.getObservaciones() : "");
	        
	        normativaDetall.put("idioma_fr_titol", normFR != null ? normFR.getTitulo() : "");
	        normativaDetall.put("idioma_fr_enllac", normFR != null ? normFR.getEnlace() : "");
	        normativaDetall.put("idioma_fr_apartat", normFR != null ? normFR.getApartado() : "");
	        normativaDetall.put("idioma_fr_pagini", normFR != null ? normFR.getPaginaInicial() : "");
	        normativaDetall.put("idioma_fr_pagfin", normFR != null ? normFR.getPaginaFinal() : "");
	        normativaDetall.put("idioma_fr_observacions", normFR != null ? normFR.getObservaciones() : "");
	        
	        normativaDetall.put("numero", normativa.getNumero());	        
	        normativaDetall.put("butlleti", normativa.getBoletin().getNombre());
	        normativaDetall.put("data_butlleti", DateUtils.formatearddMMyyyy(normativa.getFechaBoletin()));
	        normativaDetall.put("registre", normativa.getRegistro());
	        normativaDetall.put("llei", normativa.getLey());
	        normativaDetall.put("tipus", traTip != null ? traTip.getNombre() : "");
	        
	        //Normativas afectadas
	        Set<Afectacion> listaAfectadas = normativa.getAfectadas();
	        for (Afectacion afec : listaAfectadas) {
	        	Normativa normativaAfectada = afec.getNormativa();
	        	TraduccionNormativa traNormAfectada = (TraduccionNormativa)normativaAfectada.getTraduccion("ca"); //TODO: cambiar por idioma por defecto del usuario
	        	afectadas.add(new AfectacionTransient(normativaAfectada.getId(), traNormAfectada != null ? traNormAfectada.getTitulo() : ""));
	        }	        
	        normativaDetall.put("afectacions", afectadas);
	        
	        //Procedimientos
	        Set<ProcedimientoLocal> listaProcedimientos = normativa.getProcedimientos();
	        for (ProcedimientoLocal proc : listaProcedimientos) {
	        	TraduccionProcedimientoLocal traProc = (TraduccionProcedimientoLocal)proc.getTraduccion("ca"); //TODO: cambiar por idioma por defecto del usuario
	        	procedimientos.add(new ProcedimientoLocalTransient(proc.getId(), traProc != null ? traProc.getNombre() : "", null, null));
	        }
	        normativaDetall.put("procediments", procedimientos);
	        
	        
	    } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
                model.put("error", "permisos");
            } else {
                model.put("error", "altres");
                dEx.printStackTrace();
            }
        }
	    
        return normativaDetall;
	}
	
	@RequestMapping(value = "/guardar.htm", method = POST)
	public @ResponseBody Map<String, Object> guardar(HttpServletRequest request, Map<String, Object> model) {
		//TODO: implementar
		Map<String, Object> resultado = new HashMap<String,Object>();
		
		resultado.put("nom", "Guardat correctament");
		
		return resultado;
	}
	
}
