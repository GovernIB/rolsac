/**
 *
 */
package org.ibit.rol.sac.model;

/**
 * bean que contiene la información de la relacion entre HechoVital y Agrupacion HV (PORMAD)
 */

public class HechoVitalAgrupacionHV implements ValueObject{


    private Long id;
    private HechoVital hechoVital = null;
    private AgrupacionHechoVital agrupacion = null;
    private int orden;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HechoVital getHechoVital() {
        return hechoVital;
    }

    public void setHechoVital(HechoVital hechoVital) {
        this.hechoVital = hechoVital;
    }

    public AgrupacionHechoVital getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(AgrupacionHechoVital agrupacion) {
        this.agrupacion = agrupacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

}
