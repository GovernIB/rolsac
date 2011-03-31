package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

import org.ibit.rol.sac.model.Archivo;

/**
 * Clase que representa la información a transferir de un Archivo(PORMAD)
 */
public class ArchivoTransferible implements Serializable {

	
    private String mime;
    private String nombre;
    private Long peso;
    private byte[] datos;
	
	public ArchivoTransferible() {
    }

    public ArchivoTransferible(Long id, String mime, String nombre, long peso) {
        this.mime = mime;
        this.nombre = nombre;
        this.peso = peso;
    }


    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }
    
    public static ArchivoTransferible generar(final Archivo archivo){
    	ArchivoTransferible archivoTransferible = null;
    	if(archivo!=null){
    		archivoTransferible = new ArchivoTransferible();
    		archivoTransferible.setDatos(archivo.getDatos());
    		archivoTransferible.setMime(archivo.getMime());
    		archivoTransferible.setNombre(archivo.getNombre());
			archivoTransferible.setPeso(archivo.getPeso());
    	}
    	return archivoTransferible;
    }
}
