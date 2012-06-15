package es.caib.rolsac.api.v2.familia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FamiliaQueryServiceEJBStrategy implements FamiliaQueryServiceStrategy {

    private FamiliaQueryServiceDelegate familiaQueryServiceDelegate;

    public void setFamiliaQueryServiceDelegate(FamiliaQueryServiceDelegate familiaQueryServiceDelegate) {
        this.familiaQueryServiceDelegate = familiaQueryServiceDelegate;
    }

    public int getNumProcedimentsLocals(long id) {
        return familiaQueryServiceDelegate.getNumProcedimentsLocals(id);
    }

    public int getNumIcones(long id) {
        return familiaQueryServiceDelegate.getNumIcones(id);
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        return familiaQueryServiceDelegate.llistarProcedimentsLocals(id, procedimentCriteria);
    }

    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        return familiaQueryServiceDelegate.llistarIcones(id, iconaFamiliaCriteria);
    }

}
