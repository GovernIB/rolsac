package es.caib.rolsac.api.v2.fetVital;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FetVitalCriteria extends BasicCriteria {

    private String orden;
    private String codigoEstandar;
    
    private String t_nombre;
    private String t_descripcion;
    private String t_palabrasclave;
    

    /**
     * @return the orden
     */
    public String getOrden() {
        return orden;
    }
    /**
     * @param orden the orden to set
     */
    public void setOrden(String orden) {
        this.orden = orden;
    }
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
    /**
     * @return the t_descripcion
     */
    public String getT_descripcion() {
        return t_descripcion;
    }
    /**
     * @param t_descripcion the t_descripcion to set
     */
    public void setT_descripcion(String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }
    /**
     * @return the t_palabrasclave
     */
    public String getT_palabrasclave() {
        return t_palabrasclave;
    }
    /**
     * @param t_palabrasclave the t_palabrasclave to set
     */
    public void setT_palabrasclave(String t_palabrasclave) {
        this.t_palabrasclave = t_palabrasclave;
    }
    
}
