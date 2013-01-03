package es.caib.rolsac.api.v2.documentTramit;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface DocumentTramitQueryServiceStrategy {

	public TramitDTO obtenirTramit(long idTramit) throws StrategyException;
	
	public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException;
	
	public CatalegDocumentsDTO obtenirCatalegDocuments(long idCatalegDocuments) throws StrategyException;
	
	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(long idExcepcioDocumentacio) throws StrategyException;
}

