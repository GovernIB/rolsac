package org.ibit.rol.sac.back.action.contenido.procedimiento;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.*;
import org.ibit.rol.sac.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionServlet;

import es.caib.vuds.VentanillaUnica;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
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
 */
public class EditarProcedimientoActionMock extends BaseDispatchAction {
    protected static Log log = LogFactory.getLog(EditarProcedimientoActionMock.class);

	EditarProcedimientoAction action;

	
	protected Map getKeyMethodMap() {
			return action.getKeyMethodMap();
    }
	
	public ActionForward busqueda(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.busqueda(mapping, form, request, response);
	}

	public ActionForward cancelled(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.cancelled(mapping, form, request, response);
	}

	public ActionForward documento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.documento(mapping, form, request, response);
	}

	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("dins de procedimientoMock editar");
		
		return action.editar(mapping, form, request, response);
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.eliminar(mapping, form, request, response);
	}

	public boolean equals(Object obj) {
		return action.equals(obj);
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.execute(mapping, form, request, response);
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			ServletRequest request, ServletResponse response) throws Exception {
		return action.execute(mapping, form, request, response);
	}

	public ActionServlet getServlet() {
		return action.getServlet();
	}

	public int hashCode() {
		return action.hashCode();
	}

	public ActionForward materia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.materia(mapping, form, request, response);
	}

	public ActionForward normativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.normativa(mapping, form, request, response);
	}

	public ActionForward perform(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return action.perform(mapping, form, request, response);
	}

	public ActionForward perform(ActionMapping mapping, ActionForm form,
			ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		return action.perform(mapping, form, request, response);
	}

	public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.seleccionar(mapping, form, request, response);
	}

	public void setFamiliaDelegate(FamiliaDelegateImpl familiaDelegate) {
		action.setFamiliaDelegate(familiaDelegate);
	}

	public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
		action.setIdiomaDelegate(idiomaDelegate);
	}

	public void setIniciacionDelegate(IniciacionDelegateImpl iniciacionDelegate) {
		action.setIniciacionDelegate(iniciacionDelegate);
	}

	public void setProcedimientoDelegate(ProcedimientoDelegate delegate) {
		action.setProcedimientoDelegate(delegate);
	}

	public void setServlet(ActionServlet servlet) {
		action.setServlet(servlet);
	}

	public String toString() {
		return action.toString();
	}
/*
	public ActionForward traduir(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return action.traduir(mapping, form, request, response);
	}
*/	
	
/*	
    protected static Log log = LogFactory.getLog(EditarProcedimientoActionMock.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.traduir", "traduir");
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

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
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
        FamiliaDelegate familiaDelegate = null!=this.familiaDelegate? this.familiaDelegate: DelegateUtil.getFamiliaDelegate();
        IniciacionDelegate iniacionDelegate = null!=this.iniciacionDelegate? this.iniciacionDelegate: DelegateUtil.getIniciacionDelegate();
        ProcedimientoLocal procedimiento;
        if (dForm.get("id") != null) {
            procedimiento = procedimientoDelegate.consultarProcedimiento(((Long) dForm.get("id")));
        } else {
            procedimiento = new ProcedimientoLocal();
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

        Long idUA = (Long) dForm.get("idUA");

        Long procid=procedimientoDelegate.grabarProcedimiento(procedimiento, idUA);
        procedimiento.setId(procid);
        /*
        procedimientoDelegate.grabarProcedimiento(procedimiento, idUA);
        

        dForm.set("id", procedimiento.getId());
        log.info("Creat/Actualitzat " + procedimiento.getId());

 
        char accio='a';  //'a'lta, 'm'odificacio, 'b'aixa
        if(dForm.get("id") != null){
        	accio='m';
        }
        
        //u92770[enric] s'envien els tramits al servidor de vuds
        if("1".equals(procedimiento.getVentanillaUnica())) {
        	Set<Tramite> tramits = procedimiento.getTramites();
        	for(Tramite t : tramits) {
        		int codres = VentanillaUnica.enviarTramit(t,accio);	
        	}
        	
        }
       

        if('m'==accio){
            request.setAttribute("alert", "confirmacion.modificacion");
            request.setAttribute("idSelect", procedimiento.getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");
            
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        return mapping.findForward("success");
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
        	servidor=System.getProperty("es.caib.01rolsac.portal.url");
        else
        	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
    	request.setAttribute("host", servidor);
        ProcedimientoForm dForm = (ProcedimientoForm) form;
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
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
            VOUtils.describe(dForm, procedimientoLocal);
            dForm.setFechaActualizacion(procedimientoLocal.getFechaActualizacion());
            dForm.setFechaCaducidad(procedimientoLocal.getFechaCaducidad());
            dForm.setFechaPublicacion(procedimientoLocal.getFechaPublicacion());
            dForm.set("indicador",procedimientoLocal.getIndicador());
            dForm.set("ventana",procedimientoLocal.getVentanillaUnica());
            if (procedimientoLocal.getUnidadAdministrativa() != null){
                dForm.set("idUA", procedimientoLocal.getUnidadAdministrativa().getId());
                dForm.set("nombreUA", ((TraduccionUA)procedimientoLocal.getUnidadAdministrativa().getTraduccion()).getNombre());
            }
            if (procedimientoLocal.getFamilia() != null){
                dForm.set("idFamilia", procedimientoLocal.getFamilia().getId());
                dForm.set("nombreFamilia", ((TraduccionFamilia)procedimientoLocal.getFamilia().getTraduccion()).getNombre());
            }
            if (procedimientoLocal.getIniciacion() != null){
                dForm.set("idIniciacion", procedimientoLocal.getIniciacion().getId());
                dForm.set("nombreIniciacion", ((TraduccionIniciacion)procedimientoLocal.getIniciacion().getTraduccion()).getNombre());
            }
            request.setAttribute("materiaOptions", procedimientoLocal.getMaterias());
            request.setAttribute("normativaOptions", procedimientoLocal.getNormativas());
            request.setAttribute("tramiteOptions", procedimientoLocal.getTramites());
            request.setAttribute("documentoOptions", procedimientoLocal.getDocumentos());

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
        ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
        List listaProcedimientos;

        if (dForm.get("idUA") != null){
            Long idUA = (Long) dForm.get("idUA");
            listaProcedimientos = procedimientoDelegate.listarProcedimientosUA(idUA);

        } else if (dForm.get("idFamilia") != null){
            Long idFamilia = (Long) dForm.get("idFamilia");
            listaProcedimientos = procedimientoDelegate.buscarProcedimientosFamilia(idFamilia);
        } else {
            Map paramMap = new HashMap();
            Map tradMap = new HashMap();

            Iterator itTrad = ((ArrayList) dForm.get("traducciones")).iterator();
            Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

            // Parámetros generales
            paramMap.put("signatura", dForm.get("signatura").toString().toUpperCase());

            if (request.isUserInRole("sacoper")){
                paramMap.put("validacion", Validacion.INTERNA);
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
            paramMap.put("tramite", dForm.get("tramite"));
            paramMap.put("version", dForm.get("version"));
            

            // Parámetros traducibles
            tradMap.put("idioma", idiomaDelegate.lenguajePorDefecto());
            while (itLang.hasNext()){
                TraduccionProcedimientoLocal tProcedimiento = (TraduccionProcedimientoLocal) itTrad.next();
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
                x += tProcedimiento.getLugar().length();
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
        }

        if (listaProcedimientos.size() == 1) {
            request.setAttribute("idSelect", ((ProcedimientoLocal) listaProcedimientos.get(0)).getId());
            return dispatchMethod(mapping, form, request, response, "seleccionar");

        } else {
            request.setAttribute("listaProcedimientos", listaProcedimientos);
        }

        return mapping.findForward("lista");

    }
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

    public ActionForward traduir(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request,
    		HttpServletResponse response) throws Exception {

    	log.info("Entramos en traduir");
    	
    	
    	
        ProcedimientoForm dForm = (ProcedimientoForm) form;
        
        TraduccionProcedimientoLocal cat = (TraduccionProcedimientoLocal)dForm.get("traducciones", Traductor.CAT);       
        for(int lang : new int[]{Traductor.CAS,Traductor.ANG,Traductor.ALE}) { //TODO generalitzar segons IdiomaDelegate.
        	TraduccionProcedimientoLocal proc = (TraduccionProcedimientoLocal)dForm.get("traducciones", lang);
        	Traductor.traduir(cat,Traductor.CAT,proc,lang);
        }
        
    	return mapping.findForward("success");
    }

  
    
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");

        return null;
    }
*/
}
