package org.ibit.rol.sac.model;

/**
 * Clase histórico. 
 * 
 * @author slromero
 *
 */
public class Historico implements ValueObject {

	 /** Serial version UID. **/
	private static final long serialVersionUID = 1L;
	/** Id. **/
	private Long id;
	/** Nombre. **/
	private String nombre;
	    
	
    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * #417 Bug traspas normativas. 
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
    	//#417 Se había probado en poner fuera de esta clase, sino en cada sitiio antes de 
    	//   setear la variable, pero así evitamos el problema de duplicar la lógica en muchos sitios.
    	if (nombre != null && nombre.length() >= 511) {
    		this.nombre = nombre.substring(0, 511);
    	} else {
    		this.nombre = nombre;
    	}
    }
   
}
