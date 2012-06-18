package es.caib.rolsac.api.v2.formulari.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceDelegate {
    
    private FormulariQueryServiceEJBLocator formulariQueryServiceLocator;

    public void setFormulariQueryServiceLocator(FormulariQueryServiceEJBLocator formulariQueryServiceLocator) {
        this.formulariQueryServiceLocator = formulariQueryServiceLocator;
    }

    public ArxiuDTO obtenirArchivo(Long idArchivo) {
        FormulariQueryServiceEJB ejb = formulariQueryServiceLocator.getFormulariQueryServiceEJB();
        return ejb.obtenirArchivo(idArchivo);
    }

    public ArxiuDTO obtenirManual(Long idmanual) {
        FormulariQueryServiceEJB ejb = formulariQueryServiceLocator.getFormulariQueryServiceEJB();
        return ejb.obtenirManual(idmanual);
    }

    public TramitDTO obtenirTramit(Long idtramit) {
        FormulariQueryServiceEJB ejb = formulariQueryServiceLocator.getFormulariQueryServiceEJB();
        return ejb.obtenirTramit(idtramit);
    }

}
