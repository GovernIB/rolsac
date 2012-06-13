package es.caib.rolsac.api.v2.familia;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.familia.ejb.FamiliaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;

public class FamiliaQueryServiceAdapter extends FamiliaDTO implements FamiliaQueryService {

    // @Injected
    FamiliaQueryServiceStrategy familiaQueryServiceStrategy;

    public FamiliaQueryServiceAdapter() {
        // FIXME: don't harcode the familiaQueryServiceEJBStrategy.
        familiaQueryServiceStrategy =  new FamiliaQueryServiceEJBStrategy();
    }

    public FamiliaQueryServiceAdapter(FamiliaDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public int getNumProcedimentsLocals() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumIcones() {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<ProcedimentQueryService> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IconaFamiliaQueryService> llistarIcones(IconaFamiliaCriteria iconaFamiliaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
