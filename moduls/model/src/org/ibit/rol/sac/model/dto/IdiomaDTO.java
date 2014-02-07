package org.ibit.rol.sac.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.ValueObject;

public class IdiomaDTO implements ValueObject {

    private String lang;
    private String codigoEstandar;
    private int orden;
    private String nombre;
    private String langTraductor;

    public IdiomaDTO() {}

    public IdiomaDTO(String lang, String codigoEstandar, int orden, String nombre, String langTraductor) {
        super();
        this.lang           = lang;
        this.codigoEstandar = codigoEstandar;
        this.orden          = orden;
        this.nombre         = nombre;
        this.langTraductor  = langTraductor;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLangTraductor() {
        return langTraductor;
    }

    public void setLangTraductor(String langTraductor) {
        this.langTraductor = langTraductor;
    }

    public static List<IdiomaDTO> idiomaToIdomaDTO(List<Idioma> listaIdiomas) {

        List<IdiomaDTO> lista = new ArrayList<IdiomaDTO>(listaIdiomas.size());
        for (Idioma idioma : listaIdiomas) {
            lista.add(new IdiomaDTO(
                idioma.getLang(),
                idioma.getCodigoEstandar(),
                idioma.getOrden(),
                idioma.getNombre(),
                idioma.getLangTraductor()
            ));
        }

        return lista;
    }
}
