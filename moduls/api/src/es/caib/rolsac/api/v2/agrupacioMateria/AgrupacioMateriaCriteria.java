package es.caib.rolsac.api.v2.agrupacioMateria;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class AgrupacioMateriaCriteria extends BasicCriteria {

    private String codigoEstandar;
    
    //Traduccion
    private String t_nombre;

    /**
     * @return the codigoEstandar
     */
    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    /**
     * @param codigoEstandar the codigoEstandar to set
     */
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

    /**
     * @return the t_nombre
     */
    public String getT_nombre() {
        return t_nombre;
    }

    /**
     * @param t_nombre the t_nombre to set
     */
    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }


    
}
