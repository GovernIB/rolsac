package org.ibit.rol.sac.back.subscripcions.actionform;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;
import org.ibit.rol.sac.back.subscripcions.config.TraFormBeanConfig;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 *
 */
//public class TraDynaValidatorForm extends DynaValidatorForm {
public class TraDynaActionForm extends DynaActionForm {
	
    protected static Log log = LogFactory.getLog(TraDynaActionForm.class);

    protected String traClassName = null;


    public void reset(ActionMapping mapping, HttpServletRequest request) {
        //log.info("reset.name=" + mapping.getName() + ", path=" + mapping.getPath());
        super.reset(mapping, request);
        initialize(mapping);

        if (traClassName == null) {
            traClassName = getTraduccionClassName(request, mapping);
        }

        List traducciones = (List) this.get("traducciones");
        //if(traducciones.size() == 0) return;
        try {
            IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
            int numLangs = delegate.listarLenguajes().size();
            for (int i = 0; i < numLangs; i++) {
                traducciones.add(RequestUtils.applicationInstance(traClassName));
            }
        } catch (Throwable t) {
            log.error("Error creando traducciones", t);
        }
    }

    private String getTraduccionClassName(HttpServletRequest request, ActionMapping mapping) {
        ModuleConfig config = RequestUtils.getModuleConfig(request, getServlet().getServletContext());
        String n = mapping.getName();
        TraFormBeanConfig beanConfig = (TraFormBeanConfig) config.findFormBeanConfig(mapping.getName());
        String className = beanConfig.getTraduccionClassName();
        return className;
    }
    
    // FUNCIONES DE CHEQUEO DE CAMPOS
    
    public static boolean esMail(String email) 
    {
        if (email==null) email="";
        if (email.equals("null") || (email.length()==0)) email="";

        if (email.equals("")) return true;
        
        return email.matches(".+@.+\\.[a-z]+");
    }

    public static boolean esEntero(String numero) 
    {
        if (numero==null) numero="0";
        if (numero.equals("null") || (numero.length()==0)) numero="0";

        return numero.matches("[0-9]{1,40}");
    }
    
    public static boolean FechaValida(String in) {
    	
    if (in.length()!=10 && in.length()!=16)	return false;
    try{
    	DateFormat formato=null;
        if (in.length()==10) in=in+" 00:00";
    	if (!in.matches("[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}:[0-9]{2}")) return false;
        formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        formato.setLenient(false);
        formato.parse(in);

    }
    catch(ParseException e){
        return false;
    }

        return true;
    }
 
    public static boolean Decimal(String in, int entero, int decimal) {

        String regexp="";

        if (in==null) in="0";
        if (in.equals("null") || (in.length()==0)) in="0";

        if (in.substring(0,1).equals(",")) in="0"+in;
        if (in.lastIndexOf(',')!=-1) regexp="[0-9]{1,"+entero+"}[,][0-9]{1,2}";
        else regexp="[0-9]{1,"+entero+"}";
       
        return in.matches(regexp);
    }

    public static String Double2String(String in) {

        if (in==null) return "";
        if (in.equals("null") || (in.length()==0)) return "";
        in=in.replace('.',',');
        if (in.substring(0,1).equals(","))
            in="0"+in;
        return in;
    }    

    
}
