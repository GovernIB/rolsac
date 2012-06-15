package es.caib.rolsac.api.v2.familia;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public interface FamiliaQueryService {

    int getNumProcedimentsLocals();

    int getNumIcones();

    List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria);

    List<IconaFamiliaQueryServiceAdapter> llistarIcones(IconaFamiliaCriteria iconaFamiliaCriteria);

}
