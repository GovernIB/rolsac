package es.caib.rolsac.api.v2.personal;

public class PersonalDTO {

    protected Long id;
    protected Long unitatAdministrativa;

    protected String username;
    protected String nombre;
    protected String funciones;
    protected String cargo;
    protected String email;
    protected String extensionPublica;
    protected String numeroLargoPublico;
    protected String extensionPrivada;
    protected String numeroLargoPrivado;
    protected String extensionMovil;
    protected String numeroLargoMovil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnitatAdministrativa() {
        return unitatAdministrativa;
    }

    public void setUnitatAdministrativa(Long unitatAdministrativa) {
        this.unitatAdministrativa = unitatAdministrativa;
    }

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
