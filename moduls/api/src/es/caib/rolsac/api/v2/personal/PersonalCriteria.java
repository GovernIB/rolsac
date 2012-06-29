package es.caib.rolsac.api.v2.personal;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class PersonalCriteria extends BasicCriteria {

    private static final long serialVersionUID = 6373552059123956676L;

    private String username;
    private String nombre;
    private String funciones;
    private String cargo;
    private String email;
    private String extensionPublica;
    private String numeroLargoPublico;
    private String extensionPrivada;
    private String numeroLargoPrivado;
    private String extensionMovil;
    private String numeroLargoMovil;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtensionPublica() {
        return extensionPublica;
    }

    public void setExtensionPublica(String extensionPublica) {
        this.extensionPublica = extensionPublica;
    }

    public String getNumeroLargoPublico() {
        return numeroLargoPublico;
    }

    public void setNumeroLargoPublico(String numeroLargoPublico) {
        this.numeroLargoPublico = numeroLargoPublico;
    }

    public String getExtensionPrivada() {
        return extensionPrivada;
    }

    public void setExtensionPrivada(String extensionPrivada) {
        this.extensionPrivada = extensionPrivada;
    }

    public String getNumeroLargoPrivado() {
        return numeroLargoPrivado;
    }

    public void setNumeroLargoPrivado(String numeroLargoPrivado) {
        this.numeroLargoPrivado = numeroLargoPrivado;
    }

    public String getExtensionMovil() {
        return extensionMovil;
    }

    public void setExtensionMovil(String extensionMovil) {
        this.extensionMovil = extensionMovil;
    }

    public String getNumeroLargoMovil() {
        return numeroLargoMovil;
    }

    public void setNumeroLargoMovil(String numeroLargoMovil) {
        this.numeroLargoMovil = numeroLargoMovil;
    }

}
