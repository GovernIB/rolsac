package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class MateriaAgrupacioCriteria extends BasicCriteria {

    private static final long serialVersionUID = -8876063554517332845L;

    private String orden;

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

}
