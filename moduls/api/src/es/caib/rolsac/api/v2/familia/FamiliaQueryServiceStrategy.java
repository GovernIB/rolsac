package es.caib.rolsac.api.v2.familia;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;

public interface FamiliaQueryServiceStrategy {

    public int getNumProcedimentsLocals(long id) throws StrategyException;

    public int getNumServicios(long id) throws StrategyException;

    public int getNumIcones(long id) throws StrategyException;

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws StrategyException;

    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException;

	public void setUrl(String url);

}
