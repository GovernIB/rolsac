package org.ibit.rol.sac.model;

public class MateriaAgrupacionM  implements ValueObject {

	private Long id;
    private Materia materia = null;
    private AgrupacionMateria agrupacion = null;
    private int orden;
    
	public AgrupacionMateria getAgrupacion() {
		return agrupacion;
	}
	public void setAgrupacion(AgrupacionMateria agrupacion) {
		this.agrupacion = agrupacion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}	
	
}
