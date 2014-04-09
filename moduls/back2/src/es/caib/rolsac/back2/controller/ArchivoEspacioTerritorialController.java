package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/espaisTerritorials/")
public class ArchivoEspacioTerritorialController extends ArchivoController {
	
    @SuppressWarnings("unused")
	private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/archivo.do", method = GET)
    public void llistatEspaisTerritorials(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }
    
	@Override 
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {

		Long id = new Long(request.getParameter("id"));
		Integer tipo = new Integer(request.getParameter("tipus"));
		
		EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
		Archivo archivo = null;
		
		//Obtener archivo seg�n el parametro "t" (tipo)		
		switch (tipo) {
			// Mapa
			case 1:
				archivo = espacioDelegate.obtenerMapaEspacio(id);
				break;
				
			// Logo
			case 2:			
				archivo = espacioDelegate.obtenerLogoEspacio(id);
				break;
			
		}  
		
		return archivo;
	}
	
}
