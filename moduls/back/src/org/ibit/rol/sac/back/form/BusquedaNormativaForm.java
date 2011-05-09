package org.ibit.rol.sac.back.form;

import org.apache.struts.validator.DynaValidatorForm;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 02-feb-2006
 * Time: 15:15:22
 * To change this template use File | Settings | File Templates.
 */
public class BusquedaNormativaForm extends DynaValidatorForm {

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

}
