package es.caib.rolsac.api.v2.afectacio.ejb;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

public class AfectacioQueryServiceEJB {

    public NormativaDTO obtenirAfectant(long idAfectant) {
        NormativaCriteria normativaCriteria = new NormativaCriteria(); 
        normativaCriteria.setId(String.valueOf(idAfectant));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirNormativa(normativaCriteria);
    }

    public NormativaDTO obtenirNormativa(long idNormativa) {
        NormativaCriteria normativaCriteria = new NormativaCriteria(); 
        normativaCriteria.setId(String.valueOf(idNormativa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirNormativa(normativaCriteria);
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) {
        TipusAfectacioCriteria tipusAfectacioCriteria = new TipusAfectacioCriteria();
        tipusAfectacioCriteria.setId(String.valueOf(idTipusAfectacio));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirTipusAfectacio(tipusAfectacioCriteria);
    }

}
