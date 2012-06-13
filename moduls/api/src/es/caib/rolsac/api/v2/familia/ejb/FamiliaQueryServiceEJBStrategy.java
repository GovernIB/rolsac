package es.caib.rolsac.api.v2.familia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FamiliaQueryServiceEJBStrategy implements FamiliaQueryServiceStrategy {

    FamiliaQueryServiceDelegate delegate;
    FamiliaQueryServiceLocator locator;

    public int getNumProcedimentsLocals(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumIcones(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
