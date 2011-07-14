package org.ibit.rol.sac.back.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.List;

/**
 *
 */
public class UAController implements Controller {

    protected static Log log = LogFactory.getLog(UAController.class);

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        try {
            UnidadAdministrativaDelegate uadelegate = DelegateUtil.getUADelegate();
            TratamientoDelegate tratadelegate = DelegateUtil.getTratamientoDelegate();
            EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();

            Long id;
            String ident = request.getParameter("idUA");
            if (ident != null) {
                id = new Long(ident);
            } else {
                id = (Long) request.getAttribute("idUA");
            }

            if(id != null){
                //System.out.println("idUA " + id);
                log.debug("idUA " + id);
                UnidadAdministrativa ua = uadelegate.obtenerUnidadAdministrativa(id);

                EdificioDelegate edifDelegate = DelegateUtil.getEdificioDelegate();
                Set edificios = edifDelegate.listarEdificiosUnidad(id);
                request.setAttribute("ua", ua);
                log.debug("ua :"+ ua);
                request.setAttribute("edificios", edificios);
                log.debug("edificios :"+ edificios);

                List<EspacioTerritorial> espacios =  espacioDelegate.listarEspaciosTerritoriales();
                log.debug("espacios "+ espacios.size());
                request.setAttribute("listaTratamientos", tratadelegate.listarTratamientos());
                request.setAttribute("listaEspacios", espacioDelegate.listarEspaciosTerritoriales());

            } else {
                request.setAttribute("listaHijos", Collections.EMPTY_LIST);
                request.setAttribute("listaTratamientos", tratadelegate.listarTratamientos());
                request.setAttribute("listaEdificios", Collections.EMPTY_LIST);
                request.setAttribute("listaPersonal", Collections.EMPTY_LIST);
                request.setAttribute("listaNormativas", Collections.EMPTY_LIST);
                request.setAttribute("listaProcedimientos", Collections.EMPTY_LIST);
                request.setAttribute("listaUsuarios", Collections.EMPTY_LIST);
                request.setAttribute("listaFichasUA", Collections.EMPTY_LIST);
                request.setAttribute("ua", Collections.EMPTY_LIST);
                request.setAttribute("edificios", Collections.EMPTY_LIST);
                request.setAttribute("listaEspacios", espacioDelegate.listarEspaciosTerritoriales());

                String idPadre = request.getParameter("idPadre");
                if (idPadre != null && !idPadre.equals("")){
                    Long idp = new Long(idPadre);
                    UnidadAdministrativa padre = uadelegate.obtenerUnidadAdministrativa(idp);
                    request.setAttribute("padre", padre);
                    request.setAttribute("idPadre", idp);
                }
            }

        } catch (DelegateException e) {
            //System.out.println(e);
            log.error(e);
        }

    }
}