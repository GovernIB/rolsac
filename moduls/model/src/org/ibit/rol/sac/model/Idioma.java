package org.ibit.rol.sac.model;

/**
 * Representaci&oacute; de un idioma.Añadido nombre(PORMAD)
 */
public class Idioma implements ValueObject {

    //public static final String DEFAULT = "ca";


    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    private String codigoEstandar;

    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

    private int orden;

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    private String langTraductor;

    public String getLangTraductor() {
        return langTraductor;
    }

    public void setLangTraductor(String langTraductor) {
        this.langTraductor = langTraductor;
    }
}
