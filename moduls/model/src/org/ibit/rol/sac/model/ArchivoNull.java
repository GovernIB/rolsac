package org.ibit.rol.sac.model;

/*
 * Arxiu buit, es pot usar per tal d'evitar condicionals del tipus archivo==null   
 * 
 * 
 */
public class ArchivoNull extends Archivo {

	@Override public Long getId() {return 0L;}
	@Override public byte[] getDatos() {return new byte[0]; }
	@Override public String getMime() {return ""; }
	@Override public String getNombre() { return ""; }
	@Override public long getPeso() {return 0L; }
	
}
