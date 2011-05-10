package org.ibit.rol.sac.back.action.organigrama.unidad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadMateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Materia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar una Unidad Materia (PORMAD)
 *
 * @struts.action
 *  name="unidadMateriaForm"
 *  scope="request"
 *  validate="true"
 *  path="/organigrama/unimat/editar"
 *  input="unidadmateria.jsp"
 *  parameter="action"
 *
 * @struts.action
 *  name="unidadMateriaForm"
 *  scope="request"
 *  validate="false"
 *  path="/organigrama/unimat/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".organigrama.unimat.form"
 *
 *
 * @struts.action-forward
 *  name="cancel" path="/organigrama/unidad/seleccionar.do"

 */
public class EditarUnidadMateriaAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarUnidadMateriaAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.cancelar","cancelar");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;

        UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();
        UnidadMateria unidadMateria;

         if (dForm.get("id") != null) {
            unidadMateria = unidadMateriaDelegate.obtenerUnidadMateria((Long)dForm.get("id"));
        } else {
            unidadMateria  = new UnidadMateria();
        }
        VOUtils.populate(unidadMateria, dForm);



        Long idUnidad = new Long(request.getParameter("idUnidad"));
        Long idMateria = (Long) dForm.get("idMateria");
        log.info("IdMateria "+ idMateria);
        log.info("IdUnidad "+ idUnidad);

         if ((idUnidad != null) && (idMateria != null)) {
            log.info("entro en grabar");
            unidadMateria.setUnidadPrincipal("N");
            unidadMateriaDelegate.grabarUnidadMateria(unidadMateria, idUnidad, idMateria);
            if(dForm.get("id") != null){
                request.setAttribute("alert", "confirmacion.modificacion");
            }else{
                request.setAttribute("alert", "confirmacion.alta");
            }
        } else {
            log.info("entro en fail");
            return mapping.findForward("fail");
        }


        dForm.set("id",unidadMateria.getId());
        request.setAttribute("idMateria" ,unidadMateria.getMateria().getId() );
        request.setAttribute("idUnidad" ,unidadMateria.getUnidad().getId() );


        return dispatchMethod(mapping, form, request, response, "seleccionar");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");

        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();

        Long id = (Long) dForm.get("id");
        Long idUnidad = unidadMateriaDelegate.obtenerUnidadMateria(id).getUnidad().getId();

        unidadMateriaDelegate.borrarUnidadMateria(id);

        dForm.reset(mapping, request);

        request.setAttribute("idUA", idUnidad);
        request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        UnidadMateriaDelegate unidadMateriaDelegate = DelegateUtil.getUnidadMateriaDelegate();

        Long id = (Long) dForm.get("id");
        UnidadMateria unidadMateria= unidadMateriaDelegate.obtenerUnidadMateria(id);
        VOUtils.describe(dForm, unidadMateria);

        if (unidadMateria.getMateria() != null) {
            dForm.set("idMateria", unidadMateria.getMateria().getId());
            request.setAttribute("idMateria", unidadMateria.getMateria().getId());
        }
        if(unidadMateria.getUnidad() != null){
            //dForm.set("idUnidad", unidadMateria.getUnidad().getId());
            request.setAttribute("idUnidad", unidadMateria.getUnidad().getId());
        }
        return mapping.findForward("success");


    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        Long idUnidad = (Long) dForm.get("idUnidad");
        dForm.reset(mapping, request);

        request.setAttribute("idUA", idUnidad);
        request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }
}
