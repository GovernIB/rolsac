package es.caib.rolsac.api.v2.unitatAdministrativa;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class UnitatAdministrativaCriteria extends BasicCriteria {
    
    private String businessKey;
    private String claveHita;
    private String dominio;
    private String orden;
    private String validacion;
    private String responsable;
    private String telefono;
    private String fax;
    private String email;
    private String sexoResponsable;    
    private String codigoEstandar;
    
    //Traducion UA    
    private String t_nombre;
    private String t_presentacion;
    private String t_abreviatura;
    
    public String getBusinessKey() {
        return businessKey;
    }
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
    public String getClaveHita() {
        return claveHita;
    }
    public void setClaveHita(String claveHita) {
        this.claveHita = claveHita;
    }
    public String getDominio() {
        return dominio;
    }
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    public String  getOrden() {
        return orden;
    }
    public void setOrden(String  orden) {
        this.orden = orden;
    }
    public String  getValidacion() {
        return validacion;
    }
    public void setValidacion(String  validacion) {
        this.validacion = validacion;
    }
    public String getResponsable() {
        return responsable;
    }
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String  getSexoResponsable() {
        return sexoResponsable;
    }
    public void setSexoResponsable(String sexoResponsable) {
        this.sexoResponsable = sexoResponsable;
    }
    public String getCodigoEstandar() {
        return codigoEstandar;
    }
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }
    public String getT_nombre() {
        return t_nombre;
    }
    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }
    public String getT_presentacion() {
        return t_presentacion;
    }
    public void setT_presentacion(String t_presentacion) {
        this.t_presentacion = t_presentacion;
    }
    public String getT_abreviatura() {
        return t_abreviatura;
    }
    public void setT_abreviatura(String t_abreviatura) {
        this.t_abreviatura = t_abreviatura;
    }
}
