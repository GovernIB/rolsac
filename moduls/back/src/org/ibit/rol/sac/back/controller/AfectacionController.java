/**
 * User: jhorrach
 * Date: Mar 26, 2004
 * Time: 11:14:10 AM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.TipoAfectacionDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

public class AfectacionController implements Controller {
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {

            TipoAfectacionDelegate tafectacionDelegate = DelegateUtil.getTipoAfectacionDelegate();
            NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();

            request.setAttribute("tafectacionOptions", tafectacionDelegate.listarTiposAfectaciones());
            request.setAttribute("normativaOptions", normativaDelegate.listarNormativas());

        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }


}
