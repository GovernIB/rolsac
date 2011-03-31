/* Generated by Together */

package org.ibit.rol.sac.model;


public class TraduccionHechoVital implements Traduccion {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPalabrasclave() {
        return palabrasclave;
    }

    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

    public Archivo getDistribComp() {
        return distribComp;
    }

    public void setDistribComp(Archivo distribComp) {
        this.distribComp = distribComp;
    }

    public Archivo getContenido() {
        return contenido;
    }

    public void setContenido(Archivo contenido) {
        this.contenido = contenido;
    }

     public Archivo getNormativa() {
        return normativa;
    }

    public void setNormativa(Archivo normativa) {
        this.normativa = normativa;
    }
    
   /* public String getContenidoString(){
    	if(contenido!=null){
    		return new String(contenido);
    	}
    	return null;
    }
    
    public void setContenidoString(String contenido) {
        if(contenido!=null){
    		this.contenido = contenido.getBytes();
        }else{
        	this.contenido=null;
        }
    }
*/
    private String nombre;
    private String descripcion;
    private String palabrasclave;
    private Archivo distribComp;
    private Archivo contenido;
    private Archivo normativa;



}
