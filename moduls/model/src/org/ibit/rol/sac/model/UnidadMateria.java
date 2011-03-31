package org.ibit.rol.sac.model;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 07-jun-2007
 * Time: 13:29:44
 * (PORMAD)
 */
public class UnidadMateria extends Traducible {

    private Long id;

    private String unidadPrincipal;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Materia materia;

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    private UnidadAdministrativa unidad;

    public UnidadAdministrativa getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadAdministrativa unidad) {
        this.unidad = unidad;
    }

	public String getUnidadPrincipal() {
		return unidadPrincipal;
	}

	public void setUnidadPrincipal(String unidadPrincipal) {
		this.unidadPrincipal = unidadPrincipal;
	}
}
