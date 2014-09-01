package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface ProcedimentQueryServiceStrategy {

    public int getNumTramits(long id) throws StrategyException;

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws StrategyException;

    public int getNumMateries(long id) throws StrategyException;

    public int getNumDocuments(long id) throws StrategyException;

    public int getNumFetsVitals(long id) throws StrategyException;

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws StrategyException;

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException;

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException;

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetsVitalsCriteria) throws StrategyException;

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) throws StrategyException;
    
    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws StrategyException;

}
