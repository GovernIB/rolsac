package org.ibit.rol.sac.back.subscripcions.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Formulario que se corresponde con la vista del Listado
 * 
 * Autor: Fausto Navarro
 * Copyright INDRA 2006
 * 
 */
public class listaActionForm  extends ActionForm {

private String[] seleccionados;  // elementos de la lista seleccionados
private boolean todonada; // Check de selección/deselección
private String accion; // acción ejecutada

    
    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }
    
    
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

    // Limpiamos el array de objetos checkbox seleccionados
    seleccionados=null;
    setTodonada(false);
   }



    public void setTodonada(boolean todonada)
    {
        this.todonada = todonada;
    }


    public boolean isTodonada()
    {
        return todonada;
    }


    public void setSeleccionados(String[] seleccionados)
    {
        this.seleccionados = seleccionados;
    }


    public String[] getSeleccionados()
    {
        return seleccionados;
    }


    public void setAccion(String accion)
    {
        this.accion = accion;
    }


    public String getAccion()
    {
        return accion;
    }


}

