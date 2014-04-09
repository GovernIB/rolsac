package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadMateriaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.CargaModulosLateralesUtil;
import es.caib.rolsac.back2.util.GuardadoAjaxUtil;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductor.Traductor;

@Controller
@RequestMapping("/materies/")
public class TMMateriesController extends PantallaBaseController {

	private static Log log = LogFactory.getLog(TMMateriesController.class);
	private Map<String, Object> resultats;

	@RequestMapping(value = "/materia.do")
	public String pantalla(Map<String, Object> model, HttpServletRequest request) {
		
		model.put("menu", 1);
		model.put("submenu", "layout/submenu/submenuTMMateries.jsp");

		RolUtil rolUtil = new RolUtil(request);
		
		if (rolUtil.userIsAdmin()) {
		
			PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
			
			try {
				
				List<IdNomDTO> perfilesDTO = new LinkedList<IdNomDTO>();
				
				for (PerfilCiudadano perfil : (List<PerfilCiudadano>)perfilDelegate.listarPerfiles()) {
					TraduccionPerfilCiudadano tpc = (TraduccionPerfilCiudadano) perfil.getTraduccion();
					perfilesDTO.add(new IdNomDTO(perfil.getId(), tpc != null ? tpc.getNombre() : ""));
				}
				
				model.put("perfils", perfilesDTO);
				model.put("escriptori", "pantalles/taulesMestres/tmMateries.jsp");
				
			} catch (DelegateException dEx) {
				
				if (dEx.isSecurityException()) {
					model.put("error", "permisos");
				} else {
					log.error("Error: " + dEx.getMessage());
					model.put("error", "altres");
				}
				
			}
			
		} else {
			
			model.put("error", "permisos");
			
		}

		loadIndexModel (model, request);
		
		return "index";
		
	}

	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request) {

		List<Map<String, Object>> llistaMateriaDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> materiaDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		// Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");

		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);

		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();

		try {
			
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

			resultadoBusqueda = materiaDelegate.listarMaterias(Integer.parseInt(pagPag), Integer.parseInt(pagRes), DelegateUtil.getIdiomaDelegate().lenguajePorDefecto());

			for (Object o : resultadoBusqueda.getListaResultados() ) {

				Long id =  (Long) ((Object[]) o)[0];
				String codiEstandar = (String) ((Object[]) o)[1];
				String nom = (String) ((Object[]) o)[2];

				materiaDTO = new HashMap<String, Object>();
				materiaDTO.put("id", id);
				materiaDTO.put("codi_estandar", codiEstandar ); 
				materiaDTO.put("nom", nom );

				llistaMateriaDTO.add(materiaDTO);
				
			}

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
			
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados() );
		resultats.put("nodes", llistaMateriaDTO);

		return resultats;
		
	}

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {

			Long id = new Long(request.getParameter("id"));
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			Materia materia = materiaDelegate.obtenerMateria(id);	        	        

			omplirCampsTraduibles(resultats, materia);

			resultats.put("item_id", materia.getId());
			resultats.put("item_codi_hita", materia.getCodiHita());
			resultats.put("item_codi_estandard", materia.getCodigoEstandar());

			// Foto
			if (materia.getFoto() != null) {
				resultats.put("item_foto_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&tipus=1");
				resultats.put("item_foto", materia.getFoto().getNombre());
			} else {
				resultats.put("item_foto_enllas_arxiu", "");
				resultats.put("item_foto", "");
			}

			// Icona
			if (materia.getIcono() != null){
				resultats.put("item_icona_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&tipus=2");
				resultats.put("item_icona", materia.getIcono().getNombre());
			} else {
				resultats.put("item_icona_enllas_arxiu", "");
				resultats.put("item_icona", "");
			}

			// Icona gran
			if (materia.getIconoGrande() != null){
				resultats.put("item_icona_gran_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&tipus=3");
				resultats.put("item_icona_gran", materia.getIconoGrande().getNombre());
			} else {
				resultats.put("item_icona_gran_enllas_arxiu", "");
				resultats.put("item_icona_gran", "");
			}  

		} catch (DelegateException dEx) {
			
			log.error(ExceptionUtils.getStackTrace(dEx));
			
			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			
		}

		return resultats;
		
	}

	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {

		resultats = new HashMap<String, Object>();

		try {
			
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			Materia materia = materiaDelegate.obtenerMateria(id);
			
			List<IconoMateria> listaIconos = materiaDelegate.obtenerIconosPerfil(id);
			resultats.put("icones", CargaModulosLateralesUtil.recuperaIconosRelacionados(listaIconos, IconoMateria.class, id));
			
			List<UnidadAdministrativa> listaUAs = materiaDelegate.listarUAsMateria(id);
			resultats.put("uas", CargaModulosLateralesUtil.recuperaUAsRelacionadas(listaUAs, materia.getId(), lang, false));
			resultats.put("item_ua_principal", getUAPrincipal(materia.getUnidadesmaterias()));
			resultats.put("item_destacada", materia.isDestacada());			
			
		} catch (DelegateException e) {

			log.error(ExceptionUtils.getStackTrace(e));

			if (e.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));

		}

		return resultats;

	}

	private void omplirCampsTraduibles(Map<String, Object> resultats, Materia materia) throws DelegateException {
		
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();

		for (String lang : langs) {
			
			if (null != materia.getTraduccion(lang)) {
				
				HashMap<String, String> traduccionMateriaDTO = new HashMap<String, String>();
				TraduccionMateria tm = (TraduccionMateria) materia.getTraduccion(lang);

				traduccionMateriaDTO.put("item_nombre", tm.getNombre());
				traduccionMateriaDTO.put("item_descripcion", tm.getDescripcion());
				traduccionMateriaDTO.put("item_palabras_clave", tm.getPalabrasclave());

				if (tm.getDistribComp() != null) {
					traduccionMateriaDTO.put("item_distribucion_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&lang=" + lang + "&tipus=4");
					traduccionMateriaDTO.put("item_distribucion", tm.getDistribComp().getNombre());
				} else {
					traduccionMateriaDTO.put("item_distribucion_enllas_arxiu", "");
					traduccionMateriaDTO.put("item_distribucion", "");
				}

				if (tm.getNormativa() != null) {
					traduccionMateriaDTO.put("item_normativa_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&lang=" + lang + "&tipus=5");
					traduccionMateriaDTO.put("item_normativa", tm.getNormativa().getNombre());
				} else {
					traduccionMateriaDTO.put("item_normativa_enllas_arxiu", "");
					traduccionMateriaDTO.put("item_normativa", "");
				}

				if (tm.getContenido() != null) {
					traduccionMateriaDTO.put("item_contenido_enllas_arxiu", "materies/archivo.do?id=" + materia.getId() + "&lang=" + lang + "&tipus=6");
					traduccionMateriaDTO.put("item_contenido", tm.getContenido().getNombre());
				} else {
					traduccionMateriaDTO.put("item_contenido_enllas_arxiu", "");
					traduccionMateriaDTO.put("item_contenido", "");
				}

				resultats.put(lang, traduccionMateriaDTO);
				
			} else {
				
				resultats.put(lang, new HashMap<String, String>());
				
			}
			
		}
		
	}

	// Dado un set devolvemos el id de la principal
	private Long getUAPrincipal(Set uas) {

		Iterator it = uas.iterator();

		while (it.hasNext()) {

			UnidadMateria unimat = (UnidadMateria)it.next();

			if ( "S".equals(unimat.getUnidadPrincipal()) )
				return unimat.getUnidad().getId(); 

		}

		return null;

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

		Materia materia = new Materia();
		Materia materiaOld = null; 		
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

			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

			boolean edicion = valoresForm.get("item_id") != null && !"".equals(valoresForm.get("item_id"));

			if (edicion) {

				Long idMateria = ParseUtil.parseLong(valoresForm.get("item_id"));
				materiaOld = materiaDelegate.obtenerMateria(idMateria);
				materia.setId(idMateria);
				materia.setProcedimientosLocales(materiaOld.getProcedimientosLocales());

				materia.setFichas(materiaOld.getFichas());
				materia.setIconos(materiaOld.getIconos());
				materia.setMateriasAgrupacionM(materiaOld.getMateriasAgrupacionM());

			}

			// Obtener campos por idioma
			TraduccionMateria traMat; 
			List<String> idiomas = DelegateUtil.getIdiomaDelegate().listarLenguajes();

			for (String idioma : idiomas) {

				traMat = materiaOld != null ? (TraduccionMateria)materiaOld.getTraduccion(idioma) : null;
				if (traMat != null) {
					materia.setTraduccion(idioma, traMat);
				} else {
					traMat = new TraduccionMateria();
					materia.setTraduccion(idioma, traMat);
				}

				traMat.setDescripcion( RolUtil.limpiaCadena(valoresForm.get("item_descripcio_" + idioma)) );
				traMat.setNombre( RolUtil.limpiaCadena(valoresForm.get("item_nom_" + idioma)) );
				traMat.setPalabrasclave( RolUtil.limpiaCadena(valoresForm.get("item_paraules_clau_" + idioma)) );

				// Archivos
				fileItem = ficherosForm.get("item_distribucion_" + idioma);
				if (fileItem.getSize() > 0) {
					traMat.setDistribComp(UploadUtil.obtenerArchivo(traMat.getDistribComp(), fileItem));
				} else if (valoresForm.get("item_distribucion_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_distribucion_" + idioma + "_delete"))){
					traMat.setDistribComp(null);
				}

				fileItem = ficherosForm.get("item_normativa_" + idioma);
				if (fileItem.getSize() > 0) {
					traMat.setNormativa(UploadUtil.obtenerArchivo(traMat.getNormativa(), fileItem));
				} else if (valoresForm.get("item_normativa_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_normativa_" + idioma + "_delete"))){
					traMat.setNormativa(null);
				}

				fileItem = ficherosForm.get("item_contenido_" + idioma);
				if (fileItem.getSize() > 0) {
					traMat.setContenido(UploadUtil.obtenerArchivo(traMat.getContenido(), fileItem));
				} else if (valoresForm.get("item_contenido_" + idioma + "_delete") != null && !"".equals(valoresForm.get("item_contenido_" + idioma + "_delete"))){
					traMat.setContenido(null);
				}

			}

			//Obtener los demás campos
			materia.setCodiHita(valoresForm.get("item_codi_hita"));
			materia.setCodigoEstandar(valoresForm.get("item_codi_estandard"));
			materia.setDestacada(valoresForm.get("item_destacada") != null && !"".equals(valoresForm.get("item_destacada")));

			// Foto
			fileItem = ficherosForm.get("item_foto");
			if (fileItem.getSize() > 0) {
				materia.setFoto(UploadUtil.obtenerArchivo(materia.getFoto(), fileItem));
			} else if (valoresForm.get("item_foto_delete") != null && !"".equals(valoresForm.get("item_foto_delete"))){
				materia.setFoto(null);
			} else if (edicion) {
				materia.setFoto(materiaOld.getFoto());
			}

			// Icona
			fileItem = ficherosForm.get("item_icona");
			if (fileItem.getSize() > 0) {
				materia.setIcono(UploadUtil.obtenerArchivo(materia.getIcono(), fileItem));
			} else if (valoresForm.get("item_icona_delete") != null && !"".equals(valoresForm.get("item_icona_delete"))){
				materia.setIcono(null);
			} else if (edicion) {
				materia.setIcono(materiaOld.getIcono());
			}

			// Icona gran
			fileItem = ficherosForm.get("item_icona_gran");
			if (fileItem.getSize() > 0) {
				materia.setIconoGrande(UploadUtil.obtenerArchivo(materia.getIconoGrande(), fileItem));
			} else if (valoresForm.get("item_icona_gran_delete") != null && !"".equals(valoresForm.get("item_icona_gran_delete"))){
				materia.setIconoGrande(null);
			} else if (edicion) {
				materia.setIconoGrande(materiaOld.getIconoGrande());
			}

			materiaDelegate.grabarMateria(materia);

			result = new IdNomDTO(materia.getId(), messageSource.getMessage("materia.guardat.correcte", null, request.getLocale()));

		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				String error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
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

	@RequestMapping(value = "/esborrarMateria.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();
		
		try {
			
			Long id = new Long(request.getParameter("id"));
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			materiaDelegate.borrarMateria(id);
			resultatStatus.setId(1l);
			
		} catch (DelegateException dEx) {
			
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
			
		} catch (NumberFormatException nfEx) {
			
			resultatStatus.setId(-3l);
			log.error("Error: Id de materia no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		
		return resultatStatus;
		
	}

	@RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request) {
		
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

			TraduccionMateria traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			
			log.error("MateriaBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		} catch (Exception e) {
			
			log.error("MateriaBackController.traduir: Error en al traducir materia: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
			
		}

		return resultats;
		
	}

	private TraduccionMateria getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {
		
		TraduccionMateria traduccioOrigen = new TraduccionMateria();

		if (StringUtils.isNotEmpty(request.getParameter("item_nom_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setNombre(request.getParameter("item_nom_" + idiomaOrigenTraductor));
		}

		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}

		if (StringUtils.isNotEmpty(request.getParameter("item_paraules_clau_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setPalabrasclave(request.getParameter("item_paraules_clau_" + idiomaOrigenTraductor));
		}

		return traduccioOrigen;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarUnidadesRelacionadas.do")
	public @ResponseBody IdNomDTO guardarUnidadesRelacionadas(Long id, Long[] elementos, Long itemUAPrincipal, HttpServletRequest request) {
				
		IdNomDTO result = null;
		
		try {
						
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			Materia materia = materiaDelegate.obtenerMateria(id);
			
			UnidadMateriaDelegate uaMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
			
			// Obtenemos las relaciones que borraremos primero.
			Set<UnidadMateria> unidadesMateria = (Set<UnidadMateria>)materia.getUnidadesmaterias();
			List<Long> unidadesMateriaABorrar = new ArrayList<Long>();
			Iterator<UnidadMateria> it = unidadesMateria.iterator();
			while ( it.hasNext() )
				unidadesMateriaABorrar.add( it.next().getId() );
			
			List<UnidadMateria> unidadesMateriaNuevas = new ArrayList<UnidadMateria>();
			
			// Procesamos los elementos actuales.
			if ( elementos != null ) {
				
				UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
								
				for ( int i = 0; i < elementos.length; i++ ) {
					
					if ( elementos[i] != null ) {
					
						UnidadMateria uam = new UnidadMateria();
						UnidadAdministrativa ua = uaDelegate.consultarUnidadAdministrativaSinFichas(elementos[i]);
						
						uam.setMateria(materia);
						uam.setUnidad(ua);
						
						// Comprobamos si es UA principal
						if ( itemUAPrincipal != null && itemUAPrincipal.equals(elementos[i]) )
							uam.setUnidadPrincipal("S");
						else
							uam.setUnidadPrincipal("N");
						
						unidadesMateriaNuevas.add(uam);
											
					}
					
				}
				
			}
			
			uaMateriaDelegate.grabarUnidadesMateria(unidadesMateriaNuevas, unidadesMateriaABorrar);
			
			String ok = messageSource.getMessage("materia.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(materia.getId(), ok);            

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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guardarIconosRelacionados.do")
	public @ResponseBody IdNomDTO guardarIconosRelacionados(Long id, Long[] elementos, HttpServletRequest request) {
		
		IdNomDTO result = null;
		
		try {
						
			MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
			Materia materia = materiaDelegate.obtenerMateria(id);
			
			// XXX: Iconos: o no hay cambios o se han de eliminar algunos (las adiciones se hacen en IconaMateriaBackController).
			// Eliminamos los que ya no están presentes.
			GuardadoAjaxUtil.actualizarIconos(elementos, null, materia);
									
			String ok = messageSource.getMessage("materia.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(materia.getId(), ok);

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

}
