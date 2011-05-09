package org.ibit.rol.sac.model;

/**
 * Bean que representa los datos de un destinatario al que hay que notificar
 * las actualizaciones de la información(PORMAD)
 */
public class Destinatario implements ValueObject {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String endpoint;
	private String email;
	
	//Id necesario para ser reconocidos por el Destinatario
    private String idRemoto;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Id necesario para ser reconocidos por el Destinatario
	 * @return Id necesario para ser reconocidos por el Destinatario
	 */
	public String getIdRemoto() {
		return idRemoto;
	}
	
	public void setIdRemoto(String idRemoto) {
		this.idRemoto = idRemoto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
