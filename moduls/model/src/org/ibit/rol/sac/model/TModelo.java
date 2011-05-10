package org.ibit.rol.sac.model;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 24-may-2004
 * Time: 9:26:42
 */
public class TModelo implements ValueObject {

    private String tModeloKey;

    public String gettModeloKey() {
        return tModeloKey;
    }

    public void settModeloKey(String tModeloKey) {
        this.tModeloKey = tModeloKey;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String overviewDoc;

    public String getOverviewDoc() {
        return overviewDoc;
    }

    public void setOverviewDoc(String overviewDoc) {
        this.overviewDoc = overviewDoc;
    }

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
