package es.caib.rolsac.api.v2.procediment;

import java.util.Date;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class ProcedimentCriteria extends BasicCriteria {

    // camps normals
    private String signatura;
    private Date fechaCaducidad;
    private Date fechaPublicacion;
    private Date fechaActualizacion;
    private Boolean actiu;
    private String tramite;
    private String version;
    private String url;
    private String orden;
    private String orden2;
    private String orden3;
    private String validacion;
    private Boolean indicador;
    private Boolean ventanillaUnica;
    private String info;
    private String responsable;
    private Boolean taxa;

    // camps traduibles
    private String t_nombre;
    private String t_resumen;
    private String t_destinatarios;
    private String t_resultat;
    private String t_requisitos;
    private String t_plazos;
    private String t_resolucion;
    private String t_notificacion;
    private String t_silencio;
    private String t_recursos;
    private String t_observaciones;
    private String t_lugar;

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
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

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getOrden2() {
        return orden2;
    }

    public void setOrden2(String orden2) {
        this.orden2 = orden2;
    }

    public String getOrden3() {
        return orden3;
    }

    public void setOrden3(String orden3) {
        this.orden3 = orden3;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public Boolean getIndicador() {
        return indicador;
    }

    public void setIndicador(Boolean indicador) {
        this.indicador = indicador;
    }

    public Boolean getVentanillaUnica() {
        return ventanillaUnica;
    }

    public void setVentanillaUnica(Boolean ventanillaUnica) {
        this.ventanillaUnica = ventanillaUnica;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Boolean getTaxa() {
        return taxa;
    }

    public void setTaxa(Boolean taxa) {
        this.taxa = taxa;
    }

    public String getT_nombre() {
        return t_nombre;
    }

    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }

    public String getT_resumen() {
        return t_resumen;
    }

    public void setT_resumen(String t_resumen) {
        this.t_resumen = t_resumen;
    }

    public String getT_destinatarios() {
        return t_destinatarios;
    }

    public void setT_destinatarios(String t_destinatarios) {
        this.t_destinatarios = t_destinatarios;
    }

    public String getT_resultat() {
        return t_resultat;
    }

    public void setT_resultat(String t_resultat) {
        this.t_resultat = t_resultat;
    }

    public String getT_requisitos() {
        return t_requisitos;
    }

    public void setT_requisitos(String t_requisitos) {
        this.t_requisitos = t_requisitos;
    }

    public String getT_plazos() {
        return t_plazos;
    }

    public void setT_plazos(String t_plazos) {
        this.t_plazos = t_plazos;
    }

    public String getT_resolucion() {
        return t_resolucion;
    }

    public void setT_resolucion(String t_resolucion) {
        this.t_resolucion = t_resolucion;
    }

    public String getT_notificacion() {
        return t_notificacion;
    }

    public void setT_notificacion(String t_notificacion) {
        this.t_notificacion = t_notificacion;
    }

    public String getT_silencio() {
        return t_silencio;
    }

    public void setT_silencio(String t_silencio) {
        this.t_silencio = t_silencio;
    }

    public String getT_recursos() {
        return t_recursos;
    }

    public void setT_recursos(String t_recursos) {
        this.t_recursos = t_recursos;
    }

    public String getT_observaciones() {
        return t_observaciones;
    }

    public void setT_observaciones(String t_observaciones) {
        this.t_observaciones = t_observaciones;
    }

    public String getT_lugar() {
        return t_lugar;
    }

    public void setT_lugar(String t_lugar) {
        this.t_lugar = t_lugar;
    }

}
