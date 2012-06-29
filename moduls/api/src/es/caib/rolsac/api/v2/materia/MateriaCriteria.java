package es.caib.rolsac.api.v2.materia;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class MateriaCriteria extends BasicCriteria {
    
    private static final long serialVersionUID = 1146682800143659707L;
    
    private String codiHita;
    private String codigoEstandar;
    private Boolean destacada;

    // TraduccioMateria
    
    private String t_nombre;
    private String t_descripcion;
    private String t_palabrasclave;
    
    /**
     * @return the codiHita
     */
    public String getCodiHita() {
        return codiHita;
    }
    /**
     * @param codiHita the codiHita to set
     */
    public void setCodiHita(String codiHita) {
        this.codiHita = codiHita;
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
     * @return the destacada
     */
    public Boolean isDestacada() {
        return destacada;
    }
    /**
     * @param destacada the destacada to set
     */
    public void setDestacada(Boolean destacada) {
        this.destacada = destacada;
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
