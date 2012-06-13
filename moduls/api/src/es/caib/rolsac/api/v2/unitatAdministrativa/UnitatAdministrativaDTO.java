package es.caib.rolsac.api.v2.unitatAdministrativa;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class UnitatAdministrativaDTO extends EntitatRemota {

    
    protected Long id;
    private String businessKey;
    private String claveHita;
    private String dominio;
    private long orden;
    private Integer validacion;
    private String responsable;
    private String telefono;
    private String fax;
    private String email;
    private Integer sexoResponsable;
    private Integer numfoto1;
    private Integer numfoto2;
    private Integer numfoto3;
    private Integer numfoto4;
    private Long fotop;
    private Long fotog;
    private Long logoh;
    private Long logov;
    private Long logos;
    private Long logot;
    private String codigoEstandar;

    private Long padre;
    private Long tratamiento;
    private Long espacioTerrit;
    
    //String tipus;

    // TraduccioUA
    private String nombre;
    private String presentacion;
    private String abreviatura;
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
     * @return the businessKey
     */
    public String getBusinessKey() {
        return businessKey;
    }
    /**
     * @param businessKey the businessKey to set
     */
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
    /**
     * @return the claveHita
     */
    public String getClaveHita() {
        return claveHita;
    }
    /**
     * @param claveHita the claveHita to set
     */
    public void setClaveHita(String claveHita) {
        this.claveHita = claveHita;
    }
    /**
     * @return the dominio
     */
    public String getDominio() {
        return dominio;
    }
    /**
     * @param dominio the dominio to set
     */
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }
    /**
     * @return the orden
     */
    public long getOrden() {
        return orden;
    }
    /**
     * @param orden the orden to set
     */
    public void setOrden(long orden) {
        this.orden = orden;
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
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }
    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the sexoResponsable
     */
    public Integer getSexoResponsable() {
        return sexoResponsable;
    }
    /**
     * @param sexoResponsable the sexoResponsable to set
     */
    public void setSexoResponsable(Integer sexoResponsable) {
        this.sexoResponsable = sexoResponsable;
    }
    /**
     * @return the numfoto1
     */
    public Integer getNumfoto1() {
        return numfoto1;
    }
    /**
     * @param numfoto1 the numfoto1 to set
     */
    public void setNumfoto1(Integer numfoto1) {
        this.numfoto1 = numfoto1;
    }
    /**
     * @return the numfoto2
     */
    public Integer getNumfoto2() {
        return numfoto2;
    }
    /**
     * @param numfoto2 the numfoto2 to set
     */
    public void setNumfoto2(Integer numfoto2) {
        this.numfoto2 = numfoto2;
    }
    /**
     * @return the numfoto3
     */
    public Integer getNumfoto3() {
        return numfoto3;
    }
    /**
     * @param numfoto3 the numfoto3 to set
     */
    public void setNumfoto3(Integer numfoto3) {
        this.numfoto3 = numfoto3;
    }
    /**
     * @return the numfoto4
     */
    public Integer getNumfoto4() {
        return numfoto4;
    }
    /**
     * @param numfoto4 the numfoto4 to set
     */
    public void setNumfoto4(Integer numfoto4) {
        this.numfoto4 = numfoto4;
    }
    /**
     * @return the fotop
     */
    public Long getFotop() {
        return fotop;
    }
    /**
     * @param fotop the fotop to set
     */
    public void setFotop(Long fotop) {
        this.fotop = fotop;
    }
    /**
     * @return the fotog
     */
    public Long getFotog() {
        return fotog;
    }
    /**
     * @param fotog the fotog to set
     */
    public void setFotog(Long fotog) {
        this.fotog = fotog;
    }
    /**
     * @return the logoh
     */
    public Long getLogoh() {
        return logoh;
    }
    /**
     * @param logoh the logoh to set
     */
    public void setLogoh(Long logoh) {
        this.logoh = logoh;
    }
    /**
     * @return the logov
     */
    public Long getLogov() {
        return logov;
    }
    /**
     * @param logov the logov to set
     */
    public void setLogov(Long logov) {
        this.logov = logov;
    }
    /**
     * @return the logos
     */
    public Long getLogos() {
        return logos;
    }
    /**
     * @param logos the logos to set
     */
    public void setLogos(Long logos) {
        this.logos = logos;
    }
    /**
     * @return the logot
     */
    public Long getLogot() {
        return logot;
    }
    /**
     * @param logot the logot to set
     */
    public void setLogot(Long logot) {
        this.logot = logot;
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
     * @return the padre
     */
    public Long getPadre() {
        return padre;
    }
    /**
     * @param padre the padre to set
     */
    public void setPadre(Long padre) {
        this.padre = padre;
    }
    /**
     * @return the tratamiento
     */
    public Long getTratamiento() {
        return tratamiento;
    }
    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(Long tratamiento) {
        this.tratamiento = tratamiento;
    }
    /**
     * @return the espacioTerrit
     */
    public Long getEspacioTerrit() {
        return espacioTerrit;
    }
    /**
     * @param espacioTerrit the espacioTerrit to set
     */
    public void setEspacioTerrit(Long espacioTerrit) {
        this.espacioTerrit = espacioTerrit;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the presentacion
     */
    public String getPresentacion() {
        return presentacion;
    }
    /**
     * @param presentacion the presentacion to set
     */
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }
    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UnitatAdministrativaDTO other = (UnitatAdministrativaDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
