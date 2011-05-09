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
 * Action para mostrar un comentario
 *
 * @struts.action
 *  path="/contenido/comentario/mostrar"
 *
 * @struts.action-forward
 *  name="success" path=".popup.comentarios"
 */
public class MostrarComentarioAction extends BaseAction {

    protected static Log log = LogFactory.getLog(MostrarComentarioAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        Long idComentario = new Long(request.getParameter("id"));

        ComentarioDelegate delegate = DelegateUtil.getComentarioDelegate();
        request.setAttribute("comentario", delegate.obtenerComentario(idComentario));

        return mapping.findForward("success");
    }
}