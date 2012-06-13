package es.caib.rolsac.api.v2.normativa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface NormativaQueryService {

    int getNumAfectades();

    int getNumAfectants();

    int getNumProcediments();

    TipusQueryServiceAdapter obtenirTipus();
    
    List<NormativaQueryServiceAdapter> llistarAfectades();

    List<NormativaQueryServiceAdapter> llistarAfectants();

    List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria);
    
    ButlletiQueryServiceAdapter obtenirButlleti();
    
    UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa();
    
    ArxiuQueryServiceAdapter obtenirArxiuNormativa();

}
