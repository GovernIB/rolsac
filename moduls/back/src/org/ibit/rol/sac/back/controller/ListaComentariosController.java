package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.ComentarioDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * Obtiene la lista de comentarios.
 */
public class ListaComentariosController implements Controller {

    protected static Log log = LogFactory.getLog(ListaComentariosController.class);

    public void perform(ComponentContext tileContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        log.info("Entro en ListaComentariosController...");
        Long idRel = (Long) tileContext.getAttribute("idRel");
        String tipo = (String) tileContext.getAttribute("tipo");
        log.info("Obtengo comentarios de " + tipo + "(" + idRel + ")");

        ComentarioDelegate comentarioDelegate = DelegateUtil.getComentarioDelegate();
        List comentarios = null;

        try {
            if ("ficha".equals(tipo)) {
                comentarios = comentarioDelegate.listarComentariosFicha(idRel);
            } else if ("procedimiento".equals(tipo)) {
                comentarios = comentarioDelegate.listarComentariosProcedimiento(idRel);
            } else {
                log.warn("Tipo de comentario '" + tipo + "' desconocido");
            }
        } catch (DelegateException e) {
            log.error("Error listando comentarios", e);
        }

        tileContext.putAttribute("comentarios", comentarios);
    }
}
