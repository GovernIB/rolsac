package org.ibit.rol.sac.back.action.contenido.normativa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.form.NormativaForm;
import org.ibit.rol.sac.back.form.BusquedaNormativaForm;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para buscar normativas
 *
 * @struts.action
 *  name="busquedaNormativaForm"
 *  scope="request"
 *  validate="false"
 *  path="/normativa/busqueda"
 *
 * @struts.action-forward
 *  name="popup" path=".contenido.procedimiento.popupnormativas"
 */
public class BusquedaNormativaAction extends Action {
    protected static Log log = LogFactory.getLog(BusquedaNormativaAction.class);

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.debug("Entramos en normativabusqueda");
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        BusquedaNormativaForm normativaForm = (BusquedaNormativaForm)form;


        Map paramnotrad = new HashMap();
        paramnotrad.put("fecha", normativaForm.getFecha());
        paramnotrad.put("fechaBoletin", normativaForm.getFechaBoletin());


        Map paramtrad = new HashMap();
        Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

        List normativasLocales =  new ArrayList();
        List normativasExternas =  new ArrayList();


        while (itLang.hasNext()){
                paramtrad.put("idioma", itLang.next().toString());
                paramtrad.put("titulo", normativaForm.get("titulo").toString().toUpperCase());
                try {
                	normativasLocales.addAll(normativaDelegate.buscarNormativas(paramnotrad, paramtrad, "local"));
                } catch (Exception e) {log.debug("Error busqueda normativa local" + e.getMessage());}
                try {
                	normativasExternas.addAll(normativaDelegate.buscarNormativas(paramnotrad, paramtrad, "externa"));
                } catch (Exception e) {log.debug("Error busqueda normativa externa" + e.getMessage());}
        }


        List normativas = new ArrayList();
        if (normativasLocales != null) normativas.addAll(normativasLocales);
        if (normativasExternas != null) normativas.addAll(normativasExternas);

        request.setAttribute("normativas", normativas);

        return mapping.findForward("popup");

    }

}
