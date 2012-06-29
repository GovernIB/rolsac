package es.caib.rolsac.api.v2.formulari.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceEJBStrategy implements FormulariQueryServiceStrategy {

    FormulariQueryServiceDelegate formulariQueryServiceDelegate;    

    public void setFormulariQueryServiceDelegate(FormulariQueryServiceDelegate formulariQueryServiceDelegate) {
        this.formulariQueryServiceDelegate = formulariQueryServiceDelegate;
    }

    public ArxiuDTO obtenirArchivo(Long idArchivo) throws StrategyException {
        try {
            return formulariQueryServiceDelegate.obtenirArchivo(idArchivo);
        } catch (DelegateException e) {
             throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirManual(Long idManual) throws StrategyException {
        try {
            return formulariQueryServiceDelegate.obtenirManual(idManual);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TramitDTO obtenirTramit(Long idTramit) throws StrategyException {
        try {
            return formulariQueryServiceDelegate.obtenirTramit(idTramit);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
}
