package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/materies/")
public class ArchivoMateriaController extends ArchivoController {
	
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
		
		MateriaDelegate materiaDelegate = DelegateUtil.getMateriaDelegate();
		Archivo archivo = null;
		
		//Obtener archivo según el parametro "t" (tipo)		
		switch (tipo) {
			//Foto
			case 1:
				archivo = materiaDelegate.obtenerFoto(id);
				break;
				
			// Icono
			case 2:			
				archivo = materiaDelegate.obtenerIcono(id);
				break;
			
			// Icono grande
			case 3:
				archivo = materiaDelegate.obtenerIconoGrande(id);
				break;
				
			// Distribucion
			case 4:
				archivo = materiaDelegate.obtenerDistribComp(id, lang, false);
				break;
				
			// Normativa
			case 5:
				archivo = materiaDelegate.obtenerNormativa(id, lang, false);
				break;
				
			// Contenido
			case 6:
				archivo = materiaDelegate.obtenerContenido(id, lang, false);
				break;			
		}  
		
		return archivo;
	}
	
}
