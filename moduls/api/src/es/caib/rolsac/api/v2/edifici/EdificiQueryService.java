package es.caib.rolsac.api.v2.edifici;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface EdificiQueryService {

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

    public int getNumUnitatsAdministratives() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirFotoPequenya() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirFotoGrande() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirPlano() throws QueryServiceException;

}
