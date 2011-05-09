package org.ibit.rol.sac.back.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class NormativaForm extends TraDynaValidatorForm{

    protected static Log log = LogFactory.getLog(NormativaForm.class);

    public void setFecha(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fecha != null)
            set("textoFecha", df.format(fecha));
    }

    public Date getFecha() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFecha"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaBoletin(Date fechaBoletin) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaBoletin != null)
            set("textoFechaBoletin", df.format(fechaBoletin));
    }

    public Date getFechaBoletin() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaBoletin"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void initialize(ActionMapping actionMapping) {
        super.initialize(actionMapping);
        FormFile[] ficheros;
        try {
            IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
            int numLangs = delegate.listarLenguajes().size();
            ficheros = new FormFile[numLangs];
            for (int i = 0; i < numLangs; i++) {
                ficheros[i] = null;
            }
            set("ficheros", ficheros);
        } catch (Throwable t) {
            log.error("Error creando ficheros", t);
        }

    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        super.reset(mapping, request);
        FormFile[] ficheros;
        try {
            IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
            int numLangs = delegate.listarLenguajes().size();
            ficheros = new FormFile[numLangs];
            for (int i = 0; i < numLangs; i++) {
                ficheros[i] = null;
            }
            set("ficheros", ficheros);
        } catch (Throwable t) {
            log.error("Error creando ficheros", t);
        }

    }

}
