/* Generated by Together */

package org.ibit.rol.sac.model;

public class Tratamiento extends Traducible {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

     public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

    private Long id;
    private String codigoEstandar;

    public String getNombreTratamiento(String idioma) {
        TraduccionTratamiento ttr = (TraduccionTratamiento) getTraduccion(idioma);
        return ttr == null ? null : ttr.getTipo();
    }
    
}
