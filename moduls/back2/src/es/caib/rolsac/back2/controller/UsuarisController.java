package es.caib.rolsac.back2.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.ibit.rol.sac.model.UnidadAdministrativa;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/usuaris/")
public class UsuarisController {
    
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/llistat.do", method = GET)
    public String llistatMateries(Map<String, Object> model, HttpSession session) {
        model.put("menu", 2);
        model.put("submenu", "layout/submenuUsuaris.jsp");
        model.put("escriptori", "pantalles/Usuaris.jsp");

//        if (session.getAttribute("unidadAdministrativa")!=null){
//            model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
//            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa("ca"));            
//        }        
        
        // TODO: cargar los datos de BB.DD.

        return "index";
    }
}
