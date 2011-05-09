package org.ibit.rol.sac.back.subscripcions.actionform.busca;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.*;
import java.util.List;

public class BuscaOrdenaActionForm extends ActionForm
{

    public BuscaOrdenaActionForm()
    {
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest)
    {
        return null;
    }

    public void reset(ActionMapping actionmapping, HttpServletRequest httpservletrequest)
    {
    }

    public void setFiltro(String filtro)
    {
        this.filtro = filtro;
    }

    public String getFiltro()
    {
        return filtro;
    }

    public void setOrdenacion(String ordenacion)
    {
        this.ordenacion = ordenacion;
    }

    public String getOrdenacion()
    {
        return ordenacion;
    }

    public void setFiltroEstado(String filtroEstado)
    {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroEstado()
    {
        return filtroEstado;
    }

    public void setFiltroGrupo(String filtroGrupo)
    {
        this.filtroEstado = filtroEstado;
    }

    public String getFiltroGrupo()
    {
        return filtroEstado;
    }
    
    private String filtro;
    private String ordenacion;
    private String filtroEstado;
    //private String filtroGrupo;    
    //private List grupos;
}