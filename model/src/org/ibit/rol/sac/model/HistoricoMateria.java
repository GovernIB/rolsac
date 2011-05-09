package org.ibit.rol.sac.model;


public class HistoricoMateria extends Historico {

    public Materia getMateria() {
        return mat;
    }

    public void setMateria(Materia mate) {
        this.mat = mate;
    }

    private Materia mat;
}