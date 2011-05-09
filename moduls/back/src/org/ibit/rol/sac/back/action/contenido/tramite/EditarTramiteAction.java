/**
 * User: jhorrach
 * Date: Mar 10, 2004
 * Time: 11:22:27 AM
 */
package org.ibit.rol.sac.back.action.contenido.tramite;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.form.TramiteForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.model.TraduccionTramite;
import org.ibit.rol.sac.model.Tramite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import es.caib.vuds.ActualizacionVudsException;
import es.caib.vuds.ValidateVudsException;
import es.caib.vuds.VentanillaUnica;
import es.indra.rol.sac.integracion.traductor.Traductor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Enumeration;

import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;

/**
 * Action para editar un Documento
 *
 * @struts.action
 *  name="tramiteForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/tramite/crear"
 *  input=".contenido.tramite.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="tramiteForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/tramite/editar"
 *  input=".contenido.tramite.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="tramiteForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/tramite/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.tramite.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/contenido/procedimiento/seleccionar.do"
 *
 */
public class EditarTramiteAction extends BaseDispatchAction{

	public EditarTramiteAction() {
		// TODO Auto-generated constructor stub
	}
    protected static Log log = LogFactory.getLog(EditarTramiteAction.class);

    protected Map<String,String> getKeyMethodMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.insertar","editar");
        map.put("boton.modificar","editar");
        map.put("boton.traducir","editar");
        map.put("boton.seleccionar","seleccionar");
        map.put("boton.eliminar","eliminar");
        map.put("boton.cancelar","cancelar");
        map.put("boton.crear","crear");
        map.put("tramite.relacion.documents", "operarDocumentsTramit");  //ordenar ...
        map.put("tramite.relacion.taxes", "operarTaxesTramit");  //ordenar ...         
        return map;
    }

    public ActionForward crear(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception{

        log.info("entramos en crear");
        
   	 	log.info(request.getCharacterEncoding());
        
        ProcedimientoDelegate procDelegate = null!=this.procedimientoDelegate? this.procedimientoDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]
        
        Long idProcedimiento = new Long(request.getParameter("idProcedimiento"));
    	ProcedimientoLocal proc = procDelegate.obtenerProcedimiento(idProcedimiento);
    	log.info(proc);
    	request.setAttribute("idUA",proc.getUnidadAdministrativa().getId());
    	String nombreUA=((TraduccionUA)proc.getUnidadAdministrativa().getTraduccion()).getNombre();
    	request.setAttribute("nombreUA",nombreUA);
//    	request.setAttribute("nombreUA",((TraduccionUA)proc.getUnidadAdministrativa().getTraduccion()).getNombre().getBytes("UTF-8"));

    	if ("on".equals(proc.getTaxa())) { request.setAttribute("taxa","on"); }

        if ("1".equals(proc.getVentanillaUnica())) {request.setAttribute("vuds","on"); }

    	return mapping.findForward("success");
    }
    
    //u92770[enric] afegit setters dels delegate per fer unit testing del metode editar() (Dependency Injection)
    ProcedimientoDelegate procedimientoDelegate;
    TramiteDelegate tramiteDelegate;
    IdiomaDelegate idiomaDelegate;

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception{

        log.info("entramos en editar");
        TramiteForm dForm = (TramiteForm)form;
        ProcedimientoDelegate procDelegate = null!=this.procedimientoDelegate? this.procedimientoDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]
        TramiteDelegate tramiteDelegate = null!=this.tramiteDelegate? this.tramiteDelegate: DelegateUtil.getTramiteDelegate();  //u92770[enric]
        IdiomaDelegate idiomaDelegate=null!=this.idiomaDelegate?this.idiomaDelegate : DelegateUtil.getIdiomaDelegate();
        Long idProcedimiento = new Long(request.getParameter("idProcedimiento"));
        Tramite tramite = null;
        
        try {

        log.info("id="+dForm.get("id"));

        //boolean isNew = dForm.get("id") == null || dForm.get("id").equals("0");
        Object o = dForm.get("id");
        boolean isNew = o==null || (o instanceof Long && ((Long)o)==0); 
        
        //if (dForm.get("id") == null){
        if (isNew) {
            if (!tramiteDelegate.autorizaCrearTramite(idProcedimiento))
            	throw new SecurityException("Aviso: No tiene privilegios para crear el trámite");
            tramite = new Tramite();
    		tramite.setOperativa(Tramite.Operativa.CREA);
    		tramite.setOrden(0L);

        } else {
            tramite = tramiteDelegate.obtenerTramite((Long)dForm.get("id"));
            if (!tramiteDelegate.autorizaModificarTramite(tramite.getId()))
            	throw new SecurityException("Aviso: No tiene privilegios para modificar el trámite");
    		tramite.setOperativa(Tramite.Operativa.MODIFICA);
        }

        VOUtils.populate(tramite, dForm, idiomaDelegate);

        if (request.getParameter("action").equals(getResources(request).getMessage(request.getLocale(), "boton.traducir"))) {

        	traducir (request, dForm);
        	dForm.set("id", tramite.getId());
        	request.setAttribute("formularioOptions", tramite.getFormularios());
        	request.setAttribute("idProcedimiento", idProcedimiento);
        	request.setAttribute("tramiteForm", dForm);
        	request.setAttribute("docInformatiuOptions", tramite.getDocsInformatius());
        	request.setAttribute("taxesOptions", tramite.getTaxes());
            if ("1".equals(tramite.getProcedimiento().getVentanillaUnica())) {request.setAttribute("vuds","on"); }

        	return mapping.findForward("success");

        } else {

        	tramite.setDataCaducitat(dForm.getDataCaducitat());
        	tramite.setDataActualitzacio(dForm.getDataActualitzacio());
        	tramite.setDataPublicacio(dForm.getDataPublicacio());

        	Long idOC = (Long) dForm.get("idOrganCompetent");

        	tramite.setProcedimiento(procDelegate.obtenerProcedimiento(idProcedimiento));
        	
        	
//ejaen@dgtic - TODO si es un tramit de ventanilla unica, comprobem que el procediment i el tramit estan traduits al castella
//        	
//        	if("1".equals(tramite.getProcedimiento().getVentanillaUnica()))
//        			if(null==tramite.getProcedimiento().getTraduccion(VentanillaUnica.CASTELLA) || 
//        			   null==tramite.getTraduccion(VentanillaUnica.CASTELLA)) {
//                    	request.setAttribute("alert", "tramite.vuds.avis.castella");
//                       	dForm.set("id", tramite.getId());
//                    	request.setAttribute("formularioOptions", tramite.getFormularios());
//                    	request.setAttribute("idProcedimiento", idProcedimiento);
//                    	request.setAttribute("tramiteForm", dForm);
//                    	request.setAttribute("docInformatiuOptions", tramite.getDocsInformatius());
//                    	request.setAttribute("taxesOptions", tramite.getTaxes());
//                    	return mapping.findForward("success");
//        			}
        			
        	tramiteDelegate.grabarTramite(tramite,idOC);


        	//if(dForm.get("id") != null){
        	if (isNew) {
//        		tramite.setOperativa(Tramite.Operativa.CREA);
//        		tramite.setOrden(0L);
            request.setAttribute("alert", "confirmacion.alta");
            procDelegate.anyadirTramite(tramite.getId(), idProcedimiento);
        	} else {
        		request.setAttribute("alert", "confirmacion.modificacion");
        }
        dForm.set("id",tramite.getId());
        log.info("Creat/Actualitzat " + tramite.getId());

        request.setAttribute("formularioOptions", tramite.getFormularios());
        	request.setAttribute("docInformatiuOptions", tramite.getDocsInformatius());
        	request.setAttribute("taxesOptions", tramite.getTaxes());
        request.setAttribute("idProcedimiento", idProcedimiento);
        request.setAttribute("idSelect", tramite.getId());
        return dispatchMethod(mapping, form, request, response, "seleccionar");

        }

        
        } catch (SecurityException e) {
	    	
            if ((Long) dForm.get("id") == null) request.setAttribute("alert", "tramite.aviso.privilegio.insertar");
            	else request.setAttribute("alert", "tramite.aviso.privilegio.modificar");

        	request.setAttribute("idSelect", tramite.getId());
        	return dispatchMethod(mapping, form, request, response, "seleccionar");
	        
	    } catch (ValidateVudsException e) {
	    	String camps="";
	    	for(String camp: e.getCampsSenseValidar()) camps+="\\n-"+camp; 
	    	String msg="Error durant la validació del tràmit. " +
	    			"\\nNo es pot guardar perquè és un tràmit de la Finestreta Única i els següents camps " +
	    			"no estàn traduïts al castellà:"+camps+"\\n\\n ";
	    	request.setAttribute("alertVuds", msg);
	    	if(null!=tramite.getId()) {
	    		request.setAttribute("idSelect", tramite.getId());
	    		return dispatchMethod(mapping, form, request, response, "seleccionar");
	    }
	    	else
	    		return dispatchMethod(mapping, form, request, response, "crear");

	    } catch (ActualizacionVudsException e) {
	    	//encara que ha fallat la actualizacio, el tramit s'ha creat correctament i per tant es te que afegir al procediment
	    	procDelegate.anyadirTramite(tramite.getId(), idProcedimiento);

	    	String msg="Tràmit modificat correctament, però ha hagut un error enviant-lo a la " +
	    			"Finestreta Única. \\nNotificació enviada al correu del destinatari.";
	    	request.setAttribute("alertVuds", msg);

    		request.setAttribute("idSelect", tramite.getId());
    		return dispatchMethod(mapping, form, request, response, "seleccionar");
	    	
	    }	    

        

    }
        
        
        /**
         * Método que traduce un formulario de trámite
         * @author Indra
         * @param request	petición de usuario
         * @param dForm		formulario dinámico enviado por usuario
         * @throws Exception
         */
        private void traducir (HttpServletRequest request, TraDynaValidatorForm dForm) throws Exception  {	
            	
    		Traductor traductor = (Traductor) request.getSession().getAttribute("traductor");
    		String idiomaOrigen = "ca";

            TraduccionTramite tramiteOrigen = (TraduccionTramite) dForm.get("traducciones", 0);

            Iterator itTradTramites = ((ArrayList) dForm.get("traducciones")).iterator();                
            Iterator<String> itLang = traductor.getListLang().iterator(); 
                
                while (itLang.hasNext()){
                    
                	TraduccionTramite tramiteDesti = (TraduccionTramite) itTradTramites.next();
                	String idiomaDesti = itLang.next();
                	
                	if (!idiomaOrigen.equals(idiomaDesti)) 
                	{
                		traductor.setDirTraduccio(idiomaOrigen, idiomaDesti);
                		
                		if (traductor.traducir(tramiteOrigen, tramiteDesti)) request.setAttribute("alert", "traduccion.confirmacion"); 
                		else {
                			request.setAttribute("alert", "traduccion.error");
                			break;
                		}
                		}
                	}

                log.info("Traducción trámite - Id: " + (Long) dForm.get("id"));
        	
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        //TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TramiteForm dForm = (TramiteForm)form;
        TramiteDelegate tramiteDelegate = null!=this.tramiteDelegate? this.tramiteDelegate: DelegateUtil.getTramiteDelegate();  //u92770[enric]
        ProcedimientoDelegate procDelegate = null!=this.procedimientoDelegate? this.procedimientoDelegate: DelegateUtil.getProcedimientoDelegate();  //u92770[enric]

        Long id = null;

        if (request.getParameter("idSelect") != null)	//mirem parametres 
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)	//mirem atributs
            id = (Long) request.getAttribute("idSelect");

        log.info("id="+id);
        if (id != null){
            Tramite tramite = tramiteDelegate.obtenerTramite(id);
            log.info("tramite="+tramite);
            VOUtils.describe(dForm, tramite, idiomaDelegate);
            dForm.setDataActualitzacio(tramite.getDataActualitzacio());
            dForm.setDataCaducitat(tramite.getDataCaducitat());
            dForm.setDataPublicacio(tramite.getDataPublicacio());
            request.setAttribute("idProcedimiento", tramite.getProcedimiento().getId());
            request.setAttribute("formularioOptions", tramite.getFormularios());
            request.setAttribute("docInformatiuOptions", tramite.getDocsInformatius());
            request.setAttribute("taxesOptions", tramite.getTaxes());
            if (tramite.getOrganCompetent() != null){
                dForm.set("idOrganCompetent", tramite.getOrganCompetent().getId());
                dForm.set("nomOrganCompetent", ((TraduccionUA)tramite.getOrganCompetent().getTraduccion()).getNombre());
            }
            else {
            	ProcedimientoLocal proc = procDelegate.obtenerProcedimiento(tramite.getProcedimiento().getId());
            }

            if (null!= tramite.getCodiVuds()){
                dForm.set("codiVuds", tramite.getCodiVuds());
                dForm.set("descCodiVuds", tramite.getDescCodiVuds());
            }

            if ("on".equals(tramite.getProcedimiento().getTaxa())) {
            	request.setAttribute("taxa","on");
            }

            if ("1".equals(tramite.getProcedimiento().getVentanillaUnica())) {
            	request.setAttribute("vuds","on");
            }


        } else {
            return mapping.findForward("fail");
        }

        if (request.getAttribute("idSelect") != null)
        	request.setAttribute("tramiteForm", dForm);

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
        ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();

        if (request.getParameter("idSelect") != null && request.getParameter("idProcedimiento") != null){
            Long id = new Long(request.getParameter("idSelect"));
            Long idProcedimiento = new Long(request.getParameter("idProcedimiento"));
            log.info("Eliminado Tramite: " + id);
            procDelegate.eliminarTramite(id, idProcedimiento);  //quita el tramite de la lista
            tramiteDelegate.borrarTramite(id);  //borra el tramite
            TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
            dForm.reset(mapping, request);
            request.setAttribute("idSelect", idProcedimiento);
            request.setAttribute("action", getResources(request).getMessage(request.getLocale(), "boton.seleccionar"));

        } else {
            return mapping.findForward("fail");
        }

        return mapping.findForward("cancel");
    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

        Long idProcedimiento = new Long(request.getParameter("idProcedimiento"));
        request.setAttribute("idSelect", idProcedimiento);
        request.setAttribute("action", getResources(request).getMessage(request.getLocale(), "boton.seleccionar"));

        return mapping.findForward("cancel");
    }

    public ActionForward operarDocumentsTramit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception { 

    	log.info("Entramos en operar documentsTramit");
    	Long idTramite = new Long(request.getParameter("idTramite"));
    	if ("actualizar_orden".equals(request.getParameter("operacion")))
    	{
    		HashMap<String,String[]> params = new HashMap<String, String[]>();
    		Enumeration names = request.getParameterNames();
    		while(names.hasMoreElements())
    		{
    			final String name = (String) names.nextElement();
    			final String vals[] = request.getParameterValues(name);
    			params.put(name, vals);
    		}
            TramiteDelegate tramiteDelegate = null!=this.tramiteDelegate? this.tramiteDelegate: DelegateUtil.getTramiteDelegate();  //u92770[enric]
            tramiteDelegate.actualizarOrdenDocs(params,idTramite);				
    	}else {
    		return mapping.findForward("cancel");
    	}
		request.setAttribute("idSelect", idTramite);
		return dispatchMethod(mapping, form, request, response, "seleccionar");
    }
    

    public ActionForward operarTaxesTramit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception { 

    	log.info("Entramos en operar taxes Tramit");
    	/*  TODO u92770[enric] - cal reordenar les taxes? 
    	 
    	Long idTramite = new Long(request.getParameter("idTramite"));
    	if ("actualizar_orden".equals(request.getParameter("operacion")))
    	{
    		HashMap<String,String[]> params = new HashMap<String, String[]>();
    		Enumeration names = request.getParameterNames();
    		while(names.hasMoreElements())
    		{
    			final String name = (String) names.nextElement();
    			final String vals[] = request.getParameterValues(name);
    			params.put(name, vals);
    		}
            TramiteDelegate tramiteDelegate = null!=this.tramiteDelegate? this.tramiteDelegate: DelegateUtil.getTramiteDelegate();  //u92770[enric]
            tramiteDelegate.actualizarOrdenDocs(params,idTramite);				
    	}else {
    		return mapping.findForward("cancel");
    	}
		request.setAttribute("idSelect", idTramite);
		*/
		return dispatchMethod(mapping, form, request, response, "seleccionar");
    }

    
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

	public ProcedimientoDelegate getProcedimientoDelegate() {
		return procedimientoDelegate;
	}

	public void setProcedimientoDelegate(ProcedimientoDelegate procedimientoDelegate) {
		this.procedimientoDelegate = procedimientoDelegate;
	}

	public TramiteDelegate getTramiteDelegate() {
		return tramiteDelegate;
	}

	public void setTramiteDelegate(TramiteDelegate tramiteDelegate) {
		this.tramiteDelegate = tramiteDelegate;
	}

	public IdiomaDelegate getIdiomaDelegate() {
		return idiomaDelegate;
	}

	public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
		this.idiomaDelegate = idiomaDelegate;
	}


}
