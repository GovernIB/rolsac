package org.ibit.rol.sac.back.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;

public class HechoVitalForm extends TraDynaValidatorForm{

    protected static Log log = LogFactory.getLog(HechoVitalForm.class);

    public void initialize(ActionMapping actionMapping) {
        super.initialize(actionMapping);
        FormFile[] distribs;
        FormFile[] normats;
        FormFile[] contens;
        try {
            IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
            int numLangs = delegate.listarLenguajes().size();
            distribs = new FormFile[numLangs];
            normats = new FormFile[numLangs];
            contens = new FormFile[numLangs];
            for (int i = 0; i < numLangs; i++) {
                distribs[i] = null;
                normats[i] = null;
                contens[i] = null;
            }
            set("distribs", distribs);
            set("normats", normats);
            set("contens", contens);
        } catch (Throwable t) {
            log.error("Error creando ficheros", t);
        }

    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        super.reset(mapping, request);
        FormFile[] distribs;
        FormFile[] normats;
        FormFile[] contens;
        try {
            IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
            int numLangs = delegate.listarLenguajes().size();
            distribs = new FormFile[numLangs];
            normats = new FormFile[numLangs];
            contens = new FormFile[numLangs];
            for (int i = 0; i < numLangs; i++) {
                distribs[i] = null;
                normats[i] = null;
                contens[i] = null;
            }
            set("distribs", distribs);
            set("normats", normats);
            set("contens", contens);
        } catch (Throwable t) {
            log.error("Error creando distribs", t);
        }

    }

}
