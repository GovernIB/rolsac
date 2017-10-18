package es.caib.rolsac.api.v2.materia;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public interface MateriaQueryServiceStrategy {
    
    public ArxiuDTO getFotografia(long idFoto) throws StrategyException;
    
    public ArxiuDTO getIcona(long idIcona) throws StrategyException;
    
    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException;
    
    public int getNumFitxes(long id) throws StrategyException;

    public int getNumAgrupacioMateries(long id) throws StrategyException;

    public int getNumProcedimentsLocals(long id) throws StrategyException;

    public int getNumServicios(long id) throws StrategyException;

    public int getNumUnitatsMateries(long id) throws StrategyException;

    public int getNumIcones(long id) throws StrategyException;

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws StrategyException;

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException;

    public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException;

    public List<IconaMateriaDTO> llistarIconesMateries(long id, IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException;

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

    public List<UnitatMateriaDTO> llistarUnitatsMateria(long id, UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException;

	public void setUrl(String url);

}
