package org.ibit.rol.sac.back.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;

public class DocumentoForm extends TraDynaValidatorForm{

    protected static Log log = LogFactory.getLog(DocumentoForm.class);

    public static IdiomaDelegate idiomaDelegate;  //u92770[enric] - afegit per unit testing. es static pq  DocumentoForm no es crea a un factory de spring (no es creen les conexions)   
    
    public void initialize(ActionMapping actionMapping) {
        super.initialize(actionMapping);
        FormFile[] fichers;
        try {
            IdiomaDelegate delegate = null==idiomaDelegate? DelegateUtil.getIdiomaDelegate() : idiomaDelegate;
            int numLangs = delegate.listarLenguajes().size();
            fichers = new FormFile[numLangs];
            for (int i = 0; i < numLangs; i++) {
                fichers[i] = null;
            }
            set("fichers", fichers);
        } catch (Throwable t) {
            log.error("Error creando ficheros", t);
        }

    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        super.reset(mapping, request);
        FormFile[] fichers;
        try {
            IdiomaDelegate delegate = null==idiomaDelegate? DelegateUtil.getIdiomaDelegate() : idiomaDelegate;
            int numLangs = delegate.listarLenguajes().size();
            fichers = new FormFile[numLangs];
            for (int i = 0; i < numLangs; i++) {
                fichers[i] = null;
            }
            set("fichers", fichers);
        } catch (Throwable t) {
            log.error("Error creando distribs", t);
        }

    }

}
