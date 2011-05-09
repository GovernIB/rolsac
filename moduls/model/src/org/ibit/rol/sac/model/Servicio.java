package org.ibit.rol.sac.model;



/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 13-may-2004
 * Time: 11:34:29
 * To change this template use Options | File Templates.
 */
public class Servicio implements ValueObject {

    private String serviceKey;

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String puntoDeAcceso;

    public String getPuntoDeAcceso() {
        return puntoDeAcceso;
    }

    public void setPuntoDeAcceso(String puntoDeAcceso) {
        this.puntoDeAcceso = puntoDeAcceso;
    }

    private String idEntidad;

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }


    private String idModelo;

    public String getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(String idModelo) {
        this.idModelo = idModelo;
    }

    private String nombreEntidad;

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    private String nombreModelo;

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
}
