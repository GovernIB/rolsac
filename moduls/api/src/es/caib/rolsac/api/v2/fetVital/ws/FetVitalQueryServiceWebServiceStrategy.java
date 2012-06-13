package es.caib.rolsac.api.v2.fetVital.ws;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FetVitalQueryServiceWebServiceStrategy implements FetVitalQueryServiceStrategy {

    FetVitalQueryServiceGateway gateway;

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<ProcedimentDTO> llistarFetsVitalsProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id,
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumFitxes(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumProcedimentsLocals(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFetsVitalsAgrupacionsFV(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public ArxiuDTO getFotografia(long idFoto) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO getIcona(long idIcona) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO getIconaGran(long idIconaGran) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
