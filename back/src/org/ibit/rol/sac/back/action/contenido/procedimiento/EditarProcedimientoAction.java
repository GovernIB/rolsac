package org.ibit.rol.sac.back.action.contenido.procedimiento;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.FichaForm;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.*;
import org.ibit.rol.sac.model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import es.indra.rol.sac.integracion.traductor.Traductor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * Action para editar un Procedimiento Local
 *
 * @struts.action
 *  name="procedimientoForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/procedimiento/editar"
 *  input=".contenido.procedimiento.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="procedimientoForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/procedimiento/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.procedimiento.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/contenido/procedimiento/form.do"
 *
 * @struts.action-forward
 *  name="lista" path=".contenido.procedimiento.lista"
 *
 * @struts.action-forward
 *  name="eliminar" path=".contenido.procedimiento.baja"
 *
 * @struts.action-forward
 *  name="popup" path=".contenido.procedimiento.popupnormativas"
 *  
 *  
 *  
 *  
 */
public class EditarProcedimientoAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarProcedimientoAction.class);

    /**
     * 	<input type="hidden" name="procedimiento" value='247' /> 
		<input type="hidden" name="action" value='Llistat de Documents Informatius Relacionats' />  --> action "documento" 
		<input type="hidden" name="operacion" value='actualizar_orden' />                   	
		<input type="submit" value="Reordenar" class="boton" /> 
			
     */
    protected Map<String,String> getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.traducir", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("procedimiento.relacion.normativas", "normativa");
        map.put("procedimiento.relacion.materias", "materia");
        map.put("procedimiento.relacion.tramites", "tramite");
        map.put("procedimiento.relacion.documentos", "documento");
        
        return map;
    }

    //u92770[enric] afegit setters dels delegate per fer unit testing del metode editar() (Dependency Injection)
    ProcedimientoDelegate procedimientoDelegate;
	FamiliaDelegate familiaDelegate;
    IniciacionDelegate iniciacionDelegate;
    IdiomaDelegate idiomaDelegate;
    UnidadAdministrativaDelegate uaDelegate;

    public void setProcedimientoDelegate(ProcedimientoDelegate delegate) {this.procedimientoDelegate=delegate;}
    public void setFamiliaDelegate(FamiliaDelegate familiaDelegate) {
		this.familiaDelegate = familiaDelegate;
	}
	public void setIniciacionDelegate(IniciacionDelegate iniciacionDelegate) {
		this.iniciacionDelegate = iniciacionDelegate;
	}
    public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
		this.idiomaDelegate = idiomaDelegate;
	}

    public UnidadAdministrativaDelegate getUaDelegate() {
		return uaDelegate;
	}
	public void setUaDelegate(UnidadAdministrativaDelegate uaDelegate) {
		this.uaDelegate = uaDelegate;
	}
    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        String servidor="";
        String value = System.getProperty("es.indra.caib.rolsac.oficina");
        ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
        
        if ((value == null) || value.equals("N"))
        	servidor=System.getProperty("es.caib.rolsac.portal.url");
        else //INDRA
        	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
    	request.setAttribute("host", servidor);
        ProcedimientoForm dForm = (ProcedimientoForm) form;
        ProcedimientoDelegate procedimientoDelegate = null!=this.procedimientoDelegate? this.procedimientoDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]
        FamiliaDelegate familiaDelegate = null!=this.familiaDelegate? this.familiaDelegate: DelegateUtil.getFamiliaDelegate();
        IniciacionDelegate iniacionDelegate = null!=this.iniciacionDelegate? this.iniciacionDelegate: DelegateUtil.getIniciacionDelegate();
        UnidadAdministrativaDelegate uaDelegate = null!=this.uaDelegate? this.uaDelegate: DelegateUtil.getUADelegate();
        IdiomaDelegate idiomaDelegate = null!=this.idiomaDelegate? this.idiomaDelegate: DelegateUtil.getIdiomaDelegate();
        ProcedimientoLocal procedimiento;
        
        Object o = dForm.get("id");
        boolean isNew = o==null || (o instanceof Long && ((Long)o)==0); 
        
        if (isNew) {
            procedimiento = new ProcedimientoLocal();
        } else { 
            procedimiento = procedimientoDelegate.consultarProcedimiento(((Long) dForm.get("id")));
        }

        VOUtils.populate(procedimiento, dForm,idiomaDelegate);  //u92770[enric] afegit metode VOUtils.populate testejable
        procedimiento.setFechaActualizacion(dForm.getFechaActualizacion());
        procedimiento.setFechaCaducidad(dForm.getFechaCaducidad());
        procedimiento.setFechaPublicacion(dForm.getFechaPublicacion());
        if((""+dForm.get("indicador").toString()).equals("on"))
        	procedimiento.setIndicador("1");
        else
        	procedimiento.setIndicador("0"); 
        
        if((dForm.get("ventana").toString()).equals("on"))
        	procedimiento.setVentanillaUnica("1");  //VUDS
        else
        	procedimiento.setVentanillaUnica("0"); 

        if (dForm.get("idFamilia") != null) {
            procedimiento.setFamilia(familiaDelegate.obtenerFamilia((Long) dForm.get("idFamilia")));
        }
        if (dForm.get("idIniciacion") != null) {
            procedimiento.setIniciacion(iniacionDelegate.obtenerIniciacion((Long) dForm.get("idIniciacion")));
        }

        if (request.getParameter("action").equals(getResources(request).getMessage(request.getLocale(), "boton.traducir"))) {

		        	traducir (request, dForm);
			        dForm.set("id", procedimiento.getId());
			        dForm.set("indicador",procedimiento.getIndicador());  //1
			        dForm.set("ventana",procedimiento.getVentanillaUnica()); //1
		            request.setAttribute("materiaOptions", procedimiento.getMaterias());
		            request.setAttribute("normativaOptions", procedimiento.getNormativas());
		            request.setAttribute("tramiteOptions", procedimiento.getTramites());
		            request.setAttribute("documentoOptions", procedimiento.getDocumentos());
			        request.setAttribute("procedimientoForm", dForm);
	                return mapping.findForward("success");
		 } 


        Long idUA = (Long) dForm.get("idUA");

        Long idUAResol = (Long) dForm.get("idUAResol");
        if(null!=idUAResol && 0!=idUAResol) {
        	UnidadAdministrativa uaResol = uaDelegate.obtenerUnidadAdministrativa(idUAResol);
        	procedimiento.setOrganResolutori(uaResol);
        }
        Long procid=procedimientoDelegate.grabarProcedimiento(procedimiento, idUA);
        procedimiento.setId(procid);
        /*
        procedimientoDelegate.grabarProcedimiento(procedimiento, idUA);
        */

        dForm.set("id", procedimiento.getId());
        log.info("Creat/Actualitzat " + procedimiento.getId());

        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
            request.setAttribute("idSelect", procedimiento.getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        return mapping.findForward("success");
    }


   /**
     * Método que traduce un formulario de Procedimiento
     * @author - Indra 
     * @param request - petición de usuario
     * @param dForm - formulario dinámico enviado por el usuario
     * @throws Exception
     */
    private void traducir (HttpServletRequest request, ProcedimientoForm dForm) throws Exception  {	

    	
		Traductor traductor = (Traductor) request.getSession().getAttribute("traductor");
		String idiomaOrigen = "ca";

        TraduccionProcedimientoLocal procOrigen = (TraduccionProcedimientoLocal)dForm.get("traducciones", 0);        	

        Iterator itTradprocs = ((ArrayList) dForm.get("traducciones")).iterator();                
        Iterator<String> itLang = traductor.getListLang().iterator(); 
        
        while (itLang.hasNext()){
            
        	TraduccionProcedimientoLocal procDesti = (TraduccionProcedimientoLocal) itTradprocs.next();
        	String idiomaDesti = itLang.next();
        	
        	if (!idiomaOrigen.equals(idiomaDesti)) 
        	{
        		traductor.setDirTraduccio(idiomaOrigen, idiomaDesti);
        		
        		if (traductor.traducir(procOrigen, procDesti)){
        			request.setAttribute("alert", "traduccion.confirmacion");
        			request.setAttribute("booleanCheckValidar", "false");
        		}else {
        			request.setAttribute("alert", "traduccion.error");
        			break;
        		}
        		
        	}
        }
        
        log.info("Traducción procedimiento - Id: " + (Long) dForm.get("id"));

    }    

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        ProcedimientoForm dForm = (ProcedimientoForm) form;
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
        Long id = (Long) dForm.get("id");

        procedimientoDelegate.borrarProcedimiento(id);
        log.info("Eliminado Procedimiento: " + id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);
        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        String servidor="";
        String value = System.getProperty("es.indra.caib.rolsac.oficina");
        ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
        
        if ((value == null) || value.equals("N"))
        	
        	servidor=System.getProperty("es.caib.rolsac.portal.url");
        else
        	servidor = "http://"+request.getServerName()+":"+request.getServerPort();

    	request.setAttribute("host", servidor);
        ProcedimientoForm dForm = (ProcedimientoForm) form;
        ProcedimientoDelegate procedimientoDelegate = null!=this.procedimientoDelegate? this.procedimientoDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]
        IdiomaDelegate idiomaDelegate = null!=this.idiomaDelegate? this.idiomaDelegate: DelegateUtil.getIdiomaDelegate();  //u92770[enric]
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            Procedimiento procedimiento = procedimientoDelegate.obtenerProcedimiento(id);
            if(procedimiento instanceof ProcedimientoRemotoAntiguo){
                return mapping.findForward("fail");
            }

            ProcedimientoLocal procedimientoLocal = (ProcedimientoLocal)procedimiento;
            VOUtils.describe(dForm, procedimientoLocal,idiomaDelegate);
            dForm.setFechaActualizacion(procedimientoLocal.getFechaActualizacion());
            dForm.setFechaCaducidad(procedimientoLocal.getFechaCaducidad());
            dForm.setFechaPublicacion(procedimientoLocal.getFechaPublicacion());
            dForm.set("indicador",procedimientoLocal.getIndicador());
            dForm.set("ventana",procedimientoLocal.getVentanillaUnica());
            if (procedimientoLocal.getUnidadAdministrativa() != null){
                dForm.set("idUA", procedimientoLocal.getUnidadAdministrativa().getId());
                String nombreUA=((TraduccionUA)procedimientoLocal.getUnidadAdministrativa().getTraduccion()).getNombre();
                //FIXME nombreUA=nombreUA.replaceAll("'","\\'");
                dForm.set("nombreUA", nombreUA);
            }
            if (procedimientoLocal.getFamilia() != null){
                dForm.set("idFamilia", procedimientoLocal.getFamilia().getId());
                dForm.set("nombreFamilia", ((TraduccionFamilia)procedimientoLocal.getFamilia().getTraduccion()).getNombre());
            }
            if (procedimientoLocal.getIniciacion() != null){
                dForm.set("idIniciacion", procedimientoLocal.getIniciacion().getId());
                dForm.set("nombreIniciacion", ((TraduccionIniciacion)procedimientoLocal.getIniciacion().getTraduccion()).getNombre());
            }
            
              if (request.getAttribute("idSelect") != null)
            	request.setAttribute("procedimientoForm", dForm);
            	
            request.setAttribute("materiaOptions", procedimientoLocal.getMaterias());
            request.setAttribute("normativaOptions", procedimientoLocal.getNormativas());
            request.setAttribute("tramiteOptions", procedimientoLocal.getTramites());
            request.setAttribute("documentoOptions", procedimientoLocal.getDocumentos());
            if (null!= procedimientoLocal.getOrganResolutori()){
                dForm.set("idUAResol", procedimientoLocal.getOrganResolutori().getId());
                String nombreUA=((TraduccionUA)procedimientoLocal.getOrganResolutori().getTraduccion()).getNombre();
                //FIXME nombreUA=nombreUA.replaceAll("'","\\'");
                dForm.set("nombreUAResol", nombreUA);
            }


        } else {
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }


    
    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en busqueda");
        String servidor="";
        String value = System.getProperty("es.indra.caib.rolsac.oficina");
        ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
        
        if ((value == null) || value.equals("N"))
        	servidor=System.getProperty("es.caib.01rolsac.portal.url");
        else
        	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
    	request.setAttribute("host", servidor);
        ProcedimientoForm dForm = (ProcedimientoForm)form;
        ProcedimientoDelegate procedimientoDelegate = null!=this.procedimientoDelegate? this.procedimientoDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]
        IdiomaDelegate idiomaDelegate = null!=this.idiomaDelegate? this.idiomaDelegate: DelegateUtil.getIdiomaDelegate();
        List<ProcedimientoLocal> listaProcedimientos; 
            Map paramMap = new HashMap();
            Map tradMap = new HashMap();

            Iterator itTrad = ((ArrayList) dForm.get("traducciones")).iterator();
            Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

            // Parámetros generales                                                   
            paramMap.put("signatura", dForm.get("signatura").toString().toUpperCase());

            if (request.isUserInRole("sacoper")){
                //paramMap.put("validacion", Validacion.INTERNA);
            	paramMap.put("validacion", "");
            } else {
                paramMap.put("validacion", dForm.get("validacion"));
            }
            if((""+dForm.get("indicador").toString()).equals("on"))
            	paramMap.put("indicador",1);
            if((""+dForm.get("ventana").toString()).equals("on"))
            	paramMap.put("ventana",1); 
            if (dForm.get("idIniciacion") != null)
            paramMap.put("iniciacion", dForm.get("idIniciacion"));
            
            paramMap.put("fechaCaducidad", dForm.getFechaCaducidad());
            paramMap.put("fechaPublicacion", dForm.getFechaPublicacion());
            paramMap.put("fechaActualizacion", dForm.getFechaActualizacion());
            
            //anyadido por vroca
            if(mostrarTramite()){
                paramMap.put("tramite", dForm.get("tramite"));
                paramMap.put("version", dForm.get("version"));
            }
            
            if (null!=dForm.get("idUA"))
            	paramMap.put("unidadAdministrativa",(Long) dForm.get("idUA"));

            if (null!=dForm.get("idFamilia"))
            	paramMap.put("familia",(Long) dForm.get("idFamilia"));

            // Parámetros traducibles
            tradMap.put("idioma", idiomaDelegate.lenguajePorDefecto());
            while (itLang.hasNext()){
                TraduccionProcedimientoLocal tProcedimiento = (TraduccionProcedimientoLocal) itTrad.next();
            	prepareProcedimiento(tProcedimiento);
                int x = 0;
                x += tProcedimiento.getNombre().length();
                x += tProcedimiento.getResumen().length();
                x += tProcedimiento.getDestinatarios().length();
                x += tProcedimiento.getLugar().length();
                x += tProcedimiento.getPlazos().length();
                x += tProcedimiento.getResolucion().length();
                x += tProcedimiento.getNotificacion().length();
                x += tProcedimiento.getRequisitos().length();
                x += tProcedimiento.getSilencio().length();
                x += tProcedimiento.getObservaciones().length();
                if (x > 0){
                    tradMap.put("idioma", itLang.next().toString());
                    tradMap.put("resumen", tProcedimiento.getResumen().toUpperCase());
                    tradMap.put("nombre", tProcedimiento.getNombre().toUpperCase());
                    tradMap.put("destinatarios", tProcedimiento.getDestinatarios().toUpperCase());
                    tradMap.put("requisitos", tProcedimiento.getRequisitos().toUpperCase());
                    tradMap.put("plazos", tProcedimiento.getPlazos().toUpperCase());
                    tradMap.put("resolucion", tProcedimiento.getResolucion().toUpperCase());
                    tradMap.put("notificacion", tProcedimiento.getNotificacion().toUpperCase());
                    tradMap.put("silencio", tProcedimiento.getSilencio().toUpperCase());
                    tradMap.put("observaciones", tProcedimiento.getObservaciones().toUpperCase());
                    tradMap.put("lugar", tProcedimiento.getLugar().toUpperCase());
                    break;
                } else {
                    itLang.next();
                }
            }

            listaProcedimientos = procedimientoDelegate.buscarProcedimientos(paramMap, tradMap);

        if (listaProcedimientos.size() == 1) {
            request.setAttribute("idSelect", ((ProcedimientoLocal) listaProcedimientos.get(0)).getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        } else {
            request.setAttribute("listaProcedimientos", listaProcedimientos);
        }

        return mapping.findForward("lista");

    }
            
            
    private void prepareProcedimiento(TraduccionProcedimientoLocal tProcedimiento) {
        if(null==tProcedimiento.getNombre()) tProcedimiento.setNombre("");
        if(null==tProcedimiento.getResumen()) tProcedimiento.setResumen(""); 
        if(null==tProcedimiento.getDestinatarios()) tProcedimiento.setDestinatarios("");
        if(null==tProcedimiento.getLugar()) tProcedimiento.setLugar("");
        if(null==tProcedimiento.getPlazos()) tProcedimiento.setPlazos("");
        if(null==tProcedimiento.getResolucion()) tProcedimiento.setResolucion("");
        if(null==tProcedimiento.getNotificacion()) tProcedimiento.setNotificacion("");
        if(null==tProcedimiento.getRequisitos()) tProcedimiento.setRequisitos("");
        if(null==tProcedimiento.getSilencio()) tProcedimiento.setSilencio("");
        if(null==tProcedimiento.getObservaciones()) tProcedimiento.setObservaciones("");  
	}
    
    /**
     * 
     * Metodo que realiza operaciones sobre el conjunto de documentos
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward documento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

			log.info("Entramos en documento");
			DocumentoDelegate documentoDelegate = DelegateUtil.getDocumentoDelegate();
			
			Long idProcedimiento = new Long(request.getParameter("procedimiento"));
			
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

			request.setAttribute("idSelect", idProcedimiento);
			return dispatchMethod(mapping, form, request, response, "seleccionar");
	}
    public ActionForward materia(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en materia");
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();

        if (request.getParameter("procedimiento") != null && request.getParameter("materia") != null){
            Long idProcedimiento = new Long(request.getParameter("procedimiento"));
            Long idMateria = new Long(request.getParameter("materia"));

            if ("alta".equals(request.getParameter("operacion")))
                procedimientoDelegate.anyadirMateria(idMateria, idProcedimiento);
            if ("baja".equals(request.getParameter("operacion")))
                procedimientoDelegate.eliminarMateria(idMateria, idProcedimiento);

            request.setAttribute("idSelect", idProcedimiento);
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        }

        return mapping.findForward("cancel");
    }

    public ActionForward normativa(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en normativa");
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();

        if (request.getParameter("procedimiento") != null && request.getParameter("normativa") != null){
            Long idProcedimiento = new Long(request.getParameter("procedimiento"));
            Long idNormativa = new Long(request.getParameter("normativa"));

            if ("alta".equals(request.getParameter("operacion")))
                procedimientoDelegate.anyadirNormativa(idNormativa, idProcedimiento);
            if ("baja".equals(request.getParameter("operacion")))
                procedimientoDelegate.eliminarNormativa(idNormativa, idProcedimiento);

            request.setAttribute("idSelect", idProcedimiento);
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        }

        return mapping.findForward("cancel");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        if (request.getAttribute("idSelect") != null){
            this.seleccionar(mapping, form, request, response);
        }
        ProcedimientoForm dForm = (ProcedimientoForm)form;
        dForm.reset(mapping, request);

        return mapping.findForward("cancel");
    }

    public ActionForward tramite(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

			log.info("Entramos en relacion de tramites");
			ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
			
			
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
				procDelegate.actualizarOrdenTramites(params);				
	        }else {
	            return mapping.findForward("cancel");
	        }

			Long idProcedimiento = new Long(request.getParameter("procedimiento"));
			request.setAttribute("idSelect", idProcedimiento);
			return dispatchMethod(mapping, form, request, response, "seleccionar");
	}
    
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");

        return null;
    }


      /**
     * Método que comprueba si hay que mostrar la les variables per al tramit de sistra
     * @return boolean
     */
    private boolean mostrarTramite(){
        String value = System.getProperty("es.caib.rolsac.tramite.sistra");
        return !((value == null) || value.equals("N"));
    }

}
