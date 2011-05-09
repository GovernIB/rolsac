package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Marilen
 * Date: 16-jul-2010
 * Time: 13:57:14
 * To change this template use File | Settings | File Templates.
 */
public class TraduccionTramiteTransferible extends AbstractTraduccion  implements Serializable {

    private String nombre;
    private String descripcion;
    private String documentacion;
    private String requisits;
    private String plazos;
    private String observaciones;
    private String lugar;

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

    public String getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    public String getRequisits() {
        return requisits;
    }

    public void setRequisits(String requisits) {
        this.requisits = requisits;
    }

    public String getPlazos() {
        return plazos;
    }

    public void setPlazos(String plazos) {
        this.plazos = plazos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
