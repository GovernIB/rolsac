package es.caib.rolsac.api.v2.iniciacio;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface IniciacioQueryService {
	
	public List<IniciacioQueryServiceAdapter> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws QueryServiceException;
	
	public IniciacioQueryServiceAdapter obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws QueryServiceException;

}
