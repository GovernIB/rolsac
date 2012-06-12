package es.caib.rolsac.back2.controller;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edifici/")
public class ArchivoEdificioController extends ArchivoController {
	
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
		EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
		Archivo archivo = null;
		
		//Obtener archivo según el parametro "t" (tipo)		
		switch (tipo) {
		
			//Foto foto pequeno
			case 1:
				archivo = edificioDelegate.obtenerFotoPequenyaEdificio(id);
				break;
				
			//Foto foto grande
			case 2:			
				archivo = edificioDelegate.obtenerFotoGrandeEdificio(id);
				break;
			
			//Foto foto grande
			case 3:			
				archivo = edificioDelegate.obtenerPlanoEdificio(id);
				break;
		}  
		
		return archivo;
		
	}    
}
