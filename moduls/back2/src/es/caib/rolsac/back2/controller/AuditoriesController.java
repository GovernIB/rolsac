package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.model.dto.AuditoriaDTO;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.ParseUtil;

@Controller
@RequestMapping("/auditories/")
public class AuditoriesController {
    
	private static Log log = LogFactory.getLog(AuditoriesController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }


    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatUsuaris(HttpServletRequest request, HttpSession session) {

		List<AuditoriaDTO> llistaDTO = new ArrayList<AuditoriaDTO>();
		Map<String, Object> resultats = new HashMap<String, Object>();
		String tipus = request.getParameter("tipus");
		long id = ParseUtil.parseLong(request.getParameter("id"));
		
		try {
			AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();
			List<Auditoria> llista = new ArrayList<Auditoria>();

			if (tipus.equals("procediment")) {
				llista = auditoriaDelegate.listarAuditoriasProcedimiento(id);
			} else if (tipus.equals("fitxa")) {
				llista = auditoriaDelegate.listarAuditoriasFicha(id);
			} else if (tipus.equals("unitat")) {
				llista = auditoriaDelegate.listarAuditoriasUnidadAdministrativa(id);
			} else if (tipus.equals("normativa")) {
				llista = auditoriaDelegate.listarAuditoriasNormativa(id);
			} 
			
			for (Auditoria auditoria : llista) {
				llistaDTO.add(new AuditoriaDTO(
						auditoria.getId(),
						auditoria.getUsuario(),
						auditoria.getCodigoOperacion(),
						messageSource.getMessage("op."+auditoria.getCodigoOperacion(), null, request.getLocale()),
						auditoria.getFecha()
				 ));
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Error de permisos: " + ExceptionUtils.getStackTrace(dEx));
			} else {
				log.error("Error: " + ExceptionUtils.getStackTrace(dEx));
			}
		}
		
		//Ordenamos descendiente
		Collections.sort(llistaDTO);
		Collections.reverse(llistaDTO);

		resultats.put("total", llistaDTO.size());
		resultats.put("nodes", llistaDTO);

		return resultats;
	}
    
    
}
