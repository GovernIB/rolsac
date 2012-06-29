package es.caib.rolsac.api.v2.fitxa;

import java.util.Date;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FitxaCriteria extends BasicCriteria {

    private static final long serialVersionUID = 7571667616018238385L;

    private Date fechaPublicacion;
    private Date fechaCaducidad;
    private Date fechaActualizacion;
    private String urlVideo;   
    private String urlForo;      
    private String foro_tema;
    private String validacion;
    private String info;
    private String responsable;
    
    //Traduccion
    
    private String t_titulo;
    private String t_descAbr;
    private String t_descripcion;
    private String t_url;
    
    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    /**
     * @return the fechaCaducidad
     */
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }
    /**
     * @param fechaCaducidad the fechaCaducidad to set
     */
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    /**
     * @return the fechaActualizacion
     */
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }
    /**
     * @param fechaActualizacion the fechaActualizacion to set
     */
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    /**
     * @return the urlVideo
     */
    public String getUrlVideo() {
        return urlVideo;
    }
    /**
     * @param urlVideo the urlVideo to set
     */
    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
    /**
     * @return the urlForo
     */
    public String getUrlForo() {
        return urlForo;
    }
    /**
     * @param urlForo the urlForo to set
     */
    public void setUrlForo(String urlForo) {
        this.urlForo = urlForo;
    }
    /**
     * @return the foro_tema
     */
    public String getForo_tema() {
        return foro_tema;
    }
    /**
     * @param foro_tema the foro_tema to set
     */
    public void setForo_tema(String foro_tema) {
        this.foro_tema = foro_tema;
    }
    /**
     * @return the validacion
     */
    public String getValidacion() {
        return validacion;
    }
    /**
     * @param validacion the validacion to set
     */
    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }
    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }
    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }
    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
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
     * @return the t_descAbr
     */
    public String getT_descAbr() {
        return t_descAbr;
    }
    /**
     * @param t_descAbr the t_descAbr to set
     */
    public void setT_descAbr(String t_descAbr) {
        this.t_descAbr = t_descAbr;
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
     * @return the t_url
     */
    public String getT_url() {
        return t_url;
    }
    /**
     * @param t_url the t_url to set
     */
    public void setT_url(String t_url) {
        this.t_url = t_url;
    }
    
}
