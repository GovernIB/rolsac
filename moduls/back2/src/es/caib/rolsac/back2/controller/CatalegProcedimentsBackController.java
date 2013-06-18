package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionPublicoObjetivo;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.ListadoModuloTramiteDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoNormativaDTO;
import org.ibit.rol.sac.persistence.delegate.CatalegDocumentsDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.ExcepcioDocumentacioDelegate;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.LlistatUtil;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.DateUtils;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/catalegProcediments/")
public class CatalegProcedimentsBackController extends PantallaBaseController {
	
	private final String IDIOMA_ORIGEN_TRADUCTOR = "ca";
	private static final String URL_PREVISUALIZACION = "es.caib.rolsac.previsualitzacio.procediment.url";
	private static Log log = LogFactory.getLog(CatalegProcedimentsBackController.class);

	@RequestMapping(value = "/catalegProcediments.do")
	public String pantallaProcediment(Map<String, Object> model, HttpSession session, HttpServletRequest request) {
		if (estemEnUnitatAdministrativa(session) )  
			crearModelComplert_pantalla(model, session, request);
		else
			crearModelSencill_pantalla(model, session, request);

		//model.put( "idiomes_aplicacio", session.getAttribute("idiomes_aplicacio") );
		
		loadIndexModel (model, request);	
		return "index";
	}

	private boolean estemEnUnitatAdministrativa(HttpSession session) {
		return null != getUAFromSession(session);
	}

	private void crearModelComplert_pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request) {
		crearModelSencill_pantalla(model, session, request);
		model.put("idUA", getUAFromSession(session).getId());
		String lang = getRequestLanguage(request);
		model.put("nomUA", getUAFromSession(session).getNombreUnidadAdministrativa(lang));

	}

	private void crearModelSencill_pantalla(Map<String, Object> model, HttpSession session, HttpServletRequest request) {
		
		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 2);
		model.put("escriptori", "pantalles/catalegProcediments.jsp");
		model.put("urlPrevisualitzacio", System.getProperty(URL_PREVISUALIZACION));
		
		String lang = getRequestLanguage(request);
		
		try {
			
			model.put("llistaMateries", LlistatUtil.llistarMaterias(lang));
			model.put("llistaFetsVitals", LlistatUtil.llistarHechosVitales(lang));
			model.put("llistaPublicsObjectiu", LlistatUtil.llistarPublicObjectius(lang));
			model.put("families", LlistatUtil.llistarFamilias(lang));
			model.put("iniciacions", LlistatUtil.llistarIniciacions(lang));
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

	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistatProcediments(HttpServletRequest request, HttpSession session) {

		List<ProcedimientoLocalDTO> llistaProcedimientoLocalDTO = new ArrayList<ProcedimientoLocalDTO>();

		Map<String, Object> resultats = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> tradMap = new HashMap<String, String>();

		String lang = getRequestLanguage(request);
		
		UnidadAdministrativa ua = null;
		if (getUAFromSession(session) != null) {
			ua = (UnidadAdministrativa) getUAFromSession(session);
		}
		
		boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
		boolean uaMeves = "1".equals(request.getParameter("uaMeves"));		
		
		int resultadosDescartados = 0;
		
		Long materia = null;
		String materiaString = request.getParameter("materia");
		if (materiaString != null) {
			Scanner scanner = new Scanner(materiaString);
	        if (scanner.hasNextLong()) {
	        	materia = scanner.nextLong();
			}
		}
		
        Long fetVital = null;
        String fetVitalString = request.getParameter("fetVital");
        if (fetVitalString != null) {
			Scanner scanner = new Scanner(fetVitalString);
	        if (scanner.hasNextLong()) {
	        	fetVital = scanner.nextLong();
			}
        }
        
        Long publicObjectiu = null;
        String publicObjectiuString = request.getParameter("publicObjectiu");
        
        if (publicObjectiuString != null) {
			Scanner scanner = new Scanner(publicObjectiuString);
	        if (scanner.hasNextLong()) {
	        	publicObjectiu = scanner.nextLong();
			}
        }
        
		String idStr = request.getParameter("codi");
		Long id = -1l;
								
		if ( idStr != null && StringUtils.isNumeric(idStr.trim()) )
			id = ParseUtil.parseLong( idStr.trim() );
		
		paramMap.put("id", idStr != null ? id : null );
		
		Date fechaCaducidad = DateUtils.parseDate(request.getParameter("fechaCaducidad"));
		if (fechaCaducidad != null) {
			paramMap.put("fechaCaducidad", fechaCaducidad);
		}
		
		Date fechaPublicacion = DateUtils.parseDate(request.getParameter("fechaPublicacion"));
		if (fechaPublicacion != null) {
			paramMap.put("fechaPublicacion", fechaPublicacion);
		}
		
		Date fechaActualizacion = DateUtils.parseDate(request.getParameter("fechaActualizacion"));
		if (fechaActualizacion != null) {
			paramMap.put("fechaActualizacion", fechaActualizacion);
		}
		
		String visibilitat = request.getParameter("visibilitat");
		int campoVisible = 0;
		if (visibilitat != null) {
			if (visibilitat.equals("1")) {
				Integer visible = Integer.parseInt(visibilitat);
				paramMap.put("validacion", visible);
				campoVisible = 1;
			} else if (visibilitat.equals("2")) {
				campoVisible = 2;
			}
		}

		String ventanillaUnica = request.getParameter("finestreta");
		if ("1".equals(ventanillaUnica)) {
			paramMap.put("ventanillaUnica", 1);
		}
		
		String taxa = request.getParameter("taxa");
		if ("1".equals(taxa)) {
			paramMap.put("taxa", 1);
		} else if ("0".equals(taxa)) {
			paramMap.put("taxa", 0);
		}
		
		String indicador = request.getParameter("indicador");
		if ("1".equals(indicador)) {
			paramMap.put("indicador", 1);
		} else if ("0".equals(indicador)) {
			paramMap.put("indicador", 0);
		}
		
		try {
			String estat = request.getParameter("estat");
			Integer validacion = Integer.parseInt(estat);
			if (validacion > 0 && validacion < 4) {
				paramMap.put("validacion", validacion);
			}
		} catch (NumberFormatException e) {
		}
		
		String responsable = request.getParameter("responsable");
		if (responsable != null && !"".equals(responsable)) {
			paramMap.put("responsable", responsable.toUpperCase());
		}

		try {
			String familia = request.getParameter("familia");
			Integer familiaId = Integer.parseInt(familia);
			if (familiaId > 0) {
				paramMap.put("familia.id", familiaId);
			}
		} catch (NumberFormatException e) {
		}
		
		try {
			String iniciacion = request.getParameter("iniciacio");
			Integer iniciacionId = Integer.parseInt(iniciacion);
			if (iniciacionId > 0) {
				paramMap.put("iniciacion.id", iniciacionId);
			}
		} catch (NumberFormatException e) {
		}
		
		String tramite = request.getParameter("tramit");
		if (tramite != null && !"".equals(tramite)) {
			paramMap.put("tramite", tramite.toUpperCase());
		}

		
		try {
			String version = request.getParameter("versio");
			Long versionLong = Long.parseLong(version);
			if (versionLong > 0) {
				paramMap.put("version", versionLong);
			}
		} catch (NumberFormatException e) {
		}
		

		String url = request.getParameter("url");
		if (url != null && !"".equals(url)) {
			paramMap.put("url", url.toUpperCase());
		}

		
		if (request.isUserInRole("sacoper")) {
			paramMap.put("validacion", ""); // En el back antiguo estaba asi.
		} else {
			String estat = request.getParameter("estat");
			try {
				Integer validacion = Integer.parseInt(estat);
				paramMap.put("validacion", validacion);
			} catch (NumberFormatException e){
			}
		}

		// Paràmetres ordenació
		String ordreCamp = request.getParameter("ordreCamp");
		if (ordreCamp != null && !"".equals(ordreCamp)) {
			paramMap.put("ordreCamp", ordreCamp);
		}
		String ordreTipus = request.getParameter("ordreTipus");
		if (ordreTipus != null && !"".equals(ordreTipus)) {
			paramMap.put("ordreTipus", ordreTipus);
		}
		
		// Textes (en todos los campos todos los idiomas)
		String textes = request.getParameter("textes");
		if (textes != null && !"".equals(textes)) {
			textes = textes.toUpperCase();
			if (tradMap.get("nombre") == null) {
				tradMap.put("nombre", textes);
			}
			tradMap.put("resumen", textes);
			tradMap.put("destinatarios", textes);
			tradMap.put("requisitos", textes);
			tradMap.put("plazos", textes);
			tradMap.put("resultat", textes);
			tradMap.put("resolucion", textes);
			tradMap.put("notificacion", textes);
			tradMap.put("silencio", textes);
			// tradMap.put("recursos", textes);
			tradMap.put("observaciones", textes);
			tradMap.put("lugar", textes);
		} else {
			tradMap.put("idioma", lang);
		}

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		try {
			
			ProcedimientoDelegate procedimientosDelegate = DelegateUtil.getProcedimientoDelegate();
			
			resultadoBusqueda = procedimientosDelegate.buscadorProcedimientos(paramMap, tradMap, ua, uaFilles, uaMeves, materia, fetVital, publicObjectiu, pagPag, pagRes, campoVisible);
			String idiomaPorDefecto = request.getLocale().getLanguage();

			for ( ProcedimientoLocal pl : castList(ProcedimientoLocal.class, resultadoBusqueda.getListaResultados() ) ) {
				
				if ( idiomaPorDefecto.equals(pl.getIdioma()) ) {
					ProcedimientoLocalDTO procLocalDTO = new ProcedimientoLocalDTO(
							pl.getId(), pl.getNombreProcedimiento(),
							pl.isVisible(), DateUtils.formatDate(pl.getFechaActualizacion()),
							pl.getNombreFamilia());				
					
					llistaProcedimientoLocalDTO.add( procLocalDTO );
				} else {
					resultadosDescartados++;
				}
			}
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				// model.put("error", "permisos");
			} else {
				// model.put("error", "altres");
				logException(log, dEx);
			}
			
		}
 		
		//Total de registros
		resultats.put("total", resultadoBusqueda.getTotalResultados() - resultadosDescartados);
		resultats.put("nodes", llistaProcedimientoLocalDTO);
		
		return resultats;
	}
	
	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpSession session, HttpServletRequest request) {
		Map<String, Object> resultats = new HashMap<String, Object>();
		String lang = getRequestLanguage(request);
		try {
			Long id = new Long(request.getParameter("id"));

			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal proc = procedimientoDelegate.obtenerProcedimiento(id);

			resultats.put("item_id", proc.getId());
            resultats.put("item_codigo_pro", proc.getSignatura());			
			resultats.put("item_estat", proc.getValidacion());						
			resultats.put("item_data_actualitzacio", DateUtils.formatDate(proc.getFechaActualizacion()));
			resultats.put("item_data_publicacio", DateUtils.formatDate(proc.getFechaPublicacion()));
			resultats.put("item_data_caducitat", DateUtils.formatDate(proc.getFechaCaducidad()));

			// TODO: Implementar getPublico()
			// resultats.put("item_public_objectiu", DateUtils.formatDate(proc.getPublico()));
			
			// Idiomas
			//Cambiar por el valor que estará en la sesión
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			
			if (proc.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionProcedimientoLocal) proc.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionProcedimientoLocal());
			}

			if (proc.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionProcedimientoLocal) proc.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionProcedimientoLocal());
			}

			if (proc.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionProcedimientoLocal) proc.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionProcedimientoLocal());
			}

			if (proc.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionProcedimientoLocal) proc.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionProcedimientoLocal());
			}

			if (proc.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionProcedimientoLocal) proc.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionProcedimientoLocal());
			}
			// Fin idiomas
			
			// Documentos relacionados
			if (proc.getDocumentos() != null) {
				Map<String, Object> mapDoc;
				List<Map<String, Object>> llistaDocuments = new ArrayList<Map<String, Object>>();
			
				for(Documento doc: proc.getDocumentos()) {
					if (doc != null) {
						// Montar map solo con los campos 'titulo' de las traducciones del documento.
						Map<String, String> titulos = new HashMap<String, String>();
						//IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
						List<String> idiomas = idiomaDelegate.listarLenguajes();
						String nombre;
						TraduccionDocumento traDoc;
						
						for (String idioma: idiomas) {
							traDoc = (TraduccionDocumento) doc.getTraduccion(idioma);
							if (traDoc != null && traDoc.getTitulo() != null) {
								nombre = traDoc.getTitulo();
							} else {
								nombre = "";
							}
							titulos.put(idioma, nombre);
						}
		
						mapDoc = new HashMap<String, Object>(3);
						mapDoc.put("id", doc.getId());
						mapDoc.put("orden", doc.getOrden());
						mapDoc.put("nombre", titulos);
						
		                llistaDocuments.add(mapDoc);
					} else {
						log.error("El procedimient " + proc.getId() + " te un document null.");
					}
	            }
				
				resultats.put("documents", llistaDocuments);
			} else {
	            resultats.put("documents", null);
	        } 
            // Fin documentos relacionados
           			
			// Tr�mites relacionados
			List<ListadoModuloTramiteDTO> listaTramitesDTO = null;
			
			if ( proc.getTramites() != null ) {
				
				listaTramitesDTO = new ArrayList<ListadoModuloTramiteDTO>();
				
				for( Tramite tramite : proc.getTramites() ) {	
					if (tramite != null) {
						String nombreTramite = ((TraduccionTramite) tramite.getTraduccion( request.getLocale().getLanguage())).getNombre();
						listaTramitesDTO.add( new ListadoModuloTramiteDTO( tramite.getId(), nombreTramite, tramite.getFase() ) );
					}
				}								
			} 	
			
			resultats.put("tramites", listaTramitesDTO);
			//Fin tr�mites relacionados
			
			// Materias asociadas
            if (proc.getMaterias() != null) {             
                List<IdNomDTO> llistaMateriesDTO = new ArrayList<IdNomDTO>();
                for(Materia materia : proc.getMaterias()){                
                    llistaMateriesDTO.add(new IdNomDTO(materia.getId(), materia.getNombreMateria(lang)));                
                }
                resultats.put("materies", llistaMateriesDTO);
            } else {
                resultats.put("materies", null);
            } 
            // Fin Materias asociadas
            
			// Publics Objectiu associats
            if (proc.getPublicosObjetivo() != null) {             
                List<IdNomDTO> llistaPublicsDTO = new ArrayList<IdNomDTO>();
                for(PublicoObjetivo pob : proc.getPublicosObjetivo()){
                	TraduccionPublicoObjetivo tpob = (TraduccionPublicoObjetivo) pob.getTraduccion(lang);
                	llistaPublicsDTO.add(new IdNomDTO(pob.getId(), tpob == null ? "" : tpob.getTitulo() ));                
                }
                resultats.put("publicsObjectiu", llistaPublicsDTO);
            } else {
                resultats.put("publicsObjectiu", null);
            }      
            // Fi Publics Objectiu asociats
            
			// Hechos vitales asociados
            List<Map<String, Object>> llistaFetsDTO = new ArrayList<Map<String, Object>>();
            for(HechoVitalProcedimiento hechoVitalProc : proc.getHechosVitalesProcedimientos()) {
            	TraduccionHechoVital thv = (TraduccionHechoVital) hechoVitalProc.getHechoVital().getTraduccion(lang);
            	Map<String, Object> hvpDTO = new HashMap<String, Object>();
            	hvpDTO.put("id", hechoVitalProc.getHechoVital().getId());
            	hvpDTO.put("nom", thv.getNombre());
            	hvpDTO.put("orden", hechoVitalProc.getOrden());
                llistaFetsDTO.add(hvpDTO);                
            }
            Collections.sort(llistaFetsDTO, new HechoVitalProcedimientoDTOComparator());
            resultats.put("fetsVitals", llistaFetsDTO);
            // Fin Hechos vitales asociados
            
            // Normativas asociadas
            if (proc.getNormativas() != null) {             
            	Map<String, String> map;
            	List<Map<String, String>> llistaNormatives = new ArrayList<Map<String, String>>();
                TraduccionNormativa traNor;
				String titulo;
                
				for(Normativa normativa: proc.getNormativas()) {
                	traNor = (TraduccionNormativa) normativa.getTraduccion(lang);
    				titulo = "";
    				if (traNor != null) {
    					//Retirar posible enlace incrustado en titulo
    					titulo = HtmlUtils.obtenerTituloDeEnlaceHtml(traNor.getTitulo());
    				}
    				map = new HashMap<String, String>(2);
    				map.put("id", normativa.getId().toString());
    				map.put("nombre", titulo);
                    llistaNormatives.add(map);      
                }

				resultats.put("normatives", llistaNormatives);
            } else {
                resultats.put("normatives", null);
            } 
            // Fin normativas asociadas
            
			resultats.put("item_codi", proc.getSignatura());
            // Fin normativas asociadas            

			if (proc.getIniciacion() != null) {
				Iniciacion iniciacion = proc.getIniciacion();
				resultats.put("item_iniciacio", iniciacion.getId());
			}

			if (proc.getUnidadAdministrativa() != null) {
			    UnidadAdministrativa ua = proc.getUnidadAdministrativa();
                resultats.put("item_organ_responsable_id", ua.getId());
                resultats.put("item_organ_responsable_nom", ua.getNombreUnidadAdministrativa(lang));
			}
			
			if (proc.getOrganResolutori() != null) {
			    UnidadAdministrativa ua = proc.getOrganResolutori();
				resultats.put("item_organ_id", ua.getId());
				resultats.put("item_organ_nom", ua.getNombreUnidadAdministrativa(lang));
			}

			if (proc.getFamilia() != null) {
				Familia familia = proc.getFamilia();
				resultats.put("item_familia", familia.getId());
			}

			resultats.put("item_tramite", proc.getTramite());
			
			if (proc.getVersion() != null) {
				resultats.put("item_version", proc.getVersion());
			}
			
			resultats.put("item_url", proc.getUrl());
			
			resultats.put("item_responsable", proc.getResponsable());

			if (proc.getIndicador() == null || "0".equals(proc.getIndicador())) {
				resultats.put("item_fi_vida_administrativa", false);
			} else {
				resultats.put("item_fi_vida_administrativa", true);
			}

			resultats.put("item_finestreta_unica", proc.esVentanillaUnica());

			if (proc.getTaxa() == null || "0".equals(proc.getTaxa())) {
				resultats.put("item_taxa", false);
			} else {
				resultats.put("item_taxa", true);
			}
			
			if (proc.getVentanillaUnica() == null || "0".equals(proc.getVentanillaUnica())) {
				resultats.put("item_finestreta_unica", false);
			} else {
				resultats.put("item_finestreta_unica", true);
			}

			resultats.put("item_notes", proc.getInfo());
			
		} catch (DelegateException dEx) {
			logException(log, dEx);
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
		}
		
		return resultats;
	}
	

	@RequestMapping(value = "/esborrarProcediment.do", method = POST)
	public @ResponseBody IdNomDTO esborrarProcediment(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();

		try {
			Long id = new Long(request.getParameter("id"));
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			procedimientoDelegate.borrarProcedimiento(id);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}
		}

		return resultatStatus;
	}

	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardarProcediment(HttpSession session, HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			
//			UnidadAdministrativa ua = (UnidadAdministrativa) getUAFromSession(session);
//			if (ua == null) {
//				error = messageSource.getMessage("proc.error.falta.ua", null, request.getLocale());
//				result = new IdNomDTO(-3l, error);
//			} else {
			
			if ( request.getParameter("publicsObjectiu") == null || request.getParameter("publicsObjectiu").equals("") ) {
				
				error = messageSource.getMessage("proc.error.falta.public", null, request.getLocale());
				result = new IdNomDTO(-3l, error);
			
			}
			
			else {
				
				ProcedimientoDelegate procedimentDelegate = DelegateUtil.getProcedimientoDelegate();
				ProcedimientoLocal procediment = new ProcedimientoLocal();
				ProcedimientoLocal procedimentOld;			
	
				boolean edicion;
				try {
					Long id = Long.parseLong(request.getParameter("item_id"));
					procedimentOld = procedimentDelegate.obtenerProcedimiento(id);
					edicion = true;
				} catch (NumberFormatException nfe) {
					procedimentOld = null;
					edicion = false;
				}
		
				if (edicion) {
					// Mantenemos los valores originales que tiene el procedimiento.
					// procediment.setUnidadAdministrativa(procedimentOld.getUnidadAdministrativa());
					procediment.setId(procedimentOld.getId());
					procediment.setHechosVitalesProcedimientos(procedimentOld.getHechosVitalesProcedimientos());
					procediment.setTramites(procedimentOld.getTramites());					
					procediment.setOrganResolutori(procedimentOld.getOrganResolutori());
					procediment.setMaterias(procedimentOld.getMaterias());
					procediment.setPublicosObjetivo(procedimentOld.getPublicosObjetivo());
					procediment.setNormativas(procedimentOld.getNormativas());
					// } else {
					// // A los nuevos procedimientos se les asigna la UA de la miga de pan.
					// procediment.setUnidadAdministrativa(ua);
				}

	            // Materias
	            /* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
	             * En el primer caso es bastante probable que se repitan la mayoria de materias.
	             */
				if ( isModuloModificado("modulo_materias_modificado", request) ) {
					
	                if ( request.getParameter("materies") != null && !"".equals(request.getParameter("materies")) ) {
	                	
	                    MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
	                    Set<Materia> materiesNoves = new HashSet<Materia>();
	                    String[] codisMateriaNous = request.getParameter("materies").split(",");
	                    
	                    if (edicion) {
	                        for (int i = 0; i < codisMateriaNous.length; i++) {
	                            for (Materia materia : procedimentOld.getMaterias()) {
	                                if (materia.getId().equals(Long.valueOf(codisMateriaNous[i]))) { //materia ya existente
	                                    materiesNoves.add(materia);
	                                    codisMateriaNous[i] = null;
	                                    break;
	                                }
	                            }                            
	                        }                         
	                    }                    
	                    
	                    for (String codiMateria: codisMateriaNous) {
	                        if (codiMateria != null) {
	                            materiesNoves.add(materiaDelegate.obtenerMateria(Long.valueOf(codiMateria)));
	                        }                        
	                    }
	                    
	                    procediment.setMaterias(materiesNoves);
	                    
	                } else {
	                	
	                    procediment.setMaterias(new HashSet<Materia>());
	                    
	                }
	                
	            }
	            // Fin Materias
			
	            // Public Objectiu
	            /* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
	             * En el primer caso es bastante probable que se repitan la mayoria de public objectiu.
	             */
	            if ( isModuloModificado("modul_public_modificat", request) ) {
	            	
	            	if ( request.getParameter("publicsObjectiu") != null && !"".equals(request.getParameter("publicsObjectiu")) ) {
	            		
		            	PublicoObjetivoDelegate publicObjDelegate = DelegateUtil.getPublicoObjetivoDelegate();
		                Set<PublicoObjetivo> publicsNous = new HashSet<PublicoObjetivo>();
		                String[] codisPublicsNous = request.getParameter("publicsObjectiu").split(",");
		                
		                if (edicion) {
		                    for (int i = 0; i < codisPublicsNous.length; i++) {
		                        for (PublicoObjetivo pob : procedimentOld.getPublicosObjetivo()) {
		                            if (pob.getId().equals(Long.valueOf(codisPublicsNous[i]))) { // Público objetivo ya existente
		                            	publicsNous.add(pob);
		                            	codisPublicsNous[i] = null;
		                                break;
		                            }
		                        }                            
		                    }                         
		                }                    
		                
		                for (String codiPob : codisPublicsNous) {
		                    if (codiPob != null){
		                    	publicsNous.add(publicObjDelegate.obtenerPublicoObjetivo(Long.valueOf(codiPob)));
		                    }                        
		                }
		                
		                procediment.setPublicosObjetivo(publicsNous);
		                
		            } else {
		            	
		            	procediment.setPublicosObjetivo(new HashSet<PublicoObjetivo>());
		            	
		            }
	            	
	            }
	            // Fin Public Objectiu
            
	            // Actualizar la lista de Trámites
	            String tramitsProcediment = request.getParameter("tramitsProcediment");
	            TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
	            
	            if ( !"".equals(tramitsProcediment) && edicion ) {
	            	
	            	List<Long> listaTramitesBorrar = new ArrayList<Long>();
	            	List<Tramite> tramitesNuevos = new ArrayList<Tramite>();                	
	            	String[] codigosTramitesNuevos = tramitsProcediment.split(",");
	
	            	List<Tramite> listaTramitesOld = procedimentOld.getTramites();
	
	        		for (int i = 0; i < codigosTramitesNuevos.length; i++) {
	        			
	        			for ( Tramite tramite : listaTramitesOld ) {  
	        				                				
	        				if ( !"".equals(codigosTramitesNuevos[i]) && tramite != null && tramite.getId().equals(Long.valueOf(codigosTramitesNuevos[i])) ) {
	        					tramitesNuevos.add(tramite);
	        					codigosTramitesNuevos[i] = null;
	        					
	        					break;
	        				}
	        				
	        			}
	        			
	        		}
	        		                    	
	            	//Eliminar los que se han quitado de la lista
	            	for ( Tramite tramite : listaTramitesOld ) {                		
	        			if (!tramitesNuevos.contains(tramite) && tramite != null)                    				
	        				listaTramitesBorrar.add(tramite.getId());
	            	}
	
	            	for (Long id : listaTramitesBorrar ) {
	            		//procediment.removeTramite( tramiteDelegate.obtenerTramite(id) );
	            		DelegateUtil.getProcedimientoDelegate().eliminarTramite(id, procediment.getId());
	            		tramiteDelegate.borrarTramite(id);
	            	}
	            	
	            	//Crear los nuevos
	            	if (!"".equals(codigosTramitesNuevos)) {
	                	for (String codigoTramite : codigosTramitesNuevos) {
	                		if ( codigoTramite != null ) {
	                			for ( Tramite tramite : procedimentOld.getTramites() ) {
	                				if ( !tramitesNuevos.contains(tramite) ) 
	                					tramitesNuevos.add(tramite);                					
	                			}
	                		} 
	                	}
	            	}                	                   	
	            	
	            	// Actualizamos el orden de la lista de trámites
	            	HashMap<String, String[]> actualizadorTramites = new HashMap<String, String[]>();
	            	
	            	for (Tramite tramite : tramitesNuevos ) {
	            		String[] orden = { request.getParameter("tramit_orden_" + tramite.getId()) };
	            		actualizadorTramites.put("orden" + tramite.getId(), orden );
	            	}
	            	
	            	DelegateUtil.getProcedimientoDelegate().actualizarOrdenTramites(actualizadorTramites);                	
	            	procediment.setTramites(tramitesNuevos);
	            	
	            } else if (edicion) {
	            	                	
	            	for (Tramite tramite : procediment.getTramites() ) {
	            		procedimentDelegate.eliminarTramite(tramite.getId(), procediment.getId());
	            		tramiteDelegate.borrarTramite(tramite.getId());
	            	}
	            
	            	procediment.setTramites(null);
	            	
	            }               
	            //Fin trámites
            
	            // Hechos vitales
	            if ( request.getParameter("fetsVitals") != null && edicion && isModuloModificado("modulo_hechos_modificado", request) ) {
	            	
	            	String[] codisFetsVitals = request.getParameter("fetsVitals").split(",");
	            	HechoVitalDelegate hvDelegate = DelegateUtil.getHechoVitalDelegate();
	            	HechoVitalProcedimientoDelegate hvpDelegate = DelegateUtil.getHechoVitalProcedimientoDelegate();
	            	
	            	// Eliminamos los hecho vital procedimiento existentes
	            	List<Long> hvpIds = new LinkedList<Long>();
	            	if (procediment.getHechosVitalesProcedimientos() != null) {
	                	for (HechoVitalProcedimiento hvp: procediment.getHechosVitalesProcedimientos()) {
	                		hvpIds.add(hvp.getId());
	                	}
	                	hvpDelegate.borrarHechoVitalProcedimientos(hvpIds);
	            	}
	            	procediment.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());
	            	
	            	// Guardamos los nuevos
	            	Set<HechoVitalProcedimiento> hvpsAGuardar = new HashSet<HechoVitalProcedimiento>();
	            	for (int i = 0; i < codisFetsVitals.length; i++) {
	                	Long hvId = ParseUtil.parseLong(codisFetsVitals[i]);
	                	if (hvId != null) {
	                		HechoVitalProcedimiento hvp = null;
	                		HechoVital hv = hvDelegate.obtenerHechoVital(hvId);
	                		hvp = new HechoVitalProcedimiento();
	                		hvp.setProcedimiento(procediment);
	                		hvp.setHechoVital(hv);
	                		int maxOrden = 0;
	                		for (HechoVitalProcedimiento hechoVitalProcedimiento: (List<HechoVitalProcedimiento>) hv.getHechosVitalesProcedimientos()) {
	                			if (hechoVitalProcedimiento != null) {
	                				if (maxOrden < hechoVitalProcedimiento.getOrden())
	                					maxOrden = hechoVitalProcedimiento.getOrden();
	                			}
	                		}
	                		maxOrden++;
	                		hvp.setOrden(maxOrden);
	                		
	                		hvpsAGuardar.add(hvp);
	                	}
	                }
	                hvpDelegate.grabarHechoVitalProcedimientos(hvpsAGuardar);
	                procediment.setHechosVitalesProcedimientos(hvpsAGuardar); 
	                
	            }
	            // Fin Hechos vitales
            
	            // Normativas
	            /* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
	             * En el primer caso es bastante probable que se repitan la mayoria de normativas.
	             */
	            if ( isModuloModificado("modulo_normativas_modificado",request) ) {
	            	
					if ( request.getParameter("normatives") != null && !"".equals(request.getParameter("normatives")) ) {
					  
						NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
						Set<Normativa> normativesNoves = new HashSet<Normativa>();
						String[] codisNormativesNoves= request.getParameter("normatives").split(",");
					  
					if (edicion) {
					    for (int i = 0; i < codisNormativesNoves.length; i++) {
					        for (Normativa normativa: procedimentOld.getNormativas()) {
					            if (normativa.getId().equals(Long.valueOf(codisNormativesNoves[i]))) { // normativa ya existente
									normativesNoves.add(normativa);
									codisNormativesNoves[i] = null;
									break;
					            }
					        }                            
					    }                         
					}
					  
					for (String codiNormativa: codisNormativesNoves) {
					    if (codiNormativa != null) {
					    	normativesNoves.add(normativaDelegate.obtenerNormativa(Long.valueOf(codiNormativa)));
					    }                        
					}
					  
					procediment.setNormativas(normativesNoves); 
	                  
		            } else {
		            	  
		                procediment.setNormativas(new HashSet<Normativa>());
		                
		            }
              
	            }
	            // Fin normativas
            
	            // Documents
	            Enumeration<String> nomsParametres = request.getParameterNames();
	            Documento document;
	            DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
	            List<Documento> documents = new ArrayList<Documento>();
	            Map <String,String[]> actulitzadorMap = new HashMap<String, String[]>();
	
	            // obtenim  els documents i els seus ordres
	            while ( nomsParametres.hasMoreElements() ) {
	            	
	            	String nomParameter = (String)nomsParametres.nextElement();                    
	                String[] elements = nomParameter.split("_");
	                
	                if ( "documents".equals(elements[0]) && "id".equals(elements[1]) ) {
	                	
	                    // En aquest cas, elements[2] es igual al id del document
	                	Long id = ParseUtil.parseLong(request.getParameter(nomParameter));
	                	if (id != null) {
	                    	document = docDelegate.obtenerDocumento(id);
	                    	documents.add(document);
	                    	
	                    	// Se coge el orden de la web. Si se quisiesen poner del 0 al x, hacer que orden valga 0 e ir incrementandolo.
	                    	String[] orden = {request.getParameter("documents_orden_" + elements[2])};
	                    	actulitzadorMap.put("orden_doc" + id, orden);
	                	} else {
	                		log.warn("S'ha rebut un id de document no n�meric: " + id);
	                	}
	                	
	                }
	                
	            }
            
	            // actualitzam ordres
	            docDelegate.actualizarOrdenDocs(actulitzadorMap);
            
	            // assignar els documents al procedimient i eliminar els que ja no estiguin seleccionats.
	            procediment.setDocumentos(documents);
	            if (edicion) {
	            	
	                List<Documento> docsOld = procedimentOld.getDocumentos();                                    
	                
	                for(Documento doc : documents){
	                    for (Iterator<Documento> it = docsOld.iterator(); it.hasNext(); ){
	                    	Documento currentDoc = it.next();
	                        if (currentDoc != null && currentDoc.getId().equals(doc.getId())){
	                            it.remove();
	                        }
	                    }
	                }                    
	                
	                for (Documento doc: docsOld){
	                	if (doc != null) docDelegate.borrarDocumento(doc.getId());
	                }
	                
	            } 
	            // Fi documents
			
				// Idiomas
				TraduccionProcedimientoLocal tpl;
				IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
				List<String> langs = idiomaDelegate.listarLenguajes();
	
				for (String lang : langs) {
					
					if (edicion) {
						
						tpl = (TraduccionProcedimientoLocal)procedimentOld.getTraduccion(lang);
						if (tpl == null) {
							tpl = new TraduccionProcedimientoLocal();
						}
						
					} else {
						
						tpl = new TraduccionProcedimientoLocal();
						
					}
	
					tpl.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)) );
					tpl.setDestinatarios( RolUtil.limpiaCadena(request.getParameter("item_destinataris_" + lang)) );
					tpl.setResumen( RolUtil.limpiaCadena(request.getParameter("item_objecte_" + lang)) );
					tpl.setResultat( RolUtil.limpiaCadena(request.getParameter("item_resultat_" + lang)) );
					tpl.setResolucion( RolUtil.limpiaCadena(request.getParameter("item_resolucio_" + lang)) );
					tpl.setNotificacion( RolUtil.limpiaCadena(request.getParameter("item_notificacio_" + lang)) );
					tpl.setSilencio( RolUtil.limpiaCadena(request.getParameter("item_silenci_" + lang)) );
					tpl.setObservaciones( RolUtil.limpiaCadena(request.getParameter("item_observacions_" + lang)) );
					tpl.setPlazos( RolUtil.limpiaCadena(request.getParameter("item_presentacio_" + lang)) );
					tpl.setLugar( RolUtil.limpiaCadena(request.getParameter("item_lloc_" + lang)) );
					
					procediment.setTraduccion(lang, tpl);
					
				}
				// Fin idiomas
			
				try {
					
					Integer validacion = Integer.parseInt(request.getParameter("item_estat"));
					// Comprobar que no se haya cambiado la validacion/estado siendo operador
	            	if (request.isUserInRole("sacoper") && procedimentOld != null && !procedimentOld.getValidacion().equals(validacion)) {
	            		throw new DelegateException(new SecurityException());
	            	}
					procediment.setValidacion(validacion);
					
				} catch (NumberFormatException e) {
					
				    error = messageSource.getMessage("proc.error.estat.incorrecte", null, request.getLocale());
					throw new NumberFormatException();
					
				}

				procediment.setSignatura(request.getParameter("item_codigo_pro"));
				
				if (!StringUtils.isEmpty(request.getParameter("item_data_publicacio"))) {
					Date data_publicacio = DateUtils.parseDate(request.getParameter("item_data_publicacio"));
					
					if (data_publicacio == null) 
						throw new ParseException("error.data_publicacio", 0);
					
					procediment.setFechaPublicacion(data_publicacio);
				}
				
				if (!StringUtils.isEmpty(request.getParameter("item_data_caducitat"))) {
					Date data_caducitat = DateUtils.parseDate(request.getParameter("item_data_caducitat"));
					
					if (data_caducitat == null) 
						throw new ParseException("error.data_caducitat", 0);
					
					procediment.setFechaCaducidad(data_caducitat);
				}
				
				// procediment.setFechaActualizacion(new Date()); // lo hace el facade automaticamente.
			
				try {
					
					Long iniciacionId = Long.parseLong(request.getParameter("item_iniciacio"));
					IniciacionDelegate iDelegate = DelegateUtil.getIniciacionDelegate();
					Iniciacion iniciacion = iDelegate.obtenerIniciacion(iniciacionId);
					procediment.setIniciacion(iniciacion);
					
				} catch (NumberFormatException e) {
					
					error = messageSource.getMessage("proc.error.formaIniciacio.incorrecta", null, request.getLocale());
					throw new NumberFormatException();
					
				}
								
				try {
					
					Long familiaId = Long.parseLong(request.getParameter("item_familia"));
					FamiliaDelegate fDelegate = DelegateUtil.getFamiliaDelegate();
					Familia familia = fDelegate.obtenerFamilia(familiaId);
					procediment.setFamilia(familia);
					
				} catch (NumberFormatException e) {
					
					error = messageSource.getMessage("proc.error.familia.incorrecte", null, request.getLocale());
					throw new NumberFormatException();
					
				}
			
				procediment.setResponsable(request.getParameter("item_responsable"));
				/* Provisional, hasta que este activada la SEU */
				//procediment.setTramite(request.getParameter("item_tramite"));
				if (procedimentOld != null)
					procediment.setTramite(procedimentOld.getTramite());
				//procediment.setUrl(request.getParameter("item_url"));
				if (procedimentOld != null)
					procediment.setUrl(procedimentOld.getUrl());
				/*---------------------------------------------*/
				
				// TODO: Implementar setPublico()
				// TODO: ¿Seguro? Ya existe el método public void setPublicosObjetivo(Set<PublicoObjetivo> publicosObjetivo)
				// procediment.setPublico(request.getParameter("item_public_objectiu"));
				
				try {
					
					String versionStr = request.getParameter("item_version");
					if (versionStr != null && !"".equals(versionStr)) {
						Long version = Long.parseLong(versionStr);
						procediment.setVersion(version);
					} else { /* Provisional, hasta que este activada la SEU */
						if (procedimentOld != null)
							procediment.setVersion(procedimentOld.getVersion());
					}
					
				} catch (NumberFormatException e) {
					
				    error = messageSource.getMessage("proc.error.versio.incorrecte", null, request.getLocale());
					throw new NumberFormatException();
					
				}
			
				if (!"".equals(request.getParameter("item_organ_id"))) {
					
	                try {
	                	
	                    Long organId = Long.parseLong(request.getParameter("item_organ_id"));
	                    UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
	                    UnidadAdministrativa organ = uaDelegate.obtenerUnidadAdministrativa(organId);
	                    procediment.setOrganResolutori(organ);
	                    
	                } catch (NumberFormatException e) {
	                	
	                    error = messageSource.getMessage("proc.error.organ.incorrecte", null, request.getLocale());
	                    throw new NumberFormatException();
	                    
	                }
	                
	            }
				
	            try {
	            	
	                Long organRespID = Long.parseLong(request.getParameter("item_organ_responsable_id"));
	                UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
	                UnidadAdministrativa organResp = uaDelegate.obtenerUnidadAdministrativa(organRespID);
	                procediment.setUnidadAdministrativa(organResp);
	                
	            } catch (NumberFormatException e) {
	            	
	                error = messageSource.getMessage("proc.error.organ.responsable.incorrecte", null, request.getLocale());
	                throw new NumberFormatException();
	                
	            }
			
				procediment.setTaxa("on".equalsIgnoreCase(request.getParameter("item_taxa")) ? "1" : "0");
				procediment.setIndicador("on".equalsIgnoreCase(request.getParameter("item_fi_vida_administrativa")) ? "1" : "0");
				procediment.setVentanillaUnica("on".equalsIgnoreCase(request.getParameter("item_finestreta_unica")) ? "1" : "0");
				procediment.setInfo(request.getParameter("item_notes"));
	
				Long procId = procedimentDelegate.grabarProcedimiento(procediment, procediment.getUnidadAdministrativa().getId());
				
				String ok = messageSource.getMessage("proc.guardat.correcte", null, request.getLocale());
				result = new IdNomDTO(procId, ok);
				
			} // end else
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				logException(log, dEx);
			}
			
		} catch (NumberFormatException nfe) {
			
			result = new IdNomDTO(-3l, error);	
			
		} catch (ParseException pe) {
			
			error = messageSource.getMessage(pe.getMessage(), null, request.getLocale());
			result = new IdNomDTO(-4l, error);
			
		}

		return result;
		
	}
	
	@RequestMapping(value = "/cercarNormatives.do", method = POST)
	public @ResponseBody Map<String, Object> llistatNormatives(HttpServletRequest request, HttpSession session)  {
		
		//Listar las normativas de la unidad administrativa
		//List<Normativa>llistaNormatives = new ArrayList<Normativa>();
		List<ProcedimientoNormativaDTO>llistaNormativesDTO= new ArrayList<ProcedimientoNormativaDTO>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		Map<String, String> paramTrad = new HashMap<String, String>();
		
		// TODO obtener la ordenaci�n por par�metro
		//String campoOrdenacion = "normativa.fecha";
		String campoOrdenacion = "fecha";
		String orden = "desc";		
				
		String idioma = getRequestLanguage(request);
		
		if (getUAFromSession(session) == null){
			return resultats;//Si no hay unidad administrativa se devuelve vac�o
		}
		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		
		try {
			//Obtener par�metros de b�squeda
		
			if (request.getParameter("data") != null && !request.getParameter("data").equals("")) {
				Date fecha = DateUtils.parseDate(request.getParameter("data"));
				paramMap.put("fecha", fecha);
			}			

			if (request.getParameter("dataButlleti") != null && !request.getParameter("dataButlleti").equals("")) {
				Date fechaBoletin = DateUtils.parseDate(request.getParameter("dataButlleti"));
				paramMap.put("fechaBoletin", fechaBoletin);
			}
			
			// Titol (en todos los idiomas)
			String text = request.getParameter("titol");
			if (text != null && !"".equals(text)) {
				text = text.toUpperCase();
				paramTrad.put("titulo", text);
			} else {
				paramTrad.put("idioma", idioma);
			}			
			
			//Información de paginación
			String pagPag = request.getParameter("pagPag");		
			String pagRes = request.getParameter("pagRes");
			
			if (pagPag == null) pagPag = String.valueOf(0); 
			if (pagRes == null) pagRes = String.valueOf(10);                						
			
			resultadoBusqueda = new ResultadoBusqueda();
			
			//Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			
			//La búsqueda de normativas no tendrá en cuenta la UA actual (idua = null)
			resultadoBusqueda = normativaDelegate.buscarNormativas(paramMap,
					paramTrad, "local", null, false, false, campoOrdenacion,
					orden, pagPag, pagRes);		
			
			for ( Normativa normativa : castList(Normativa.class, resultadoBusqueda.getListaResultados()) ) {
				llistaNormativesDTO.add(new ProcedimientoNormativaDTO(normativa
						.getId(), HtmlUtils.obtenerTituloDeEnlaceHtml(normativa.getTraduccionTitulo()), DateUtils
						.formatDate(normativa.getFecha()), DateUtils
						.formatDate(normativa.getFechaBoletin())));
			}

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				//model.put("error", "permisos");
			} else {
				//model.put("error", "altres");
				logException(log, dEx);
			}
		}
		
		resultats.put("total", resultadoBusqueda.getTotalResultados() );
		resultats.put("nodes", llistaNormativesDTO);
		
		return resultats;
	}

	
	class HechoVitalProcedimientoDTOComparator implements Comparator<Map<String, Object>> {
		public int compare(Map<String, Object> hvp1, Map<String, Object> hvp2) {
			Integer orden1 = (Integer) hvp1.get("orden");
			Integer orden2 = (Integer) hvp2.get("orden");
			return orden1.compareTo(orden2); 
		}
	}
	

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
		
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			
			TraduccionProcedimientoLocal traduccioOrigen = new TraduccionProcedimientoLocal();
			
			if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setNombre(request.getParameter("item_nom_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_presentacio_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setPlazos(request.getParameter("item_presentacio_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_objecte_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setResumen(request.getParameter("item_objecte_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_resultat_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setResultat(request.getParameter("item_resultat_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_lloc_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setLugar(request.getParameter("item_lloc_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_destinataris_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setDestinatarios(request.getParameter("item_destinataris_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_notificacio_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setNotificacion(request.getParameter("item_notificacio_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_observacions_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setObservaciones(request.getParameter("item_observacions_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_resolucio_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setResolucion(request.getParameter("item_resolucio_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			if (StringUtils.isNotEmpty(request.getParameter("item_silenci_" + IDIOMA_ORIGEN_TRADUCTOR))) {
				traduccioOrigen.setSilencio(request.getParameter("item_silenci_" + IDIOMA_ORIGEN_TRADUCTOR));
			}
			
			Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
			List<String> langs = traductor.getListLang();
			Map<String, Object> traduccio;
			List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
	        
	        for (String lang: langs) {
	        	if (!IDIOMA_ORIGEN_TRADUCTOR.equalsIgnoreCase(lang)) {
	        		TraduccionProcedimientoLocal traduccioDesti = new TraduccionProcedimientoLocal();
	        		traductor.setDirTraduccio(IDIOMA_ORIGEN_TRADUCTOR, lang);
	        		if (traductor.traducir(traduccioOrigen, traduccioDesti)) {
	        			traduccio = new HashMap<String, Object>();
	        			traduccio.put("lang", lang);
	        			traduccio.put("traduccio", traduccioDesti);
	        			traduccions.add(traduccio);
	        		} else {
	        			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
	        			break;
	        		}
	        	}
	        }
	        	        
			resultats.put("traduccions", traduccions);
						
		} catch (DelegateException dEx) {
			logException(log, dEx);
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
		} catch (NullPointerException npe) {
			log.error("CatalegProcedimentBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("CatalegProcedimentBackController.traduir: Error en al traducir procedimiento: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}
		
		return resultats;
		
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

}
