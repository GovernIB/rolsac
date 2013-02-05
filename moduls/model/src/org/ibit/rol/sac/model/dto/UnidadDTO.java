package org.ibit.rol.sac.model.dto;

import java.io.Serializable;

public class UnidadDTO implements Serializable {

    private static final long serialVersionUID = -1227937193155485933L;

    private Long id;
    private String nom;
    private String abreviatura;
    private Long idPare;
    private boolean filles;

    public UnidadDTO() {
        super();
    }

    public UnidadDTO(Long id, String nom, String abreviatura, Long idPare, boolean filles) {
        super();
        this.id = id;
        this.nom = nom;
        this.setAbreviatura(abreviatura);
        this.idPare = idPare;
        this.filles = filles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Long getidPare() {
        return idPare;
    }

    public void setidPare(Long id) {
        this.idPare = id;
    }

    public boolean getFilles() {
        return filles;
    }

    public void setFills(boolean filles) {
        this.filles = filles;
    }

}
