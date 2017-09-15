package es.caib.rolsac.api.v2.documentoNormativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;

public interface DocumentoNormativaQueryServiceStrategy {

	public NormativaDTO obtenirNormativa(long idNormativa) throws StrategyException;
	
	public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException;
	
	public void setUrl(String url);
}

