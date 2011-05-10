package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Clase que representa la información traducida a transferir de un Procedimiento(PORMAD)
 */
public class TraduccionProcedimientoTransferible extends AbstractTraduccion implements Serializable {
	
	
	private String nombre;
    private String resumen; //objeto  VUDS
    private String destinatarios;
    private String requisitos;
    private String plazos; //presentacion VUDS
    private String silencio;
    private String recursos;
    private String observaciones;
    private String lugar;
    private String codigoEstandarIdioma;

   //VUDS
    private String resultat;
    private String resolucion;
    private String notificacion;


    public String getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getPlazos() {
		return plazos;
	}
	public void setPlazos(String plazos) {
		this.plazos = plazos;
	}
	public String getRecursos() {
		return recursos;
	}
	public void setRecursos(String recursos) {
		this.recursos = recursos;
	}
	public String getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getSilencio() {
		return silencio;
	}
	public void setSilencio(String silencio) {
		this.silencio = silencio;
	}
    public String getCodigoEstandarIdioma() {
        return codigoEstandarIdioma;
    }

    public void setCodigoEstandarIdioma(String codigoEstandarIdioma) {
        this.codigoEstandarIdioma = codigoEstandarIdioma;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
}
