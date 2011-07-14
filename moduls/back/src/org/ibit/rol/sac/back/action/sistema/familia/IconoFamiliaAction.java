/**
 * User: jhorrach
 * Date: Dec 11, 2003
 * Time: 12:46:56 PM
 */
package org.ibit.rol.sac.back.action.sistema.familia;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IconoFamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.model.IconoFamilia;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar un Icono Familia.
 *
 * @struts.action
 *  name="iconoFamiliaForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/icofam"
 *  input="icono.jsp"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.familia.form"
 *
 */
public class IconoFamiliaAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(IconoFamiliaAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.eliminar", "eliminar");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        IconoFamiliaDelegate iconoDelegate = DelegateUtil.getIconoFamiliaDelegate();
        IconoFamilia icono = new IconoFamilia();

        Long idFamilia = (Long) dForm.get("idFamilia");
        Long idPerfil = (Long) dForm.get("idPerfil");
        FormFile archivo = (FormFile) dForm.get("file");
        if (archivoValido(archivo) && (idFamilia != null) && (idPerfil != null)) {
            icono.setIcono(populateArchivo(icono.getIcono(), archivo));
            iconoDelegate.grabarIconoFamilia(icono, idFamilia, idPerfil);
        } else {
            return mapping.findForward("fail");
        }

        ActionForm famForm = obtenerActionForm(mapping, request, "/sistema/familia/editar");
        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();

        VOUtils.describe((TraDynaValidatorForm) famForm, familiaDelegate.obtenerFamilia(idFamilia));
        request.setAttribute("iconosFamilia", familiaDelegate.obtenerFamilia(idFamilia).getIconos());
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        IconoFamiliaDelegate iconoDelegate = DelegateUtil.getIconoFamiliaDelegate();

        Long id = (Long) dForm.get("id");
        Long idFamilia = iconoDelegate.obtenerIconoFamilia(id).getFamilia().getId();

        iconoDelegate.borrarIconoFamilia(id);

        ActionForm famForm = obtenerActionForm(mapping, request, "/sistema/familia/editar");
        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();

        VOUtils.describe((TraDynaValidatorForm) famForm, familiaDelegate.obtenerFamilia(idFamilia));
        request.setAttribute("iconosFamilia", familiaDelegate.obtenerFamilia(idFamilia).getIconos());
        return mapping.findForward("success");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        Long idFamilia = (Long) dForm.get("idFamilia");
        dForm.initialize(mapping);

        ActionForm famForm = obtenerActionForm(mapping, request, "/sistema/familia/editar");
        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();

        VOUtils.describe((TraDynaValidatorForm) famForm, familiaDelegate.obtenerFamilia(idFamilia));
        request.setAttribute("iconosFamilia", familiaDelegate.obtenerFamilia(idFamilia).getIconos());
        return mapping.findForward("success");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

}
