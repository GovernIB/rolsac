package es.caib.rolsac.api.v2.fitxa;

import java.util.Date;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class FitxaDTO extends EntitatRemota {

    protected Long id;
    private Date fechaPublicacion;
    private Date fechaCaducidad;
    private Date fechaActualizacion;
    private String urlVideo;   
    private String urlForo;      
    private String foro_tema;
    private Integer validacion;
    private String info;
    private String responsable;
    private Long icono;
    private Long imagen;
    private Long baner;
    
    // TraduccioFitxa
    private String titulo;
    private String descAbr;
    private String descripcion;
    private String url;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
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
    public Integer getValidacion() {
        return validacion;
    }
    /**
     * @param validacion the validacion to set
     */
    public void setValidacion(Integer validacion) {
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * @return the descAbr
     */
    public String getDescAbr() {
        return descAbr;
    }
    /**
     * @param descAbr the descAbr to set
     */
    public void setDescAbr(String descAbr) {
        this.descAbr = descAbr;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }    

    /**
     * @return the icono
     */
    public Long getIcono() {
        return icono;
    }
    /**
     * @param icono the icono to set
     */
    public void setIcono(Long icono) {
        this.icono = icono;
    }
    /**
     * @return the imagen
     */
    public Long getImagen() {
        return imagen;
    }
    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Long imagen) {
        this.imagen = imagen;
    }
    /**
     * @return the baner
     */
    public Long getBaner() {
        return baner;
    }
    /**
     * @param baner the baner to set
     */
    public void setBaner(Long baner) {
        this.baner = baner;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FitxaDTO other = (FitxaDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
