package es.caib.traspasa.config;

import org.apache.struts.action.ActionFormBean;

/**
 *
 */
public class TraFormBeanConfig extends ActionFormBean {

    protected String traduccionClassName;

    public String getTraduccionClassName() {
        return traduccionClassName;
    }

    public void setTraduccionClassName(String traduccionClassName) {
        if (configured) {
            throw new IllegalStateException("Configuration is frozen");
        }
        this.traduccionClassName = traduccionClassName;
    }

}