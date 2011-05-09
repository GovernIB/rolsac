package org.ibit.rol.sac.back.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para elegir el layout a usar.
 *
 * @struts.action
 *  path="/layout"
 *
 * @struts.action-forward
 *  name="main" path="/layout/mainLayout.jsp"
 *
 * @struts.action-forward
 *  name="table" path="/layout/tableLayout.jsp"
 */
public class LayoutAction extends Action {

    public static final String LAYOUT_KEY = "org.ibit.rol.sac.back.LAYOUT_KEY";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);

        String layout = request.getParameter("layout");

        if (layout != null) {
            ActionForward forward = mapping.findForward(layout);
            if (forward != null) {
                session.setAttribute(LAYOUT_KEY, layout);
                return forward;
            }
        }

        layout = (String) session.getAttribute(LAYOUT_KEY);
        if (layout == null) {
            layout = "main";
            session.setAttribute(LAYOUT_KEY, layout);
        }

        return mapping.findForward(layout);
    }
}
