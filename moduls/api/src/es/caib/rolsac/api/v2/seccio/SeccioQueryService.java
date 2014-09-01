package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface SeccioQueryService {

    public int getNumFilles() throws QueryServiceException;

    public int getNumFitxes() throws QueryServiceException;

    public int getNumPares() throws QueryServiceException;
    
    public int getNumUnitatsAdministratives() throws QueryServiceException;

    public List<SeccioQueryServiceAdapter> llistarPares() throws QueryServiceException;

    public List<SeccioQueryServiceAdapter> llistarFilles(SeccioCriteria seccioCriteria) throws QueryServiceException;

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria, FitxaUACriteria fitxaUACriteria) throws QueryServiceException;
    
    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria, FitxaUACriteria fitxaUACriteria) throws QueryServiceException;
    
    public SeccioQueryServiceAdapter obtenirPare() throws QueryServiceException;

}
