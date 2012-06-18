package es.caib.rolsac.api.v2.formulari.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceEJBStrategy implements FormulariQueryServiceStrategy {

    FormulariQueryServiceDelegate formulariQueryServiceDelegate;    

    public void setFormulariQueryServiceDelegate(FormulariQueryServiceDelegate formulariQueryServiceDelegate) {
        this.formulariQueryServiceDelegate = formulariQueryServiceDelegate;
    }

    public ArxiuDTO obtenirArchivo(Long idArchivo) {
        return formulariQueryServiceDelegate.obtenirArchivo(idArchivo);
    }

    public ArxiuDTO obtenirManual(Long idManual) {
        return formulariQueryServiceDelegate.obtenirManual(idManual);
    }

    public TramitDTO obtenirTramit(Long idTramit) {
        return formulariQueryServiceDelegate.obtenirTramit(idTramit);
    }
}
