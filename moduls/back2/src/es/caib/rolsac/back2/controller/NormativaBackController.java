package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.DocumentoNormativa;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionDocumentoNormativa;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionServicio;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.TraduccionTipoAfectacion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadNormativa;
import org.ibit.rol.sac.model.ValidacionNormativa;
import org.ibit.rol.sac.model.dto.AfectacionDTO;
import org.ibit.rol.sac.model.dto.AfectacionesDTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.NormativaDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.model.dto.ServicioDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.DocumentoNormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadNormativaDelegate;
import org.ibit.rol.sac.persistence.eboib.EBoibSearchNormativa;
import org.ibit.rol.sac.persistence.eboib.SearchNormativa;
import org.ibit.rol.sac.persistence.eboib.TrListadoNormativaBean;
import org.ibit.rol.sac.persistence.eboib.TrNormativaBean;
import org.ibit.rol.sac.persistence.util.DateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.CSVUtil;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;
import net.sf.hibernate.Hibernate;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/normativa/")
public class NormativaBackController extends PantallaBaseController {
    private static Log log = LogFactory.getLog(NormativaBackController.class);

    @RequestMapping(value = "/normativa.do", method = GET)
    public String pantalla(Map<String, Object> model, HttpServletRequest request, HttpSession session) {
    	
        model.put("menu", 0);
        model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 4);
        model.put("titol_escriptori", "Normativa");
        model.put("escriptori", "pantalles/normativa.jsp");

        if (session.getAttribute("unidadAdministrativa") != null) {
            model.put("idUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getId());
            model.put("nomUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa());
        }
        
        String traspasboib = System.getProperty("es.caib.rolsac.traspasboib");
        if (traspasboib == null) {
            traspasboib = "N";
        }

        model.put("traspasboib", traspasboib);

        // Listas para el buscador. Las pasamos a DTO.
        try {
            String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            // Boletines.
            model.put("llistaButlletins", getListaBoletinesDTO());
            // Tipos normativa.
            model.put("llistaTipusNormativa", getListaTiposNormativaDTO(idioma));
            // Tipos afectacion.
            model.put("llistaTipusAfectacio", getListaTiposAfectacionDTO(idioma));

        } catch (DelegateException e) {
            if (e.isSecurityException()) {
                model.put("error", "permisos");
            } else {
                model.put("error", "altres");
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }

        loadIndexModel(model, request);
        return "index";
    }

    private List<IdNomDTO> getListaTiposAfectacionDTO(String idioma) throws DelegateException {

        List<TipoAfectacion> listaTiposAfectacion = DelegateUtil.getTipoAfectacionDelegate().listarTiposAfectaciones();
        List<IdNomDTO> listaTiposAfectacionDTO = new ArrayList<IdNomDTO>();
        for (TipoAfectacion tipoAfec : listaTiposAfectacion) { 
            TraduccionTipoAfectacion traTipAfec = (TraduccionTipoAfectacion) tipoAfec.getTraduccion(idioma);
            if (traTipAfec == null) {
                traTipAfec = (TraduccionTipoAfectacion) tipoAfec.getTraduccion();
            }

            IdNomDTO tipAfecTran = new IdNomDTO(tipoAfec.getId(), traTipAfec.getNombre());
            listaTiposAfectacionDTO.add(tipAfecTran);
        }

        return listaTiposAfectacionDTO;

    }

    private List<IdNomDTO> getListaTiposNormativaDTO(String idioma) throws DelegateException {

        List<Tipo> listaTiposNormativa = DelegateUtil.getTipoNormativaDelegate().listarTiposNormativas();
        List<IdNomDTO> listaTiposNormativaDTO = new ArrayList<IdNomDTO>();
        for (Tipo tipo : listaTiposNormativa) { 
            TraduccionTipo traTipo = (TraduccionTipo) tipo.getTraduccion(idioma);
            if (traTipo == null) {
                traTipo = (TraduccionTipo) tipo.getTraduccion();
            }

            IdNomDTO tipoTran;
            if (traTipo != null) {
                tipoTran = new IdNomDTO(tipo.getId(), traTipo.getNombre());
            } else {
                tipoTran = new IdNomDTO(tipo.getId(), "");
            }

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
    public @ResponseBody Map<String, Object> llistat(HttpServletRequest request, HttpSession session) {

        // Listar las normativas de la unidad administrativa.
        Map<String, Object> resultats = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> paramTrad = new HashMap<String, String>();
        List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();

        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

        // Obtenemos la ordenacion por parametro.
        String campoOrdenacion = request.getParameter("ordreCamp");
        String orden = request.getParameter("ordreTipus");

        // Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas.
        boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));

        // Determinar si ha marcado el checkbox "Cerar a totes les meves unitats".
        boolean meves = "true".equals(request.getParameter("totesUnitats"));
        boolean uaFilles = "true".equals(request.getParameter("uaFilles"));
        String invalids = request.getParameter("invalids");

        Long idUA = null;

        if (request.getParameter("idUA") != null && !request.getParameter("idUA").equals("")) {
            idUA = ParseUtil.parseLong(request.getParameter("idUA"));
        }
        
        

        try {
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            // Obtener parametros de bÃºsqueda.
            String idStr = request.getParameter("id");
            Long id = -1l;

            if (idStr != null && StringUtils.isNumeric(idStr.trim()))
                id = ParseUtil.parseLong(idStr.trim());

            paramMap.put("id", idStr != null ? id : null);

            // Procesa el objeto request y anyade los valores necesarios a los mapas de parametros y de traducciones.
            procesarParametrosBusqueda(request, paramMap, paramTrad, lang);

            // Realizar la consulta y obtener resultados
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            // Informacion de paginacion.
            String pagPag = request.getParameter("pagPag");
            String pagRes = request.getParameter("pagRes");

            if (pagPag == null) {
                pagPag = String.valueOf(0);
            }

            if (pagRes == null) {
                pagRes = String.valueOf(10);
            }

            String queBuscar = "local";
            if (buscaExternas) {
                queBuscar = "todas"; 
            }

            resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, queBuscar, idUA, meves, uaFilles, invalids, campoOrdenacion, orden, pagPag, pagRes, false);

            for (Normativa normativa : (List<Normativa>) resultadoBusqueda.getListaResultados()) {
            	String color = "";
                if (!normativa.isDatosValidos()) {
                	color = "red";
                }
                
                normativa.setIdioma(lang);
                llistaNormativesDTO.add(new NormativaDTO(normativa.getId(), normativa.getNumero() != null ? normativa.getNumero() : 0, obtenerTituloDeEnlaceHtml(normativa.getTraduccionTitulo()),
                    normativa.getFecha(), normativa.getFechaBoletin(), normativa.getNombreBoletin(), normativa.getNombreTipo(), "normativa", normativa.isVigente(), normativa.getNumNormativa(), color));
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

        resultats.put("total", resultadoBusqueda.getTotalResultados());
        resultats.put("nodes", llistaNormativesDTO);

        return resultats;
    }

	@RequestMapping(value = "/exportar.do", method = POST)
    public void exportar(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {

        // Listar las normativas de la unidad administrativa.
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> paramTrad = new HashMap<String, String>();

        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

        // Obtenemos la ordenacion por parametro.
        String campoOrdenacion = request.getParameter("ordreCamp");
        String orden = request.getParameter("ordreTipus");

        // Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas.
        boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));

        // Determinar si ha marcado el checkbox "Cerar a totes les meves unitats".
        boolean meves = "true".equals(request.getParameter("totesUnitats"));
        boolean uaFilles = "true".equals(request.getParameter("uaFilles"));
        String invalids = request.getParameter("invalids");
        
        Long idUA = null;

        if (request.getParameter("idUA") != null && !request.getParameter("idUA").equals("")) {
            idUA = ParseUtil.parseLong(request.getParameter("idUA"));
        }

        try {
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            // Obtener parametros de busqueda.
            String idStr = request.getParameter("id");
            Long id = -1l;

            if (idStr != null && StringUtils.isNumeric(idStr.trim()))
                id = ParseUtil.parseLong(idStr.trim());

            paramMap.put("id", idStr != null ? id : null);

            // Procesa el objeto request y anyade los valores necesarios a los mapas de parametros y de traducciones.
            procesarParametrosBusqueda(request, paramMap, paramTrad, lang);

            // Realizar la consulta y obtener resultados
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            // Informacion de paginacion.
            String pagPag = request.getParameter("pagPag");
            String pagRes = request.getParameter("pagRes");

            if (pagPag == null) {
                pagPag = String.valueOf(0);
            }

            if (pagRes == null) {
                pagRes = String.valueOf(10);
            }

            String queBuscar = "local";
            if (buscaExternas) {
                queBuscar = "todas";
            }

            resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, queBuscar, idUA, meves, uaFilles, invalids, campoOrdenacion, orden, pagPag, pagRes, true);
            CSVUtil.mostrarCSV(response, convertirNormativaToCSV((List<Object[]>) resultadoBusqueda.getListaResultados()));

        } catch (ParseException e) {
            log.error(ExceptionUtils.getStackTrace(e));

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                log.error("Error: " + dEx.getMessage());
            }
        }
    }
    
    private String convertirNormativaToCSV(List<Object[]> listaResultados) throws UnsupportedEncodingException {
    	StringBuffer retorno = new StringBuffer();
		
		//cabecera!
		retorno.append("CODI_NORMA;");
		retorno.append("NOM_NORMA_CA;");
		retorno.append("NOM_NORMA_ES;");
		retorno.append("ESTAT_NORMA;");
		///Siempre visible: retorno.append("VISIBILITAT_NORMA;");
		retorno.append("NOM UA;");
		retorno.append("RANG_LEGAL;");
		retorno.append("TIPUS_BUTLLETI;");
		retorno.append("NUM_NORMA;");
		retorno.append("ENLLA"+new String(new byte[]{(byte)199})+";");
		retorno.append("DATA_APROVACIO;");
		retorno.append("\n");
	
		
		NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
		for ( Object[] resultado :  listaResultados ) {
			Normativa normativa;
			final Long idNormativa = Long.valueOf(resultado[0].toString());
			try {
				normativa = normativaDelegate.obtenerNormativa(idNormativa);
			} catch (Exception exception) {
				log.error("Error obteniendo la normativa con id : " + idNormativa , exception);
				retorno.append(CSVUtil.limpiar(idNormativa));
				retorno.append(ExceptionUtils.getCause(exception));
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}
			
			//Extraemos datos.
			TraduccionNormativa tradEs = (TraduccionNormativa) normativa.getTraduccion("es");
			TraduccionNormativa tradCa = (TraduccionNormativa) normativa.getTraduccion("ca");
			
			String nomEs, nomCa;
			if (tradEs == null) {
				nomEs = "";
			} else {
				nomEs = tradEs.getTitulo();
			}

			if (tradCa == null) {
				nomCa = "";
			} else {
				nomCa = tradCa.getTitulo();
			}
			
			String rangLegal = "";
			if (normativa.getTipo() != null) {
				TraduccionTipo tradEsT = (TraduccionTipo) normativa.getTipo().getTraduccion("es");
				TraduccionTipo tradCaT = (TraduccionTipo) normativa.getTipo().getTraduccion("ca");
				if (tradCaT != null) {
					rangLegal = tradCaT.getNombre();
				} else if(tradEsT != null) {
					rangLegal = tradEsT.getNombre();
				}
			}
			String estado;
			if (normativa.getValidacion().compareTo(1) == 0) {
				estado = "VIGENT";
			} else {
				estado = "DEROGADA";
			}
			
			String bolletiEnllac, bolletiTipus, bolletiNum;
			if (normativa.getBoletin() == null) {
				bolletiEnllac = "";
				bolletiTipus = "";
			} else {
				bolletiEnllac = normativa.getBoletin().getEnlace();
				bolletiTipus = normativa.getBoletin().getNombre();
			}
			if (normativa.getNumero() == null) {
				bolletiNum = "";
			} else {
				bolletiNum = normativa.getNumero().toString();
			}
			
			
			
			
			retorno.append(CSVUtil.limpiar(normativa.getId())); 	//CODI_NORMA,
			retorno.append(CSVUtil.limpiar(nomCa)); 				//NOM_NORMA_CA,
			retorno.append(CSVUtil.limpiar(nomEs)); 				//NOM_NORMA_ES,
			retorno.append(CSVUtil.limpiar(estado)); 				//ESTAT_NORMA DECODE(NOR_VALIDA,1,'PUBLIC',2,'INTERN','RESERVA'),
			//retorno.append(CSVUtil.limpiar(normativa.isVisible()));	//VISIBILITAT_NORMA (ESTAT+DATA_PUB+DATA_CAD + UA_VISIBLE),
			//retorno.append(CSVUtil.limpiar(tipoNormativa));			//TIPUS_NORMATIVA (LOCAL, EXTERNA),
			StringBuffer UA = new StringBuffer();
			for(final UnidadNormativa unidadNormativa : normativa.getUnidadesnormativas()) {
				UA.append(CSVUtil.getNombreUA(unidadNormativa.getUnidadAdministrativa())+",");
			}
			retorno.append(CSVUtil.limpiar(UA.toString())); 	//NOM_UA
			retorno.append(CSVUtil.limpiar(rangLegal)); 			//RANG LEGAL (LLEI, DECRETET,...)
			retorno.append(CSVUtil.limpiar(bolletiTipus)); 			//TIPUS_BUTLLETÍ,
			retorno.append(CSVUtil.limpiar(normativa.getNumNormativa())); 			//NUM_NORMA,
			retorno.append(CSVUtil.limpiar(bolletiEnllac)); 		//ENLLAÇ,
			retorno.append(CSVUtil.limpiar(normativa.getFechaBoletin())); //DATA_APROVACIO
			retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
		}
		
		return retorno.toString();		
	}

	


    /**
     * Procesa el objeto request y anyade los valores necesarios a los mapas de
     * parametros y de traducciones.
     * 
     * @param request
     * @param paramMap
     * @param paramTrad
     * @param idioma
     * @throws ParseException
     */
    private void procesarParametrosBusqueda(HttpServletRequest request, Map<String, Object> paramMap, Map<String, String> paramTrad, String idioma) throws ParseException {

        if (request.getParameter("data_butlleti") != null && !request.getParameter("data_butlleti").equals("")) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dataButlleti = df.parse(request.getParameter("data_butlleti"));
            paramMap.put("fechaBoletin", dataButlleti);
        }
        
        if (request.getParameter("numNormativa") != null && !"".equals(request.getParameter("numNormativa"))) {
            paramMap.put("numNormativa", request.getParameter("numNormativa"));
        }
        
        if (request.getParameter("dataAprovacio") != null && !request.getParameter("dataAprovacio").equals("")) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAprobacio = df.parse(request.getParameter("dataAprovacio"));
            paramMap.put("dataAprovacio", dataAprobacio);
        }
        
       
        if (request.getParameter("tipus") != null && !"".equals(request.getParameter("tipus"))) {
            paramMap.put("tipo", ParseUtil.parseLong(request.getParameter("tipus")));
        }
        if (request.getParameter("butlleti") != null && !"".equals(request.getParameter("butlleti"))) {
            paramMap.put("boletin", ParseUtil.parseLong(request.getParameter("butlleti")));
        }
        if (request.getParameter("llei") != null && !"".equals(request.getParameter("llei"))) {
            paramMap.put("ley", request.getParameter("llei"));
        }

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
    public @ResponseBody
    Map<String, Object> llistatEnviamentsBoib(HttpServletRequest request, HttpSession session) {

        List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
        Map<String, Object> resultats = new HashMap<String, Object>();

        // Realizar la consulta y obtener resultados
        String numBoletin = request.getParameter("numeroboletin");
        String numRegistro = request.getParameter("numeroregistro");
        String fecha = request.getParameter("fecha");

        if (numBoletin != null) {
            numBoletin = numBoletin.trim();
        }
        if (numRegistro != null) {
            numRegistro = numRegistro.trim();
        }
        if (fecha != null) {
            fecha = fecha.trim();
        }

        SearchNormativa bdcons = new EBoibSearchNormativa(numBoletin, numRegistro, fecha);
        bdcons.makeSearch();

        // Error
        if (bdcons.getNumeroNormativas() == -1) {
            resultats.put("errorMessage", bdcons.getMensajeavisobean().getCabecera() + ":" + bdcons.getMensajeavisobean().getSubcabecera());
        }

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
    public @ResponseBody
    Map<String, Object> detallBoib(HttpServletRequest request, Map<String, Object> model) throws DelegateException {

        Map<String, Object> normativaDetall = new HashMap<String, Object>();

        SearchNormativa bdcons = new EBoibSearchNormativa(null, null, null);
        bdcons.makeSearchFromBoibRegistro(request.getParameter("id"));

        if (bdcons.getNumeroNormativas() == 1) {
            TrNormativaBean normativa = bdcons.getNormativabean();

            // Campos por idioma
            normativaDetall.put("idioma_ca_titol", normativa.getTra_titulo_c());
            normativaDetall.put("idioma_ca_enllac", normativa.getTra_enlace_c());
            normativaDetall.put("idioma_ca_apartat", normativa.getTra_apartado_c());
            normativaDetall.put("idioma_ca_pagini", normativa.getTra_paginaInicial_c());
            normativaDetall.put("idioma_ca_pagfin", normativa.getTra_paginaFinal_c());
            normativaDetall.put("idioma_ca_tipo_publicacion", normativa.getTipoPublicacion_c());

            normativaDetall.put("idioma_es_titol", normativa.getTra_titulo_v());
            normativaDetall.put("idioma_es_enllac", normativa.getTra_enlace_v());
            normativaDetall.put("idioma_es_apartat", normativa.getTra_apartado_v());
            normativaDetall.put("idioma_es_pagini", normativa.getTra_paginaInicial_v());
            normativaDetall.put("idioma_es_pagfin", normativa.getTra_paginaFinal_v());
            normativaDetall.put("idioma_es_tipo_publicacion", normativa.getTipoPublicacion_v());

            normativaDetall.put("numero", normativa.getNumeroboib());
            normativaDetall.put("butlleti_id", normativa.getIdBoletin());
            normativaDetall.put("butlleti", normativa.getNombreBoletin());
            normativaDetall.put("data_butlleti", normativa.getFechaBoletin() != null ? DateUtils.formatearddMMyyyy(normativa.getFechaBoletin()) : null);
            normativaDetall.put("registre", normativa.getValorRegistro());
            normativaDetall.put("validacio", normativa.getValidacion());

            if (normativa.getIdTipoNormativa() != null) {
            	String nuevoId = "";
            	Tipo tipo = DelegateUtil.getTipoNormativaDelegate().obtenerTipoNormativaByBOIB(normativa.getIdTipoNormativa());
            	if (tipo != null) {
            		nuevoId = tipo.getId().toString();
            	}
            	normativaDetall.put("tipo_normativa_id", nuevoId);
            }
            
        } else {
            normativaDetall.put("error", bdcons.getMensajeavisobean().getCabecera() + ": " + bdcons.getMensajeavisobean().getSubcabecera());
        }

        return normativaDetall;
    }

    @RequestMapping(value = "/pagDetall.do", method = POST)
    public @ResponseBody
    Map<String, Object> recuperaDetall(Long id, HttpServletRequest request, Map<String, Object> model) {

        Map<String, Object> normativaDetall = new HashMap<String, Object>();

        try {
        	
        	String lang;
    		
    		try {
    			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
    		} catch (DelegateException dEx) {
    			log.error("Error al recuperar el idioma por defecto.");
    			lang = "ca";
    		}
    		
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
            Normativa normativa = normativaDelegate.obtenerNormativa(id);

            normativaDetall.put("idUA", null);
            normativaDetall.put("nomUA", null);
            /*
            if (normativa.getUnidadAdministrativa() != null) {
                normativaDetall.put("idUA", normativa.getUnidadAdministrativa().getId());
                normativaDetall.put("nomUA", normativa.getUnidadAdministrativa().getNombreUnidadAdministrativa());
            } else {
                normativaDetall.put("idUA", null);
                normativaDetall.put("nomUA", null);
            }*/

            normativaDetall.put("id", normativa.getId());
            normativaDetall.put("data_norma", normativa.getFecha() != null ? DateUtils.formatearddMMyyyy(normativa.getFecha()) : "");

            // Campos por idioma.
            List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
            for (String idioma : idiomas) {
                TraduccionNormativa traNorm = (TraduccionNormativa) normativa.getTraduccion(idioma);
                agregarTraduccionNormativaADetalle(traNorm, idioma, normativaDetall,  normativa);
            }

            
            
            normativaDetall.put("numNormativa", normativa.getNumNormativa());
            normativaDetall.put("numero", normativa.getNumero());
            normativaDetall.put("butlleti_id", normativa.getBoletin() != null ? normativa.getBoletin().getId() : null);
            normativaDetall.put("butlleti", normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : null);
            normativaDetall.put("data_butlleti", normativa.getFechaBoletin() != null ? DateUtils.formatearddMMyyyy(normativa.getFechaBoletin()) : null);
            normativaDetall.put("registre", normativa.getRegistro());
            normativaDetall.put("llei", normativa.getLey());
            normativaDetall.put("tipus", normativa.getTipo() != null ? normativa.getTipo().getId() : null);
            normativaDetall.put("validacio", normativa.getValidacion());
            normativaDetall.put("documents", recuperaDocumentosRelacionados(normativa.getDocumentos(), id, idiomas));
            List<UnidadAdministrativa> uas = new ArrayList<UnidadAdministrativa>();
            if (normativa.getUnidadesnormativas() != null) {
            	for(UnidadNormativa unaNormativa : normativa.getUnidadesnormativas()) {
            		uas.add(unaNormativa.getUnidadAdministrativa());
            	}
            }
            normativaDetall.put("uas", CargaModulosLateralesUtil.recuperaUAsRelacionadas(uas, normativa.getId(), lang, false));
			normativaDetall.put("datosValidos", normativa.getDatosValidos());

        } catch (DelegateException dEx) {
            log.error("Error: " + dEx.getMessage());
            if (dEx.isSecurityException()) {
                normativaDetall.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
            } else {
                normativaDetall.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
            }
        }

        return normativaDetall;
    }
    
    
    @RequestMapping(value = "/carregarDocument.do")
    public @ResponseBody
    Map<String, Object> carregarDocument(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();

        try {

            Long id = new Long(request.getParameter("id"));
            
            DocumentoNormativa doc = DelegateUtil.getNormativaDelegate().obtenerDocumentoNormativa(id); 
            
            Map<String, Object> mapDoc = new HashMap<String, Object>();
            IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
            List<String> idiomas = idiomaDelegate.listarLenguajes();
            TraduccionDocumentoNormativa traDoc;

            for (String idioma : idiomas) {
                traDoc = (TraduccionDocumentoNormativa) doc.getTraduccion(idioma);

                if (traDoc != null) {
                    if (traDoc.getTitulo() != null) {
                        mapDoc.put("idioma_titol_" + idioma, traDoc.getTitulo());
                    } else {
                        mapDoc.put("idioma_titol_" + idioma, "");
                    }

                    if (traDoc.getDescripcion() != null) {
                        mapDoc.put("idioma_descripcio_" + idioma, traDoc.getDescripcion());
                    } else {
                        mapDoc.put("idioma_descripcio_" + idioma, "");
                    }

                    // archivo
                    if (traDoc.getArchivo() != null) {
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "normativa/archivoNormativa.do?id=" + doc.getId() + "&lang=" + idioma);
                        mapDoc.put("idioma_nom_arxiu_" + idioma, traDoc.getArchivo().getNombre());

                    } else {
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "");
                        mapDoc.put("idioma_nom_arxiu_" + idioma, "");

                    }
                }
            }

            mapDoc.put("item_id", doc.getId());
            resultats.put("document", mapDoc);
            resultats.put("id", doc.getId());

        } catch (NumberFormatException nfe) {
            log.error("El id del document no es numeric: " + nfe.toString());
            resultats.put("id", -3);
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Error de permisos: " + dEx.toString());
                resultats.put("id", -1);
            } else {
                log.error("Error: " + dEx.toString());
                resultats.put("id", -2);
            }
        }

        return resultats;
    }
    
    
    /**
     * Llena el campo para la listsa de documentos. 
     * @param listaDocumentos
     * @param id
     * @param idiomas
     * @param ordenable
     * @return
     */
    private List<Map<String, Object>> recuperaDocumentosRelacionados(Set<DocumentoNormativa> listaDocumentos, Long id,  List<String> idiomas) {
		
		List<Map<String, Object>> listaDocumentosDTO = new ArrayList<Map<String, Object>>();
		
		for (DocumentoNormativa doc : listaDocumentos) {
			
			if (doc != null) {
			    
				// Montar map solo con los campos 'titulo' de las traducciones del documento.
				Map<String, String> titulos = new HashMap<String, String>();
				String nombre;
				TraduccionDocumentoNormativa traDoc;

				for (String idioma : idiomas) {
					
					traDoc = (TraduccionDocumentoNormativa)doc.getTraduccion(idioma);
					nombre = (traDoc != null && traDoc.getTitulo() != null) ? traDoc.getTitulo() : "";
					
					titulos.put(idioma, nombre);
					
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", doc.getId());
				map.put("orden", doc.getOrden());
				map.put("nombre", titulos);
				map.put("idMainItem", id);
				map.put("idRelatedItem", doc.getId());
				
				listaDocumentosDTO.add(map);
				
			} else {
				
				log.error("El registre amb ID " + id + " té un document nul.");
				
			}
			
		}
		
		return listaDocumentosDTO;
		
	}
    
    
    @RequestMapping(value = "/modulos.do")
   	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

   		Map<String, Object> resultats = new HashMap<String, Object>();

   		try {
   			
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
            Normativa normativa = normativaDelegate.obtenerNormativa(id);
   			
   			resultats.put("afectacions", getNormativasAfectadasDTO(normativa, lang, id));
   			resultats.put("procediments", getProcedimientosNormativaDTO(normativa, lang));
   			resultats.put("serveis", getServiciosNormativaDTO(normativa, lang));

   		} catch (DelegateException dEx) {

   			log.error(ExceptionUtils.getStackTrace(dEx));

   			if (dEx.isSecurityException())
   				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

   			else
   				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

   		}

   		return resultats;

   	}

    private List<ProcedimientoLocalDTO> getProcedimientosNormativaDTO(Normativa normativa, String idiomaUsuario) {

        Set<ProcedimientoLocal> listaProcedimientos = normativa.getProcedimientos();
        List<ProcedimientoLocalDTO> procedimientos = new ArrayList<ProcedimientoLocalDTO>();

        for (ProcedimientoLocal proc : listaProcedimientos) {
        	
            TraduccionProcedimientoLocal traProc = (TraduccionProcedimientoLocal)proc.getTraduccion(idiomaUsuario);
            
            procedimientos.add(
        		new ProcedimientoLocalDTO(
        			0L,
    				proc.getId(),
    				traProc != null ? traProc.getNombre() : "",
    				null, null, null, null
				)
    		);
        }

        return procedimientos;
        
    }
    
    private List<ServicioDTO> getServiciosNormativaDTO(Normativa normativa, String idiomaUsuario) {

        Set<Servicio> listaServicios = normativa.getServicios();
        List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();

        for (Servicio servicio : listaServicios) {
        	
        	TraduccionServicio traServ = (TraduccionServicio)servicio.getTraduccion(idiomaUsuario);
            
            servicios.add(
        		new ServicioDTO(
        			0L,
    				servicio.getId(),
    				traServ != null ? traServ.getNombre() : "",
    				null, null, null, null
				)
    		);
        }

        return servicios;
        
    }

    private List<AfectacionDTO> getNormativasAfectadasDTO(Normativa normativa, String idiomaUsuario, long id) {

        Set<Afectacion> listaAfectadas = normativa.getAfectadas();
        List<AfectacionDTO> afectadas = new ArrayList<AfectacionDTO>();

        for (Afectacion afec : listaAfectadas) {
        	
            Normativa normativaAfectada = afec.getNormativa();
            AfectacionDTO afectacion = new AfectacionDTO();
            
            afectacion.setAfectacioId(afec.getTipoAfectacion().getId());

            TraduccionTipoAfectacion traTipAfec = (TraduccionTipoAfectacion)afec.getTipoAfectacion().getTraduccion(idiomaUsuario);
            if (traTipAfec == null) {
                traTipAfec = (TraduccionTipoAfectacion)afec.getTipoAfectacion().getTraduccion();
            }

            afectacion.setAfectacioNom(traTipAfec.getNombre());
            afectacion.setNormaId(normativaAfectada.getId());

            TraduccionNormativa traNormAfectada = (TraduccionNormativa) normativaAfectada.getTraduccion(idiomaUsuario);
            if (traNormAfectada == null) {
                traNormAfectada = (TraduccionNormativa) normativaAfectada.getTraduccion();
            }

            afectacion.setNormaNom(traNormAfectada != null ? obtenerTituloDeEnlaceHtml(traNormAfectada.getTitulo()) : "");
            
            afectacion.setIdMainItem(id);
            afectacion.setIdRelatedItem(normativaAfectada.getId());
            
            afectadas.add(afectacion);
            
        }

        return afectadas;

    }

    private void agregarTraduccionNormativaADetalle(TraduccionNormativa traNorm, String idioma, Map<String, Object> normativaDetall, Normativa normativa) {

        normativaDetall.put("idioma_" + idioma + "_titol", traNorm != null ? traNorm.getTitulo() : "");
        normativaDetall.put("idioma_" + idioma + "_enllac", traNorm != null ? traNorm.getEnlace() : "");
        normativaDetall.put("idioma_" + idioma + "_apartat", traNorm != null ? traNorm.getApartado() : "");
        normativaDetall.put("idioma_" + idioma + "_pagini", traNorm != null ? traNorm.getPaginaInicial() : "");
        normativaDetall.put("idioma_" + idioma + "_pagfin", traNorm != null ? traNorm.getPaginaFinal() : "");
        normativaDetall.put("idioma_" + idioma + "_responsable", traNorm != null ? traNorm.getResponsable() : "");
        

        normativaDetall.put("idioma_" + idioma + "_observacions", traNorm != null ? traNorm.getObservaciones() : "");

        // archivo
        if (traNorm != null && traNorm.getArchivo() != null) {
            normativaDetall.put("idioma_" + idioma + "_enllas_arxiu", "normativa/archivoNormativa.do?id=" + normativa.getId() + "&lang=" + idioma);
            normativaDetall.put("idioma_" + idioma + "_nom_arxiu", traNorm.getArchivo().getNombre());
        } else {
            normativaDetall.put("idioma_" + idioma + "_enllas_arxiu", "");
            normativaDetall.put("idioma_" + idioma + "_nom_arxiu", "");
        }

    }

    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {

        /**
         * Forzar content type en la cabecera para evitar bug en IE y en
         * Firefox. Si no se fuerza el content type Spring lo calcula y
         * curiosamente depende del navegador desde el que se hace la peticion.
         * Esto se debe a que como esta peticion es invocada desde un iFrame
         * (oculto) algunos navegadores interpretan la respuesta como un
         * descargable o fichero vinculado a una aplicacion. De esta forma, y
         * devolviendo un ResponseEntity, forzaremos el Content-Type de la
         * respuesta.
         */
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        Normativa normativa = null;
        Normativa normativaOld = null;
        IdNomDTO result = null;

        Map<String, String> valoresForm = new HashMap<String, String>();
        Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

        try {
            // Recuperacio dels diferents items, tant dades con fitxers dels
            // formularis
        	// Ya no se recupera el fichero.
            recuperarItems(request, valoresForm, ficherosForm);

            // Recuperamos la UA de la normativa
            UnidadAdministrativa ua = null;

            // Determinar si la normativa a guardar tiene que ser local/externa
            normativa = new Normativa();
            /*if (ua != null) {
                normativa.setUnidadAdministrativa(ua);
            }*/

            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));

            if (edicion) {
                Long idNorm = ParseUtil.parseLong(valoresForm.get("item_id"));
                normativaOld = normativaDelegate.obtenerNormativa(idNorm);
                
                //#427 No se pueden editar y actualizarse las normativas con datos no válidos
                if (normativaOld.getDatosValidos() != null && normativaOld.getDatosValidos() == 0) {
                	
                	log.debug("La normativa está marcada como no válida y no se puede editar.");
                  	String error = messageSource.getMessage("error.normativa.novalida.edicion", null, request.getLocale());
                    result = new IdNomDTO(-4l, error);    
                    return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
                }	
                
                String mensaje = mensajeEditable(request, valoresForm, normativaOld, ua);
                if (!mensaje.equals("")) {
                    IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage(mensaje, null, request.getLocale()));
                    return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
                }

                normativa.setAfectadas(normativaOld.getAfectadas());
                normativa.setAfectantes(normativaOld.getAfectantes());
                normativa.setProcedimientos(normativaOld.getProcedimientos());
                normativa.setId(idNorm);

            } else {
            	// Ya no hace falta, no existe publica.
                // Comprobar permisos de creacion
                // if (!normativaDelegate.autorizaCrearNormativa(ParseUtil.parseInt(valoresForm.get("item_validacio")))) {
                //     IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
                //     return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
                // }
                
                ua =  (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
                
            }

            // Recuperamos las traducciones
            normativa = recuperarTraducciones(valoresForm, ficherosForm, normativaOld, normativa);

            // Recuperar el resto de campos de la normativa
            normativa = recuperarCamposNormativa(valoresForm, normativa);

            boolean todoCorrecto = true;
            //El num normativa es obligatorio, menos si es de tipo 'Ordre' (id=4)
            if (normativa.getTipo().getId().compareTo(4l) != 0) {
            	if (normativa.getNumNormativa() == null || normativa.getNumNormativa().isEmpty()) {
            		todoCorrecto = false;
            		log.debug("El num de normativa no está introducido y no es de tipo ordre.");
               	 	String error = messageSource.getMessage("error.normativa.numnormativavacio", null, request.getLocale());
                    result = new IdNomDTO(-4l, error);           
            		
            	}
            }
            
            if (todoCorrecto) {
	            if (normativaDelegate.isNumNormativaCorrecto(normativa)) {
	            
	            	//Comprobamos si la normativa es privada.
	            	if (normativa.getValidacion() == ValidacionNormativa.INTERNA_PRIVADA) {
	            		
	            		log.debug("El estado es privado / interna de la normativa y se bloquea cualquier actualización.");
	               	 	String error = messageSource.getMessage("error.normativa.interna", null, request.getLocale());
	                    result = new IdNomDTO(-4l, error);           
	            		
	            	} else {
	            	
		            	//Seteamos los datos validos a 1 (correcto)
		            	normativa.setDatosValidos(1);
		            	
			            // Guardar la Normativa
			            guardarNormativa(normativa, ua);
			
			            // Finalizado correctamente
			            result = new IdNomDTO(normativa.getId(), messageSource.getMessage("normativa.guardat.correcte", null, request.getLocale()));
	            	}
	            } else {
	            	 log.debug("El numero de normativa ya existe.");
	            	 String error = messageSource.getMessage("error.numnormativa.repetido", null, request.getLocale());
	                 result = new IdNomDTO(-4l, error);                 
	            }
            }
        
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

        } catch (Exception e) {
        	
            String error = messageSource.getMessage("error.altres", null, request.getLocale());
            result = new IdNomDTO(-2l, error);
            log.error(ExceptionUtils.getStackTrace(e));

        }

        
        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
        
    }

    /**
     * AquÃ­ nos llegara un multipart, de modo que no podemos obtener los datos
     * mediante request.getParameter(). Iremos recopilando los parametros de
     * tipo fichero en el Map ficherosForm y el resto en valoresForm.
     */
    private void recuperarItems(HttpServletRequest request, Map<String, String> valoresForm, Map<String, FileItem> ficherosForm) throws FileUploadException, UnsupportedEncodingException {

        List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                ficherosForm.put(item.getFieldName(), item);
            }
        }
    }


    /**
     * Funcion para revisar si una normativa es editable
     * si el editable la cadena es vacia
     * en caso contrario devuelve el error
     * 
     * @throws DelegateException
     */
    private String mensajeEditable(HttpServletRequest request, Map<String, String> valoresForm, Normativa normativaOld, UnidadAdministrativa ua) throws DelegateException {

        // Comprobar permisos para modificar normativa
        // O comprobar que no se haya cambiado la validacion siendo operador
        if (!DelegateUtil.getNormativaDelegate().autorizaModificarNormativa(normativaOld.getId())
            || (request.isUserInRole("sacoper") && !normativaOld.getValidacion().equals(ParseUtil.parseInt(valoresForm.get("item_validacio"))))) {

            return "error.permisos";
        }

        //Solo comprobaremos que el numnormativa no pase de tamanyo 8, tenga la '/' y que el año este relleno. 
        //   Se deja la posibilidad que el campo DDD (DDD/MMMM) se rellene entero o no. 
        if (valoresForm.get("item_num_norma") != null && !"".equals(valoresForm.get("item_num_norma"))) {
      	  	String numNorma = valoresForm.get("item_num_norma");
      	  	if (numNorma.length() > 8) {
      	  		return "normativa.formulari.error.numnormativaincorrecto";
      	  	} 
      	  	
      	  	if (!numNorma.contains("/")) {
      	  		return "normativa.formulari.error.numnormativaincorrecto";
      	  	}
      	  	
      	  	String anyo = numNorma.substring(numNorma.indexOf("/")+1);
      	  	if (anyo.length() != 4) {
      	  		return "normativa.formulari.error.numnormativaincorrecto";
      	  	}
        }

        return "";
        
    }

    /**
     * Obtener traducciones de los idiomas
     */
    private Normativa recuperarTraducciones(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm, Normativa normativaOld, Normativa normativa)
        throws DelegateException {
        // Obtener campos por idioma
        List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

        for (String idioma : idiomas) {
            TraduccionNormativa traNorm = normativaOld != null ? (TraduccionNormativa) normativaOld.getTraduccion(idioma) : null;
            if (traNorm != null) {
                normativa.setTraduccion(idioma, traNorm);
            } else {
                traNorm = new TraduccionNormativa();
                normativa.setTraduccion(idioma, traNorm);
            }

            traNorm.setTitulo(RolUtil.limpiaCadena(valoresForm.get("item_titol_" + idioma)));
            traNorm.setEnlace(RolUtil.limpiaCadena(valoresForm.get("item_enllac_" + idioma)));
            traNorm.setApartado(RolUtil.limpiaCadena(valoresForm.get("item_apartat_" + idioma)));

            if (valoresForm.get("item_pagina_inicial_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_inicial_" + idioma))) {
                traNorm.setPaginaInicial(ParseUtil.parseInt(valoresForm.get("item_pagina_inicial_" + idioma)));
            }

            if (valoresForm.get("item_pagina_final_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_final_" + idioma))) {
                traNorm.setPaginaFinal(ParseUtil.parseInt(valoresForm.get("item_pagina_final_" + idioma)));
            }


            // Responsable sólo en normativa externa
            traNorm.setResponsable(RolUtil.limpiaCadena(valoresForm.get("item_responsable_" + idioma)));
     
            // Archivo
            FileItem fileItem = ficherosForm.get("item_arxiu_" + idioma);
            if (fileItem != null && fileItem.getSize() > 0) {
                traNorm.setArchivo(UploadUtil.obtenerArchivo(traNorm.getArchivo(), fileItem));
            } else if (valoresForm.get("item_arxiu_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_arxiu_" + idioma + "_delete"))) {
                // borrar fichero si se solicita
                traNorm.setArchivo(null);
            }
        }

        return normativa;
    }

    /**
     * Recuperar campos de la normativa
     */
    private Normativa recuperarCamposNormativa(Map<String, String> valoresForm, Normativa normativa) throws DelegateException, ParseException {
        // Obtener los demas campos
        if (valoresForm.get("item_numero") != null && !"".equals(valoresForm.get("item_numero"))) {
            normativa.setNumero(ParseUtil.parseLong(valoresForm.get("item_numero")));
        }

        normativa.setLey(valoresForm.get("item_llei"));

        if (valoresForm.get("item_registre") != null && !"".equals(valoresForm.get("item_registre"))) {
            normativa.setRegistro(ParseUtil.parseLong(valoresForm.get("item_registre")));
        }

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
        
        if (valoresForm.get("item_num_norma") != null && !"".equals(valoresForm.get("item_num_norma"))) {
        	  normativa.setNumNormativa(valoresForm.get("item_num_norma"));
        }

        if (valoresForm.get("item_validacio") != null && !"".equals(valoresForm.get("item_validacio"))) {
            normativa.setValidacion(ParseUtil.parseInt(valoresForm.get("item_validacio")));
        }

        if (valoresForm.get("item_butlleti_id") != null && !"".equals(valoresForm.get("item_butlleti_id"))) {
            Boletin boletin = DelegateUtil.getBoletinDelegate().obtenerBoletin(ParseUtil.parseLong(valoresForm.get("item_butlleti_id")));
            normativa.setBoletin(boletin);
        }

        return normativa;
    }

    /**
     * Guardado de la normativa
     * 
     * @param normativa
     * @param ua
     * @return
     * @throws DelegateException
     */
    private Long guardarNormativa(Normativa normativa, UnidadAdministrativa ua) throws DelegateException {
        
        /* NOTA IMPORTANTE PARA EL RENDIMIENTO */
        // Con este null evitamos que hibernate vaya a actualizar tablas innecesarias
        normativa.setProcedimientos(null);
        /* FIN NOTA */
        
        Long idUA = null;
        if (ua != null) { //Solo estara relleno en la creación y será cuando no sea edicion
        	idUA = ua.getId();
        }
        return DelegateUtil.getNormativaDelegate().grabarNormativa(normativa, idUA);
        
    }

    /**
     * Eliminando una normativa.
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/eliminar.do", method = POST)
    public @ResponseBody
    IdNomDTO eliminar(HttpSession session, HttpServletRequest request) {

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

        return new IdNomDTO(id, messageSource.getMessage("normativa.eliminat.correcte", null, request.getLocale()));
    }

    @RequestMapping(value = "/cercarNormatives.do", method = POST)
    public @ResponseBody
    Map<String, Object> cercarNormatives(HttpServletRequest request, HttpSession session) {

        // Listar las normativas de la unidad administrativa
        List<Normativa> llistaNormatives = new ArrayList<Normativa>();
        List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
        Map<String, Object> resultats = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> paramTrad = new HashMap<String, String>();

        // TODO obtener la ordenacion por parametro
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

            // Informacion de paginacion
            String pagPag = request.getParameter("pagPagina");
            String pagRes = request.getParameter("pagRes");
            
            
            if (pagPag == null) {
                pagPag = String.valueOf(0);
            }
            if (pagRes == null) {
                pagRes = String.valueOf(10);
            }
            final String invalids = request.getParameter("invalids");

            // Realizar la consulta y obtener resultados
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
            ResultadoBusqueda resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, "todas", null, false, false, invalids, campoOrdenacion, orden, pagPag, pagRes, false);
            resultats.put("total", resultadoBusqueda.getTotalResultados());

            llistaNormatives.addAll((List<Normativa>) resultadoBusqueda.getListaResultados());
            llistaNormativesDTO = pasarListaNormativasADTO(llistaNormatives, idioma);

        } catch (ParseException e) {
            log.error("Error: " + e.getMessage());

        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
                log.error("Error: " + dEx.getMessage());
            }
        }

        resultats.put("nodes", llistaNormativesDTO);
        return resultats;
    }

    /**
     * Obtiene una lista de NormativaDTO a partir de una lista de envÃ­os de eboib
     * 
     * @param listadonormativas
     * @param idioma
     * @return
     */
    private List<NormativaDTO> pasarListaEboibADTO(final List<TrListadoNormativaBean> listadonormativas, String idioma) {

        final List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
        for (TrListadoNormativaBean normativa : listadonormativas) {
            // Retirar posible enlace incrustado en titulo
            llistaNormativesDTO.add(new NormativaDTO(0, Long.parseLong(normativa.getBoib()), normativa.getTitulo(), null, normativa.getFechaBoletin(), null, "BOIB", true, normativa.getRegistro()));
        }

        return llistaNormativesDTO;
    }

    /**
     * Obtiene una lista de NormativaDTO a partir de una lista de Normativa.
     * 
     * @param llistaNormatives Lista de Normativa.
     * @param idioma Idioma seleccionado por el usuario.
     * @throws DelegateException
     */
    private List<NormativaDTO> pasarListaNormativasADTO(List<Normativa> llistaNormatives, String idioma) throws DelegateException {

        List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
        for (Normativa normativa : llistaNormatives) {
            String traNor = normativa.getTraduccionTitulo();
            if (traNor == null) {
                NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
                Normativa norm = normativaDelegate.obtenerNormativa(normativa.getId());
                TraduccionNormativa traduc = (TraduccionNormativa) norm.getTraduccion();
                traNor = traduc.getTitulo();
            }

            // Retirar posible enlace incrustado en titulo
            String titulo = traNor != null ? obtenerTituloDeEnlaceHtml(traNor) : "";
            TraduccionTipo traTip = normativa.getTipo() != null ? (TraduccionTipo) normativa.getTipo().getTraduccion(idioma) : null;
            String tipus = traTip != null ? traTip.getNombre() : "";
            
            String color = "";
            if (!normativa.isDatosValidos()) {
            	color = "red";
            }
            llistaNormativesDTO
                .add(new NormativaDTO(normativa.getId() != null ? normativa.getId().longValue() : 0, normativa.getNumero() != null ? normativa.getNumero().longValue() : -1l, titulo, normativa
                    .getFecha(), normativa.getFechaBoletin(), normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : "", tipus, "Normativa", normativa.isVisible(), normativa.getNumNormativa(), color));
        }

        return llistaNormativesDTO;
    }

    // TODO: mover a clase de utilidades.
    /**
     * De un string que contiene un enlace HTML extrae el titulo del enlace.
     * 
     * @param texto String que contiene el enlace.
     * @return TÃ­tulo del enlace, si no hay enlace devuelve el texto tal cual.
     */
    private static String obtenerTituloDeEnlaceHtml(String texto) {

        if (texto == null) {
            return null;
        }

        String tmp = texto;
        
        return HtmlUtils.html2text(texto).trim();

        // Sera el texto desde el primer '>' y desde ahi al primer '<'
   /*     int pos = tmp.indexOf('>');
        if (pos > -1) {
            tmp = tmp.substring(pos + 1);
            pos = tmp.indexOf('<');
            if (pos > -1) {
                tmp = tmp.substring(0, pos);
                return tmp.trim();
            } else {
                return texto;
            }
        } else {
            return texto;
        }*/
    }

    @RequestMapping(value = "/traduir.do")
    public @ResponseBody
    Map<String, Object> traduir(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();
        try {
            String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            TraduccionNormativa traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
            log.error("NormativaBackController.traduir: El traductor no se encuentra en en contexto.");
            resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
        } catch (Exception e) {
            log.error("NormativaBackController.traduir: Error en al traducir normativa: " + e);
            resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
        }

        return resultats;
    }

    /**
     * Recuperacio dels camps dels formularis
     * @param request
     * @param idiomaOrigenTraductor
     * @return
     */
    private TraduccionNormativa getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {

        TraduccionNormativa traduccioOrigen = new TraduccionNormativa();

        if (StringUtils.isNotEmpty(request.getParameter("item_titol_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setTitulo(request.getParameter("item_titol_" + idiomaOrigenTraductor));
        }

        return traduccioOrigen;
    }
    
    @RequestMapping(value = "/guardarAfectaciones.do")
	public @ResponseBody IdNomDTO guardarAfectaciones(Long id, Long[] elementos, Long[] tiposAfectacion, HttpServletRequest request) {
    	
    	/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type, Spring lo calcula y curiosamente depende del navegador desde el que se hace la peticion.
		 * Esto se debe a que como esta peticion es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicacion. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String error = null;       
		IdNomDTO result = null;
		
		try {
					
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			Normativa normativa = normativaDelegate.obtenerNormativa(id);
			
            Set<Afectacion> listaActualAfectaciones = normativa.getAfectadas();
            
            // Construimos lista de nuevas afectaciones enviadas.
            List<AfectacionDTO> listaNuevasAfectaciones = new ArrayList<AfectacionDTO>();
            if ( elementos != null ) {
            	
            	for (int i = 0; i < elementos.length; i++) {
            		
            		AfectacionDTO afectacion = new AfectacionDTO();
            		afectacion.setNormaId(elementos[i]);
            		afectacion.setAfectacioId(tiposAfectacion[i]);
            		
            		listaNuevasAfectaciones.add(afectacion);
            		
            	}
            	
            }
            
            AfectacionesDTO afectaciones = new AfectacionesDTO();
            afectaciones.setListaAfectaciones(listaNuevasAfectaciones);
            
            // Comparar la lista actual de afectaciones con la nueva para determinar quÃ© anyadir y quÃ© eliminar.
            for (Afectacion afectacionOld : listaActualAfectaciones) {

                // Buscar la afectacion afectacionOld en la lista nueva recibida en el post
                boolean estaEnLaListaNueva = false;
                
                for (AfectacionDTO afectacionNew : afectaciones.getListaAfectaciones()) {
                
                	if (afectacionOld.getNormativa().getId().equals(afectacionNew.getNormaId()) && afectacionOld.getTipoAfectacion().getId().equals(afectacionNew.getAfectacioId())) {
                        estaEnLaListaNueva = true;
                        afectaciones.getListaAfectaciones().remove(afectacionNew);
                        break;
                    }
                
                }
                
                // Si no esta en la lista nueva es que hay que eliminarla
                if (!estaEnLaListaNueva) {
                    normativaDelegate.eliminarAfectacion(normativa.getId(), afectacionOld.getTipoAfectacion().getId(), afectacionOld.getNormativa().getId());
                }
                
            }

            // Anyadir afectaciones
            for (AfectacionDTO afectacion : afectaciones.getListaAfectaciones()) {
                normativaDelegate.anyadirAfectacion(afectacion.getNormaId(), afectacion.getAfectacioId(), normativa.getId());
            }
            
            // Finalizado correctamente
            result = new IdNomDTO(normativa.getId(), messageSource.getMessage("normativa.guardat.afectacions.correcte", null, request.getLocale()));
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
		}
		
		return result;
    	
    }

    
	@RequestMapping(value = "/guardarUnidadesRelacionadas.do")
	public @ResponseBody IdNomDTO guardarUnidadesRelacionadas(final Long id, final Long[] elementos, final Long itemUAPrincipal, final HttpServletRequest request) {
				
		IdNomDTO result = null;
		
		try {
						
			Normativa normativa = DelegateUtil.getNormativaDelegate().obtenerNormativa(id);
			
			UnidadNormativaDelegate uaNormativaDelegate = DelegateUtil.getUnidadNormativaDelegate();
			 
			// Obtenemos las relaciones que borraremos primero.
			Set<UnidadNormativa> unidadesNormativa = (Set<UnidadNormativa>)normativa.getUnidadesnormativas();
			List<Long> unidadesNormativaABorrar = new ArrayList<Long>();
			Iterator<UnidadNormativa> it = unidadesNormativa.iterator();
			while ( it.hasNext() )
				unidadesNormativaABorrar.add( it.next().getId() );
			
			List<UnidadNormativa> unidadesNormativaNuevas = new ArrayList<UnidadNormativa>();
			
			// Procesamos los elementos actuales.
			if ( elementos != null ) {
				
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
								
				for ( int i = 0; i < elementos.length; i++ ) {
					
					if ( elementos[i] != null ) {
					
						UnidadNormativa uam = new UnidadNormativa();
						UnidadAdministrativa ua = uaDelegate.consultarUnidadAdministrativaSinFichas(elementos[i]);
						
						uam.setNormativa(normativa);
						uam.setUnidadAdministrativa(ua);
						
						unidadesNormativaNuevas.add(uam);
											
					}
					
				}
				
			}
			
			uaNormativaDelegate.grabarUnidadesNormativa(unidadesNormativaNuevas, unidadesNormativaABorrar);
			
			String ok = messageSource.getMessage("materia.guardat.uas.correcte", null, request.getLocale());
			result = new IdNomDTO(normativa.getId(), ok);            

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		}

		return result;
		
	}
	
    @RequestMapping(value = "/guardarDocument.do", method = POST)
    public ResponseEntity<String> guardar(HttpServletRequest request, HttpSession session) {
        /*
         * Forzar content type en la cabecera para evitar bug en IE y en
         * Firefox. Si no se fuerza el content type Spring lo calcula y
         * curiosamente depende del navegador desde el que se hace la petición.
         * Esto se debe a que como esta petici�n es invocada desde un iFrame
         * (oculto) algunos navegadores interpretan la respuesta como un
         * descargable o fichero vinculado a una aplicación. De esta forma, y
         * devolviendo un ResponseEntity, forzaremos el Content-Type de la
         * respuesta.
         */
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        Locale locale = request.getLocale();
        String jsonResult = null;
        Map<String, String> valoresForm = new HashMap<String, String>();
        Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

        try {

            // Recuperamos los valores del request
            recuperarForms(request, valoresForm, ficherosForm);

            // Recuperamos el documento antiguo si existe
            DocumentoNormativa docOld = recuperarDocOld(valoresForm);

            // Copiamos la información deseada al nuevo documento
            DocumentoNormativa doc = recuperarInformacionDocumento(valoresForm, docOld);

            // Actualizamos las traducciones y marcamos los archivos que deven
            // ser eliminados
            List<Long> archivosAborrar = new Vector<Long>();

            doc = gestionarTraducciones(valoresForm, ficherosForm, archivosAborrar, docOld, doc);

            String iden = "";

            if (valoresForm.get("procId") != null && !"".equals(valoresForm.get("procId"))) {
                iden = "procId";
            } else if (valoresForm.get("fitxaId") != null && !"".equals(valoresForm.get("fitxaId"))) {
                iden = "fitxaId";
            }

            boolean continuar = true;
            //#421 Comprobacion del tamaño del nombre de archivo.
            if (doc != null && doc.getTraducciones() != null) {
        	    //Buscamos el archivo del idioma.
	           	for(String idioma : doc.getTraducciones().keySet()) {
	   				TraduccionDocumentoNormativa tradNor = (TraduccionDocumentoNormativa) doc.getTraduccion(idioma);
	   				if (tradNor != null && tradNor.getArchivo() != null  && tradNor.getArchivo().getNombre() != null && tradNor.getArchivo().getNombre().length() >= Archivo.NOMBRE_LONGITUD_MAXIMA) {
	   					String error = messageSource.getMessage("error.fitxer.tamany_nom", null, locale);
	   	            	log.error("Error controlado, ha intentado subir un fichero con una longitud en el nombre de más de 128 caracteres.");
	   	            	jsonResult = new IdNomDTO(-3l, error).getJson();
	   	            	continuar = false;
	   				}
	   			}
            }

            if (continuar) {
            	 // Guardar el documento
                jsonResult = guardarDocumento(valoresForm, iden, locale, archivosAborrar, doc);
            }
        } catch (FileUploadException fue) {
            String error = messageSource.getMessage("error.fitxer.tamany", null, locale);
            jsonResult = new IdNomDTO(-3l, error).getJson();
            log.error(error + ": " + fue.toString());
        } catch (UnsupportedEncodingException uee) {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error(error + ": " + uee.toString());
        } catch (NumberFormatException nfe) {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error(error + ": " + nfe.toString());
        } catch (DelegateException de) {
            String error = null;
            if (de.isSecurityException()) {
                error = messageSource.getMessage("error.permisos", null, locale);
                jsonResult = new IdNomDTO(-1l, error).getJson();
            } else {
                error = messageSource.getMessage("error.altres", null, locale);
                jsonResult = new IdNomDTO(-2l, error).getJson();
            }
            log.error(error + ": " + de.toString());
        }

        return new ResponseEntity<String>(jsonResult, responseHeaders, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/guardarDocumentosRelacionados.do", method = POST)
	public @ResponseBody IdNomDTO guardarDocumentosRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
		
		// Guardaremos el orden y borraremos los documentos que se hayan marcado para borrar.
		// La creación se gestiona en el controlador DocumentBackController.
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		//ProcedimientoLocal procedimiento = null;
		
		try {
			if (elementos == null) {
				elementos = new Long[0];
			}
			DelegateUtil.getNormativaDelegate().reordenarDocumentos(id, Arrays.asList(elementos));
			result = new IdNomDTO(id, messageSource.getMessage("proc.guardat.documents.correcte", null, request.getLocale()));
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
				
			}
			
		}		
		
		return result;

	}
    
    /** Guardado del documento */
    private String guardarDocumento(Map<String, String> valoresForm, String iden, Locale locale,
        List<Long> archivosBorrar, DocumentoNormativa doc) throws DelegateException {

        String jsonResult = null;
        Long docId = null;

        if (valoresForm.get(iden) != null && !"".equals(valoresForm.get(iden))) {

            DocumentoNormativaDelegate docDelegate = DelegateUtil.getDocumentoNormativaDelegate();
            Long id = Long.parseLong(valoresForm.get(iden));
            //Map<String, Traduccion> traducciones = doc.getTraducciones();
            //doc.setTraducciones(null);
            docId = docDelegate.grabarDocument(doc, id);
            //doc.setTraducciones(traducciones);
            //docId = docDelegate.grabarDocument(doc, id);
            

            for (Long idArchivo : archivosBorrar) {
                DelegateUtil.getArchivoDelegate().borrarArchivo(idArchivo);
            }

            jsonResult = new IdNomDTO(docId, messageSource.getMessage("document.guardat.correcte", null, locale))
                .getJson();

        } else {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error("Error guardant document: No s'ha especificat id de procediment o de fitxer.");
        }

        return jsonResult;
    }
    

    /**
     * Aquí nos llegará un multipart, de modo que no podemos obtener los datos
     * mediante request.getParameter(). Iremos recopilando los parámetros de
     * tipo fichero en el Map ficherosForm y el resto en valoresForm.
     */
    private void recuperarForms(HttpServletRequest request, Map<String, String> valoresForm,
        Map<String, FileItem> ficherosForm) throws UnsupportedEncodingException, FileUploadException {

        @SuppressWarnings("unchecked")
		List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

        for (FileItem item : items) {
            if (item.isFormField()) {
                valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                ficherosForm.put(item.getFieldName(), item);
            }
        }
    }
    

    /** 
     * Vemos si se debe recuperar el documento viejo 
     * @param valoresForm
     * @return
     * @throws DelegateException
     */
    private DocumentoNormativa recuperarDocOld(Map<String, String> valoresForm) throws DelegateException {

        DocumentoNormativa docOld = null;

        if (!this.isDocumentoNuevo(valoresForm)) {
            Long docId = Long.parseLong(valoresForm.get("docId"));
            docOld = DelegateUtil.getDocumentoNormativaDelegate().obtenerDocumentoNormativa(docId);
        }

        return docOld;
    }

    
    /** 
     * Recuperamos la información antigua si el documento ya existia 
     * @param valoresForm
     * @param docOld
     * @return
     */
    private DocumentoNormativa recuperarInformacionDocumento(Map<String, String> valoresForm, DocumentoNormativa docOld) {

    	DocumentoNormativa doc = new DocumentoNormativa();

        if (!this.isDocumentoNuevo(valoresForm)) {
            doc.setId(docOld.getId());
            // Este atributo parece que ya no se usa. Se mantiene por si acaso.
            doc.setArchivo(docOld.getArchivo()); 
            doc.setOrden(docOld.getOrden());
            doc.setNormativa(docOld.getNormativa());
            doc.setTraducciones(docOld.getTraducciones());
        }

        return doc;
    }


    /** 
     * Gestión de las traducciones y los archivos.
     * 
     * @param valoresForm
     * @param ficherosForm
     * @param archivosAborrar
     * @param docOld
     * @param doc
     * @return
     * @throws DelegateException
     */
    private DocumentoNormativa gestionarTraducciones(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm,
        List<Long> archivosAborrar, DocumentoNormativa docOld, DocumentoNormativa doc) throws DelegateException {

    	TraduccionDocumentoNormativa tradDoc;
        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

            tradDoc = new TraduccionDocumentoNormativa();
            tradDoc.setTitulo(RolUtil.limpiaCadena(valoresForm.get("doc_titol_" + lang)));
            tradDoc.setDescripcion(RolUtil.limpiaCadena(valoresForm.get("doc_descripcio_" + lang)));
            tradDoc.setEnlace(RolUtil.limpiaCadena(valoresForm.get("doc_url_" + lang)));
            FileItem fileItem = ficherosForm.get("doc_arxiu_" + lang); // Archivo

            if (fileItem != null && fileItem.getSize() > 0) {
            	
                if (!this.isDocumentoNuevo(valoresForm)) {
                	
                	TraduccionDocumentoNormativa traDocOld = (TraduccionDocumentoNormativa)docOld.getTraduccion(lang);
                    
                    // Si aún no hay traducción asociada es que no toca procesar el archivo adjunto.
                    if (traDocOld != null) {
                    
	                    if (this.isArchivoParaBorrar(valoresForm, lang) 
	                    		|| this.ficheroAdjuntoIsModificado(valoresForm, traDocOld)) {
	                    	
	                        // Se indica que hay que borrar el fichero.
	                        archivosAborrar.add(traDocOld.getArchivo().getId());
	                        
	                    }
                    
                    }
                    
                }
                
                // Nuevo archivo
                tradDoc.setArchivo(UploadUtil.obtenerArchivo(tradDoc.getArchivo(), fileItem));

            } else if (this.isArchivoParaBorrar(valoresForm, lang)) {
            	
                // Indicamos a la traducción del documento que no va a tener
                // asignado el archivo.
                TraduccionDocumentoNormativa traDocOld = (TraduccionDocumentoNormativa)docOld.getTraduccion(lang);
                archivosAborrar.add(traDocOld.getArchivo().getId());
                tradDoc.setArchivo(null);

            } else if (docOld != null) {
            	
                // mantener el fichero anterior
            	TraduccionDocumentoNormativa traDocOld = (TraduccionDocumentoNormativa)docOld.getTraduccion(lang);
                if (traDocOld != null) {
                    tradDoc.setArchivo(traDocOld.getArchivo());
                }
                
            }

            doc.setTraduccion(lang, tradDoc);
            
        }

        return doc;
        
    }
    

    /**
     * Método que indica si el documento obtenido de la petición es un documento
     * nuevo.
     * 
     * @param valoresForm
     *            Estructura de datos que contiene los valores enciados por el
     *            formulario.
     * @return Devuelve <code>true</code> si es un documento nuevo.
     */
    private boolean isDocumentoNuevo(Map<String, String> valoresForm) {
        return (valoresForm.get("docId") == null || "".equals(valoresForm.get("docId")));
    }
    
    /**
     * Método que indica si el archivo adjunto de un documento se tiene que
     * borrar.
     * 
     * @param valoresForm
     *            Estructura de datos que contiene los valores enciados por el
     *            formulario.
     * @param lang
     *            Indica el idioma del documento.
     * @return Devuelve <code>true</code> si el archivo adjunto de un documento
     *         se ha marcado para borrar.
     */
    private boolean isArchivoParaBorrar(Map<String, String> valoresForm, String lang) {
        return (valoresForm.get("doc_arxiu_" + lang + "_delete") != null && !"".equals(valoresForm.get("doc_arxiu_"
            + lang + "_delete")));
    }

    /**
     * Método que indica si se va a modificar el fichero adjunto a un documento
     * existente.
     * 
     * @param valoresForm
     *            Estructura de datos que contiene los valores enciados por el
     *            formulario.
     * @param traDocOld
     *            Indica un documento que se va a modificar.
     * @return Devuelve <code>true</code> si el fichero adjunto se va a
     *         modificar.
     */
    private boolean ficheroAdjuntoIsModificado(Map<String, String> valoresForm, TraduccionDocumentoNormativa traDocOld) {
        return (traDocOld.getArchivo() != null);
    }

    
    @RequestMapping(value = "/archivoNormativa.do")
    public void devolverArchivoDocumento(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);
    }
    
    /**
     * Devuelve el archivo.
     * @param request
     * @param response
     * @throws Exception
     */
    protected final void devolverArchivo(HttpServletRequest request, HttpServletResponse response)  throws Exception {

    	Archivo archivo = obtenerArchivo(request);
    	if ( archivo != null ) {
    		response.reset();
    		response.setContentType( archivo.getMime() );
    		response.setHeader("Content-Disposition", "inline; filename=\"" + archivo.getNombre() + "\"");
            response.addHeader("cache-response-directive", "no-cache");
    		response.setContentLength( archivo.getDatos().length );
    		response.getOutputStream().write( archivo.getDatos() );
    	}
    }
    
    /**
     * Obtiene el archivo.
     * @param request
     * @return
     * @throws Exception
     */
    public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {
        // Obtener archivo concreto con el delegate

        Long id = new Long(request.getParameter("id"));
        String lang = request.getParameter("lang");
        DocumentoNormativa documentoNormativa = DelegateUtil.getDocumentoNormativaDelegate().obtenerDocumentoNormativa(id);
        Archivo archivo = null;
        if (documentoNormativa != null) {
        	
        	//Buscamos el archivo del idioma.
        	for(String idioma : documentoNormativa.getTraducciones().keySet()) {
				TraduccionDocumentoNormativa tradNor = (TraduccionDocumentoNormativa) documentoNormativa.getTraduccion(idioma);
				if (tradNor != null && tradNor.getArchivo() != null && idioma.equals(lang)) {
					archivo = tradNor.getArchivo();
					break;
				}
			}
			
		}
        
        return archivo;
        
    }

}
