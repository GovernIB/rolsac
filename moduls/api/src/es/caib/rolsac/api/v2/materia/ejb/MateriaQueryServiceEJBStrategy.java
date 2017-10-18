package es.caib.rolsac.api.v2.materia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQueryServiceEJBStrategy implements MateriaQueryServiceStrategy {

    private MateriaQueryServiceDelegate materiaQueryServiceDelegate;

    public void setMateriaQueryServiceDelegate(MateriaQueryServiceDelegate materiaQueryServiceDelegate) {
        this.materiaQueryServiceDelegate = materiaQueryServiceDelegate;
    }

    public ArxiuDTO getFotografia(long idFoto) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getFotografia(idFoto);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO getIcona(long idIcona) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getIcona(idIcona);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getIconaGran(idIconaGran);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public int getNumFitxes(long id) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getNumFitxes(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumAgrupacioMateries(long id) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getNumAgrupacioMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumProcedimentsLocals(long id) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getNumProcedimentsLocals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public int getNumServicios(long id) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getNumServicios(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUnitatsMateries(long id) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getNumUnitatsMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumIcones(long id) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.getNumIcones(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarProcedimentsLocals(id, procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarServicios(id, servicioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarIconesMateries(id, iconaMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException {
        try {
            return materiaQueryServiceDelegate.llistarUnitatsMateria(id, unitatMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No se utilizan en EJB.
	}

}
