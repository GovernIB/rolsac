package es.caib.rolsac.back2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ibit.rol.sac.model.Archivo;

public abstract class ArchivoController {
        
    protected final void devolverArchivo(HttpServletRequest request, HttpServletResponse response)  throws Exception {

    	Archivo archivo = obtenerArchivo(request);
    	if ( archivo != null ) {
    		response.reset();
    		response.setContentType( archivo.getMime() );
    		response.setHeader("Content-Disposition", "inline; filename=\"" + archivo.getNombre() + "\"");
            response.addHeader("cache-response-directive", "no-cache");
    		response.setContentLength( archivo.getDatos().length );
    		response.getOutputStream().write( archivo.getDatos() );
    	}
    }
       
    public abstract Archivo obtenerArchivo(HttpServletRequest request) throws Exception;    
    
}
