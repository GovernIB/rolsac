package org.ibit.rol.sac.back.action.organigrama.unidad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para buscar fichas
 *
 * @struts.action
 *  name="busquedaFichaForm"
 *  scope="request"
 *  validate="false"
 *  path="/ficha/busqueda"
 *
 * @struts.action-forward
 *  name="popup" path=".organigrama.unidad.popupfichas"
 */
public class BusquedaFichaAction extends Action {
    protected static Log log = LogFactory.getLog(BusquedaFichaAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.debug("Entramos en fichabusqueda");
        FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        DynaActionForm fichaForm = (DynaActionForm)form;

        // Parametros sin traduccion
        Map paramnotrad = new HashMap();

        // Parametros con traduccion
        Map paramtrad = new HashMap();
        
        Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

        List fichas =  new ArrayList();
        
        while (itLang.hasNext()){
        	paramtrad.put("idioma", itLang.next().toString());
        	paramtrad.put("titulo", fichaForm.get("titulo").toString().toUpperCase());
        	paramtrad.put("descripcion", fichaForm.get("descripcion").toString().toUpperCase());
            try {
            	fichas.addAll(fichaDelegate.buscarFichas(paramnotrad, paramtrad));            	
            } catch (Exception e) {log.debug("Error busqueda fichas " + e.getMessage());}
        }
        
        request.setAttribute("fichas", fichas);

        return mapping.findForward("popup");

    }

}
