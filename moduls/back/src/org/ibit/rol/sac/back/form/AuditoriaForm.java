package org.ibit.rol.sac.back.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class AuditoriaForm extends DynaValidatorForm{
    protected static Log log = LogFactory.getLog(AuditoriaForm.class);

    public void setFechaIni(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fecha != null)
            set("textoFechaIni", df.format(fecha));
    }

    public Date getFechaIni() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaIni"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaFin(Date fecha) {
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       if (fecha != null)
           set("textoFechaFin", df.format(fecha));
    }

    public Date getFechaFin() {
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       try {
           return df.parse((String) get("textoFechaFin"));
       } catch (ParseException pe) {
           return null;
       }
    }


    public void initialize(ActionMapping actionMapping) {
        super.initialize(actionMapping);
    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        super.reset(mapping, request);
    }

}
