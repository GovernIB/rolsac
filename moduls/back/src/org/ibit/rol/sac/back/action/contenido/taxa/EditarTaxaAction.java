/**
 * User: jhorrach
 * Date: Mar 9, 2004
 * Time: 1:50:49 PM
 */
package org.ibit.rol.sac.back.action.contenido.taxa;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.DocumentoForm;
import org.ibit.rol.sac.back.form.TaxaForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.ibit.rol.sac.persistence.delegate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para editar un Documento
 *
 * @struts.action
 *  name="taxaForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/taxa/editar"
 *  input=".contenido.taxa.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="taxaForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/taxa/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.taxa.form"
 *
 * @struts.action-forward
 *  name="tramite" path="/contenido/tramite/seleccionar.do"
 *
 *
 */
public class EditarTaxaAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarTaxaAction.class);

    protected Map getKeyMethodMap(){
        Map map = new HashMap();
        map.put("boton.insertar","editar");
        map.put("boton.modificar","editar");
        map.put("boton.seleccionar","seleccionar");
        map.put("boton.eliminar","eliminar");
        map.put("boton.cancelar","cancelar");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception{

        log.info("entramos en editar");
        TaxaForm dForm = (TaxaForm)form;

        IdiomaDelegate idiomaDelegate = null==this.idiomaDelegate?  DelegateUtil.getIdiomaDelegate() : this.idiomaDelegate;        
        TramiteDelegate tramDelegate = null==this.tramiteDelegate? DelegateUtil.getTramiteDelegate() : this.tramiteDelegate;

        Taxa taxa;
        Object o = dForm.get("id");
        boolean isNew = o==null || (o instanceof Long && ((Long)o)==0); 
        
        if (isNew) {
            taxa = new Taxa();
        } else {
            taxa = tramDelegate.obtenirTaxa((Long)dForm.get("id"));
        }

        VOUtils.populate(taxa,dForm, idiomaDelegate);
        if(null!=taxa.getId() && 0==taxa.getId()) taxa.setId(null);
        
        Long tid = new Long(request.getParameter("idTramite"));
        //FIXME - (u92770[enric]) he observat que al fer el grabarTaxa hi ha un LogInterceptor que fa un toString, 
        //pero com la referencia
        //al tramit no existeix encara, falla per un nullpointer. Per tant, crec que seria millor
        //afegir la refencia aqui, abans de cridar al grabarTaxa. 
        //Pero no faré aquesta modificaciopq això mateix passa amb els altres continguts del rolsac.
        
        Long taxId=tramDelegate.grabarTaxa(taxa, tid);
        dForm.set("id",taxId);
        request.setAttribute("idSelect", tid);  //ens redirigim al tramite
        request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
        log.info("success");
        return mapping.findForward("tramite");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        log.info(idiomaDelegate);
        IdiomaDelegate idiomaDelegate = null==this.idiomaDelegate?  DelegateUtil.getIdiomaDelegate() : this.idiomaDelegate;        
        TramiteDelegate tramDelegate = null==this.tramiteDelegate? DelegateUtil.getTramiteDelegate() : this.tramiteDelegate;

        log.info(idiomaDelegate);
        Long taxId = Long.valueOf(request.getParameter("idSelect"));
        if (taxId != null){
            log.info("idSelect="+taxId);
            Taxa taxa = tramDelegate.obtenirTaxa(taxId);
            log.info(taxa);
            VOUtils.describe(dForm, taxa,idiomaDelegate);
            
        } else {
            log.info("failed");
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {


        log.info("Entramos en eliminar");
        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
        TramiteDelegate tramDelegate = null==this.tramiteDelegate? DelegateUtil.getTramiteDelegate() : this.tramiteDelegate;
        if (request.getParameter("idSelect") != null){
            Long id = new Long(request.getParameter("idSelect"));
            tramDelegate.borrarTaxa(id);
            TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
            dForm.reset(mapping, request);

            
            log.info("Eliminado taxa: " + id);

            id= new Long(request.getParameter("idTramite"));
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
            return mapping.findForward("tramite");
        }
        return mapping.findForward("fail");

    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

        if (request.getParameter("idTramite") != null){
            Long id = new Long(request.getParameter("idTramite"));
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
            return mapping.findForward("tramite");
        }
        return mapping.findForward("fail");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

    
    
    
    //propietats usades pel testing:
    
    IdiomaDelegate idiomaDelegate;
    TramiteDelegate tramiteDelegate;
    
    public IdiomaDelegate getIdiomaDelegate() {
		return idiomaDelegate;
	}

	public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
		this.idiomaDelegate = idiomaDelegate;
	}

	public TramiteDelegate getTramiteDelegate() {
		return tramiteDelegate;
	}

	public void setTramiteDelegate(TramiteDelegate tramiteDelegate) {
		this.tramiteDelegate = tramiteDelegate;
	}

    
}
