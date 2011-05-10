
package es.caib.vuds;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class ProcedimientoVUDSForm extends TraDynaValidatorForm{

    protected static Log log = LogFactory.getLog(ProcedimientoVUDSForm.class);

    public void setFechaCaducidad(Date fechaCaducidad) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaCaducidad != null)
            set("textoFechaCaducidad", df.format(fechaCaducidad));
    }

    public Date getFechaCaducidad() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaCaducidad"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaPublicacion != null)
            set("textoFechaPublicacion", df.format(fechaPublicacion));
    }

    public Date getFechaPublicacion() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return df.parse((String) get("textoFechaPublicacion"));
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaActualizacion != null)
            set("textoFechaActualizacion", df.format(fechaActualizacion));
    }

    public Date getFechaActualizacion() {
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
