package org.ibit.rol.sac.back.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.config.TraFormBeanConfig;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

/**
 *
 */
public class TraDynaValidatorForm extends DynaValidatorForm {

	public TraDynaValidatorForm() {
		// TODO Auto-generated constructor stub
	}
    protected static Log log = LogFactory.getLog(TraDynaValidatorForm.class);

    protected String traClassName = null;

    // ejaen@dgtic: como TraDynaValidatorForm no es una action, no se puede inicializar 
    // idiomaDelegate con el factory de spring en el testing framework. Por tanto hago la referencia static
    // para establecerla a mano desde el test.
    public static IdiomaDelegate idiomaDelegate;
    
    
    //public static IdiomaDelegate getIdiomaDelegate() {return idiomaDelegate;}
	//public static void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {idiomaDelegate = idiomaDelegate;}

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        log.info("entramos en reset. name=" + mapping.getName() + ", path=" + mapping.getPath());
        super.reset(mapping, request);
        initialize(mapping);

        if (traClassName == null) {
            traClassName = getTraduccionClassName(request, mapping);
        }

        List traducciones = (List) this.get("traducciones");
        try {
            IdiomaDelegate delegate = null==idiomaDelegate? DelegateUtil.getIdiomaDelegate() : idiomaDelegate;
            int numLangs = delegate.listarLenguajes().size();
            //int numLangs=4; //FIXME (enric@dgtic) lo pongo temporalmente, hasta saber como hacer InversionOfControl del ActionForm durante el testing.
            for (int i = 0; i < numLangs; i++) {
                traducciones.add(RequestUtils.applicationInstance(traClassName));
            }
        } catch (Throwable t) {
            log.error("Error creando traducciones", t);
        }
        log.info("salimos del reset");

    }

    private String getTraduccionClassName(HttpServletRequest request, ActionMapping mapping) {
        ModuleConfig config = RequestUtils.getModuleConfig(request, getServlet().getServletContext());
        TraFormBeanConfig beanConfig = (TraFormBeanConfig) config.findFormBeanConfig(mapping.getName());
        String className = beanConfig.getTraduccionClassName();
        return className;
    }
}
