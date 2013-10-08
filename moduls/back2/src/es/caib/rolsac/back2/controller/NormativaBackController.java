package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ibit.rol.sac.model.Afectacion;
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
import org.ibit.rol.sac.model.dto.AfectacionDTO;
import org.ibit.rol.sac.model.dto.AfectacionesDTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.NormativaDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.eboib.EBoibSearchNormativa;
import org.ibit.rol.sac.persistence.eboib.SearchNormativa;
import org.ibit.rol.sac.persistence.eboib.TrListadoNormativaLocalBean;
import org.ibit.rol.sac.persistence.eboib.TrNormativaLocalBean;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/normativa/")
public class NormativaBackController extends PantallaBaseController
{
	private static Log log = LogFactory.getLog(NormativaBackController.class);
	
	@RequestMapping(value = "/normativa.do", method = GET)
	public String pantallaNormatives(Map<String, Object> model, HttpServletRequest request, HttpSession session)
	{
		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 4);
		model.put("titol_escriptori", "Normativa");
		model.put("escriptori", "pantalles/normativa.jsp");
		
		if (session.getAttribute("unidadAdministrativa") != null) {
			model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
			model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa());
		}
		
		String traspasboib = System.getProperty("es.caib.rolsac.traspasboib");
		if (traspasboib == null)
			traspasboib = "N";
		
		model.put("traspasboib", traspasboib);
		
		// Listas para el buscador. Las pasamos a DTO.
		try {
			String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			model.put("llistaButlletins", getListaBoletinesDTO());					// Boletines.
			model.put("llistaTipusNormativa", getListaTiposNormativaDTO(idioma));	// Tipos normativa.
			model.put("llistaTipusAfectacio", getListaTiposAfectacionDTO(idioma));	// Tipos afectación.
			
		} catch (DelegateException e) {
			if (e.isSecurityException()) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				log.error(ExceptionUtils.getStackTrace(e));
			}
		}
		
		loadIndexModel (model, request);
		return "index";
	}
	
	private List<IdNomDTO> getListaTiposAfectacionDTO(String idioma) throws DelegateException {

		List<TipoAfectacion> listaTiposAfectacion = DelegateUtil.getTipoAfectacionDelegate().listarTiposAfectaciones();
    	List<IdNomDTO> listaTiposAfectacionDTO = new ArrayList<IdNomDTO>();
    	for (TipoAfectacion tipoAfec : listaTiposAfectacion) {
    		TraduccionTipoAfectacion traTipAfec = (TraduccionTipoAfectacion)tipoAfec.getTraduccion(idioma);
    		if (traTipAfec == null)
    			traTipAfec = (TraduccionTipoAfectacion)tipoAfec.getTraduccion();
    		
    		IdNomDTO tipAfecTran = new IdNomDTO(tipoAfec.getId(), traTipAfec.getNombre());
    		listaTiposAfectacionDTO.add(tipAfecTran);
    	}
    	
		return listaTiposAfectacionDTO;
		
	}

	private List<IdNomDTO> getListaTiposNormativaDTO(String idioma) throws DelegateException {
		
		List<Tipo> listaTiposNormativa = DelegateUtil.getTipoNormativaDelegate().listarTiposNormativas();
    	List<IdNomDTO> listaTiposNormativaDTO = new ArrayList<IdNomDTO>();
    	for (Tipo tipo : listaTiposNormativa) {
    		TraduccionTipo traTipo = (TraduccionTipo)tipo.getTraduccion(idioma);
    		if (traTipo == null) 
    			traTipo = (TraduccionTipo)tipo.getTraduccion();
    		
    		IdNomDTO tipoTran;
    		if (traTipo != null)
    			tipoTran = new IdNomDTO(tipo.getId(), traTipo.getNombre());
    		else
    			tipoTran = new IdNomDTO(tipo.getId(), "");
    		
    		listaTiposNormativaDTO.add(tipoTran);
    	}
    	
		return listaTiposNormativaDTO;
		
	}

	private List<IdNomDTO> getListaBoletinesDTO() throws DelegateException {
		
		List<Boletin> listaBoletines = DelegateUtil.getBoletinDelegate().listarBoletines();
    	List<IdNomDTO> listaBoletinesDTO = new ArrayList<IdNomDTO>();
    	for (Boletin boletin : listaBoletines) {        		       		
    		IdNomDTO bol = new IdNomDTO(boletin.getId(), boletin.getNombre());
    		listaBoletinesDTO.add(bol);
    	}
    	
    	return listaBoletinesDTO;
		
	}

	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistatNormatives(HttpServletRequest request, HttpSession session)  {

		// Listar las normativas de la unidad administrativa.
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		Map<String, String> paramTrad = new HashMap<String, String>();
		List<NormativaDTO>llistaNormativesDTO= new ArrayList<NormativaDTO>();

        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		//Obtenemos la ordenación por parámetro.
		String campoOrdenacion = request.getParameter("ordreCamp");
		String orden = request.getParameter("ordreTipus");
		
		//Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas.
		boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));
		
		//Determinar si ha marcado el checkbox "Cerar a totes les meves unitats".
		boolean meves = "true".equals(request.getParameter("totesUnitats"));
        boolean uaFilles = "true".equals(request.getParameter("uaFilles"));
		
		Long idUA = null;
		
		if (request.getParameter("idUA") != null && !request.getParameter("idUA").equals("")){                      
			idUA = ParseUtil.parseLong(request.getParameter("idUA"));
		}
		
		try {
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			//Obtener parámetros de búsqueda.
			String idStr = request.getParameter("id");
			Long id = -1l;
									
			if ( idStr != null && StringUtils.isNumeric(idStr.trim()) )
				id = ParseUtil.parseLong( idStr.trim() );
			
			paramMap.put("id", idStr != null ? id : null );
			
			//Procesa el objeto request y añade los valores necesarios a los mapas de parámetros y de traducciones.
			procesarParametrosBusqueda(request, paramMap, paramTrad, lang);
						
			//Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			
			//Información de paginación.
			String pagPag = request.getParameter("pagPag");		
			String pagRes = request.getParameter("pagRes");
			
			if (pagPag == null) pagPag = String.valueOf(0); 
			if (pagRes == null) pagRes = String.valueOf(10);                			
			
			String queBuscar = "local";
			if (buscaExternas) {
				queBuscar = "todas";
			}
			
			resultadoBusqueda = normativaDelegate.buscarNormativas(
					paramMap, paramTrad, queBuscar, idUA, meves, uaFilles,
					campoOrdenacion, orden, pagPag, pagRes);
			
			for ( Normativa normativa : (List<Normativa>)resultadoBusqueda.getListaResultados() ) {

				boolean local = NormativaLocal.class.isInstance(normativa);
				normativa.setIdioma(lang);

				llistaNormativesDTO.add( new NormativaDTO(
							normativa.getId(), 
							normativa.getNumero()!=null?normativa.getNumero():0,
							obtenerTituloDeEnlaceHtml(normativa.getTraduccionTitulo()),
							normativa.getFecha(),
							normativa.getFechaBoletin(),
							normativa.getNombreBoletin(),
							normativa.getNombreTipo(),
							local ? "Local" : "Externa",
							normativa.isVisible())
				);
			}

			
		} catch (ParseException e) {
			log.error(ExceptionUtils.getStackTrace(e));
					
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
		}
		
		resultats.put("total",   resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaNormativesDTO);

		return resultats;
		
	}

	/**
	 * Procesa el objeto request y añade los valores necesarios a los mapas de parámetros y de traducciones.
	 * 
	 * @param request
	 * @param paramMap
	 * @param paramTrad
	 * @param idioma
	 * @throws ParseException
	 */
	private void procesarParametrosBusqueda(HttpServletRequest request, Map<String, Object> paramMap,
			Map<String, String> paramTrad, String idioma) throws ParseException {
		
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
			paramMap.put("numero", ParseUtil.parseLong(request.getParameter("numero")));
		
		if (request.getParameter("tipus") != null && !"".equals(request.getParameter("tipus")) )
			paramMap.put("tipo", ParseUtil.parseLong(request.getParameter("tipus")));
		
		if (request.getParameter("butlleti") != null && !"".equals(request.getParameter("butlleti")) )
			paramMap.put("boletin", ParseUtil.parseLong(request.getParameter("butlleti")));

		if (request.getParameter("registre") != null && !"".equals(request.getParameter("registre")) )
			paramMap.put("registro", request.getParameter("registre"));

		if (request.getParameter("llei") != null && !"".equals(request.getParameter("llei")) )
			paramMap.put("ley", request.getParameter("llei"));			
		
        if (request.isUserInRole("sacoper")) {
        	paramMap.put("validacion", "");
        } else {
            paramMap.put("validacion", request.getParameter("validacio"));
        }			
		
		// Textes (en todos los campos todos los idiomas).
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
		
	}
	
	
	@RequestMapping(value = "/cercaBoib.do", method = POST)
	public @ResponseBody Map<String, Object> llistatEnviamentsBoib(HttpServletRequest request, HttpSession session)
	{
		List<NormativaDTO>llistaNormativesDTO= new ArrayList<NormativaDTO>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		
		// Realizar la consulta y obtener resultados
		String numBoletin = request.getParameter("numeroboletin");
		String numRegistro = request.getParameter("numeroregistro");
		String fecha = request.getParameter("fecha");
		
		if (numBoletin != null) numBoletin = numBoletin.trim();
		if (numRegistro != null) numRegistro = numRegistro.trim();
		if (fecha != null) fecha = fecha.trim();
		
		SearchNormativa bdcons = new EBoibSearchNormativa(numBoletin, numRegistro, fecha);
		bdcons.makeSearch();
		
		if (bdcons.getNumeroNormativas() == -1)	//error
			resultats.put("errorMessage", bdcons.getMensajeavisobean().getCabecera() + ":" + bdcons.getMensajeavisobean().getSubcabecera());
		
		if (bdcons.getNumeroNormativas() > 0) {
			try {
				llistaNormativesDTO = pasarListaEboibADTO(bdcons.getListadonormativas(), DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());
			} catch (DelegateException dEx) {
				log.error("Error: " + dEx.getMessage());
			}
		}
		resultats.put("total", llistaNormativesDTO.size());
		resultats.put("nodes", llistaNormativesDTO);
		
		return resultats;
	}
	
	
	@RequestMapping(value = "/detallBoib.do", method = POST)
	public @ResponseBody Map<String, Object> detallBoib(HttpServletRequest request, Map<String, Object> model) {
			
	    Map<String, Object> normativaDetall = new HashMap<String, Object>();
	        
		SearchNormativa bdcons = new EBoibSearchNormativa(null, null, null);
        bdcons.makeSearchFromBoibRegistro(request.getParameter("id"));
        
        if (bdcons.getNumeroNormativas() == 1) {
	        TrNormativaLocalBean normativa = bdcons.getNormativabean();
	        
        	//Campos por idioma
	        normativaDetall.put("idioma_ca_titol", normativa.getTra_titulo_c());
	        normativaDetall.put("idioma_ca_enllac", normativa.getTra_enlace_c());
	        normativaDetall.put("idioma_ca_apartat", normativa.getTra_apartado_c());
	        normativaDetall.put("idioma_ca_pagini", normativa.getTra_paginaInicial_c());
	        normativaDetall.put("idioma_ca_pagfin", normativa.getTra_paginaFinal_c());
	        	
	        normativaDetall.put("idioma_es_titol", normativa.getTra_titulo_v());
	        normativaDetall.put("idioma_es_enllac", normativa.getTra_enlace_v());
	        normativaDetall.put("idioma_es_apartat", normativa.getTra_apartado_v());
	        normativaDetall.put("idioma_es_pagini", normativa.getTra_paginaInicial_v());
	        normativaDetall.put("idioma_es_pagfin", normativa.getTra_paginaFinal_v());
	        
	        normativaDetall.put("numero", normativa.getNumeroboib());
	        normativaDetall.put("butlleti_id", normativa.getIdBoletin());
	        normativaDetall.put("butlleti", normativa.getNombreBoletin());
	        normativaDetall.put("data_butlleti", normativa.getFechaBoletin() != null ? DateUtils.formatearddMMyyyy(normativa.getFechaBoletin()) : null);
	        normativaDetall.put("registre", normativa.getValorRegistro());
	        normativaDetall.put("validacio", normativa.getValidacion());
	        
        } else {
        	normativaDetall.put("error", bdcons.getMensajeavisobean().getCabecera() + ": " + bdcons.getMensajeavisobean().getSubcabecera());
        }

        return normativaDetall;
	}
	
	
	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request, Map<String, Object> model)
	{
		Map<String, Object> normativaDetall = new HashMap<String, Object>();
		
		try {
			String idiomaUsuario = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			Long id = ParseUtil.parseLong(request.getParameter("id"));
			
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
			normativaDetall.put("data_norma", normativa.getFecha() != null ? DateUtils.formatearddMMyyyy(normativa.getFecha()) : "");
			
			// Campos por idioma.
			List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			for (String idioma : idiomas) {
				TraduccionNormativa traNorm = (TraduccionNormativa)normativa.getTraduccion(idioma);
				agregarTraduccionNormativaADetalle(traNorm, idioma, normativaDetall, normativaLocal, normativa);
			}
			
			normativaDetall.put("numero", normativa.getNumero());
			normativaDetall.put("butlleti_id", normativa.getBoletin() != null ? normativa.getBoletin().getId() : null);
			normativaDetall.put("butlleti", normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : null);
			normativaDetall.put("data_butlleti", normativa.getFechaBoletin() != null ? DateUtils.formatearddMMyyyy(normativa.getFechaBoletin()) : null);
			normativaDetall.put("registre", normativa.getRegistro());
			normativaDetall.put("llei", normativa.getLey());
			normativaDetall.put("tipus", normativa.getTipo() != null ? normativa.getTipo().getId() : null);
			normativaDetall.put("validacio", normativa.getValidacion());
			// Normativas afectadas.
			normativaDetall.put("afectacions", getNormativasAfectadasDTO(normativa, idiomaUsuario));
			// Procedimientos asociados a la normativa.
			normativaDetall.put("procediments", getProcedimientosNormativaDTO(normativa, idiomaUsuario));
			
		} catch (DelegateException dEx) {
			log.error("Error: " + dEx.getMessage());
			if (dEx.isSecurityException())
				normativaDetall.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				normativaDetall.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
		}
		
		return normativaDetall;
	}
	
	private List<ProcedimientoLocalDTO> getProcedimientosNormativaDTO(Normativa normativa, String idiomaUsuario) {
		
        Set<ProcedimientoLocal> listaProcedimientos = normativa.getProcedimientos();
        List<ProcedimientoLocalDTO> procedimientos = new ArrayList<ProcedimientoLocalDTO>();
        
        for (ProcedimientoLocal proc : listaProcedimientos) {
        	TraduccionProcedimientoLocal traProc = (TraduccionProcedimientoLocal)proc.getTraduccion(idiomaUsuario); 
        	procedimientos.add(new ProcedimientoLocalDTO(proc.getId(), null, traProc != null ? traProc.getNombre() : "", null, null, null, null));
        }
        
		return procedimientos;
		
	}

	private List<AfectacionDTO> getNormativasAfectadasDTO(Normativa normativa, String idiomaUsuario) {
		
		Set<Afectacion> listaAfectadas = normativa.getAfectadas();
        List<AfectacionDTO> afectadas = new ArrayList<AfectacionDTO>();
        
        for (Afectacion afec : listaAfectadas) {
        	
        	Normativa normativaAfectada = afec.getNormativa();	        	        		        		       
        	AfectacionDTO afeTran = new AfectacionDTO();	        	
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
        
        return afectadas;
        
	}

	private void agregarTraduccionNormativaADetalle(TraduccionNormativa traNorm, String idioma,
			Map<String, Object> normativaDetall, boolean normativaLocal, Normativa normativa) {
		
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
        	normativaDetall.put("idioma_" + idioma + "_enllas_arxiu", "normativa/archivo.do?id=" + normativa.getId() + "&lang=" + idioma);
        	normativaDetall.put("idioma_" + idioma + "_nom_arxiu", traNorm.getArchivo().getNombre());
        } else {
        	normativaDetall.put("idioma_" + idioma + "_enllas_arxiu", "");
        	normativaDetall.put("idioma_" + idioma + "_nom_arxiu", "");
        }
		
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {	
		
		/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
    	Normativa normativa = null;
    	Normativa normativaOld = null; 		
		IdNomDTO result = null;
		boolean normativaLocal;
        
		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
		
        try {
        	
    		//Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		//Iremos recopilando los parámetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
    		
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
        		Long idUA = ParseUtil.parseLong(valoresForm.get("item_ua_id"));
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
				Long idNorm = ParseUtil.parseLong(valoresForm.get("item_id"));
        		normativaOld = normativaDelegate.obtenerNormativa(idNorm);
        		
        		//Comprobar permisos para modificar normativa
            	if (!normativaDelegate.autorizaModificarNormativa(normativaOld.getId())) {
    				IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
    				return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
            	}
            	
            	//Comprobar que si la normativa es local se ha indicado UA y que si es externa no se ha hecho
            	if (NormativaLocal.class.isInstance(normativaOld) && ua == null) {
    				IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.altres", null, request.getLocale()));
    				return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
            	} else if (NormativaExterna.class.isInstance(normativaOld) && ua != null) {
    				IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.altres", null, request.getLocale()));
    				return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
            	}
            	
            	//Comprobar que no se haya cambiado la validacion siendo operador
            	if (request.isUserInRole("sacoper") && !normativaOld.getValidacion().equals(ParseUtil.parseInt(valoresForm.get("item_validacio")))) {
    				IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
    				return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);            	
            	}
        		
        		normativa.setAfectadas(normativaOld.getAfectadas());
        		normativa.setAfectantes(normativaOld.getAfectantes());        		
        		normativa.setProcedimientos(normativaOld.getProcedimientos());
        		normativa.setId(idNorm);
        	} else {
        		//Comprobar permisos de creación
            	if (!normativaDelegate.autorizaCrearNormativa(ParseUtil.parseInt(valoresForm.get("item_validacio")))) {
    				IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
    				return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
            	}
        	}
        	
        	//Obtener campos por idioma
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

        		traNorm.setTitulo( RolUtil.limpiaCadena(valoresForm.get("item_titol_" + idioma)) );
        		traNorm.setEnlace( RolUtil.limpiaCadena(valoresForm.get("item_enllas_" + idioma)) );
        		traNorm.setApartado( RolUtil.limpiaCadena(valoresForm.get("item_apartat_" + idioma)) );
        		
        		if (valoresForm.get("item_pagina_inicial_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_inicial_" + idioma)))
        			traNorm.setPaginaInicial(ParseUtil.parseInt(valoresForm.get("item_pagina_inicial_" + idioma)));

        		if (valoresForm.get("item_pagina_final_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_final_" + idioma)))
        			traNorm.setPaginaFinal(ParseUtil.parseInt(valoresForm.get("item_pagina_final_" + idioma)));        			

        		//Campo comentado en Back2
        		//traNorm.setObservaciones(valoresForm.get("item_des_curta_" + idioma));     

        		//Responsable sólo en normativa externa
        		if (!normativaLocal) {        				
        			((TraduccionNormativaExterna)traNorm).setResponsable( RolUtil.limpiaCadena(valoresForm.get("item_responsable_" + idioma)) );
        		}

        		//Archivo
        		FileItem fileItem = ficherosForm.get("item_arxiu_" + idioma);
        		if (fileItem != null && fileItem.getSize() > 0 ) {
        			traNorm.setArchivo(UploadUtil.obtenerArchivo(traNorm.getArchivo(), fileItem));
        		} else
        		//borrar fichero si se solicita
        		if (valoresForm.get("item_arxiu_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_arxiu_" + idioma + "_delete"))){
        			traNorm.setArchivo(null);
        		}
        	}

        	//Obtener los demás campos
        	if (valoresForm.get("item_numero") != null && !"".equals(valoresForm.get("item_numero")))
        		normativa.setNumero(ParseUtil.parseLong(valoresForm.get("item_numero")));

        	normativa.setLey(valoresForm.get("item_llei"));

        	if (valoresForm.get("item_registre") != null && !"".equals(valoresForm.get("item_registre")))
        		normativa.setRegistro(ParseUtil.parseLong(valoresForm.get("item_registre")));

        	if (valoresForm.get("item_data_butlleti") != null && !"".equals(valoresForm.get("item_data_butlleti"))) {
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		normativa.setFechaBoletin(dateFormat.parse(valoresForm.get("item_data_butlleti")));
        	}

        	if (valoresForm.get("item_data_norma") != null && !"".equals(valoresForm.get("item_data_norma"))) {
        		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        		normativa.setFecha(dateFormat.parse(valoresForm.get("item_data_norma")));
        	}       

        	if (valoresForm.get("item_tipus") != null && !"".equals(valoresForm.get("item_tipus"))) {
        		Tipo tipo = DelegateUtil.getTipoNormativaDelegate().obtenerTipoNormativa(ParseUtil.parseLong(valoresForm.get("item_tipus")));
        		normativa.setTipo(tipo);
        	}

        	if (valoresForm.get("item_validacio") != null && !"".equals(valoresForm.get("item_validacio")))
        		normativa.setValidacion(ParseUtil.parseInt(valoresForm.get("item_validacio")));

        	if (valoresForm.get("item_butlleti_id") != null && !"".equals(valoresForm.get("item_butlleti_id"))) {
        		Boletin boletin = DelegateUtil.getBoletinDelegate().obtenerBoletin(ParseUtil.parseLong(valoresForm.get("item_butlleti_id")));
        		normativa.setBoletin(boletin);
        	}

        	//Guardar
        	Long idNormativa;
        	/* NOTA IMPORTANTE PARA EL RENDIMIENTO */
    		normativa.setProcedimientos(null);	// Con este null evitamos que hibernate vaya a actualizar tablas innecesarias
    		/* FIN NOTA */
        	if (normativaLocal) {
        		idNormativa = normativaDelegate.grabarNormativaLocal((NormativaLocal)normativa, ua.getId());
        	} else {
        		idNormativa = normativaDelegate.grabarNormativaExterna((NormativaExterna)normativa);
        	}
        	
        	//Actualizar estadísticas
        	DelegateUtil.getEstadisticaDelegate().grabarEstadisticaNormativa(idNormativa);
        	
        	if (isModuloModificado("modulo_afectaciones_modificado", valoresForm)){
            	
        		//Gestionar afectaciones
        		ObjectMapper mapper = new ObjectMapper();    	
        		String jsonAfectaciones = valoresForm.get("afectaciones");
        		AfectacionesDTO afectaciones = mapper.readValue(jsonAfectaciones, AfectacionesDTO.class);
    
        		//Si estamos editando comparar la lista actual de afectaciones actual con la nueva para determinar qu� a�adir y qu� eliminar.
        		if (edicion) {
        			Set<Afectacion> listaActualAfectaciones = normativa.getAfectadas();
        			for (Afectacion afectacionOld : listaActualAfectaciones) {
        				
        				//Buscar la afectaci�n afectacionOld en la lista nueva recibida en el post
        				boolean estaEnLaListaNueva = false;
        				for (AfectacionDTO afectacionNew : afectaciones.getListaAfectaciones()) {
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
        		
        		//Añadir afectaciones
    			for (AfectacionDTO afectacion : afectaciones.getListaAfectaciones()) {
    				normativaDelegate.anyadirAfectacion(afectacion.getNormaId(), afectacion.getAfectacioId(), normativa.getId());
    			}         	
        	
        	}
			
			//Finalizado correctamente
        	result = new IdNomDTO(normativa.getId(), messageSource.getMessage("normativa.guardat.correcte", null, request.getLocale()) );
        	
        } catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}        	        	
        } catch (ParseException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);			
			log.error(ExceptionUtils.getStackTrace(e));
			
		} catch (FileUploadException e) {
			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));
			
		} catch (UnsupportedEncodingException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
			
		} catch (JsonParseException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
			
		} catch (IOException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);			
			log.error(ExceptionUtils.getStackTrace(e));
		}
		
		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
	}


	@RequestMapping(value = "/eliminar.do", method = POST)
	public @ResponseBody IdNomDTO eliminar(HttpSession session, HttpServletRequest request) {
		Long id = ParseUtil.parseLong(request.getParameter("id"));
		NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
		
		try {
			if (normativaDelegate.autorizaModificarNormativa(id)) {			
				normativaDelegate.borrarNormativa(id);
			} else {
				return new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));				
			}
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				return new IdNomDTO(-1l, error);
			} else {
				log.error(ExceptionUtils.getStackTrace(dEx));
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				return new IdNomDTO(-2l, error);				
			}        	        	
        }
		
        return  new IdNomDTO(id, messageSource.getMessage("normativa.eliminat.correcte", null, request.getLocale()));		
	}
	
	
	@RequestMapping(value = "/cercarNormatives.do", method = POST)
	public @ResponseBody Map<String, Object> cercarNormatives(HttpServletRequest request, HttpSession session)
	{
		//Listar las normativas de la unidad administrativa
		List<Normativa>llistaNormatives = new ArrayList<Normativa>();
		List<NormativaDTO>llistaNormativesDTO = new ArrayList<NormativaDTO>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> paramTrad = new HashMap<String, String>();
		
		//TODO obtener la ordenacion por parametro
		String campoOrdenacion = "fecha";
		String orden = "desc";
		
		try {
			String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			// Obtener parametros de busqueda
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
			
			// Información de paginación
			String pagPag = request.getParameter("pagPagina");
			String pagRes = request.getParameter("pagRes");
			
			if (pagPag == null) pagPag = String.valueOf(0);
			if (pagRes == null) pagRes = String.valueOf(10);
			
			// Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			ResultadoBusqueda resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, "todas", null, false, false, campoOrdenacion, orden, pagPag, pagRes);
			resultats.put("total", resultadoBusqueda.getTotalResultados());
			
			llistaNormatives.addAll((List<Normativa>) resultadoBusqueda.getListaResultados());
			llistaNormativesDTO = pasarListaNormativasADTO(llistaNormatives, idioma);
			
		} catch (ParseException e) {
			log.error("Error: " + e.getMessage());
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException())
				log.error("Permisos insuficients: " + dEx.getMessage());
			else
				log.error("Error: " + dEx.getMessage());
		}
		
		resultats.put("nodes", llistaNormativesDTO);
		return resultats;
	}	

	/**
	 * Obtiene una lista de NormativaDTO a partir de una lista de env�os de eboib
	 * @param listadonormativas
	 * @param idioma
	 * @return
	 */
	private List<NormativaDTO> pasarListaEboibADTO(List<TrListadoNormativaLocalBean> listadonormativas, String idioma) {

		List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
		
		for (TrListadoNormativaLocalBean normativa : listadonormativas) {

			//Retirar posible enlace incrustado en titulo
			llistaNormativesDTO.add(
						new NormativaDTO(
								0, 
								Long.parseLong(normativa.getBoib()), 
								normativa.getTitulo(), 
								null,
								normativa.getFechaBoletin(),
								null,
								"BOIB",
								true,
								normativa.getRegistro()
								)
					);
		}
		
		return llistaNormativesDTO;

	
	}	
	
	/**
	 * Obtiene una lista de NormativaDTO a partir de una lista de Normativa.
	 * @param llistaNormatives Lista de Normativa.
	 * @param idioma Idioma seleccionado por el usuario.
	 * @throws DelegateException 
	 */
	private List<NormativaDTO> pasarListaNormativasADTO(List<Normativa> llistaNormatives, String idioma) throws DelegateException {
		
		List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
		
		for (Normativa normativa : llistaNormatives) {
			
			//TraduccionNormativa traNor = (TraduccionNormativa)normativa.getTraduccion(idioma);
			//if (traNor == null)
				//traNor = (TraduccionNormativa)normativa.getTraduccion();
			String traNor = normativa.getTraduccionTitulo();
			if (traNor == null) {
				NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
		        Normativa norm = normativaDelegate.obtenerNormativa(normativa.getId());
		        TraduccionNormativa traduc = (TraduccionNormativa)norm.getTraduccion();
		        traNor = traduc.getTitulo();
			}
				
			
							
			//Retirar posible enlace incrustado en titulo
			String titulo = traNor != null ? obtenerTituloDeEnlaceHtml(traNor) : "";	
			
			TraduccionTipo traTip = normativa.getTipo() != null ? (TraduccionTipo)normativa.getTipo().getTraduccion(idioma) : null;

			String tipus  = traTip != null ? traTip.getNombre() : "";
			
			boolean local = NormativaLocal.class.isInstance(normativa);
			
			llistaNormativesDTO.add(
						new NormativaDTO(
								normativa.getId() != null ? normativa.getId().longValue() : 0, 
								normativa.getNumero() != null ? normativa.getNumero().longValue() : -1l, 
								titulo, 
								normativa.getFecha(),
								normativa.getFechaBoletin(),
								normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : "",
								tipus,
								local ? "Local" : "Externa",
								normativa.isVisible())
					);
		}
		
		return llistaNormativesDTO;
	}	
		
	//TODO: mover a clase de utilidades.
	/**
	 * De un string que contiene un enlace HTML extrae el t�tulo del enlace.
	 * @param texto String que contiene el enlace.
	 * @return T�tulo del enlace, si no hay enlace devuelve el texto tal cual.
	 */
	private static String obtenerTituloDeEnlaceHtml(String texto) {
		if (texto == null)
			return null;
		
		String tmp = texto;
		
		//Ser� el texto desde el primer '>' y desde ah� al primer '<'
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
     * Devuelve true si ha habido algun cambio en el modulo.
     * 
     * @param modulo
     * @param valoresForm
     * @return boolean
     */
    private boolean isModuloModificado(String modulo, Map<String, String> valoresForm) {
        return "1".equals(valoresForm.get(modulo));
    }
    
}