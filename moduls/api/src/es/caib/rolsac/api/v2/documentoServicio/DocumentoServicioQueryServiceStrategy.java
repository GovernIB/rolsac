package es.caib.rolsac.api.v2.documentoServicio;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;

public interface DocumentoServicioQueryServiceStrategy {

//	public NormativaDTO obtenirNormativa(long idNormativa) throws StrategyException;
	
	public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException;
	
	public void setUrl(String url);
}

