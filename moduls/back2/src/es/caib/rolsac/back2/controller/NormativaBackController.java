package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import es.caib.rolsac.back2.util.CSVUtil;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

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
            // Tipos afectación.
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

        // Obtenemos la ordenación por parámetro.
        String campoOrdenacion = request.getParameter("ordreCamp");
        String orden = request.getParameter("ordreTipus");

        // Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas.
        boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));

        // Determinar si ha marcado el checkbox "Cerar a totes les meves unitats".
        boolean meves = "true".equals(request.getParameter("totesUnitats"));
        boolean uaFilles = "true".equals(request.getParameter("uaFilles"));

        Long idUA = null;

        if (request.getParameter("idUA") != null && !request.getParameter("idUA").equals("")) {
            idUA = ParseUtil.parseLong(request.getParameter("idUA"));
        }

        try {
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            // Obtener parámetros de búsqueda.
            String idStr = request.getParameter("id");
            Long id = -1l;

            if (idStr != null && StringUtils.isNumeric(idStr.trim()))
                id = ParseUtil.parseLong(idStr.trim());

            paramMap.put("id", idStr != null ? id : null);

            // Procesa el objeto request y añade los valores necesarios a los mapas de parámetros y de traducciones.
            procesarParametrosBusqueda(request, paramMap, paramTrad, lang);

            // Realizar la consulta y obtener resultados
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            // Información de paginación.
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

            resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, queBuscar, idUA, meves, uaFilles, campoOrdenacion, orden, pagPag, pagRes, false);

            for (Normativa normativa : (List<Normativa>) resultadoBusqueda.getListaResultados()) {
                boolean local = NormativaLocal.class.isInstance(normativa);
                normativa.setIdioma(lang);
                llistaNormativesDTO.add(new NormativaDTO(normativa.getId(), normativa.getNumero() != null ? normativa.getNumero() : 0, obtenerTituloDeEnlaceHtml(normativa.getTraduccionTitulo()),
                    normativa.getFecha(), normativa.getFechaBoletin(), normativa.getNombreBoletin(), normativa.getNombreTipo(), local ? "Local" : "Externa", normativa.isVisible()));
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
        Map<String, Object> resultats = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, String> paramTrad = new HashMap<String, String>();
        List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();

        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

        // Obtenemos la ordenación por parámetro.
        String campoOrdenacion = request.getParameter("ordreCamp");
        String orden = request.getParameter("ordreTipus");

        // Determinar si el usuario ha marcado el checkbox de buscar en normaticas externas.
        boolean buscaExternas = "true".equals(request.getParameter("cercaExternes"));

        // Determinar si ha marcado el checkbox "Cerar a totes les meves unitats".
        boolean meves = "true".equals(request.getParameter("totesUnitats"));
        boolean uaFilles = "true".equals(request.getParameter("uaFilles"));

        Long idUA = null;

        if (request.getParameter("idUA") != null && !request.getParameter("idUA").equals("")) {
            idUA = ParseUtil.parseLong(request.getParameter("idUA"));
        }

        try {
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            // Obtener parámetros de búsqueda.
            String idStr = request.getParameter("id");
            Long id = -1l;

            if (idStr != null && StringUtils.isNumeric(idStr.trim()))
                id = ParseUtil.parseLong(idStr.trim());

            paramMap.put("id", idStr != null ? id : null);

            // Procesa el objeto request y añade los valores necesarios a los mapas de parámetros y de traducciones.
            procesarParametrosBusqueda(request, paramMap, paramTrad, lang);

            // Realizar la consulta y obtener resultados
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            // Información de paginación.
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

            resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, queBuscar, idUA, meves, uaFilles, campoOrdenacion, orden, pagPag, pagRes, true);
            CSVUtil.mostrarCSV(response, convertirNormativaToCSV((List<Long>) resultadoBusqueda.getListaResultados()));


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
    
    
    private String convertirNormativaToCSV(List<Long> listaResultados) throws UnsupportedEncodingException {
    	StringBuffer retorno = new StringBuffer();
		
		//cabecera!
		retorno.append("CODI_NORMA;");
		retorno.append("NOM_NORMA_CA;");
		retorno.append("NOM_NORMA_ES;");
		retorno.append("ESTAT_NORMA;");
		retorno.append("VISIBILITAT_NORMA;");
		//retorno.append("PUBLIC_OBJECTIU;");
		retorno.append("TIPUS_NORMATIVA;");
		retorno.append("NOM UA;");
		retorno.append("RANG_LEGAL;");
		retorno.append("TIPUS_BUTLLET"+new String(new byte[]{(byte)205})+";");
		retorno.append("NUM_BUTLLET"+new String(new byte[]{(byte)205})+";");
		retorno.append("ENLLA"+new String(new byte[]{(byte)199})+";");
		retorno.append("DATA_NORMA;");
		retorno.append("\n");
	
		
		NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
		for ( Long idNormativa : castList(Long.class, listaResultados) ) {
			Normativa normativa;
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
				estado = "PUBLIC";
			} else if (normativa.getValidacion().compareTo(2) == 0) {
				estado = "INTERN";
			} else {
				estado = "RESERVA";
			}
			
			String bolletiEnllac, bolletiNom = normativa.getNombreBoletin(), bolletiTipus;
			if (normativa.getBoletin() == null) {
				bolletiEnllac = "";
				bolletiTipus = "";
			} else {
				bolletiEnllac = normativa.getBoletin().getEnlace();
				bolletiTipus = normativa.getBoletin().getNombre();
			}
			
			String tipoNormativa;
			if (normativa instanceof NormativaLocal) {
				tipoNormativa = "LOCAL";
			} else {//			normativa instanceof NormativaExterna
				tipoNormativa = "EXTERNA";
			}
			
			
			retorno.append(CSVUtil.limpiar(normativa.getId())); 	//CODI_NORMA,
			retorno.append(CSVUtil.limpiar(nomCa)); 				//NOM_NORMA_CA,
			retorno.append(CSVUtil.limpiar(nomEs)); 				//NOM_NORMA_ES,
			retorno.append(CSVUtil.limpiar(estado)); 				//ESTAT_NORMA DECODE(NOR_VALIDA,1,'PUBLIC',2,'INTERN','RESERVA'),
			retorno.append(CSVUtil.limpiar(normativa.isVisible()));	//VISIBILITAT_NORMA (ESTAT+DATA_PUB+DATA_CAD + UA_VISIBLE),
			//retorno.append(CSVUtil.limpiar("PO")); 					//PUBLIC_OBJECTIU (ID_PUBLIC OBJECTIU SEPARATS PER COMES),
			retorno.append(CSVUtil.limpiar(tipoNormativa));			//TIPUS_NORMATIVA (LOCAL, EXTERNA),
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(normativa.getUnidadAdministrativa()))); 	//NOM_UA
			retorno.append(CSVUtil.limpiar(rangLegal)); 			//RANG LEGAL (LLEI, DECRETET,...)
			retorno.append(CSVUtil.limpiar(bolletiTipus)); 			//TIPUS_BUTLLETÍ,
			retorno.append(CSVUtil.limpiar(bolletiNom)); 			//NUM_BUTLLETÍ,
			retorno.append(CSVUtil.limpiar(bolletiEnllac)); 		//ENLLAÇ,
			retorno.append(CSVUtil.limpiar(normativa.getFechaBoletin())); //DATA_NORMA
			retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
		}
		
		return retorno.toString();		
	}

	


    /**
     * Procesa el objeto request y añade los valores necesarios a los mapas de
     * parámetros y de traducciones.
     * 
     * @param request
     * @param paramMap
     * @param paramTrad
     * @param idioma
     * @throws ParseException
     */
    private void procesarParametrosBusqueda(HttpServletRequest request, Map<String, Object> paramMap, Map<String, String> paramTrad, String idioma) throws ParseException {

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

        if (request.getParameter("numero") != null && !"".equals(request.getParameter("numero"))) {
            paramMap.put("numero", ParseUtil.parseLong(request.getParameter("numero")));
        }
        if (request.getParameter("tipus") != null && !"".equals(request.getParameter("tipus"))) {
            paramMap.put("tipo", ParseUtil.parseLong(request.getParameter("tipus")));
        }
        if (request.getParameter("butlleti") != null && !"".equals(request.getParameter("butlleti"))) {
            paramMap.put("boletin", ParseUtil.parseLong(request.getParameter("butlleti")));
        }
        if (request.getParameter("registre") != null && !"".equals(request.getParameter("registre"))) {
            paramMap.put("registro", request.getParameter("registre"));
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
    Map<String, Object> detallBoib(HttpServletRequest request, Map<String, Object> model) {

        Map<String, Object> normativaDetall = new HashMap<String, Object>();

        SearchNormativa bdcons = new EBoibSearchNormativa(null, null, null);
        bdcons.makeSearchFromBoibRegistro(request.getParameter("id"));

        if (bdcons.getNumeroNormativas() == 1) {
            TrNormativaLocalBean normativa = bdcons.getNormativabean();

            // Campos por idioma
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
    public @ResponseBody
    Map<String, Object> recuperaDetall(Long id, HttpServletRequest request, Map<String, Object> model) {

        Map<String, Object> normativaDetall = new HashMap<String, Object>();

        try {
        	
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
            Normativa normativa = normativaDelegate.obtenerNormativa(id);

            boolean normativaLocal = NormativaLocal.class.isInstance(normativa);

            if (normativaLocal) {
                normativaDetall.put("tipologia", "L");
                NormativaLocal normLoc = (NormativaLocal) normativa;
                if (normLoc.getUnidadAdministrativa() != null) {
                    normativaDetall.put("idUA", normLoc.getUnidadAdministrativa().getId());
                    normativaDetall.put("nomUA", normLoc.getUnidadAdministrativa().getNombreUnidadAdministrativa());
                } else {
                    normativaDetall.put("idUA", null);
                    normativaDetall.put("nomUA", null);
                }
                
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
                TraduccionNormativa traNorm = (TraduccionNormativa) normativa.getTraduccion(idioma);
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
    
    
    @RequestMapping(value = "/modulos.do")
   	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

   		Map<String, Object> resultats = new HashMap<String, Object>();

   		try {
   			
            String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
            Normativa normativa = normativaDelegate.obtenerNormativa(id);
   			
   			resultats.put("afectacions", getNormativasAfectadasDTO(normativa, lang, id));
   			resultats.put("procediments", getProcedimientosNormativaDTO(normativa, lang));

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

    private void agregarTraduccionNormativaADetalle(TraduccionNormativa traNorm, String idioma, Map<String, Object> normativaDetall, boolean normativaLocal, Normativa normativa) {

        normativaDetall.put("idioma_" + idioma + "_titol", traNorm != null ? traNorm.getTitulo() : "");
        normativaDetall.put("idioma_" + idioma + "_enllac", traNorm != null ? traNorm.getEnlace() : "");
        normativaDetall.put("idioma_" + idioma + "_apartat", traNorm != null ? traNorm.getApartado() : "");
        normativaDetall.put("idioma_" + idioma + "_pagini", traNorm != null ? traNorm.getPaginaInicial() : "");
        normativaDetall.put("idioma_" + idioma + "_pagfin", traNorm != null ? traNorm.getPaginaFinal() : "");
        if (normativaLocal)
            normativaDetall.put("idioma_" + idioma + "_responsable", "");
        else {
            TraduccionNormativaExterna normExt = (TraduccionNormativaExterna) traNorm;
            normativaDetall.put("idioma_" + idioma + "_responsable", normExt != null ? normExt.getResponsable() : "");
        }

        normativaDetall.put("idioma_" + idioma + "_observacions", traNorm != null ? traNorm.getObservaciones() : "");

        // archivo
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
         * Forzar content type en la cabecera para evitar bug en IE y en
         * Firefox. Si no se fuerza el content type Spring lo calcula y
         * curiosamente depende del navegador desde el que se hace la petici�n.
         * Esto se debe a que como esta petici�n es invocada desde un iFrame
         * (oculto) algunos navegadores interpretan la respuesta como un
         * descargable o fichero vinculado a una aplicación. De esta forma, y
         * devolviendo un ResponseEntity, forzaremos el Content-Type de la
         * respuesta.
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
            // Recuperació dels diferents items, tant dades con fitxers dels
            // formularis
            recuperarItems(request, valoresForm, ficherosForm);

            // Recuperamos la UA de la normativa
            UnidadAdministrativa ua = recuperarUA(valoresForm);

            // Determinar si la normativa a guardar tiene que ser local/externa
            if (ua == null) {
                normativaLocal = false;
                normativa = new NormativaExterna();
            } else {
                normativaLocal = true;
                normativa = new NormativaLocal();
                // Asociar UA si es normativa local
                ((NormativaLocal) normativa).setUnidadAdministrativa(ua);
            }

            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));

            if (edicion) {
                Long idNorm = ParseUtil.parseLong(valoresForm.get("item_id"));
                normativaOld = normativaDelegate.obtenerNormativa(idNorm);

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
                // Comprobar permisos de creación
                if (!normativaDelegate.autorizaCrearNormativa(ParseUtil.parseInt(valoresForm.get("item_validacio")))) {
                    IdNomDTO error = new IdNomDTO(-1l, messageSource.getMessage("error.permisos", null, request.getLocale()));
                    return new ResponseEntity<String>(error.getJson(), responseHeaders, HttpStatus.CREATED);
                }
            }

            // Recuperamos las traducciones
            normativa = recuperarTraducciones(valoresForm, ficherosForm, normativaOld, normativa, normativaLocal);

            // Recuperar el resto de campos de la normativa
            normativa = recuperarCamposNormativa(valoresForm, normativa);

            // Guardar la Normativa
            Long idNormativa = guardarNormativa(normativa, ua, normativaLocal);

            // Actualizar estadísticas
            //DelegateUtil.getEstadisticaDelegate().grabarEstadisticaNormativa(idNormativa);

            // Finalizado correctamente
            result = new IdNomDTO(normativa.getId(), messageSource.getMessage("normativa.guardat.correcte", null, request.getLocale()));

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

        }

        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
        
    }

    /**
     * Aquí nos llegará un multipart, de modo que no podemos obtener los datos
     * mediante request.getParameter(). Iremos recopilando los parámetros de
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
     * Obtener la UA de la normativa. Si no tiene UA asignada es una normativa
     * externa.
     */
    private UnidadAdministrativa recuperarUA(Map<String, String> valoresForm) throws DelegateException {
        UnidadAdministrativa ua = null;
        if (valoresForm.get("item_ua_id") != null && !"".equals(valoresForm.get("item_ua_id"))) {
            Long idUA = ParseUtil.parseLong(valoresForm.get("item_ua_id"));
            ua = DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(idUA);
        }
        return ua;
    }

    /**
     * Función para revisar si una normativa es editable
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

        // Comprobar que si la normativa es local se ha indicado UA y que si es externa no se ha hecho
        if (ua == null && (NormativaLocal.class.isInstance(normativaOld) || NormativaExterna.class.isInstance(normativaOld))) {
            return "error.altres";
        }

        return "";
        
    }

    /**
     * Obtener traducciones de los idiomas
     */
    private Normativa recuperarTraducciones(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm, Normativa normativaOld, Normativa normativa, boolean normativaLocal)
        throws DelegateException {
        // Obtener campos por idioma
        List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

        for (String idioma : idiomas) {
            TraduccionNormativa traNorm = normativaOld != null ? (TraduccionNormativa) normativaOld.getTraduccion(idioma) : null;
            if (traNorm != null) {
                normativa.setTraduccion(idioma, traNorm);
            } else {
                if (normativaLocal) {
                    traNorm = new TraduccionNormativa();
                } else {
                    traNorm = new TraduccionNormativaExterna();
                }
                normativa.setTraduccion(idioma, traNorm);
            }

            traNorm.setTitulo(RolUtil.limpiaCadena(valoresForm.get("item_titol_" + idioma)));
            traNorm.setEnlace(RolUtil.limpiaCadena(valoresForm.get("item_enllas_" + idioma)));
            traNorm.setApartado(RolUtil.limpiaCadena(valoresForm.get("item_apartat_" + idioma)));

            if (valoresForm.get("item_pagina_inicial_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_inicial_" + idioma))) {
                traNorm.setPaginaInicial(ParseUtil.parseInt(valoresForm.get("item_pagina_inicial_" + idioma)));
            }

            if (valoresForm.get("item_pagina_final_" + idioma) != null && !"".equals(valoresForm.get("item_pagina_final_" + idioma))) {
                traNorm.setPaginaFinal(ParseUtil.parseInt(valoresForm.get("item_pagina_final_" + idioma)));
            }

            // Responsable sólo en normativa externa
            if (!normativaLocal) {
                ((TraduccionNormativaExterna) traNorm).setResponsable(RolUtil.limpiaCadena(valoresForm.get("item_responsable_" + idioma)));
            }

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
        // Obtener los demás campos
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
     * @param normativaLocal
     * @return
     * @throws DelegateException
     */
    private Long guardarNormativa(Normativa normativa, UnidadAdministrativa ua, boolean normativaLocal) throws DelegateException {
        
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        /* NOTA IMPORTANTE PARA EL RENDIMIENTO */
        // Con este null evitamos que hibernate vaya a actualizar tablas innecesarias
        normativa.setProcedimientos(null);
        /* FIN NOTA */

        if (normativaLocal) {
            return normativaDelegate.grabarNormativaLocal((NormativaLocal) normativa, ua.getId());
        } else {
            return normativaDelegate.grabarNormativaExterna((NormativaExterna) normativa);
        }
    }

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

            // Información de paginación
            String pagPag = request.getParameter("pagPagina");
            String pagRes = request.getParameter("pagRes");

            if (pagPag == null) {
                pagPag = String.valueOf(0);
            }
            if (pagRes == null) {
                pagRes = String.valueOf(10);
            }

            // Realizar la consulta y obtener resultados
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
            ResultadoBusqueda resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap, paramTrad, "todas", null, false, false, campoOrdenacion, orden, pagPag, pagRes, false);
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
     * Obtiene una lista de NormativaDTO a partir de una lista de envíos de eboib
     * 
     * @param listadonormativas
     * @param idioma
     * @return
     */
    private List<NormativaDTO> pasarListaEboibADTO(List<TrListadoNormativaLocalBean> listadonormativas, String idioma) {

        List<NormativaDTO> llistaNormativesDTO = new ArrayList<NormativaDTO>();
        for (TrListadoNormativaLocalBean normativa : listadonormativas) {
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
            boolean local = NormativaLocal.class.isInstance(normativa);

            llistaNormativesDTO
                .add(new NormativaDTO(normativa.getId() != null ? normativa.getId().longValue() : 0, normativa.getNumero() != null ? normativa.getNumero().longValue() : -1l, titulo, normativa
                    .getFecha(), normativa.getFechaBoletin(), normativa.getBoletin() != null ? normativa.getBoletin().getNombre() : "", tipus, local ? "Local" : "Externa", normativa.isVisible()));
        }

        return llistaNormativesDTO;
    }

    // TODO: mover a clase de utilidades.
    /**
     * De un string que contiene un enlace HTML extrae el t�tulo del enlace.
     * 
     * @param texto String que contiene el enlace.
     * @return Título del enlace, si no hay enlace devuelve el texto tal cual.
     */
    private static String obtenerTituloDeEnlaceHtml(String texto) {

        if (texto == null) {
            return null;
        }

        String tmp = texto;

        // Ser� el texto desde el primer '>' y desde ah� al primer '<'
        int pos = tmp.indexOf('>');
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
        }
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
     * Recuperació dels camps dels formularis
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
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarAfectaciones.do")
	public @ResponseBody IdNomDTO guardarAfectaciones(Long id, Long[] elementos, Long[] tiposAfectacion, HttpServletRequest request) {
    	
    	/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type, Spring lo calcula y curiosamente depende del navegador desde el que se hace la petición.
		 * Esto se debe a que como esta petición es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicación. 
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
            
            // Comparar la lista actual de afectaciones con la nueva para determinar qué añadir y qué eliminar.
            for (Afectacion afectacionOld : listaActualAfectaciones) {

                // Buscar la afectación afectacionOld en la lista nueva recibida en el post
                boolean estaEnLaListaNueva = false;
                
                for (AfectacionDTO afectacionNew : afectaciones.getListaAfectaciones()) {
                
                	if (afectacionOld.getNormativa().getId().equals(afectacionNew.getNormaId()) && afectacionOld.getTipoAfectacion().getId().equals(afectacionNew.getAfectacioId())) {
                        estaEnLaListaNueva = true;
                        afectaciones.getListaAfectaciones().remove(afectacionNew);
                        break;
                    }
                
                }
                
                // Si no está en la lista nueva es que hay que eliminarla
                if (!estaEnLaListaNueva) {
                    normativaDelegate.eliminarAfectacion(normativa.getId(), afectacionOld.getTipoAfectacion().getId(), afectacionOld.getNormativa().getId());
                }
                
            }

            // Añadir afectaciones
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

}
