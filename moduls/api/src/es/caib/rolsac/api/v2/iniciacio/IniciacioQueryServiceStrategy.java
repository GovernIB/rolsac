package es.caib.rolsac.api.v2.iniciacio;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;

public interface IniciacioQueryServiceStrategy {
	
	public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws StrategyException;
	
	public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws StrategyException;

	public void setUrl(String url);

}
