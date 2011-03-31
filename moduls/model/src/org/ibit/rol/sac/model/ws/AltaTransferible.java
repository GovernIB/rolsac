package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 13-jun-2007
 * Time: 14:14:17
 * To change this template use File | Settings | File Templates.
 */
public class AltaTransferible implements Serializable {
	
	
	private String[] codigoEstandarMaterias;

    public String[] getCodigoEstandarMaterias() {
        return codigoEstandarMaterias;
    }

    public void setCodigoEstandarMaterias(String[] codigoEstandarMaterias) {
        this.codigoEstandarMaterias = codigoEstandarMaterias;
    }

    private String[] codigoEstandarHV;

    public String[] getCodigoEstandarHV() {
        return codigoEstandarHV;
    }

    public void setCodigoEstandarHV(String[] codigoEstandarHV) {
        this.codigoEstandarHV = codigoEstandarHV;
    }

    private Integer profundidad;

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    private String[] codigoEstandarSecciones;

    public String[] getCodigoEstandarSecciones() {
        return codigoEstandarSecciones;
    }

    public void setCodigoEstandarSecciones(String[] codigoEstandarSecciones) {
        this.codigoEstandarSecciones = codigoEstandarSecciones;
    }

    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


