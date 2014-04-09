package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.AgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agrupacioFetsVitals/")
public class ArchivoAgrupacionHechoVitalController extends ArchivoController {
	
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
		AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();
		Archivo archivo = null;
		
		//Obtener archivo seg�n el parametro "t" (tipo)		
		switch (tipo) {
		
			//Foto
			case 1:
				archivo = agrupacionHVDelegate.obtenerFoto(id);
				break;
				
			//Icono
			case 2:			
				archivo = agrupacionHVDelegate.obtenerIcono(id);
				break;

			//Icono grande
			case 3:			
				archivo = agrupacionHVDelegate.obtenerIconoGrande(id);
				break;
		}  
		
		return archivo;
		
	}
	
}
