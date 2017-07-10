package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administracioRemota/")
public class ArchivoAdministracionRemotaController extends ArchivoController {
	
    @SuppressWarnings("unused")
	private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/archivo.do", method = GET)
    public void retornaFitxer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }
    
	@Override 
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {

		Long id = new Long(request.getParameter("id"));
		Integer tipo = new Integer(request.getParameter("tipus"));
		AdministracionRemotaDelegate adRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
		Archivo archivo = null;
		
		//Obtener archivo segun el parametro "t" (tipo)		
		switch (tipo) {
		
			//Foto logo pequeno
			case 1:
				archivo = adRemotaDelegate.obtenerLogop(id);
				break;
				
			//Foto logo grande
			case 2:			
				archivo = adRemotaDelegate.obtenerLogog(id);
				break;
		}  
		
		return archivo;
		
	}
	
}
