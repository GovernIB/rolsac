/**
 * User: jhorrach
 * Date: Dec 11, 2003
 * Time: 11:45:47 AM
 */
package org.ibit.rol.sac.back.form;

import org.apache.struts.validator.DynaValidatorForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.DynaProperty;

import javax.servlet.http.HttpServletRequest;

public class IconoForm extends DynaValidatorForm {
/*
    protected static Log log = LogFactory.getLog(IconoForm.class);

    private FormFile icono;


    public FormFile getIcono() {
        return icono;
    }


    public void setIcono(FormFile icono) {
        this.icono = icono;
    }


    protected DynaProperty getDynaProperty(String s) {
        log.debug("detDynaProperty >>>>>>>> " + s);
        return super.getDynaProperty(s);
    }


    public Object get(String s) {
        log.debug("get >>>>>>>>>>>>>>>" + s);
        if ("icono".equals(s)) {
            return getIcono();
        } else {
            return super.get(s);
        }
    }


    public void set(String s, Object o) {
        log.debug("set >>>>>>>>>>>>>" + s);
        if ("icono".equals(s)) {
            setIcono((FormFile) o);
        } else {
            super.set(s, o);
        }
    }



    public void reset(ActionMapping mapping, HttpServletRequest request) {
        log.debug("reset >>>>>>>>>>>>>>");
        super.initialize(mapping);
        super.reset(mapping, request);
        this.icono = null;
    }
*/
}
