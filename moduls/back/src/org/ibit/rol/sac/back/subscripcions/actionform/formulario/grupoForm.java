package org.ibit.rol.sac.back.subscripcions.actionform.formulario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.TraduccionGrupoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.back.subscripcions.actionform.TraDynaActionForm;


public class grupoForm extends TraDynaActionForm {

	// Necesario para el formateo de las Fechas

    protected static Log log = LogFactory.getLog(grupoForm.class);

    /*
    public void setFechaEnvio(Date fecha) {
    	if (fecha != null) {
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		set("fechaEnvio", df.format(fecha));
    	}
    }

    public Date getFechaEnvio() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha=(String) get("fechaEnvio");
        if (fecha.length()==10) fecha+=" 00:00";
        try {
            return df.parse(fecha);
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaEnviado(Date fecha) {
    	if (fecha != null) {
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		set("fechaEnviado", df.format(fecha));
    	}
    }

    public Date getFechaEnviado() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha=(String) get("fechaEnviado");
        if (fecha.length()==10) fecha+=" 00:00";
        try {
            return df.parse(fecha);
        } catch (ParseException pe) {
            return null;
        }
    }

    
    public void setFechaAlta(Date fecha) {
    	if (fecha != null) {
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		set("fechaAlta", df.format(fecha));
    	}
    }

    public Date getFechaAlta() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha=(String) get("fechaAlta");
        if (fecha.length()==10) fecha+=" 00:00";
        try {
            return df.parse(fecha);
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaModificacion(Date fecha) {
    	if (fecha != null) {
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		set("fechaModificacion", df.format(fecha));
    	}
    }

    public Date getFechaModificacion() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha=(String) get("fechaModificacion");
        if (fecha.length()==10) fecha+=" 00:00";
        try {
            return df.parse(fecha);
        } catch (ParseException pe) {
            return null;
        }
    }

    public void setFechaBaja(Date fecha) {
    	if (fecha != null) {
    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		set("fechaBaja", df.format(fecha));
    	}
    }

    public Date getFechaBaja() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha=(String) get("fechaBaja");
        if (fecha.length()==10) fecha+=" 00:00";
        try {
            return df.parse(fecha);
        } catch (ParseException pe) {
            return null;
        }
    }
    */
    public void initialize(ActionMapping actionMapping) {
        super.initialize(actionMapping);
        //inicio();        
    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        super.reset(mapping, request);
        //inicio();
    }

    /*private void inicio() {
    	set("activo", "S");
    }
   */
    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

    	ActionErrors errors = new ActionErrors();
    	
    	if(httpServletRequest.getParameter("modifica")!=null || httpServletRequest.getParameter("anyade")!=null) {
    	/*
    		if (!Fechas.FechaValida(""+get("fechaEnvio"))) 
    			errors.add("fechaEnvio", new ActionError("error.grupo.inicio"));
    		String dato = (String) get("estado");
    		if((dato == null) || dato.equals(""))
    			errors.add("estado", new ActionError("error.grupo.estado"));
    		dato = (String) get("asunto");
    		if((dato == null) || dato.equals(""))
    			errors.add("estado", new ActionError("error.grupo.asunto"));
    		dato = (String) get("canalEnvio");
    		if((dato == null) || dato.equals(""))
    			errors.add("canalEnvio", new ActionError("error.grupo.canalEnvio"));
    		dato = (String) get("canalEnvio");
    		if((dato == null) || dato.equals(""))
    			errors.add("titulo", new ActionError("error.grupo.titulo"));
    		
    		Long idSeccion = (Long) get("idSeccion");
    		if((idSeccion == null) || idSeccion.intValue() == 0)
    			errors.add("idSeccion", new ActionError("error.grupo.seccion"));
    	    			*/
            try {
            	IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
            	List lang = idiomaDelegate.listarLenguajes();
            	
            	for (int i=0;i<lang.size();i++) {
            		if (lang.get(i).equals(idiomaDelegate.lenguajePorDefecto())) {
            			TraduccionGrupoSuscripcion  trad = (TraduccionGrupoSuscripcion)((ArrayList)get("traducciones")).get(i);
            			if (trad.getNombre().length()==0)
            				errors.add("titulo", new ActionError("error.grupo.titulo"));
            		}
            	}
    			
            } catch (Throwable t) {
                log.error("Error comprobando nombres de grupo", t);
            }    
    		
    	}
    	
    	
    	return errors;

    }
        

    
}
