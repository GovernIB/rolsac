package org.ibit.rol.sac.integracion.uddi;

import java.io.Serializable;

/**
 * Representación de los datos de un servicio web.
 */
public class ServicioWeb implements Serializable {

    private String clave;
    private String nombre;
    private String descripcion;
    private String puntoAcceso;

    private String claveEspecificacion;
    private String claveOrganismo;

    private String nombreOrganismo;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuntoAcceso() {
        return puntoAcceso;
    }

    public void setPuntoAcceso(String puntoAcceso) {
        this.puntoAcceso = puntoAcceso;
    }

    public String getClaveEspecificacion() {
        return claveEspecificacion;
    }

    public void setClaveEspecificacion(String claveEspecificacion) {
        this.claveEspecificacion = claveEspecificacion;
    }

    public String getClaveOrganismo() {
        return claveOrganismo;
    }

    public void setClaveOrganismo(String claveOrganismo) {
        this.claveOrganismo = claveOrganismo;
    }

    public String getNombreOrganismo() {
        return nombreOrganismo;
    }

    public void setNombreOrganismo(String nombreOrganismo) {
        this.nombreOrganismo = nombreOrganismo;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServicioWeb)) return false;

        final ServicioWeb servicioWeb = (ServicioWeb) o;

        if (clave != null ? !clave.equals(servicioWeb.clave) : servicioWeb.clave != null) return false;

        return true;
    }

    public int hashCode() {
        return (clave != null ? clave.hashCode() : 0);
    }
}
