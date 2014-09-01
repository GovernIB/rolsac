package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByPaginaFinalCriteria extends ByLongCriteria {

    public NormativaByPaginaFinalCriteria(String i18nAlias) {
        super(i18nAlias + ".paginaFinal");
    }

}
