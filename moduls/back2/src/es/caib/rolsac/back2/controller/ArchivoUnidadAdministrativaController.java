package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/unitatadm/")
public class ArchivoUnidadAdministrativaController extends ArchivoController {
	
    @SuppressWarnings("unused")
	private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/archivo.do", method = GET)
    public void llistatMateries(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }
    
	@Override 
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {

		Long id = new Long(request.getParameter("id"));
		Integer tipo = new Integer(request.getParameter("tipus"));
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
		Archivo archivo = null;
		
		//Obtener archivo según el parámetro "t" (tipo)		
		switch (tipo) {
		
			//Foto responsable pequeña
			case 1:
				archivo = uaDelegate.obtenerFotoPequenyaUA(id);
				break;
				
			//Foto responsable grande
			case 2:			
				archivo = uaDelegate.obtenerFotoGrandeUA(id);
				break;
			
			//Logotipo horizontal
			case 3:
				archivo = uaDelegate.obtenerLogoHorizontalUA(id);
				break;
				
			//Logotipo vertical
			case 4:
				archivo = uaDelegate.obtenerLogoVerticalUA(id);
				break;
				
			//Logo saludo horizontal
			case 5:
				archivo = uaDelegate.obtenerLogoSaludoUA(id);
				break;
				
			//Logo saludo vertical
			case 6:
				archivo = uaDelegate.obtenerLogoSaludoVerticalUA(id);
				break;			
		}  
		
		return archivo;
		
	}
	
}
