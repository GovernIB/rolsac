package es.caib.rolsac.api.v2.edifici;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class EdificiDTO extends EntitatRemota {

    private static final long serialVersionUID = -4411519320993555579L;

    protected long id;

    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String telefono;
    private String fax;
    private String email;
    private String latitud;
    private String longitud;

    // Arxius
    private Long fotoPequenya;
    private Long fotoGrande;
    private Long plano;

    // TraduccioEdifici
    protected String descripcion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Long getFotoPequenya() {
        return fotoPequenya;
    }

    public void setFotoPequenya(Long fotoPequenya) {
        this.fotoPequenya = fotoPequenya;
    }

    public Long getFotoGrande() {
        return fotoGrande;
    }

    public void setFotoGrande(Long fotoGrande) {
        this.fotoGrande = fotoGrande;
    }

    public Long getPlano() {
        return plano;
    }

    public void setPlano(Long plano) {
        this.plano = plano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}