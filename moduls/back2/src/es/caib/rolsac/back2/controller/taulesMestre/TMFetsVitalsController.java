package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.model.dto.ProcedimientoLocalDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.LlistatUtil;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/fetsVitals/")
public class TMFetsVitalsController extends PantallaBaseController
{
	private static Log log = LogFactory.getLog(TMFetsVitalsController.class);
	
	
	@RequestMapping(value = "/fetsVitals.do")
	public String pantallaFetsVitals(Map<String, Object> model, HttpServletRequest request)
	{
		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMFetsVitals.jsp");
		
		RolUtil rolUtil= new RolUtil(request);
		if (rolUtil.userIsAdmin()) {
			model.put("escriptori", "pantalles/taulesMestres/tmFetsVitals.jsp");
			try {
				String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
				model.put("families", LlistatUtil.llistarFamilias(lang));
				model.put("iniciacions", LlistatUtil.llistarIniciacions(lang));
				
			} catch (DelegateException dEx) {
				if (dEx.isSecurityException()) {
					model.put("error", "permisos");
				} else {
					model.put("error", "altres");
					logException(log, dEx);
				}
			}
		} else {
			model.put("error", "permisos");
		}
		
		loadIndexModel(model, request);
		return "index";
	}
    
    @RequestMapping(value = "/llistat.do")
   	public @ResponseBody Map<String, Object> llistatFetsVitals(HttpServletRequest request) {
   	
   		List<Map<String, Object>> llistaFetsVitalsDTO = new ArrayList<Map<String, Object>>();
   		Map<String, Object> fetVitalDTO;
   		Map<String, Object> resultats = new HashMap<String, Object>();
   		
		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
       		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();   			
   		
   		try {
   			HechoVitalDelegate fetsVitalsDelegate = DelegateUtil.getHechoVitalDelegate();
   			String idiomaPorDefecto = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

   			resultadoBusqueda = fetsVitalsDelegate.listarHechosVitales(Integer.parseInt(pagPag), Integer.parseInt(pagRes), idiomaPorDefecto);
   			
   			for (Object o : resultadoBusqueda.getListaResultados() ) {
   				
   				Long id = (Long) ((Object[]) o )[0];
   				int ordre = ((Integer) ((Object[]) o )[1]).intValue();
   				String nom = (String) ((Object[]) o)[2];
   				
   				fetVitalDTO = new HashMap<String, Object>();
   				fetVitalDTO.put("id", id);
   				fetVitalDTO.put("ordre", ordre);
   				fetVitalDTO.put("nom", nom);
   				
   				llistaFetsVitalsDTO.add(fetVitalDTO);
   				
   			}
   			
   		} catch (DelegateException dEx) {
   			if (dEx.isSecurityException()) {
   				log.error("Permisos insuficients: " + dEx.getMessage());
   			} else {
   				log.error("Error: " + dEx.getMessage());
   			}
   		}

   		resultats.put("total", resultadoBusqueda.getTotalResultados());
   		resultats.put("nodes", llistaFetsVitalsDTO);

   		return resultats;
   	}
    
    
    @RequestMapping(value = "/pagDetall.do")
    public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request)
    {
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	try {
    		Long id = new Long(request.getParameter("id"));
    		
    		HechoVitalDelegate fetsVitalsDelegate = DelegateUtil.getHechoVitalDelegate();
    		HechoVital fetsVitals = fetsVitalsDelegate.obtenerHechoVital(id);
    		
    		resultats.put("item_id", fetsVitals.getId());
    		resultats.put("item_codi_estandard", fetsVitals.getCodigoEstandar());
	        
	        // Foto
            if (fetsVitals.getFoto() != null) {
                resultats.put("item_foto_enllas_arxiu", "fetsVitals/archivo.do?id=" + fetsVitals.getId() + "&tipus=1");
                resultats.put("item_foto", fetsVitals.getFoto().getNombre());
            } else {
                resultats.put("item_foto_enllas_arxiu", "");
                resultats.put("item_foto", "");
            }
            
            // Icona
            if (fetsVitals.getIcono() != null) {
                resultats.put("item_icona_enllas_arxiu", "fetsVitals/archivo.do?id=" + fetsVitals.getId() + "&tipus=2");
                resultats.put("item_icona", fetsVitals.getIcono().getNombre());
            } else {
                resultats.put("item_icona_enllas_arxiu", "");
                resultats.put("item_icona", "");
            }
            
            // Icona gran
            if (fetsVitals.getIconoGrande() != null) {
                resultats.put("item_icona_gran_enllas_arxiu", "fetsVitals/archivo.do?id=" + fetsVitals.getId() + "&tipus=3");
                resultats.put("item_icona_gran", fetsVitals.getIconoGrande().getNombre());
            } else {
                resultats.put("item_icona_gran_enllas_arxiu", "");
                resultats.put("item_icona_gran", "");
            }
            
            omplirCampsTraduibles(resultats, fetsVitals);
            
            //Procedimientos
			List<ProcedimientoLocalDTO> procedimientos = new LinkedList<ProcedimientoLocalDTO>();
	        List<HechoVitalProcedimiento> lista = castList(HechoVitalProcedimiento.class, fetsVitals.getHechosVitalesProcedimientos());
	        List<HechoVitalProcedimiento> listaProcedimientos = new LinkedList<HechoVitalProcedimiento>();
	        for (HechoVitalProcedimiento hechoProc: lista) {
	        	if (hechoProc != null)
	        		listaProcedimientos.add(hechoProc);
	        }
	        Collections.sort(listaProcedimientos);
	        String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
	        for (HechoVitalProcedimiento hvProc : listaProcedimientos) {
	        	if (hvProc != null) {
		        	TraduccionProcedimientoLocal traProc = (TraduccionProcedimientoLocal)hvProc.getProcedimiento().getTraduccion(lang); 
		        	procedimientos.add(new ProcedimientoLocalDTO(
		        			hvProc.getId(),
		        			hvProc.getProcedimiento().getId(),
		        			traProc != null ? traProc.getNombre() : "",
	    					null, null, null,
	    					hvProc.getOrden()
					));
	        	}
	        }
	        resultats.put("procediments", procedimientos);
	        
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
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, HechoVital fetsVitals) throws DelegateException {
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
        List<String> langs = castList(String.class, idiomaDelegate.listarLenguajes());
        
        for (String lang: langs) {
            if (null != fetsVitals.getTraduccion(lang)) {
                HashMap<String, String> traduccionFetsVitalsDTO = new HashMap<String, String>();
                TraduccionHechoVital tm = (TraduccionHechoVital) fetsVitals.getTraduccion(lang);
                
                traduccionFetsVitalsDTO.put("nombre", tm.getNombre());
                traduccionFetsVitalsDTO.put("descripcion", tm.getDescripcion());
                traduccionFetsVitalsDTO.put("palabrasclave", tm.getPalabrasclave());
                
                if (tm.getDistribComp() != null) {
                    traduccionFetsVitalsDTO.put("distribucio_enllas_arxiu", "fetsVitals/archivo.do?id=" + fetsVitals.getId() + "&lang=" + lang + "&tipus=4");
                    traduccionFetsVitalsDTO.put("distribucio", tm.getDistribComp().getNombre());
                } else {
                    traduccionFetsVitalsDTO.put("distribucio_enllas_arxiu", "");
                    traduccionFetsVitalsDTO.put("distribucio", "");
                }
                
                if (tm.getNormativa() != null) {
                    traduccionFetsVitalsDTO.put("normativa_enllas_arxiu", "fetsVitals/archivo.do?id=" + fetsVitals.getId() + "&lang=" + lang + "&tipus=5");
                    traduccionFetsVitalsDTO.put("normativa", tm.getNormativa().getNombre());
                } else {
                    traduccionFetsVitalsDTO.put("normativa_enllas_arxiu", "");
                    traduccionFetsVitalsDTO.put("normativa", "");
                }
                
                if (tm.getContenido() != null) {
                    traduccionFetsVitalsDTO.put("contingut_enllas_arxiu", "fetsVitals/archivo.do?id=" + fetsVitals.getId() + "&lang=" + lang + "&tipus=6");
                    traduccionFetsVitalsDTO.put("contingut", tm.getContenido().getNombre());
                } else {
                    traduccionFetsVitalsDTO.put("contingut_enllas_arxiu", "");
                    traduccionFetsVitalsDTO.put("contingut", "");
                }
                
                resultats.put(lang, traduccionFetsVitalsDTO);
            } else {
                resultats.put(lang, new HashMap<String, String>());
            }
        }
    }
    
    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardaFetsVitals(HttpSession session, HttpServletRequest request) {  
        /**
         * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
         * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
         * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
         * un descargable o fichero vinculado a una aplicaci�n. 
         * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
         */
    	HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        
        HechoVital fetVital = new HechoVital();
        HechoVital fetVitalOld = null;         
        IdNomDTO result = null;
        
        Map<String, String> valoresForm = new HashMap<String, String>();
        Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
        
        try {
            
            //Aqu� nos llegar� un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
            //Iremos recopilando los par�metros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
            
            FileItem fileItem;
            List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
                } else {
                    ficherosForm.put(item.getFieldName(), item);                    
                }
            }
            
            HechoVitalDelegate fetsVitalsDelegate = DelegateUtil.getHechoVitalDelegate();

            boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));
            if (edicion) {                              
                Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
                fetVitalOld = fetsVitalsDelegate.obtenerHechoVital(id);
               
                fetVital.setId(id);
                fetVital.setFichas(fetVitalOld.getFichas());
                fetVital.setHechosVitalesAgrupacionHV(fetVitalOld.getHechosVitalesAgrupacionHV());
                //fetVital.setHechosVitalesProcedimientos(fetVitalOld.getHechosVitalesProcedimientos());
                fetVital.setOrden(fetVitalOld.getOrden());
                fetVital.setFoto(fetVitalOld.getFoto());
                fetVital.setIcono(fetVitalOld.getIcono());
                fetVital.setIconoGrande(fetVitalOld.getIconoGrande());
            }
            
            // Obtener campos por idioma
            TraduccionHechoVital traFV; 
            List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

            for (String idioma : idiomas) {
                traFV = fetVitalOld != null ? (TraduccionHechoVital)fetVitalOld.getTraduccion(idioma) : null;
                if (traFV != null) {
                    fetVital.setTraduccion(idioma, traFV);
                } else {
                    traFV = new TraduccionHechoVital();
                    fetVital.setTraduccion(idioma, traFV);
                }

                traFV.setDescripcion(valoresForm.get("item_descripcio_" + idioma));
                traFV.setNombre(valoresForm.get("item_nom_" + idioma));
                traFV.setPalabrasclave(valoresForm.get("item_paraules_clau_" + idioma));
                
                
                // Archivos
                fileItem = ficherosForm.get("item_distribucio_" + idioma);
                if (fileItem != null && fileItem.getSize() > 0) {
                    traFV.setDistribComp(UploadUtil.obtenerArchivo(traFV.getDistribComp(), fileItem));
                } else if (valoresForm.get("item_distribucio_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_distribucio_" + idioma + "_delete"))){
                    traFV.setDistribComp(null);
                }
                
                fileItem = ficherosForm.get("item_normativa_" + idioma);
                if (fileItem != null &&fileItem.getSize() > 0) {
                    traFV.setNormativa(UploadUtil.obtenerArchivo(traFV.getNormativa(), fileItem));
                } else if (valoresForm.get("item_normativa_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_normativa_" + idioma + "_delete"))){
                    traFV.setNormativa(null);
                }
                
                fileItem = ficherosForm.get("item_contingut_" + idioma);
                if (fileItem != null &&fileItem.getSize() > 0) {
                    traFV.setContenido(UploadUtil.obtenerArchivo(traFV.getContenido(), fileItem));
                } else if (valoresForm.get("item_contingut_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_contingut_" + idioma + "_delete"))){
                    traFV.setContenido(null);
                }
               
            }
 
            //Obtener los dem�s campos
            fetVital.setCodigoEstandar(valoresForm.get("item_codi_estandard"));
            
            // Foto
            fileItem = ficherosForm.get("item_foto");
            if (fileItem != null &&fileItem.getSize() > 0) {
                fetVital.setFoto(UploadUtil.obtenerArchivo(fetVital.getFoto(), fileItem));
            } else if (valoresForm.get("item_foto_delete") != null && !"".equals(valoresForm.get("item_foto_delete"))){
                fetVital.setFoto(null);
            }
            
            // Icona
            fileItem = ficherosForm.get("item_icona");
            if (fileItem != null &&fileItem.getSize() > 0) {
                fetVital.setIcono(UploadUtil.obtenerArchivo(fetVital.getIcono(), fileItem));
            } else if (valoresForm.get("item_icona_delete") != null && !"".equals(valoresForm.get("item_icona_delete"))){
                fetVital.setIcono(null);
            }
            
            // Icona gran
            fileItem = ficherosForm.get("item_icona_gran");
            if (fileItem != null &&fileItem.getSize() > 0) {
                fetVital.setIconoGrande(UploadUtil.obtenerArchivo(fetVital.getIconoGrande(), fileItem));
            } else if (valoresForm.get("item_icona_gran_delete") != null && !"".equals(valoresForm.get("item_icona_gran_delete"))){
                fetVital.setIconoGrande(null);
            }

            
            // Guardamos el hecho vital antes de modificar sus procedimientos relacionados.
            fetsVitalsDelegate.grabarHechoVital(fetVital);
            
            
            // Procediments relacionats
            if (edicion) {
            	HechoVitalProcedimientoDelegate hvpDelegate = DelegateUtil.getHechoVitalProcedimientoDelegate();
            	ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
            	List<Long> hvProcIds = new LinkedList<Long>();
            	List<HechoVitalProcedimiento> hvpAGrabar = new LinkedList<HechoVitalProcedimiento>();
            	
            	// Recogemos los id de los campos que queremos borrar y los eliminamos
            	for (HechoVitalProcedimiento hvProc: (List<HechoVitalProcedimiento>) fetVitalOld.getHechosVitalesProcedimientos()) {
            		if (hvProc != null)
            			hvProcIds.add(hvProc.getId());
            	}
            	hvpDelegate.borrarHechoVitalProcedimientos(hvProcIds);
            	
            	// Escribimos la nueva selección
            	for (String key: valoresForm.keySet()) {
            		if (key.startsWith("procediment_id_")) {
            			long procHvId = Long.parseLong(valoresForm.get(key));
            			long procedimientoId = Long.parseLong(valoresForm.get("procediment_idProcedimiento_" + procHvId));
            			
	            		HechoVitalProcedimiento hvp = new HechoVitalProcedimiento();
	            		hvp.setHechoVital(fetVitalOld);
	            		hvp.setOrden(Integer.parseInt(valoresForm.get("procediment_orden_" + procHvId)));
	            		hvp.setProcedimiento(procDelegate.obtenerProcedimiento(procedimientoId));
	            		
	            		hvpAGrabar.add(hvp);
            		}
            	}
            	hvpDelegate.grabarHechoVitalProcedimientos(hvpAGrabar);
            }
            
            result = new IdNomDTO(fetVital.getId(), messageSource.getMessage("fetVital.guardat.correcte", null, request.getLocale()) );
            
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                String error = messageSource.getMessage("error.permisos", null, request.getLocale());
                result = new IdNomDTO(-1l, error);
            } else {
                String error = messageSource.getMessage("error.altres", null, request.getLocale());
                result = new IdNomDTO(-2l, error);
                log.error(ExceptionUtils.getStackTrace(dEx));
            }
        } catch (UnsupportedEncodingException e) {
			String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
        } catch (NumberFormatException e) {
        	String error = messageSource.getMessage("error.altres", null, request.getLocale());
			result = new IdNomDTO(-2l, error);
			log.error(ExceptionUtils.getStackTrace(e));
        } catch (FileUploadException e) {
			String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
			result = new IdNomDTO(-3l, error);
			log.error(ExceptionUtils.getStackTrace(e));
        }
        
        return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
	}
    
    @RequestMapping(value = "/esborrarFetsVitals.do", method = POST)
	public @ResponseBody IdNomDTO esborrarFetsVitals(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			HechoVitalDelegate fetsVitalsDelegate = DelegateUtil.getHechoVitalDelegate();
			fetsVitalsDelegate.borrarHechoVital(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de fets vitals no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
    @RequestMapping(value = "/pujarFetsVitals.do", method = POST)
	public @ResponseBody IdNomDTO pujarFetsVitals(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			HechoVitalDelegate fetsVitalsDelegate = DelegateUtil.getHechoVitalDelegate();
			fetsVitalsDelegate.subirOrden(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de fets vitals no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
    @RequestMapping(value = "/reordenarFetsVitals.do", method = POST)
    public @ResponseBody IdNomDTO reordenarFetsVitals(HttpServletRequest request) {
    	IdNomDTO resultatStatus = new IdNomDTO();
    	
		try {
			
			Long id = new Long(request.getParameter("id"));
			Integer ordenNuevo = new Integer(request.getParameter("orden"));
			Integer ordenAnterior = new Integer(request.getParameter("ordenAnterior"));
			
			HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();
			hechoVitalDelegate.reordenar(id, ordenNuevo, ordenAnterior);
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de fet vital no numèric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		
		return resultatStatus;
    }    
    
}
