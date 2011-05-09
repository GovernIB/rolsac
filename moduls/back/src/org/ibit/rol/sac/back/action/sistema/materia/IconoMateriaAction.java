/**
 * User: jhorrach
 * Date: Dec 11, 2003
 * Time: 12:00:00 PM
 */
package org.ibit.rol.sac.back.action.sistema.materia;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IconoMateriaDelegate;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

/**
 * Action para editar un Icono anyadirMateria.
 *
 * @struts.action
 *  name="iconoMateriaForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/icomat"
 *  input="icono.jsp"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.materia.form"
 *
 */
public class IconoMateriaAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(IconoMateriaAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.eliminar", "eliminar");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        IconoMateriaDelegate iconoDelegate = DelegateUtil.getIconoMateriaDelegate();
        IconoMateria icono = new IconoMateria();

        Long idMateria = (Long) dForm.get("idMateria");
        Long idPerfil = (Long) dForm.get("idPerfil");
        FormFile archivo = (FormFile) dForm.get("file");
        if (archivoValido(archivo) && (idMateria != null) && (idPerfil != null)) {
            icono.setIcono(populateArchivo(icono.getIcono(), archivo));
            iconoDelegate.grabarIconoMateria(icono, idMateria, idPerfil);
        } else {
            return mapping.findForward("fail");
        }

        ActionForm matForm = obtenerActionForm(mapping, request, "/sistema/materia/editar");
        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

        VOUtils.describe((TraDynaValidatorForm) matForm, materiaDelegate.obtenerMateria(idMateria));
        request.setAttribute("iconosMateria", materiaDelegate.obtenerMateria(idMateria).getIconos());
        request.setAttribute("listaUAsMateria", materiaDelegate.obtenerMateria(idMateria).getUnidadesmaterias());
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        IconoMateriaDelegate iconoDelegate = DelegateUtil.getIconoMateriaDelegate();
        Long id = (Long) dForm.get("id");
        Long idMateria = iconoDelegate.obtenerIconoMateria(id).getMateria().getId();

        iconoDelegate.borrarIconoMateria(id);

        ActionForm matForm = obtenerActionForm(mapping, request, "/sistema/materia/editar");
        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

        VOUtils.describe((TraDynaValidatorForm) matForm, materiaDelegate.obtenerMateria(idMateria));
        request.setAttribute("iconosMateria", materiaDelegate.obtenerMateria(idMateria).getIconos());
        request.setAttribute("listaUAsMateria", materiaDelegate.obtenerMateria(idMateria).getUnidadesmaterias());
        return mapping.findForward("success");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        Long idMateria = (Long) dForm.get("idMateria");
        dForm.initialize(mapping);

        ActionForm matForm = obtenerActionForm(mapping, request, "/sistema/materia/editar");
        MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();

        VOUtils.describe((TraDynaValidatorForm) matForm, materiaDelegate.obtenerMateria(idMateria));
        request.setAttribute("iconosMateria", materiaDelegate.obtenerMateria(idMateria).getIconos());
        request.setAttribute("listaUAsMateria", materiaDelegate.obtenerMateria(idMateria).getUnidadesmaterias());

        return mapping.findForward("success");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

}
