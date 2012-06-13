package es.caib.rolsac.api.v2.fetVital.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FetVitalQueryServiceEJBStrategy implements FetVitalQueryServiceStrategy {

    private FetVitalQueryServiceDelegate fetVitalQueryServiceDelegate;
    
    public void setFetVitalQueryServiceDelegate(FetVitalQueryServiceDelegate fetVitalQueryServiceDelegate) {
        this.fetVitalQueryServiceDelegate = fetVitalQueryServiceDelegate;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        return fetVitalQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        return fetVitalQueryServiceDelegate.llistarProcedimentsLocals(id, procedimentCriteria);
    }

    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id,
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        return fetVitalQueryServiceDelegate.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
    }

    public int getNumFitxes(long id) {
        return fetVitalQueryServiceDelegate.getNumFitxes(id);
    }

    public int getNumProcedimentsLocals(long id) {
        return fetVitalQueryServiceDelegate.getNumProcedimentsLocals(id);
    }

    public int getNumFetsVitalsAgrupacionsFV(long id) {
        return fetVitalQueryServiceDelegate.getNumFetsVitalsAgrupacionsFV(id);
    }

    public ArxiuDTO getFotografia(long idFoto) {
        return fetVitalQueryServiceDelegate.getFotografia(idFoto);
    }
    
    public ArxiuDTO getIcona(long idIcona) {
        return fetVitalQueryServiceDelegate.getIcona(idIcona);
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return fetVitalQueryServiceDelegate.getIconaGran(idIconaGran);
    }

}
