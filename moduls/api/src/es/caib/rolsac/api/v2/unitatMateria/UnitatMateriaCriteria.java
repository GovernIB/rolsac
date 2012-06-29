package es.caib.rolsac.api.v2.unitatMateria;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class UnitatMateriaCriteria extends BasicCriteria {

    private static final long serialVersionUID = -5804324638930046053L;

    private String unidadPrincipal;

    // TraduccioUnitatMateria
    private String t_urlUnidadMateria;

    /**
     * @return the unidadPrincipal
     */
    public String getUnidadPrincipal() {
        return unidadPrincipal;
    }

    /**
     * @param unidadPrincipal the unidadPrincipal to set
     */
    public void setUnidadPrincipal(String unidadPrincipal) {
        this.unidadPrincipal = unidadPrincipal;
    }

    /**
     * @return the t_urlUnidadMateria
     */
    public String getT_urlUnidadMateria() {
        return t_urlUnidadMateria;
    }

    /**
     * @param t_urlUnidadMateria the t_urlUnidadMateria to set
     */
    public void setT_urlUnidadMateria(String t_urlUnidadMateria) {
        this.t_urlUnidadMateria = t_urlUnidadMateria;
    }
    
}
