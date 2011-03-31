package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.AuditoriaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.model.Auditoria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AuditoriaUAController implements Controller{
    protected static Log log = LogFactory.getLog(AuditoriaUAController.class);

    public void perform(ComponentContext tilesContext,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       ServletContext servletContext)
           throws ServletException, IOException {

        AuditoriaDelegate auditoriaDelegate = DelegateUtil.getAuditoriaDelegate();

        try{
            if(request.getParameter("idSelect")!=null){
                log.info("entro aqui UA controller");
                Long idUnidad = new Long(request.getParameter("idSelect"));
                log.info("idunidad"+idUnidad);
                List auditorias = auditoriaDelegate.listarAuditoriasUnidadAdministrativa(idUnidad);
                request.setAttribute("listaAuditoriasUA", auditorias);
            }
        }catch(DelegateException e){
            //System.out.println(e);
            log.error(e);
        }
    }

}
