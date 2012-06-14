package es.caib.rolsac.api.v2.materia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQueryServiceEJBStrategy implements MateriaQueryServiceStrategy {

    private MateriaQueryServiceDelegate materiaQueryServiceDelegate;

    public void setMateriaQueryServiceDelegate(MateriaQueryServiceDelegate materiaQueryServiceDelegate) {
        this.materiaQueryServiceDelegate = materiaQueryServiceDelegate;
    }

    public ArxiuDTO getFotografia(long idFoto) {
        return materiaQueryServiceDelegate.getFotografia(idFoto);
    }
    
    public ArxiuDTO getIcona(long idIcona) {
        return materiaQueryServiceDelegate.getIcona(idIcona);
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return materiaQueryServiceDelegate.getIconaGran(idIconaGran);
    }

    
    public int getNumFitxes(long id) {
        return materiaQueryServiceDelegate.getNumFitxes(id);
    }

    public int getNumAgrupacioMateries(long id) {
        return materiaQueryServiceDelegate.getNumAgrupacioMateries(id);
    }

    public int getNumProcedimentsLocals(long id) {
        return materiaQueryServiceDelegate.getNumProcedimentsLocals(id);
    }

    public int getNumUnitatsMateries(long id) {
        return materiaQueryServiceDelegate.getNumUnitatsMateries(id);
    }

    public int getNumIcones(long id) {
        return materiaQueryServiceDelegate.getNumIcones(id);
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        return materiaQueryServiceDelegate.llistarProcedimentsLocals(id, procedimentCriteria);
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        return materiaQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        return materiaQueryServiceDelegate.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
    }

    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        return materiaQueryServiceDelegate.llistarIconesMateries(id, iconaMateriaCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return materiaQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) {
        return materiaQueryServiceDelegate.llistarUnitatsMateria(id, unitatMateriaCriteria);
    }

}
