package es.caib.rolsac.back2.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/quadreControl/")  
public class QuadreControlController {
 
	private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value="/quadreControl.do")        
	public String quadreControl(HttpSession session, HttpServletRequest request, Map<String,Object> model) throws ServletException, IOException {
        model.put("menu", 0);
        model.put("submenu", "layout/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 0);
		model.put("titol_escriptori", messageSource.getMessage("submenu.quadre_control", null, request.getLocale()));
		model.put("escriptori", "pantalles/quadreControl.jsp");
				
		return "index";
	}
}
