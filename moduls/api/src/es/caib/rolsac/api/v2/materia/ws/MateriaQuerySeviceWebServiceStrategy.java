package es.caib.rolsac.api.v2.materia.ws;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuCriteria;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQuerySeviceWebServiceStrategy implements MateriaQueryServiceStrategy {

    MateriaQueryServiceGateway gateway;
    
    public ArxiuDTO obtenirDistribucioCompetencial(long id, ArxiuCriteria arxiuCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumFitxes(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumAgrupacioMateries(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumProcedimentsLocals(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumUnitatsMateries(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumIcones(long id) {
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

}
