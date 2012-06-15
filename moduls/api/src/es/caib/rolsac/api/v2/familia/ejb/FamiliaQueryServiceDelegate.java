package es.caib.rolsac.api.v2.familia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FamiliaQueryServiceDelegate {

    private FamiliaQueryServiceEJBLocator familiaQueryServiceLocator;

    public void setFamiliaQueryServiceLocator(FamiliaQueryServiceEJBLocator familiaQueryServiceLocator) {
        this.familiaQueryServiceLocator = familiaQueryServiceLocator;
    }
    
    public int getNumProcedimentsLocals(long id) {
        FamiliaQueryServiceEJB ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
        return ejb.getNumProcedimentsLocals(id);
    }
    
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        FamiliaQueryServiceEJB ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
        return ejb.llistarProcedimentsLocals(id, procedimentCriteria);
    }
    
    public int getNumIcones(long id) {
        FamiliaQueryServiceEJB ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
        return ejb.getNumIcones(id);
    }
    
    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        FamiliaQueryServiceEJB ejb = familiaQueryServiceLocator.getFamiliaQueryServceEJB();
        return ejb.llistarIcones(id, iconaFamiliaCriteria);
    }
    
}
