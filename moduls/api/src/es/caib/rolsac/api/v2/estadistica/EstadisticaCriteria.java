package es.caib.rolsac.api.v2.estadistica;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class EstadisticaCriteria extends BasicCriteria {

    int numRes;
    String idioma;
    String text;
    String resultat;
    String consulta;

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public int getNumRes() {
        return numRes;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getText() {
        return text;
    }

    public String getResultat() {
        return resultat;
    }

    public String getConsulta() {
        return consulta;
    }

}
