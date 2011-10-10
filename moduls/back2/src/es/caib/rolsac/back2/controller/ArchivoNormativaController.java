package es.caib.rolsac.back2.controller;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/normativa/")
public class ArchivoNormativaController extends ArchivoController {
    
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/archivo.htm", method = GET)
    public void llistatMateries(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }


	@Override
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {		
        //obtener archivo concreto con el delegate
        Long idNorm = new Long(request.getParameter("id"));
        String lang = request.getParameter("lang");
        NormativaDelegate normDelegate = DelegateUtil.getNormativaDelegate();
        return normDelegate.obtenerArchivoNormativa(idNorm, lang, false);
	}
}
