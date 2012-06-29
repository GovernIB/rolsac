package es.caib.rolsac.api.v2.normativa;

import java.util.Date;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class NormativaCriteria extends BasicCriteria {

    private static final long serialVersionUID = 1930472039717697492L;

    private String numero;
    private String registro;
    private String ley;
    private Date fecha;
    private Date fechaBoletin;
    private String validacion;
    private String codiVuds;
    private String descCodiVuds;
    private Boolean incluirExternas;

    // Traduccion

    private String t_seccion;
    private String t_apartado;
    private String t_paginaInicial;
    private String t_paginaFinal;
    private String t_titulo;
    private String t_enlace;
    private String t_observaciones;
    private String t_responsable;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getLey() {
        return ley;
    }

    public void setLey(String ley) {
        this.ley = ley;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaBoletin() {
        return fechaBoletin;
    }

    public void setFechaBoletin(Date fechaBoletin) {
        this.fechaBoletin = fechaBoletin;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public String getCodiVuds() {
        return codiVuds;
    }

    public void setCodiVuds(String codiVuds) {
        this.codiVuds = codiVuds;
    }

    public String getDescCodiVuds() {
        return descCodiVuds;
    }

    public void setDescCodiVuds(String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }

    public Boolean getIncluirExternas() {
        return incluirExternas;
    }

    public void setIncluirExternas(Boolean incluirExternas) {
        this.incluirExternas = incluirExternas;
    }

    public String getT_seccion() {
        return t_seccion;
    }

    public void setT_seccion(String t_seccion) {
        this.t_seccion = t_seccion;
    }

    public String getT_apartado() {
        return t_apartado;
    }

    public void setT_apartado(String t_apartado) {
        this.t_apartado = t_apartado;
    }

    public String getT_paginaInicial() {
        return t_paginaInicial;
    }

    public void setT_paginaInicial(String t_paginaInicial) {
        this.t_paginaInicial = t_paginaInicial;
    }

    public String getT_paginaFinal() {
        return t_paginaFinal;
    }

    public void setT_paginaFinal(String t_paginaFinal) {
        this.t_paginaFinal = t_paginaFinal;
    }

    public String getT_titulo() {
        return t_titulo;
    }

    public void setT_titulo(String t_titulo) {
        this.t_titulo = t_titulo;
    }

    public String getT_enlace() {
        return t_enlace;
    }

    public void setT_enlace(String t_enlace) {
        this.t_enlace = t_enlace;
    }

    public String getT_observaciones() {
        return t_observaciones;
    }

    public void setT_observaciones(String t_observaciones) {
        this.t_observaciones = t_observaciones;
    }

    public String getT_responsable() {
        return t_responsable;
    }

    public void setT_responsable(String t_responsable) {
        this.t_responsable = t_responsable;
    }

}
