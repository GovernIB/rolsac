package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoNormativaDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.DateUtil;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;


@Controller
@RequestMapping("/catalegProcediments/")
public class CatalegProcedimentsBackController {
	
	private static Log log = LogFactory.getLog(CatalegProcedimentsBackController.class);

	private MessageSource messageSource = null;

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/catalegProcediments.do")
	public String pantallaProcediment(Map<String, Object> model, HttpSession session, HttpServletRequest request) {

		model.put("menu", 0);
		model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
		model.put("submenu_seleccionado", 1);
		model.put("escriptori", "pantalles/catalegProcediments.jsp");
		if (session.getAttribute("unidadAdministrativa") != null) {
			model.put("idUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getId());
			String lang = request.getLocale().getLanguage();
			model.put("nomUA", ((UnidadAdministrativa) session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(lang));

			try {
                MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
                List<IdNomDTO> materiesDTOList = new ArrayList<IdNomDTO>();
                List<Materia> llistaMateries = materiaDelegate.listarMaterias();
                for (Materia materia : llistaMateries) {
                	materiesDTOList.add(new IdNomDTO(materia.getId(), materia.getNombreMateria(lang)));
                }
                model.put("llistaMateries", materiesDTOList);
				
                
				FamiliaDelegate fd = DelegateUtil.getFamiliaDelegate();
				List<IdNomDTO> familiasDTOList = new LinkedList<IdNomDTO>();
				List<Familia> familias = fd.listarFamilias();
				TraduccionFamilia tf;
				for (Familia f : familias) {
					tf = (TraduccionFamilia) f.getTraduccion(lang);
					familiasDTOList.add(new IdNomDTO(f.getId(), tf.getNombre()));
				}
				model.put("families", familiasDTOList);
				
				
				IniciacionDelegate id = DelegateUtil.getIniciacionDelegate();
				List<IdNomDTO> iniciacionDTOList = new LinkedList<IdNomDTO>();
				List<Iniciacion> iniciaciones = id.listarIniciacion();
				TraduccionIniciacion ti;
				for (Iniciacion i : iniciaciones) {
					ti = (TraduccionIniciacion) i.getTraduccion(lang);
					iniciacionDTOList.add(new IdNomDTO(i.getId(), ti.getNombre()));
				}
				model.put("iniciacions", iniciacionDTOList);
				

			} catch (DelegateException dEx) {
				if (dEx.isSecurityException()) {
					model.put("error", "permisos");
				} else {
					model.put("error", "altres");
					log.error(ExceptionUtils.getStackTrace(dEx));
				}
			}
		}

		return "index";
	}

	
	@RequestMapping(value = "/llistat.do", method = POST)
	public @ResponseBody Map<String, Object> llistatProcediments(HttpServletRequest request, HttpSession session) {

		List<ProcedimientoLocal> llistaProcedimientos = new ArrayList<ProcedimientoLocal>();
		List<ProcedimientoLocalDTO> llistaProcedimientoLocalDTO = new ArrayList<ProcedimientoLocalDTO>();

		Map<String, Object> resultats = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> tradMap = new HashMap<String, String>();

		String lang = request.getLocale().getLanguage();

		
		UnidadAdministrativa ua = null;
		if (session.getAttribute("unidadAdministrativa") == null) {
			return resultats; // Si no hay unidad administrativa se devuelve vacio
		} else {
			ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
		}
		// paramMap.put("unidadAdministrativa.id", ua.getId());

		
		boolean uaFilles;
		if ("1".equals(request.getParameter("uaFilles"))) {
			uaFilles = true;
		} else {
			uaFilles = false;
		}

		
		boolean uaMeves;
		if ("1".equals(request.getParameter("uaMeves"))) {
			uaMeves = true;
		} else {
			uaMeves = false;
		}

		
		try {
            Long codi = ParseUtil.parseLong(request.getParameter("codi"));
            paramMap.put("id", codi);
        } catch (NumberFormatException e){
        }
        
        
//      String codi = request.getParameter("codi");
//		if (codi != null && !"".equals(codi)) {
//			paramMap.put("signatura", codi.toUpperCase());
//		}

		
		Date fechaCaducidad = DateUtil.parseDate(request.getParameter("fechaCaducidad"));
		if (fechaCaducidad != null) {
			paramMap.put("fechaCaducidad", fechaCaducidad);
		}
		
		
		Date fechaPublicacion = DateUtil.parseDate(request.getParameter("fechaPublicacion"));
		if (fechaPublicacion != null) {
			paramMap.put("fechaPublicacion", fechaPublicacion);
		}
		
		
		Date fechaActualizacion = DateUtil.parseDate(request.getParameter("fechaActualizacion"));
		if (fechaActualizacion != null) {
			paramMap.put("fechaActualizacion", fechaActualizacion);
		}

		
		String ventanillaUnica = request.getParameter("finestreta");
		if ("1".equals(ventanillaUnica)) {
			paramMap.put("ventana", 1);
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
		} else if ("0".equals(taxa)) {
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

		try {
			ProcedimientoDelegate procedimientosDelegate = DelegateUtil.getProcedimientoDelegate();
			// llistaProcedimientos = procedimientosDelegate.buscarProcedimientos(paramMap, tradMap);
			llistaProcedimientos = procedimientosDelegate.buscadorProcedimientos(paramMap, tradMap, ua, uaFilles, uaMeves);

			for (ProcedimientoLocal pl : llistaProcedimientos) {
				TraduccionProcedimientoLocal tpl = (TraduccionProcedimientoLocal) pl.getTraduccion(lang);
				llistaProcedimientoLocalDTO.add(new ProcedimientoLocalDTO(
								 pl.getId(), 
								 tpl == null ? "" : tpl.getNombre(), 
								 DateUtil.formatDate(pl.getFechaPublicacion()),
								 DateUtil.formatDate(pl.getFechaCaducidad()),
								 pl.isVisible()));
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				// model.put("error", "permisos");
			} else {
				// model.put("error", "altres");
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}

		resultats.put("total", llistaProcedimientoLocalDTO.size());
		resultats.put("nodes", llistaProcedimientoLocalDTO);

		return resultats;
	}

	
	@RequestMapping(value = "/pagDetall.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
		Map<String, Object> resultats = new HashMap<String, Object>();
		String lang = request.getLocale().getLanguage();
		try {
			Long id = new Long(request.getParameter("id"));

			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			ProcedimientoLocal proc = procedimientoDelegate.obtenerProcedimiento(id);

			resultats.put("item_id", proc.getId());

			resultats.put("item_estat", proc.getValidacion());

			resultats.put("item_data_actualitzacio", DateUtil.formatDate(proc.getFechaActualizacion()));
			
			resultats.put("item_data_publicacio", DateUtil.formatDate(proc.getFechaPublicacion()));

			resultats.put("item_data_caducitat", DateUtil.formatDate(proc.getFechaCaducidad()));

			// Idiomas
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
						IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
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

			if (proc.getIniciacion() != null) {
				Iniciacion iniciacion = proc.getIniciacion();
				resultats.put("item_iniciacio", iniciacion.getId());
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

			resultats.put("item_notes", proc.getInfo());

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
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}

		return resultatStatus;
	}

	
	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardarProcediment(HttpSession session, HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
			if (ua == null) {
				error = messageSource.getMessage("procediment.error.falten.camps", null, request.getLocale());
				result = new IdNomDTO(-3l, error);
			} else {
				
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
					procediment.setUnidadAdministrativa(procedimentOld.getUnidadAdministrativa());
					procediment.setId(procedimentOld.getId());
					procediment.setHechosVitalesProcedimientos(procedimentOld.getHechosVitalesProcedimientos());
					procediment.setTramites(procedimentOld.getTramites());
				} else {
					// A los nuevos procedimientos se les asigna la UA de la miga de pan.
					procediment.setUnidadAdministrativa(ua);
				}


                // Materias
                /* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
                 * En el primer caso es bastante probable que se repitan la mayoria de materias.
                 */
                if (request.getParameter("materies") != null && !"".equals(request.getParameter("materies"))){
                    MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
                    Set<Materia> materiesNoves = new HashSet<Materia>();
                    String[] codisMateriaNous = request.getParameter("materies").split(",");
                    
                    if (edicion){
                        for (int i = 0; i < codisMateriaNous.length; i++){
                            for (Materia materia: procedimentOld.getMaterias()){
                                if(materia.getId().equals(Long.valueOf(codisMateriaNous[i]))) {//materia ya existente
                                    materiesNoves.add(materia);
                                    codisMateriaNous[i] = null;
                                    break;
                                }
                            }                            
                        }                         
                    }                    
                    
                    for (String codiMateria: codisMateriaNous){
                        if (codiMateria != null){
                            materiesNoves.add(materiaDelegate.obtenerMateria(Long.valueOf(codiMateria)));
                        }                        
                    }
                    
                    procediment.setMaterias(materiesNoves);                                   
                }
                // Fin Materias
                
                
                // Normativas
                /* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
                 * En el primer caso es bastante probable que se repitan la mayoria de normativas.
                 */
                if (request.getParameter("normatives") != null && !"".equals(request.getParameter("normatives"))){
                    NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
                    Set<Normativa> normativesNoves = new HashSet<Normativa>();
                    String[] codisNormativesNoves= request.getParameter("normatives").split(",");
                    
                    if (edicion){
                        for (int i = 0; i < codisNormativesNoves.length; i++){
                            for (Normativa normativa: procedimentOld.getNormativas()){
                                if(normativa.getId().equals(Long.valueOf(codisNormativesNoves[i]))) { // normativa ya existente
                                	normativesNoves.add(normativa);
                                    codisNormativesNoves[i] = null;
                                    break;
                                }
                            }                            
                        }                         
                    }                    
                    
                    for (String codiNormativa: codisNormativesNoves){
                        if (codiNormativa != null){
                        	normativesNoves.add(normativaDelegate.obtenerNormativa(Long.valueOf(codiNormativa)));
                        }                        
                    }
                    
                    procediment.setNormativas(normativesNoves);                                   
                }
                // Fin normativas
                
                
                // Documents
                Enumeration<String> nomsParametres = request.getParameterNames();
                Documento document;
                DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
                List<Documento> documents = new ArrayList<Documento>();
                Map <String,String[]> actulitzadorMap = new HashMap<String, String[]>();

                // obtenim  els documents i els seus ordres
                while(nomsParametres.hasMoreElements()) {
                	String nomParameter = (String)nomsParametres.nextElement();                    
                    String[] elements = nomParameter.split("_");
                    
                    if ("documents".equals(elements[0]) && "id".equals(elements[1])) {
                        // En aquest cas, elements[2] es igual al id del document
                    	Long id = ParseUtil.parseLong(request.getParameter(nomParameter));
                    	if (id != null) {
	                    	document = docDelegate.obtenerDocumento(id);
	                    	documents.add(document);
	                    	
	                    	// Se coge el orden de la web. Si se quisiesen poner del 0 al x, hacer que orden valga 0 e ir incrementandolo.
	                    	String[] orden = {request.getParameter("documents_orden_" + elements[2])};
	                    	actulitzadorMap.put("orden_doc" + id, orden);
                    	} else {
                    		log.warn("S'ha rebut un id de document no númeric: " + id);
                    	}
                    }
                }
                
                // actualitzam ordres
                docDelegate.actualizarOrdenDocs(actulitzadorMap);
                
                // assignar els documents al procedimient i eliminar els que ja no estiguin seleccionats.
                procediment.setDocumentos(documents);
                if (edicion){
                    List<Documento> docsOld = procedimentOld.getDocumentos();                                    
                    
                    for(Documento doc: documents){
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

				for (String lang: langs) {
					if (edicion) {
						tpl = (TraduccionProcedimientoLocal) procedimentOld.getTraduccion(lang);
						if (tpl == null) {
							tpl = new TraduccionProcedimientoLocal();
						}
					} else {
						tpl = new TraduccionProcedimientoLocal();
					}

					tpl.setNombre(request.getParameter("item_nom_" + lang));
					tpl.setDestinatarios(request.getParameter("item_destinataris_" + lang));
					tpl.setResumen(request.getParameter("item_objecte_" + lang));
					tpl.setDestinatarios(request.getParameter("item_destinataris_" + lang));
					tpl.setRequisitos(request.getParameter("item_requisits_" + lang));
					tpl.setResolucion(request.getParameter("item_resolucio_" + lang));
					tpl.setNotificacion(request.getParameter("item_notificacio_" + lang));
					tpl.setSilencio(request.getParameter("item_silenci_" + lang));
					tpl.setObservaciones(request.getParameter("item_observacions_" + lang));
					
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

				
				Date data_publicacio = DateUtil.parseDate(request.getParameter("item_data_publicacio"));
				if (data_publicacio != null) {
					procediment.setFechaPublicacion(data_publicacio);
				}
				
				
				Date data_caducitat = DateUtil.parseDate(request.getParameter("item_data_caducitat"));
				if (data_caducitat != null) {
					procediment.setFechaCaducidad(data_caducitat);
				}
				
				
//				procediment.setFechaActualizacion(new Date()); // lo hace el facade automaticamente.
				
				
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
				procediment.setTramite(request.getParameter("item_tramite"));
				procediment.setUrl(request.getParameter("item_url"));
				try {
					String versionStr = request.getParameter("item_version");
					if (versionStr != null && !"".equals(versionStr)) {
						Long version = Long.parseLong(versionStr);
						procediment.setVersion(version);
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
				
				
				procediment.setTaxa("on".equalsIgnoreCase(request.getParameter("item_taxa")) ? "1" : "0");
				procediment.setIndicador("on".equalsIgnoreCase(request.getParameter("item_fi_vida_administrativa")) ? "1" : "0");
				procediment.setVentanillaUnica("on".equalsIgnoreCase(request.getParameter("item_finestreta_unica")) ? "1" : "0");
				
				
				procediment.setSignatura(request.getParameter("item_codi"));
				procediment.setInfo(request.getParameter("item_notes"));

				
				Long procId = procedimentDelegate.grabarProcedimiento(procediment, procediment.getUnidadAdministrativa().getId());

				
				String ok = messageSource.getMessage("proc.guardat.correcte", null, request.getLocale());
				result = new IdNomDTO(procId, ok);
			}

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
	
	
	@RequestMapping(value = "/cercarNormatives.do", method = POST)
	public @ResponseBody Map<String, Object> llistatNormatives(HttpServletRequest request, HttpSession session)  {
		
		//Listar las normativas de la unidad administrativa
		List<Normativa>llistaNormatives = new ArrayList<Normativa>();
		List<ProcedimientoNormativaDTO>llistaNormativesDTO= new ArrayList<ProcedimientoNormativaDTO>();
		Map<String,Object> resultats = new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();	
		Map<String, String> paramTrad = new HashMap<String, String>();
		
		// TODO obtener la ordenación por parámetro
		String campoOrdenacion = "normativa.fecha";
		String orden = "desc";		
				
		String idioma = request.getLocale().getLanguage();
		
		if (session.getAttribute("unidadAdministrativa") == null){
			return resultats;//Si no hay unidad administrativa se devuelve vacío
		}
		UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");		
		
		
		try {
			//Obtener parámetros de búsqueda
		
			if (request.getParameter("data") != null && !request.getParameter("data").equals("")) {
				Date fecha = DateUtil.parseDate(request.getParameter("data"));
				paramMap.put("fecha", fecha);
			}			

			if (request.getParameter("dataButlleti") != null && !request.getParameter("dataButlleti").equals("")) {
				Date fechaBoletin = DateUtil.parseDate(request.getParameter("dataButlleti"));
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
			
			
			//Realizar la consulta y obtener resultados
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			llistaNormatives = normativaDelegate.buscarNormativas(paramMap, paramTrad, "local", ua.getId(), false, campoOrdenacion, orden);
			
			for (Normativa normativa : llistaNormatives) {
				TraduccionNormativa traNor = (TraduccionNormativa)normativa.getTraduccion(idioma);
				String titulo = "";
				if (traNor != null) {
					//Retirar posible enlace incrustado en titulo
					titulo = HtmlUtils.obtenerTituloDeEnlaceHtml(traNor.getTitulo());
				}					

				llistaNormativesDTO.add(new ProcedimientoNormativaDTO(
						normativa.getId(),
						titulo,
						DateUtil.formatDate(normativa.getFecha()),
						DateUtil.formatDate(normativa.getFechaBoletin())
				));
			}

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				//model.put("error", "permisos");
			} else {
				//model.put("error", "altres");
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}
		
		resultats.put("total", llistaNormativesDTO.size());
		resultats.put("nodes", llistaNormativesDTO);

		return resultats;
	}

	
}
