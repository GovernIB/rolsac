package es.caib.rolsac.api.v2.familia;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;

public interface FamiliaQueryService {

    int getNumProcedimentsLocals();

    int getNumIcones();

    List<ProcedimentQueryService> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria);

    List<IconaFamiliaQueryService> llistarIcones(IconaFamiliaCriteria iconaFamiliaCriteria);

}
