package org.ibit.rol.sac.integracion.uddi;

import java.io.Serializable;

/**
 * Representación de los datos de un TModel de especificación de servicio
 * mediante wsdl.
 */
public class Especificacion implements Serializable {

    private String clave;
    private String nombre;
    private String descripcion;
    private String descripcionDocumento;
    private String urlDocumento;

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

    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }
}


