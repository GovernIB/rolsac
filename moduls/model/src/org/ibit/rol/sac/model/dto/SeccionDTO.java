package org.ibit.rol.sac.model.dto;

import java.io.Serializable;

public class SeccionDTO implements Comparable<Object>, Serializable  {

	private static final long serialVersionUID = -1227937193155485933L;

	private Long id;
	private String nom;
	private Long idPare;
	private boolean filles;
	
	public SeccionDTO() {
		super();
	}
	
	public SeccionDTO(Long id, String nom, Long idPare, boolean filles) {
		super();
		this.id = id;
		this.nom = nom;
		this.idPare = idPare;
		this.filles = filles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Long getidPare() {
        return idPare;
    }

    public void setidPare(Long id) {
        this.idPare = id;
    }
	
    public boolean getFilles() {
        return filles;
    }

    public void setFills(boolean filles) {
        this.filles = filles;
    }    
    
    public int compareTo( Object o ) {    	
    	
    	String primerNom;
    	String segonNom;
		
    	primerNom = getNom().toLowerCase().replaceAll("[באה]", "a")
			 							  .replaceAll("[יטכ]", "e")
			 							  .replaceAll("[םלן]", "i")
			 							  .replaceAll("[ףעצ]", "o")
			 							  .replaceAll("[תשü]", "u");
		
    	segonNom = ((SeccionDTO)o).getNom().toLowerCase().replaceAll("[באה]", "a")
											 		     .replaceAll("[יטכ]", "e")
											 		     .replaceAll("[םלן]", "i")
											 		     .replaceAll("[ףעצ]", "o")
											 		     .replaceAll("[תשü]", "u");
    	
    	return primerNom.compareTo( segonNom );
    }
    
}
