package org.ibit.rol.sac.model.dto;


public class ListadoModuloTramiteDTO extends IdNomDTO {

    private static final long serialVersionUID = 7245073701720708757L;
    
    private int moment;
	
	public ListadoModuloTramiteDTO() {
		super();
	}
	
	public ListadoModuloTramiteDTO(Long id, String nom, int moment) {
		super(id, nom);
		this.moment = moment;
	}

    public int getMoment() {
		return moment;
	}

    public void setMoment(int moment) {
		this.moment = moment;
	}
	
	@Override
    public String getJson() {
		return "{\"id\" : \"" + getId() + "\", \"nom\" : \"" + getNom() + ", \"moment\": \"" + moment + "\"}";
	}
	
}
