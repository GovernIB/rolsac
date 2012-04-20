package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

public class TraduccionNormativaTransferible extends AbstractTraduccion implements Serializable {

    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public ArchivoTransferible getArchivo() {
		return archivo;
	}

	public void setArchivo(ArchivoTransferible archivo) {
		this.archivo = archivo;
	}

	private String titulo;
    private String enlace;
    private ArchivoTransferible archivo;

}
