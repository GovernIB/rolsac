package org.ibit.rol.sac.model.ws;

import java.io.Serializable;


public class TraduccionDocumentoTransferible extends AbstractTraduccion implements Serializable {


	 public String getTitulo() {
	        return titulo;
	    }

	    public void setTitulo(String titulo) {
	        this.titulo = titulo;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }
 	    
	    public ArchivoTransferible getArchivoTransferible() {
			return archivoTransferible;
		}

		public void setArchivoTransferible(ArchivoTransferible archivoTransferible) {
			this.archivoTransferible = archivoTransferible;
		}

	    private String titulo;
	    private String descripcion;
		private ArchivoTransferible archivoTransferible;
	   
	


}
