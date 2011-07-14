package org.ibit.rol.sac.back.subscripcions.actionform;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.subscripcions.config.TraFormBeanConfig;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

/**
 *
 */
public class TraDynaValidatorForm extends DynaValidatorForm {

    protected static Log log = LogFactory.getLog(TraDynaValidatorForm.class);

    protected String traClassName = null;


    public void reset(ActionMapping mapping, HttpServletRequest request) {
        //log.debug("reset. name=" + mapping.getName() + ", path=" + mapping.getPath());
        super.reset(mapping, request);
        initialize(mapping);

        if (traClassName == null) {
            traClassName = getTraduccionClassName(request, mapping);
        }

        List traducciones = (List) this.get("traducciones");
        try {
            IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
            int numLangs = delegate.listarLenguajes().size();
            for (int i = 0; i < numLangs; i++) {
                traducciones.add(RequestUtils.applicationInstance(traClassName));
            }
        } catch (Throwable t) {
            log.error("Error creando traducciones", t);
        }
    }

    private String getTraduccionClassName(HttpServletRequest request, ActionMapping mapping) {
        ModuleConfig config = RequestUtils.getModuleConfig(request, getServlet().getServletContext());
        TraFormBeanConfig beanConfig = (TraFormBeanConfig) config.findFormBeanConfig(mapping.getName());
        String className = beanConfig.getTraduccionClassName();
        return className;
    }
}
