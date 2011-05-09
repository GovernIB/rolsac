package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 13-jun-2007
 * Time: 15:17:46
 * Clase que representa la información traducida a transferir de una ficha(PORMAD)
 */
public class TraduccionFichaTransferible extends AbstractTraduccion implements Serializable {


    //TRADUCCIONFICHA

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescAbr() {
        return descAbr;
    }

    public void setDescAbr(String descAbr) {
        this.descAbr = descAbr;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String titulo;
    private String descAbr;
    private String descripcion;
    private String url;
    //TRADUCCIONFICHA


}
