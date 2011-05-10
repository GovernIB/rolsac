package org.ibit.rol.sac.back.action.contenido.comentario;

import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ComentarioDelegate;
import org.apache.struts.action.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action para borrar un comentario
 *
 * @struts.action
 *  path="/contenido/comentario/borrar"
 *  name="comentarioForm"
 *  scope="request"
 * 
 * @struts.action-forward
 *  name="procedimiento" path="/contenido/procedimiento/seleccionar.do"
 * @struts.action-forward
 *  name="ficha" path="/contenido/ficha/seleccionar.do"
 */
public class BorrarComentarioAction extends BaseAction {

    protected static Log log = LogFactory.getLog(BorrarComentarioAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        ComentarioDelegate comentarioDelegate = DelegateUtil.getComentarioDelegate();

        DynaActionForm dForm = (DynaActionForm) form;

        Long idComentario = (Long) dForm.get("idComentario");
        log.info("Eliminando comentario: " + idComentario);
        comentarioDelegate.borrarComentario(idComentario);

        request.setAttribute("idSelect", dForm.get("idRel"));
        request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

        String tipo = (String) dForm.get("tipo");
        return mapping.findForward(tipo);
    }
}
