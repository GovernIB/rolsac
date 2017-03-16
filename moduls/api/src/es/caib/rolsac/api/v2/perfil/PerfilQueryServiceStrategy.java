package es.caib.rolsac.api.v2.perfil;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;

public interface PerfilQueryServiceStrategy {

    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException;

    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException;
    
    public int getNumIconesFamilia(long id) throws StrategyException;
    
    public int getNumIconesMateria(long id) throws StrategyException;

	public void setUrl(String url);

}
