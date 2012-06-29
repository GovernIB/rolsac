package es.caib.rolsac.api.v2.tipusAfectacio;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class TipusAfectacioCriteria extends BasicCriteria {
    
    private static final long serialVersionUID = 8667843456946406670L;

    private String t_nombre;

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
