package es.caib.rolsac.api.v2.materia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQueryServiceDelegate {

    private MateriaQueryServiceEJBLocator materiaQueryServiceLocator;
    

    public void setMateriaQueryServiceLocator(MateriaQueryServiceEJBLocator materiaQueryServiceLocator) {
        this.materiaQueryServiceLocator = materiaQueryServiceLocator;
    }

    public ArxiuDTO getFotografia(long idFoto) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getFotografia(idFoto);
    }

    public ArxiuDTO getIcona(long idIcona) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getIcona(idIcona);
    }

    public ArxiuDTO getIconaGran(long idIconaGran) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getIconaGran(idIconaGran);
    }
   
    public int getNumFitxes(long id) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getNumFitxes(id);
    }

    public int getNumAgrupacioMateries(long id) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getNumAgrupacioMateries(id);
    }

    public int getNumProcedimentsLocals(long id) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getNumProcedimentsLocals(id);
    }

    public int getNumUnitatsMateries(long id) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getNumUnitatsMateries(id);
    }

    public int getNumIcones(long id) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.getNumIcones(id);
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.llistarProcedimentsLocals(id, procedimentCriteria);
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.llistarFitxes(id, fitxaCriteria);
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.llistarUnitatsMateria(id, unitatMateriaCriteria);
    }

    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.llistarIconesMateries(id, iconaMateriaCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        MateriaQueryServiceEJB ejb = materiaQueryServiceLocator.getMateriaQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }
    
}
