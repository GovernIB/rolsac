package es.caib.rolsac.api.v2.seccio;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class SeccioCriteria extends BasicCriteria {
    
    private String codigoEstandard;
    private String perfil;
    private String orden; // int
    private Boolean arrel = null;

    // traducible
    private String t_nombre;
    private String t_descripcion;

    public String getCodigoEstandard() {
        return codigoEstandard;
    }

    public void setCodigoEstandard(String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getT_nombre() {
        return t_nombre;
    }

    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }

    public String getT_descripcion() {
        return t_descripcion;
    }

    public void setT_descripcion(String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }

    public Boolean getArrel() {
        return arrel;
    }

    public void setArrel(Boolean arrel) {
        this.arrel = arrel;
    }
   
    
}
