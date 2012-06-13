package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByPaginaInicialCriteria extends ByLongCriteria {

    public NormativaByPaginaInicialCriteria(String i18nAlias) {
        super(i18nAlias + ".paginaInicial");
    }

}
