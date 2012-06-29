package es.caib.rolsac.api.v2.edifici;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class EdificiCriteria extends BasicCriteria {

    private static final long serialVersionUID = 8725741289373138522L;

    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String telefono;
    private String fax;
    private String email;
    private String latitud;
    private String longitud;

    private String t_descripcion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getT_descripcion() {
        return t_descripcion;
    }

    public void setT_descripcion(String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }

}
