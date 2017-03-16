package es.caib.rolsac.api.v2.agrupacioMateria;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public interface AgrupacioMateriaQueryServiceStrategy {

    public SeccioDTO obtenirSeccio(long idSeccio) throws StrategyException;

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException;

    public int getNumMateries(long id) throws StrategyException;

	public void setUrl(String url);

}
