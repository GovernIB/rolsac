package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionNormativaExterna;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.transients.AfectacionTransient;
import org.ibit.rol.sac.model.transients.IdNomTransient;
import org.ibit.rol.sac.model.transients.NormativaTransient;
import org.ibit.rol.sac.model.transients.ProcedimientoLocalTransient;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/normativa/")
public class NormativaBackController {
	
	//TODO: obtener de algo global
	final static String idioma_per_defecte = "ca";
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }	
    
	@RequestMapping(value = "/pruebaform.htm", method = GET)
	public String pruebaForm(HttpServletRequest request, HttpSession session) {
		
		return "prueba";		
	}

	@RequestMapping(value = "/normativa.htm", method = GET)
	public String llistatNormatives(Map<String, Object> model, HttpServletRequest request, HttpSession session) {

		String idioma = request.getLocale().getLanguage();
		
		model.put("menu", 0);
		model.put("submenu", "layout/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 4);
		model.put("titol_escriptori", "Normativa");
		model.put("escriptori", "pantalles/normativa.jsp");

        if (session.getAttribute("unidadAdministrativa")!=null){
            model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(idioma_per_defecte));            
        } 		
        
        //Listas para el buscador
        try {
        	//Las pasamos a transient
        	List<Boletin> listaBoletines = DelegateUtil.getBoletinDelegate().listarBoletines();
        	List<IdNomTransient> listaBoletinesTransient = new ArrayList<IdNomTransient>();
        	for (Boletin boletin : listaBoletines) {        		       		
        		IdNomTransient bol = new IdNomTransient(boletin.getId(), boletin.getNombre());
        		listaBoletinesTransient.add(bol);
        	}
        	model.put("llistaButlletins", listaBoletinesTransient);
        	
        	List<Tipo> listaTiposNormativa = DelegateUtil.getTipoNormativaDelegate().listarTiposNormativas();
        	List<IdNomTransient> listaTiposNormativaTransient = new ArrayList<IdNomTransient>();
        	for (Tipo tipo : listaTiposNormativa) {
        		TraduccionTipo traTipo = (TraduccionTipo)tipo.getTraduccion(idioma);
        		if (traTipo == null) {
        			traTipo = (TraduccionTipo)tipo.getTraduccion(idioma_per_defecte);        			
        		}
        		IdNomTransient tipoTran = new IdNomTransient(tipo.getId(), traTipo.getNombre());
        		listaTiposNormativaTransient.add(tipoTran);
        	}        	
        	model.put("llistaTipusNormativa", listaTiposNormativaTransient);
        	        	
        } catch (DelegateException e) {
            if (e.getCause() instanceof SecurityException) {
                model.put("error", "permisos");
            } else {
                model.put("error", "altres");
                e.printStackTrace();
            }        	
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
		
		String idioma = request.getLocale().getLanguage();
		
		//TODO obtener la ordenación por parámetro
		String campoOrdenacion = "normativa.fecha";
		String orden = "desc";		
		
		//Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas
		boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));
		
		//Determinar si ha marcado el checkbox "Cerar a totes les meves unitats"
		boolean meves = "true".equals(request.getParameter("totesUnitats"));
		
		
		if (request.getParameter("idUA") == null || request.getParameter("idUA").equals("")){                      
			return resultats;//Si no hay unidad administrativa se devuelve vacio
		}	
		Long idUA = new Long(request.getParameter("idUA"));
		
		
		try {
			//Obtener parámetros de búsqueda
		
			if (request.getParameter("data") != null && !request.getParameter("data").equals("")) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date data = df.parse(request.getParameter("data"));
				paramMap.put("fecha", data);
			}			
			
			if (request.getParameter("data_butlleti") != null && !request.getParameter("data_butlleti").equals("")) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date dataButlleti = df.parse(request.getParameter("data_butlleti"));
				paramMap.put("fechaBoletin", dataButlleti);
			}
			
			if (request.getParameter("numero") != null && !"".equals(request.getParameter("numero")) )
				paramMap.put("numero", new Long(request.getParameter("numero")));
			
			if (request.getParameter("tipus") != null && !"".equals(request.getParameter("tipus")) )
				paramMap.put("tipo", new Long(request.getParameter("tipus")));
			
			if (request.getParameter("butlleti") != null && !"".equals(request.getParameter("butlleti")) )
				paramMap.put("boletin", new Long(request.getParameter("butlleti")));

			if (request.getParameter("registre") != null && !"".equals(request.getParameter("registre")) )
				paramMap.put("registro", request.getParameter("registre"));

			if (request.getParameter("llei") != null && !"".equals(request.getParameter("llei")) )
				paramMap.put("ley", request.getParameter("llei"));			
			
			if (request.getParameter("validacio") != null && !"".equals(request.getParameter("validacio")) )
				paramMap.put("validacion", request.getParameter("validacio"));
			
			//paramTrad.put("titulo", request.getParameter("titol") != null ? request.getParameter("titol").toUpperCase() : null);
			
			//paramTrad.put("idioma", idioma);
			// Textes (en todos los campos todos los idiomas)
			String text = request.getParameter("text");
			if (text != null && !"".equals(text)) {
				text = text.toUpperCase();
				/*
				if (paramTrad.get("titulo") == null) {
					paramTrad.put("titulo", text);
				}
				*/
				paramTrad.put("titulo", text);
				
				paramTrad.put("seccion", text);
				paramTrad.put("apartado", text);
				paramTrad.put("observaciones", text);
			} else {
				paramTrad.put("idioma", idioma);
			}			
			

			
			//Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			
			llistaNormatives = normativaDelegate.buscarNormativas(paramMap, paramTrad, "local", idUA, meves, campoOrdenacion, orden);
			
			if (buscaExternas) {
				List listaExternas = normativaDelegate.buscarNormativas(paramMap, paramTrad, "externa", idUA, meves, campoOrdenacion, orden);
				llistaNormatives.addAll(listaExternas);
			}
			
			for (Normativa normativa : llistaNormatives) {
				TraduccionNormativa traNor = (TraduccionNormativa)normativa.getTraduccion(idioma);
				String titulo = "";
				if (traNor != null) {
					//Retirar posible enlace incrustado en titulo
					titulo = obtenerTituloDeEnlaceHtml(traNor.getTitulo());
				}					
				
				TraduccionTipo traTip = normativa.getTipo() != null ? (TraduccionTipo)normativa.getTipo().getTraduccion(idioma) : null;
				String tipus = "";
				
				if (traTip != null)
					tipus = traTip.getNombre();
				
				
				boolean local = NormativaLocal.class.isInstance(normativa);
				
				llistaNormativesTransient.add(
							new NormativaTransient(
									normativa.getId() != null ? normativa.getId().longValue() : 0, 
									normativa.getNumero() != null ? normativa.getNumero().longValue() : 0, 
									titulo, 
									normativa.getFecha(), 
									tipus,
									local ? "Local" : "Externa")
						);
			}
			
			//Ordenar lista si se combinan locales y externas
			if (buscaExternas) {				
				Collections.sort(llistaNormativesTransient);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
					
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
	        
	        boolean normativaLocal = NormativaLocal.class.isInstance(normativa);
	        
	        if (normativaLocal) {
	        	normativaDetall.put("tipologia", "L");
	        	NormativaLocal normLoc = (NormativaLocal)normativa;
	        	normativaDetall.put("idUA", normLoc.getUnidadAdministrativa().getId());
	        	normativaDetall.put("nomUA", normLoc.getUnidadAdministrativa().getNombreUnidadAdministrativa());
	        } else {
	        	normativaDetall.put("tipologia", "E");
	        	normativaDetall.put("idUA", null);
	        	normativaDetall.put("nomUA", null);
	        }
	        
	        TraduccionNormativa normCA = (TraduccionNormativa)normativa.getTraduccion("ca");
	        TraduccionNormativa normES = (TraduccionNormativa)normativa.getTraduccion("es");
	        TraduccionNormativa normEN = (TraduccionNormativa)normativa.getTraduccion("en");
	        TraduccionNormativa normDE = (TraduccionNormativa)normativa.getTraduccion("de");
	        TraduccionNormativa normFR = (TraduccionNormativa)normativa.getTraduccion("fr");
	        	        
	        normativaDetall.put("id", normativa.getId());
	        normativaDetall.put("data", normativa.getFecha() != null ? DateUtils.formatearddMMyyyy(normativa.getFecha()) : "");	        
	        normativaDetall.put("idioma_ca_titol", normCA != null ? normCA.getTitulo() : "");
	        normativaDetall.put("idioma_ca_enllac", normCA != null ? normCA.getEnlace() : "");
	        normativaDetall.put("idioma_ca_apartat", normCA != null ? normCA.getApartado() : "");
	        normativaDetall.put("idioma_ca_pagini", normCA != null ? normCA.getPaginaInicial() : "");
	        normativaDetall.put("idioma_ca_pagfin", normCA != null ? normCA.getPaginaFinal() : "");
	        if (normativaLocal)
	        	normativaDetall.put("idioma_ca_responsable", "");	
	        else {
	        	TraduccionNormativaExterna normExt = (TraduccionNormativaExterna)normCA;
	        	normativaDetall.put("idioma_ca_responsable", normExt != null ? normExt.getResponsable() : "");
	        }
	        	
	        normativaDetall.put("idioma_ca_observacions", normCA != null ? normCA.getObservaciones() : "");
	        
	        normativaDetall.put("idioma_es_titol", normES != null ? normES.getTitulo() : "");
	        normativaDetall.put("idioma_es_enllac", normES != null ? normES.getEnlace() : "");
	        normativaDetall.put("idioma_es_apartat", normES != null ? normES.getApartado() : "");
	        normativaDetall.put("idioma_es_pagini", normES != null ? normES.getPaginaInicial() : "");
	        normativaDetall.put("idioma_es_pagfin", normES != null ? normES.getPaginaFinal() : "");
	        if (normativaLocal)
	        	normativaDetall.put("idioma_es_responsable", "");	
	        else {
	        	TraduccionNormativaExterna normExt = (TraduccionNormativaExterna)normES;
	        	normativaDetall.put("idioma_es_responsable", normExt != null ? normExt.getResponsable() : "");
	        }	        
	        normativaDetall.put("idioma_es_observacions", normES != null ? normES.getObservaciones() : "");	        
	        
	        normativaDetall.put("idioma_en_titol", normEN != null ? normEN.getTitulo() : "");
	        normativaDetall.put("idioma_en_enllac", normEN != null ? normEN.getEnlace() : "");
	        normativaDetall.put("idioma_en_apartat", normEN != null ? normEN.getApartado() : "");
	        normativaDetall.put("idioma_en_pagini", normEN != null ? normEN.getPaginaInicial() : "");
	        normativaDetall.put("idioma_en_pagfin", normEN != null ? normEN.getPaginaFinal() : "");
	        if (normativaLocal)
	        	normativaDetall.put("idioma_en_responsable", "");	
	        else {
	        	TraduccionNormativaExterna normExt = (TraduccionNormativaExterna)normEN;
	        	normativaDetall.put("idioma_en_responsable", normExt != null ? normExt.getResponsable() : "");
	        }	        
	        normativaDetall.put("idioma_en_observacions", normEN != null ? normEN.getObservaciones() : "");	        
	        
	        normativaDetall.put("idioma_de_titol", normDE != null ? normDE.getTitulo() : "");
	        normativaDetall.put("idioma_de_enllac", normDE != null ? normDE.getEnlace() : "");
	        normativaDetall.put("idioma_de_apartat", normDE != null ? normDE.getApartado() : "");
	        normativaDetall.put("idioma_de_pagini", normDE != null ? normDE.getPaginaInicial() : "");
	        normativaDetall.put("idioma_de_pagfin", normDE != null ? normDE.getPaginaFinal() : "");
	        if (normativaLocal)
	        	normativaDetall.put("idioma_de_responsable", "");	
	        else {
	        	TraduccionNormativaExterna normExt = (TraduccionNormativaExterna)normDE;
	        	normativaDetall.put("idioma_de_responsable", normExt != null ? normExt.getResponsable() : "");
	        }	        
	        normativaDetall.put("idioma_de_observacions", normDE != null ? normDE.getObservaciones() : "");
	        
	        normativaDetall.put("idioma_fr_titol", normFR != null ? normFR.getTitulo() : "");
	        normativaDetall.put("idioma_fr_enllac", normFR != null ? normFR.getEnlace() : "");
	        normativaDetall.put("idioma_fr_apartat", normFR != null ? normFR.getApartado() : "");
	        normativaDetall.put("idioma_fr_pagini", normFR != null ? normFR.getPaginaInicial() : "");
	        normativaDetall.put("idioma_fr_pagfin", normFR != null ? normFR.getPaginaFinal() : "");
	        if (normativaLocal)
	        	normativaDetall.put("idioma_fr_responsable", "");	
	        else {
	        	TraduccionNormativaExterna normExt = (TraduccionNormativaExterna)normFR;
	        	normativaDetall.put("idioma_fr_responsable", normExt != null ? normExt.getResponsable() : "");
	        }
	        normativaDetall.put("idioma_fr_observacions", normFR != null ? normFR.getObservaciones() : "");
	        
	        normativaDetall.put("numero", normativa.getNumero());	        
	        normativaDetall.put("butlleti_id", normativa.getBoletin() != null ? normativa.getBoletin().getId() : null);
	        normativaDetall.put("butlleti", normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : null);
	        normativaDetall.put("data_butlleti", normativa.getFechaBoletin() != null ? DateUtils.formatearddMMyyyy(normativa.getFechaBoletin()) : null);
	        normativaDetall.put("registre", normativa.getRegistro());
	        normativaDetall.put("llei", normativa.getLey());
	        normativaDetall.put("tipus", normativa.getTipo() != null ? normativa.getTipo().getId() : null);	        
	        normativaDetall.put("validacio", normativa.getValidacion());
	        
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
	public @ResponseBody IdNomTransient guardar(HttpSession session, HttpServletRequest request) {
		
		
		//TODO: completar paso de normativa local a externa y viceversa.
		
    	Normativa normativa = null;
    	Normativa normativaOld = null; 		
		IdNomTransient result = null;
		boolean normativaLocal;
        
        try {
        	NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        	
        	//Obtener la UA de la normativa. Si no tiene UA asignada es una normativa externa.
        	UnidadAdministrativa ua = null;
        	if (request.getParameter("item_ua_id") != null && !"".equals(request.getParameter("item_ua_id"))) {
        		Long idUA = new Long(request.getParameter("item_ua_id"));
        		ua = DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(idUA);
        	}

    		//Determinar si la normativa a guardar tiene que ser local/externa
    		if (ua == null) {
    			normativaLocal = false;
    			normativa = new NormativaExterna();
    		} else {
    			normativaLocal = true;
    			normativa = new NormativaLocal();      
            	//Asociar UA si es normativa local
            	((NormativaLocal)normativa).setUnidadAdministrativa(ua);    			
    		}        	
    		    		
    		boolean edicion = request.getParameter("item_id") != null && !"".equals(request.getParameter("item_id"));
    		
        	if (edicion) {
				Long idNorm = Long.parseLong(request.getParameter("item_id"));
        		normativaOld = normativaDelegate.obtenerNormativa(idNorm);
        		normativa.setAfectadas(normativaOld.getAfectadas());
        		normativa.setAfectantes(normativaOld.getAfectantes());
        		normativa.setProcedimientos(normativaOld.getProcedimientos());
        		normativa.setId(idNorm);
        	} 

        	//Campos por idioma
        	List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	for (String idioma : idiomas) {
        		TraduccionNormativa traNorm = normativaOld != null ? (TraduccionNormativa)normativaOld.getTraduccion(idioma) : null;
        		        		
        		if (traNorm != null) {
        			/*
        			//Paso de externa a local
        			if (TraduccionNormativaExterna.class.isInstance(traNorm) && normativaLocal) {        				
        				//TODO: borrar traducción externa?
        				
        				traNorm = null;
        			} 
        			//Paso de local a externa
        			else
        			if (TraduccionNormativa.class.isInstance(traNorm) && !normativaLocal) {
        				//TODO: borrar traduccion local?
        				
        				traNorm = null;
        			}
        			//Se queda igual
        			else
        			*/
            			normativa.setTraduccion(idioma, traNorm);
        		}
        		else
        		if (traNorm == null) {
        			if (normativaLocal)
        				traNorm = new TraduccionNormativa();
        			else
        				traNorm = new TraduccionNormativaExterna();

        			normativa.setTraduccion(idioma, traNorm);
        		}

        		traNorm.setTitulo(request.getParameter("item_titol_" + idioma));
        		traNorm.setEnlace(request.getParameter("item_enllas_" + idioma));
        		traNorm.setApartado(request.getParameter("item_apartat_" + idioma));
        		if (request.getParameter("item_pagina_inicial_" + idioma) != null && !"".equals(request.getParameter("item_pagina_inicial_" + idioma)))
        			traNorm.setPaginaInicial(Integer.parseInt(request.getParameter("item_pagina_inicial_" + idioma)));

        		if (request.getParameter("item_pagina_final_" + idioma) != null && !"".equals(request.getParameter("item_pagina_final_" + idioma)))
        			traNorm.setPaginaFinal(Integer.parseInt(request.getParameter("item_pagina_final_" + idioma)));        			

        		traNorm.setObservaciones(request.getParameter("item_des_curta_" + idioma));     

        		//Responsable sólo en normativa externa
        		if (!normativaLocal) {        				
        			((TraduccionNormativaExterna)traNorm).setResponsable(request.getParameter("item_responsable_" + idioma));
        		}

        	}

        	//Los demás campos
        	if (request.getParameter("item_numero") != null && !"".equals(request.getParameter("item_numero")))
        		normativa.setNumero(Long.parseLong(request.getParameter("item_numero")));

        	normativa.setLey(request.getParameter("item_llei"));

        	if (request.getParameter("item_registre") != null && !"".equals(request.getParameter("item_registre")))
        		normativa.setRegistro(Long.parseLong(request.getParameter("item_registre")));

        	if (request.getParameter("item_data_butlleti") != null && !"".equals(request.getParameter("item_data_butlleti"))) {
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		normativa.setFechaBoletin(dateFormat.parse(request.getParameter("item_data_butlleti")));
        	}

        	if (request.getParameter("item_data") != null && !"".equals(request.getParameter("item_data"))) {
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		normativa.setFecha(dateFormat.parse(request.getParameter("item_data")));
        	}       

        	if (request.getParameter("item_tipus") != null && !"".equals(request.getParameter("item_tipus"))) {
        		Tipo tipo = DelegateUtil.getTipoNormativaDelegate().obtenerTipoNormativa(new Long(request.getParameter("item_tipus")));
        		normativa.setTipo(tipo);
        	}

        	if (request.getParameter("item_validacio") != null && !"".equals(request.getParameter("item_validacio")))
        		normativa.setValidacion(new Integer(request.getParameter("item_validacio")));


        	//Boletín
        	if (request.getParameter("item_butlleti_id") != null && !"".equals(request.getParameter("item_butlleti_id"))) {
        		Boletin boletin = DelegateUtil.getBoletinDelegate().obtenerBoletin(Long.parseLong(request.getParameter("item_butlleti_id")));
        		normativa.setBoletin(boletin);
        	}

        	if (normativaLocal) {
        		/*
        		//Era externa y la pasamos a local, eliminamos el registro anterior
        		if (edicion && NormativaExterna.class.isInstance(normativaOld))
        			normativaDelegate.borrarNormativa(normativaOld.getId()); 
        		*/
        		normativaDelegate.grabarNormativaLocal((NormativaLocal)normativa, ua.getId());
        	} else {
        		/*
        		//Era externa y la pasamos a local, eliminamos el registro anterior
        		if (edicion && NormativaLocal.class.isInstance(normativaOld))
        			normativaDelegate.borrarNormativa(normativaOld.getId());        		
        		*/
        		normativaDelegate.grabarNormativaExterna((NormativaExterna)normativa);
        	}

        	String ok = messageSource.getMessage("normativa.guardat.correcte", null, request.getLocale());
        	result = new IdNomTransient(normativa.getId(), ok);
        	
        	
        } catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomTransient(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomTransient(-2l, error);
				dEx.printStackTrace();
			}        	        	
        } catch (ParseException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomTransient(-2l, error);			
			e.printStackTrace();
		}
						
		
		return result;
	}
	
	
	@RequestMapping(value = "/eliminar.htm", method = POST)
	public @ResponseBody IdNomTransient eliminar(HttpSession session, HttpServletRequest request) {
		Long id = new Long(request.getParameter("id"));
		
		try {
			DelegateUtil.getNormativaDelegate().borrarNormativa(id);
			
		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				return new IdNomTransient(-1l, error);
			} else {
				dEx.printStackTrace();
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				return new IdNomTransient(-2l, error);				
			}        	        	
        }
		
        return  new IdNomTransient(id, messageSource.getMessage("normativa.eliminat.correcte", null, request.getLocale()));		
	}
	
	
	@RequestMapping(value = "/upload.htm", method = POST)
	public @ResponseBody IdNomTransient upload(HttpSession session, HttpServletRequest request) {
		return new IdNomTransient(1L, "Archivo subido");
	}
	
	
	//TODO: mover a clase de utilidades.
	/**
	 * De un string que contiene un enlace HTML extrae el título del enlace.
	 * @param texto String que contiene el enlace.
	 * @return Título del enlace, si no hay enlace devuelve el texto tal cual.
	 */
	private static String obtenerTituloDeEnlaceHtml(String texto) {
		if (texto == null)
			return null;
		
		String tmp = texto;
		
		//Será el texto desde el primer '>' y desde ahí el primer '<'
		int pos = tmp.indexOf('>');
		if ( pos > -1) {
			tmp = tmp.substring(pos + 1);
			pos = tmp.indexOf('<');
			if (pos > -1) {
				tmp = tmp.substring(0, pos);
				return tmp.trim();
			} else
				return texto;
		} else 
			return texto;
				
	}
	
}
