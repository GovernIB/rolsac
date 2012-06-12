package es.caib.rolsac.back2.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.ibit.rol.sac.model.UnidadAdministrativa;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/administracio/")
/**
 * TODO: Parece que no se usa, comprobar y borrar.
 */
public class AdministracioController extends PantallaBaseController {
    
    @RequestMapping(value = "/llistat.do", method = GET)
    public String llistatMateries(Map<String, Object> model, HttpSession session) {
        model.put("menu", 3);
        model.put("submenu", "layout/submenu/submenuAdministracio.jsp");
        model.put("escriptori", "pantalles/Administracio.jsp");

//        if (session.getAttribute("unidadAdministrativa")!=null){
//            model.put("idUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getId());
//            model.put("nomUA",((UnidadAdministrativa)session.getAttribute("unidadAdministrativa")).getNombreUnidadAdministrativa("ca"));            
//        }        
        
        // TODO: cargar los datos de BB.DD.

        return "index";
    }
}
