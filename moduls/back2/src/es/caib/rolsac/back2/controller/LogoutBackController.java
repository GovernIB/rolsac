package es.caib.rolsac.back2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LogoutBackController {
 
    @RequestMapping(value="/logout.do")        
	public String logout(HttpSession session) throws ServletException, IOException {
 
        if (session != null) {
            session.invalidate();
        }		
 
		return "logout";
	}
}
