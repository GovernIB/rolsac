package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public interface AgrupacioFetVitalQueryServiceStrategy {

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) throws StrategyException;

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) throws StrategyException;
   
    public ArxiuDTO getFotografia(long idFoto) throws StrategyException;
    
    public ArxiuDTO getIcona(long idIcona) throws StrategyException;
    
    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException;
    
    public int getNumFetsVitals(long id) throws StrategyException;

	public void setUrl(String url); 
    
}
