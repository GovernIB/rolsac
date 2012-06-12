package es.caib.rolsac.api.v1;

import java.io.Serializable;

import org.ibit.rol.sac.model.Archivo;

public class ArchivoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String mime;
	private String nombre;
	private long peso;
	//private byte[] datos;

	public ArchivoDTO( Archivo archivo, String idioma )
	{
		this.id = archivo.getId();
		this.nombre = archivo.getNombre();
		this.peso = archivo.getPeso();
		//this.datos = archivo.getDatos();
		this.mime = archivo.getMime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public long getPeso() {
		return peso;
	}

	public void setPeso(long peso) {
		this.peso = peso;
	}
/*
	public byte[] getDatos() {
		return datos;
	}

	public void setDatos(byte[] datos) {
		this.datos = datos;
	}
*/
}
