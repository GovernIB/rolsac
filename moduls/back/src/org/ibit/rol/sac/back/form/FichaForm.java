package org.ibit.rol.sac.back.form;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Formulario de fichas informativas
 */
public class FichaForm extends TraDynaValidatorForm{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2450403088329368184L;
	
	protected static Log log = LogFactory.getLog(FichaForm.class);

    /**
     * Setters y Getters
     */

    public void setFechaPublicacion(Date fechaPublicacion) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (fechaPublicacion != null) {
            set("textoFechaPublicacion", df.format(fechaPublicacion));
        }
        else
        	set("textoFechaPublicacion", "");
    }
    
    public void setFechaCaducidad(Date fechaCaducidad) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (fechaCaducidad != null) {
            set("textoFechaCaducidad", df.format(fechaCaducidad));
        }
        else
        	set("textoFechaCaducidad", "");
    }
    
    public void setFechaActualizacion(Date fechaActualizacion) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (fechaActualizacion != null) {
            set("textoFechaActualizacion", df.format(fechaActualizacion));
        }
    }    

    public Date getFechaPublicacion() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return df.parse((String) get("textoFechaPublicacion"));
        } catch (ParseException pe) {
            return null;
        }
    }
    
    public Date getFechaCaducidad() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return df.parse((String) get("textoFechaCaducidad"));
        } catch (ParseException pe) {
            return null;
        }
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
