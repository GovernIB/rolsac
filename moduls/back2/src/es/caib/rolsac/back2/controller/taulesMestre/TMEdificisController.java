package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.HtmlUtils;
import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@Controller
@RequestMapping("/edifici/")
public class TMEdificisController extends PantallaBaseController
{
	private static Log log = LogFactory.getLog(TMEdificisController.class);
	private Map<String, Object> resultats;
	
    @RequestMapping(value = "/edifici.do")
    public String pantalla(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMEdificis.jsp");
        
        String lang;
        try {
        	lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
        } catch (DelegateException dEx) {
        	log.error("No se ha encontrado el idioma por defecto");
        	lang = "ca";
        }
        
        RolUtil rolUtil= new RolUtil(request);
    	loadIndexModel (model, request);
		Boolean permisosSuperAddicionals = (Boolean) model.get("permisosSuperAddicionals");
        if (rolUtil.userIsAdmin() || permisosSuperAddicionals) {
        	 model.put("escriptori", "pantalles/taulesMestres/tmEdificis.jsp");
	       	 if (request.getSession().getAttribute("unidadAdministrativa")!=null){
	             model.put("idUA",((UnidadAdministrativa)request.getSession().getAttribute("unidadAdministrativa")).getId());
	             
	             model.put("nomUA",((UnidadAdministrativa)request.getSession().getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa(lang));
	    	 }
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistat(HttpServletRequest request, HttpSession session) {
	
		List<Map<String, Object>> llistaEdificiDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> edificiDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, String> tradMap = new HashMap<String, String>();
		Map<String, Object> model= new HashMap<String, Object>();
		
		String lang;
		try {
			lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
		} catch (DelegateException dEx) {
			log.error("Idioma por defecto no encontrado");
			lang = "ca";
		}
		
		Long idUABase=null;
		
    	loadIndexModel (model, request);
		Boolean permisosSuperAddicionals = (Boolean) model.get("permisosSuperAddicionals");
		
		// Parametres de cerca
		
		String direccio = request.getParameter("direccio");
		if (direccio != null && !"".equals(direccio)) {
			paramMap.put("direccion", direccio.toUpperCase());
		}
		
		String codiPostal = request.getParameter("codiPostal");
		if (codiPostal != null && !"".equals(codiPostal)) {
			paramMap.put("codigoPostal", codiPostal.toUpperCase());
		}
		
		String poblacio = request.getParameter("poblacio");
		if (poblacio != null && !"".equals(poblacio)) {
			paramMap.put("poblacion", poblacio.toUpperCase());
		}
		
		String telefon = request.getParameter("telefon");
		if (telefon != null && !"".equals(telefon)) {
			paramMap.put("telefono", telefon.toUpperCase());
		}
		
		String fax = request.getParameter("fax");
		if (fax != null && !"".equals(fax)) {
			paramMap.put("fax", fax.toUpperCase());
		}
		
		String email = request.getParameter("email");
		if (email != null && !"".equals(email)) {
			paramMap.put("email", email.toUpperCase());
		}
		
		boolean uaFilles = "1".equals(request.getParameter("uaFilles"));
		boolean uaMeves = "1".equals(request.getParameter("uaMeves"));	
		
		String idUA = request.getParameter("idUA");
		if (idUA != null && !"".equals(idUA)) {
			idUABase=Long.valueOf(idUA);
		}else if (permisosSuperAddicionals) { // Només mostra els edificis de les UAs autoritzades a l'usuari
			uaFilles=true;
    		uaMeves=true;
		}
			
		// Textes (en todos los campos todos los idiomas)
		String descripcio = request.getParameter("descripcio");
		if (descripcio != null && !"".equals(descripcio)) {
			descripcio = descripcio.toUpperCase();
			tradMap.put("descripcion", descripcio);
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

			EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
			resultadoBusqueda = edificiDelegate.buscarEdificios(paramMap, tradMap, idUABase, uaFilles, uaMeves, pagPag, pagRes);
			
			for (Edificio edf : castList(Edificio.class, resultadoBusqueda.getListaResultados() ) ) {
                TraduccionEdificio tedf = (TraduccionEdificio) edf.getTraduccion(lang);
				edificiDTO = new HashMap<String, Object>();
				edificiDTO.put("id", edf.getId());
				edificiDTO.put("direccio", edf.getDireccion());
				edificiDTO.put("descripcio", tedf == null ? null : tedf.getDescripcion());
				
				llistaEdificiDTO.add(edificiDTO);
            }
			resultats.put("total", resultadoBusqueda.getTotalResultados());
			resultats.put("nodes", llistaEdificiDTO);
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
                resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
                resultats.put("id", -1);
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		return resultats;
	}
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request)
	{
    	Map<String, Object> resultats = new HashMap<String, Object>();
    	try {
	        Long id = new Long(request.getParameter("id"));
	        
	        EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
	        Edificio edifici = edificiDelegate.obtenerEdificio(id);
	        
	        resultats.put("item_id", edifici.getId());
	        resultats.put("item_direccio", edifici.getDireccion());
	        resultats.put("item_codi_postal", edifici.getCodigoPostal());
	        resultats.put("item_poblacio", edifici.getPoblacion());
	        
	        resultats.put("item_latitud", edifici.getLatitud());
	        resultats.put("item_longitud", edifici.getLongitud());
	        
	        resultats.put("item_telefon", edifici.getTelefono());
	        resultats.put("item_fax", edifici.getFax());
	        resultats.put("item_email", edifici.getEmail());
	        
	        if (edifici.getFotoPequenya() != null) {
            	resultats.put("item_foto_petita_enllas_arxiu", "edifici/archivo.do?id=" + edifici.getId() + "&tipus=1");
                resultats.put("item_foto_petita", edifici.getFotoPequenya().getNombre());
            } else {
            	resultats.put("item_foto_petita_enllas_arxiu", "");
                resultats.put("item_foto_petita", "");
            }
            if (edifici.getFotoGrande() != null) {
            	resultats.put("item_foto_gran_enllas_arxiu", "edifici/archivo.do?id=" + edifici.getId() + "&tipus=2");
                resultats.put("item_foto_gran", edifici.getFotoGrande().getNombre());
            } else {
                resultats.put("item_foto_gran_enllas_arxiu", "");
                resultats.put("item_foto_gran", "");
            } 
            if (edifici.getPlano() != null) {
            	resultats.put("item_planol_enllas_arxiu", "edifici/archivo.do?id=" + edifici.getId() + "&tipus=3");
                resultats.put("item_planol", edifici.getPlano().getNombre());
            } else {
                resultats.put("item_planol_enllas_arxiu", "");
                resultats.put("item_planol", "");
            }
            
            omplirCampsTraduibles(resultats, edifici);
            
//          String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
//          Unitats Administratives asociadas
//          if (edifici.getUnidadesAdministrativas() != null) {
//          	Map<String, String> map;
//          	List<Map<String, String>> llistaUnitatsAdministratives = new ArrayList<Map<String, String>>();
//          	TraduccionUA traUA;
//				String nombre;
//				
//				for (Iterator it = edifici.getUnidadesAdministrativas().iterator(); it.hasNext();) {
//					UnidadAdministrativa unitatAdministrativa = (UnidadAdministrativa) it.next();
//					traUA = (TraduccionUA) unitatAdministrativa.getTraduccion(lang);
//					nombre = "";
//  				if (traUA != null) {
//  					//Retirar posible enlace incrustado en titulo
//  					nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(traUA.getNombre());
//  				}
//  				map = new HashMap<String, String>(2);
//  				map.put("id", unitatAdministrativa.getId().toString());
//  				map.put("nombre", nombre);
//                  llistaUnitatsAdministratives.add(map);
//				}
//				resultats.put("unitatsAdm", llistaUnitatsAdministratives);
//          } else {
//              resultats.put("unitatsAdm", null);
//          }
            // Fin unitatsAdm asociadas
            
        } catch (DelegateException dEx) {
			log.error(ExceptionUtils.getStackTrace(dEx));
			if (dEx.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
		}
    	
    	return resultats;
	}
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, Edificio edifici) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null!=edifici.getTraduccion(lang)) {
				resultats.put(lang, (TraduccionEdificio) edifici.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionEdificio());
			}
		}
	}
    
    @RequestMapping(value = "/guardar.do", method = POST)
    public ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request)
    {
    	/**
		 * Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    	
    	IdNomDTO result = null;
    	
    	Map<String, String> valoresForm = new HashMap<String, String>();
    	Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
    	
    	try {
    		// Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
    		// Iremos recopilando los parémetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
    		List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);
    		for (FileItem item : items) {
    			if (item.isFormField())
    				valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
    			else
    				ficherosForm.put(item.getFieldName(), item);
    		}
    		
    		Edificio edifici = guardarEdificiRecuperar(valoresForm);	// Recuperamos o creamos un edificio nuevo si no existe
    		// Campos obligatorios
    		String direccio = valoresForm.get("item_direccio");
    		if (direccio == null || "".equals(direccio)) {
    			String error = messageSource.getMessage("edifici.formulari.error.falten_camps", null, request.getLocale());
    			result = new IdNomDTO(-3l, error);
    			return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    		}
    		edifici.setDireccion(direccio);
    		// Recumeramos los campos directamente del form
    		if (valoresForm.get("item_latitud").length() < 15)
    			edifici.setLatitud(valoresForm.get("item_latitud"));
    		else
    			edifici.setLatitud(valoresForm.get("item_latitud").substring(0,14));
    		
    		if (valoresForm.get("item_longitud").length() < 15)
    			edifici.setLongitud(valoresForm.get("item_longitud"));
    		else
    			edifici.setLongitud(valoresForm.get("item_longitud").substring(0, 14));
    		
    		edifici.setCodigoPostal(valoresForm.get("item_codi_postal"));
    		edifici.setPoblacion(valoresForm.get("item_poblacio"));
    		edifici.setTelefono(valoresForm.get("item_telefon"));
    		edifici.setFax(valoresForm.get("item_fax"));
    		edifici.setEmail(valoresForm.get("item_email"));
    		
    		edifici = guardarEdificisFileItem(valoresForm, ficherosForm, "item_foto_petita", edifici);	// Recuperamos la foto pequeña
    		edifici = guardarEdificisFileItem(valoresForm, ficherosForm, "item_foto_gran", edifici);	// Recuperamos la foto grande
    		edifici = guardarEdificisFileItem(valoresForm, ficherosForm, "item_planol", edifici);		// Recuperamos el planol
    		edifici = guardarEdificiTraduccions(valoresForm, edifici);
    		
    		// Unitats Administratives
    		if (valoresForm.get("unitatsAdministratives") != null && !"".equals(valoresForm.get("unitatsAdministratives"))) {
    			edifici = guardarEdificiUAs(valoresForm, edifici);										// Recuperamos las UAs
    		} else {
    			String error = messageSource.getMessage("edifici.formulari.unitat_administrativa.minim", null, request.getLocale());
    			result = new IdNomDTO(-3l, error);
    			return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    		}
    		
    		EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
    		edificiDelegate.grabarEdificio(edifici);													// Se guarda el edicicio en bbdd
    		
    		String ok = messageSource.getMessage("administracioRemota.guardat.correcte", null, request.getLocale());
    		result = new IdNomDTO(edifici.getId(), ok);
    		
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
    	} catch (FileUploadException e) {
    		String error = messageSource.getMessage("error.fitxer.tamany", null, request.getLocale());
    		result = new IdNomDTO(-3l, error);
    		log.error(ExceptionUtils.getStackTrace(e));;
    	}
    	
    	return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
    }
    
    /*
	 * Recuperamos el edificio o creamos uno nuevo si no existe
	 */
	private Edificio guardarEdificiRecuperar(Map<String, String> valoresForm) throws DelegateException
	{
		EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
		Long id = ParseUtil.parseLong(valoresForm.get("item_id"));
		if (id != null)
			return edificiDelegate.obtenerEdificio(id);
		else
			return new Edificio();
	}
	
	/*
	 * Función para recuperar los FIleItems
	 */
	private Edificio guardarEdificisFileItem(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm, String parametro, Edificio edifici)
	{
		FileItem fileItem = ficherosForm.get(parametro);
		if (fileItem.getSize() > 0){	
			if(parametro.equals("item_foto_petita")){
				edifici.setFotoPequenya(UploadUtil.obtenerArchivo(edifici.getFotoPequenya(), fileItem));
			}else if(parametro.equals("item_foto_gran")){
				edifici.setFotoGrande(UploadUtil.obtenerArchivo(edifici.getFotoGrande(), fileItem));
			}else if(parametro.equals("item_planol")){				
				edifici.setPlano(UploadUtil.obtenerArchivo(edifici.getPlano(), fileItem));
			}
		}
		else if (valoresForm.get(parametro + "_delete") != null && !"".equals(valoresForm.get(parametro + "_delete"))){ //borrar fichero si se solicita
			if(parametro.equals("item_foto_petita")){
				edifici.setFotoPequenya(null);
			}else if(parametro.equals("item_foto_gran")){
				edifici.setFotoGrande(null);
			}else if(parametro.equals("item_planol")){				
				edifici.setPlano(null);
			}
		}
		
		return edifici;
	}
	
	/*
	 * Función para recuperar las traducciones de los edificios
	 */
	private Edificio guardarEdificiTraduccions(Map<String, String> valoresForm, Edificio edifici) throws DelegateException
	{
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		Map traduccions = new HashMap(langs.size());
		for (String lang: langs) {
			TraduccionEdificio tp = new TraduccionEdificio();
			tp.setDescripcion( RolUtil.limpiaCadena(valoresForm.get("item_descripcio_"+  lang )) );
			traduccions.put(lang, tp);
		}
		edifici.setTraduccionMap(traduccions);
		return edifici;
	}
	
	/*
     * Función para controlar las UAs de los edificios
     */
    private Edificio guardarEdificiUAs(Map<String, String> valoresForm, Edificio edifici) throws DelegateException
    {
    	UnidadAdministrativaDelegate unitatAdminsitrativaDelegate = DelegateUtil.getUADelegate();
    	Set<UnidadAdministrativa> unitatAdmNoves = new HashSet<UnidadAdministrativa>();
    	String[] codisUANous = valoresForm.get("unitatsAdministratives").split(",");
    	
    	if (edifici.getId() != null) {
    		for (int i = 0; i<codisUANous.length; i++) {
    			for (Iterator it = edifici.getUnidadesAdministrativas().iterator(); it.hasNext();) {
    				UnidadAdministrativa unitatAdministrativa = (UnidadAdministrativa) it.next();
    				if (unitatAdministrativa.getId().equals(ParseUtil.parseLong(codisUANous[i]))) {
    					unitatAdmNoves.add(unitatAdministrativa);
    					codisUANous[i] = null;
    					break;
    				}
    			}
    		}
    	}
    	for (String codiUA: codisUANous) {
    		if (codiUA != null)
    			unitatAdmNoves.add(unitatAdminsitrativaDelegate.obtenerUnidadAdministrativa(ParseUtil.parseLong(codiUA)));
    	}
    	edifici.setUnidadesAdministrativas(unitatAdmNoves);
    	
    	return edifici;
    }
    
    
    @RequestMapping(value = "/esborrarEdifici.do", method = POST)
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
			edificiDelegate.borrarEdificio(id);
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
			log.error("Error: Id de pefil no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
    
    @RequestMapping(value = "/traduir.do")
	public @ResponseBody Map<String, Object> traduir(HttpServletRequest request)
	{
		Map<String, Object> resultats = new HashMap<String, Object>();
		
		try {
			String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
			
			TraduccionEdificio traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
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
			log.error("EspaiTerritorialBackController.traduir: El traductor no se encuentra en en contexto.");
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		} catch (Exception e) {
			log.error("EspaiTerritorialBackController.traduir: Error en al traducir Espai Territorial: " + e);
			resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
		}
		
		return resultats;
	}
	
	
    private TraduccionEdificio getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor)
	{
    	TraduccionEdificio traduccioOrigen = new TraduccionEdificio();
		
		if (StringUtils.isNotEmpty(request.getParameter("item_descripcio_" + idiomaOrigenTraductor))) {
			traduccioOrigen.setDescripcion(request.getParameter("item_descripcio_" + idiomaOrigenTraductor));
		}
		
		return traduccioOrigen;
	}
    
	@RequestMapping(value = "/modulos.do")
	public @ResponseBody Map<String, Object> recuperaModulos(Long id, HttpServletRequest request) {
	
		resultats = new HashMap<String, Object>();
		try {
			
			String lang = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();
		        
		     EdificioDelegate edificiDelegate = DelegateUtil.getEdificioDelegate();
		     Edificio edifici = edificiDelegate.obtenerEdificio(id);
	        // Unitats Administratives asociadas
	        if (edifici.getUnidadesAdministrativas() != null) {
	        	Map<String, String> map;
	        	List<Map<String, String>> llistaUnitatsAdministratives = new ArrayList<Map<String, String>>();
	        	TraduccionUA traUA;
				String nombre;
				
				for (Iterator it = edifici.getUnidadesAdministrativas().iterator(); it.hasNext();) {
					UnidadAdministrativa unitatAdministrativa = (UnidadAdministrativa) it.next();
					traUA = (TraduccionUA) unitatAdministrativa.getTraduccion(lang);
					nombre = "";
					if (traUA != null) {
						//Retirar posible enlace incrustado en titulo
						nombre = HtmlUtils.obtenerTituloDeEnlaceHtml(traUA.getNombre());
					}
					map = new HashMap<String, String>(2);
					map.put("id", unitatAdministrativa.getId().toString());
					map.put("nombre", nombre);
	                llistaUnitatsAdministratives.add(map);
				}
				resultats.put("unitatsAdm", llistaUnitatsAdministratives);
	        } else {
	            resultats.put("unitatsAdm", null);
	        }
	        // Fin unitatsAdm asociadas
			
		} catch (DelegateException e) {
	
			log.error(ExceptionUtils.getStackTrace(e));
	
			if (e.isSecurityException())
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			else
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
	
		}
	
		return resultats;
	
	}
}
//public ActionForward unidades(ActionMapping mapping, ActionForm form,
//        HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//	log.debug("Entramos en unidades");
//	EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
//
//	if (request.getParameter("edificio") != null && request.getParameter("unidad") != null){
//		Long idEdi = new Long(request.getParameter("edificio"));
//		Long idUnidad = new Long(request.getParameter("unidad"));
//
//		if ("alta".equals(request.getParameter("operacion")))
//			edificioDelegate.anyadirUnidad(idUnidad, idEdi);
//		if ("baja".equals(request.getParameter("operacion")))
//    		edificioDelegate.eliminarUnidad(idUnidad, idEdi);
//
//		request.setAttribute("idSelect", idEdi);
//		return dispatchMethod(mapping, form, request, response, "seleccionar");
//		
//	}
//	
//	return mapping.findForward("cancel");
//}
