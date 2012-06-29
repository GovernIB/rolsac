package es.caib.rolsac.api.v2.procediment;

import java.util.Date;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class ProcedimentDTO extends EntitatRemota {

    private static final long serialVersionUID = 8141854682354603049L;

    protected Long id;
    
    protected Long familia;
    protected Long organResolutori;
    protected Long unidadAdministrativa;
    protected Long iniciacion;

    protected boolean ventanillaUnica;
    protected boolean indicador;
    protected boolean taxa;

    protected Date fechaCaducidad;
    protected Date fechaPublicacion;
    protected Date fechaActualizacion;

    protected String signatura;
    protected Integer validacion;
    protected String tramite;
    protected Long version;
    protected String info;
    protected String url;
    protected Long orden;
    protected Long orden2;
    protected Long orden3;
    protected String responsable;

    // Camps de la traduccio (TraduccionProcedimientoLocal)
    protected String nombre;
    protected String resolucion;
    protected String notificacion;
    protected String resultat;
    protected String resumen;
    protected String destinatarios;
    protected String requisitos;
    protected String plazos;
    protected String silencio;
    protected String recursos;
    protected String observaciones;
    protected String lugar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFamilia() {
        return familia;
    }

    public void setFamilia(Long familia) {
        this.familia = familia;
    }

    public Long getOrganResolutori() {
        return organResolutori;
    }

    public void setOrganResolutori(Long organResolutori) {
        this.organResolutori = organResolutori;
    }

    public Long getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(Long unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Long getIniciacion() {
        return iniciacion;
    }

    public void setIniciacion(Long iniciacion) {
        this.iniciacion = iniciacion;
    }

    public boolean isVentanillaUnica() {
        return ventanillaUnica;
    }

    public void setVentanillaUnica(boolean ventanillaUnica) {
        this.ventanillaUnica = ventanillaUnica;
    }

    public boolean isIndicador() {
        return indicador;
    }

    public void setIndicador(boolean indicador) {
        this.indicador = indicador;
    }

    public boolean isTaxa() {
        return taxa;
    }

    public void setTaxa(boolean taxa) {
        this.taxa = taxa;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    public Integer getValidacion() {
        return validacion;
    }

    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Long getOrden2() {
        return orden2;
    }

    public void setOrden2(Long orden2) {
        this.orden2 = orden2;
    }

    public Long getOrden3() {
        return orden3;
    }

    public void setOrden3(Long orden3) {
        this.orden3 = orden3;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getPlazos() {
        return plazos;
    }

    public void setPlazos(String plazos) {
        this.plazos = plazos;
    }

    public String getSilencio() {
        return silencio;
    }

    public void setSilencio(String silencio) {
        this.silencio = silencio;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}