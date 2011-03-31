package org.ibit.rol.sac.model.ws;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 12-jul-2007
 * Time: 12:40:49
 * Clase que representa la información a transferir de una UnidadMateria(PORMAD)
 */
public class UnidadMateriaTransferible {

    private String codEstMateria;

    public String getCodEstMateria() {
        return codEstMateria;
    }

    public void setCodEstMateria(String codEstMateria) {
        this.codEstMateria = codEstMateria;
    }

    private TraduccionUnidadMateriaTransferible[] traducciones;

    public TraduccionUnidadMateriaTransferible[] getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(TraduccionUnidadMateriaTransferible[] traducciones) {
        this.traducciones = traducciones;
    }
}
