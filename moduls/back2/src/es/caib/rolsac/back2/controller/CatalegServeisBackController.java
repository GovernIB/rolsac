package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.DocumentoServicio;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalServicio;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SilencioAdm;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
import org.ibit.rol.sac.model.TraduccionDocumentoServicio;
import org.ibit.rol.sac.model.TraduccionExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionServicio;
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.TraduccionTipoAfectacion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.model.criteria.BuscadorServicioCriteria;
import org.ibit.rol.sac.model.dto.CodNomDTO;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.ServicioDTO;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.CatalegDocumentsDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoServicioDelegate;
import org.ibit.rol.sac.persistence.delegate.ExcepcioDocumentacioDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalServicioDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.ServicioDelegate;
import org.ibit.rol.sac.persistence.delegate.SilencioAdmDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.CSVUtil;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.GuardadoAjaxUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.LlistatUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;
import es.indra.rol.sac.integracion.traductor.TraductorException;

@Controller
@RequestMapping("/catalegServeis/")
public class CatalegServeisBackController extends PantallaBaseController {

	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.servei.url";
	private static Log log = LogFactory.getLog(CatalegServeisBackController.class);

	@RequestMapping(value = "/catalegServeis.do")
	public String pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request) {

		String lang;
		
		try {
			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
		} catch (DelegateException dEx) {
			log.error("Error al recuperar el idioma por defecto.");
			lang = "ca";
		}

		if (estemEnUnitatAdministrativa(session)) {
		    crearModelComplert_pantalla(model, session, request, lang);
		} else {
		    crearModelSencill_pantalla(model, session, request, lang);
		}

		loadIndexModel (model, request);
		
		//#427 Listas para el buscador  de normativas. Las pasamos a DTO. 
        // Lo ponemos en try catch para evitar que esto bloquee cualquier recuperación 
        try {
            String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
            // Boletines.
            model.put("llistaButlletins", getListaBoletinesDTO());
            // Tipos normativa.
            model.put("llistaTipusNormativa", getListaTiposNormativaDTO(idioma));
            // Tipos afectacion.
            model.put("llistaTipusAfectacio", getListaTiposAfectacionDTO(idioma));

        } catch (DelegateException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        
		
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

	private boolean estemEnUnitatAdministrativa(HttpSession session) {
		return null != getUAFromSession(session);
	}

	private void crearModelComplert_pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request, String lang) {

		crearModelSencill_pantalla(model, session, request, lang);
		model.put("idUA", getUAFromSession(session).getId());
		model.put("nomUA", getUAFromSession(session).getNombreUnidadAdministrativa(lang));

	}

	private void crearModelSencill_pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request, String lang) {

		try {

			model.put("menu", 0);
			model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
			model.put("submenu_seleccionado", 6);
			model.put("escriptori", "pantalles/catalegServeis.jsp");
			model.put("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));
			model.put("llistaMateries", LlistatUtil.llistarMaterias(lang));
			model.put("llistaPublicsObjectiu", LlistatUtil.llistarPublicObjectius(lang));
			model.put("families", LlistatUtil.llistarFamilias(lang));
			model.put("iniciacions", LlistatUtil.llistarIniciacions(lang));
			model.put("llistaSilenci", llistarSilenci(lang));
			model.put("excepcions", llistarExcepcionsDocumentacio(lang));
			model.put("cataleg", llistarCatalegDocuments(lang));

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				logException(log, dEx);
			}
		}
	}

	private List<CodNomDTO> llistarSilenci(String lang) throws DelegateException {
		//#366 se carga el combo silencio adm y su selección
        SilencioAdmDelegate silencioDelegate = DelegateUtil.getSilencioAdmDelegate();
		List<CodNomDTO> llistaSilencioDTO = new ArrayList<CodNomDTO>();
		List<SilencioAdm> llistaSilencio = new ArrayList<SilencioAdm>();
		
		llistaSilencio = silencioDelegate.listarSilencioAdm();
		for (SilencioAdm silAdm : llistaSilencio) {
			llistaSilencioDTO.add(new CodNomDTO(
					silAdm.getId().toString(),
					silAdm.getNombreSilencio(DelegateUtil.getIdiomaDelegate().lenguajePorDefecto())
			));
		}
		return llistaSilencioDTO;
	}

	private List<IdNomDTO> llistarExcepcionsDocumentacio(String lang) throws DelegateException {
		
        ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
        List<IdNomDTO> excepcioObjDTOList = new ArrayList<IdNomDTO>();
        List<ExcepcioDocumentacio> llistaExcepcionsDocumentacio = excepcioDelegate.llistarExcepcioDocumentacio();
        TraduccionExcepcioDocumentacio ted;
        
        for (ExcepcioDocumentacio excepcio : llistaExcepcionsDocumentacio ) {
            ted = (TraduccionExcepcioDocumentacio) excepcio.getTraduccion(lang);
            excepcioObjDTOList.add(new IdNomDTO(excepcio.getId(), ted.getNombre()));
        }
        
        return excepcioObjDTOList;
        
    }

	private List<IdNomDTO> llistarCatalegDocuments(String lang) throws DelegateException {

        CatalegDocumentsDelegate catdocDelegate = DelegateUtil.getCatalegDocumentsDelegate();
        List<IdNomDTO> catalegObjDTOList = new ArrayList<IdNomDTO>();
        List<CatalegDocuments> llistaCatalegDocuments = catdocDelegate.llistarCatalegDocuments();
        TraduccionCatalegDocuments tcd;
        
        for (CatalegDocuments document : llistaCatalegDocuments ) {
            tcd = (TraduccionCatalegDocuments) document.getTraduccion(lang);
            catalegObjDTOList.add(new IdNomDTO(document.getId(), tcd.getNombre()));
        }
        
        return catalegObjDTOList;
        
    }


	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistat(String criteria, HttpServletRequest request, HttpSession session) {

		Map<String, Object> resultats = new HashMap<String, Object>();
		BuscadorServicioCriteria buscadorCriteria = this.jsonToBuscadorServicioCriteria(criteria);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		List<ServicioDTO> llistaServicioDTO = new ArrayList<ServicioDTO>();

		if (getUAFromSession(session) != null && buscadorCriteria != null) {
			
			try {
				
				UnidadAdministrativa ua = getUAFromSession(session);
				buscadorCriteria.setUnidadAdministrativa(ua);

				ServicioDelegate serviciosDelegate = DelegateUtil.getServicioDelegate();
				resultadoBusqueda = serviciosDelegate.buscadorServicios(buscadorCriteria);
				llistaServicioDTO.addAll(convertirServeisLocales(resultadoBusqueda, request));

			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
				    resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
				} else {
				    resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
				}
				
			}
			
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaServicioDTO);

		return resultats;
		
	}
	
	
	@RequestMapping(value = "/exportar.do", method = POST)
	public void exportar(String criteria, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws Exception {

		BuscadorServicioCriteria buscadorCriteria = this.jsonToBuscadorServicioCriteria(criteria);
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		if (getUAFromSession(session) != null && buscadorCriteria != null) {
			
			try {
				
				UnidadAdministrativa ua = getUAFromSession(session);
				buscadorCriteria.setUnidadAdministrativa(ua);

				ServicioDelegate serviciosDelegate = DelegateUtil.getServicioDelegate();
				buscadorCriteria.setSoloId(true);
				resultadoBusqueda = serviciosDelegate.buscadorServicios(buscadorCriteria);
				CSVUtil.mostrarCSV(response, convertirServeisToCSV((List<Object[]>) resultadoBusqueda.getListaResultados()));

			} catch (Exception dEx) {
				log.error("Error generando el export de la búsqueda en servicios.",dEx);
				throw new Exception(dEx);
			}
			
		}

	}
	
	
	
	
	/**
	 * Convierte servicio a String para CSV.
	 * @param listaResultados
	 * @return
	 */
	private String convertirServeisToCSV(List<Object[]> listaResultados) {
		StringBuffer retorno = new StringBuffer();
		
		//cabecera!
		retorno.append("CODI_SERVEI;");
		retorno.append("CODI_SIA;");
		retorno.append("ESTAT_SIA;");
		retorno.append("DATA_ACTUALITZACIO_SIA;");
		retorno.append("ESTAT_SERVEI;");
		retorno.append("VISIBILITAT_SERVEI;");
		retorno.append("NOM_SERVEI_CA;");
		retorno.append("NOM_SERVEI_ES;");
		retorno.append("OBJECTE_CA;");
		retorno.append("PUBLIC_OBJECTIU;");
		retorno.append("NOM_UA_RESPONSABLE;");
		retorno.append("NOM_UA_INSTRUCTOR;");
		retorno.append("NOM_RESPONSABLE;");
		retorno.append("NUM NORMES;");
		retorno.append("DATA_ACTUALITZACIO;");
		retorno.append("COD_USUARI_DARRERA_ACT;");
		retorno.append("NOM_USUARI_DARRERA_ACT;");
		retorno.append("\n");
		
		final ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
		final AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
		final UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
		//Contenido.
		for (Object[] filaResultado : listaResultados) {
			Servicio servicio = null;
			Long idServicio = Long.valueOf(filaResultado[0].toString()); 
			try {
				servicio = servicioDelegate.obtenerServicioParaSolr(idServicio, null);
			} catch (Exception exception) {
				log.error("Error obteniendo el servicio con id : " + idServicio , exception);
				retorno.append(CSVUtil.limpiar(idServicio));
				retorno.append(ExceptionUtils.getCause(exception));
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}
			
			if (servicio == null) {
				retorno.append(CSVUtil.limpiar(idServicio));
				retorno.append("Servicio nulo");
				retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
				continue;
			}
			
			//Extraemos datos.
			TraduccionServicio tradEs = (TraduccionServicio) servicio.getTraduccion("es");
			TraduccionServicio tradCa = (TraduccionServicio) servicio.getTraduccion("ca");
			String nomEs, nomCa;
			String objecte;
			if (tradEs == null) {
				nomEs = "";
			} else {
				nomEs = tradEs.getNombre();
			}

			if (tradCa == null) {
				nomCa = "";
			} else {
				nomCa = tradCa.getNombre();
			}
			
			if (tradCa != null) {
				objecte = tradCa.getObjeto();
			} else if (tradEs != null) {
				objecte = tradEs.getObjeto();
			} else {
				objecte = "";
			}
			
			String publicoObjectivo = "";
			if (servicio.getPublicosObjetivo() != null) {
				for(PublicoObjetivo po : servicio.getPublicosObjetivo()) {
					publicoObjectivo += po.getId() + " ,";
				}
				
				if (publicoObjectivo.endsWith(",")) { publicoObjectivo = publicoObjectivo.substring(0, publicoObjectivo.length()- 1); }
			}
			
			
			 
			int num_nombres;
			if (servicio.getNormativas() == null) {
				num_nombres = 0;
			} else {
				num_nombres = servicio.getNormativas().size();
			}
			
			String estado;
			if (servicio.getValidacion().compareTo(1) == 0) {
				estado = "PUBLIC";
			} else if (servicio.getValidacion().compareTo(2) == 0) {
				estado = "INTERN";
			} else {
				estado = "RESERVA";
			}
			
			String codUsuario = "", nomUsuario = "";
			try {
				List<Auditoria> llista = auditoriaDelegate.listarAuditoriasServicio(idServicio);
				Collections.sort(llista, new Comparator< Auditoria >( ){

					/**
					 * Teniendo en cuenta la siguiente simbología:
					 * <p> -1 : o1 < o2   </p> 
					 * <p>  0 : o1 == o2  </p>
					 * <p> +1 : o1 > o2   </p>
					 * Se penaliza que no tenga usuario o que no tenga fecha.
					 * 
					 * En caso que ambos tengan usuario y fecha, se compara la fecha.
					 * <b>Como se ve, se da el valor al revés, para ordenar hacia abajo por fecha.</b>
					 * 
					 * @param o1
					 * @param o2
					 * @return
					 */
					public int compare(Auditoria o1, Auditoria o2) {
						//Si o1 no tiene usuario o fecha
						if (o1.getFecha() == null || o1.getUsuario() == null || o1.getUsuario().isEmpty()) {
							return 1;
						}
						
						//Si o2 no tiene usuario o fecha.
						if (o2.getFecha() == null || o2.getUsuario() == null || o2.getUsuario().isEmpty()) {
							return -1;
						}
						
						//Se compara por fecha.
						return o2.getFecha().compareTo(o1.getFecha());
					}
				
				} );
				
				for (Auditoria auditoria : llista) {
					Usuario usuario = usuarioDelegate.obtenerUsuariobyUsernamePMA(auditoria.getUsuario());
					if (usuario != null){									
						codUsuario = usuario.getUsername();
						nomUsuario = usuario.getNombre();
						break;
					}
				}								
			} catch (DelegateException e) {
				log.error("Error obteniendo auditorias del servicio id:"+idServicio, e);
			}
			
			String estadoSIA = servicio.getEstadoSIA();
			if (estadoSIA != null) {
				if ("A".equals(estadoSIA)) {
					estadoSIA = "Alta";
				} else if ("B".equals(estadoSIA)) {
					estadoSIA = "Baixa";
				} else if ("M".equals(estadoSIA)) {
					estadoSIA = "Modificació";
				} else if ("R".equals(estadoSIA)) {
					estadoSIA = "Reactivació";
				}
			}
			
			retorno.append(CSVUtil.limpiar(servicio.getId())); 		//CODI_SERVEI,
			retorno.append(CSVUtil.limpiar(servicio.getCodigoSIA()));  //CODI_SIA
			retorno.append(CSVUtil.limpiar(estadoSIA));	//ESTAT_SIA
			retorno.append(CSVUtil.limpiar(servicio.getFechaSIA()));	//DATA_ACTUALITZACIO_SIA
			retorno.append(CSVUtil.limpiar(estado)); 						//ESTAT_SERVEI DECODE(PRO_VALIDA,1,'PUBLIC',2,'INTERN','RESERVA')
			retorno.append(CSVUtil.limpiar(servicio.isVisible()));		//VISIBILITAT_SERVEI (ESTAT+DATA_PUB+DATA_CAD + UA_VISIBLE)
			retorno.append(CSVUtil.limpiar(nomCa)); 						//NOM_SERVEI_CA,
			retorno.append(CSVUtil.limpiar(nomEs));							//NOM_SERVEI_ES,
			retorno.append(CSVUtil.limpiar(objecte));						//OBJECTE_CA
			retorno.append(CSVUtil.limpiar(publicoObjectivo));				//PUBLIC_OBJECTIU (ID_PUBLIC OBJECTIU SEPARATS PER COMES)
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(servicio.getServicioResponsable())));		//NOM UA_RESPONSABLE
			retorno.append(CSVUtil.limpiar(CSVUtil.getNombreUA(servicio.getOrganoInstructor())));		//NOM UA_INSTRUCTOR
			retorno.append(CSVUtil.limpiar(servicio.getNombreResponsable()));//NOM_RESPONSABLE
			retorno.append(CSVUtil.limpiar(num_nombres));					//NUM NORMES
			retorno.append(CSVUtil.limpiar(servicio.getFechaActualizacion())); //DATA_ACTUALITZACIO
			retorno.append(CSVUtil.limpiar(codUsuario));					//COD_USUARI_DARRERA_ACT
			retorno.append(CSVUtil.limpiar(nomUsuario));					//NOM_USUARI_DARRERA_ACT
			retorno.append(CSVUtil.CARACTER_SALTOLINEA_CSV);
		}
		
		return retorno.toString();		
	}

	/** 
	 * Método que se encarga de convertir un String en formato json a una instancia de BuscadorServicioCriteria 
	 * @param criteria
	 * @return
	 */
	private BuscadorServicioCriteria jsonToBuscadorServicioCriteria (String criteria) {

		BuscadorServicioCriteria buscadorCriteria = null;

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

			buscadorCriteria = mapper.readValue(criteria, BuscadorServicioCriteria.class);
			buscadorCriteria.setLocale( DelegateUtil.getIdiomaDelegate().lenguajePorDefecto() ); 

		} catch (JsonParseException e) {
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (DelegateException e) {
			log.error(e.getMessage());
		} 

		return buscadorCriteria;
		
	}

	/**
	 * Función para convertir a servicios locales los resultados
	 * @param resultadoBusqueda
	 * @param request
	 * @return
	 */
	private List<ServicioDTO> convertirServeisLocales(ResultadoBusqueda resultadoBusqueda, HttpServletRequest request) {

		List<ServicioDTO> llistaServicioDTO = new ArrayList<ServicioDTO>();
		for (Servicio pl : castList(Servicio.class, resultadoBusqueda.getListaResultados())) {
			
			ServicioDTO servicioDTO = new ServicioDTO(
			    pl.getId(),
			    pl.getNombreServicio(),
			    pl.isVisible(),
			    DateUtils.formatDate(pl.getFechaActualizacion())
			);

			llistaServicioDTO.add(servicioDTO);
			
		}
		
		return llistaServicioDTO;
		
	}


	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(Long id, HttpSession session, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
		    IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			
			String lang = idiomaDelegate.lenguajePorDefecto();

			ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
			Servicio serv = servicioDelegate.obtenerServicioNewBack(id);

			resultats.put("item_id", serv.getId());
			resultats.put("item_codigo_servicio", serv.getCodigo());
			resultats.put("item_codigo_sia", serv.getCodigoSIA()); //#366 Se añade SIA
			resultats.put("item_fecha_sia", DateUtils.formatDate(serv.getFechaSIA())); //#366 Se añade fecha SIA
			resultats.put("item_estado_sia", serv.getEstadoSIA()); //#366 Se añade estado SIA
			resultats.put("item_estat", serv.getValidacion());						
			resultats.put("item_data_actualitzacion", DateUtils.formatDate(serv.getFechaActualizacion()));
			resultats.put("item_data_publicacion", DateUtils.formatDateSimpleTime(serv.getFechaPublicacion()));
			resultats.put("item_data_despublicacion", DateUtils.formatDateSimpleTime(serv.getFechaDespublicacion()));
			resultats.put("item_codigo", serv.getCodigo());
			resultats.put("item_tasa_url", serv.getTasaUrl());
			resultats.put("item_telefon", serv.getTelefono());
			resultats.put("item_email", serv.getCorreo());
			resultats.put("item_responsable_nombre", serv.getNombreResponsable());
			resultats.put("item_tramite_url", serv.getTramiteUrl());
			resultats.put("item_tramite_version", serv.getTramiteVersion());
			resultats.put("item_tramite_id", serv.getTramiteId());
    		resultats.put("item_check_tramit_presencial", serv.isPresencial());
    		resultats.put("item_check_tramit_telematico", serv.isTelematico());
    		resultats.put("item_check_tramit_telefonico", serv.isTelefonico());
			
			if (serv.getServicioResponsable() != null) {
				UnidadAdministrativa ua = serv.getServicioResponsable();
				resultats.put("item_servei_responsable_id", ua.getId());
				resultats.put("item_servei_responsable_nom", ua.getNombreUnidadAdministrativa(lang));
			}

			if (serv.getOrganoInstructor() != null) {
				UnidadAdministrativa ua = serv.getOrganoInstructor();
				resultats.put("item_organ_instructor_id", ua.getId());
				resultats.put("item_organ_instructor_nom", ua.getNombreUnidadAdministrativa(lang));
			}

			// Obtención de listado de posibles hechos vitales del servicio
			Set<PublicoObjetivo> listaPublicosObjetivos =  serv.getPublicosObjetivo();
			if (!listaPublicosObjetivos.isEmpty()) {
				resultats.put("listadoHechosVitales", LlistatUtil.llistarHechosVitales(listaPublicosObjetivos, lang));
			} else {
				resultats.put("listadoHechosVitales", Collections.EMPTY_SET);
			}

			recuperaIdiomas(resultats, serv, lang);         // Recuperar los servicios según los idiomas
            recuperaPO(resultats, serv, lang);              // Recuperar los públicos objetivos asociados a un servicio


		} catch (DelegateException dEx) {
			
			logException(log, dEx);
			
			if ( dEx.isSecurityException() ){
				resultats.put( "error", messageSource.getMessage( "error.permisos", null, request.getLocale() ) );
			} else {
				resultats.put( "error", messageSource.getMessage( "error.altres", null, request.getLocale() ) );
			}
			
		}

		return resultats;
		
	}
	
	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();
			
			ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
			Servicio serv = servicioDelegate.obtenerServicioNewBack(id);
			
			//  Pasamos el Set de materias relacionados a un List.
			List<Materia> listaMaterias = new ArrayList<Materia>(serv.getMaterias());
            resultats.put("materies", CargaModulosLateralesUtil.recuperaMateriasRelacionadas(listaMaterias, id, lang, false));
            
            // Pasamos el Set de HHVV relacionados a un List.
            List<HechoVitalServicio> listaHechosVitales = new ArrayList<HechoVitalServicio>(serv.getHechosVitalesServicios());
            resultats.put("fetsVitals", CargaModulosLateralesUtil.recuperaHechosVitalesRelacionados(listaHechosVitales, HechoVitalServicio.class, id, lang, true));
            
			// Documentos relacionados.
			Set<DocumentoServicio> listaDocumentos = serv.getDocumentos();
			resultats.put("documents", recuperaDocumentosRelacionados(listaDocumentos, id, idiomas));
           
			recuperaNormativas(resultats, serv, lang, id);      // Recuperar las normativas asociadas a un servicio
			
		} catch (DelegateException dEx) {

			log.error(ExceptionUtils.getStackTrace(dEx));

			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));

			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}

	/*
	 * Función que recupera el contenido de los servicios según el idioma.
	 */
	private void recuperaIdiomas(Map<String, Object> resultats, Servicio serv, String langDefault) throws DelegateException {

	    List<String> langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
	    
        for (String lang : langs) {
            if (serv.getTraduccion(lang) != null) {
                resultats.put(lang, serv.getTraduccion(lang));
            } else {
                if (serv.getTraduccion(langDefault) != null) {
                    resultats.put(lang, serv.getTraduccion(langDefault));
                } else {
                    resultats.put(lang, new TraduccionServicio());
                }
            }
        }
        
	}
	

	/*
	 * Función para recuperar los públicos objeticos de un servicio
	 */
	private void recuperaPO(Map<String, Object> resultats, Servicio serv, String lang) {

		if (serv.getPublicosObjetivo() != null) {
			
			List<IdNomDTO> llistaPublicsDTO = new ArrayList<IdNomDTO>();
			
			for (PublicoObjetivo pob : serv.getPublicosObjetivo()) {
				TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo)pob.getTraduccion(lang);
				llistaPublicsDTO.add(new IdNomDTO(pob.getId(), tpob == null ? "" : tpob.getTitulo()));
			}
			
			resultats.put("publicsObjectiu", llistaPublicsDTO);

		} else {
			
			resultats.put("publicsObjectiu", null);
			
		}
		
	}

	/*
	 * Función para recuperar las materias de un servicio
	 */
	private void recuperaNormativas(Map<String, Object> resultats, Servicio serv, String lang, Long idServicio) {

		if (serv.getNormativas() != null) {
			
			Map<String, String> map;
			List<Map<String, String>> llistaNormatives = new ArrayList<Map<String, String>>();
			TraduccionNormativa traNor;
			String titulo;

			for (Normativa normativa : serv.getNormativas()) {
				
				traNor = (TraduccionNormativa)normativa.getTraduccion(lang);
				
				// Retirar posible enlace incrustado en titulo
				titulo = (traNor == null) ? "" : HtmlUtils.obtenerTituloDeEnlaceHtml(traNor.getTitulo());
				map = new HashMap<String, String>();
				map.put("id", normativa.getId().toString());
				map.put("nombre", titulo);
				map.put("idMainItem", idServicio.toString());
				map.put("idRelatedItem", normativa.getId().toString());
				
				llistaNormatives.add(map);
				
			}
			
			resultats.put("normatives", llistaNormatives);

		} else {
			
			resultats.put("normatives", null);
			
		}
		
	}

	@RequestMapping(value = "/esborrarServei.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {

		IdNomDTO resultatStatus = new IdNomDTO();

		try {
			
			Long id = new Long(request.getParameter("id"));
			ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
			servicioDelegate.borrarServicio(id);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);dEx.printStackTrace();
			}
			
		}  catch (Exception dEx) {
			
			resultatStatus.setId(-2l);
			logException(log, dEx);
			
		}

		return resultatStatus;
		
	}
	

	@RequestMapping(value = "/checkNormativaVigente.do", method = POST)
	public @ResponseBody IdNomDTO checkNormativaVigente(HttpSession session, HttpServletRequest request) {
		IdNomDTO result = null;
		String error = null;

		try {
			Long id = null;
			if (!request.getParameter("id").isEmpty()) {
				
				ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
				id = Long.parseLong(request.getParameter("id"));
				
				if (id != null) {
					if (servicioDelegate.isNormativaDerogada(id)) {
						error = messageSource.getMessage("serv.error.normativa.derogadas", null, request.getLocale());
						return result = new IdNomDTO(-1l, error);
					}
				}
			}
			
			return new IdNomDTO(id, "");
		} catch (Exception exception) {
			error = exception.getMessage();
			result = new IdNomDTO(-66l, error);
			
		} 

		return result;
		
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardar(HttpSession session, HttpServletRequest request) {
		
		IdNomDTO result = null;
		String error = null;

		try {
			
			if (request.getParameter("publicsObjectiu") == null || request.getParameter("publicsObjectiu").equals("")) {
				error = messageSource.getMessage("serv.error.falta.public", null, request.getLocale());
				return result = new IdNomDTO(-3l, error);
			}
			

			ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
			Servicio servicio = new Servicio();
			Servicio servicioOld;

			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				servicioOld = servicioDelegate.obtenerServicioNewBack(id);
				edicion = true;
			} catch (NumberFormatException nfe) {
				servicioOld = null;
				edicion = false;
			}

			//Solo si es edicion, es obligado tener materias
			if (edicion &&  (request.getParameter("materies") == null || request.getParameter("materies").equals(""))) {
				error = messageSource.getMessage("serv.error.falta.materia", null, request.getLocale());
				return result = new IdNomDTO(-4l, error);
			} 
				
			
			servicio = guardarPublicoObjetivo(request, servicio, servicioOld);			// Procesar Público Objectivo
			///Actualizamos lo que viene de pantalla para servicio Publico Objetivo en servicioOld para que 
			///en guardarServicioAntiguo no lo machaque en modo edicion
			if (edicion) {
				servicioOld.setPublicosObjetivo(servicio.getPublicosObjetivo());		        // Procesar Público Objectivo				
			}			
			///
			servicio = guardarIdioma(request, servicio, servicioOld);       			// Idiomas
			servicio = guardarValidacion(request, servicio, servicioOld, error);		// Validación
			servicio = guardarFechaPublicacion(request, servicio);						// Fecha Publicación
			servicio = guardarFechaDespublicacion(request, servicio);					// Fecha Caducidad
			servicio = guardarOrganInstructor(request, servicio, error);				// Organ Resolutori
			servicio = guardarServeiResponsable(request, servicio, error);				// Servei Responsable
			
			//Cargamos los datos básicos
			servicio.setNombreResponsable(request.getParameter("item_responsable_nombre"));	// Responsable
			servicio.setCodigo(request.getParameter("item_codigo_servicio"));			// Codigo
			servicio.setCorreo(request.getParameter("item_email"));						// Email
			servicio.setTelefono(request.getParameter("item_telefon"));					// Telefon
			servicio.setTramiteId(request.getParameter("item_tramite_id")==null?"":request.getParameter("item_tramite_id"));				// Tramite id
			servicio.setTramiteUrl(request.getParameter("item_tramite_url")==null?"":request.getParameter("item_tramite_url"));			// Tramite url
			
			String version =request.getParameter("item_tramite_version")==null ?"":request.getParameter("item_tramite_version");
			servicio.setTramiteVersion("".equals(version)?"0":version);	// Tramite version
			servicio.setTasaUrl(request.getParameter("item_tasa_url"));					// Tasa url
			
			
			servicio.setTelematico( request.getParameter("item_check_tramit_telematico") != null && !"".equals(request.getParameter("item_check_tramit_telematico")));
			servicio.setPresencial( request.getParameter("item_check_tramit_presencial") != null && !"".equals(request.getParameter("item_check_tramit_presencial")));
			servicio.setTelefonico( request.getParameter("item_check_tramit_telefonico") != null && !"".equals(request.getParameter("item_check_tramit_telefonico")));
			
			boolean urlTramiteRelleno = !servicio.getTramiteUrl().equals("");
			boolean idTramiteTelleno = !servicio.getTramiteId().equals("") && !"".equals(version);
			boolean idTramiteIncoherente = (!servicio.getTramiteId().equals("") && "".equals(version))  || 
											(servicio.getTramiteId().equals("") && ( !"".equals(version) && !"0".equals(version)));
			
			//si es telematico debe estar rellenos url o version+id, pero no ambos.
			if(servicio.isTelematico()) {
				//Traramos la posible incoherencia de datos
				if( urlTramiteRelleno && idTramiteTelleno || //estan los dos completados  
					!urlTramiteRelleno && !idTramiteTelleno || // ninguno esta completado	
					idTramiteIncoherente) { //el id y version es incoherente (uno si y el otro no)
						error = messageSource.getMessage("proc.formulari.error.telematic.sensedades", null, request.getLocale());
			            return result = new IdNomDTO(-2l, error);			            				
				}
			}else {
				// si no es telemático vaciamos los campos.
				servicio.setTramiteVersion( "0" );
				servicio.setTramiteUrl("");
				servicio.setTramiteId("");
			}			
			
			if (edicion) {
				
				// Verificamos que no se "pierda" el cod SIA
				if (StringUtils.isNotBlank(request.getParameter("item_codigo_sia")) && 
					 !StringUtils.equals(request.getParameter("item_codigo_sia"), servicioOld.getCodigoSIA())) {
					log.error("Error: el parámetro item_codigo_sia de la pantalla no concuerda con el de la BBDD.");
					throw new IllegalStateException("error_sia_incorrecto");
				}
				
				servicio = guardarServicioAntiguo(servicio, servicioOld);		// Si estamos guardando un servicio ya existente en vez de uno nuevo
				
				
			}

			Long servId = guardarGrabar(servicio);

			String ok = messageSource.getMessage("serv.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(servId, ok);

		} catch (DelegateException dEx) {
			log.error(dEx);
			if (dEx.isSecurityException()) {
				
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
				
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				logException(log, dEx);
			}

		} catch (NumberFormatException nfe) {
			log.error(nfe);
			error = nfe.getMessage();
			result = new IdNomDTO(-3l, error);

		} catch (ParseException pe) {
			log.error(pe);
			error = pe.getMessage();
			result = new IdNomDTO(-4l, error);
			
		} catch (IllegalStateException ise) {
			log.error(ise);
			if ("error_sia_incorrecto".equals(ise.getMessage())) {
				error = messageSource.getMessage("error.servei_codigo_sia_incorrecto", null, request.getLocale());
			} else {
				error = ise.getMessage();
			}
			result = new IdNomDTO(-5l, error);
			
		} catch (Exception pe) {
			log.error(pe);
			error = pe.getMessage();
			result = new IdNomDTO(-4l, error);
			
		} 

		return result;
		
	}

	

	/*
	 * Guardamos el servicio anterior si se trata de una edición. 
	 */
	private Servicio guardarServicioAntiguo(Servicio servicio, Servicio servicioOld) {

		// Mantenemos los valores originales que tiene el servicio.
		servicio.setId(servicioOld.getId());
		servicio.setPublicosObjetivo(servicioOld.getPublicosObjetivo());
		servicio.setMaterias(servicioOld.getMaterias());
		servicio.setNormativas(servicioOld.getNormativas());
		
		//Estos campos no se encuentran en la pantalla y se perderian sus valores al guardar
		servicio.setFechaSIA(servicioOld.getFechaSIA());
		servicio.setEstadoSIA(servicioOld.getEstadoSIA());
		servicio.setCodigoSIA(servicioOld.getCodigoSIA());
		return servicio;
		
	}

	/**
	 *  Para hacer menos accesos a BBDD se comprueba si es edicion o no.
	 * En el primer caso es bastante probable que se repitan la mayoria de public objectiu.
	 * 
	 * @param request
	 * @param servicio
	 * @param servicioOld
	 * @return
	 * @throws DelegateException
	 */
	private Servicio guardarPublicoObjetivo(HttpServletRequest request, Servicio servicio,  Servicio servicioOld) throws DelegateException {

	    if (isModuloModificado("modul_public_modificat", request)) {

			if (request.getParameter("publicsObjectiu") != null && !"".equals(request.getParameter("publicsObjectiu"))) {
			    
				String idioma = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
				Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
				publicsNous.addAll(publicObjDelegate.obtenerPublicosObjetivoPorIDs(request.getParameter("publicsObjectiu"), idioma));
				servicio.setPublicosObjetivo(publicsNous);
			} else {
				servicio.setPublicosObjetivo(new HashSet<PublicoObjetivo>());
			}
			
		}else{
			//#349
			servicio.setPublicosObjetivo(servicioOld.getPublicosObjetivo());
		}
		return servicio;
	}

	/*
	 * Traducimos al idioma deseado del servicio.
	 */
	private Servicio guardarIdioma(HttpServletRequest request, Servicio servicio, Servicio servicioOld) 
			throws DelegateException {

		TraduccionServicio tpl;
		
		for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
			
			tpl = (TraduccionServicio) ((servicioOld != null) ? servicioOld.getTraduccion(lang) : new TraduccionServicio());
			
			if (tpl == null) {
                tpl = new TraduccionServicio();
            }
			
			tpl.setNombre(RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)));
			tpl.setObjeto(RolUtil.limpiaCadena(request.getParameter("item_objeto_" + lang)));
			tpl.setDestinatarios(RolUtil.limpiaCadena(request.getParameter("item_destinatarios_" + lang)));
			tpl.setRequisitos(RolUtil.limpiaCadena(request.getParameter("item_requisitos_" + lang)));
			tpl.setObservaciones(RolUtil.limpiaCadena(request.getParameter("item_observaciones_" + lang)));
			
			servicio.setTraduccion(lang, tpl);
			
		}

		return servicio;
		
	}

	/*
	 * Controlamos la validación del servicio.
	 */
	private Servicio guardarValidacion(HttpServletRequest request, Servicio servicio, 
			Servicio servicioOld, String error) throws DelegateException {

		try {
			
			Integer validacion = Integer.parseInt(request.getParameter("item_estat"));
			
			// Si es superusuario no haremos ninguna comprobación.
			if (!request.isUserInRole("sacsystem")) {
				// Comprobar que no se haya cambiado la validacion/estado siendo operador.
				if (request.isUserInRole("sacoper") && servicioOld != null && !servicioOld.getValidacion().equals(validacion)) {
				    throw new DelegateException(new SecurityException());
				}
			}

			servicio.setValidacion(validacion);

		} catch (NumberFormatException e) {
			
			error = messageSource.getMessage("serv.error.estat.incorrecte", null, request.getLocale());
			throw new NumberFormatException(e.getMessage());
			
		}

		return servicio;
		
	}

	/*
	 * Controlamos el formato de la fecha de publicación.
	 */
	private Servicio guardarFechaPublicacion(HttpServletRequest request, Servicio servicio) 
			throws ParseException {

		if (parametroNoNulo(request, "item_data_publicacion")) {
			
			Date data_publicacio = DateUtils.parseDateSimpleTime(request.getParameter("item_data_publicacion"));
			
			if (data_publicacio == null) {
			    throw new ParseException("error.data_publicacio", 0);
			}

			servicio.setFechaPublicacion(data_publicacio);
			
		}

		return servicio;
		
	}

	/*
	 * Controlamos el formato de la fecha de caducidad del servicio.
	 */
	private Servicio guardarFechaDespublicacion(HttpServletRequest request, Servicio servicio) 
			throws ParseException {

		if (parametroNoNulo(request, "item_data_despublicacion")) {
			
			Date data_despublicacion = DateUtils.parseDateSimpleTime(request.getParameter("item_data_despublicacion"));
			servicio.setFechaDespublicacion(data_despublicacion);
			
		}

		return servicio;
		
	}


	/**
	 * Comprueba que el parametro del request, además de ser no nulo, no está vacío.
	 * @param request
	 * @param parametro
	 * @return
	 */
	private boolean parametroNoNulo(HttpServletRequest request, String parametro) {
		boolean esParametroNoNulo;
		if (request.getParameter(parametro) != null && !"".equals(request.getParameter(""))) {
			esParametroNoNulo = true;
		} else {
			esParametroNoNulo = false;
		}
		return esParametroNoNulo;
	}

	/*
	 * Obtenemos el Organo resolutorio del servicio.
	 */
	private Servicio guardarOrganInstructor(HttpServletRequest request, Servicio servicio, String error) 
			throws DelegateException {

		if (parametroNoNulo(request, "item_organ_instructor_id")) { 
			
			try {
				
				Long organId = Long.parseLong(request.getParameter("item_organ_instructor_id"));
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				UnidadAdministrativa organ = uaDelegate.obtenerUnidadAdministrativa(organId);
				servicio.setOrganoInstructor(organ);

			} catch (NumberFormatException e) {
				
				error = messageSource.getMessage("serv.error.organ.incorrecte", null, request.getLocale());
				throw new NumberFormatException(e.getMessage());
				
			}
			
		}

		return servicio;
	

	}
	
	
	
	/*
	 * Obtenemos el Servei del responsable del servicio.
	 */
	private Servicio guardarServeiResponsable(HttpServletRequest request, Servicio servicio, String error) 
			throws DelegateException {

		if (parametroNoNulo(request, "item_servei_responsable_id")) {
			
			try {
				
				Long organId = Long.parseLong(request.getParameter("item_servei_responsable_id"));
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
				UnidadAdministrativa organ = uaDelegate.obtenerUnidadAdministrativa(organId);
				servicio.setServicioResponsable(organ);

			} catch (NumberFormatException e) {
				
				error = messageSource.getMessage("serv.error.organ.incorrecte", null, request.getLocale());
				throw new NumberFormatException(e.getMessage());
				
			}
			
		}

		return servicio;
		
	}

	/*
	 * Función de grabar() servicio
     */
    private Long guardarGrabar(Servicio servicio) throws DelegateException {

        /* NOTA IMPORTANTE PARA EL RENDIMIENTO */
        servicio.setDocumentos(null);
        /* FIN NOTA */
        
        Long servId = DelegateUtil.getServicioDelegate().grabarServicio(
    		servicio, 
    		servicio.getServicioResponsable().getId()
		);
        
        return servId;
        
    }
    
   

	/**
     * Devuelve true si ha habido algun cambio en el modulo.
     * 
     * @param modulo
     * @param request
     * @return boolean
     */
    private boolean isModuloModificado(String modulo, HttpServletRequest request) {
        return "1".equals(request.getParameter(modulo));
    }

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
		    String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            TraduccionServicio traduccioOrigen = getTraduccionFromRequest(request, idiomaOrigenTraductor);
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
			
		} catch (TraductorException traEx) {
			
		    log.error("CatalegServeisBackController.traduir: El traductor no puede traducir todos los idiomas");
		    resultats.put("error", messageSource.getMessage("traductor.no_traduible", null, request.getLocale()));
		    
		} catch (NullPointerException npe) {
			
			log.error("CatalegServeisBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("CatalegServeisBackController.traduir: Error en al traducir servicio: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}

		return resultats;
		
	}

	/**
	 * Devuelve la traduccion segun la request.
	 * @param request
	 * @param idiomaOrigenTraductor
	 * @return
	 */
	private TraduccionServicio getTraduccionFromRequest(HttpServletRequest request, String idiomaOrigenTraductor) {

	    final TraduccionServicio traduccioOrigen = new TraduccionServicio();

	    if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_objeto_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setObjeto(request.getParameter("item_objeto_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_destinataris_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setDestinatarios(request.getParameter("item_destinataris_" + idiomaOrigenTraductor));
	    }
	    if (StringUtils.isNotEmpty(request.getParameter("item_requisitos_" + idiomaOrigenTraductor))) {
	        traduccioOrigen.setRequisitos(request.getParameter("item_requisitos_" + idiomaOrigenTraductor));
	    }
        if (StringUtils.isNotEmpty(request.getParameter("item_observacions_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setObservaciones(request.getParameter("item_observacions_" + idiomaOrigenTraductor));
        }
        return traduccioOrigen;
        
	}


	// Actualiza los hechos vitales que se pueden seleccionar en el mantenimiento de un servicio,
	// en función de los públicos objetivo seleccionados.
	@RequestMapping( value = "/listarHechosVitales.do" , method = POST)
	public @ResponseBody Map<String, Object> listarHechosVitales(@RequestParam Set<Long> publicosObjectivosSeleccionados, HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			resultats.put("listadoHechosVitales", LlistatUtil.llistarHechosVitales(publicosObjectivosSeleccionados, DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));

		} catch (DelegateException e) {
			
			logException(log, e);
			
			if (e.isSecurityException()) {
			    resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
			    resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
			
		}

		return resultats;
		
	}
	
	@RequestMapping(value = "/guardarHechosVitales.do", method = POST)
	public @ResponseBody IdNomDTO guardarHechosVitales(Long id, Long[] elementos, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		Servicio servicio = null;
					
		try {
			
			servicio = DelegateUtil.getServicioDelegate().obtenerServicioNewBack(id);
			
			// Borramos los anteriores.
			borrarHechosVitalesServicios(servicio);
			
			if (elementos != null && elementos.length > 0) {
				
	            // Guardamos los nuevos.
	            List<HechoVital> listHV = DelegateUtil.getHechoVitalDelegate().buscarPorIds(elementos);
	            Set<HechoVitalServicio> hvpsAGuardar = new HashSet<HechoVitalServicio>();
	            
	            for (HechoVital hv : listHV) {
	            	
	                HechoVitalServicio hvp = new HechoVitalServicio();
	                hvp.setServicio(servicio);
	                hvp.setHechoVital(hv);
	               
	                int maxOrden = 0;
	                for (HechoVitalServicio hechoVitalServicio : hv.getHechosVitalesServicios()) {
	                    if (hechoVitalServicio != null) {
	                        if (maxOrden < hechoVitalServicio.getOrden()) {
	                            maxOrden = hechoVitalServicio.getOrden();
	                        }
	                    }
	                }
	                
	                maxOrden++;
	                hvp.setOrden(maxOrden);
	                hvpsAGuardar.add(hvp);
	                
	            }

	            servicio.setHechosVitalesServicios(hvpsAGuardar);

			} else {
				
				servicio.setHechosVitalesServicios(new HashSet<HechoVitalServicio>());
				
			}
			
			guardarGrabar(servicio);
			
			result = new IdNomDTO(servicio.getId(), messageSource.getMessage("serv.guardat.fetsVitals.correcte", null, request.getLocale()));
			
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
	
	private void borrarHechosVitalesServicios(Servicio servicio) throws DelegateException {
		
        HechoVitalServicioDelegate hvpDelegate = DelegateUtil.getHechoVitalServicioDelegate();
        List<Long> hvpIds = new LinkedList<Long>();
        
        if (servicio.getHechosVitalesServicios() != null) {
        	
            for (HechoVitalServicio hvp : servicio.getHechosVitalesServicios())
                hvpIds.add(hvp.getId());

            hvpDelegate.borrarHechoVitalServicios(hvpIds);
            
        }
        
        servicio.setHechosVitalesServicios(new HashSet<HechoVitalServicio>());
        
	}
	
	@RequestMapping(value = "/guardarMaterias.do", method = POST)
	public @ResponseBody IdNomDTO guardarMaterias(Long id, Long[] elementos, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		Servicio servicio = null;
					
		try {
			
			servicio = DelegateUtil.getServicioDelegate().obtenerServicioNewBack(id);
			
			Set<Materia> materias = GuardadoAjaxUtil.obtenerMateriasRelacionadas(elementos);
			if (servicio.getValidacion() == 1 && materias.isEmpty()){
				error = messageSource.getMessage("serv.error.falta.materia", null, request.getLocale());
				return result = new IdNomDTO(-6l, error);
				
			}
			servicio.setMaterias(materias); 			
			guardarGrabar(servicio);
			result = new IdNomDTO(servicio.getId(), messageSource.getMessage("serv.guardat.materies.correcte", null, request.getLocale()));

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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarNormativas.do", method = POST)
	public @ResponseBody IdNomDTO guardarNormativas(Long id, Long[] elementos, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		IdNomDTO result;
		String error = null;
		Servicio servicio = null;
		Set<Normativa> normativas = new HashSet<Normativa>();
		
		if (id != null) {
			
			try {
				
				servicio = DelegateUtil.getServicioDelegate().obtenerServicioNewBack(id);
				
				if (elementos != null && elementos.length > 0) {

					List<Long> normativasList = new Vector<Long>();
					NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
					
					for (int i = 0; i < elementos.length; i++)
						normativasList.add(elementos[i]);
					
					
					normativas.addAll(normativaDelegate.buscarNormativas(normativasList));
					
					servicio.setNormativas(normativas); 
					
				} else {
					if (servicio.getValidacion() == 1 && normativas.isEmpty()){
						error = messageSource.getMessage("serv.error.falta.normativa", null, request.getLocale());
						return result = new IdNomDTO(-6l, error);
					}
					
					servicio.setNormativas(new HashSet<Normativa>());
					
				}
				
				guardarGrabar(servicio);
				
				result = new IdNomDTO(servicio.getId(), messageSource.getMessage("serv.guardat.normatives.correcte", null, request.getLocale()));

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
			
		} else {
			
			error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			
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
	            DocumentoServicio docOld = recuperarDocOld(valoresForm);

	            // Copiamos la información deseada al nuevo documento
	            DocumentoServicio doc = recuperarInformacionDocumento(valoresForm, docOld);

	            // Actualizamos las traducciones y marcamos los archivos que deven
	            // ser eliminados
	            List<Long> archivosAborrar = new Vector<Long>();

	            doc = gestionarTraducciones(valoresForm, ficherosForm, archivosAborrar, docOld, doc);

	            // Guardar el documento
	            String iden = "procId";

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
		   				TraduccionDocumentoServicio tradNor = (TraduccionDocumentoServicio) doc.getTraduccion(idioma);
		   				if (tradNor != null && tradNor.getArchivo() != null  && tradNor.getArchivo().getNombre() != null && tradNor.getArchivo().getNombre().length() >= Archivo.NOMBRE_LONGITUD_MAXIMA) {
		   					String error = messageSource.getMessage("error.fitxer.tamany_nom", null, locale);
		   	            	log.error("Error controlado, ha intentado subir un fichero con una longitud en el nombre de más de 128 caracteres.");
		   	            	jsonResult = new IdNomDTO(-3l, error).getJson();
		   	            	continuar = false;
		   				}
		   			}
	            }

	            if (continuar) {
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
	    private DocumentoServicio gestionarTraducciones(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm,
	        List<Long> archivosAborrar, DocumentoServicio docOld, DocumentoServicio doc) throws DelegateException {

	    	TraduccionDocumentoServicio tradDoc;
	        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

	            tradDoc = new TraduccionDocumentoServicio();
	            tradDoc.setTitulo(RolUtil.limpiaCadena(valoresForm.get("doc_titol_" + lang)));
	            tradDoc.setDescripcion(RolUtil.limpiaCadena(valoresForm.get("doc_descripcio_" + lang)));
	            FileItem fileItem = ficherosForm.get("doc_arxiu_" + lang); // Archivo

	            if (fileItem != null && fileItem.getSize() > 0) {
	            	
	                if (!this.isDocumentoNuevo(valoresForm)) {
	                	
	                	TraduccionDocumentoServicio traDocOld = (TraduccionDocumentoServicio)docOld.getTraduccion(lang);
	                    
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
	                TraduccionDocumentoServicio traDocOld = (TraduccionDocumentoServicio)docOld.getTraduccion(lang);
	                archivosAborrar.add(traDocOld.getArchivo().getId());
	                tradDoc.setArchivo(null);

	            } else if (docOld != null) {
	            	
	                // mantener el fichero anterior
	            	TraduccionDocumentoServicio traDocOld = (TraduccionDocumentoServicio)docOld.getTraduccion(lang);
	                if (traDocOld != null) {
	                    tradDoc.setArchivo(traDocOld.getArchivo());
	                }
	                
	            }

	            doc.setTraduccion(lang, tradDoc);
	            
	        }

	        return doc;
	        
	    }
	    
	    
	    @RequestMapping(value = "/guardarDocumentosRelacionados.do", method = POST)
		public @ResponseBody IdNomDTO guardarDocumentosRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
			
			// Guardaremos el orden y borraremos los documentos que se hayan marcado para borrar.
			// La creación se gestiona en el controlador DocumentBackController.
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
			IdNomDTO result;
			String error = null;
			
			try {
				if (elementos == null) {
					elementos = new Long[0];
				}
				DelegateUtil.getServicioDelegate().reordenarDocumentos(id, Arrays.asList(elementos));
				result = new IdNomDTO(id, messageSource.getMessage("serv.guardat.documents.correcte", null, request.getLocale()));
				
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
	        List<Long> archivosBorrar, DocumentoServicio doc) throws DelegateException {

	        String jsonResult = null;
	        Long docId = null;

	        if (valoresForm.get(iden) != null && !"".equals(valoresForm.get(iden))) {

	            DocumentoServicioDelegate docDelegate = DelegateUtil.getDocumentoServicioDelegate();
	            Long id = Long.parseLong(valoresForm.get(iden));
	            docId = docDelegate.grabarDocument(doc, id);
	            

	            for (Long idArchivo : archivosBorrar) {
	                DelegateUtil.getArchivoDelegate().borrarArchivo(idArchivo);
	            }

	            jsonResult = new IdNomDTO(docId, messageSource.getMessage("document.guardat.correcte", null, locale))
	                .getJson();

	        } else {
	            String error = messageSource.getMessage("error.altres", null, locale);
	            jsonResult = new IdNomDTO(-2l, error).getJson();
	            log.error("Error guardant document: No s'ha especificat id de servei.");
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
	    private DocumentoServicio recuperarDocOld(Map<String, String> valoresForm) throws DelegateException {

	        DocumentoServicio docOld = null;

	        if (!this.isDocumentoNuevo(valoresForm)) {
	            Long docId = Long.parseLong(valoresForm.get("docId"));
	            docOld = DelegateUtil.getDocumentoServicioDelegate().obtenerDocumentoServicio(docId);
	        }

	        return docOld;
	    }

	    
	    /** 
	     * Recuperamos la información antigua si el documento ya existia 
	     * @param valoresForm
	     * @param docOld
	     * @return
	     */
	    private DocumentoServicio recuperarInformacionDocumento(Map<String, String> valoresForm, DocumentoServicio docOld) {

	    	DocumentoServicio doc = new DocumentoServicio();

	        if (!this.isDocumentoNuevo(valoresForm)) {
	            doc.setId(docOld.getId());
	            // Este atributo parece que ya no se usa. Se mantiene por si acaso.
	            doc.setArchivo(docOld.getArchivo()); 
	            doc.setOrden(docOld.getOrden());
	            doc.setServicio(docOld.getServicio());
	            //Hay que comentarlo porque sino no se podrán guardar los documentos de servicios.
	            //doc.setTraducciones(docOld.getTraducciones());
	        }

	        return doc;
	    }


	class HechoVitalServicioDTOComparator implements Comparator<Map<String, Object>> {
		
        public int compare(Map<String, Object> hvp1, Map<String, Object> hvp2) {
            
        	Integer orden1 = (Integer) hvp1.get("orden");
            Integer orden2 = (Integer) hvp2.get("orden");
            
            return orden1.compareTo(orden2); 
            
        }
        
    }

	
	/**
     * Llena el campo para la listsa de documentos. 
     * @param listaDocumentos
     * @param id
     * @param idiomas
     * @param ordenable
     * @return
     */
    private List<Map<String, Object>> recuperaDocumentosRelacionados(Set<DocumentoServicio> listaDocumentos, Long id,  List<String> idiomas) {
		
		List<Map<String, Object>> listaDocumentosDTO = new ArrayList<Map<String, Object>>();
		
		for (DocumentoServicio doc : listaDocumentos) {
			
			if (doc != null) {
			    
				// Montar map solo con los campos 'titulo' de las traducciones del documento.
				Map<String, String> titulos = new HashMap<String, String>();
				String nombre;
				TraduccionDocumentoServicio traDoc;

				for (String idioma : idiomas) {
					
					traDoc = (TraduccionDocumentoServicio)doc.getTraduccion(idioma);
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
    private boolean ficheroAdjuntoIsModificado(Map<String, String> valoresForm, TraduccionDocumentoServicio traDocOld) {
        return (traDocOld.getArchivo() != null);
    }
    
    @RequestMapping(value = "/carregarDocument.do")
    public @ResponseBody
    Map<String, Object> carregarDocument(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();

        try {

            Long id = new Long(request.getParameter("id"));
            
            DocumentoServicio doc = DelegateUtil.getDocumentoServicioDelegate().obtenerDocumentoServicio(id); 
            
            Map<String, Object> mapDoc = new HashMap<String, Object>();
            IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
            List<String> idiomas = idiomaDelegate.listarLenguajes();
            TraduccionDocumentoServicio traDoc;

            for (String idioma : idiomas) {
                traDoc = (TraduccionDocumentoServicio) doc.getTraduccion(idioma);

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
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "servicio/archivoServicio.do?id=" + doc.getId() + "&lang=" + idioma);
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
}
