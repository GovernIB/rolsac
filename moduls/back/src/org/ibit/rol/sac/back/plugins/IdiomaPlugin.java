package org.ibit.rol.sac.back.plugins;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;

/**
 * Fijar los lenguajes usados por la aplicación
 */
public class IdiomaPlugin implements PlugIn {

    public static final String LANGS_KEY = "org.ibit.rol.sac.back.LANGS_KEY";

    ActionServlet servlet;

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        IdiomaDelegate delegate = DelegateUtil.getIdiomaDelegate();
        this.servlet = servlet;

        try {
            this.servlet.getServletContext().setAttribute(LANGS_KEY, delegate.listarLenguajes());
        } catch (DelegateException e) {
            throw new UnavailableException("No se pudieron encontrar los idiomas.");
        }
    }

    public void destroy() {
        servlet.getServletContext().removeAttribute(LANGS_KEY);
        servlet = null;
    }

}
