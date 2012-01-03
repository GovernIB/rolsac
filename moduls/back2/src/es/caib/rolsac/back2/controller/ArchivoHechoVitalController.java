package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fetsVitals/")
public class ArchivoHechoVitalController extends ArchivoController {
	
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
		String lang = request.getParameter("lang");
		
		HechoVitalDelegate fetVitalDelegate = DelegateUtil.getHechoVitalDelegate();
		Archivo archivo = null;
		
		//Obtener archivo según el parametro "t" (tipo)		
		switch (tipo) {
			//Foto
			case 1:
				archivo = fetVitalDelegate.obtenerFoto(id);
				break;
				
			// Icono
			case 2:			
				archivo = fetVitalDelegate.obtenerIcono(id);
				break;
			
			// Icono grande
			case 3:
				archivo = fetVitalDelegate.obtenerIconoGrande(id);
				break;
				
			// Distribucion
			case 4:
				archivo = fetVitalDelegate.obtenerDistribComp(id, lang, false);
				break;
				
			// Normativa
			case 5:
				archivo = fetVitalDelegate.obtenerNormativa(id, lang, false);
				break;
				
			// Contenido
			case 6:
				archivo = fetVitalDelegate.obtenerContenido(id, lang, false);
				break;			
		}  
		
		return archivo;
	}
	
}
