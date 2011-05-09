package org.ibit.rol.sac.back.action.contenido.ficha;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.FichaForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.*;
import org.ibit.rol.sac.persistence.delegate.*;

import org.ibit.rol.sac.back.utils.Parametros;
 
import es.indra.rol.sac.integracion.traductor.Traductor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Action para editar una Ficha  (PORMAD)
 *
 * @struts.action
 *  name="fichaForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/ficha/editar"
 *  input=".contenido.ficha.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="fichaForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/ficha/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.ficha.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/contenido/ficha/form.do"
 *
 * @struts.action-forward
 *  name="lista" path=".contenido.ficha.lista"
 *
 * @struts.action-forward
 *  name="eliminar" path=".contenido.ficha.baja"
 *
 * @struts.action-forward
 *  name="seccion" path="/sistema/seccion/seleccionar.do"
 */
public class EditarFichaAction extends BaseDispatchAction{
    protected static Log log = LogFactory.getLog(EditarFichaAction.class);

    protected Map<String,String> getKeyMethodMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.insertar","editar");
        map.put("boton.modificar","editar");
        map.put("boton.traducir", "editar");
        map.put("boton.busqueda","busqueda");
        map.put("boton.seleccionar","seleccionar");
        map.put("boton.relacionar","relacionar");
        map.put("boton.eliminar","eliminar");
        map.put("boton.cancelar","cancelar");
        map.put("ficha.relacion.documentos", "documento");
        map.put("ficha.relacion.enlaces", "enlace");
        map.put("ficha.relacion.materias", "materia");
        map.put("ficha.relacion.hechovital", "hechovital");
        map.put("ficha.relacion.ua", "fichaUA");
        map.put("boton.busqueda.huerfanas", "huerfanas");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
    	
        log.info("entramos en editar");
 
        FichaForm dForm = (FichaForm) form;
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        Ficha ficha = null;
      
        try {

        

            if ((Long) dForm.get("id") == null) {
            ficha = new Ficha();
            	if (!fichaDelegate.autorizaCrearFicha((Integer) dForm.get("validacion")))
            		throw new SecurityException("Aviso: No tiene privilegios para crear una ficha pública");
        } else {
            	ficha = fichaDelegate.obtenerFicha((Long) dForm.get("id"));
            	if (!fichaDelegate.autorizaModificarFicha(ficha.getId()))
            		throw new SecurityException("Aviso: No tiene privilegios para modificar la ficha");
        }
	        
		        if ((System.getProperty("es.indra.caib.rolsac.oficina") == null) 
		        		|| System.getProperty("es.indra.caib.rolsac.oficina").equals("N")) 
		        		request.setAttribute("host", System.getProperty("es.caib.rolsac.portal.url"));
		        	else request.setAttribute("host", "http://"+request.getServerName()+":"+request.getServerPort());
		 
        VOUtils.populate(ficha, dForm);
		        
        ficha.setFechaPublicacion(dForm.getFechaPublicacion());        
        ficha.setFechaCaducidad(dForm.getFechaCaducidad());
        ficha.setFechaActualizacion(dForm.getFechaActualizacion());
        ficha.setUrlVideo((String) dForm.get("urlVideo"));
        if(mostrarForo()){
            ficha.setUrlForo((String) dForm.get("urlForo"));
            request.setAttribute("mostrarForo", "mostrarForo");
        }
        
		        if (archivoValido(((FormFile) dForm.get("fileicono")))){
		        	if (((FormFile) dForm.get("fileicono")).getFileSize()<=204800) 
		        		ficha.setIcono(populateArchivo(ficha.getIcono(), (FormFile) dForm.get("fileicono")));
		        	  else request.setAttribute("alert_ico", "ficha.alert_ico");
		        } else if (((Boolean) dForm.get("borraricono")).booleanValue()) ficha.setIcono(null);

		        if (ficha.getIcono() != null) dForm.set("nombreicono", ficha.getIcono().getNombre());
		            else dForm.set("nombreicono", null); 


		        if (archivoValido(((FormFile) dForm.get("filebanner")))){
		        	if (((FormFile) dForm.get("filebanner")).getFileSize()<=204800) 
		        		ficha.setBaner(populateArchivo(ficha.getBaner(), (FormFile) dForm.get("filebanner")));
		        	  else request.setAttribute("alert_ban", "ficha.alert_ban");
		        } else if (((Boolean) dForm.get("borrarbanner")).booleanValue()) ficha.setBaner(null);

		        if (ficha.getBaner() != null) dForm.set("nombrebanner", ficha.getBaner().getNombre());
		        	else dForm.set("nombrebanner", null);


		        if (archivoValido(((FormFile) dForm.get("fileimagen")))){
		        	if (((FormFile) dForm.get("filebanner")).getFileSize()<=204800) 
		        		ficha.setImagen(populateArchivo(ficha.getImagen(), (FormFile) dForm.get("fileimagen")));
		        	  else request.setAttribute("alert_img", "ficha.alert_img");
		        } else if (((Boolean) dForm.get("borrarimagen")).booleanValue()) ficha.setImagen(null);

		        if (ficha.getImagen() != null) dForm.set("nombreimagen", ficha.getImagen().getNombre());
		        	else dForm.set("nombreimagen", null);		

        
		        if (request.getParameter("action").equals(getResources(request).getMessage(request.getLocale(), "boton.traducir"))) {
        
		        	traducir (request, dForm);
		        	dForm.set("id", ficha.getId());
            request.setAttribute("materiaOptions", ficha.getMaterias());
		         	request.setAttribute("hechovitalOptions", ficha.getHechosVitales());
            request.setAttribute("fichaUAOptions", ficha.getFichasua());
					request.setAttribute("documentoOptions",ficha.getDocumentos());  
		            request.setAttribute("enlaceOptions", ficha.getEnlaces()); 
			       	request.setAttribute("fichaForm", dForm);
			       	
			        return mapping.findForward("success");

        } else {
		        	
		        	fichaDelegate.grabarFicha(ficha);
		           
		        	if (request.getParameter("action").equals(getResources(request).getMessage(request.getLocale(), "boton.modificar"))) {
		        		
			        	request.setAttribute("alert", "confirmacion.modificacion");
			        	request.setAttribute("idSelect", ficha.getId());
			            log.info("Modificada ficha - Id: " + ficha.getId());
			        	return dispatchMethod(mapping, form, request, response, "seleccionar");
			            
		        	} else {
			        	
            if (request.getParameter("idSeccion") != null){
                Long idSeccion = new Long(request.getParameter("idSeccion"));
                //fichaDelegate.crearFichaUA(null, idSeccion, ficha.getId());
                // Una ficha se debe relacionar con la unidad administrativa raiz, en este caso se debe configurar
                // el codigo estandar, por tanto primero se comprueba que exista dicho codigo estandar.
                String codiEstandarUAArrel = System.getProperty("es.caib.rolsac.codiEstandarUAArrel");
                if(codiEstandarUAArrel != null){
                    UnidadAdministrativa ua = uaDelegate.obtenerUnidadAdministrativaPorCodEstandar(codiEstandarUAArrel);
                    fichaDelegate.crearFichaUA(ua.getId(), idSeccion, ficha.getId());
                }
                request.setAttribute("idSeccion", idSeccion);
            }
		        		
		        		request.setAttribute("alert", "confirmacion.alta");
			        	request.setAttribute("idSelect", ficha.getId());
			        	log.info("Creada ficha nueva - Id: " + ficha.getId());			        	
			        	return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

		        } 

		       // return mapping.findForward("success");
        
        } catch (SecurityException e) {
	    	
            if ((Long) dForm.get("id") == null) request.setAttribute("alert", "ficha.aviso.privilegio.insertar");
            	else request.setAttribute("alert", "ficha.aviso.privilegio.modificar");

        	request.setAttribute("idSelect", ficha.getId());
        	return dispatchMethod(mapping, form, request, response, "seleccionar");
	        
	    }
    }
    
    /**
     * Método que traduce un formulario de Ficha
     * @author Indra
     * @param request	petición de usuario
     * @param dForm		formulario dinámico enviado por usuario
     * @throws Exception
     */
    private void traducir (HttpServletRequest request, FichaForm dForm) throws Exception  {	

    		Traductor traductor = (Traductor) request.getSession().getAttribute("traductor");
    		String idiomaOrigen = "ca";

            TraduccionFicha fichaOrigen = (TraduccionFicha) dForm.get("traducciones", 0);

            Iterator itTradFichas = ((ArrayList) dForm.get("traducciones")).iterator();                
            Iterator<String> itLang = traductor.getListLang().iterator(); 
            
            while (itLang.hasNext()){
                
            	TraduccionFicha fichaDesti = (TraduccionFicha) itTradFichas.next();
            	String idiomaDesti = itLang.next();
            	
            	if (!idiomaOrigen.equals(idiomaDesti)) 
            	{
            		traductor.setDirTraduccio(idiomaOrigen, idiomaDesti);
            		
            		if (traductor.traducir(fichaOrigen, fichaDesti)) request.setAttribute("alert", "traduccion.confirmacion"); 
            		else {
            			request.setAttribute("alert", "traduccion.error");
            			break;
            		}
            		}
            	}
           
            log.info("Traducción ficha - Id: " + (Long) dForm.get("id"));
    }
     
     
    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{

        log.info("entramos en busqueda");

     
        String servidor="";
        String value = System.getProperty("es.indra.caib.rolsac.oficina");
        ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
        
        if ((value == null) || value.equals("N"))
        	servidor = System.getProperty("es.caib.rolsac.portal.url");
        else
        	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
    	request.setAttribute("host", servidor);
        FichaForm dForm = (FichaForm)form;
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        List fichas;

        if (request.getParameter("materia") != null){
            Long idMateria = new Long(request.getParameter("materia"));
            log.info("busqueda por materia : " + idMateria);
            fichas = fichaDelegate.buscarFichasMateria(idMateria);

        } else if (request.getParameter("hechovital") != null){
            Long idHechovital = new Long(request.getParameter("hechovital"));
            log.info("busqueda por hechovital : " + idHechovital);
            fichas = fichaDelegate.buscarFichasHechoVital(idHechovital);

        } else {

            Map paramMap = new HashMap();
            Map tradMap = new HashMap();

            Iterator itTrad = ((ArrayList) dForm.get("traducciones")).iterator();
            Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

            // Parámetros generales
            if (request.isUserInRole("sacoper")){
                //paramMap.put("validacion", Validacion.INTERNA);
            	paramMap.put("validacion", "");
            } else {
                paramMap.put("validacion", dForm.get("validacion"));
            }
            paramMap.put("fechaPublicacion", dForm.getFechaPublicacion());
            paramMap.put("fechaCaducidad", dForm.getFechaCaducidad());
            
            paramMap.put("fechaActualizacion", dForm.getFechaActualizacion());            
            if (dForm.get("urlVideo").toString().length() > 0) paramMap.put("urlVideo", dForm.get("urlVideo").toString().toUpperCase());
            if(mostrarForo()){
                if (dForm.get("urlForo").toString().length() > 0) paramMap.put("urlForo", dForm.get("urlForo").toString().toUpperCase());
            }

            // Parámetros traducibles
            tradMap.put("idioma", idiomaDelegate.lenguajePorDefecto());
            while (itLang.hasNext()){
                TraduccionFicha tFicha = (TraduccionFicha) itTrad.next();
                int x = 0;
                x += tFicha.getTitulo().length();
                x += tFicha.getDescAbr().length();
                x += tFicha.getDescripcion().length();
                x += tFicha.getUrl().length();
                if (x > 0){
                    tradMap.put("idioma", itLang.next().toString());
                    tradMap.put("titulo", tFicha.getTitulo().toUpperCase());
                    tradMap.put("descAbr", tFicha.getDescAbr().toUpperCase());
                    tradMap.put("descripcion", tFicha.getDescripcion().toUpperCase());
                    tradMap.put("url", tFicha.getUrl().toUpperCase());
                    break;
                } else {
                    itLang.next();
                }
            }

            fichas = fichaDelegate.buscarFichas(paramMap, tradMap);
        }

        if (fichas.size() == 1) {
            request.setAttribute("idSelect", ((Ficha) fichas.get(0)).getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        } else {
            request.setAttribute("listaFichas", fichas);
        }

        return mapping.findForward("lista");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        FichaForm dForm = (FichaForm) form;
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();

        Long id = (Long) dForm.get("id");

        if(fichaDelegate.autorizaModificarFicha(id)) {
        log.info("Eliminada Ficha: " + id);
        fichaDelegate.borrarFicha(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

	        request.setAttribute("fichaForm", dForm);
        if (request.getParameter("idSeccion") != null){
            request.setAttribute("idSelect", new Long(request.getParameter("idSeccion")));
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
	            
            return mapping.findForward("seccion");
        }

        return mapping.findForward("cancel");
        
    	} else {
    		request.setAttribute("alert", "ficha.aviso.privilegio.eliminar");
    		request.setAttribute("idSelect", id);
    		return dispatchMethod(mapping, form, request, response, "seleccionar");
		}
        
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        
        String servidor="";
        String value = System.getProperty("es.indra.caib.rolsac.oficina");
        ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
        
        if ((value == null) || value.equals("N"))
			servidor = System.getProperty("es.caib.rolsac.portal.url");
        else
        	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
    	request.setAttribute("host", servidor);
  
        FichaForm dForm = (FichaForm) form;
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");


        if (id != null){
            Ficha ficha = fichaDelegate.obtenerFicha(id);
            VOUtils.describe(dForm, ficha);
            dForm.setFechaPublicacion(ficha.getFechaPublicacion());            
            dForm.setFechaCaducidad(ficha.getFechaCaducidad());
            dForm.setFechaActualizacion(ficha.getFechaActualizacion());  
            dForm.set("urlVideo",ficha.getUrlVideo());
            if(mostrarForo()){
                dForm.set("urlForo",ficha.getUrlForo());
                request.setAttribute("mostrarForo", "mostrarForo");
            }
            
            if (ficha.getIcono() != null)
                dForm.set("nombreicono",ficha.getIcono().getNombre());
            if (ficha.getBaner() != null)
                dForm.set("nombrebanner",ficha.getBaner().getNombre());
            if (ficha.getImagen() != null)
                dForm.set("nombreimagen", ficha.getImagen().getNombre());
            request.setAttribute("materiaOptions", ficha.getMaterias());
            request.setAttribute("hechovitalOptions", ficha.getHechosVitales());
            request.setAttribute("fichaUAOptions", ficha.getFichasua());
            request.setAttribute("documentoOptions", ficha.getDocumentos() );
            request.setAttribute("enlaceOptions", ficha.getEnlaces() );
   	  	
            if (request.getParameter("idSeccion") != null)
                request.setAttribute("idSeccion", request.getParameter("idSeccion"));

            if (request.getAttribute("idSelect") != null)
            	request.setAttribute("fichaForm", dForm);	

        } else {
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

    public ActionForward fichaUA(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en fichaUA");
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();

        if (request.getParameter("ficha") != null){
            Long idFicha = new Long(request.getParameter("ficha"));

            if (request.getParameter("seccion") != null){
                Long idSeccion = new Long(request.getParameter("seccion"));
                Long idUA = null;
                if (request.getParameter("ua") != null && request.getParameter("ua").length() > 0){
                    idUA = new Long(request.getParameter("ua"));
                }
                if (idUA != null) {
                	fichaDelegate.crearFichaUA(idUA, idSeccion, idFicha);
                	
                	String pidip = System.getProperty("es.caib.rolsac.pidip");
                	if(!((pidip == null) || pidip.equals("N"))) {
                		// Si se anyade una ficha a la seccion Actualidad, se añade tambien a Portada Actualidad (PIDIP)
	                    if (idSeccion.longValue()== new Long(Parametros.ESDEVENIMENTS).longValue())
	                    {  	//comprobamos  antes si ya exite la ficha en actualidad  en portada en cuyo caso no la insertamos para no duplicarla.
	                    	int existe=0;Long ficodi;Long codficha;
	                    	List listac = fichaDelegate.listarFichasSeccionTodas(new Long(Parametros.PORTADAS_ACTUALIDAD));
	                    	Iterator iter = listac.iterator();
	                    	while (iter.hasNext())
	                    	{
	                    		Ficha ficac=(Ficha)iter.next();
	                    		if((""+ficac.getId()).equals(""+idFicha))
	                    			existe=1;
	                    	}
	                    	if (existe==0)
	                    	fichaDelegate.crearFichaUA(idUA, new Long(Parametros.PORTADAS_ACTUALIDAD), idFicha);
	                    }
                	}
                }
            }

            if (request.getParameter("fichaUA") != null){
                Long idFichaUA = new Long(request.getParameter("fichaUA"));
                fichaDelegate.borrarFichaUA(idFichaUA);
            }

            request.setAttribute("idSelect", idFicha);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
    }

    public ActionForward materia(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en materia");
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();

        if (request.getParameter("ficha") != null && request.getParameter("materia") != null){
            Long idFicha = new Long(request.getParameter("ficha"));
            Long idMateria = new Long(request.getParameter("materia"));

            if ("alta".equals(request.getParameter("operacion")))
                fichaDelegate.anyadirMateria(idMateria, idFicha);
            if ("baja".equals(request.getParameter("operacion")))
                fichaDelegate.eliminarMateria(idMateria, idFicha);

            request.setAttribute("idSelect", idFicha);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
    }
    
    public ActionForward documento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

			log.info("Entramos en documento");
			DocumentoDelegate documentoDelegate = DelegateUtil.getDocumentoDelegate();
			
			Long idFicha = new Long(request.getParameter("ficha"));
			
			if ("actualizar_orden".equals(request.getParameter("operacion")))
			{
				HashMap params = new HashMap();
	            Enumeration names = request.getParameterNames();
	            while(names.hasMoreElements())
	            {
	               final String name = (String) names.nextElement();
	               final String vals[] = request.getParameterValues(name);
	               params.put(name, vals);
	            }
				documentoDelegate.actualizarOrdenDocs(params);				
	        }else {
	            return mapping.findForward("cancel");
	        }

			request.setAttribute("idSelect", idFicha);
			return dispatchMethod(mapping, form, request, response, "seleccionar");
	}

    public ActionForward enlace(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

			log.info("Entramos en enlace");
			EnlaceDelegate enlaceDelegate = DelegateUtil.getEnlaceDelegate();
			
			Long idFicha = new Long(request.getParameter("ficha"));
			
			if ("actualizar_orden".equals(request.getParameter("operacion")))
			{
				HashMap params = new HashMap();
	            Enumeration names = request.getParameterNames();
	            while(names.hasMoreElements())
	            {
	               final String name = (String) names.nextElement();
	               final String vals[] = request.getParameterValues(name);
	               params.put(name, vals);
	            }
	            enlaceDelegate.actualizarOrdenEnlaces(params);				
	        }else {
	            return mapping.findForward("cancel");
	        }

			request.setAttribute("idSelect", idFicha);
			return dispatchMethod(mapping, form, request, response, "seleccionar");
	}
    
    public ActionForward hechovital(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	
		log.info("Entramos en hechovital");
		FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
		
		if (request.getParameter("ficha") != null && request.getParameter("hechovital") != null){
			Long idFicha = new Long(request.getParameter("ficha"));
			Long idHechoVital = new Long(request.getParameter("hechovital"));
			
			if ("alta".equals(request.getParameter("operacion")))
				fichaDelegate.anyadirHechoVital(idHechoVital, idFicha);
			if ("baja".equals(request.getParameter("operacion")))
				fichaDelegate.eliminarHechoVital(idHechoVital, idFicha);
				
			request.setAttribute("idSelect", idFicha);
			return dispatchMethod(mapping, form, request, response, "seleccionar");
		}
		
		return mapping.findForward("cancel");
	}
    

    public ActionForward huerfanas(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en huerfanas");
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();

        List fichas = fichaDelegate.buscarFichasHuerfanas();
        if (fichas.size() == 1) {
            request.setAttribute("idSelect", ((Ficha) fichas.get(0)).getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        } else {
            request.setAttribute("listaFichas", fichas);
        }

         return mapping.findForward("lista");
    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        log.info("Entramos en cancelar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

        if (request.getParameter("idSeccion") != null){
            request.setAttribute("idSelect", new Long(request.getParameter("idSeccion")));
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
            return mapping.findForward("seccion");
        }

        return mapping.findForward("cancel");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }


      /**
     * Método que comprueba si hay que mostrar la url del Foro
     * @return boolean
     */
    private boolean mostrarForo(){
        String value = System.getProperty("es.caib.rolsac.foro");
        return !((value == null) || value.equals("N"));
    }
}
