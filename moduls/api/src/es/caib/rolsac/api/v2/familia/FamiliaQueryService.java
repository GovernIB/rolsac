package es.caib.rolsac.api.v2.familia;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public interface FamiliaQueryService {

    public int getNumProcedimentsLocals() throws QueryServiceException;

    public int getNumIcones() throws QueryServiceException;

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;

    public List<IconaFamiliaQueryServiceAdapter> llistarIcones(IconaFamiliaCriteria iconaFamiliaCriteria) throws QueryServiceException;

}
