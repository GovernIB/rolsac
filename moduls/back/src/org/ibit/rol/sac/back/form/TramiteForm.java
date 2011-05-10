/**
 * User: jhorrach
 * Date: Feb 27, 2004
 * Time: 12:03:30 PM
 */
package org.ibit.rol.sac.back.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TramiteForm extends TraDynaValidatorForm{

    protected static Log log = LogFactory.getLog(TramiteForm.class);

    public void setDataCaducitat(Date fechaCaducidad) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaCaducidad != null)
            set("textoFechaCaducidad", df.format(fechaCaducidad));
    }

    public Date getDataCaducitat() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaCaducidad"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setDataPublicacio(Date fechaPublicacion) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaPublicacion != null)
            set("textoFechaPublicacion", df.format(fechaPublicacion));
    }

    public Date getDataPublicacio() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaPublicacion"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setDataActualitzacio(Date fechaActualizacion) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaActualizacion != null)
            set("textoFechaActualizacion", df.format(fechaActualizacion));
    }

    public Date getDataActualitzacio() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaActualizacion"));
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
