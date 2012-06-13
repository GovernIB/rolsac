package es.caib.rolsac.api.v2.document;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class DocumentCriteria extends BasicCriteria {
    
    private String orden;
    
    //Traduccion
    
    private String t_titulo;
    private String t_descripcion;

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
     * @return the t_titulo
     */
    public String getT_titulo() {
        return t_titulo;
    }
    /**
     * @param t_titulo the t_titulo to set
     */
    public void setT_titulo(String t_titulo) {
        this.t_titulo = t_titulo;
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
    
}
