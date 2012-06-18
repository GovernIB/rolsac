package es.caib.rolsac.api.v2.normativa;

import java.util.Date;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class NormativaDTO extends EntitatRemota {

    protected Long id;
    private long numero;
    private long registro;
    private Integer validacion;
    private Long boletin;
    private Long tipo;
    private Long unidadAdministrativa;

    private Date fecha;
    private Date fechaBoletin;
    private String codiVuds;
    private String descCodiVuds;

    // local o externa
    private boolean local;

    // traducciones
    private String seccion;
    private String apartado;
    private Integer paginaInicial;
    private Integer paginaFinal;
    private String titulo;
    private String enlace;
    private String observaciones;
    private String responsable;
    private Long archivo;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the numero
     */
    public Long getNumero() {
        return numero;
    }

    /**
     * @param numero
     *            the numero to set
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return the registro
     */
    public Long getRegistro() {
        return registro;
    }

    /**
     * @param registro
     *            the registro to set
     */
    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha
     *            the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the fechaBoletin
     */
    public Date getFechaBoletin() {
        return fechaBoletin;
    }

    /**
     * @param fechaBoletin
     *            the fechaBoletin to set
     */
    public void setFechaBoletin(Date fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }

    /**
     * @return the validacion
     */
    public Integer getValidacion() {
        return validacion;
    }

    /**
     * @param validacion
     *            the validacion to set
     */
    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }

    /**
     * @return the codiVuds
     */
    public String getCodiVuds() {
        return codiVuds;
    }

    /**
     * @param codiVuds
     *            the codiVuds to set
     */
    public void setCodiVuds(String codiVuds) {
        this.codiVuds = codiVuds;
    }

    /**
     * @return the descCodiVuds
     */
    public String getDescCodiVuds() {
        return descCodiVuds;
    }

    /**
     * @param descCodiVuds
     *            the descCodiVuds to set
     */
    public void setDescCodiVuds(String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }

    /**
     * @return the seccion
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * @param seccion
     *            the seccion to set
     */
    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    /**
     * @return the apartado
     */
    public String getApartado() {
        return apartado;
    }

    /**
     * @param apartado
     *            the apartado to set
     */
    public void setApartado(String apartado) {
        this.apartado = apartado;
    }

    /**
     * @return the paginaInicial
     */
    public Integer getPaginaInicial() {
        return paginaInicial;
    }

    /**
     * @param paginaInicial
     *            the paginaInicial to set
     */
    public void setPaginaInicial(Integer paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    /**
     * @return the paginaFinal
     */
    public Integer getPaginaFinal() {
        return paginaFinal;
    }

    /**
     * @param paginaFinal
     *            the paginaFinal to set
     */
    public void setPaginaFinal(Integer paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the enlace
     */
    public String getEnlace() {
        return enlace;
    }

    /**
     * @param enlace
     *            the enlace to set
     */
    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones
     *            the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable
     *            the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(long registro) {
        this.registro = registro;
    }

    /**
     * @return the archivo
     */
    public Long getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(Long archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the boletin
     */
    public Long getBoletin() {
        return boletin;
    }

    /**
     * @param boletin the boletin to set
     */
    public void setBoletin(Long boletin) {
        this.boletin = boletin;
    }

    /**
     * @return the tipo
     */
    public Long getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the unidadAdministrativa
     */
    public Long getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    /**
     * @param unidadAdministrativa the unidadAdministrativa to set
     */
    public void setUnidadAdministrativa(Long unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

}
