package es.caib.rolsac.api.v2.fetVital.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FetVitalQueryServiceDelegate {

    private FetVitalQueryServiceEJBLocator fetVitalQueryServiceLocator;
    
    public void setFetVitalQueryServiceLocator(FetVitalQueryServiceEJBLocator fetVitalQueryServiceLocator) {
        this.fetVitalQueryServiceLocator = fetVitalQueryServiceLocator;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.llistarFitxes(id, fitxaCriteria);
    }
    
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.llistarProcedimentsLocals(id, procedimentCriteria);
    }
       
    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
    }
    
    public ArxiuDTO getFotografia(long idFoto) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.getFotografia(idFoto);
    }

    public ArxiuDTO getIcona(long idIcona) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.getIcona(idIcona);
    }

    public ArxiuDTO getIconaGran(long idIconaGran) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.getIconaGran(idIconaGran);
    }

    public int getNumFitxes(long id) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.getNumFitxes(id);
    }

    public int getNumProcedimentsLocals(long id) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.getNumProcedimentsLocals(id);
    }

    public int getNumFetsVitalsAgrupacionsFV(long id) {
        FetVitalQueryServiceEJB ejb = fetVitalQueryServiceLocator.getFetVitalQueryServiceEJB();
        return ejb.getNumFetsVitalsAgrupacionsFV(id);
    }
    
}
