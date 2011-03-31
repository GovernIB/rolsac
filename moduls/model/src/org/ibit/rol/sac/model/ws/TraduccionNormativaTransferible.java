package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

public class TraduccionNormativaTransferible extends AbstractTraduccion implements Serializable {

    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private String titulo;

}
