package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;


public interface FormulariQueryServiceStrategy {

    public ArxiuDTO obtenirArchivo(Long idArchivo) throws StrategyException;

    public ArxiuDTO obtenirManual(Long idManual) throws StrategyException;

    public TramitDTO obtenirTramit(Long idTramit) throws StrategyException;

}
