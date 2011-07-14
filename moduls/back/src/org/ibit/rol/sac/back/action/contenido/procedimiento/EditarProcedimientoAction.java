package org.ibit.rol.sac.back.action.contenido.procedimiento;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.action.contenido.common.ActionParameters;
import org.ibit.rol.sac.back.action.contenido.common.BaseAction;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.persistence.delegate.*;
import org.ibit.rol.sac.model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
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
 *  TODO  (ejaen@dgtic)
 *  Esta clase era muy grande (aprox 640 lineas), 
 *  Se ha refactorizado parcialmente pues tiene demasiadas responsabilidades: 
 *  	- editar, borrar, seleccionar, traducir, ... 
 *  	- realiza las tareas de control y negocio 
 *  
 *  Las ventajas de esta refactorizacion serian:
 *  	- SRP -> hacer un cambio no afecta a toda la funcionalidad
 *  	- clases mas pequeñas -> mas testeables
 *  	- posibilidad de reutilizar metodos
 *  
 *   (ejaen@dgtic) Por el momento se ha refactorizado las actions editar, seleccionar, traducir
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
        map.put("boton.traducir", "traducir");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("procedimiento.relacion.normativas", "normativa");
        map.put("procedimiento.relacion.materias", "relacionarMateria");
        map.put("procedimiento.relacion.tramites", "tramite");
        map.put("procedimiento.relacion.documentos", "documento");
        
        return map;
    }

    
	// getters and setters

    //u92770[enric] afegit setters dels delegate per fer unit testing del metode editar() (Dependency Injection)
    ProcedimientoDelegate procedimientoDelegate;
	FamiliaDelegate familiaDelegate;
    IniciacionDelegate iniciacionDelegate;
    IdiomaDelegate idiomaDelegate;
    UnidadAdministrativaDelegate uaDelegate;
	private BaseAction baseProcedimentoAction;
	private SeleccionarProcedimientoAction seleccionarProcedimientoAction;
	private GuardarProcedimientoAction guardarProcedimientoAction;
	private TraducirProcedimientoAction traducirProcedimientoAction;
	
	
	public void setTraducirProcedimientoAction(
			TraducirProcedimientoAction traducirProcedimientoAction) {
		this.traducirProcedimientoAction = traducirProcedimientoAction;
	}

	private TraducirProcedimientoAction getTraducirProcedimientoAction() {
    	if(null==traducirProcedimientoAction) traducirProcedimientoAction=new TraducirProcedimientoAction();
		return traducirProcedimientoAction;
	}
	
	public GuardarProcedimientoAction getGuardarProcedimientoAction() {
    	if(null==guardarProcedimientoAction) guardarProcedimientoAction=new GuardarProcedimientoAction();
		return guardarProcedimientoAction;
	}
	public void setGuardarProcedimientoAction(
			GuardarProcedimientoAction guardarProcedimientoAction) {
		this.guardarProcedimientoAction = guardarProcedimientoAction;
	}


    public BaseAction getBaseProcedimentoAction() {
    	if(null==baseProcedimentoAction) baseProcedimentoAction=new BaseAction();
		return baseProcedimentoAction;
	}
	public void setBaseProcedimentoAction(
			BaseAction baseProcedimentoAction) {
		this.baseProcedimentoAction = baseProcedimentoAction;
	}
	public SeleccionarProcedimientoAction getSeleccionarProcedimientoAction() {
    	if (null==seleccionarProcedimientoAction) seleccionarProcedimientoAction = new SeleccionarProcedimientoAction();
    	return seleccionarProcedimientoAction;
	}
	public void setSeleccionarProcedimientoAction(SeleccionarProcedimientoAction seleccionarAction) {
		this.seleccionarProcedimientoAction = seleccionarAction;
	}

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

	private RelacionarMateriaAction relacionarMateriaAction; 

    public void setProcedimientoRelacionarMateriaAction(RelacionarMateriaAction relacionarMateriaAction) {
    	this.relacionarMateriaAction = relacionarMateriaAction;
    }

    public RelacionarMateriaAction getProcedimientoRelacionarMateriaAction() {
    	if(relacionarMateriaAction==null) 
    		relacionarMateriaAction=new RelacionarMateriaAction();
    	return relacionarMateriaAction;
    }


	// editar

	
	public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
 
		/*
		 * se ha refactorizado editar() por tener demasiadas responsabilidades
		 * - se ha dividido en editar y traducir
		 * - se ha separado la capa de controlador de negocio 
		 * - se ha separado la action de la capa de controlador 
		 */

        return getGuardarProcedimientoAction().guardar(this, new ActionParameters(mapping, form, request, response));
    }
	
	
	public ActionForward traducir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return getTraducirProcedimientoAction().traducir(new ActionParameters(mapping, form, request, response));
		

	}

	
    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        ProcedimientoForm dForm = (ProcedimientoForm) form;
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
        Long id = (Long) dForm.get("id");

        procedimientoDelegate.borrarProcedimiento(id);
        log.debug("Eliminado Procedimiento: " + id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);
        return mapping.findForward("cancel");
    } 

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        return getSeleccionarProcedimientoAction().seleccionar(mapping, form, request);
    }
    
	
    
    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en busqueda");
        String servidor="";
        String value = System.getProperty("es.indra.caib.rolsac.oficina");
        ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
        
        if ((value == null) || value.equals("N"))
        	servidor=System.getProperty("es.caib.rolsac.portal.url");
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

			log.debug("Entramos en documento");
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
    public ActionForward relacionarMateria(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        return getProcedimientoRelacionarMateriaAction().relacionarMateria(this, new ActionParameters(mapping, form, request, response));
    }


  

	public ActionForward normativa(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en normativa");
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

        log.debug("Entramos en cancelled");
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

			log.debug("Entramos en relacion de tramites");
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

        log.debug("Entramos en unspecified");

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
