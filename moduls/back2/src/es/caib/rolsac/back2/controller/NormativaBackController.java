package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionNormativaExterna;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.TraduccionTipoAfectacion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.transients.AfectacionTransient;
import org.ibit.rol.sac.model.transients.AfectacionesTransient;
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

import es.caib.rolsac.back2.customJSTLTags.PrintRolTag;
import es.caib.rolsac.back2.util.UploadUtil;


@Controller
@RequestMapping("/normativa/")
public class NormativaBackController {
		
	private static Log log = LogFactory.getLog(PrintRolTag.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
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
            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa());            
        } 		
        
        //Listas para el buscador
        try {
        	//Las pasamos a transient
        	
        	//Boletines
        	List<Boletin> listaBoletines = DelegateUtil.getBoletinDelegate().listarBoletines();
        	List<IdNomTransient> listaBoletinesTransient = new ArrayList<IdNomTransient>();
        	for (Boletin boletin : listaBoletines) {        		       		
        		IdNomTransient bol = new IdNomTransient(boletin.getId(), boletin.getNombre());
        		listaBoletinesTransient.add(bol);
        	}
        	model.put("llistaButlletins", listaBoletinesTransient);
        	
        	//Tipos normativa
        	List<Tipo> listaTiposNormativa = DelegateUtil.getTipoNormativaDelegate().listarTiposNormativas();
        	List<IdNomTransient> listaTiposNormativaTransient = new ArrayList<IdNomTransient>();
        	for (Tipo tipo : listaTiposNormativa) {
        		TraduccionTipo traTipo = (TraduccionTipo)tipo.getTraduccion(idioma);
        		if (traTipo == null) {
        			traTipo = (TraduccionTipo)tipo.getTraduccion();        			
        		}
        		IdNomTransient tipoTran = new IdNomTransient(tipo.getId(), traTipo.getNombre());
        		listaTiposNormativaTransient.add(tipoTran);
        	}        	
        	model.put("llistaTipusNormativa", listaTiposNormativaTransient);
        	
        	//Tipos afectacion
        	List<TipoAfectacion> listaTiposAfectacion = DelegateUtil.getTipoAfectacionDelegate().listarTiposAfectaciones();
        	List<IdNomTransient> listaTiposAfectacionTransient = new ArrayList<IdNomTransient>();
        	for (TipoAfectacion tipoAfec : listaTiposAfectacion) {
        		TraduccionTipoAfectacion traTipAfec = (TraduccionTipoAfectacion)tipoAfec.getTraduccion(idioma);
        		if (traTipAfec == null)
        			traTipAfec = (TraduccionTipoAfectacion)tipoAfec.getTraduccion();
        		
        		IdNomTransient tipAfecTran = new IdNomTransient(tipoAfec.getId(), traTipAfec.getNombre());
        		listaTiposAfectacionTransient.add(tipAfecTran);
        	}
        	model.put("llistaTipusAfectacio", listaTiposAfectacionTransient);
        	        	
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
			return resultats;//Si no hay unidad administrativa se devuelve vacío
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
			
            if (request.isUserInRole("sacoper")){
            	paramMap.put("validacion", "");
            } else {
                paramMap.put("validacion", request.getParameter("validacio"));
            }			
			
			
			// Textes (en todos los campos todos los idiomas)
			String text = request.getParameter("text");
			if (text != null && !"".equals(text)) {
				text = text.toUpperCase();

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
			
			llistaNormativesTransient = pasarListaNormativasATransient(llistaNormatives, idioma);
			
			//Ordenar lista si se combinan locales y externas
			if (buscaExternas) {				
				Collections.sort(llistaNormativesTransient);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
					
		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
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
	    
	    String idiomaUsuario = request.getLocale().getLanguage();
	    
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
	        
	        normativaDetall.put("id", normativa.getId());
	        normativaDetall.put("data", normativa.getFecha() != null ? DateUtils.formatearddMMyyyy(normativa.getFecha()) : "");	   	        
	        
        	//Campos por idioma
        	List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	for (String idioma : idiomas) {
        		TraduccionNormativa traNorm = (TraduccionNormativa)normativa.getTraduccion(idioma);
        		
    	        normativaDetall.put("idioma_" + idioma + "_titol", traNorm != null ? traNorm.getTitulo() : "");
    	        normativaDetall.put("idioma_" + idioma + "_enllac", traNorm != null ? traNorm.getEnlace() : "");
    	        normativaDetall.put("idioma_" + idioma + "_apartat", traNorm != null ? traNorm.getApartado() : "");
    	        normativaDetall.put("idioma_" + idioma + "_pagini", traNorm != null ? traNorm.getPaginaInicial() : "");
    	        normativaDetall.put("idioma_" + idioma + "_pagfin", traNorm != null ? traNorm.getPaginaFinal() : "");
    	        if (normativaLocal)
    	        	normativaDetall.put("idioma_" + idioma + "_responsable", "");	
    	        else {
    	        	TraduccionNormativaExterna normExt = (TraduccionNormativaExterna)traNorm;
    	        	normativaDetall.put("idioma_" + idioma + "_responsable", normExt != null ? normExt.getResponsable() : "");
    	        }
    	        	
    	        normativaDetall.put("idioma_" + idioma + "_observacions", traNorm != null ? traNorm.getObservaciones() : "");
    	        
    	        //archivo
    	        if (traNorm != null && traNorm.getArchivo() != null) {
    	        	normativaDetall.put("idioma_" + idioma + "_enllas_arxiu", "normativa/archivo.htm?id=" + normativa.getId() + "&lang=" + idioma);
    	        	normativaDetall.put("idioma_" + idioma + "_nom_arxiu", traNorm.getArchivo().getNombre());
    	        } else {
    	        	normativaDetall.put("idioma_" + idioma + "_enllas_arxiu", "");
    	        	normativaDetall.put("idioma_" + idioma + "_nom_arxiu", "");
    	        }
    	        
        	}
	        
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
	        	AfectacionTransient afeTran = new AfectacionTransient();	        	
	        	afeTran.setAfectacioId(afec.getTipoAfectacion().getId());
	        	
	        	TraduccionTipoAfectacion traTipAfec = (TraduccionTipoAfectacion)afec.getTipoAfectacion().getTraduccion(idiomaUsuario);
	        	if (traTipAfec == null)
	        		traTipAfec = (TraduccionTipoAfectacion)afec.getTipoAfectacion().getTraduccion();
	        	
	        	afeTran.setAfectacioNom(traTipAfec.getNombre());
	        	afeTran.setNormaId(normativaAfectada.getId());
	        	
	        	TraduccionNormativa traNormAfectada = (TraduccionNormativa)normativaAfectada.getTraduccion(idiomaUsuario);
	        	if (traNormAfectada == null)
	        		traNormAfectada = (TraduccionNormativa)normativaAfectada.getTraduccion();
	        	
	        	afeTran.setNormaNom(traNormAfectada != null ? obtenerTituloDeEnlaceHtml(traNormAfectada.getTitulo()) : "");
	        	afectadas.add(afeTran);
	        }	        
	        normativaDetall.put("afectacions", afectadas);
	        
	        //Procedimientos
	        Set<ProcedimientoLocal> listaProcedimientos = normativa.getProcedimientos();
	        for (ProcedimientoLocal proc : listaProcedimientos) {
	        	TraduccionProcedimientoLocal traProc = (TraduccionProcedimientoLocal)proc.getTraduccion(idiomaUsuario); 
	        	procedimientos.add(new ProcedimientoLocalTransient(proc.getId(), traProc != null ? traProc.getNombre() : "", null, null));
	        }
	        normativaDetall.put("procediments", procedimientos);
	        
	        
	    } catch (DelegateException dEx) {
            if (dEx.getCause() instanceof SecurityException) {
            	log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
        }
	    
        return normativaDetall;
	}
	
	@RequestMapping(value = "/guardar.htm", method = POST)
	public @ResponseBody String guardar(HttpSession session, HttpServletRequest request) {
		
		//TODO: no permitir paso de normativa externa a local y viceversa
		
    	Normativa normativa = null;
    	Normativa normativaOld = null; 		
		IdNomTransient result = null;
		boolean normativaLocal;
        
		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
		
        try {
        	
    		//Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando todos los parámetros en el map valoresForm y los que son de tipo fichero en ficherosForm.
    		
    		List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

    		for (FileItem item : items) {
    			if (item.isFormField()) {
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			} else {
    				ficherosForm.put(item.getFieldName(), item);    				
    			}
    		}
        	
        	NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        	
        	//Obtener la UA de la normativa. Si no tiene UA asignada es una normativa externa.
        	UnidadAdministrativa ua = null;
        	if ( valoresForm.get("item_ua_id") != null && !"".equals(valoresForm.get("item_ua_id")) ) {	
        		Long idUA = new Long(valoresForm.get("item_ua_id"));
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
    		    		
    		boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));
    		
        	if (edicion) {      		        		
				Long idNorm = Long.parseLong(valoresForm.get("item_id"));
        		normativaOld = normativaDelegate.obtenerNormativa(idNorm);
        		
        		//Comprobar permisos
            	if (!normativaDelegate.autorizaModificarNormativa(normativaOld.getId())) {
            		//throw new SecurityException("Aviso: No tiene privilegios para modificar la normativa");
            		return (new IdNomTransient(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()))).getJson();
            	}
            	
            	//Comprobar si se ha cambiado la validacion siendo operador
            	if (request.isUserInRole("sacoper") && !normativaOld.getValidacion().equals(new Integer(valoresForm.get("item_validacio")))) {
            		return (new IdNomTransient(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()))).getJson();
            	}
            		
        		
        		normativa.setAfectadas(normativaOld.getAfectadas());
        		normativa.setAfectantes(normativaOld.getAfectantes());        		
        		normativa.setProcedimientos(normativaOld.getProcedimientos());
        		normativa.setId(idNorm);
        	} else {
        		//Comprobar permisos
            	if (!normativaDelegate.autorizaCrearNormativa(Integer.parseInt(valoresForm.get("item_validacio")))) {
            		//throw new SecurityException("Aviso: No tiene privilegios para crear una normativa pública");            		
    				return (new IdNomTransient(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()))).getJson();
            	}
        	}
        	
        	//Campos por idioma
        	List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
        	for (String idioma : idiomas) {
        		TraduccionNormativa traNorm = normativaOld != null ? (TraduccionNormativa)normativaOld.getTraduccion(idioma) : null;
        		        		
        		if (traNorm != null) {
            		normativa.setTraduccion(idioma, traNorm);
        		}
        		else {
        			if (normativaLocal)
        				traNorm = new TraduccionNormativa();
        			else
        				traNorm = new TraduccionNormativaExterna();

        			normativa.setTraduccion(idioma, traNorm);
        		}

        		traNorm.setTitulo(valoresForm.get("item_titol_" + idioma));
        		traNorm.setEnlace(valoresForm.get("item_enllas_" + idioma));
        		traNorm.setApartado(valoresForm.get("item_apartat_" + idioma));
        		if (valoresForm.get("item_pagina_inicial_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_inicial_" + idioma)))
        			traNorm.setPaginaInicial(Integer.parseInt(valoresForm.get("item_pagina_inicial_" + idioma)));

        		if (valoresForm.get("item_pagina_final_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_final_" + idioma)))
        			traNorm.setPaginaFinal(Integer.parseInt(valoresForm.get("item_pagina_final_" + idioma)));        			

        		traNorm.setObservaciones(valoresForm.get("item_des_curta_" + idioma));     

        		//Responsable sólo en normativa externa
        		if (!normativaLocal) {        				
        			((TraduccionNormativaExterna)traNorm).setResponsable(valoresForm.get("item_responsable_" + idioma));
        		}
        		
        		//Archivo
        		FileItem fileItem = ficherosForm.get("item_arxiu_" + idioma);
        		if ( fileItem.getSize() > 0 ) {
        			traNorm.setArchivo(obtenerArchivo(traNorm.getArchivo(), fileItem));
        		} else
        		//borrar fichero si se solicita
        		if (valoresForm.get("item_arxiu_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_arxiu_" + idioma + "_delete"))){
        			traNorm.setArchivo(null);
        		}
        	}

        	//Los demás campos
        	if (valoresForm.get("item_numero") != null && !"".equals(valoresForm.get("item_numero")))
        		normativa.setNumero(Long.parseLong(valoresForm.get("item_numero")));

        	normativa.setLey(valoresForm.get("item_llei"));

        	if (valoresForm.get("item_registre") != null && !"".equals(valoresForm.get("item_registre")))
        		normativa.setRegistro(Long.parseLong(valoresForm.get("item_registre")));

        	if (valoresForm.get("item_data_butlleti") != null && !"".equals(valoresForm.get("item_data_butlleti"))) {
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		normativa.setFechaBoletin(dateFormat.parse(valoresForm.get("item_data_butlleti")));
        	}

        	if (valoresForm.get("item_data") != null && !"".equals(valoresForm.get("item_data"))) {
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		normativa.setFecha(dateFormat.parse(valoresForm.get("item_data")));
        	}       

        	if (valoresForm.get("item_tipus") != null && !"".equals(valoresForm.get("item_tipus"))) {
        		Tipo tipo = DelegateUtil.getTipoNormativaDelegate().obtenerTipoNormativa(new Long(valoresForm.get("item_tipus")));
        		normativa.setTipo(tipo);
        	}

        	if (valoresForm.get("item_validacio") != null && !"".equals(valoresForm.get("item_validacio")))
        		normativa.setValidacion(new Integer(valoresForm.get("item_validacio")));

        	//Boletín
        	if (valoresForm.get("item_butlleti_id") != null && !"".equals(valoresForm.get("item_butlleti_id"))) {
        		Boletin boletin = DelegateUtil.getBoletinDelegate().obtenerBoletin(Long.parseLong(valoresForm.get("item_butlleti_id")));
        		normativa.setBoletin(boletin);
        	}

        	if (normativaLocal) {
        		normativaDelegate.grabarNormativaLocal((NormativaLocal)normativa, ua.getId());
        	} else {
        		normativaDelegate.grabarNormativaExterna((NormativaExterna)normativa);
        	}
        	
    		//Afectaciones
    		ObjectMapper mapper = new ObjectMapper();    	
    		String jsonAfectaciones = valoresForm.get("afectaciones");
    		AfectacionesTransient afectaciones = mapper.readValue(jsonAfectaciones, AfectacionesTransient.class);

    		//Si estamos editando comparar la lista actual de afectaciones actual con la nueva para determinar qué añadir y qué eliminar.
    		if (edicion) {
    			Set<Afectacion> listaActualAfectaciones = normativa.getAfectadas();
    			for (Afectacion afectacionOld : listaActualAfectaciones) {
    				
    				//Buscar la afectación en la lista nueva
    				boolean estaEnLaListaNueva = false;
    				for (AfectacionTransient afectacionNew : afectaciones.getListaAfectaciones()) {
    					if (afectacionOld.getNormativa().getId().equals(afectacionNew.getNormaId()) && afectacionOld.getTipoAfectacion().getId().equals(afectacionNew.getAfectacioId()) ) {
    						estaEnLaListaNueva = true;
    						afectaciones.getListaAfectaciones().remove(afectacionNew);
    						break;
    					}
    				}
    				//Si no está en la lista nueva es que hay que eliminarla		
    				if (!estaEnLaListaNueva) {    				
    					normativaDelegate.eliminarAfectacion(normativa.getId(), afectacionOld.getTipoAfectacion().getId(), afectacionOld.getNormativa().getId());
    				}
    			}
    		}
    		
    		//Establecer las afectaciones indicadas en la pantalla
			for (AfectacionTransient afectacion : afectaciones.getListaAfectaciones()) {
				normativaDelegate.anyadirAfectacion(afectacion.getNormaId(), afectacion.getAfectacioId(), normativa.getId());
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
			
		} catch (FileUploadException e) {
			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomTransient(-3l, error);
			e.printStackTrace();
			
		} catch (UnsupportedEncodingException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomTransient(-2l, error);
			e.printStackTrace();
			
		} catch (JsonParseException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomTransient(-2l, error);
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		//Si en el caso de multipart no lo hacemos manualmente da problemas en el navegador, 
		//pues la respuesta intenta guardarla como una nueva página html.
		return result.getJson();
	}


	@RequestMapping(value = "/eliminar.htm", method = POST)
	public @ResponseBody IdNomTransient eliminar(HttpSession session, HttpServletRequest request) {
		Long id = new Long(request.getParameter("id"));
		NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
		
		try {
			if (normativaDelegate.autorizaModificarNormativa(id)) {			
				normativaDelegate.borrarNormativa(id);
			} else {
				return new IdNomTransient(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));				
			}
			
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
	
	
	@RequestMapping(value = "/cercarNormatives.htm", method = POST)
	public @ResponseBody Map<String, Object> cercarNormatives(HttpServletRequest request, HttpSession session)  {

		//Listar las normativas de la unidad administrativa
		List<Normativa>llistaNormatives = new ArrayList<Normativa>();
		List<NormativaTransient>llistaNormativesTransient = new ArrayList<NormativaTransient>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		Map<String, String> paramTrad = new HashMap<String, String>();
		
		String idioma = request.getLocale().getLanguage();
		
		//TODO obtener la ordenación por parámetro
		String campoOrdenacion = "normativa.fecha";
		String orden = "desc";		
		
		
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
			
			String titulo = request.getParameter("titol");
			if (titulo != null && !"".equals(titulo)) {
				titulo = titulo.toUpperCase();
				paramTrad.put("titulo", titulo);
			} else {
				paramTrad.put("idioma", idioma);
			}			
			
			
			//Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			
			llistaNormatives.addAll(normativaDelegate.buscarNormativas(paramMap, paramTrad, "local", null, false, campoOrdenacion, orden));
			llistaNormatives.addAll(normativaDelegate.buscarNormativas(paramMap, paramTrad, "externa", null, false, campoOrdenacion, orden));
			
			llistaNormativesTransient = pasarListaNormativasATransient(llistaNormatives, idioma);
			
			//Ordenar lista (por fecha descendente)		
			Collections.sort(llistaNormativesTransient);
			
			
		} catch (ParseException e) {			
			log.error("Error: " + e.getMessage());
					
		} catch (DelegateException dEx) {
			if (dEx.getCause() instanceof SecurityException) {
				log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
		}
		
		resultats.put("total", llistaNormativesTransient.size());
		resultats.put("nodes", llistaNormativesTransient);

		return resultats;
		
	}	
	
	/**
	 * Obtiene una lista de NormativaTransient a partir de una lista de Normativa.
	 * @param llistaNormatives Lista de Normativa.
	 * @param idioma Idioma seleccionado por el usuario.
	 */
	private List<NormativaTransient> pasarListaNormativasATransient(List<Normativa> llistaNormatives,	 String idioma) {
		
		List<NormativaTransient> llistaNormativesTransient = new ArrayList<NormativaTransient>();
		
		for (Normativa normativa : llistaNormatives) {
			
			TraduccionNormativa traNor = (TraduccionNormativa)normativa.getTraduccion(idioma);
			if (traNor == null)
				traNor = (TraduccionNormativa)normativa.getTraduccion();
							
			//Retirar posible enlace incrustado en titulo
			String titulo = traNor != null ? obtenerTituloDeEnlaceHtml(traNor.getTitulo()) : "";	
			
			TraduccionTipo traTip = normativa.getTipo() != null ? (TraduccionTipo)normativa.getTipo().getTraduccion(idioma) : null;

			String tipus  = traTip != null ? traTip.getNombre() : "";
			
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
		
		return llistaNormativesTransient;
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
		
		//Será el texto desde el primer '>' y desde ahí al primer '<'
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
	
	
	/**
	 * Convierte el objeto FileItem al objeto Archivo.
	 * @param archivo Objeto Archivo.
	 * @param fileItem Objeto FileItem.
	 * @return Objeto Archivo.
	 */
	private Archivo obtenerArchivo(Archivo archivo, FileItem fileItem) {
		if (archivo == null)
			archivo = new Archivo();
		
		archivo.setMime(fileItem.getContentType());
		archivo.setPeso(fileItem.getSize());
		archivo.setNombre(fileItem.getName());        			
		archivo.setDatos(fileItem.get());
		return archivo;
	}

}
